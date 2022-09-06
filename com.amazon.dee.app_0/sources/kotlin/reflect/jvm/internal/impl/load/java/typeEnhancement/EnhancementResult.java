package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeEnhancement.kt */
/* loaded from: classes3.dex */
final class EnhancementResult<T> {
    @Nullable
    private final Annotations enhancementAnnotations;
    private final T result;

    public EnhancementResult(T t, @Nullable Annotations annotations) {
        this.result = t;
        this.enhancementAnnotations = annotations;
    }

    public final T component1() {
        return this.result;
    }

    @Nullable
    public final Annotations component2() {
        return this.enhancementAnnotations;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof EnhancementResult)) {
                return false;
            }
            EnhancementResult enhancementResult = (EnhancementResult) obj;
            return Intrinsics.areEqual(this.result, enhancementResult.result) && Intrinsics.areEqual(this.enhancementAnnotations, enhancementResult.enhancementAnnotations);
        }
        return true;
    }

    public int hashCode() {
        T t = this.result;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        Annotations annotations = this.enhancementAnnotations;
        if (annotations != null) {
            i = annotations.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EnhancementResult(result=");
        outline107.append(this.result);
        outline107.append(", enhancementAnnotations=");
        outline107.append(this.enhancementAnnotations);
        outline107.append(")");
        return outline107.toString();
    }
}
