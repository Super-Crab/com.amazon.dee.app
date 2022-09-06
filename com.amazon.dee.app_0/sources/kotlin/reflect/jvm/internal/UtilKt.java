package kotlin.reflect.jvm.internal;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.reflect.KCallable;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.text.StringsKt__StringsJVMKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: util.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ª\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001an\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\u000b*\u00020\f\"\b\b\u0001\u0010\n*\u00020\u00062\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000f\u001a\u0002H\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u001d\u0010\u0016\u001a\u0019\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\n0\u0017¢\u0006\u0002\b\u0019H\u0000¢\u0006\u0002\u0010\u001a\u001a.\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002\u001a(\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010!\u001a\u00020\"H\u0002\u001a\"\u0010%\u001a\u0002H&\"\u0004\b\u0000\u0010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H&0(H\u0080\b¢\u0006\u0002\u0010)\u001a\u0014\u0010*\u001a\b\u0012\u0002\b\u0003\u0018\u00010+*\u0004\u0018\u00010,H\u0000\u001a\u0010\u0010-\u001a\u0004\u0018\u00010.*\u0004\u0018\u00010,H\u0000\u001a\u0014\u0010/\u001a\b\u0012\u0002\b\u0003\u0018\u000100*\u0004\u0018\u00010,H\u0000\u001a\u0012\u00101\u001a\b\u0012\u0004\u0012\u00020302*\u000204H\u0000\u001a\u000e\u00105\u001a\u0004\u0018\u000103*\u000206H\u0002\u001a\u0012\u00107\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u000208H\u0000\u001a\u000e\u00109\u001a\u0004\u0018\u00010:*\u00020;H\u0000\u001a\u001a\u0010<\u001a\u0004\u0018\u00010,*\u0006\u0012\u0002\b\u00030=2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006>"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "", "className", "arrayDimensions", "", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/Visibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UtilKt {
    @NotNull
    private static final FqName JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");

    @Nullable
    public static final KCallableImpl<?> asKCallableImpl(@Nullable Object obj) {
        KCallableImpl<?> kCallableImpl = (KCallableImpl) (!(obj instanceof KCallableImpl) ? null : obj);
        if (kCallableImpl == null) {
            kCallableImpl = asKFunctionImpl(obj);
        }
        return kCallableImpl != null ? kCallableImpl : asKPropertyImpl(obj);
    }

    @Nullable
    public static final KFunctionImpl asKFunctionImpl(@Nullable Object obj) {
        KFunctionImpl kFunctionImpl = (KFunctionImpl) (!(obj instanceof KFunctionImpl) ? null : obj);
        if (kFunctionImpl != null) {
            return kFunctionImpl;
        }
        if (!(obj instanceof FunctionReference)) {
            obj = null;
        }
        FunctionReference functionReference = (FunctionReference) obj;
        KCallable compute = functionReference != null ? functionReference.compute() : null;
        if (!(compute instanceof KFunctionImpl)) {
            compute = null;
        }
        return (KFunctionImpl) compute;
    }

    @Nullable
    public static final KPropertyImpl<?> asKPropertyImpl(@Nullable Object obj) {
        KPropertyImpl<?> kPropertyImpl = (KPropertyImpl) (!(obj instanceof KPropertyImpl) ? null : obj);
        if (kPropertyImpl != null) {
            return kPropertyImpl;
        }
        if (!(obj instanceof PropertyReference)) {
            obj = null;
        }
        PropertyReference propertyReference = (PropertyReference) obj;
        KCallable compute = propertyReference != null ? propertyReference.compute() : null;
        if (!(compute instanceof KPropertyImpl)) {
            compute = null;
        }
        return (KPropertyImpl) compute;
    }

    @NotNull
    public static final List<Annotation> computeAnnotations(@NotNull Annotated computeAnnotations) {
        Intrinsics.checkParameterIsNotNull(computeAnnotations, "$this$computeAnnotations");
        Annotations mo12070getAnnotations = computeAnnotations.mo12070getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (AnnotationDescriptor annotationDescriptor : mo12070getAnnotations) {
            SourceElement mo11658getSource = annotationDescriptor.mo11658getSource();
            Annotation annotation = null;
            if (mo11658getSource instanceof ReflectAnnotationSource) {
                annotation = ((ReflectAnnotationSource) mo11658getSource).getAnnotation();
            } else if (mo11658getSource instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
                ReflectJavaElement mo11614getJavaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) mo11658getSource).mo11614getJavaElement();
                if (!(mo11614getJavaElement instanceof ReflectJavaAnnotation)) {
                    mo11614getJavaElement = null;
                }
                ReflectJavaAnnotation reflectJavaAnnotation = (ReflectJavaAnnotation) mo11614getJavaElement;
                if (reflectJavaAnnotation != null) {
                    annotation = reflectJavaAnnotation.getAnnotation();
                }
            } else {
                annotation = toAnnotationInstance(annotationDescriptor);
            }
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        return arrayList;
    }

    @Nullable
    public static final <M extends MessageLite, D extends CallableDescriptor> D deserializeToDescriptor(@NotNull Class<?> moduleAnchor, @NotNull M proto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull BinaryVersion metadataVersion, @NotNull Function2<? super MemberDeserializer, ? super M, ? extends D> createDescriptor) {
        List<ProtoBuf.TypeParameter> typeParameterList;
        Intrinsics.checkParameterIsNotNull(moduleAnchor, "moduleAnchor");
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(createDescriptor, "createDescriptor");
        RuntimeModuleData orCreateModule = ModuleByClassLoaderKt.getOrCreateModule(moduleAnchor);
        if (proto instanceof ProtoBuf.Function) {
            typeParameterList = ((ProtoBuf.Function) proto).getTypeParameterList();
        } else if (!(proto instanceof ProtoBuf.Property)) {
            throw new IllegalStateException(("Unsupported message: " + proto).toString());
        } else {
            typeParameterList = ((ProtoBuf.Property) proto).getTypeParameterList();
        }
        List<ProtoBuf.TypeParameter> typeParameters = typeParameterList;
        DeserializationComponents deserialization = orCreateModule.getDeserialization();
        ModuleDescriptor module = orCreateModule.getModule();
        VersionRequirementTable empty = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "typeParameters");
        return createDescriptor.mo12248invoke(new MemberDeserializer(new DeserializationContext(deserialization, nameResolver, module, typeTable, empty, metadataVersion, null, null, typeParameters)), proto);
    }

    @Nullable
    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(@NotNull CallableDescriptor instanceReceiverParameter) {
        Intrinsics.checkParameterIsNotNull(instanceReceiverParameter, "$this$instanceReceiverParameter");
        if (instanceReceiverParameter.getDispatchReceiverParameter() != null) {
            DeclarationDescriptor mo11607getContainingDeclaration = instanceReceiverParameter.mo11607getContainingDeclaration();
            if (mo11607getContainingDeclaration == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            return ((ClassDescriptor) mo11607getContainingDeclaration).getThisAsReceiverParameter();
        }
        return null;
    }

    @NotNull
    public static final FqName getJVM_STATIC() {
        return JVM_STATIC;
    }

    private static final Class<?> loadClass(ClassLoader classLoader, ClassId classId, int i) {
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = classId.asSingleFqName().toUnsafe();
        Intrinsics.checkExpressionValueIsNotNull(unsafe, "kotlinClassId.asSingleFqName().toUnsafe()");
        ClassId mapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (mapKotlinToJava != null) {
            classId = mapKotlinToJava;
        }
        String asString = classId.getPackageFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "javaClassId.packageFqName.asString()");
        String asString2 = classId.getRelativeClassName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "javaClassId.relativeClassName.asString()");
        return loadClass(classLoader, asString, asString2, i);
    }

    static /* synthetic */ Class loadClass$default(ClassLoader classLoader, ClassId classId, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return loadClass(classLoader, classId, i);
    }

    private static final Annotation toAnnotationInstance(@NotNull AnnotationDescriptor annotationDescriptor) {
        Map map;
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Class<?> javaClass = annotationClass != null ? toJavaClass(annotationClass) : null;
        if (!(javaClass instanceof Class)) {
            javaClass = null;
        }
        if (javaClass != null) {
            Set<Map.Entry<Name, ConstantValue<?>>> entrySet = annotationDescriptor.getAllValueArguments().entrySet();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it2 = entrySet.iterator();
            while (it2.hasNext()) {
                Map.Entry entry = (Map.Entry) it2.next();
                Name name = (Name) entry.getKey();
                ClassLoader classLoader = javaClass.getClassLoader();
                Intrinsics.checkExpressionValueIsNotNull(classLoader, "annotationClass.classLoader");
                Object runtimeValue = toRuntimeValue((ConstantValue) entry.getValue(), classLoader);
                Pair pair = runtimeValue != null ? TuplesKt.to(name.asString(), runtimeValue) : null;
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            map = MapsKt__MapsKt.toMap(arrayList);
            return (Annotation) AnnotationConstructorCallerKt.createAnnotationInstance$default(javaClass, map, null, 4, null);
        }
        return null;
    }

    @Nullable
    public static final Class<?> toJavaClass(@NotNull ClassDescriptor toJavaClass) {
        Intrinsics.checkParameterIsNotNull(toJavaClass, "$this$toJavaClass");
        SourceElement source = toJavaClass.getSource();
        Intrinsics.checkExpressionValueIsNotNull(source, "source");
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            if (binaryClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.components.ReflectKotlinClass");
            }
            return ((ReflectKotlinClass) binaryClass).getKlass();
        } else if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
            ReflectJavaElement mo11614getJavaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).mo11614getJavaElement();
            if (mo11614getJavaElement == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            }
            return ((ReflectJavaClass) mo11614getJavaElement).mo11620getElement();
        } else {
            ClassId classId = DescriptorUtilsKt.getClassId(toJavaClass);
            if (classId == null) {
                return null;
            }
            return loadClass(ReflectClassUtilKt.getSafeClassLoader(toJavaClass.getClass()), classId, 0);
        }
    }

    @Nullable
    public static final KVisibility toKVisibility(@NotNull Visibility toKVisibility) {
        Intrinsics.checkParameterIsNotNull(toKVisibility, "$this$toKVisibility");
        if (Intrinsics.areEqual(toKVisibility, Visibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if (Intrinsics.areEqual(toKVisibility, Visibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if (Intrinsics.areEqual(toKVisibility, Visibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        if (Intrinsics.areEqual(toKVisibility, Visibilities.PRIVATE) || Intrinsics.areEqual(toKVisibility, Visibilities.PRIVATE_TO_THIS)) {
            return KVisibility.PRIVATE;
        }
        return null;
    }

    private static final Object toRuntimeValue(@NotNull ConstantValue<?> constantValue, ClassLoader classLoader) {
        int collectionSizeOrDefault;
        if (constantValue instanceof AnnotationValue) {
            return toAnnotationInstance(((AnnotationValue) constantValue).getValue());
        }
        if (constantValue instanceof ArrayValue) {
            List<? extends ConstantValue<?>> value = ((ArrayValue) constantValue).getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(value, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator<T> it2 = value.iterator();
            while (it2.hasNext()) {
                arrayList.add(toRuntimeValue((ConstantValue) it2.next(), classLoader));
            }
            Object[] array = arrayList.toArray(new Object[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return array;
        } else if (constantValue instanceof EnumValue) {
            Pair<? extends ClassId, ? extends Name> value2 = ((EnumValue) constantValue).getValue();
            Name component2 = value2.component2();
            Class loadClass$default = loadClass$default(classLoader, value2.component1(), 0, 4, null);
            if (loadClass$default == null) {
                return null;
            }
            return Util.getEnumConstantByName(loadClass$default, component2.asString());
        } else if (constantValue instanceof KClassValue) {
            KClassValue.Value value3 = ((KClassValue) constantValue).getValue();
            if (value3 instanceof KClassValue.Value.NormalClass) {
                KClassValue.Value.NormalClass normalClass = (KClassValue.Value.NormalClass) value3;
                return loadClass(classLoader, normalClass.getClassId(), normalClass.getArrayDimensions());
            } else if (!(value3 instanceof KClassValue.Value.LocalClass)) {
                throw new NoWhenBranchMatchedException();
            } else {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((KClassValue.Value.LocalClass) value3).getType().mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
                if (classDescriptor == null) {
                    return null;
                }
                return toJavaClass(classDescriptor);
            }
        } else if ((constantValue instanceof ErrorValue) || (constantValue instanceof NullValue)) {
            return null;
        } else {
            return constantValue.getValue();
        }
    }

    private static final Class<?> loadClass(ClassLoader classLoader, String str, String str2, int i) {
        String replace$default;
        String repeat;
        if (Intrinsics.areEqual(str, "kotlin")) {
            switch (str2.hashCode()) {
                case -901856463:
                    if (str2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (str2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (str2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (str2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (str2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (str2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (str2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (str2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (str2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, '.');
        replace$default = StringsKt__StringsJVMKt.replace$default(str2, '.', '$', false, 4, (Object) null);
        outline108.append(replace$default);
        String sb = outline108.toString();
        if (i > 0) {
            StringBuilder sb2 = new StringBuilder();
            repeat = StringsKt__StringsJVMKt.repeat("[", i);
            sb2.append(repeat);
            sb2.append(Matrix.MATRIX_TYPE_RANDOM_LT);
            sb2.append(sb);
            sb2.append(';');
            sb = sb2.toString();
        }
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader, sb);
    }
}
