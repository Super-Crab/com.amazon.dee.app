package io.ktor.util.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.devicesetup.common.v1.Event;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LockFreeLinkedList.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0015\u001a\u00060\u0016j\u0002`\u0017*\u00020\u0001H\u0001\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u0016\u0010\t\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003\"\u001c\u0010\f\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u0005\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0011\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003\"\u0016\u0010\u0013\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003*\n\u0010\u0018\"\u00020\u00192\u00020\u0019*\u001c\u0010\u001a\u001a\u0004\b\u0000\u0010\u001b\"\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\b\u0012\u0004\u0012\u0002H\u001b0\u001c*\f\b\u0002\u0010\u001d\"\u00020\u00162\u00020\u0016*\u001c\u0010\u001e\u001a\u0004\b\u0000\u0010\u001b\"\b\u0012\u0004\u0012\u0002H\u001b0\u001f2\b\u0012\u0004\u0012\u0002H\u001b0\u001f¨\u0006 "}, d2 = {"ALREADY_REMOVED", "", "ALREADY_REMOVED$annotations", "()V", "getALREADY_REMOVED", "()Ljava/lang/Object;", "CONDITION_FALSE", "CONDITION_FALSE$annotations", "getCONDITION_FALSE", Event.FAILURE, "", "FAILURE$annotations", "LIST_EMPTY", "LIST_EMPTY$annotations", "getLIST_EMPTY", "NO_DECISION", "REMOVE_PREPARED", "SUCCESS", "SUCCESS$annotations", "UNDECIDED", "UNDECIDED$annotations", "unwrap", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "AbstractAtomicDesc", "Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "AddLastDesc", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/internal/LockFreeLinkedListNode$AddLastDesc;", "Node", "RemoveFirstDesc", "Lio/ktor/util/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LockFreeLinkedListKt {
    public static final int FAILURE = 2;
    public static final int SUCCESS = 1;
    public static final int UNDECIDED = 0;
    @NotNull
    private static final Object CONDITION_FALSE = new Symbol("CONDITION_FALSE");
    @NotNull
    private static final Object ALREADY_REMOVED = new Symbol("ALREADY_REMOVED");
    @NotNull
    private static final Object LIST_EMPTY = new Symbol("LIST_EMPTY");
    private static final Object REMOVE_PREPARED = new Symbol("REMOVE_PREPARED");
    private static final Object NO_DECISION = new Symbol("NO_DECISION");

    @PublishedApi
    public static /* synthetic */ void ALREADY_REMOVED$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void CONDITION_FALSE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void FAILURE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void LIST_EMPTY$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void SUCCESS$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void UNDECIDED$annotations() {
    }

    @NotNull
    public static final Object getALREADY_REMOVED() {
        return ALREADY_REMOVED;
    }

    @NotNull
    public static final Object getCONDITION_FALSE() {
        return CONDITION_FALSE;
    }

    @NotNull
    public static final Object getLIST_EMPTY() {
        return LIST_EMPTY;
    }

    @PublishedApi
    @NotNull
    public static final LockFreeLinkedListNode unwrap(@NotNull Object receiver$0) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Removed removed = (Removed) (!(receiver$0 instanceof Removed) ? null : receiver$0);
        return (removed == null || (lockFreeLinkedListNode = removed.ref) == null) ? (LockFreeLinkedListNode) receiver$0 : lockFreeLinkedListNode;
    }
}
