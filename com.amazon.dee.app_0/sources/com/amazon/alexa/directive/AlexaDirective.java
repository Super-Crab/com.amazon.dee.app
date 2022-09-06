package com.amazon.alexa.directive;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public class AlexaDirective<NamespaceType, DirectiveType, InstanceType, PayloadType> {
    @CheckForNull
    private final Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class AlexaDirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        AlexaDirectiveBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public AlexaDirective<NamespaceType, DirectiveType, InstanceType, PayloadType> build() {
            return new AlexaDirective<>(this.directive);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public AlexaDirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> directive(Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive) {
            this.directive = directive;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaDirective.AlexaDirectiveBuilder(directive=");
            outline107.append(this.directive);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public AlexaDirective(Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive) {
        this.directive = directive;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, DirectiveType, InstanceType, PayloadType> AlexaDirectiveBuilder<NamespaceType, DirectiveType, InstanceType, PayloadType> builder() {
        return new AlexaDirectiveBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof AlexaDirective;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlexaDirective)) {
            return false;
        }
        AlexaDirective alexaDirective = (AlexaDirective) obj;
        if (!alexaDirective.canEqual(this)) {
            return false;
        }
        Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive = getDirective();
        Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive2 = alexaDirective.getDirective();
        return directive != null ? directive.equals(directive2) : directive2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> getDirective() {
        return this.directive;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Directive<NamespaceType, DirectiveType, InstanceType, PayloadType> directive = getDirective();
        return 59 + (directive == null ? 43 : directive.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaDirective(directive=");
        outline107.append(getDirective());
        outline107.append(")");
        return outline107.toString();
    }
}
