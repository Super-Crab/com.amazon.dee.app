package com.amazon.deecomms.alexa;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.device.SipDeviceState;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.annotations.VisibleForTesting;
/* loaded from: classes12.dex */
public class HalloEvent extends InCallEvent<HalloEventType, SipClientState> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, HalloEvent.class);
    private final SipDeviceState sipDeviceState;

    public HalloEvent(@NonNull HalloEventType halloEventType, @NonNull String str, @NonNull SipClientState sipClientState) {
        super(halloEventType, str, sipClientState);
        this.sipDeviceState = CallUtils.getSipDeviceState((SipClientState) this.data, str);
    }

    @VisibleForTesting
    SipDeviceState getSipDeviceState() {
        return this.sipDeviceState;
    }

    @Override // com.amazon.deecomms.alexa.InCallEvent
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        try {
            String serialize = Utils.serialize(this.sipDeviceState);
            bundle.putString("ACTION", "com.amazon.deecomms.alexa.HALLO_EVENT_ACTION");
            bundle.putString("inCallEvent", getEventName());
            bundle.putString("callId", this.callId);
            bundle.putString("callState", ((SipClientState) this.data).getCallState().toString());
            bundle.putString("SipClientState", serialize);
            bundle.putString("statusCode", ((SipClientState) this.data).getStatusCode());
            bundle.putString(HalloConstants.HALLO_EVENT, getEventName());
            return bundle;
        } catch (JsonProcessingException e) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Error serializing '");
            outline1.append(this.data);
            outline1.append("'");
            commsLogger.e(outline1.toString(), e);
            return bundle;
        }
    }
}
