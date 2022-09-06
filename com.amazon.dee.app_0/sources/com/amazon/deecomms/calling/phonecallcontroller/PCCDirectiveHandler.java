package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PCCDirectiveHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PCCDirectiveHandler.class);
    private final AcceptNativeCallHandler acceptNativeCallHandler;
    private final CapabilitiesManager capabilitiesManager;
    private final EndNativeCallHandler endNativeCallHandler;
    private final MakeNativeCallHandler makeNativeCallHandler;
    private final NoCallPermissionHandler noCallPermissionHandler;

    public PCCDirectiveHandler(@NonNull CapabilitiesManager capabilitiesManager, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull AcceptNativeCallHandler acceptNativeCallHandler, @NonNull EndNativeCallHandler endNativeCallHandler, @NonNull NoCallPermissionHandler noCallPermissionHandler) {
        this.capabilitiesManager = capabilitiesManager;
        this.makeNativeCallHandler = makeNativeCallHandler;
        this.noCallPermissionHandler = noCallPermissionHandler;
        this.acceptNativeCallHandler = acceptNativeCallHandler;
        this.endNativeCallHandler = endNativeCallHandler;
    }

    private void recordPCCDirectiveReceptionMetric(@NonNull String str, @NonNull String str2) {
        try {
            String string = new JSONObject(str).getString("callId");
            CounterMetric generateOperational = CounterMetric.generateOperational(str2);
            generateOperational.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, string);
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(1.0d));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void recordPCCMetric(@NonNull CounterMetric counterMetric, @NonNull boolean z) {
        SetupCallHelper.MetadataBuilder metadataBuilder = new SetupCallHelper.MetadataBuilder();
        SetupCallHelper.addPCCInformationForCalling(metadataBuilder, true);
        String str = Utils.areAccessoriesConnected() ? MetricKeys.ACCESSORY : DeviceUtils.isAMPDDevice() ? MetricKeys.AMPD : MetricKeys.VOX;
        counterMetric.setMetricName(counterMetric.getMetricName() + str);
        counterMetric.getMetadata().putAll(metadataBuilder.build());
        if (z) {
            MetricsHelper.recordCounterMetric(counterMetric, Double.valueOf(1.0d));
        } else {
            MetricsHelper.recordCounterMetric(counterMetric, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
        }
    }

    public boolean handleDirective(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        char c;
        LOG.d("Handling PCC Directive: " + str);
        int hashCode = str.hashCode();
        if (hashCode == 2129808) {
            if (str.equals(PCCConstants.DIAL_DIRECTIVE)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 2587682) {
            if (hashCode == 1966025694 && str.equals(PCCConstants.ACCEPT_DIRECTIVE)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(PCCConstants.STOP_DIRECTIVE)) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            try {
                String encode = Uri.encode(new JSONObject(str2).getJSONObject("callee").getJSONObject(PCCConstants.DIAL_DIRCTIVE_DEFAULT_ADDRESS_KEY).getString("value"));
                LOG.i("Dial directive received ...making API call for dial");
                try {
                    CommsDaggerWrapper.getComponent().getEventBus().publish(new Message.Builder().setEventType(EventBusEventType.START_NATIVE_CALL.toString()).build());
                    StringBuilder sb = new StringBuilder();
                    sb.append("comms.eventbus.event.published.");
                    sb.append(EventBusEventType.START_NATIVE_CALL.toString());
                    MetricsHelper.recordCounterMetric(CounterMetric.generateOperational(sb.toString()), Double.valueOf(1.0d));
                } catch (Exception e) {
                    LOG.e("failed to send START_NATIVE_CALL event", e);
                }
                recordPCCDirectiveReceptionMetric(str2, MetricKeys.PCC_DIAL_DIRECTIVE);
                this.makeNativeCallHandler.initiateNativePhoneCall(encode, this.noCallPermissionHandler, CommsDaggerWrapper.getComponent().getDriveModeSharedPreferencesUseCase().isInDriveMode());
            } catch (JSONException e2) {
                LOG.e("Error parsing PCC Dial Directive: ", e2);
                CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_DIAL);
                generateOperational.getMetadata().put("errorSource", MetricKeys.VALUE_JSON_EXCEPTION);
                recordPCCMetric(generateOperational, false);
                return false;
            }
        } else if (c == 1) {
            recordPCCDirectiveReceptionMetric(str2, MetricKeys.PCC_ACCEPT_DIRECTIVE);
            this.acceptNativeCallHandler.acceptNativePhoneCall();
        } else if (c != 2) {
            LOG.e("Received unrecognized directive: " + str + " under PCC Namespace");
            CounterMetric generateOperational2 = CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_UNSUPPORTED);
            generateOperational2.getMetadata().put("EventValue", str);
            recordPCCMetric(generateOperational2, true);
            return true;
        } else {
            recordPCCDirectiveReceptionMetric(str2, MetricKeys.PCC_STOP_DIRECTIVE);
            this.endNativeCallHandler.endNativePhoneCall();
        }
        return true;
    }
}
