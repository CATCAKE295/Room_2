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
import edu.uca.ni.movie.databinding.FragmentClaAddBinding
import edu.uca.ni.movie.model.Clasificacion
import edu.uca.ni.movie.viewmodel.ClasificacionViewModel


class claAddFragment : Fragment() {

    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private var _binding: FragmentClaAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClaAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            insertToDataBase()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun insertToDataBase() {
        val abreviacion = binding.etAbreviacion.text.toString()
        val clasificacion = binding.etClasificacion.text.toString()

        if (inputCheck(abreviacion,clasificacion)){

            val clasificacion = Clasificacion(0,clasificacion,abreviacion)

            mclasificacionViewModel.addClasificacion(clasificacion)

            Toast.makeText(requireContext(),"Agregado Correctamente",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_claAddFragment_to_claListFragment)

        } else{

            Toast.makeText(requireContext(),"LLene todos los campos",Toast.LENGTH_LONG).show()
        }
    }

    private fun  inputCheck(abreviacion: String, clasificacion:String): Boolean{

        return !(TextUtils.isEmpty(abreviacion) && TextUtils.isEmpty(clasificacion))

    }



}