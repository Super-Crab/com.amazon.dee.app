package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000005:\u0005bcdefB\u0007??\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016??\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002??\u0006\u0004\b\n\u0010\u000bJ#\u0010\u000f\u001a\u000e\u0012\u0002\b\u00030\rj\u0006\u0012\u0002\b\u0003`\u000e2\u0006\u0010\f\u001a\u00028\u0000H\u0004??\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0011\u001a\u000e\u0012\u0002\b\u00030\rj\u0006\u0012\u0002\b\u0003`\u000e2\u0006\u0010\f\u001a\u00028\u0000H\u0004??\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\f\u001a\u00028\u0000H\u0004??\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014??\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001d\u001a\u00020\u001c2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0002??\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010\u001f\u001a\u00020\u00042\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0002??\u0006\u0004\b\u001f\u0010 J)\u0010$\u001a\u00020\u001c2\u0018\u0010#\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u001c0!j\u0002`\"H\u0016??\u0006\u0004\b$\u0010%J\u0019\u0010&\u001a\u00020\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002??\u0006\u0004\b&\u0010'J\u0015\u0010(\u001a\u00020\u00062\u0006\u0010\f\u001a\u00028\u0000??\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00172\u0006\u0010\f\u001a\u00028\u0000H\u0014??\u0006\u0004\b*\u0010+J#\u0010.\u001a\u00020\u00172\u0006\u0010\f\u001a\u00028\u00002\n\u0010-\u001a\u0006\u0012\u0002\b\u00030,H\u0014??\u0006\u0004\b.\u0010/J\u0017\u00101\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u000200H\u0014??\u0006\u0004\b1\u00102JX\u00108\u001a\u00020\u001c\"\u0004\b\u0001\u001032\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00010,2\u0006\u0010\f\u001a\u00028\u00002(\u00107\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000106\u0012\u0006\u0012\u0004\u0018\u00010\u001704H\u0002??\u0001\u0000??\u0006\u0004\b8\u00109J\u001b\u0010\u0016\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00028\u0000H\u0086@??\u0001\u0000??\u0006\u0004\b\u0016\u0010:J\u001d\u0010<\u001a\b\u0012\u0002\b\u0003\u0018\u00010;2\u0006\u0010\f\u001a\u00028\u0000H\u0004??\u0006\u0004\b<\u0010=J\u001b\u0010?\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00028\u0000H\u0080@??\u0001\u0000??\u0006\u0004\b>\u0010:J\u001b\u0010@\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00028\u0000H\u0082@??\u0001\u0000??\u0006\u0004\b@\u0010:J\u0017\u0010A\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010;H\u0014??\u0006\u0004\bA\u0010BJ\u0011\u0010C\u001a\u0004\u0018\u00010\u0015H\u0004??\u0006\u0004\bC\u0010DJ\u000f\u0010F\u001a\u00020EH\u0016??\u0006\u0004\bF\u0010GJ#\u0010H\u001a\u00020\u001c*\u0006\u0012\u0002\b\u0003062\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0002??\u0006\u0004\bH\u0010IR\u0016\u0010K\u001a\u00020E8T@\u0014X\u0094\u0004??\u0006\u0006\u001a\u0004\bJ\u0010GR\u001c\u0010N\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a8D@\u0004X\u0084\u0004??\u0006\u0006\u001a\u0004\bL\u0010MR\u001c\u0010P\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a8D@\u0004X\u0084\u0004??\u0006\u0006\u001a\u0004\bO\u0010MR\u0016\u0010Q\u001a\u00020\u00068$@$X??\u0004??\u0006\u0006\u001a\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u00068$@$X??\u0004??\u0006\u0006\u001a\u0004\bS\u0010RR\u0013\u0010T\u001a\u00020\u00068F@\u0006??\u0006\u0006\u001a\u0004\bT\u0010RR\u0016\u0010U\u001a\u00020\u00068V@\u0016X\u0096\u0004??\u0006\u0006\u001a\u0004\bU\u0010RR\u0016\u0010V\u001a\u00020\u00068D@\u0004X\u0084\u0004??\u0006\u0006\u001a\u0004\bV\u0010RR%\u0010Z\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000050W8F@\u0006??\u0006\u0006\u001a\u0004\bX\u0010YR\u001c\u0010\\\u001a\u00020[8\u0004@\u0004X\u0084\u0004??\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_R\u0016\u0010a\u001a\u00020E8B@\u0002X\u0082\u0004??\u0006\u0006\u001a\u0004\b`\u0010G\u0082\u0002\u0004\n\u0002\b\u0019??\u0006g"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", ExifInterface.LONGITUDE_EAST, "<init>", "()V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeSendConflated", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", WebRTCSignalTypes.OFFER, "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendFair$kotlinx_coroutines_core", "sendFair", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFull", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendConflatedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    @NotNull
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile Object onCloseHandler = null;

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001??\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004??\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004??\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019??\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class SendBuffered<E> extends Send {
        @JvmField
        public final E element;

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        /* renamed from: getPollResult */
        public Object mo12250getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed<?> closed) {
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendBuffered@");
            outline107.append(DebugStringsKt.getHexAddress(this));
            outline107.append('(');
            outline107.append(this.element);
            outline107.append(')');
            return outline107.toString();
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return symbol;
        }
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001??\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014??\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, new SendBuffered(e));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (!(lockFreeLinkedListNode instanceof ReceiveOrClosed)) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }
    }

    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0001??\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014??\u0006\f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendConflatedDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "finishOnSuccess", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private static final class SendConflatedDesc<E> extends SendBufferedDesc<E> {
        public SendConflatedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e) {
            super(lockFreeLinkedListHead, e);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AddLastDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void finishOnSuccess(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode, @NotNull LockFreeLinkedListNode lockFreeLinkedListNode2) {
            super.finishOnSuccess(lockFreeLinkedListNode, lockFreeLinkedListNode2);
            if (!(lockFreeLinkedListNode instanceof SendBuffered)) {
                lockFreeLinkedListNode = null;
            }
            SendBuffered sendBuffered = (SendBuffered) lockFreeLinkedListNode;
            if (sendBuffered != null) {
                sendBuffered.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BX\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n\u0012(\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\f??\u0001\u0000??\u0006\u0002\u0010\u000fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0014\u0010\u0016\u001a\u00020\u00142\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R7\u0010\u000b\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\f8\u0006X\u0087\u0004??\u0001\u0000??\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004??\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004??\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\n8\u0006X\u0087\u0004??\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019??\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class SendSelect<E, R> extends Send implements DisposableHandle {
        @JvmField
        @NotNull
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        @JvmField
        @NotNull
        public final AbstractSendChannel<E> channel;
        @Nullable
        private final Object pollResult;
        @JvmField
        @NotNull
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect(@Nullable Object obj, @NotNull AbstractSendChannel<E> abstractSendChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = obj;
            this.channel = abstractSendChannel;
            this.select = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            ContinuationKt.startCoroutine(this.block, this.channel, this.select.getCompletion());
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            remove();
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        /* renamed from: getPollResult */
        public Object mo12250getPollResult() {
            return this.pollResult;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed<?> closed) {
            if (this.select.trySelect()) {
                this.select.resumeSelectWithException(closed.getSendException());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendSelect@");
            outline107.append(DebugStringsKt.getHexAddress(this));
            outline107.append('(');
            outline107.append(mo12250getPollResult());
            outline107.append(")[");
            outline107.append(this.channel);
            outline107.append(", ");
            outline107.append(this.select);
            outline107.append(JsonReaderKt.END_LIST);
            return outline107.toString();
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(prepareOp);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: AbstractChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007??\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004??\u0006\u0004\n\u0002\u0010\t??\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        @JvmField
        public final E element;

        public TryOfferDesc(E e, @NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            LockFreeLinkedListNode lockFreeLinkedListNode = prepareOp.affected;
            if (lockFreeLinkedListNode != null) {
                Symbol tryResumeReceive = ((ReceiveOrClosed) lockFreeLinkedListNode).tryResumeReceive(this.element, prepareOp);
                if (tryResumeReceive != null) {
                    Object obj = AtomicKt.RETRY_ATOMIC;
                    if (tryResumeReceive == obj) {
                        return obj;
                    }
                    if (!DebugKt.getASSERTIONS_ENABLED()) {
                        return null;
                    }
                    if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                        throw new AssertionError();
                    }
                    return null;
                }
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E>");
        }
    }

    private final int countQueueSize() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        Object next = lockFreeLinkedListHead.getNext();
        if (next != null) {
            int i = 0;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    i++;
                }
            }
            return i;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final String getQueueDebugStateString() {
        String str;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            str = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            str = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            str = "SendQueued";
        } else {
            str = "UNEXPECTED:" + nextNode;
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode == nextNode) {
            return str;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ",queueSize=");
        outline113.append(countQueueSize());
        String sb = outline113.toString();
        if (!(prevNode instanceof Closed)) {
            return sb;
        }
        return sb + ",closedForSend=" + prevNode;
    }

    private final void helpClose(Closed<?> closed) {
        Object m12294constructorimpl$default = InlineList.m12294constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if (!(prevNode instanceof Receive)) {
                prevNode = null;
            }
            Receive receive = (Receive) prevNode;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                m12294constructorimpl$default = InlineList.m12299plusimpl(m12294constructorimpl$default, receive);
            }
        }
        if (m12294constructorimpl$default != null) {
            if (!(m12294constructorimpl$default instanceof ArrayList)) {
                ((Receive) m12294constructorimpl$default).resumeReceiveClosed(closed);
            } else {
                ArrayList arrayList = (ArrayList) m12294constructorimpl$default;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
                }
            }
        }
        onClosedIdempotent(closed);
    }

    private final Throwable helpCloseAndGetSendException(Closed<?> closed) {
        helpClose(closed);
        return closed.getSendException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void helpCloseAndResumeWithSendException(@NotNull Continuation<?> continuation, Closed<?> closed) {
        helpClose(closed);
        Throwable sendException = closed.getSendException();
        Result.Companion companion = Result.Companion;
        GeneratedOutlineSupport1.outline186(sendException, continuation);
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Object obj;
        Object obj2 = this.onCloseHandler;
        if (obj2 == null || obj2 == (obj = AbstractChannelKt.HANDLER_INVOKED) || !onCloseHandler$FU.compareAndSet(this, obj2, obj)) {
            return;
        }
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj2, 1)).mo12165invoke(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (isFullImpl()) {
                SendSelect sendSelect = new SendSelect(e, this, selectInstance, function2);
                Object enqueueSend = enqueueSend(sendSelect);
                if (enqueueSend == null) {
                    selectInstance.disposeOnSelect(sendSelect);
                    return;
                } else if (!(enqueueSend instanceof Closed)) {
                    if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                        throw new IllegalStateException(("enqueueSend returned " + enqueueSend + Chars.SPACE).toString());
                    }
                } else {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) enqueueSend));
                }
            }
            Object offerSelectInternal = offerSelectInternal(e, selectInstance);
            if (offerSelectInternal == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (offerSelectInternal != AbstractChannelKt.OFFER_FAILED && offerSelectInternal != AtomicKt.RETRY_ATOMIC) {
                if (offerSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                    return;
                } else if (!(offerSelectInternal instanceof Closed)) {
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline70("offerSelectInternal returned ", offerSelectInternal).toString());
                } else {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) offerSelectInternal));
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(@Nullable Throwable th) {
        boolean z;
        Closed<?> closed = new Closed<>(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            z = true;
            if (!(prevNode instanceof Closed)) {
                if (prevNode.addNext(closed, lockFreeLinkedListNode)) {
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            LockFreeLinkedListNode prevNode2 = this.queue.getPrevNode();
            if (prevNode2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            }
            closed = (Closed) prevNode2;
        }
        helpClose(closed);
        if (z) {
            invokeOnCloseHandler(th);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E e) {
        return new SendBufferedDesc(this.queue, e);
    }

    @NotNull
    protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendConflated(E e) {
        return new SendConflatedDesc(this.queue, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final TryOfferDesc<E> describeTryOffer(E e) {
        return new TryOfferDesc<>(e, this.queue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public Object enqueueSend(@NotNull final Send send) {
        boolean z;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(send) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            @Nullable
            public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode3) {
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
            if (!(prevNode2 instanceof ReceiveOrClosed)) {
                int tryCondAddNext = prevNode2.tryCondAddNext(send, lockFreeLinkedListNode2, condAddOp);
                z = true;
                if (tryCondAddNext != 1) {
                    if (tryCondAddNext == 2) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return prevNode2;
            }
        }
        if (z) {
            return null;
        }
        return AbstractChannelKt.ENQUEUE_FAILED;
    }

    @NotNull
    protected String getBufferDebugString() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed<?> closed = (Closed) nextNode;
        if (closed != null) {
            helpClose(closed);
            return closed;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed)) {
            prevNode = null;
        }
        Closed<?> closed = (Closed) prevNode;
        if (closed != null) {
            helpClose(closed);
            return closed;
        }
        return null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return (SelectClause2<E, SendChannel<? super E>>) new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, E e, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
                AbstractSendChannel.this.registerSelectSend(selectInstance, e, function2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        if (!onCloseHandler$FU.compareAndSet(this, null, function1)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("Another handler was already registered: ", obj));
        }
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !onCloseHandler$FU.compareAndSet(this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        function1.mo12165invoke(closedForSend.closeCause);
    }

    protected abstract boolean isBufferAlwaysFull();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBufferFull();

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        return isFullImpl();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean offer(E e) {
        Object offerInternal = offerInternal(e);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(closedForSend));
            }
            return false;
        } else if (!(offerInternal instanceof Closed)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline70("offerInternal returned ", offerInternal).toString());
        } else {
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed) offerInternal));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        Symbol tryResumeReceive;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed != null) {
                tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null);
            } else {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } while (tryResumeReceive == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
        return takeFirstReceiveOrPeekClosed.mo12249getOfferResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        TryOfferDesc<E> describeTryOffer = describeTryOffer(e);
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryOffer);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> result = describeTryOffer.getResult();
        result.completeResumeReceive(e);
        return result.mo12249getOfferResult();
    }

    protected void onClosedIdempotent(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public final Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(e, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return sendSuspend == coroutine_suspended ? sendSuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final ReceiveOrClosed<?> sendBuffered(E e) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        SendBuffered sendBuffered = new SendBuffered(e);
        do {
            prevNode = lockFreeLinkedListNode.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(sendBuffered, lockFreeLinkedListNode));
        return null;
    }

    @Nullable
    public final Object sendFair$kotlinx_coroutines_core(E e, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            Object yield = YieldKt.yield(continuation);
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return yield == coroutine_suspended2 ? yield : Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(e, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return sendSuspend == coroutine_suspended ? sendSuspend : Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object sendSuspend(E e, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        while (true) {
            if (isFullImpl()) {
                SendElement sendElement = new SendElement(e, orCreateCancellableContinuation);
                Object enqueueSend = enqueueSend(sendElement);
                if (enqueueSend == null) {
                    CancellableContinuationKt.removeOnCancellation(orCreateCancellableContinuation, sendElement);
                    break;
                } else if (enqueueSend instanceof Closed) {
                    helpCloseAndResumeWithSendException(orCreateCancellableContinuation, (Closed) enqueueSend);
                    break;
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline70("enqueueSend returned ", enqueueSend).toString());
                }
            }
            Object offerInternal = offerInternal(e);
            if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                orCreateCancellableContinuation.resumeWith(Result.m10378constructorimpl(unit));
                break;
            } else if (offerInternal != AbstractChannelKt.OFFER_FAILED) {
                if (offerInternal instanceof Closed) {
                    helpCloseAndResumeWithSendException(orCreateCancellableContinuation, (Closed) offerInternal);
                } else {
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline70("offerInternal returned ", offerInternal).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        ReceiveOrClosed<E> receiveOrClosed;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            if (next != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                receiveOrClosed = null;
                if (lockFreeLinkedListNode == lockFreeLinkedListHead || !(lockFreeLinkedListNode instanceof ReceiveOrClosed)) {
                    break;
                } else if ((!(((ReceiveOrClosed) lockFreeLinkedListNode) instanceof Closed) || lockFreeLinkedListNode.isRemoved()) && (removeOrNext = lockFreeLinkedListNode.removeOrNext()) != null) {
                    removeOrNext.helpRemovePrev();
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        receiveOrClosed = lockFreeLinkedListNode;
        return receiveOrClosed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Send send;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            if (next != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                send = null;
                if (lockFreeLinkedListNode == lockFreeLinkedListHead || !(lockFreeLinkedListNode instanceof Send)) {
                    break;
                } else if ((!(((Send) lockFreeLinkedListNode) instanceof Closed) || lockFreeLinkedListNode.isRemoved()) && (removeOrNext = lockFreeLinkedListNode.removeOrNext()) != null) {
                    removeOrNext.helpRemovePrev();
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        send = lockFreeLinkedListNode;
        return send;
    }

    @NotNull
    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + JsonReaderKt.BEGIN_OBJ + getQueueDebugStateString() + JsonReaderKt.END_OBJ + getBufferDebugString();
    }
}
