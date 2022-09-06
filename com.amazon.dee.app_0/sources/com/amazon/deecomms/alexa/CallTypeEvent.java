package com.amazon.deecomms.alexa;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class CallTypeEvent extends InCallEvent<CallTypeEventType, CallTypeEventModel> {
    public CallTypeEventModel callTypeEventModel;

    public CallTypeEvent(@NonNull CallTypeEventType callTypeEventType, @NonNull CallTypeEventModel callTypeEventModel) {
        super(callTypeEventType, callTypeEventModel.getCallId(), callTypeEventModel);
        this.callTypeEventModel = callTypeEventModel;
    }

    @Override // com.amazon.deecomms.alexa.InCallEvent
    @NonNull
    public Bundle toBundle() {
        String callType = this.callTypeEventModel.getCallType();
        Bundle outline11 = GeneratedOutlineSupport1.outline11("ACTION", "com.amazon.deecomms.alexa.CALL_TYPE_EVENT_ACTION");
        outline11.putString("inCallEvent", getEventName());
        outline11.putStringArrayList("callFlags", new ArrayList<>(this.callTypeEventModel.getCallFlags()));
        outline11.putString("callId", this.callId);
        outline11.putString("callExperienceType", callType);
        outline11.putString("SipClientState", this.callTypeEventModel.getSipDeviceStateSerialized());
        return outline11;
    }
}
