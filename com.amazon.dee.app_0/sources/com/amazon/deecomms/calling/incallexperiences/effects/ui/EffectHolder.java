package com.amazon.deecomms.calling.incallexperiences.effects.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.ui.ImageLoader;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class EffectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final RelativeLayout activeEffectImageView;
    private final ImageView effectIconImageView;
    private final TextView effectIconTextView;
    @NonNull
    private final List<EffectIcon> effectIcons;
    @NonNull
    private final ImageLoader imageLoader;
    @NonNull
    private final EffectsMenuPresenter presenter;

    public EffectHolder(@NonNull View view, @NonNull EffectsMenuPresenter effectsMenuPresenter, @NonNull ImageLoader imageLoader, @NonNull List<EffectIcon> list) {
        super(view);
        this.effectIconImageView = (ImageView) view.findViewById(R.id.effects_imageview);
        this.activeEffectImageView = (RelativeLayout) view.findViewById(R.id.active_effects_imageview);
        this.effectIconTextView = (TextView) view.findViewById(R.id.effects_textview);
        this.presenter = effectsMenuPresenter;
        this.imageLoader = imageLoader;
        this.effectIcons = list;
        this.effectIconImageView.setOnClickListener(this);
    }

    public void bind(@Nullable EffectIcon effectIcon) {
        if (effectIcon != null) {
            String name = effectIcon.getName();
            this.effectIconTextView.setText(StringUtils.capitalize(name));
            this.effectIconImageView.setContentDescription(name);
            this.effectIconImageView.setBackgroundResource(0);
            this.effectIconImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (effectIcon.isActive()) {
                this.activeEffectImageView.setBackgroundResource(R.drawable.f_btn_effects_panel_focused);
                this.activeEffectImageView.setContentDescription(name);
            } else {
                this.activeEffectImageView.setBackgroundResource(0);
            }
            this.imageLoader.loadImage(this.effectIconImageView, effectIcon.getThumbnail());
            return;
        }
        this.effectIconImageView.setImageResource(R.drawable.ic_none_48dp);
        this.activeEffectImageView.setBackgroundResource(0);
        this.effectIconImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.effectIconImageView.setBackgroundResource(R.drawable.fiesta_selector_effect_menu_button_background);
        this.effectIconImageView.setContentDescription(this.itemView.getContext().getString(R.string.effects_menu_clear));
        this.effectIconTextView.setText(R.string.effects_menu_clear);
    }

    @VisibleForTesting
    int getAdapterPositionInternal() {
        return getAdapterPosition();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int adapterPositionInternal = getAdapterPositionInternal();
        if (adapterPositionInternal == 0) {
            this.presenter.onEffectButtonTapped(null);
        } else if (adapterPositionInternal == -1) {
        } else {
            this.presenter.onEffectButtonTapped(this.effectIcons.get(adapterPositionInternal - 1));
        }
    }
}
