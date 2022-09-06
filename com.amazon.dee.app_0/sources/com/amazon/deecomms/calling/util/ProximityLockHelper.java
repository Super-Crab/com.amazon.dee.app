package com.amazon.deecomms.calling.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Looper;
import android.os.PowerManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class ProximityLockHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ProximityLockHelper.class);
    private static final String PROXIMITY_SENSOR_WAKE_LOCK_TAG = "AlexaComms:CommsProximitySensorWakeLock";
    private static ProximityLockHelper sInstance;
    private final AudioManager audioManager;
    private final Context context;
    private boolean isCallInProgress;
    private PowerManager.WakeLock proximitySensorWakeLock;
    private final BroadcastReceiver speakerStateReceiver;

    /* loaded from: classes12.dex */
    private class SpeakerStateReceiver extends BroadcastReceiver {
        private SpeakerStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!ProximityLockHelper.this.isCallInProgress) {
                ProximityLockHelper.LOG.e("Received speaker state notification when there is no ongoing call");
            } else if (ProximityLockHelper.this.audioManager.isSpeakerphoneOn()) {
                ProximityLockHelper.this.releaseLockInternal();
            } else {
                ProximityLockHelper.this.acquireLockInternal();
            }
        }

        /* synthetic */ SpeakerStateReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    private ProximityLockHelper(Context context) {
        Preconditions.checkState(Looper.getMainLooper() == Looper.myLooper());
        this.context = context;
        this.speakerStateReceiver = new SpeakerStateReceiver(null);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.speakerStateReceiver, new IntentFilter(CallUtils.INTENT_ACTION_SPEAKER));
        this.audioManager = (AudioManager) context.getSystemService("audio");
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (!powerManager.isWakeLockLevelSupported(32)) {
            LOG.w("Proximity lock not supported for the current API level");
        } else {
            this.proximitySensorWakeLock = powerManager.newWakeLock(32, PROXIMITY_SENSOR_WAKE_LOCK_TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void acquireLockInternal() {
        PowerManager.WakeLock wakeLock = this.proximitySensorWakeLock;
        if (wakeLock != null && !wakeLock.isHeld() && !this.audioManager.isSpeakerphoneOn()) {
            LOG.i("Proximity lock acquired");
            this.proximitySensorWakeLock.acquire();
        }
    }

    public static synchronized ProximityLockHelper getInstance(Context context) {
        ProximityLockHelper proximityLockHelper;
        synchronized (ProximityLockHelper.class) {
            if (sInstance == null) {
                sInstance = new ProximityLockHelper(context);
            }
            proximityLockHelper = sInstance;
        }
        return proximityLockHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void releaseLockInternal() {
        PowerManager.WakeLock wakeLock = this.proximitySensorWakeLock;
        if (wakeLock != null && wakeLock.isHeld()) {
            LOG.i("Proximity lock released");
            this.proximitySensorWakeLock.release();
        }
    }

    public void cleanup() {
        Preconditions.checkState(Looper.getMainLooper() == Looper.myLooper());
        releaseLockInternal();
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.speakerStateReceiver);
    }

    public void markCallEnded() {
        Preconditions.checkState(Looper.getMainLooper() == Looper.myLooper());
        this.isCallInProgress = false;
        releaseLockInternal();
    }

    public void markCallStarted() {
        Preconditions.checkState(Looper.getMainLooper() == Looper.myLooper());
        this.isCallInProgress = true;
        acquireLockInternal();
    }
}
