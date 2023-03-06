package eetu.rouhiainen.parliamentapp.viewmodels
/**
 * Eetu Rouhiainen
 * 2113716
 * 06.03.2023
 *
 * */
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

/**
 * Makes a LiveData list of all parties in the parliament
 * */
class PartyListViewModel() : ViewModel() {

    val parties: LiveData<List<String>> = ParliamentRepo.getParties()

}
