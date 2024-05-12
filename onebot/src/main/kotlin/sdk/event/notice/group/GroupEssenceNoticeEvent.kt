package cn.evolvefield.onebot.sdk.event.notice.group

import cn.evolvefield.onebot.sdk.event.notice.NoticeEvent
import com.google.gson.annotations.SerializedName
import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
class GroupEssenceNoticeEvent : NoticeEvent() {
    /**
     * add,delete	添加为add,移出为delete
     */
    @SerializedName("sub_type")
    var subType = ""

    /**
     * 消息发送者ID
     */
    @SerializedName("sender_id")
    var senderId: Long? = null

    /**
     * 操作者ID
     */
    @SerializedName("operator_id")
    var operatorId: Long? = null

    /**
     * 消息ID
     */
    @SerializedName("message_id")
    var messageId: Long? = null
}
