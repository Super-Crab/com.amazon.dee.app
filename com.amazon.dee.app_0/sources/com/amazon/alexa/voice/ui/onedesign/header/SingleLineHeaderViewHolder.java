package com.amazon.alexa.voice.ui.onedesign.header;

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
public final class SingleLineHeaderViewHolder extends BindableViewHolder<SingleLineHeaderModel> {
    private final TextView titleView;

    public SingleLineHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, final OnCloseClickListener onCloseClickListener) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_header, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.title);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.titleView);
        this.itemView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.header.-$$Lambda$SingleLineHeaderViewHolder$WN2K84VGeTXRy8I1kCvRpgDfLSc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnCloseClickListener.this.onCloseClicked();
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(SingleLineHeaderModel singleLineHeaderModel) {
        this.titleView.setText(singleLineHeaderModel.getTitle());
    }
}
