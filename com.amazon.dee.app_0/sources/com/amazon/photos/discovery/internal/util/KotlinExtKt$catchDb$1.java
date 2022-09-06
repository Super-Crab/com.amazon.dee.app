package com.amazon.photos.discovery.internal.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "getEventName"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class KotlinExtKt$catchDb$1 implements MetricName {
    final /* synthetic */ String $intent;

    public KotlinExtKt$catchDb$1(String str) {
        this.$intent = str;
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
    @NotNull
    public final String getEventName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryMetricsKt.DB_ERROR_PREFIX);
        outline107.append(this.$intent);
        return outline107.toString();
    }
}
