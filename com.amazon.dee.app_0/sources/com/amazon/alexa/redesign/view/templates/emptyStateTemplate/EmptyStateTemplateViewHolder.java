package com.amazon.alexa.redesign.view.templates.emptyStateTemplate;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class EmptyStateTemplateViewHolder extends BaseCardViewHolder<EmptyStateTemplateViewItem> {
    private final LottieAnimationView animationView;
    private final Context context;
    private final TextView line1;

    public EmptyStateTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, HomeContract.Presenter presenter) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_empty_state, viewGroup, false), presenter, context);
        this.animationView = (LottieAnimationView) this.itemView.findViewById(R.id.polar_bear);
        this.line1 = (TextView) this.itemView.findViewById(R.id.line1);
        this.context = context;
        styleText();
        bindText();
    }

    private void bindText() {
        Resources resources = this.context.getResources();
        this.line1.setText(GeneratedOutlineSupport1.outline75(resources.getString(R.string.amahc_cards_show_up_here), " ", resources.getString(R.string.amahc_pull_to_refresh)));
    }

    private void styleText() {
        this.line1.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_BOLD));
        this.line1.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(@NonNull EmptyStateTemplateViewItem emptyStateTemplateViewItem, int i, boolean z) {
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }
}
