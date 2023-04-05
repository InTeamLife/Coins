package com.example.coins

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object NetworkService {

    private const val BASE_URL = "https://explorer.kaspa.org"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val kaspaApi = retrofit.create(KaspaApi::class.java)

    suspend fun getCoinData(): List<Coin> {
        val coinList = mutableListOf<Coin>()

        val blockStats = kaspaApi.getBlockStats()
        val hashrate = blockStats.hashRate.toString()
        val difficulty = blockStats.difficulty.toString()

        val blockReward = kaspaApi.getBlockReward()
        val blockReward100MHash = blockReward / 100_000_000

        val price = kaspaApi.getPrice()

        val coin = Coin(
            "Kaspa",
            "https://cryptologos.cc/logos/kaspa-ksp-logo.svg?v=013",
            "$$price",
            "$hashrate",
            "$difficulty",
            "${"%.8f".format(blockReward100MHash)} KSP/day"
        )
        coinList.add (coin)

        return coinList
    }

    private interface KaspaApi {

        @GET("/api/get_block_stats")
        suspend fun getBlockStats(): BlockStats

        @GET("/api/get_block_reward")
        suspend fun getBlockReward(): Double

        @GET("/api/get_price")
        suspend fun getPrice(): Double
    }

    private data class BlockStats(
        @SerializedName("difficulty") val difficulty: Long,
        @SerializedName("hash_rate") val hashRate: Long
    )
}
