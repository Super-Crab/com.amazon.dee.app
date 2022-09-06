package com.amazon.alexa.fitness.view.fullscreen;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricComponent;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.utils.TabViewMetrics;
import com.amazon.alexa.fitness.view.startTab.StartTabContent;
import com.amazon.alexa.fitness.view.workoutTab.WorkoutTabContent;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.material.tabs.TabLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessTabController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/view/fullscreen/FitnessTabController;", "Landroidx/fragment/app/FragmentActivity;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "parentController", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;)V", "getAfitsDataHelper", "()Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "getParentController", "()Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "workoutTabContent", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutTabContent;", "addTab", "", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", ViewProps.POSITION, "Lcom/amazon/alexa/fitness/view/fullscreen/TabPosition;", "getMetricComponent", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getTabViewLayout", "Landroid/view/View;", "routeSelectedTab", "setTabContent", "setupTabView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessTabController extends FragmentActivity {
    @NotNull
    private final AfitsDataHelper afitsDataHelper;
    @NotNull
    private final FullScreenViewController parentController;
    private final WorkoutTabContent workoutTabContent;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TabPosition.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[TabPosition.TRACK.ordinal()] = 1;
            $EnumSwitchMapping$0[TabPosition.HISTORY.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[TabPosition.values().length];
            $EnumSwitchMapping$1[TabPosition.TRACK.ordinal()] = 1;
            $EnumSwitchMapping$1[TabPosition.HISTORY.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[TabPosition.values().length];
            $EnumSwitchMapping$2[TabPosition.TRACK.ordinal()] = 1;
            $EnumSwitchMapping$2[TabPosition.HISTORY.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[TabPosition.values().length];
            $EnumSwitchMapping$3[TabPosition.TRACK.ordinal()] = 1;
            $EnumSwitchMapping$3[TabPosition.HISTORY.ordinal()] = 2;
        }
    }

    public FitnessTabController(@NotNull AfitsDataHelper afitsDataHelper, @NotNull FullScreenViewController parentController) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        Intrinsics.checkParameterIsNotNull(parentController, "parentController");
        this.afitsDataHelper = afitsDataHelper;
        this.parentController = parentController;
        this.workoutTabContent = new WorkoutTabContent(this.afitsDataHelper, this.parentController);
    }

    private final void addTab(TabLayout tabLayout, TabPosition tabPosition) {
        Resources resources;
        Resources resources2;
        String str = "adding tab for position " + tabPosition;
        TabLayout.Tab newTab = tabLayout.newTab();
        Intrinsics.checkExpressionValueIsNotNull(newTab, "tabLayout.newTab()");
        int i = WhenMappings.$EnumSwitchMapping$0[tabPosition.ordinal()];
        String str2 = null;
        if (i == 1) {
            ViewGroup container = FullScreenUtil.Companion.getContainer();
            if (container != null && (resources = container.getResources()) != null) {
                str2 = resources.getString(R.string.track_tab_title);
            }
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            ViewGroup container2 = FullScreenUtil.Companion.getContainer();
            if (container2 != null && (resources2 = container2.getResources()) != null) {
                str2 = resources2.getString(R.string.history_tab_title);
            }
        }
        newTab.setText(str2);
        tabLayout.addTab(newTab);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MetricComponent getMetricComponent(TabPosition tabPosition) {
        int i = WhenMappings.$EnumSwitchMapping$3[tabPosition.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return TabViewMetrics.Companion.getHISTORY_TAB();
        }
        return TabViewMetrics.Companion.getTRACK_TAB();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void routeSelectedTab(TabPosition tabPosition) {
        int i = WhenMappings.$EnumSwitchMapping$2[tabPosition.ordinal()];
        if (i == 1) {
            FullScreenUtil.Companion.goTrackTab();
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            FullScreenUtil.Companion.goHistoryTab();
        }
    }

    private final void setTabContent(TabPosition tabPosition) {
        String str = "set tab content for position " + tabPosition;
        int i = WhenMappings.$EnumSwitchMapping$1[tabPosition.ordinal()];
        if (i == 1) {
            StartTabContent.Companion.showStartTabContent();
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            this.workoutTabContent.showWorkoutTabContent();
        }
    }

    @NotNull
    public final AfitsDataHelper getAfitsDataHelper() {
        return this.afitsDataHelper;
    }

    @NotNull
    public final FullScreenViewController getParentController() {
        return this.parentController;
    }

    @NotNull
    public final View getTabViewLayout(@NotNull TabPosition position) {
        Intrinsics.checkParameterIsNotNull(position, "position");
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        View view = LayoutInflater.from(container != null ? container.getContext() : null).inflate(R.layout.tab_view_layout, FullScreenUtil.Companion.getContainer(), false);
        FullScreenUtil.Companion.setFullScreenView(view);
        setupTabView(position);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return view;
    }

    public final void setupTabView(@NotNull final TabPosition position) {
        Intrinsics.checkParameterIsNotNull(position, "position");
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            AppCompatImageButton appCompatImageButton = (AppCompatImageButton) fullScreenView.findViewById(R.id.btn_close);
            if (appCompatImageButton != null) {
                appCompatImageButton.setOnClickListener(FitnessTabController$setupTabView$1$1.INSTANCE);
            }
            ImageButton imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_settings);
            if (imageButton != null) {
                imageButton.setImageDrawable(fullScreenView.getContext().getDrawable(R.drawable.ic_settings));
            }
            if (imageButton != null) {
                imageButton.setOnClickListener(FitnessTabController$setupTabView$1$2.INSTANCE);
            }
            try {
                TabLayout tabLayout = (TabLayout) fullScreenView.findViewById(R.id.tab_view_layout);
                Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
                addTab(tabLayout, TabPosition.TRACK);
                addTab(tabLayout, TabPosition.HISTORY);
                TabLayout.Tab tabAt = tabLayout.getTabAt(position.getValue());
                if (tabAt != null) {
                    tabAt.select();
                }
                setTabContent(position);
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.amazon.alexa.fitness.view.fullscreen.FitnessTabController$setupTabView$$inlined$let$lambda$1
                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabReselected(@NotNull TabLayout.Tab tab) {
                        Intrinsics.checkParameterIsNotNull(tab, "tab");
                    }

                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabSelected(@NotNull TabLayout.Tab tab) {
                        MetricComponent metricComponent;
                        Intrinsics.checkParameterIsNotNull(tab, "tab");
                        String str = "onTabSelected " + tab.getPosition();
                        TabPosition fromInt = TabPosition.Companion.fromInt(tab.getPosition());
                        metricComponent = FitnessTabController.this.getMetricComponent(fromInt);
                        MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), metricComponent, EventType.TAP);
                        FitnessTabController.this.routeSelectedTab(fromInt);
                    }

                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                        Intrinsics.checkParameterIsNotNull(tab, "tab");
                    }
                });
                Unit unit = Unit.INSTANCE;
            } catch (Exception e) {
                Integer.valueOf(Log.e("AFX-TabController", "Setting up tab view throw an error", e));
            }
        }
    }
}
