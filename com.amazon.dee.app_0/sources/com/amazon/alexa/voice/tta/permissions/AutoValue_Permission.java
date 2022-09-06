package com.amazon.alexa.voice.tta.permissions;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.permissions.Permission;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_Permission extends Permission {
    private final String message;
    private final PermissionType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends Permission.Builder {
        private String message;
        private PermissionType type;

        @Override // com.amazon.alexa.voice.tta.permissions.Permission.Builder
        public Permission build() {
            String str = "";
            if (this.type == null) {
                str = GeneratedOutlineSupport1.outline72(str, " type");
            }
            if (this.message == null) {
                str = GeneratedOutlineSupport1.outline72(str, " message");
            }
            if (str.isEmpty()) {
                return new AutoValue_Permission(this.type, this.message);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.tta.permissions.Permission.Builder
        public Permission.Builder message(String str) {
            if (str != null) {
                this.message = str;
                return this;
            }
            throw new NullPointerException("Null message");
        }

        @Override // com.amazon.alexa.voice.tta.permissions.Permission.Builder
        public Permission.Builder type(PermissionType permissionType) {
            if (permissionType != null) {
                this.type = permissionType;
                return this;
            }
            throw new NullPointerException("Null type");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Permission)) {
            return false;
        }
        Permission permission = (Permission) obj;
        return this.type.equals(permission.getType()) && this.message.equals(permission.getMessage());
    }

    @Override // com.amazon.alexa.voice.tta.permissions.Permission
    public String getMessage() {
        return this.message;
    }

    @Override // com.amazon.alexa.voice.tta.permissions.Permission
    public PermissionType getType() {
        return this.type;
    }

    public int hashCode() {
        return ((this.type.hashCode() ^ 1000003) * 1000003) ^ this.message.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Permission{type=");
        outline107.append(this.type);
        outline107.append(", message=");
        return GeneratedOutlineSupport1.outline91(outline107, this.message, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_Permission(PermissionType permissionType, String str) {
        this.type = permissionType;
        this.message = str;
    }
}
