package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.incallcommands.models.PCCInCallCommandModel;
import com.amazon.deecomms.calling.ui.NativeCallActivity;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import java.security.SecureRandom;
/* loaded from: classes12.dex */
public class NoCallPermissionHandler implements INoCallPermissionHandler {
    private final AlexaServicesConnection alexaServicesConnection;
    private final CapabilitiesManager capabilitiesManager;
    private final CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener;
    private final CommsEventSender commsEventSender;
    private final Context context;
    private final PCCQueuedEvents mPccPendingEventsManager;
    private final PCCConnectionEndpointHandler pccConnectionEndpointHandler;
    private final PCCContextProvider pccContextProvider;

    public NoCallPermissionHandler(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CommsEventSender commsEventSender, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull PCCContextProvider pCCContextProvider, @NonNull Context context, @NonNull PCCQueuedEvents pCCQueuedEvents, @NonNull PCCConnectionEndpointHandler pCCConnectionEndpointHandler) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.capabilitiesManager = capabilitiesManager;
        this.commsEventSender = commsEventSender;
        this.commsAlexaServicesConnectionListener = commsAlexaServicesConnectionListener;
        this.pccContextProvider = pCCContextProvider;
        this.context = context;
        this.mPccPendingEventsManager = pCCQueuedEvents;
        this.pccConnectionEndpointHandler = pCCConnectionEndpointHandler;
    }

    @Override // com.amazon.deecomms.calling.phonecallcontroller.INoCallPermissionHandler
    public void handleNoPermission(int i, @Nullable String str, @NonNull String str2) {
        int nextInt = new SecureRandom().nextInt();
        this.commsEventSender.manageInCallCommands(new PCCInCallCommandModel(PCCEventModel.getFailedEvent(nextInt), this.pccContextProvider, nextInt), this.commsAlexaServicesConnectionListener, this.alexaServicesConnection, this.mPccPendingEventsManager, this.pccConnectionEndpointHandler);
        CounterMetric generateOperational = CounterMetric.generateOperational(str2);
        generateOperational.getMetadata().put("errorSource", MetricKeys.VALUE_NO_PERMISSIONS);
        PCCDirectiveHandler.recordPCCMetric(generateOperational, false);
        if (DeviceUtils.isCommsNativeDefaulted(this.capabilitiesManager)) {
            Intent intent = new Intent(this.context, NativeCallActivity.class);
            intent.setFlags(268435456);
            intent.putExtra(Constants.NativeCalling.NO_CALL_PERMISSIONS_SOURCE, MetricKeys.AMPD);
            intent.putExtra(Constants.NativeCalling.EXTRA_ACTION_AFTER_PERMISSION_GRANTED, i);
            intent.putExtra(Constants.NativeCalling.EXTRA_ACTION_PCC, str);
            ContextCompat.startActivity(this.context, intent, null);
        }
    }
}
