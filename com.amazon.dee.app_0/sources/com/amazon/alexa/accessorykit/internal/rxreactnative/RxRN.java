package com.amazon.alexa.accessorykit.internal.rxreactnative;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.fitness.metrics.SubComponents;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public class RxRN {
    private static final String TAG = "RxRN:";
    private final ContainerProvider containerProvider;
    private final Map<String, Disposable> disposableMap;
    private final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter;
    private final Object lock;

    /* loaded from: classes6.dex */
    public static class RxRNCleanupListener implements LifecycleEventListener {
        private static final String TAG = "RxRNCleanupListener:";
        private final RxRN rxRN;

        public RxRNCleanupListener(RxRN rxRN) {
            this.rxRN = rxRN;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            Logger.d("%s onHostDestroy", TAG);
            this.rxRN.disposeAll();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }
    }

    public RxRN(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, ContainerProvider containerProvider) {
        Preconditions.notNull(rCTDeviceEventEmitter, SubComponents.EVENT_EMITTER);
        this.disposableMap = new LinkedHashMap();
        this.eventEmitter = rCTDeviceEventEmitter;
        this.containerProvider = containerProvider;
        this.lock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ RxRNEvent lambda$subscribe$0(RxRNEventId rxRNEventId, WritableArray writableArray) throws Throwable {
        return new RxRNEvent(rxRNEventId.getOnNextEventId(), writableArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ RxRNEvent lambda$subscribe$1(RxRNEventId rxRNEventId, WritableMap writableMap) throws Throwable {
        return new RxRNEvent(rxRNEventId.getOnNextEventId(), writableMap);
    }

    public void dispose(RxRNEventId rxRNEventId) {
        Disposable remove;
        Preconditions.notNull(rxRNEventId, "eventIds");
        synchronized (this.lock) {
            remove = this.disposableMap.remove(rxRNEventId.getOnDisposeEventId());
        }
        ObservableUtils.dispose(remove);
    }

    public void disposeAll() {
        LinkedHashMap linkedHashMap;
        Logger.d("%s disposeAll", TAG);
        synchronized (this.lock) {
            linkedHashMap = new LinkedHashMap(this.disposableMap);
            this.disposableMap.clear();
        }
        for (Disposable disposable : linkedHashMap.values()) {
            ObservableUtils.dispose(disposable);
        }
    }

    public void error(RxRNEventId rxRNEventId, Throwable th) {
        subscribe(rxRNEventId, Observable.error(th));
    }

    public /* synthetic */ void lambda$subscribe$2$RxRN(RxRNEvent rxRNEvent) throws Throwable {
        this.eventEmitter.emit(RxRNEvent.EVENT_ID, rxRNEvent.data(this.containerProvider));
    }

    public /* synthetic */ void lambda$subscribe$3$RxRN(RxRNEventId rxRNEventId, Throwable th) throws Throwable {
        synchronized (this.lock) {
            if (this.disposableMap.remove(rxRNEventId.getOnDisposeEventId()) == null) {
                return;
            }
            this.eventEmitter.emit(RxRNEvent.EVENT_ID, new RxRNEvent(rxRNEventId.getOnErrorEventId(), th).data(this.containerProvider));
        }
    }

    public /* synthetic */ void lambda$subscribe$4$RxRN(RxRNEventId rxRNEventId) throws Throwable {
        synchronized (this.lock) {
            if (this.disposableMap.remove(rxRNEventId.getOnDisposeEventId()) == null) {
                return;
            }
            this.eventEmitter.emit(RxRNEvent.EVENT_ID, new RxRNEvent(rxRNEventId.getOnCompleteEventId()).data(this.containerProvider));
        }
    }

    public <T> void subscribe(final RxRNEventId rxRNEventId, final ArrayModelTransformer<T> arrayModelTransformer, Observable<T> observable) {
        arrayModelTransformer.getClass();
        subscribe(rxRNEventId, observable.map(new Function() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$iyuOlIVGp0ALpp4DtFD0QipcMj0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ArrayModelTransformer.this.transformToArray(obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$RxRN$5Qd4BYdCbl3-m9kM2Qx6VyFPPRE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RxRN.lambda$subscribe$0(RxRNEventId.this, (WritableArray) obj);
            }
        }));
    }

    public <T> void subscribe(final RxRNEventId rxRNEventId, final MapModelTransformer<T> mapModelTransformer, Observable<T> observable) {
        mapModelTransformer.getClass();
        subscribe(rxRNEventId, observable.map(new Function() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$jEiR-p5bEailqDYk3Hj0PCvLqYU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MapModelTransformer.this.transformToMap(obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$RxRN$JoR-axgqqy-oeXGP5rgkCWYwWOU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return RxRN.lambda$subscribe$1(RxRNEventId.this, (WritableMap) obj);
            }
        }));
    }

    /* loaded from: classes6.dex */
    public static final class RxRNEvent {
        private static final String DATA = "data";
        public static final String EVENT_ID = "RxRN";
        private static final String ID = "id";
        private final WritableArray dataArray;
        private final WritableMap dataMap;
        private final String dataString;
        private final String id;

        public RxRNEvent(String str, WritableMap writableMap) {
            Preconditions.notNull(str, "id");
            Preconditions.notNull(writableMap, "dataMap");
            this.id = str;
            this.dataMap = writableMap;
            this.dataArray = null;
            this.dataString = null;
        }

        public WritableMap data(ContainerProvider containerProvider) {
            WritableMap map = containerProvider.getMap();
            map.putString("id", this.id);
            WritableMap writableMap = this.dataMap;
            if (writableMap != null) {
                map.putMap("data", writableMap);
                return map;
            }
            WritableArray writableArray = this.dataArray;
            if (writableArray != null) {
                map.putArray("data", writableArray);
                return map;
            }
            String str = this.dataString;
            if (str != null) {
                map.putString("data", str);
            }
            return map;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || RxRNEvent.class != obj.getClass()) {
                return false;
            }
            RxRNEvent rxRNEvent = (RxRNEvent) obj;
            return Objects.equals(this.id, rxRNEvent.id) && Objects.equals(this.dataString, rxRNEvent.dataString) && Objects.equals(this.dataArray, rxRNEvent.dataArray) && Objects.equals(this.dataMap, rxRNEvent.dataMap);
        }

        public int hashCode() {
            return Objects.hash(this.id, this.dataString, this.dataArray, this.dataMap);
        }

        public RxRNEvent(String str, WritableArray writableArray) {
            Preconditions.notNull(str, "id");
            Preconditions.notNull(writableArray, "dataArray");
            this.id = str;
            this.dataArray = writableArray;
            this.dataMap = null;
            this.dataString = null;
        }

        public RxRNEvent(String str, Throwable th) {
            Preconditions.notNull(str, "id");
            Preconditions.notNull(th, "dataError");
            this.id = str;
            this.dataMap = null;
            this.dataArray = null;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            this.dataString = stringWriter.toString();
        }

        public RxRNEvent(String str) {
            Preconditions.notNull(str, "id");
            this.id = str;
            this.dataMap = null;
            this.dataArray = null;
            this.dataString = null;
        }
    }

    @SuppressLint({"CheckResult"})
    private void subscribe(final RxRNEventId rxRNEventId, Observable<RxRNEvent> observable) {
        Preconditions.notNull(rxRNEventId, "eventIds");
        Preconditions.notNull(observable, "observable");
        PublishSubject create = PublishSubject.create();
        Disposable subscribe = create.concatWith(observable).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$RxRN$WSlPZ3-5zl5bVrI8Ha41HZXxHNI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                RxRN.this.lambda$subscribe$2$RxRN((RxRN.RxRNEvent) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$RxRN$zSq5D1Yw86RzXoGb1E6_RzoV0Yo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                RxRN.this.lambda$subscribe$3$RxRN(rxRNEventId, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.alexa.accessorykit.internal.rxreactnative.-$$Lambda$RxRN$nlIcY_-JmOS-dsemyqQkqaAKHkM
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                RxRN.this.lambda$subscribe$4$RxRN(rxRNEventId);
            }
        });
        synchronized (this.lock) {
            this.disposableMap.put(rxRNEventId.getOnDisposeEventId(), subscribe);
        }
        create.onComplete();
    }
}
