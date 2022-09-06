package com.amazonaws.services.s3.model.transform;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.services.s3.model.FilterRule;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
class FilterRuleStaxUnmarshaller implements Unmarshaller<FilterRule, StaxUnmarshallerContext> {
    private static FilterRuleStaxUnmarshaller instance = new FilterRuleStaxUnmarshaller();

    private FilterRuleStaxUnmarshaller() {
    }

    public static FilterRuleStaxUnmarshaller getInstance() {
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public FilterRule unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i += 2;
        }
        FilterRule filterRule = new FilterRule();
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                return filterRule;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression(MAPCookie.KEY_NAME, i)) {
                    filterRule.setName(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression(MAPCookie.KEY_VALUE, i)) {
                    filterRule.setValue(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return filterRule;
            }
        }
    }
}
