package com.amazon.alexa.voice.ui.onedesign.tta.adapter;

import com.amazon.alexa.voice.ui.onedesign.rv.RvAdapter;
import java.util.List;
/* loaded from: classes11.dex */
public class TtaSuggestionsAdapter extends RvAdapter {
    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void clear() {
        super.clear(true);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void setItems(List<Object> list) {
        super.setItems(list, true);
    }
}
