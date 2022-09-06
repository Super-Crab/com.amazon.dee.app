package com.amazon.deecomms.calling.incallcommands.models;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.calling.incallcommands.models.CallTypeInCallCommandModel;
import com.amazon.deecomms.calling.incallcommands.models.HalloInCallCommandModel;
/* loaded from: classes12.dex */
public class InCallCommandModelFactory {
    private CommsManager commsManager;
    private Context context;

    public InCallCommandModelFactory(@NonNull Context context, @NonNull CommsManager commsManager) {
        this.context = context;
        this.commsManager = commsManager;
    }

    public InCallCommandModel createInCallCommandModel(@NonNull Intent intent) {
        String action = intent.getAction();
        if ("com.amazon.deecomms.alexa.CALL_TYPE_EVENT_ACTION".equals(action)) {
            return new CallTypeInCallCommandModel(intent, this.commsManager.getCommsId(), new CallTypeInCallCommandModel.Helper());
        }
        if ("com.amazon.deecomms.alexa.HALLO_EVENT_ACTION".equals(action)) {
            return new HalloInCallCommandModel(intent, new HalloInCallCommandModel.Helper());
        }
        throw new IllegalArgumentException("not supported action for RingServiceBroadcastReceiver");
    }
}
