package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteReadChannelJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 a2\u00020\u0001:\u0001aJ\u0012\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&JM\u0010\u0019\u001a\u00020\u001a2:\u0010\u001b\u001a6\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00070\u001cj\u0002`\"H¦@ø\u0001\u0000¢\u0006\u0002\u0010#J\u0019\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0011H¦@ø\u0001\u0000¢\u0006\u0002\u0010&J,\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(2\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u0002H(0)¢\u0006\u0002\b+H'¢\u0006\u0002\u0010,J@\u0010-\u001a\u0002H(\"\u0004\b\u0000\u0010(2'\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0/\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b+H§@ø\u0001\u0000¢\u0006\u0002\u0010#J/\u00100\u001a\u00020\u001a2\b\b\u0002\u00101\u001a\u00020\u00032\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001a0)H¦@ø\u0001\u0000¢\u0006\u0002\u00103J\u0019\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u00106J)\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u0002072\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010:J\u0019\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020;H¦@ø\u0001\u0000¢\u0006\u0002\u0010<J\u0011\u0010=\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0011\u0010?\u001a\u00020@H¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0011\u0010A\u001a\u00020BH¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0011\u0010C\u001a\u00020DH¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0019\u0010E\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u00106J)\u0010E\u001a\u00020\u001a2\u0006\u00105\u001a\u0002072\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010:J!\u0010E\u001a\u00020\u001a2\u0006\u00105\u001a\u00020;2\u0006\u0010F\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0011\u0010H\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0011\u0010I\u001a\u00020\u0011H¦@ø\u0001\u0000¢\u0006\u0002\u0010>J!\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010NJ!\u0010O\u001a\u00020K2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010M\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010QJ!\u0010R\u001a\u00020\u001a2\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020\u001a0)¢\u0006\u0002\b+H'J\u0011\u0010T\u001a\u00020UH¦@ø\u0001\u0000¢\u0006\u0002\u0010>J:\u0010V\u001a\u00020\u001a2'\u00102\u001a#\b\u0001\u0012\u0004\u0012\u00020W\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0/\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001c¢\u0006\u0002\b+H§@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001b\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010P\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010ZJ/\u0010[\u001a\u00020\u0007\"\f\b\u0000\u0010\\*\u00060]j\u0002`^2\u0006\u0010_\u001a\u0002H\\2\u0006\u0010P\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010`R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0018\u0010\n\u001a\u00020\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u00118&X§\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, d2 = {"Lkotlinx/coroutines/io/ByteReadChannel;", "", "availableForRead", "", "getAvailableForRead", "()I", "isClosedForRead", "", "()Z", "isClosedForWrite", "readByteOrder", "Lkotlinx/io/core/ByteOrder;", "getReadByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setReadByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "totalBytesRead", "", "totalBytesRead$annotations", "()V", "getTotalBytesRead", "()J", DeviceStateModule.CANCEL, "cause", "", "consumeEachBufferRange", "", "visitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "last", "Lkotlinx/coroutines/io/ConsumeEachBufferVisitor;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discard", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookAhead", "R", "Lkotlin/Function1;", "Lkotlinx/coroutines/io/LookAheadSession;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "lookAheadSuspend", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "Lkotlin/coroutines/Continuation;", "read", ReactProperties.GEOFENCE_MINIMUM_RANGE, "consumer", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailable", "dst", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "offset", "length", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/io/core/IoBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBoolean", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readByte", "", "readDouble", "", "readFloat", "", "readFully", JsonReportFormat.COUNT, "(Lkotlinx/io/core/IoBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readInt", "readLong", "readPacket", "Lkotlinx/io/core/ByteReadPacket;", "size", "headerSizeHint", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemaining", MetricsUtil.LegacyMetricTypes.LIMIT, "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readSession", "Lkotlinx/coroutines/io/ReadSession;", "readShort", "", "readSuspendableSession", "Lkotlinx/coroutines/io/SuspendableReadSession;", "readUTF8Line", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUTF8LineTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface ByteReadChannel {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: ByteReadChannelJVM.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/io/ByteReadChannel$Companion;", "", "()V", "Empty", "Lkotlinx/coroutines/io/ByteReadChannel;", "getEmpty", "()Lkotlinx/coroutines/io/ByteReadChannel;", "Empty$delegate", "Lkotlin/Lazy;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "Empty", "getEmpty()Lkotlinx/coroutines/io/ByteReadChannel;"))};
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Lazy Empty$delegate = LazyKt.lazy(ByteReadChannel$Companion$Empty$2.INSTANCE);

        private Companion() {
        }

        @NotNull
        public final ByteReadChannel getEmpty() {
            Lazy lazy = Empty$delegate;
            KProperty kProperty = $$delegatedProperties[0];
            return (ByteReadChannel) lazy.getValue();
        }
    }

    /* compiled from: ByteReadChannelJVM.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Nullable
        public static /* synthetic */ Object read$default(ByteReadChannel byteReadChannel, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    i = 1;
                }
                return byteReadChannel.read(i, function1, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: read");
        }

        @Deprecated(message = "Don't use byte count")
        public static /* synthetic */ void totalBytesRead$annotations() {
        }
    }

    boolean cancel(@Nullable Throwable th);

    @Nullable
    Object consumeEachBufferRange(@NotNull Function2<? super ByteBuffer, ? super Boolean, Boolean> function2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object discard(long j, @NotNull Continuation<? super Long> continuation);

    int getAvailableForRead();

    @NotNull
    ByteOrder getReadByteOrder();

    long getTotalBytesRead();

    boolean isClosedForRead();

    boolean isClosedForWrite();

    @ExperimentalIoApi
    <R> R lookAhead(@NotNull Function1<? super LookAheadSession, ? extends R> function1);

    @ExperimentalIoApi
    @Nullable
    <R> Object lookAheadSuspend(@NotNull Function2<? super LookAheadSuspendSession, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation);

    @Nullable
    Object read(int i, @NotNull Function1<? super ByteBuffer, Unit> function1, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object readAvailable(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object readAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object readAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object readBoolean(@NotNull Continuation<? super Boolean> continuation);

    @Nullable
    Object readByte(@NotNull Continuation<? super Byte> continuation);

    @Nullable
    Object readDouble(@NotNull Continuation<? super Double> continuation);

    @Nullable
    Object readFloat(@NotNull Continuation<? super Float> continuation);

    @Nullable
    Object readFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object readFully(@NotNull IoBuffer ioBuffer, int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object readFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object readInt(@NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object readLong(@NotNull Continuation<? super Long> continuation);

    @Nullable
    Object readPacket(int i, int i2, @NotNull Continuation<? super ByteReadPacket> continuation);

    @Nullable
    Object readRemaining(long j, int i, @NotNull Continuation<? super ByteReadPacket> continuation);

    @ExperimentalIoApi
    void readSession(@NotNull Function1<? super ReadSession, Unit> function1);

    @Nullable
    Object readShort(@NotNull Continuation<? super Short> continuation);

    @ExperimentalIoApi
    @Nullable
    Object readSuspendableSession(@NotNull Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object readUTF8Line(int i, @NotNull Continuation<? super String> continuation);

    @Nullable
    <A extends Appendable> Object readUTF8LineTo(@NotNull A a, int i, @NotNull Continuation<? super Boolean> continuation);

    void setReadByteOrder(@NotNull ByteOrder byteOrder);
}
