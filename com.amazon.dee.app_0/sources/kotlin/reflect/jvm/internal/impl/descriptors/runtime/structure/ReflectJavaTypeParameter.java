package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaTypeParameter.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaTypeParameter extends ReflectJavaElement implements ReflectJavaAnnotationOwner, JavaTypeParameter {
    @NotNull
    private final TypeVariable<?> typeVariable;

    public ReflectJavaTypeParameter(@NotNull TypeVariable<?> typeVariable) {
        Intrinsics.checkParameterIsNotNull(typeVariable, "typeVariable");
        this.typeVariable = typeVariable;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ReflectJavaTypeParameter) && Intrinsics.areEqual(this.typeVariable, ((ReflectJavaTypeParameter) obj).typeVariable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    /* renamed from: findAnnotation */
    public ReflectJavaAnnotation mo11638findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return ReflectJavaAnnotationOwner.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    @Nullable
    /* renamed from: getElement */
    public AnnotatedElement mo11620getElement() {
        TypeVariable<?> typeVariable = this.typeVariable;
        if (!(typeVariable instanceof AnnotatedElement)) {
            typeVariable = null;
        }
        return (AnnotatedElement) typeVariable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    @NotNull
    public Name getName() {
        Name identifier = Name.identifier(this.typeVariable.getName());
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(typeVariable.name)");
        return identifier;
    }

    public int hashCode() {
        return this.typeVariable.hashCode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return ReflectJavaAnnotationOwner.DefaultImpls.isDeprecatedInJavaDoc(this);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline146(ReflectJavaTypeParameter.class, sb, RealTimeTextConstants.COLON_SPACE);
        sb.append(this.typeVariable);
        return sb.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    /* renamed from: getAnnotations  reason: collision with other method in class */
    public List<ReflectJavaAnnotation> mo11639getAnnotations() {
        return ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter
    @NotNull
    /* renamed from: getUpperBounds  reason: collision with other method in class */
    public List<ReflectJavaClassifierType> mo11637getUpperBounds() {
        List<ReflectJavaClassifierType> emptyList;
        Type[] bounds = this.typeVariable.getBounds();
        Intrinsics.checkExpressionValueIsNotNull(bounds, "typeVariable.bounds");
        ArrayList arrayList = new ArrayList(bounds.length);
        for (Type type : bounds) {
            arrayList.add(new ReflectJavaClassifierType(type));
        }
        ReflectJavaClassifierType reflectJavaClassifierType = (ReflectJavaClassifierType) CollectionsKt.singleOrNull((List<? extends Object>) arrayList);
        if (Intrinsics.areEqual(reflectJavaClassifierType != null ? reflectJavaClassifierType.mo11642getReflectType() : null, Object.class)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return arrayList;
    }
}
