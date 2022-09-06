package com.amazon.photos.uploader.internal.utils;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: IterableUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b¨\u0006\u0006"}, d2 = {"sumByLong", "", ExifInterface.GPS_DIRECTION_TRUE, "", "selector", "Lkotlin/Function1;", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class IterableUtilsKt {
    public static final <T> long sumByLong(@NotNull Iterable<? extends T> sumByLong, @NotNull Function1<? super T, Long> selector) {
        Intrinsics.checkParameterIsNotNull(sumByLong, "$this$sumByLong");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Iterator<? extends T> it2 = sumByLong.iterator();
        long j = 0;
        while (it2.hasNext()) {
            j += selector.mo12165invoke((T) it2.next()).longValue();
        }
        return j;
    }
}
