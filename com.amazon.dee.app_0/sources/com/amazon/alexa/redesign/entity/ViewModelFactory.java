package com.amazon.alexa.redesign.entity;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.viewtypes.AlarmsTimersModel;
import com.amazon.alexa.redesign.entity.viewtypes.BorderedTextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselChipModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselGridModel;
import com.amazon.alexa.redesign.entity.viewtypes.EmptyModel;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.HeaderModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleHintModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageModel;
import com.amazon.alexa.redesign.entity.viewtypes.OptInBottomSheetModel;
import com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.TitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class ViewModelFactory {
    private final ActionFactory actionFactory;
    @VisibleForTesting
    final Map<String, Factory<ViewTypeModel>> viewModelFactories = new HashMap();

    /* loaded from: classes10.dex */
    public interface Factory<ReturnType> {
        ReturnType create(JSONObject jSONObject) throws JSONException, IllegalArgumentException;
    }

    public ViewModelFactory(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
        this.viewModelFactories.put(ImageModel.IMAGE_MODEL_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$8Cba_GqhoASuAeaBYe-6LHED47A
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$0$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(IconTitleSubtitleModel.ICON_TITLE_SUBTITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$4JGvpvuEOTKhce54oYpHxvdEzmk
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$1$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(ImageButtonModel.IMAGE_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$3xKXOVFHOoydh3i-DdMBb7oJLec
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$2$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(TextButtonModel.TEXT_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$caOGIdJuiLbAa9NcsHw44RH6z1U
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$3$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(BorderedTextButtonModel.BORDERED_TEXT_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$pZb0LpCuSVEwl5qW0V1SPkJWFCY
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$4$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(EmptyModel.EMPTY_MODEL, $$Lambda$ViewModelFactory$Jpeu17RNwmFzzLT2usuAgKQRSDE.INSTANCE);
        this.viewModelFactories.put(TitleSubtitleModel.TITLE_SUBTITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$LQVLMnLGPgdlnfK7V2n_IrKUZwg
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$6$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(IconTitleSubtitleModel.ICON_TITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$xV35E8OUcxo6JTdGPrczZ548wNI
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$7$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(AlarmsTimersModel.ALARMS_TIMERS_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$ZDwbyv11otCB5kV73MzBh9I8sY8
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$8$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(IconTitleSubtitleHintModel.ICON_TITLE_SUBTITLE_HINT_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$EDsRDSrcJR-k_T3Hd7lqPfoZM4M
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$9$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(HeaderModel.HEADER_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$ER0nMOaEzWQ3CPu-9tCFJ8SD0mg
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$10$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(ChipIconTitleModel.CHIP_ICON_TITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$QMmmI0Xz6lXPvPBjqnOn3JnBwhk
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$11$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(ChipIconTitleModel.CHIP_TITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$qrdkf5OaM9XHqMywSq-2Zyrg4jc
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$12$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(CarouselChipModel.CAROUSEL_CHIP_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$IwzTgl7uDKzCn36RGnk8xEds9-c
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$13$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(GridIconTitleModel.GRID_ICON_TITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$2O4yPBSN_A7vOVHQityM7IYJfCs
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$14$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(CarouselGridModel.CAROUSEL_GRID_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$zZXEX_7PbK-RxAfZtgX8MyodFFM
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$15$ViewModelFactory(jSONObject);
            }
        });
        this.viewModelFactories.put(OptInBottomSheetModel.OPT_IN_BOTTOM_SHEET_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.entity.-$$Lambda$ViewModelFactory$LawufSIMJgTZRccDriOR68WWyAk
            @Override // com.amazon.alexa.redesign.entity.ViewModelFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ViewModelFactory.this.lambda$new$16$ViewModelFactory(jSONObject);
            }
        });
    }

    public ViewTypeModel getModel(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        Factory<ViewTypeModel> factory = this.viewModelFactories.get(ViewModelFactoryUtil.getViewType(jSONObject));
        if (factory != null) {
            return factory.create(jSONObject);
        }
        throw new IllegalArgumentException("HomeChannelNative: ViewType is not in ViewModelFactoryMap");
    }

    public /* synthetic */ ViewTypeModel lambda$new$0$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getImageModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$1$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getIconTitleSubtitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$10$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getHeaderModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$11$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getChipIconTitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$12$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getChipIconTitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$13$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getCarouselChipModel(jSONObject, this.actionFactory, this);
    }

    public /* synthetic */ ViewTypeModel lambda$new$14$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getGridIconTitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$15$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getCarouselGridModel(jSONObject, this.actionFactory, this);
    }

    public /* synthetic */ ViewTypeModel lambda$new$16$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getOptInBottomSheetModel(jSONObject, this);
    }

    public /* synthetic */ ViewTypeModel lambda$new$2$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getImageButtonModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$3$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getTextButtonModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$4$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getBorderedTextButtonModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$6$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getTitleSubtitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$7$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getIconTitleSubtitleModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$8$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getAlarmsTimersModel(jSONObject, this.actionFactory);
    }

    public /* synthetic */ ViewTypeModel lambda$new$9$ViewModelFactory(JSONObject jSONObject) throws JSONException, IllegalArgumentException {
        return ViewModelFactoryUtil.getSkillsModel(jSONObject, this.actionFactory);
    }
}
