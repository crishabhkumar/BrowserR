package com.rishabhkumar.browserr.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
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
        binding.myPager.adapter = TabsAdapter(supportFragmentManager, lifecycle)
        binding.myPager.isUserInputEnabled = false

    }


    //Fragment adapter
    private inner class TabsAdapter(fa: FragmentManager, lc : Lifecycle) : FragmentStateAdapter(fa,lc) {
        override fun getItemCount(): Int = tabsList.size

        override fun createFragment(position: Int): Fragment = tabsList[position]
    }


    //function for tab changing
    @SuppressLint("NotifyDataSetChanged")
    fun changeTab(url : String, fragment: Fragment){
        tabsList.add(fragment)
        binding.myPager.adapter?.notifyDataSetChanged()
        binding.myPager.currentItem = tabsList.size - 1
    }
}