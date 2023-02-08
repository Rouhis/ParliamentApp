package eetu.rouhiainen.parliamentapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import eetu.rouhiainen.parliamentapp.MyApp.Companion.appContext
import eetu.rouhiainen.parliamentapp.data.ParliamentApi
import eetu.rouhiainen.parliamentapp.data.ParliamentDB
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

import eetu.rouhiainen.parliamentapp.data.ParliamentRepo.getMembers
import eetu.rouhiainen.parliamentapp.databinding.ActivityMainBinding
import eetu.rouhiainen.parliamentapp.viewmodels.PartyListViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addMembersToDB()
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

