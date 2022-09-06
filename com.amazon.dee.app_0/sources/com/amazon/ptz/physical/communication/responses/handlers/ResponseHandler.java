package com.amazon.ptz.physical.communication.responses.handlers;

import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.google.gson.JsonElement;
/* loaded from: classes13.dex */
public interface ResponseHandler {
    boolean canHandle(HeaderName headerName, NamespaceName namespaceName);

    void handle(JsonElement jsonElement);
}
