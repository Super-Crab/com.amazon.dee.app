package com.amazon.deecomms.notifications.filters;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.processor.Task;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.notifications.PushProcessStatus;
import com.amazon.deecomms.notifications.PushTypeHelper;
/* loaded from: classes12.dex */
public class AvoidMessagePushFromSelfFilter implements Task<PushProcessStatus, Bundle> {
    @Override // com.amazon.deecomms.common.processor.Task
    public PushProcessStatus execute(Bundle bundle) {
        JacksonJSONConverter jacksonJSONConverter = new JacksonJSONConverter();
        String string = bundle.getString(Constants.AMP_KEY);
        bundle.getString("target");
        if (PushTypeHelper.determineType(string) == PushTypeHelper.PushType.Message) {
            Message message = (Message) jacksonJSONConverter.fromJson(string, Message.class);
            if (message == null) {
                return PushProcessStatus.INVALID_PUSH_PAYLOAD_MESSAGE;
            }
            if (TextUtils.isEmpty(message.getSenderCommsId())) {
                return PushProcessStatus.INVALID_SENDER;
            }
            if (CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("AvoidMessagePushFromSelfFilter", false).equals(message.getSenderCommsId())) {
                return PushProcessStatus.RECEIVED_FROM_SELF;
            }
        }
        return PushProcessStatus.CONTINUE;
    }
}
