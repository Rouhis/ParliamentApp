package eetu.rouhiainen.parliamentapp.viewmodels
/**
 * Eetu Rouhiainen
 * 2113716
 * 06.03.2023
 *
 * */
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo
/** ViewModel for MemberFragment */
class MemberViewModel() : ViewModel() {
    /** Takes out all members from the database */
    val membersOfParty: LiveData<List<Member>> = ParliamentRepo.getMembers()

    /** Returns a single members seat number and if they are a minister or no */
    fun memberInfo(memberList: List<Member>, memberName: String): String {
        return memberList.filter { it.firstname + " " + it.lastname == memberName }
            .joinToString(" ") {
                "Seat number: " + it.seatNumber + "\nMinister: " + if (it.minister) "Yes!" else "No"
            }
    }

    /** Returns the picturUrl of a single member */
    fun memberPhoto(memberList: List<Member>, memberName: String): String {
        return memberList.filter { it.firstname + " " + it.lastname == memberName }
            .joinToString() { it.pictureUrl }
    }
}
