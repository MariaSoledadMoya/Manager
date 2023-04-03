package com.leventsurer.manager.tools.adapters.homePageAdapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.leventsurer.manager.databinding.ConciergeAnnouncementRowBinding
import com.leventsurer.manager.databinding.ManagerAnnouncementRowBinding
import com.leventsurer.manager.databinding.ResidentRequestRawBinding

sealed class HomeRecyclerViewHolder(binding:ViewBinding):RecyclerView.ViewHolder(binding.root){

    class ManagerAnnouncementHolder(private val binding:ManagerAnnouncementRowBinding):HomeRecyclerViewHolder(binding){
        fun bind(announcement:HomeRecyclerViewItem.ManagerAnnouncement){
            binding.twAnnouncement.text = announcement.announcement
        }
    }

    class ConciergeAnnouncementHolder(private val binding: ConciergeAnnouncementRowBinding):HomeRecyclerViewHolder(binding){
        fun bind(announcement:HomeRecyclerViewItem.ConciergeAnnouncement){
            binding.twAnnouncement.text = announcement.announcement
        }
    }

    class ResidentRequestHolder(private val binding:ResidentRequestRawBinding):HomeRecyclerViewHolder(binding){
        fun bind(request:HomeRecyclerViewItem.ResidentRequest){
            binding.twResidentRequest.text = request.request
        }
    }
}