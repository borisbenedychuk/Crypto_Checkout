package com.example.mycryptoapp.Pojos.BasicInfoPojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mycryptoapp.API.APIFactory
import com.example.mycryptoapp.utils.Utils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "coins")
data class CoinDetailedInfoByCoins (
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,

    @SerializedName("MARKET")
    @Expose
    val market: String? = null,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val tosymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: Double = 0.0,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastupdate: Long = 0,

    @SerializedName("MEDIAN")
    @Expose
    val median: Double = 0.0,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastvolume: Double = 0.0,

    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastvolumeto: Double = 0.0,

    @SerializedName("LASTTRADEID")
    @Expose
    val lasttradeid: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: Double = 0.0,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumedayto: Double = 0.0,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double = 0.0,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourto: Double = 0.0,

    @SerializedName("OPENDAY")
    @Expose
    val openday: Double = 0.0,

    @SerializedName("HIGHDAY")
    @Expose
    val highday: Double = 0.0,

    @SerializedName("LOWDAY")
    @Expose
    val lowday: Int = 0,

    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double = 0.0,

    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double = 0.0,

    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double = 0.0,

    @SerializedName("LASTMARKET")
    @Expose
    val lastmarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumehour: Double = 0.0,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumehourto: Double = 0.0,

    @SerializedName("OPENHOUR")
    @Expose
    val openhour: Double = 0.0,

    @SerializedName("HIGHHOUR")
    @Expose
    val highhour: Double = 0.0,

    @SerializedName("LOWHOUR")
    @Expose
    val lowhour: Double = 0.0,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val toptiervolume24hour: Double = 0.0,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    val toptiervolume24hourto: Double = 0.0,

    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24hour: Double = 0.0,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changepct24hour: Double = 0.0,

    @SerializedName("CHANGEDAY")
    @Expose
    val changeday: Double = 0.0,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changepctday: Double = 0.0,

    @SerializedName("CHANGEHOUR")
    @Expose
    val changehour: Double = 0.0,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changepcthour: Double = 0.0,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversiontype: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionsymbol: String? = null,

    @SerializedName("SUPPLY")
    @Expose
    val supply:Int = 0,

    @SerializedName("MKTCAP")
    @Expose
    val mktcap: Double = 0.0,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    val mktcappenalty: Int = 0,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalvolume24h: Double = 0.0,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalvolume24hto: Double = 0.0,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totaltoptiervolume24h: Double = 0.0,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totaltoptiervolume24hto: Double = 0.0,

    @SerializedName("IMAGEURL")
    @Expose
    val imageurl: String? = null,
) {
    fun getNormalTime () = Utils.getCurrentTime(lastupdate)
    fun getFullImageUrl () = APIFactory.BASE_IMAGE_URL + imageurl
}