package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Visibility.kt */
/* loaded from: classes2.dex */
public abstract class Visibility {
    private final boolean isPublicAPI;
    @NotNull
    private final String name;

    /* JADX INFO: Access modifiers changed from: protected */
    public Visibility(@NotNull String name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.isPublicAPI = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public Integer compareTo(@NotNull Visibility visibility) {
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        return Visibilities.compareLocal(this, visibility);
    }

    @NotNull
    public String getInternalDisplayName() {
        return this.name;
    }

    public final boolean isPublicAPI() {
        return this.isPublicAPI;
    }

    public abstract boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor);

    @NotNull
    public Visibility normalize() {
        return this;
    }

    @NotNull
    public final String toString() {
        return getInternalDisplayName();
    }
}
