package com.my.testproject.view

/**
 * Created by Mostafa Shiri.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.testproject.R
import com.my.testproject.datamodel.response.LeaderBoardResponse
import kotlinx.android.synthetic.main.leader_board_item.view.*

class LeaderBoardAdapter:RecyclerView.Adapter<LeaderBoardAdapter.MovieHolder>() {

    private val leaderBoardList = mutableListOf<LeaderBoardResponse.Leaderboard>()

    var adapterOnClick: ((item:LeaderBoardResponse.Leaderboard) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(inflater, parent)
    }

    fun addItems(turnoverList:ArrayList<LeaderBoardResponse.Leaderboard>) {
        leaderBoardList.addAll(turnoverList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = leaderBoardList.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemContent = leaderBoardList[position]
        holder.bindDeal(itemContent)
        holder.setItem(itemContent)
    }
    inner class MovieHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.leader_board_item, parent, false)) {

        fun bindDeal(item: LeaderBoardResponse.Leaderboard) {
            itemView.tv_player_id.text = item.playFabId
            itemView.tv_player_name.text = item.displayName
        }

        fun setItem(item: LeaderBoardResponse.Leaderboard) {
            itemView.setOnClickListener {
                adapterOnClick?.invoke(item)
            }
        }
    }

    fun clear() {
        leaderBoardList.clear()
        notifyDataSetChanged()
    }

}