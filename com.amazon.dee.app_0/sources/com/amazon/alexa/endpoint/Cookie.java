package com.amazon.alexa.endpoint;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public final class Cookie {

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class CookieBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        CookieBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Cookie build() {
            return new Cookie();
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            return "Cookie.CookieBuilder()";
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    Cookie() {
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static CookieBuilder builder() {
        return new CookieBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Cookie);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        return 1;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        return "Cookie()";
    }
}
