package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface TrafficCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface RouteModel extends Parcelable {
        public static final int CONDITION_GOOD = 0;
        public static final int CONDITION_SLOW = 2;
        public static final int CONDITION_SLUGGISH = 1;

        int getCondition();

        @NonNull
        CharSequence getDistance();

        @NonNull
        List<CharSequence> getStreetList();

        @NonNull
        CharSequence getTimeToDestination();
    }

    @NonNull
    List<? extends RouteModel> getRouteList();
}
