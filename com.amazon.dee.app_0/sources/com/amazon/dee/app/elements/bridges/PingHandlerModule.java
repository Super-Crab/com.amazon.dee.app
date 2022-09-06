package com.amazon.dee.app.elements.bridges;

import androidx.annotation.NonNull;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.services.logging.Log;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsConstants;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import rx.Observable;
import rx.subjects.PublishSubject;
@ReactModule(name = PingHandlerModule.MODULE_NAME)
/* loaded from: classes12.dex */
public class PingHandlerModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "PingHandler";
    private static final long PERIOD_IN_MILLIS = 5000;
    private static final String TAG = Log.tag(PingHandlerModule.class);
    private static final long TIMER_DELAY_IN_MILLIS = 0;
    private PublishSubject<Void> bridgePingTimeout;
    private final CollectionsFactory collectionsFactory;
    private final MetricsServiceV2 metricsServiceV2;
    private PingTask pingTask;
    private Timer pingTimer;
    private final ReactApplicationContext reactApplicationContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class PingTask extends TimerTask {
        private static final String ID = "id";
        private static final String PING_EVENT = "ping";
        private static final int STALE_PING_LIMIT = 3;
        private static final String TIMESTAMP = "timestamp";
        private ConcurrentMap<String, PingData> activePings;
        private final MetricDescriptor pingDelayedDescriptor;
        private final MetricDescriptor pingFailureDescriptor;
        private final MetricDescriptor pingLatencyDescriptor;
        private final MetricDescriptor pingTimeoutDescriptor;

        PingTask() {
            MetricComponentName build = new MetricComponentName.Builder().withCategoryId("ping").build();
            this.pingLatencyDescriptor = new MetricDescriptor.Builder(new MetricName.Builder("ping").withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).withEmissionFactor(10).build();
            this.pingFailureDescriptor = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.PING_FAILURE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).withEmissionFactor(10).build();
            this.pingTimeoutDescriptor = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.PING_TIMEOUT).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).build();
            this.pingDelayedDescriptor = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.PING_DELAYED).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).build();
            this.activePings = new ConcurrentHashMap();
        }

        private synchronized void checkStalePings() {
            if (this.activePings.isEmpty()) {
                return;
            }
            for (String str : this.activePings.keySet()) {
                PingData pingData = this.activePings.get(str);
                if (!pingData.isTimedOut()) {
                    String str2 = PingHandlerModule.TAG;
                    Log.w(str2, "Possible stale ping " + str);
                    long currentTimeMillis = System.currentTimeMillis() - pingData.getTimestamp();
                    PingHandlerModule.this.metricsServiceV2.recordLimit(this.pingFailureDescriptor, true);
                    PingHandlerModule.this.metricsServiceV2.recordTime(this.pingLatencyDescriptor, currentTimeMillis);
                    PingHandlerModule.this.metricsServiceV2.recordCount(this.pingTimeoutDescriptor, 1.0d);
                    pingData.setTimedOut(true);
                }
            }
            if (this.activePings.size() >= 3) {
                Log.e(PingHandlerModule.TAG, "Ping limit reached. React bridge unresponsive");
                this.activePings.clear();
                PingHandlerModule.this.emitTimeoutEvent();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void removePing(ReadableMap readableMap) {
            String string = readableMap.getString("id");
            if (string == null) {
                Log.e(PingHandlerModule.TAG, "Received bad ping. Possible error in PingHandler.");
            } else if (!this.activePings.containsKey(string)) {
                Log.e(PingHandlerModule.TAG, "Received invalid ping from Ping Handler.");
            } else {
                PingData pingData = this.activePings.get(string);
                long currentTimeMillis = System.currentTimeMillis() - pingData.getTimestamp();
                if (currentTimeMillis < 0) {
                    Log.e(PingHandlerModule.TAG, "Possible error in pinging");
                    return;
                }
                boolean z = currentTimeMillis <= 5000;
                if (pingData.isTimedOut()) {
                    PingHandlerModule.this.metricsServiceV2.recordTime(this.pingDelayedDescriptor, currentTimeMillis);
                } else {
                    PingHandlerModule.this.metricsServiceV2.recordLimit(this.pingFailureDescriptor, z);
                    PingHandlerModule.this.metricsServiceV2.recordTime(this.pingLatencyDescriptor, currentTimeMillis);
                    pingData.setTimedOut(true);
                }
                if (!z) {
                    String str = PingHandlerModule.TAG;
                    Log.w(str, "React bridge ping " + pingData.getId() + " timed out with latency " + currentTimeMillis + " ms.");
                } else {
                    this.activePings.clear();
                }
            }
        }

        private synchronized void sendPing() {
            checkStalePings();
            String uuid = UUID.randomUUID().toString();
            long currentTimeMillis = System.currentTimeMillis();
            WritableMap createMap = PingHandlerModule.this.collectionsFactory.createMap();
            createMap.putString("id", uuid);
            createMap.putDouble("timestamp", currentTimeMillis);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) PingHandlerModule.this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("ping", createMap);
            this.activePings.put(uuid, new PingData(uuid, currentTimeMillis, false));
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            sendPing();
        }
    }

    public PingHandlerModule(@NonNull ReactApplicationContext reactApplicationContext, @NonNull CollectionsFactory collectionsFactory, @NonNull MetricsServiceV2 metricsServiceV2) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
        this.collectionsFactory = collectionsFactory;
        this.metricsServiceV2 = metricsServiceV2;
        this.bridgePingTimeout = PublishSubject.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void emitTimeoutEvent() {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$PingHandlerModule$-YAuum6mr9A40LBIMhJD6385cd4
                @Override // java.lang.Runnable
                public final void run() {
                    PingHandlerModule.this.lambda$emitTimeoutEvent$0$PingHandlerModule();
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public /* synthetic */ void lambda$emitTimeoutEvent$0$PingHandlerModule() {
        this.bridgePingTimeout.onNext(null);
    }

    public Observable<Void> onBridgePingTimeout() {
        return this.bridgePingTimeout;
    }

    @ReactMethod
    public synchronized void ping(ReadableMap readableMap) {
        if (this.pingTask != null) {
            this.pingTask.removePing(readableMap);
        }
    }

    public void startPingTimer() {
        if (this.pingTimer == null) {
            Log.i(TAG, "Starting ping timer");
            this.pingTask = new PingTask();
            this.pingTimer = new Timer();
            this.pingTimer.schedule(this.pingTask, 0L, 5000L);
        }
    }

    public void stopPingTimer() {
        if (this.pingTimer != null) {
            Log.i(TAG, "Stopping ping timer");
            this.pingTask.cancel();
            this.pingTimer.cancel();
            this.pingTask = null;
            this.pingTimer = null;
        }
    }
}
