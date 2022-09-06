package com.amazon.alexa.api;

import android.os.Handler;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class ConnectionListenerLifecycles {
    private static final long LISTENER_TIMEOUT_MS = 1000;
    private static final String TAG = "ConnectionListenerLifecycles";
    private final Handler defaultHandler;
    private final ReentrantLock lock;
    private final ManagedServiceConnection managedServiceConnection;
    private final Set<ConnectionListenerLifecycle> connectionListeners = new CopyOnWriteArraySet();
    private final Map<ConnectionListener, ConnectionListenerLifecycle> lifecycleMap = new HashMap();

    /* loaded from: classes6.dex */
    public interface ConnectionListener {
        void onConnected();

        void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str);

        void onDisconnected();
    }

    /* loaded from: classes6.dex */
    public static class ConnectionListenerLifecycle {
        private ConnectionListener connectionListener;
        private Handler handler;
        private ReentrantLock lock;
        private ManagedServiceConnection managedServiceConnection;
        private AtomicReference<ConnectionListenerState> state;

        private ConnectionListenerLifecycle(@Nullable Handler handler, ConnectionListener connectionListener, ManagedServiceConnection managedServiceConnection, ReentrantLock reentrantLock) {
            this.handler = handler;
            this.connectionListener = connectionListener;
            this.managedServiceConnection = managedServiceConnection;
            this.lock = reentrantLock;
            this.state = new AtomicReference<>(ConnectionListenerState.UNKNOWN);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ConnectionListenerLifecycle.class != obj.getClass()) {
                return false;
            }
            ConnectionListenerLifecycle connectionListenerLifecycle = (ConnectionListenerLifecycle) obj;
            return Objects.equals(this.handler.getLooper(), connectionListenerLifecycle.handler.getLooper()) && Objects.equals(this.connectionListener, connectionListenerLifecycle.connectionListener);
        }

        Handler getHandler() {
            return this.handler;
        }

        public int hashCode() {
            return Objects.hash(this.handler.getLooper(), this.connectionListener);
        }

        public void onConnected() {
            if (!this.state.get().equals(ConnectionListenerState.CONNECTED)) {
                ApiThreadHelper.runOnDefaultHandler(this.handler, new Runnable() { // from class: com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListenerLifecycle.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectionListenerLifecycle.this.lock.lock();
                        try {
                            if (ConnectionListenerLifecycle.this.managedServiceConnection.isConnected() && !((ConnectionListenerState) ConnectionListenerLifecycle.this.state.getAndSet(ConnectionListenerState.CONNECTED)).equals(ConnectionListenerState.CONNECTED)) {
                                ConnectionListenerLifecycle.this.connectionListener.onConnected();
                            }
                        } finally {
                            ConnectionListenerLifecycle.this.lock.unlock();
                        }
                    }
                });
            }
        }

        public void onConnectingFailed(final AlexaConnectingFailedReason alexaConnectingFailedReason, final String str) {
            if (!this.state.get().equals(ConnectionListenerState.FAILED)) {
                ApiThreadHelper.runOnDefaultHandler(this.handler, new Runnable() { // from class: com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListenerLifecycle.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!((ConnectionListenerState) ConnectionListenerLifecycle.this.state.getAndSet(ConnectionListenerState.FAILED)).equals(ConnectionListenerState.FAILED)) {
                            ConnectionListenerLifecycle.this.connectionListener.onConnectingFailed(alexaConnectingFailedReason, str);
                        }
                    }
                });
            }
        }

        public void onDisconnected(final CountDownLatch countDownLatch) {
            ConnectionListenerState connectionListenerState = this.state.get();
            if (connectionListenerState.equals(ConnectionListenerState.CONNECTED) || connectionListenerState.equals(ConnectionListenerState.UNKNOWN)) {
                ApiThreadHelper.runOnDefaultHandler(this.handler, new Runnable() { // from class: com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListenerLifecycle.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectionListenerState connectionListenerState2 = (ConnectionListenerState) ConnectionListenerLifecycle.this.state.getAndSet(ConnectionListenerState.DISCONNECTED);
                        if (connectionListenerState2.equals(ConnectionListenerState.CONNECTED) || connectionListenerState2.equals(ConnectionListenerState.UNKNOWN)) {
                            ConnectionListenerLifecycle.this.connectionListener.onDisconnected();
                        } else {
                            String str = ConnectionListenerLifecycles.TAG;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring disconnect state, already in state : ");
                            outline107.append(connectionListenerState2.name());
                            Log.w(str, outline107.toString());
                        }
                        countDownLatch.countDown();
                    }
                });
                return;
            }
            String str = ConnectionListenerLifecycles.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring disconnect state, already in state : ");
            outline107.append(connectionListenerState.name());
            Log.w(str, outline107.toString());
            countDownLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum ConnectionListenerState {
        CONNECTED,
        FAILED,
        DISCONNECTED,
        UNKNOWN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionListenerLifecycles(ManagedServiceConnection managedServiceConnection, ReentrantLock reentrantLock, Handler handler) {
        this.managedServiceConnection = managedServiceConnection;
        this.lock = reentrantLock;
        this.defaultHandler = handler;
    }

    public ConnectionListenerLifecycle addListener(@Nullable Handler handler, @NonNull ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "The provided ConnectionListener was null.");
        if (handler == null) {
            handler = this.defaultHandler;
        }
        ConnectionListenerLifecycle connectionListenerLifecycle = new ConnectionListenerLifecycle(handler, connectionListener, this.managedServiceConnection, this.lock);
        synchronized (this.connectionListeners) {
            this.lifecycleMap.put(connectionListener, connectionListenerLifecycle);
            this.connectionListeners.add(connectionListenerLifecycle);
        }
        return connectionListenerLifecycle;
    }

    public ConnectionListenerLifecycle addListener(@NonNull ConnectionListener connectionListener) {
        return addListener(null, connectionListener);
    }

    public void notifyConnectionConnected() {
        synchronized (this.connectionListeners) {
            for (ConnectionListenerLifecycle connectionListenerLifecycle : this.connectionListeners) {
                connectionListenerLifecycle.onConnected();
            }
        }
    }

    public void notifyConnectionDisconnected() {
        synchronized (this.connectionListeners) {
            CountDownLatch countDownLatch = new CountDownLatch(this.connectionListeners.size());
            for (ConnectionListenerLifecycle connectionListenerLifecycle : this.connectionListeners) {
                connectionListenerLifecycle.onDisconnected(countDownLatch);
            }
            try {
                countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e(TAG, "Waiting for onDisconnected listeners failed", e);
            }
        }
    }

    public void notifyConnectionFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        synchronized (this.connectionListeners) {
            for (ConnectionListenerLifecycle connectionListenerLifecycle : this.connectionListeners) {
                connectionListenerLifecycle.onConnectingFailed(alexaConnectingFailedReason, str);
            }
        }
    }

    public void removeListener(@NonNull ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "The provided ConnectionListener was null.");
        synchronized (this.connectionListeners) {
            ConnectionListenerLifecycle remove = this.lifecycleMap.remove(connectionListener);
            if (remove != null) {
                this.connectionListeners.remove(remove);
            }
        }
    }

    public void reset() {
        synchronized (this.connectionListeners) {
            for (ConnectionListenerLifecycle connectionListenerLifecycle : this.connectionListeners) {
                connectionListenerLifecycle.state.set(ConnectionListenerState.UNKNOWN);
            }
        }
    }

    int size() {
        int size;
        synchronized (this.connectionListeners) {
            size = this.connectionListeners.size();
        }
        return size;
    }
}
