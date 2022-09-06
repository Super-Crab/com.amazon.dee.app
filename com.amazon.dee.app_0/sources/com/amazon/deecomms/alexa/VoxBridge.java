package com.amazon.deecomms.alexa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
/* loaded from: classes12.dex */
public class VoxBridge implements AlexaInterface {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, VoxBridge.class);
    private final Context context;

    public VoxBridge(@NonNull Context context) {
        this.context = context;
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean acquireCommsFocus() {
        return true;
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean releaseCommsFocus() {
        return true;
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean sendCommsEvent(@NonNull InCallEvent inCallEvent) {
        String callId = inCallEvent.getCallId();
        if (callId == null) {
            LOG.e("The CallId is null. This should never be the case.");
            return false;
        } else if (callId.isEmpty()) {
            LOG.e("The CallId is empty. This should never be the case.");
            return false;
        } else {
            Bundle bundle = inCallEvent.toBundle();
            String string = bundle.getString("ACTION");
            if (string == null) {
                LOG.e("The InCallEvent Action is null. This should never be the case.");
                return false;
            } else if (string.isEmpty()) {
                LOG.e("The InCallEvent Action is empty. This should never be the case.");
                return false;
            } else {
                Utils.sendExplicitBroadcastIntent(this.context, new Intent(string).putExtras(bundle), Constants.COMMS_PERMISSION);
                return true;
            }
        }
    }
}
