package io.ktor.client.features;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRedirect.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"Lio/ktor/client/features/HttpRedirect;", "", "()V", "Feature", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpRedirect {
    public static final Feature Feature = new Feature(null);
    @NotNull
    private static final AttributeKey<HttpRedirect> key = new AttributeKey<>("HttpRedirect");

    /* compiled from: HttpRedirect.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J!\u0010\r\u001a\u00020\u00032\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f¢\u0006\u0002\b\u0010H\u0016J\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/client/features/HttpRedirect$Feature;", "Lio/ktor/client/features/HttpClientFeature;", "", "Lio/ktor/client/features/HttpRedirect;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "handleCall", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/features/Sender;", "origin", "(Lio/ktor/client/features/Sender;Lio/ktor/client/call/HttpClientCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Feature implements HttpClientFeature<Unit, HttpRedirect> {
        private Feature() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<HttpRedirect> getKey() {
            return HttpRedirect.key;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0093  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00ac A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00bd A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00aa -> B:30:0x00ad). Please submit an issue!!! */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        final /* synthetic */ java.lang.Object handleCall(@org.jetbrains.annotations.NotNull io.ktor.client.features.Sender r10, @org.jetbrains.annotations.NotNull io.ktor.client.call.HttpClientCall r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r12) {
            /*
                r9 = this;
                boolean r0 = r12 instanceof io.ktor.client.features.HttpRedirect$Feature$handleCall$1
                if (r0 == 0) goto L13
                r0 = r12
                io.ktor.client.features.HttpRedirect$Feature$handleCall$1 r0 = (io.ktor.client.features.HttpRedirect$Feature$handleCall$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                io.ktor.client.features.HttpRedirect$Feature$handleCall$1 r0 = new io.ktor.client.features.HttpRedirect$Feature$handleCall$1
                r0.<init>(r9, r12)
            L18:
                java.lang.Object r12 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L52
                if (r2 != r3) goto L4a
                java.lang.Object r10 = r0.L$4
                java.lang.String r10 = (java.lang.String) r10
                java.lang.Object r10 = r0.L$3
                io.ktor.client.call.HttpClientCall r10 = (io.ktor.client.call.HttpClientCall) r10
                java.lang.Object r10 = r0.L$2
                io.ktor.client.call.HttpClientCall r10 = (io.ktor.client.call.HttpClientCall) r10
                java.lang.Object r11 = r0.L$1
                io.ktor.client.features.Sender r11 = (io.ktor.client.features.Sender) r11
                java.lang.Object r2 = r0.L$0
                io.ktor.client.features.HttpRedirect$Feature r2 = (io.ktor.client.features.HttpRedirect.Feature) r2
                boolean r4 = r12 instanceof kotlin.Result.Failure
                if (r4 != 0) goto L45
                r7 = r12
                r12 = r10
                r10 = r11
                r11 = r7
                r8 = r2
                r2 = r1
                r1 = r8
                goto Lad
            L45:
                kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
                java.lang.Throwable r10 = r12.exception
                throw r10
            L4a:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L52:
                boolean r2 = r12 instanceof kotlin.Result.Failure
                if (r2 != 0) goto Lbe
                io.ktor.client.response.HttpResponse r12 = r11.getResponse()
                io.ktor.http.HttpStatusCode r12 = r12.getStatus()
                boolean r12 = io.ktor.client.features.HttpRedirectKt.access$isRedirect(r12)
                if (r12 != 0) goto L65
                return r11
            L65:
                r12 = r11
                r2 = r1
                r1 = r9
            L68:
                io.ktor.client.response.HttpResponse r4 = r11.getResponse()
                io.ktor.http.Headers r4 = r4.getHeaders()
                io.ktor.http.HttpHeaders r5 = io.ktor.http.HttpHeaders.INSTANCE
                java.lang.String r5 = r5.getLocation()
                java.lang.String r4 = r4.get(r5)
                io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder
                r5.<init>()
                io.ktor.client.request.HttpRequest r6 = r12.getRequest()
                io.ktor.client.request.HttpRequestKt.takeFrom(r5, r6)
                io.ktor.http.URLBuilder r6 = r5.getUrl()
                io.ktor.http.ParametersBuilder r6 = r6.getParameters()
                r6.clear()
                if (r4 == 0) goto L9a
                io.ktor.http.URLBuilder r6 = r5.getUrl()
                io.ktor.http.URLParserKt.takeFrom(r6, r4)
            L9a:
                r0.L$0 = r1
                r0.L$1 = r10
                r0.L$2 = r12
                r0.L$3 = r11
                r0.L$4 = r4
                r0.label = r3
                java.lang.Object r11 = r10.execute(r5, r0)
                if (r11 != r2) goto Lad
                return r2
            Lad:
                io.ktor.client.call.HttpClientCall r11 = (io.ktor.client.call.HttpClientCall) r11
                io.ktor.client.response.HttpResponse r4 = r11.getResponse()
                io.ktor.http.HttpStatusCode r4 = r4.getStatus()
                boolean r4 = io.ktor.client.features.HttpRedirectKt.access$isRedirect(r4)
                if (r4 != 0) goto L68
                return r11
            Lbe:
                kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
                java.lang.Throwable r10 = r12.exception
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.HttpRedirect.Feature.handleCall(io.ktor.client.features.Sender, io.ktor.client.call.HttpClientCall, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull HttpRedirect feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            Object feature2 = HttpClientFeatureKt.feature(scope, HttpSend.Feature);
            if (feature2 == null) {
                Intrinsics.throwNpe();
            }
            ((HttpSend) feature2).intercept(new HttpRedirect$Feature$install$1(null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public HttpRedirect mo10282prepare(@NotNull Function1<? super Unit, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new HttpRedirect();
        }
    }
}
