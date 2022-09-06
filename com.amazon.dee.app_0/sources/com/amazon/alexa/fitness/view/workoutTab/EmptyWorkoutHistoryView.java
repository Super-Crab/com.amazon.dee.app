package com.amazon.alexa.fitness.view.workoutTab;

import android.view.View;
import android.widget.TextView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: EmptyWorkoutHistoryView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;", "", "()V", "setFonts", "", "view", "Landroid/view/View;", "setupEmptyWorkoutHistoryView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class EmptyWorkoutHistoryView {
    public void setFonts(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.noWorkouts));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.noWorkoutLink));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.emptyWorkoutDescription1));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.emptyWorkoutDescription2));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.emptyWorkoutDescription3));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_utterance));
    }

    public final void setupEmptyWorkoutHistoryView() {
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            setFonts(fullScreenView);
            TextView noWorkoutLink = (TextView) fullScreenView.findViewById(R.id.noWorkoutLink);
            TextView emptyWorkoutDescriptionLine1 = (TextView) fullScreenView.findViewById(R.id.emptyWorkoutDescription1);
            TextView emptyWorkoutDescriptionLine2 = (TextView) fullScreenView.findViewById(R.id.emptyWorkoutDescription2);
            TextView emptyWorkoutDescriptionLine3 = (TextView) fullScreenView.findViewById(R.id.emptyWorkoutDescription3);
            if (FullScreenUtil.Companion.isUserProfileSet()) {
                Intrinsics.checkExpressionValueIsNotNull(emptyWorkoutDescriptionLine2, "emptyWorkoutDescriptionLine2");
                emptyWorkoutDescriptionLine2.setVisibility(8);
                Intrinsics.checkExpressionValueIsNotNull(emptyWorkoutDescriptionLine1, "emptyWorkoutDescriptionLine1");
                emptyWorkoutDescriptionLine1.setText(fullScreenView.getContext().getString(R.string.empty_workout_start_description1));
                Intrinsics.checkExpressionValueIsNotNull(noWorkoutLink, "noWorkoutLink");
                noWorkoutLink.setText(fullScreenView.getContext().getString(R.string.start_a_workout));
            } else {
                Intrinsics.checkExpressionValueIsNotNull(emptyWorkoutDescriptionLine2, "emptyWorkoutDescriptionLine2");
                emptyWorkoutDescriptionLine2.setVisibility(0);
                Intrinsics.checkExpressionValueIsNotNull(emptyWorkoutDescriptionLine1, "emptyWorkoutDescriptionLine1");
                emptyWorkoutDescriptionLine1.setText(fullScreenView.getContext().getString(R.string.empty_workout_description1));
                emptyWorkoutDescriptionLine2.setText(fullScreenView.getContext().getString(R.string.empty_workout_description2));
                Intrinsics.checkExpressionValueIsNotNull(emptyWorkoutDescriptionLine3, "emptyWorkoutDescriptionLine3");
                emptyWorkoutDescriptionLine3.setText(fullScreenView.getContext().getString(R.string.empty_workout_description3));
                Intrinsics.checkExpressionValueIsNotNull(noWorkoutLink, "noWorkoutLink");
                noWorkoutLink.setText(fullScreenView.getContext().getString(R.string.create_fitness_profile_link));
            }
            noWorkoutLink.setOnClickListener(EmptyWorkoutHistoryView$setupEmptyWorkoutHistoryView$1$1.INSTANCE);
        }
    }
}
