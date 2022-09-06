package com.amazon.alexa.accessory.internal.monitor;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultA2dpPlayingMonitor implements A2dpPlayingMonitor {
    private static final IntentFilter INTENT_FILTER = new IntentFilter("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
    private final BluetoothA2dpReceiver bluetoothA2dpReceiver;
    private final BluetoothAdapter bluetoothAdapter;
    private final Context context;
    private boolean isA2dpPlaying = false;
    private boolean isCacheCurrent = false;
    private final Set<A2dpPlayingMonitor.Observer> observers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BluetoothA2dpReceiver extends BroadcastReceiver {
        BluetoothA2dpReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 11);
            DefaultA2dpPlayingMonitor.this.isA2dpPlaying = intExtra == 10;
            DefaultA2dpPlayingMonitor.this.notifyObservers();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ServiceListener implements BluetoothProfile.ServiceListener {
        private BluetoothA2dp bluetoothProfileProxy;

        ServiceListener() {
        }

        @SuppressLint({"MissingPermission"})
        private boolean isA2dpPlaying(BluetoothA2dp bluetoothA2dp) {
            BluetoothA2dp bluetoothA2dp2 = this.bluetoothProfileProxy;
            if (bluetoothA2dp2 != null) {
                for (BluetoothDevice bluetoothDevice : bluetoothA2dp2.getConnectedDevices()) {
                    if (this.bluetoothProfileProxy.isA2dpPlaying(bluetoothDevice)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            this.bluetoothProfileProxy = (BluetoothA2dp) bluetoothProfile;
            DefaultA2dpPlayingMonitor.this.isA2dpPlaying = isA2dpPlaying(this.bluetoothProfileProxy);
            DefaultA2dpPlayingMonitor.this.bluetoothAdapter.closeProfileProxy(2, this.bluetoothProfileProxy);
            DefaultA2dpPlayingMonitor.this.notifyObservers();
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
        }
    }

    public DefaultA2dpPlayingMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.bluetoothAdapter = BluetoothUtils.getBluetoothAdapter(context);
        this.observers = new HashSet();
        this.bluetoothA2dpReceiver = new BluetoothA2dpReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObservers() {
        this.isCacheCurrent = true;
        for (A2dpPlayingMonitor.Observer observer : new HashSet(this.observers)) {
            observer.onA2dpPlayingState(this.isA2dpPlaying);
        }
    }

    private void startObservingA2dpPlaying() {
        this.context.registerReceiver(this.bluetoothA2dpReceiver, INTENT_FILTER);
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(this.context, new ServiceListener(), 2);
        }
    }

    private void stopObservingA2dpPlaying() {
        this.context.unregisterReceiver(this.bluetoothA2dpReceiver);
        this.isCacheCurrent = false;
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor
    public void addObserver(A2dpPlayingMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            startObservingA2dpPlaying();
        } else if (this.isCacheCurrent) {
            observer.onA2dpPlayingState(this.isA2dpPlaying);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.A2dpPlayingMonitor
    public void removeObserver(A2dpPlayingMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        stopObservingA2dpPlaying();
    }
}
