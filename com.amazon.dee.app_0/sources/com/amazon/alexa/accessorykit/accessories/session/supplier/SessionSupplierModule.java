package com.amazon.alexa.accessorykit.accessories.session.supplier;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessoryclient.common.api.AccessoryTransportChange;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.accessories.session.supplier.SessionFailedEvent;
import com.amazon.alexa.accessorykit.accessories.session.supplier.SessionTransportChangedEvent;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes6.dex */
public final class SessionSupplierModule {
    private static final String TAG = "SessionSupplierModule";
    private final MapModelTransformer<Accessory> accessoryTransformer;
    private final SessionSupplier clientSessionSupplier;
    private final ContainerProvider containerProvider;
    private final RxRN rxRN;
    private final MapModelTransformer<SessionFailedEvent> sessionFailedEventTransformer;
    private final MapModelTransformer<SessionTransportChangedEvent> sessionTransportChangedEventTransformer;

    public SessionSupplierModule(RxRN rxRN, ContainerProvider containerProvider, SessionSupplier sessionSupplier) {
        Preconditions.notNull(rxRN, "rxRn");
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.rxRN = rxRN;
        this.containerProvider = containerProvider;
        this.clientSessionSupplier = sessionSupplier;
        this.accessoryTransformer = new AccessoryModelTransformer(containerProvider);
        this.sessionFailedEventTransformer = new SessionFailedEvent.Transformer(containerProvider, this.accessoryTransformer);
        this.sessionTransportChangedEventTransformer = new SessionTransportChangedEvent.Transformer(containerProvider, this.accessoryTransformer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WritableArray lambda$getSessionIdentifiers$6(WritableArray writableArray, List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            writableArray.pushString(((Accessory) it2.next()).getAddress());
        }
        return writableArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getSessionIdentifiers$8(Promise promise, WritableArray writableArray, Throwable th) throws Throwable {
        promise.resolve(writableArray);
        Logger.e("%s CRITICAL error in getSessionIdentifiers", th, TAG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hasSession$3(Promise promise, Throwable th) throws Throwable {
        Logger.e("%s CRITICAL error in hasSession", th, TAG);
        promise.resolve(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hasSessions$5(Promise promise, Throwable th) throws Throwable {
        Logger.e("%s CRITICAL error in hasSessions", th, TAG);
        promise.resolve(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SessionFailedEvent lambda$querySessionFailed$11(Accessory accessory) throws Throwable {
        return new SessionFailedEvent(accessory, new Throwable("Session Failed!"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SessionTransportChangedEvent lambda$querySessionTransportChanged$12(AccessoryTransportChange accessoryTransportChange) throws Throwable {
        return new SessionTransportChangedEvent(accessoryTransportChange.getOldAccessory(), accessoryTransportChange.getOldTransport(), accessoryTransportChange.getNewAccessory(), accessoryTransportChange.getNewTransport());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$releaseSession$10(Promise promise, Throwable th) throws Throwable {
        Logger.e("Problem in releasing session", th);
        promise.resolve(null);
    }

    public void connectSession(ReadableMap readableMap, final Promise promise) {
        try {
            Completable createAndConnectSessionAwaitConnection = this.clientSessionSupplier.createAndConnectSessionAwaitConnection(this.accessoryTransformer.mo626transform(readableMap));
            Action action = new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$ZNrWqQsP0FuprDnBrfqOmelW1F8
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Promise.this.resolve(null);
                }
            };
            promise.getClass();
            createAndConnectSessionAwaitConnection.subscribe(action, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$uy64BVvzETLGSEBSP5-7B5ZCU4o
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Promise.this.reject((Throwable) obj);
                }
            });
        } catch (ParseException e) {
            promise.reject(e);
        }
    }

    public void getSessionIdentifiers(final Promise promise) {
        final WritableArray array = this.containerProvider.getArray();
        this.clientSessionSupplier.getActiveAccessories().map(new Function() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$H7caghIpUoZCE9xkMzxQxE1reBU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                WritableArray writableArray = WritableArray.this;
                SessionSupplierModule.lambda$getSessionIdentifiers$6(writableArray, (List) obj);
                return writableArray;
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$eQ-ZvdzFtnj1qLmeJDuWZE0wt6w
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((WritableArray) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$d1JVXZu0OTMUGRHS6IFRQ1-igi8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSupplierModule.lambda$getSessionIdentifiers$8(Promise.this, array, (Throwable) obj);
            }
        });
    }

    public void hasSession(String str, final Promise promise) {
        this.clientSessionSupplier.getSession(str).queryConnectionStatus().map($$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$JojJHdQsLjESb4MK587HwUtVf3E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$RCrWGck9Iei5ItrdXEBr7cA-VaU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSupplierModule.lambda$hasSession$3(Promise.this, (Throwable) obj);
            }
        });
    }

    public void hasSessions(final Promise promise) {
        this.clientSessionSupplier.hasActiveSessions().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$N31Mj89xzP10mhh5wso-PKDEIVo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$CcH4BVlOeU67L8hDiuNsyQk50u4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSupplierModule.lambda$hasSessions$5(Promise.this, (Throwable) obj);
            }
        });
    }

    public void querySessionConnected(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.accessoryTransformer, this.clientSessionSupplier.observeSessionConnected());
    }

    public void querySessionCreated(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.accessoryTransformer, this.clientSessionSupplier.observeSessionCreated());
    }

    public void querySessionDisconnected(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.accessoryTransformer, this.clientSessionSupplier.observeSessionDisconnected());
    }

    public void querySessionFailed(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), (MapModelTransformer) this.sessionFailedEventTransformer, (Observable) this.clientSessionSupplier.observeSessionFailed().map($$Lambda$SessionSupplierModule$_VfICXfYwwF5iPKpHhW_g6zQQTA.INSTANCE));
    }

    public void querySessionReleased(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.accessoryTransformer, this.clientSessionSupplier.observeSessionReleased());
    }

    public void querySessionTransportChanged(ReadableMap readableMap) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), (MapModelTransformer) this.sessionTransportChangedEventTransformer, (Observable) this.clientSessionSupplier.observeSessionTransportChanged().map($$Lambda$SessionSupplierModule$hDDEAUj1Q9qsFWOUn5RWE9sRtaA.INSTANCE));
    }

    public void releaseSession(String str, final Promise promise) {
        this.clientSessionSupplier.getSession(str).release().subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$2YyCXzsOSfoycgMlhKRhkLkPrPg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(null);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$9ONIcRgACWo5AQzp_afjX7wk99w
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SessionSupplierModule.lambda$releaseSession$10(Promise.this, (Throwable) obj);
            }
        });
    }
}
