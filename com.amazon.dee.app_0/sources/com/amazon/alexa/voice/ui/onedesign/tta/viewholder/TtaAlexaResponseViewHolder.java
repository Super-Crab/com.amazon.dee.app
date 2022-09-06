package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaAlexaResponseViewHolder extends RvViewHolder {
    private final TextView alexaResponseTextView;

    public TtaAlexaResponseViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_alexa_response_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.alexaResponseTextView = (TextView) this.itemView.findViewById(R.id.alexa_response);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        this.alexaResponseTextView.setText(((AlexaResponseTtaItem) obj).getItemText());
    }
}
