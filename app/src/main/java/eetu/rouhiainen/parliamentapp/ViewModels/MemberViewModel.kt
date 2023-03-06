package eetu.rouhiainen.parliamentapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

class MemberViewModel() : ViewModel() {

    val membersOfParty: LiveData<List<Member>> = ParliamentRepo.getMembers()

    fun memberInfo(memberList: List<Member>, memberName: String): String {
        return memberList.filter { it.firstname + " " + it.lastname == memberName }
            .joinToString(" ") {
                "Seat number: " + it.seatNumber + "\nMinister: " + if (it.minister) "Yes!" else "No"
            }
    }

    fun memberPhoto(memberList: List<Member>, memberName: String): String {
        return memberList.filter { it.firstname + " " + it.lastname == memberName }
            .joinToString() { it.pictureUrl }
    }
}
