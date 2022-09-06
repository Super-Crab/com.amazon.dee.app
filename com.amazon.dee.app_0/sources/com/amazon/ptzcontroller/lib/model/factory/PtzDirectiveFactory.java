package com.amazon.ptzcontroller.lib.model.factory;

import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import com.amazon.alexa.rangecontroller.lib.model.factory.RcDirectiveFactory;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
/* loaded from: classes13.dex */
public final class PtzDirectiveFactory {
    private PtzDirectiveFactory() {
    }

    public static AlexaDirective<NamespaceName, HeaderName, CameraPtzInstance, AdjustRangeValuePayload> forAdjustRangeValue(CameraPtzInstance cameraPtzInstance, double d, String str) {
        return RcDirectiveFactory.forAdjustRangeValue(cameraPtzInstance, d, str).create();
    }
}
