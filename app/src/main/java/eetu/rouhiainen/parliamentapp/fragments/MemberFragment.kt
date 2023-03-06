package eetu.rouhiainen.parliamentapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.databinding.FragmentMemberBinding
import eetu.rouhiainen.parliamentapp.databinding.FragmentMemberListBinding
import eetu.rouhiainen.parliamentapp.viewmodels.MemberListViewModel
import eetu.rouhiainen.parliamentapp.viewmodels.MemberViewModel



class MemberFragment : Fragment() {
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMemberBinding>(
            inflater, R.layout.fragment_member, container, false)

        val member = MemberFragmentArgs.fromBundle(requireArguments()).membername
        Log.d("membername", member)
        binding.textViewName.text = member

        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]

        memberViewModel.membersOfParty.observe(viewLifecycleOwner, Observer {
            val pictureUrl = memberViewModel.memberPhoto(it, member)

            binding.textViewmemberInfo.text = memberViewModel.memberInfo(it, member)

            Picasso.get().load("https://avoindata.eduskunta.fi/$pictureUrl").into(binding.memberPhoto)
        })

        (activity as AppCompatActivity).supportActionBar?.title = member

        return binding.root

    }
}