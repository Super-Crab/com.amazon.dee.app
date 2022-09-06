package com.amazon.deecomms.accessories;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AccessoriesHardwareIntentHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AccessoriesHardwareIntentHandler.class);
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    @Inject
    CallManager mCallManager;
    @Inject
    Context mContext;
    @Inject
    PCCContextProvider mPccContextProvider;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState mSipClientState;
    @Inject
    TelephonyManager mTelephonyManager;
    @Inject
    VipCallingHandler mVipCallingHandler;

    public AccessoriesHardwareIntentHandler() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    public void handleCallVipContactEvent(@NonNull Message message) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("comms.eventbus.event.received.");
        outline1.append(EventBusEventType.ACCESSORY_CALL_VIP_CONTACT_EVENT.toString());
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(outline1.toString()), Double.valueOf(1.0d));
        this.mVipCallingHandler.requestOutboundVipCall();
    }

    public void handlePrivacyStatusEvent(@NonNull Message message) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("comms.eventbus.event.received.");
            sb.append(EventBusEventType.ACCESSORY_PRIVACY_BUTTON_TOGGLED.toString());
            MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(sb.toString()), Double.valueOf(1.0d));
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            onAccessoryPrivacyButtonToggle(jSONObject.getBoolean(Constants.ACCESSORY_PRIVACY_STATUS), jSONObject.getBoolean(Constants.ACCESSORY_PRIVACY_INITIAL_STATUS));
        } catch (JSONException unused) {
            LOG.i("Exception while trying to parse event payload JSON");
        }
    }

    @VisibleForTesting
    void onAccessoryPrivacyButtonToggle(boolean z, boolean z2) {
        GeneratedOutlineSupport.outline5("Privacy button toggled to: ", z, LOG);
        Utils.writeBooleanPreferenceToSharedPrefs(this.mContext, Constants.ACCESSORY_PRIVACY_STATUS, z);
        setAlexaCallMute(z);
        setCellularCallMute(z, z2);
    }

    @VisibleForTesting
    void setAlexaCallMute(boolean z) {
        Call currentActiveCall = this.mSipClientState.getCurrentActiveCall();
        if (currentActiveCall != null) {
            CallUtils.toggleMuteAlexaCallForAccessories(z, currentActiveCall);
        } else {
            LOG.i("Active Alexa call is absent.");
        }
    }

    @VisibleForTesting
    void setCellularCallMute(boolean z, boolean z2) {
        if (z2) {
            LOG.i("This is the initial state of cellular call privacy status - skipping, as we don't handle it");
        } else if (CallUtils.getCellularCallState(this.mCallManager, this.mTelephonyManager, this.mContext) != 0) {
            if (this.mPccContextProvider.isHFPPCCCompliantAccessorySessionAvailable()) {
                return;
            }
            if (z) {
                this.mAlexaAudioPlayer.play(R.raw.accessory_mute_tts, false, 0, 2);
            } else {
                this.mAlexaAudioPlayer.play(R.raw.accessory_unmute_tts, false, 0, 2);
            }
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.ACCESSORY_MUTE_TTS_PLAYED);
            generateOperational.getMetadata().put("EventValue", Boolean.valueOf(z));
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
        } else {
            LOG.i("No active cellular call");
        }
    }

    @VisibleForTesting
    public AccessoriesHardwareIntentHandler(@NonNull Context context, @NonNull CallManager callManager, @NonNull TelephonyManager telephonyManager, @NonNull SipClientState sipClientState, @NonNull PCCContextProvider pCCContextProvider, @NonNull AlexaAudioPlayer alexaAudioPlayer, @NonNull VipCallingHandler vipCallingHandler) {
        this.mContext = context;
        this.mTelephonyManager = telephonyManager;
        this.mSipClientState = sipClientState;
        this.mCallManager = callManager;
        this.mPccContextProvider = pCCContextProvider;
        this.mAlexaAudioPlayer = alexaAudioPlayer;
        this.mVipCallingHandler = vipCallingHandler;
    }
}
