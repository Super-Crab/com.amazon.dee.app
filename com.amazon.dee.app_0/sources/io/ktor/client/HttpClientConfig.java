package io.ktor.client;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.features.HttpClientFeature;
import io.ktor.util.AttributeKey;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientConfig.kt */
@HttpClientDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\u001f\u0010 \u001a\u00020\n2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\fJ\u000e\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\tJI\u0010\"\u001a\u00020\n\"\b\b\u0001\u0010$*\u00020\u0003\"\b\b\u0002\u0010%*\u00020\u00032\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H%0'2\u0019\b\u0002\u0010(\u001a\u0013\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\fJ'\u0010\"\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u00072\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\fJ\u0019\u0010*\u001a\u00020\n2\u000e\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000H\u0086\u0002R&\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u0016R\u001a\u0010\u001c\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016¨\u0006,"}, d2 = {"Lio/ktor/client/HttpClientConfig;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", "", "()V", "customInterceptors", "", "", "Lkotlin/Function1;", "Lio/ktor/client/HttpClient;", "", "engineConfig", "Lkotlin/ExtensionFunctionType;", "getEngineConfig$ktor_client_core", "()Lkotlin/jvm/functions/Function1;", "setEngineConfig$ktor_client_core", "(Lkotlin/jvm/functions/Function1;)V", "expectSuccess", "", "getExpectSuccess", "()Z", "setExpectSuccess", "(Z)V", NetworkConstants.FEATURES_KEY, "Lio/ktor/util/AttributeKey;", "followRedirects", "getFollowRedirects", "setFollowRedirects", "useDefaultTransformers", "getUseDefaultTransformers", "setUseDefaultTransformers", "clone", "engine", "block", "install", "client", "TBuilder", "TFeature", "feature", "Lio/ktor/client/features/HttpClientFeature;", "configure", "key", "plusAssign", "other", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientConfig<T extends HttpClientEngineConfig> {
    private final Map<AttributeKey<?>, Function1<HttpClient, Unit>> features = new LinkedHashMap();
    private final Map<String, Function1<HttpClient, Unit>> customInterceptors = new LinkedHashMap();
    @NotNull
    private Function1<? super T, Unit> engineConfig = HttpClientConfig$engineConfig$1.INSTANCE;
    private boolean followRedirects = true;
    private boolean useDefaultTransformers = true;
    private boolean expectSuccess = true;

    public static /* synthetic */ void install$default(HttpClientConfig httpClientConfig, HttpClientFeature httpClientFeature, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = HttpClientConfig$install$1.INSTANCE;
        }
        httpClientConfig.install(httpClientFeature, function1);
    }

    @NotNull
    public final HttpClientConfig<T> clone() {
        HttpClientConfig<T> httpClientConfig = new HttpClientConfig<>();
        httpClientConfig.features.putAll(this.features);
        httpClientConfig.customInterceptors.putAll(this.customInterceptors);
        httpClientConfig.engineConfig = this.engineConfig;
        return httpClientConfig;
    }

    public final void engine(@NotNull Function1<? super T, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.engineConfig = new HttpClientConfig$engine$1(this.engineConfig, block);
    }

    @NotNull
    public final Function1<T, Unit> getEngineConfig$ktor_client_core() {
        return (Function1<? super T, Unit>) this.engineConfig;
    }

    public final boolean getExpectSuccess() {
        return this.expectSuccess;
    }

    public final boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public final boolean getUseDefaultTransformers() {
        return this.useDefaultTransformers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <TBuilder, TFeature> void install(@NotNull HttpClientFeature<? extends TBuilder, TFeature> feature, @NotNull Function1<? super TBuilder, Unit> configure) {
        Intrinsics.checkParameterIsNotNull(feature, "feature");
        Intrinsics.checkParameterIsNotNull(configure, "configure");
        this.features.put(feature.getKey(), new HttpClientConfig$install$2(feature, feature.mo10282prepare(configure)));
    }

    public final void plusAssign(@NotNull HttpClientConfig<? extends T> other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        this.followRedirects = other.followRedirects;
        this.useDefaultTransformers = other.useDefaultTransformers;
        this.expectSuccess = other.expectSuccess;
        this.features.putAll(other.features);
        this.customInterceptors.putAll(other.customInterceptors);
    }

    public final void setEngineConfig$ktor_client_core(@NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.engineConfig = function1;
    }

    public final void setExpectSuccess(boolean z) {
        this.expectSuccess = z;
    }

    public final void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public final void setUseDefaultTransformers(boolean z) {
        this.useDefaultTransformers = z;
    }

    public final void install(@NotNull String key, @NotNull Function1<? super HttpClient, Unit> block) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.customInterceptors.put(key, block);
    }

    public final void install(@NotNull HttpClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Iterator<T> it2 = this.features.values().iterator();
        while (it2.hasNext()) {
            ((Function1) it2.next()).mo12165invoke(client);
        }
        Iterator<T> it3 = this.customInterceptors.values().iterator();
        while (it3.hasNext()) {
            ((Function1) it3.next()).mo12165invoke(client);
        }
    }
}
