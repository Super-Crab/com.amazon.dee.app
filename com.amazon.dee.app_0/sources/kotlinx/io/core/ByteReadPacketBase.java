package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.EOFException;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import kotlinx.io.core.internal.MalformedUTF8InputException;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.core.internal.UnsafeKt;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Packet.kt */
@DangerousInternalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b'\u0018\u0000 \u009a\u00012\u00020\u0001:\u0002\u009a\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\b\u0010/\u001a\u000200H\u0002J\u0015\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0003H\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u0002002\u0006\u00105\u001a\u00020\u0003H\u0000¢\u0006\u0002\b6J\u0010\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020!H\u0002J\u0006\u00109\u001a\u00020\u0018J\b\u0010:\u001a\u000200H\u0016J\b\u0010;\u001a\u000200H$J\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020!2\u0006\u0010?\u001a\u00020!J\u000e\u0010>\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005J\u0019\u0010@\u001a\u00020!2\u0006\u0010?\u001a\u00020!2\u0006\u0010A\u001a\u00020!H\u0082\u0010J\u000e\u0010B\u001a\u0002002\u0006\u0010?\u001a\u00020!J\n\u0010C\u001a\u0004\u0018\u00010\u0003H\u0004J\u0012\u0010D\u001a\u0004\u0018\u00010\u00032\u0006\u0010E\u001a\u00020\u0003H\u0001J\u001b\u0010D\u001a\u0004\u0018\u00010\u00032\u0006\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0003H\u0082\u0010J\u0012\u0010G\u001a\u0004\u0018\u00010\u00032\u0006\u0010E\u001a\u00020\u0003H\u0007J\n\u0010H\u001a\u0004\u0018\u00010\u0003H$J\u0010\u0010I\u001a\u0002002\u0006\u0010E\u001a\u00020\u0003H\u0007J\u0010\u0010J\u001a\u0002002\u0006\u0010E\u001a\u00020\u0003H\u0002J \u0010K\u001a\u0002002\u0006\u0010E\u001a\u00020\u00032\u0006\u0010L\u001a\u00020!2\u0006\u0010M\u001a\u00020!H\u0002J\b\u0010,\u001a\u00020!H\u0007J\u000e\u0010N\u001a\u00020\u00182\u0006\u0010?\u001a\u00020!J\r\u0010O\u001a\u000200H\u0000¢\u0006\u0002\bPJ\u0018\u0010Q\u001a\u00020\u00132\u0006\u00108\u001a\u00020!2\u0006\u0010R\u001a\u00020!H\u0002J\u0010\u0010S\u001a\u00020\u00132\u0006\u0010T\u001a\u00020!H\u0002J\u0010\u0010U\u001a\u00020\u00132\u0006\u0010?\u001a\u00020!H\u0002J\u000e\u0010V\u001a\u00020!2\u0006\u0010W\u001a\u00020\u0003J\u0010\u0010X\u001a\u00020\u00132\u0006\u0010L\u001a\u00020!H\u0002J\u0018\u0010Y\u001a\u00020\u00132\u0006\u00108\u001a\u00020!2\u0006\u0010Z\u001a\u00020!H\u0002J\u0018\u0010[\u001a\u0004\u0018\u00010\u00032\u0006\u0010T\u001a\u00020!H\u0080\b¢\u0006\u0002\b\\J\u001b\u0010[\u001a\u0004\u0018\u00010\u00032\u0006\u0010T\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u0003H\u0081\u0010J\u0012\u0010]\u001a\u0004\u0018\u00010\u00032\u0006\u0010T\u001a\u00020!H\u0007J\u001d\u0010^\u001a\u0002002\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002000`H\u0081\bJ%\u0010^\u001a\u0002002\u0006\u0010?\u001a\u00020!2\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002000`H\u0081\bJ$\u0010a\u001a\u00020!2\n\u0010b\u001a\u00060cj\u0002`d2\u0006\u00108\u001a\u00020!2\u0006\u0010R\u001a\u00020!H\u0002J)\u0010e\u001a\u00020!2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!2\u0006\u0010Z\u001a\u00020!H\u0082\u0010J\u000e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020gJ\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020g2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020l2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020m2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020n2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020o2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020p2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u0016\u0010j\u001a\u00020!2\u0006\u0010k\u001a\u00020\u00032\u0006\u0010i\u001a\u00020!J\u0006\u0010q\u001a\u00020rJ\u0010\u0010s\u001a\u00020r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010t\u001a\u00020rH\u0002J%\u0010u\u001a\u00020!2\u0006\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020!2\u0006\u0010y\u001a\u00020!H\u0000¢\u0006\u0002\bzJ\u0006\u0010{\u001a\u00020|J\u0006\u0010}\u001a\u00020~J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020g2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020l2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020m2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020n2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020o2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u001e\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020p2\u0006\u0010h\u001a\u00020!2\u0006\u0010i\u001a\u00020!J\u0016\u0010\u007f\u001a\u0002002\u0006\u0010k\u001a\u00020\u00032\u0006\u0010i\u001a\u00020!J\u0007\u0010\u0080\u0001\u001a\u00020!J\t\u0010\u0081\u0001\u001a\u00020!H\u0002J\u0007\u0010\u0082\u0001\u001a\u00020\u0005J\t\u0010\u0083\u0001\u001a\u00020\u0005H\u0002J;\u0010\u0084\u0001\u001a\u0003H\u0085\u0001\"\u0005\b\u0000\u0010\u0085\u00012\u0006\u0010?\u001a\u00020!2\u0019\u0010_\u001a\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0005\u0012\u0003H\u0085\u00010`¢\u0006\u0003\b\u0086\u0001H\u0082\b¢\u0006\u0003\u0010\u0087\u0001J\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001J\u001c\u0010\u008a\u0001\u001a\u00030\u008b\u00012\b\b\u0002\u00108\u001a\u00020!2\b\b\u0002\u0010R\u001a\u00020!J'\u0010\u008a\u0001\u001a\u00020!2\n\u0010b\u001a\u00060cj\u0002`d2\b\b\u0002\u00108\u001a\u00020!2\b\b\u0002\u0010R\u001a\u00020!J\u0011\u0010\u008c\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u008d\u0001\u001a\u00020!J\u001c\u0010\u008c\u0001\u001a\u0002002\n\u0010b\u001a\u00060cj\u0002`d2\u0007\u0010\u008d\u0001\u001a\u00020!J%\u0010\u008e\u0001\u001a\u00020!2\n\u0010b\u001a\u00060cj\u0002`d2\u0006\u00108\u001a\u00020!2\u0006\u0010R\u001a\u00020!H\u0002J\u0007\u0010\u008f\u0001\u001a\u000200J\u0017\u0010\u0090\u0001\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0003\b\u0091\u0001J\u0011\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0003\b\u0093\u0001J\u0011\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0003\b\u0095\u0001J\u0007\u0010\u0096\u0001\u001a\u00020!J\u0017\u0010\u0097\u0001\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u0003H\u0000¢\u0006\u0003\b\u0098\u0001J\u0011\u0010\u0099\u0001\u001a\u0002002\u0006\u0010\u0004\u001a\u00020!H\u0007R,\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\r\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u0002\u001a\u00020\u00038\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020!8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\"\u0010\r\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b'\u0010\u001aR\u0011\u0010(\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b(\u0010\u001aR\u000e\u0010)\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u009b\u0001"}, d2 = {"Lkotlinx/io/core/ByteReadPacketBase;", "Lkotlinx/io/core/Input;", TtmlNode.TAG_HEAD, "Lkotlinx/io/core/IoBuffer;", "remaining", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "(Lkotlinx/io/core/IoBuffer;JLkotlinx/io/pool/ObjectPool;)V", "newOrder", "Lkotlinx/io/core/ByteOrder;", "byteOrder", "byteOrder$annotations", "()V", "getByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "doNotImplementInputButExtendAbstractInputInstead", "", "doNotImplementInputButExtendAbstractInputInstead$annotations", "getDoNotImplementInputButExtendAbstractInputInstead", "()Ljava/lang/Void;", "endOfInput", "", "getEndOfInput", "()Z", "head$annotations", "getHead", "()Lkotlinx/io/core/IoBuffer;", "setHead", "(Lkotlinx/io/core/IoBuffer;)V", "headRemaining", "", "headRemaining$annotations", "getHeadRemaining", "()I", "setHeadRemaining", "(I)V", "isEmpty", "isNotEmpty", "noMoreChunksAvailable", "getPool", "()Lkotlinx/io/pool/ObjectPool;", "getRemaining", "()J", "tailRemaining", "afterRead", "", "append", "chain", "append$kotlinx_io", "appendView", "chunk", "appendView$kotlinx_io", "atLeastMinCharactersRequire", ReactProperties.GEOFENCE_MINIMUM_RANGE, "canRead", "close", "closeSource", "copy", "Lkotlinx/io/core/ByteReadPacket;", "discard", JsonReportFormat.COUNT, "discardAsMuchAsPossible", "skipped", "discardExact", "doFill", "ensureNext", "current", MetricKeys.EMPTY_VALUE, "ensureNextHead", "fill", "fixGapAfterRead", "fixGapAfterReadFallback", "fixGapAfterReadFallbackUnreserved", "size", "overrun", "hasBytes", "markNoMoreChunksAvailable", "markNoMoreChunksAvailable$kotlinx_io", "minShouldBeLess", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "minSizeIsTooBig", "minSize", "notEnoughBytesAvailable", "peekTo", "buffer", "prematureEndOfStream", "prematureEndOfStreamChars", "copied", "prepareRead", "prepareRead$kotlinx_io", "prepareReadHead", "read", "block", "Lkotlin/Function1;", "readASCII", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "readAsMuchAsPossible", "array", "", "offset", "length", "readAvailable", "dst", "", "", "", "", "", "readByte", "", "readByteSlow", "readByteSlow2", "readCbuf", "cbuf", "", "off", "len", "readCbuf$kotlinx_io", "readDouble", "", "readFloat", "", "readFully", "readInt", "readIntSlow", "readLong", "readLongSlow", "readN", "R", "Lkotlin/ExtensionFunctionType;", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readShort", "", "readText", "", "readTextExact", "exactCharacters", "readUtf8", "release", "releaseHead", "releaseHead$kotlinx_io", "steal", "steal$kotlinx_io", "stealAll", "stealAll$kotlinx_io", "tryPeek", "tryWriteAppend", "tryWriteAppend$kotlinx_io", "updateHeadRemaining", "Companion", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class ByteReadPacketBase implements Input {
    public static final Companion Companion = new Companion(null);
    private static final int ReservedSize = IoBuffer.Companion.getReservedSize();
    @NotNull
    private ByteOrder byteOrder;
    @NotNull
    private IoBuffer head;
    private int headRemaining;
    private boolean noMoreChunksAvailable;
    @NotNull
    private final ObjectPool<IoBuffer> pool;
    private long tailRemaining;

    /* compiled from: Packet.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlinx/io/core/ByteReadPacketBase$Companion;", "", "()V", "Empty", "Lkotlinx/io/core/ByteReadPacket;", "Empty$annotations", "getEmpty", "()Lkotlinx/io/core/ByteReadPacket;", "ReservedSize", "", "ReservedSize$annotations", "getReservedSize", "()I", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use ByteReadPacket.Empty instead", replaceWith = @ReplaceWith(expression = "ByteReadPacket.Empty", imports = {}))
        public static /* synthetic */ void Empty$annotations() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use IoBuffer.ReservedSize instead", replaceWith = @ReplaceWith(expression = "IoBuffer.ReservedSize", imports = {"kotlinx.io.core.IoBuffer"}))
        public static /* synthetic */ void ReservedSize$annotations() {
        }

        @NotNull
        public final ByteReadPacket getEmpty() {
            return ByteReadPacket.Companion.getEmpty();
        }

        public final int getReservedSize() {
            return ByteReadPacketBase.ReservedSize;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ByteReadPacketBase(@NotNull IoBuffer head, long j, @NotNull ObjectPool<IoBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.head = head;
        this.pool = pool;
        PacketKt.access$setByteOrderForNonEmpty(this.head, ByteOrder.BIG_ENDIAN);
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.headRemaining = this.head.getReadRemaining();
        this.tailRemaining = j - this.headRemaining;
    }

    private final void afterRead() {
        IoBuffer ioBuffer = this.head;
        if (ioBuffer.getReadRemaining() == 0) {
            releaseHead$kotlinx_io(ioBuffer);
        }
    }

    private final Void atLeastMinCharactersRequire(int i) {
        throw new EOFException(GeneratedOutlineSupport1.outline52("at least ", i, " characters required but no bytes available"));
    }

    @Deprecated(message = "Use readXXXLittleEndian or readXXX then X.reverseByteOrder() instead.")
    public static /* synthetic */ void byteOrder$annotations() {
    }

    private final int discardAsMuchAsPossible(int i, int i2) {
        while (i != 0) {
            IoBuffer prepareRead = prepareRead(1, getHead());
            if (prepareRead == null) {
                return i2;
            }
            int min = Math.min(prepareRead.getReadRemaining(), i);
            prepareRead.discardExact(min);
            this.headRemaining -= min;
            afterRead();
            i -= min;
            i2 += min;
        }
        return i2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Suppress warning.")
    public static /* synthetic */ void doNotImplementInputButExtendAbstractInputInstead$annotations() {
    }

    private final void fixGapAfterReadFallback(IoBuffer ioBuffer) {
        if (this.noMoreChunksAvailable) {
            this.headRemaining = ioBuffer.getReadRemaining();
            this.tailRemaining = 0L;
            return;
        }
        int readRemaining = ioBuffer.getReadRemaining();
        int min = Math.min(readRemaining, IoBuffer.Companion.getReservedSize() - ioBuffer.getEndGap());
        if (readRemaining > min) {
            fixGapAfterReadFallbackUnreserved(ioBuffer, readRemaining, min);
        } else {
            IoBuffer mo12351borrow = this.pool.mo12351borrow();
            mo12351borrow.reserveEndGap(IoBuffer.Companion.getReservedSize());
            mo12351borrow.setNext(ioBuffer.getNext());
            mo12351borrow.writeBufferAppend$kotlinx_io(ioBuffer, readRemaining);
            this.head = mo12351borrow;
            this.headRemaining = readRemaining;
            this.tailRemaining = 0L;
        }
        ioBuffer.release(this.pool);
    }

    private final void fixGapAfterReadFallbackUnreserved(IoBuffer ioBuffer, int i, int i2) {
        IoBuffer mo12351borrow = this.pool.mo12351borrow();
        IoBuffer mo12351borrow2 = this.pool.mo12351borrow();
        mo12351borrow.reserveEndGap(IoBuffer.Companion.getReservedSize());
        mo12351borrow2.reserveEndGap(IoBuffer.Companion.getReservedSize());
        mo12351borrow.setNext(mo12351borrow2);
        mo12351borrow2.setNext(ioBuffer.getNext());
        mo12351borrow.writeBufferAppend$kotlinx_io(ioBuffer, i - i2);
        mo12351borrow2.writeBufferAppend$kotlinx_io(ioBuffer, i2);
        this.head = mo12351borrow;
        this.headRemaining = mo12351borrow.getReadRemaining();
        this.tailRemaining = mo12351borrow2.getReadRemaining();
    }

    @PublishedApi
    public static /* synthetic */ void head$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void headRemaining$annotations() {
    }

    private final Void minShouldBeLess(int i, int i2) {
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("min should be less or equal to max but min = ", i, ", max = ", i2));
    }

    private final Void minSizeIsTooBig(int i) {
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("minSize of ", i, " is too big (should be less than ");
        outline109.append(IoBuffer.Companion.getReservedSize());
        throw new IllegalStateException(outline109.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Void notEnoughBytesAvailable(int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not enough data in packet (");
        outline107.append(m12336getRemaining());
        outline107.append(") to read ");
        outline107.append(i);
        outline107.append(" byte(s)");
        throw new EOFException(outline107.toString());
    }

    private final Void prematureEndOfStream(int i) {
        throw new MalformedUTF8InputException(GeneratedOutlineSupport1.outline52("Premature end of stream: expected ", i, " bytes"));
    }

    private final Void prematureEndOfStreamChars(int i, int i2) {
        throw new MalformedUTF8InputException(GeneratedOutlineSupport1.outline53("Premature end of stream: expected at least ", i, " chars but had only ", i2));
    }

    private final int readASCII(Appendable appendable, int i, int i2) {
        int i3;
        boolean z;
        boolean z2;
        int i4;
        boolean z3;
        boolean z4 = false;
        if (i2 == 0 && i == 0) {
            return 0;
        }
        if (isEmpty()) {
            if (i == 0) {
                return 0;
            }
            atLeastMinCharactersRequire(i);
            throw null;
        } else if (i2 >= i) {
            boolean z5 = true;
            IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(this, 1);
            if (prepareReadFirstHead != null) {
                int i5 = 0;
                boolean z6 = false;
                while (true) {
                    try {
                        int readRemaining = prepareReadFirstHead.getReadRemaining();
                        i3 = i5;
                        int i6 = 0;
                        while (i6 < readRemaining) {
                            int readByte = prepareReadFirstHead.readByte() & 255;
                            if ((readByte & 128) == 0) {
                                char c = (char) readByte;
                                if (i3 == i2) {
                                    i4 = i3;
                                    z3 = false;
                                } else {
                                    appendable.append(c);
                                    i4 = i3 + 1;
                                    z3 = true;
                                }
                                if (!z3) {
                                    i3 = i4;
                                } else {
                                    i6++;
                                    i3 = i4;
                                }
                            }
                            prepareReadFirstHead.pushBack(1);
                            z = false;
                            break;
                        }
                        z = true;
                        if (z) {
                            z2 = true;
                        } else if (i3 == i2) {
                            z2 = false;
                        } else {
                            z2 = false;
                            z6 = true;
                        }
                        if (!z2) {
                            z4 = true;
                            break;
                        }
                        try {
                            IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(this, prepareReadFirstHead);
                            if (prepareReadNextHead == null) {
                                break;
                            }
                            prepareReadFirstHead = prepareReadNextHead;
                            i5 = i3;
                        } catch (Throwable th) {
                            th = th;
                            z5 = false;
                            if (z5) {
                                UnsafeKt.completeReadHead(this, prepareReadFirstHead);
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (z4) {
                    UnsafeKt.completeReadHead(this, prepareReadFirstHead);
                }
                z4 = z6;
            } else {
                i3 = 0;
            }
            if (z4) {
                return i3 + readUtf8(appendable, i - i3, i2 - i3);
            }
            if (i3 >= i) {
                return i3;
            }
            prematureEndOfStreamChars(i, i3);
            throw null;
        } else {
            minShouldBeLess(i, i2);
            throw null;
        }
    }

    private final int readAsMuchAsPossible(byte[] bArr, int i, int i2, int i3) {
        while (i2 != 0) {
            IoBuffer prepareRead = prepareRead(1, getHead());
            if (prepareRead == null) {
                return i3;
            }
            int min = Math.min(i2, prepareRead.getReadRemaining());
            prepareRead.readFully(bArr, i, min);
            this.headRemaining -= min;
            if (min == i2 && prepareRead.getReadRemaining() != 0) {
                return i3 + min;
            }
            afterRead();
            i += min;
            i2 -= min;
            i3 += min;
        }
        return i3;
    }

    private final byte readByteSlow(IoBuffer ioBuffer) {
        if (ensureNext(ioBuffer) != null) {
            return readByte();
        }
        throw new EOFException("One more byte required but reached end of input");
    }

    private final byte readByteSlow2() {
        IoBuffer ioBuffer = this.head;
        int i = this.headRemaining;
        if (i == 1) {
            this.headRemaining = i - 1;
            byte readByte = ioBuffer.readByte();
            ensureNext(ioBuffer);
            return readByte;
        }
        return readByteSlow(ioBuffer);
    }

    private final int readIntSlow() {
        IoBuffer prepareRead = prepareRead(4, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(4);
            throw null;
        }
        int readInt = prepareRead.readInt();
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return readInt;
    }

    private final long readLongSlow() {
        IoBuffer prepareRead = prepareRead(8, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(8);
            throw null;
        }
        long readLong = prepareRead.readLong();
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return readLong;
    }

    private final <R> R readN(int i, Function1<? super IoBuffer, ? extends R> function1) {
        IoBuffer prepareRead = prepareRead(i, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(i);
            throw null;
        }
        R mo12165invoke = function1.mo12165invoke(prepareRead);
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return mo12165invoke;
    }

    public static /* synthetic */ int readText$default(ByteReadPacketBase byteReadPacketBase, Appendable appendable, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = Integer.MAX_VALUE;
            }
            return byteReadPacketBase.readText(appendable, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readText");
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ba, code lost:
        r1.pushBack(r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int readUtf8(java.lang.Appendable r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readUtf8(java.lang.Appendable, int, int):int");
    }

    public final void append$kotlinx_io(@NotNull IoBuffer chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        if (chain == IoBuffer.Companion.getEmpty()) {
            return;
        }
        long remainingAll = BuffersKt.remainingAll(chain);
        if (this.head == IoBuffer.Companion.getEmpty()) {
            this.head = chain;
            this.headRemaining = chain.getReadRemaining();
            this.tailRemaining = remainingAll - this.headRemaining;
        } else {
            BuffersKt.findTail(this.head).setNext(chain);
            this.tailRemaining += remainingAll;
        }
        chain.setByteOrder(this.byteOrder);
    }

    public final void appendView$kotlinx_io(@NotNull IoBuffer chunk) {
        Intrinsics.checkParameterIsNotNull(chunk, "chunk");
        IoBuffer findTail = BuffersKt.findTail(this.head);
        if (findTail == IoBuffer.Companion.getEmpty()) {
            this.head = chunk;
            chunk.setByteOrder(this.byteOrder);
            long j = 0;
            if (this.tailRemaining == 0) {
                this.headRemaining = chunk.getReadRemaining();
                IoBuffer next = chunk.getNext();
                if (next != null) {
                    j = BuffersKt.remainingAll(next);
                }
                this.tailRemaining = j;
                return;
            }
            new RequireFailureCapture() { // from class: kotlinx.io.core.ByteReadPacketBase$appendView$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    throw new IllegalStateException("It should be no tail remaining bytes if current tail is EmptyBuffer");
                }
            }.doFail();
            throw null;
        }
        findTail.setNext(chunk);
        this.tailRemaining = BuffersKt.remainingAll(chunk) + this.tailRemaining;
    }

    public final boolean canRead() {
        return this.tailRemaining != 0 || this.head.canRead();
    }

    @Override // kotlinx.io.core.Input, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release();
        if (!this.noMoreChunksAvailable) {
            this.noMoreChunksAvailable = true;
        }
        closeSource();
    }

    protected abstract void closeSource();

    @NotNull
    public final ByteReadPacket copy() {
        return new ByteReadPacket(BuffersKt.copyAll(this.head), m12336getRemaining(), this.pool);
    }

    public final int discard(int i) {
        return discardAsMuchAsPossible(i, 0);
    }

    public final void discardExact(int i) {
        if (discard(i) == i) {
            return;
        }
        throw new EOFException(GeneratedOutlineSupport1.outline52("Unable to discard ", i, " bytes due to end of packet"));
    }

    @Nullable
    protected final IoBuffer doFill() {
        if (this.noMoreChunksAvailable) {
            return null;
        }
        IoBuffer mo12334fill = mo12334fill();
        if (mo12334fill == null) {
            this.noMoreChunksAvailable = true;
            return null;
        }
        appendView$kotlinx_io(mo12334fill);
        return mo12334fill;
    }

    @PublishedApi
    @Nullable
    public final IoBuffer ensureNext(@NotNull IoBuffer current) {
        Intrinsics.checkParameterIsNotNull(current, "current");
        return ensureNext(current, IoBuffer.Companion.getEmpty());
    }

    @DangerousInternalIoApi
    @Nullable
    public final IoBuffer ensureNextHead(@NotNull IoBuffer current) {
        Intrinsics.checkParameterIsNotNull(current, "current");
        return ensureNext(current);
    }

    @Nullable
    /* renamed from: fill */
    protected abstract IoBuffer mo12334fill();

    @DangerousInternalIoApi
    public final void fixGapAfterRead(@NotNull IoBuffer current) {
        int readRemaining;
        Intrinsics.checkParameterIsNotNull(current, "current");
        IoBuffer next = current.getNext();
        if (next != null) {
            int readRemaining2 = current.getReadRemaining();
            int min = Math.min(readRemaining2, IoBuffer.Companion.getReservedSize() - current.getEndGap());
            if (next.getStartGap() < min) {
                fixGapAfterReadFallback(current);
                return;
            }
            next.restoreStartGap$kotlinx_io(min);
            if (readRemaining2 > min) {
                current.restoreEndGap$kotlinx_io(min);
                this.headRemaining = readRemaining2 - min;
                this.tailRemaining += min;
                return;
            }
            this.head = next;
            this.headRemaining = next.getReadRemaining();
            this.tailRemaining -= readRemaining - min;
            current.release(this.pool);
            return;
        }
        fixGapAfterReadFallback(current);
    }

    @Override // kotlinx.io.core.Input
    @NotNull
    public final ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    @Override // kotlinx.io.core.Input
    @NotNull
    public final /* synthetic */ Void getDoNotImplementInputButExtendAbstractInputInstead() {
        throw new IllegalStateException("Should be never accessed.".toString());
    }

    @Override // kotlinx.io.core.Input
    public boolean getEndOfInput() {
        return isEmpty() && (this.noMoreChunksAvailable || doFill() == null);
    }

    @NotNull
    public final IoBuffer getHead() {
        return this.head;
    }

    public final int getHeadRemaining() {
        return this.headRemaining;
    }

    @NotNull
    public final ObjectPool<IoBuffer> getPool() {
        return this.pool;
    }

    /* renamed from: getRemaining  reason: collision with other method in class */
    public final long m12336getRemaining() {
        return this.headRemaining + this.tailRemaining;
    }

    public final boolean hasBytes(int i) {
        return ((long) this.headRemaining) + this.tailRemaining >= ((long) i);
    }

    public final boolean isEmpty() {
        return this.headRemaining == 0 && this.tailRemaining == 0 && this.noMoreChunksAvailable;
    }

    public final boolean isNotEmpty() {
        return this.headRemaining > 0 || this.tailRemaining > 0 || !this.noMoreChunksAvailable;
    }

    public final void markNoMoreChunksAvailable$kotlinx_io() {
        if (!this.noMoreChunksAvailable) {
            this.noMoreChunksAvailable = true;
        }
    }

    @Override // kotlinx.io.core.Input
    public final int peekTo(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        IoBuffer prepareReadHead = prepareReadHead(1);
        if (prepareReadHead != null) {
            int min = Math.min(buffer.getWriteRemaining(), prepareReadHead.getReadRemaining());
            buffer.writeFully(prepareReadHead, min);
            return min;
        }
        return -1;
    }

    @PublishedApi
    @Nullable
    public final IoBuffer prepareRead(int i, @NotNull IoBuffer head) {
        while (true) {
            Intrinsics.checkParameterIsNotNull(head, "head");
            int i2 = this.headRemaining;
            if (i2 >= i) {
                return head;
            }
            IoBuffer next = head.getNext();
            if (next == null) {
                next = doFill();
            }
            if (next == null) {
                return null;
            }
            next.setByteOrder(this.byteOrder);
            if (i2 == 0) {
                if (head != IoBuffer.Companion.getEmpty()) {
                    releaseHead$kotlinx_io(head);
                }
                head = next;
            } else {
                int readRemaining = next.getReadRemaining();
                head.writeBufferAppend$kotlinx_io(next, i - i2);
                int readRemaining2 = next.getReadRemaining();
                this.headRemaining = head.getReadRemaining();
                this.tailRemaining -= readRemaining - readRemaining2;
                if (readRemaining2 == 0) {
                    head.setNext(next.getNext());
                    next.release(this.pool);
                }
                if (head.getReadRemaining() >= i) {
                    return head;
                }
                if (i > IoBuffer.Companion.getReservedSize()) {
                    minSizeIsTooBig(i);
                    throw null;
                }
            }
        }
    }

    @Nullable
    public final IoBuffer prepareRead$kotlinx_io(int i) {
        return prepareRead(i, getHead());
    }

    @DangerousInternalIoApi
    @Nullable
    public final IoBuffer prepareReadHead(int i) {
        return prepareRead(i, this.head);
    }

    @PublishedApi
    public final void read(int i, @NotNull Function1<? super IoBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer head = getHead();
        int readRemaining = head.getReadRemaining();
        if (readRemaining < i) {
            head = prepareRead(i, head);
            readRemaining = head != null ? head.getReadRemaining() : 0;
        }
        if (head != null) {
            block.mo12165invoke(head);
            int readRemaining2 = head.getReadRemaining();
            int i2 = readRemaining - readRemaining2;
            if (i2 > 0) {
                setHeadRemaining(getHeadRemaining() - i2);
            }
            if (readRemaining2 != 0) {
                return;
            }
            ensureNext(head);
        }
    }

    public final int readAvailable(@NotNull byte[] dst) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return readAvailable(dst, 0, dst.length);
    }

    @Override // kotlinx.io.core.Input
    public final byte readByte() {
        int i = this.headRemaining;
        if (i > 1) {
            this.headRemaining = i - 1;
            return this.head.readByte();
        }
        return readByteSlow2();
    }

    public final int readCbuf$kotlinx_io(@NotNull final char[] cbuf, final int i, int i2) {
        Intrinsics.checkParameterIsNotNull(cbuf, "cbuf");
        if (isEmpty()) {
            return -1;
        }
        return readText(new Appendable(cbuf, i) { // from class: kotlinx.io.core.ByteReadPacketBase$readCbuf$out$1
            final /* synthetic */ char[] $cbuf;
            final /* synthetic */ int $off;
            private int idx;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$off = i;
                this.idx = i;
            }

            @Override // java.lang.Appendable
            @NotNull
            public Appendable append(char c) {
                char[] cArr = this.$cbuf;
                int i3 = this.idx;
                this.idx = i3 + 1;
                cArr[i3] = c;
                return this;
            }

            @Override // java.lang.Appendable
            @NotNull
            public Appendable append(@Nullable CharSequence charSequence) {
                if (charSequence instanceof String) {
                    StringsJVMKt.getCharsInternal((String) charSequence, this.$cbuf, this.idx);
                    this.idx = charSequence.length() + this.idx;
                } else if (charSequence != null) {
                    int length = charSequence.length();
                    for (int i3 = 0; i3 < length; i3++) {
                        char[] cArr = this.$cbuf;
                        int i4 = this.idx;
                        this.idx = i4 + 1;
                        cArr[i4] = charSequence.charAt(i3);
                    }
                }
                return this;
            }

            @Override // java.lang.Appendable
            @NotNull
            public Appendable append(@Nullable CharSequence charSequence, int i3, int i4) {
                throw new UnsupportedOperationException();
            }
        }, 0, i2);
    }

    @Override // kotlinx.io.core.Input
    public final double readDouble() {
        IoBuffer prepareRead = prepareRead(8, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(8);
            throw null;
        }
        double readDouble = prepareRead.readDouble();
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return readDouble;
    }

    @Override // kotlinx.io.core.Input
    public final float readFloat() {
        IoBuffer prepareRead = prepareRead(4, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(4);
            throw null;
        }
        float readFloat = prepareRead.readFloat();
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return readFloat;
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readAvailable = readAvailable(dst, i, i2);
        if (readAvailable == i2) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not enough data in packet to fill buffer: ");
        outline107.append(i2 - readAvailable);
        outline107.append(" more bytes required");
        throw new EOFException(outline107.toString());
    }

    @Override // kotlinx.io.core.Input
    public final int readInt() {
        int i = this.headRemaining;
        if (i > 4) {
            this.headRemaining = i - 4;
            return this.head.readInt();
        }
        return readIntSlow();
    }

    @Override // kotlinx.io.core.Input
    public final long readLong() {
        int i = this.headRemaining;
        if (i > 8) {
            this.headRemaining = i - 8;
            return this.head.readLong();
        }
        return readLongSlow();
    }

    @Override // kotlinx.io.core.Input
    public final short readShort() {
        IoBuffer prepareRead = prepareRead(2, getHead());
        if (prepareRead == null) {
            notEnoughBytesAvailable(2);
            throw null;
        }
        short readShort = prepareRead.readShort();
        int readRemaining = prepareRead.getReadRemaining();
        if (readRemaining == 0) {
            ensureNext(prepareRead);
        } else {
            setHeadRemaining(readRemaining);
        }
        return readShort;
    }

    public final int readText(@NotNull Appendable out, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(out, "out");
        if (i2 >= m12336getRemaining()) {
            String readTextExactBytes$default = StringsKt.readTextExactBytes$default(this, (int) m12336getRemaining(), (Charset) null, 2, (Object) null);
            out.append(readTextExactBytes$default);
            return readTextExactBytes$default.length();
        }
        return readASCII(out, i, i2);
    }

    public final void readTextExact(@NotNull Appendable out, int i) {
        Intrinsics.checkParameterIsNotNull(out, "out");
        readText(out, i, i);
    }

    public final void release() {
        IoBuffer ioBuffer = this.head;
        IoBuffer empty = IoBuffer.Companion.getEmpty();
        if (ioBuffer != empty) {
            this.head = empty;
            this.headRemaining = 0;
            this.tailRemaining = 0L;
            BuffersKt.releaseAll(ioBuffer, this.pool);
        }
    }

    @NotNull
    public final IoBuffer releaseHead$kotlinx_io(@NotNull IoBuffer head) {
        Intrinsics.checkParameterIsNotNull(head, "head");
        IoBuffer next = head.getNext();
        if (next == null) {
            next = IoBuffer.Companion.getEmpty();
        }
        this.head = next;
        int readRemaining = next.getReadRemaining();
        this.headRemaining = readRemaining;
        this.tailRemaining -= readRemaining;
        head.release(this.pool);
        return next;
    }

    @Override // kotlinx.io.core.Input
    public final void setByteOrder(@NotNull ByteOrder newOrder) {
        Intrinsics.checkParameterIsNotNull(newOrder, "newOrder");
        this.byteOrder = newOrder;
        PacketKt.access$setByteOrderForNonEmpty(this.head, newOrder);
    }

    public final void setHead(@NotNull IoBuffer ioBuffer) {
        Intrinsics.checkParameterIsNotNull(ioBuffer, "<set-?>");
        this.head = ioBuffer;
    }

    public final void setHeadRemaining(int i) {
        this.headRemaining = i;
    }

    @Nullable
    public final IoBuffer steal$kotlinx_io() {
        IoBuffer ioBuffer = this.head;
        IoBuffer next = ioBuffer.getNext();
        IoBuffer empty = IoBuffer.Companion.getEmpty();
        if (ioBuffer == empty) {
            return null;
        }
        int readRemaining = next != null ? next.getReadRemaining() : 0;
        if (next == null) {
            next = empty;
        }
        this.head = next;
        this.headRemaining = readRemaining;
        this.tailRemaining -= readRemaining;
        ioBuffer.setNext(null);
        return ioBuffer;
    }

    @Nullable
    public final IoBuffer stealAll$kotlinx_io() {
        IoBuffer ioBuffer = this.head;
        IoBuffer empty = IoBuffer.Companion.getEmpty();
        if (ioBuffer == empty) {
            return null;
        }
        this.head = empty;
        this.headRemaining = 0;
        this.tailRemaining = 0L;
        return ioBuffer;
    }

    @Override // kotlinx.io.core.Input
    public final int tryPeek() {
        IoBuffer prepareRead;
        IoBuffer ioBuffer = this.head;
        if (this.headRemaining > 0) {
            return ioBuffer.tryPeek();
        }
        if ((this.tailRemaining == 0 && this.noMoreChunksAvailable) || (prepareRead = prepareRead(1, ioBuffer)) == null) {
            return -1;
        }
        return prepareRead.tryPeek();
    }

    public final boolean tryWriteAppend$kotlinx_io(@NotNull IoBuffer chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        IoBuffer findTail = BuffersKt.findTail(this.head);
        int readRemaining = chain.getReadRemaining();
        if (readRemaining == 0 || findTail.getWriteRemaining() < readRemaining) {
            return false;
        }
        findTail.writeBufferAppend$kotlinx_io(chain, readRemaining);
        if (this.head == findTail) {
            this.headRemaining += readRemaining;
            return true;
        }
        this.tailRemaining += readRemaining;
        return true;
    }

    @DangerousInternalIoApi
    public final void updateHeadRemaining(int i) {
        this.headRemaining = i;
    }

    private final IoBuffer ensureNext(IoBuffer ioBuffer, IoBuffer ioBuffer2) {
        while (ioBuffer != ioBuffer2) {
            IoBuffer next = ioBuffer.getNext();
            ioBuffer.release(this.pool);
            if (next == null) {
                this.headRemaining = 0;
                this.tailRemaining = 0L;
                this.head = ioBuffer2;
                ioBuffer = ioBuffer2;
            } else if (next.canRead()) {
                this.head = next;
                next.setByteOrder(this.byteOrder);
                int readRemaining = next.getReadRemaining();
                this.headRemaining = readRemaining;
                this.tailRemaining -= readRemaining;
                return next;
            } else {
                ioBuffer = next;
            }
        }
        return doFill();
    }

    @Override // kotlinx.io.core.Input
    public final long discard(long j) {
        return discardAsMuchAsPossible((int) Math.min(Integer.MAX_VALUE, j), 0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "For compatibility purpose")
    public final /* synthetic */ int getRemaining() {
        return (int) Math.min(m12336getRemaining(), Integer.MAX_VALUE);
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull final byte[] dst, final int i, final int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        if (!(i >= 0)) {
            new RequireFailureCapture() { // from class: kotlinx.io.core.ByteReadPacketBase$readAvailable$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset shouldn't be negative: ");
                    outline107.append(i);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        }
        if (i2 >= 0) {
            if (i + i2 > dst.length) {
                z = false;
            }
            if (z) {
                return readAsMuchAsPossible(dst, i, i2, 0);
            }
            new RequireFailureCapture() { // from class: kotlinx.io.core.ByteReadPacketBase$readAvailable$$inlined$require$3
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                    outline107.append(i);
                    outline107.append(") + length (");
                    outline107.append(i2);
                    outline107.append(") > dst.size (");
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline85(outline107, dst.length, ')'));
                }
            }.doFail();
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.ByteReadPacketBase$readAvailable$$inlined$require$2
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length shouldn't be negative: ");
                outline107.append(i2);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    @NotNull
    public final String readTextExact(int i) {
        return readText(i, i);
    }

    @NotNull
    public static /* synthetic */ String readText$default(ByteReadPacketBase byteReadPacketBase, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = Integer.MAX_VALUE;
            }
            return byteReadPacketBase.readText(i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readText");
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull IoBuffer dst, final int i) {
        int i2;
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        if (i <= dst.getWriteRemaining()) {
            IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(this, 1);
            if (prepareReadFirstHead != null) {
                IoBuffer ioBuffer = prepareReadFirstHead;
                i2 = 0;
                while (true) {
                    try {
                        int readAvailable = ioBuffer.readAvailable(dst, i - i2);
                        if (readAvailable > 0) {
                            i2 += readAvailable;
                        }
                        if (!(i2 < i)) {
                            break;
                        }
                        try {
                            IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(this, ioBuffer);
                            if (prepareReadNextHead == null) {
                                z = false;
                                break;
                            }
                            ioBuffer = prepareReadNextHead;
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(this, ioBuffer);
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (z) {
                    UnsafeKt.completeReadHead(this, ioBuffer);
                }
            } else {
                i2 = 0;
            }
            if (i2 != i) {
                throw new EOFException(GeneratedOutlineSupport1.outline54("Not enough bytes available to read ", i, " bytes, ", i2, " were copied"));
            }
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.ByteReadPacketBase$readFully$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough free space in destination buffer to write "), i, " bytes"));
            }
        }.doFail();
        throw null;
    }

    public /* synthetic */ ByteReadPacketBase(IoBuffer ioBuffer, long j, ObjectPool objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ioBuffer, (i & 2) != 0 ? BuffersKt.remainingAll(ioBuffer) : j, objectPool);
    }

    @NotNull
    public final String readText(int i, int i2) {
        int coerceAtLeast;
        int coerceAtMost;
        if (i != 0 || (i2 != 0 && !isEmpty())) {
            long m12336getRemaining = m12336getRemaining();
            if (m12336getRemaining > 0 && i2 >= m12336getRemaining) {
                return StringsKt.readTextExactBytes$default(this, (int) m12336getRemaining, (Charset) null, 2, (Object) null);
            }
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i, 16);
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(coerceAtLeast, i2);
            StringBuilder sb = new StringBuilder(coerceAtMost);
            readASCII(sb, i, i2);
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).…builderAction).toString()");
            return sb2;
        }
        return "";
    }

    @PublishedApi
    public final void read(@NotNull Function1<? super IoBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer head = getHead();
        int readRemaining = head.getReadRemaining();
        if (readRemaining < 1) {
            head = prepareRead(1, head);
            readRemaining = head != null ? head.getReadRemaining() : 0;
        }
        if (head != null) {
            block.mo12165invoke(head);
            int readRemaining2 = head.getReadRemaining();
            int i = readRemaining - readRemaining2;
            if (i > 0) {
                setHeadRemaining(getHeadRemaining() - i);
            }
            if (readRemaining2 != 0) {
                return;
            }
            ensureNext(head);
        }
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i, i2);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, i2);
        readFully(dst, i, min);
        return min;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw new java.io.EOFException("Unexpected EOF while reading " + r9 + " bytes");
     */
    @Override // kotlinx.io.core.Input
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readFully(@org.jetbrains.annotations.NotNull short[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            r2 = 0
            if (r1 == 0) goto L58
            r3 = r1
            r1 = r2
        Lf:
            int r4 = r8 + r1
            int r5 = r9 - r1
            int r4 = r3.readAvailable(r7, r4, r5)     // Catch: java.lang.Throwable -> L51
            r5 = -1
            if (r4 == r5) goto L35
            int r1 = r1 + r4
            if (r1 >= r9) goto L1f
            r4 = r0
            goto L20
        L1f:
            r4 = r2
        L20:
            if (r4 != 0) goto L23
            goto L2c
        L23:
            kotlinx.io.core.IoBuffer r4 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r3)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            r3 = r4
            goto Lf
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L59
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
            goto L59
        L32:
            r7 = move-exception
            r0 = r2
            goto L52
        L35:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: java.lang.Throwable -> L51
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "Unexpected EOF while reading "
            r8.append(r1)     // Catch: java.lang.Throwable -> L51
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = " bytes"
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L51:
            r7 = move-exception
        L52:
            if (r0 == 0) goto L57
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
        L57:
            throw r7
        L58:
            r1 = r2
        L59:
            if (r1 != r9) goto L5c
            return
        L5c:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Not enough bytes available to read "
            java.lang.String r0 = " short integers, "
            java.lang.String r2 = " were copied"
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline54(r8, r9, r0, r1, r2)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readFully(short[], int, int):void");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i, i2);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, i2);
        readFully(dst, i, min);
        return min;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw new java.io.EOFException("Unexpected EOF while read " + r9 + " short integers");
     */
    @Override // kotlinx.io.core.Input
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readFully(@org.jetbrains.annotations.NotNull int[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            r2 = 0
            if (r1 == 0) goto L58
            r3 = r1
            r1 = r2
        Lf:
            int r4 = r8 + r1
            int r5 = r9 - r1
            int r4 = r3.readAvailable(r7, r4, r5)     // Catch: java.lang.Throwable -> L51
            r5 = -1
            if (r4 == r5) goto L35
            int r1 = r1 + r4
            if (r1 >= r9) goto L1f
            r4 = r0
            goto L20
        L1f:
            r4 = r2
        L20:
            if (r4 != 0) goto L23
            goto L2c
        L23:
            kotlinx.io.core.IoBuffer r4 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r3)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            r3 = r4
            goto Lf
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L59
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
            goto L59
        L32:
            r7 = move-exception
            r0 = r2
            goto L52
        L35:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: java.lang.Throwable -> L51
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "Unexpected EOF while read "
            r8.append(r1)     // Catch: java.lang.Throwable -> L51
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = " short integers"
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L51:
            r7 = move-exception
        L52:
            if (r0 == 0) goto L57
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
        L57:
            throw r7
        L58:
            r1 = r2
        L59:
            if (r1 != r9) goto L5c
            return
        L5c:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Not enough bytes available to read "
            java.lang.String r0 = " integers, "
            java.lang.String r2 = " were copied"
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline54(r8, r9, r0, r1, r2)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readFully(int[], int, int):void");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i, i2);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull float[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i, i2);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, i2);
        readFully(dst, i, min);
        return min;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw new java.io.EOFException("Unexpected EOF while reading " + r9 + " long integers");
     */
    @Override // kotlinx.io.core.Input
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readFully(@org.jetbrains.annotations.NotNull long[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            r2 = 0
            if (r1 == 0) goto L58
            r3 = r1
            r1 = r2
        Lf:
            int r4 = r8 + r1
            int r5 = r9 - r1
            int r4 = r3.readAvailable(r7, r4, r5)     // Catch: java.lang.Throwable -> L51
            r5 = -1
            if (r4 == r5) goto L35
            int r1 = r1 + r4
            if (r1 >= r9) goto L1f
            r4 = r0
            goto L20
        L1f:
            r4 = r2
        L20:
            if (r4 != 0) goto L23
            goto L2c
        L23:
            kotlinx.io.core.IoBuffer r4 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r3)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            r3 = r4
            goto Lf
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L59
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
            goto L59
        L32:
            r7 = move-exception
            r0 = r2
            goto L52
        L35:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: java.lang.Throwable -> L51
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "Unexpected EOF while reading "
            r8.append(r1)     // Catch: java.lang.Throwable -> L51
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = " long integers"
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L51:
            r7 = move-exception
        L52:
            if (r0 == 0) goto L57
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
        L57:
            throw r7
        L58:
            r1 = r2
        L59:
            if (r1 != r9) goto L5c
            return
        L5c:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Not enough bytes available to read "
            java.lang.String r0 = " long integers, "
            java.lang.String r2 = " were copied"
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline54(r8, r9, r0, r1, r2)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readFully(long[], int, int):void");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull double[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i, i2);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, i2);
        readFully(dst, i, min);
        return min;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw new java.io.EOFException("Unexpected EOF while read " + r9 + " float number");
     */
    @Override // kotlinx.io.core.Input
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readFully(@org.jetbrains.annotations.NotNull float[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            r2 = 0
            if (r1 == 0) goto L58
            r3 = r1
            r1 = r2
        Lf:
            int r4 = r8 + r1
            int r5 = r9 - r1
            int r4 = r3.readAvailable(r7, r4, r5)     // Catch: java.lang.Throwable -> L51
            r5 = -1
            if (r4 == r5) goto L35
            int r1 = r1 + r4
            if (r1 >= r9) goto L1f
            r4 = r0
            goto L20
        L1f:
            r4 = r2
        L20:
            if (r4 != 0) goto L23
            goto L2c
        L23:
            kotlinx.io.core.IoBuffer r4 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r3)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            r3 = r4
            goto Lf
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L59
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
            goto L59
        L32:
            r7 = move-exception
            r0 = r2
            goto L52
        L35:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: java.lang.Throwable -> L51
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "Unexpected EOF while read "
            r8.append(r1)     // Catch: java.lang.Throwable -> L51
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = " float number"
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L51:
            r7 = move-exception
        L52:
            if (r0 == 0) goto L57
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
        L57:
            throw r7
        L58:
            r1 = r2
        L59:
            if (r1 != r9) goto L5c
            return
        L5c:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Not enough bytes available to read "
            java.lang.String r0 = " float numbers, "
            java.lang.String r2 = " were copied"
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline54(r8, r9, r0, r1, r2)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readFully(float[], int, int):void");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull IoBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            if (doFill() != null) {
                return readAvailable(dst, i);
            }
            return -1;
        }
        int min = (int) Math.min(m12336getRemaining, Math.min(i, dst.getWriteRemaining()));
        readFully(dst, min);
        return min;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw new java.io.EOFException("Unexpected EOF while reading " + r9 + " double float numbers");
     */
    @Override // kotlinx.io.core.Input
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readFully(@org.jetbrains.annotations.NotNull double[] r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            r2 = 0
            if (r1 == 0) goto L58
            r3 = r1
            r1 = r2
        Lf:
            int r4 = r8 + r1
            int r5 = r9 - r1
            int r4 = r3.readAvailable(r7, r4, r5)     // Catch: java.lang.Throwable -> L51
            r5 = -1
            if (r4 == r5) goto L35
            int r1 = r1 + r4
            if (r1 >= r9) goto L1f
            r4 = r0
            goto L20
        L1f:
            r4 = r2
        L20:
            if (r4 != 0) goto L23
            goto L2c
        L23:
            kotlinx.io.core.IoBuffer r4 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r3)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            r3 = r4
            goto Lf
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L59
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
            goto L59
        L32:
            r7 = move-exception
            r0 = r2
            goto L52
        L35:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: java.lang.Throwable -> L51
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r8.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "Unexpected EOF while reading "
            r8.append(r1)     // Catch: java.lang.Throwable -> L51
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = " double float numbers"
            r8.append(r9)     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L51:
            r7 = move-exception
        L52:
            if (r0 == 0) goto L57
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r6, r3)
        L57:
            throw r7
        L58:
            r1 = r2
        L59:
            if (r1 != r9) goto L5c
            return
        L5c:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Not enough bytes available to read "
            java.lang.String r0 = " double numbers, "
            java.lang.String r2 = " were copied"
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline54(r8, r9, r0, r1, r2)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ByteReadPacketBase.readFully(double[], int, int):void");
    }
}
