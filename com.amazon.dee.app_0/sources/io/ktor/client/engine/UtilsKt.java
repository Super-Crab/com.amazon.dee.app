package io.ktor.client.engine;

import io.ktor.client.utils.HeadersKt;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.InternalAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aP\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000526\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¨\u0006\r"}, d2 = {"mergeHeaders", "", "requestHeaders", "Lio/ktor/http/Headers;", "content", "Lio/ktor/http/content/OutgoingContent;", "block", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "key", "value", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt {
    @InternalAPI
    public static final void mergeHeaders(@NotNull Headers requestHeaders, @NotNull OutgoingContent content, @NotNull Function2<? super String, ? super String, Unit> block) {
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HeadersKt.buildHeaders(new UtilsKt$mergeHeaders$1(requestHeaders, content)).forEach(new UtilsKt$mergeHeaders$2(block));
        ContentType contentType = content.getContentType();
        if (contentType == null || (str = contentType.toString()) == null) {
            str = content.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        }
        Long contentLength = content.getContentLength();
        if (contentLength == null || (str2 = String.valueOf(contentLength.longValue())) == null) {
            str2 = content.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        }
        if (str != null) {
            block.mo12248invoke(HttpHeaders.INSTANCE.getContentType(), str);
        }
        if (str2 != null) {
            block.mo12248invoke(HttpHeaders.INSTANCE.getContentLength(), str2);
        }
    }
}
