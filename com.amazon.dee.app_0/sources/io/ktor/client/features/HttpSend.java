package io.ktor.client.features;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorExperimentalAPI;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpSend.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J=\u0010\u000f\u001a\u00020\u00102-\u0010\u0011\u001a)\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\u000bø\u0001\u0000¢\u0006\u0002\u0010\u0012R>\u0010\u0005\u001a/\u0012+\u0012)\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\u000b0\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/client/features/HttpSend;", "", "maxSendCount", "", "(I)V", "interceptors", "", "Lkotlin/Function3;", "Lio/ktor/client/features/Sender;", "Lio/ktor/client/call/HttpClientCall;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "getMaxSendCount", "()I", "setMaxSendCount", "intercept", "", "block", "(Lkotlin/jvm/functions/Function3;)V", "DefaultSender", "Feature", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpSend {
    public static final Feature Feature = new Feature(null);
    @NotNull
    private static final AttributeKey<HttpSend> key = new AttributeKey<>("HttpSend");
    private final List<Function3<Sender, HttpClientCall, Continuation<? super HttpClientCall>, Object>> interceptors;
    private int maxSendCount;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HttpSend.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/ktor/client/features/HttpSend$DefaultSender;", "Lio/ktor/client/features/Sender;", "maxSendCount", "", "client", "Lio/ktor/client/HttpClient;", "(ILio/ktor/client/HttpClient;)V", "sentCount", "execute", "Lio/ktor/client/call/HttpClientCall;", "requestBuilder", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultSender implements Sender {
        private final HttpClient client;
        private final int maxSendCount;
        private int sentCount;

        public DefaultSender(int i, @NotNull HttpClient client) {
            Intrinsics.checkParameterIsNotNull(client, "client");
            this.maxSendCount = i;
            this.client = client;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
        @Override // io.ktor.client.features.Sender
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object execute(@org.jetbrains.annotations.NotNull io.ktor.client.request.HttpRequestBuilder r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof io.ktor.client.features.HttpSend$DefaultSender$execute$1
                if (r0 == 0) goto L13
                r0 = r6
                io.ktor.client.features.HttpSend$DefaultSender$execute$1 r0 = (io.ktor.client.features.HttpSend$DefaultSender$execute$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                io.ktor.client.features.HttpSend$DefaultSender$execute$1 r0 = new io.ktor.client.features.HttpSend$DefaultSender$execute$1
                r0.<init>(r4, r6)
            L18:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L3f
                if (r2 != r3) goto L37
                java.lang.Object r5 = r0.L$1
                io.ktor.client.request.HttpRequestBuilder r5 = (io.ktor.client.request.HttpRequestBuilder) r5
                java.lang.Object r5 = r0.L$0
                io.ktor.client.features.HttpSend$DefaultSender r5 = (io.ktor.client.features.HttpSend.DefaultSender) r5
                boolean r5 = r6 instanceof kotlin.Result.Failure
                if (r5 != 0) goto L32
                goto L63
            L32:
                kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
                java.lang.Throwable r5 = r6.exception
                throw r5
            L37:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L3f:
                boolean r2 = r6 instanceof kotlin.Result.Failure
                if (r2 != 0) goto L84
                int r6 = r4.sentCount
                int r2 = r4.maxSendCount
                if (r6 >= r2) goto L70
                int r6 = r6 + r3
                r4.sentCount = r6
                io.ktor.client.HttpClient r6 = r4.client
                io.ktor.client.request.HttpSendPipeline r6 = r6.getSendPipeline()
                java.lang.Object r2 = r5.getBody()
                r0.L$0 = r4
                r0.L$1 = r5
                r0.label = r3
                java.lang.Object r6 = r6.execute(r5, r2, r0)
                if (r6 != r1) goto L63
                return r1
            L63:
                if (r6 == 0) goto L68
                io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
                return r6
            L68:
                kotlin.TypeCastException r5 = new kotlin.TypeCastException
                java.lang.String r6 = "null cannot be cast to non-null type io.ktor.client.call.HttpClientCall"
                r5.<init>(r6)
                throw r5
            L70:
                io.ktor.client.features.SendCountExceedException r5 = new io.ktor.client.features.SendCountExceedException
                java.lang.String r6 = "Max send count "
                java.lang.StringBuilder r6 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r6)
                int r0 = r4.maxSendCount
                java.lang.String r1 = " exceeded"
                java.lang.String r6 = com.android.tools.r8.GeneratedOutlineSupport1.outline86(r6, r0, r1)
                r5.<init>(r6)
                throw r5
            L84:
                kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
                java.lang.Throwable r5 = r6.exception
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.HttpSend.DefaultSender.execute(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* compiled from: HttpSend.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J!\u0010\r\u001a\u00020\u00022\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/features/HttpSend$Feature;", "Lio/ktor/client/features/HttpClientFeature;", "Lio/ktor/client/features/HttpSend;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Feature implements HttpClientFeature<HttpSend, HttpSend> {
        private Feature() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<HttpSend> getKey() {
            return HttpSend.key;
        }

        public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull HttpSend feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getSend(), new HttpSend$Feature$install$1(feature, scope, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public HttpSend mo10282prepare(@NotNull Function1<? super HttpSend, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            HttpSend httpSend = new HttpSend(0, 1, null);
            block.mo12165invoke(httpSend);
            return httpSend;
        }
    }

    public HttpSend() {
        this(0, 1, null);
    }

    public HttpSend(int i) {
        this.maxSendCount = i;
        this.interceptors = new ArrayList();
    }

    public final int getMaxSendCount() {
        return this.maxSendCount;
    }

    public final void intercept(@NotNull Function3<? super Sender, ? super HttpClientCall, ? super Continuation<? super HttpClientCall>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.interceptors.add(block);
    }

    public final void setMaxSendCount(int i) {
        this.maxSendCount = i;
    }

    public /* synthetic */ HttpSend(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 20 : i);
    }
}
