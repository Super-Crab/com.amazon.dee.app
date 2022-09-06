package com.amazon.alexa.redesign.view.templates.emptyStateTemplate;

import com.amazon.alexa.redesign.entity.templates.EmptyStateTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
/* loaded from: classes10.dex */
public class EmptyStateTemplateViewItem extends RecyclerViewItem {
    public EmptyStateTemplateViewItem(EmptyStateTemplateModel emptyStateTemplateModel) {
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.EmptyState;
    }
}
