package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ImplicitClassReceiver.kt */
/* loaded from: classes4.dex */
public class ImplicitClassReceiver implements ImplicitReceiver, ThisClassReceiver {
    @NotNull
    private final ClassDescriptor classDescriptor;
    @NotNull
    private final ClassDescriptor declarationDescriptor;
    private final ImplicitClassReceiver original;

    public ImplicitClassReceiver(@NotNull ClassDescriptor classDescriptor, @Nullable ImplicitClassReceiver implicitClassReceiver) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        this.classDescriptor = classDescriptor;
        this.original = implicitClassReceiver == null ? this : implicitClassReceiver;
        this.declarationDescriptor = this.classDescriptor;
    }

    public boolean equals(@Nullable Object obj) {
        ClassDescriptor classDescriptor = this.classDescriptor;
        ClassDescriptor classDescriptor2 = null;
        if (!(obj instanceof ImplicitClassReceiver)) {
            obj = null;
        }
        ImplicitClassReceiver implicitClassReceiver = (ImplicitClassReceiver) obj;
        if (implicitClassReceiver != null) {
            classDescriptor2 = implicitClassReceiver.classDescriptor;
        }
        return Intrinsics.areEqual(classDescriptor, classDescriptor2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver
    @NotNull
    public final ClassDescriptor getClassDescriptor() {
        return this.classDescriptor;
    }

    public int hashCode() {
        return this.classDescriptor.hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class{");
        outline107.append(mo12036getType());
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo12036getType() {
        SimpleType defaultType = this.classDescriptor.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "classDescriptor.defaultType");
        return defaultType;
    }
}
