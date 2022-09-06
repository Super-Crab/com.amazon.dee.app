package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.ConnectionListenerLifecycles;
import com.amazon.alexa.api.Releasable;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.security.ComponentEnabler;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes6.dex */
public abstract class ManagedServiceConnection<T extends Releasable> {
    private static final String BINDING_FAILED_MESSAGE = "Binding failed. Check logs for details";
    static final int BIND_ALLOW_BACKGROUND_ACTIVITY_STARTS = 1048576;
    private static final String COMPONENT_DISABLED_MESSAGE = "Could not connect to service. Service is disabled.";
    private static final String CONNECTION_LISTENER_THREAD_NAME_SUFFIX = "-connlisteners";
    private static final String CONNECTION_RELEASED_MESSAGE = "Connection object was released";
    private static final String EXECUTOR_NAME_PREFIX = "msc-";
    private static final String INVALID_SIGNATURE_MESSAGE = "Invalid signature";
    static final String START_ACTIVITIES_FROM_BACKGROUND_PERMISSION = "android.permission.START_ACTIVITIES_FROM_BACKGROUND";
    private static final String TAG = "ManagedServiceConnection";
    private volatile boolean bindingToService;
    private final ExtendedClient client;
    private final Handler connectionListenerHandler;
    private final ConnectionListenerLifecycles connectionListeners;
    private final AtomicLong connectionStartTime;
    private Context context;
    private volatile boolean expectedConnected;
    private final Handler handler;
    private final AtomicBoolean isConnected;
    private volatile boolean isReleased;
    protected final ReentrantLock lock;
    private final MessageReceiversManager messageReceiversManager;
    private final MetricBroadcastSender metricBroadcastSender;
    private final PackageManager packageManager;
    private ServiceConnection serviceConnectionListener;
    protected final AtomicReference<T> serviceInterface;
    private final SignatureVerifier signatureVerifier;
    private volatile boolean successfullyCalledBindService;
    private final TimeProvider timeProvider;
    private final ManagedServiceConnection<T>.UpdateConnectedStateRunnable updateConnectedStateRunnable;

    /* loaded from: classes6.dex */
    public interface ConnectionListener extends ConnectionListenerLifecycles.ConnectionListener {
    }

    /* loaded from: classes6.dex */
    private static class ServiceConnectionListener implements ServiceConnection {
        private final Handler handler;
        private final WeakReference<ManagedServiceConnection> serviceConnectionReference;

        ServiceConnectionListener(ManagedServiceConnection managedServiceConnection, Handler handler) {
            this.serviceConnectionReference = new WeakReference<>(managedServiceConnection);
            this.handler = handler;
        }

        private void handleConnectionCallback(Runnable runnable) {
            ManagedServiceConnection managedServiceConnection = this.serviceConnectionReference.get();
            if (managedServiceConnection != null) {
                if (managedServiceConnection.isReleased) {
                    managedServiceConnection.sendConnectingFailed(AlexaConnectingFailedReason.CONNECTION_OBJECT_RELEASED, ManagedServiceConnection.CONNECTION_RELEASED_MESSAGE);
                } else {
                    this.handler.post(runnable);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(final ComponentName componentName) {
            final ManagedServiceConnection managedServiceConnection = this.serviceConnectionReference.get();
            if (managedServiceConnection == null) {
                return;
            }
            String unused = ManagedServiceConnection.TAG;
            String.format(java.util.Locale.US, "ServiceConnectionListener.onBindingDied: %s for client: %s with debug tag: %s", componentName.flattenToShortString(), managedServiceConnection.getClient().getId(), managedServiceConnection.getClient().getDebugTag());
            handleConnectionCallback(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.ServiceConnectionListener.3
                @Override // java.lang.Runnable
                public void run() {
                    managedServiceConnection.onBindingDied(componentName);
                }
            });
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
            final ManagedServiceConnection managedServiceConnection = this.serviceConnectionReference.get();
            if (managedServiceConnection == null) {
                return;
            }
            String unused = ManagedServiceConnection.TAG;
            String.format(java.util.Locale.US, "ServiceConnectionListener.onServiceConnected: %s for client: %s with debug tag: %s", componentName.flattenToShortString(), managedServiceConnection.getClient().getId(), managedServiceConnection.getClient().getDebugTag());
            handleConnectionCallback(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.ServiceConnectionListener.1
                @Override // java.lang.Runnable
                public void run() {
                    managedServiceConnection.onConnectedToService(componentName, iBinder);
                }
            });
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(final ComponentName componentName) {
            final ManagedServiceConnection managedServiceConnection = this.serviceConnectionReference.get();
            if (managedServiceConnection == null) {
                return;
            }
            String unused = ManagedServiceConnection.TAG;
            String.format(java.util.Locale.US, "ServiceConnectionListener.onServiceDisconnected: %s for client: %s with debug tag: %s", componentName.flattenToShortString(), managedServiceConnection.getClient().getId(), managedServiceConnection.getClient().getDebugTag());
            handleConnectionCallback(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.ServiceConnectionListener.2
                @Override // java.lang.Runnable
                public void run() {
                    managedServiceConnection.onUnexpectedlyDisconnectedFromService(componentName);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class UpdateConnectedStateRunnable implements Runnable {
        private UpdateConnectedStateRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ManagedServiceConnection.this.isConnected.set(ManagedServiceConnection.this.internalIsConnected());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ManagedServiceConnection(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ManagedServiceConnection(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull Handler handler, @NonNull Handler handler2) {
        Preconditions.notNull(context, "The provided Context was null.");
        Preconditions.notNull(signatureVerifier, "The provided SignatureVerifier was null.");
        this.lock = new ReentrantLock();
        this.context = context;
        this.packageManager = context.getPackageManager();
        this.handler = handler;
        this.client = new ExtendedClient(context.getPackageName());
        this.connectionListenerHandler = handler2;
        this.connectionListeners = new ConnectionListenerLifecycles(this, this.lock, this.connectionListenerHandler);
        this.timeProvider = new TimeProvider();
        this.connectionStartTime = new AtomicLong();
        this.serviceConnectionListener = new ServiceConnectionListener(this, handler);
        this.signatureVerifier = signatureVerifier;
        this.messageReceiversManager = createMessageReceiverManager();
        this.metricBroadcastSender = new MetricBroadcastSender(this);
        this.serviceInterface = new AtomicReference<>();
        this.isConnected = new AtomicBoolean(false);
        this.updateConnectedStateRunnable = new UpdateConnectedStateRunnable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ManagedServiceConnection(@NonNull Context context, @Nullable String str) {
        Preconditions.notNull(context, "The provided Context was null.");
        String packageName = context.getPackageName();
        this.packageManager = context.getPackageManager();
        this.lock = new ReentrantLock();
        this.context = context;
        this.timeProvider = new TimeProvider();
        this.connectionStartTime = new AtomicLong();
        this.client = new ExtendedClient(packageName, str);
        this.handler = createHandler(this.client.getId());
        this.connectionListenerHandler = createHandler(this.client.getId() + CONNECTION_LISTENER_THREAD_NAME_SUFFIX);
        this.connectionListeners = new ConnectionListenerLifecycles(this, this.lock, this.connectionListenerHandler);
        this.serviceConnectionListener = new ServiceConnectionListener(this, this.handler);
        this.signatureVerifier = new SignatureVerifier(context);
        this.messageReceiversManager = createMessageReceiverManager();
        this.metricBroadcastSender = new MetricBroadcastSender(this);
        this.serviceInterface = new AtomicReference<>();
        this.isConnected = new AtomicBoolean(false);
        this.updateConnectedStateRunnable = new UpdateConnectedStateRunnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectInternal() {
        this.lock.lock();
        try {
            if (!this.isReleased && (isBoundToService() || isConnecting())) {
                doDisconnect(false);
            }
        } finally {
            unbindService(false);
            this.lock.unlock();
        }
    }

    private void doDisconnect() {
        doDisconnect(false);
    }

    private void doDisconnect(boolean z) {
        this.lock.lock();
        try {
            this.connectionStartTime.set(0L);
            Log.i(TAG, String.format(java.util.Locale.US, "onDisconnected: client: %s with debug tag: %s", getClient().getId(), getClient().getDebugTag()));
            this.connectionListeners.notifyConnectionDisconnected();
        } finally {
            onDisconnectedFromService();
            unbindService(z);
            releaseServiceInterface();
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:9|(1:11)(1:29)|12|(3:16|17|18)|19|20|17|18) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (1 != 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
        android.util.Log.e(com.amazon.alexa.api.ManagedServiceConnection.TAG, "Background service start failed. Attempting to start a foreground service");
        r5.context.startForegroundService(getConnectionIntent(r1, true));
     */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doStartService() {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.lock()
            boolean r0 = r5.isReleased     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto L16
            java.lang.String r0 = com.amazon.alexa.api.ManagedServiceConnection.TAG     // Catch: java.lang.Throwable -> L62
            java.lang.String r1 = "Not starting service - connection object is released"
            android.util.Log.w(r0, r1)     // Catch: java.lang.Throwable -> L62
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.unlock()
            return
        L16:
            boolean r0 = r5.preferBackgroundService()     // Catch: java.lang.Throwable -> L62
            android.content.ComponentName r1 = r5.getServiceComponentName()     // Catch: java.lang.Throwable -> L62
            r2 = 1
            if (r0 != 0) goto L23
            r3 = r2
            goto L24
        L23:
            r3 = 0
        L24:
            android.content.Intent r3 = r5.getConnectionIntent(r1, r3)     // Catch: java.lang.Throwable -> L62
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L62
            if (r0 != 0) goto L35
            if (r2 != 0) goto L2f
            goto L35
        L2f:
            android.content.Context r0 = r5.context     // Catch: java.lang.Throwable -> L62
            r0.startForegroundService(r3)     // Catch: java.lang.Throwable -> L62
            goto L53
        L35:
            android.content.Context r0 = r5.context     // Catch: java.lang.IllegalStateException -> L3b java.lang.SecurityException -> L59 java.lang.Throwable -> L62
            r0.startService(r3)     // Catch: java.lang.IllegalStateException -> L3b java.lang.SecurityException -> L59 java.lang.Throwable -> L62
            goto L53
        L3b:
            if (r2 == 0) goto L53
            boolean r0 = r5.shouldLaunchAsForegroundServiceIfBackgroundLaunchFails()     // Catch: java.lang.Throwable -> L62
            if (r0 == 0) goto L53
            java.lang.String r0 = com.amazon.alexa.api.ManagedServiceConnection.TAG     // Catch: java.lang.Throwable -> L62
            java.lang.String r3 = "Background service start failed. Attempting to start a foreground service"
            android.util.Log.e(r0, r3)     // Catch: java.lang.Throwable -> L62
            android.content.Context r0 = r5.context     // Catch: java.lang.Throwable -> L62
            android.content.Intent r1 = r5.getConnectionIntent(r1, r2)     // Catch: java.lang.Throwable -> L62
            r0.startForegroundService(r1)     // Catch: java.lang.Throwable -> L62
        L53:
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.unlock()
            return
        L59:
            r0 = move-exception
            java.lang.String r1 = com.amazon.alexa.api.ManagedServiceConnection.TAG     // Catch: java.lang.Throwable -> L62
            java.lang.String r2 = "Unable to start service"
            android.util.Log.e(r1, r2, r0)     // Catch: java.lang.Throwable -> L62
            throw r0     // Catch: java.lang.Throwable -> L62
        L62:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantLock r1 = r5.lock
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.ManagedServiceConnection.doStartService():void");
    }

    private int getFlags() {
        return hasAllowBackgroundActivityStartsPermission() ? 1048577 : 1;
    }

    private boolean hasAllowBackgroundActivityStartsPermission() {
        return this.context.checkPermission(START_ACTIVITIES_FROM_BACKGROUND_PERMISSION, Process.myPid(), Process.myUid()) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBindingDied(ComponentName componentName) {
        this.lock.lock();
        try {
            if (isServiceComponentNameResolved() && getServiceComponentName().equals(componentName)) {
                releaseServiceInterface();
                disconnect();
                this.bindingToService = false;
            }
        } finally {
            this.connectionStartTime.set(0L);
            this.lock.unlock();
        }
    }

    private void onPackageDisabledFailure() {
        sendConnectingFailed(AlexaConnectingFailedReason.ALEXA_SERVICES_DISABLED, COMPONENT_DISABLED_MESSAGE);
        throw new SecurityException(COMPONENT_DISABLED_MESSAGE);
    }

    private void onSignatureConnectionFailed() {
        sendConnectingFailed(AlexaConnectingFailedReason.UNAUTHORIZED, INVALID_SIGNATURE_MESSAGE);
        throw new SecurityException("Signature could not be verified. Hence, cannot connect to service.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUnexpectedlyDisconnectedFromService(ComponentName componentName) {
        this.lock.lock();
        try {
            if (isServiceComponentNameResolved() && getServiceComponentName().equals(componentName)) {
                if (!isConnected()) {
                    sendConnectingFailed(AlexaConnectingFailedReason.UNKNOWN, "Unexpectedly disconnected while connecting.");
                }
                doDisconnect();
                this.bindingToService = false;
            }
        } finally {
            this.connectionStartTime.set(0L);
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseServiceInterface() {
        T t = this.serviceInterface.get();
        if (t != null) {
            t.release();
            this.serviceInterface.set(null);
        }
        updateConnectedState();
    }

    private void terminateHandlerThread(Handler handler) {
        Looper looper = handler.getLooper();
        if (looper != null) {
            looper.quitSafely();
        }
    }

    private void unbindService(boolean z) {
        if (!this.isReleased || this.successfullyCalledBindService) {
            if (!this.successfullyCalledBindService && !z) {
                return;
            }
            try {
                String.format(java.util.Locale.US, "unbindService: %s for client: %s with debug tag: %s", getServiceComponentName().flattenToShortString(), getClient().getId(), getClient().getDebugTag());
                this.context.unbindService(this.serviceConnectionListener);
                this.successfullyCalledBindService = false;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnbindService failed. boundToService? ");
                outline107.append(isBoundToService());
                outline107.append(", force? ");
                outline107.append(z);
                outline107.append(", bindingToService? ");
                outline107.append(this.bindingToService);
                outline107.append(", isRelease? ");
                outline107.append(this.isReleased);
                Log.e(str, outline107.toString());
                if (!z) {
                    this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.UNBIND_SERVICE_FAILED);
                }
            }
            this.bindingToService = false;
        }
    }

    void bindService(Intent intent) {
        String.format(java.util.Locale.US, "bindService: %s for client: %s with debug tag: %s", getServiceComponentName().flattenToShortString(), getClient().getId(), getClient().getDebugTag());
        if (this.context.bindService(intent, this.serviceConnectionListener, getFlags())) {
            this.successfullyCalledBindService = true;
            this.bindingToService = true;
            return;
        }
        Log.e(TAG, "Failed to connect, disconnecting");
        sendConnectingFailed(AlexaConnectingFailedReason.UNKNOWN, BINDING_FAILED_MESSAGE);
        this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.BIND_SERVICE_FAILED);
        doDisconnect(true);
    }

    protected boolean checkIfServiceIsEnabled(ComponentName componentName) {
        return ComponentEnabler.checkIfServiceIsEnabled(this.packageManager, componentName);
    }

    public void connect() {
        String str = TAG;
        Log.i(str, getClient().getId() + " called connect");
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.1
            @Override // java.lang.Runnable
            public void run() {
                ManagedServiceConnection.this.lock.lock();
                try {
                    ManagedServiceConnection.this.connectionListeners.reset();
                    if (ManagedServiceConnection.this.isReleased) {
                        ManagedServiceConnection.this.sendConnectingFailed(AlexaConnectingFailedReason.CONNECTION_OBJECT_RELEASED, ManagedServiceConnection.CONNECTION_RELEASED_MESSAGE);
                        return;
                    }
                    ManagedServiceConnection.this.expectedConnected = true;
                    ManagedServiceConnection.this.updateConnectedState();
                    if (ManagedServiceConnection.this.isServiceComponentNameResolved()) {
                        ManagedServiceConnection.this.doConnect();
                    } else {
                        ManagedServiceConnection.this.resolveServiceComponentName();
                    }
                } finally {
                    ManagedServiceConnection.this.lock.unlock();
                }
            }
        });
    }

    Handler createHandler(String str) {
        HandlerThread handlerThread = new HandlerThread(GeneratedOutlineSupport1.outline72(EXECUTOR_NAME_PREFIX, str));
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    protected MessageReceiversManager createMessageReceiverManager() {
        return new MessageReceiversManager(getSignatureVerifier());
    }

    /* renamed from: createServiceInterface */
    protected abstract T mo811createServiceInterface(IBinder iBinder);

    public void deregisterListener(@NonNull ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "The provided ConnectionListener was null.");
        this.connectionListeners.removeListener(connectionListener);
    }

    public void disconnect() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.2
            @Override // java.lang.Runnable
            public void run() {
                ManagedServiceConnection.this.lock.lock();
                try {
                    ManagedServiceConnection.this.expectedConnected = false;
                    ManagedServiceConnection.this.updateConnectedState();
                    ManagedServiceConnection.this.disconnectInternal();
                } finally {
                    ManagedServiceConnection.this.lock.unlock();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doConnect() {
        this.lock.lock();
        try {
            try {
                if (isServiceComponentNameResolved() && !isConnecting() && !isConnected()) {
                    ComponentName serviceComponentName = getServiceComponentName();
                    if (!verifySignature(serviceComponentName.getPackageName())) {
                        onSignatureConnectionFailed();
                    } else if (!checkIfServiceIsEnabled(serviceComponentName)) {
                        onPackageDisabledFailure();
                    } else {
                        this.connectionStartTime.set(this.timeProvider.elapsedRealTime());
                        bindService(getConnectionIntent(serviceComponentName, !preferBackgroundService()));
                    }
                }
            } catch (SecurityException unused) {
                Log.e(TAG, "Security exception thrown, disconnecting");
                sendConnectingFailed(AlexaConnectingFailedReason.UNAUTHORIZED, BINDING_FAILED_MESSAGE);
                doDisconnect();
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeOnInternalConnectionThread(Runnable runnable) {
        ApiThreadHelper.runOnDefaultHandler(this.handler, runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: get */
    public T mo838get() {
        return this.serviceInterface.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtendedClient getClient() {
        return this.client;
    }

    protected abstract Intent getConnectionIntent(ComponentName componentName, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageReceiversManager getMessageReceiversManager() {
        return this.messageReceiversManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MetricBroadcastSender getMetricBroadcastSender() {
        return this.metricBroadcastSender;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ComponentName getServiceComponentName();

    /* JADX INFO: Access modifiers changed from: protected */
    public SignatureVerifier getSignatureVerifier() {
        return this.signatureVerifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean internalIsConnected() {
        return isBoundToService() && this.expectedConnected;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isBoundToService() {
        return mo838get() != null;
    }

    public boolean isConnected() {
        return this.isConnected.get();
    }

    public boolean isConnecting() {
        return this.bindingToService;
    }

    protected abstract boolean isServiceComponentNameResolved();

    protected void onConnectedToService() {
    }

    void onConnectedToService(ComponentName componentName, IBinder iBinder) {
        this.lock.lock();
        try {
            this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.CLIENT_CONNECTION_LATENCY, this.timeProvider.elapsedRealTime() - this.connectionStartTime.getAndSet(0L));
            if (this.isReleased) {
                Log.w(TAG, "onConnectedToService connection is already released");
                return;
            }
            if (isServiceComponentNameResolved() && getServiceComponentName().equals(componentName)) {
                releaseServiceInterface();
                this.serviceInterface.set(mo811createServiceInterface(iBinder));
                this.bindingToService = false;
                updateConnectedState();
                onConnectedToService();
                if (isConnected()) {
                    Log.i(TAG, String.format(java.util.Locale.US, "onConnected: client: %s with debug tag: %s", getClient().getId(), getClient().getDebugTag()));
                    this.connectionListeners.notifyConnectionConnected();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    protected void onDisconnectedFromService() {
    }

    protected boolean preferBackgroundService() {
        return false;
    }

    public void registerListener(@NonNull Handler handler, @NonNull ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "The provided ConnectionListener was null.");
        final ConnectionListenerLifecycles.ConnectionListenerLifecycle addListener = this.connectionListeners.addListener(handler, connectionListener);
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.4
            @Override // java.lang.Runnable
            public void run() {
                ManagedServiceConnection.this.lock.lock();
                try {
                    if (ManagedServiceConnection.this.isConnected()) {
                        addListener.onConnected();
                    }
                } finally {
                    ManagedServiceConnection.this.lock.unlock();
                }
            }
        });
    }

    public void registerListener(@NonNull ConnectionListener connectionListener) {
        registerListener(this.connectionListenerHandler, connectionListener);
    }

    public void release() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.3
            @Override // java.lang.Runnable
            public void run() {
                ManagedServiceConnection.this.lock.lock();
                try {
                    ManagedServiceConnection.this.expectedConnected = false;
                    ManagedServiceConnection.this.updateConnectedState();
                    if (!ManagedServiceConnection.this.isReleased) {
                        ManagedServiceConnection.this.disconnectInternal();
                        ManagedServiceConnection.this.isReleased = true;
                        ManagedServiceConnection.this.releaseServiceInterface();
                        ManagedServiceConnection.this.messageReceiversManager.clear();
                        ManagedServiceConnection.this.context = null;
                        ManagedServiceConnection.this.serviceConnectionListener = null;
                    }
                } finally {
                    ManagedServiceConnection.this.lock.unlock();
                }
            }
        });
        terminateHandlerThread(this.handler);
        terminateHandlerThread(this.connectionListenerHandler);
    }

    protected abstract void resolveServiceComponentName();

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        Log.i(TAG, String.format(java.util.Locale.US, "onConnectingFailed due to %s: (client: %s with debug tag: %s)", str, getClient().getId(), getClient().getDebugTag()));
        this.connectionListeners.notifyConnectionFailed(alexaConnectingFailedReason, str);
    }

    protected boolean shouldLaunchAsForegroundServiceIfBackgroundLaunchFails() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startService() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.ManagedServiceConnection.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ManagedServiceConnection.this.doStartService();
                } catch (SecurityException e) {
                    throw e;
                } catch (Exception e2) {
                    if (Build.VERSION.SDK_INT < 31 || !"ForegroundServiceStartNotAllowedException".equals(e2.getClass().getSimpleName())) {
                        ManagedServiceConnection.this.sendConnectingFailed(AlexaConnectingFailedReason.UNKNOWN, e2.getMessage());
                        ManagedServiceConnection.this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.SERVICE_START_FAILED_REASON_PREFIX.appendToAlexaMetricsName(e2.getMessage()));
                        return;
                    }
                    ManagedServiceConnection.this.sendConnectingFailed(AlexaConnectingFailedReason.CANNOT_START_FOREGROUND_SERVICE_APP_IN_BACKGROUND, e2.getMessage());
                    ManagedServiceConnection.this.metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.FOREGROUND_START_NOT_ALLOWED);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateConnectedState() {
        executeOnInternalConnectionThread(this.updateConnectedStateRunnable);
    }

    protected boolean verifySignature(String str) {
        return this.signatureVerifier.verify(str);
    }
}
