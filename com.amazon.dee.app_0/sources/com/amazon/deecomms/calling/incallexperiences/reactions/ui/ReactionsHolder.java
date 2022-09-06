package com.amazon.deecomms.calling.incallexperiences.reactions.ui;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.ui.ImageLoader;
import java.util.List;
/* loaded from: classes12.dex */
public class ReactionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @NonNull
    private final ImageLoader imageLoader;
    @NonNull
    private final ReactionsMenuPresenter presenter;
    private final ImageView reactionIconImageView;
    @NonNull
    private final List<EffectIcon> reactionIcons;

    public ReactionsHolder(@NonNull View view, @NonNull ReactionsMenuPresenter reactionsMenuPresenter, @NonNull ImageLoader imageLoader, @NonNull List<EffectIcon> list) {
        super(view);
        this.reactionIconImageView = (ImageView) view.findViewById(R.id.reactions_imageview);
        this.presenter = reactionsMenuPresenter;
        this.imageLoader = imageLoader;
        this.reactionIcons = list;
        this.reactionIconImageView.setOnClickListener(this);
    }

    public void bind(@NonNull EffectIcon effectIcon) {
        this.reactionIconImageView.setContentDescription(effectIcon.getName());
        if (effectIcon.isActive()) {
            this.imageLoader.loadImage(this.reactionIconImageView, effectIcon.getActiveReactionThumbnail());
            this.reactionIconImageView.setClickable(false);
        } else {
            this.imageLoader.loadImage(this.reactionIconImageView, effectIcon.getThumbnail());
            this.reactionIconImageView.setClickable(true);
        }
        this.reactionIconImageView.setVisibility(0);
    }

    @VisibleForTesting
    int getAdapterPositionInternal() {
        return getAdapterPosition();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int adapterPositionInternal = getAdapterPositionInternal();
        if (adapterPositionInternal != -1) {
            this.presenter.onEffectButtonTapped(this.reactionIcons.get(adapterPositionInternal));
        }
    }
}
