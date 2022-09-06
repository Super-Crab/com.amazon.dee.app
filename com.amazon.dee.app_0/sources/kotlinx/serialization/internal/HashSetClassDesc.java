package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionDescriptors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/serialization/internal/HashSetClassDesc;", "Lkotlinx/serialization/internal/ListLikeDescriptor;", "elementDesc", "Lkotlinx/serialization/SerialDescriptor;", "(Lkotlinx/serialization/SerialDescriptor;)V", "serialName", "", "getSerialName", "()Ljava/lang/String;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class HashSetClassDesc extends ListLikeDescriptor {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashSetClassDesc(@NotNull SerialDescriptor elementDesc) {
        super(elementDesc, null);
        Intrinsics.checkParameterIsNotNull(elementDesc, "elementDesc");
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return CollectionDescriptorsKt.HASH_SET_NAME;
    }
}
