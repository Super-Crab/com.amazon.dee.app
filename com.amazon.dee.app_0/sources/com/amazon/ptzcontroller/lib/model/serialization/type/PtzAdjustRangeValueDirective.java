package com.amazon.ptzcontroller.lib.model.serialization.type;

import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.RcAlexaDirective;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
/* loaded from: classes13.dex */
public class PtzAdjustRangeValueDirective extends RcAlexaDirective<CameraPtzInstance, AdjustRangeValuePayload> {
    protected PtzAdjustRangeValueDirective(Directive<NamespaceName, HeaderName, CameraPtzInstance, AdjustRangeValuePayload> directive) {
        super(directive);
    }
}
