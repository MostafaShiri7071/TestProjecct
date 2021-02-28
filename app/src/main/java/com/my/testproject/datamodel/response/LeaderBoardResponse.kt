package com.my.testproject.datamodel.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Mostafa Shiri.
 */
class LeaderBoardResponse {

    @SerializedName("Version")
    var version: Int? = null

    @SerializedName("Leaderboard")
    var leaderboard: ArrayList<Leaderboard>? = null

    class Leaderboard{

        @SerializedName("PlayFabId")
        var playFabId: String? = null

        @SerializedName("DisplayName")
        var displayName: String? = null

        @SerializedName("StatValue")
        var statValue: Int? = null

        @SerializedName("Position")
        var position: Int? = null

        @SerializedName("Profile")
        var profile: Profile? = null

    }

    class Profile{

        @SerializedName("PublisherId")
        var publisherId: String? = null

        @SerializedName("TitleId")
        var titleId: String? = null

        @SerializedName("PlayerId")
        var playerId: String? = null

        @SerializedName("DisplayName")
        var displayName: String? = null

    }

}