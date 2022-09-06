package com.amazon.alexa.accessory.internal.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.SparseIntArray;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultVolumeChangedMonitor implements VolumeChangedMonitor {
    @VisibleForTesting
    static final String MUTE_STATE_CHANGED_ACTION = "com.amazon.alexa.intent.action.MUTE_STATE_CHANGED_ACTION";
    private final MuteStateBroadcastReceiver broadcastReceiver;
    private final VolumeContentObserver contentObserver;
    private final Context context;
    private final IntentFilter intentFilter;
    private final Set<VolumeChangedMonitor.Observer> observers;

    /* loaded from: classes.dex */
    private static final class AudioManagerVolume {
        private final AudioManager audioManager;
        private final SparseIntArray volumeLevels;

        public AudioManagerVolume(Context context) {
            Preconditions.notNull(context, "context");
            this.volumeLevels = new SparseIntArray();
            this.audioManager = (AudioManager) context.getSystemService("audio");
            this.volumeLevels.put(3, this.audioManager.getStreamVolume(3));
            this.volumeLevels.put(0, this.audioManager.getStreamVolume(0));
            this.volumeLevels.put(1, this.audioManager.getStreamVolume(1));
            this.volumeLevels.put(2, this.audioManager.getStreamVolume(2));
            this.volumeLevels.put(5, this.audioManager.getStreamVolume(5));
        }

        public boolean hasVolumeChanged() {
            boolean z = false;
            for (int i = 0; i < this.volumeLevels.size(); i++) {
                int keyAt = this.volumeLevels.keyAt(i);
                int streamVolume = this.audioManager.getStreamVolume(keyAt);
                if (streamVolume != this.volumeLevels.get(keyAt)) {
                    z = true;
                }
                this.volumeLevels.put(keyAt, streamVolume);
            }
            return z;
        }
    }

    /* loaded from: classes.dex */
    private final class MuteStateBroadcastReceiver extends BroadcastReceiver {
        private MuteStateBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DefaultVolumeChangedMonitor.this.notifyVolumeChanged();
        }
    }

    /* loaded from: classes.dex */
    private final class VolumeContentObserver extends ContentObserver {
        private final AudioManagerVolume audioManagerVolume;

        public VolumeContentObserver(Context context) {
            super(new Handler(Looper.myLooper()));
            this.audioManagerVolume = new AudioManagerVolume(context);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (this.audioManagerVolume.hasVolumeChanged()) {
                DefaultVolumeChangedMonitor.this.notifyVolumeChanged();
            }
        }
    }

    public DefaultVolumeChangedMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.contentObserver = new VolumeContentObserver(context);
        this.broadcastReceiver = new MuteStateBroadcastReceiver();
        this.intentFilter = new IntentFilter(MUTE_STATE_CHANGED_ACTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyVolumeChanged() {
        for (VolumeChangedMonitor.Observer observer : new HashSet(this.observers)) {
            observer.onVolumeChanged();
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor
    public void addObserver(VolumeChangedMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.context.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.contentObserver);
            this.context.registerReceiver(this.broadcastReceiver, this.intentFilter);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.VolumeChangedMonitor
    public void removeObserver(VolumeChangedMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
        this.context.unregisterReceiver(this.broadcastReceiver);
    }
}
