package kotlin.reflect.jvm.internal.impl.load.java;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes3.dex */
public final class NameAndSignature {
    @NotNull
    private final Name name;
    @NotNull
    private final String signature;

    public NameAndSignature(@NotNull Name name, @NotNull String signature) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signature, "signature");
        this.name = name;
        this.signature = signature;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NameAndSignature)) {
                return false;
            }
            NameAndSignature nameAndSignature = (NameAndSignature) obj;
            return Intrinsics.areEqual(this.name, nameAndSignature.name) && Intrinsics.areEqual(this.signature, nameAndSignature.signature);
        }
        return true;
    }

    @NotNull
    public final Name getName() {
        return this.name;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        Name name = this.name;
        int i = 0;
        int hashCode = (name != null ? name.hashCode() : 0) * 31;
        String str = this.signature;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NameAndSignature(name=");
        outline107.append(this.name);
        outline107.append(", signature=");
        return GeneratedOutlineSupport1.outline91(outline107, this.signature, ")");
    }
}
