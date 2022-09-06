package com.amazon.alexa.voice.ui.onedesign.local.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LocalDetailController extends ViewController implements LocalDetailContract.View, LocalDelegate {
    private static final String EXTRA_BACK_NAVIGATION = "back_navigation";
    private static final String EXTRA_BUSINESS = "business";
    private TextView addressView;
    private ImageView businessImageView;
    private ImageView businessRatingView;
    private TextView descriptionView;
    private TextView distanceView;
    private TextView linkView;
    private TextView nameView;
    private TextView openingHoursView;
    private TextView phoneNumberView;
    private LocalDetailContract.Presenter presenter;
    private ImageView providerLogo;
    private TextView providerView;
    private TextView reviewCountView;
    private boolean supportsBackNavigation;

    public static LocalDetailController create(LocalCardModel.BusinessModel businessModel, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_BUSINESS, businessModel);
        bundle.putBoolean(EXTRA_BACK_NAVIGATION, z);
        LocalDetailController localDetailController = new LocalDetailController();
        localDetailController.setArguments(bundle);
        return localDetailController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate
    public void close() {
        this.presenter.closeClicked();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$LocalDetailController(View view) {
        this.presenter.linkClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$LocalDetailController(View view) {
        this.presenter.phoneNumberClicked();
    }

    public /* synthetic */ void lambda$onCreateView$2$LocalDetailController(View view) {
        this.presenter.addressClicked();
    }

    public /* synthetic */ void lambda$onCreateView$3$LocalDetailController(View view) {
        this.presenter.backClicked();
    }

    public /* synthetic */ void lambda$onCreateView$4$LocalDetailController(View view) {
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
        LocalCardModel.BusinessModel businessModel = arguments != null ? (LocalCardModel.BusinessModel) arguments.getParcelable(EXTRA_BUSINESS) : null;
        this.supportsBackNavigation = true;
        if (arguments != null) {
            this.supportsBackNavigation = arguments.getBoolean(EXTRA_BACK_NAVIGATION, true);
        }
        if (businessModel != null) {
            try {
                localDelegate = (LocalDelegate) getComponent().get(LocalDelegate.class);
            } catch (IllegalStateException unused) {
                localDelegate = this;
            }
            LocalDetailInteractor localDetailInteractor = new LocalDetailInteractor(businessModel, new LocalDetailMediator(this), localDelegate);
            Locale locale = ComponentUtils.getLocale(getComponent());
            this.presenter = new LocalDetailPresenter(this, localDetailInteractor, new AndroidResources(getContext(), locale), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class), locale);
            return;
        }
        throw new IllegalStateException("Use LocalDetailController.create(LocalCard.Business) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_local_detail, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.providerView = (TextView) inflate.findViewById(R.id.providerInfo);
        this.providerLogo = (ImageView) inflate.findViewById(R.id.providerLogo);
        this.businessImageView = (ImageView) inflate.findViewById(R.id.businessImage);
        this.nameView = (TextView) inflate.findViewById(R.id.businessName);
        this.businessRatingView = (ImageView) inflate.findViewById(R.id.rating);
        this.reviewCountView = (TextView) inflate.findViewById(R.id.reviewCount);
        this.addressView = (TextView) inflate.findViewById(R.id.address);
        this.phoneNumberView = (TextView) inflate.findViewById(R.id.phoneNumber);
        this.openingHoursView = (TextView) inflate.findViewById(R.id.openingHours);
        this.descriptionView = (TextView) inflate.findViewById(R.id.description);
        this.distanceView = (TextView) inflate.findViewById(R.id.distance);
        this.linkView = (TextView) inflate.findViewById(R.id.link);
        this.linkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.-$$Lambda$LocalDetailController$4fmsT5Y6pX-joxmcFQsqp1EFn18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalDetailController.this.lambda$onCreateView$0$LocalDetailController(view);
            }
        });
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.linkView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.nameView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.reviewCountView, this.addressView, this.phoneNumberView, this.openingHoursView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.descriptionView, this.distanceView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.providerView);
        this.phoneNumberView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.-$$Lambda$LocalDetailController$yux98H_k9iQYbF2S676vGmSHVWk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalDetailController.this.lambda$onCreateView$1$LocalDetailController(view);
            }
        });
        this.addressView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.-$$Lambda$LocalDetailController$_u8FkZ8OGJG63cc_Y7d8diSCcWg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalDetailController.this.lambda$onCreateView$2$LocalDetailController(view);
            }
        });
        View findViewById = inflate.findViewById(R.id.back);
        View findViewById2 = inflate.findViewById(R.id.close);
        if (this.supportsBackNavigation) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.-$$Lambda$LocalDetailController$As2afJHUCSRLRe1EWUXG850Gh_g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LocalDetailController.this.lambda$onCreateView$3$LocalDetailController(view);
                }
            });
            findViewById.setVisibility(0);
            findViewById2.setVisibility(8);
        } else {
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.-$$Lambda$LocalDetailController$wDXfYmj0cykp4E6CeIhrn0GHP6c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LocalDetailController.this.lambda$onCreateView$4$LocalDetailController(view);
                }
            });
            findViewById.setVisibility(8);
            findViewById2.setVisibility(0);
        }
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailController.1
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                LocalDetailController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        Glide.with(getContext().getApplicationContext()).clear(this.businessImageView);
        this.businessImageView = null;
        this.nameView = null;
        this.reviewCountView = null;
        this.addressView = null;
        this.phoneNumberView = null;
        this.openingHoursView = null;
        this.descriptionView = null;
        this.distanceView = null;
        this.linkView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setAddress(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.addressView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setDescription(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.descriptionView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setDistance(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.distanceView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setImageUrl(String str) {
        Glide.with(getContext().getApplicationContext()).clear(this.businessImageView);
        if (str != null) {
            Glide.with(getContext().getApplicationContext()).mo6762load(str).mo1615apply(new RequestOptions().mo1574circleCrop()).into(this.businessImageView);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setLinkText(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.linkView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setMergedProviderInfo(String str) {
        ViewUtils.setTextOrRemove(this.providerView, str, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setName(CharSequence charSequence) {
        this.nameView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setOpeningHours(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.openingHoursView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setPhoneNumber(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.phoneNumberView, charSequence, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setProviderLogoAndDescription(String str, CharSequence charSequence) {
        Glide.with(this.providerLogo).mo6762load(str).into(this.providerLogo);
        this.providerLogo.setContentDescription(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setRating(CharSequence charSequence, String str, CharSequence charSequence2) {
        if (URLUtil.isValidUrl(str)) {
            Glide.with(this.businessImageView).mo6762load(str).into(this.businessRatingView);
        }
        this.businessRatingView.setContentDescription(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.View
    public void setReviewCount(CharSequence charSequence, CharSequence charSequence2) {
        ViewUtils.setTextOrRemove(this.reviewCountView, charSequence, charSequence2);
    }
}
