package com.amazon.alexa.drive.comms.view;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.view.DriveModeSingleLineView;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class CommsLandingPageViewController extends ViewManagerViewController implements CommsLandingPageContract.View {
    private static final String TAG = "CommsLandingPageViewController";
    @Inject
    CommsLandingPageContract.Presenter mCommsLandingPagePresenter;
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;

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
        return context.getString(R.string.dm_title_communication);
    }

    void handleAnnouncementCardClick() {
        Log.i(TAG, "handleAnnouncementCardClick");
        this.mCommsLandingPagePresenter.onAnnouncementCardClick();
    }

    void handleCallCardClick() {
        Log.i(TAG, "handleCallCardClick");
        this.mCommsLandingPagePresenter.onCallCardClick();
    }

    void handleDropInCardClick() {
        Log.i(TAG, "handleDropInCardClick");
        this.mCommsLandingPagePresenter.onDropInCardClick();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        String str = TAG;
        Log.i(str, "handleNetworkConnectionChange " + z);
        View view = getView();
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dm_no_network_banner);
            DriveModeSingleLineView driveModeSingleLineView = (DriveModeSingleLineView) view.findViewById(R.id.drop_in_card);
            DriveModeSingleLineView driveModeSingleLineView2 = (DriveModeSingleLineView) view.findViewById(R.id.announcement_card);
            if (z) {
                linearLayout.setVisibility(8);
                driveModeSingleLineView.setVisibility(0);
                driveModeSingleLineView2.setVisibility(0);
                return;
            }
            linearLayout.setVisibility(0);
            driveModeSingleLineView.setVisibility(8);
            driveModeSingleLineView2.setVisibility(8);
        }
    }

    void initCommsCardViews() {
        Log.i(TAG, "initCommsCardViews");
        View view = getView();
        if (view != null) {
            DriveModeSingleLineView driveModeSingleLineView = (DriveModeSingleLineView) view.findViewById(R.id.call_card);
            driveModeSingleLineView.setVisibility(0);
            driveModeSingleLineView.showUnderLine(true);
            DriveModeSingleLineView driveModeSingleLineView2 = (DriveModeSingleLineView) view.findViewById(R.id.drop_in_card);
            driveModeSingleLineView2.setVisibility(0);
            driveModeSingleLineView2.showUnderLine(true);
            DriveModeSingleLineView driveModeSingleLineView3 = (DriveModeSingleLineView) view.findViewById(R.id.announcement_card);
            driveModeSingleLineView3.setVisibility(0);
            driveModeSingleLineView3.showUnderLine(true);
        }
    }

    void initNetworkConnectionManager() {
        Log.i(TAG, "initNetworkConnectionManager");
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.comms.view.-$$Lambda$s-cAOBBKfexfS4q4iZHiBp86U8g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommsLandingPageViewController.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    void initOnClickListeners(View view) {
        if (view != null) {
            ((DriveModeSingleLineView) view.findViewById(R.id.call_card)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.comms.view.-$$Lambda$CommsLandingPageViewController$M7Uap7uL_RX1XPjusWt1Q4wynqQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CommsLandingPageViewController.this.lambda$initOnClickListeners$0$CommsLandingPageViewController(view2);
                }
            });
            ((DriveModeSingleLineView) view.findViewById(R.id.drop_in_card)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.comms.view.-$$Lambda$CommsLandingPageViewController$DVOQz3_b2H9Bsb4CjZaZY7muV8Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CommsLandingPageViewController.this.lambda$initOnClickListeners$1$CommsLandingPageViewController(view2);
                }
            });
            ((DriveModeSingleLineView) view.findViewById(R.id.announcement_card)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.comms.view.-$$Lambda$CommsLandingPageViewController$-wS9wvqtf4FnCJLXfXen3jjGiX8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CommsLandingPageViewController.this.lambda$initOnClickListeners$2$CommsLandingPageViewController(view2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initOnClickListeners$0$CommsLandingPageViewController(View view) {
        handleCallCardClick();
    }

    public /* synthetic */ void lambda$initOnClickListeners$1$CommsLandingPageViewController(View view) {
        handleDropInCardClick();
    }

    public /* synthetic */ void lambda$initOnClickListeners$2$CommsLandingPageViewController(View view) {
        handleAnnouncementCardClick();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Log.i(TAG, "makeView");
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.mDriveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        View inflate = cloneInContext.inflate(R.layout.view_controller_comms_landing_page, viewGroup, false);
        initOnClickListeners(inflate);
        viewGroup2.addView(inflate);
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        Log.i(TAG, "onAttach");
        this.mCommsLandingPagePresenter.initialize(this);
        initCommsCardViews();
        initNetworkConnectionManager();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        Log.i(TAG, "onCreate");
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        super.onDetach(view);
        Log.i(TAG, "onDetach");
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
    }
}
