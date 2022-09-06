package kotlin.reflect.jvm.internal.impl.builtins.functions;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.util.OperatorNameConventions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FunctionInvokeDescriptor.kt */
/* loaded from: classes2.dex */
public final class FunctionInvokeDescriptor extends SimpleFunctionDescriptorImpl {
    public static final Factory Factory = new Factory(null);

    /* compiled from: FunctionInvokeDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class Factory {
        private Factory() {
        }

        private final ValueParameterDescriptor createValueParameter(FunctionInvokeDescriptor functionInvokeDescriptor, int i, TypeParameterDescriptor typeParameterDescriptor) {
            String str;
            String asString = typeParameterDescriptor.getName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "typeParameter.name.asString()");
            int hashCode = asString.hashCode();
            if (hashCode != 69) {
                if (hashCode == 84 && asString.equals(ExifInterface.GPS_DIRECTION_TRUE)) {
                    str = "instance";
                }
                str = asString.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
            } else {
                if (asString.equals(ExifInterface.LONGITUDE_EAST)) {
                    str = Constants.BUNDLE_RECEIVER_TAG;
                }
                str = asString.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
            }
            Annotations empty = Annotations.Companion.getEMPTY();
            Name identifier = Name.identifier(str);
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
            SimpleType defaultType = typeParameterDescriptor.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "typeParameter.defaultType");
            SourceElement sourceElement = SourceElement.NO_SOURCE;
            Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
            return new ValueParameterDescriptorImpl(functionInvokeDescriptor, null, i, empty, identifier, defaultType, false, false, false, null, sourceElement);
        }

        @NotNull
        public final FunctionInvokeDescriptor create(@NotNull FunctionClassDescriptor functionClass, boolean z) {
            List<? extends TypeParameterDescriptor> emptyList;
            Iterable<IndexedValue> withIndex;
            int collectionSizeOrDefault;
            Intrinsics.checkParameterIsNotNull(functionClass, "functionClass");
            List<TypeParameterDescriptor> declaredTypeParameters = functionClass.getDeclaredTypeParameters();
            FunctionInvokeDescriptor functionInvokeDescriptor = new FunctionInvokeDescriptor(functionClass, null, CallableMemberDescriptor.Kind.DECLARATION, z, null);
            ReceiverParameterDescriptor thisAsReceiverParameter = functionClass.getThisAsReceiverParameter();
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            ArrayList arrayList = new ArrayList();
            for (Object obj : declaredTypeParameters) {
                if (!(((TypeParameterDescriptor) obj).getVariance() == Variance.IN_VARIANCE)) {
                    break;
                }
                arrayList.add(obj);
            }
            withIndex = CollectionsKt___CollectionsKt.withIndex(arrayList);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(withIndex, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : withIndex) {
                arrayList2.add(FunctionInvokeDescriptor.Factory.createValueParameter(functionInvokeDescriptor, indexedValue.getIndex(), (TypeParameterDescriptor) indexedValue.getValue()));
            }
            functionInvokeDescriptor.mo11596initialize((ReceiverParameterDescriptor) null, thisAsReceiverParameter, emptyList, (List<ValueParameterDescriptor>) arrayList2, (KotlinType) ((TypeParameterDescriptor) CollectionsKt.last((List<? extends Object>) declaredTypeParameters)).getDefaultType(), Modality.ABSTRACT, Visibilities.PUBLIC);
            functionInvokeDescriptor.setHasSynthesizedParameterNames(true);
            return functionInvokeDescriptor;
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ FunctionInvokeDescriptor(DeclarationDescriptor declarationDescriptor, FunctionInvokeDescriptor functionInvokeDescriptor, CallableMemberDescriptor.Kind kind, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(declarationDescriptor, functionInvokeDescriptor, kind, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Object, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl$CopyConfiguration] */
    private final FunctionDescriptor replaceParameterNames(List<Name> list) {
        int collectionSizeOrDefault;
        boolean z;
        Name name;
        int size = getValueParameters().size() - list.size();
        boolean z2 = false;
        boolean z3 = size == 0 || size == 1;
        if (!_Assertions.ENABLED || z3) {
            List<ValueParameterDescriptor> valueParameters = getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameters, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ValueParameterDescriptor it2 : valueParameters) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                Name name2 = it2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "it.name");
                int index = it2.getIndex();
                int i = index - size;
                if (i >= 0 && (name = list.get(i)) != null) {
                    name2 = name;
                }
                arrayList.add(it2.copy(this, name2, index));
            }
            FunctionDescriptorImpl.CopyConfiguration newCopyBuilder = newCopyBuilder(TypeSubstitutor.EMPTY);
            if (!list.isEmpty()) {
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    if (((Name) it3.next()) == null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        z2 = true;
                        break;
                    }
                }
            }
            ?? original = newCopyBuilder.setHasSynthesizedParameterNames(z2).setValueParameters2((List<ValueParameterDescriptor>) arrayList).setOriginal((CallableMemberDescriptor) mo11613getOriginal());
            Intrinsics.checkExpressionValueIsNotNull(original, "newCopyBuilder(TypeSubst…   .setOriginal(original)");
            FunctionDescriptor doSubstitute = super.doSubstitute(original);
            if (doSubstitute == null) {
                Intrinsics.throwNpe();
            }
            return doSubstitute;
        }
        throw new AssertionError("Assertion failed");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    /* renamed from: createSubstitutedCopy */
    protected FunctionDescriptorImpl mo12045createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor functionDescriptor, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement source) {
        Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(source, "source");
        return new FunctionInvokeDescriptor(newOwner, (FunctionInvokeDescriptor) functionDescriptor, kind, isSuspend());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @Nullable
    public FunctionDescriptor doSubstitute(@NotNull FunctionDescriptorImpl.CopyConfiguration configuration) {
        int collectionSizeOrDefault;
        boolean z;
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        FunctionInvokeDescriptor functionInvokeDescriptor = (FunctionInvokeDescriptor) super.doSubstitute(configuration);
        if (functionInvokeDescriptor != null) {
            List<ValueParameterDescriptor> valueParameters = functionInvokeDescriptor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "substituted.valueParameters");
            boolean z2 = true;
            if (!valueParameters.isEmpty()) {
                Iterator<T> it2 = valueParameters.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    ValueParameterDescriptor it3 = (ValueParameterDescriptor) it2.next();
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    KotlinType type = it3.getType();
                    Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
                    if (FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        z2 = false;
                        break;
                    }
                }
            }
            if (z2) {
                return functionInvokeDescriptor;
            }
            List<ValueParameterDescriptor> valueParameters2 = functionInvokeDescriptor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "substituted.valueParameters");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameters2, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ValueParameterDescriptor it4 : valueParameters2) {
                Intrinsics.checkExpressionValueIsNotNull(it4, "it");
                KotlinType type2 = it4.getType();
                Intrinsics.checkExpressionValueIsNotNull(type2, "it.type");
                arrayList.add(FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type2));
            }
            return functionInvokeDescriptor.replaceParameterNames(arrayList);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isTailrec() {
        return false;
    }

    private FunctionInvokeDescriptor(DeclarationDescriptor declarationDescriptor, FunctionInvokeDescriptor functionInvokeDescriptor, CallableMemberDescriptor.Kind kind, boolean z) {
        super(declarationDescriptor, functionInvokeDescriptor, Annotations.Companion.getEMPTY(), OperatorNameConventions.INVOKE, kind, SourceElement.NO_SOURCE);
        setOperator(true);
        setSuspend(z);
        setHasStableParameterNames(false);
    }
}
