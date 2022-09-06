package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteReadChannelJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/io/ByteChannel;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ByteReadChannel$Companion$Empty$2 extends Lambda implements Function0<ByteChannel> {
    public static final ByteReadChannel$Companion$Empty$2 INSTANCE = new ByteReadChannel$Companion$Empty$2();

    ByteReadChannel$Companion$Empty$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final ByteChannel mo12560invoke() {
        ByteChannel ByteChannel$default = ByteChannelKt.ByteChannel$default(false, 1, null);
        ByteWriteChannelKt.close(ByteChannel$default);
        return ByteChannel$default;
    }
}
