package com.my.testproject.datamodel.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Mostafa Shiri.
 */
class LoginResponse {

    @SerializedName("SessionTicket")
    var sessionTicket: String? = null

    @SerializedName("PlayFabId")
    var playFabId: String? = null

    @SerializedName("NewlyCreated")
    var newlyCreated: Boolean? = null

    @SerializedName("LastLoginTime")
    var lastLoginTime: String? = null

    @SerializedName("SettingsForUser")
    var settingsForUser: SettingsForUser? = null

    @SerializedName("EntityToken")
    var entityToken: EntityToken? = null

    class SettingsForUser{

        @SerializedName("NeedsAttribution")
        var needsAttribution: Boolean? = null

        @SerializedName("GatherDeviceInfo")
        var gatherDeviceInfo: Boolean? = null

        @SerializedName("GatherFocusInfo")
        var gatherFocusInfo: Boolean? = null

    }

    class EntityToken{

        @SerializedName("EntityToken")
        var entityToken: String? = null

        @SerializedName("TokenExpiration")
        var tokenExpiration: String? = null

        @SerializedName("Entity")
        var entity: Entity? = null

    }

    class Entity{

        @SerializedName("Id")
        var id: String? = null

        @SerializedName("Type")
        var type: String? = null

        @SerializedName("TypeString")
        var typeString: String? = null

    }


}