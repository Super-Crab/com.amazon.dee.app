package kotlin.sequences;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Sequences.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lkotlin/sequences/DropTakeSequence;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "drop", JsonReportFormat.COUNT, "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface DropTakeSequence<T> extends Sequence<T> {
    @NotNull
    /* renamed from: drop */
    Sequence<T> mo12152drop(int i);

    @NotNull
    /* renamed from: take */
    Sequence<T> mo12153take(int i);
}
