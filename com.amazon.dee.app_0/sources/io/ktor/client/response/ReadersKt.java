package io.ktor.client.response;

import kotlin.Metadata;
/* compiled from: readers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"discardRemaining", "", "Lio/ktor/client/response/HttpResponse;", "(Lio/ktor/client/response/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBytes", "", "count", "", "(Lio/ktor/client/response/HttpResponse;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ReadersKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object discardRemaining(@org.jetbrains.annotations.NotNull io.ktor.client.response.HttpResponse r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.client.response.ReadersKt$discardRemaining$1
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.client.response.ReadersKt$discardRemaining$1 r0 = (io.ktor.client.response.ReadersKt$discardRemaining$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.response.ReadersKt$discardRemaining$1 r0 = new io.ktor.client.response.ReadersKt$discardRemaining$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            io.ktor.client.response.HttpResponse r4 = (io.ktor.client.response.HttpResponse) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L4e
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L51
            kotlinx.coroutines.io.ByteReadChannel r5 = r4.getContent()
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r4 = kotlinx.coroutines.io.ByteReadChannelKt.discard(r5, r0)
            if (r4 != r1) goto L4e
            return r1
        L4e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L51:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.response.ReadersKt.discardRemaining(io.ktor.client.response.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(@org.jetbrains.annotations.NotNull io.ktor.client.response.HttpResponse r4, int r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super byte[]> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.response.ReadersKt$readBytes$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.response.ReadersKt$readBytes$1 r0 = (io.ktor.client.response.ReadersKt$readBytes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.response.ReadersKt$readBytes$1 r0 = new io.ktor.client.response.ReadersKt$readBytes$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L45
            if (r2 != r3) goto L3d
            java.lang.Object r4 = r0.L$2
            byte[] r4 = (byte[]) r4
            java.lang.Object r4 = r0.L$1
            byte[] r4 = (byte[]) r4
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$0
            io.ktor.client.response.HttpResponse r5 = (io.ktor.client.response.HttpResponse) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L38
            goto L61
        L38:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        L3d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L45:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L62
            byte[] r6 = new byte[r5]
            kotlinx.coroutines.io.ByteReadChannel r2 = r4.getContent()
            r0.L$0 = r4
            r0.I$0 = r5
            r0.L$1 = r6
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r4 = kotlinx.coroutines.io.ByteReadChannelKt.readFully(r2, r6, r0)
            if (r4 != r1) goto L60
            return r1
        L60:
            r4 = r6
        L61:
            return r4
        L62:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.response.ReadersKt.readBytes(io.ktor.client.response.HttpResponse, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(@org.jetbrains.annotations.NotNull io.ktor.client.response.HttpResponse r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super byte[]> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.client.response.ReadersKt$readBytes$3
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.response.ReadersKt$readBytes$3 r0 = (io.ktor.client.response.ReadersKt$readBytes$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.response.ReadersKt$readBytes$3 r0 = new io.ktor.client.response.ReadersKt$readBytes$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r6 = r0.L$0
            io.ktor.client.response.HttpResponse r6 = (io.ktor.client.response.HttpResponse) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L2e
            goto L53
        L2e:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5c
            kotlinx.coroutines.io.ByteReadChannel r7 = r6.getContent()
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.io.ByteReadChannelKt.readRemaining(r7, r4, r0)
            if (r7 != r1) goto L53
            return r1
        L53:
            kotlinx.io.core.ByteReadPacket r7 = (kotlinx.io.core.ByteReadPacket) r7
            r6 = 0
            r0 = 0
            byte[] r6 = kotlinx.io.core.StringsKt.readBytes$default(r7, r6, r3, r0)
            return r6
        L5c:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.response.ReadersKt.readBytes(io.ktor.client.response.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
