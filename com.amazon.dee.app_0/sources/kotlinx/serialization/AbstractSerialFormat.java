package kotlinx.serialization;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialFormat.kt */
@Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated for removal since it is indistinguishable from SerialFormat interface. Use SerialFormat instead.", replaceWith = @ReplaceWith(expression = "SerialFormat", imports = {}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/serialization/AbstractSerialFormat;", "Lkotlinx/serialization/SerialFormat;", "context", "Lkotlinx/serialization/modules/SerialModule;", "(Lkotlinx/serialization/modules/SerialModule;)V", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractSerialFormat implements SerialFormat {
    @NotNull
    private final SerialModule context;

    public AbstractSerialFormat(@NotNull SerialModule context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // kotlinx.serialization.SerialFormat
    @NotNull
    public SerialModule getContext() {
        return this.context;
    }
}
