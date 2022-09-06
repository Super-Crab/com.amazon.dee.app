package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: util.kt */
/* loaded from: classes3.dex */
public final class UtilKt {
    @NotNull
    public static final List<ValueParameterDescriptor> copyValueParameters(@NotNull Collection<ValueParameterData> newValueParametersTypes, @NotNull Collection<? extends ValueParameterDescriptor> oldValueParameters, @NotNull CallableDescriptor newOwner) {
        List<Pair> zip;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(newValueParametersTypes, "newValueParametersTypes");
        Intrinsics.checkParameterIsNotNull(oldValueParameters, "oldValueParameters");
        Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
        boolean z = newValueParametersTypes.size() == oldValueParameters.size();
        if (_Assertions.ENABLED && !z) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Different value parameters sizes: Enhanced = ");
            outline107.append(newValueParametersTypes.size());
            outline107.append(", Old = ");
            outline107.append(oldValueParameters.size());
            throw new AssertionError(outline107.toString());
        }
        zip = CollectionsKt___CollectionsKt.zip(newValueParametersTypes, oldValueParameters);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(zip, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Pair pair : zip) {
            ValueParameterData valueParameterData = (ValueParameterData) pair.component1();
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) pair.component2();
            int index = valueParameterDescriptor.getIndex();
            Annotations mo12070getAnnotations = valueParameterDescriptor.mo12070getAnnotations();
            Name name = valueParameterDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "oldParameter.name");
            KotlinType type = valueParameterData.getType();
            boolean hasDefaultValue = valueParameterData.getHasDefaultValue();
            boolean isCrossinline = valueParameterDescriptor.isCrossinline();
            boolean isNoinline = valueParameterDescriptor.isNoinline();
            KotlinType arrayElementType = valueParameterDescriptor.getVarargElementType() != null ? DescriptorUtilsKt.getModule(newOwner).getBuiltIns().getArrayElementType(valueParameterData.getType()) : null;
            SourceElement source = valueParameterDescriptor.getSource();
            Intrinsics.checkExpressionValueIsNotNull(source, "oldParameter.source");
            arrayList.add(new ValueParameterDescriptorImpl(newOwner, null, index, mo12070getAnnotations, name, type, hasDefaultValue, isCrossinline, isNoinline, arrayElementType, source));
        }
        return arrayList;
    }

    @Nullable
    public static final AnnotationDefaultValue getDefaultValueFromAnnotation(@NotNull ValueParameterDescriptor getDefaultValueFromAnnotation) {
        ConstantValue<?> firstArgument;
        String value;
        Intrinsics.checkParameterIsNotNull(getDefaultValueFromAnnotation, "$this$getDefaultValueFromAnnotation");
        Annotations mo12070getAnnotations = getDefaultValueFromAnnotation.mo12070getAnnotations();
        FqName fqName = JvmAnnotationNames.DEFAULT_VALUE_FQ_NAME;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.DEFAULT_VALUE_FQ_NAME");
        AnnotationDescriptor mo11701findAnnotation = mo12070getAnnotations.mo11701findAnnotation(fqName);
        if (mo11701findAnnotation != null && (firstArgument = DescriptorUtilsKt.firstArgument(mo11701findAnnotation)) != null) {
            if (!(firstArgument instanceof StringValue)) {
                firstArgument = null;
            }
            StringValue stringValue = (StringValue) firstArgument;
            if (stringValue != null && (value = stringValue.getValue()) != null) {
                return new StringDefaultValue(value);
            }
        }
        Annotations mo12070getAnnotations2 = getDefaultValueFromAnnotation.mo12070getAnnotations();
        FqName fqName2 = JvmAnnotationNames.DEFAULT_NULL_FQ_NAME;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "JvmAnnotationNames.DEFAULT_NULL_FQ_NAME");
        if (mo12070getAnnotations2.hasAnnotation(fqName2)) {
            return NullDefaultValue.INSTANCE;
        }
        return null;
    }

    @Nullable
    public static final LazyJavaStaticClassScope getParentJavaStaticClassScope(@NotNull ClassDescriptor getParentJavaStaticClassScope) {
        Intrinsics.checkParameterIsNotNull(getParentJavaStaticClassScope, "$this$getParentJavaStaticClassScope");
        ClassDescriptor superClassNotAny = DescriptorUtilsKt.getSuperClassNotAny(getParentJavaStaticClassScope);
        LazyJavaStaticClassScope lazyJavaStaticClassScope = null;
        if (superClassNotAny != null) {
            MemberScope mo12047getStaticScope = superClassNotAny.mo12047getStaticScope();
            if (mo12047getStaticScope instanceof LazyJavaStaticClassScope) {
                lazyJavaStaticClassScope = mo12047getStaticScope;
            }
            LazyJavaStaticClassScope lazyJavaStaticClassScope2 = lazyJavaStaticClassScope;
            return lazyJavaStaticClassScope2 != null ? lazyJavaStaticClassScope2 : getParentJavaStaticClassScope(superClassNotAny);
        }
        return null;
    }
}
