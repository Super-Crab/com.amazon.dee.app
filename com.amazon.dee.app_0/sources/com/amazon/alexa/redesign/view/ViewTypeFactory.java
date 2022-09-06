package com.amazon.alexa.redesign.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.entity.viewtypes.AlarmsTimersModel;
import com.amazon.alexa.redesign.entity.viewtypes.BorderedTextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselChipModel;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselGridModel;
import com.amazon.alexa.redesign.entity.viewtypes.HeaderModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleHintModel;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.ImageModel;
import com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.TitleSubtitleModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.viewtypes.AlarmsTimersView;
import com.amazon.alexa.redesign.view.viewtypes.BorderedTextButtonView;
import com.amazon.alexa.redesign.view.viewtypes.CarouselChipView;
import com.amazon.alexa.redesign.view.viewtypes.CarouselGridView;
import com.amazon.alexa.redesign.view.viewtypes.HeaderView;
import com.amazon.alexa.redesign.view.viewtypes.IconTitleSubtitleHintView;
import com.amazon.alexa.redesign.view.viewtypes.IconTitleSubtitleView;
import com.amazon.alexa.redesign.view.viewtypes.ImageButtonView;
import com.amazon.alexa.redesign.view.viewtypes.ImageView;
import com.amazon.alexa.redesign.view.viewtypes.TextButtonView;
import com.amazon.alexa.redesign.view.viewtypes.TitleSubtitleView;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class ViewTypeFactory {
    @VisibleForTesting
    final Map<String, Factory<ViewType>> factoryMap = new HashMap();

    /* loaded from: classes10.dex */
    public interface Factory<ReturnType> {
        ReturnType create(LinearLayout linearLayout);
    }

    public ViewTypeFactory(final Context context, final LayoutInflater layoutInflater, final HomeMetricsRecorder homeMetricsRecorder) {
        this.factoryMap.put(ImageModel.IMAGE_MODEL_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$pf7WjaBiJBS4Up-KPYwzVbifqqQ
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new ImageView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(IconTitleSubtitleModel.ICON_TITLE_SUBTITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$-VKTBYcq1fZ2hG3MzPHjEc1Eov0
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new IconTitleSubtitleView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(AlarmsTimersModel.ALARMS_TIMERS_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$hETlE3xox7QI2ZPum4REa4_KIpw
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new AlarmsTimersView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(TextButtonModel.TEXT_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$IV0TvzTJ8ltRyunSD4BNtOHTzTw
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new TextButtonView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(ImageButtonModel.IMAGE_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$Bok2zM13OO_oDCn8i2WiLAFeDvg
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new ImageButtonView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(TitleSubtitleModel.TITLE_SUBTITLE_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$tKX5yS4q8Koe8Gjp5Mu16BNxnn4
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new TitleSubtitleView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(BorderedTextButtonModel.BORDERED_TEXT_BUTTON_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$xxcMD0pFYOqnwPkB5HowB4nfnX8
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new BorderedTextButtonView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(IconTitleSubtitleHintModel.ICON_TITLE_SUBTITLE_HINT_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$KLYI_FDaf7xEe-Di56JLsflXryM
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new IconTitleSubtitleHintView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(HeaderModel.HEADER_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$9kXZOXrPcq1Lclut0jAg13kIK5k
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new HeaderView(context, linearLayout, layoutInflater);
            }
        });
        this.factoryMap.put(CarouselChipModel.CAROUSEL_CHIP_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$JtN_kVb_h3zrXEMzuplx-AajL2U
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new CarouselChipView(context, linearLayout, layoutInflater, homeMetricsRecorder);
            }
        });
        this.factoryMap.put(CarouselGridModel.CAROUSEL_GRID_TYPE, new Factory() { // from class: com.amazon.alexa.redesign.view.-$$Lambda$ViewTypeFactory$XP8na_HGnUg87M5ExbeuRwhOA1g
            @Override // com.amazon.alexa.redesign.view.ViewTypeFactory.Factory
            public final Object create(LinearLayout linearLayout) {
                return new CarouselGridView(context, linearLayout, layoutInflater, homeMetricsRecorder);
            }
        });
    }

    static /* synthetic */ ViewType lambda$new$0(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new ImageView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$1(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new IconTitleSubtitleView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$10(Context context, LayoutInflater layoutInflater, HomeMetricsRecorder homeMetricsRecorder, LinearLayout linearLayout) {
        return new CarouselGridView(context, linearLayout, layoutInflater, homeMetricsRecorder);
    }

    static /* synthetic */ ViewType lambda$new$2(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new AlarmsTimersView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$3(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new TextButtonView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$4(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new ImageButtonView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$5(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new TitleSubtitleView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$6(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new BorderedTextButtonView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$7(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new IconTitleSubtitleHintView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$8(Context context, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        return new HeaderView(context, linearLayout, layoutInflater);
    }

    static /* synthetic */ ViewType lambda$new$9(Context context, LayoutInflater layoutInflater, HomeMetricsRecorder homeMetricsRecorder, LinearLayout linearLayout) {
        return new CarouselChipView(context, linearLayout, layoutInflater, homeMetricsRecorder);
    }

    public ViewType getViewType(String str, LinearLayout linearLayout) {
        return this.factoryMap.get(str).create(linearLayout);
    }
}
