package eetu.rouhiainen.parliamentapp.data

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
}