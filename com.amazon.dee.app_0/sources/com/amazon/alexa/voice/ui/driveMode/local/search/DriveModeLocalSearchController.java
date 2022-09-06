package com.amazon.alexa.voice.ui.driveMode.local.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.driveMode.R;
import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalLocationProvider;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchAdapter;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.driveMode.util.DriverDistractionLog;
import com.amazon.alexa.voice.ui.driveMode.util.Fonts;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.util.StatusBar;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class DriveModeLocalSearchController extends ViewController implements DriveModeLocalSearchContract.View {
    private static final String EXTRA_CARD = "card";
    private static final String TAG = "DriveModeLocalSearchController";
    private DriveModeLocalSearchAdapter contentAdapter;
    private DriveModeLocalSearchContract.Interactor interactor;
    private DriveModeLocalSearchContract.Presenter presenter;
    private Resources resources;
    private TextView titleView;
    private TextView voiceHint;

    public static DriveModeLocalSearchController create(LocalCard localCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", localCard);
        DriveModeLocalSearchController driveModeLocalSearchController = new DriveModeLocalSearchController();
        driveModeLocalSearchController.setArguments(bundle);
        return driveModeLocalSearchController;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.View
    public void floodBackgroundToStatusBar() {
        StatusBar.tryFlood(getComponent(), getContext());
    }

    public /* synthetic */ void lambda$onCreateView$0$DriveModeLocalSearchController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        LocalCard localCard = (LocalCard) getArguments().getParcelable("card");
        if (localCard == null) {
            Log.e(TAG, "Use DriveModeLocalSearchController.create(LocalCard) to create a controller");
        }
        Component component = getComponent();
        this.resources = new AndroidResources(getContext(), ComponentUtils.getLocale(component));
        this.interactor = new DriveModeLocalSearchInteractor(localCard, (LocalDelegate) component.get(LocalDelegate.class), new DriveModeLocalLocationProvider(this));
        DriverDistractionLog driverDistractionLog = new DriverDistractionLog();
        this.presenter = new DriveModeLocalSearchPresenter(this, this.interactor, this.resources, (DriveModeCardMetricsInteractor) getComponent().get(DriveModeCardMetricsInteractor.class), driverDistractionLog);
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_drive_mode_local_search, viewGroup, false);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.voiceHint = (TextView) inflate.findViewById(R.id.dm_vui_landing_page_hint);
        HeaderUtils.applyFontStyles(this.titleView);
        Fonts.EMBER_BOLD_ITALIC.apply(this.voiceHint);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.search.-$$Lambda$DriveModeLocalSearchController$FSVC9uePVNt8OBHz98MA0PANR5o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeLocalSearchController.this.lambda$onCreateView$0$DriveModeLocalSearchController(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        Resources resources = this.resources;
        final DriveModeLocalSearchContract.Presenter presenter = this.presenter;
        presenter.getClass();
        this.contentAdapter = new DriveModeLocalSearchAdapter(resources, new DriveModeLocalSearchAdapter.OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.search.-$$Lambda$5MRh_epmGtQKL_ehJfDRykV1knk
            @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchAdapter.OnItemClickListener
            public final void onItemClicked(LocalSearchItemModel localSearchItemModel) {
                DriveModeLocalSearchContract.Presenter.this.itemClicked(localSearchItemModel);
            }
        });
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchController.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                DriveModeLocalSearchController.this.presenter.interacted();
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewGroup.getContext(), 1);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(viewGroup.getContext(), R.drawable.local_search_list_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.contentAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.View
    public void setSubTitle(CharSequence charSequence) {
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.View
    public void setTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.titleView, charSequence);
    }
}
