package com.amazon.deecomms.calling.core;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.calling.enums.AssistCspId;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.google.common.base.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class CallInitiationAuthority {
    private CapabilitiesManager capabilitiesManager;
    private final SipClientState sipClientState;
    private boolean wasPreviousCallAssistOrGroupCall;

    @Inject
    public CallInitiationAuthority(@NonNull @Named("CurrentCall") SipClientState sipClientState, @NonNull CapabilitiesManager capabilitiesManager) {
        this.capabilitiesManager = capabilitiesManager;
        this.sipClientState = sipClientState;
    }

    @VisibleForTesting
    public boolean getPreviousCallState() {
        return this.wasPreviousCallAssistOrGroupCall;
    }

    public boolean isNewCallInitiationUIFlowEnabled(@NonNull Optional<String> optional, @NonNull Optional<String> optional2) {
        return !this.wasPreviousCallAssistOrGroupCall && !this.sipClientState.isGroupCall() && (!optional.isPresent() || !optional.get().equalsIgnoreCase(CallProvider.COBO)) && (!optional2.isPresent() || !AssistCspId.isValidCsp(optional2.get()));
    }

    public void resetPreviousCallState() {
        this.wasPreviousCallAssistOrGroupCall = false;
    }

    public void setPreviousCallState(boolean z) {
        this.wasPreviousCallAssistOrGroupCall = z;
    }
}
