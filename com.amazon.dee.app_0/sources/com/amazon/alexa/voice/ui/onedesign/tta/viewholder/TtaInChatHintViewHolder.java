package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.cards.util.StringUtils;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaInChatHintViewHolder extends RvViewHolder {
    private final TextView hintText;
    private final ViewGroup inChatHintView;

    public TtaInChatHintViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_in_chat_hint, viewGroup, false), rvActionHandler, rvDataProvider);
        this.inChatHintView = (ViewGroup) this.itemView.findViewById(R.id.in_chat_hint_message_row);
        this.hintText = (TextView) this.itemView.findViewById(R.id.in_chat_hint_alexa_response);
        this.inChatHintView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaInChatHintViewHolder$4wYk15OTrB-_Tc4FAtSWpRuygJM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaInChatHintViewHolder.this.lambda$new$0$TtaInChatHintViewHolder(view);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        InChatHintTtaItem inChatHintTtaItem = (InChatHintTtaItem) obj;
        this.inChatHintView.setBackground(DrawableUtils.getBackgroundForInChatViewHolder(getContext(), inChatHintTtaItem.getItemPosition()));
        this.hintText.setText(StringUtils.quoteText(inChatHintTtaItem.getItemText()));
    }

    public /* synthetic */ void lambda$new$0$TtaInChatHintViewHolder(View view) {
        handleAction(1, this.inChatHintView);
    }
}
