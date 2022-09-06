package com.amazon.deecomms.accessories.monitors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallListener;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceListener;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.phonecallcontroller.CallStateListener;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class AlexaCallNotificationMonitor implements CallNotificationStateMonitor, CallListener, DeviceCallingServiceListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaCallNotificationMonitor.class);
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    Context context;
    @Inject
    CallManager mCallManager;
    private final Context mContext;
    @Inject
    protected CallStateListener mCustomPhoneStateListener;
    @Inject
    DeviceCallingService mDeviceCallingService;
    @Inject
    TelephonyManager mTelephonyManager;
    private final Set<CallNotificationStateMonitor.Observer> observers = new HashSet();

    public AlexaCallNotificationMonitor(@NonNull Context context) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mContext = context;
        this.mCustomPhoneStateListener.setMonitor(this);
        IntentFilter intentFilter = new IntentFilter(Constants.COMMS_DEVICE_CALLING_SERVICE_REGISTERED_NOTIFICATION);
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(new BroadcastReceiver() { // from class: com.amazon.deecomms.accessories.monitors.AlexaCallNotificationMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                AlexaCallNotificationMonitor.LOG.i("Received a new DeviceCallingService registered notification...");
                AlexaCallNotificationMonitor alexaCallNotificationMonitor = AlexaCallNotificationMonitor.this;
                alexaCallNotificationMonitor.mDeviceCallingService.registerListener(alexaCallNotificationMonitor);
            }
        }, intentFilter);
    }

    private int getCallNotificationStatusForGivenAlexaCallStatus(int i) {
        int cellularCallState = CallUtils.getCellularCallState(this.mCallManager, this.mTelephonyManager, this.mContext);
        if (cellularCallState != 0) {
            if (cellularCallState == 1) {
                if (i != CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()) {
                    return i | CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus();
                }
                return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
            } else if (cellularCallState != 2) {
                return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
            } else {
                if (i != CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()) {
                    return i | CallNotificationStatus.ACTIVE_CALL.getCallNotificationStatus();
                }
                return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
            }
        }
        return i;
    }

    public static int getConsolidatedCallNotificationStatus(@NonNull TelephonyManager telephonyManager, @NonNull CallManager callManager, @NonNull Context context) {
        int cellularCallState = CallUtils.getCellularCallState(callManager, telephonyManager, context);
        if (cellularCallState == 0) {
            if (callManager.isInAlexaCallInboundRingingMode()) {
                return CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus();
            }
            if (callManager.isInAlexaCallMode()) {
                return CallNotificationStatus.ACTIVE_CALL.getCallNotificationStatus();
            }
            return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
        } else if (cellularCallState == 1) {
            if (callManager.isInAlexaCallOffhookMode()) {
                return CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus() | CallNotificationStatus.ACTIVE_CALL.getCallNotificationStatus();
            }
            return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
        } else if (cellularCallState != 2) {
            return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
        } else {
            if (callManager.isInAlexaCallInboundRingingMode()) {
                return CallNotificationStatus.ACTIVE_CALL.getCallNotificationStatus() | CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus();
            }
            return CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus();
        }
    }

    private void onCallNotificationStatusChanged(int i) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Accessory call notification status changed to " + i);
        for (CallNotificationStateMonitor.Observer observer : new HashSet(this.observers)) {
            observer.onCallStatusChanged(i);
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public void addObserver(CallNotificationStateMonitor.Observer observer) {
        int consolidatedCallNotificationStatus;
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        CommsLogger commsLogger = LOG;
        commsLogger.i("Adding a new observer to AlexaCallNotificationMonitor: " + this);
        if (this.observers.isEmpty()) {
            this.mDeviceCallingService.registerListener(this);
        }
        if (!this.observers.add(observer) || (consolidatedCallNotificationStatus = getConsolidatedCallNotificationStatus(this.mTelephonyManager, this.mCallManager, this.context)) == CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()) {
            return;
        }
        CommsLogger commsLogger2 = LOG;
        commsLogger2.i("Notifying call status change: " + consolidatedCallNotificationStatus);
        observer.onCallStatusChanged(getConsolidatedCallNotificationStatus(this.mTelephonyManager, this.mCallManager, this.context));
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void configureCall(Call call) {
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public int getCallNotificationStatus() {
        return getConsolidatedCallNotificationStatus(this.mTelephonyManager, this.mCallManager, this.context);
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onAccepted(Call call) {
        onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.ACTIVE_CALL.getCallNotificationStatus()));
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onAuthTokenExpiring(DeviceCallingService deviceCallingService, long j) {
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onCallAdded(@NonNull Call call) {
        call.registerCallListener(this);
        if (call.getOrigin() == Call.Side.Local) {
            onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.OUTBOUND_CALL.getCallNotificationStatus()));
        } else if (!Utils.shouldAllowAlexaCall(this.mContext) && !this.mCallManager.isInAlexaCallInboundRingingMode()) {
        } else {
            onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus()));
        }
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onCallRemoved(@NonNull Call call) {
        call.unregisterCallListener(this);
        onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()));
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onEarlyMedia(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onError(Call call, ErrorCode errorCode, int i, String str) {
        onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()));
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onHangup(Call call, HangupReason hangupReason) {
        onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()));
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onMediaDTMFResponse(Call call, boolean z, String str) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onReconnect(Call call, boolean z, boolean z2, String str) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRemoteParticipantUpdated(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRinging(@NonNull Call call) {
        if (call.getOrigin() == Call.Side.Remote) {
            onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.INCOMING_CALL.getCallNotificationStatus()));
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onSignalingDTMFResponse(Call call, int i, String str) {
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onStateChanged(DeviceCallingService deviceCallingService, DeviceCallingService.State state) {
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public void removeObserver(CallNotificationStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        CommsLogger commsLogger = LOG;
        commsLogger.i("Removing an observer: " + observer);
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        LOG.i("Deregistering the Alexa call notification monitor as all observers have been removed.");
        this.mDeviceCallingService.unregisterListener(this);
    }

    public void updateMonitorNotification() {
        onCallNotificationStatusChanged(getConsolidatedCallNotificationStatus(this.mTelephonyManager, this.mCallManager, this.context));
    }

    @Override // com.amazon.comms.calling.service.DeviceCallingServiceListener
    public void onError(String str, ErrorCode errorCode, int i, String str2) {
        onCallNotificationStatusChanged(getCallNotificationStatusForGivenAlexaCallStatus(CallNotificationStatus.NO_ACTIVITY.getCallNotificationStatus()));
    }
}
