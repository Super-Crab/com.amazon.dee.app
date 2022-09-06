package com.amazon.alexa.rangecontroller.lib.model.factory;

import com.amazon.alexa.header.Header;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes9.dex */
public class RcHeaderFactory<NamespaceType, NameType, Instance> {
    private static final String PAYLOAD_VERSION = "3";
    private String correlationToken;
    private Instance instance;
    private UUID messageId;
    private NameType name;
    private NamespaceType namespace;
    private String payloadVersion;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcHeaderFactory() {
    }

    @NonNull
    public static RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forChangeReport() {
        return newInstance(NamespaceName.ALEXA, HeaderName.CHANGE_REPORT, null);
    }

    @NonNull
    public static <Instance> RcHeaderFactory<NamespaceName, HeaderName, Instance> forDirective(HeaderName headerName, Instance instance) {
        return newInstance(NamespaceName.RANGE_CONTROLLER, headerName, instance);
    }

    @NonNull
    public static RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forErrorResponse(@Nonnull String str) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> newInstance = newInstance(NamespaceName.ALEXA, HeaderName.ERROR_RESPONSE, null);
        newInstance.correlationToken(str);
        return newInstance;
    }

    @NonNull
    public static RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forResponse(@Nonnull String str) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> newInstance = newInstance(NamespaceName.ALEXA, HeaderName.RESPONSE, null);
        newInstance.correlationToken(str);
        return newInstance;
    }

    @NonNull
    public static RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forSafetyErrorResponse(@Nonnull String str) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> newInstance = newInstance(NamespaceName.ALEXA_SAFETY, HeaderName.ERROR_RESPONSE, null);
        newInstance.correlationToken(str);
        return newInstance;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcHeaderFactory<NamespaceType, NameType, Instance> instance(Instance instance) {
        this.instance = instance;
        return this;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcHeaderFactory<NamespaceType, NameType, Instance> name(NameType nametype) {
        this.name = nametype;
        return this;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcHeaderFactory<NamespaceType, NameType, Instance> namespace(NamespaceType namespacetype) {
        this.namespace = namespacetype;
        return this;
    }

    @NonNull
    private static <NamespaceType, NameType, Instance> RcHeaderFactory<NamespaceType, NameType, Instance> newInstance(NamespaceType namespacetype, NameType nametype, Instance instance) {
        return new RcHeaderFactory().namespace(namespacetype).name(nametype).instance(instance).messageId(UUID.randomUUID()).payloadVersion("3");
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcHeaderFactory<NamespaceType, NameType, Instance> payloadVersion(String str) {
        this.payloadVersion = str;
        return this;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public RcHeaderFactory<NamespaceType, NameType, Instance> correlationToken(String str) {
        this.correlationToken = str;
        return this;
    }

    @NonNull
    public Header<NamespaceType, NameType, Instance> create() {
        return Header.builder().namespace(this.namespace).name(this.name).instance(this.instance).messageId(this.messageId).correlationToken(this.correlationToken).payloadVersion(this.payloadVersion).build();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public RcHeaderFactory<NamespaceType, NameType, Instance> messageId(UUID uuid) {
        this.messageId = uuid;
        return this;
    }
}
