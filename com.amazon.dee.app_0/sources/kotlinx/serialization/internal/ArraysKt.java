package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Arrays.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0000\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\b¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\b¨\u0006\b"}, d2 = {"getChecked", ExifInterface.GPS_DIRECTION_TRUE, "", "index", "", "([Ljava/lang/Object;I)Ljava/lang/Object;", "", "", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ArraysKt {
    public static final <T> T getChecked(@NotNull T[] getChecked, int i) {
        Intrinsics.checkParameterIsNotNull(getChecked, "$this$getChecked");
        return getChecked[i];
    }

    public static final boolean getChecked(@NotNull boolean[] getChecked, int i) {
        Intrinsics.checkParameterIsNotNull(getChecked, "$this$getChecked");
        return getChecked[i];
    }
}
