package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
/* loaded from: classes10.dex */
public class CarouselGridView extends CarouselView {
    public CarouselGridView(Context context) {
        super(context);
    }

    public CarouselGridView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater, HomeMetricsRecorder homeMetricsRecorder) {
        super(context, viewGroup, layoutInflater, R.drawable.amahc_divider_grid_carousel, homeMetricsRecorder);
    }
}
