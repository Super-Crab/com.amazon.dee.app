package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: util.kt */
/* loaded from: classes3.dex */
public final class StringDefaultValue extends AnnotationDefaultValue {
    @NotNull
    private final String value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringDefaultValue(@NotNull String value) {
        super(null);
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
