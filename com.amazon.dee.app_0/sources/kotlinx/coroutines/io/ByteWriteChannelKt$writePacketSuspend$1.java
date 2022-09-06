package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.commons.net.telnet.TelnetCommand;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteWriteChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@ø\u0001\u0000"}, d2 = {"writePacketSuspend", "", "Lkotlinx/coroutines/io/ByteWriteChannel;", "builder", "Lkotlin/Function2;", "Lkotlinx/io/core/BytePacketBuilder;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "continuation"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteWriteChannelKt", f = "ByteWriteChannel.kt", i = {0, 0, 0, 0, 0, 1, 1}, l = {CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, TelnetCommand.WONT}, m = "writePacketSuspend", n = {"$receiver", "builder", "headerSizeHint$iv", "builder$iv", "$receiver", "$receiver", "builder"}, s = {"L$0", "L$1", "I$0", "L$2", "L$3", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class ByteWriteChannelKt$writePacketSuspend$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteWriteChannelKt$writePacketSuspend$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteWriteChannelKt.writePacketSuspend(null, null, this);
    }
}
