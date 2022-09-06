package io.ktor.client.engine.android;

import io.ktor.client.engine.HttpClientEngineConfig;
import java.net.Proxy;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AndroidEngineConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR&\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/client/engine/android/AndroidEngineConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "()V", "connectTimeout", "", "getConnectTimeout", "()I", "setConnectTimeout", "(I)V", "proxy", "Ljava/net/Proxy;", "getProxy", "()Ljava/net/Proxy;", "setProxy", "(Ljava/net/Proxy;)V", "socketTimeout", "getSocketTimeout", "setSocketTimeout", "sslManager", "Lkotlin/Function1;", "Ljavax/net/ssl/HttpsURLConnection;", "", "getSslManager", "()Lkotlin/jvm/functions/Function1;", "setSslManager", "(Lkotlin/jvm/functions/Function1;)V", "ktor-client-android"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AndroidEngineConfig extends HttpClientEngineConfig {
    @Nullable
    private Proxy proxy;
    private int connectTimeout = 100000;
    private int socketTimeout = 100000;
    @NotNull
    private Function1<? super HttpsURLConnection, Unit> sslManager = AndroidEngineConfig$sslManager$1.INSTANCE;

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Nullable
    public final Proxy getProxy() {
        return this.proxy;
    }

    public final int getSocketTimeout() {
        return this.socketTimeout;
    }

    @NotNull
    public final Function1<HttpsURLConnection, Unit> getSslManager() {
        return this.sslManager;
    }

    public final void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public final void setProxy(@Nullable Proxy proxy) {
        this.proxy = proxy;
    }

    public final void setSocketTimeout(int i) {
        this.socketTimeout = i;
    }

    public final void setSslManager(@NotNull Function1<? super HttpsURLConnection, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.sslManager = function1;
    }
}
