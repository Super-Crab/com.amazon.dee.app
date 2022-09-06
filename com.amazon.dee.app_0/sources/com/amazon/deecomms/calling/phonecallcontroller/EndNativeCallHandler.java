package com.amazon.deecomms.calling.phonecallcontroller;

import android.annotation.TargetApi;
import android.content.Context;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.ATCommand;
import com.amazon.deecomms.accessories.AccessoryUtilities;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.Utils;
/* loaded from: classes12.dex */
public class EndNativeCallHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EndNativeCallHandler.class);
    private final Context context;
    private NoCallPermissionHandler noCallPermissionHandler;
    private final PCCContextProvider pccContextProvider;
    private final TelecomManager telecomManager;

    public EndNativeCallHandler(@NonNull NoCallPermissionHandler noCallPermissionHandler, @NonNull PCCContextProvider pCCContextProvider, @NonNull TelecomManager telecomManager, @NonNull Context context) {
        this.noCallPermissionHandler = noCallPermissionHandler;
        this.pccContextProvider = pCCContextProvider;
        this.telecomManager = telecomManager;
        this.context = context;
    }

    @TargetApi(28)
    private void endIncomingNativeCall() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ANSWER_PHONE_CALLS") == 0) {
            LOG.i("Accepting native phone call...");
            this.telecomManager.endCall();
            PCCDirectiveHandler.recordPCCMetric(CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_END), true);
            return;
        }
        LOG.i("No answer phone call permission to accept the call");
        this.noCallPermissionHandler.handleNoPermission(3, null, MetricKeys.CPCC_CALLING_END);
    }

    public void endNativePhoneCall() {
        if (this.pccContextProvider.isHFPPCCCompliantAccessorySessionAvailable()) {
            LOG.i("Accept directive received when there is HFP...sending AT Command");
            AccessoryUtilities.forwardATCommand(ATCommand.END_CALL.toString(), CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_END), this.context);
        } else if (Utils.isPieAndAbove()) {
            LOG.i("Accept directive when there is no HFP and Oreo above...making API call for Accept");
            endIncomingNativeCall();
        } else {
            LOG.i("Accept call directive is received when there is no HFP and device is below Oreo");
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.CPCC_CALLING_END);
            generateOperational.getMetadata().put("EventValue", "OS not supported");
            PCCDirectiveHandler.recordPCCMetric(generateOperational, false);
        }
    }
}
