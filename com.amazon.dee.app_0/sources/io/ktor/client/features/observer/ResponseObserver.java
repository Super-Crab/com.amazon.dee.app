package io.ktor.client.features.observer;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.HttpClient;
import io.ktor.client.features.HttpClientFeature;
import io.ktor.client.response.HttpReceivePipeline;
import io.ktor.client.response.HttpResponse;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ResponseObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0002\t\nB,\u0012\"\u0010\u0002\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0007R/\u0010\u0002\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lio/ktor/client/features/observer/ResponseObserver;", "", "responseHandler", "Lkotlin/Function2;", "Lio/ktor/client/response/HttpResponse;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "Config", "Feature", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ResponseObserver {
    public static final Feature Feature = new Feature(null);
    @NotNull
    private static final AttributeKey<ResponseObserver> key = new AttributeKey<>("BodyInterceptor");
    private final Function2<HttpResponse, Continuation<? super Unit>, Object> responseHandler;

    /* compiled from: ResponseObserver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J2\u0010\r\u001a\u00020\u00072\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u000bR;\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0080\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/client/features/observer/ResponseObserver$Config;", "", "()V", "responseHandler", "Lkotlin/Function2;", "Lio/ktor/client/response/HttpResponse;", "Lkotlin/coroutines/Continuation;", "", "getResponseHandler$ktor_client_core", "()Lkotlin/jvm/functions/Function2;", "setResponseHandler$ktor_client_core", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "onResponse", "block", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Config {
        @NotNull
        private Function2<? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> responseHandler = new ResponseObserver$Config$responseHandler$1(null);

        @NotNull
        public final Function2<HttpResponse, Continuation<? super Unit>, Object> getResponseHandler$ktor_client_core() {
            return this.responseHandler;
        }

        public final void onResponse(@NotNull Function2<? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            this.responseHandler = block;
        }

        public final void setResponseHandler$ktor_client_core(@NotNull Function2<? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
            this.responseHandler = function2;
        }
    }

    /* compiled from: ResponseObserver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/features/observer/ResponseObserver$Feature;", "Lio/ktor/client/features/HttpClientFeature;", "Lio/ktor/client/features/observer/ResponseObserver$Config;", "Lio/ktor/client/features/observer/ResponseObserver;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Feature implements HttpClientFeature<Config, ResponseObserver> {
        private Feature() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<ResponseObserver> getKey() {
            return ResponseObserver.key;
        }

        public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull ResponseObserver feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            scope.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getBefore(), new ResponseObserver$Feature$install$1(scope, feature, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public ResponseObserver mo10282prepare(@NotNull Function1<? super Config, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            Config config = new Config();
            block.mo12165invoke(config);
            return new ResponseObserver(config.getResponseHandler$ktor_client_core());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseObserver(@NotNull Function2<? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> responseHandler) {
        Intrinsics.checkParameterIsNotNull(responseHandler, "responseHandler");
        this.responseHandler = responseHandler;
    }
}
