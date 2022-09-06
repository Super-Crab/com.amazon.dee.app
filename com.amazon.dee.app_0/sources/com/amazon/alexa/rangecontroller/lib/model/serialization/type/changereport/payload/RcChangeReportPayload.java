package com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.payload;

import com.amazon.alexa.changereport.Change;
import com.amazon.alexa.changereport.payload.ChangeReportPayload;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.PropertyName;
import com.google.gson.JsonElement;
/* loaded from: classes9.dex */
public final class RcChangeReportPayload extends ChangeReportPayload<NamespaceName, PropertyName, EmptyInstance, JsonElement> {
    protected RcChangeReportPayload(Change<NamespaceName, PropertyName, EmptyInstance, JsonElement> change) {
        super(change);
    }
}
