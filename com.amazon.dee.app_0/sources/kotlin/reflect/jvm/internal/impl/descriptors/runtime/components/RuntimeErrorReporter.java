package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import org.jetbrains.annotations.NotNull;
/* compiled from: RuntimeErrorReporter.kt */
/* loaded from: classes2.dex */
public final class RuntimeErrorReporter implements ErrorReporter {
    public static final RuntimeErrorReporter INSTANCE = new RuntimeErrorReporter();

    private RuntimeErrorReporter() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
    public void reportCannotInferVisibility(@NotNull CallableMemberDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        throw new IllegalStateException("Cannot infer visibility for " + descriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
    public void reportIncompleteHierarchy(@NotNull ClassDescriptor descriptor, @NotNull List<String> unresolvedSuperClasses) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(unresolvedSuperClasses, "unresolvedSuperClasses");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Incomplete hierarchy for class ");
        outline107.append(descriptor.getName());
        outline107.append(", unresolved classes ");
        outline107.append(unresolvedSuperClasses);
        throw new IllegalStateException(outline107.toString());
    }
}
