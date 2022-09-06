package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import lombok.NonNull;
/* loaded from: classes11.dex */
public final class InviteAddress implements Comparable<InviteAddress> {
    @NonNull
    private final String target;
    @NonNull
    private final Type type;

    /* loaded from: classes11.dex */
    public static class InviteAddressBuilder {
        private String target;
        private Type type;

        InviteAddressBuilder() {
        }

        public InviteAddress build() {
            return new InviteAddress(this.type, this.target);
        }

        public InviteAddressBuilder target(String str) {
            this.target = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InviteAddress.InviteAddressBuilder(type=");
            outline107.append(this.type);
            outline107.append(", target=");
            return GeneratedOutlineSupport1.outline91(outline107, this.target, ")");
        }

        public InviteAddressBuilder type(Type type) {
            this.type = type;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public enum Type {
        EMAIL,
        SMS,
        CUSTOMER_ID,
        UNKNOWN;

        public static Type valueOfOrDefault(String str, Type type) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException | NullPointerException unused) {
                return type;
            }
        }
    }

    InviteAddress(@NonNull Type type, @NonNull String str) {
        if (type != null) {
            if (str == null) {
                throw new IllegalArgumentException("target is null");
            }
            this.type = type;
            this.target = str;
            return;
        }
        throw new IllegalArgumentException("type is null");
    }

    public static InviteAddressBuilder builder() {
        return new InviteAddressBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InviteAddress)) {
            return false;
        }
        InviteAddress inviteAddress = (InviteAddress) obj;
        Type type = getType();
        Type type2 = inviteAddress.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String target = getTarget();
        String target2 = inviteAddress.getTarget();
        return target != null ? target.equals(target2) : target2 == null;
    }

    @NonNull
    public String getTarget() {
        return this.target;
    }

    @NonNull
    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        Type type = getType();
        int i = 43;
        int hashCode = type == null ? 43 : type.hashCode();
        String target = getTarget();
        int i2 = (hashCode + 59) * 59;
        if (target != null) {
            i = target.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InviteAddress(type=");
        outline107.append(getType());
        outline107.append(", target=");
        outline107.append(getTarget());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(InviteAddress inviteAddress) {
        if (this.type.compareTo(inviteAddress.type) < 0) {
            return -1;
        }
        if (this.type.compareTo(inviteAddress.type) > 0) {
            return 1;
        }
        if (this.target.compareTo(inviteAddress.target) < 0) {
            return -1;
        }
        return this.target.compareTo(inviteAddress.target) > 0 ? 1 : 0;
    }
}
