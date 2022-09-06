package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JvmMemberSignature.kt */
/* loaded from: classes4.dex */
public abstract class JvmMemberSignature {

    /* compiled from: JvmMemberSignature.kt */
    /* loaded from: classes4.dex */
    public static final class Field extends JvmMemberSignature {
        @NotNull
        private final String desc;
        @NotNull
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(@NotNull String name, @NotNull String desc) {
            super(null);
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            this.name = name;
            this.desc = desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String asString() {
            return getName() + JsonReaderKt.COLON + getDesc();
        }

        @NotNull
        public final String component1() {
            return getName();
        }

        @NotNull
        public final String component2() {
            return getDesc();
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Field)) {
                    return false;
                }
                Field field = (Field) obj;
                return Intrinsics.areEqual(getName(), field.getName()) && Intrinsics.areEqual(getDesc(), field.getDesc());
            }
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getName() {
            return this.name;
        }

        public int hashCode() {
            String name = getName();
            int i = 0;
            int hashCode = (name != null ? name.hashCode() : 0) * 31;
            String desc = getDesc();
            if (desc != null) {
                i = desc.hashCode();
            }
            return hashCode + i;
        }
    }

    /* compiled from: JvmMemberSignature.kt */
    /* loaded from: classes4.dex */
    public static final class Method extends JvmMemberSignature {
        @NotNull
        private final String desc;
        @NotNull
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Method(@NotNull String name, @NotNull String desc) {
            super(null);
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            this.name = name;
            this.desc = desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String asString() {
            return getName() + getDesc();
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Method)) {
                    return false;
                }
                Method method = (Method) obj;
                return Intrinsics.areEqual(getName(), method.getName()) && Intrinsics.areEqual(getDesc(), method.getDesc());
            }
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getDesc() {
            return this.desc;
        }

        @Override // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        @NotNull
        public String getName() {
            return this.name;
        }

        public int hashCode() {
            String name = getName();
            int i = 0;
            int hashCode = (name != null ? name.hashCode() : 0) * 31;
            String desc = getDesc();
            if (desc != null) {
                i = desc.hashCode();
            }
            return hashCode + i;
        }
    }

    private JvmMemberSignature() {
    }

    @NotNull
    public abstract String asString();

    @NotNull
    public abstract String getDesc();

    @NotNull
    public abstract String getName();

    @NotNull
    public final String toString() {
        return asString();
    }

    public /* synthetic */ JvmMemberSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
