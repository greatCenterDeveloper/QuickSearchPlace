package com.swj.quicksearchplace.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swj.quicksearchplace.activities.MainActivity
import com.swj.quicksearchplace.adapters.PlaceListRecyclerAdapter
import com.swj.quicksearchplace.databinding.FragmentPlaceListBinding

class PlaceListFragment : Fragment() {

    lateinit var binding:FragmentPlaceListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MainActivity에 있는 대량의 데이터 소환
        val ma:MainActivity = requireActivity() as MainActivity
        //if(ma.searchPlaceResponse == null) return
        //ma.searchPlaceResponse ?: return
        //binding.recycler.adapter = PlaceListRecyclerAdapter(requireActivity(), ma.searchPlaceResponse!!.documents)
        ma.searchPlaceResponse?.apply {
            // 영역 안에서 this는 생략이 가능.
            binding.recycler.adapter = PlaceListRecyclerAdapter(requireActivity(), documents)
        }
    }
}