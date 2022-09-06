package io.ktor.client.features;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientFeature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0005\u001a\u0004\u0018\u0001H\u0006\"\b\b\u0000\u0010\u0007*\u00020\b\"\b\b\u0001\u0010\u0006*\u00020\b*\u00020\t2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\n¢\u0006\u0002\u0010\u000b\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"FEATURE_INSTALLED_LIST", "Lio/ktor/util/AttributeKey;", "Lio/ktor/util/Attributes;", "getFEATURE_INSTALLED_LIST", "()Lio/ktor/util/AttributeKey;", "feature", "F", "B", "", "Lio/ktor/client/HttpClient;", "Lio/ktor/client/features/HttpClientFeature;", "(Lio/ktor/client/HttpClient;Lio/ktor/client/features/HttpClientFeature;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientFeatureKt {
    @NotNull
    private static final AttributeKey<Attributes> FEATURE_INSTALLED_LIST = new AttributeKey<>("ApplicationFeatureRegistry");

    @Nullable
    public static final <B, F> F feature(@NotNull HttpClient receiver$0, @NotNull HttpClientFeature<? extends B, F> feature) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(feature, "feature");
        Attributes attributes = (Attributes) receiver$0.getAttributes().getOrNull(FEATURE_INSTALLED_LIST);
        if (attributes != null) {
            return (F) attributes.getOrNull(feature.getKey());
        }
        return null;
    }

    @NotNull
    public static final AttributeKey<Attributes> getFEATURE_INSTALLED_LIST() {
        return FEATURE_INSTALLED_LIST;
    }
}
