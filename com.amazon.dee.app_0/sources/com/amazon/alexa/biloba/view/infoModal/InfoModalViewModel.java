package com.amazon.alexa.biloba.view.infoModal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class InfoModalViewModel implements Parcelable {
    private String callClickedMetric;
    private String captionHeadlineText;
    private String clickMetricName;
    private String commsSectionDescription1;
    private String commsSectionTitle;
    private String description1;
    private String description2;
    private String description3;
    private String dropInClickedMetric;
    private String headlineText;
    private String messageClickedMetric;
    private String modalRenderedMetric;
    private String okButtonRoute;
    private String okButtonText;
    private Bundle routingBundle;
    @Inject
    Lazy<RoutingService> routingService;
    private boolean showCommsSection;
    private String titleText;
    private String viewMetricName;
    private static final String TAG = InfoModalViewModel.class.getSimpleName();
    public static final Parcelable.Creator<InfoModalViewModel> CREATOR = new Parcelable.Creator<InfoModalViewModel>() { // from class: com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public InfoModalViewModel mo944createFromParcel(Parcel parcel) {
            return new InfoModalViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public InfoModalViewModel[] mo945newArray(int i) {
            return new InfoModalViewModel[i];
        }
    };

    @VisibleForTesting
    InfoModalViewModel(Lazy<RoutingService> lazy) {
        this.routingService = lazy;
        this.routingBundle = new Bundle();
        this.showCommsSection = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCallClickedMetric() {
        return this.callClickedMetric;
    }

    public String getCaptionHeadlineText() {
        return this.captionHeadlineText;
    }

    public String getClickMetricName() {
        return this.clickMetricName;
    }

    public String getCommsSectionDescription1() {
        return this.commsSectionDescription1;
    }

    public String getCommsSectionTitle() {
        return this.commsSectionTitle;
    }

    public String getDescription1() {
        return this.description1;
    }

    public String getDescription2() {
        return this.description2;
    }

    public String getDescription3() {
        return this.description3;
    }

    public String getDropInClickedMetric() {
        return this.dropInClickedMetric;
    }

    public String getHeadlineText() {
        return this.headlineText;
    }

    public String getMessageClickedMetric() {
        return this.messageClickedMetric;
    }

    public String getModalRenderedMetric() {
        return this.modalRenderedMetric;
    }

    public String getOkButtonRoute() {
        return this.okButtonRoute;
    }

    public String getOkButtonText() {
        return this.okButtonText;
    }

    public Bundle getRoutingBundle() {
        return this.routingBundle;
    }

    public boolean getShowCommsSection() {
        return this.showCommsSection;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public String getViewMetricName() {
        return this.viewMetricName;
    }

    public void onTapOkButton() {
        Lazy<RoutingService> lazy = this.routingService;
        if (lazy == null) {
            LogUtils.e(TAG, "Routing service is null");
        } else if (this.okButtonRoute != null) {
            lazy.mo358get().route(this.okButtonRoute).withAll(this.routingBundle).navigate();
        } else if (lazy.mo358get().canNavigateBackward()) {
            LogUtils.e(TAG, "OK button route not defined navigating back");
            this.routingService.mo358get().navigateBackward();
        } else {
            BilobaRouteUtil.routeTo(this.routingService.mo358get(), "biloba");
        }
    }

    public InfoModalViewModel setCaptionHeadlineText(String str) {
        this.captionHeadlineText = str;
        return this;
    }

    public InfoModalViewModel setClickMetricName(String str) {
        this.clickMetricName = str;
        return this;
    }

    public InfoModalViewModel setCommsMetrics(String str, String str2, String str3) {
        this.dropInClickedMetric = str;
        this.callClickedMetric = str2;
        this.messageClickedMetric = str3;
        return this;
    }

    public InfoModalViewModel setCommsSectionDescription1(String str) {
        this.commsSectionDescription1 = str;
        return this;
    }

    public InfoModalViewModel setCommsSectionTitle(String str) {
        this.commsSectionTitle = str;
        return this;
    }

    public InfoModalViewModel setDescription1(String str) {
        this.description1 = str;
        return this;
    }

    public InfoModalViewModel setDescription2(String str) {
        this.description2 = str;
        return this;
    }

    public InfoModalViewModel setDescription3(String str) {
        this.description3 = str;
        return this;
    }

    public InfoModalViewModel setHeadlineText(String str) {
        this.headlineText = str;
        return this;
    }

    public InfoModalViewModel setModalRenderedMetric(String str) {
        this.modalRenderedMetric = str;
        return this;
    }

    public InfoModalViewModel setOkButtonRoute(String str) {
        this.okButtonRoute = str;
        return this;
    }

    public InfoModalViewModel setOkButtonText(String str) {
        this.okButtonText = str;
        return this;
    }

    public InfoModalViewModel setRoutingBundle(@NonNull Bundle bundle) {
        this.routingBundle = bundle;
        return this;
    }

    public InfoModalViewModel setShowCommsSection(boolean z) {
        this.showCommsSection = z;
        return this;
    }

    public InfoModalViewModel setTitleText(String str) {
        this.titleText = str;
        return this;
    }

    public InfoModalViewModel setViewMetricName(String str) {
        this.viewMetricName = str;
        return this;
    }

    public void show() {
        Lazy<RoutingService> lazy = this.routingService;
        if (lazy == null) {
            LogUtils.e(TAG, "Routing service is null");
        } else {
            lazy.mo358get().route(Routes.BILOBA_INFO_MODAL).withAll(this.routingBundle).with(RouteArgumentKeys.PARCEL, this).navigate();
        }
    }

    public InfoModalViewModel with(@NonNull String str, String str2) {
        this.routingBundle.putString(str, str2);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.titleText);
        parcel.writeString(this.captionHeadlineText);
        parcel.writeString(this.headlineText);
        parcel.writeString(this.description1);
        parcel.writeString(this.description2);
        parcel.writeString(this.description3);
        parcel.writeInt(this.showCommsSection ? 1 : 0);
        parcel.writeString(this.commsSectionTitle);
        parcel.writeString(this.commsSectionDescription1);
        parcel.writeString(this.viewMetricName);
        parcel.writeString(this.clickMetricName);
        parcel.writeString(this.okButtonText);
        parcel.writeString(this.okButtonRoute);
        parcel.writeBundle(this.routingBundle);
    }

    public InfoModalViewModel with(@NonNull String str, int i) {
        this.routingBundle.putInt(str, i);
        return this;
    }

    public InfoModalViewModel with(@NonNull String str, Bundle bundle) {
        this.routingBundle.putBundle(str, bundle);
        return this;
    }

    public InfoModalViewModel() {
        BilobaDependencies.inject(this);
        this.routingBundle = new Bundle();
        this.showCommsSection = false;
    }

    protected InfoModalViewModel(Parcel parcel) {
        this.titleText = parcel.readString();
        this.captionHeadlineText = parcel.readString();
        this.headlineText = parcel.readString();
        this.description1 = parcel.readString();
        this.description2 = parcel.readString();
        this.description3 = parcel.readString();
        this.showCommsSection = parcel.readInt() != 1 ? false : true;
        this.commsSectionTitle = parcel.readString();
        this.commsSectionDescription1 = parcel.readString();
        this.viewMetricName = parcel.readString();
        this.clickMetricName = parcel.readString();
        this.okButtonText = parcel.readString();
        this.okButtonRoute = parcel.readString();
        this.routingBundle = parcel.readBundle();
    }
}
