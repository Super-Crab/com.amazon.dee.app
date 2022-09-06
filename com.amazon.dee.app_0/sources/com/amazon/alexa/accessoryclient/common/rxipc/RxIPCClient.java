package com.amazon.alexa.accessoryclient.common.rxipc;

import amazon.speech.simclient.settings.Settings;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.BundleSource;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.query.response.CompletableQueryResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.util.Preconditions;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxIPCClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\b\u0016\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00039:;B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0006H\u0002JQ\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\u000e\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u0019\"\u000e\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002H\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001fH\u0016¢\u0006\u0002\u0010 J-\u0010!\u001a\u00020\"\"\u000e\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010#JY\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\u000e\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u0019\"\u000e\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002H\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001f2\u0006\u0010%\u001a\u00020&H\u0002¢\u0006\u0002\u0010'JQ\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00170)\"\u000e\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u0019\"\u000e\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002H\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001fH\u0016¢\u0006\u0002\u0010*JQ\u0010+\u001a\b\u0012\u0004\u0012\u0002H\u00170,\"\u000e\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u0019\"\u000e\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002H\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001fH\u0016¢\u0006\u0002\u0010-J\u0014\u0010.\u001a\u00020/2\n\u00100\u001a\u00060\u0011j\u0002`\u0012H\u0016J\b\u00101\u001a\u00020/H\u0002J\u0010\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\fH\u0016J \u00104\u001a\u00020/2\u0006\u00105\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0006H\u0002J\u0010\u00107\u001a\u00020/2\u0006\u00108\u001a\u00020\u0001H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "()V", "emitterMap", "", "", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxTypeEmitter;", "ensuredOutputStreamThread", "Ljava/lang/Thread;", "gate", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Landroid/os/Bundle;", JoinPoint.SYNCHRONIZATION_LOCK, "", "outputStream", "releasedReason", "Ljava/lang/Exception;", "Lkotlin/Exception;", "clearEmitter", "uuid", "create", "Lio/reactivex/rxjava3/core/Observable;", "O", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "apiIdentifier", "Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;", "request", "responseTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;)Lio/reactivex/rxjava3/core/Observable;", "createCompletable", "Lio/reactivex/rxjava3/core/Completable;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;)Lio/reactivex/rxjava3/core/Completable;", "createInternal", "rxType", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;)Lio/reactivex/rxjava3/core/Observable;", "createMaybe", "Lio/reactivex/rxjava3/core/Maybe;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;)Lio/reactivex/rxjava3/core/Maybe;", "createSingle", "Lio/reactivex/rxjava3/core/Single;", "(Lcom/amazon/alexa/accessoryclient/common/query/ApiIdentifier;Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;)Lio/reactivex/rxjava3/core/Single;", "dispose", "", "e", "ensureSingleThreadInputStream", "handleBundle", "bundle", "provideEmitterError", "rxTypeEmitter", Settings.ListeningSettings.EXTRA_REASON, "setBundleSink", "bundleSink", "Companion", "RxType", "RxTypeEmitter", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class RxIPCClient implements BundleSink, BundleSource {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "RxIPCClient:";
    private final Map<String, RxTypeEmitter> emitterMap = new HashMap();
    private Thread ensuredOutputStreamThread;
    private final BehaviorSubject<Bundle> gate;
    private final Object lock;
    private BundleSink outputStream;
    private Exception releasedReason;

    /* compiled from: RxIPCClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: RxIPCClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;", "", "(Ljava/lang/String;I)V", "COMPLETABLE", "SINGLE", "MAYBE", "OBSERVABLE", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum RxType {
        COMPLETABLE,
        SINGLE,
        MAYBE,
        OBSERVABLE
    }

    /* compiled from: RxIPCClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0002\u0010\nR\u0015\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxTypeEmitter;", "", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Landroid/os/Bundle;", "type", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;", "calledFromException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lio/reactivex/rxjava3/core/ObservableEmitter;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;Ljava/lang/Exception;)V", "getCalledFromException", "()Ljava/lang/Exception;", "getEmitter", "()Lio/reactivex/rxjava3/core/ObservableEmitter;", "getType", "()Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient$RxType;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class RxTypeEmitter {
        @NotNull
        private final Exception calledFromException;
        @NotNull
        private final ObservableEmitter<Bundle> emitter;
        @NotNull
        private final RxType type;

        public RxTypeEmitter(@NotNull ObservableEmitter<Bundle> emitter, @NotNull RxType type, @NotNull Exception calledFromException) {
            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(calledFromException, "calledFromException");
            this.emitter = emitter;
            this.type = type;
            this.calledFromException = calledFromException;
        }

        @NotNull
        public final Exception getCalledFromException() {
            return this.calledFromException;
        }

        @NotNull
        public final ObservableEmitter<Bundle> getEmitter() {
            return this.emitter;
        }

        @NotNull
        public final RxType getType() {
            return this.type;
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RxIPCEventId.Action.values().length];

        static {
            $EnumSwitchMapping$0[RxIPCEventId.Action.ON_NEXT.ordinal()] = 1;
            $EnumSwitchMapping$0[RxIPCEventId.Action.ON_ERROR.ordinal()] = 2;
            $EnumSwitchMapping$0[RxIPCEventId.Action.ON_COMPLETE.ordinal()] = 3;
        }
    }

    public RxIPCClient() {
        BehaviorSubject<Bundle> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.gate = create;
        this.lock = new Object();
    }

    public static final /* synthetic */ Thread access$getEnsuredOutputStreamThread$p(RxIPCClient rxIPCClient) {
        Thread thread = rxIPCClient.ensuredOutputStreamThread;
        if (thread == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ensuredOutputStreamThread");
        }
        return thread;
    }

    public static final /* synthetic */ BundleSink access$getOutputStream$p(RxIPCClient rxIPCClient) {
        BundleSink bundleSink = rxIPCClient.outputStream;
        if (bundleSink == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputStream");
        }
        return bundleSink;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RxTypeEmitter clearEmitter(String str) {
        Preconditions.Companion.hasLock(this.lock);
        RxTypeEmitter remove = this.emitterMap.remove(str);
        if (remove != null) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("RxIPCClient: Client completed observable sequence ", str, ", remaining observable sequences: ");
            outline115.append(this.emitterMap.size());
            Logger.d(outline115.toString());
        }
        return remove;
    }

    private final <T extends Query.Request<T>, O extends Query.Response<O>> Observable<O> createInternal(final ApiIdentifier apiIdentifier, T t, final BundleTransformer<O> bundleTransformer, RxType rxType) {
        Observable<O> map = this.gate.concatWith(Observable.create(new RxIPCClient$createInternal$observable$1(this, apiIdentifier, t, rxType, new Exception()))).map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient$createInternal$1
            /* JADX WARN: Incorrect return type in method signature: (Landroid/os/Bundle;)TO; */
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final Query.Response mo10358apply(@NotNull Bundle bundle) {
                Intrinsics.checkParameterIsNotNull(bundle, "bundle");
                return (Query.Response) BundleTransformer.this.mo568fromBundle(bundle);
            }
        }).map(new Function<T, R>() { // from class: com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient$createInternal$2
            /* JADX WARN: Incorrect return type in method signature: (TO;)TO; */
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final Query.Response mo10358apply(@NotNull Query.Response response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                boolean areEqual = Intrinsics.areEqual(response.getClass(), ApiIdentifier.this.getResponseClass());
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("apiIdentifier ");
                outline107.append(ApiIdentifier.this);
                outline107.append(" does not support ");
                outline107.append(response.getClass());
                outline107.append(" for response type");
                com.amazon.alexa.accessory.internal.util.Preconditions.precondition(areEqual, outline107.toString());
                return response;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "observable.map { bundle:…   response\n            }");
        return map;
    }

    private final void ensureSingleThreadInputStream() {
        Preconditions.Companion.hasLock(this.lock);
        Thread thread = this.ensuredOutputStreamThread;
        if (thread == null) {
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            this.ensuredOutputStreamThread = currentThread;
            return;
        }
        if (thread == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ensuredOutputStreamThread");
        }
        if (!(!Intrinsics.areEqual(thread, Thread.currentThread()))) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxIPCClient: handleBundle method must be called from thread ");
        Thread thread2 = this.ensuredOutputStreamThread;
        if (thread2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ensuredOutputStreamThread");
        }
        outline107.append(thread2);
        outline107.append(" but was called from ");
        outline107.append(Thread.currentThread());
        throw new IllegalStateException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void provideEmitterError(RxTypeEmitter rxTypeEmitter, String str, String str2) {
        Preconditions.Companion.hasLock(this.lock);
        rxTypeEmitter.getEmitter().onError(new IOException(GeneratedOutlineSupport1.outline76("Error for ", str, RealTimeTextConstants.COLON_SPACE, str2), rxTypeEmitter.getCalledFromException()));
    }

    @NotNull
    public <T extends Query.Request<T>, O extends Query.Response<O>> Observable<O> create(@NotNull ApiIdentifier apiIdentifier, @NotNull T request, @NotNull BundleTransformer<O> responseTransformer) {
        Intrinsics.checkParameterIsNotNull(apiIdentifier, "apiIdentifier");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        return createInternal(apiIdentifier, request, responseTransformer, RxType.OBSERVABLE);
    }

    @NotNull
    public <T extends Query.Request<T>> Completable createCompletable(@NotNull ApiIdentifier apiIdentifier, @NotNull T request) {
        Intrinsics.checkParameterIsNotNull(apiIdentifier, "apiIdentifier");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Completable ignoreElements = createInternal(apiIdentifier, request, CompletableQueryResponse.Transformer.INSTANCE, RxType.COMPLETABLE).ignoreElements();
        Intrinsics.checkExpressionValueIsNotNull(ignoreElements, "createInternal(apiIdenti…        .ignoreElements()");
        return ignoreElements;
    }

    @NotNull
    public <T extends Query.Request<T>, O extends Query.Response<O>> Maybe<O> createMaybe(@NotNull ApiIdentifier apiIdentifier, @NotNull T request, @NotNull BundleTransformer<O> responseTransformer) {
        Intrinsics.checkParameterIsNotNull(apiIdentifier, "apiIdentifier");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Maybe<O> singleElement = createInternal(apiIdentifier, request, responseTransformer, RxType.MAYBE).singleElement();
        Intrinsics.checkExpressionValueIsNotNull(singleElement, "createInternal(apiIdenti…         .singleElement()");
        return singleElement;
    }

    @NotNull
    public <T extends Query.Request<T>, O extends Query.Response<O>> Single<O> createSingle(@NotNull ApiIdentifier apiIdentifier, @NotNull T request, @NotNull BundleTransformer<O> responseTransformer) {
        Intrinsics.checkParameterIsNotNull(apiIdentifier, "apiIdentifier");
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(responseTransformer, "responseTransformer");
        Single<O> singleOrError = createInternal(apiIdentifier, request, responseTransformer, RxType.SINGLE).singleOrError();
        Intrinsics.checkExpressionValueIsNotNull(singleOrError, "createInternal(apiIdenti…         .singleOrError()");
        return singleOrError;
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
    public void dispose(@NotNull Exception e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        synchronized (this.lock) {
            if (this.releasedReason != null) {
                return;
            }
            if (!(e instanceof AbstractAlexaAccessoryClient.UnexpectedlyDisconnectedFromServiceException)) {
                Logger.e("RxIPCClient:: Client was terminated purposefully, releasing.", this.releasedReason);
                this.releasedReason = e;
            }
            HashMap hashMap = new HashMap(this.emitterMap);
            for (String str : hashMap.keySet()) {
                clearEmitter(str);
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                String str2 = (String) entry.getKey();
                RxTypeEmitter rxTypeEmitter = (RxTypeEmitter) entry.getValue();
                if (rxTypeEmitter.getEmitter().isDisposed()) {
                    return;
                }
                provideEmitterError(rxTypeEmitter, str2, e.toString());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
    public void handleBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        com.amazon.alexa.accessoryclient.common.util.Logger.INSTANCE.v(new RxIPCClient$handleBundle$1(bundle));
        RxIPCEvent fromBundle = RxIPCEvent.Companion.fromBundle(bundle);
        synchronized (this.lock) {
            ensureSingleThreadInputStream();
            RxTypeEmitter rxTypeEmitter = this.emitterMap.get(fromBundle.getRxIPCEventId().getUuid());
            if (rxTypeEmitter != null) {
                if (rxTypeEmitter.getEmitter().isDisposed()) {
                    return;
                }
                int i = WhenMappings.$EnumSwitchMapping$0[fromBundle.getRxIPCEventId().getAction().ordinal()];
                if (i == 1) {
                    rxTypeEmitter.getEmitter().onNext(fromBundle.getData());
                } else if (i == 2) {
                    clearEmitter(fromBundle.getRxIPCEventId().getUuid());
                    String uuid = fromBundle.getRxIPCEventId().getUuid();
                    String error = fromBundle.getError();
                    if (error == null) {
                        Intrinsics.throwNpe();
                    }
                    provideEmitterError(rxTypeEmitter, uuid, error);
                } else if (i == 3) {
                    if (rxTypeEmitter.getType() != RxType.OBSERVABLE) {
                        clearEmitter(fromBundle.getRxIPCEventId().getUuid());
                    }
                    rxTypeEmitter.getEmitter().onComplete();
                } else {
                    throw new IllegalStateException("RxIPCClient: Unexpected Action from RxIPCServer for rxIPCEventId " + fromBundle.getRxIPCEventId());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSource
    public void setBundleSink(@NotNull BundleSink bundleSink) {
        Intrinsics.checkParameterIsNotNull(bundleSink, "bundleSink");
        synchronized (this.lock) {
            Logger.d("RxIPCClient: is ready to communicate. Output stream sink has been set.");
            this.outputStream = bundleSink;
            this.gate.onComplete();
            Unit unit = Unit.INSTANCE;
        }
    }
}
