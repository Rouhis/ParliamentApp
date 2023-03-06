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
/**Viewmodel for MemberFragment*/
class MemberListViewModel(): ViewModel() {
    /**
     * Takes out all members from the db
     * */
    val membersOfParty: LiveData<List<Member>> = ParliamentRepo.getMembers()

    /**
     * Takes out members of one party and returns their first and lastname
     * */
    fun takeOutMembers(memberList: List<Member>, partyName: String): List<String> {
        return memberList.filter { it.party == partyName }.map { it.firstname + " " + it.lastname}
    }
}