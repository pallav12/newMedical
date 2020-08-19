package com.alonemusk.medicalapp.ui.new_home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import com.alonemusk.medicalapp.ui.Search.SearchViewModel

class SearchableFragment : Fragment(), MySearchableFragmentRecyclerViewAdapter.onClick {

    private var columnCount = 1
    lateinit var searchViewModel:SearchViewModel
    lateinit var adapter:MySearchableFragmentRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_searchable_fragment_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
            }
            val arrayList=ArrayList<SearchMedicine>()
            adapter=MySearchableFragmentRecyclerViewAdapter(arrayList,this)
            view.adapter=adapter
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel=ViewModelProviders.of(requireActivity()).get(SearchViewModel::class.java)
        searchViewModel.search.observe(requireActivity(), Observer {
            adapter.update(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {

        @JvmStatic
        fun newInstance(columnCount: Int) =
                SearchableFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onClick(medicine: SearchMedicine) {
        val intent=Intent(requireContext(),DetailedMedicine::class.java)
        intent.putExtra("medicine_id",medicine.hns)
        startActivity(intent)
    }
}
