package com.amazon.deecomms.calling.phonecallcontroller;

import android.content.Context;
import android.telephony.PhoneStateListener;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.pcc.PCCConnectionEndpointHandler;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.alexa.unsent.event.pcc.PCCQueuedEvents;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.incallcommands.models.PCCInCallCommandModel;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class CallStateListener extends PhoneStateListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallStateListener.class);
    private final Lazy<PCCContextProvider> alexaContextProvider;
    private final Lazy<AlexaServicesConnection> alexaServicesConnection;
    private final Lazy<CallManager> callManager;
    private final Lazy<CapabilitiesManager> capabilitiesManager;
    private final Lazy<CommsAlexaServicesConnectionListener> commsAlexaServicesConnectionListener;
    private final Lazy<Context> context;
    private final Lazy<CommsEventSender> mCommsEventSender;
    private AlexaCallNotificationMonitor monitor;
    private final Lazy<PCCConnectionEndpointHandler> pccEndpointHandler;
    private final Lazy<PCCQueuedEvents> pccUnsentEventsManager;

    public CallStateListener(@NonNull Lazy<Context> lazy, @NonNull Lazy<CallManager> lazy2, @NonNull Lazy<CommsEventSender> lazy3, @NonNull Lazy<AlexaServicesConnection> lazy4, @NonNull Lazy<CommsAlexaServicesConnectionListener> lazy5, @NonNull Lazy<CapabilitiesManager> lazy6, @NonNull Lazy<PCCContextProvider> lazy7, @NonNull Lazy<PCCQueuedEvents> lazy8, @NonNull Lazy<PCCConnectionEndpointHandler> lazy9) {
        this.context = lazy;
        this.callManager = lazy2;
        this.mCommsEventSender = lazy3;
        this.alexaServicesConnection = lazy4;
        this.commsAlexaServicesConnectionListener = lazy5;
        this.capabilitiesManager = lazy6;
        this.alexaContextProvider = lazy7;
        this.pccUnsentEventsManager = lazy8;
        this.pccEndpointHandler = lazy9;
        LOG.i("CallStateListener constructor");
    }

    @Override // android.telephony.PhoneStateListener
    public void onCallStateChanged(int i, String str) {
        super.onCallStateChanged(i, str);
        onCallStateChangedHelper(i, str);
    }

    @VisibleForTesting
    void onCallStateChangedHelper(int i, String str) {
        if (Utils.getBooleanPreferenceFromSharedPrefs(this.context.mo358get(), TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false) && this.callManager.mo358get().isInAlexaCallMode()) {
            CommsLogger commsLogger = LOG;
            commsLogger.i("Alexa call state changed to " + i + ", or cellular call state changed but user is in Alexa call mode");
            return;
        }
        CommsLogger commsLogger2 = LOG;
        commsLogger2.i("cellular call state changed to " + i);
        boolean areAccessoriesConnected = Utils.areAccessoriesConnected();
        if (!areAccessoriesConnected && !DeviceUtils.isCommsNativeDefaulted(this.capabilitiesManager.mo358get())) {
            LOG.i("No PCC accessory available and no native call capable device...ignoring call state change.");
        } else if (this.monitor == null && areAccessoriesConnected) {
        } else {
            int generateInt = Utils.generateInt();
            this.mCommsEventSender.mo358get().manageInCallCommands(new PCCInCallCommandModel(PCCEventModel.getPCCModelForState(i, generateInt), this.alexaContextProvider.mo358get(), generateInt), this.commsAlexaServicesConnectionListener.mo358get(), this.alexaServicesConnection.mo358get(), this.pccUnsentEventsManager.mo358get(), this.pccEndpointHandler.mo358get());
            if (!areAccessoriesConnected) {
                return;
            }
            this.monitor.updateMonitorNotification();
        }
    }

    public void setMonitor(@NonNull AlexaCallNotificationMonitor alexaCallNotificationMonitor) {
        this.monitor = alexaCallNotificationMonitor;
    }
}
