package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialBase$atLeastNBytesAvailableForRead$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$atLeastNBytesAvailableForRead$1(ByteChannelSequentialBase byteChannelSequentialBase) {
        super(0);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
        return Boolean.valueOf(mo12560invoke());
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final Boolean mo12560invoke() {
        int i;
        int availableForRead = this.this$0.getAvailableForRead();
        i = this.this$0.waitingForRead;
        return (availableForRead >= i || this.this$0.getClosed()) ? 1 : null;
    }
}
