package com.amazon.deecomms.alexa;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.accessories.ATCommand;
import com.amazon.deecomms.accessories.AccessoryUtilities;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.enums.AssistCspId;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.RingCallDirective;
import com.amazon.deecomms.calling.model.VoxCallInfo;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class CommsDirectiveHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsDirectiveHandler.class);
    private final ApplicationManager applicationManager;
    private final CallContext callContext;
    private final Lazy<CallManager> callManagerLazy;
    private final CallingFacade callingFacade;
    private final CapabilitiesManager capabilitiesManager;
    private final CommsIdentityManager commsIdentityManager;
    private final Context context;
    private final EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    private final NameChangeObservable nameChangeObservable;
    private final SipClientState sipClientState;
    private final VoxUtils voxUtils;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Inject
    public CommsDirectiveHandler(@NonNull Context context, @NonNull CommsIdentityManager commsIdentityManager, @NonNull Lazy<CallManager> lazy, @NonNull VoxUtils voxUtils, @NonNull ApplicationManager applicationManager, @NonNull CallingFacade callingFacade, @NonNull CapabilitiesManager capabilitiesManager, @NonNull @Named("CurrentCall") SipClientState sipClientState, @NonNull CallContext callContext, @NonNull NameChangeObservable nameChangeObservable, @NonNull EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        this.callManagerLazy = lazy;
        this.commsIdentityManager = commsIdentityManager;
        this.context = context;
        this.voxUtils = voxUtils;
        this.applicationManager = applicationManager;
        this.capabilitiesManager = capabilitiesManager;
        this.callingFacade = callingFacade;
        this.nameChangeObservable = nameChangeObservable;
        this.sipClientState = sipClientState;
        this.callContext = callContext;
        this.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    private boolean handleAcceptCall(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("sipCommand").getJSONObject("payload");
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.HALLO_ACCEPT_CALL_DIRECTIVE);
            generateOperational.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, jSONObject.getString("callId"));
            recordDirectiveReceptionMetric(generateOperational);
            CallUtils.acceptCall(this.context, jSONObject.getString("callId"));
            return true;
        } catch (JSONException e) {
            LOG.e("Exception processing accept call directive ", e);
            return false;
        }
    }

    private boolean handleCallMediaStateChange(@NonNull String str) {
        recordDirectiveReceptionMetric(CounterMetric.generateOperational(MetricKeys.HALLO_MEDIA_STATE_CHANGE_DIRECTIVE));
        if (!Utils.isFireOS()) {
            LOG.i("Changing call media state is not supported in this device");
            return true;
        }
        try {
            this.callManagerLazy.mo358get().enableVideoStreamInVideoCall("ON".equalsIgnoreCase(new JSONObject(str).getJSONObject("sipCommand").getJSONObject("payload").getJSONObject("callMediaState").getString("outboundVideo")));
            return true;
        } catch (JSONException e) {
            LOG.e("Exception processing call media state change directive ", e);
            return false;
        }
    }

    private boolean handleIgnoreCall(@NonNull String str) {
        PCCContextProvider pCCContextProvider = CommsDaggerWrapper.getComponent().getPCCContextProvider();
        TelephonyManager telephonyManager = CommsDaggerWrapper.getComponent().getTelephonyManager();
        if (Utils.areAccessoriesConnected() && telephonyManager.getCallState() == 1) {
            if (pCCContextProvider.isHFPPCCCompliantAccessorySessionAvailable()) {
                LOG.i("Handling Ignore call when PCC HFP session is available and telephony is in Ringing state");
                AccessoryUtilities.forwardATCommand(ATCommand.END_CALL.toString(), CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_END), this.context);
            } else if (this.callManagerLazy.mo358get().isInAlexaCallInboundRingingMode()) {
                handleTerminateCall(str);
            } else {
                LOG.i("Handling Ignore SIP call when PCC is available without HFP - swallowing the directive");
            }
            return true;
        }
        return handleTerminateCall(str);
    }

    private boolean handleTerminateCall(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("sipCommand").getJSONObject("payload");
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.HALLO_END_CALL_DIRECTIVE);
            generateOperational.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, jSONObject.getString("callId"));
            recordDirectiveReceptionMetric(generateOperational);
            CallUtils.cancelAnyCall(jSONObject.getString("callId"));
            return true;
        } catch (JSONException e) {
            LOG.e("Exception processing terminate call directive ", e);
            return false;
        }
    }

    private void recordDirectiveReceptionMetric(@NonNull CounterMetric counterMetric) {
        MetricsHelper.recordCounterMetric(counterMetric, Double.valueOf(1.0d));
    }

    @VisibleForTesting
    boolean canProcessDirective(@NonNull String str, boolean z) {
        if (str.equals(HalloConstants.WARM_UP)) {
            return false;
        }
        if (this.commsIdentityManager.isCommsIdPopulated("CommsDirectiveHandler.canProcessDirective", false)) {
            return true;
        }
        if (!SharedPreferencesUtils.getBooleanValue(this.context, Constants.COMMS_SUPPORT, false)) {
            LOG.i("Cannot process directive as comms is not supported on this device");
            return false;
        }
        CommsProvisionStatus provisionStatus = this.commsIdentityManager.getProvisionStatus(true, "CommsDirectiveHandler.canProcessDirective", false);
        if (CommsProvisionStatus.PROVISIONED.equals(provisionStatus) || CommsProvisionStatus.AUTO_PROVISIONED.equals(provisionStatus)) {
            return true;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.i("Cannot process directive as user is " + provisionStatus);
        return false;
    }

    @VisibleForTesting
    BeginCallPayload extractBeginCallPayload(@NonNull String str) {
        try {
            BeginCallPayload beginCallPayload = (BeginCallPayload) this.objectMapper.readValue(str, BeginCallPayload.class);
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Extracted payload: ");
            sb.append(beginCallPayload);
            commsLogger.d(sb.toString());
            return beginCallPayload;
        } catch (IOException e) {
            LOG.e("Exception while processing begin call directive ", e);
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.BEGIN_CALL_DIRECTIVE_PARSE_FAILURE);
            return null;
        }
    }

    @VisibleForTesting
    boolean handleBeginCall(@NonNull String str) {
        boolean z;
        boolean z2;
        try {
            BeginCallPayload extractBeginCallPayload = extractBeginCallPayload(str);
            if (extractBeginCallPayload != null) {
                Iterator<Map<String, Object>> it2 = extractBeginCallPayload.sipCommand.payload.headers.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    } else if (it2.next().containsKey(Constants.GROUPID_HEADER)) {
                        z2 = true;
                        break;
                    }
                }
                if (shouldUseNewBeginCallProcessingFlow(z2, extractBeginCallPayload.getCallProvider(), extractBeginCallPayload.getCspId())) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Using new begin call flow for call id : ");
                    sb.append(extractBeginCallPayload.getCallId());
                    commsLogger.i(sb.toString());
                    recordMetricsIfNeeded(extractBeginCallPayload.getCspId());
                    this.callingFacade.beginCall(extractBeginCallPayload);
                    return true;
                }
            }
            LOG.i("Using old begin call flow");
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("displayInfo");
            JSONObject jSONObject3 = jSONObject.getJSONObject("sipCommand").getJSONObject("payload");
            String replace = jSONObject2.getJSONObject("calleePartyInfo").getString("name").replace("<scrub>", "").replace("</scrub>", "");
            String replace2 = jSONObject2.getJSONObject("calleePartyInfo").getString("endpointDescription").replace("<scrub>", "").replace("</scrub>", "");
            String string = jSONObject3.getJSONObject("mediaInfo").getJSONObject("srtp").getString("keying");
            String string2 = jSONObject2.getJSONObject("mediaSettingsInfo").getString("enhancedProcessing");
            JSONArray jSONArray = jSONObject3.getJSONObject(WebRTCSignalTypes.OFFER).getJSONArray("mediaStreams");
            int i = 0;
            while (true) {
                if (i >= jSONArray.length()) {
                    z = false;
                    break;
                } else if (TextUtils.equals("VIDEO", ((JSONObject) jSONArray.get(i)).getString("type"))) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            VoxCallInfo voxCallInfo = new VoxCallInfo();
            voxCallInfo.setCalleeCommsID(jSONObject3.getJSONObject("callee").getString("id"));
            voxCallInfo.setDropIn(Boolean.valueOf(jSONObject3.getBoolean("isDropIn")));
            voxCallInfo.setName(jSONObject3.getJSONObject("callee").getString("name"));
            voxCallInfo.setSipUri(jSONObject3.getJSONObject("callee").getString("uri"));
            voxCallInfo.setProvider(jSONObject3.getString("callProvider"));
            voxCallInfo.setDisplayName(replace);
            voxCallInfo.setCalleeNumberType(replace2);
            voxCallInfo.setCallID(jSONObject3.getString("callId"));
            voxCallInfo.setVideo(Boolean.valueOf(z));
            voxCallInfo.setSrtpKey(string);
            voxCallInfo.setEnhancedProcessingState(EnhancedProcessingState.valueOf(string2));
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.HALLO_BEGIN_CALL_DIRECTIVE);
            generateOperational.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, jSONObject3.getString("callId"));
            recordDirectiveReceptionMetric(generateOperational);
            CommsLogger commsLogger2 = LOG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Starting a call using the old flow with call id : ");
            sb2.append(voxCallInfo.getCallID());
            commsLogger2.i(sb2.toString());
            this.voxUtils.beginCall(voxCallInfo);
            return true;
        } catch (JSONException e) {
            LOG.e("Exception processing begin call directive ", e);
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean handleDirective(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        char c;
        LOG.i("Received directive " + str + " under namespace " + str2);
        if (!canProcessDirective(str, shouldSkipProvisionCheck(str, str3))) {
            return true;
        }
        Bundle outline13 = GeneratedOutlineSupport1.outline13(Constants.ORIGINATED_FROM_VOICE, true);
        switch (str.hashCode()) {
            case -1421439120:
                if (str.equals(HalloConstants.IGNORE_CALL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1173412658:
                if (str.equals(HalloConstants.RING_CALL)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -727028177:
                if (str.equals(HalloConstants.SHOW_MY_CONTACTS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -392539728:
                if (str.equals(HalloConstants.SHOW_ACTIVE_CONTACTS)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 56895929:
                if (str.equals(HalloConstants.END_CALL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 765967270:
                if (str.equals(HalloConstants.ACCEPT_CALL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 930613895:
                if (str.equals(HalloConstants.BEGIN_CALL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1391632621:
                if (str.equals(HalloConstants.CALL_MEDIA_STATE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return handleBeginCall(str3);
            case 1:
                return handleRingCall(str3);
            case 2:
                return handleAcceptCall(str3);
            case 3:
                return handleIgnoreCall(str3);
            case 4:
                return handleTerminateCall(str3);
            case 5:
                return handleCallMediaStateChange(str3);
            case 6:
                return handleShowMyContacts(outline13);
            case 7:
                return handleShowActiveContacts(outline13);
            default:
                LOG.i("Unknown directive under SIP Client namespace.");
                return true;
        }
    }

    @VisibleForTesting
    boolean handleRingCall(@NonNull String str) {
        GeneratedOutlineSupport1.outline159("Handling ringcall ", str, LOG);
        try {
            RingCallDirective ringCallDirective = (RingCallDirective) this.objectMapper.readValue(str, RingCallDirective.class);
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Extracted payload: ");
            sb.append(ringCallDirective);
            commsLogger.d(sb.toString());
            this.sipClientState.setEnhancedProcessingState(EnhancedProcessingState.valueOf(ringCallDirective.displayInfo.mediaSettingsInfo.enhancedProcessing));
            this.sipClientState.setEnhancedProcessingSetting(!ringCallDirective.displayInfo.mediaSettingsInfo.isEnhancedProcessingOptInRequired);
            String replace = ringCallDirective.displayInfo.callerPartyInfo.name.replace("<scrub>", "").replace("</scrub>", "");
            if (this.sipClientState.isGroupCall() && !replace.startsWith(this.context.getString(R.string.group_calling_caller_name_identifier))) {
                StringBuilder sb2 = new StringBuilder(this.context.getString(R.string.group_calling_caller_name_identifier));
                sb2.append(" ");
                sb2.append(replace);
                replace = sb2.toString();
            }
            this.sipClientState.setRemoteParticipantName(replace);
            this.callContext.setRemoteParticipantName(replace);
            this.nameChangeObservable.onCustomerNameChanged(replace);
            boolean isEnhancedProcessedCall = this.sipClientState.isEnhancedProcessedCall();
            CommsLogger commsLogger2 = LOG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Call is using Enhanced Processing : ");
            sb3.append(isEnhancedProcessedCall);
            commsLogger2.i(sb3.toString());
            this.callContext.setEnhancedProcessingCall(isEnhancedProcessedCall);
            this.enhancedProcessingStateObservable.onEnhancedProcessingStateChange(isEnhancedProcessedCall);
            return true;
        } catch (IOException e) {
            LOG.e("Exception while processing begin call directive ", e);
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.BEGIN_CALL_DIRECTIVE_PARSE_FAILURE);
            return false;
        }
    }

    @VisibleForTesting
    boolean handleShowActiveContacts(final Bundle bundle) {
        this.handler.post(new Runnable() { // from class: com.amazon.deecomms.alexa.-$$Lambda$CommsDirectiveHandler$Va5BchF2w3d2Hwykk-2kPZfwsKc
            @Override // java.lang.Runnable
            public final void run() {
                CommsDirectiveHandler.this.lambda$handleShowActiveContacts$1$CommsDirectiveHandler(bundle);
            }
        });
        return true;
    }

    @VisibleForTesting
    boolean handleShowMyContacts(final Bundle bundle) {
        final CommsView commsView = this.capabilitiesManager.isSunflowersEnabled() ? CommsView.ReactNativeContactList : CommsView.ContactList;
        this.handler.post(new Runnable() { // from class: com.amazon.deecomms.alexa.-$$Lambda$CommsDirectiveHandler$Nl_XlI3fooxHNuOd2ccWCEGbSTY
            @Override // java.lang.Runnable
            public final void run() {
                CommsDirectiveHandler.this.lambda$handleShowMyContacts$0$CommsDirectiveHandler(commsView, bundle);
            }
        });
        return true;
    }

    @VisibleForTesting
    boolean isAssistCall(@NonNull String str) {
        return this.capabilitiesManager.isAssistSipCallingEnabled() && AssistCspId.isValidCsp(str);
    }

    public /* synthetic */ void lambda$handleShowActiveContacts$1$CommsDirectiveHandler(Bundle bundle) {
        this.applicationManager.navigateToView(CommsView.ReactNativeConversationList, bundle, true);
    }

    public /* synthetic */ void lambda$handleShowMyContacts$0$CommsDirectiveHandler(CommsView commsView, Bundle bundle) {
        this.applicationManager.navigateToView(commsView, bundle, true);
    }

    @VisibleForTesting
    void recordMetricsIfNeeded(@NonNull String str) {
        if (AssistCspId.isValidCsp(str)) {
            MetricsHelper.recordCounterMetricOperational(MetricKeys.ASSIST_BEGIN_CALL, 1.0d);
        }
    }

    @VisibleForTesting
    boolean shouldSkipProvisionCheck(@NonNull String str, @NonNull String str2) {
        if (!str.equals(HalloConstants.BEGIN_CALL)) {
            return false;
        }
        BeginCallPayload extractBeginCallPayload = extractBeginCallPayload(str2);
        return isAssistCall(extractBeginCallPayload != null ? extractBeginCallPayload.getCspId() : "") && CommsProvisionStatus.AUTO_PROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "CommsDirectiveHandler.canProcessDirective", false));
    }

    @VisibleForTesting
    boolean shouldUseNewBeginCallProcessingFlow(@NonNull boolean z, @NonNull String str, @NonNull String str2) {
        return !CallProvider.COBO.equalsIgnoreCase(str) || z || isAssistCall(str2);
    }
}
