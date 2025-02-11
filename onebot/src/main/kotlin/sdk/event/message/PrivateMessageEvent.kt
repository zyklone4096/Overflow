package cn.evolvefield.onebot.sdk.event.message

import cn.evolvefield.onebot.sdk.entity.PrivateSender
import cn.evolvefield.onebot.sdk.util.json.MessageEventAdapter
import com.google.gson.annotations.JsonAdapter

/**
 * 私聊消息
 *
 * @author cnlimiter
 */
@JsonAdapter(MessageEventAdapter::class)
class PrivateMessageEvent : MessageEvent() {
    var messageId = 0
    var subType = ""
    lateinit var sender: PrivateSender
    var groupId = 0L // OpenShamrock

    /**
     * go-cqhttp: [docs](https://docs.go-cqhttp.org/reference/data_struct.html#post-message-tempsource)<br></br>
     * Group(0),
     * Consultation(1),
     * Seek(2),
     * QQMovie(3),
     * HotChat(4),
     * VerifyMsg(6),
     * Discussion(7),
     * Dating(8),
     * Contact(9),
     * Unknown(-1)
     */
    var tempSource = 0
    var fromNick = "" // OpenShamrock
}
