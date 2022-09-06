package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

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
public final class LanguageCombinationListAdapter extends RecyclerView.Adapter<LanguageCombinationListItemViewHolder> implements DividerDecoration.DividerDecision {
    private final List<LanguageCombinationListItem> languageCombinationList = new ArrayList();
    private final OnItemClickListener<LanguageCombinationListItem> onItemClickListener;
    private final OnItemSelectionDetected onItemSelectionDetected;
    private LanguageCombinationListItem selectedItem;

    public LanguageCombinationListAdapter(OnItemClickListener<LanguageCombinationListItem> onItemClickListener, OnItemSelectionDetected onItemSelectionDetected) {
        this.onItemClickListener = onItemClickListener;
        this.onItemSelectionDetected = onItemSelectionDetected;
    }

    private void detectIfItemSelected() {
        for (LanguageCombinationListItem languageCombinationListItem : this.languageCombinationList) {
            if (languageCombinationListItem.isSelected()) {
                if (this.selectedItem == null) {
                    this.selectedItem = languageCombinationListItem;
                }
                notifyItemSelectionDetected(languageCombinationListItem);
                return;
            }
        }
    }

    private void notifyItemSelectionDetected(LanguageCombinationListItem languageCombinationListItem) {
        OnItemSelectionDetected onItemSelectionDetected = this.onItemSelectionDetected;
        if (onItemSelectionDetected == null || languageCombinationListItem == null) {
            return;
        }
        onItemSelectionDetected.onSelectionDetected(languageCombinationListItem);
    }

    private void setSelectedItem(@Nullable LanguageCombinationListItem languageCombinationListItem) {
        Iterator<LanguageCombinationListItem> it2 = this.languageCombinationList.iterator();
        while (it2.hasNext()) {
            LanguageCombinationListItem next = it2.next();
            next.setSelected(next == languageCombinationListItem);
        }
        this.selectedItem = languageCombinationListItem;
        notifyItemSelectionDetected(languageCombinationListItem);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        setSelectedItem(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.languageCombinationList.size();
    }

    public LanguageCombinationListItem getSelectedItem() {
        return this.selectedItem;
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$LanguageCombinationListAdapter(LanguageCombinationListItem languageCombinationListItem, View view) {
        setSelectedItem(languageCombinationListItem);
        OnItemClickListener<LanguageCombinationListItem> onItemClickListener = this.onItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClicked(languageCombinationListItem);
        }
    }

    public void set(List<LanguageCombinationListItem> list) {
        this.languageCombinationList.clear();
        this.languageCombinationList.addAll(list);
        notifyDataSetChanged();
        detectIfItemSelected();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration.DividerDecision
    public boolean shouldDrawDivider(int i) {
        return i != getItemCount() - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LanguageCombinationListItemViewHolder languageCombinationListItemViewHolder, int i) {
        final LanguageCombinationListItem languageCombinationListItem = this.languageCombinationList.get(i);
        languageCombinationListItemViewHolder.bind(languageCombinationListItem, new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationListAdapter$4K711RRGDQiKSxeNRycnpVtnCbk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguageCombinationListAdapter.this.lambda$onBindViewHolder$0$LanguageCombinationListAdapter(languageCombinationListItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public LanguageCombinationListItemViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LanguageCombinationListItemViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }
}
