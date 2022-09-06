package com.amazon.alexa.accessoryservice.service.rxipc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.BundleSource;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEvent;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId;
import com.amazon.alexa.accessoryclient.common.transformers.BundleUtils;
import com.amazon.alexa.accessoryclient.common.util.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxIPCServer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J,\u0010\u0014\u001a\u00020\u000e\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0019J\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cJ.\u0010\u001d\u001a\u00020\u000e\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0019H\u0003J,\u0010\u001e\u001a\u00020\u000e\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00150 J,\u0010!\u001a\u00020\u000e\"\u000e\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00150#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "()V", JoinPoint.SYNCHRONIZATION_LOCK, "", "outputStreamBundleSink", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "released", "", "trackedDisposables", "", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "dispose", "", "eventId", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "release", "setBundleSink", "bundleSink", "subscribe", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "eventIds", "observable", "Lio/reactivex/rxjava3/core/Observable;", "subscribeCompletable", "completable", "Lio/reactivex/rxjava3/core/Completable;", "subscribeInternal", "subscribeMaybe", "maybe", "Lio/reactivex/rxjava3/core/Maybe;", "subscribeSingle", "single", "Lio/reactivex/rxjava3/core/Single;", "Companion", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RxIPCServer implements BundleSource {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "RxIPCServer: ";
    private BundleSink outputStreamBundleSink;
    private boolean released;
    private final Map<String, Disposable> trackedDisposables = new HashMap();
    private final Object lock = new Object();

    /* compiled from: RxIPCServer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final /* synthetic */ BundleSink access$getOutputStreamBundleSink$p(RxIPCServer rxIPCServer) {
        BundleSink bundleSink = rxIPCServer.outputStreamBundleSink;
        if (bundleSink == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputStreamBundleSink");
        }
        return bundleSink;
    }

    @SuppressLint({"CheckResult"})
    private final <T extends Query.Response<T>> void subscribeInternal(final RxIPCEventId rxIPCEventId, Observable<T> observable) {
        Preconditions.notNull(rxIPCEventId, "eventIds");
        Preconditions.notNull(observable, "observable");
        PublishSubject create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create<RxIPCEvent>()");
        final Observable<T> concatWith = create.concatWith(observable.map(RxIPCServer$subscribeInternal$observe$1.INSTANCE).map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$observe$2
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final RxIPCEvent mo10358apply(@NotNull Bundle bundle) {
                Intrinsics.checkParameterIsNotNull(bundle, "bundle");
                return new RxIPCEvent(RxIPCEventId.this, bundle);
            }
        }));
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            Disposable disposable = concatWith.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RxIPCEvent>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$1

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$1$1"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$1$1  reason: invalid class name */
                /* loaded from: classes6.dex */
                public static final class AnonymousClass1 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onNextBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Bundle bundle) {
                        super(0);
                        this.$onNextBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  going to send methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onNext ");
                        outline107.append(BundleUtils.bundleToString(this.$onNextBundle));
                        return outline107.toString();
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$1$2"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$1$2  reason: invalid class name */
                /* loaded from: classes6.dex */
                public static final class AnonymousClass2 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onNextBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(Bundle bundle) {
                        super(0);
                        this.$onNextBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  wrote  methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onNext ");
                        outline107.append(BundleUtils.bundleToString(this.$onNextBundle));
                        return outline107.toString();
                    }
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(@NotNull RxIPCEvent rxIPCEvent) {
                    Intrinsics.checkParameterIsNotNull(rxIPCEvent, "rxIPCEvent");
                    Bundle bundle = rxIPCEvent.toBundle(RxIPCEventId.Action.ON_NEXT);
                    Logger.INSTANCE.v(new AnonymousClass1(bundle));
                    RxIPCServer.access$getOutputStreamBundleSink$p(RxIPCServer.this).handleBundle(bundle);
                    Logger.INSTANCE.v(new AnonymousClass2(bundle));
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$2

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$2$1"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$2$1  reason: invalid class name */
                /* loaded from: classes6.dex */
                public static final class AnonymousClass1 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onErrorBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Bundle bundle) {
                        super(0);
                        this.$onErrorBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  going to send methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onError ");
                        outline107.append(BundleUtils.bundleToString(this.$onErrorBundle));
                        return outline107.toString();
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$2$3"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$2$2  reason: invalid class name */
                /* loaded from: classes6.dex */
                public static final class AnonymousClass2 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onErrorBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(Bundle bundle) {
                        super(0);
                        this.$onErrorBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  wrote  methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onNext ");
                        outline107.append(BundleUtils.bundleToString(this.$onErrorBundle));
                        return outline107.toString();
                    }
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(@NotNull Throwable error) {
                    Object obj;
                    Map map;
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    Bundle bundle = new RxIPCEvent(rxIPCEventId, error.toString()).toBundle(RxIPCEventId.Action.ON_ERROR);
                    Logger.INSTANCE.v(new AnonymousClass1(bundle));
                    obj = RxIPCServer.this.lock;
                    synchronized (obj) {
                        map = RxIPCServer.this.trackedDisposables;
                        Disposable disposable2 = (Disposable) map.remove(rxIPCEventId.getUuid());
                    }
                    RxIPCServer.access$getOutputStreamBundleSink$p(RxIPCServer.this).handleBundle(bundle);
                    Logger.INSTANCE.v(new AnonymousClass2(bundle));
                    com.amazon.alexa.accessory.internal.util.Logger.e("RxIPCServer:  IPC call encountered error", error);
                }
            }, new Action() { // from class: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$3

                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$3$1"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$3$1  reason: invalid class name */
                /* loaded from: classes6.dex */
                static final class AnonymousClass1 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onCompleteBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Bundle bundle) {
                        super(0);
                        this.$onCompleteBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  going to send methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onComplete ");
                        outline107.append(BundleUtils.bundleToString(this.$onCompleteBundle));
                        return outline107.toString();
                    }
                }

                /* compiled from: RxIPCServer.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "invoke", "com/amazon/alexa/accessoryservice/service/rxipc/RxIPCServer$subscribeInternal$1$disposable$3$3"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.accessoryservice.service.rxipc.RxIPCServer$subscribeInternal$$inlined$synchronized$lambda$3$2  reason: invalid class name */
                /* loaded from: classes6.dex */
                static final class AnonymousClass2 extends Lambda implements Function0<String> {
                    final /* synthetic */ Bundle $onCompleteBundle;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(Bundle bundle) {
                        super(0);
                        this.$onCompleteBundle = bundle;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    /* renamed from: invoke  reason: collision with other method in class */
                    public final String mo12560invoke() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCServer:  wrote  methodUuid: ");
                        outline107.append(rxIPCEventId.getUuid());
                        outline107.append(" message onNext ");
                        outline107.append(BundleUtils.bundleToString(this.$onCompleteBundle));
                        return outline107.toString();
                    }
                }

                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Object obj;
                    Map map;
                    Bundle bundle = new RxIPCEvent(rxIPCEventId).toBundle(RxIPCEventId.Action.ON_COMPLETE);
                    Logger.INSTANCE.v(new AnonymousClass1(bundle));
                    obj = RxIPCServer.this.lock;
                    synchronized (obj) {
                        map = RxIPCServer.this.trackedDisposables;
                        Disposable disposable2 = (Disposable) map.remove(rxIPCEventId.getUuid());
                    }
                    RxIPCServer.access$getOutputStreamBundleSink$p(RxIPCServer.this).handleBundle(bundle);
                    Logger.INSTANCE.v(new AnonymousClass2(bundle));
                }
            });
            Map<String, Disposable> map = this.trackedDisposables;
            String uuid = rxIPCEventId.getUuid();
            Intrinsics.checkExpressionValueIsNotNull(disposable, "disposable");
            map.put(uuid, disposable);
            Unit unit = Unit.INSTANCE;
            create.onComplete();
        }
    }

    public final void dispose(@NotNull RxIPCEventId eventId) {
        Intrinsics.checkParameterIsNotNull(eventId, "eventId");
        Preconditions.notNull(eventId, "eventIds");
        synchronized (this.lock) {
            com.amazon.alexa.accessory.internal.util.Logger.d("RxIPCServer:  disposing RxIPCEventId due to downstream dispose: " + eventId);
            ObservableUtils.dispose(this.trackedDisposables.remove(eventId.getUuid()));
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void release() {
        com.amazon.alexa.accessory.internal.util.Logger.d("RxIPCServer:  release() called");
        synchronized (this.lock) {
            if (this.released) {
                return;
            }
            this.released = true;
            for (Disposable disposable : this.trackedDisposables.values()) {
                ObservableUtils.dispose(disposable);
            }
            this.trackedDisposables.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSource
    public void setBundleSink(@NotNull BundleSink bundleSink) {
        Intrinsics.checkParameterIsNotNull(bundleSink, "bundleSink");
        synchronized (this.lock) {
            this.outputStreamBundleSink = bundleSink;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final <T extends Query.Response<T>> void subscribe(@NotNull RxIPCEventId eventIds, @NotNull Observable<T> observable) {
        Intrinsics.checkParameterIsNotNull(eventIds, "eventIds");
        Intrinsics.checkParameterIsNotNull(observable, "observable");
        subscribeInternal(eventIds, observable);
    }

    public final void subscribeCompletable(@NotNull RxIPCEventId eventIds, @NotNull Completable completable) {
        Intrinsics.checkParameterIsNotNull(eventIds, "eventIds");
        Intrinsics.checkParameterIsNotNull(completable, "completable");
        Observable observable = completable.toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "completable.toObservable…mpletableQueryResponse>()");
        subscribeInternal(eventIds, observable);
    }

    public final <T extends Query.Response<T>> void subscribeMaybe(@NotNull RxIPCEventId eventIds, @NotNull Maybe<T> maybe) {
        Intrinsics.checkParameterIsNotNull(eventIds, "eventIds");
        Intrinsics.checkParameterIsNotNull(maybe, "maybe");
        Observable<T> observable = maybe.toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "maybe.toObservable()");
        subscribeInternal(eventIds, observable);
    }

    public final <T extends Query.Response<T>> void subscribeSingle(@NotNull RxIPCEventId eventIds, @NotNull Single<T> single) {
        Intrinsics.checkParameterIsNotNull(eventIds, "eventIds");
        Intrinsics.checkParameterIsNotNull(single, "single");
        Observable<T> observable = single.toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "single.toObservable()");
        subscribeInternal(eventIds, observable);
    }
}
