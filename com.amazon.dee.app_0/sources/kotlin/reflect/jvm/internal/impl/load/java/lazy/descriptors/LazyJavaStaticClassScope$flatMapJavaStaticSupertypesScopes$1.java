package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LazyJavaStaticClassScope.kt */
/* loaded from: classes3.dex */
final class LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1<N> implements DFS.Neighbors<N> {
    public static final LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1 INSTANCE = new LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LazyJavaStaticClassScope.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<KotlinType, ClassDescriptor> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final ClassDescriptor mo12165invoke(KotlinType kotlinType) {
            ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
            if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                mo12085getDeclarationDescriptor = null;
            }
            return (ClassDescriptor) mo12085getDeclarationDescriptor;
        }
    }

    LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
    @NotNull
    public final Iterable<ClassDescriptor> getNeighbors(ClassDescriptor it2) {
        Sequence asSequence;
        Sequence mapNotNull;
        Iterable<ClassDescriptor> asIterable;
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        TypeConstructor mo11528getTypeConstructor = it2.mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "it.typeConstructor");
        Collection<KotlinType> mo12135getSupertypes = mo11528getTypeConstructor.mo12135getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "it.typeConstructor.supertypes");
        asSequence = CollectionsKt___CollectionsKt.asSequence(mo12135getSupertypes);
        mapNotNull = SequencesKt___SequencesKt.mapNotNull(asSequence, AnonymousClass1.INSTANCE);
        asIterable = SequencesKt___SequencesKt.asIterable(mapNotNull);
        return asIterable;
    }
}
