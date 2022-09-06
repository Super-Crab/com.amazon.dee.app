package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InlineList.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0080@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0016\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002HÖ\u0003J$\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0012\u001a\u00028\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", ExifInterface.LONGITUDE_EAST, "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", "", "other", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "hashCode", "", "plus", "element", "plus-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InlineList<E> {
    private final Object holder;

    private /* synthetic */ InlineList(@Nullable Object obj) {
        this.holder = obj;
    }

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ InlineList m12292boximpl(@Nullable Object obj) {
        return new InlineList(obj);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m12293constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static /* synthetic */ Object m12294constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return m12293constructorimpl(obj);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m12295equalsimpl(Object obj, @Nullable Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.areEqual(obj, ((InlineList) obj2).m12301unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m12296equalsimpl0(@Nullable Object obj, @Nullable Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* renamed from: forEachReversed-impl  reason: not valid java name */
    public static final void m12297forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            function1.mo12165invoke(obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            function1.mo12165invoke((Object) arrayList.get(size));
        }
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m12298hashCodeimpl(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    @NotNull
    /* renamed from: plus-impl  reason: not valid java name */
    public static final Object m12299plusimpl(Object obj, E e) {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(e instanceof List))) {
            if (obj == null) {
                return m12293constructorimpl(e);
            }
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(e);
                return m12293constructorimpl(obj);
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(e);
            return m12293constructorimpl(arrayList);
        }
        throw new AssertionError();
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m12300toStringimpl(Object obj) {
        return "InlineList(holder=" + obj + ")";
    }

    public boolean equals(Object obj) {
        return m12295equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m12298hashCodeimpl(this.holder);
    }

    public String toString() {
        return m12300toStringimpl(this.holder);
    }

    @Nullable
    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m12301unboximpl() {
        return this.holder;
    }
}
