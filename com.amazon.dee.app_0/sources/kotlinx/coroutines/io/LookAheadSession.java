package kotlinx.coroutines.io;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlinx.io.core.ExperimentalIoApi;
import org.jetbrains.annotations.Nullable;
/* compiled from: LookAheadSession.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/io/LookAheadSession;", "", "consumed", "", JsonReportFormat.COUNT, "", "request", "Ljava/nio/ByteBuffer;", "skip", "atLeast", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface LookAheadSession {
    void consumed(int i);

    @Nullable
    ByteBuffer request(int i, int i2);
}
