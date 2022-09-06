package com.amazon.deecomms.smsmessaging.messagingcontroller;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class MessagingControllerContextProvider implements AlexaContextProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingControllerContextProvider.class);
    @Inject
    Context mContext;
    private volatile AlexaContext mDeviceContext;
    private volatile AlexaHeader mDeviceContextHeader = createHeader();
    private volatile JSONObject mDeviceContextPayload;

    /* loaded from: classes12.dex */
    public interface ContextUpdated {
        void onContextUpdated();
    }

    public MessagingControllerContextProvider() {
        CommsDaggerWrapper.getComponent().inject(this);
        updateContext(null);
    }

    @NonNull
    private AlexaHeader createHeader() {
        return AlexaHeader.builder().setNamespace(MessagingControllerConstant.MESSAGING_CONTROLLER_NAMESPACE).setName(MessagingControllerConstant.MESSAGING_CONTROLLER_CONTEXT_STATE_NAME).build();
    }

    @NonNull
    private AlexaPayload createPayload(@NonNull String str) {
        return new AlexaPayload(str);
    }

    private synchronized void createPayloadJSON() {
        this.mDeviceContextPayload = new JSONObject();
        try {
            JSONObject jSONObject = new JSONObject();
            String str = "OFF";
            String str2 = "OFF";
            if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.SEND_SMS") == 0) {
                str = "ON";
            }
            if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_SMS") == 0) {
                str2 = "ON";
            }
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_SEND_PERMISSION_KEY, str);
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_READ_PERMISSION_KEY, str2);
            this.mDeviceContextPayload.put(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_PERMISSIONS_KEY, jSONObject);
        } catch (JSONException e) {
            LOG.e("JSON error while creating Messaging Controller device context payload.", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaContextProvider
    @NonNull
    public AlexaContext getAlexaContext() {
        if (this.mDeviceContext != null) {
            updateContext(null);
            return this.mDeviceContext;
        }
        throw new IllegalStateException("Messaging Controller Context is null. Call updateContextWithAccessoryInformation()");
    }

    public synchronized void updateContext(@Nullable ContextUpdated contextUpdated) {
        createPayloadJSON();
        this.mDeviceContext = new AlexaContext(this.mDeviceContextHeader, createPayload(this.mDeviceContextPayload.toString()));
        if (contextUpdated != null) {
            contextUpdated.onContextUpdated();
        }
    }
}
