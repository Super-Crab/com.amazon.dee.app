package com.amazon.alexa.rangecontroller.lib.model.serialization.type.directive;

import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.RcAlexaDirective;
/* loaded from: classes9.dex */
public class AdjustRangeValueDirective<T> extends RcAlexaDirective<T, AdjustRangeValuePayload> {
    protected AdjustRangeValueDirective(Directive<NamespaceName, HeaderName, T, AdjustRangeValuePayload> directive) {
        super(directive);
    }
}
