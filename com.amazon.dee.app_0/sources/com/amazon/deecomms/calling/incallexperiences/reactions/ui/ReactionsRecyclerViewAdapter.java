package com.amazon.deecomms.calling.incallexperiences.reactions.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.ui.ImageLoader;
import java.util.List;
/* loaded from: classes12.dex */
public class ReactionsRecyclerViewAdapter extends RecyclerView.Adapter<ReactionsHolder> {
    @NonNull
    private final ImageLoader imageLoader;
    @NonNull
    private final List<EffectIcon> reactionIcons;
    @NonNull
    private final ReactionsMenuPresenter reactionsMenuPresenter;

    public ReactionsRecyclerViewAdapter(@NonNull ReactionsMenuPresenter reactionsMenuPresenter, @NonNull ImageLoader imageLoader, @NonNull List<EffectIcon> list) {
        this.reactionsMenuPresenter = reactionsMenuPresenter;
        this.imageLoader = imageLoader;
        this.reactionIcons = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.reactionIcons.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ReactionsHolder reactionsHolder, int i) {
        reactionsHolder.bind(this.reactionIcons.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public ReactionsHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ReactionsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fiesta_reactions_layout, viewGroup, false), this.reactionsMenuPresenter, this.imageLoader, this.reactionIcons);
    }
}
