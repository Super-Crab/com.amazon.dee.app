package com.amazon.alexa.redesign.view.templates.skillsSingleTemplate;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.templates.SkillsSingleTemplateModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
/* loaded from: classes10.dex */
public class SkillsSingleTemplateViewHolder extends BaseCardViewHolder<SkillsSingleTemplateViewItem> {
    private final AnimationPlayer animationPlayer;
    private final Resources resources;

    public SkillsSingleTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter, AnimationPlayer animationPlayer) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_single_skill, viewGroup, false), viewTypeFactory, homeMetricsRecorder, presenter, new int[]{R.id.slot0, R.id.slot1, R.id.slot2, R.id.slot3, R.id.slot4}, context);
        this.animationPlayer = animationPlayer;
        this.resources = context.getResources();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bindDots(CardModel cardModel) {
        super.bindDots(cardModel);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.dots.getLayoutParams();
        layoutParams.topMargin = 0;
        this.dots.setLayoutParams(layoutParams);
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(SkillsSingleTemplateViewItem skillsSingleTemplateViewItem, int i, boolean z) {
        super.bind((SkillsSingleTemplateViewHolder) skillsSingleTemplateViewItem, i, z);
        this.itemView.findViewById(R.id.icon).setPadding(0, (int) this.resources.getDimension(R.dimen.amahc_padding_extra_small), 0, 0);
        if (this.itemView.findViewById(R.id.slot2).getVisibility() == 8) {
            View findViewById = this.itemView.findViewById(R.id.slot1_slot2_space);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.height = (int) this.resources.getDimension(R.dimen.amahc_height_small_12);
            findViewById.setLayoutParams(layoutParams);
        }
        SkillsSingleTemplateModel mo2390getModel = skillsSingleTemplateViewItem.mo2390getModel();
        bindDots(mo2390getModel);
        if (BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            this.animationPlayer.playAnimation(mo2390getModel, this.itemView);
        }
        this.itemView.findViewById(R.id.slot2).setImportantForAccessibility(4);
    }
}
