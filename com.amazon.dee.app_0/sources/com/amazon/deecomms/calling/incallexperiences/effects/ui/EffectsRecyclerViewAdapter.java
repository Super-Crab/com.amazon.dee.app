package com.amazon.deecomms.calling.incallexperiences.effects.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.ui.ImageLoader;
import java.util.List;
/* loaded from: classes12.dex */
public class EffectsRecyclerViewAdapter extends RecyclerView.Adapter<EffectHolder> {
    private static final CommsLogger LOG = CommsLogger.getLogger(EffectsBottomSheetDialogFragmentView.class);
    @NonNull
    private final List<EffectIcon> effectIcons;
    @NonNull
    private final EffectsMenuPresenter effectsMenuPresenter;
    @NonNull
    private final ImageLoader imageLoader;

    public EffectsRecyclerViewAdapter(@NonNull EffectsMenuPresenter effectsMenuPresenter, @NonNull ImageLoader imageLoader, @NonNull List<EffectIcon> list) {
        this.effectsMenuPresenter = effectsMenuPresenter;
        this.imageLoader = imageLoader;
        this.effectIcons = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.effectIcons.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(EffectHolder effectHolder, int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            effectHolder.bind(this.effectIcons.get(i2));
            return;
        }
        LOG.i("[SEPIA] Setting Effect Icon image to None for position: " + i);
        effectHolder.bind(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public EffectHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new EffectHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fiesta_effects_layout, viewGroup, false), this.effectsMenuPresenter, this.imageLoader, this.effectIcons);
    }
}
