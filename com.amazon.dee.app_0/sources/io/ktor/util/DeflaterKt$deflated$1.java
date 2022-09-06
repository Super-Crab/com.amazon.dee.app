package io.ktor.util;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deflater.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.DeflaterKt$deflated$1", f = "Deflater.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4}, l = {73, 78, 83, 87, 90}, m = "invokeSuspend", n = {"crc", "deflater", "input", "compressed", "crc", "deflater", "input", "compressed", "crc", "deflater", "input", "compressed", "crc", "deflater", "input", "compressed", "crc", "deflater", "input", "compressed"}, s = {"L$1", "L$2", "L$3", "L$4", "L$1", "L$2", "L$3", "L$4", "L$1", "L$2", "L$3", "L$4", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class DeflaterKt$deflated$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $gzip;
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ ByteReadChannel $this_deflated;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Deflater.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.util.DeflaterKt$deflated$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ Deflater $deflater;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Deflater deflater) {
            super(0);
            this.$deflater = deflater;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
            return Boolean.valueOf(mo12560invoke());
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Boolean] */
        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: collision with other method in class */
        public final Boolean mo12560invoke() {
            return !this.$deflater.needsInput();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Deflater.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.util.DeflaterKt$deflated$1$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ Deflater $deflater;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Deflater deflater) {
            super(0);
            this.$deflater = deflater;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
            return Boolean.valueOf(mo12560invoke());
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Boolean] */
        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: collision with other method in class */
        public final Boolean mo12560invoke() {
            return !this.$deflater.finished();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeflaterKt$deflated$1(ByteReadChannel byteReadChannel, ObjectPool objectPool, boolean z, Continuation continuation) {
        super(2, continuation);
        this.$this_deflated = byteReadChannel;
        this.$pool = objectPool;
        this.$gzip = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeflaterKt$deflated$1 deflaterKt$deflated$1 = new DeflaterKt$deflated$1(this.$this_deflated, this.$pool, this.$gzip, completion);
        deflaterKt$deflated$1.p$ = (WriterScope) obj;
        return deflaterKt$deflated$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((DeflaterKt$deflated$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:52|(1:(1:(4:(1:(4:58|59|60|(3:62|44|45)(2:63|64))(2:69|70))(4:71|72|73|(5:75|38|(2:40|(1:42))|44|45)(2:76|77))|68|32|33)(3:81|82|(4:84|15|16|(2:18|(1:20)(8:21|22|23|(2:26|(1:28))|25|15|16|(2:34|(1:36)(5:37|38|(0)|44|45))(0)))(0))(2:85|86)))(3:87|88|(8:90|22|23|(0)|25|15|16|(0)(0))(2:91|92)))(3:93|94|(1:96)(2:97|98)))(2:3|(4:5|6|7|(2:9|(1:11)))(2:50|51))|14|15|16|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d0, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01d1, code lost:
        r6 = r1;
        r1 = r14;
        r14 = r2;
     */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00da: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:46:0x00da */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0131 A[Catch: all -> 0x01d0, TRY_LEAVE, TryCatch #0 {all -> 0x01d0, blocks: (B:75:0x01a7, B:77:0x01ab, B:57:0x0129, B:59:0x0131, B:71:0x0187), top: B:90:0x0129 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015c A[Catch: all -> 0x0181, TRY_LEAVE, TryCatch #1 {all -> 0x0181, blocks: (B:63:0x014f, B:66:0x015c), top: B:92:0x014f }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0187 A[Catch: all -> 0x01d0, TRY_ENTER, TryCatch #0 {all -> 0x01d0, blocks: (B:75:0x01a7, B:77:0x01ab, B:57:0x0129, B:59:0x0131, B:71:0x0187), top: B:90:0x0129 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ab A[Catch: all -> 0x01d0, TRY_LEAVE, TryCatch #0 {all -> 0x01d0, blocks: (B:75:0x01a7, B:77:0x01ab, B:57:0x0129, B:59:0x0131, B:71:0x0187), top: B:90:0x0129 }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0157 -> B:90:0x0129). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.DeflaterKt$deflated$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
