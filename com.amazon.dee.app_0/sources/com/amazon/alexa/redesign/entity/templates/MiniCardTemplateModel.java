package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class MiniCardTemplateModel extends CardModel {
    public static final String MINI_CARD_TEMPLATE_TYPE = "MiniTemplate";
    public static final int NUM_OF_SLOTS = 2;
    private final boolean isSkeleton;
    private final boolean shouldTriggerCacheLoad;

    public MiniCardTemplateModel(@NonNull JSONObject jSONObject, @NonNull List<ViewTypeModel> list) {
        super(jSONObject, list);
        this.isSkeleton = false;
        this.shouldTriggerCacheLoad = false;
    }

    public boolean isShouldTriggerCacheLoad() {
        return this.shouldTriggerCacheLoad;
    }

    public boolean isSkeleton() {
        return this.isSkeleton;
    }

    public MiniCardTemplateModel(@NonNull JSONObject jSONObject, @NonNull List<ViewTypeModel> list, boolean z, boolean z2) {
        super(jSONObject, list);
        this.isSkeleton = z;
        this.shouldTriggerCacheLoad = z2;
    }
}
