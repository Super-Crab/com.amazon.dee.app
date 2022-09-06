package com.amazon.alexa.drive.landing;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.entertainment.EntertainmentManagerV2;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class LandingPageViewControllerV2 extends ViewManagerViewController {
    private static final int ONE_COLUMN = 1;
    private static final String TAG = "LandingPageViewControllerV2";
    private static final int TWO_COLUMNS = 2;
    @Inject
    DriveModeThemeManager driveModeThemeManager;
    private LandingPageCardManagerV2 landingPageCardManager;
    @Inject
    LandingPageVoiceHintHelper landingPageVoiceHintHelper;
    @Inject
    AutoModeCommonUtils mAutoModeCommonUtils;
    @Inject
    EntertainmentManagerV2 mEntertainmentManager;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    @Inject
    NavigationCardManager navigationCardManager;
    @Inject
    TTCFRecordOnce ttcfRecordOnce;

    private void initDriveModeCards() {
        this.landingPageCardManager.getCardsFromProviders();
    }

    private void initLandingPageLayout(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.drive_mode_feed);
        this.landingPageCardManager = new LandingPageCardManagerV2(getContext(), this.ttcfRecordOnce, this.driveModeThemeManager);
        final LandingPageCardAdapterV2 adapter = this.landingPageCardManager.getAdapter();
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.amazon.alexa.drive.landing.LandingPageViewControllerV2.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (adapter.getItemViewType(i) == 3) {
                    return 2;
                }
                return (adapter.getDynamicCardsCount() % 2 != 0 && adapter.getDynamicCardsCount() % 2 == 1 && i == adapter.getItemCount() - 1) ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initTTTButton(View view) {
        view.findViewById(R.id.dm_voice_ingress_image).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$LandingPageViewControllerV2$9O1NDW3qWJECKFcmyJbNYz6a4j8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LandingPageViewControllerV2.this.lambda$initTTTButton$0$LandingPageViewControllerV2(view2);
            }
        });
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

    @VisibleForTesting
    void initNetworkConnectionManager() {
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.landing.-$$Lambda$hO1M5vHT24mRPDcgGy5mXsYhd70
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LandingPageViewControllerV2.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    public /* synthetic */ void lambda$initTTTButton$0$LandingPageViewControllerV2(View view) {
        this.mAutoModeCommonUtils.launchAlexa(getContext());
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.driveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_landing_page_v2, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAttach");
        outline107.append(hashCode());
        Log.i(str, outline107.toString());
        super.onAttach(view);
        this.navigationCardManager.start();
        this.mEntertainmentManager.init();
        initNetworkConnectionManager();
        this.landingPageVoiceHintHelper.displayVoiceHintV2(view);
        initLandingPageLayout(view);
        initDriveModeCards();
        initTTTButton(view);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        Log.i(TAG, "onCreate");
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDetach ");
        outline107.append(hashCode());
        Log.i(str, outline107.toString());
        this.landingPageCardManager.removeCardChangedListenerFromProviders();
        this.landingPageCardManager.clearCards();
        this.navigationCardManager.stop();
        this.mEntertainmentManager.deinit();
        this.landingPageVoiceHintHelper.onVoiceHintSwitch();
        super.onDetach(view);
    }
}
