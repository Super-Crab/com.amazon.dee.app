package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.onedesign.rv.RvAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class TtaPillsAdapter extends RvAdapter {
    private static final String TAG = "TtaPillsAdapter";

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void clear() {
        super.clear(true);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void setItems(List<Object> list) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("adding items : ");
        outline107.append(list.size());
        outline107.toString();
        super.setItems(list, true);
    }
}
