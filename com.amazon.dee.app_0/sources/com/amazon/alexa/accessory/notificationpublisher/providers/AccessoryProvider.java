package com.amazon.alexa.accessory.notificationpublisher.providers;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.consumption.ConsumptionEngine;
import com.amazon.alexa.accessory.notificationpublisher.easteregg.EasterEggAudioManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.AlexaServiceHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class AccessoryProvider {
    private static final String TAG = "AccessoryProvider";
    private static String disconnectedAccessoryDeviceType;
    @VisibleForTesting
    static List<Accessory> connectedAccessoryList = Collections.synchronizedList(new ArrayList());
    static Map<String, String> accessoryDeviceTypeMap = new HashMap();
    private static AtomicBoolean accessoryDndEnabled = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass6 implements Consumer<StateOuterClass.State> {
        final /* synthetic */ StateRepository val$stateRepository;

        AnonymousClass6(StateRepository stateRepository) {
            this.val$stateRepository = stateRepository;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$accept$0() throws Throwable {
            Log.i(AccessoryProvider.TAG, "Easter egg state is reset.");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_STATE_RESET_SUCCESSFUL);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$accept$1(Throwable th) throws Throwable {
            String str = AccessoryProvider.TAG;
            Log.e(str, "Reset failed - on Error - " + th);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.EASTER_EGG_STATE_RESET_ERROR);
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(StateOuterClass.State state) {
            if (state.getBoolean()) {
                AudioFocusManager.easterEggAudioTriggered = true;
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.RECEIVED_EASTER_EGG_EVENT);
                this.val$stateRepository.set(StateOuterClass.State.newBuilder().setFeature(StateFeature.EASTER_EGG_ENABLED.toInteger()).setBoolean(false).mo10084build()).subscribe($$Lambda$AccessoryProvider$6$B_UDCuZBcmbZWYeWK3UokxRBeDA.INSTANCE, $$Lambda$AccessoryProvider$6$HaWeqgHjeEEdBcTHmdhpQZRUvs0.INSTANCE);
                Log.i(AccessoryProvider.TAG, "AccessoryClient: subscribeToEggStateFeature - Accessory Easter Egg Audio is triggered, Starting the Audio.");
                EasterEggAudioManager.getInstance().requestAudioFocus();
            }
        }
    }

    /* loaded from: classes.dex */
    public interface IssueMediaControlCallback {
        void onMediaControlIssued(boolean z, Throwable th, Media.MediaControl mediaControl);
    }

    private AccessoryProvider() {
    }

    @Nullable
    public static Accessory getAccessory() {
        if (connectedAccessoryList.size() == 0) {
            return null;
        }
        return (Accessory) GeneratedOutlineSupport1.outline24(connectedAccessoryList, -1);
    }

    public static String getAccessoryDeviceType() {
        if (getAccessory() == null) {
            return null;
        }
        return accessoryDeviceTypeMap.get(getAccessory().getAddress());
    }

    public static List<Accessory> getConnectedAccessoryList() {
        return Collections.unmodifiableList(connectedAccessoryList);
    }

    public static String getLastDisconnectedAccessoryDeviceType() {
        return disconnectedAccessoryDeviceType;
    }

    public static boolean issueMediaControlToAccessory(Media.MediaControl mediaControl) {
        return issueMediaControlToAccessory(mediaControl, null);
    }

    @VisibleForTesting
    public static void subscribeToAccessoryStateChanges() {
        DependencyProvider.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.1
            @Override // java.lang.Runnable
            public void run() {
                AccessoryProvider.subscribeToDndStateFeature();
                AccessoryProvider.subscribeToEasterEggStateFeature();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void subscribeToDndStateFeature() {
        Log.d(TAG, "AccessoryClient: subscribeToDndStateFeature");
        Accessory accessory = getAccessory();
        if (accessory == null) {
            Log.e(TAG, "AccessoryClient: subscribeToDndStateFeature - Accessory session is null");
            return;
        }
        final String accessoryDeviceType = getAccessoryDeviceType(accessory);
        DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getStateRepository().query(StateFeature.DEVICE_DND_ENABLED).subscribeOn(Schedulers.computation()).subscribe(new Consumer<StateOuterClass.State>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(StateOuterClass.State state) {
                AccessoryProvider.accessoryDndEnabled.set(state.getBoolean());
                DistractionModeProvider.setNoDistractionMode(accessoryDeviceType, AccessoryProvider.accessoryDndEnabled.get());
                if (state.getBoolean()) {
                    Log.i(AccessoryProvider.TAG, "AccessoryClient: subscribeToDndStateFeature - Accessory DND is on, Calling stopAlexa");
                    AlexaServiceHelper.stopAlexa();
                }
                String str = AccessoryProvider.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: subscribeToDndStateFeature - onNext. Accessory DND = ");
                outline107.append(AccessoryProvider.accessoryDndEnabled.get());
                Log.i(str, outline107.toString());
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) {
                String str = AccessoryProvider.TAG;
                Log.e(str, "AccessoryClient: subscribeToDndStateFeature - onError - " + th);
            }
        }, new Action() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.5
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void subscribeToEasterEggStateFeature() {
        Log.d(TAG, "AccessoryClient: subscribeToEggStateFeature");
        Accessory accessory = getAccessory();
        if (accessory == null) {
            Log.e(TAG, "AccessoryClient: subscribeToEggStateFeature - Accessory session is null");
            return;
        }
        StateRepository stateRepository = DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getStateRepository();
        EasterEggAudioManager.getInstance().downloadAndSaveAudio();
        stateRepository.query(StateFeature.EASTER_EGG_ENABLED).subscribeOn(Schedulers.computation()).subscribe(new AnonymousClass6(stateRepository), new Consumer<Throwable>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) {
                String str = AccessoryProvider.TAG;
                Log.e(str, "AccessoryClient: subscribeToEggStateFeature - onError - " + th);
            }
        }, new Action() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.8
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() {
            }
        });
    }

    public static void updateAccessory(Accessory accessory, String str, boolean z) {
        String str2 = TAG;
        Log.d(str2, "updateAccessory - connected = " + z);
        synchronized (connectedAccessoryList) {
            if (z) {
                if (!connectedAccessoryList.contains(accessory)) {
                    connectedAccessoryList.add(accessory);
                }
                if (accessory != null && accessory.getAddress() != null) {
                    accessoryDeviceTypeMap.put(accessory.getAddress(), str);
                }
                subscribeToAccessoryStateChanges();
            } else {
                connectedAccessoryList.remove(accessory);
                disconnectedAccessoryDeviceType = str;
            }
            if ("A3HVREY4JWAZ6K".equalsIgnoreCase(str)) {
                ConsumptionEngine.getInstance().postEventMessage(2, 4, 99, null);
            }
        }
    }

    public static boolean issueMediaControlToAccessory(final Media.MediaControl mediaControl, @Nullable final IssueMediaControlCallback issueMediaControlCallback) {
        Accessory accessory = getAccessory();
        if (accessory == null) {
            Log.w(TAG, "AccessoryClient: issueMediaControlToAccessory - No connected accessory");
            return false;
        }
        Log.i(TAG, String.format(Locale.US, "AccessoryClient: issueMediaControlToAccessory - Sending %d media control", Integer.valueOf(mediaControl.getNumber())));
        DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getMediaRepository().issueMediaControl(mediaControl).subscribe(new CompletableObserver() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider.2
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                Log.i(AccessoryProvider.TAG, "AccessoryClient: issueMediaControlToAccessory - Issue Media Control - onComplete");
                IssueMediaControlCallback issueMediaControlCallback2 = IssueMediaControlCallback.this;
                if (issueMediaControlCallback2 != null) {
                    issueMediaControlCallback2.onMediaControlIssued(true, null, mediaControl);
                }
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                String str = AccessoryProvider.TAG;
                Log.i(str, "AccessoryClient: issueMediaControlToAccessory - Issue Media Control - onError - " + th);
                IssueMediaControlCallback issueMediaControlCallback2 = IssueMediaControlCallback.this;
                if (issueMediaControlCallback2 != null) {
                    issueMediaControlCallback2.onMediaControlIssued(false, th, mediaControl);
                }
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                Log.i(AccessoryProvider.TAG, "AccessoryClient: issueMediaControlToAccessory - Issue Media Control - onSubscribe");
            }
        });
        return true;
    }

    public static String getAccessoryDeviceType(Accessory accessory) {
        return accessoryDeviceTypeMap.get(accessory.getAddress());
    }
}
