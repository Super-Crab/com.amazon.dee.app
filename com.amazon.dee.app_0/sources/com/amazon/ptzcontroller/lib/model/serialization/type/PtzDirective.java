package com.amazon.ptzcontroller.lib.model.serialization.type;

import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.RcAlexaDirective;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.google.gson.JsonElement;
/* loaded from: classes13.dex */
public class PtzDirective extends RcAlexaDirective<CameraPtzInstance, JsonElement> {
    protected PtzDirective(Directive<NamespaceName, HeaderName, CameraPtzInstance, JsonElement> directive) {
        super(directive);
    }
}
