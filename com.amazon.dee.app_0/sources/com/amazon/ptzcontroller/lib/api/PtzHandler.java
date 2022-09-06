package com.amazon.ptzcontroller.lib.api;

import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import com.amazon.ptzcontroller.lib.model.serialization.type.PtzAdjustRangeValueDirective;
/* loaded from: classes13.dex */
public interface PtzHandler {
    void handle(PtzAdjustRangeValueDirective ptzAdjustRangeValueDirective, AdjustRangeValuePayload adjustRangeValuePayload);
}
