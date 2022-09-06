package com.amazon.alexa.fitness.view.workoutTab;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.GetAllFitnessSessionResponse;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.UIState;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController;
import com.amazon.alexa.fitness.view.startTab.StartTabContent;
import com.amazon.alexa.protocols.network.NetworkService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.functions.Action1;
/* compiled from: WorkoutHistoryListView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u001f\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0012\u0010%\u001a\u00020\u00102\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\u0006\u0010)\u001a\u00020\"J\u0010\u0010*\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$J\u0006\u0010+\u001a\u00020\"J\u0006\u0010,\u001a\u00020\"J\u0010\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0010H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 ¨\u0006/"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutHistoryListView;", "", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "controller", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "emptyWorkoutHistoryView", "Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;)V", "getAfitsDataHelper", "()Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "getController", "()Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "getEmptyWorkoutHistoryView", "()Lcom/amazon/alexa/fitness/view/workoutTab/EmptyWorkoutHistoryView;", "isLoading", "", "value", "isNetworkConnected", "setNetworkConnected", "(Z)V", "networkService", "Lcom/amazon/alexa/protocols/network/NetworkService;", "kotlin.jvm.PlatformType", "orchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerViewAdapter", "Lcom/amazon/alexa/fitness/view/workoutTab/RecyclerViewAdapter;", "scrollListener", "com/amazon/alexa/fitness/view/workoutTab/WorkoutHistoryListView$scrollListener$1", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutHistoryListView$scrollListener$1;", "handleGetNextFitnessSessions", "", "fitnessSessions", "Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "hasScrollEnded", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "initAdapter", "loadEmptyWorkoutHistoryView", "loadInitialFitnessSessions", "loadNextAfitsData", "setupWorkoutHistoryListView", "toggleNetworkStatus", "connected", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutHistoryListView {
    @NotNull
    private final AfitsDataHelper afitsDataHelper;
    @NotNull
    private final FullScreenViewController controller;
    @NotNull
    private final EmptyWorkoutHistoryView emptyWorkoutHistoryView;
    private boolean isLoading;
    private boolean isNetworkConnected;
    private final NetworkService networkService;
    private final FitnessSessionOrchestrator orchestrator;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private final WorkoutHistoryListView$scrollListener$1 scrollListener;

    /* JADX WARN: Type inference failed for: r2v7, types: [com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$scrollListener$1] */
    public WorkoutHistoryListView(@NotNull AfitsDataHelper afitsDataHelper, @NotNull FullScreenViewController controller, @NotNull EmptyWorkoutHistoryView emptyWorkoutHistoryView) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        Intrinsics.checkParameterIsNotNull(emptyWorkoutHistoryView, "emptyWorkoutHistoryView");
        this.afitsDataHelper = afitsDataHelper;
        this.controller = controller;
        this.emptyWorkoutHistoryView = emptyWorkoutHistoryView;
        this.orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class);
        this.networkService = (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
        this.scrollListener = new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$scrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NotNull RecyclerView recyclerView, int i, int i2) {
                boolean z;
                boolean hasScrollEnded;
                Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, i, i2);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                z = WorkoutHistoryListView.this.isLoading;
                if (!z) {
                    hasScrollEnded = WorkoutHistoryListView.this.hasScrollEnded(linearLayoutManager);
                    if (!hasScrollEnded) {
                        return;
                    }
                    WorkoutHistoryListView.this.loadNextAfitsData();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasScrollEnded(LinearLayoutManager linearLayoutManager) {
        return linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == this.afitsDataHelper.getEndOfList();
    }

    private final void initAdapter() {
        final UIState uIState = this.orchestrator.getUIState();
        FitnessSession[] workouts = uIState.getHistory().getWorkouts();
        if (!uIState.getHistory().getHasLoadedInitialWorkouts() && this.isNetworkConnected) {
            AfitsClient.DefaultImpls.getAllFitnessSessions$default(this.afitsDataHelper.getAfitsClient(), null, 15, new CoralService.Callback<GetAllFitnessSessionResponse>() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$initAdapter$1
                @Override // com.dee.app.http.CoralService.Callback
                public void onFailure(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @Nullable CoralServiceException coralServiceException) {
                    Log.e("AFX-WorkoutHistoryView", "An error occurred trying to load all fitness sessions", coralServiceException);
                    FullScreenUtil.Companion.getMainHandler().post(WorkoutHistoryListView$initAdapter$1$onFailure$1.INSTANCE);
                }

                @Override // com.dee.app.http.CoralService.Callback
                public void onResult(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @Nullable GetAllFitnessSessionResponse getAllFitnessSessionResponse) {
                    FitnessSessionOrchestrator fitnessSessionOrchestrator;
                    uIState.getHistory().setHasLoadedInitialWorkouts(true);
                    if (getAllFitnessSessionResponse != null) {
                        if (!(getAllFitnessSessionResponse.getFitnessSessions().length == 0)) {
                            fitnessSessionOrchestrator = WorkoutHistoryListView.this.orchestrator;
                            fitnessSessionOrchestrator.updateWorkouts(getAllFitnessSessionResponse.getFitnessSessions(), getAllFitnessSessionResponse.getNextToken());
                            WorkoutHistoryListView.this.loadInitialFitnessSessions(getAllFitnessSessionResponse);
                            return;
                        }
                    }
                    WorkoutHistoryListView.this.loadEmptyWorkoutHistoryView();
                }
            }, 1, null);
            return;
        }
        if (workouts != null) {
            if (!(workouts.length == 0)) {
                loadInitialFitnessSessions(new GetAllFitnessSessionResponse(workouts, this.orchestrator.getUIState().getHistory().getNextToken()));
                this.afitsDataHelper.setNextToken(this.orchestrator.getUIState().getHistory().getNextToken());
                return;
            }
        }
        loadEmptyWorkoutHistoryView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNetworkConnected(boolean z) {
        UIState uIState = this.orchestrator.getUIState();
        boolean z2 = this.isNetworkConnected;
        this.isNetworkConnected = z;
        toggleNetworkStatus(z);
        if ((!z || z2 == z) && uIState.getHistory().getHasLoadedInitialWorkouts()) {
            return;
        }
        initAdapter();
    }

    private final void toggleNetworkStatus(boolean z) {
        if (z) {
            this.controller.hideBanner();
        } else {
            this.controller.showBanner(R.string.no_internet_connection);
        }
    }

    @NotNull
    public final AfitsDataHelper getAfitsDataHelper() {
        return this.afitsDataHelper;
    }

    @NotNull
    public final FullScreenViewController getController() {
        return this.controller;
    }

    @NotNull
    public final EmptyWorkoutHistoryView getEmptyWorkoutHistoryView() {
        return this.emptyWorkoutHistoryView;
    }

    public final void handleGetNextFitnessSessions(@NotNull GetAllFitnessSessionResponse fitnessSessions) {
        Intrinsics.checkParameterIsNotNull(fitnessSessions, "fitnessSessions");
        this.afitsDataHelper.addAllWorkoutSessions(fitnessSessions);
        FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$handleGetNextFitnessSessions$1
            @Override // java.lang.Runnable
            public final void run() {
                RecyclerViewAdapter recyclerViewAdapter;
                recyclerViewAdapter = WorkoutHistoryListView.this.recyclerViewAdapter;
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
        this.isLoading = false;
    }

    public final void loadEmptyWorkoutHistoryView() {
        final View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$loadEmptyWorkoutHistoryView$$inlined$let$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ViewGroup viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.workouts_tab);
                    if (viewGroup != null) {
                        viewGroup.setVisibility(8);
                    }
                    ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(R.id.empty_workouts_tab);
                    if (viewGroup2 != null) {
                        viewGroup2.setVisibility(0);
                    }
                    this.getEmptyWorkoutHistoryView().setupEmptyWorkoutHistoryView();
                    StartTabContent.Companion.hideStartTabContent();
                }
            });
        }
    }

    public final void loadInitialFitnessSessions(@Nullable GetAllFitnessSessionResponse getAllFitnessSessionResponse) {
        if (getAllFitnessSessionResponse != null) {
            this.afitsDataHelper.initializeWorkoutSessions(getAllFitnessSessionResponse);
            this.afitsDataHelper.addWeeklySummaryItem();
        }
        FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$loadInitialFitnessSessions$1
            @Override // java.lang.Runnable
            public final void run() {
                WorkoutHistoryListView$scrollListener$1 workoutHistoryListView$scrollListener$1;
                RecyclerView recyclerView;
                RecyclerViewAdapter recyclerViewAdapter;
                View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
                if (fullScreenView != null) {
                    ViewGroup viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.workouts_tab);
                    if (viewGroup != null) {
                        viewGroup.setVisibility(0);
                    }
                    ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(R.id.empty_workouts_tab);
                    if (viewGroup2 != null) {
                        viewGroup2.setVisibility(8);
                    }
                }
                WorkoutHistoryListView workoutHistoryListView = WorkoutHistoryListView.this;
                AfitsDataHelper afitsDataHelper = workoutHistoryListView.getAfitsDataHelper();
                workoutHistoryListView$scrollListener$1 = WorkoutHistoryListView.this.scrollListener;
                workoutHistoryListView.recyclerViewAdapter = new RecyclerViewAdapter(afitsDataHelper, workoutHistoryListView$scrollListener$1);
                recyclerView = WorkoutHistoryListView.this.recyclerView;
                if (recyclerView != null) {
                    recyclerViewAdapter = WorkoutHistoryListView.this.recyclerViewAdapter;
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
        });
    }

    public final void loadNextAfitsData() {
        String nextToken = this.orchestrator.getUIState().getHistory().getNextToken();
        if (nextToken != null) {
            this.afitsDataHelper.getWorkoutHistoryList().add(new WorkoutItem(FitnessListType.LOADING));
            RecyclerViewAdapter recyclerViewAdapter = this.recyclerViewAdapter;
            if (recyclerViewAdapter != null) {
                recyclerViewAdapter.notifyItemInserted(this.afitsDataHelper.getEndOfList());
            }
            this.isLoading = true;
            GeneratedOutlineSupport1.outline158("Loading next set of data with token: ", nextToken);
            this.afitsDataHelper.getAfitsClient().getAllFitnessSessions(nextToken, 10, new CoralService.Callback<GetAllFitnessSessionResponse>() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$loadNextAfitsData$$inlined$let$lambda$1
                @Override // com.dee.app.http.CoralService.Callback
                public void onFailure(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @Nullable CoralServiceException coralServiceException) {
                    Log.e("AFX-WorkoutHistoryView", "An error occurred trying to load the next fitness sessions", coralServiceException);
                    WorkoutHistoryListView.this.isLoading = false;
                }

                @Override // com.dee.app.http.CoralService.Callback
                public void onResult(@Nullable CoralService.Call<GetAllFitnessSessionResponse> call, @NotNull GetAllFitnessSessionResponse fitnessSessions) {
                    FitnessSessionOrchestrator fitnessSessionOrchestrator;
                    Intrinsics.checkParameterIsNotNull(fitnessSessions, "fitnessSessions");
                    if (fitnessSessions.getNextToken() == null) {
                        Integer.valueOf(Log.d("AFX-WorkoutHistoryView", "All fitness sessions have been loaded, none remain on the server"));
                    }
                    WorkoutHistoryListView.this.isLoading = false;
                    List<WorkoutItem> workoutHistoryList = WorkoutHistoryListView.this.getAfitsDataHelper().getWorkoutHistoryList();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : workoutHistoryList) {
                        if (((WorkoutItem) obj).getItemType() == FitnessListType.LOADING) {
                            arrayList.add(obj);
                        }
                    }
                    WorkoutHistoryListView.this.getAfitsDataHelper().getWorkoutHistoryList().removeAll(arrayList);
                    WorkoutHistoryListView.this.handleGetNextFitnessSessions(fitnessSessions);
                    fitnessSessionOrchestrator = WorkoutHistoryListView.this.orchestrator;
                    fitnessSessionOrchestrator.updateWorkouts(fitnessSessions.getFitnessSessions(), fitnessSessions.getNextToken());
                }
            });
        }
    }

    public final void setupWorkoutHistoryListView() {
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            this.recyclerView = (RecyclerView) fullScreenView.findViewById(R.id.recyclerView);
        }
        this.networkService.onConnectivityChanged().subscribe(new Action1<Boolean>() { // from class: com.amazon.alexa.fitness.view.workoutTab.WorkoutHistoryListView$setupWorkoutHistoryListView$2
            @Override // rx.functions.Action1
            public final void call(Boolean isConnected) {
                WorkoutHistoryListView workoutHistoryListView = WorkoutHistoryListView.this;
                Intrinsics.checkExpressionValueIsNotNull(isConnected, "isConnected");
                workoutHistoryListView.setNetworkConnected(isConnected.booleanValue());
            }
        });
        NetworkService networkService = this.networkService;
        Intrinsics.checkExpressionValueIsNotNull(networkService, "networkService");
        setNetworkConnected(networkService.isConnected());
    }
}
