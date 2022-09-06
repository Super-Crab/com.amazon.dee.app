package com.amazon.alexa.rangecontroller.lib.model.serialization.type;

import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
/* loaded from: classes9.dex */
public class RcAlexaDirective<T, U> extends AlexaDirective<NamespaceName, HeaderName, T, U> {
    /* JADX INFO: Access modifiers changed from: protected */
    public RcAlexaDirective(Directive<NamespaceName, HeaderName, T, U> directive) {
        super(directive);
    }
}
