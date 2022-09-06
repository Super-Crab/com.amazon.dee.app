package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.ThinkingStateLoadingView;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaThinkingStateViewHolder extends RvViewHolder {
    private final ThinkingStateLoadingView thinkingStateLoadingView;

    public TtaThinkingStateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_thinking_state_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.thinkingStateLoadingView = (ThinkingStateLoadingView) this.itemView.findViewById(R.id.thinking_state_indicator);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        this.thinkingStateLoadingView.setVisibility(0);
    }
}
