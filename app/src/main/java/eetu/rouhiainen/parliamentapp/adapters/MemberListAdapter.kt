package eetu.rouhiainen.parliamentapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.fragments.MemberListFragmentDirections

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class MemberListAdapter(private val context: Context, var members: List<String>) :
    ListAdapter<String, MemberViewHolder>(MemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyler_item, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, pos: Int) {
        (holder.itemView as TextView).apply {
            text = members[pos]
            setOnClickListener {
                val action = MemberListFragmentDirections.actionMemberListFragmentToMemberFragment()
                it.findNavController().navigate(action)
            }
        }
    }
}
// DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one
// The callback that acts as a gateway to the backing list data
class MemberDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(old: String, new: String): Boolean {
        return old == new
    }
    override fun areContentsTheSame(old: String, new: String): Boolean {
        return old == new
    }
}