package com.amazon.alexa.redesign.view.templates.carouselChipTemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.redesign.view.viewtypes.CarouselView;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselChipTemplateViewHolder extends BaseCardViewHolder<CarouselChipTemplateViewItem> {
    @VisibleForTesting
    final AnimationPlayer animationPlayer;
    CarouselView carouselView;

    public CarouselChipTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter, AnimationPlayer animationPlayer) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_template_chip_carousel, viewGroup, false), viewTypeFactory, homeMetricsRecorder, presenter, new int[]{R.id.slot0, R.id.slot1}, context);
        this.animationPlayer = animationPlayer;
        applyBackgroundColorToView(getCardForegroundView(), R.attr.mosaicBackground);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public ViewType bindSlot(ViewTypeModel viewTypeModel, Map<String, Object> map, CardModel cardModel) {
        ViewType bindSlot = super.bindSlot(viewTypeModel, map, cardModel);
        if (bindSlot instanceof CarouselView) {
            this.carouselView = (CarouselView) bindSlot;
        }
        return bindSlot;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }

    public void recordViewsForVisibleCarouselItems() {
        this.carouselView.recordViewsForVisibleCarouselItems();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(CarouselChipTemplateViewItem carouselChipTemplateViewItem, int i, boolean z) {
        super.bind((CarouselChipTemplateViewHolder) carouselChipTemplateViewItem, i, z);
        CarouselChipTemplateModel mo2390getModel = carouselChipTemplateViewItem.mo2390getModel();
        bindDots(mo2390getModel);
        if (BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            this.animationPlayer.playAnimation(mo2390getModel, this.itemView);
        }
    }
}
