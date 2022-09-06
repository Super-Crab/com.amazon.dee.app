package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WriterContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BE\u0012'\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0003¢\u0006\u0002\b\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0019\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016R4\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0003¢\u0006\u0002\b\bX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/http/content/WriterContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "body", "Lkotlin/Function2;", "Ljava/io/Writer;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "contentType", "Lio/ktor/http/ContentType;", "status", "Lio/ktor/http/HttpStatusCode;", "(Lkotlin/jvm/functions/Function2;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;)V", "Lkotlin/jvm/functions/Function2;", "getContentType", "()Lio/ktor/http/ContentType;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "writeTo", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class WriterContent extends OutgoingContent.WriteChannelContent {
    private final Function2<Writer, Continuation<? super Unit>, Object> body;
    @NotNull
    private final ContentType contentType;
    @Nullable
    private final HttpStatusCode status;

    public /* synthetic */ WriterContent(Function2 function2, ContentType contentType, HttpStatusCode httpStatusCode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, contentType, (i & 4) != 0 ? null : httpStatusCode);
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // io.ktor.http.content.OutgoingContent
    @Nullable
    public HttpStatusCode getStatus() {
        return this.status;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable] */
    @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.http.content.WriterContent$writeTo$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.http.content.WriterContent$writeTo$1 r0 = (io.ktor.http.content.WriterContent$writeTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.content.WriterContent$writeTo$1 r0 = new io.ktor.http.content.WriterContent$writeTo$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L52
            if (r2 != r3) goto L4a
            java.lang.Object r7 = r0.L$5
            java.io.Writer r7 = (java.io.Writer) r7
            java.lang.Object r7 = r0.L$4
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r1 = r0.L$3
            java.io.Closeable r1 = (java.io.Closeable) r1
            java.lang.Object r2 = r0.L$2
            java.nio.charset.Charset r2 = (java.nio.charset.Charset) r2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.io.ByteWriteChannel r2 = (kotlinx.coroutines.io.ByteWriteChannel) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.http.content.WriterContent r0 = (io.ktor.http.content.WriterContent) r0
            boolean r0 = r8 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L47
            if (r0 != 0) goto L42
            goto L81
        L42:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8     // Catch: java.lang.Throwable -> L47
            java.lang.Throwable r7 = r8.exception     // Catch: java.lang.Throwable -> L47
            throw r7     // Catch: java.lang.Throwable -> L47
        L47:
            r7 = move-exception
            r2 = r1
            goto L8a
        L4a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L52:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L90
            io.ktor.http.ContentType r8 = r6.getContentType()
            java.nio.charset.Charset r8 = io.ktor.http.ContentTypesKt.charset(r8)
            if (r8 == 0) goto L61
            goto L63
        L61:
            java.nio.charset.Charset r8 = kotlin.text.Charsets.UTF_8
        L63:
            java.io.Writer r2 = io.ktor.util.cio.OutputStreamAdaptersKt.writer(r7, r8)
            r4 = 0
            kotlin.jvm.functions.Function2<java.io.Writer, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r5 = r6.body     // Catch: java.lang.Throwable -> L89
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L89
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L89
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L89
            r0.L$3 = r2     // Catch: java.lang.Throwable -> L89
            r0.L$4 = r4     // Catch: java.lang.Throwable -> L89
            r0.L$5 = r2     // Catch: java.lang.Throwable -> L89
            r0.label = r3     // Catch: java.lang.Throwable -> L89
            java.lang.Object r7 = r5.mo12248invoke(r2, r0)     // Catch: java.lang.Throwable -> L89
            if (r7 != r1) goto L7f
            return r1
        L7f:
            r1 = r2
            r7 = r4
        L81:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L47
            kotlin.io.CloseableKt.closeFinally(r1, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L89:
            r7 = move-exception
        L8a:
            throw r7     // Catch: java.lang.Throwable -> L8b
        L8b:
            r8 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r7)
            throw r8
        L90:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.WriterContent.writeTo(kotlinx.coroutines.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WriterContent(@NotNull Function2<? super Writer, ? super Continuation<? super Unit>, ? extends Object> body, @NotNull ContentType contentType, @Nullable HttpStatusCode httpStatusCode) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.body = body;
        this.contentType = contentType;
        this.status = httpStatusCode;
    }
}
