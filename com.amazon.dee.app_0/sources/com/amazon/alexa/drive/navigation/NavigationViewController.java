package com.amazon.alexa.drive.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.view.DriveModeSingleLineView;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.util.Fonts;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class NavigationViewController extends ViewManagerViewController {
    private static final String TAG = "NavigationViewController";
    private static int sHintIndex;
    private DriveModeSingleLineView createLocations;
    private CompositeDisposable disposable;
    private DriveModeSingleLineView homeButton;
    private Disposable mAccessoryConnectionStatusDisposable;
    private BehaviorSubject<Boolean> mAccessoryConnectionStatusObservable;
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    NavigationCardManager mNavigationCardManager;
    @Inject
    NavigationDataProvider mNavigationDataProvider;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    @Inject
    PreferredNavigationAppContentResolver mPreferredNavigationAppContentResolver;
    private RecyclerView mRecyclerView;
    @Inject
    TTCFRecordOnce mTTCFRecordOnce;
    private View myLocationText;
    private View navigationLoading;
    @Inject
    NavigationMetrics navigationMetrics;
    private Resources res;
    private boolean savedHomeOrWorkInNavRepo;
    private boolean savedLocationInNavRepo;
    private DriveModeSingleLineView workButton;

    private boolean checkSavedHomeOrWorkInNavRepo() {
        return (this.mNavigationDataProvider.getHome() == null && this.mNavigationDataProvider.getWork() == null) ? false : true;
    }

    private boolean checkSavedLocationInNavRepo() {
        return (this.mNavigationDataProvider.getSavedLocations() == null || this.mNavigationDataProvider.getSavedLocations().size() == 0) ? false : true;
    }

    private void findNavigationViews(final View view) {
        this.navigationLoading = view.findViewById(R.id.dm_navigation_loading);
        this.myLocationText = view.findViewById(R.id.dm_saved_location_text);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.nav_saved_locations_feed);
        this.homeButton = (DriveModeSingleLineView) view.findViewById(R.id.navigation_home);
        this.workButton = (DriveModeSingleLineView) view.findViewById(R.id.navigation_work);
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        this.homeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewController$YiWCLoqghfMwkFrqGDQuUMXELIM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NavigationViewController.this.lambda$findNavigationViews$1$NavigationViewController(view, autoModeType, view2);
            }
        });
        this.workButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewController$_Wq16RN2TZV9wcCPgo-42wrJLW4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NavigationViewController.this.lambda$findNavigationViews$2$NavigationViewController(view, autoModeType, view2);
            }
        });
        this.createLocations = (DriveModeSingleLineView) view.findViewById(R.id.dm_navigation_create_location);
        Fonts.EMBER_BOLD_ITALIC.apply((TextView) view.findViewById(R.id.dm_no_saved_locations_description));
        this.res = view.getContext().getResources();
    }

    private void initAccessoryConnectionObserver() {
        Log.i(TAG, "initAccessoryConnectionObserver");
        ModeService modeService = (ModeService) ComponentRegistry.getInstance().get(ModeService.class).orNull();
        if (modeService != null) {
            this.mAccessoryConnectionStatusObservable = modeService.isDriveModeAccessoryDeviceConnected();
            updateAlexaHint(this.mAccessoryConnectionStatusObservable.getValue().booleanValue());
            this.mAccessoryConnectionStatusDisposable = this.mAccessoryConnectionStatusObservable.subscribe(new Consumer() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewController$hEr3jTGKjw8DX9Y5Ydbk73WpTt4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    NavigationViewController.this.lambda$initAccessoryConnectionObserver$0$NavigationViewController((Boolean) obj);
                }
            });
        }
    }

    private void setupOnClickNavigationEvent(SavedLocations.Item item, View view) {
        if (item == null) {
            return;
        }
        MappingApplication from = MappingApplication.from(this.mPreferredNavigationAppContentResolver.getPreferredNavigationApp());
        SavedLocations.Item.Address address = item.getAddress();
        if (this.mNavigationDataProvider == null) {
            return;
        }
        view.getContext().startActivity(from.getIntent(address.getAddressLine1() + " " + address.getCity() + " " + address.getPostalCode(), MappingApplication.DRIVING));
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.mDriveModeThemeManager;
    }

    Subscription getNetworkStatusSubscription() {
        return this.mNetworkStatusSubscription;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.dm_title_navigate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        if (getView() != null) {
            View view = getView();
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dm_no_network_banner);
            DriveModeSingleLineView driveModeSingleLineView = (DriveModeSingleLineView) view.findViewById(R.id.dm_navigation_create_location);
            TextView textView = (TextView) view.findViewById(R.id.dm_no_saved_locations_title);
            TextView textView2 = (TextView) view.findViewById(R.id.dm_no_saved_locations_description);
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.mDriveModeThemeManager.getTheme());
            if (z) {
                if (!this.mNavigationDataProvider.isSavedLocationFetched()) {
                    this.mNavigationDataProvider.requestNavigationSavedLocations();
                }
                linearLayout.setVisibility(8);
                textView.setVisibility(0);
                textView2.setVisibility(0);
                driveModeSingleLineView.setText(this.res.getString(R.string.drive_mode_navigation_save_location));
                driveModeSingleLineView.setImage(contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_navigation, contextThemeWrapper.getTheme()));
                return;
            }
            linearLayout.setVisibility(0);
            textView.setVisibility(8);
            textView2.setVisibility(8);
            updateLocationStatus(Boolean.valueOf(this.savedHomeOrWorkInNavRepo || this.savedLocationInNavRepo));
            if (this.savedLocationInNavRepo) {
                return;
            }
            driveModeSingleLineView.setText(this.res.getString(R.string.drive_mode_navigation_unable_to_load_saved_location));
            driveModeSingleLineView.setImage(contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_navigation_offline, contextThemeWrapper.getTheme()));
            driveModeSingleLineView.showUnderLine(false);
        }
    }

    void initNetworkConnectionManager() {
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$5eMf23y5d26F3KJhLL7rBNTl8kA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NavigationViewController.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    @VisibleForTesting
    void initializeNavigation(View view) {
        findNavigationViews(view);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.disposable = new CompositeDisposable();
        this.mRecyclerView.setAdapter(this.mNavigationCardManager.getAdapter());
        this.disposable.add(this.mNavigationDataProvider.queryLocations().subscribe(new Consumer() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$ysMsWh_p05tWytq04iAmL-6ANaM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                NavigationViewController.this.updateLocationStatus((Boolean) obj);
            }
        }));
        this.savedLocationInNavRepo = checkSavedLocationInNavRepo();
        this.savedHomeOrWorkInNavRepo = checkSavedHomeOrWorkInNavRepo();
    }

    public /* synthetic */ void lambda$findNavigationViews$1$NavigationViewController(View view, String str, View view2) {
        setupOnClickNavigationEvent(this.mNavigationDataProvider.getHome(), view);
        this.navigationMetrics.logHomeButtonSelected(str);
        DriverDistractionLog.logTouch(LogConstants.NAVIGATION, LogConstants.HOME_BUTTON);
    }

    public /* synthetic */ void lambda$findNavigationViews$2$NavigationViewController(View view, String str, View view2) {
        setupOnClickNavigationEvent(this.mNavigationDataProvider.getWork(), view);
        this.navigationMetrics.logWorkButtonSelected(str);
        DriverDistractionLog.logTouch(LogConstants.NAVIGATION, LogConstants.WORK_BUTTON);
    }

    public /* synthetic */ void lambda$initAccessoryConnectionObserver$0$NavigationViewController(Boolean bool) throws Throwable {
        String str = "Accessory connection status changed " + bool;
        updateAlexaHint(bool.booleanValue());
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.mDriveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_navigation, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        initializeNavigation(view);
        initNetworkConnectionManager();
        initAccessoryConnectionObserver();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        super.onDetach(view);
        this.disposable.clear();
        if (this.mAccessoryConnectionStatusDisposable != null) {
            Log.i(TAG, "Disposing accessory connection observable");
            this.mAccessoryConnectionStatusDisposable.dispose();
        }
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
    }

    void updateAlexaHint(boolean z) {
        String str = TAG;
        Log.i(str, "updateAlexaHint " + z);
        if (getView() != null) {
            View view = getView();
            TextView textView = (TextView) view.findViewById(R.id.dm_no_saved_locations_description);
            if (!z) {
                updateHint(textView, view.getResources().getStringArray(R.array.drive_mode_navigation_hints_stand_alone));
            } else {
                updateHint(textView, view.getResources().getStringArray(R.array.drive_mode_navigation_hints));
            }
        }
    }

    void updateHint(TextView textView, String[] strArr) {
        String string = getView().getContext().getResources().getString(R.string.entertainment_page_default_title);
        textView.setText(strArr[sHintIndex]);
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        int i = sHintIndex;
        sHintIndex = i + 1;
        sb.append(strArr[i]);
        textView.setContentDescription(sb.toString());
        sHintIndex %= strArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fe  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateLocationStatus(java.lang.Boolean r9) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.navigation.NavigationViewController.updateLocationStatus(java.lang.Boolean):void");
    }
}
