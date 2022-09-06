package io.ktor.http.content;

import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.DateUtilsKt;
import java.nio.file.attribute.FileTime;
import java.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LastModifiedJavaTime.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"LastModifiedVersion", "Lio/ktor/http/content/LastModifiedVersion;", "lastModified", "Ljava/nio/file/attribute/FileTime;", "Ljava/time/ZonedDateTime;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LastModifiedJavaTimeKt {
    @NotNull
    public static final LastModifiedVersion LastModifiedVersion(@NotNull ZonedDateTime lastModified) {
        Intrinsics.checkParameterIsNotNull(lastModified, "lastModified");
        return new LastModifiedVersion(DateUtilsKt.toGMTDate(lastModified));
    }

    @NotNull
    public static final LastModifiedVersion LastModifiedVersion(@NotNull FileTime lastModified) {
        Intrinsics.checkParameterIsNotNull(lastModified, "lastModified");
        return new LastModifiedVersion(DateJvmKt.GMTDate(Long.valueOf(lastModified.toMillis())));
    }
}
