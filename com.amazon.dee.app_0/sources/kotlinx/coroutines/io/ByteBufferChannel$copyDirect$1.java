package kotlinx.coroutines.io;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0080@ø\u0001\u0000"}, d2 = {"copyDirect", "", "src", "Lkotlinx/coroutines/io/ByteBufferChannel;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "joined", "Lkotlinx/coroutines/io/ByteBufferChannel$JoiningState;", "continuation", "Lkotlin/coroutines/Continuation;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {1252, OlympusCameraSettingsMakernoteDirectory.TagPictureMode, OlympusCameraSettingsMakernoteDirectory.TagPictureModeTone}, m = "copyDirect$kotlinx_coroutines_io", n = {"this", "src", MetricsUtil.LegacyMetricTypes.LIMIT, "joined", "autoFlush", "byteOrder", "copied", "this_$iv", "buffer$iv", "capacity$iv", "current$iv", "before$iv", "state", "dstBuffer", "$receiver", "avWBefore", "this", "src", MetricsUtil.LegacyMetricTypes.LIMIT, "joined", "autoFlush", "byteOrder", "copied", "this", "src", MetricsUtil.LegacyMetricTypes.LIMIT, "joined", "autoFlush", "byteOrder", "copied"}, s = {"L$0", "L$1", "J$0", "L$2", "Z$0", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "J$1", "L$9", "L$10", "L$11", "I$0", "L$0", "L$1", "J$0", "L$2", "Z$0", "L$3", "L$4", "L$0", "L$1", "J$0", "L$2", "Z$0", "L$3", "L$4"})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$copyDirect$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$copyDirect$1(ByteBufferChannel byteBufferChannel, Continuation continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.copyDirect$kotlinx_coroutines_io(null, 0L, null, this);
    }
}
