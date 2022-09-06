package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Reflection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u001aU\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u001e\u0012\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00030\u00040\u00032\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00072\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0007H\u0082\u0010\u001a\u001a\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\t*\u0006\u0012\u0002\b\u00030\u0005H\u0007\u001a\u001d\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0003H\u0002¢\u0006\u0002\u0010\f\u001a\u001a\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003*\u0006\u0012\u0002\b\u00030\u0005H\u0002¨\u0006\u000e"}, d2 = {"findAllSupertypes", "", "nodes", "", "Lkotlin/Pair;", "Ljava/lang/Class;", RouteParameter.PATH, "", "visited", "", "removeLast", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/util/List;)Ljava/lang/Object;", "supertypes", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ReflectionKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "To be removed.")
    @NotNull
    public static final List<Class<?>> findAllSupertypes(@NotNull Class<?> receiver$0) {
        List mutableListOf;
        Set mutableSetOf;
        List<Class<?>> list;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new Pair(receiver$0, supertypes(receiver$0)));
        mutableSetOf = SetsKt__SetsKt.mutableSetOf(receiver$0);
        findAllSupertypes(mutableListOf, mutableSetOf, linkedHashSet);
        list = CollectionsKt___CollectionsKt.toList(linkedHashSet);
        return list;
    }

    private static final <T> T removeLast(@NotNull List<T> list) {
        int lastIndex;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        return list.remove(lastIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
        r4 = kotlin.collections.ArraysKt___ArraysKt.toMutableList(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.List<java.lang.Class<?>> supertypes(@org.jetbrains.annotations.NotNull java.lang.Class<?> r4) {
        /*
            java.lang.Class r0 = r4.getSuperclass()
            if (r0 != 0) goto L19
            java.lang.Class[] r4 = r4.getInterfaces()
            if (r4 == 0) goto L13
            java.util.List r4 = kotlin.collections.ArraysKt.toMutableList(r4)
            if (r4 == 0) goto L13
            goto L62
        L13:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            goto L62
        L19:
            java.lang.Class[] r0 = r4.getInterfaces()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L51
            java.lang.Class[] r0 = r4.getInterfaces()
            java.lang.String r3 = "interfaces"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            int r0 = r0.length
            if (r0 != 0) goto L2f
            r0 = r2
            goto L30
        L2f:
            r0 = r1
        L30:
            if (r0 == 0) goto L33
            goto L51
        L33:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.Class[] r1 = r4.getInterfaces()
            int r1 = r1.length
            int r1 = r1 + r2
            r0.<init>(r1)
            java.lang.Class[] r1 = r4.getInterfaces()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            kotlin.collections.ArraysKt.toCollection(r1, r0)
            java.lang.Class r4 = r4.getSuperclass()
            r0.add(r4)
            r4 = r0
            goto L62
        L51:
            java.lang.Class[] r0 = new java.lang.Class[r2]
            java.lang.Class r4 = r4.getSuperclass()
            java.lang.String r2 = "superclass"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r2)
            r0[r1] = r4
            java.util.List r4 = kotlin.collections.CollectionsKt.mutableListOf(r0)
        L62:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ReflectionKt.supertypes(java.lang.Class):java.util.List");
    }

    private static final void findAllSupertypes(List<Pair<Class<?>, List<Class<?>>>> list, Set<Class<?>> set, Set<Class<?>> set2) {
        int lastIndex;
        while (!list.isEmpty()) {
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            Pair<Class<?>, List<Class<?>>> pair = list.get(lastIndex);
            Class<?> component1 = pair.component1();
            List<Class<?>> component2 = pair.component2();
            if (component2.isEmpty()) {
                set2.add(component1);
                set.remove(component1);
                removeLast(list);
            } else {
                Class<?> cls = (Class) removeLast(component2);
                if (set.add(cls)) {
                    list.add(new Pair<>(cls, supertypes(cls)));
                }
            }
        }
    }
}
