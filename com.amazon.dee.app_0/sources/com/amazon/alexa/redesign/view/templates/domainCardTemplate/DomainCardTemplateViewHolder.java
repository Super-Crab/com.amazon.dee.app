package com.amazon.alexa.redesign.view.templates.domainCardTemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.view.AnimationPlayer;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.registry.ViewProviderRegistry;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
/* loaded from: classes10.dex */
public class DomainCardTemplateViewHolder extends BaseCardViewHolder<DomainCardTemplateViewItem> {
    private final AnimationPlayer animationPlayer;
    private final CardView cardContainer;
    private ViewModule currentModule;
    private final EventCapture eventCapture;
    private final ViewProviderRegistry viewProviderRegistry;

    public DomainCardTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewProviderRegistry viewProviderRegistry, EventCapture eventCapture, HomeContract.Presenter presenter, AnimationPlayer animationPlayer, Context context) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_domain_card, viewGroup, false), presenter, context);
        this.cardContainer = (CardView) this.itemView.findViewById(R.id.domain_card_container);
        this.viewProviderRegistry = viewProviderRegistry;
        this.eventCapture = eventCapture;
        this.animationPlayer = animationPlayer;
        applyBackgroundColorToView(getCardForegroundView(), R.attr.mosaicBackground);
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
        if (this.cardContainer.getChildCount() != 0) {
            this.cardContainer.removeViewAt(0);
        }
        ViewModule viewModule = this.currentModule;
        if (viewModule != null) {
            viewModule.cleanUp();
        }
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(DomainCardTemplateViewItem domainCardTemplateViewItem, int i, boolean z) {
        super.bind((DomainCardTemplateViewHolder) domainCardTemplateViewItem, i, z);
        detach();
        this.currentModule = this.viewProviderRegistry.createViewModule(domainCardTemplateViewItem.getPayload(), this.eventCapture);
        ViewModule viewModule = this.currentModule;
        if (viewModule != null) {
            viewModule.prepare();
            this.cardContainer.addView(this.currentModule.getView());
        }
        scaleDomainCardMinHeight(this.itemView.findViewById(R.id.amahc_domain_card_min_height_view));
        this.animationPlayer.playAnimation(domainCardTemplateViewItem.mo2390getModel(), this.itemView);
    }
}
