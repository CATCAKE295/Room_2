package edu.uca.ni.movie.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uca.ni.movie.R
import edu.uca.ni.movie.adapter.ListAdapter
import edu.uca.ni.movie.databinding.FragmentClaListBinding
import edu.uca.ni.movie.viewmodel.ClasificacionViewModel


class ClaListFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private var _binding: FragmentClaListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClaListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListAdapter()
        val recyclerView = binding.clarecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)
        mclasificacionViewModel.readAllData.observe(viewLifecycleOwner, Observer { clasificacion ->
            adapter.setData(clasificacion)
        })


        setHasOptionsMenu(true)

        setUpNavigation()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpNavigation() {
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment) as NavHost
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteAllClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllClasificacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mclasificacionViewModel.deleteAllClasificacion()

            Toast.makeText(requireContext(),"Todo ha sido Borrado con exito", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Borrar Todo ?")
        builder.setMessage("Estas seguro de querer borrar todo ?")
        builder.create().show()
    }


}