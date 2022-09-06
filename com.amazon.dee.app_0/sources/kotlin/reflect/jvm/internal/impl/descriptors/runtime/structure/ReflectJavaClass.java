package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaClass.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaClass extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaClass {
    private final Class<?> klass;

    public ReflectJavaClass(@NotNull Class<?> klass) {
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        this.klass = klass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isEnumValuesOrValueOf(Method method) {
        String name = method.getName();
        if (name != null) {
            int hashCode = name.hashCode();
            if (hashCode != -823812830) {
                if (hashCode == 231605032 && name.equals("valueOf")) {
                    return Arrays.equals(method.getParameterTypes(), new Class[]{String.class});
                }
            } else if (name.equals("values")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Intrinsics.checkExpressionValueIsNotNull(parameterTypes, "method.parameterTypes");
                if (parameterTypes.length == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ReflectJavaClass) && Intrinsics.areEqual(this.klass, ((ReflectJavaClass) obj).klass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    /* renamed from: findAnnotation */
    public ReflectJavaAnnotation mo11638findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return ReflectJavaAnnotationOwner.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    public FqName getFqName() {
        FqName asSingleFqName = ReflectClassUtilKt.getClassId(this.klass).asSingleFqName();
        Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "klass.classId.asSingleFqName()");
        return asSingleFqName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @Nullable
    public LightClassOriginKind getLightClassOriginKind() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return this.klass.getModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    @NotNull
    public Name getName() {
        Name identifier = Name.identifier(this.klass.getSimpleName());
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(klass.simpleName)");
        return identifier;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    public Collection<JavaClassifierType> getSupertypes() {
        List<Type> listOf;
        int collectionSizeOrDefault;
        List emptyList;
        Object obj = Object.class;
        if (Intrinsics.areEqual(this.klass, obj)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Object genericSuperclass = this.klass.getGenericSuperclass();
        if (genericSuperclass != null) {
            obj = genericSuperclass;
        }
        spreadBuilder.add(obj);
        Type[] genericInterfaces = this.klass.getGenericInterfaces();
        Intrinsics.checkExpressionValueIsNotNull(genericInterfaces, "klass.genericInterfaces");
        spreadBuilder.addSpread(genericInterfaces);
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) ((Type[]) spreadBuilder.toArray(new Type[spreadBuilder.size()])));
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Type type : listOf) {
            arrayList.add(new ReflectJavaClassifierType(type));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    @NotNull
    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable<Class<?>>[] typeParameters = this.klass.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "klass.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Class<?>> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    @NotNull
    public Visibility getVisibility() {
        return ReflectJavaModifierListOwner.DefaultImpls.getVisibility(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean hasDefaultConstructor() {
        return false;
    }

    public int hashCode() {
        return this.klass.hashCode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isAbstract() {
        return ReflectJavaModifierListOwner.DefaultImpls.isAbstract(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isAnnotationType() {
        return this.klass.isAnnotation();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return ReflectJavaAnnotationOwner.DefaultImpls.isDeprecatedInJavaDoc(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isEnum() {
        return this.klass.isEnum();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isFinal() {
        return ReflectJavaModifierListOwner.DefaultImpls.isFinal(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    public boolean isInterface() {
        return this.klass.isInterface();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isStatic() {
        return ReflectJavaModifierListOwner.DefaultImpls.isStatic(this);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline146(ReflectJavaClass.class, sb, RealTimeTextConstants.COLON_SPACE);
        sb.append(this.klass);
        return sb.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    /* renamed from: getAnnotations  reason: collision with other method in class */
    public List<ReflectJavaAnnotation> mo11639getAnnotations() {
        return ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    /* renamed from: getConstructors  reason: collision with other method in class */
    public List<ReflectJavaConstructor> mo11619getConstructors() {
        Sequence asSequence;
        Sequence filterNot;
        Sequence map;
        List<ReflectJavaConstructor> list;
        Constructor<?>[] declaredConstructors = this.klass.getDeclaredConstructors();
        Intrinsics.checkExpressionValueIsNotNull(declaredConstructors, "klass.declaredConstructors");
        asSequence = ArraysKt___ArraysKt.asSequence(declaredConstructors);
        filterNot = SequencesKt___SequencesKt.filterNot(asSequence, ReflectJavaClass$constructors$1.INSTANCE);
        map = SequencesKt___SequencesKt.map(filterNot, ReflectJavaClass$constructors$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(map);
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    @NotNull
    /* renamed from: getElement */
    public Class<?> mo11620getElement() {
        return this.klass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    /* renamed from: getFields  reason: collision with other method in class */
    public List<ReflectJavaField> mo11621getFields() {
        Sequence asSequence;
        Sequence filterNot;
        Sequence map;
        List<ReflectJavaField> list;
        Field[] declaredFields = this.klass.getDeclaredFields();
        Intrinsics.checkExpressionValueIsNotNull(declaredFields, "klass.declaredFields");
        asSequence = ArraysKt___ArraysKt.asSequence(declaredFields);
        filterNot = SequencesKt___SequencesKt.filterNot(asSequence, ReflectJavaClass$fields$1.INSTANCE);
        map = SequencesKt___SequencesKt.map(filterNot, ReflectJavaClass$fields$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(map);
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    /* renamed from: getInnerClassNames  reason: collision with other method in class */
    public List<Name> mo11622getInnerClassNames() {
        Sequence asSequence;
        Sequence filterNot;
        Sequence mapNotNull;
        List<Name> list;
        Class<?>[] declaredClasses = this.klass.getDeclaredClasses();
        Intrinsics.checkExpressionValueIsNotNull(declaredClasses, "klass.declaredClasses");
        asSequence = ArraysKt___ArraysKt.asSequence(declaredClasses);
        filterNot = SequencesKt___SequencesKt.filterNot(asSequence, ReflectJavaClass$innerClassNames$1.INSTANCE);
        mapNotNull = SequencesKt___SequencesKt.mapNotNull(filterNot, ReflectJavaClass$innerClassNames$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(mapNotNull);
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @NotNull
    /* renamed from: getMethods  reason: collision with other method in class */
    public List<ReflectJavaMethod> mo11623getMethods() {
        Sequence asSequence;
        Sequence filter;
        Sequence map;
        List<ReflectJavaMethod> list;
        Method[] declaredMethods = this.klass.getDeclaredMethods();
        Intrinsics.checkExpressionValueIsNotNull(declaredMethods, "klass.declaredMethods");
        asSequence = ArraysKt___ArraysKt.asSequence(declaredMethods);
        filter = SequencesKt___SequencesKt.filter(asSequence, new ReflectJavaClass$methods$1(this));
        map = SequencesKt___SequencesKt.map(filter, ReflectJavaClass$methods$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(map);
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass
    @Nullable
    /* renamed from: getOuterClass */
    public ReflectJavaClass mo11624getOuterClass() {
        Class<?> declaringClass = this.klass.getDeclaringClass();
        if (declaringClass != null) {
            return new ReflectJavaClass(declaringClass);
        }
        return null;
    }
}
