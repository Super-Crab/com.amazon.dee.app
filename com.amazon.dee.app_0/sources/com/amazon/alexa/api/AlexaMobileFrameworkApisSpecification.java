package com.amazon.alexa.api;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.bl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaMobileFrameworkApisSpecification {
    static final int DEFAULT_CONNECTION_RETRY_COUNT = 2;
    private static final String TAG = "AlexaMobileFrameworkApisSpecification";
    private static List<EnabledListener> apiEnabledListeners = new CopyOnWriteArrayList();
    private static volatile boolean enabled;
    private AtomicBoolean apisInStartedState;
    private final AlexaServicesConnection connection;
    private final ConnectionListener connectionListener;
    private final EnabledListener enabledListener = new EnabledListener(this);
    private final Subcomponent[] subcomponents;

    /* renamed from: com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason = new int[AlexaConnectingFailedReason.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.ALEXA_SERVICES_DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.UNAUTHORIZED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.CONNECTION_OBJECT_RELEASED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.CANNOT_START_FOREGROUND_SERVICE_APP_IN_BACKGROUND.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ConnectionListener implements AlexaServicesConnection.ConnectionListener {
        private final AlexaServicesConnection connection;
        private final ConcurrentLinkedQueue<g> postponedApiCalls;
        private int retryCount = 2;
        private final AtomicBoolean started;
        private final Subcomponent[] subcomponents;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ConnectionListener(Subcomponent[] subcomponentArr, ConcurrentLinkedQueue<g> concurrentLinkedQueue, AtomicBoolean atomicBoolean, AlexaServicesConnection alexaServicesConnection) {
            this.subcomponents = subcomponentArr;
            this.postponedApiCalls = concurrentLinkedQueue;
            this.started = atomicBoolean;
            this.connection = alexaServicesConnection;
        }

        private void retryConnection() {
            int i = this.retryCount - 1;
            this.retryCount = i;
            if (i >= 0) {
                this.connection.connect();
                return;
            }
            Log.w(AlexaMobileFrameworkApisSpecification.TAG, "Retries failed. Dropping postponed apis");
            AlexaMobileFrameworkApisSpecification.dropPostponedApis(this.postponedApiCalls);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            Log.i(AlexaMobileFrameworkApisSpecification.TAG, "Connected to service. Calling APIs");
            for (Subcomponent subcomponent : this.subcomponents) {
                subcomponent.onConnected();
            }
            AlexaMobileFrameworkApisSpecification.executePostponedApis(this.postponedApiCalls);
            this.retryCount = 2;
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            String str2 = AlexaMobileFrameworkApisSpecification.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Connecting failed due to ");
            outline107.append(alexaConnectingFailedReason.name());
            Log.w(str2, outline107.toString());
            int ordinal = alexaConnectingFailedReason.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                retryConnection();
            } else if (ordinal == 3 || ordinal == 4) {
            } else {
                AlexaMobileFrameworkApisSpecification.dropPostponedApis(this.postponedApiCalls);
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            Log.i(AlexaMobileFrameworkApisSpecification.TAG, "Disconnected from service");
            AlexaMobileFrameworkApisSpecification.dropPostponedApis(this.postponedApiCalls);
            for (Subcomponent subcomponent : this.subcomponents) {
                subcomponent.onDisconnected();
            }
            if (this.started.get()) {
                retryConnection();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class EnabledListener {
        private final WeakReference<AlexaMobileFrameworkApisSpecification> wrapperWeakReference;

        EnabledListener(AlexaMobileFrameworkApisSpecification alexaMobileFrameworkApisSpecification) {
            this.wrapperWeakReference = new WeakReference<>(alexaMobileFrameworkApisSpecification);
        }

        void onEnabledStateChanged(boolean z) {
            GeneratedOutlineSupport1.outline173("Enabled state changed. Enabled? ", z, AlexaMobileFrameworkApisSpecification.TAG);
            AlexaMobileFrameworkApisSpecification alexaMobileFrameworkApisSpecification = this.wrapperWeakReference.get();
            if (alexaMobileFrameworkApisSpecification == null) {
                Log.w(AlexaMobileFrameworkApisSpecification.TAG, "Weak reference to AlexaMobileFrameworkApisSpecification expired");
            } else if (!alexaMobileFrameworkApisSpecification.apisInStartedState.get()) {
            } else {
                if (z) {
                    alexaMobileFrameworkApisSpecification.doStart();
                } else {
                    alexaMobileFrameworkApisSpecification.doStop();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static abstract class Subcomponent {
        private final AtomicBoolean apisAreInStartedState;
        protected final AlexaServicesConnection connection;
        private final ConcurrentLinkedQueue<g> postponedApiCalls;
        private final bl registeredObjects = new bl();
        private final Map<Object, Map<Class<?>, Object>> objectWrapperMapping = new HashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Subcomponent(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, ConcurrentLinkedQueue<g> concurrentLinkedQueue) {
            this.connection = alexaServicesConnection;
            this.apisAreInStartedState = atomicBoolean;
            this.postponedApiCalls = concurrentLinkedQueue;
        }

        private void deregisterObjects() {
            this.registeredObjects.b();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void deregisterCallbacksObject(Object obj) {
            bl.a a = this.registeredObjects.a(obj);
            if (a != null) {
                a.b();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void deregisterCallbacksObject(Object obj, Class<?> cls) {
            if (!this.objectWrapperMapping.containsKey(obj)) {
                String str = AlexaMobileFrameworkApisSpecification.TAG;
                Log.w(str, "object " + obj + " is not registered");
                return;
            }
            Map<Class<?>, Object> map = this.objectWrapperMapping.get(obj);
            if (map != null && map.containsKey(cls)) {
                Object remove = map.remove(cls);
                if (map.isEmpty()) {
                    this.objectWrapperMapping.remove(obj);
                }
                deregisterCallbacksObject(remove);
                return;
            }
            String str2 = AlexaMobileFrameworkApisSpecification.TAG;
            Log.w(str2, "no registered object of class " + cls);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void executeApi(g gVar) {
            this.postponedApiCalls.add(gVar);
            if (this.connection.isConnected()) {
                AlexaMobileFrameworkApisSpecification.executePostponedApis(this.postponedApiCalls);
            } else if (!this.apisAreInStartedState.get()) {
            } else {
                this.connection.connect();
            }
        }

        void onConnected() {
            this.registeredObjects.a();
        }

        void onDisconnected() {
            deregisterObjects();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void registerCallbacksObject(Object obj, Class<?> cls, Runnable runnable, Runnable runnable2) {
            if (!this.objectWrapperMapping.containsKey(obj)) {
                this.objectWrapperMapping.put(obj, new HashMap());
            }
            Map<Class<?>, Object> map = this.objectWrapperMapping.get(obj);
            if (!map.containsKey(cls)) {
                Object obj2 = new Object();
                map.put(cls, obj2);
                registerCallbacksObject(obj2, runnable, runnable2);
                return;
            }
            String str = AlexaMobileFrameworkApisSpecification.TAG;
            Log.w(str, "object of class " + cls + " is already registered");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void registerCallbacksObject(Object obj, Runnable runnable, Runnable runnable2) {
            this.registeredObjects.a(obj, runnable, runnable2);
            if (this.connection.isConnected()) {
                runnable.run();
            } else if (!this.apisAreInStartedState.get()) {
            } else {
                this.connection.connect();
            }
        }

        void release() {
            AlexaMobileFrameworkApisSpecification.executePostponedApis(this.postponedApiCalls);
            deregisterObjects();
            this.registeredObjects.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaMobileFrameworkApisSpecification(AlexaServicesConnection alexaServicesConnection, AtomicBoolean atomicBoolean, Subcomponent[] subcomponentArr, ConnectionListener connectionListener) {
        this.connection = alexaServicesConnection;
        this.apisInStartedState = atomicBoolean;
        this.subcomponents = subcomponentArr;
        this.connectionListener = connectionListener;
        alexaServicesConnection.registerListener(connectionListener);
        alexaServicesConnection.initialize();
        apiEnabledListeners.add(this.enabledListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disableAlexa(Context context) {
        enabled = false;
        AlexaServices.disable(context);
        for (EnabledListener enabledListener : apiEnabledListeners) {
            enabledListener.onEnabledStateChanged(enabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStart() {
        this.connection.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStop() {
        this.connection.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dropPostponedApis(Queue<g> queue) {
        executePostponedApis(queue);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableAlexa(Context context) {
        enabled = true;
        AlexaServices.enable(context);
        for (EnabledListener enabledListener : apiEnabledListeners) {
            enabledListener.onEnabledStateChanged(enabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void executePostponedApis(Queue<g> queue) {
        while (!queue.isEmpty()) {
            g poll = queue.poll();
            if (poll != null) {
                poll.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAlexaEnabled(Context context) {
        boolean isAlexaEnabled = AlexaServices.isAlexaEnabled(context);
        GeneratedOutlineSupport1.outline172("Is Alexa enabled? ", isAlexaEnabled);
        return isAlexaEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        for (Subcomponent subcomponent : this.subcomponents) {
            subcomponent.release();
        }
        this.connection.deregisterListener(this.connectionListener);
        this.connection.release();
        apiEnabledListeners.remove(this.enabledListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isStarted() {
        return this.apisInStartedState.get();
    }

    void setAllowsBackgroundActivityStarts(boolean z) {
        this.connection.setAllowsBackgroundActivityStarts(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start() {
        this.apisInStartedState.set(true);
        enabled = isAlexaEnabled(this.connection.getContext());
        if (enabled) {
            doStart();
        } else {
            Log.i(TAG, "Waiting for Alexa to be enabled before starting");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        this.apisInStartedState.set(false);
        doStop();
    }
}
