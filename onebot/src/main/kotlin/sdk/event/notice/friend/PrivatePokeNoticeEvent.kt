package cn.evolvefield.onebot.sdk.event.notice.friend

import cn.evolvefield.onebot.sdk.event.notice.NoticeEvent
import com.google.gson.annotations.SerializedName

class PrivatePokeNoticeEvent : NoticeEvent() {
    @SerializedName("sub_type")
    var subType = ""
    @SerializedName("sender_id")
    var senderId = 0L
    @SerializedName("target_id")
    var targetId = 0L
}
