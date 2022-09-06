package io.ktor.http.content;

import kotlin.Metadata;
/* compiled from: Multipart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"forEachPart", "", "Lio/ktor/http/content/MultiPartData;", "partHandler", "Lkotlin/Function2;", "Lio/ktor/http/content/PartData;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/http/content/MultiPartData;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAllParts", "", "(Lio/ktor/http/content/MultiPartData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class MultipartKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x007c -> B:27:0x005f). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object forEachPart(@org.jetbrains.annotations.NotNull io.ktor.http.content.MultiPartData r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super io.ktor.http.content.PartData, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.http.content.MultipartKt$forEachPart$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.http.content.MultipartKt$forEachPart$1 r0 = (io.ktor.http.content.MultipartKt$forEachPart$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.content.MultipartKt$forEachPart$1 r0 = new io.ktor.http.content.MultipartKt$forEachPart$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L58
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r6 = r0.L$2
            io.ktor.http.content.PartData r6 = (io.ktor.http.content.PartData) r6
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.http.content.MultiPartData r7 = (io.ktor.http.content.MultiPartData) r7
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L39
            goto L5f
        L39:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.http.content.MultiPartData r7 = (io.ktor.http.content.MultiPartData) r7
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L53
            goto L6c
        L53:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L58:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L82
            r5 = r7
            r7 = r6
            r6 = r5
        L5f:
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r8 = r7.readPart(r0)
            if (r8 != r1) goto L6c
            return r1
        L6c:
            io.ktor.http.content.PartData r8 = (io.ktor.http.content.PartData) r8
            if (r8 == 0) goto L7f
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r8 = r6.mo12248invoke(r8, r0)
            if (r8 != r1) goto L5f
            return r1
        L7f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L82:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.MultipartKt.forEachPart(io.ktor.http.content.MultiPartData, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x007e -> B:35:0x0081). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readAllParts(@org.jetbrains.annotations.NotNull io.ktor.http.content.MultiPartData r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends io.ktor.http.content.PartData>> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.http.content.MultipartKt$readAllParts$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.http.content.MultipartKt$readAllParts$1 r0 = (io.ktor.http.content.MultipartKt$readAllParts$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.content.MultipartKt$readAllParts$1 r0 = new io.ktor.http.content.MultipartKt$readAllParts$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r6 = r0.L$2
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r2 = r0.L$1
            io.ktor.http.content.PartData r2 = (io.ktor.http.content.PartData) r2
            java.lang.Object r2 = r0.L$0
            io.ktor.http.content.MultiPartData r2 = (io.ktor.http.content.MultiPartData) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L39
            goto L81
        L39:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            java.lang.Object r6 = r0.L$0
            io.ktor.http.content.MultiPartData r6 = (io.ktor.http.content.MultiPartData) r6
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L4f
            goto L63
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8f
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.readPart(r0)
            if (r7 != r1) goto L63
            return r1
        L63:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7
            if (r7 == 0) goto L8a
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r2.add(r7)
            r5 = r2
            r2 = r6
            r6 = r5
        L72:
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r2.readPart(r0)
            if (r7 != r1) goto L81
            return r1
        L81:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7
            if (r7 == 0) goto L89
            r6.add(r7)
            goto L72
        L89:
            return r6
        L8a:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
            return r6
        L8f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.MultipartKt.readAllParts(io.ktor.http.content.MultiPartData, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
