package com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.payload;

import com.amazon.alexa.changereport.Change;
import com.amazon.alexa.changereport.payload.ChangeReportPayload;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.PropertyName;
import org.apache.commons.net.ntp.TimeStamp;
/* loaded from: classes9.dex */
public final class TimeStampChangeReportPayload extends ChangeReportPayload<NamespaceName, PropertyName, EmptyInstance, TimeStamp> {
    protected TimeStampChangeReportPayload(Change<NamespaceName, PropertyName, EmptyInstance, TimeStamp> change) {
        super(change);
    }
}
