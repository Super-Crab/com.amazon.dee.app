package kotlin.reflect.jvm.internal.impl.types;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IntersectionTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class IntersectionTypeConstructor implements TypeConstructor {
    private final int hashCode;
    private final LinkedHashSet<KotlinType> intersectedTypes;

    public IntersectionTypeConstructor(@NotNull Collection<? extends KotlinType> typesToIntersect) {
        Intrinsics.checkParameterIsNotNull(typesToIntersect, "typesToIntersect");
        boolean z = !typesToIntersect.isEmpty();
        if (!_Assertions.ENABLED || z) {
            this.intersectedTypes = new LinkedHashSet<>(typesToIntersect);
            this.hashCode = this.intersectedTypes.hashCode();
            return;
        }
        throw new AssertionError("Attempt to create an empty intersection");
    }

    private final String makeDebugNameForIntersectionType(Iterable<? extends KotlinType> iterable) {
        List sortedWith;
        String joinToString$default;
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(iterable, new Comparator<T>() { // from class: kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor$makeDebugNameForIntersectionType$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(((KotlinType) t).toString(), ((KotlinType) t2).toString());
                return compareValues;
            }
        });
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(sortedWith, " & ", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE, 0, null, null, 56, null);
        return joinToString$default;
    }

    @NotNull
    public final MemberScope createScopeForKotlinType() {
        TypeIntersectionScope.Companion companion = TypeIntersectionScope.Companion;
        return companion.create("member scope for intersection type " + this, this.intersectedTypes);
    }

    @NotNull
    public final SimpleType createType() {
        List emptyList;
        Annotations empty = Annotations.Companion.getEMPTY();
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(empty, this, emptyList, false, createScopeForKotlinType(), new IntersectionTypeConstructor$createType$1(this));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IntersectionTypeConstructor) {
            return Intrinsics.areEqual(this.intersectedTypes, ((IntersectionTypeConstructor) obj).intersectedTypes);
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = this.intersectedTypes.iterator().next().mo12131getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "intersectedTypes.iterato…xt().constructor.builtIns");
        return builtIns;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @Nullable
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo12085getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        List<TypeParameterDescriptor> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes */
    public Collection<KotlinType> mo12135getSupertypes() {
        return this.intersectedTypes;
    }

    public int hashCode() {
        return this.hashCode;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @NotNull
    public String toString() {
        return makeDebugNameForIntersectionType(this.intersectedTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: refine */
    public IntersectionTypeConstructor mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        LinkedHashSet<KotlinType> linkedHashSet = this.intersectedTypes;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedHashSet, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (KotlinType kotlinType : linkedHashSet) {
            arrayList.add(kotlinType.mo12133refine(kotlinTypeRefiner));
        }
        return new IntersectionTypeConstructor(arrayList);
    }
}
