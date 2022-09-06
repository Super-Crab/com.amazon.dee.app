package com.amazon.alexa.fitness.utils;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtil;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* compiled from: FullScreenUpdaterUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FullScreenUpdaterUtil;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FullScreenUpdaterUtil {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static FitnessMetrics fitnessMetrics;
    private static FitnessMetrics updatedMetrics;

    /* compiled from: FullScreenUpdaterUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ&\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013H\u0002J&\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013H\u0002J\u001f\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010\u001fJ$\u0010 \u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0002J&\u0010\"\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013H\u0002R(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FullScreenUpdaterUtil$Companion;", "", "()V", "value", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "fitnessMetrics", "getFitnessMetrics", "()Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "setFitnessMetrics", "(Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;)V", "updatedMetrics", "initFitnessData", "", "showLastSummary", "", "updateCalories", "fullScreenView", "Landroid/view/View;", "caloriesValue", "Lkotlin/Pair;", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "updateDistance", "distance", "updateDuration", "durationValue", "", "(Landroid/view/View;Ljava/lang/Integer;)V", "updateFitnessData", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;", "duration", "(Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;Ljava/lang/Integer;)V", "updatePace", "speed", "updateSteps", "stepsValue", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateCalories(View view, Pair<Double, ? extends Units> pair) {
            String formattedCalories = FormatUtilKt.getFormattedCalories(pair);
            GeneratedOutlineSupport1.outline158("setting calories to ", formattedCalories);
            TextView textView = (TextView) view.findViewById(R.id.lbl_calories);
            if (textView != null) {
                textView.setText(formattedCalories);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateDistance(View view, Pair<Double, ? extends Units> pair) {
            String str;
            TextView textView = (TextView) view.findViewById(R.id.lbl_distance);
            if (textView != null) {
                if (pair == null || (str = FormatUtilKt.toDistanceString(pair)) == null) {
                    str = FullScreenUpdaterUtilKt.DEFAULT_DATA;
                }
                textView.setText(str);
            }
            TextView textView2 = (TextView) view.findViewById(R.id.lbl_unit_distance);
            if (textView2 != null) {
                textView2.setText(FormatUtilKt.getDistanceUnit());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateDuration(View view, Integer num) {
            String secondsToDurationString = FormatUtilKt.secondsToDurationString(Integer.valueOf(num != null ? num.intValue() : 0));
            GeneratedOutlineSupport1.outline158("setting duration to ", secondsToDurationString);
            TextView textView = (TextView) view.findViewById(R.id.lbl_duration);
            if (textView != null) {
                textView.setText(secondsToDurationString);
            }
        }

        private final void updateFitnessData(final FitnessMetrics.StepBased stepBased, final Integer num) {
            FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.utils.FullScreenUpdaterUtil$Companion$updateFitnessData$1
                @Override // java.lang.Runnable
                public final void run() {
                    Pair<Double, Units> pair;
                    View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
                    if (fullScreenView != null) {
                        FullScreenUpdaterUtil.Companion.updateDuration(fullScreenView, num);
                        FullScreenUpdaterUtil.Companion companion = FullScreenUpdaterUtil.Companion;
                        FitnessMetrics.StepBased stepBased2 = stepBased;
                        Pair<Double, Units> pair2 = null;
                        companion.updateDistance(fullScreenView, stepBased2 != null ? stepBased2.getDistance() : null);
                        FullScreenUpdaterUtil.Companion companion2 = FullScreenUpdaterUtil.Companion;
                        FitnessMetrics.StepBased stepBased3 = stepBased;
                        companion2.updateSteps(fullScreenView, stepBased3 != null ? stepBased3.getSteps() : null);
                        FullScreenUpdaterUtil.Companion companion3 = FullScreenUpdaterUtil.Companion;
                        FitnessMetrics.StepBased stepBased4 = stepBased;
                        if (stepBased4 != null) {
                            pair2 = stepBased4.getCalories();
                        }
                        companion3.updateCalories(fullScreenView, pair2);
                        FullScreenUpdaterUtil.Companion companion4 = FullScreenUpdaterUtil.Companion;
                        FitnessMetrics.StepBased stepBased5 = stepBased;
                        if (stepBased5 == null || (pair = stepBased5.getSpeed()) == null) {
                            pair = new Pair<>(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Units.MilesPerHour);
                        }
                        companion4.updatePace(fullScreenView, pair);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updatePace(View view, Pair<Double, ? extends Units> pair) {
            Pair<Double, Units> convertMilesPerHourToMinutesPerMile = FormatUtilKt.convertMilesPerHourToMinutesPerMile(pair);
            TextView textView = (TextView) view.findViewById(R.id.lbl_pace);
            if (textView != null) {
                String formattedPaceInDeviceLocale$default = FormatUtilKt.getFormattedPaceInDeviceLocale$default(convertMilesPerHourToMinutesPerMile, view.getContext(), null, 2, null);
                String str = "setting pace value to " + formattedPaceInDeviceLocale$default;
                textView.setText(formattedPaceInDeviceLocale$default);
                return;
            }
            Log.e("AFX-FullScreenUpdater", "pace value text view not found");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateSteps(View view, Pair<Integer, ? extends Units> pair) {
            String str;
            Integer first;
            if (pair == null || (first = pair.getFirst()) == null || (str = FormatUtilKt.getFormattedString(first.intValue())) == null) {
                str = FullScreenUpdaterUtilKt.DEFAULT_DATA;
            }
            GeneratedOutlineSupport1.outline158("setting steps to ", str);
            TextView textView = (TextView) view.findViewById(R.id.lbl_steps);
            if (textView != null) {
                textView.setText(str);
            }
        }

        @Nullable
        public final FitnessMetrics getFitnessMetrics() {
            return FullScreenUpdaterUtil.fitnessMetrics;
        }

        public final void initFitnessData(boolean z) {
            FullScreenUpdaterUtil$Companion$initFitnessData$1 fullScreenUpdaterUtil$Companion$initFitnessData$1 = new FullScreenUpdaterUtil$Companion$initFitnessData$1(z);
            Session session = FullScreenUtil.Companion.getSession();
            int processingDuration$default = session != null ? SessionDataModelsKt.processingDuration$default(session, null, 1, null) : 0;
            if (FullScreenUpdaterUtil.updatedMetrics != null) {
                if (!(FullScreenUpdaterUtil.updatedMetrics instanceof FitnessMetrics.StepBased)) {
                    return;
                }
                FitnessMetrics fitnessMetrics = FullScreenUpdaterUtil.updatedMetrics;
                if (fitnessMetrics != null) {
                    updateFitnessData((FitnessMetrics.StepBased) fullScreenUpdaterUtil$Companion$initFitnessData$1.mo12165invoke((FitnessMetrics.StepBased) fitnessMetrics), (Integer) fullScreenUpdaterUtil$Companion$initFitnessData$1.mo12165invoke(Integer.valueOf(processingDuration$default)));
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.afx.FitnessMetrics.StepBased");
            }
            updateFitnessData(null, (Integer) fullScreenUpdaterUtil$Companion$initFitnessData$1.mo12165invoke(Integer.valueOf(processingDuration$default)));
        }

        public final void setFitnessMetrics(@Nullable FitnessMetrics fitnessMetrics) {
            FullScreenUpdaterUtil.updatedMetrics = fitnessMetrics;
            FullScreenUpdaterUtil.Companion.initFitnessData(true);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
