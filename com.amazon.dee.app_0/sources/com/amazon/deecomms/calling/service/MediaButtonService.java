package com.amazon.deecomms.calling.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.session.MediaButtonReceiver;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.List;
/* loaded from: classes12.dex */
public class MediaButtonService extends MediaBrowserServiceCompat {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaButtonService.class);
    private static final float PLAYBACK_SPEED = 1.0f;
    private static final int POSITION = 0;
    private MediaSessionCompat mSession;

    private void initMediaSessions() {
        this.mSession = new MediaSessionCompat(getApplicationContext(), MediaButtonService.class.getSimpleName());
        this.mSession.setFlags(3);
        setSessionToken(this.mSession.getSessionToken());
        this.mSession.setCallback(new MediaSessionCompat.Callback() { // from class: com.amazon.deecomms.calling.service.MediaButtonService.1
            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPause() {
                MediaButtonService.this.handleMediaButtonClick();
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPlay() {
                MediaButtonService.this.handleMediaButtonClick();
            }
        });
        PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
        builder.setActions(6L);
        builder.setState(3, 0L, 1.0f).build();
        this.mSession.setPlaybackState(builder.build());
        this.mSession.setActive(true);
    }

    @VisibleForTesting
    public void handleMediaButtonClick() {
        LOG.i("Handling media button click");
        SipClientState.CallState callState = CommsDaggerWrapper.getComponent().getCurrentCallSipClientState().getCallState();
        if (callState == SipClientState.CallState.ACTIVE) {
            CallUtils.endActiveCall(getApplicationContext());
        } else if (callState == SipClientState.CallState.OUTBOUND_RINGING) {
            CallUtils.cancelOutgoingCall(getApplicationContext());
        } else if (callState == SipClientState.CallState.INBOUND_RINGING) {
            boolean isVideoOrDropInVideoCall = CallUtils.isVideoOrDropInVideoCall();
            boolean z = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.RECORD_AUDIO") == 0;
            if (isVideoOrDropInVideoCall) {
                z = z && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CAMERA") == 0;
            }
            if (!z) {
                Intent intent = new Intent(Constants.CALL_PERMISSION_REQUEST_ACTION);
                intent.putExtra("IS_VIDEO_CALL", isVideoOrDropInVideoCall);
                intent.putExtra(Constants.PERMISSION_REQUEST_SCREEN_NAME, isVideoOrDropInVideoCall ? MetricKeys.SCREEN_NAME_INCOMING_VIDEO_CALL : MetricKeys.SCREEN_NAME_INCOMING_CALL);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                return;
            }
            CallUtils.acceptIncomingCall(getApplicationContext(), isVideoOrDropInVideoCall);
        } else if (!CommsDaggerWrapper.getComponent().getAudioPlayer().isPlaying()) {
        } else {
            CommsDaggerWrapper.getComponent().getAudioPlayer().stopPlaying();
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() {
        super.onCreate();
        initMediaSessions();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mSession.setActive(false);
        this.mSession.release();
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    @Nullable
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(@NonNull String str, int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(@NonNull String str, @NonNull MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            MediaButtonReceiver.handleIntent(this.mSession, intent);
            return 1;
        }
        return 1;
    }
}
