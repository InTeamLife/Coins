package com.example.coins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinAdapter(private val coins: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        holder.bind(coin)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val coinIconImageView: ImageView = itemView.findViewById(R.id.coinIconImageView)
        private val coinNameTextView: TextView = itemView.findViewById(R.id.coinNameTextView)
        private val coinPriceTextView: TextView = itemView.findViewById(R.id.coinPriceTextView)
        private val coinHashrateTextView: TextView = itemView.findViewById(R.id.coinHashrateTextView)
        private val coinDifficultyTextView: TextView = itemView.findViewById(R.id.coinDifficultyTextView)
        private val coinProfitTextView: TextView = itemView.findViewById(R.id.coinProfitTextView)

        fun bind(coin: Coin) {
            coinNameTextView.text = coin.name
            coinPriceTextView.text = coin.price
            coinHashrateTextView.text = coin.hashrate
            coinDifficultyTextView.text = coin.difficulty
            coinProfitTextView.text = coin.profit
        }
    }
}
