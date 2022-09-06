package com.amazon.rtcsc.capabilityagent.avs;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscConstants;
import com.amazon.rtcsc.capabilityagent.common.dependencies.RtcscDaggerWrapper;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
import java.util.Collections;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes13.dex */
public class RtcscContextProvider implements AlexaContextsProvider {
    private static final String TAG = "RtcscContextProvider";
    @VisibleForTesting
    volatile AlexaContext mRtcscContext;

    @Inject
    public RtcscContextProvider() {
        RtcscDaggerWrapper.getComponent().inject(this);
        RtcscLogger.i(TAG, "created new RtcscContextProvider", new Object[0]);
    }

    @NonNull
    @VisibleForTesting
    AlexaHeader createHeader() {
        RtcscLogger.d(TAG, "createHeader", new Object[0]);
        return AlexaHeader.builder().setNamespace(RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_NAME).setName(RtcscConstants.RTCSC_CONTEXT_STATE_NAME).build();
    }

    @NonNull
    @VisibleForTesting
    AlexaPayload createPayload(@NonNull String str) {
        RtcscLogger.d(TAG, "createPayload", new Object[0]);
        return new AlexaPayload(str);
    }

    @Override // com.amazon.alexa.api.AlexaContextsProvider
    @NonNull
    public Set<AlexaContext> getAlexaContexts() {
        if (this.mRtcscContext != null) {
            RtcscLogger.d(TAG, "getAlexaContext", new Object[0]);
            return Collections.singleton(this.mRtcscContext);
        }
        throw new IllegalStateException("RTCSC Context is null. Call setRtcscSessionContext");
    }

    public synchronized void setRtcscSessionContext(@NonNull String str) {
        RtcscLogger.i(TAG, "setRtcscSessionContext", new Object[0]);
        if (str != null && !str.isEmpty()) {
            this.mRtcscContext = new AlexaContext(createHeader(), createPayload(str));
        } else {
            RtcscLogger.w(TAG, "rtcscSessionContext is empty. skipping to update context", new Object[0]);
        }
    }

    @VisibleForTesting
    RtcscContextProvider(String str) {
    }
}
