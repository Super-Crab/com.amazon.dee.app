package com.amazon.alexa.directive;

import com.amazon.alexa.header.Header;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public class Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> {
    @CheckForNull
    private final Header<NamespaceType, DirectiveType, InstanceType> header;
    @CheckForNull
    private final PayloadType payload;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class DirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Header<NamespaceType, DirectiveType, InstanceType> header;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PayloadType payload;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        DirectiveBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> build() {
            return new Directive<>(this.header, this.payload);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public DirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> header(Header<NamespaceType, DirectiveType, InstanceType> header) {
            this.header = header;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public DirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> payload(PayloadType payloadtype) {
            this.payload = payloadtype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Directive.DirectiveBuilder(header=");
            outline107.append(this.header);
            outline107.append(", payload=");
            return GeneratedOutlineSupport1.outline88(outline107, this.payload, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Directive(Header<NamespaceType, DirectiveType, InstanceType> header, PayloadType payloadtype) {
        this.header = header;
        this.payload = payloadtype;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, DirectiveType, InstanceType, PayloadType> DirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> builder() {
        return new DirectiveBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Directive;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Directive)) {
            return false;
        }
        Directive directive = (Directive) obj;
        if (!directive.canEqual(this)) {
            return false;
        }
        Header<NamespaceType, DirectiveType, InstanceType> header = getHeader();
        Header<NamespaceType, DirectiveType, InstanceType> header2 = directive.getHeader();
        if (header != null ? !header.equals(header2) : header2 != null) {
            return false;
        }
        PayloadType payload = getPayload();
        Object payload2 = directive.getPayload();
        return payload != null ? payload.equals(payload2) : payload2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Header<NamespaceType, DirectiveType, InstanceType> getHeader() {
        return this.header;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PayloadType getPayload() {
        return this.payload;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Header<NamespaceType, DirectiveType, InstanceType> header = getHeader();
        int i = 43;
        int hashCode = header == null ? 43 : header.hashCode();
        PayloadType payload = getPayload();
        int i2 = (hashCode + 59) * 59;
        if (payload != null) {
            i = payload.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Directive(header=");
        outline107.append(getHeader());
        outline107.append(", payload=");
        outline107.append(getPayload());
        outline107.append(")");
        return outline107.toString();
    }
}
