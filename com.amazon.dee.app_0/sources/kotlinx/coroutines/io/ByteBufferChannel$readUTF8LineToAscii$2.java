package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.internal.StringsKt;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSession;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$readUTF8LineToAscii$2 extends Lambda implements Function1<LookAheadSession, Unit> {
    final /* synthetic */ char[] $ca;
    final /* synthetic */ CharBuffer $cb;
    final /* synthetic */ Ref.IntRef $consumed;
    final /* synthetic */ Ref.BooleanRef $eol;
    final /* synthetic */ int $limit;
    final /* synthetic */ Appendable $out;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readUTF8LineToAscii$2(ByteBufferChannel byteBufferChannel, Ref.BooleanRef booleanRef, Appendable appendable, char[] cArr, CharBuffer charBuffer, Ref.IntRef intRef, int i) {
        super(1);
        this.this$0 = byteBufferChannel;
        this.$eol = booleanRef;
        this.$out = appendable;
        this.$ca = cArr;
        this.$cb = charBuffer;
        this.$consumed = intRef;
        this.$limit = i;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(LookAheadSession lookAheadSession) {
        invoke2(lookAheadSession);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull LookAheadSession receiver$0) {
        ByteBuffer request;
        int max;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Ref.BooleanRef booleanRef = this.$eol;
        ByteBufferChannel byteBufferChannel = this.this$0;
        Appendable appendable = this.$out;
        char[] cArr = this.$ca;
        CharBuffer charBuffer = this.$cb;
        boolean z = true;
        int i = 1;
        do {
            if ((this.this$0.getAvailableForRead() >= i) && (request = receiver$0.request(0, 1)) != null) {
                int position = request.position();
                if (request.remaining() < i) {
                    byteBufferChannel.rollBytes(request, i);
                }
                char[] cArr2 = this.$ca;
                long decodeASCIILine = StringsKt.decodeASCIILine(request, cArr2, 0, Math.min(cArr2.length, this.$limit - this.$consumed.element));
                receiver$0.consumed(request.position() - position);
                int i2 = (int) (decodeASCIILine >> 32);
                int i3 = (int) (decodeASCIILine & BodyPartID.bodyIdMax);
                if (i3 == -1) {
                    max = 0;
                } else {
                    max = (i3 != 0 || !request.hasRemaining()) ? Math.max(1, i3) : -1;
                }
                this.$consumed.element += i2;
                if (appendable instanceof StringBuilder) {
                    ((StringBuilder) appendable).append(cArr, 0, i2);
                } else {
                    appendable.append(charBuffer, 0, i2);
                }
                i = max;
            }
        } while (max > 0);
        if (i != 0) {
            z = false;
        }
        booleanRef.element = z;
    }
}
