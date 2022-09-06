package com.amazon.alexa.accessorykit.interprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessorykit.interprocess.DefaultCallNotificationStateMonitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes6.dex */
public class DefaultCallNotificationStateMonitor implements CallNotificationStateMonitor {
    @VisibleForTesting
    static final int ACTIVE_CALL_VALUE = 4;
    @VisibleForTesting
    static final int INCOMING_CALL_VALUE = 16;
    @VisibleForTesting
    static final int MISSED_CALLS_VALUE = 2;
    @VisibleForTesting
    static final int NO_ACTIVITY_VALUE = 1;
    @VisibleForTesting
    static final int OUTBOUND_CALL_VALUE = 8;
    private boolean active;
    private final CallNotificationStatusReceiver callNotificationStatusReceiver;
    private CallNotificationStatus knownStatus;
    private final Object lock = new Object();
    private final List<CallNotificationStateMonitor.Observer> observers = new ArrayList();

    /* loaded from: classes6.dex */
    public enum CallNotificationStatus {
        NO_ACTIVITY(1),
        MISSED_CALLS(2),
        ACTIVE_CALL(4),
        OUTBOUND_CALL(8),
        INCOMING_CALL(16);
        
        public final int intRepresentation;

        CallNotificationStatus(int i) {
            this.intRepresentation = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static CallNotificationStatus fromState(StateOuterClass.State state) {
            Preconditions.notNull(state, "state");
            int integer = state.getInteger();
            if (integer != 2) {
                if (integer == 4) {
                    return ACTIVE_CALL;
                }
                if (integer == 8) {
                    return OUTBOUND_CALL;
                }
                if (integer != 16) {
                    return NO_ACTIVITY;
                }
                return INCOMING_CALL;
            }
            return MISSED_CALLS;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class CallNotificationStatusReceiver {
        private final Callback callback;
        private final Context context;
        private boolean registered;
        private final TelephonyManager telephonyManager;
        private final CallStateBroadcastReceiver callStateBroadcastReceiver = new CallStateBroadcastReceiver();
        private final IntentFilter intentFilter = new IntentFilter("android.intent.action.PHONE_STATE");
        private final Object lock = new Object();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public class CallStateBroadcastReceiver extends BroadcastReceiver {
            CallStateBroadcastReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                synchronized (CallNotificationStatusReceiver.this.lock) {
                }
                if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                    CallNotificationStatusReceiver.this.callback.onCallStateReceived(CallNotificationStatus.OUTBOUND_CALL);
                } else {
                    CallNotificationStatusReceiver.this.callback.onCallStateReceived(CallNotificationStatusReceiver.this.getCurrentCallNotificationStatus());
                }
            }
        }

        /* loaded from: classes6.dex */
        interface Callback {
            void onCallStateReceived(CallNotificationStatus callNotificationStatus);
        }

        public CallNotificationStatusReceiver(Context context, Callback callback) {
            this.context = context;
            this.telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CallNotificationStatus getCurrentCallNotificationStatus() {
            int callState = this.telephonyManager.getCallState();
            if (callState != 1) {
                if (callState != 2) {
                    return CallNotificationStatus.NO_ACTIVITY;
                }
                return CallNotificationStatus.ACTIVE_CALL;
            }
            return CallNotificationStatus.INCOMING_CALL;
        }

        CallNotificationStatus activate() {
            CallNotificationStatus currentCallNotificationStatus;
            synchronized (this.lock) {
                if (!this.registered) {
                    this.registered = true;
                    currentCallNotificationStatus = getCurrentCallNotificationStatus();
                    this.context.registerReceiver(this.callStateBroadcastReceiver, this.intentFilter);
                } else {
                    throw new RuntimeException("Can't activate before deactivating if active");
                }
            }
            return currentCallNotificationStatus;
        }

        void deactivate() {
            synchronized (this.lock) {
                if (!this.registered) {
                    return;
                }
                this.registered = false;
                this.context.unregisterReceiver(this.callStateBroadcastReceiver);
            }
        }
    }

    public DefaultCallNotificationStateMonitor(Context context) {
        this.callNotificationStatusReceiver = new CallNotificationStatusReceiver(context, new CallNotificationStatusReceiver.Callback() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultCallNotificationStateMonitor$TcjfDQGvJtyXPbVaGB3kYCjxnwE
            @Override // com.amazon.alexa.accessorykit.interprocess.DefaultCallNotificationStateMonitor.CallNotificationStatusReceiver.Callback
            public final void onCallStateReceived(DefaultCallNotificationStateMonitor.CallNotificationStatus callNotificationStatus) {
                DefaultCallNotificationStateMonitor.this.newCallState(callNotificationStatus);
            }
        });
    }

    private void ensureActive() {
        synchronized (this.lock) {
            if (!this.active) {
                this.active = true;
                this.knownStatus = this.callNotificationStatusReceiver.activate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void newCallState(CallNotificationStatus callNotificationStatus) {
        synchronized (this.lock) {
            Iterator it2 = new ArrayList(this.observers).iterator();
            while (it2.hasNext()) {
                ((CallNotificationStateMonitor.Observer) it2.next()).onCallStatusChanged(callNotificationStatus.intRepresentation);
            }
            this.knownStatus = callNotificationStatus;
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public void addObserver(CallNotificationStateMonitor.Observer observer) {
        synchronized (this.lock) {
            ensureActive();
            this.observers.add(observer);
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public int getCallNotificationStatus() {
        int i;
        synchronized (this.lock) {
            ensureActive();
            i = this.knownStatus.intRepresentation;
        }
        return i;
    }

    @Override // com.amazon.alexa.accessory.monitor.CallNotificationStateMonitor
    public void removeObserver(CallNotificationStateMonitor.Observer observer) {
        synchronized (this.lock) {
            this.observers.remove(observer);
        }
    }
}
