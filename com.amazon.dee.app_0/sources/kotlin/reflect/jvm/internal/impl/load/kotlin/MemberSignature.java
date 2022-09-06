package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MemberSignature.kt */
/* loaded from: classes3.dex */
public final class MemberSignature {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String signature;

    /* compiled from: MemberSignature.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromFieldNameAndDesc(@NotNull String name, @NotNull String desc) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            return new MemberSignature(GeneratedOutlineSupport1.outline48(name, '#', desc), null);
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromJvmMemberSignature(@NotNull JvmMemberSignature signature) {
            Intrinsics.checkParameterIsNotNull(signature, "signature");
            if (signature instanceof JvmMemberSignature.Method) {
                return fromMethodNameAndDesc(signature.getName(), signature.getDesc());
            }
            if (!(signature instanceof JvmMemberSignature.Field)) {
                throw new NoWhenBranchMatchedException();
            }
            return fromFieldNameAndDesc(signature.getName(), signature.getDesc());
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethod(@NotNull NameResolver nameResolver, @NotNull JvmProtoBuf.JvmMethodSignature signature) {
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(signature, "signature");
            return fromMethodNameAndDesc(nameResolver.getString(signature.getName()), nameResolver.getString(signature.getDesc()));
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethodNameAndDesc(@NotNull String name, @NotNull String desc) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            return new MemberSignature(GeneratedOutlineSupport1.outline72(name, desc), null);
        }

        @JvmStatic
        @NotNull
        public final MemberSignature fromMethodSignatureAndParameterIndex(@NotNull MemberSignature signature, int i) {
            Intrinsics.checkParameterIsNotNull(signature, "signature");
            return new MemberSignature(signature.getSignature$descriptors_jvm() + '@' + i, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private MemberSignature(String str) {
        this.signature = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof MemberSignature) && Intrinsics.areEqual(this.signature, ((MemberSignature) obj).signature);
        }
        return true;
    }

    @NotNull
    public final String getSignature$descriptors_jvm() {
        return this.signature;
    }

    public int hashCode() {
        String str = this.signature;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("MemberSignature(signature="), this.signature, ")");
    }

    public /* synthetic */ MemberSignature(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
