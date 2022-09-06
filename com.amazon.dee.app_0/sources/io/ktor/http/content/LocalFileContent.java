package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.FileChannelsKt;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalFileContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lio/ktor/http/content/LocalFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "file", "Ljava/io/File;", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/io/File;Lio/ktor/http/ContentType;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "getFile", "()Ljava/io/File;", "readFrom", "Lkotlinx/coroutines/io/ByteReadChannel;", "range", "Lkotlin/ranges/LongRange;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LocalFileContent extends OutgoingContent.ReadChannelContent {
    @NotNull
    private final ContentType contentType;
    @NotNull
    private final File file;

    public /* synthetic */ LocalFileContent(File file, ContentType contentType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? FileContentTypeJvmKt.defaultForFile(ContentType.Companion, file) : contentType);
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public Long getContentLength() {
        return Long.valueOf(this.file.length());
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public ContentType getContentType() {
        return this.contentType;
    }

    @NotNull
    public final File getFile() {
        return this.file;
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    @NotNull
    public ByteReadChannel readFrom() {
        return FileChannelsKt.readChannel$default(this.file, 0L, 0L, null, 7, null);
    }

    public LocalFileContent(@NotNull File file, @NotNull ContentType contentType) {
        List plus;
        Intrinsics.checkParameterIsNotNull(file, "file");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.file = file;
        this.contentType = contentType;
        List<Version> versions = VersionsKt.getVersions(this);
        FileTime lastModifiedTime = Files.getLastModifiedTime(this.file.toPath(), new LinkOption[0]);
        Intrinsics.checkExpressionValueIsNotNull(lastModifiedTime, "Files.getLastModifiedTime(file.toPath())");
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) versions), (Object) LastModifiedJavaTimeKt.LastModifiedVersion(lastModifiedTime));
        VersionsKt.setVersions(this, plus);
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    @NotNull
    public ByteReadChannel readFrom(@NotNull LongRange range) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        return FileChannelsKt.readChannel$default(this.file, range.mo11373getStart().longValue(), range.mo11372getEndInclusive().longValue(), null, 4, null);
    }
}
