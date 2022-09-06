package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionDescriptors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/serialization/internal/NamedListClassDescriptor;", "Lkotlinx/serialization/internal/ListLikeDescriptor;", "serialName", "", "elementDescriptor", "Lkotlinx/serialization/SerialDescriptor;", "(Ljava/lang/String;Lkotlinx/serialization/SerialDescriptor;)V", "getSerialName", "()Ljava/lang/String;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NamedListClassDescriptor extends ListLikeDescriptor {
    @NotNull
    private final String serialName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NamedListClassDescriptor(@NotNull String serialName, @NotNull SerialDescriptor elementDescriptor) {
        super(elementDescriptor, null);
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(elementDescriptor, "elementDescriptor");
        this.serialName = serialName;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }
}
