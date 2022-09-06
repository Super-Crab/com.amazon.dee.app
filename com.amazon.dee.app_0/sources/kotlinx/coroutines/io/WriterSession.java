package kotlinx.coroutines.io;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import kotlin.Metadata;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.Nullable;
/* compiled from: WriterSession.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/io/WriterSession;", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "request", "Lkotlinx/io/core/IoBuffer;", ReactProperties.GEOFENCE_MINIMUM_RANGE, "", "written", JsonReportFormat.COUNT, "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface WriterSession {
    void flush();

    @Nullable
    IoBuffer request(int i);

    void written(int i);
}
