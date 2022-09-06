package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.onedesign.rv.RvAdapter;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class TtaConversationAdapter extends RvAdapter {
    private static final String TAG = "TtaConversationAdapter";
    private final TtaEventSender eventSender;

    public TtaConversationAdapter(TtaEventSender ttaEventSender) {
        this.eventSender = ttaEventSender;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void add(Object obj) {
        int i;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("add: ");
        outline107.append(obj.toString());
        outline107.toString();
        if (obj instanceof AlexaResponseTtaItem) {
            i = indexOf(obj);
            this.eventSender.sendEvent(TtaUiEvent.RESPONSE_MESSAGE_RENDER);
        } else {
            i = -1;
        }
        if (i != -1) {
            set(i, obj, true);
        } else {
            super.add((TtaConversationAdapter) obj, true);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void clear() {
        super.clear(true);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvArrayAdapter
    public void remove(Object obj) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Remove the TtaItemModel: ");
        outline107.append(obj.toString());
        outline107.toString();
        super.remove((TtaConversationAdapter) obj, true);
    }
}
