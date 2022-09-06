package com.amazon.deecomms.calling.phonecallcontroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.enums.PCCEvents;
import com.amazon.deecomms.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PCCEventModel extends AlexaEvent {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PCCEventModel.class);

    public PCCEventModel(@NonNull AlexaHeader alexaHeader, @NonNull AlexaPayload alexaPayload) {
        super(alexaHeader, alexaPayload);
    }

    @NonNull
    public static AlexaHeader createHeaderForEvent(@NonNull String str) {
        return AlexaHeader.builder().setNamespace(PCCConstants.PHONE_CALL_CONTROLLER_NAMESPACE).setName(str).build();
    }

    private static AlexaPayload createPayloadForCallFailedEvent(@NonNull int i, @NonNull String str) {
        String str2;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", str);
            jSONObject.put("error", jSONObject2);
            jSONObject.put("callId", i);
            str2 = jSONObject.toString();
        } catch (JSONException e) {
            LOG.e("createPayloadFor PCC Event exception: ", e);
            str2 = "";
        }
        return new AlexaPayload(str2);
    }

    @NonNull
    public static AlexaPayload createPayloadForEvent(@NonNull int i) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("callId", i);
            str = jSONObject.toString();
        } catch (JSONException e) {
            LOG.e("createPayloadFor PCC Event exception: ", e);
            str = "";
        }
        return new AlexaPayload(str);
    }

    public static PCCEventModel getFailedEvent(@NonNull int i) {
        return new PCCEventModel(createHeaderForEvent(PCCEvents.CallFailed.toString()), createPayloadForCallFailedEvent(i, PCCConstants.PCC_NO_CALL_PERMISSION_ERROR_CODE));
    }

    @Nullable
    public static PCCEventModel getPCCModelForState(int i, int i2) {
        return new PCCEventModel(createHeaderForEvent(mapCallStateToPCCEvent(i)), createPayloadForEvent(i2));
    }

    @Nullable
    private static String mapCallStateToPCCEvent(int i) {
        if (i != 0) {
            if (i == 1) {
                return PCCEvents.InboundRingingStarted.toString();
            }
            if (i != 2) {
                LOG.i("Unsupported PCC event");
                return null;
            }
            return PCCEvents.CallActivated.toString();
        }
        return PCCEvents.CallTerminated.toString();
    }
}
