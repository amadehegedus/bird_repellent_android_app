package hu.bme.aut.moblab.birdrepellent.ui.enemybirds

import android.app.ActionBar
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.moblab.birdrepellent.R
import hu.bme.aut.moblab.birdrepellent.adapter.EnemyBirdsAdapter
import hu.bme.aut.moblab.birdrepellent.databinding.FragmentEnemyBirdsBinding
import hu.bme.aut.moblab.birdrepellent.ui.harmfulbirds.HarmfulBirdsViewModel

@AndroidEntryPoint
class EnemyBirdsFragment : Fragment(R.layout.fragment_enemy_birds), EnemyBirdsAdapter.OnItemClickListener {
    private val viewModel: EnemyBirdsViewModel by viewModels()
    private lateinit var enemyBirdsAdapter: EnemyBirdsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Enemies of"
        val binding = FragmentEnemyBirdsBinding.bind(view)
        val harmfulBirdId = arguments?.getLong("HARMFUL_BIRD_ID")!!;
        Log.d("HARMFUL_BIRD_ID", harmfulBirdId.toString());
        enemyBirdsAdapter = EnemyBirdsAdapter(this)

        binding.apply {
            rvEnemyBirdList.apply {
                adapter = enemyBirdsAdapter
                setHasFixedSize(true)
            }

            //Add btn
            btnAddNewEnemyBird.setOnClickListener(View.OnClickListener {
                Log.d("name of new enemy", etNewEnemyBirdName.text.toString())
                viewModel.addEnemy(etNewEnemyBirdName.text.toString())
                etNewEnemyBirdName.setText("")
            })

            //Keyboard enter
            etNewEnemyBirdName.setOnKeyListener(object: View.OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        val name = etNewEnemyBirdName.text.toString()
                        etNewEnemyBirdName.setText("") //empty
                        viewModel.addEnemy(name)
                        return true
                    }
                    return false
                }
            })
        }

        viewModel.getHarmfulBirdDetails(harmfulBirdId).observe(this){
            viewModel.harmfulBird = it;
            activity?.title = "Enemies of " + it.name
            enemyBirdsAdapter.submitList(it.enemies);
            enemyBirdsAdapter.notifyDataSetChanged();
        }
    }

    override fun onItemClick(enemyBird: String) {
        Log.d("enemy clicked", enemyBird)
    }

    override fun onItemDelete(enemyBird: String) {
        Log.d("enemy delete clicked", enemyBird)
        viewModel.removeEnemy(enemyBird)
    }
}