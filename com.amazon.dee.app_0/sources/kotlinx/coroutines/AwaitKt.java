package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
/* compiled from: Await.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"awaitAll", "", ExifInterface.GPS_DIRECTION_TRUE, "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AwaitKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object awaitAll(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Deferred<? extends T>[] r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends T>> r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.AwaitKt$awaitAll$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.AwaitKt$awaitAll$1 r0 = (kotlinx.coroutines.AwaitKt$awaitAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.AwaitKt$awaitAll$1 r0 = new kotlinx.coroutines.AwaitKt$awaitAll$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.Deferred[] r4 = (kotlinx.coroutines.Deferred[]) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L55
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            int r5 = r4.length
            if (r5 != 0) goto L3d
            r5 = r3
            goto L3e
        L3d:
            r5 = 0
        L3e:
            if (r5 == 0) goto L45
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            goto L58
        L45:
            kotlinx.coroutines.AwaitAll r5 = new kotlinx.coroutines.AwaitAll
            r5.<init>(r4)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.await(r0)
            if (r5 != r1) goto L55
            return r1
        L55:
            r4 = r5
            java.util.List r4 = (java.util.List) r4
        L58:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.awaitAll(kotlinx.coroutines.Deferred[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0075  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0070 -> B:19:0x0073). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object joinAll(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Job[] r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.AwaitKt$joinAll$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.AwaitKt$joinAll$1 r0 = (kotlinx.coroutines.AwaitKt$joinAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.AwaitKt$joinAll$1 r0 = new kotlinx.coroutines.AwaitKt$joinAll$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4d
            if (r2 != r3) goto L45
            java.lang.Object r7 = r0.L$4
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            int r7 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$2
            kotlinx.coroutines.Job[] r4 = (kotlinx.coroutines.Job[]) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.Job[] r5 = (kotlinx.coroutines.Job[]) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.Job[] r6 = (kotlinx.coroutines.Job[]) r6
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r4
            r4 = r5
            r5 = r1
            r1 = r6
            goto L73
        L45:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4d:
            kotlin.ResultKt.throwOnFailure(r8)
            int r8 = r7.length
            r2 = 0
            r4 = r7
            r5 = r1
            r1 = r4
            r7 = r2
            r2 = r8
            r8 = r1
        L58:
            if (r7 >= r2) goto L75
            r6 = r8[r7]
            r0.L$0 = r1
            r0.L$1 = r4
            r0.L$2 = r8
            r0.I$0 = r2
            r0.I$1 = r7
            r0.L$3 = r6
            r0.L$4 = r6
            r0.label = r3
            java.lang.Object r6 = r6.join(r0)
            if (r6 != r5) goto L73
            return r5
        L73:
            int r7 = r7 + r3
            goto L58
        L75:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(kotlinx.coroutines.Job[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object awaitAll(@org.jetbrains.annotations.NotNull java.util.Collection<? extends kotlinx.coroutines.Deferred<? extends T>> r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends T>> r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.AwaitKt$awaitAll$2
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.AwaitKt$awaitAll$2 r0 = (kotlinx.coroutines.AwaitKt$awaitAll$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.AwaitKt$awaitAll$2 r0 = new kotlinx.coroutines.AwaitKt$awaitAll$2
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            java.util.Collection r4 = (java.util.Collection) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L5e
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L43
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            goto L61
        L43:
            r5 = 0
            kotlinx.coroutines.Deferred[] r5 = new kotlinx.coroutines.Deferred[r5]
            java.lang.Object[] r5 = r4.toArray(r5)
            if (r5 == 0) goto L62
            kotlinx.coroutines.Deferred[] r5 = (kotlinx.coroutines.Deferred[]) r5
            kotlinx.coroutines.AwaitAll r2 = new kotlinx.coroutines.AwaitAll
            r2.<init>(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r2.await(r0)
            if (r5 != r1) goto L5e
            return r1
        L5e:
            r4 = r5
            java.util.List r4 = (java.util.List) r4
        L61:
            return r4
        L62:
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Array<T>"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.awaitAll(java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object joinAll(@org.jetbrains.annotations.NotNull java.util.Collection<? extends kotlinx.coroutines.Job> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.AwaitKt$joinAll$3
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = (kotlinx.coroutines.AwaitKt$joinAll$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = new kotlinx.coroutines.AwaitKt$joinAll$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L47
            if (r2 != r3) goto L3f
            java.lang.Object r7 = r0.L$4
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            java.lang.Object r7 = r0.L$3
            java.lang.Object r7 = r0.L$2
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.L$1
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r4 = r0.L$0
            java.util.Collection r4 = (java.util.Collection) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r4
            r6 = r2
            r2 = r1
            r1 = r6
            goto L52
        L3f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L47:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.Iterator r8 = r7.iterator()
            r2 = r1
            r1 = r7
            r7 = r8
            r8 = r1
        L52:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L72
            java.lang.Object r4 = r7.next()
            r5 = r4
            kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5
            r0.L$0 = r8
            r0.L$1 = r1
            r0.L$2 = r7
            r0.L$3 = r4
            r0.L$4 = r5
            r0.label = r3
            java.lang.Object r4 = r5.join(r0)
            if (r4 != r2) goto L52
            return r2
        L72:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
