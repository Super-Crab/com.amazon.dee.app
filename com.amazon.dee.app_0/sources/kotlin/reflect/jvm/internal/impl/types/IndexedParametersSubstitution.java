package kotlin.reflect.jvm.internal.impl.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSubstitution.kt */
/* loaded from: classes4.dex */
public final class IndexedParametersSubstitution extends TypeSubstitution {
    private final boolean approximateCapturedTypes;
    @NotNull
    private final TypeProjection[] arguments;
    @NotNull
    private final TypeParameterDescriptor[] parameters;

    public /* synthetic */ IndexedParametersSubstitution(TypeParameterDescriptor[] typeParameterDescriptorArr, TypeProjection[] typeProjectionArr, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeParameterDescriptorArr, typeProjectionArr, (i & 4) != 0 ? false : z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.approximateCapturedTypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @Nullable
    /* renamed from: get */
    public TypeProjection mo12121get(@NotNull KotlinType key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        ClassifierDescriptor mo12085getDeclarationDescriptor = key.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
            mo12085getDeclarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) mo12085getDeclarationDescriptor;
        if (typeParameterDescriptor != null) {
            int index = typeParameterDescriptor.getIndex();
            TypeParameterDescriptor[] typeParameterDescriptorArr = this.parameters;
            if (index < typeParameterDescriptorArr.length && Intrinsics.areEqual(typeParameterDescriptorArr[index].mo11528getTypeConstructor(), typeParameterDescriptor.mo11528getTypeConstructor())) {
                return this.arguments[index];
            }
        }
        return null;
    }

    @NotNull
    public final TypeProjection[] getArguments() {
        return this.arguments;
    }

    @NotNull
    public final TypeParameterDescriptor[] getParameters() {
        return this.parameters;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return this.arguments.length == 0;
    }

    public IndexedParametersSubstitution(@NotNull TypeParameterDescriptor[] parameters, @NotNull TypeProjection[] arguments, boolean z) {
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        this.parameters = parameters;
        this.arguments = arguments;
        this.approximateCapturedTypes = z;
        boolean z2 = this.parameters.length <= this.arguments.length;
        if (!_Assertions.ENABLED || z2) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of arguments should not be less then number of parameters, but: parameters=");
        outline107.append(this.parameters.length);
        outline107.append(", args=");
        outline107.append(this.arguments.length);
        throw new AssertionError(outline107.toString());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public IndexedParametersSubstitution(@org.jetbrains.annotations.NotNull java.util.List<? extends kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor> r9, @org.jetbrains.annotations.NotNull java.util.List<? extends kotlin.reflect.jvm.internal.impl.types.TypeProjection> r10) {
        /*
            r8 = this;
            java.lang.String r0 = "parameters"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "argumentsList"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            r0 = 0
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor[] r1 = new kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor[r0]
            java.lang.Object[] r9 = r9.toArray(r1)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Array<T>"
            if (r9 == 0) goto L31
            r3 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor[] r3 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor[]) r3
            kotlin.reflect.jvm.internal.impl.types.TypeProjection[] r9 = new kotlin.reflect.jvm.internal.impl.types.TypeProjection[r0]
            java.lang.Object[] r9 = r10.toArray(r9)
            if (r9 == 0) goto L2b
            r4 = r9
            kotlin.reflect.jvm.internal.impl.types.TypeProjection[] r4 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection[]) r4
            r5 = 0
            r6 = 4
            r7 = 0
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            return
        L2b:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            r9.<init>(r1)
            throw r9
        L31:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            r9.<init>(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution.<init>(java.util.List, java.util.List):void");
    }
}
