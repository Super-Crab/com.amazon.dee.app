package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: BuiltInAnnotationDescriptor.kt */
/* loaded from: classes2.dex */
public final class BuiltInAnnotationDescriptor implements AnnotationDescriptor {
    @NotNull
    private final Map<Name, ConstantValue<?>> allValueArguments;
    private final KotlinBuiltIns builtIns;
    @NotNull
    private final FqName fqName;
    @NotNull
    private final Lazy type$delegate;

    /* JADX WARN: Multi-variable type inference failed */
    public BuiltInAnnotationDescriptor(@NotNull KotlinBuiltIns builtIns, @NotNull FqName fqName, @NotNull Map<Name, ? extends ConstantValue<?>> allValueArguments) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(allValueArguments, "allValueArguments");
        this.builtIns = builtIns;
        this.fqName = fqName;
        this.allValueArguments = allValueArguments;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new BuiltInAnnotationDescriptor$type$2(this));
        this.type$delegate = lazy;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return this.allValueArguments;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public FqName getFqName() {
        return this.fqName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    /* renamed from: getSource */
    public SourceElement mo11658getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return sourceElement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    /* renamed from: getType */
    public KotlinType mo11659getType() {
        return (KotlinType) this.type$delegate.getValue();
    }
}
