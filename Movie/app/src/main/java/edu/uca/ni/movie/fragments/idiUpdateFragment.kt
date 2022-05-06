package edu.uca.ni.movie.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uca.ni.movie.R
import edu.uca.ni.movie.databinding.FragmentIdiUpdateBinding
import edu.uca.ni.movie.model.Idioma
import edu.uca.ni.movie.viewmodel.IdiomaViewModel


class idiUpdateFragment : Fragment() {

    private lateinit var midiomaViewModel: IdiomaViewModel
    private val args by navArgs<idiUpdateFragmentArgs>()
    private  var _binding: FragmentIdiUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIdiUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        midiomaViewModel = ViewModelProvider(this).get(IdiomaViewModel::class.java)


        binding.etUpnombre.setText(args.currentIdiomItem.nombre)

        binding.btnIActualizar.setOnClickListener { updateItem() }
        setHasOptionsMenu(true)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateItem() {
        val uNombre = binding.etUpnombre.text.toString()

        if (inputCheck(uNombre)){

            val upIdioma = Idioma(args.currentIdiomItem.id,uNombre)

            midiomaViewModel.updateIdioma(upIdioma)

            Toast.makeText(requireContext(),"Actualizado Correctamente", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_idiUpdateFragment_to_idiListFragment)

        } else {

            Toast.makeText(requireContext(),"LLene todos los campos", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(uNombre: String): Boolean {

        return !(TextUtils.isEmpty(uNombre))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteIdioma()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteIdioma() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            midiomaViewModel.deleteIdioma(args.currentIdiomItem)

            Toast.makeText(requireContext(),"Borrado con exito el idioma ${args.currentIdiomItem.nombre}", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_idiUpdateFragment_to_idiListFragment)
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Borrar ${args.currentIdiomItem.nombre}?")
        builder.setMessage("Estas seguro de querer borrar ${args.currentIdiomItem.nombre}?")
        builder.create().show()
    }

}