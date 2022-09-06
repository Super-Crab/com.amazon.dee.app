package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
/* loaded from: classes10.dex */
public class CarouselChipView extends CarouselView {
    public CarouselChipView(Context context) {
        super(context);
    }

    public CarouselChipView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater, HomeMetricsRecorder homeMetricsRecorder) {
        super(context, viewGroup, layoutInflater, R.drawable.amahc_divider_chip_carousel, homeMetricsRecorder);
    }
}
