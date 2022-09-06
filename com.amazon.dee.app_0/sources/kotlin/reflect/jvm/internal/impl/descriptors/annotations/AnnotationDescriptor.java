package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnotationDescriptor.kt */
/* loaded from: classes2.dex */
public interface AnnotationDescriptor {

    /* compiled from: AnnotationDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        @Nullable
        public static FqName getFqName(AnnotationDescriptor annotationDescriptor) {
            ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
            if (annotationClass != null) {
                if (ErrorUtils.isError(annotationClass)) {
                    annotationClass = null;
                }
                if (annotationClass == null) {
                    return null;
                }
                return DescriptorUtilsKt.fqNameOrNull(annotationClass);
            }
            return null;
        }
    }

    @NotNull
    Map<Name, ConstantValue<?>> getAllValueArguments();

    @Nullable
    FqName getFqName();

    @NotNull
    /* renamed from: getSource */
    SourceElement mo11658getSource();

    @NotNull
    /* renamed from: getType */
    KotlinType mo11659getType();
}
