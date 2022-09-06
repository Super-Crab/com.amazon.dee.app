package com.amazon.deecomms.calling.incallcommands.models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.deecomms.calling.incallcommands.SipDeviceStateContextProvider;
import com.amazon.deecomms.calling.incallcommands.constants.InCallEventsConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class CallTypeInCallCommandModel extends InCallCommandModel {
    private static final String TAG = "CallTypeInCallCommandModel";

    /* loaded from: classes12.dex */
    static class Helper {
        AlexaHeader createHeader(@NonNull String str) {
            return AlexaHeader.builder().setNamespace("SipClient").setName(str).build();
        }

        AlexaPayload createPayload(@NonNull String str, @NonNull List<String> list, @NonNull String str2, @NonNull String str3) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("callExperienceType", str);
            if (!list.isEmpty()) {
                jSONObject.put("callFlags", new JSONArray((Collection) list));
            }
            jSONObject.put("requestId", str3);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(InCallEventsConstants.COMMS_ID, str2);
            jSONObject2.put(InCallEventsConstants.IDENTITY_TYPE, InCallEventsConstants.IDENTITY_TYPE_INDIVIDUAL);
            jSONObject.put(InCallEventsConstants.REQUESTER_IDENTITY, jSONObject2);
            return new AlexaPayload(jSONObject.toString());
        }
    }

    public CallTypeInCallCommandModel(@NonNull Intent intent, @NonNull String str, @NonNull Helper helper) {
        super(intent);
        Bundle extras = intent.getExtras();
        String string = extras.getString("callExperienceType", "");
        List<String> stringArrayList = extras.getStringArrayList("callFlags");
        stringArrayList = stringArrayList == null ? Collections.emptyList() : stringArrayList;
        String string2 = extras.getString("SipClientState", "");
        if (!string.isEmpty() && !string2.isEmpty()) {
            this.header = helper.createHeader(this.inCallCommandName);
            this.context = createContext(string2);
            try {
                this.payload = helper.createPayload(string, stringArrayList, str, this.callId);
                return;
            } catch (JSONException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        Log.i(TAG, "IllegalArgumentException. CallExperienceType is empty");
        throw new IllegalArgumentException("IllegalArgumentException. CallExperienceType is empty");
    }

    private AlexaHeader createHeader(@NonNull Helper helper, @NonNull String str) {
        return helper.createHeader(str);
    }

    private AlexaPayload createPayload(@NonNull Helper helper, @NonNull String str, @NonNull List<String> list, @NonNull String str2, @NonNull String str3) throws JSONException {
        return helper.createPayload(str, list, str2, str3);
    }

    public AlexaContextProvider createContext(@NonNull String str) {
        SipDeviceStateContextProvider orCreateSipDeviceStateContextProvider = SipDeviceStateContextProvider.getOrCreateSipDeviceStateContextProvider();
        orCreateSipDeviceStateContextProvider.setDeviceCallStateSerialized(str);
        return orCreateSipDeviceStateContextProvider;
    }

    @Override // com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel
    public synchronized boolean hasContext() {
        return true;
    }
}
