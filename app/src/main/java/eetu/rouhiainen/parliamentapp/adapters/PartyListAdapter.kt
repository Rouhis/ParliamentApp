package eetu.rouhiainen.parliamentapp.adapters

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentRepo

class PartiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class PartyListAdapter(private val context: Context, var parties: LiveData<List<String>>) :
    ListAdapter<String, PartiesViewHolder>(PartyDiffCallback()) {

    override fun getItemCount(): Int {

        return parties.value?.size ?: 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): PartiesViewHolder {
        Log.d("XXX", "onCreateViewHolder()")
        // inflate creates layout including the widget objects in the layout
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyler_item, parent, false)
        return PartiesViewHolder(itemView)
    }



    // populate the view, in this case one-to-one mapping between list elements and recyclerView positions
    override fun onBindViewHolder(holder: PartiesViewHolder, i: Int) {
        (holder.itemView as TextView).apply {
            text = parties.value?.get(i)
            setOnClickListener {
                println("Miau")
            }
        }
    }
}
    class PartyDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(old: String, new: String): Boolean {
            return old == new }
        override fun areContentsTheSame(old: String, new: String): Boolean {
            return old == new
        }
    }
