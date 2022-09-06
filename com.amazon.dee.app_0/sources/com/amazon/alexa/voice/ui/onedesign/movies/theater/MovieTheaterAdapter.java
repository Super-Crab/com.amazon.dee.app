package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class MovieTheaterAdapter extends RecyclerView.Adapter<MovieTheaterViewHolder> {
    private static final int TIME = 1;
    private final List<Object> items = new ArrayList();
    private final Locale locale;

    public MovieTheaterAdapter(Locale locale) {
        this.locale = locale;
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
        if (obj instanceof MovieTheaterTimeModel) {
            return 1;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Unknown item type: ")));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MovieTheaterViewHolder movieTheaterViewHolder, int i) {
        movieTheaterViewHolder.bind(this.items.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public MovieTheaterViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new MovieTheaterTimeViewHolder(from, viewGroup, this.locale, DateFormat.is24HourFormat(viewGroup.getContext()));
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unknown item view type: ", i));
    }
}
