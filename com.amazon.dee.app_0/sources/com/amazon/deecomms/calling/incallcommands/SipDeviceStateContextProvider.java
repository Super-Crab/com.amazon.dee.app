package com.amazon.deecomms.calling.incallcommands;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
/* loaded from: classes12.dex */
public class SipDeviceStateContextProvider implements AlexaContextProvider {
    private static volatile SipDeviceStateContextProvider sipDeviceStateContextProvider;
    private volatile String deviceCallStateSerialized;

    @NonNull
    private AlexaHeader createHeader() {
        return AlexaHeader.builder().setNamespace("SipClient").setName("SipClientState").build();
    }

    @NonNull
    private AlexaPayload createPayload(@NonNull String str) {
        return new AlexaPayload(str);
    }

    public static synchronized SipDeviceStateContextProvider getOrCreateSipDeviceStateContextProvider() {
        SipDeviceStateContextProvider sipDeviceStateContextProvider2;
        synchronized (SipDeviceStateContextProvider.class) {
            if (sipDeviceStateContextProvider == null) {
                sipDeviceStateContextProvider = new SipDeviceStateContextProvider();
            }
            sipDeviceStateContextProvider2 = sipDeviceStateContextProvider;
        }
        return sipDeviceStateContextProvider2;
    }

    @Override // com.amazon.alexa.api.AlexaContextProvider
    @NonNull
    public AlexaContext getAlexaContext() {
        AlexaPayload createPayload;
        AlexaHeader createHeader = createHeader();
        synchronized (this) {
            if (!TextUtils.isEmpty(this.deviceCallStateSerialized)) {
                createPayload = createPayload(this.deviceCallStateSerialized);
            } else {
                throw new IllegalStateException("deviceCallState is null or is empty. Use setDeviceCallStateMethod");
            }
        }
        return new AlexaContext(createHeader, createPayload);
    }

    public synchronized void setDeviceCallStateSerialized(@NonNull String str) {
        this.deviceCallStateSerialized = str;
    }
}
