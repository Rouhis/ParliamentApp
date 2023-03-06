package eetu.rouhiainen.parliamentapp.fragments
/**
 * Eetu Rouhiainen
 * 2113716
 * 06.03.2023
 *
 * */
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person.fromBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.adapters.MemberListAdapter
import eetu.rouhiainen.parliamentapp.adapters.MemberViewHolder
import eetu.rouhiainen.parliamentapp.adapters.PartyListAdapter
import eetu.rouhiainen.parliamentapp.databinding.FragmentMemberListBinding
import eetu.rouhiainen.parliamentapp.viewmodels.MemberListViewModel
/**
 * Creates the view for the fragment and sets up the binding, view model, adapter, and title.
 * @return The view for the fragment.
 */

class MemberListFragment : Fragment() {
    private lateinit var memberViewModel: MemberListViewModel
    private lateinit var adapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentMemberListBinding>(
            inflater, R.layout.fragment_member_list, container, false)

        val partyName = MemberListFragmentArgs.fromBundle(requireArguments()).partyName
        memberViewModel = ViewModelProvider(this)[MemberListViewModel::class.java]

        memberViewModel.membersOfParty.observe(viewLifecycleOwner, Observer {
            adapter = MemberListAdapter(requireContext(), memberViewModel.takeOutMembers(it, partyName))
            binding.memberList.adapter = adapter
        })

        binding.memberList.layoutManager = LinearLayoutManager(activity)

        (activity as AppCompatActivity).supportActionBar?.title = "Members"

        return binding.root
    }
}