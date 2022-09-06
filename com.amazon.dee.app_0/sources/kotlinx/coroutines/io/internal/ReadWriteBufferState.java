package kotlinx.coroutines.io.internal;

import com.amazon.deecomms.common.Constants;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReadWriteBufferState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0007\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0010\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0017R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r\u0082\u0001\u0007\u001f !\"#$%¨\u0006&"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "", "backingBuffer", "Ljava/nio/ByteBuffer;", "capacity", "Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "(Ljava/nio/ByteBuffer;Lkotlinx/coroutines/io/internal/RingBufferCapacity;)V", "idle", "", "getIdle", "()Z", "readBuffer", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "writeBuffer", "getWriteBuffer", "startReading", "startReading$kotlinx_coroutines_io", "startWriting", "startWriting$kotlinx_coroutines_io", "stopReading", "stopReading$kotlinx_coroutines_io", "stopWriting", "stopWriting$kotlinx_coroutines_io", "IdleEmpty", "IdleNonEmpty", "Initial", "Reading", "ReadingWriting", "Terminated", "Writing", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleEmpty;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Terminated;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class ReadWriteBufferState {
    @JvmField
    @NotNull
    public final ByteBuffer backingBuffer;
    @JvmField
    @NotNull
    public final RingBufferCapacity capacity;

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleEmpty;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "()V", "idle", "", "getIdle", "()Z", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class IdleEmpty extends ReadWriteBufferState {
        public static final IdleEmpty INSTANCE = new IdleEmpty();

        private IdleEmpty() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), null);
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        public boolean getIdle() {
            return true;
        }

        @NotNull
        public String toString() {
            return "IDLE(empty)";
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "(Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;)V", "idle", "", "getIdle", "()Z", "getInitial", "()Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "startReading", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "startReading$kotlinx_coroutines_io", "startWriting", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "startWriting$kotlinx_coroutines_io", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class IdleNonEmpty extends ReadWriteBufferState {
        @NotNull
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdleNonEmpty(@NotNull Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkParameterIsNotNull(initial, "initial");
            this.initial = initial;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        public boolean getIdle() {
            return true;
        }

        @NotNull
        public final Initial getInitial() {
            return this.initial;
        }

        @NotNull
        public String toString() {
            return "IDLE(with buffer)";
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startReading$kotlinx_coroutines_io */
        public Reading mo12321startReading$kotlinx_coroutines_io() {
            return this.initial.getReadingState$kotlinx_coroutines_io();
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startWriting$kotlinx_coroutines_io */
        public Writing mo12317startWriting$kotlinx_coroutines_io() {
            return this.initial.getWritingState$kotlinx_coroutines_io();
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010 \u001a\u00020\u0013H\u0010¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u001dH\u0010¢\u0006\u0002\b#J\b\u0010$\u001a\u00020%H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0014\u0010\u001c\u001a\u00020\u001dX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006&"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "backingBuffer", "Ljava/nio/ByteBuffer;", "reservedSize", "", "(Ljava/nio/ByteBuffer;I)V", "idle", "", "getIdle", "()Z", "idleState", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "getIdleState$kotlinx_coroutines_io", "()Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "readBuffer", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "readingState", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "getReadingState$kotlinx_coroutines_io", "()Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "readingWritingState", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "getReadingWritingState$kotlinx_coroutines_io", "()Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "writeBuffer", "getWriteBuffer", "writingState", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "getWritingState$kotlinx_coroutines_io", "()Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "startReading", "startReading$kotlinx_coroutines_io", "startWriting", "startWriting$kotlinx_coroutines_io", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Initial extends ReadWriteBufferState {
        @NotNull
        private final IdleNonEmpty idleState;
        @NotNull
        private final ByteBuffer readBuffer;
        @NotNull
        private final Reading readingState;
        @NotNull
        private final ReadingWriting readingWritingState;
        @NotNull
        private final ByteBuffer writeBuffer;
        @NotNull
        private final Writing writingState;

        public /* synthetic */ Initial(ByteBuffer byteBuffer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(byteBuffer, (i2 & 2) != 0 ? 8 : i);
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        public boolean getIdle() {
            throw new IllegalStateException("Not available for initial state".toString());
        }

        @NotNull
        public final IdleNonEmpty getIdleState$kotlinx_coroutines_io() {
            return this.idleState;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getReadBuffer() {
            return this.readBuffer;
        }

        @NotNull
        public final Reading getReadingState$kotlinx_coroutines_io() {
            return this.readingState;
        }

        @NotNull
        public final ReadingWriting getReadingWritingState$kotlinx_coroutines_io() {
            return this.readingWritingState;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getWriteBuffer() {
            return this.writeBuffer;
        }

        @NotNull
        public final Writing getWritingState$kotlinx_coroutines_io() {
            return this.writingState;
        }

        @NotNull
        public String toString() {
            return "Initial";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Initial(@NotNull ByteBuffer backingBuffer, int i) {
            super(backingBuffer, new RingBufferCapacity(backingBuffer.capacity() - i), null);
            Intrinsics.checkParameterIsNotNull(backingBuffer, "backingBuffer");
            boolean z = true;
            if (backingBuffer.position() == 0) {
                if (backingBuffer.limit() != backingBuffer.capacity() ? false : z) {
                    ByteBuffer duplicate = backingBuffer.duplicate();
                    Intrinsics.checkExpressionValueIsNotNull(duplicate, "backingBuffer.duplicate()");
                    this.writeBuffer = duplicate;
                    ByteBuffer duplicate2 = backingBuffer.duplicate();
                    Intrinsics.checkExpressionValueIsNotNull(duplicate2, "backingBuffer.duplicate()");
                    this.readBuffer = duplicate2;
                    this.idleState = new IdleNonEmpty(this);
                    this.readingState = new Reading(this);
                    this.writingState = new Writing(this);
                    this.readingWritingState = new ReadingWriting(this);
                    return;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startReading$kotlinx_coroutines_io */
        public Reading mo12321startReading$kotlinx_coroutines_io() {
            return this.readingState;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startWriting$kotlinx_coroutines_io */
        public Writing mo12317startWriting$kotlinx_coroutines_io() {
            return this.writingState;
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "(Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;)V", "readBuffer", "Ljava/nio/ByteBuffer;", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "startWriting", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "startWriting$kotlinx_coroutines_io", "stopReading", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "stopReading$kotlinx_coroutines_io", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Reading extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Reading(@NotNull Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkParameterIsNotNull(initial, "initial");
            this.initial = initial;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        @NotNull
        public String toString() {
            return "Reading";
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startWriting$kotlinx_coroutines_io */
        public ReadingWriting mo12317startWriting$kotlinx_coroutines_io() {
            return this.initial.getReadingWritingState$kotlinx_coroutines_io();
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: stopReading$kotlinx_coroutines_io */
        public IdleNonEmpty mo12319stopReading$kotlinx_coroutines_io() {
            return this.initial.getIdleState$kotlinx_coroutines_io();
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "(Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;)V", "readBuffer", "Ljava/nio/ByteBuffer;", "getReadBuffer", "()Ljava/nio/ByteBuffer;", "writeBuffer", "getWriteBuffer", "stopReading", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "stopReading$kotlinx_coroutines_io", "stopWriting", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Reading;", "stopWriting$kotlinx_coroutines_io", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class ReadingWriting extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReadingWriting(@NotNull Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkParameterIsNotNull(initial, "initial");
            this.initial = initial;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        @NotNull
        public String toString() {
            return "Reading+Writing";
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: stopReading$kotlinx_coroutines_io */
        public Writing mo12319stopReading$kotlinx_coroutines_io() {
            return this.initial.getWritingState$kotlinx_coroutines_io();
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: stopWriting$kotlinx_coroutines_io */
        public Reading mo12322stopWriting$kotlinx_coroutines_io() {
            return this.initial.getReadingState$kotlinx_coroutines_io();
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Terminated;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "()V", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Terminated extends ReadWriteBufferState {
        public static final Terminated INSTANCE = new Terminated();

        private Terminated() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), null);
        }

        @NotNull
        public String toString() {
            return "Terminated";
        }
    }

    /* compiled from: ReadWriteBufferState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Writing;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "(Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;)V", "writeBuffer", "Ljava/nio/ByteBuffer;", "getWriteBuffer", "()Ljava/nio/ByteBuffer;", "startReading", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$ReadingWriting;", "startReading$kotlinx_coroutines_io", "stopWriting", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$IdleNonEmpty;", "stopWriting$kotlinx_coroutines_io", "toString", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Writing extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Writing(@NotNull Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkParameterIsNotNull(initial, "initial");
            this.initial = initial;
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        @NotNull
        public String toString() {
            return "Writing";
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: startReading$kotlinx_coroutines_io */
        public ReadingWriting mo12321startReading$kotlinx_coroutines_io() {
            return this.initial.getReadingWritingState$kotlinx_coroutines_io();
        }

        @Override // kotlinx.coroutines.io.internal.ReadWriteBufferState
        @NotNull
        /* renamed from: stopWriting$kotlinx_coroutines_io */
        public IdleNonEmpty mo12322stopWriting$kotlinx_coroutines_io() {
            return this.initial.getIdleState$kotlinx_coroutines_io();
        }
    }

    private ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity) {
        this.backingBuffer = byteBuffer;
        this.capacity = ringBufferCapacity;
    }

    public boolean getIdle() {
        return false;
    }

    @NotNull
    public ByteBuffer getReadBuffer() {
        throw new IllegalStateException(("read buffer is not available in state " + this).toString());
    }

    @NotNull
    public ByteBuffer getWriteBuffer() {
        throw new IllegalStateException(("write buffer is not available in state " + this).toString());
    }

    @NotNull
    /* renamed from: startReading$kotlinx_coroutines_io */
    public ReadWriteBufferState mo12321startReading$kotlinx_coroutines_io() {
        throw new IllegalStateException(("Reading is not available in state " + this).toString());
    }

    @NotNull
    /* renamed from: startWriting$kotlinx_coroutines_io */
    public ReadWriteBufferState mo12317startWriting$kotlinx_coroutines_io() {
        throw new IllegalStateException(("Writing is not available in state " + this).toString());
    }

    @NotNull
    /* renamed from: stopReading$kotlinx_coroutines_io */
    public ReadWriteBufferState mo12319stopReading$kotlinx_coroutines_io() {
        throw new IllegalStateException(("Unable to stop reading in state " + this).toString());
    }

    @NotNull
    /* renamed from: stopWriting$kotlinx_coroutines_io */
    public ReadWriteBufferState mo12322stopWriting$kotlinx_coroutines_io() {
        throw new IllegalStateException(("Unable to stop writing in state " + this).toString());
    }

    public /* synthetic */ ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, ringBufferCapacity);
    }
}
