package com.amazon.alexa.rangecontroller.lib.model.factory;

import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import lombok.Generated;
/* loaded from: classes9.dex */
public class RcDirectiveFactory<Instance, Payload> {
    private String correlationToken;
    private HeaderName directiveName;
    private Instance instance;
    private UUID messageId;
    private Payload payload;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcDirectiveFactory() {
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcDirectiveFactory<Instance, Payload> directiveName(HeaderName headerName) {
        this.directiveName = headerName;
        return this;
    }

    public static <I> RcDirectiveFactory<I, AdjustRangeValuePayload> forAdjustRangeValue(I i, double d, String str) {
        return newInstance(HeaderName.ADJUST_RANGE_VALUE, i, AdjustRangeValuePayload.builder().rangeValueDelta(Double.valueOf(d)).rangeValueDeltaDefault(false).build(), str);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcDirectiveFactory<Instance, Payload> instance(Instance instance) {
        this.instance = instance;
        return this;
    }

    private static <I, P> RcDirectiveFactory<I, P> newInstance(@NonNull HeaderName headerName, @NonNull I i, @NonNull P p, String str) {
        return (RcDirectiveFactory<Instance, Payload>) new RcDirectiveFactory().directiveName(headerName).instance(i).payload(p).correlationToken(str);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcDirectiveFactory<Instance, Payload> payload(Payload payload) {
        this.payload = payload;
        return this;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public RcDirectiveFactory<Instance, Payload> correlationToken(String str) {
        this.correlationToken = str;
        return this;
    }

    @NonNull
    public AlexaDirective<NamespaceName, HeaderName, Instance, Payload> create() {
        RcHeaderFactory forDirective = RcHeaderFactory.forDirective(this.directiveName, this.instance);
        UUID uuid = this.messageId;
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        return AlexaDirective.builder().directive(Directive.builder().header(forDirective.messageId(uuid).correlationToken(this.correlationToken).create()).payload(this.payload).build()).build();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public RcDirectiveFactory<Instance, Payload> messageId(UUID uuid) {
        this.messageId = uuid;
        return this;
    }
}
