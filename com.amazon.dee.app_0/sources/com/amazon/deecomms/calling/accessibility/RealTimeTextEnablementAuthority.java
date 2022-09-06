package com.amazon.deecomms.calling.accessibility;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.sipclient.RealTimeTextMetrics;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class RealTimeTextEnablementAuthority {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RealTimeTextEnablementAuthority.class);
    private final CapabilitiesManager capabilitiesManager;
    private final SipClientState sipClientState;

    @Inject
    public RealTimeTextEnablementAuthority(@NonNull CapabilitiesManager capabilitiesManager, @NonNull @Named("CurrentCall") SipClientState sipClientState) {
        this.capabilitiesManager = capabilitiesManager;
        this.sipClientState = sipClientState;
    }

    public boolean isFeatureFlagAndSettingEnabled() {
        boolean isRealTimeTextEnabled = this.capabilitiesManager.isRealTimeTextEnabled();
        GeneratedOutlineSupport.outline5("Caller has RTT Feature enabled : ", isRealTimeTextEnabled, LOG);
        boolean isRTTSettingEnabled = this.sipClientState.isRTTSettingEnabled();
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Caller is RTT Capable : "), isRealTimeTextEnabled && isRTTSettingEnabled, LOG);
        return isRealTimeTextEnabled && isRTTSettingEnabled;
    }

    public boolean isRTTAvailableForTheCall() {
        boolean z;
        MediaStatus mediaStatus;
        boolean z2 = false;
        if (this.sipClientState.getCurrentActiveCall() != null) {
            Call.Side origin = this.sipClientState.getCurrentActiveCall().getOrigin();
            Call.Side side = Call.Side.Local;
            String str = RealTimeTextConstants.RTT_REQUESTED;
            if (origin == side) {
                boolean isFeatureFlagAndSettingEnabled = isFeatureFlagAndSettingEnabled();
                MediaStatus mediaStatus2 = this.sipClientState.getCurrentActiveCall().getMediaStatus();
                boolean isRemoteRealTimeTextEnabled = mediaStatus2 != null ? mediaStatus2.isRemoteRealTimeTextEnabled() : false;
                GeneratedOutlineSupport.outline5("Callee has RTT enabled : ", isRemoteRealTimeTextEnabled, LOG);
                if (isFeatureFlagAndSettingEnabled && isRemoteRealTimeTextEnabled) {
                    z2 = true;
                }
                RealTimeTextMetrics realTimeTextMetrics = this.sipClientState.getRealTimeTextMetrics();
                if (!isFeatureFlagAndSettingEnabled) {
                    str = RealTimeTextConstants.RTT_NOT_REQUESTED;
                }
                realTimeTextMetrics.setRequestState(str);
                this.sipClientState.getRealTimeTextMetrics().setEnablementState(z2 ? "ENABLED" : "DISABLED");
                return z2;
            }
            boolean isRealTimeTextEnabled = this.capabilitiesManager.isRealTimeTextEnabled();
            if (this.sipClientState.getCurrentActiveCall() == null || (mediaStatus = this.sipClientState.getCurrentActiveCall().getMediaStatus()) == null) {
                z = false;
            } else {
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1(" Caller has RTT enabled : ");
                outline1.append(mediaStatus.isRemoteRealTimeTextEnabled());
                commsLogger.i(outline1.toString());
                z = mediaStatus.isRemoteRealTimeTextEnabled();
            }
            if (isRealTimeTextEnabled && z) {
                z2 = true;
            }
            RealTimeTextMetrics realTimeTextMetrics2 = this.sipClientState.getRealTimeTextMetrics();
            if (!z2) {
                str = RealTimeTextConstants.RTT_NOT_REQUESTED;
            }
            realTimeTextMetrics2.setRequestState(str);
        }
        return z2;
    }

    public boolean shouldShowRTTMessageOnIncomingScreen() {
        MediaStatus mediaStatus;
        boolean isRealTimeTextEnabled = this.capabilitiesManager.isRealTimeTextEnabled();
        boolean z = false;
        boolean isRemoteRealTimeTextEnabled = (this.sipClientState.getCurrentActiveCall() == null || (mediaStatus = this.sipClientState.getCurrentActiveCall().getMediaStatus()) == null) ? false : mediaStatus.isRemoteRealTimeTextEnabled();
        if (isRealTimeTextEnabled && isRemoteRealTimeTextEnabled) {
            z = true;
        }
        GeneratedOutlineSupport.outline5(" RTT message should be shown on incoming call :", z, LOG);
        return z;
    }

    public boolean shouldShowRTTUnavailablePopup() {
        boolean z;
        MediaStatus mediaStatus;
        boolean z2 = false;
        if (this.sipClientState.getCurrentActiveCall() == null || (mediaStatus = this.sipClientState.getCurrentActiveCall().getMediaStatus()) == null) {
            z = false;
        } else {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1(" Remote has RTT enabled : ");
            outline1.append(mediaStatus.isRemoteRealTimeTextEnabled());
            commsLogger.i(outline1.toString());
            z = mediaStatus.isRemoteRealTimeTextEnabled();
        }
        if (isFeatureFlagAndSettingEnabled() && !z) {
            z2 = true;
        }
        GeneratedOutlineSupport.outline5(" Should show RTT Unavailable : ", z2, LOG);
        return z2;
    }
}
