package com.amazon.alexa.drive.landing;

import android.content.Context;
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
import com.amazon.alexa.drive.comms.CommsManager;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.entertainment.EntertainmentManager;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.smart.device.SmartDevicePresenter;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mode.ModeService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class LandingPageViewController extends ViewManagerViewController {
    private static final String TAG = "LandingPageViewController";
    static int sLandingPageViewControllerInstance;
    @Inject
    CommsManager commsManager;
    @Inject
    DriveModeThemeManager driveModeThemeManager;
    private LandingPageCardManager landingPageCardManager;
    @Inject
    LandingPageVoiceHintHelper landingPageVoiceHintHelper;
    @Inject
    EntertainmentManager mEntertainmentManager;
    @Inject
    EventBus mEventBus;
    @Inject
    ModeService modeService;
    @Inject
    NavigationCardManager navigationCardManager;
    @Inject
    PreferredNavigationAppContentResolver navigationContentResolver;
    @Inject
    NetworkConnectivityManager networkConnectivityManager;
    private RecyclerView recyclerView;
    @Inject
    SmartDevicePresenter smartDevicePresenter;
    @Inject
    TTCFRecordOnce ttcfRecordOnce;
    @Inject
    WeblabManager weblabManager;

    private void initDriveModeCards(View view) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.driveModeThemeManager.getTheme());
        this.recyclerView = (RecyclerView) view.findViewById(R.id.drive_mode_feed);
        this.recyclerView.setHasFixedSize(false);
        LandingPageRecyclerViewMarginDecoration landingPageRecyclerViewMarginDecoration = new LandingPageRecyclerViewMarginDecoration();
        landingPageRecyclerViewMarginDecoration.setDrawable(getView().getContext().getResources().getDrawable(R.drawable.landing_page_list_divider, contextThemeWrapper.getTheme()));
        this.recyclerView.addItemDecoration(landingPageRecyclerViewMarginDecoration);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.landingPageCardManager = new LandingPageCardManager(getContext(), this.ttcfRecordOnce, this.driveModeThemeManager);
        this.recyclerView.setAdapter(this.landingPageCardManager.getAdapter());
        this.landingPageCardManager.getCardsFromProviders();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setupNetworkConnectionBanner$0(LinearLayout linearLayout, TextView textView, TextView textView2, Boolean bool) {
        if (bool.booleanValue()) {
            linearLayout.setVisibility(8);
            textView.setVisibility(0);
            textView2.setVisibility(0);
            return;
        }
        linearLayout.setVisibility(0);
        textView.setVisibility(8);
        textView2.setVisibility(8);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    public DriveModeThemeManager getDriveModeThemeManager() {
        return this.driveModeThemeManager;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.dm_title_landing_page);
    }

    @VisibleForTesting
    void initializeVuiVisibility(TextView textView, TextView textView2) {
        ModeService modeService = this.modeService;
        int i = 0;
        boolean z = modeService != null && modeService.isTtsDeviceJustRegistered();
        textView.setVisibility(z ? 8 : 0);
        if (z) {
            i = 8;
        }
        textView2.setVisibility(i);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.driveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_landing_page, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAttach");
        outline107.append(hashCode());
        Log.i(str, outline107.toString());
        super.onAttach(view);
        sLandingPageViewControllerInstance = hashCode();
        this.commsManager.init();
        this.mEntertainmentManager.init();
        this.navigationCardManager.start();
        if (this.weblabManager.isSmartHomeWeblabEnabled()) {
            this.smartDevicePresenter.init();
        }
        initDriveModeCards(view);
        setupNetworkConnectionBanner(view);
        this.landingPageVoiceHintHelper.displayVoiceHint(view);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        Log.i(TAG, "onCreate");
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        if (sLandingPageViewControllerInstance != hashCode()) {
            Log.i(TAG, "Incorrect order of view controller life cycle event, ignoring");
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDetach ");
        outline107.append(hashCode());
        Log.i(str, outline107.toString());
        this.landingPageCardManager.removeCardChangedListenerFromProviders();
        this.landingPageCardManager.clearCards();
        this.commsManager.deinit();
        this.mEntertainmentManager.deinit();
        this.navigationCardManager.stop();
        if (this.weblabManager.isSmartHomeWeblabEnabled()) {
            this.smartDevicePresenter.deinit();
        }
        this.landingPageVoiceHintHelper.onVoiceHintSwitch();
        super.onDetach(view);
    }

    @VisibleForTesting
    void setupNetworkConnectionBanner(View view) {
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dm_no_network_banner);
        linearLayout.setVisibility(this.networkConnectivityManager.isNetworkConnected() ? 8 : 0);
        final TextView textView = (TextView) view.findViewById(R.id.dm_vui_landing_page_hint_title);
        final TextView textView2 = (TextView) view.findViewById(R.id.dm_vui_landing_page_hint);
        initializeVuiVisibility(textView, textView2);
        this.networkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageViewController$zY3Q7QIkdeb8lj8laPFiL3VHPGU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LandingPageViewController.lambda$setupNetworkConnectionBanner$0(linearLayout, textView, textView2, (Boolean) obj);
            }
        });
    }
}
