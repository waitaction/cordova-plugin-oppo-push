package com.lifang123.push.oppo.service;

import android.content.Context;
import android.util.Log;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.service.CompatibleDataMessageCallbackService;
import com.lifang123.push.oppo.MessageDispatcher;

/**
 * 如果应用需要解析和处理Push消息（如透传消息），则继承PushService来处理，并在Manifest文件中申明Service
 * 如果不需要处理Push消息，则不需要继承PushService，直接在Manifest文件申明PushService即可
 */
public class PushMessageService extends CompatibleDataMessageCallbackService {
    /**
     * 透传消息处理，应用可以打开页面或者执行命令,如果应用不需要处理透传消息，则不需要重写此方法
     *
     * @param context
     * @param dataMessage
     */
    @Override
    public void processMessage(Context context, DataMessage dataMessage) {
        super.processMessage(context.getApplicationContext(), dataMessage);
        String content = dataMessage.getContent();
        Log.i("oppo",content);
        MessageDispatcher.dispatch(context, content);//统一处理
    }
}
