package edu.uca.ni.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation
import edu.uca.ni.movie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy {AnimationUtils.loadAnimation(this,R.anim.to_anim_bottom)}

    private var clicked = false

    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabtnMenu.setOnClickListener {
            DisplayButtonClicked()
        }

        binding.fabtnIdioma.setOnClickListener {
            Navigation.findNavController(this,R.id.fragment).navigate(R.id.action_claListFragment_to_idiListFragment)
        }

        binding.fabtnClasificacion.setOnClickListener {
            Navigation.findNavController(this,R.id.fragment).navigate(R.id.action_idiListFragment_to_claListFragment)

        }

    }

    private fun  DisplayButtonClicked() {

        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        binding.apply {
            if(!clicked){

                fabtnIdioma.startAnimation(fromBottom)
                fabtnClasificacion.startAnimation(fromBottom)
                fabtnMenu.startAnimation(rotateOpen)
            }else{

                fabtnIdioma.startAnimation(toBottom)
                fabtnClasificacion.startAnimation(toBottom)
                fabtnMenu.startAnimation(rotateClose)

            }
        }

    }

    private fun setVisibility(clicked: Boolean) {
        binding.apply {
            if (!clicked) {

                fabtnIdioma.visibility = View.VISIBLE
                fabtnClasificacion.visibility = View.VISIBLE


            } else {

                fabtnIdioma.visibility = View.INVISIBLE
                fabtnClasificacion.visibility = View.INVISIBLE

            }
        }
    }

}