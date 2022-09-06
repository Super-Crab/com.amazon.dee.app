package com.amazon.alexa.accessory.avsclient.bootup;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class AccessoryBootupService {
    private static final int DEFAULT_CP_SWEEPING_LIGHT_TIME = 5;
    private static final boolean LED_CONNECTION_ENABLED = true;
    private static final boolean SUCCESS_EARCON_ENABLED = true;
    private static final String TAG = "AccessoryBootupService";
    private final Map<String, AccessoryBootUpMetadata> connectedAccessories;
    private boolean downchannelIsConnected;
    private final Object lock;
    private final ReadinessListener readinessListener;
    private final ReadinessSupplier readinessSupplier;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ReadinessListener implements ReadinessSupplier.Listener {
        private ReadinessListener() {
        }

        @Override // com.amazon.alexa.accessory.avsclient.bootup.ReadinessSupplier.Listener
        public void onReadyState(boolean z) {
            Logger.d("%s : ReadinessListener onReadyState - isReady: %b ", AccessoryBootupService.TAG, Boolean.valueOf(z));
            synchronized (AccessoryBootupService.this.lock) {
                AccessoryBootupService.this.downchannelIsConnected = z;
                if (!AccessoryBootupService.this.connectedAccessories.isEmpty() && AccessoryBootupService.this.downchannelIsConnected) {
                    AccessoryBootupService.this.logMetricsForConnectedAccessories();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            Logger.d("%s : SessionListener onAccessorySessionConnected", AccessoryBootupService.TAG);
            AccessorySession session = AccessoryBootupService.this.sessionSupplier.getSession(accessory);
            AccessoryBootupService.this.setAccessoryStates(session);
            AccessoryBootupService.this.awaitPoweredOnMetric(session);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            Logger.d("AccessoryBootupServiceSessionListener onAccessorySessionReleased");
            synchronized (AccessoryBootupService.this.lock) {
                AccessoryBootupService.this.connectedAccessories.remove(accessory.getAddress());
            }
        }
    }

    public AccessoryBootupService(ReadinessSupplier readinessSupplier, SessionSupplier sessionSupplier, AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(readinessSupplier, "readinessSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        this.readinessSupplier = readinessSupplier;
        this.sessionSupplier = sessionSupplier;
        AccessoryMetricsServiceHolder.getInstance().set(accessoryMetricsService);
        this.connectedAccessories = new HashMap();
        this.sessionListener = new SessionListener();
        this.readinessListener = new ReadinessListener();
        this.lock = new Object();
        this.downchannelIsConnected = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void awaitPoweredOnMetric(final AccessorySession accessorySession) {
        accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$AccessoryBootupService$RIdxrLyJxECevfkgFH-NrITg-v4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryBootupService.this.lambda$awaitPoweredOnMetric$2$AccessoryBootupService(accessorySession, (Device.DeviceInformation) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$AccessoryBootupService$f9NahaywtogdVlAroPKhXng7WKo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryBootupService.lambda$awaitPoweredOnMetric$3(AccessorySession.this, (Throwable) obj);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    private void dispatchState(AccessorySession accessorySession, StateOuterClass.State state) {
        accessorySession.getStateRepository().set(state).subscribe(Functions.EMPTY_ACTION, Functions.emptyConsumer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$awaitPoweredOnMetric$3(AccessorySession accessorySession, Throwable th) throws Throwable {
        Logger.d("%s : ERROR: Failed to get device Type for the session %s", TAG, accessorySession.getAddress());
        Logger.e("%s : Failed to get device Type for the session", TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMetricsForConnectedAccessories() {
        Date date = new Date();
        synchronized (this.lock) {
            for (AccessoryBootUpMetadata accessoryBootUpMetadata : this.connectedAccessories.values()) {
                long time = date.getTime() - accessoryBootUpMetadata.getPoweredOnTime().getTime();
                Logger.d("%s : Logging metric %s, delay %d", TAG, MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_TIME, Long.valueOf(time));
                AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_TIME, accessoryBootUpMetadata.getDeviceType(), time, null);
                Logger.d("%s : Logging metric %s, delay %d", TAG, MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_NON_ZERO_TIME, Long.valueOf(time));
                AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_NON_ZERO_TIME, accessoryBootUpMetadata.getDeviceType(), time, null);
            }
            this.connectedAccessories.clear();
        }
    }

    private void sendConnectionProgressErrorColorLEDState(AccessorySession accessorySession) {
        dispatchState(accessorySession, StateOuterClass.State.newBuilder().setFeature(StateFeature.CONNECTION_PROGRESS_SWEEPING_LIGHT_LED_COLOR.toInteger()).setBoolean(true).mo10084build());
    }

    private void sendSuccessEarconMessagingState(AccessorySession accessorySession) {
        dispatchState(accessorySession, StateOuterClass.State.newBuilder().setFeature(StateFeature.SUCCESS_EARCON_MESSAGE.toInteger()).setBoolean(true).mo10084build());
    }

    private void sendSweepingLightTime(AccessorySession accessorySession) {
        dispatchState(accessorySession, StateOuterClass.State.newBuilder().setFeature(StateFeature.CONNECTION_PROGRESS_SWEEPING_LIGHT_TIME.toInteger()).setInteger(5).mo10084build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccessoryStates(AccessorySession accessorySession) {
        Logger.d("%s : sending state messages to device", TAG);
        sendSweepingLightTime(accessorySession);
        sendConnectionProgressErrorColorLEDState(accessorySession);
        sendSuccessEarconMessagingState(accessorySession);
    }

    @SuppressLint({"CheckResult"})
    private void updateConnectedAccessories(final String str, final String str2, StateRepository stateRepository) {
        stateRepository.query(StateFeature.DEVICE_POWERED_ON).map($$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$AccessoryBootupService$XA04reRvoIFYMQCIPxKayZvGsaA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryBootupService.this.lambda$updateConnectedAccessories$0$AccessoryBootupService(str2, str, (Boolean) obj);
            }
        }, $$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ.INSTANCE);
    }

    public void activate() {
        this.sessionSupplier.addSessionListener(this.sessionListener);
        this.readinessSupplier.registerReadinessListener(this.readinessListener);
    }

    public /* synthetic */ void lambda$awaitPoweredOnMetric$2$AccessoryBootupService(AccessorySession accessorySession, Device.DeviceInformation deviceInformation) throws Throwable {
        updateConnectedAccessories(accessorySession.getAccessory().getAddress(), deviceInformation.getDeviceType(), accessorySession.getStateRepository());
    }

    public /* synthetic */ void lambda$updateConnectedAccessories$0$AccessoryBootupService(String str, String str2, Boolean bool) throws Throwable {
        Logger.d("%s : got devicePoweredOn state : %b ", TAG, bool);
        if (bool.booleanValue()) {
            AccessoryBootUpMetadata build = AccessoryBootUpMetadata.newBuilder().setDeviceType(str).setPoweredOnTime(new Date()).build();
            synchronized (this.lock) {
                if (this.downchannelIsConnected) {
                    Logger.d("%s : Logging metric %s, delay %d", TAG, MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_TIME, 0);
                    AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Bootup.ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_TIME, build.getDeviceType(), 0L, null);
                } else {
                    this.connectedAccessories.put(str2, build);
                }
            }
        }
    }
}
