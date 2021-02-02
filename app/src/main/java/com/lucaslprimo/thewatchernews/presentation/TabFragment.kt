package com.lucaslprimo.thewatchernews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucaslprimo.thewatchernews.R
import com.lucaslprimo.thewatchernews.presentation.objects.Category

class TabFragment(var category: Category) : Fragment() {

    lateinit var homeViewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            homeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }

        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val newsAdapter = NewsAdapter(this.context!!)
        recyclerView.adapter = newsAdapter

        recyclerView.layoutManager = LinearLayoutManager(this.context!!,RecyclerView.VERTICAL,false)

        when(category){
            Category.ENTERTAINMENT ->
                homeViewModel.loadEntertainment(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.TECHNOLOGY ->
                homeViewModel.loadTech(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.BUSINESS ->
                homeViewModel.loadBusiness(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.WORLD ->
                homeViewModel.loadWorld(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.SPORTS ->
                homeViewModel.loadSports(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.POLITICS ->
                homeViewModel.loadPolitics(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
            Category.BREAKINGNEWS ->
                homeViewModel.loadBreaking(category, this.context!!).observe(this, Observer {
                    newsAdapter.items = it
                })
        }

        return view
    }
}