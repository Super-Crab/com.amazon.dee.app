package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.amazon.alexa.voice.ui.onedesign.widget.OnItemClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public final class LanguageListAdapter extends RecyclerView.Adapter<LanguageListItemViewHolder> implements DividerDecoration.DividerDecision {
    private final List<LanguageListItem> languageList = new ArrayList();
    private final OnItemClickListener<LanguageListItem> onItemClickListener;
    private final OnItemSelectionDetected onItemSelectionDetected;
    private LanguageListItem selectedItem;

    public LanguageListAdapter(OnItemClickListener<LanguageListItem> onItemClickListener, OnItemSelectionDetected onItemSelectionDetected) {
        this.onItemClickListener = onItemClickListener;
        this.onItemSelectionDetected = onItemSelectionDetected;
    }

    private void detectIfItemSelected() {
        for (LanguageListItem languageListItem : this.languageList) {
            if (languageListItem.isSelected()) {
                if (this.selectedItem == null) {
                    this.selectedItem = languageListItem;
                }
                notifyItemSelectionDetected(languageListItem);
                return;
            }
        }
    }

    private void notifyItemSelectionDetected(LanguageListItem languageListItem) {
        OnItemSelectionDetected onItemSelectionDetected = this.onItemSelectionDetected;
        if (onItemSelectionDetected == null || languageListItem == null) {
            return;
        }
        onItemSelectionDetected.onSelectionDetected(languageListItem);
    }

    private void setSelectedItem(@Nullable LanguageListItem languageListItem) {
        Iterator<LanguageListItem> it2 = this.languageList.iterator();
        while (it2.hasNext()) {
            LanguageListItem next = it2.next();
            next.setSelected(next == languageListItem);
        }
        this.selectedItem = languageListItem;
        notifyItemSelectionDetected(languageListItem);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        setSelectedItem(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.languageList.size();
    }

    public LanguageListItem getSelectedItem() {
        return this.selectedItem;
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$LanguageListAdapter(LanguageListItem languageListItem, View view) {
        setSelectedItem(languageListItem);
        OnItemClickListener<LanguageListItem> onItemClickListener = this.onItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClicked(languageListItem);
        }
    }

    public void set(List<LanguageListItem> list) {
        this.languageList.clear();
        this.languageList.addAll(list);
        notifyDataSetChanged();
        detectIfItemSelected();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration.DividerDecision
    public boolean shouldDrawDivider(int i) {
        return i != getItemCount() - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LanguageListItemViewHolder languageListItemViewHolder, int i) {
        final LanguageListItem languageListItem = this.languageList.get(i);
        languageListItemViewHolder.bind(languageListItem, new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguageListAdapter$GVSlCvC0xENkCUntgHhAXTV2ZNM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguageListAdapter.this.lambda$onBindViewHolder$0$LanguageListAdapter(languageListItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public LanguageListItemViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LanguageListItemViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }
}
