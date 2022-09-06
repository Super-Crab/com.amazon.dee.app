package kotlinx.io.utils;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
/* compiled from: Atomic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u000b0\nH\u0080\b¨\u0006\f"}, d2 = {"getIOIntProperty", "", "name", "", "default", "longUpdater", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "Owner", "", "p", "Lkotlin/reflect/KProperty1;", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicKt {
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
        r2 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int getIOIntProperty(@org.jetbrains.annotations.NotNull java.lang.String r2, int r3) {
        /*
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L1b
            r0.<init>()     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r1 = "kotlinx.io."
            r0.append(r1)     // Catch: java.lang.SecurityException -> L1b
            r0.append(r2)     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r2 = r0.toString()     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.SecurityException -> L1b
            goto L1c
        L1b:
            r2 = 0
        L1c:
            if (r2 == 0) goto L28
            java.lang.Integer r2 = kotlin.text.StringsKt.toIntOrNull(r2)
            if (r2 == 0) goto L28
            int r3 = r2.intValue()
        L28:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.utils.AtomicKt.getIOIntProperty(java.lang.String, int):int");
    }

    private static final <Owner> AtomicLongFieldUpdater<Owner> longUpdater(KProperty1<Owner, Long> kProperty1) {
        Intrinsics.reifiedOperationMarker(4, "Owner");
        AtomicLongFieldUpdater<Owner> newUpdater = AtomicLongFieldUpdater.newUpdater(Object.class, kProperty1.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicLongFieldUpdater.n…wner::class.java, p.name)");
        return newUpdater;
    }
}
