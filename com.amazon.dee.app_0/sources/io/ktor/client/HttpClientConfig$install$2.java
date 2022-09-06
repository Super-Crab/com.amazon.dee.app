package io.ktor.client;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.features.HttpClientFeature;
import io.ktor.client.features.HttpClientFeatureKt;
import io.ktor.util.Attributes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "TBuilder", "", "TFeature", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpClientConfig$install$2 extends Lambda implements Function1<HttpClient, Unit> {
    final /* synthetic */ HttpClientFeature $feature;
    final /* synthetic */ Object $featureData;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientConfig$install$2(HttpClientFeature httpClientFeature, Object obj) {
        super(1);
        this.$feature = httpClientFeature;
        this.$featureData = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(HttpClient httpClient) {
        invoke2(httpClient);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull HttpClient scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.$feature.install(this.$featureData, scope);
        ((Attributes) scope.getAttributes().computeIfAbsent(HttpClientFeatureKt.getFEATURE_INSTALLED_LIST(), HttpClientConfig$install$2$attributes$1.INSTANCE)).put(this.$feature.getKey(), this.$featureData);
    }
}
