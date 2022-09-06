package kotlinx.serialization.internal;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerialClassDescImpl.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Should not be used in general code, please migrate to SerialDescriptor() factory function instead")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/serialization/internal/SerialClassDescImpl;", "Lkotlinx/serialization/internal/PluginGeneratedSerialDescriptor;", "serialName", "", "generatedSerializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "elementsCount", "", "(Ljava/lang/String;Lkotlinx/serialization/internal/GeneratedSerializer;I)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public class SerialClassDescImpl extends PluginGeneratedSerialDescriptor {
    public /* synthetic */ SerialClassDescImpl(String str, GeneratedSerializer generatedSerializer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : generatedSerializer, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialClassDescImpl(@NotNull String serialName, @Nullable GeneratedSerializer<?> generatedSerializer, int i) {
        super(serialName, generatedSerializer, i);
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
    }
}
