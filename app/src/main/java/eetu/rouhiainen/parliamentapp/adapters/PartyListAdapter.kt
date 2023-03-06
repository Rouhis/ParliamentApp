package eetu.rouhiainen.parliamentapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eetu.rouhiainen.parliamentapp.R
import eetu.rouhiainen.parliamentapp.fragments.PartyListFragmentDirections


/**
 * Eetu Rouhiainen
 * 2113716
 * 8.2.2023
 *
 * ViewHolder class for the recycler view items.
 *
 * @param itemView The view to be held.
 */
class PartiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

/**
 * The adapter for the recycler view that displays the parties.
 *
 * @param context The context in which the adapter is used.
 * @param parties The live data containing the list of parties to be displayed.
 */
class PartyListAdapter(private val context: Context, var parties: LiveData<List<String>>) :
    ListAdapter<String, PartiesViewHolder>(PartyDiffCallback()) {

    /**
     * Gets the number of items in the list.
     *
     * @return The number of items in the list.
     */
    override fun getItemCount(): Int {
        return parties.value?.size ?: 0
    }

    /**
     * Creates the view holder for an item in the list.
     *
     * @param parent The parent view group.
     * @param vt The view type of the item.
     *
     * @return The view holder for the item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): PartiesViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyler_item, parent, false)
        return PartiesViewHolder(itemView)
    }

    /**
     * Binds the data for an item to the view holder.
     * Depending on the value sets the party its full name
     * Onclicklistener calls the navigation component and sets it depending on the party
     * @param holder The view holder for the item.
     * @param pos The position of the item in the list.
     */
    override fun onBindViewHolder(holder: PartiesViewHolder, pos: Int) {
        (holder.itemView as TextView).apply {
            text = parties.value?.get(pos)
            val text2:String = text.toString()
            if (text2 == "kd"){
                text = "Kristillisdemokraatit"
            }else if (text2 == "kesk"){
                text= "Keskusta"
            }else if (text2 == "kok"){
                text= "Kokoomus"
            }
            else if (text2 == "liik"){
                text= "Liike Nyt"
            }

            else if (text2 == "ps"){
                text= "Perussuomalaiset"
            }
            else if (text2 == "r"){
                text= "RKP"
            }
            else if (text2 == "sd"){
                text= "Sosiaalidemokraattinen Puolue"
            }
            else if (text2 == "vas"){
                text= "Vasemmistoliitto"
            }
            else if (text2 == "vihr"){
                text= "Vihreät"
            }
            else if (text2 == "vkk"){
                text= "Valta kuuluu kansalle"
            }
            else if (text2 == "wr"){
                text= "Eduskuntaryhmä Wille Rydman"
            }
            setOnClickListener {
               val action = PartyListFragmentDirections.actionPartyListFragmentToMemberListFragment(text2)
                it.findNavController().navigate(action)
            }
        }
    }
}

/**
 * Callback class for calculating the differences between lists of parties.
 */
class PartyDiffCallback : DiffUtil.ItemCallback<String>() {
    /**
     * Determines if two party items are the same.
     *
     * @param old The old party item.
     * @param new The new party item.
     *
     * @return True if the items are the same, false otherwise.
     */
    override fun areItemsTheSame(old: String, new: String): Boolean {
        return old == new
    }

    /**
     * Determines if the contents of two party items are the same.
     *
     * @param old The old party item.
     * @param new The new party item.
     *
     * @return True if the contents are the same, false otherwise.
     */
    override fun areContentsTheSame(old: String, new: String): Boolean {
        return old == new
    }
}

