package kotlinx.serialization;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.serialization.PolymorphicKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: Polymorphic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"PolymorphicClassDescriptor", "Lkotlinx/serialization/SerialDescriptor;", "PolymorphicClassDescriptor$annotations", "()V", "getPolymorphicClassDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PolymorphicKt {
    @NotNull
    private static final SerialDescriptor PolymorphicClassDescriptor = SerialDescriptorBuilderKt.SerialDescriptor("kotlinx.serialization.Polymorphic", PolymorphicKind.OPEN.INSTANCE, PolymorphicKt$PolymorphicClassDescriptor$1.INSTANCE);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Top-level polymorphic descriptor is deprecated, use descriptor from the instance of PolymorphicSerializer orcheck for descriptor kind instead")
    public static /* synthetic */ void PolymorphicClassDescriptor$annotations() {
    }

    @NotNull
    public static final SerialDescriptor getPolymorphicClassDescriptor() {
        return PolymorphicClassDescriptor;
    }
}
