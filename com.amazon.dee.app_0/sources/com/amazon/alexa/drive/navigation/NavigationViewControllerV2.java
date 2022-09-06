package com.amazon.alexa.drive.navigation;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drive.view.AutoModeSingleIconGridView;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class NavigationViewControllerV2 extends ViewManagerViewController {
    private static final String TAG = "NavigationViewControllerV2";
    private static int sHintIndex;
    private CompositeDisposable disposable;
    private AutoModeSingleIconGridView homeCard;
    @Inject
    AutoModeCommonUtils mAutoModeCommonUtils;
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    NavigationCardManager mNavigationCardManager;
    @Inject
    NavigationDataProvider mNavigationDataProvider;
    @Inject
    NavigationMetrics mNavigationMetrics;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    @Inject
    PreferredNavigationAppContentResolver mPreferredNavigationAppContentResolver;
    private RecyclerView mRecyclerView;
    @Inject
    TTCFRecordOnce mTTCFRecordOnce;
    private View myLocationText;
    private boolean savedHomeOrWorkInNavRepo;
    private boolean savedLocationInNavRepo;
    private AutoModeSingleIconGridView workCard;

    private boolean checkSavedHomeOrWorkInNavRepo() {
        return (this.mNavigationDataProvider.getHome() == null && this.mNavigationDataProvider.getWork() == null) ? false : true;
    }

    private boolean checkSavedLocationInNavRepo() {
        return (this.mNavigationDataProvider.getSavedLocations() == null || this.mNavigationDataProvider.getSavedLocations().size() == 0) ? false : true;
    }

    private void findNavigationViews(final View view) {
        this.myLocationText = view.findViewById(R.id.saved_locations_title);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.saved_locations_recycler_view);
        this.homeCard = (AutoModeSingleIconGridView) view.findViewById(R.id.navigation_home);
        this.workCard = (AutoModeSingleIconGridView) view.findViewById(R.id.navigation_work);
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        this.homeCard.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewControllerV2$RoFvoslYm7chzGbUeZmucbDuyo4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NavigationViewControllerV2.this.lambda$findNavigationViews$0$NavigationViewControllerV2(view, autoModeType, view2);
            }
        });
        this.workCard.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewControllerV2$S6-URHD9If1ESp21o25GjpvKZ9k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NavigationViewControllerV2.this.lambda$findNavigationViews$1$NavigationViewControllerV2(view, autoModeType, view2);
            }
        });
    }

    private void initTTTButton(View view) {
        view.findViewById(R.id.dm_voice_ingress_image).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationViewControllerV2$WKKHtMDRzXmxcSctQKCOVWC-ssI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NavigationViewControllerV2.this.lambda$initTTTButton$2$NavigationViewControllerV2(view2);
            }
        });
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

    private void updateHomeAndWork() {
        SavedLocations.Item home = this.mNavigationDataProvider.getHome();
        SavedLocations.Item work = this.mNavigationDataProvider.getWork();
        boolean z = true;
        boolean z2 = home != null;
        if (work == null) {
            z = false;
        }
        if (z2) {
            this.mNavigationMetrics.logHomeButtonDisplayed();
            this.homeCard.setVisibility(0);
        }
        if (z) {
            this.mNavigationMetrics.logWorkButtonDisplayed();
            this.workCard.setVisibility(0);
        }
    }

    private void updateSavedLocations() {
        List<SavedLocations.Item> savedLocations = this.mNavigationDataProvider.getSavedLocations();
        boolean z = (savedLocations == null || savedLocations.size() == 0) ? false : true;
        if (z) {
            this.myLocationText.setVisibility(0);
        } else {
            this.myLocationText.setVisibility(8);
        }
        this.mNavigationCardManager.updateSavedLocationsFeed();
        if (z) {
            this.mNavigationMetrics.logStoredLocationsDisplayed();
        } else {
            this.mNavigationMetrics.logStoredLocationErrorDisplayed();
        }
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.mDriveModeThemeManager;
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
            TextView textView = (TextView) view.findViewById(R.id.no_network_banner);
            TextView textView2 = (TextView) view.findViewById(R.id.auto_mode_hint);
            if (z) {
                textView.setVisibility(8);
                textView2.setVisibility(0);
                return;
            }
            textView.setVisibility(0);
            textView2.setVisibility(8);
        }
    }

    void initNetworkConnectionManager() {
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$ovfPyUdtBYGf0MvzTCnzjeiyFE4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NavigationViewControllerV2.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
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
        this.disposable.add(this.mNavigationDataProvider.queryLocations().subscribe(new Consumer() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$xhWAH514Dh3scFy1i_5qHq-ENd0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                NavigationViewControllerV2.this.updateLocationStatus((Boolean) obj);
            }
        }));
        this.savedLocationInNavRepo = checkSavedLocationInNavRepo();
        this.savedHomeOrWorkInNavRepo = checkSavedHomeOrWorkInNavRepo();
    }

    public /* synthetic */ void lambda$findNavigationViews$0$NavigationViewControllerV2(View view, String str, View view2) {
        setupOnClickNavigationEvent(this.mNavigationDataProvider.getHome(), view);
        this.mNavigationMetrics.logHomeButtonSelected(str);
        DriverDistractionLog.logTouch(LogConstants.NAVIGATION, LogConstants.HOME_BUTTON);
    }

    public /* synthetic */ void lambda$findNavigationViews$1$NavigationViewControllerV2(View view, String str, View view2) {
        setupOnClickNavigationEvent(this.mNavigationDataProvider.getWork(), view);
        this.mNavigationMetrics.logWorkButtonSelected(str);
        DriverDistractionLog.logTouch(LogConstants.NAVIGATION, LogConstants.WORK_BUTTON);
    }

    public /* synthetic */ void lambda$initTTTButton$2$NavigationViewControllerV2(View view) {
        this.mAutoModeCommonUtils.launchAlexa(getContext());
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.mDriveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_navigation_page_v2, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        initializeNavigation(view);
        initNetworkConnectionManager();
        updateAlexaHint();
        initTTTButton(view);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        super.onDetach(view);
    }

    void updateAlexaHint() {
        Log.i(TAG, "updateAlexaHint");
        if (getView() != null) {
            View view = getView();
            updateHint((TextView) view.findViewById(R.id.auto_mode_hint), view.getResources().getStringArray(R.array.drive_mode_navigation_hints_stand_alone));
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
    @VisibleForTesting
    public void updateLocationStatus(Boolean bool) {
        String str = "updateLocationStatus success ? " + bool;
        if (!bool.booleanValue()) {
            return;
        }
        updateHomeAndWork();
        updateSavedLocations();
        this.mTTCFRecordOnce.markComplete(TTCFRecordOnce.NAVIGATION_MAIN);
    }
}
