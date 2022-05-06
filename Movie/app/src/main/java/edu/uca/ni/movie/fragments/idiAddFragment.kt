package edu.uca.ni.movie.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.uca.ni.movie.R
import edu.uca.ni.movie.databinding.FragmentIdiAddBinding
import edu.uca.ni.movie.model.Idioma
import edu.uca.ni.movie.viewmodel.IdiomaViewModel


class idiAddFragment : Fragment() {

    private lateinit var midiomaViewModel: IdiomaViewModel
    private var _binding: FragmentIdiAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdiAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        midiomaViewModel = ViewModelProvider(this).get(IdiomaViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            insertToDataBase()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun insertToDataBase() {
        val nombre = binding.etNombre.text.toString()

        if(inputCheck(nombre)){

            val idioma =  Idioma(0,nombre)

            midiomaViewModel.addIdioma(idioma)

            Toast.makeText(requireContext(),"Agregado Correctamente", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_idiAddFragment_to_idiListFragment)

        } else {

        Toast.makeText(requireContext(),"LLene todos los campos",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(nombre: String): Boolean {

        return !(TextUtils.isEmpty(nombre))
    }


}