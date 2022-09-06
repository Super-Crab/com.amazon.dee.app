package com.amazon.alexa.fitness.view.workoutTab;

import android.view.View;
import android.view.ViewGroup;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController;
import com.amazon.alexa.fitness.view.startTab.StartTabContent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkoutTabContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutTabContent;", "", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "controller", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;)V", "emptyWorkoutHistoryView", "Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;", "getEmptyWorkoutHistoryView", "()Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;", "setEmptyWorkoutHistoryView", "(Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;)V", "workoutHistoryListView", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutHistoryListView;", "showWorkoutTabContent", "", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutTabContent {
    public static final Companion Companion = new Companion(null);
    private final AfitsDataHelper afitsDataHelper;
    private final FullScreenViewController controller;
    @NotNull
    private EmptyWorkoutHistoryView emptyWorkoutHistoryView;
    private final WorkoutHistoryListView workoutHistoryListView;

    /* compiled from: WorkoutTabContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutTabContent$Companion;", "", "()V", "hideWorkoutTabContent", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        public final void hideWorkoutTabContent() {
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView != null) {
                ViewGroup viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.empty_workouts_tab);
                if (viewGroup != null) {
                    viewGroup.setVisibility(8);
                }
                ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(R.id.workouts_tab);
                if (viewGroup2 == null) {
                    return;
                }
                viewGroup2.setVisibility(8);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WorkoutTabContent(@NotNull AfitsDataHelper afitsDataHelper, @NotNull FullScreenViewController controller) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        this.afitsDataHelper = afitsDataHelper;
        this.controller = controller;
        this.emptyWorkoutHistoryView = new EmptyWorkoutHistoryView();
        this.workoutHistoryListView = new WorkoutHistoryListView(this.afitsDataHelper, this.controller, this.emptyWorkoutHistoryView);
    }

    @NotNull
    public final EmptyWorkoutHistoryView getEmptyWorkoutHistoryView() {
        return this.emptyWorkoutHistoryView;
    }

    public final void setEmptyWorkoutHistoryView(@NotNull EmptyWorkoutHistoryView emptyWorkoutHistoryView) {
        Intrinsics.checkParameterIsNotNull(emptyWorkoutHistoryView, "<set-?>");
        this.emptyWorkoutHistoryView = emptyWorkoutHistoryView;
    }

    public final void showWorkoutTabContent() {
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            ViewGroup viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.workouts_tab);
            if (viewGroup != null) {
                viewGroup.setVisibility(0);
            }
            this.workoutHistoryListView.setupWorkoutHistoryListView();
            StartTabContent.Companion.hideStartTabContent();
        }
        StartTabContent.Companion.hideStartTabContent();
    }
}
