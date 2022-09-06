package com.amazon.alexa.redesign.view.templates.listTemplate;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.templates.ListTemplateModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
/* loaded from: classes10.dex */
public class ListTemplateViewHolder extends BaseCardViewHolder<ListTemplateViewItem> {
    private final AnimationPlayer animationPlayer;
    private final Context context;
    private final Resources resources;

    public ListTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter, @NonNull AnimationPlayer animationPlayer) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_list_template, viewGroup, false), viewTypeFactory, homeMetricsRecorder, presenter, new int[]{R.id.slot0, R.id.slot1, R.id.slot2, R.id.slot3, R.id.slot4}, context);
        TemplateHelperUtil.scaleWithFont(this.itemView.findViewById(R.id.slot1), context, R.integer.amahc_list_template_mini_card_height);
        TemplateHelperUtil.scaleWithFont(this.itemView.findViewById(R.id.slot2), context, R.integer.amahc_list_template_mini_card_height);
        TemplateHelperUtil.scaleWithFont(this.itemView.findViewById(R.id.slot3), context, R.integer.amahc_list_template_mini_card_height);
        this.animationPlayer = animationPlayer;
        this.resources = context.getResources();
        this.context = context;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(ListTemplateViewItem listTemplateViewItem, int i, boolean z) {
        super.bind((ListTemplateViewHolder) listTemplateViewItem, i, z);
        View findViewById = this.itemView.findViewById(R.id.divider1);
        View findViewById2 = this.itemView.findViewById(R.id.divider2);
        findViewById.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral50));
        findViewById2.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral50));
        ListTemplateModel mo2390getModel = listTemplateViewItem.mo2390getModel();
        bindDots(mo2390getModel);
        if (BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            this.animationPlayer.playAnimation(mo2390getModel, this.itemView);
        }
    }
}
