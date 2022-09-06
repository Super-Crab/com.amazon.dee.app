package com.amazon.alexa.rangecontroller.lib.model.serialization.type.directive;

import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.RcAlexaDirective;
import com.google.gson.JsonElement;
/* loaded from: classes9.dex */
public final class SimpleRcDirective extends RcAlexaDirective<String, JsonElement> {
    protected SimpleRcDirective(Directive<NamespaceName, HeaderName, String, JsonElement> directive) {
        super(directive);
    }
}
