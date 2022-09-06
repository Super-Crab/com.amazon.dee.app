package com.amazon.alexa.redesign.entity.templates;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class CarouselGridTemplateModel extends CardModel {
    public static final String CAROUSEL_GRID_TEMPLATE_TYPE = "CarouselGridTemplate";
    public static final int NUM_OF_SLOTS = 2;

    public CarouselGridTemplateModel(@NonNull JSONObject jSONObject, @NonNull List<ViewTypeModel> list) {
        super(jSONObject, list);
    }
}
