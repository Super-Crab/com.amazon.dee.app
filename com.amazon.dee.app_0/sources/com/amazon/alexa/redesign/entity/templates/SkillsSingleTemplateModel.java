package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class SkillsSingleTemplateModel extends CardModel {
    public static final int NUM_OF_SLOTS = 5;
    public static final String SKILLS_SINGLE_TEMPLATE_TYPE = "SkillsSingle";

    public SkillsSingleTemplateModel(@NonNull JSONObject jSONObject, @NonNull List<ViewTypeModel> list) {
        super(jSONObject, list);
    }
}
