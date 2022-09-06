package io.ktor.client.features;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.response.HttpResponsePipeline;
import io.ktor.util.AttributeKey;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpPlainText.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u001e\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/features/HttpPlainText;", "", "defaultCharset", "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "(Ljava/nio/charset/Charset;)V", "getDefaultCharset", "()Ljava/nio/charset/Charset;", "setDefaultCharset", "read", "", "response", "Lio/ktor/client/response/HttpResponse;", "read$ktor_client_core", "(Lio/ktor/client/response/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "Feature", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpPlainText {
    public static final Feature Feature = new Feature(null);
    @NotNull
    private static final AttributeKey<HttpPlainText> key = new AttributeKey<>("HttpPlainText");
    @NotNull
    private Charset defaultCharset;

    /* compiled from: HttpPlainText.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fR\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lio/ktor/client/features/HttpPlainText$Config;", "", "()V", "defaultCharset", "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "getDefaultCharset", "()Ljava/nio/charset/Charset;", "setDefaultCharset", "(Ljava/nio/charset/Charset;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/client/features/HttpPlainText;", "build$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Config {
        @NotNull
        private Charset defaultCharset = Charsets.UTF_8;

        @NotNull
        public final HttpPlainText build$ktor_client_core() {
            return new HttpPlainText(this.defaultCharset);
        }

        @NotNull
        public final Charset getDefaultCharset() {
            return this.defaultCharset;
        }

        public final void setDefaultCharset(@NotNull Charset charset) {
            Intrinsics.checkParameterIsNotNull(charset, "<set-?>");
            this.defaultCharset = charset;
        }
    }

    /* compiled from: HttpPlainText.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/features/HttpPlainText$Feature;", "Lio/ktor/client/features/HttpClientFeature;", "Lio/ktor/client/features/HttpPlainText$Config;", "Lio/ktor/client/features/HttpPlainText;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Feature implements HttpClientFeature<Config, HttpPlainText> {
        private Feature() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<HttpPlainText> getKey() {
            return HttpPlainText.key;
        }

        public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull HttpPlainText feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new HttpPlainText$Feature$install$1(feature, null));
            scope.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new HttpPlainText$Feature$install$2(feature, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public HttpPlainText mo10282prepare(@NotNull Function1<? super Config, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            Config config = new Config();
            block.mo12165invoke(config);
            return config.build$ktor_client_core();
        }
    }

    public HttpPlainText(@NotNull Charset defaultCharset) {
        Intrinsics.checkParameterIsNotNull(defaultCharset, "defaultCharset");
        this.defaultCharset = defaultCharset;
    }

    @NotNull
    public final Charset getDefaultCharset() {
        return this.defaultCharset;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.Closeable] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object read$ktor_client_core(@org.jetbrains.annotations.NotNull io.ktor.client.response.HttpResponse r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.features.HttpPlainText$read$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.features.HttpPlainText$read$1 r0 = (io.ktor.client.features.HttpPlainText$read$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.features.HttpPlainText$read$1 r0 = new io.ktor.client.features.HttpPlainText$read$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4b
            if (r2 != r3) goto L43
            java.lang.Object r5 = r0.L$3
            io.ktor.client.response.HttpResponse r5 = (io.ktor.client.response.HttpResponse) r5
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$2
            java.io.Closeable r5 = (java.io.Closeable) r5
            java.lang.Object r1 = r0.L$1
            io.ktor.client.response.HttpResponse r1 = (io.ktor.client.response.HttpResponse) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.client.features.HttpPlainText r0 = (io.ktor.client.features.HttpPlainText) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L41
            if (r0 != 0) goto L3c
            goto L65
        L3c:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6     // Catch: java.lang.Throwable -> L41
            java.lang.Throwable r6 = r6.exception     // Catch: java.lang.Throwable -> L41
            throw r6     // Catch: java.lang.Throwable -> L41
        L41:
            r6 = move-exception
            goto L6b
        L43:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L4b:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L76
            r6 = 0
            java.nio.charset.Charset r2 = r4.defaultCharset     // Catch: java.lang.Throwable -> L41
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L41
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L41
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L41
            r0.I$0 = r6     // Catch: java.lang.Throwable -> L41
            r0.L$3 = r5     // Catch: java.lang.Throwable -> L41
            r0.label = r3     // Catch: java.lang.Throwable -> L41
            java.lang.Object r6 = io.ktor.client.response.HttpResponseKt.readText(r5, r2, r0)     // Catch: java.lang.Throwable -> L41
            if (r6 != r1) goto L65
            return r1
        L65:
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> L41
            r5.close()
            return r6
        L6b:
            r5.close()     // Catch: java.lang.Throwable -> L6f
            goto L73
        L6f:
            r5 = move-exception
            kotlinx.io.core.CloseableJVMKt.addSuppressedInternal(r6, r5)     // Catch: java.lang.Throwable -> L74
        L73:
            throw r6     // Catch: java.lang.Throwable -> L74
        L74:
            r5 = move-exception
            throw r5
        L76:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.HttpPlainText.read$ktor_client_core(io.ktor.client.response.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setDefaultCharset(@NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "<set-?>");
        this.defaultCharset = charset;
    }
}
