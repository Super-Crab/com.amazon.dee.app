package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import io.ktor.util.Attributes;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AttributesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0086\u0002J%\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0005¢\u0006\u0002\u0010\u0012J+\u0010\u0013\u001a\u00020\u0014\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00052\u0006\u0010\u0015\u001a\u0002H\u0011¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u0014\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0005R\u001b\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R$\u0010\b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lio/ktor/util/AttributesJvmBase;", "Lio/ktor/util/Attributes;", "()V", "allKeys", "", "Lio/ktor/util/AttributeKey;", "getAllKeys", "()Ljava/util/List;", BuildConfig.FLAVOR_authtype, "", "", "getMap", "()Ljava/util/Map;", "contains", "", "key", "getOrNull", ExifInterface.GPS_DIRECTION_TRUE, "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_PUT, "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", BulkOperationType.remove, "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class AttributesJvmBase implements Attributes {
    @Override // io.ktor.util.Attributes
    public final boolean contains(@NotNull AttributeKey<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return mo10323getMap().containsKey(key);
    }

    @Override // io.ktor.util.Attributes
    @NotNull
    public <T> T get(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) Attributes.DefaultImpls.get(this, key);
    }

    @Override // io.ktor.util.Attributes
    @NotNull
    public final List<AttributeKey<?>> getAllKeys() {
        List<AttributeKey<?>> list;
        list = CollectionsKt___CollectionsKt.toList(mo10323getMap().keySet());
        return list;
    }

    @NotNull
    /* renamed from: getMap */
    protected abstract Map<AttributeKey<?>, Object> mo10323getMap();

    @Override // io.ktor.util.Attributes
    @Nullable
    public final <T> T getOrNull(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) mo10323getMap().get(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.ktor.util.Attributes
    public final <T> void put(@NotNull AttributeKey<T> key, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        mo10323getMap().put(key, value);
    }

    @Override // io.ktor.util.Attributes
    public final <T> void remove(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        mo10323getMap().remove(key);
    }

    @Override // io.ktor.util.Attributes
    @KtorExperimentalAPI
    @NotNull
    public <T> T take(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) Attributes.DefaultImpls.take(this, key);
    }

    @Override // io.ktor.util.Attributes
    @KtorExperimentalAPI
    @Nullable
    public <T> T takeOrNull(@NotNull AttributeKey<T> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) Attributes.DefaultImpls.takeOrNull(this, key);
    }
}
