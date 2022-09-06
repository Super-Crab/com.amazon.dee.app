package com.amazon.alexa.rangecontroller.lib.model.factory;

import com.amazon.alexa.changereport.payload.ChangeReportPayload;
import com.amazon.alexa.endpoint.Endpoint;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.header.Header;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.response.payload.EmptyPayload;
import com.amazon.alexa.response.payload.ErrorResponsePayload;
import com.amazon.alexa.response.payload.SafetyErrorResponsePayload;
import com.amazon.alexa.response.payload.type.ErrorResponseType;
import com.amazon.alexa.response.payload.type.SafetyErrorResponseType;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes9.dex */
public class RcEventFactory<NamespaceType, NameType, PayloadType> {
    private Endpoint endpoint;
    private Header<NamespaceType, NameType, EmptyInstance> header;
    private PayloadType payload;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcEventFactory() {
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcEventFactory<NamespaceType, NameType, PayloadType> endpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, ChangeReportPayload> forChangeReport(@Nonnull ChangeReportPayload changeReportPayload) {
        return forChangeReport(changeReportPayload, null);
    }

    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, ErrorResponsePayload> forErrorResponse(@Nonnull ErrorResponseType errorResponseType, @Nonnull String str, @Nonnull String str2) {
        return forErrorResponse(errorResponseType, str, str2, null);
    }

    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, EmptyPayload> forResponse(@Nonnull String str) {
        return forResponse(str, null);
    }

    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, SafetyErrorResponsePayload> forSafetyErrorResponse(@Nonnull SafetyErrorResponseType safetyErrorResponseType, @Nonnull String str, @Nonnull String str2) {
        return forSafetyErrorResponse(safetyErrorResponseType, str, str2, null);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcEventFactory<NamespaceType, NameType, PayloadType> header(Header<NamespaceType, NameType, EmptyInstance> header) {
        this.header = header;
        return this;
    }

    @NonNull
    private static <NamespaceType, NameType, PayloadType> RcEventFactory<NamespaceType, NameType, PayloadType> newInstance(Header<NamespaceType, NameType, EmptyInstance> header, PayloadType payloadtype) {
        return new RcEventFactory().header(header).payload(payloadtype);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private RcEventFactory<NamespaceType, NameType, PayloadType> payload(PayloadType payloadtype) {
        this.payload = payloadtype;
        return this;
    }

    @NonNull
    public Event<NamespaceType, NameType, EmptyInstance, PayloadType> create() {
        return Event.builder().header(this.header).endpoint(this.endpoint).payload(this.payload).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, ChangeReportPayload> forChangeReport(@Nonnull ChangeReportPayload changeReportPayload, @CheckForNull UUID uuid) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forChangeReport = RcHeaderFactory.forChangeReport();
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        return newInstance(forChangeReport.messageId(uuid).create(), changeReportPayload);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, ErrorResponsePayload> forErrorResponse(@Nonnull ErrorResponseType errorResponseType, @Nonnull String str, @Nonnull String str2, @CheckForNull UUID uuid) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forErrorResponse = RcHeaderFactory.forErrorResponse(str2);
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        return newInstance(forErrorResponse.messageId(uuid).create(), ErrorResponsePayload.builder().type(errorResponseType).message(str).build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, EmptyPayload> forResponse(@Nonnull String str, @CheckForNull UUID uuid) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forResponse = RcHeaderFactory.forResponse(str);
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        return newInstance(forResponse.messageId(uuid).create(), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public static RcEventFactory<NamespaceName, HeaderName, SafetyErrorResponsePayload> forSafetyErrorResponse(@Nonnull SafetyErrorResponseType safetyErrorResponseType, @Nonnull String str, @Nonnull String str2, @CheckForNull UUID uuid) {
        RcHeaderFactory<NamespaceName, HeaderName, EmptyInstance> forSafetyErrorResponse = RcHeaderFactory.forSafetyErrorResponse(str2);
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        return newInstance(forSafetyErrorResponse.messageId(uuid).create(), SafetyErrorResponsePayload.builder().type(safetyErrorResponseType).message(str).build());
    }
}
