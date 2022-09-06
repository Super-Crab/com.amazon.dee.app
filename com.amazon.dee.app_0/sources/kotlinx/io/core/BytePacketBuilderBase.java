package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Builder.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed in future releases")
@DangerousInternalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b'\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u0015\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\tH\u0001J\b\u0010'\u001a\u00020%H\u0007J\u0010\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*H\u0016J$\u0010(\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0016J\u0012\u0010(\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010/H\u0016J\"\u0010(\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010/2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0016J \u00100\u001a\u00020\t2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J \u00100\u001a\u00020\t2\u0006\u0010+\u001a\u00020/2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\b\u00101\u001a\u00020\u0006H\u0001J\u0018\u00102\u001a\u00020%2\u0006\u0010&\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\u0015\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\u0006H ¢\u0006\u0002\b8J\u0010\u00109\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\tH\u0007J\b\u0010:\u001a\u00020%H&J\u0006\u0010;\u001a\u00020%J%\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020\t2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0?H\u0081\bJ\u000e\u0010@\u001a\u00020%2\u0006\u00104\u001a\u000205J\u000e\u0010A\u001a\u00020%2\u0006\u00104\u001a\u00020BJ\u000e\u0010C\u001a\u00020%2\u0006\u00104\u001a\u00020DJ\u001e\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tJ \u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020J2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016J \u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020K2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016J \u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020L2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016J \u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020M2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016J \u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020N2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0016J\u0018\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\tH\u0016J\u000e\u0010O\u001a\u00020%2\u0006\u00104\u001a\u00020\tJ\u000e\u0010P\u001a\u00020%2\u0006\u00104\u001a\u000203J9\u0010Q\u001a\u00020%2\u0006\u0010=\u001a\u00020\t2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020T0S2\u0018\u0010>\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0UH\u0082\bJ\u0010\u0010V\u001a\u00020%2\u0006\u0010W\u001a\u00020XH\u0016J\u0016\u0010V\u001a\u00020%2\u0006\u0010W\u001a\u00020X2\u0006\u0010&\u001a\u00020\tJ\u0016\u0010V\u001a\u00020%2\u0006\u0010W\u001a\u00020X2\u0006\u0010&\u001a\u000203J\u000e\u0010Y\u001a\u00020%2\u0006\u00104\u001a\u00020ZJ\u000e\u0010[\u001a\u00020%2\u0006\u0010\\\u001a\u00020/J\u000e\u0010[\u001a\u00020%2\u0006\u0010]\u001a\u00020^J\u0015\u0010_\u001a\u00020\t*\u00020\u00062\u0006\u00104\u001a\u00020\tH\u0082\bR\u001a\u0010\b\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR,\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0012\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006`"}, d2 = {"Lkotlinx/io/core/BytePacketBuilderBase;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lkotlinx/io/core/Output;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/io/pool/ObjectPool;)V", "_size", "", "get_size", "()I", "set_size", "(I)V", "value", "Lkotlinx/io/core/ByteOrder;", "byteOrder", "byteOrder$annotations", "()V", "getByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "doNotImplementOutputButExtendAbstractOutputInstead", "", "doNotImplementOutputButExtendAbstractOutputInstead$annotations", "getDoNotImplementOutputButExtendAbstractOutputInstead", "()Ljava/lang/Void;", "getPool", "()Lkotlinx/io/pool/ObjectPool;", "tail", "tail$annotations", "getTail", "()Lkotlinx/io/core/IoBuffer;", "setTail", "(Lkotlinx/io/core/IoBuffer;)V", "addSize", "", JsonReportFormat.COUNT, "afterHeadWrite", "append", "c", "", "csq", "", "start", "end", "", "appendChars", "appendNewBuffer", "fill", "", "v", "", "last", "buffer", "last$kotlinx_io", "prepareWriteHead", "release", "reset", "write", "size", "block", "Lkotlin/Function1;", "writeByte", "writeDouble", "", "writeFloat", "", "writeFully", "src", "", "offset", "length", "", "", "", "", "", "writeInt", "writeLong", "writeLoop", "predicate", "Lkotlin/Function0;", "", "Lkotlin/Function2;", "writePacket", "p", "Lkotlinx/io/core/ByteReadPacket;", "writeShort", "", "writeStringUtf8", "cs", "s", "", "putUtf8Char", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class BytePacketBuilderBase implements Appendable, Output {
    private int _size;
    @NotNull
    private ByteOrder byteOrder;
    @NotNull
    private final ObjectPool<IoBuffer> pool;
    @NotNull
    private IoBuffer tail;

    public BytePacketBuilderBase(@NotNull ObjectPool<IoBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.pool = pool;
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.tail = IoBuffer.Companion.getEmpty();
    }

    private final int appendChars(CharSequence charSequence, int i, int i2) {
        if (i >= i2) {
            return i;
        }
        IoBuffer ioBuffer = this.tail;
        if (ioBuffer.canWrite()) {
            i = ioBuffer.appendChars(charSequence, i, i2);
        }
        while (i < i2) {
            i = appendNewBuffer().appendChars(charSequence, i, i2);
        }
        this._size = -1;
        return i;
    }

    @Deprecated(message = "Write with writeXXXLittleEndian or do X.reverseByteOrder() and then writeXXX instead.")
    public static /* synthetic */ void byteOrder$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Suppress warning.")
    public static /* synthetic */ void doNotImplementOutputButExtendAbstractOutputInstead$annotations() {
    }

    private final int putUtf8Char(@NotNull IoBuffer ioBuffer, int i) {
        if (1 <= i && 127 >= i) {
            ioBuffer.writeByte((byte) i);
            return 1;
        } else if (i > 2047) {
            ioBuffer.writeByte((byte) (((i >> 12) & 15) | 224));
            ioBuffer.writeByte((byte) (((i >> 6) & 63) | 128));
            ioBuffer.writeByte((byte) ((i & 63) | 128));
            return 3;
        } else {
            ioBuffer.writeByte((byte) (((i >> 6) & 31) | 192));
            ioBuffer.writeByte((byte) ((i & 63) | 128));
            return 2;
        }
    }

    @PublishedApi
    public static /* synthetic */ void tail$annotations() {
    }

    private final void writeLoop(int i, Function0<Boolean> function0, Function2<? super IoBuffer, ? super Integer, Integer> function2) {
        if (!function0.mo12560invoke().booleanValue()) {
            return;
        }
        int i2 = 0;
        IoBuffer tail = getTail();
        int writeRemaining = tail.getWriteRemaining();
        do {
            if (writeRemaining < i) {
                tail = appendNewBuffer();
                writeRemaining = tail.getWriteRemaining();
            }
            int intValue = function2.mo12248invoke(tail, Integer.valueOf(writeRemaining)).intValue();
            i2 += intValue;
            writeRemaining -= intValue;
        } while (function0.mo12560invoke().booleanValue());
        addSize(i2);
    }

    @PublishedApi
    public final void addSize(int i) {
        int i2 = this._size;
        if (i2 != -1) {
            this._size = i2 + i;
        }
    }

    @DangerousInternalIoApi
    public final void afterHeadWrite() {
        this._size = -1;
    }

    @PublishedApi
    @NotNull
    public final IoBuffer appendNewBuffer() {
        IoBuffer mo12351borrow = this.pool.mo12351borrow();
        mo12351borrow.reserveEndGap(IoBuffer.Companion.getReservedSize());
        mo12351borrow.setByteOrder(this.byteOrder);
        last$kotlinx_io(mo12351borrow);
        return mo12351borrow;
    }

    @Override // kotlinx.io.core.Output
    public void fill(final long j, byte b) {
        boolean z;
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (!(i >= 0)) {
            new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$fill$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("n shouldn't be negative: ");
                    outline107.append(j);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        }
        if (!(i > 0)) {
            return;
        }
        IoBuffer tail = getTail();
        int writeRemaining = tail.getWriteRemaining();
        long j2 = j;
        int i2 = 0;
        do {
            if (writeRemaining < 1) {
                tail = appendNewBuffer();
                writeRemaining = tail.getWriteRemaining();
            }
            int min = (int) Math.min(writeRemaining, j);
            long j3 = min;
            tail.fill(j3, b);
            j2 -= j3;
            i2 += min;
            writeRemaining -= min;
            if (j2 > 0) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
        } while (z);
        addSize(i2);
    }

    @Override // kotlinx.io.core.Output
    @NotNull
    public final ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    @Override // kotlinx.io.core.Output
    @NotNull
    public final /* synthetic */ Void getDoNotImplementOutputButExtendAbstractOutputInstead() {
        throw new IllegalStateException("Should be never accessed.".toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final ObjectPool<IoBuffer> getPool() {
        return this.pool;
    }

    @NotNull
    public final IoBuffer getTail() {
        return this.tail;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int get_size() {
        return this._size;
    }

    public abstract void last$kotlinx_io(@NotNull IoBuffer ioBuffer);

    @DangerousInternalIoApi
    @NotNull
    public final IoBuffer prepareWriteHead(int i) {
        return this.tail.getWriteRemaining() >= i ? this.tail : appendNewBuffer();
    }

    public abstract void release();

    public final void reset() {
        release();
    }

    @Override // kotlinx.io.core.Output
    public final void setByteOrder(@NotNull ByteOrder value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.byteOrder = value;
        IoBuffer ioBuffer = this.tail;
        if (ioBuffer.canWrite()) {
            ioBuffer.setByteOrder(value);
        }
    }

    public final void setTail(@NotNull IoBuffer ioBuffer) {
        Intrinsics.checkParameterIsNotNull(ioBuffer, "<set-?>");
        this.tail = ioBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void set_size(int i) {
        this._size = i;
    }

    @PublishedApi
    public final void write(int i, @NotNull Function1<? super IoBuffer, Integer> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < i) {
            tail = appendNewBuffer();
        }
        addSize(block.mo12165invoke(tail).intValue());
    }

    @Override // kotlinx.io.core.Output
    public final void writeByte(byte b) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 1) {
            tail = appendNewBuffer();
        }
        tail.writeByte(b);
        addSize(1);
    }

    @Override // kotlinx.io.core.Output
    public final void writeDouble(double d) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 8) {
            tail = appendNewBuffer();
        }
        tail.writeDouble(d);
        addSize(8);
    }

    @Override // kotlinx.io.core.Output
    public final void writeFloat(float f) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 4) {
            tail = appendNewBuffer();
        }
        tail.writeFloat(f);
        addSize(4);
    }

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final short[] src, final int i, final int i2) {
        int lastIndex;
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 >= 0) {
            int i3 = i + i2;
            lastIndex = ArraysKt___ArraysKt.getLastIndex(src);
            if (!(i3 < lastIndex)) {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$2
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        int lastIndex2;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                        outline107.append(i);
                        outline107.append(") + length (");
                        outline107.append(i2);
                        outline107.append(") >= src.lastIndex (");
                        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(src);
                        outline107.append(lastIndex2);
                        outline107.append(')');
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }.doFail();
                throw null;
            } else if (i2 == 0) {
                return;
            } else {
                if (!(i2 > 0)) {
                    return;
                }
                IoBuffer tail = getTail();
                int writeRemaining = tail.getWriteRemaining();
                int i4 = 0;
                do {
                    if (writeRemaining < 2) {
                        tail = appendNewBuffer();
                        writeRemaining = tail.getWriteRemaining();
                    }
                    int min = Math.min(writeRemaining >> 1, i2);
                    tail.writeFully(src, i, min);
                    i += min;
                    i2 -= min;
                    int i5 = min * 2;
                    i4 += i5;
                    writeRemaining -= i5;
                    if (i2 > 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                } while (z);
                addSize(i4);
                return;
            }
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$1
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

    @Override // kotlinx.io.core.Output
    public final void writeInt(int i) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 4) {
            tail = appendNewBuffer();
        }
        tail.writeInt(i);
        addSize(4);
    }

    @Override // kotlinx.io.core.Output
    public final void writeLong(long j) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 8) {
            tail = appendNewBuffer();
        }
        tail.writeLong(j);
        addSize(8);
    }

    public void writePacket(@NotNull ByteReadPacket p) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        while (true) {
            IoBuffer steal$kotlinx_io = p.steal$kotlinx_io();
            if (steal$kotlinx_io != null) {
                last$kotlinx_io(steal$kotlinx_io);
            } else {
                return;
            }
        }
    }

    @Override // kotlinx.io.core.Output
    public final void writeShort(short s) {
        IoBuffer tail = getTail();
        if (tail.getWriteRemaining() < 2) {
            tail = appendNewBuffer();
        }
        tail.writeShort(s);
        addSize(2);
    }

    public final void writeStringUtf8(@NotNull String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        mo12333append((CharSequence) s, 0, s.length());
    }

    public final void writeStringUtf8(@NotNull CharSequence cs) {
        Intrinsics.checkParameterIsNotNull(cs, "cs");
        mo12333append(cs, 0, cs.length());
    }

    public final void writePacket(@NotNull ByteReadPacket p, int i) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        while (i > 0) {
            int headRemaining = p.getHeadRemaining();
            if (headRemaining <= i) {
                i -= headRemaining;
                IoBuffer steal$kotlinx_io = p.steal$kotlinx_io();
                if (steal$kotlinx_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                last$kotlinx_io(steal$kotlinx_io);
            } else {
                IoBuffer head = p.getHead();
                int readRemaining = head.getReadRemaining();
                if (readRemaining < 1) {
                    head = p.prepareRead(1, head);
                    readRemaining = head != null ? head.getReadRemaining() : 0;
                }
                if (head == null) {
                    return;
                }
                writeFully(head, i);
                int readRemaining2 = head.getReadRemaining();
                int i2 = readRemaining - readRemaining2;
                if (i2 > 0) {
                    p.setHeadRemaining(p.getHeadRemaining() - i2);
                }
                if (readRemaining2 != 0) {
                    return;
                }
                p.ensureNext(head);
                return;
            }
        }
    }

    @Override // java.lang.Appendable
    @NotNull
    /* renamed from: append */
    public BytePacketBuilderBase mo12332append(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            appendChars("null", 0, 4);
        } else {
            appendChars(charSequence, 0, charSequence.length());
        }
        return this;
    }

    private final int appendChars(char[] cArr, int i, int i2) {
        if (i >= i2) {
            return i;
        }
        IoBuffer ioBuffer = this.tail;
        if (ioBuffer.canWrite()) {
            i = ioBuffer.appendChars(cArr, i, i2);
        }
        while (i < i2) {
            i = appendNewBuffer().appendChars(cArr, i, i2);
        }
        this._size = -1;
        return i;
    }

    @Override // java.lang.Appendable
    @NotNull
    /* renamed from: append */
    public BytePacketBuilderBase mo12333append(@Nullable CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return mo12333append("null", i, i2);
        }
        appendChars(charSequence, i, i2);
        return this;
    }

    @Override // kotlinx.io.core.Output
    @NotNull
    public Appendable append(@NotNull char[] csq, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        appendChars(csq, i, i2);
        return this;
    }

    @Override // java.lang.Appendable
    @NotNull
    /* renamed from: append */
    public BytePacketBuilderBase mo12331append(char c) {
        IoBuffer tail = getTail();
        int i = 3;
        if (tail.getWriteRemaining() < 3) {
            tail = appendNewBuffer();
        }
        if (1 <= c && 127 >= c) {
            tail.writeByte((byte) c);
            i = 1;
        } else if (c > 2047) {
            tail.writeByte((byte) (((c >> '\f') & 15) | 224));
            tail.writeByte((byte) (((c >> 6) & 63) | 128));
            tail.writeByte((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
        } else {
            tail.writeByte((byte) (((c >> 6) & 31) | 192));
            tail.writeByte((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
            i = 2;
        }
        addSize(i);
        return this;
    }

    public final void writePacket(@NotNull ByteReadPacket p, long j) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        while (j > 0) {
            long headRemaining = p.getHeadRemaining();
            if (headRemaining <= j) {
                j -= headRemaining;
                IoBuffer steal$kotlinx_io = p.steal$kotlinx_io();
                if (steal$kotlinx_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                last$kotlinx_io(steal$kotlinx_io);
            } else {
                IoBuffer head = p.getHead();
                int readRemaining = head.getReadRemaining();
                if (readRemaining < 1) {
                    head = p.prepareRead(1, head);
                    readRemaining = head != null ? head.getReadRemaining() : 0;
                }
                if (head == null) {
                    return;
                }
                writeFully(head, (int) j);
                int readRemaining2 = head.getReadRemaining();
                int i = readRemaining - readRemaining2;
                if (i > 0) {
                    p.setHeadRemaining(p.getHeadRemaining() - i);
                }
                if (readRemaining2 != 0) {
                    return;
                }
                p.ensureNext(head);
                return;
            }
        }
    }

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final int[] src, final int i, final int i2) {
        int lastIndex;
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 >= 0) {
            int i3 = i + i2;
            lastIndex = ArraysKt___ArraysKt.getLastIndex(src);
            if (!(i3 < lastIndex)) {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$4
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        int lastIndex2;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                        outline107.append(i);
                        outline107.append(") + length (");
                        outline107.append(i2);
                        outline107.append(") >= src.lastIndex (");
                        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(src);
                        outline107.append(lastIndex2);
                        outline107.append(')');
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }.doFail();
                throw null;
            }
            if (!(i2 > 0)) {
                return;
            }
            IoBuffer tail = getTail();
            int writeRemaining = tail.getWriteRemaining();
            int i4 = 0;
            do {
                if (writeRemaining < 4) {
                    tail = appendNewBuffer();
                    writeRemaining = tail.getWriteRemaining();
                }
                int min = Math.min(writeRemaining >> 2, i2);
                tail.writeFully(src, i, min);
                i += min;
                i2 -= min;
                int i5 = min * 4;
                i4 += i5;
                writeRemaining -= i5;
                if (i2 > 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
            addSize(i4);
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$3
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

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final long[] src, final int i, final int i2) {
        int lastIndex;
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 >= 0) {
            int i3 = i + i2;
            lastIndex = ArraysKt___ArraysKt.getLastIndex(src);
            if (!(i3 < lastIndex)) {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$6
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        int lastIndex2;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                        outline107.append(i);
                        outline107.append(") + length (");
                        outline107.append(i2);
                        outline107.append(") >= src.lastIndex (");
                        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(src);
                        outline107.append(lastIndex2);
                        outline107.append(')');
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }.doFail();
                throw null;
            }
            if (!(i2 > 0)) {
                return;
            }
            IoBuffer tail = getTail();
            int writeRemaining = tail.getWriteRemaining();
            int i4 = 0;
            do {
                if (writeRemaining < 8) {
                    tail = appendNewBuffer();
                    writeRemaining = tail.getWriteRemaining();
                }
                int min = Math.min(writeRemaining >> 3, i2);
                tail.writeFully(src, i, min);
                i += min;
                i2 -= min;
                int i5 = min * 8;
                i4 += i5;
                writeRemaining -= i5;
                if (i2 > 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
            addSize(i4);
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$5
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

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final float[] src, final int i, final int i2) {
        int lastIndex;
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 >= 0) {
            int i3 = i + i2;
            lastIndex = ArraysKt___ArraysKt.getLastIndex(src);
            if (!(i3 < lastIndex)) {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$8
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        int lastIndex2;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                        outline107.append(i);
                        outline107.append(") + length (");
                        outline107.append(i2);
                        outline107.append(") >= src.lastIndex (");
                        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(src);
                        outline107.append(lastIndex2);
                        outline107.append(')');
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }.doFail();
                throw null;
            }
            if (!(i2 > 0)) {
                return;
            }
            IoBuffer tail = getTail();
            int writeRemaining = tail.getWriteRemaining();
            int i4 = 0;
            do {
                if (writeRemaining < 4) {
                    tail = appendNewBuffer();
                    writeRemaining = tail.getWriteRemaining();
                }
                int min = Math.min(writeRemaining >> 2, i2);
                tail.writeFully(src, i, min);
                i += min;
                i2 -= min;
                int i5 = min * 4;
                i4 += i5;
                writeRemaining -= i5;
                if (i2 > 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
            addSize(i4);
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$7
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

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final double[] src, final int i, final int i2) {
        int lastIndex;
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 >= 0) {
            int i3 = i + i2;
            lastIndex = ArraysKt___ArraysKt.getLastIndex(src);
            if (!(i3 < lastIndex)) {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$10
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        int lastIndex2;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("offset (");
                        outline107.append(i);
                        outline107.append(") + length (");
                        outline107.append(i2);
                        outline107.append(") >= src.lastIndex (");
                        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(src);
                        outline107.append(lastIndex2);
                        outline107.append(')');
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }.doFail();
                throw null;
            }
            if (!(i2 > 0)) {
                return;
            }
            IoBuffer tail = getTail();
            int writeRemaining = tail.getWriteRemaining();
            int i4 = 0;
            do {
                if (writeRemaining < 8) {
                    tail = appendNewBuffer();
                    writeRemaining = tail.getWriteRemaining();
                }
                int min = Math.min(writeRemaining >> 3, i2);
                tail.writeFully(src, i, min);
                i += min;
                i2 -= min;
                int i5 = min * 8;
                i4 += i5;
                writeRemaining -= i5;
                if (i2 > 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
            addSize(i4);
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$9
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

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull IoBuffer src, final int i) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        boolean z = true;
        if (i >= 0) {
            if (i > src.getReadRemaining()) {
                z = false;
            }
            if (z) {
                int min = Math.min(src.getReadRemaining(), i);
                if (min == 0) {
                    return;
                }
                IoBuffer ioBuffer = this.tail;
                if (!ioBuffer.canWrite()) {
                    ioBuffer = appendNewBuffer();
                }
                int i2 = min;
                while (true) {
                    int min2 = Math.min(ioBuffer.getWriteRemaining(), i2);
                    ioBuffer.writeFully(src, min2);
                    i2 -= min2;
                    if (i2 == 0) {
                        addSize(min);
                        return;
                    }
                    ioBuffer = appendNewBuffer();
                }
            } else {
                new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$12
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough bytes available in src buffer to read "), i, " bytes"));
                    }
                }.doFail();
                throw null;
            }
        } else {
            new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilderBase$writeFully$$inlined$require$11
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length shouldn't be negative: ");
                    outline107.append(i);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        }
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull byte[] src, int i, int i2) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (i2 == 0) {
            return;
        }
        if (!(i2 > 0)) {
            return;
        }
        IoBuffer tail = getTail();
        int writeRemaining = tail.getWriteRemaining();
        int i3 = 0;
        IoBuffer ioBuffer = tail;
        int i4 = 0;
        do {
            if (writeRemaining < 1) {
                IoBuffer appendNewBuffer = appendNewBuffer();
                ioBuffer = appendNewBuffer;
                writeRemaining = appendNewBuffer.getWriteRemaining();
            }
            int min = Math.min(writeRemaining, i2 - i4);
            ioBuffer.writeFully(src, i + i4, min);
            i4 += min;
            i3 += min;
            writeRemaining -= min;
            if (i4 < i2) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
        } while (z);
        addSize(i3);
    }
}
