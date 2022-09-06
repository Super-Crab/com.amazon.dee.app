package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class HeroCardTemplateModel extends CardModel {
    public static final String HERO_CARD_TEMPLATE_TYPE = "HeroTemplate";
    public static final int NUM_OF_SLOTS = 3;

    public HeroCardTemplateModel(@NonNull JSONObject jSONObject, @NonNull List<ViewTypeModel> list) {
        super(jSONObject, list);
    }
}
