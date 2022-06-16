package com.rishabhkumar.browserr.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.rishabhkumar.browserr.Activities.MainActivity
import com.rishabhkumar.browserr.R
import com.rishabhkumar.browserr.databinding.FragmentBrowseBinding

class BrowseFragment(private var urlNew : String) : Fragment() {

    lateinit var binding: FragmentBrowseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        binding = FragmentBrowseBinding.bind(view)


        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()
        val mainActivityRef = requireActivity() as MainActivity
        binding.browseWebView.settings.apply{
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
        }

        binding.browseWebView.apply {
            webViewClient = object:WebViewClient(){
                override fun doUpdateVisitedHistory(
                    view: WebView?,
                    url: String?,
                    isReload: Boolean
                ) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                    mainActivityRef.binding.topSearchBar.text = SpannableStringBuilder(url)
                }
            }
            webChromeClient = object: WebChromeClient(){
                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    super.onShowCustomView(view, callback)
                    binding.browseWebView.visibility = View.GONE
                    binding.customView.visibility = View.VISIBLE
                    binding.customView.addView(view)
                }

                override fun onHideCustomView() {
                    super.onHideCustomView()
                    binding.browseWebView.visibility = View.VISIBLE
                    binding.customView.visibility = View.GONE
                }
            }

            //default search engine setting
            when{
                URLUtil.isValidUrl(urlNew) -> loadUrl(urlNew)
                urlNew.contains(".com",ignoreCase = true) -> loadUrl(urlNew)
                else -> loadUrl("https://www.google.com/search?q=$urlNew")
            }
        }
    }

}