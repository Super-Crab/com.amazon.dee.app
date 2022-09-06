package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteArrayContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/content/ByteArrayContent;", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "bytes", "", "contentType", "Lio/ktor/http/ContentType;", "status", "Lio/ktor/http/HttpStatusCode;", "([BLio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ByteArrayContent extends OutgoingContent.ByteArrayContent {
    private final byte[] bytes;
    @Nullable
    private final ContentType contentType;
    @Nullable
    private final HttpStatusCode status;

    public /* synthetic */ ByteArrayContent(byte[] bArr, ContentType contentType, HttpStatusCode httpStatusCode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i & 2) != 0 ? null : contentType, (i & 4) != 0 ? null : httpStatusCode);
    }

    @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
    @NotNull
    public byte[] bytes() {
        return this.bytes;
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public Long getContentLength() {
        return Long.valueOf(this.bytes.length);
    }

    @Override // io.ktor.http.content.OutgoingContent
    @Nullable
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // io.ktor.http.content.OutgoingContent
    @Nullable
    public HttpStatusCode getStatus() {
        return this.status;
    }

    public ByteArrayContent(@NotNull byte[] bytes, @Nullable ContentType contentType, @Nullable HttpStatusCode httpStatusCode) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.bytes = bytes;
        this.contentType = contentType;
        this.status = httpStatusCode;
    }
}
