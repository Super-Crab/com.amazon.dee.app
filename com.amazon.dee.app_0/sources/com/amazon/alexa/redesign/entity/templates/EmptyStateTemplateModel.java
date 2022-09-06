package com.amazon.alexa.redesign.entity.templates;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.redesign.entity.CardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class EmptyStateTemplateModel extends CardModel {
    private static final String EMPTY_STATE = "EmptyState";
    private static final String EMPTY_STATE_ACTION = "EmptyStateAction";

    @Override // com.amazon.alexa.redesign.entity.CardModel
    public Map<String, Object> getTopLevelMetricsObject() {
        HashMap outline134 = GeneratedOutlineSupport1.outline134(JsonFields.ACTION_TYPE, EMPTY_STATE_ACTION, "contentType", EMPTY_STATE);
        outline134.put("contentProvider", EMPTY_STATE);
        return outline134;
    }

    @Override // com.amazon.alexa.redesign.entity.CardModel
    public boolean isDismissible() {
        return false;
    }
}
