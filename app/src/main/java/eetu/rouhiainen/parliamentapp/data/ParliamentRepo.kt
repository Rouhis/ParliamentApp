package eetu.rouhiainen.parliamentapp.data

import androidx.lifecycle.LiveData

object ParliamentRepo {

    fun getPlayers(): LiveData<List<Member>> = ParliamentDB.getInstance().parliamentDao.getAll()

    fun getMemberByHetekaId(ID: Int) {
        ParliamentDB.getInstance().parliamentDao.getAll()
    }
}