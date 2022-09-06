package com.amazon.alexa.voice.ui.driveMode.local.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.voice.ui.driveMode.R;
import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalLocationProvider;
import com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.alexa.voice.ui.driveMode.util.DriverDistractionLog;
import com.amazon.alexa.voice.ui.driveMode.util.Fonts;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.StatusBar;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.regulator.ViewController;
import com.bumptech.glide.Glide;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class DriveModeLocalDetailController extends ViewController implements DriveModeLocalDetailContract.View, LocalDelegate {
    private static final String EXTRA_BACK_NAVIGATION = "back_navigation";
    private static final String EXTRA_BUSINESS = "business";
    private static final String TAG = "DriveModeLocalDetailController";
    private TextView addressView;
    private LocalCardModel.BusinessModel business;
    private ImageView businessRatingView;
    private TextView callView;
    private TextView dataProviderView;
    private TextView descriptionView;
    private TextView directionView;
    private TextView distanceView;
    private TextView nameView;
    private TextView openingHoursView;
    private DriveModeLocalDetailContract.Presenter presenter;
    private ImageView providerLogo;
    private TextView reviewCountView;
    private boolean supportsBackNavigation;

    public static DriveModeLocalDetailController create(LocalCardModel.BusinessModel businessModel, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_BUSINESS, businessModel);
        bundle.putBoolean(EXTRA_BACK_NAVIGATION, z);
        DriveModeLocalDetailController driveModeLocalDetailController = new DriveModeLocalDetailController();
        driveModeLocalDetailController.setArguments(bundle);
        return driveModeLocalDetailController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate
    public void close() {
        this.presenter.closeClicked();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void floodBackgroundToStatusBar() {
        StatusBar.tryFlood(getComponent(), getContext());
    }

    public /* synthetic */ void lambda$onCreateView$0$DriveModeLocalDetailController(View view) {
        this.presenter.phoneNumberClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$DriveModeLocalDetailController(View view) {
        this.presenter.addressClicked();
    }

    public /* synthetic */ void lambda$onCreateView$2$DriveModeLocalDetailController(View view) {
        this.presenter.backClicked();
    }

    public /* synthetic */ void lambda$onCreateView$3$DriveModeLocalDetailController(View view) {
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
        LocalDelegate localDelegate;
        super.onCreate();
        Bundle arguments = getArguments();
        this.supportsBackNavigation = true;
        if (arguments != null) {
            this.business = (LocalCardModel.BusinessModel) arguments.getParcelable(EXTRA_BUSINESS);
            this.supportsBackNavigation = arguments.getBoolean(EXTRA_BACK_NAVIGATION, true);
        }
        if (this.business == null) {
            Log.e(TAG, "Use DriveModeLocalDetailController.create(LocalCard.Business) to create a controller");
        }
        try {
            localDelegate = (LocalDelegate) getComponent().get(LocalDelegate.class);
        } catch (IllegalStateException unused) {
            localDelegate = this;
        }
        DriveModeLocalDetailInteractor driveModeLocalDetailInteractor = new DriveModeLocalDetailInteractor(this.business, new DriveModeLocalDetailMediator(this), localDelegate, new DriveModeLocalLocationProvider(this));
        Locale locale = ComponentUtils.getLocale(getComponent());
        this.presenter = new DriveModeLocalDetailPresenter(this, driveModeLocalDetailInteractor, new AndroidResources(getContext(), locale), (DriveModeCardMetricsInteractor) getComponent().get(DriveModeCardMetricsInteractor.class), new DriverDistractionLog(), locale);
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_drive_mode_local_detail, viewGroup, false);
        this.providerLogo = (ImageView) inflate.findViewById(R.id.dm_providerLogo);
        this.nameView = (TextView) inflate.findViewById(R.id.dm_businessName);
        this.businessRatingView = (ImageView) inflate.findViewById(R.id.dm_rating);
        this.reviewCountView = (TextView) inflate.findViewById(R.id.dm_reviewCount);
        this.directionView = (TextView) inflate.findViewById(R.id.dm_directions);
        this.callView = (TextView) inflate.findViewById(R.id.dm_make_call);
        this.addressView = (TextView) inflate.findViewById(R.id.dm_address);
        this.openingHoursView = (TextView) inflate.findViewById(R.id.dm_openingHours);
        this.descriptionView = (TextView) inflate.findViewById(R.id.dm_description);
        this.distanceView = (TextView) inflate.findViewById(R.id.dm_distance);
        this.dataProviderView = (TextView) inflate.findViewById(R.id.dm_data_from);
        Fonts.EMBER_BOLD.apply(this.nameView);
        Fonts.EMBER_REGULAR.apply(this.reviewCountView, this.addressView, this.openingHoursView, this.descriptionView, this.distanceView, this.directionView, this.callView, this.dataProviderView);
        inflate.findViewById(R.id.dm_call_bar).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.detail.-$$Lambda$DriveModeLocalDetailController$edaYDCcSDvPX3SeBuYQNkooCa0Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeLocalDetailController.this.lambda$onCreateView$0$DriveModeLocalDetailController(view);
            }
        });
        inflate.findViewById(R.id.dm_nav_bar).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.detail.-$$Lambda$DriveModeLocalDetailController$J_RCxakqj_h0Xclkcopuui04Ya8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeLocalDetailController.this.lambda$onCreateView$1$DriveModeLocalDetailController(view);
            }
        });
        View findViewById = inflate.findViewById(R.id.back);
        View findViewById2 = inflate.findViewById(R.id.close);
        if (this.supportsBackNavigation) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.detail.-$$Lambda$DriveModeLocalDetailController$3nucxaCxoAZN1Vf43h1h55eFJqQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DriveModeLocalDetailController.this.lambda$onCreateView$2$DriveModeLocalDetailController(view);
                }
            });
            findViewById.setVisibility(0);
            findViewById2.setVisibility(8);
        } else {
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.detail.-$$Lambda$DriveModeLocalDetailController$6Z5uWyIhcVwZ4tM7_EsS1uqXbe4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DriveModeLocalDetailController.this.lambda$onCreateView$3$DriveModeLocalDetailController(view);
                }
            });
            findViewById.setVisibility(8);
            findViewById2.setVisibility(0);
        }
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailController.1
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                DriveModeLocalDetailController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.nameView = null;
        this.reviewCountView = null;
        this.directionView = null;
        this.addressView = null;
        this.openingHoursView = null;
        this.descriptionView = null;
        this.distanceView = null;
        this.dataProviderView = null;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setAddress(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.addressView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setDataProvider(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.dataProviderView, charSequence, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setDescription(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.descriptionView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setDistance(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.distanceView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setName(CharSequence charSequence) {
        this.nameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setOpeningHours(CharSequence charSequence, CharSequence charSequence2, String str) {
        ViewUtils.setTextOrRemove(this.openingHoursView, charSequence, charSequence2);
        if (DriveModeVoxUiConstants.WARN.equals(str)) {
            this.openingHoursView.setTextColor(getContext().getResources().getColor(R.color.orangeWarning));
        }
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setProviderLogoAndDescription(String str, CharSequence charSequence, boolean z) {
        Glide.with(this.providerLogo).mo6762load(str).into(this.providerLogo);
        this.providerLogo.setContentDescription(charSequence);
        if (!z) {
            this.providerLogo.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setRating(CharSequence charSequence, String str, CharSequence charSequence2, boolean z) {
        if (URLUtil.isValidUrl(str)) {
            Glide.with(this.businessRatingView).mo6762load(str).into(this.businessRatingView);
        }
        this.businessRatingView.setContentDescription(charSequence2);
        if (!z) {
            this.businessRatingView.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.View
    public void setReviewCount(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        ViewUtils.setTextOrRemove(this.reviewCountView, charSequence, charSequence2);
        if (!z) {
            this.reviewCountView.setVisibility(8);
        }
    }
}
