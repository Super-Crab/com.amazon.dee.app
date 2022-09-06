package com.amazon.alexa.voice.ui.onedesign.sports.update;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.header.SingleLineHeaderModel;
import com.amazon.alexa.voice.ui.onedesign.header.SingleLineHeaderViewHolder;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.OnCloseClickListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class SportsUpdateAdapter extends RecyclerView.Adapter<BindableViewHolder> {
    private static final int HEADER = 1;
    private static final int ITEM = 2;
    private final OnCloseClickListener closeClickListener;
    private final List<Object> itemList = new ArrayList();
    private final Locale locale;

    public SportsUpdateAdapter(OnCloseClickListener onCloseClickListener, Locale locale) {
        this.closeClickListener = onCloseClickListener;
        this.locale = locale;
    }

    public void add(Object obj) {
        this.itemList.add(obj);
        notifyItemInserted(this.itemList.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Object obj = this.itemList.get(i);
        if (obj instanceof GameModel) {
            return 2;
        }
        if (!(obj instanceof SingleLineHeaderModel)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Unknown item type ")));
        }
        return 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int i) {
        bindableViewHolder.bind(this.itemList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 1) {
            if (i == 2) {
                return new GameViewHolder(from, viewGroup, this.locale);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unknown view type ", i));
        }
        return new SingleLineHeaderViewHolder(from, viewGroup, this.closeClickListener);
    }
}
