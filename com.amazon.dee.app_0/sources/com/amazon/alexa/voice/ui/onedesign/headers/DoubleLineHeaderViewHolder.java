package com.amazon.alexa.voice.ui.onedesign.headers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.OnCloseClickListener;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class DoubleLineHeaderViewHolder extends BindableViewHolder<DoubleLineHeaderModel> {
    private final TextView subTitleView;
    private final TextView titleView;

    public DoubleLineHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, final OnCloseClickListener onCloseClickListener) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_header, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.title);
        this.subTitleView = (TextView) this.itemView.findViewById(R.id.subTitle);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.titleView, this.subTitleView);
        this.itemView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.headers.-$$Lambda$DoubleLineHeaderViewHolder$xojSGym_5JoW2TBz9K5g2oNf5Fs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnCloseClickListener.this.onCloseClicked();
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(DoubleLineHeaderModel doubleLineHeaderModel) {
        this.titleView.setText(doubleLineHeaderModel.getTitle());
        this.subTitleView.setText(doubleLineHeaderModel.getSubTitle());
    }
}
