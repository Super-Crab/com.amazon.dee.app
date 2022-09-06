package com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport;

import com.amazon.alexa.changereport.ChangeReport;
import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.payload.TimeStampChangeReportPayload;
/* loaded from: classes9.dex */
public final class TimeStampChangeReport extends ChangeReport<NamespaceName, HeaderName, EmptyInstance, TimeStampChangeReportPayload> {
    protected TimeStampChangeReport(Event<NamespaceName, HeaderName, EmptyInstance, TimeStampChangeReportPayload> event, Context context) {
        super(event, context);
    }
}
