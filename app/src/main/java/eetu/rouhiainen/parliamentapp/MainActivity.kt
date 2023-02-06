package eetu.rouhiainen.parliamentapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eetu.rouhiainen.parliamentapp.MyApp.App.Companion.appContext
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentApi
import eetu.rouhiainen.parliamentapp.data.ParliamentDB
import eetu.rouhiainen.parliamentapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var appContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appContext = applicationContext
        addMembers()
    }

    private fun addMembers() {
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
