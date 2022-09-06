package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.CardModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class VoxIngressTemplateModel extends CardModel {
    private static final String VOX_ACTION_TYPE = "VoxIngressAction";
    private static final String VOX_CONTENT_PROVIDER = "Vox";
    private static final String VOX_CONTENT_TYPE = "VoxIngress";
    private final int hintResId;
    private final Action onVoxClickAction;
    private boolean shouldAnimate = true;
    private boolean offlineMode = false;
    private boolean isSkeleton = false;
    private boolean showingRefreshPill = false;

    public VoxIngressTemplateModel(@NonNull Action action, int i) {
        this.onVoxClickAction = action;
        this.hintResId = i;
    }

    public int getHintResId() {
        return this.hintResId;
    }

    public Action getOnVoxClickAction() {
        return this.onVoxClickAction;
    }

    @Override // com.amazon.alexa.redesign.entity.CardModel
    public Map<String, Object> getTopLevelMetricsObject() {
        HashMap outline134 = GeneratedOutlineSupport1.outline134(JsonFields.ACTION_TYPE, VOX_ACTION_TYPE, "contentType", VOX_CONTENT_TYPE);
        outline134.put("contentProvider", VOX_CONTENT_PROVIDER);
        return outline134;
    }

    public boolean isOfflineMode() {
        return this.offlineMode;
    }

    public boolean isShouldAnimate() {
        return this.shouldAnimate;
    }

    public boolean isShowingRefreshPill() {
        return this.showingRefreshPill;
    }

    public boolean isSkeleton() {
        return this.isSkeleton;
    }

    public void setOfflineMode(boolean z) {
        this.offlineMode = z;
    }

    public void setShouldAnimate(boolean z) {
        this.shouldAnimate = z;
    }

    public void setShowingRefreshPill(boolean z) {
        this.showingRefreshPill = z;
    }

    public void setSkeleton(boolean z) {
        this.isSkeleton = z;
    }
}
