package eetu.rouhiainen.parliamentapp.data

import androidx.lifecycle.LiveData

object ParliamentRepo {

    suspend fun addMember(
        hetekaId: Int,
        seatNumber: Int,
        lastname: String,
        firstname: String,
        party: String,
        minister: Boolean,
        pictureUrl: String,

    ) {
        ParliamentDB.getInstance()
            .parliamentDao
            .insertOrUpdate(
                Member(
                    hetekaId,
                    seatNumber,
                    lastname,
                    firstname,
                    party,
                    minister,
                    pictureUrl,
                )
            )
    }

    fun getMemberByHetekaId(ID: Int) {
        ParliamentDB.getInstance()
            .parliamentDao
            .getByhetekaId(ID)
    }


    fun getPlayers(): LiveData<List<Member>> = ParliamentDB.getInstance().parliamentDao.getAll()


}