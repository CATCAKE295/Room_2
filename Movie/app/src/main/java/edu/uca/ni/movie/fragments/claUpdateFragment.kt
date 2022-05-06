package edu.uca.ni.movie.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import edu.uca.ni.movie.R
import edu.uca.ni.movie.databinding.FragmentClaUpdateBinding
import edu.uca.ni.movie.model.Clasificacion
import edu.uca.ni.movie.viewmodel.ClasificacionViewModel
import androidx.navigation.fragment.findNavController


class claUpdateFragment : Fragment() {


    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private val args by navArgs<claUpdateFragmentArgs>()
    private  var _binding: FragmentClaUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClaUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)

        binding.etAbreviacionupdate.setText(args.currentClasificationItem.abreviacion)
        binding.etClasificacionupdate.setText(args.currentClasificationItem.n_clasificacion)

        binding.btnActualizar.setOnClickListener { updateItem() }

        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun updateItem() {
        val uAbreviacion = binding.etAbreviacionupdate.text.toString()
        val uClasificacion = binding.etClasificacionupdate.text.toString()
        if (inputCheck(uAbreviacion,uClasificacion)){

            val upClasificacion = Clasificacion(args.currentClasificationItem.id,uClasificacion,uAbreviacion)

            mclasificacionViewModel.updateClasificacion(upClasificacion)

            Toast.makeText(requireContext(),"Actualizado Correctamente", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_claUpdateFragment_to_claListFragment)


        }else{

            Toast.makeText(requireContext(),"LLene todos los campos", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(uAbreviacionn: String,uClasificacion: String ): Boolean {

        return  !(TextUtils.isEmpty(uAbreviacionn) && TextUtils.isEmpty(uClasificacion))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteClasificacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mclasificacionViewModel.deleteClasificacion(args.currentClasificationItem)

            Toast.makeText(requireContext(),"Borrado con exito la clasificacion ${args.currentClasificationItem.n_clasificacion}", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_claUpdateFragment_to_claListFragment)

        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Borrar ${args.currentClasificationItem.n_clasificacion}?")
        builder.setMessage("Estas seguro de querer borrar ${args.currentClasificationItem.n_clasificacion}?")
        builder.create().show()
    }

}