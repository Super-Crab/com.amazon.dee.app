package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaInChatLinkVewHolder extends RvViewHolder {
    private final ViewGroup inChatLinkView;
    private final LinearLayout primarySecondaryLayout;
    private final TextView primaryText1;
    private final TextView primaryText2;
    private final TextView secondText;

    public TtaInChatLinkVewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_in_chat_link, viewGroup, false), rvActionHandler, rvDataProvider);
        this.primaryText1 = (TextView) this.itemView.findViewById(R.id.in_chat_link_primary_text_1);
        this.primaryText2 = (TextView) this.itemView.findViewById(R.id.in_chat_link_primary_text_2);
        this.secondText = (TextView) this.itemView.findViewById(R.id.in_chat_link_second_text);
        this.primarySecondaryLayout = (LinearLayout) this.itemView.findViewById(R.id.primary_secondary_layout);
        this.inChatLinkView = (ViewGroup) this.itemView.findViewById(R.id.in_chat_link_message_row);
        this.inChatLinkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaInChatLinkVewHolder$v-eOYDiAB4fB6fPfcKloxg4JdLc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaInChatLinkVewHolder.this.lambda$new$0$TtaInChatLinkVewHolder(view);
            }
        });
    }

    private void resetViews() {
        this.primarySecondaryLayout.setVisibility(8);
        this.primaryText1.setVisibility(8);
    }

    private void setTextContent(InChatLinkTtaItem inChatLinkTtaItem) {
        resetViews();
        if (TextUtils.isEmpty(inChatLinkTtaItem.getSecondText())) {
            this.primaryText1.setVisibility(0);
            this.primaryText1.setText(inChatLinkTtaItem.getPrimaryText());
            return;
        }
        this.primarySecondaryLayout.setVisibility(0);
        this.primaryText2.setText(inChatLinkTtaItem.getPrimaryText());
        this.secondText.setText(inChatLinkTtaItem.getSecondText());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        InChatLinkTtaItem inChatLinkTtaItem = (InChatLinkTtaItem) obj;
        this.inChatLinkView.setBackground(DrawableUtils.getBackgroundForInChatViewHolder(getContext(), inChatLinkTtaItem.getItemPosition()));
        setTextContent(inChatLinkTtaItem);
    }

    public /* synthetic */ void lambda$new$0$TtaInChatLinkVewHolder(View view) {
        handleAction(1, this.inChatLinkView);
    }
}
