package com.amazon.photos.discovery.internal.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010#\n\u0002\b\u0004\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0080\b¢\u0006\u0002\u0010\b\u001a?\u0010\t\u001a\u00020\n\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f*\u0014\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u000e0\r2\u0006\u0010\u000f\u001a\u0002H\u000b2\u0006\u0010\u0010\u001a\u0002H\fH\u0000¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"catchDb", ExifInterface.GPS_DIRECTION_TRUE, "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", MAPAccountManager.KEY_INTENT, "", "operation", "Lkotlin/Function0;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "addToMapSet", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "", "key", "setValue", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class KotlinExtKt {
    public static final <K, V> void addToMapSet(@NotNull Map<K, Set<V>> addToMapSet, K k, V v) {
        Set<V> mutableSetOf;
        Intrinsics.checkParameterIsNotNull(addToMapSet, "$this$addToMapSet");
        Set<V> set = addToMapSet.get(k);
        if (set != null) {
            set.add(v);
            return;
        }
        mutableSetOf = SetsKt__SetsKt.mutableSetOf(v);
        addToMapSet.put(k, mutableSetOf);
    }

    @Nullable
    public static final <T> T catchDb(@NotNull Metrics metrics, @NotNull String intent, @NotNull Function0<? extends T> operation) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        try {
            return operation.mo12560invoke();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(intent, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
            return null;
        }
    }
}
