package edu.uca.ni.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import edu.uca.ni.movie.databinding.ItemList2Binding
import edu.uca.ni.movie.fragments.idiListFragmentDirections
import edu.uca.ni.movie.model.Idioma

class IdiListAdapter: RecyclerView.Adapter<IdiListAdapter.IdiListViewHolder>() {

    private var idiomaList = emptyList<Idioma>()

    class IdiListViewHolder(val binding: ItemList2Binding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdiListViewHolder {
        return IdiListViewHolder(ItemList2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: IdiListViewHolder, position: Int) {
        val item = idiomaList[position]

        holder.binding.txtNombre.text = item.nombre.toString()

        holder.binding.listLayout2.setOnClickListener { view: View ->
            val action = idiListFragmentDirections.actionIdiListFragmentToIdiUpdateFragment(item)
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun getItemCount(): Int = idiomaList.size

    fun setData(idioma: List<Idioma>){
        this.idiomaList = idioma
        notifyDataSetChanged()
    }
}