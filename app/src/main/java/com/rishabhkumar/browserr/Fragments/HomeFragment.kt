package com.rishabhkumar.browserr.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rishabhkumar.browserr.Activities.MainActivity
import com.rishabhkumar.browserr.R
import com.rishabhkumar.browserr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return view
    }

    override fun onResume() {
        super.onResume()

        val mainActivityRef = requireActivity() as MainActivity
        mainActivityRef.binding.topSearchBar.setText("")
        binding.homeSearchView.setQuery("",false)

        binding.homeSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            //call when user enter to search
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(mainActivityRef.checkForInternet(requireContext())){
                    mainActivityRef.changeTab(query!!, BrowseFragment(query))
                }else{
                    Snackbar.make(binding.root,"Please connect to internet",3000).show()
                }
                return true
            }

            //call when user start typing in searchview
            override fun onQueryTextChange(newText: String?): Boolean = false

        })

        mainActivityRef.binding.btnGoToSearch.setOnClickListener{
            val query = mainActivityRef.binding.topSearchBar.text.toString()
            if(mainActivityRef.checkForInternet(requireContext())){
                mainActivityRef.changeTab(query, BrowseFragment(query))
            }else{
                Snackbar.make(binding.root,"Please connect to internet",3000).show()
            }
        }


    }

}