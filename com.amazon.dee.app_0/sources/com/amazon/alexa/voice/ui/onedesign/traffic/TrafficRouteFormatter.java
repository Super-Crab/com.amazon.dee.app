package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.content.Context;
import android.content.res.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.util.List;
/* loaded from: classes11.dex */
public class TrafficRouteFormatter implements RouteFormatter {
    private final Resources resources;

    public TrafficRouteFormatter(Context context) {
        this.resources = context.getResources();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.RouteFormatter
    public CharSequence format(CharSequence charSequence, List<? extends CharSequence> list) {
        int size = list.size();
        return size == 0 ? "" : size == 1 ? this.resources.getQuantityString(R.plurals.voice_ui_od_traffic_route, 1, list.get(0), charSequence) : this.resources.getQuantityString(R.plurals.voice_ui_od_traffic_route, 2, list.get(0), list.get(1), charSequence);
    }
}
