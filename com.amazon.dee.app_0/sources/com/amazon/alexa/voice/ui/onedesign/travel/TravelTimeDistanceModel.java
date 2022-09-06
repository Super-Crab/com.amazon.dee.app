package com.amazon.alexa.voice.ui.onedesign.travel;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface TravelTimeDistanceModel extends Parcelable {
    public static final int CONDITION_EXTREMELY_SLOW = 3;
    public static final int CONDITION_GOOD = 0;
    public static final int CONDITION_SLOW = 2;
    public static final int CONDITION_SLUGGISH = 1;
    public static final int TRAVEL_DISTANCE = 1;
    public static final int TRAVEL_TIME = 0;

    int getCondition();

    @NonNull
    CharSequence getDestination();

    @NonNull
    CharSequence getDistanceToDestination();

    @NonNull
    CharSequence getOrigin();

    @NonNull
    List<CharSequence> getRoadSegments();

    @NonNull
    CharSequence getTimeToDestination();

    int getTravelCardType();
}
