package com.amazon.alexa.rangecontroller.lib.model.serialization.type.response;

import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.response.Response;
import com.amazon.alexa.response.payload.SafetyErrorResponsePayload;
/* loaded from: classes9.dex */
public final class SafetyErrorResponse extends Response<NamespaceName, HeaderName, EmptyInstance, SafetyErrorResponsePayload> {
    protected SafetyErrorResponse(Event<NamespaceName, HeaderName, EmptyInstance, SafetyErrorResponsePayload> event, Context context) {
        super(event, context);
    }
}
