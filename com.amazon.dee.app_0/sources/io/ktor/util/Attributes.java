package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Attributes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J3\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH&¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0004H¦\u0002J&\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0096\u0002¢\u0006\u0002\u0010\u0010J'\u0010\u0011\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H&¢\u0006\u0002\u0010\u0010J-\u0010\u0012\u001a\u00020\u0013\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00042\u0006\u0010\u0014\u001a\u0002H\bH&¢\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u00020\u0013\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H&J%\u0010\u0017\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0017¢\u0006\u0002\u0010\u0010J'\u0010\u0018\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0017¢\u0006\u0002\u0010\u0010R\u001c\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0019"}, d2 = {"Lio/ktor/util/Attributes;", "", "allKeys", "", "Lio/ktor/util/AttributeKey;", "getAllKeys", "()Ljava/util/List;", "computeIfAbsent", ExifInterface.GPS_DIRECTION_TRUE, "key", "block", "Lkotlin/Function0;", "(Lio/ktor/util/AttributeKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "contains", "", MetricsConstants.Method.CACHE_GET, "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "getOrNull", MetricsConstants.Method.CACHE_PUT, "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", BulkOperationType.remove, "take", "takeOrNull", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface Attributes {

    /* compiled from: Attributes.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static <T> T get(Attributes attributes, @NotNull AttributeKey<T> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            T t = (T) attributes.getOrNull(key);
            if (t != null) {
                return t;
            }
            throw new IllegalStateException("No instance for key " + key);
        }

        @KtorExperimentalAPI
        @NotNull
        public static <T> T take(Attributes attributes, @NotNull AttributeKey<T> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            T t = (T) attributes.get(key);
            attributes.remove(key);
            return t;
        }

        @KtorExperimentalAPI
        @Nullable
        public static <T> T takeOrNull(Attributes attributes, @NotNull AttributeKey<T> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            T t = (T) attributes.getOrNull(key);
            attributes.remove(key);
            return t;
        }
    }

    @NotNull
    <T> T computeIfAbsent(@NotNull AttributeKey<T> attributeKey, @NotNull Function0<? extends T> function0);

    boolean contains(@NotNull AttributeKey<?> attributeKey);

    @NotNull
    <T> T get(@NotNull AttributeKey<T> attributeKey);

    @NotNull
    List<AttributeKey<?>> getAllKeys();

    @Nullable
    <T> T getOrNull(@NotNull AttributeKey<T> attributeKey);

    <T> void put(@NotNull AttributeKey<T> attributeKey, @NotNull T t);

    <T> void remove(@NotNull AttributeKey<T> attributeKey);

    @KtorExperimentalAPI
    @NotNull
    <T> T take(@NotNull AttributeKey<T> attributeKey);

    @KtorExperimentalAPI
    @Nullable
    <T> T takeOrNull(@NotNull AttributeKey<T> attributeKey);
}
