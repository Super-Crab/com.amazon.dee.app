package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.deecomms.common.Constants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import java.io.EOFException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.OutputKt;
import kotlinx.io.core.PacketJVMKt;
import kotlinx.io.core.internal.UTF8Kt;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteChannelSequential.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0019\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010?\u001a\u00020@H\u0004J\b\u0010A\u001a\u00020@H\u0004J\u0019\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u0011\u0010E\u001a\u00020@H\u0084@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0013\u0010G\u001a\u00020\bH\u0080@ø\u0001\u0000¢\u0006\u0004\bH\u0010FJ\u0019\u0010I\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0010H\u0084@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u0012\u0010J\u001a\u00020\b2\b\u0010K\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010L\u001a\u00020@2\u0006\u0010M\u001a\u00020\u0010H\u0002J\u0010\u0010N\u001a\u00020\b2\b\u0010K\u001a\u0004\u0018\u00010\u001aJ\b\u0010O\u001a\u00020@H\u0002J\u0010\u0010P\u001a\u00020\u00102\u0006\u0010M\u001a\u00020\u0010H\u0016J\u0019\u0010P\u001a\u0002012\u0006\u0010Q\u001a\u000201H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010RJ!\u0010S\u001a\u0002012\u0006\u0010Q\u001a\u0002012\u0006\u0010T\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010UJ\b\u0010V\u001a\u00020@H\u0002J\b\u0010W\u001a\u00020@H\u0016J)\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010]J\u0019\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010^J\b\u0010_\u001a\u00020\u0010H\u0004J)\u0010`\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010]J\u0019\u0010`\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010^J\u0011\u0010a\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010b\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010c\u001a\u00020dH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010e\u001a\u00020dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010f\u001a\u00020gH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010h\u001a\u00020gH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010i\u001a\u00020jH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010k\u001a\u00020jH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ)\u0010l\u001a\u00020@2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010]J!\u0010l\u001a\u00020@2\u0006\u0010Y\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010mJ)\u0010n\u001a\u00020@2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010]J!\u0010n\u001a\u00020@2\u0006\u0010Y\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010mJ\u0011\u0010o\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010p\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010q\u001a\u000201H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0011\u0010r\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJ'\u0010s\u001a\u00020t2\u0006\u0010M\u001a\u00020\u00102\f\u0010u\u001a\b\u0012\u0004\u0012\u00020t0vH\u0082Hø\u0001\u0000¢\u0006\u0002\u0010wJ!\u0010x\u001a\u00020-2\u0006\u0010y\u001a\u00020\u00102\u0006\u0010z\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010{J!\u0010|\u001a\u00020-2\u0006\u0010}\u001a\u0002092\u0006\u0010y\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010~J#\u0010\u007f\u001a\u00020-2\u0007\u0010\u0080\u0001\u001a\u0002012\u0006\u0010z\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0081\u0001J$\u0010\u0082\u0001\u001a\u00020-2\u0006\u0010}\u001a\u0002092\u0007\u0010\u0080\u0001\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0083\u0001J&\u0010\u0084\u0001\u001a\u00020@2\u001b\u0010\u0085\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0087\u0001\u0012\u0004\u0012\u00020@0\u0086\u0001¢\u0006\u0003\b\u0088\u0001H\u0017J\u0013\u0010\u0089\u0001\u001a\u00030\u008a\u0001H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0013\u0010\u008b\u0001\u001a\u00030\u008a\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010FJA\u0010\u008c\u0001\u001a\u00020@2,\u0010\u0085\u0001\u001a'\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020@0\u008e\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u008f\u00010\u008d\u0001¢\u0006\u0003\b\u0088\u0001H\u0097@ø\u0001\u0000¢\u0006\u0003\u0010\u0090\u0001J\u001e\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010DJ7\u0010\u0093\u0001\u001a\u00020\b\"\u000f\b\u0000\u0010\u0094\u0001*\b0\u0095\u0001j\u0003`\u0096\u00012\b\u0010\u0097\u0001\u001a\u0003H\u0094\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u0013\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u00062\u0006\u0010C\u001a\u00020\u0010H\u0016J\n\u0010\u009a\u0001\u001a\u00020\u0010H\u0082\bJ \u0010\u009b\u0001\u001a\u0002012\u0006\u0010Y\u001a\u00020\u00002\u0007\u0010\u0080\u0001\u001a\u000201H\u0000¢\u0006\u0003\b\u009c\u0001J+\u0010\u009d\u0001\u001a\u00020\u00102\u0007\u0010\u009e\u0001\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010]J\u001b\u0010\u009d\u0001\u001a\u00020\u00102\u0007\u0010\u009e\u0001\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010^J+\u0010\u009f\u0001\u001a\u00020\u00102\u0007\u0010\u009e\u0001\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010]J\u001b\u0010\u009f\u0001\u001a\u00020\u00102\u0007\u0010\u009e\u0001\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010^J\u001c\u0010 \u0001\u001a\u00020@2\u0007\u0010¡\u0001\u001a\u00020dH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010¢\u0001J\u001c\u0010£\u0001\u001a\u00020@2\u0007\u0010¤\u0001\u001a\u00020gH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010¥\u0001J\u001c\u0010¦\u0001\u001a\u00020@2\u0007\u0010§\u0001\u001a\u00020jH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010¨\u0001J+\u0010©\u0001\u001a\u00020@2\u0007\u0010\u009e\u0001\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010]J\u001b\u0010©\u0001\u001a\u00020@2\u0007\u0010\u009e\u0001\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010^J\u001b\u0010ª\u0001\u001a\u00020@2\u0007\u0010«\u0001\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u001b\u0010¬\u0001\u001a\u00020@2\u0007\u0010\u00ad\u0001\u001a\u000201H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010RJ\u001c\u0010®\u0001\u001a\u00020@2\u0007\u0010¯\u0001\u001a\u00020-H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010°\u0001J\u001d\u0010±\u0001\u001a\u00020@2\b\u0010²\u0001\u001a\u00030\u008a\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010³\u0001JB\u0010´\u0001\u001a\u00020@2-\u0010µ\u0001\u001a(\b\u0001\u0012\u0005\u0012\u00030¶\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020@0\u008e\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u008f\u00010\u008d\u0001¢\u0006\u0003\b\u0088\u0001H\u0097@ø\u0001\u0000¢\u0006\u0003\u0010\u0090\u0001R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0018R\"\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u000eR\u0014\u0010\u001f\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000eR\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R$\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020&@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00103R\u000e\u00106\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\u000209X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R$\u0010<\u001a\u00020&2\u0006\u0010%\u001a\u00020&@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010)\"\u0004\b>\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006·\u0001"}, d2 = {"Lkotlinx/coroutines/io/ByteChannelSequentialBase;", "Lkotlinx/coroutines/io/ByteChannel;", "Lkotlinx/coroutines/io/ByteReadChannel;", "Lkotlinx/coroutines/io/ByteWriteChannel;", "Lkotlinx/coroutines/io/SuspendableReadSession;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/io/core/IoBuffer;", "autoFlush", "", "(Lkotlinx/io/core/IoBuffer;Z)V", "atLeastNBytesAvailableForRead", "Lkotlinx/coroutines/io/Condition;", "atLeastNBytesAvailableForWrite", "getAutoFlush", "()Z", "availableForRead", "", "getAvailableForRead", "()I", "availableForWrite", "getAvailableForWrite", "closed", "getClosed", "setClosed", "(Z)V", "<set-?>", "", "closedCause", "getClosedCause", "()Ljava/lang/Throwable;", "isClosedForRead", "isClosedForWrite", "lastReadAvailable", "lastReadView", "notFull", "getNotFull$kotlinx_coroutines_io", "()Lkotlinx/coroutines/io/Condition;", "newOrder", "Lkotlinx/io/core/ByteOrder;", "readByteOrder", "getReadByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setReadByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "readable", "Lkotlinx/io/core/ByteReadPacket;", "getReadable", "()Lkotlinx/io/core/ByteReadPacket;", "totalBytesRead", "", "getTotalBytesRead", "()J", "totalBytesWritten", "getTotalBytesWritten", "waitingForRead", "waitingForSize", "writable", "Lkotlinx/io/core/BytePacketBuilder;", "getWritable", "()Lkotlinx/io/core/BytePacketBuilder;", "writeByteOrder", "getWriteByteOrder", "setWriteByteOrder", "afterRead", "", "afterWrite", "await", "atLeast", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFreeSpace", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternalAtLeast1", "awaitInternalAtLeast1$kotlinx_coroutines_io", "awaitSuspend", DeviceStateModule.CANCEL, "cause", "checkClosed", JsonReportFormat.COUNT, "close", "completeReading", "discard", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discardSuspend", "discarded0", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureNotClosed", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "readAvailable", "dst", "", "offset", "length", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/io/core/IoBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailableClosed", "readAvailableSuspend", "readBoolean", "readBooleanSlow", "readByte", "", "readByteSlow", "readDouble", "", "readDoubleSlow", "readFloat", "", "readFloatSlow", "readFully", "(Lkotlinx/io/core/IoBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFullySuspend", "readInt", "readIntSlow", "readLong", "readLongSlow", "readNSlow", "", "block", "Lkotlin/Function0;", "(ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPacket", "size", "headerSizeHint", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPacketSuspend", "builder", "(Lkotlinx/io/core/BytePacketBuilder;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemaining", MetricsUtil.LegacyMetricTypes.LIMIT, "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemainingSuspend", "(Lkotlinx/io/core/BytePacketBuilder;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readSession", "consumer", "Lkotlin/Function1;", "Lkotlinx/coroutines/io/ReadSession;", "Lkotlin/ExtensionFunctionType;", "readShort", "", "readShortSlow", "readSuspendableSession", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUTF8Line", "", "readUTF8LineTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "request", "totalPending", "transferTo", "transferTo$kotlinx_coroutines_io", "writeAvailable", "src", "writeAvailableSuspend", "writeByte", "b", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeDouble", "d", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFloat", "f", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "writeInt", ContextChain.TAG_INFRA, "writeLong", "l", "writePacket", "packet", "(Lkotlinx/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShort", "s", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeSuspendSession", "visitor", "Lkotlinx/coroutines/io/WriterSuspendSession;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class ByteChannelSequentialBase implements ByteChannel, ByteReadChannel, ByteWriteChannel, SuspendableReadSession {
    private final Condition atLeastNBytesAvailableForRead;
    private final Condition atLeastNBytesAvailableForWrite;
    private final boolean autoFlush;
    private boolean closed;
    @Nullable
    private Throwable closedCause;
    private int lastReadAvailable;
    private IoBuffer lastReadView;
    @NotNull
    private final Condition notFull;
    @NotNull
    private ByteOrder readByteOrder;
    @NotNull
    private final ByteReadPacket readable;
    private int waitingForRead;
    private int waitingForSize;
    @NotNull
    private final BytePacketBuilder writable;
    @NotNull
    private ByteOrder writeByteOrder;

    public ByteChannelSequentialBase(@NotNull IoBuffer initial, boolean z) {
        Intrinsics.checkParameterIsNotNull(initial, "initial");
        this.autoFlush = z;
        this.writable = PacketJVMKt.BytePacketBuilder(0);
        this.readable = new ByteReadPacket(initial, IoBuffer.Companion.getPool());
        this.notFull = new Condition(new ByteChannelSequentialBase$notFull$1(this));
        this.waitingForSize = 1;
        this.atLeastNBytesAvailableForWrite = new Condition(new ByteChannelSequentialBase$atLeastNBytesAvailableForWrite$1(this));
        this.waitingForRead = 1;
        this.atLeastNBytesAvailableForRead = new Condition(new ByteChannelSequentialBase$atLeastNBytesAvailableForRead$1(this));
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this.readByteOrder = byteOrder;
        this.writeByteOrder = byteOrder;
        this.lastReadView = IoBuffer.Companion.getEmpty();
    }

    static /* synthetic */ Object await$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, int i, Continuation continuation) {
        boolean z = false;
        if (i >= 0) {
            if (i <= 4088) {
                z = true;
            }
            if (z) {
                byteChannelSequentialBase.completeReading();
                if (i == 0) {
                    return Boxing.boxBoolean(!byteChannelSequentialBase.isClosedForRead());
                }
                return byteChannelSequentialBase.getAvailableForRead() >= i ? Boxing.boxBoolean(true) : byteChannelSequentialBase.awaitSuspend(i, continuation);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be larger than max buffer size of 4088: ", i).toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be negative: ", i).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkClosed(int i) {
        if (this.closed) {
            Throwable th = this.closedCause;
            if (th != null) {
                throw th;
            }
            throw new EOFException(i + " bytes required but EOF reached");
        }
    }

    private final void completeReading() {
        int readRemaining = this.lastReadView.getReadRemaining();
        int i = this.lastReadAvailable - readRemaining;
        if (this.lastReadView != IoBuffer.Companion.getEmpty()) {
            this.readable.updateHeadRemaining(readRemaining);
        }
        if (i > 0) {
            afterRead();
        }
        this.lastReadAvailable = 0;
        this.lastReadView = IoBuffer.Companion.getEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object discard$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r7, long r8, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$discard$3
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.io.ByteChannelSequentialBase$discard$3 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$discard$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$discard$3 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$discard$3
            r0.<init>(r7, r10)
        L18:
            r6 = r0
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            long r7 = r6.J$1
            long r7 = r6.J$0
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r7 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r7
            boolean r7 = r10 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L33
            goto L66
        L33:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L40:
            boolean r1 = r10 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L76
            kotlinx.io.core.ByteReadPacket r10 = r7.readable
            long r4 = r10.discard(r8)
            int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r10 == 0) goto L71
            boolean r10 = r7.isClosedForRead()
            if (r10 == 0) goto L55
            goto L71
        L55:
            r6.L$0 = r7
            r6.J$0 = r8
            r6.J$1 = r4
            r6.label = r2
            r1 = r7
            r2 = r8
            java.lang.Object r10 = r1.discardSuspend(r2, r4, r6)
            if (r10 != r0) goto L66
            return r0
        L66:
            java.lang.Number r10 = (java.lang.Number) r10
            long r7 = r10.longValue()
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)
            return r7
        L71:
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            return r7
        L76:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.discard$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void ensureNotClosed() {
        if (!this.closed) {
            return;
        }
        throw new ClosedWriteChannelException("Channel is already closed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlinx.io.core.IoBuffer r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r4 = r0.L$1
            kotlinx.io.core.IoBuffer r4 = (kotlinx.io.core.IoBuffer) r4
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L32
            goto L8c
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        L37:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3f:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L97
            java.lang.Throwable r6 = r4.closedCause
            if (r6 == 0) goto L4d
            if (r6 != 0) goto L4c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L4c:
            throw r6
        L4d:
            kotlinx.io.core.ByteReadPacket r6 = r4.readable
            boolean r6 = r6.canRead()
            if (r6 == 0) goto L6e
            int r6 = r5.getWriteRemaining()
            long r0 = (long) r6
            kotlinx.io.core.ByteReadPacket r6 = r4.readable
            long r2 = r6.m12336getRemaining()
            long r0 = java.lang.Math.min(r0, r2)
            int r6 = (int) r0
            kotlinx.io.core.ByteReadPacket r0 = r4.readable
            r0.readFully(r5, r6)
            r4.afterRead()
            goto L92
        L6e:
            boolean r6 = r4.closed
            if (r6 == 0) goto L77
            int r6 = r4.readAvailableClosed()
            goto L92
        L77:
            boolean r6 = r5.canWrite()
            if (r6 != 0) goto L7f
            r6 = 0
            goto L92
        L7f:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r4.readAvailableSuspend(r5, r0)
            if (r6 != r1) goto L8c
            return r1
        L8c:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
        L92:
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r4
        L97:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readBoolean$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readBoolean$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readBoolean$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readBoolean$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readBoolean$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readBoolean$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L6c
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L77
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            boolean r5 = r5.canRead()
            if (r5 == 0) goto L61
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            byte r5 = r5.readByte()
            byte r0 = (byte) r3
            if (r5 != r0) goto L51
            goto L52
        L51:
            r3 = 0
        L52:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r5.booleanValue()
            r4.afterRead()
            boolean r4 = r5.booleanValue()
            goto L72
        L61:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readBooleanSlow(r0)
            if (r5 != r1) goto L6c
            return r1
        L6c:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r4 = r5.booleanValue()
        L72:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        L77:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readBoolean$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readByte$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readByte$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readByte$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readByte$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readByte$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L67
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L72
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            boolean r5 = r5.isNotEmpty()
            if (r5 == 0) goto L5c
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            byte r5 = r5.readByte()
            java.lang.Byte r5 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r5)
            r5.byteValue()
            r4.afterRead()
            byte r4 = r5.byteValue()
            goto L6d
        L5c:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readByteSlow(r0)
            if (r5 != r1) goto L67
            return r1
        L67:
            java.lang.Number r5 = (java.lang.Number) r5
            byte r4 = r5.byteValue()
        L6d:
            java.lang.Byte r4 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r4)
            return r4
        L72:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readByte$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readDouble$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readDouble$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readDouble$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readDouble$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readDouble$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readDouble$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L69
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L74
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            r2 = 8
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L5e
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            double r0 = r5.readDouble()
            java.lang.Double r5 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r0)
            r5.doubleValue()
            r4.afterRead()
            double r4 = r5.doubleValue()
            goto L6f
        L5e:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readDoubleSlow(r0)
            if (r5 != r1) goto L69
            return r1
        L69:
            java.lang.Number r5 = (java.lang.Number) r5
            double r4 = r5.doubleValue()
        L6f:
            java.lang.Double r4 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r4)
            return r4
        L74:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readDouble$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readFloat$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readFloat$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFloat$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readFloat$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFloat$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readFloat$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L68
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L73
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            r2 = 4
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L5d
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            float r5 = r5.readFloat()
            java.lang.Float r5 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r5)
            r5.floatValue()
            r4.afterRead()
            float r4 = r5.floatValue()
            goto L6e
        L5d:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readFloatSlow(r0)
            if (r5 != r1) goto L68
            return r1
        L68:
            java.lang.Number r5 = (java.lang.Number) r5
            float r4 = r5.floatValue()
        L6e:
            java.lang.Float r4 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r4)
            return r4
        L73:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readFloat$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readFully$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, IoBuffer ioBuffer, int i, Continuation continuation) {
        boolean z = true;
        if (i <= ioBuffer.getWriteRemaining()) {
            if (i < 0) {
                z = false;
            }
            if (z) {
                Throwable th = byteChannelSequentialBase.closedCause;
                if (th != null) {
                    if (th == null) {
                        Intrinsics.throwNpe();
                    }
                    throw th;
                } else if (byteChannelSequentialBase.readable.m12336getRemaining() >= i) {
                    byteChannelSequentialBase.readable.readFully(ioBuffer, i);
                    Unit unit = Unit.INSTANCE;
                    byteChannelSequentialBase.afterRead();
                    return unit;
                } else if (!byteChannelSequentialBase.closed) {
                    return byteChannelSequentialBase.readFullySuspend(ioBuffer, i, continuation);
                } else {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Channel is closed and not enough bytes available: required ", i, " but ");
                    outline109.append(byteChannelSequentialBase.getAvailableForRead());
                    outline109.append(" available");
                    throw new EOFException(outline109.toString());
                }
            }
            throw new IllegalArgumentException("n shouldn't be negative".toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Not enough space in the destination buffer to write ", i, " bytes").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readInt$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readInt$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readInt$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readInt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readInt$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readInt$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L68
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L73
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            r2 = 4
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L5d
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            int r5 = r5.readInt()
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r5.intValue()
            r4.afterRead()
            int r4 = r5.intValue()
            goto L6e
        L5d:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readIntSlow(r0)
            if (r5 != r1) goto L68
            return r1
        L68:
            java.lang.Number r5 = (java.lang.Number) r5
            int r4 = r5.intValue()
        L6e:
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            return r4
        L73:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readInt$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readLong$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readLong$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readLong$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readLong$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readLong$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L69
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L74
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            r2 = 8
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L5e
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            long r0 = r5.readLong()
            java.lang.Long r5 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            r5.longValue()
            r4.afterRead()
            long r4 = r5.longValue()
            goto L6f
        L5e:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readLongSlow(r0)
            if (r5 != r1) goto L69
            return r1
        L69:
            java.lang.Number r5 = (java.lang.Number) r5
            long r4 = r5.longValue()
        L6f:
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            return r4
        L74:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readLong$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final /* synthetic */ Object readNSlow(int i, Function0 function0, Continuation<?> continuation) {
        while (true) {
            InlineMarker.mark(0);
            awaitSuspend(i, continuation);
            InlineMarker.mark(1);
            if (!this.readable.hasBytes(i)) {
                checkClosed(i);
            } else {
                function0.mo12560invoke();
                throw null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readPacket$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r8, int r9, int r10, kotlin.coroutines.Continuation r11) {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readPacket$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.io.ByteChannelSequentialBase$readPacket$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readPacket$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readPacket$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readPacket$1
            r0.<init>(r8, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L47
            if (r2 != r3) goto L3f
            int r8 = r0.I$3
            int r8 = r0.I$2
            java.lang.Object r8 = r0.L$1
            kotlinx.io.core.BytePacketBuilder r8 = (kotlinx.io.core.BytePacketBuilder) r8
            int r8 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r8 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r8
            boolean r8 = r11 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L3a
            goto L7c
        L3a:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        L3f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L47:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L84
            kotlinx.io.core.BytePacketBuilder r11 = kotlinx.io.core.PacketJVMKt.BytePacketBuilder(r10)
            long r4 = (long) r9
            kotlinx.io.core.ByteReadPacket r2 = r8.readable
            long r6 = r2.m12336getRemaining()
            long r4 = java.lang.Math.min(r4, r6)
            int r2 = (int) r4
            int r4 = r9 - r2
            kotlinx.io.core.ByteReadPacket r5 = r8.readable
            r11.writePacket(r5, r2)
            r8.afterRead()
            if (r4 <= 0) goto L7f
            r0.L$0 = r8
            r0.I$0 = r9
            r0.I$1 = r10
            r0.L$1 = r11
            r0.I$2 = r4
            r0.I$3 = r2
            r0.label = r3
            java.lang.Object r11 = r8.readPacketSuspend(r11, r4, r0)
            if (r11 != r1) goto L7c
            return r1
        L7c:
            kotlinx.io.core.ByteReadPacket r11 = (kotlinx.io.core.ByteReadPacket) r11
            goto L83
        L7f:
            kotlinx.io.core.ByteReadPacket r11 = r11.build()
        L83:
            return r11
        L84:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readPacket$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readRemaining$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r8, long r9, int r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readRemaining$1
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.io.ByteChannelSequentialBase$readRemaining$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readRemaining$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readRemaining$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readRemaining$1
            r0.<init>(r8, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L45
            if (r2 != r3) goto L3d
            long r8 = r0.J$1
            java.lang.Object r8 = r0.L$1
            kotlinx.io.core.BytePacketBuilder r8 = (kotlinx.io.core.BytePacketBuilder) r8
            int r8 = r0.I$0
            long r8 = r0.J$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r8 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r8
            boolean r8 = r12 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L38
            goto L87
        L38:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r8 = r12.exception
            throw r8
        L3d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L45:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L92
            kotlinx.io.core.BytePacketBuilder r12 = kotlinx.io.core.PacketJVMKt.BytePacketBuilder(r11)
            kotlinx.io.core.ByteReadPacket r2 = r8.readable
            long r4 = r2.m12336getRemaining()
            long r4 = java.lang.Math.min(r9, r4)
            r12.writePacket(r2, r4)
            int r2 = r12.getSize()
            long r4 = (long) r2
            long r4 = r9 - r4
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L8a
            kotlinx.io.core.ByteReadPacket r2 = r8.readable
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L74
            boolean r2 = r8.closed
            if (r2 == 0) goto L74
            goto L8a
        L74:
            r0.L$0 = r8
            r0.J$0 = r9
            r0.I$0 = r11
            r0.L$1 = r12
            r0.J$1 = r4
            r0.label = r3
            java.lang.Object r12 = r8.readRemainingSuspend(r12, r4, r0)
            if (r12 != r1) goto L87
            return r1
        L87:
            kotlinx.io.core.ByteReadPacket r12 = (kotlinx.io.core.ByteReadPacket) r12
            goto L91
        L8a:
            r8.afterRead()
            kotlinx.io.core.ByteReadPacket r12 = r12.build()
        L91:
            return r12
        L92:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r8 = r12.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readRemaining$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readShort$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readShort$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$readShort$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readShort$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readShort$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readShort$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r5 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2e
            goto L68
        L2e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L73
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            r2 = 2
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L5d
            kotlinx.io.core.ByteReadPacket r5 = r4.readable
            short r5 = r5.readShort()
            java.lang.Short r5 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r5)
            r5.shortValue()
            r4.afterRead()
            short r4 = r5.shortValue()
            goto L6e
        L5d:
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.readShortSlow(r0)
            if (r5 != r1) goto L68
            return r1
        L68:
            java.lang.Number r5 = (java.lang.Number) r5
            short r4 = r5.shortValue()
        L6e:
            java.lang.Short r4 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r4)
            return r4
        L73:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readShort$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readSuspendableSession$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, kotlin.jvm.functions.Function2 r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readSuspendableSession$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readSuspendableSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readSuspendableSession$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r5 = r6 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L37
            if (r5 != 0) goto L32
            goto L52
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6     // Catch: java.lang.Throwable -> L37
            java.lang.Throwable r5 = r6.exception     // Catch: java.lang.Throwable -> L37
            throw r5     // Catch: java.lang.Throwable -> L37
        L37:
            r5 = move-exception
            goto L58
        L39:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L41:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5c
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L37
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L37
            r0.label = r3     // Catch: java.lang.Throwable -> L37
            java.lang.Object r5 = r5.mo12248invoke(r4, r0)     // Catch: java.lang.Throwable -> L37
            if (r5 != r1) goto L52
            return r1
        L52:
            r4.completeReading()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L58:
            r4.completeReading()
            throw r5
        L5c:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readSuspendableSession$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r5, int r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8Line$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8Line$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8Line$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r5 = r0.L$1
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r4 = r7
            r7 = r5
            r5 = r4
            goto L5c
        L37:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L44:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L6b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r0.L$0 = r5
            r0.I$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r5 = r5.readUTF8LineTo(r7, r6, r0)
            if (r5 != r1) goto L5c
            return r1
        L5c:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L66
            r5 = 0
            return r5
        L66:
            java.lang.String r5 = r7.toString()
            return r5
        L6b:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readUTF8Line$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readUTF8LineTo$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, Appendable appendable, int i, Continuation continuation) {
        return byteChannelSequentialBase.isClosedForRead() ? Boxing.boxBoolean(false) : UTF8Kt.decodeUTF8LineLoopSuspend(appendable, i, new ByteChannelSequentialBase$readUTF8LineTo$2(byteChannelSequentialBase, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int totalPending() {
        return this.writable.getSize() + ((int) this.readable.m12336getRemaining());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r5, kotlinx.io.core.IoBuffer r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5c
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            int r5 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L39
            goto La5
        L39:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L46:
            int r5 = r0.I$1
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$1
            kotlinx.io.core.IoBuffer r5 = (kotlinx.io.core.IoBuffer) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r5 = r7 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L57
            goto L87
        L57:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L5c:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Laa
            int r7 = r6.getReadRemaining()
            if (r7 != 0) goto L6c
            r5 = 0
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        L6c:
            int r2 = r5.getAvailableForWrite()
            int r2 = java.lang.Math.min(r7, r2)
            if (r2 != 0) goto L8e
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r2
            r0.label = r4
            java.lang.Object r7 = r5.writeAvailableSuspend(r6, r0)
            if (r7 != r1) goto L87
            return r1
        L87:
            java.lang.Number r7 = (java.lang.Number) r7
            int r5 = r7.intValue()
            goto La5
        L8e:
            kotlinx.io.core.BytePacketBuilder r4 = r5.writable
            r4.writeFully(r6, r2)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r2
            r0.label = r3
            java.lang.Object r5 = r5.awaitFreeSpace(r0)
            if (r5 != r1) goto La4
            return r1
        La4:
            r5 = r2
        La5:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        Laa:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.writeAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeByte$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, byte b, Continuation continuation) {
        byteChannelSequentialBase.writable.writeByte(b);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeDouble$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, double d, Continuation continuation) {
        byteChannelSequentialBase.writable.writeDouble(d);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeFloat$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, float f, Continuation continuation) {
        byteChannelSequentialBase.writable.writeFloat(f);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, IoBuffer ioBuffer, Continuation continuation) {
        OutputKt.writeFully$default(byteChannelSequentialBase.writable, ioBuffer, 0, 2, null);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeInt$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, int i, Continuation continuation) {
        byteChannelSequentialBase.writable.writeInt(i);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeLong$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, long j, Continuation continuation) {
        byteChannelSequentialBase.writable.writeLong(j);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writePacket$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, ByteReadPacket byteReadPacket, Continuation continuation) {
        byteChannelSequentialBase.writable.writePacket(byteReadPacket);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeShort$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, short s, Continuation continuation) {
        byteChannelSequentialBase.writable.writeShort(s);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    static /* synthetic */ Object writeSuspendSession$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, Function2 function2, Continuation continuation) {
        return function2.mo12248invoke(new ByteChannelSequentialBase$writeSuspendSession$session$1(byteChannelSequentialBase), continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void afterRead() {
        this.atLeastNBytesAvailableForWrite.signal();
        this.notFull.signal();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void afterWrite() {
        if (this.closed) {
            this.writable.release();
            Throwable th = this.closedCause;
            if (th == null) {
                throw new ClosedWriteChannelException("Channel is already closed");
            }
        } else if (!getAutoFlush() && getAvailableForWrite() != 0) {
        } else {
            flush();
        }
    }

    @Override // kotlinx.coroutines.io.SuspendableReadSession
    @Nullable
    public Object await(int i, @NotNull Continuation<? super Boolean> continuation) {
        return await$suspendImpl(this, i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitFreeSpace(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$awaitFreeSpace$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$awaitFreeSpace$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$awaitFreeSpace$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.io.Condition r1 = (kotlinx.coroutines.io.Condition) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r0
            boolean r0 = r5 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L32
            goto L9c
        L32:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L3f:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La3
            r4.afterWrite()
            kotlinx.coroutines.io.Condition r5 = r4.notFull
            kotlin.jvm.functions.Function0 r2 = r5.getPredicate()
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L5b
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L9c
        L5b:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            r3 = 0
            boolean r2 = r2.compareAndSet(r5, r3, r0)
            if (r2 == 0) goto L9d
            kotlin.jvm.functions.Function0 r2 = r5.getPredicate()
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L89
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            boolean r5 = r2.compareAndSet(r5, r0, r3)
            if (r5 == 0) goto L89
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L90
        L89:
            r4.flush()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
        L90:
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r2) goto L99
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L99:
            if (r5 != r1) goto L9c
            return r1
        L9c:
            return r5
        L9d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>()
            throw r5
        La3:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r5 = r5.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.awaitFreeSpace(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object awaitInternalAtLeast1$kotlinx_coroutines_io(@NotNull Continuation<? super Boolean> continuation) {
        return this.readable.isNotEmpty() ? Boxing.boxBoolean(true) : awaitSuspend(1, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bf  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitSuspend(int r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$awaitSuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$awaitSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$awaitSuspend$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 != r4) goto L3b
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.io.Condition r7 = (kotlinx.coroutines.io.Condition) r7
            int r7 = r0.I$0
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r0
            boolean r1 = r8 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L36
            goto La9
        L36:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Ld2
            if (r7 < 0) goto L4b
            r8 = r4
            goto L4c
        L4b:
            r8 = r3
        L4c:
            if (r8 == 0) goto Lc6
            r6.waitingForRead = r7
            kotlinx.coroutines.io.Condition r8 = r6.atLeastNBytesAvailableForRead
            kotlin.jvm.functions.Function0 r2 = r8.getPredicate()
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L65
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            goto La8
        L65:
            r0.L$0 = r6
            r0.I$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            r5 = 0
            boolean r2 = r2.compareAndSet(r8, r5, r0)
            if (r2 == 0) goto Lc0
            kotlin.jvm.functions.Function0 r2 = r8.getPredicate()
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L95
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = kotlinx.coroutines.io.Condition.access$getUpdater$cp()
            boolean r8 = r2.compareAndSet(r8, r0, r5)
            if (r8 == 0) goto L95
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            goto L9c
        L95:
            r6.afterRead()
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
        L9c:
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r8 != r2) goto La5
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        La5:
            if (r8 != r1) goto La8
            return r1
        La8:
            r0 = r6
        La9:
            java.lang.Throwable r8 = r0.closedCause
            if (r8 != 0) goto Lbf
            boolean r8 = r0.isClosedForRead()
            if (r8 != 0) goto Lba
            int r8 = r0.getAvailableForRead()
            if (r8 < r7) goto Lba
            r3 = r4
        Lba:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        Lbf:
            throw r8
        Lc0:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        Lc6:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Failed requirement."
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        Ld2:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.awaitSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public boolean cancel(@Nullable Throwable th) {
        if (this.closedCause != null || this.closed) {
            return false;
        }
        if (th == null) {
            th = new CancellationException("Channel cancelled");
        }
        return close(th);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public final boolean close(@Nullable Throwable th) {
        if (this.closed || this.closedCause != null) {
            return false;
        }
        this.closedCause = th;
        this.closed = true;
        if (th != null) {
            this.readable.release();
            this.writable.release();
        } else {
            flush();
        }
        this.atLeastNBytesAvailableForRead.signal();
        this.atLeastNBytesAvailableForWrite.signal();
        return true;
    }

    @Override // kotlinx.coroutines.io.ReadSession
    public int discard(int i) {
        Throwable th = this.closedCause;
        if (th == null) {
            int discard = this.readable.discard(i);
            afterRead();
            return discard;
        }
        throw th;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object discard(long j, @NotNull Continuation<? super Long> continuation) {
        return discard$suspendImpl(this, j, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0068 -> B:25:0x006b). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object discardSuspend(long r17, long r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r21) {
        /*
            r16 = this;
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$discardSuspend$1
            if (r1 == 0) goto L17
            r1 = r0
            kotlinx.coroutines.io.ByteChannelSequentialBase$discardSuspend$1 r1 = (kotlinx.coroutines.io.ByteChannelSequentialBase$discardSuspend$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.label = r2
            r2 = r16
            goto L1e
        L17:
            kotlinx.coroutines.io.ByteChannelSequentialBase$discardSuspend$1 r1 = new kotlinx.coroutines.io.ByteChannelSequentialBase$discardSuspend$1
            r2 = r16
            r1.<init>(r2, r0)
        L1e:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            if (r4 == 0) goto L4c
            if (r4 != r5) goto L44
            long r6 = r1.J$2
            long r8 = r1.J$1
            long r10 = r1.J$0
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r12 = r0 instanceof kotlin.Result.Failure
            if (r12 != 0) goto L3f
            r14 = r10
            r11 = r0
            r10 = r3
            r0 = r4
            r3 = r14
            goto L6b
        L3f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        L44:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4c:
            boolean r4 = r0 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L91
            r6 = r19
            r8 = r6
            r0 = r2
            r10 = r3
            r3 = r17
        L57:
            r1.L$0 = r0
            r1.J$0 = r3
            r1.J$1 = r6
            r1.J$2 = r8
            r1.label = r5
            java.lang.Object r11 = r0.await(r5, r1)
            if (r11 != r10) goto L68
            return r10
        L68:
            r14 = r6
            r6 = r8
            r8 = r14
        L6b:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L74
            goto L8c
        L74:
            kotlinx.io.core.ByteReadPacket r11 = r0.readable
            long r12 = r3 - r6
            long r11 = r11.discard(r12)
            long r6 = r6 + r11
            int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r11 >= 0) goto L8c
            boolean r11 = r0.isClosedForRead()
            if (r11 == 0) goto L88
            goto L8c
        L88:
            r14 = r6
            r6 = r8
            r8 = r14
            goto L57
        L8c:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            return r0
        L91:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.discardSuspend(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public void flush() {
        if (this.writable.isNotEmpty()) {
            UnsafeKt.$unsafeAppend$(this.readable, this.writable);
            this.atLeastNBytesAvailableForRead.signal();
        }
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public int getAvailableForRead() {
        return (int) this.readable.m12336getRemaining();
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public int getAvailableForWrite() {
        return Math.max(0, 4088 - (this.writable.getSize() + ((int) this.readable.m12336getRemaining())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getClosed() {
        return this.closed;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public final Throwable getClosedCause() {
        return this.closedCause;
    }

    @NotNull
    public final Condition getNotFull$kotlinx_coroutines_io() {
        return this.notFull;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @NotNull
    public ByteOrder getReadByteOrder() {
        return this.readByteOrder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final ByteReadPacket getReadable() {
        return this.readable;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public long getTotalBytesRead() {
        return 0L;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public long getTotalBytesWritten() {
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final BytePacketBuilder getWritable() {
        return this.writable;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @NotNull
    public ByteOrder getWriteByteOrder() {
        return this.writeByteOrder;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public boolean isClosedForRead() {
        return this.closed && this.readable.isEmpty();
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel, kotlinx.coroutines.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return this.closed;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, ioBuffer, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    protected final int readAvailableClosed() {
        Throwable th = this.closedCause;
        if (th == null) {
            return -1;
        }
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0073 A[PHI: r7 
      PHI: (r7v5 java.lang.Object) = (r7v4 java.lang.Object), (r7v1 java.lang.Object) binds: [B:31:0x0070, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L73
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L74
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.awaitSuspend(r4, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable(r6, r0)
            if (r7 != r1) goto L73
            return r1
        L73:
            return r7
        L74:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readAvailableSuspend(kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readBoolean(@NotNull Continuation<? super Boolean> continuation) {
        return readBoolean$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a A[PHI: r7 
      PHI: (r7v6 java.lang.Object) = (r7v5 java.lang.Object), (r7v1 java.lang.Object) binds: [B:31:0x0067, B:13:0x002e] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readBooleanSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readBooleanSlow$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readBooleanSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readBooleanSlow$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r0
            boolean r0 = r7 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L6a
        L31:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r7 = r7.exception
            throw r7
        L36:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r5 = r7 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L47
            goto L5c
        L47:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r7 = r7.exception
            throw r7
        L4c:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L6b
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.awaitSuspend(r4, r0)
            if (r7 != r1) goto L5b
            return r1
        L5b:
            r2 = r6
        L5c:
            r2.checkClosed(r4)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r2.readBoolean(r0)
            if (r7 != r1) goto L6a
            return r1
        L6a:
            return r7
        L6b:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r7 = r7.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readBooleanSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readByte(@NotNull Continuation<? super Byte> continuation) {
        return readByte$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0049 -> B:24:0x004c). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readByteSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Byte> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readByteSlow$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteChannelSequentialBase$readByteSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readByteSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readByteSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readByteSlow$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L2f
            r6 = r2
            goto L4c
        L2f:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L34:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L69
            r6 = r5
        L41:
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r2 = r6.awaitSuspend(r3, r0)
            if (r2 != r1) goto L4c
            return r1
        L4c:
            kotlinx.io.core.ByteReadPacket r2 = r6.readable
            boolean r2 = r2.isNotEmpty()
            if (r2 == 0) goto L65
            kotlinx.io.core.ByteReadPacket r0 = r6.readable
            byte r0 = r0.readByte()
            java.lang.Byte r0 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r0)
            r0.byteValue()
            r6.afterRead()
            return r0
        L65:
            r6.checkClosed(r3)
            goto L41
        L69:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readByteSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readDouble(@NotNull Continuation<? super Double> continuation) {
        return readDouble$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005a -> B:24:0x005d). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readDoubleSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Double> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readDoubleSlow$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readDoubleSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readDoubleSlow$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r8 = r4
            r4 = r1
            r1 = r5
            goto L5d
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L44:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7c
            r8 = 8
            r2 = r8
            r4 = r1
            r8 = r7
            r1 = r8
        L4e:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r5 = access$awaitSuspend(r8, r2, r0)
            if (r5 != r4) goto L5d
            return r4
        L5d:
            kotlinx.io.core.ByteReadPacket r5 = access$getReadable$p(r8)
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L78
            kotlinx.io.core.ByteReadPacket r8 = r1.readable
            double r2 = r8.readDouble()
            java.lang.Double r8 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r2)
            r8.doubleValue()
            r1.afterRead()
            return r8
        L78:
            access$checkClosed(r8, r2)
            goto L4e
        L7c:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readDoubleSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readFloat(@NotNull Continuation<? super Float> continuation) {
        return readFloat$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0059 -> B:24:0x005c). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFloatSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readFloatSlow$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readFloatSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readFloatSlow$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r8 = r4
            r4 = r1
            r1 = r5
            goto L5c
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L44:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7b
            r8 = 4
            r2 = r8
            r4 = r1
            r8 = r7
            r1 = r8
        L4d:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r5 = access$awaitSuspend(r8, r2, r0)
            if (r5 != r4) goto L5c
            return r4
        L5c:
            kotlinx.io.core.ByteReadPacket r5 = access$getReadable$p(r8)
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L77
            kotlinx.io.core.ByteReadPacket r8 = r1.readable
            float r8 = r8.readFloat()
            java.lang.Float r8 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            r8.floatValue()
            r1.afterRead()
            return r8
        L77:
            access$checkClosed(r8, r2)
            goto L4d
        L7b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readFloatSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readFully(@NotNull IoBuffer ioBuffer, int i, @NotNull Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, ioBuffer, i, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[PHI: r8 
      PHI: (r8v5 java.lang.Object) = (r8v4 java.lang.Object), (r8v1 java.lang.Object) binds: [B:31:0x0078, B:13:0x0034] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r6, int r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L58
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3c
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            goto L7b
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L53
            goto L6c
        L53:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L58:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7c
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.label = r4
            java.lang.Object r8 = r5.awaitSuspend(r7, r0)
            if (r8 != r1) goto L6b
            return r1
        L6b:
            r2 = r5
        L6c:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r8 = r2.readFully(r6, r7, r0)
            if (r8 != r1) goto L7b
            return r1
        L7b:
            return r8
        L7c:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readFullySuspend(kotlinx.io.core.IoBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readInt(@NotNull Continuation<? super Integer> continuation) {
        return readInt$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0059 -> B:24:0x005c). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readIntSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readIntSlow$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readIntSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readIntSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readIntSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readIntSlow$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r8 = r4
            r4 = r1
            r1 = r5
            goto L5c
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L44:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7b
            r8 = 4
            r2 = r8
            r4 = r1
            r8 = r7
            r1 = r8
        L4d:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r5 = access$awaitSuspend(r8, r2, r0)
            if (r5 != r4) goto L5c
            return r4
        L5c:
            kotlinx.io.core.ByteReadPacket r5 = access$getReadable$p(r8)
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L77
            kotlinx.io.core.ByteReadPacket r8 = r1.readable
            int r8 = r8.readInt()
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            r8.intValue()
            r1.afterRead()
            return r8
        L77:
            access$checkClosed(r8, r2)
            goto L4d
        L7b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readIntSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readLong(@NotNull Continuation<? super Long> continuation) {
        return readLong$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005a -> B:24:0x005d). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readLongSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readLongSlow$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readLongSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readLongSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readLongSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readLongSlow$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r8 = r4
            r4 = r1
            r1 = r5
            goto L5d
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L44:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7c
            r8 = 8
            r2 = r8
            r4 = r1
            r8 = r7
            r1 = r8
        L4e:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r5 = access$awaitSuspend(r8, r2, r0)
            if (r5 != r4) goto L5d
            return r4
        L5d:
            kotlinx.io.core.ByteReadPacket r5 = access$getReadable$p(r8)
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L78
            kotlinx.io.core.ByteReadPacket r8 = r1.readable
            long r2 = r8.readLong()
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r2)
            r8.longValue()
            r1.afterRead()
            return r8
        L78:
            access$checkClosed(r8, r2)
            goto L4e
        L7c:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readLongSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readPacket(int i, int i2, @NotNull Continuation<? super ByteReadPacket> continuation) {
        return readPacket$suspendImpl(this, i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readPacketSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.BytePacketBuilder r10, int r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.io.core.ByteReadPacket> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readPacketSuspend$1
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readPacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readPacketSuspend$1
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4b
            if (r2 != r3) goto L43
            int r10 = r0.I$2
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.io.core.BytePacketBuilder r2 = (kotlinx.io.core.BytePacketBuilder) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r5 = r12 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3e
            r12 = r4
            r8 = r11
            r11 = r10
            r10 = r2
            r2 = r1
            r1 = r8
            goto L52
        L3e:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r10 = r12.exception
            throw r10
        L43:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L4b:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L83
            r12 = r9
            r2 = r1
            r1 = r11
        L52:
            if (r11 <= 0) goto L7e
            long r4 = (long) r11
            kotlinx.io.core.ByteReadPacket r6 = r12.readable
            long r6 = r6.m12336getRemaining()
            long r4 = java.lang.Math.min(r4, r6)
            int r4 = (int) r4
            int r11 = r11 - r4
            kotlinx.io.core.ByteReadPacket r5 = r12.readable
            r10.writePacket(r5, r4)
            r12.afterRead()
            if (r11 <= 0) goto L52
            r0.L$0 = r12
            r0.L$1 = r10
            r0.I$0 = r1
            r0.I$1 = r11
            r0.I$2 = r4
            r0.label = r3
            java.lang.Object r4 = r12.awaitSuspend(r3, r0)
            if (r4 != r2) goto L52
            return r2
        L7e:
            kotlinx.io.core.ByteReadPacket r10 = r10.build()
            return r10
        L83:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r10 = r12.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readPacketSuspend(kotlinx.io.core.BytePacketBuilder, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readRemaining(long j, int i, @NotNull Continuation<? super ByteReadPacket> continuation) {
        return readRemaining$suspendImpl(this, j, i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readRemainingSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.BytePacketBuilder r11, long r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.io.core.ByteReadPacket> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readRemainingSuspend$1
            if (r0 == 0) goto L13
            r0 = r14
            kotlinx.coroutines.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readRemainingSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readRemainingSuspend$1
            r0.<init>(r10, r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L45
            if (r2 != r3) goto L3d
            long r11 = r0.J$0
            java.lang.Object r13 = r0.L$1
            kotlinx.io.core.BytePacketBuilder r13 = (kotlinx.io.core.BytePacketBuilder) r13
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r14 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L38
            r14 = r2
            r8 = r11
            r11 = r13
            r12 = r8
            goto L4a
        L38:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r11 = r14.exception
            throw r11
        L3d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L45:
            boolean r2 = r14 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L88
            r14 = r10
        L4a:
            int r2 = r11.getSize()
            long r4 = (long) r2
            int r2 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r2 >= 0) goto L83
            kotlinx.io.core.ByteReadPacket r2 = r14.readable
            r11.writePacket(r2)
            r14.afterRead()
            kotlinx.io.core.ByteReadPacket r2 = r14.readable
            long r4 = r2.m12336getRemaining()
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L74
            kotlinx.io.core.BytePacketBuilder r2 = r14.writable
            int r2 = r2.getSize()
            if (r2 != 0) goto L74
            boolean r2 = r14.closed
            if (r2 == 0) goto L74
            goto L83
        L74:
            r0.L$0 = r14
            r0.L$1 = r11
            r0.J$0 = r12
            r0.label = r3
            java.lang.Object r2 = r14.awaitSuspend(r3, r0)
            if (r2 != r1) goto L4a
            return r1
        L83:
            kotlinx.io.core.ByteReadPacket r11 = r11.build()
            return r11
        L88:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r11 = r14.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readRemainingSuspend(kotlinx.io.core.BytePacketBuilder, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @ExperimentalIoApi
    public void readSession(@NotNull Function1<? super ReadSession, Unit> consumer) {
        Intrinsics.checkParameterIsNotNull(consumer, "consumer");
        try {
            consumer.mo12165invoke(this);
        } finally {
            completeReading();
        }
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readShort(@NotNull Continuation<? super Short> continuation) {
        return readShort$suspendImpl(this, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0059 -> B:24:0x005c). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readShortSlow(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Short> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readShortSlow$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readShortSlow$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readShortSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readShortSlow$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readShortSlow$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            r8 = r4
            r4 = r1
            r1 = r5
            goto L5c
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L44:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7b
            r8 = 2
            r2 = r8
            r4 = r1
            r8 = r7
            r1 = r8
        L4d:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r5 = access$awaitSuspend(r8, r2, r0)
            if (r5 != r4) goto L5c
            return r4
        L5c:
            kotlinx.io.core.ByteReadPacket r5 = access$getReadable$p(r8)
            boolean r5 = r5.hasBytes(r2)
            if (r5 == 0) goto L77
            kotlinx.io.core.ByteReadPacket r8 = r1.readable
            short r8 = r8.readShort()
            java.lang.Short r8 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r8)
            r8.shortValue()
            r1.afterRead()
            return r8
        L77:
            access$checkClosed(r8, r2)
            goto L4d
        L7b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readShortSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @ExperimentalIoApi
    @Nullable
    public Object readSuspendableSession(@NotNull Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return readSuspendableSession$suspendImpl(this, function2, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readUTF8Line(int i, @NotNull Continuation<? super String> continuation) {
        return readUTF8Line$suspendImpl(this, i, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public <A extends Appendable> Object readUTF8LineTo(@NotNull A a, int i, @NotNull Continuation<? super Boolean> continuation) {
        return readUTF8LineTo$suspendImpl(this, a, i, continuation);
    }

    @Override // kotlinx.coroutines.io.ReadSession
    @Nullable
    public IoBuffer request(int i) {
        Throwable th = this.closedCause;
        if (th == null) {
            completeReading();
            IoBuffer prepareReadHead = this.readable.prepareReadHead(i);
            if (prepareReadHead == null) {
                this.lastReadView = IoBuffer.Companion.getEmpty();
                this.lastReadAvailable = 0;
            } else {
                this.lastReadView = prepareReadHead;
                this.lastReadAvailable = prepareReadHead.getReadRemaining();
            }
            if (prepareReadHead != null) {
                prepareReadHead.setByteOrder(getReadByteOrder());
            }
            return prepareReadHead;
        }
        throw th;
    }

    protected final void setClosed(boolean z) {
        this.closed = z;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public void setReadByteOrder(@NotNull ByteOrder newOrder) {
        Intrinsics.checkParameterIsNotNull(newOrder, "newOrder");
        if (this.readByteOrder != newOrder) {
            this.readByteOrder = newOrder;
            this.readable.setByteOrder(newOrder);
        }
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public void setWriteByteOrder(@NotNull ByteOrder newOrder) {
        Intrinsics.checkParameterIsNotNull(newOrder, "newOrder");
        if (this.writeByteOrder != newOrder) {
            this.writeByteOrder = newOrder;
            this.writable.setByteOrder(newOrder);
        }
    }

    public final long transferTo$kotlinx_coroutines_io(@NotNull ByteChannelSequentialBase dst, long j) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = this.readable.m12336getRemaining();
        if (m12336getRemaining <= j) {
            dst.writable.writePacket(this.readable);
            dst.afterWrite();
            afterRead();
            return m12336getRemaining;
        }
        return 0L;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, ioBuffer, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0073 A[PHI: r7 
      PHI: (r7v5 java.lang.Object) = (r7v4 java.lang.Object), (r7v1 java.lang.Object) binds: [B:31:0x0070, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeAvailableSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L73
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L74
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.awaitFreeSpace(r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r6, r0)
            if (r7 != r1) goto L73
            return r1
        L73:
            return r7
        L74:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.writeAvailableSuspend(kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeByte(byte b, @NotNull Continuation<? super Unit> continuation) {
        return writeByte$suspendImpl(this, b, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeDouble(double d, @NotNull Continuation<? super Unit> continuation) {
        return writeDouble$suspendImpl(this, d, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFloat(float f, @NotNull Continuation<? super Unit> continuation) {
        return writeFloat$suspendImpl(this, f, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, ioBuffer, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeInt(int i, @NotNull Continuation<? super Unit> continuation) {
        return writeInt$suspendImpl(this, i, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeLong(long j, @NotNull Continuation<? super Unit> continuation) {
        return writeLong$suspendImpl(this, j, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writePacket(@NotNull ByteReadPacket byteReadPacket, @NotNull Continuation<? super Unit> continuation) {
        return writePacket$suspendImpl(this, byteReadPacket, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeShort(short s, @NotNull Continuation<? super Unit> continuation) {
        return writeShort$suspendImpl(this, s, continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @ExperimentalIoApi
    @Nullable
    public Object writeSuspendSession(@NotNull Function2<? super WriterSuspendSession, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return writeSuspendSession$suspendImpl(this, function2, continuation);
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, byte[] bArr, int i, int i2, Continuation continuation) {
        byteChannelSequentialBase.writable.writeFully(bArr, i, i2);
        return byteChannelSequentialBase.awaitFreeSpace(continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[PHI: r9 
      PHI: (r9v5 java.lang.Object) = (r9v4 java.lang.Object), (r9v1 java.lang.Object) binds: [B:31:0x0080, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull byte[] r6, int r7, int r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$2
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$2 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$2 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailableSuspend$2
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5c
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            int r6 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L39
            goto L83
        L39:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r9 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L57
            goto L72
        L57:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L5c:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L84
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.awaitSuspend(r4, r0)
            if (r9 != r1) goto L71
            return r1
        L71:
            r2 = r5
        L72:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.readAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L83
            return r1
        L83:
            return r9
        L84:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0071 -> B:26:0x0076). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull byte[] r9, int r10, int r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$2
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readFullySuspend$2
            r0.<init>(r8, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4c
            if (r2 != r3) goto L44
            int r9 = r0.I$2
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r5 = r12 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3f
            r6 = r4
            r4 = r10
            r10 = r6
            r7 = r0
            r0 = r11
            r11 = r1
            r1 = r7
            goto L76
        L3f:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r9 = r12.exception
            throw r9
        L44:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L4c:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8f
            r12 = 0
            r2 = r1
            r1 = r0
            r0 = r10
            r10 = r8
            r6 = r12
            r12 = r9
            r9 = r6
        L58:
            if (r9 >= r11) goto L8c
            int r4 = r0 + r9
            int r5 = r11 - r9
            r1.L$0 = r10
            r1.L$1 = r12
            r1.I$0 = r0
            r1.I$1 = r11
            r1.I$2 = r9
            r1.label = r3
            java.lang.Object r4 = r10.readAvailable(r12, r4, r5, r1)
            if (r4 != r2) goto L71
            return r2
        L71:
            r6 = r4
            r4 = r11
            r11 = r2
            r2 = r12
            r12 = r6
        L76:
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            r5 = -1
            if (r12 == r5) goto L84
            int r9 = r9 + r12
            r12 = r2
            r2 = r11
            r11 = r4
            goto L58
        L84:
            java.io.EOFException r9 = new java.io.EOFException
            java.lang.String r10 = "Unexpected end of stream"
            r9.<init>(r10)
            throw r9
        L8c:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L8f:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r9 = r12.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[PHI: r9 
      PHI: (r9v5 java.lang.Object) = (r9v4 java.lang.Object), (r9v1 java.lang.Object) binds: [B:31:0x0080, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeAvailableSuspend(@org.jetbrains.annotations.NotNull byte[] r6, int r7, int r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$2
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailableSuspend$2
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5c
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            int r6 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L39
            goto L83
        L39:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r2 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r2
            boolean r4 = r9 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L57
            goto L72
        L57:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L5c:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L84
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.awaitFreeSpace(r0)
            if (r9 != r1) goto L71
            return r1
        L71:
            r2 = r5
        L72:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.writeAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L83
            return r1
        L83:
            return r9
        L84:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.writeAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readFully$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r5, byte[] r6, int r7, int r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readFully$5
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFully$5 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readFully$5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readFully$5 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readFully$5
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5f
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            int r5 = r0.I$2
            int r5 = r0.I$1
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$1
            byte[] r5 = (byte[]) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r5 = r9 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3b
            goto L99
        L3b:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        L40:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L48:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r5 = r0.L$1
            r6 = r5
            byte[] r6 = (byte[]) r6
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5a
            goto L74
        L5a:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        L5f:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La2
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.readAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L74
            return r1
        L74:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            if (r9 != r8) goto L7f
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L7f:
            r2 = -1
            if (r9 == r2) goto L9a
            int r2 = r7 + r9
            int r4 = r8 - r9
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.I$2 = r9
            r0.label = r3
            java.lang.Object r9 = r5.readFullySuspend(r6, r2, r4, r0)
            if (r9 != r1) goto L99
            return r1
        L99:
            return r9
        L9a:
            java.io.EOFException r5 = new java.io.EOFException
            java.lang.String r6 = "Unexpected end of stream"
            r5.<init>(r6)
            throw r5
        La2:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readFully$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r5, byte[] r6, int r7, int r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$2
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$2 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$2 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$writeAvailable$2
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L60
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            int r5 = r0.I$2
            int r6 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r6 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r6
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L3b
            goto La9
        L3b:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        L40:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L48:
            int r5 = r0.I$2
            int r5 = r0.I$1
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$1
            byte[] r5 = (byte[]) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r5 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r5
            boolean r5 = r9 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L5b
            goto L89
        L5b:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        L60:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lae
            if (r8 != 0) goto L6c
            r5 = 0
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        L6c:
            int r9 = r5.getAvailableForWrite()
            int r9 = java.lang.Math.min(r8, r9)
            if (r9 != 0) goto L90
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.I$2 = r9
            r0.label = r4
            java.lang.Object r9 = r5.writeAvailableSuspend(r6, r7, r8, r0)
            if (r9 != r1) goto L89
            return r1
        L89:
            java.lang.Number r9 = (java.lang.Number) r9
            int r5 = r9.intValue()
            goto La9
        L90:
            kotlinx.io.core.BytePacketBuilder r2 = r5.writable
            r2.writeFully(r6, r7, r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.I$2 = r9
            r0.label = r3
            java.lang.Object r5 = r5.awaitFreeSpace(r0)
            if (r5 != r1) goto La8
            return r1
        La8:
            r5 = r9
        La9:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        Lae:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r5 = r9.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.writeAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase r4, byte[] r5, int r6, int r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$2
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$2 r0 = (kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$2 r0 = new kotlinx.coroutines.io.ByteChannelSequentialBase$readAvailable$2
            r0.<init>(r4, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            int r4 = r0.I$1
            int r4 = r0.I$0
            java.lang.Object r4 = r0.L$1
            byte[] r4 = (byte[]) r4
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialBase r4 = (kotlinx.coroutines.io.ByteChannelSequentialBase) r4
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L36
            goto L7e
        L36:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r4 = r8.exception
            throw r4
        L3b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L43:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L89
            kotlinx.io.core.ByteReadPacket r8 = r4.readable
            boolean r8 = r8.canRead()
            if (r8 == 0) goto L64
            long r7 = (long) r7
            kotlinx.io.core.ByteReadPacket r0 = r4.readable
            long r0 = r0.m12336getRemaining()
            long r7 = java.lang.Math.min(r7, r0)
            int r7 = (int) r7
            kotlinx.io.core.ByteReadPacket r8 = r4.readable
            r8.readFully(r5, r6, r7)
            r4.afterRead()
            goto L84
        L64:
            boolean r8 = r4.closed
            if (r8 == 0) goto L6d
            int r7 = r4.readAvailableClosed()
            goto L84
        L6d:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.I$0 = r6
            r0.I$1 = r7
            r0.label = r3
            java.lang.Object r8 = r4.readAvailableSuspend(r5, r6, r7, r0)
            if (r8 != r1) goto L7e
            return r1
        L7e:
            java.lang.Number r8 = (java.lang.Number) r8
            int r7 = r8.intValue()
        L84:
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r4
        L89:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r4 = r8.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialBase.readAvailable$suspendImpl(kotlinx.coroutines.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
