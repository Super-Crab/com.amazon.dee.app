package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReversedViews.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¢\u0006\u0002\b\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\b\u001a\u001d\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"asReversed", "", ExifInterface.GPS_DIRECTION_TRUE, "", "asReversedMutable", "reverseElementIndex", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    @NotNull
    public static final <T> List<T> asReversed(@NotNull List<? extends T> asReversed) {
        Intrinsics.checkParameterIsNotNull(asReversed, "$this$asReversed");
        return new ReversedListReadOnly(asReversed);
    }

    @JvmName(name = "asReversedMutable")
    @NotNull
    public static <T> List<T> asReversedMutable(@NotNull List<T> asReversed) {
        Intrinsics.checkParameterIsNotNull(asReversed, "$this$asReversed");
        return new ReversedList(asReversed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> list, int i) {
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        if (i < 0 || lastIndex < i) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Element index ", i, " must be in range [");
            outline109.append(new IntRange(0, CollectionsKt__CollectionsKt.getLastIndex(list)));
            outline109.append("].");
            throw new IndexOutOfBoundsException(outline109.toString());
        }
        return CollectionsKt__CollectionsKt.getLastIndex(list) - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> list, int i) {
        int size = list.size();
        if (i < 0 || size < i) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Position index ", i, " must be in range [");
            outline109.append(new IntRange(0, list.size()));
            outline109.append("].");
            throw new IndexOutOfBoundsException(outline109.toString());
        }
        return list.size() - i;
    }
}
