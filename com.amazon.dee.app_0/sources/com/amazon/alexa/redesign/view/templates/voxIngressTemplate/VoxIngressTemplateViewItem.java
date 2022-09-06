package com.amazon.alexa.redesign.view.templates.voxIngressTemplate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
/* loaded from: classes10.dex */
public class VoxIngressTemplateViewItem extends RecyclerViewItem {
    private AlertBannerModel alertBannerModel;
    private final VoxIngressTemplateModel model;

    public VoxIngressTemplateViewItem(@NonNull VoxIngressTemplateModel voxIngressTemplateModel, @Nullable AlertBannerModel alertBannerModel) {
        this.model = voxIngressTemplateModel;
        this.alertBannerModel = alertBannerModel;
    }

    @Nullable
    public AlertBannerModel getOutageModel() {
        return this.alertBannerModel;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public TemplateType getTemplateType() {
        return TemplateType.VoxIngress;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    public Map<String, Object> getTopLevelMetricsObject() {
        return this.model.getTopLevelMetricsObject();
    }

    public boolean isAlertModelEqual(@Nullable AlertBannerModel alertBannerModel) {
        AlertBannerModel alertBannerModel2 = this.alertBannerModel;
        if (alertBannerModel2 == null) {
            return alertBannerModel == null;
        }
        return alertBannerModel2.isEqual(alertBannerModel);
    }

    public void setOutageModel(@Nullable AlertBannerModel alertBannerModel) {
        this.alertBannerModel = alertBannerModel;
    }

    public boolean shouldRebind(VoxIngressTemplateViewItem voxIngressTemplateViewItem) {
        VoxIngressTemplateModel mo2390getModel = voxIngressTemplateViewItem.mo2390getModel();
        AlertBannerModel outageModel = voxIngressTemplateViewItem.getOutageModel();
        if (!mo2390getModel.isShouldAnimate() && mo2390getModel.isOfflineMode() == mo2390getModel().isOfflineMode() && !mo2390getModel.isOfflineMode()) {
            return !isAlertModelEqual(outageModel);
        }
        return true;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem
    @NonNull
    /* renamed from: getModel  reason: collision with other method in class */
    public VoxIngressTemplateModel mo2390getModel() {
        return this.model;
    }
}
