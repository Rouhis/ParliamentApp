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

class PartyListFragment : Fragment() {
    private lateinit var partyViewModel: PartyListViewModel
    private lateinit var adapter: PartyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(
            inflater, R.layout.fragment_party_list, container, false)

        partyViewModel = ViewModelProvider(this)[PartyListViewModel::class.java]
        adapter = PartyListAdapter(requireContext(), partyViewModel.parties)

        partyViewModel.parties.observe(viewLifecycleOwner, Observer {(binding.partyList.adapter as PartyListAdapter).notifyDataSetChanged()})
        binding.partyList.layoutManager = LinearLayoutManager(activity)
        binding.partyList.adapter = activity?.let {
            PartyListAdapter(it, partyViewModel.parties)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Parliament parties"
        return binding.root
    }
}
