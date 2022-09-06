package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaClassifierType.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaClassifierType extends ReflectJavaType implements JavaClassifierType {
    @NotNull
    private final JavaClassifier classifier;
    @NotNull
    private final Type reflectType;

    public ReflectJavaClassifierType(@NotNull Type reflectType) {
        JavaClassifier reflectJavaClass;
        Intrinsics.checkParameterIsNotNull(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type mo11642getReflectType = mo11642getReflectType();
        if (mo11642getReflectType instanceof Class) {
            reflectJavaClass = new ReflectJavaClass((Class) mo11642getReflectType);
        } else if (mo11642getReflectType instanceof TypeVariable) {
            reflectJavaClass = new ReflectJavaTypeParameter((TypeVariable) mo11642getReflectType);
        } else if (!(mo11642getReflectType instanceof ParameterizedType)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not a classifier type (");
            outline107.append(mo11642getReflectType.getClass());
            outline107.append("): ");
            outline107.append(mo11642getReflectType);
            throw new IllegalStateException(outline107.toString());
        } else {
            Type rawType = ((ParameterizedType) mo11642getReflectType).getRawType();
            if (rawType == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<*>");
            }
            reflectJavaClass = new ReflectJavaClass((Class) rawType);
        }
        this.classifier = reflectJavaClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    /* renamed from: findAnnotation */
    public JavaAnnotation mo11638findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    /* renamed from: getAnnotations */
    public Collection<JavaAnnotation> mo11639getAnnotations() {
        List emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    @NotNull
    public JavaClassifier getClassifier() {
        return this.classifier;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    @NotNull
    public String getClassifierQualifiedName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Type not found: ");
        outline107.append(mo11642getReflectType());
        throw new UnsupportedOperationException(outline107.toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    @NotNull
    public String getPresentableText() {
        return mo11642getReflectType().toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    @NotNull
    /* renamed from: getReflectType */
    public Type mo11642getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    @NotNull
    public List<JavaType> getTypeArguments() {
        int collectionSizeOrDefault;
        List<Type> parameterizedTypeArguments = ReflectClassUtilKt.getParameterizedTypeArguments(mo11642getReflectType());
        ReflectJavaType.Factory factory = ReflectJavaType.Factory;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameterizedTypeArguments, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Type type : parameterizedTypeArguments) {
            arrayList.add(factory.create(type));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public boolean isRaw() {
        Type mo11642getReflectType = mo11642getReflectType();
        if (mo11642getReflectType instanceof Class) {
            TypeVariable[] typeParameters = ((Class) mo11642getReflectType).getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, "getTypeParameters()");
            return (typeParameters.length == 0) ^ true;
        }
        return false;
    }
}
