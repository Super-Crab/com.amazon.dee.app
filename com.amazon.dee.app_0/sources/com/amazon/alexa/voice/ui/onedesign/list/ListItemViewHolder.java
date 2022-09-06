package com.amazon.alexa.voice.ui.onedesign.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class ListItemViewHolder extends BindableViewHolder<ListItemModel> {
    private final TextView nameView;

    public ListItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_list_item, viewGroup, false));
        this.nameView = (TextView) this.itemView.findViewById(R.id.name);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_LIGHT, this.nameView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(ListItemModel listItemModel) {
        this.nameView.setText(listItemModel.getName());
    }
}
