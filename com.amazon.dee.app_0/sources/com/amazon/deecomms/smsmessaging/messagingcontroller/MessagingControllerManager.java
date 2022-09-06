package com.amazon.deecomms.smsmessaging.messagingcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.smsmessaging.database.SMSDatabaseManager;
import com.amazon.deecomms.smsmessaging.database.SMSDatabaseUtils;
import com.amazon.deecomms.smsmessaging.database.SMSMessageContract;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReadManager;
import com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReceiveManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Set;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class MessagingControllerManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingControllerManager.class);
    private AlexaServiceConnectionReceiver alexaServiceConnectionReceiver;
    private AlexaServicesConnection mAlexaServicesConnection;
    private CapabilitiesManager mCapabilitiesManager;
    @Inject
    Context mContext;
    private MessagingControllerContextProvider mMessagingControllerContextProvider;
    private SMSReceiveManager mSMSReceiveManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class AlexaServiceConnectionReceiver extends BroadcastReceiver {
        private AlexaServiceConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MessagingControllerManager.LOG.i("Received intent in MCC ASC Receiver");
            if (Constants.ALEXA_SERVICE_CONNECTION_CONNECTED.equals(intent.getAction())) {
                MessagingControllerManager.LOG.i("Alexa Service connection for MCC connected");
                MessagingControllerManager.this.mMessagingControllerContextProvider.updateContext(null);
                AlexaServices.ContextProvider.register(MessagingControllerManager.this.mAlexaServicesConnection, CommsDaggerWrapper.getComponent().getMessagingControllerContextProvider());
                MessagingControllerManager.LOG.i("Muffin & AlexaService are both connected. Start SMS listener and do FullSync");
                MessagingControllerManager.this.newAndStartSMSReceiveManager();
            } else if (Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED.equals(intent.getAction())) {
                MessagingControllerManager.LOG.i("Alexa Service connection for MCC dis-connected");
                MessagingControllerManager.this.mMessagingControllerContextProvider.updateContext(null);
                MessagingControllerManager.this.stopSMSReceiveManager();
            } else if (!Constants.ACCESSORY_UPDATED.equals(intent.getAction())) {
            } else {
                MessagingControllerManager.LOG.i("Echo Auto status updated..updating MCC Context Provider");
                MessagingControllerManager.this.mMessagingControllerContextProvider.updateContext(null);
            }
        }

        /* synthetic */ AlexaServiceConnectionReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    public MessagingControllerManager(@NonNull CapabilitiesManager capabilitiesManager, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull MessagingControllerContextProvider messagingControllerContextProvider) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mCapabilitiesManager = capabilitiesManager;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mMessagingControllerContextProvider = messagingControllerContextProvider;
        this.mSMSReceiveManager = new SMSReceiveManager(this.mContext, this);
        this.alexaServiceConnectionReceiver = new AlexaServiceConnectionReceiver(null);
    }

    private void sendEvent(@NonNull String str, @NonNull String str2) {
        MessagingControllerEventModel messagingControllerEventModel = new MessagingControllerEventModel(MessagingControllerEventModel.createHeader(str), MessagingControllerEventModel.createPayload(str2));
        LOG.i("In sendEvent, only send the event when MC & AS are both connected.");
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("mAlexaServicesConnection.isConnected() = ");
        outline1.append(this.mAlexaServicesConnection.isConnected());
        commsLogger.i(outline1.toString());
        if (this.mAlexaServicesConnection.isConnected()) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Sending Messaging Controller Event: ");
            outline12.append(messagingControllerEventModel.getAlexaHeader().getName());
            commsLogger2.i(outline12.toString());
            AlexaServices.EventSender.send(this.mAlexaServicesConnection, (AlexaEvent) messagingControllerEventModel, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSMSReceiveManager() {
        SMSReceiveManager sMSReceiveManager = this.mSMSReceiveManager;
        if (sMSReceiveManager != null) {
            sMSReceiveManager.stopMessageMonitoring();
        }
    }

    public boolean checkPermissions() {
        boolean z;
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_SMS") != 0) {
            LOG.i("Missing READ_SMS permission.");
            z = false;
        } else {
            z = true;
        }
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.RECEIVE_SMS") != 0) {
            LOG.i("Missing RECEIVE_SMS permission.");
            z = false;
        }
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.RECEIVE_MMS") != 0) {
            LOG.i("Missing RECEIVE_MMS permission.");
            z = false;
        }
        int i = Build.VERSION.SDK_INT;
        if ((i == 26 || i == 27) && ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") != 0) {
            LOG.i("Missing READ_PHONE_STATE permission.");
            z = false;
        }
        if (!z) {
            MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_MISS_PERMISSION);
        }
        return z;
    }

    protected void finalize() throws Throwable {
        stopSMSReceiveManager();
        super.finalize();
    }

    public void fullSync(@NonNull Context context, int i) {
        LOG.i("Start fullSync.");
        Set<String> allMessageIds = new SMSDatabaseUtils(SMSDatabaseManager.getInstance(context)).getAllMessageIds(SMSMessageContract.READ_TABLE_NAME);
        SMSReadManager sMSReadManager = new SMSReadManager(context);
        sMSReadManager.setReadMessageIdSet(allMessageIds);
        sendEvent(MessagingControllerConstant.MessagingControllerEvents.UploadConversations.toString(), sMSReadManager.getFullSyncPayload(sMSReadManager.getUnreadSMSMMSMessages(i)).toString());
    }

    public void initializeMessagingController() {
        if (!CommsDaggerWrapper.getComponent().getTelephonyManager().isSmsCapable()) {
            LOG.i("Device does not have SMS Capability...skipping MC initialization");
            return;
        }
        LOG.i("Initializing messagingController component");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ALEXA_SERVICE_CONNECTION_CONNECTED);
        intentFilter.addAction(Constants.ALEXA_SERVICE_CONNECTION_DISCONNECTED);
        intentFilter.addAction(Constants.ACCESSORY_UPDATED);
        LOG.i("Registering receiver for ASC MCC");
        LocalBroadcastManager.getInstance(CommsDaggerWrapper.getComponent().getContext()).registerReceiver(this.alexaServiceConnectionReceiver, intentFilter);
        this.mMessagingControllerContextProvider.updateContext(new MessagingControllerContextProvider.ContextUpdated() { // from class: com.amazon.deecomms.smsmessaging.messagingcontroller.-$$Lambda$MessagingControllerManager$tiQfN8_nRTC4hSp4znvkt2cKNoI
            @Override // com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider.ContextUpdated
            public final void onContextUpdated() {
                MessagingControllerManager.this.lambda$initializeMessagingController$0$MessagingControllerManager();
            }
        });
    }

    public /* synthetic */ void lambda$initializeMessagingController$0$MessagingControllerManager() {
        LOG.i("Trying to start SMS Monitor in initializeMessagingController.");
        newAndStartSMSReceiveManager();
    }

    public void newAndStartSMSReceiveManager() {
        if (CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener().isAnyAccessoryConnected() && checkPermissions()) {
            this.mSMSReceiveManager.startMessageMonitoring();
            fullSync(this.mContext, 40);
            MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_UPLOAD_CONNECT);
            return;
        }
        LOG.i("Accessory is not connected or no SMS permission.Not starting SMS monitor.No FullSync.");
    }

    public void sendUpdateSendMessageStatusEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_SEND_MESSAGE_REQUEST_ID, str);
            jSONObject.put("status", str2);
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_STATUS_REASON_KEY, str3);
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY, str4);
            sendEvent(MessagingControllerConstant.MessagingControllerEvents.UpdateSendMessageStatus.toString(), jSONObject.toString());
        } catch (JSONException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("sendUpdateSendMessageStatusEvent error, " + e);
        }
    }
}
