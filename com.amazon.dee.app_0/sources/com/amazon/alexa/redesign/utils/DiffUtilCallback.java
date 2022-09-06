package com.amazon.alexa.redesign.utils;

import androidx.recyclerview.widget.DiffUtil;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewItem;
import java.util.List;
/* loaded from: classes10.dex */
public class DiffUtilCallback extends DiffUtil.Callback {
    private final List<RecyclerViewItem> newItems;
    private final List<RecyclerViewItem> oldItems;

    public DiffUtilCallback(List<RecyclerViewItem> list, List<RecyclerViewItem> list2) {
        this.oldItems = list;
        this.newItems = list2;
    }

    private boolean isDomainCardPayloadSame(DomainCardTemplateModel domainCardTemplateModel, DomainCardTemplateModel domainCardTemplateModel2) {
        return domainCardTemplateModel.getCardPayload().toString().equals(domainCardTemplateModel2.getCardPayload().toString());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        RecyclerViewItem recyclerViewItem = this.oldItems.get(i);
        RecyclerViewItem recyclerViewItem2 = this.newItems.get(i2);
        CardModel mo2390getModel = recyclerViewItem.mo2390getModel();
        CardModel mo2390getModel2 = recyclerViewItem2.mo2390getModel();
        if ((mo2390getModel instanceof DomainCardTemplateModel) && (mo2390getModel2 instanceof DomainCardTemplateModel)) {
            DomainCardTemplateModel domainCardTemplateModel = (DomainCardTemplateModel) mo2390getModel2;
            if (isDomainCardPayloadSame((DomainCardTemplateModel) mo2390getModel, domainCardTemplateModel)) {
                return domainCardTemplateModel.getViewUpdateType().equals(Constants.VIEW_UPDATE_TYPE_SERVER);
            }
        }
        return (recyclerViewItem instanceof VoxIngressTemplateViewItem) && (recyclerViewItem2 instanceof VoxIngressTemplateViewItem) && !((VoxIngressTemplateViewItem) recyclerViewItem).shouldRebind((VoxIngressTemplateViewItem) recyclerViewItem2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        CardModel mo2390getModel = this.oldItems.get(i).mo2390getModel();
        CardModel mo2390getModel2 = this.newItems.get(i2).mo2390getModel();
        if (mo2390getModel == null || mo2390getModel2 == null) {
            return false;
        }
        return mo2390getModel.getDismissCardId().equals(mo2390getModel2.getDismissCardId());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        return this.newItems.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        return this.oldItems.size();
    }
}
