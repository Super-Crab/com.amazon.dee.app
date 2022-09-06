package com.amazon.alexa.endpoint;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public final class Scope {
    @CheckForNull
    private final String token;
    @CheckForNull
    private final String type;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class ScopeBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String token;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String type;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ScopeBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Scope build() {
            return new Scope(this.type, this.token);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scope.ScopeBuilder(type=");
            outline107.append(this.type);
            outline107.append(", token=");
            return GeneratedOutlineSupport1.outline91(outline107, this.token, ")");
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ScopeBuilder token(String str) {
            this.token = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ScopeBuilder type(String str) {
            this.type = str;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    Scope(String str, String str2) {
        this.type = str;
        this.token = str2;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static ScopeBuilder builder() {
        return new ScopeBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        Scope scope = (Scope) obj;
        String type = getType();
        String type2 = scope.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String token = getToken();
        String token2 = scope.getToken();
        return token != null ? token.equals(token2) : token2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getToken() {
        return this.token;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getType() {
        return this.type;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        String type = getType();
        int i = 43;
        int hashCode = type == null ? 43 : type.hashCode();
        String token = getToken();
        int i2 = (hashCode + 59) * 59;
        if (token != null) {
            i = token.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scope(type=");
        outline107.append(getType());
        outline107.append(", token=");
        outline107.append(getToken());
        outline107.append(")");
        return outline107.toString();
    }
}
