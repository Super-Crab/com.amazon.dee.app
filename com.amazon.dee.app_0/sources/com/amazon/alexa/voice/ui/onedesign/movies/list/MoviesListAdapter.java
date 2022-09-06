package com.amazon.alexa.voice.ui.onedesign.movies.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class MoviesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LINK = 3;
    private static final int POSTER = 2;
    private final List<Object> items = new ArrayList();
    private OnMovieLinkClickListener onMovieLinkClickListener;
    private OnMoviePosterClickListener onMoviePosterClickListener;

    /* loaded from: classes11.dex */
    public interface OnMovieLinkClickListener {
        void onMovieLinkClicked(MoviesListLinkModel moviesListLinkModel);
    }

    /* loaded from: classes11.dex */
    public interface OnMoviePosterClickListener {
        void onMoviePosterClicked(MoviesListPosterModel moviesListPosterModel);
    }

    public void add(Object obj) {
        this.items.add(obj);
        notifyItemInserted(this.items.size() - 1);
    }

    public Object get(int i) {
        return this.items.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        if (obj instanceof MoviesListPosterModel) {
            return 2;
        }
        if (!(obj instanceof MoviesListLinkModel)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Unknown type of an item ")));
        }
        return 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == 2) {
            ((MoviesListPosterViewHolder) viewHolder).bind((MoviesListPosterModel) this.items.get(i), this.onMoviePosterClickListener);
        } else if (itemViewType != 3) {
        } else {
            ((MoviesListLinkViewHolder) viewHolder).bind((MoviesListLinkModel) this.items.get(i), this.onMovieLinkClickListener);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 2) {
            if (i == 3) {
                return new MoviesListLinkViewHolder(from, viewGroup);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unsupported viewType ", i));
        }
        return new MoviesListPosterViewHolder(from, viewGroup);
    }

    public void setOnMovieLinkClickListener(OnMovieLinkClickListener onMovieLinkClickListener) {
        this.onMovieLinkClickListener = onMovieLinkClickListener;
    }

    public void setOnMoviePosterClickListener(OnMoviePosterClickListener onMoviePosterClickListener) {
        this.onMoviePosterClickListener = onMoviePosterClickListener;
    }
}
