package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
/* loaded from: classes3.dex */
public final class AbstractBinaryClassAnnotationAndConstantLoader$storage$1 extends Lambda implements Function1<KotlinJvmBinaryClass, AbstractBinaryClassAnnotationAndConstantLoader.Storage<? extends A, ? extends C>> {
    final /* synthetic */ AbstractBinaryClassAnnotationAndConstantLoader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractBinaryClassAnnotationAndConstantLoader$storage$1(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader) {
        super(1);
        this.this$0 = abstractBinaryClassAnnotationAndConstantLoader;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final AbstractBinaryClassAnnotationAndConstantLoader.Storage<A, C> mo12165invoke(@NotNull KotlinJvmBinaryClass kotlinClass) {
        AbstractBinaryClassAnnotationAndConstantLoader.Storage<A, C> loadAnnotationsAndInitializers;
        Intrinsics.checkParameterIsNotNull(kotlinClass, "kotlinClass");
        loadAnnotationsAndInitializers = this.this$0.loadAnnotationsAndInitializers(kotlinClass);
        return loadAnnotationsAndInitializers;
    }
}
