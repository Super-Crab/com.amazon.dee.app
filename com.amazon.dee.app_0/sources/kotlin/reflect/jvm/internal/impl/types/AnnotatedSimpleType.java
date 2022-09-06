package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes4.dex */
public final class AnnotatedSimpleType extends DelegatingSimpleTypeImpl {
    @NotNull
    private final Annotations annotations;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnnotatedSimpleType(@NotNull SimpleType delegate, @NotNull Annotations annotations) {
        super(delegate);
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        this.annotations = annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations */
    public Annotations mo12070getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    /* renamed from: replaceDelegate */
    public AnnotatedSimpleType mo12116replaceDelegate(@NotNull SimpleType delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        return new AnnotatedSimpleType(delegate, mo12070getAnnotations());
    }
}
