package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaCardViewHolder extends RvViewHolder {
    private final RelativeLayout cardView;
    private final TextView source;
    private final TextView title;
    private final TextView titleCenter;

    public TtaCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_tta_card_view_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.title = (TextView) this.itemView.findViewById(R.id.card_title);
        this.titleCenter = (TextView) this.itemView.findViewById(R.id.card_title_center);
        this.source = (TextView) this.itemView.findViewById(R.id.card_description);
        this.cardView = (RelativeLayout) this.itemView.findViewById(R.id.card_view);
        this.cardView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaCardViewHolder$2RIKkz3CUSO0bhhewx0mDDGOuoM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaCardViewHolder.this.lambda$new$0$TtaCardViewHolder(view);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        CardTtaItem cardTtaItem = (CardTtaItem) obj;
        String itemDescription = cardTtaItem.getItemDescription();
        if (TextUtils.isEmpty(itemDescription)) {
            this.titleCenter.setText(cardTtaItem.getItemText());
            this.titleCenter.setVisibility(0);
            this.title.setVisibility(8);
            this.source.setVisibility(8);
            return;
        }
        this.title.setText(cardTtaItem.getItemText());
        this.titleCenter.setVisibility(8);
        this.title.setVisibility(0);
        this.source.setText(itemDescription);
        this.source.setVisibility(0);
    }

    public /* synthetic */ void lambda$new$0$TtaCardViewHolder(View view) {
        handleAction(1, this.cardView);
    }
}
