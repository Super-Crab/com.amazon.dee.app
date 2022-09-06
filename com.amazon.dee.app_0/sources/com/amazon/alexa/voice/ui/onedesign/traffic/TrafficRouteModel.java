package com.amazon.alexa.voice.ui.onedesign.traffic;

import androidx.annotation.DrawableRes;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel
/* loaded from: classes11.dex */
public interface TrafficRouteModel {
    @DrawableRes
    int getIndicator();

    CharSequence getSubTitle();

    CharSequence getTitle();

    boolean isPrimary();
}
