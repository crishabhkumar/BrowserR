package com.rishabhkumar.browserr.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rishabhkumar.browserr.Fragments.BrowseFragment
import com.rishabhkumar.browserr.Fragments.HomeFragment
import com.rishabhkumar.browserr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        var tabsList : ArrayList<Fragment> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        tabsList.add(HomeFragment())
        tabsList.add(BrowseFragment())
        binding.myPager.adapter = TabsAdapter(supportFragmentManager, lifecycle)

    }


    //Fragment adapter
    private inner class TabsAdapter(fa: FragmentManager, lc : Lifecycle) : FragmentStateAdapter(fa,lc) {
        override fun getItemCount(): Int = tabsList.size

        override fun createFragment(position: Int): Fragment = tabsList[position]
    }

}