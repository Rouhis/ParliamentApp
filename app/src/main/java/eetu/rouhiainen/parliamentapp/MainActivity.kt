package eetu.rouhiainen.parliamentapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import eetu.rouhiainen.parliamentapp.MyApp.Companion.appContext
import eetu.rouhiainen.parliamentapp.data.ParliamentApi
import eetu.rouhiainen.parliamentapp.data.ParliamentDB
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        addMembersToDB()

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun addMembersToDB() {
        GlobalScope.launch(
            Dispatchers.IO,
            CoroutineStart.DEFAULT) {
            try {
                val listResult = ParliamentApi.retrofitService.getMemberList()
                println(listResult.count())
                listResult.forEach {
                    ParliamentDB.getInstance(appContext).parliamentDao.insertOrUpdate(it)
                }
            } catch (e: Exception) {
                println("Failure: ${e.message}")
            }
        }
    }
}

