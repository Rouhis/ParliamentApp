package eetu.rouhiainen.parliamentapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

class MemberListViewModel(application: Application): AndroidViewModel(application) {

    val membersOfParty: LiveData<List<Member>> = ParliamentRepo.getMembers()
    fun extractMembers(memberList: List<Member>, partyName: String): List<String> {
        return memberList.filter { it.party == partyName }.map { it.firstname + " " + it.lastname}
    }
}