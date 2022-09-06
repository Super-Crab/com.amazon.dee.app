package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Delimited.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSession;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class DelimitedKt$readUntilDelimiter$2 extends Lambda implements Function1<LookAheadSession, Unit> {
    final /* synthetic */ Ref.IntRef $copied;
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ ByteBuffer $dst;
    final /* synthetic */ Ref.BooleanRef $endFound;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$readUntilDelimiter$2(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, Ref.BooleanRef booleanRef, Ref.IntRef intRef) {
        super(1);
        this.$delimiter = byteBuffer;
        this.$dst = byteBuffer2;
        this.$endFound = booleanRef;
        this.$copied = intRef;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(LookAheadSession lookAheadSession) {
        invoke2(lookAheadSession);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull LookAheadSession receiver$0) {
        int tryCopyUntilDelimiter;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        do {
            tryCopyUntilDelimiter = DelimitedKt.tryCopyUntilDelimiter(receiver$0, this.$delimiter, this.$dst);
            if (tryCopyUntilDelimiter == 0) {
                return;
            }
            if (tryCopyUntilDelimiter < 0) {
                this.$endFound.element = true;
                tryCopyUntilDelimiter = -tryCopyUntilDelimiter;
            }
            this.$copied.element += tryCopyUntilDelimiter;
            if (!this.$dst.hasRemaining()) {
                return;
            }
        } while (!this.$endFound.element);
    }
}
