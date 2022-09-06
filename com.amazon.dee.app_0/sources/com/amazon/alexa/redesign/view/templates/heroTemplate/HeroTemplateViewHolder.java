package com.amazon.alexa.redesign.view.templates.heroTemplate;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.templates.HeroCardTemplateModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
/* loaded from: classes10.dex */
public class HeroTemplateViewHolder extends BaseCardViewHolder<HeroTemplateViewItem> {
    private final AnimationPlayer animationPlayer;
    private final Resources resources;

    public HeroTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter, AnimationPlayer animationPlayer) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_hero_template, viewGroup, false), viewTypeFactory, homeMetricsRecorder, presenter, new int[]{R.id.slot0, R.id.slot1, R.id.slot2}, context);
        this.animationPlayer = animationPlayer;
        this.resources = context.getResources();
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(HeroTemplateViewItem heroTemplateViewItem, int i, boolean z) {
        super.bind((HeroTemplateViewHolder) heroTemplateViewItem, i, z);
        HeroCardTemplateModel mo2390getModel = heroTemplateViewItem.mo2390getModel();
        bindDots(mo2390getModel);
        if (BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            this.animationPlayer.playAnimation(mo2390getModel, this.itemView);
        }
        this.itemView.findViewById(R.id.slot1).setImportantForAccessibility(4);
    }
}
