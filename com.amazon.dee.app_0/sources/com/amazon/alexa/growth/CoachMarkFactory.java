package com.amazon.alexa.growth;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.growth.coachmark.CoachMark;
/* loaded from: classes8.dex */
public interface CoachMarkFactory {
    CoachMark getCoachMark(@NonNull View view);

    CoachMark getCoachMark(@NonNull View view, @Nullable String str);

    CoachMark getCoachMark(@NonNull View view, @Nullable String str, @Nullable ViewGroup viewGroup);

    CoachMark getCoachMark(@NonNull View view, @Nullable String str, @Nullable ViewGroup viewGroup, @NonNull CoachMark.ORIENTATION orientation);

    CoachMark getCoachMark(@NonNull View view, @Nullable String str, @NonNull CoachMark.ORIENTATION orientation);
}
