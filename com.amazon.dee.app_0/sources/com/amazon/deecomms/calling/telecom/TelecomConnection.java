package com.amazon.deecomms.calling.telecom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.DisconnectCause;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomAudioUtils;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
@TargetApi(26)
/* loaded from: classes12.dex */
public class TelecomConnection extends Connection {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomConnection.class);
    @Inject
    public AudioManager audioManager;
    @Inject
    public Context context;
    private TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;

    public TelecomConnection(@NonNull boolean z, @NonNull ConnectionRequest connectionRequest, @NonNull TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
        setAudioModeIsVoip(true);
        if (z) {
            boolean z2 = connectionRequest.getExtras().getBundle("android.telecom.extra.INCOMING_CALL_EXTRAS").getBoolean("IS_VIDEO_CALL", false);
            Bundle bundle = new Bundle();
            bundle.putBoolean("IS_VIDEO_CALL", z2);
            setExtras(bundle);
        }
        setConnectionCapabilities(getConnectionCapabilities() | 524288 | 64);
        setConnectionProperties(getConnectionProperties() | 128);
    }

    @VisibleForTesting
    void acceptIncomingCall(@NonNull Bundle bundle) {
        String[] permissionListForAudio;
        boolean z = bundle.getBoolean("IS_VIDEO_CALL");
        if (z) {
            permissionListForAudio = PermissionsHelper.getPermissionListForVideoCalling();
        } else {
            permissionListForAudio = PermissionsHelper.getPermissionListForAudio();
        }
        if (!(PermissionsHelper.checkPermissions(this.context, permissionListForAudio).length == 0)) {
            LOG.i("No permissions to pick up call, requesting.");
            Intent intent = new Intent(Constants.CALL_PERMISSION_REQUEST_ACTION);
            intent.putExtra("IS_VIDEO_CALL", z);
            intent.putExtra(Constants.PERMISSION_REQUEST_SCREEN_NAME, z ? MetricKeys.SCREEN_NAME_INCOMING_VIDEO_CALL : MetricKeys.SCREEN_NAME_INCOMING_CALL);
            LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
            return;
        }
        LOG.i("Accepting incoming call.");
        CallUtils.acceptIncomingCall(this.context, z);
    }

    @Override // android.telecom.Connection
    public void onAbort() {
        LOG.i("onAbort() called");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.MULTI_ACTION_BUTTON_ABORT);
        destroy();
    }

    @Override // android.telecom.Connection
    public void onAnswer() {
        LOG.i("Multi-action button clicked: onAnswer() called.");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.MULTI_ACTION_BUTTON_ANSWER);
        acceptIncomingCall(getExtras());
    }

    @Override // android.telecom.Connection
    public void onCallAudioStateChanged(@Nullable CallAudioState callAudioState) {
        if (callAudioState != null) {
            String audioRouteToString = CallAudioState.audioRouteToString(callAudioState.getRoute());
            GeneratedOutlineSupport.outline4("onCallAudioStateChanged state is: ", audioRouteToString, LOG);
            TelecomAudioUtils.verifyRouteSelected(this.audioManager, audioRouteToString);
            this.telecomCallAudioRouteObservable.onCallAudioStateUpdated(callAudioState);
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.CONNECTION_AUDIO_CHANGE, audioRouteToString);
        }
    }

    @Override // android.telecom.Connection
    public void onDisconnect() {
        int state = getState();
        setDisconnected(new DisconnectCause(2));
        destroy();
        LOG.i("Bluetooth multi-action button clicked: onDisconnect() or PSTN/another 3rd party Telecom call started");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.MULTI_ACTION_BUTTON_DISCONNECT_OR_CONCURRENT_CALL);
        onDisconnectHelper(state);
    }

    @VisibleForTesting
    void onDisconnectHelper(int i) {
        if (i == 4) {
            LOG.i("Ending active call.");
            CallUtils.endActiveCall(this.context);
        } else if (i == 3) {
            LOG.i("Cancelling outgoing call");
            CallUtils.cancelOutgoingCall(this.context);
        } else if (i != 2) {
        } else {
            LOG.i("Rejecting incoming call");
            CallUtils.rejectCall(this.context);
        }
    }

    @Override // android.telecom.Connection
    public void onHold() {
        LOG.i("onHold() called");
    }

    @Override // android.telecom.Connection
    public void onReject() {
        setDisconnected(new DisconnectCause(2));
        destroy();
        LOG.i("onReject() called");
        onRejectHelper();
    }

    @VisibleForTesting
    void onRejectHelper() {
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.REJECTING_ALEXA_AFTER_INCOMING_CONCURRENT_CALLS);
        CallUtils.rejectCall(this.context);
    }

    @Override // android.telecom.Connection
    public void onShowIncomingCallUi() {
        LOG.i("onShowIncomingCallUi() called");
    }

    @NonNull
    public String toFailedConnection() {
        int state = getState();
        DisconnectCause disconnectCause = getDisconnectCause();
        if (disconnectCause != null) {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("FailedConnectionInfo - Reason: ");
            outline1.append(disconnectCause.getReason());
            outline1.append(", description: ");
            outline1.append((Object) disconnectCause.getDescription());
            outline1.append(", label: ");
            outline1.append((Object) disconnectCause.getLabel());
            outline1.append(", state ");
            outline1.append(state);
            return outline1.toString();
        }
        return GeneratedOutlineSupport1.outline49("FailedConnectionInfo - State: ", state);
    }

    @VisibleForTesting
    public TelecomConnection(@NonNull Context context, @NonNull TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        this.context = context;
        this.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }
}
