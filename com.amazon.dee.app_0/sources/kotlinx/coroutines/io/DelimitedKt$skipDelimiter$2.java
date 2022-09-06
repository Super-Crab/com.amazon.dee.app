package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* compiled from: Delimited.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSession;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class DelimitedKt$skipDelimiter$2 extends Lambda implements Function1<LookAheadSession, Unit> {
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ Ref.BooleanRef $found;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$skipDelimiter$2(Ref.BooleanRef booleanRef, ByteBuffer byteBuffer) {
        super(1);
        this.$found = booleanRef;
        this.$delimiter = byteBuffer;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(LookAheadSession lookAheadSession) {
        invoke2(lookAheadSession);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull LookAheadSession receiver$0) {
        int tryEnsureDelimiter;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Ref.BooleanRef booleanRef = this.$found;
        tryEnsureDelimiter = DelimitedKt.tryEnsureDelimiter(receiver$0, this.$delimiter);
        booleanRef.element = tryEnsureDelimiter == this.$delimiter.remaining();
    }
}
