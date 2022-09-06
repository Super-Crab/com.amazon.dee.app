package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class ShoppingAdapter extends RecyclerView.Adapter<BindableViewHolder> {
    private final List<Object> itemList = new ArrayList();
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;

    public ShoppingAdapter(Locale locale, CardMetricsInteractor cardMetricsInteractor) {
        this.locale = locale;
        this.metricsInteractor = cardMetricsInteractor;
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
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int i) {
        bindableViewHolder.bind(this.itemList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ShoppingItemViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup, this.locale, this.metricsInteractor);
    }
}
