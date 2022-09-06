package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.KtorExperimentalAPI;
import io.ktor.util.cio.ByteBufferPoolKt;
import java.io.InputStream;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.jvm.javaio.ReadingKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: URIFileContent.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lio/ktor/http/content/URIFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "url", "Ljava/net/URL;", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/net/URL;Lio/ktor/http/ContentType;)V", "uri", "Ljava/net/URI;", "(Ljava/net/URI;Lio/ktor/http/ContentType;)V", "getContentType", "()Lio/ktor/http/ContentType;", "getUri", "()Ljava/net/URI;", "readFrom", "Lkotlinx/coroutines/io/ByteReadChannel;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URIFileContent extends OutgoingContent.ReadChannelContent {
    @NotNull
    private final ContentType contentType;
    @NotNull
    private final URI uri;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ URIFileContent(java.net.URI r1, io.ktor.http.ContentType r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L13
            io.ktor.http.ContentType$Companion r2 = io.ktor.http.ContentType.Companion
            java.lang.String r3 = r1.getPath()
            java.lang.String r4 = "uri.path"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            io.ktor.http.ContentType r2 = io.ktor.http.FileContentTypeKt.defaultForFilePath(r2, r3)
        L13:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URI, io.ktor.http.ContentType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public ContentType getContentType() {
        return this.contentType;
    }

    @NotNull
    public final URI getUri() {
        return this.uri;
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    @NotNull
    public ByteReadChannel readFrom() {
        InputStream openStream = this.uri.toURL().openStream();
        Intrinsics.checkExpressionValueIsNotNull(openStream, "uri.toURL().openStream()");
        return ReadingKt.toByteReadChannel$default(openStream, null, ByteBufferPoolKt.getKtorDefaultPool(), 1, null);
    }

    public URIFileContent(@NotNull URI uri, @NotNull ContentType contentType) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.uri = uri;
        this.contentType = contentType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ URIFileContent(java.net.URL r1, io.ktor.http.ContentType r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L13
            io.ktor.http.ContentType$Companion r2 = io.ktor.http.ContentType.Companion
            java.lang.String r3 = r1.getPath()
            java.lang.String r4 = "url.path"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            io.ktor.http.ContentType r2 = io.ktor.http.FileContentTypeKt.defaultForFilePath(r2, r3)
        L13:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URL, io.ktor.http.ContentType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public URIFileContent(@org.jetbrains.annotations.NotNull java.net.URL r2, @org.jetbrains.annotations.NotNull io.ktor.http.ContentType r3) {
        /*
            r1 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "contentType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.net.URI r2 = r2.toURI()
            java.lang.String r0 = "url.toURI()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            r1.<init>(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URL, io.ktor.http.ContentType):void");
    }
}
