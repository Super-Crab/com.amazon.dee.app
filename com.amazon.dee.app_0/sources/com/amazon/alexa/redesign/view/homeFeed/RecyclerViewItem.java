package com.amazon.alexa.redesign.view.homeFeed;

import android.graphics.Color;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.view.templates.TemplateType;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public abstract class RecyclerViewItem {
    protected static final String DEFAULT_BACKGROUND_COLOR = "#163954";
    protected CardModel model;
    private TemplateType templateType = TemplateType.UNKNOWN;

    public String getCardId() {
        return null;
    }

    public String getCardType() {
        return null;
    }

    public String getItemId(int i) {
        return null;
    }

    public JSONObject getMetrics(int i) {
        return null;
    }

    /* renamed from: getModel */
    public CardModel mo2390getModel() {
        return null;
    }

    public String getPublisher(int i) {
        return null;
    }

    public TemplateType getTemplateType() {
        return this.templateType;
    }

    public String getTitle() {
        return null;
    }

    public Map<String, Object> getTopLevelMetricsObject() {
        return null;
    }

    public int getViewBackgroundColor() {
        return Color.parseColor(DEFAULT_BACKGROUND_COLOR);
    }

    public boolean isDismissible() {
        return false;
    }
}
