package com.amazon.deecomms.calling.telecom;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.Queue;
@TargetApi(26)
/* loaded from: classes12.dex */
public class TelecomBridge {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomBridge.class);
    private final Map<String, Connection> callIdByTelecomConnection;
    private final PhoneAccountHandle phoneAccountHandle;
    private final Queue<String> telecomCallIds;
    private final Object telecomLock;
    private final TelecomManager telecomManager;

    public TelecomBridge(@NonNull TelecomManager telecomManager, @NonNull PhoneAccountHandle phoneAccountHandle, @NonNull Map<String, Connection> map, @NonNull Queue<String> queue, @NonNull Object obj) {
        this.telecomManager = telecomManager;
        this.phoneAccountHandle = phoneAccountHandle;
        this.callIdByTelecomConnection = map;
        this.telecomCallIds = queue;
        this.telecomLock = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getSipUri(@NonNull String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "@");
        outline113.append(Utils.getStringFromResource(R.string.alexa_app_name));
        String sb = outline113.toString();
        GeneratedOutlineSupport.outline4("User info ", sb, LOG);
        return Uri.fromParts("sip", sb, "");
    }

    public void activateTelecomConnection(@NonNull String str) {
        synchronized (this.telecomLock) {
            if (!isValidCallId(str)) {
                LOG.i("Callid empty for activateTelecomConnection. Call won't be managed with Telecom.");
                return;
            }
            LOG.i("activateTelecomConnection()");
            if (this.callIdByTelecomConnection.containsKey(str)) {
                LOG.i("Setting telecom connection to active.");
                this.callIdByTelecomConnection.get(str).setActive();
                MetricsHelper.recordCounterMetricOperational(MetricKeys.ACTIVATE_CONNECTION, 1.0d);
            } else {
                LOG.i("Accepted call that is not in telecom yet. Telecom Service was too slow. Won't be able to manage this call with telecom i.e. hang up the call.");
                this.telecomCallIds.remove(str);
                MetricsHelper.recordCounterMetricOperational(MetricKeys.ACTIVATE_CONNECTION, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
            }
        }
    }

    public void addTelecomCall(@NonNull Call call, boolean z) {
        synchronized (this.telecomLock) {
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("AddTelecomCall called. IsIncoming call: ");
            sb.append(z);
            commsLogger.i(sb.toString());
            String callId = call.getCallId();
            if (!isValidCallId(callId)) {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.TELECOM_EMPTY_CALLID);
                LOG.i("Callid empty for addTelecomCall. Call won't be managed with Telecom.");
                return;
            }
            String providerSpecifiedId = call.getRemoteParticipant().getProviderSpecifiedId();
            if (providerSpecifiedId != null && !providerSpecifiedId.isEmpty()) {
                Bundle bundle = new Bundle();
                if (z) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("IS_VIDEO_CALL", call.isVideoRequested());
                    bundle.putParcelable("android.telecom.extra.INCOMING_CALL_EXTRAS", bundle2);
                    createCallTask(providerSpecifiedId, callId, bundle, true).execute(new Void[0]);
                } else {
                    createCallTask(providerSpecifiedId, callId, bundle, false).execute(new Void[0]);
                }
            } else {
                LOG.i("commsid null or empty");
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.TELECOM_EMPTY_COMMS_ID);
            }
        }
    }

    @VisibleForTesting
    AsyncTask<Void, Void, Void> createCallTask(@NonNull final String str, @NonNull final String str2, @NonNull final Bundle bundle, @NonNull final boolean z) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.calling.telecom.TelecomBridge.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                String callerNameForCommsId = CallUtils.getCallerNameForCommsId(str, z ? TelecomConstants.INCOMING_CALL_REASON : TelecomConstants.OUTGOING_CALL_REASON);
                if (callerNameForCommsId == null || callerNameForCommsId.isEmpty()) {
                    TelecomBridge.LOG.e("Caller name empty");
                    MetricsHelper.recordCounterMetricOperational(MetricKeys.TELECOM_EMPTY_CALLER_NAME, 1.0d);
                    return null;
                }
                synchronized (TelecomBridge.this.telecomLock) {
                    TelecomBridge.this.telecomCallIds.add(str2);
                    Uri sipUri = TelecomBridge.this.getSipUri(callerNameForCommsId);
                    if (z) {
                        TelecomBridge.LOG.i("addTelecomIncomingCall()");
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.ADD_NEW_INCOMING_CALL);
                        bundle.putParcelable("android.telecom.extra.INCOMING_CALL_ADDRESS", sipUri);
                        TelecomBridge.this.telecomManager.addNewIncomingCall(TelecomBridge.this.phoneAccountHandle, bundle);
                    } else {
                        TelecomBridge.LOG.i("addTelecomOutgoingCall()");
                        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.PLACE_CALL);
                        bundle.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", TelecomBridge.this.phoneAccountHandle);
                        try {
                            TelecomBridge.this.telecomManager.placeCall(sipUri, bundle);
                        } catch (SecurityException e) {
                            TelecomBridge.LOG.e("Telecom framework requested permission for self-managed connection, even though it should not. ", e);
                            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.PLACE_CALL_PERMISSION_CRASH);
                        }
                    }
                }
                return null;
            }
        };
    }

    public void destroyTelecomConnection(@NonNull String str, @NonNull HangupReason hangupReason) {
        synchronized (this.telecomLock) {
            if (!isValidCallId(str)) {
                LOG.i("Callid empty for destroyTelecomConnection. Call won't be managed with Telecom.");
                return;
            }
            LOG.i("destroyTelecomConnection()");
            if (this.telecomCallIds.remove(str) && this.callIdByTelecomConnection.containsKey(str)) {
                Connection remove = this.callIdByTelecomConnection.remove(str);
                if (remove.getState() != 6) {
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Destroying Telecom Connection. Reason: ");
                    sb.append(hangupReason);
                    sb.append(", state ");
                    sb.append(remove.getState());
                    commsLogger.i(sb.toString());
                    remove.setDisconnected(new DisconnectCause(hangupReason == HangupReason.LocalHangup ? 2 : 3));
                    remove.destroy();
                } else {
                    LOG.i("Telecom Connection already destroyed by another call: PSTN on 3rd party, or multi-action button");
                }
            } else {
                LOG.i("No need to destroy connection, it wasn't created yet.");
            }
        }
    }

    public Connection getCurrentConnection() {
        synchronized (this.telecomLock) {
            String peek = this.telecomCallIds.peek();
            if (peek == null) {
                return null;
            }
            return this.callIdByTelecomConnection.get(peek);
        }
    }

    @VisibleForTesting
    public boolean isValidCallId(String str) {
        return str != null && !str.isEmpty();
    }
}
