package com.amazon.alexa.accessorykit.interprocess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.interprocess.InterprocessPrivacyModeReceiver;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.reactivestreams.Subscription;
/* loaded from: classes6.dex */
public class InterprocessPrivacyModeEmitter {
    private static final Intent BASE_INTENT = new Intent("com.amazon.alexa.accessorykit.action.privacymode.status").setPackage(AccessoriesFactory.getAppName()).setFlags(268435456);
    private static final String TAG = "InterprocessPrivacyModeEmitter:";
    private boolean activated = false;
    private final PhoneStatePlugin callNotificationPlugin;
    private final Context context;
    private final SessionSupplier sessionSupplier;

    public InterprocessPrivacyModeEmitter(SessionSupplier sessionSupplier, PhoneStatePlugin phoneStatePlugin, Context context) {
        this.sessionSupplier = sessionSupplier;
        this.callNotificationPlugin = phoneStatePlugin;
        this.context = context;
    }

    @VisibleForTesting
    static Intent createIntent(InterprocessPrivacyModeReceiver.PrivacyStatusPayload privacyStatusPayload) {
        Intent intent = new Intent(BASE_INTENT);
        try {
            intent.putExtra("privacyStatusPayloadJson", privacyStatusPayload.toJsonObject().toString());
            return intent;
        } catch (JSONException e) {
            throw new RuntimeException("Caught unexpected JSONException", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Device.DeviceInformation deviceInformationWithHighestId(Set<Device.DeviceInformation> set) {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8.INSTANCE);
    }

    private void emitPrivacyModeState(boolean z, boolean z2, Device.DeviceInformation deviceInformation) {
        InterprocessPrivacyModeReceiver.PrivacyStatusPayload privacyStatusPayload = new InterprocessPrivacyModeReceiver.PrivacyStatusPayload(z, deviceInformation.getDeviceType(), z2);
        Intent createIntent = createIntent(privacyStatusPayload);
        Logger.d("%s sending broadcast with payload %s", TAG, privacyStatusPayload.toString());
        this.context.sendBroadcast(createIntent, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$deviceInformationWithHighestId$8(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$observeActiveCall$7(AtomicReference atomicReference) throws Throwable {
        if (atomicReference.get() != null) {
            ((Disposable) atomicReference.get()).dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sessionIsConnected$3(Pair pair) throws Throwable {
        if (((Boolean) pair.second).booleanValue()) {
            Logger.d("%s emitting call privacy mode enabled: %b", TAG, pair.first);
        }
    }

    @SuppressLint({"CheckResult"})
    private Pair<Subject<Boolean>, Flowable<Boolean>> observeActiveCall() {
        final PublishSubject create = PublishSubject.create();
        final AtomicReference atomicReference = new AtomicReference();
        return new Pair<>(create, create.toFlowable(BackpressureStrategy.BUFFER).doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$THejOWFv0AWFe2C2ogcLJyxzciQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                InterprocessPrivacyModeEmitter.this.lambda$observeActiveCall$6$InterprocessPrivacyModeEmitter(atomicReference, create, (Subscription) obj);
            }
        }).doOnTerminate(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$nD4uQRSO3nOzfc0AonS0vxaEEJE
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InterprocessPrivacyModeEmitter.lambda$observeActiveCall$7(atomicReference);
            }
        }));
    }

    private void observeNewSessions() {
        this.sessionSupplier.addSessionListener(new AccessorySessionListener() { // from class: com.amazon.alexa.accessorykit.interprocess.InterprocessPrivacyModeEmitter.1
            @Override // com.amazon.alexa.accessory.AccessorySessionListener
            @SuppressLint({"CheckResult"})
            public void onAccessorySessionConnected(Accessory accessory) {
                AccessorySession session = InterprocessPrivacyModeEmitter.this.sessionSupplier.getSession(accessory);
                if (session == null) {
                    return;
                }
                InterprocessPrivacyModeEmitter.this.sessionIsConnected(session);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Disposable sessionIsConnected(AccessorySession accessorySession) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Pair<Subject<Boolean>, Flowable<Boolean>> observeActiveCall = observeActiveCall();
        final Subject subject = (Subject) observeActiveCall.first;
        final Flowable flowable = (Flowable) observeActiveCall.second;
        final Flowable doOnTerminate = accessorySession.getStateRepository().query(StateFeature.DEVICE_PRIVACY_MODE_ENABLED).map($$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ.INSTANCE).doOnTerminate(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$pFKDfYoFLNqg9oXM-XWqb0cMyIo
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                ObservableUtils.release(Subject.this);
            }
        });
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk.INSTANCE).flatMapObservable(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$BZGpgEa95e-lV6o0oafio41TBZg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterprocessPrivacyModeEmitter.this.lambda$sessionIsConnected$2$InterprocessPrivacyModeEmitter(doOnTerminate, flowable, atomicBoolean, (Device.DeviceInformation) obj);
            }
        }).subscribe($$Lambda$InterprocessPrivacyModeEmitter$W3Nte2vbpjhGgBObyeSx3D61Rm4.INSTANCE, $$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0.INSTANCE);
    }

    public synchronized void activate() {
        if (!this.activated) {
            this.activated = true;
            observeNewSessions();
        } else {
            throw new IllegalStateException("Already activated");
        }
    }

    public /* synthetic */ Pair lambda$null$1$InterprocessPrivacyModeEmitter(AtomicBoolean atomicBoolean, Device.DeviceInformation deviceInformation, Pair pair) throws Throwable {
        if (!((Boolean) pair.second).booleanValue()) {
            atomicBoolean.set(true);
            return pair;
        }
        emitPrivacyModeState(((Boolean) pair.first).booleanValue(), atomicBoolean.get(), deviceInformation);
        atomicBoolean.set(false);
        return pair;
    }

    public /* synthetic */ void lambda$observeActiveCall$6$InterprocessPrivacyModeEmitter(AtomicReference atomicReference, final PublishSubject publishSubject, Subscription subscription) throws Throwable {
        Flowable subscribeOn = Flowable.concat(this.callNotificationPlugin.getState().toFlowable(), this.callNotificationPlugin.queryState()).map($$Lambda$Za9HdPxc3h1khCJiWnJIs_ZgaA.INSTANCE).map($$Lambda$InterprocessPrivacyModeEmitter$CmtfG9hv2E5Cr3XbbiLaGTt7hg.INSTANCE).distinctUntilChanged().subscribeOn(Schedulers.io());
        publishSubject.getClass();
        atomicReference.set(subscribeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$cLcwLZ97CwBM991g2iGu1xae6GI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PublishSubject.this.onNext((Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$qJbSWG8kOkUj2L6eY1lC5Myi8sY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PublishSubject.this.onError((Throwable) obj);
            }
        }));
    }

    public /* synthetic */ ObservableSource lambda$sessionIsConnected$2$InterprocessPrivacyModeEmitter(Flowable flowable, Flowable flowable2, final AtomicBoolean atomicBoolean, final Device.DeviceInformation deviceInformation) throws Throwable {
        return Flowable.combineLatest(flowable, flowable2, $$Lambda$yH3bAzdeQsfsW6sraJ0UtHN0bo8.INSTANCE).toObservable().map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$FXE6Dx9tVamibnY0F4tMqUsyVpo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterprocessPrivacyModeEmitter.this.lambda$null$1$InterprocessPrivacyModeEmitter(atomicBoolean, deviceInformation, (Pair) obj);
            }
        });
    }
}
