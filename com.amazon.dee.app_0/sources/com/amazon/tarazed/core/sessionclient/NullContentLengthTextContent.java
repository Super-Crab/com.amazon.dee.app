package com.amazon.tarazed.core.sessionclient;

import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NullContentLengthTextContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/NullContentLengthTextContent;", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "text", "", "contentType", "Lio/ktor/http/ContentType;", "status", "Lio/ktor/http/HttpStatusCode;", "(Ljava/lang/String;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;)V", "bytes", "", "getBytes", "()[B", "bytes$delegate", "Lkotlin/Lazy;", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getText", "()Ljava/lang/String;", "toString", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NullContentLengthTextContent extends OutgoingContent.ByteArrayContent {
    private final Lazy bytes$delegate;
    @NotNull
    private final ContentType contentType;
    @Nullable
    private final HttpStatusCode status;
    @NotNull
    private final String text;

    public /* synthetic */ NullContentLengthTextContent(String str, ContentType contentType, HttpStatusCode httpStatusCode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, contentType, (i & 4) != 0 ? null : httpStatusCode);
    }

    private final byte[] getBytes() {
        return (byte[]) this.bytes$delegate.getValue();
    }

    @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
    @NotNull
    public byte[] bytes() {
        return getBytes();
    }

    @Override // io.ktor.http.content.OutgoingContent
    @Nullable
    public Long getContentLength() {
        int i = Build.VERSION.SDK_INT;
        return Long.valueOf(getBytes().length);
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

    @NotNull
    public final String getText() {
        return this.text;
    }

    @NotNull
    public String toString() {
        String take;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TextContent[");
        outline107.append(getContentType());
        outline107.append("] \"");
        take = StringsKt___StringsKt.take(this.text, 30);
        outline107.append(take);
        outline107.append('\"');
        return outline107.toString();
    }

    public NullContentLengthTextContent(@NotNull String text, @NotNull ContentType contentType, @Nullable HttpStatusCode httpStatusCode) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.text = text;
        this.contentType = contentType;
        this.status = httpStatusCode;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new NullContentLengthTextContent$bytes$2(this));
        this.bytes$delegate = lazy;
    }
}
