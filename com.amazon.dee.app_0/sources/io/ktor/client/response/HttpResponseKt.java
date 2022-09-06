package io.ktor.client.response;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0010\b\u0002\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"readText", "", "Lio/ktor/client/response/HttpResponse;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "(Lio/ktor/client/response/HttpResponse;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpResponseKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readText(@org.jetbrains.annotations.NotNull io.ktor.client.response.HttpResponse r6, @org.jetbrains.annotations.Nullable java.nio.charset.Charset r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.response.HttpResponseKt$readText$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.response.HttpResponseKt$readText$1 r0 = (io.ktor.client.response.HttpResponseKt$readText$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.response.HttpResponseKt$readText$1 r0 = new io.ktor.client.response.HttpResponseKt$readText$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r6 = r0.L$1
            r7 = r6
            java.nio.charset.Charset r7 = (java.nio.charset.Charset) r7
            java.lang.Object r6 = r0.L$0
            io.ktor.client.response.HttpResponse r6 = (io.ktor.client.response.HttpResponse) r6
            boolean r0 = r8 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L33
            goto L5a
        L33:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7a
            kotlinx.coroutines.io.ByteReadChannel r8 = r6.getContent()
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.io.ByteReadChannelKt.readRemaining(r8, r4, r0)
            if (r8 != r1) goto L5a
            return r1
        L5a:
            kotlinx.io.core.ByteReadPacket r8 = (kotlinx.io.core.ByteReadPacket) r8
            java.nio.charset.Charset r0 = io.ktor.http.HttpMessagePropertiesKt.charset(r6)
            if (r0 == 0) goto L63
            r7 = r0
        L63:
            if (r7 == 0) goto L66
            goto L72
        L66:
            io.ktor.client.call.HttpClientCall r6 = r6.getCall()
            io.ktor.client.response.HttpResponseConfig r6 = r6.getResponseConfig()
            java.nio.charset.Charset r7 = r6.getDefaultCharset()
        L72:
            r6 = 0
            r0 = 2
            r1 = 0
            java.lang.String r6 = kotlinx.io.core.StringsKt.readText$default(r8, r7, r6, r0, r1)
            return r6
        L7a:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.response.HttpResponseKt.readText(io.ktor.client.response.HttpResponse, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static /* synthetic */ Object readText$default(HttpResponse httpResponse, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = null;
        }
        return readText(httpResponse, charset, continuation);
    }
}
