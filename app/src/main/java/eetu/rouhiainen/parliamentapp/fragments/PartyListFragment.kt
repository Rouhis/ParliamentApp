package eetu.rouhiainen.parliamentapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.adapters.PartyListAdapter
import eetu.rouhiainen.parliamentapp.databinding.FragmentPartyListBinding
import eetu.rouhiainen.parliamentapp.viewmodels.PartyListViewModel

/**
 * PartyListFragment is a Fragment class that displays a list of parties.
 */
class PartyListFragment : Fragment() {
    private lateinit var partyViewModel: PartyListViewModel
    private lateinit var adapter: PartyListAdapter
    /**
     * Creates the view for the fragment and sets up the binding, view model, adapter, and title.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The view for the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(
            inflater, R.layout.fragment_party_list, container, false)

        partyViewModel = ViewModelProvider(this)[PartyListViewModel::class.java]
        adapter = PartyListAdapter(requireContext(), partyViewModel.parties)

        /**
         * Observes the parties in the view model and updates the adapter when there is a change.
         */
        partyViewModel.parties.observe(viewLifecycleOwner, Observer {(binding.partyList.adapter as PartyListAdapter).notifyDataSetChanged()})
        binding.partyList.layoutManager = LinearLayoutManager(activity)
        binding.partyList.adapter = activity?.let {
            PartyListAdapter(it, partyViewModel.parties)
        }

        /**
         * Sets the title of the action bar to "Parliament parties".
         */
        (activity as AppCompatActivity).supportActionBar?.title = "Parliament parties"
        return binding.root
    }
}

