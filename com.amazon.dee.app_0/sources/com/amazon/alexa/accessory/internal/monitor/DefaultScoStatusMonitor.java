package com.amazon.alexa.accessory.internal.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultScoStatusMonitor implements ScoStatusMonitor {
    private static final IntentFilter INTENT_FILTER = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
    private final BluetoothScoReceiver bluetoothScoReceiver;
    private final Context context;
    private final Set<ScoStatusMonitor.Observer> observers;
    private boolean isScoConnected = false;
    private boolean isCacheCurrent = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BluetoothScoReceiver extends BroadcastReceiver {
        BluetoothScoReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
            DefaultScoStatusMonitor.this.isScoConnected = intExtra == 1;
            for (ScoStatusMonitor.Observer observer : new HashSet(DefaultScoStatusMonitor.this.observers)) {
                observer.onScoConnectionState(DefaultScoStatusMonitor.this.isScoConnected);
            }
            DefaultScoStatusMonitor.this.isCacheCurrent = true;
        }
    }

    public DefaultScoStatusMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.bluetoothScoReceiver = new BluetoothScoReceiver();
    }

    private void startObservingSco() {
        this.context.registerReceiver(this.bluetoothScoReceiver, INTENT_FILTER);
    }

    private void stopObservingSco() {
        this.context.unregisterReceiver(this.bluetoothScoReceiver);
        this.isCacheCurrent = false;
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor
    public void addObserver(ScoStatusMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            startObservingSco();
        } else if (this.isCacheCurrent) {
            observer.onScoConnectionState(this.isScoConnected);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.ScoStatusMonitor
    public void removeObserver(ScoStatusMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        stopObservingSco();
    }
}
