package cn.evolvefield.onebot.sdk.util.json

import cn.evolvefield.onebot.sdk.response.group.ForwardMsgResp
import cn.evolvefield.onebot.sdk.util.JsonDeserializerKt
import cn.evolvefield.onebot.sdk.util.forceString
import cn.evolvefield.onebot.sdk.util.fromJson
import cn.evolvefield.onebot.sdk.util.ignorable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ForwardMsgAdapter : JsonDeserializerKt<ForwardMsgResp> {
    override fun deserializeFromJson(
        json: JsonElement,
        type: Type,
        ctx: JsonDeserializationContext
    ): ForwardMsgResp {
        val nodes = mutableListOf<ForwardMsgResp.Node>()
        val jsonObj = json.asJsonObject
        var msgArray = jsonObj.get("messages")
        if (msgArray == null) {
            msgArray = jsonObj.get("message") // Lagrange
        }
        for (element in msgArray.asJsonArray) {
            val obj = element.asJsonObject.run {
                if (has("data")) get("data").asJsonObject
                else this
            }
            nodes.add(ForwardMsgResp.Node().apply {
                time = obj.ignorable("time", 0) // OpenShamrock
                messageType = obj.ignorable("message_type", "") // OpenShamrock
                realId = obj.ignorable("real_id", 0) // OpenShamrock
                sender = obj.fromJson("sender") // OpenShamrock
                if (sender == null) { // Lagrange
                    sender = ForwardMsgResp.Sender().apply {
                        userId = obj.ignorable("user_id", 0L)
                        nickname = obj.ignorable("nickname", "")
                        sex = "unknown"
                    }
                }
                message = if (obj.has("content")) {
                    obj.forceString("content") // Lagrange
                } else {
                    obj.forceString("message") // go-cqhttp, OpenShamrock
                }
                peerId = obj.ignorable("peer_id", 0L) // OpenShamrock
                targetId = obj.ignorable("target_id", 0L) // OpenShamrock
            })
        }
        return ForwardMsgResp().apply { message = nodes }
    }
}
