package com.amazon.alexa.rangecontroller.lib.model.factory;

import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.response.Response;
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
public class RcResponseFactory<NamespaceType, ResponseType, InstanceType, PayloadType> {
    private Context context;
    private Event<NamespaceType, ResponseType, InstanceType, PayloadType> event;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected RcResponseFactory() {
    }

    @NonNull
    public static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, ErrorResponsePayload> forErrorResponse(@Nonnull ErrorResponseType errorResponseType, @Nonnull String str, @Nonnull String str2) {
        return forErrorResponse(errorResponseType, str, str2, null);
    }

    @NonNull
    public static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, EmptyPayload> forResponse(@Nonnull String str) {
        return forResponse(str, null);
    }

    @NonNull
    public static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, SafetyErrorResponsePayload> forSafetyErrorResponse(@Nonnull SafetyErrorResponseType safetyErrorResponseType, @Nonnull String str, @Nonnull String str2) {
        return forSafetyErrorResponse(safetyErrorResponseType, str, str2, null);
    }

    @NonNull
    private static <NamespaceType, ResponseType, InstanceType, PayloadType> RcResponseFactory<NamespaceType, ResponseType, InstanceType, PayloadType> newInstance(Event<NamespaceType, ResponseType, InstanceType, PayloadType> event, Context context) {
        return new RcResponseFactory().event(event).context(context);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected RcResponseFactory<NamespaceType, ResponseType, InstanceType, PayloadType> context(Context context) {
        this.context = context;
        return this;
    }

    @NonNull
    public Response<NamespaceType, ResponseType, InstanceType, PayloadType> create() {
        return Response.builder().event(this.event).context(this.context).build();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected RcResponseFactory<NamespaceType, ResponseType, InstanceType, PayloadType> event(Event<NamespaceType, ResponseType, InstanceType, PayloadType> event) {
        this.event = event;
        return this;
    }

    @NonNull
    protected static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, ErrorResponsePayload> forErrorResponse(@Nonnull ErrorResponseType errorResponseType, @Nonnull String str, @Nonnull String str2, @CheckForNull UUID uuid) {
        return newInstance(RcEventFactory.forErrorResponse(errorResponseType, str, str2, uuid).create(), null);
    }

    @NonNull
    protected static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, EmptyPayload> forResponse(@Nonnull String str, @CheckForNull UUID uuid) {
        return newInstance(RcEventFactory.forResponse(str, uuid).create(), null);
    }

    @NonNull
    protected static RcResponseFactory<NamespaceName, HeaderName, EmptyInstance, SafetyErrorResponsePayload> forSafetyErrorResponse(@Nonnull SafetyErrorResponseType safetyErrorResponseType, @Nonnull String str, @Nonnull String str2, @CheckForNull UUID uuid) {
        return newInstance(RcEventFactory.forSafetyErrorResponse(safetyErrorResponseType, str, str2, uuid).create(), null);
    }
}
