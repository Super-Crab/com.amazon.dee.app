package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotFoundClasses.kt */
/* loaded from: classes2.dex */
public final class NotFoundClasses$classes$1 extends Lambda implements Function1<NotFoundClasses.ClassRequest, NotFoundClasses.MockClassDescriptor> {
    final /* synthetic */ NotFoundClasses this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotFoundClasses$classes$1(NotFoundClasses notFoundClasses) {
        super(1);
        this.this$0 = notFoundClasses;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0029, code lost:
        if (r1 != null) goto L7;
     */
    @Override // kotlin.jvm.functions.Function1
    @org.jetbrains.annotations.NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor mo12165invoke(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.ClassRequest r9) {
        /*
            r8 = this;
            java.lang.String r0 = "<name for destructuring parameter 0>"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r9.component1()
            java.util.List r9 = r9.component2()
            boolean r1 = r0.isLocal()
            if (r1 != 0) goto L6b
            kotlin.reflect.jvm.internal.impl.name.ClassId r1 = r0.getOuterClassId()
            if (r1 == 0) goto L2c
            kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses r2 = r8.this$0
            java.lang.String r3 = "outerClassId"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            r3 = 1
            java.util.List r3 = kotlin.collections.CollectionsKt.drop(r9, r3)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = r2.getClass(r1, r3)
            if (r1 == 0) goto L2c
            goto L41
        L2c:
            kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses r1 = r8.this$0
            kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull r1 = kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.access$getPackageFragments$p(r1)
            kotlin.reflect.jvm.internal.impl.name.FqName r2 = r0.getPackageFqName()
            java.lang.String r3 = "classId.packageFqName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.Object r1 = r1.mo12165invoke(r2)
            kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassOrPackageFragmentDescriptor) r1
        L41:
            r4 = r1
            boolean r6 = r0.isNestedClass()
            kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$MockClassDescriptor r1 = new kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$MockClassDescriptor
            kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses r2 = r8.this$0
            kotlin.reflect.jvm.internal.impl.storage.StorageManager r3 = kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.access$getStorageManager$p(r2)
            kotlin.reflect.jvm.internal.impl.name.Name r5 = r0.getShortClassName()
            java.lang.String r0 = "classId.shortClassName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            java.lang.Object r9 = kotlin.collections.CollectionsKt.firstOrNull(r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r9 == 0) goto L64
            int r9 = r9.intValue()
            goto L65
        L64:
            r9 = 0
        L65:
            r7 = r9
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            return r1
        L6b:
            java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unresolved local class: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$classes$1.mo12165invoke(kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$ClassRequest):kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses$MockClassDescriptor");
    }
}
