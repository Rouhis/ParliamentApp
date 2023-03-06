package eetu.rouhiainen.parliamentapp.data
/**
 * Eetu Rouhiainen
 * 2113716
 * 06.03.2023
 *
 * */
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParliamentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(Member: Member)

    @Query("select * from members_table")
    fun getAll(): LiveData<List<Member>>

    @Query("SELECT * FROM members_table WHERE hetekaId=:id")
    fun getByhetekaId(id: Int): LiveData<List<Member>>

    @Query("SELECT * FROM members_table GROUP BY party")
    fun getParties(): LiveData<List<Member>>

    @Query("select * from members_table WHERE party=:partyName")
    fun getMembersByParty(partyName: String): LiveData<List<Member>>
}