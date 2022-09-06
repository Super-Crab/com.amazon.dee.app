package com.amazon.deecomms.smsmessaging.service.sendsms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.smsmessaging.database.SMSMessageContract;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import java.util.ArrayList;
import java.util.UUID;
/* loaded from: classes12.dex */
public class SMSSendManager {
    private static final String ERROR_GENERIC_ERROR = "Error - Generic failure";
    private static final String ERROR_NO_SERVICE = "Error - No Service";
    private static final String ERROR_NULL_PDU = "Error - Null PDU";
    private static final String ERROR_RADIO_OFF = "Error - Radio off";
    private static final String ERROR_UNKNOWN = "Error - Unknown";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SMSSendManager.class);
    private Context mContext;
    private MessagingControllerManager mcManager;
    private String sendMessageRequestId;
    private boolean failed = false;
    private int messageSendPieceNumber = 1;
    private int messageTotalPieces = 1;

    public SMSSendManager(Context context, MessagingControllerManager messagingControllerManager, String str) {
        this.mContext = context;
        this.sendMessageRequestId = str;
        this.mcManager = messagingControllerManager;
    }

    static /* synthetic */ int access$608(SMSSendManager sMSSendManager) {
        int i = sMSSendManager.messageSendPieceNumber;
        sMSSendManager.messageSendPieceNumber = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getResultCodeDetails(int i) {
        return i != -1 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? ERROR_UNKNOWN : ERROR_NO_SERVICE : ERROR_NULL_PDU : ERROR_RADIO_OFF : ERROR_GENERIC_ERROR : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStatusReasonFromResultCode(int i) {
        return (i == 4 || i == 2) ? MessagingControllerConstant.SendMessageStatusReason.NO_CONNECTIVITY.toString() : MessagingControllerConstant.SendMessageStatusReason.GENERIC_FAILURE.toString();
    }

    private void sendLongSMSMessage(@NonNull String str, @NonNull String str2) {
        LOG.i("Sending long SMS");
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> divideMessage = smsManager.divideMessage(str2);
        this.messageTotalPieces = divideMessage.size();
        ArrayList<PendingIntent> arrayList = new ArrayList<>();
        ArrayList<PendingIntent> arrayList2 = new ArrayList<>();
        this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.amazon.deecomms.smsmessaging.service.sendsms.SMSSendManager.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, @NonNull Intent intent) {
                SMSSendManager.LOG.i("Long SMS SendIntent onReceive");
                if (SMSSendManager.this.failed) {
                    return;
                }
                int resultCode = getResultCode();
                String statusReasonFromResultCode = SMSSendManager.this.getStatusReasonFromResultCode(resultCode);
                String resultCodeDetails = SMSSendManager.this.getResultCodeDetails(resultCode);
                if (resultCode != -1) {
                    SMSSendManager.this.failed = true;
                    SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.DEVICE_FAILURE.toString(), statusReasonFromResultCode, resultCodeDetails);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_LONG_SEND_FAIL);
                } else if (SMSSendManager.this.messageSendPieceNumber == SMSSendManager.this.messageTotalPieces) {
                    SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.SUCCESS.toString(), MessagingControllerConstant.SendMessageStatusReason.NONE.toString(), resultCodeDetails);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_LONG_SEND_SUCCESS);
                }
                SMSSendManager.access$608(SMSSendManager.this);
            }
        }, new IntentFilter(uuid));
        this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.amazon.deecomms.smsmessaging.service.sendsms.SMSSendManager.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, @NonNull Intent intent) {
                SMSSendManager.LOG.i("Long SMS DeliverIntent onReceive");
                if (SMSSendManager.this.failed) {
                    return;
                }
                int resultCode = getResultCode();
                String statusReasonFromResultCode = SMSSendManager.this.getStatusReasonFromResultCode(resultCode);
                String resultCodeDetails = SMSSendManager.this.getResultCodeDetails(resultCode);
                if (resultCode == -1) {
                    return;
                }
                SMSSendManager.this.failed = true;
                SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.DEVICE_FAILURE.toString(), statusReasonFromResultCode, resultCodeDetails);
                MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_LONG_DELIVER_FAIL);
            }
        }, new IntentFilter(uuid2));
        for (int i = 0; i < this.messageTotalPieces; i++) {
            arrayList.add(PendingIntent.getBroadcast(this.mContext, 0, new Intent(uuid), 0));
            arrayList2.add(PendingIntent.getBroadcast(this.mContext, 0, new Intent(uuid2), 0));
        }
        smsManager.sendMultipartTextMessage(str, null, divideMessage, arrayList, arrayList2);
        LOG.i("Long SMS sent");
    }

    private void sendShortSMSMessage(@NonNull String str, @NonNull String str2) {
        LOG.i("Sending short SMS");
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.amazon.deecomms.smsmessaging.service.sendsms.SMSSendManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, @NonNull Intent intent) {
                SMSSendManager.LOG.i("Short SMS SendIntent onReceive");
                int resultCode = getResultCode();
                String statusReasonFromResultCode = SMSSendManager.this.getStatusReasonFromResultCode(resultCode);
                String resultCodeDetails = SMSSendManager.this.getResultCodeDetails(resultCode);
                if (resultCode != -1) {
                    SMSSendManager.this.failed = true;
                    SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.DEVICE_FAILURE.toString(), statusReasonFromResultCode, resultCodeDetails);
                    MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_SHORT_SEND_FAIL);
                    return;
                }
                SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.SUCCESS.toString(), MessagingControllerConstant.SendMessageStatusReason.NONE.toString(), resultCodeDetails);
                MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_SHORT_SEND_SUCCESS);
            }
        }, new IntentFilter(uuid));
        this.mContext.registerReceiver(new BroadcastReceiver() { // from class: com.amazon.deecomms.smsmessaging.service.sendsms.SMSSendManager.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, @NonNull Intent intent) {
                SMSSendManager.LOG.i("Short SMS DeliverIntent onReceive");
                if (SMSSendManager.this.failed) {
                    return;
                }
                int resultCode = getResultCode();
                String statusReasonFromResultCode = SMSSendManager.this.getStatusReasonFromResultCode(resultCode);
                String resultCodeDetails = SMSSendManager.this.getResultCodeDetails(resultCode);
                if (resultCode == -1) {
                    return;
                }
                SMSSendManager.this.failed = true;
                SMSSendManager.this.mcManager.sendUpdateSendMessageStatusEvent(SMSSendManager.this.sendMessageRequestId, MessagingControllerConstant.SendMessageStatus.DEVICE_FAILURE.toString(), statusReasonFromResultCode, resultCodeDetails);
                MetricsHelper.recordSingleOccurrenceOperational(SMSMessageContract.MC_SHORT_DELIVER_FAIL);
            }
        }, new IntentFilter(uuid2));
        SmsManager.getDefault().sendTextMessage(str, null, str2, PendingIntent.getBroadcast(this.mContext, 0, new Intent(uuid), 0), PendingIntent.getBroadcast(this.mContext, 0, new Intent(uuid2), 0));
        LOG.i("Short SMS sent.");
    }

    public void sendSMSMessage(@NonNull String str, @NonNull String str2) {
        if (str2.length() > 160) {
            sendLongSMSMessage(str, str2);
        } else {
            sendShortSMSMessage(str, str2);
        }
    }
}
