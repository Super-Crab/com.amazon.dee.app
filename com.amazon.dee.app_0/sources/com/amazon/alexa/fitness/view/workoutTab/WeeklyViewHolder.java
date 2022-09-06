package com.amazon.alexa.fitness.view.workoutTab;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.DayOfWeekPosition;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WeeklyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "(Landroid/view/View;Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;)V", "setFontType", "", "updateWeeklySummaryView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WeeklyViewHolder extends RecyclerView.ViewHolder {
    private AfitsDataHelper afitsDataHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeeklyViewHolder(@NotNull View itemView, @NotNull AfitsDataHelper afitsDataHelper) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        this.afitsDataHelper = afitsDataHelper;
    }

    private final void setFontType() {
        TextView textView = (TextView) this.itemView.findViewById(R.id.weeklyDistanceValueText);
        if (textView != null) {
            Fonts.EMBER_REGULAR.apply(textView);
        }
        TextView textView2 = (TextView) this.itemView.findViewById(R.id.numWorkouts);
        if (textView2 != null) {
            Fonts.EMBER_BOLD.apply(textView2);
        }
        TextView textView3 = (TextView) this.itemView.findViewById(R.id.activeWorkoutTime);
        if (textView3 != null) {
            Fonts.EMBER_BOLD.apply(textView3);
        }
        TextView textView4 = (TextView) this.itemView.findViewById(R.id.activeWorkoutTime);
        if (textView4 != null) {
            Fonts.EMBER_BOLD.apply(textView4);
        }
        TextView textView5 = (TextView) this.itemView.findViewById(R.id.weeklyDistanceValue);
        if (textView5 != null) {
            Fonts.EMBER_BOLD.apply(textView5);
        }
        TextView textView6 = (TextView) this.itemView.findViewById(R.id.weeklyCaloriesValue);
        if (textView6 != null) {
            Fonts.EMBER_BOLD.apply(textView6);
        }
        TextView textView7 = (TextView) this.itemView.findViewById(R.id.weeklyPaceValue);
        if (textView7 != null) {
            Fonts.EMBER_BOLD.apply(textView7);
        }
        TextView textView8 = (TextView) this.itemView.findViewById(R.id.weeklyStepsValue);
        if (textView8 != null) {
            Fonts.EMBER_BOLD.apply(textView8);
        }
        TextView textView9 = (TextView) this.itemView.findViewById(R.id.numWorkoutsText);
        if (textView9 != null) {
            Fonts.EMBER_REGULAR.apply(textView9);
        }
        TextView textView10 = (TextView) this.itemView.findViewById(R.id.activeWorkoutTimeText);
        if (textView10 != null) {
            Fonts.EMBER_REGULAR.apply(textView10);
        }
        TextView textView11 = (TextView) this.itemView.findViewById(R.id.weeklyDistanceValueText);
        if (textView11 != null) {
            Fonts.EMBER_REGULAR.apply(textView11);
        }
        TextView textView12 = (TextView) this.itemView.findViewById(R.id.weeklyCaloriesValueText);
        if (textView12 != null) {
            Fonts.EMBER_REGULAR.apply(textView12);
        }
        TextView textView13 = (TextView) this.itemView.findViewById(R.id.weeklyPaceValueUnit);
        if (textView13 != null) {
            Fonts.EMBER_REGULAR.apply(textView13);
        }
        TextView textView14 = (TextView) this.itemView.findViewById(R.id.weeklyPaceValueText);
        if (textView14 != null) {
            Fonts.EMBER_REGULAR.apply(textView14);
        }
        TextView textView15 = (TextView) this.itemView.findViewById(R.id.weeklyStepsValueText);
        if (textView15 != null) {
            Fonts.EMBER_REGULAR.apply(textView15);
        }
        for (Number number : DayOfWeekPosition.Companion.getDateStringMap().values()) {
            TextView textView16 = (TextView) this.itemView.findViewById(number.intValue());
            if (textView16 != null) {
                Fonts.EMBER_REGULAR.apply(textView16);
            }
        }
    }

    public final void updateWeeklySummaryView() {
        TextView textView;
        Log.i("AFX-ListViewHolder", "Updating weekly summary view");
        setFontType();
        WeeklySummary calculateWeeklySummary = this.afitsDataHelper.calculateWeeklySummary(new Date(DateTime.Companion.now().getEpochMilli()));
        TextView textView2 = (TextView) this.itemView.findViewById(R.id.weeklyDistanceValueText);
        if (textView2 != null) {
            textView2.setText(FormatUtilKt.getDistanceUnit());
        }
        TextView textView3 = (TextView) this.itemView.findViewById(R.id.numWorkouts);
        if (textView3 != null) {
            textView3.setText(FormatUtilKt.getFormattedString(calculateWeeklySummary.getNumOfWorkouts()));
        }
        TextView textView4 = (TextView) this.itemView.findViewById(R.id.activeWorkoutTime);
        if (textView4 != null) {
            textView4.setText(FormatUtilKt.getWeeklyFormattedTime(calculateWeeklySummary.getTotalActiveWorkoutTimeInSeconds()));
        }
        TextView textView5 = (TextView) this.itemView.findViewById(R.id.weeklyDistanceValue);
        if (textView5 != null) {
            textView5.setText(FormatUtilKt.convertToLocalDistanceWithoutUnits(calculateWeeklySummary.getTotalDistanceInMeters()));
        }
        TextView textView6 = (TextView) this.itemView.findViewById(R.id.weeklyCaloriesValue);
        if (textView6 != null) {
            textView6.setText(FitnessViewHoldersKt.getValidCalories(calculateWeeklySummary.getTotalCalories()));
        }
        TextView textView7 = (TextView) this.itemView.findViewById(R.id.weeklyStepsValue);
        if (textView7 != null) {
            textView7.setText(FormatUtilKt.getFormattedString((int) calculateWeeklySummary.getTotalSteps()));
        }
        TextView textView8 = (TextView) this.itemView.findViewById(R.id.weeklyPaceValue);
        if (textView8 != null) {
            textView8.setText(FormatUtilKt.getFormattedPaceInDeviceLocale(new Pair(Double.valueOf(calculateWeeklySummary.getAveragePace()), Units.MinutesPerKilometer), FullScreenUtil.Companion.getCurrentContext(), false));
        }
        if (calculateWeeklySummary.getAveragePace() <= 0 && (textView = (TextView) this.itemView.findViewById(R.id.weeklyPaceValueUnit)) != null) {
            textView.setVisibility(8);
        }
        TextView textView9 = (TextView) this.itemView.findViewById(R.id.weeklyPaceValueUnit);
        if (textView9 != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            textView9.setText(FormatUtilKt.usesMetricsSystem(locale) ? FormatUtilKt.getPaceUnit(Units.MinutesPerKilometer) : FormatUtilKt.getPaceUnit(Units.MinutesPerMile));
        }
        for (Number number : calculateWeeklySummary.getActiveDays()) {
            Integer id = DayOfWeekPosition.Companion.getId(number.intValue());
            if (id != null) {
                int intValue = id.intValue();
                TextView textView10 = (TextView) this.itemView.findViewById(intValue);
                if (textView10 != null) {
                    textView10.setTextColor(-1);
                }
                TextView textView11 = (TextView) this.itemView.findViewById(intValue);
                if (textView11 != null) {
                    Fonts.EMBER_BOLD.apply(textView11);
                }
                TextView textView12 = (TextView) this.itemView.findViewById(intValue);
                if (textView12 != null) {
                    View itemView = this.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                    textView12.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.circle));
                }
            }
        }
    }
}
