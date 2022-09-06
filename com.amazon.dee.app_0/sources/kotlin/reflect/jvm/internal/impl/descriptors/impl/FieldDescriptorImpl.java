package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
/* compiled from: FieldDescriptorImpl.kt */
/* loaded from: classes2.dex */
public final class FieldDescriptorImpl extends AnnotatedImpl implements FieldDescriptor {
    @NotNull
    private final PropertyDescriptor correspondingProperty;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FieldDescriptorImpl(@NotNull Annotations annotations, @NotNull PropertyDescriptor correspondingProperty) {
        super(annotations);
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(correspondingProperty, "correspondingProperty");
        this.correspondingProperty = correspondingProperty;
    }
}
