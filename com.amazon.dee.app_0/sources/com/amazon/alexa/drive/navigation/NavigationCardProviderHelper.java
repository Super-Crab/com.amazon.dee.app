package com.amazon.alexa.drive.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.cards.OnCardClickListenerInterface;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.NavigationCardProviderHelper;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse;
import com.amazon.alexa.drive.navigation.traffic.TrafficCard;
import com.amazon.alexa.drive.navigation.traffic.TrafficCardV2;
import com.amazon.alexa.drive.navigation.traffic.TrafficConstants;
import com.amazon.alexa.drive.navigation.traffic.TrafficErrorDialogFragment;
import com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager;
import com.amazon.alexa.drive.service.DefaultDriveModeService;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DefaultDriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SingleIconCard;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class NavigationCardProviderHelper {
    private static final int NAVIGATION_CARD_ID = 256;
    private static final int RANGE_HOME_IN_METERS = 800;
    private static final String TAG = "NavigationCardProviderHelper";
    private final OnCardClickListenerInterface cardClickListener;
    private final Context context;
    private final DefaultDriveModeService driveModeService;
    private final DriveModeThemeManager driveModeThemeManager;
    private DefaultDriveModeCardsProvider landingPageCardProvider;
    private CurrentLocationManager locationManager;
    @VisibleForTesting
    LocationPublisher locationPublisher;
    private final NavigationDataProvider navigationDataProvider;
    private final NavigationMetrics navigationMetrics;
    private final boolean trafficNotificationFeatureEnabled;
    @VisibleForTesting
    TrafficNotificationManager trafficNotificationManager;
    private final WeblabManager weblabManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class DefaultNavigationCardProvider extends DefaultDriveModeCardsProvider implements TrafficNotificationManager.TrafficNotificationRequestCallback, TrafficCard.TrafficCardCallback {
        private boolean cardAdded;
        private final OnCardClickListenerInterface cardClickListenerInterface;
        private final Context context;
        private final CurrentLocationManager currentLocationManager;
        private final LocationPublisher locationPublisher;
        private DriveModeThemeManager mDriveModeThemeManager;
        private boolean mLatencyTimerEnded;
        private boolean mLatencyTimerStarted;
        private TrafficCard mTrafficCard;
        private String mTrafficCardCorrelationId;
        private boolean mTrafficCardFeatureEnabled;
        private final NavigationDataProvider navigationDataProvider;
        private final NavigationMetrics navigationMetrics;
        private final TrafficNotificationManager trafficNotificationManager;
        private boolean mIsNetworkConnected = true;
        private final Handler handler = new Handler(Looper.getMainLooper());

        DefaultNavigationCardProvider(Context context, CurrentLocationManager currentLocationManager, NavigationDataProvider navigationDataProvider, NavigationMetrics navigationMetrics, OnCardClickListenerInterface onCardClickListenerInterface, TrafficNotificationManager trafficNotificationManager, LocationPublisher locationPublisher, DriveModeThemeManager driveModeThemeManager) {
            this.context = context;
            this.currentLocationManager = currentLocationManager;
            this.navigationMetrics = navigationMetrics;
            this.navigationDataProvider = navigationDataProvider;
            this.trafficNotificationManager = trafficNotificationManager;
            this.locationPublisher = locationPublisher;
            this.cardClickListenerInterface = onCardClickListenerInterface;
            this.mDriveModeThemeManager = driveModeThemeManager;
        }

        private void getLocalNavigationCard() {
            NavigationIconCard navigationIconCard;
            SavedLocations.Item home = this.navigationDataProvider.getHome();
            SavedLocations.Item work = this.navigationDataProvider.getWork();
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.mDriveModeThemeManager.getTheme());
            if (home == null && work == null) {
                Log.e(NavigationCardProviderHelper.TAG, "No home or work cards!");
                return;
            }
            Location currentLocation = this.currentLocationManager.getCurrentLocation();
            DriveModeCardChangeListener cardChangeListener = getCardChangeListener();
            Location location = new Location("");
            if (home != null) {
                location.setLatitude(home.getCoordinate().getLatitudeInDegrees().doubleValue());
                location.setLongitude(home.getCoordinate().getLongitudeInDegrees().doubleValue());
            }
            if (home != null && (work == null || currentLocation == null || currentLocation.distanceTo(location) > 800.0f)) {
                navigationIconCard = new NavigationIconCard(contextThemeWrapper.getResources().getString(R.string.dm_card_nav_home), "", contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_home, contextThemeWrapper.getTheme()), home, this.cardClickListenerInterface);
                this.navigationMetrics.logHomeLandingCardDisplayed();
            } else {
                navigationIconCard = new NavigationIconCard(contextThemeWrapper.getResources().getString(R.string.dm_card_nav_work), "", contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_work, contextThemeWrapper.getTheme()), work, this.cardClickListenerInterface);
                this.navigationMetrics.logWorkLandingCardDisplayed();
            }
            if (this.cardAdded) {
                cardChangeListener.updateCard(navigationIconCard);
                return;
            }
            cardChangeListener.addCard(navigationIconCard);
            this.cardAdded = true;
        }

        private void onTrafficCardClicked(String str, String str2) {
            this.trafficNotificationManager.requestTrafficDetails(this, this.mTrafficCardCorrelationId);
            this.trafficNotificationManager.requestTrafficUpdate(this, str, str2);
        }

        private void requestTrafficDestination() {
            this.trafficNotificationManager.requestTrafficDestination(this);
        }

        private void updateTrafficCardWithCorrectDetails() {
            SavedLocations.Item home = this.navigationDataProvider.getHome();
            SavedLocations.Item work = this.navigationDataProvider.getWork();
            if (this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD) && work != null) {
                this.mTrafficCard.setItem(work);
            } else if (!this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD) || home == null) {
                Log.e(NavigationCardProviderHelper.TAG, "Cannot update unknown traffic card type");
                this.mTrafficCard = null;
                return;
            } else {
                this.mTrafficCard.setItem(home);
            }
            getCardChangeListener().updateCard(this.mTrafficCard);
        }

        public /* synthetic */ void lambda$onTrafficUpdateRequested$2$NavigationCardProviderHelper$DefaultNavigationCardProvider(String str, String str2, boolean z) {
            onTrafficCardClicked(str, str2);
        }

        public /* synthetic */ void lambda$provideCards$0$NavigationCardProviderHelper$DefaultNavigationCardProvider(boolean z) {
            requestTrafficDestination();
        }

        public /* synthetic */ void lambda$setNetworkConnected$1$NavigationCardProviderHelper$DefaultNavigationCardProvider(boolean z) {
            requestTrafficDestination();
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onCommuteDestinationReceived(GetCommuteDestinationResponse getCommuteDestinationResponse) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.mDriveModeThemeManager.getTheme());
            if (this.mTrafficCard != null) {
                Log.e(NavigationCardProviderHelper.TAG, "Traffic card displayed already no need to add again");
            } else if (getCardChangeListener() == null) {
                Log.e(NavigationCardProviderHelper.TAG, "Landing page was already minimized so ignoring this request");
            } else {
                if (getCommuteDestinationResponse.getDestinationAddressType().equals(TrafficConstants.HOME_CARD) && this.navigationDataProvider.getHome() != null) {
                    this.mTrafficCard = new TrafficCard(contextThemeWrapper, contextThemeWrapper.getResources().getString(R.string.dm_card_nav_home), contextThemeWrapper.getResources().getString(R.string.traffic_default_status), contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_home, contextThemeWrapper.getTheme()), this.navigationDataProvider.getHome(), this.currentLocationManager.getCurrentLocation(), getCommuteDestinationResponse.getDestinationAddressLabel(), getCommuteDestinationResponse.getDestinationAddressType(), this, this.mDriveModeThemeManager);
                    this.currentLocationManager.registerForLocationUpdates(this.mTrafficCard);
                    this.navigationMetrics.logTrafficCardDisplayed("Home");
                } else if (getCommuteDestinationResponse.getDestinationAddressType().equals(TrafficConstants.WORK_CARD) && this.navigationDataProvider.getWork() != null) {
                    this.mTrafficCard = new TrafficCard(contextThemeWrapper, contextThemeWrapper.getResources().getString(R.string.dm_card_nav_work), contextThemeWrapper.getResources().getString(R.string.traffic_default_status), contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_work, contextThemeWrapper.getTheme()), this.navigationDataProvider.getWork(), this.currentLocationManager.getCurrentLocation(), getCommuteDestinationResponse.getDestinationAddressLabel(), getCommuteDestinationResponse.getDestinationAddressType(), this, this.mDriveModeThemeManager);
                    this.currentLocationManager.registerForLocationUpdates(this.mTrafficCard);
                    this.navigationMetrics.logTrafficCardDisplayed(NavigationMetrics.CardType.WORK);
                }
                if (this.mTrafficCard == null) {
                    return;
                }
                getCardChangeListener().addCard(this.mTrafficCard);
                this.trafficNotificationManager.requestTrafficDetails(this, getCommuteDestinationResponse.getCorrelationId());
                this.mTrafficCardCorrelationId = getCommuteDestinationResponse.getCorrelationId();
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onCommuteDetailsReceived(GetCommuteDetailsResponse getCommuteDetailsResponse) {
            String str = NavigationCardProviderHelper.TAG;
            Log.i(str, "getCommuteDetailsResponse | " + getCommuteDetailsResponse);
            this.mTrafficCard.onTrafficDataUpdated(getCommuteDetailsResponse.getEstimatedTravelTimeInSeconds(), getCommuteDetailsResponse.getTrafficCondition(), getCommuteDetailsResponse.getEstimatedDelayInSeconds());
            if (!this.mLatencyTimerEnded) {
                this.navigationMetrics.logTrafficCardLatencyMeasuringEndedWithTimers();
                this.mLatencyTimerEnded = true;
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCard.TrafficCardCallback
        public void onNavigationClicked(SavedLocations.Item item) {
            NavigationCard navigationCard = new NavigationCard();
            navigationCard.setItem(item);
            this.cardClickListenerInterface.onCardClick(navigationCard);
            String autoModeType = DriveModeMetricsHelper.getAutoModeType();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficCardNavigateSelected(autoModeType, NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficCardNavigateSelected(autoModeType, "Home");
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onSendCommuteNotificationError() {
            if (getCardChangeListener() == null) {
                Log.e(NavigationCardProviderHelper.TAG, "CardProvider is not currently attached. So ignoring request");
                return;
            }
            Activity activity = (Activity) this.context;
            FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
            Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag(TrafficConstants.TRAFFIC_NOTIFICATION_ERROR_FRAGMENT_TAG);
            if ((findFragmentByTag instanceof TrafficErrorDialogFragment) && findFragmentByTag.isVisible()) {
                return;
            }
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            beginTransaction.addToBackStack(null);
            beginTransaction.add(new TrafficErrorDialogFragment(), TrafficConstants.TRAFFIC_NOTIFICATION_ERROR_FRAGMENT_TAG).commitAllowingStateLoss();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficErrorDisplayed(NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficErrorDisplayed("Home");
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCard.TrafficCardCallback
        public void onTrafficCardUpdated() {
            if (getCardChangeListener() == null) {
                Log.i(NavigationCardProviderHelper.TAG, "onTrafficCardUpdated | card change listener is null so returning");
            } else {
                getCardChangeListener().updateCard(this.mTrafficCard);
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCard.TrafficCardCallback
        public void onTrafficUpdateRequested(final String str, final String str2) {
            this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProvider$ruCck2638IWnRhNSCPENEa0MR04
                @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                public final void onLocationFinished(boolean z) {
                    NavigationCardProviderHelper.DefaultNavigationCardProvider.this.lambda$onTrafficUpdateRequested$2$NavigationCardProviderHelper$DefaultNavigationCardProvider(str, str2, z);
                }
            });
            String autoModeType = DriveModeMetricsHelper.getAutoModeType();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficCardSelected(autoModeType, NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficCardSelected(autoModeType, "Home");
            }
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
        public void provideCards() {
            if (getCardChangeListener() == null) {
                return;
            }
            if (this.mIsNetworkConnected && this.mTrafficCardFeatureEnabled) {
                if (this.mTrafficCard != null) {
                    updateTrafficCardWithCorrectDetails();
                    return;
                }
                if (!this.mLatencyTimerStarted) {
                    this.navigationMetrics.logTrafficCardLatencyMeasuringStartedWithTimers();
                    this.mLatencyTimerStarted = true;
                }
                this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProvider$N_okmKddXBJk1bzVOpLncB0bxpo
                    @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                    public final void onLocationFinished(boolean z) {
                        NavigationCardProviderHelper.DefaultNavigationCardProvider.this.lambda$provideCards$0$NavigationCardProviderHelper$DefaultNavigationCardProvider(z);
                    }
                });
                return;
            }
            getLocalNavigationCard();
        }

        public void setNetworkConnected(boolean z) {
            this.mIsNetworkConnected = z;
            if (!this.mTrafficCardFeatureEnabled || getCardChangeListener() == null) {
                return;
            }
            if (z) {
                if (this.mTrafficCard != null) {
                    getCardChangeListener().updateCard(this.mTrafficCard);
                    return;
                } else {
                    this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProvider$RuIYtFl427EMGSC_f1zlgOYSbwM
                        @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                        public final void onLocationFinished(boolean z2) {
                            NavigationCardProviderHelper.DefaultNavigationCardProvider.this.lambda$setNetworkConnected$1$NavigationCardProviderHelper$DefaultNavigationCardProvider(z2);
                        }
                    });
                    return;
                }
            }
            getLocalNavigationCard();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class DefaultNavigationCardProviderV2 extends DefaultDriveModeCardsProvider implements TrafficNotificationManager.TrafficNotificationRequestCallback, TrafficCardV2.TrafficCardCallback {
        private boolean cardAdded;
        private final OnCardClickListenerInterface cardClickListenerInterface;
        private final Context context;
        private final CurrentLocationManager currentLocationManager;
        private final LocationPublisher locationPublisher;
        private DriveModeThemeManager mDriveModeThemeManager;
        private boolean mLatencyTimerEnded;
        private boolean mLatencyTimerStarted;
        private TrafficCardV2 mTrafficCard;
        private String mTrafficCardCorrelationId;
        private boolean mTrafficCardFeatureEnabled;
        private final NavigationDataProvider navigationDataProvider;
        private final NavigationMetrics navigationMetrics;
        private final TrafficNotificationManager trafficNotificationManager;
        private boolean mIsNetworkConnected = true;
        private final Handler handler = new Handler(Looper.getMainLooper());

        DefaultNavigationCardProviderV2(Context context, CurrentLocationManager currentLocationManager, NavigationDataProvider navigationDataProvider, NavigationMetrics navigationMetrics, OnCardClickListenerInterface onCardClickListenerInterface, TrafficNotificationManager trafficNotificationManager, LocationPublisher locationPublisher, DriveModeThemeManager driveModeThemeManager) {
            this.context = context;
            this.currentLocationManager = currentLocationManager;
            this.navigationMetrics = navigationMetrics;
            this.navigationDataProvider = navigationDataProvider;
            this.trafficNotificationManager = trafficNotificationManager;
            this.locationPublisher = locationPublisher;
            this.cardClickListenerInterface = onCardClickListenerInterface;
            this.mDriveModeThemeManager = driveModeThemeManager;
        }

        private void getLocalNavigationCard() {
            NavigationIconCard navigationIconCard;
            SavedLocations.Item home = this.navigationDataProvider.getHome();
            SavedLocations.Item work = this.navigationDataProvider.getWork();
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.mDriveModeThemeManager.getTheme());
            if (home == null && work == null) {
                Log.e(NavigationCardProviderHelper.TAG, "No home or work cards!");
                return;
            }
            Location currentLocation = this.currentLocationManager.getCurrentLocation();
            DriveModeCardChangeListener cardChangeListener = getCardChangeListener();
            Location location = new Location("");
            if (home != null) {
                location.setLatitude(home.getCoordinate().getLatitudeInDegrees().doubleValue());
                location.setLongitude(home.getCoordinate().getLongitudeInDegrees().doubleValue());
            }
            if (home != null && (work == null || currentLocation == null || currentLocation.distanceTo(location) > 800.0f)) {
                navigationIconCard = new NavigationIconCard(contextThemeWrapper.getResources().getString(R.string.dm_card_nav_home), "", contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_home, contextThemeWrapper.getTheme()), home, this.cardClickListenerInterface);
                this.navigationMetrics.logHomeLandingCardDisplayed();
            } else {
                navigationIconCard = new NavigationIconCard(contextThemeWrapper.getResources().getString(R.string.dm_card_nav_work), "", contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_work, contextThemeWrapper.getTheme()), work, this.cardClickListenerInterface);
                this.navigationMetrics.logWorkLandingCardDisplayed();
            }
            if (this.cardAdded) {
                cardChangeListener.updateCard(navigationIconCard);
                return;
            }
            cardChangeListener.addCard(navigationIconCard);
            this.cardAdded = true;
        }

        private void onTrafficCardClicked(String str, String str2) {
            this.trafficNotificationManager.requestTrafficDetails(this, this.mTrafficCardCorrelationId);
            this.trafficNotificationManager.requestTrafficUpdate(this, str, str2);
        }

        private void requestTrafficDestination() {
            this.trafficNotificationManager.requestTrafficDestination(this);
        }

        private void updateTrafficCardWithCorrectDetails() {
            SavedLocations.Item home = this.navigationDataProvider.getHome();
            SavedLocations.Item work = this.navigationDataProvider.getWork();
            if (this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD) && work != null) {
                this.mTrafficCard.setItem(work);
            } else if (!this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD) || home == null) {
                Log.e(NavigationCardProviderHelper.TAG, "Cannot update unknown traffic card type");
                this.mTrafficCard = null;
                return;
            } else {
                this.mTrafficCard.setItem(home);
            }
            getCardChangeListener().updateCard(this.mTrafficCard);
        }

        public /* synthetic */ void lambda$onTrafficUpdateRequested$2$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(String str, String str2, boolean z) {
            onTrafficCardClicked(str, str2);
        }

        public /* synthetic */ void lambda$provideCards$0$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(boolean z) {
            requestTrafficDestination();
        }

        public /* synthetic */ void lambda$setNetworkConnected$1$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(boolean z) {
            requestTrafficDestination();
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onCommuteDestinationReceived(GetCommuteDestinationResponse getCommuteDestinationResponse) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.mDriveModeThemeManager.getTheme());
            if (this.mTrafficCard != null) {
                Log.e(NavigationCardProviderHelper.TAG, "Traffic card displayed already no need to add again");
            } else if (getCardChangeListener() == null) {
                Log.e(NavigationCardProviderHelper.TAG, "Landing page was already minimized so ignoring this request");
            } else {
                if (getCommuteDestinationResponse.getDestinationAddressType().equals(TrafficConstants.HOME_CARD) && this.navigationDataProvider.getHome() != null) {
                    this.mTrafficCard = new TrafficCardV2(contextThemeWrapper, contextThemeWrapper.getResources().getString(R.string.dm_card_nav_home), contextThemeWrapper.getResources().getString(R.string.traffic_default_status), contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_home, contextThemeWrapper.getTheme()), this.navigationDataProvider.getHome(), this.currentLocationManager.getCurrentLocation(), getCommuteDestinationResponse.getDestinationAddressType(), this, this.mDriveModeThemeManager);
                    this.currentLocationManager.registerForLocationUpdates(this.mTrafficCard);
                    this.navigationMetrics.logTrafficCardDisplayed("Home");
                } else if (getCommuteDestinationResponse.getDestinationAddressType().equals(TrafficConstants.WORK_CARD) && this.navigationDataProvider.getWork() != null) {
                    this.mTrafficCard = new TrafficCardV2(contextThemeWrapper, contextThemeWrapper.getResources().getString(R.string.dm_card_nav_work), contextThemeWrapper.getResources().getString(R.string.traffic_default_status), contextThemeWrapper.getResources().getDrawable(R.drawable.ic_dm_work, contextThemeWrapper.getTheme()), this.navigationDataProvider.getWork(), this.currentLocationManager.getCurrentLocation(), getCommuteDestinationResponse.getDestinationAddressType(), this, this.mDriveModeThemeManager);
                    this.currentLocationManager.registerForLocationUpdates(this.mTrafficCard);
                    this.navigationMetrics.logTrafficCardDisplayed(NavigationMetrics.CardType.WORK);
                }
                if (this.mTrafficCard == null) {
                    return;
                }
                getCardChangeListener().addCard(this.mTrafficCard);
                this.trafficNotificationManager.requestTrafficDetails(this, getCommuteDestinationResponse.getCorrelationId());
                this.mTrafficCardCorrelationId = getCommuteDestinationResponse.getCorrelationId();
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onCommuteDetailsReceived(GetCommuteDetailsResponse getCommuteDetailsResponse) {
            String str = NavigationCardProviderHelper.TAG;
            Log.i(str, "getCommuteDetailsResponse | " + getCommuteDetailsResponse);
            this.mTrafficCard.onTrafficDataUpdated(getCommuteDetailsResponse.getEstimatedTravelTimeInSeconds(), getCommuteDetailsResponse.getTrafficCondition(), getCommuteDetailsResponse.getEstimatedDelayInSeconds());
            if (!this.mLatencyTimerEnded) {
                this.navigationMetrics.logTrafficCardLatencyMeasuringEndedWithTimers();
                this.mLatencyTimerEnded = true;
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCardV2.TrafficCardCallback
        public void onNavigationClicked(SavedLocations.Item item) {
            NavigationCard navigationCard = new NavigationCard();
            navigationCard.setItem(item);
            this.cardClickListenerInterface.onCardClick(navigationCard);
            String autoModeType = DriveModeMetricsHelper.getAutoModeType();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficCardNavigateSelected(autoModeType, NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficCardNavigateSelected(autoModeType, "Home");
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager.TrafficNotificationRequestCallback
        public void onSendCommuteNotificationError() {
            if (getCardChangeListener() == null) {
                Log.e(NavigationCardProviderHelper.TAG, "CardProvider is not currently attached. So ignoring request");
                return;
            }
            Activity activity = (Activity) this.context;
            FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
            Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag(TrafficConstants.TRAFFIC_NOTIFICATION_ERROR_FRAGMENT_TAG);
            if ((findFragmentByTag instanceof TrafficErrorDialogFragment) && findFragmentByTag.isVisible()) {
                return;
            }
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
            beginTransaction.addToBackStack(null);
            beginTransaction.add(new TrafficErrorDialogFragment(), TrafficConstants.TRAFFIC_NOTIFICATION_ERROR_FRAGMENT_TAG).commitAllowingStateLoss();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficErrorDisplayed(NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficErrorDisplayed("Home");
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCardV2.TrafficCardCallback
        public void onTrafficCardUpdated() {
            if (getCardChangeListener() == null) {
                Log.i(NavigationCardProviderHelper.TAG, "onTrafficCardUpdated | card change listener is null so returning");
            } else {
                getCardChangeListener().updateCard(this.mTrafficCard);
            }
        }

        @Override // com.amazon.alexa.drive.navigation.traffic.TrafficCardV2.TrafficCardCallback
        public void onTrafficUpdateRequested(final String str, final String str2) {
            this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProviderV2$sZ8aQWJ-Em_pdpeO_e1EwELfuf8
                @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                public final void onLocationFinished(boolean z) {
                    NavigationCardProviderHelper.DefaultNavigationCardProviderV2.this.lambda$onTrafficUpdateRequested$2$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(str, str2, z);
                }
            });
            String autoModeType = DriveModeMetricsHelper.getAutoModeType();
            if (this.mTrafficCard.getTrafficCardType() != null && this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.WORK_CARD)) {
                this.navigationMetrics.logTrafficCardSelected(autoModeType, NavigationMetrics.CardType.WORK);
            } else if (this.mTrafficCard.getTrafficCardType() == null || !this.mTrafficCard.getTrafficCardType().equals(TrafficConstants.HOME_CARD)) {
            } else {
                this.navigationMetrics.logTrafficCardSelected(autoModeType, "Home");
            }
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
        public void provideCards() {
            if (getCardChangeListener() == null) {
                return;
            }
            if (this.mIsNetworkConnected && this.mTrafficCardFeatureEnabled) {
                if (this.mTrafficCard != null) {
                    updateTrafficCardWithCorrectDetails();
                    return;
                }
                if (!this.mLatencyTimerStarted) {
                    this.navigationMetrics.logTrafficCardLatencyMeasuringStartedWithTimers();
                    this.mLatencyTimerStarted = true;
                }
                this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProviderV2$1TZ22YdAGpyieFsuM32bzpLPT8g
                    @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                    public final void onLocationFinished(boolean z) {
                        NavigationCardProviderHelper.DefaultNavigationCardProviderV2.this.lambda$provideCards$0$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(z);
                    }
                });
                return;
            }
            getLocalNavigationCard();
        }

        public void setNetworkConnected(boolean z) {
            this.mIsNetworkConnected = z;
            if (!this.mTrafficCardFeatureEnabled || getCardChangeListener() == null) {
                return;
            }
            if (z) {
                if (this.mTrafficCard != null) {
                    getCardChangeListener().updateCard(this.mTrafficCard);
                    return;
                } else {
                    this.locationPublisher.publishLocation(this.currentLocationManager.getCurrentLocation(), new LocationPublisher.LocationStatusCallback() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$DefaultNavigationCardProviderV2$696Y6wvXJAYUeuok1IXQA1QTTiE
                        @Override // com.amazon.alexa.drive.navigation.location.LocationPublisher.LocationStatusCallback
                        public final void onLocationFinished(boolean z2) {
                            NavigationCardProviderHelper.DefaultNavigationCardProviderV2.this.lambda$setNetworkConnected$1$NavigationCardProviderHelper$DefaultNavigationCardProviderV2(z2);
                        }
                    });
                    return;
                }
            }
            getLocalNavigationCard();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class NavigationIconCard extends SingleIconCard {
        private final OnCardClickListenerInterface clickListener;
        private final SavedLocations.Item item;

        NavigationIconCard(String str, String str2, Drawable drawable, SavedLocations.Item item, OnCardClickListenerInterface onCardClickListenerInterface) {
            super(str, str2, drawable);
            this.item = item;
            this.clickListener = onCardClickListenerInterface;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public DriveModeCard.CardDomain getCardDomain() {
            return DriveModeCard.CardDomain.NAVIGATION;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public int getCardId() {
            return 256;
        }

        @Override // com.amazon.alexa.drivemode.api.SingleIconCard
        public void onCardClicked() {
            if (this.item == null || this.clickListener == null) {
                return;
            }
            NavigationCard navigationCard = new NavigationCard();
            navigationCard.setItem(this.item);
            this.clickListener.onCardClick(navigationCard);
        }
    }

    public NavigationCardProviderHelper(Context context, NavigationDataProvider navigationDataProvider, NavigationMetrics navigationMetrics, OnCardClickListenerInterface onCardClickListenerInterface, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        this.context = context;
        this.navigationDataProvider = navigationDataProvider;
        this.navigationMetrics = navigationMetrics;
        this.driveModeThemeManager = driveModeThemeManager;
        this.weblabManager = weblabManager;
        this.locationManager = new CurrentLocationManager(context);
        this.driveModeService = (DefaultDriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class);
        this.cardClickListener = onCardClickListenerInterface;
        this.trafficNotificationFeatureEnabled = isTrafficNotificationFeatureEnabled();
        if (this.trafficNotificationFeatureEnabled) {
            this.locationPublisher = new LocationPublisher(navigationMetrics);
            this.trafficNotificationManager = new TrafficNotificationManager(navigationMetrics);
            return;
        }
        this.locationPublisher = null;
        this.trafficNotificationManager = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DefaultDriveModeCardsProvider createCardProvider() {
        if (this.landingPageCardProvider == null) {
            if (this.weblabManager.isAutoMode_2_0_WeblabEnabled()) {
                this.landingPageCardProvider = new DefaultNavigationCardProviderV2(this.context, this.locationManager, this.navigationDataProvider, this.navigationMetrics, this.cardClickListener, this.trafficNotificationManager, this.locationPublisher, this.driveModeThemeManager);
                ((DefaultNavigationCardProviderV2) this.landingPageCardProvider).mTrafficCardFeatureEnabled = this.trafficNotificationFeatureEnabled;
            } else {
                this.landingPageCardProvider = new DefaultNavigationCardProvider(this.context, this.locationManager, this.navigationDataProvider, this.navigationMetrics, this.cardClickListener, this.trafficNotificationManager, this.locationPublisher, this.driveModeThemeManager);
                ((DefaultNavigationCardProvider) this.landingPageCardProvider).mTrafficCardFeatureEnabled = this.trafficNotificationFeatureEnabled;
            }
        }
        return this.landingPageCardProvider;
    }

    private boolean isTrafficNotificationFeatureEnabled() {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);
        if (featureServiceV2 != null) {
            return featureServiceV2.hasAccess("ALEXA_AUTO_ANDROID_TRAFFIC_NOTIFICATION_ENABLED", false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCardsReady() {
        DefaultDriveModeCardsProvider defaultDriveModeCardsProvider = this.landingPageCardProvider;
        if (defaultDriveModeCardsProvider != null) {
            defaultDriveModeCardsProvider.provideCards();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNetworkConnectionStatus(boolean z) {
        GeneratedOutlineSupport1.outline172("setNetworkConnectionStatus | isNetworkConnected: ", z);
        if (this.weblabManager.isAutoMode_2_0_WeblabEnabled()) {
            ((DefaultNavigationCardProviderV2) this.landingPageCardProvider).setNetworkConnected(z);
        } else {
            ((DefaultNavigationCardProvider) this.landingPageCardProvider).setNetworkConnected(z);
        }
    }

    public void start() {
        DefaultDriveModeService defaultDriveModeService = this.driveModeService;
        if (defaultDriveModeService != null) {
            defaultDriveModeService.addCardsProvider(new Lazy() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationCardProviderHelper$ra80LmF4hSGyhvO325X9b01mQpo
                @Override // dagger.Lazy
                /* renamed from: get */
                public final Object mo358get() {
                    DefaultDriveModeCardsProvider createCardProvider;
                    createCardProvider = NavigationCardProviderHelper.this.createCardProvider();
                    return createCardProvider;
                }
            });
        }
        this.locationManager.setCardProvider(this.landingPageCardProvider);
        this.locationManager.startLocationTracking();
    }

    public void stop() {
        this.locationManager.setCardProvider(null);
        this.locationManager.stopLocationTracking();
        DefaultDriveModeService defaultDriveModeService = this.driveModeService;
        if (defaultDriveModeService != null) {
            defaultDriveModeService.getProviders().remove(this.landingPageCardProvider);
        }
    }

    public NavigationCardProviderHelper(Context context, NavigationDataProvider navigationDataProvider, NavigationMetrics navigationMetrics, OnCardClickListenerInterface onCardClickListenerInterface, LocationPublisher locationPublisher, TrafficNotificationManager trafficNotificationManager, DriveModeThemeManager driveModeThemeManager, CurrentLocationManager currentLocationManager, WeblabManager weblabManager) {
        this(context, navigationDataProvider, navigationMetrics, onCardClickListenerInterface, driveModeThemeManager, weblabManager);
        this.locationPublisher = locationPublisher;
        this.trafficNotificationManager = trafficNotificationManager;
        this.locationManager = currentLocationManager;
    }
}
