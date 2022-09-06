package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
/* compiled from: Annotations.kt */
/* loaded from: classes2.dex */
final class CompositeAnnotations$iterator$1 extends Lambda implements Function1<Annotations, Sequence<? extends AnnotationDescriptor>> {
    public static final CompositeAnnotations$iterator$1 INSTANCE = new CompositeAnnotations$iterator$1();

    CompositeAnnotations$iterator$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Sequence<AnnotationDescriptor> mo12165invoke(@NotNull Annotations it2) {
        Sequence<AnnotationDescriptor> asSequence;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        asSequence = CollectionsKt___CollectionsKt.asSequence(it2);
        return asSequence;
    }
}
