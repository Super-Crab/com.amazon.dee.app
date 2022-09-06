package com.amazon.alexa.rangecontroller.lib.model.serialization.type.response;

import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.response.Response;
import com.amazon.alexa.response.payload.ErrorResponsePayload;
/* loaded from: classes9.dex */
public final class ErrorResponse extends Response<NamespaceName, HeaderName, EmptyInstance, ErrorResponsePayload> {
    protected ErrorResponse(Event<NamespaceName, HeaderName, EmptyInstance, ErrorResponsePayload> event, Context context) {
        super(event, context);
    }
}
