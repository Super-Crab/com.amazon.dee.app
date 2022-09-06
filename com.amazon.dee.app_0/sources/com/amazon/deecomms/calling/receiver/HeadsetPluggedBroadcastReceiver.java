package com.amazon.deecomms.calling.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.AudioOutputController;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class HeadsetPluggedBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_STATE_KEY = "state";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, HeadsetPluggedBroadcastReceiver.class);
    @Inject
    protected AudioManager audioManager;
    @Inject
    protected AudioOutputController audioOutputController;
    @Inject
    protected CapabilitiesManager capabilitiesManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    protected SipClientState sipClientState;

    public HeadsetPluggedBroadcastReceiver() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private void handleHeadsetPluggedForEnhancedProcessingCalls(@NonNull Intent intent) {
        if (!this.sipClientState.isEnhancedProcessedCall()) {
            LOG.d("Not an EP Call. Ignoring EP Audio Management");
        } else if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
            boolean z = true;
            if (intent.getIntExtra("state", 0) != 1) {
                z = false;
            }
            this.audioOutputController.wiredHeadsetStateChanged(z);
        } else if (!"android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
        } else {
            this.audioOutputController.wiredHeadsetStateChanged(false);
        }
    }

    @Deprecated
    private void handleHeadsetPluggedForPeerToPeerCalls(@NonNull Intent intent, @NonNull Context context) {
        if (TelecomUtils.setAndGetTelecomSupported(context)) {
            LOG.i("Not modifying headset state as telecom will manage it for us");
        } else if (this.sipClientState.getCallType() != CallType.VIDEO && this.sipClientState.getCallType() != CallType.VIDEO_DEVICE_TARGETED_DROP_IN && this.sipClientState.getCallType() != CallType.VIDEO_DROP_IN) {
        } else {
            boolean z = true;
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                if (intent.getIntExtra("state", 0) != 1) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                LOG.i("Headset plugged in");
                this.audioManager.setSpeakerphoneOn(false);
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone false; speakerphone was set to ");
                outline1.append(this.audioManager.isSpeakerphoneOn());
                commsLogger.i(outline1.toString());
            } else if (!"android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            } else {
                LOG.i("Headset plugged out");
                this.audioManager.setSpeakerphoneOn(true);
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
                outline12.append(this.audioManager.isSpeakerphoneOn());
                commsLogger2.i(outline12.toString());
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        handleHeadsetPluggedForPeerToPeerCalls(intent, context);
        handleHeadsetPluggedForEnhancedProcessingCalls(intent);
    }
}
