package com.amazon.deecomms.calling.phonecallcontroller;

import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.calling.phonecallcontroller.enums.PCCConstraints;
import com.amazon.deecomms.calling.phonecallcontroller.enums.PCCStates;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.security.SecureRandom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PCCContextProvider implements AlexaContextProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PCCContextProvider.class);
    @VisibleForTesting
    volatile AlexaContext mPCCContext;
    private final TelephonyManager mTelephonyManager = CommsDaggerWrapper.getComponent().getTelephonyManager();
    private final CapabilitiesManager mCapabilitiesManager = CommsDaggerWrapper.getComponent().getCapabilitiesManager();

    private synchronized void updatePCCContext() {
        try {
            LOG.i("Updating PCC Context...");
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            boolean areAccessoriesConnected = Utils.areAccessoriesConnected();
            if (!areAccessoriesConnected && !DeviceUtils.isCommsNativeDefaulted(this.mCapabilitiesManager)) {
                jSONObject2.put(PCCConstants.PHONE_CALL_CONTROLLER_CONNECTION_STATE_KEY, "DISCONNECTED");
            } else {
                jSONObject2.put(PCCConstants.PHONE_CALL_CONTROLLER_CONNECTION_STATE_KEY, "CONNECTED");
            }
            jSONObject.put("device", jSONObject2);
            jSONObject.put(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, fetchPCCConfigurationsObject(areAccessoriesConnected, isHFPPCCCompliantAccessorySessionAvailable()));
            int callState = this.mTelephonyManager.getCallState();
            JSONObject jSONObject3 = null;
            JSONObject jSONObject4 = new JSONObject();
            int nextInt = new SecureRandom().nextInt();
            JSONArray jSONArray = new JSONArray();
            if (callState == 1) {
                jSONObject3 = new JSONObject();
                jSONObject3.put("callId", nextInt);
                jSONObject3.put("callState", PCCStates.INBOUND_RINGING.toString());
                jSONObject4.put("callId", nextInt);
            } else if (callState != 2) {
                LOG.e("Reached Idle Telephony state");
            } else {
                jSONObject3 = new JSONObject();
                jSONObject3.put("callId", nextInt);
                jSONObject3.put("callState", PCCStates.ACTIVE.toString());
                jSONObject4.put("callId", nextInt);
            }
            if (jSONObject3 != null) {
                jSONArray.put(jSONObject3);
            }
            jSONObject.put(PCCConstants.PHONE_CALL_CONTROLLER_CURRENT_CALL_KEY, jSONObject4);
            jSONObject.put(PCCConstants.PHONE_CALL_CONTROLLER_ALL_CALLS_KEY, jSONArray);
            this.mPCCContext = new AlexaContext(createHeader(), createPayload(jSONObject.toString()));
        } catch (JSONException e) {
            LOG.e("JSON Error while updating the PCC Context", e);
        }
    }

    @NonNull
    @VisibleForTesting
    AlexaHeader createHeader() {
        return AlexaHeader.builder().setNamespace(PCCConstants.PHONE_CALL_CONTROLLER_NAMESPACE).setName(PCCConstants.PHONE_CALL_CONTROLLER_CONTEXT_STATE_NAME).build();
    }

    @NonNull
    @VisibleForTesting
    AlexaPayload createPayload(@NonNull String str) {
        return new AlexaPayload(str);
    }

    @Nullable
    public JSONObject fetchPCCConfigurationsObject(boolean z, boolean z2) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("Fetching Config. PCC accessory available: " + z + ", HFP profile available: " + z2);
        JSONArray jSONArray = new JSONArray();
        try {
            if (z) {
                jSONArray.put(new JSONObject().put(PCCConstraints.OVERRIDE_RINGTONE_SUPPORTED.toString(), PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
                if (!z2) {
                    if (!Utils.isOreoAndAbove()) {
                        jSONArray.put(new JSONObject().put(PCCConstraints.MANUAL_ANSWER_CALL.toString(), "true"));
                    }
                    if (!Utils.isPieAndAbove()) {
                        jSONArray.put(new JSONObject().put(PCCConstraints.MANUAL_DECLINE_CALL.toString(), "true"));
                        jSONArray.put(new JSONObject().put(PCCConstraints.MANUAL_END_CALL.toString(), "true"));
                    }
                }
            } else if (DeviceUtils.isCommsNativeDefaulted(this.mCapabilitiesManager)) {
                jSONArray.put(new JSONObject().put(PCCConstraints.OVERRIDE_RINGTONE_SUPPORTED.toString(), PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE));
                jSONArray.put(new JSONObject().put(PCCConstraints.VIDEO_SUPPORTED.toString(), "true"));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_KEY, jSONArray);
            return jSONObject;
        } catch (JSONException unused) {
            LOG.e("Exception in fetching PCC Constraints");
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaContextProvider
    @NonNull
    public AlexaContext getAlexaContext() {
        updatePCCContext();
        return this.mPCCContext;
    }

    public boolean isA2DPPCCCompliantAccessorySessionAvailable() {
        CommsAccessorySessionListener commsAccessorySessionListener = CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener();
        return commsAccessorySessionListener.isAnyAccessoryConnected() && commsAccessorySessionListener.isA2DPAvailable();
    }

    public boolean isHFPPCCCompliantAccessorySessionAvailable() {
        CommsAccessorySessionListener commsAccessorySessionListener = CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener();
        return commsAccessorySessionListener.isAnyAccessoryConnected() && commsAccessorySessionListener.isHFPAvailable();
    }
}
