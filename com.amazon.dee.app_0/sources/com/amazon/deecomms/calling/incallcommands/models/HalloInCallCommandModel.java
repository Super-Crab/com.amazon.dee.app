package com.amazon.deecomms.calling.incallcommands.models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.deecomms.calling.incallcommands.SipDeviceStateContextProvider;
import com.amazon.deecomms.calling.incallcommands.constants.InCallEventsConstants;
import com.android.tools.r8.GeneratedOutlineSupport;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class HalloInCallCommandModel extends InCallCommandModel {
    private static final String TAG = "HalloInCallCommandModel";
    private String callState;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public static class Helper {
        @VisibleForTesting
        AlexaHeader createHeader(@NonNull String str) {
            return AlexaHeader.builder().setNamespace("SipClient").setName(str).build();
        }

        @VisibleForTesting
        AlexaPayload createPayload(@NonNull String str, @NonNull String str2) throws JSONException {
            JSONObject jSONObject = new JSONObject(str2);
            JSONObject jSONObject2 = jSONObject.getJSONObject(InCallEventsConstants.SIP_CALL_STATE);
            jSONObject2.put(InCallEventsConstants.DEVICE_SENT_ABSOLUTE_TIMESTAMP, jSONObject.getString(InCallEventsConstants.DEVICE_SENT_ABSOLUTE_TIMESTAMP));
            jSONObject2.put(InCallEventsConstants.DEVICE_SENT_RELATIVE_TIMESTAMP, jSONObject.getString(InCallEventsConstants.DEVICE_SENT_RELATIVE_TIMESTAMP));
            jSONObject2.put("statusCode", str);
            return new AlexaPayload(jSONObject2.toString());
        }
    }

    public HalloInCallCommandModel(@NonNull Intent intent, @NonNull Helper helper) {
        super(intent);
        Bundle extras = intent.getExtras();
        String string = extras.getString("SipClientState", "");
        this.callState = extras.getString("callState", "");
        String string2 = extras.getString("statusCode", "");
        if (!string.isEmpty() && !this.callState.isEmpty() && !string2.isEmpty()) {
            this.header = createHeader(helper, this.inCallCommandName);
            this.context = createContext(string);
            try {
                this.payload = createPayload(helper, string2, string);
                return;
            } catch (JSONException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("IllegalArgumentException ");
        outline1.append(toString());
        String sb = outline1.toString();
        Log.i(TAG, sb);
        throw new IllegalArgumentException(sb);
    }

    public AlexaContextProvider createContext(@NonNull String str) {
        SipDeviceStateContextProvider orCreateSipDeviceStateContextProvider = SipDeviceStateContextProvider.getOrCreateSipDeviceStateContextProvider();
        orCreateSipDeviceStateContextProvider.setDeviceCallStateSerialized(str);
        return orCreateSipDeviceStateContextProvider;
    }

    protected AlexaHeader createHeader(@NonNull Helper helper, @NonNull String str) {
        return helper.createHeader(str);
    }

    protected AlexaPayload createPayload(@NonNull Helper helper, @NonNull String str, @NonNull String str2) throws JSONException {
        return helper.createPayload(str, str2);
    }

    @Override // com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel
    public synchronized boolean hasContext() {
        return true;
    }
}
