package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes3.dex */
public class JvmDescriptorTypeWriter<T> {
    @Nullable
    private T jvmCurrentType;
    private int jvmCurrentTypeArrayLevel;
    private final JvmTypeFactory<T> jvmTypeFactory;

    public void writeArrayEnd() {
    }

    public void writeArrayType() {
        if (this.jvmCurrentType == null) {
            this.jvmCurrentTypeArrayLevel++;
        }
    }

    public void writeClass(@NotNull T objectType) {
        Intrinsics.checkParameterIsNotNull(objectType, "objectType");
        writeJvmTypeAsIs(objectType);
    }

    protected final void writeJvmTypeAsIs(@NotNull T type) {
        String repeat;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (this.jvmCurrentType == null) {
            if (this.jvmCurrentTypeArrayLevel > 0) {
                JvmTypeFactory<T> jvmTypeFactory = this.jvmTypeFactory;
                StringBuilder sb = new StringBuilder();
                repeat = StringsKt__StringsJVMKt.repeat("[", this.jvmCurrentTypeArrayLevel);
                sb.append(repeat);
                sb.append(this.jvmTypeFactory.toString(type));
                type = jvmTypeFactory.createFromString(sb.toString());
            }
            this.jvmCurrentType = type;
        }
    }

    public void writeTypeVariable(@NotNull Name name, @NotNull T type) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        writeJvmTypeAsIs(type);
    }
}
