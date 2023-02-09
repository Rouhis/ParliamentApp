package eetu.rouhiainen.parliamentapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import eetu.rouhiainen.parliamentapp.MyApp.Companion.appContext


object ParliamentRepo {

    fun getMembers(): LiveData<List<Member>> = ParliamentDB.getInstance(appContext).parliamentDao.getAll()

    fun getParties(): LiveData<List<String>> =
        Transformations.map(ParliamentDB.getInstance(appContext).parliamentDao.getParties()) { parties ->
            parties.map { it.party }
        }
}



