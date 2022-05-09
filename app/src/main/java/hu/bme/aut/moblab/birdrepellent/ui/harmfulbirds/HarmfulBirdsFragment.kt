package hu.bme.aut.moblab.birdrepellent.ui.harmfulbirds

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.moblab.birdrepellent.R
import hu.bme.aut.moblab.birdrepellent.adapter.HarmfulBirdsAdapter
import hu.bme.aut.moblab.birdrepellent.databinding.FragmentHarmfulBirdsBinding
import hu.bme.aut.moblab.birdrepellent.network.model.sync.HarmfulBirdDto
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.util.Resource

@AndroidEntryPoint
class HarmfulBirdsFragment : Fragment(R.layout.fragment_harmful_birds), HarmfulBirdsAdapter.OnItemClickListener {
    private val viewModel: HarmfulBirdsViewModel by viewModels()
    var enemies: MutableList<String> = mutableListOf()
    lateinit var harmfulBirds: List<HarmfulBird>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Harmful birds"
        val binding = FragmentHarmfulBirdsBinding.bind(view)
        val harmfulBirdsAdapter = HarmfulBirdsAdapter(this)

        binding.apply {
            //Rec. view
            rvBirdList.apply {
                adapter = harmfulBirdsAdapter
                setHasFixedSize(true)
            }

            //Add btn
            btnCreateNewHarmfulBird.setOnClickListener(
                View.OnClickListener {
                    viewModel.createHarmfulBird(etNewHarmfulBirdName.text.toString())
                    etNewHarmfulBirdName.setText("") //empty
                }
            )

            //Keyboard enter
            etNewHarmfulBirdName.setOnKeyListener(object: View.OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        val name = etNewHarmfulBirdName.text.toString()
                        etNewHarmfulBirdName.setText("") //empty
                        viewModel.createHarmfulBird(name)
                        return true
                    }
                    return false
                }
            })

            btnToggleRepeller.setOnClickListener(View.OnClickListener {
                //Enable repeller
                if (!viewModel.repellerState){
                    if (enemies.size > 0){
                        viewModel.repellerState = true;
                        tvRepellerState.setText("Started")
                        var randomEnemy: String = enemies[(0..enemies.size-1).random()]
                        viewModel.loadBirdSounds(randomEnemy)
                    }
                }
                //Disable repeller
                else{
                    viewModel.repellerState = false;
                    viewModel.mediaPlayer.stop()
                    tvRepellerState.setText("Stopped")
                }
            })

            btnUpload.setOnClickListener(View.OnClickListener {
                uploadDialog();
            })

            btnDelete.setOnClickListener(View.OnClickListener {
                deleteDialog();
            })

            btnDownload.setOnClickListener(View.OnClickListener {
                downloadDialog();
            })

        }

        viewModel.getHarmfulBirds().observe(this) {
            harmfulBirdsAdapter.submitList(it)
            harmfulBirds = it;
            enemies.clear()
            for (hb in it){
                if (hb.active){
                    for (eb in hb.enemies){
                        enemies.add(eb)
                    }
                }
            }
        }

        viewModel.birdSounds.observe(this) {
            when(it){
                is Resource.Success -> {
                    it.data?.let { response ->
                        if (viewModel.repellerState){
                            var randomSoundUrl = response.recordings?.get((0..response.recordings.size-1).random())?.file
                            Log.d("randomsound", randomSoundUrl!!)
                            viewModel.play(randomSoundUrl)
                            binding.apply {
                                tvRepellerState.setText("Playing")
                            }
                        }
                        else{
                            binding.apply {
                                tvRepellerState.setText("Stopped")
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        binding.apply {
                            tvRepellerState.setText(message)
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        tvRepellerState.setText("Downloading")
                    }
                }
            }
        }

        viewModel.mediaPlayer.setOnCompletionListener {
            if (enemies.size > 0 && viewModel.repellerState) {
                var randomEnemy: String = enemies[(0..enemies.size - 1).random()]
                viewModel.loadBirdSounds(randomEnemy)
            }
        }
    }

    override fun onItemClick(harmfulBird: HarmfulBird) {
        val bundle = bundleOf("HARMFUL_BIRD_ID" to harmfulBird.id)
        //Navigate to details page
        findNavController().navigate(
            R.id.enemyBirdsFragment, bundle
        )
    }

    override fun onItemDelete(harmfulBird: HarmfulBird) {
        viewModel.deleteHarmfulBird(harmfulBird)
    }

    override fun onItemToggle(harmfulBird: HarmfulBird, active: Boolean) {
        harmfulBird.active = active;
        viewModel.updateHarmfulBird(harmfulBird);
    }

    fun uploadDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Upload config")
        val input = EditText(activity!!)
        input.setHint("Config key")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setView(input)
        builder.setPositiveButton("Upload") { _, _ ->
            var harmfulBirdDtos = mutableListOf<HarmfulBirdDto>()
            for(hb in harmfulBirds){
                harmfulBirdDtos.add(viewModel.convertHarmfulBirdToHarmfulBirdDto(hb));
            }
            viewModel.uploadConfig(input.text.toString(), harmfulBirdDtos)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    fun downloadDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Download config")
        val input = EditText(activity!!)
        input.setHint("Config key")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setView(input)
        builder.setPositiveButton("Download") { _, _ ->
            viewModel.downloadConfig(input.text.toString())
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    fun deleteDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Delete config")
        val input = EditText(activity!!)
        input.setHint("Config key")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setView(input)
        builder.setPositiveButton("Delete") { _, _ ->
            viewModel.deleteConfig(input.text.toString())
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}