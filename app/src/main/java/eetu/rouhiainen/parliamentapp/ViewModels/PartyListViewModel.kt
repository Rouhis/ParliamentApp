package eetu.rouhiainen.parliamentapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentDB
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

class PartyListViewModel() : ViewModel() {

    val parties: LiveData<List<String>> = ParliamentRepo.getParties()

}
