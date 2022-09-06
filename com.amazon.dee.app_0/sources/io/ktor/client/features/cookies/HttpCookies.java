package io.ktor.client.features.cookies;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.dee.app.metrics.MetricsConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.client.HttpClient;
import io.ktor.client.features.HttpClientFeature;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.response.HttpReceivePipeline;
import io.ktor.http.Cookie;
import io.ktor.http.Url;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpCookies.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/ktor/client/features/cookies/HttpCookies;", "", "storage", "Lio/ktor/client/features/cookies/CookiesStorage;", "(Lio/ktor/client/features/cookies/CookiesStorage;)V", MetricsConstants.Method.CACHE_GET, "", "Lio/ktor/http/Cookie;", "requestUrl", "Lio/ktor/http/Url;", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Config", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpCookies {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final AttributeKey<HttpCookies> key = new AttributeKey<>("HttpCookies");
    private final CookiesStorage storage;

    /* compiled from: HttpCookies.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/features/cookies/HttpCookies$Companion;", "Lio/ktor/client/features/HttpClientFeature;", "Lio/ktor/client/features/cookies/HttpCookies$Config;", "Lio/ktor/client/features/cookies/HttpCookies;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion implements HttpClientFeature<Config, HttpCookies> {
        private Companion() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<HttpCookies> getKey() {
            return HttpCookies.key;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull HttpCookies feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            scope.getSendPipeline().intercept(HttpSendPipeline.Phases.getState(), new HttpCookies$Companion$install$1(feature, null));
            scope.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getState(), new HttpCookies$Companion$install$2(feature, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public HttpCookies mo10282prepare(@NotNull Function1<? super Config, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            Config config = new Config();
            block.mo12165invoke(config);
            return config.build$ktor_client_core();
        }
    }

    /* compiled from: HttpCookies.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\u001f\u0010\u0011\u001a\u00020\u00072\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bR%\u0010\u0003\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lio/ktor/client/features/cookies/HttpCookies$Config;", "", "()V", "defaults", "", "Lkotlin/Function1;", "Lio/ktor/client/features/cookies/CookiesStorage;", "", "Lkotlin/ExtensionFunctionType;", "storage", "getStorage", "()Lio/ktor/client/features/cookies/CookiesStorage;", "setStorage", "(Lio/ktor/client/features/cookies/CookiesStorage;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/client/features/cookies/HttpCookies;", "build$ktor_client_core", "default", "block", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Config {
        private final List<Function1<CookiesStorage, Unit>> defaults = new ArrayList();
        @NotNull
        private CookiesStorage storage = new AcceptAllCookiesStorage();

        @NotNull
        public final HttpCookies build$ktor_client_core() {
            Iterator<T> it2 = this.defaults.iterator();
            while (it2.hasNext()) {
                ((Function1) it2.next()).mo12165invoke(this.storage);
            }
            return new HttpCookies(this.storage);
        }

        /* renamed from: default  reason: not valid java name */
        public final void m10281default(@NotNull Function1<? super CookiesStorage, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            this.defaults.add(block);
        }

        @NotNull
        public final CookiesStorage getStorage() {
            return this.storage;
        }

        public final void setStorage(@NotNull CookiesStorage cookiesStorage) {
            Intrinsics.checkParameterIsNotNull(cookiesStorage, "<set-?>");
            this.storage = cookiesStorage;
        }
    }

    public HttpCookies(@NotNull CookiesStorage storage) {
        Intrinsics.checkParameterIsNotNull(storage, "storage");
        this.storage = storage;
    }

    @Nullable
    public final Object get(@NotNull Url url, @NotNull Continuation<? super List<Cookie>> continuation) {
        return this.storage.get(url, continuation);
    }
}
