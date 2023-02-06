package eetu.rouhiainen.parliamentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eetu.rouhiainen.parliamentapp.data.Member
import eetu.rouhiainen.parliamentapp.data.ParliamentApi
import eetu.rouhiainen.parliamentapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener {
            viewModel.readPlayers()
        }

        viewModel.players.observe(this) {
            println("players changed!!!")
            binding.textView.text = it.joinToString(", ")
        }
    }
}

class MainActivityViewModel: ViewModel() {
    var players: MutableLiveData<List<Member>> = MutableLiveData()

    fun readPlayers() {
        viewModelScope.launch {
            try {
                players.value = ParliamentApi.retrofitService.getMemberList()
                println("Read players from NW with great success.")
            } catch (e: Exception) {
                println("asd" + players)
                println("No luck in reading players from NW: ${e}")
            }

        }
    }
}
