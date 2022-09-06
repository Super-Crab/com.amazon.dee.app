package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationDeserializer.kt */
/* loaded from: classes4.dex */
public final class AnnotationDeserializer {
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.CHAR.ordinal()] = 2;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.SHORT.ordinal()] = 3;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.INT.ordinal()] = 4;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.LONG.ordinal()] = 5;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 10;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.ENUM.ordinal()] = 11;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 12;
            $EnumSwitchMapping$0[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
            $EnumSwitchMapping$1 = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
            $EnumSwitchMapping$1[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$1[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 2;
        }
    }

    public AnnotationDeserializer(@NotNull ModuleDescriptor module, @NotNull NotFoundClasses notFoundClasses) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        this.module = module;
        this.notFoundClasses = notFoundClasses;
    }

    private final boolean doesValueConformToExpectedType(ConstantValue<?> constantValue, KotlinType kotlinType, ProtoBuf.Annotation.Argument.Value value) {
        IntRange indices;
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        if (type != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
            if (i == 1) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
                if (classDescriptor != null && !KotlinBuiltIns.isKClass(classDescriptor)) {
                    return false;
                }
            } else if (i == 2) {
                if ((constantValue instanceof ArrayValue) && ((ArrayValue) constantValue).getValue().size() == value.getArrayElementList().size()) {
                    KotlinType arrayElementType = getBuiltIns().getArrayElementType(kotlinType);
                    Intrinsics.checkExpressionValueIsNotNull(arrayElementType, "builtIns.getArrayElementType(expectedType)");
                    ArrayValue arrayValue = (ArrayValue) constantValue;
                    indices = CollectionsKt__CollectionsKt.getIndices(arrayValue.getValue());
                    if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
                        Iterator<Integer> it2 = indices.iterator();
                        while (it2.hasNext()) {
                            int nextInt = ((IntIterator) it2).nextInt();
                            ProtoBuf.Annotation.Argument.Value arrayElement = value.getArrayElement(nextInt);
                            Intrinsics.checkExpressionValueIsNotNull(arrayElement, "value.getArrayElement(i)");
                            if (!doesValueConformToExpectedType(arrayValue.getValue().get(nextInt), arrayElementType, arrayElement)) {
                                return false;
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException(("Deserialized ArrayValue should have the same number of elements as the original array value: " + constantValue).toString());
                }
            }
            return true;
        }
        return Intrinsics.areEqual(constantValue.mo12026getType(this.module), kotlinType);
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    private final Pair<Name, ConstantValue<?>> resolveArgument(ProtoBuf.Annotation.Argument argument, Map<Name, ? extends ValueParameterDescriptor> map, NameResolver nameResolver) {
        ValueParameterDescriptor valueParameterDescriptor = map.get(NameResolverUtilKt.getName(nameResolver, argument.getNameId()));
        if (valueParameterDescriptor != null) {
            Name name = NameResolverUtilKt.getName(nameResolver, argument.getNameId());
            KotlinType type = valueParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
            ProtoBuf.Annotation.Argument.Value value = argument.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "proto.value");
            return new Pair<>(name, resolveValueAndCheckExpectedType(type, value, nameResolver));
        }
        return null;
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }

    private final ConstantValue<?> resolveValueAndCheckExpectedType(KotlinType kotlinType, ProtoBuf.Annotation.Argument.Value value, NameResolver nameResolver) {
        ConstantValue<?> resolveValue = resolveValue(kotlinType, value, nameResolver);
        if (!doesValueConformToExpectedType(resolveValue, kotlinType, value)) {
            resolveValue = null;
        }
        if (resolveValue != null) {
            return resolveValue;
        }
        ErrorValue.Companion companion = ErrorValue.Companion;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected argument value: actual type ");
        outline107.append(value.getType());
        outline107.append(" != expected type ");
        outline107.append(kotlinType);
        return companion.create(outline107.toString());
    }

    @NotNull
    public final AnnotationDescriptor deserializeAnnotation(@NotNull ProtoBuf.Annotation proto, @NotNull NameResolver nameResolver) {
        Map emptyMap;
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        ClassDescriptor resolveClass = resolveClass(NameResolverUtilKt.getClassId(nameResolver, proto.getId()));
        emptyMap = MapsKt__MapsKt.emptyMap();
        if (proto.getArgumentCount() != 0 && !ErrorUtils.isError(resolveClass) && DescriptorUtils.isAnnotationClass(resolveClass)) {
            Collection<ClassConstructorDescriptor> mo11663getConstructors = resolveClass.mo11663getConstructors();
            Intrinsics.checkExpressionValueIsNotNull(mo11663getConstructors, "annotationClass.constructors");
            ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) CollectionsKt.singleOrNull(mo11663getConstructors);
            if (classConstructorDescriptor != null) {
                List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructor.valueParameters");
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameters, 10);
                mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
                LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
                for (Object obj : valueParameters) {
                    ValueParameterDescriptor it2 = (ValueParameterDescriptor) obj;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    linkedHashMap.put(it2.getName(), obj);
                }
                List<ProtoBuf.Annotation.Argument> argumentList = proto.getArgumentList();
                Intrinsics.checkExpressionValueIsNotNull(argumentList, "proto.argumentList");
                ArrayList arrayList = new ArrayList();
                for (ProtoBuf.Annotation.Argument it3 : argumentList) {
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    Pair<Name, ConstantValue<?>> resolveArgument = resolveArgument(it3, linkedHashMap, nameResolver);
                    if (resolveArgument != null) {
                        arrayList.add(resolveArgument);
                    }
                }
                emptyMap = MapsKt__MapsKt.toMap(arrayList);
            }
        }
        return new AnnotationDescriptorImpl(resolveClass.getDefaultType(), emptyMap, SourceElement.NO_SOURCE);
    }

    @NotNull
    public final ConstantValue<?> resolveValue(@NotNull KotlinType expectedType, @NotNull ProtoBuf.Annotation.Argument.Value value, @NotNull NameResolver nameResolver) {
        ConstantValue<?> uByteValue;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(expectedType, "expectedType");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Boolean mo11937get = Flags.IS_UNSIGNED.mo11937get(value.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_UNSIGNED.get(value.flags)");
        boolean booleanValue = mo11937get.booleanValue();
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        if (type != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    byte intValue = (byte) value.getIntValue();
                    uByteValue = booleanValue ? new UByteValue(intValue) : new ByteValue(intValue);
                    return uByteValue;
                case 2:
                    return new CharValue((char) value.getIntValue());
                case 3:
                    short intValue2 = (short) value.getIntValue();
                    uByteValue = booleanValue ? new UShortValue(intValue2) : new ShortValue(intValue2);
                    return uByteValue;
                case 4:
                    int intValue3 = (int) value.getIntValue();
                    uByteValue = booleanValue ? new UIntValue(intValue3) : new IntValue(intValue3);
                    return uByteValue;
                case 5:
                    long intValue4 = value.getIntValue();
                    return booleanValue ? new ULongValue(intValue4) : new LongValue(intValue4);
                case 6:
                    return new FloatValue(value.getFloatValue());
                case 7:
                    return new DoubleValue(value.getDoubleValue());
                case 8:
                    return new BooleanValue(value.getIntValue() != 0);
                case 9:
                    return new StringValue(nameResolver.getString(value.getStringValue()));
                case 10:
                    return new KClassValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), value.getArrayDimensionCount());
                case 11:
                    return new EnumValue(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()), NameResolverUtilKt.getName(nameResolver, value.getEnumValueId()));
                case 12:
                    ProtoBuf.Annotation annotation = value.getAnnotation();
                    Intrinsics.checkExpressionValueIsNotNull(annotation, "value.annotation");
                    return new AnnotationValue(deserializeAnnotation(annotation, nameResolver));
                case 13:
                    ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
                    List<ProtoBuf.Annotation.Argument.Value> arrayElementList = value.getArrayElementList();
                    Intrinsics.checkExpressionValueIsNotNull(arrayElementList, "value.arrayElementList");
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayElementList, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (ProtoBuf.Annotation.Argument.Value it2 : arrayElementList) {
                        SimpleType anyType = getBuiltIns().getAnyType();
                        Intrinsics.checkExpressionValueIsNotNull(anyType, "builtIns.anyType");
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        arrayList.add(resolveValue(anyType, it2, nameResolver));
                    }
                    return constantValueFactory.createArrayValue(arrayList, expectedType);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported annotation argument type: ");
        outline107.append(value.getType());
        outline107.append(" (expected ");
        outline107.append(expectedType);
        outline107.append(')');
        throw new IllegalStateException(outline107.toString().toString());
    }
}
