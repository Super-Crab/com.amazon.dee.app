package kotlinx.serialization.internal;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorKt;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.UnionKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Enums.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "For plugin-generated code")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/internal/EnumDescriptor;", "Lkotlinx/serialization/internal/SerialClassDescImpl;", "name", "", "elementsCount", "", "(Ljava/lang/String;I)V", "elementDescriptors", "", "Lkotlinx/serialization/SerialDescriptor;", "getElementDescriptors", "()[Lkotlinx/serialization/SerialDescriptor;", "elementDescriptors$delegate", "Lkotlin/Lazy;", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "equals", "", "other", "", "getElementDescriptor", "index", "hashCode", "toString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class EnumDescriptor extends SerialClassDescImpl {
    private final Lazy elementDescriptors$delegate;
    @NotNull
    private final SerialKind kind;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumDescriptor(@NotNull String name, int i) {
        super(name, null, i, 2, null);
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.kind = UnionKind.ENUM_KIND.INSTANCE;
        lazy = LazyKt__LazyJVMKt.lazy(new EnumDescriptor$elementDescriptors$2(this, i, name));
        this.elementDescriptors$delegate = lazy;
    }

    private final SerialDescriptor[] getElementDescriptors() {
        return (SerialDescriptor[]) this.elementDescriptors$delegate.getValue();
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SerialDescriptor)) {
            return false;
        }
        SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
        return serialDescriptor.mo12397getKind() == UnionKind.ENUM_KIND.INSTANCE && !(Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) ^ true) && !(Intrinsics.areEqual(SerialDescriptorKt.elementNames(this), SerialDescriptorKt.elementNames(serialDescriptor)) ^ true);
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor, kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        return getElementDescriptors()[i];
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor, kotlinx.serialization.SerialDescriptor
    @NotNull
    /* renamed from: getKind */
    public SerialKind mo12397getKind() {
        return this.kind;
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public int hashCode() {
        return SerialDescriptorKt.elementNames(this).hashCode() + (getSerialName().hashCode() * 31);
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    @NotNull
    public String toString() {
        String joinToString$default;
        List<String> elementNames = SerialDescriptorKt.elementNames(this);
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(elementNames, ", ", getSerialName() + '(', ")", 0, null, null, 56, null);
        return joinToString$default;
    }
}
