package kotlin.reflect.jvm.internal.impl.descriptors;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface PropertyAccessorDescriptor extends VariableAccessorDescriptor {
    @NotNull
    PropertyDescriptor getCorrespondingProperty();

    boolean isDefault();
}
