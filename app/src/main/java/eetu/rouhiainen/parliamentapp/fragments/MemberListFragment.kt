package eetu.rouhiainen.parliamentapp.fragments

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MemberListFragment.newInstance] factory method to
 * create an instance of this fragment.
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
            adapter = MemberListAdapter(requireContext(), memberViewModel.extractMembers(it, partyName))
            binding.memberList.adapter = adapter
        })

        binding.memberList.layoutManager = LinearLayoutManager(activity)

        (activity as AppCompatActivity).supportActionBar?.title = "Members of the party"

        return binding.root
    }
}