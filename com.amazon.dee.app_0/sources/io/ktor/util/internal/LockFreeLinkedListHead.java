package io.ktor.util.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LockFreeLinkedList.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J-\u0010\b\u001a\u00020\t\"\u000e\b\u0000\u0010\n\u0018\u0001*\u00060\u0001j\u0002`\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\t0\rH\u0086\bJ\u0006\u0010\u000e\u001a\u00020\u0004J\r\u0010\u000f\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0010R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0011"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListHead;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "describeRemove", "", "forEach", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/internal/Node;", "block", "Lkotlin/Function1;", BulkOperationType.remove, "validate", "validate$ktor_utils", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    private final <T extends LockFreeLinkedListNode> void forEach(Function1<? super T, Unit> function1) {
        Object next = getNext();
        if (next != null) {
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    function1.mo12165invoke(lockFreeLinkedListNode);
                }
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
    }

    @Override // io.ktor.util.internal.LockFreeLinkedListNode
    /* renamed from: describeRemove */
    public /* bridge */ /* synthetic */ AtomicDesc mo10331describeRemove() {
        return (AtomicDesc) mo10331describeRemove();
    }

    public final boolean isEmpty() {
        return getNext() == this;
    }

    @Override // io.ktor.util.internal.LockFreeLinkedListNode
    public final boolean remove() {
        throw new UnsupportedOperationException();
    }

    public final void validate$ktor_utils() {
        Object next = getNext();
        if (next != null) {
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            LockFreeLinkedListNode lockFreeLinkedListNode2 = this;
            while (!Intrinsics.areEqual(lockFreeLinkedListNode, this)) {
                LockFreeLinkedListNode nextNode = lockFreeLinkedListNode.getNextNode();
                lockFreeLinkedListNode.validateNode$ktor_utils(lockFreeLinkedListNode2, nextNode);
                lockFreeLinkedListNode2 = lockFreeLinkedListNode;
                lockFreeLinkedListNode = nextNode;
            }
            Object next2 = getNext();
            if (next2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
            }
            validateNode$ktor_utils(lockFreeLinkedListNode2, (LockFreeLinkedListNode) next2);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.internal.Node /* = io.ktor.util.internal.LockFreeLinkedListNode */");
    }

    @Override // io.ktor.util.internal.LockFreeLinkedListNode
    @NotNull
    /* renamed from: describeRemove  reason: collision with other method in class */
    public final Void mo10331describeRemove() {
        throw new UnsupportedOperationException();
    }
}
