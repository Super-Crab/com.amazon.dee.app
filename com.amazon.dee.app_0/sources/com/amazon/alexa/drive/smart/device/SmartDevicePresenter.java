package com.amazon.alexa.drive.smart.device;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.cards.Card;
import com.amazon.alexa.drive.cards.OnCardClickListenerInterface;
import com.amazon.alexa.drive.main.routing.DriveModeRoutingConstants;
import com.amazon.alexa.drive.service.DefaultDriveModeService;
import com.amazon.alexa.drive.smart.device.SmartDeviceInteractor;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.smart.device.data.SmartDevice;
import com.amazon.alexa.drive.smart.device.lock.Lock;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DefaultDriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SecureCard;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes7.dex */
public class SmartDevicePresenter implements SmartDeviceContract.CardClickListener, SmartDeviceContract.Presenter {
    private static final int GUARD_DEVICE_LANDING_PAGE_CARD_ID = 4097;
    private static final int LOCK_DEVICE_LANDING_PAGE_CARD_ID = 4098;
    private static final int SMART_DEVICE_LANDING_PAGE_CARD_ID = 4096;
    private static final String TAG = "SmartDevicePresenter";
    private final Context context;
    private DefaultDriveModeCardsProvider defaultDriveModeCardsProvider;
    private final List<SmartDeviceContract.DeviceUpdateListener> deviceUpdateListeners = new ArrayList();
    private Lazy<DriveModeCardsProvider> driveModeCardsProviderLazy;
    @VisibleForTesting
    DefaultDriveModeService driveModeService;
    @VisibleForTesting
    DriveModeThemeManager driveModeThemeManager;
    @VisibleForTesting
    SecureCard guardCard;
    private Handler handler;
    @VisibleForTesting
    SecureCard lockCard;
    private Runnable pollForSmartDevicesRunnable;
    @VisibleForTesting
    SmartDeviceInteractor smartDeviceInteractor;

    /* loaded from: classes7.dex */
    public static class GuardCard extends SecureCard {
        private final OnCardClickListenerInterface clickListener;

        public GuardCard(String str, String str2, Drawable drawable, String str3, boolean z, OnCardClickListenerInterface onCardClickListenerInterface) {
            super(str, str2, drawable, str3, z);
            this.clickListener = onCardClickListenerInterface;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public DriveModeCard.CardDomain getCardDomain() {
            return DriveModeCard.CardDomain.SMART_HOME;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public int getCardId() {
            return 4097;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public int getSecuredButtonBackground() {
            return R.drawable.smart_device_background_away;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public int getUnsecuredButtonBackground() {
            return R.drawable.smart_device_background_home;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public void onCardClicked() {
            if (this.clickListener == null) {
                return;
            }
            Card card = new Card();
            card.setTitle(getTitle());
            this.clickListener.onCardClick(card);
        }
    }

    /* loaded from: classes7.dex */
    public static class LockCard extends SecureCard {
        private final OnCardClickListenerInterface clickListener;

        public LockCard(String str, String str2, Drawable drawable, String str3, boolean z, OnCardClickListenerInterface onCardClickListenerInterface) {
            super(str, str2, drawable, str3, z);
            this.clickListener = onCardClickListenerInterface;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public DriveModeCard.CardDomain getCardDomain() {
            return DriveModeCard.CardDomain.SMART_HOME;
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCard
        public int getCardId() {
            return 4098;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public int getSecuredButtonBackground() {
            return R.drawable.smart_device_background_locked;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public int getUnsecuredButtonBackground() {
            return R.drawable.smart_device_background_unlocked;
        }

        @Override // com.amazon.alexa.drivemode.api.SecureCard
        public void onCardClicked() {
            if (this.clickListener == null) {
                return;
            }
            Card card = new Card();
            card.setTitle(getTitle());
            this.clickListener.onCardClick(card);
        }
    }

    public SmartDevicePresenter(Context context, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        this.context = context;
        this.driveModeThemeManager = driveModeThemeManager;
        this.smartDeviceInteractor = new SmartDeviceInteractor(context, weblabManager);
        this.smartDeviceInteractor.setUpdateDeviceListener(new SmartDeviceContract.Interactor.UpdateDevice() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$j8RKCJQPgq-S5r9DPnC72CR-nTc
            @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Interactor.UpdateDevice
            public final void onUpdate() {
                SmartDevicePresenter.this.updateSmartDevices();
            }
        });
    }

    private void initDriveModeCardProvider() {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.driveModeThemeManager.getTheme());
        Resources resources = contextThemeWrapper.getResources();
        this.lockCard = new LockCard(resources.getString(R.string.dm_smart_home_front_door), resources.getString(R.string.dm_smart_home_lock_unresponsive), resources.getDrawable(R.drawable.ic_smart_lock, contextThemeWrapper.getTheme()), "", false, new OnCardClickListenerInterface() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartDevicePresenter$8Wb6_iUSlGOmgi-C9NvuFcTeYy8
            @Override // com.amazon.alexa.drive.cards.OnCardClickListenerInterface
            public final void onCardClick(Card card) {
                SmartDevicePresenter.this.lambda$initDriveModeCardProvider$0$SmartDevicePresenter(card);
            }
        });
        this.guardCard = new GuardCard(this.context.getString(R.string.dm_smart_home_guard), "", resources.getDrawable(R.drawable.ic_guard, contextThemeWrapper.getTheme()), "", false, new OnCardClickListenerInterface() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartDevicePresenter$nQJpEo3mrKoAmEtRNHt3lUOGW18
            @Override // com.amazon.alexa.drive.cards.OnCardClickListenerInterface
            public final void onCardClick(Card card) {
                SmartDevicePresenter.this.lambda$initDriveModeCardProvider$1$SmartDevicePresenter(card);
            }
        });
        this.driveModeCardsProviderLazy = new Lazy() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartDevicePresenter$OjaH8t84WhzwForVRU43wYeWw7M
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return SmartDevicePresenter.this.lambda$initDriveModeCardProvider$2$SmartDevicePresenter();
            }
        };
    }

    private void initDriveModeService() {
        this.driveModeService = (DefaultDriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class);
    }

    private void navigateToLockPermissionWarning() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        if (routingService.match(DriveModeRoutingConstants.DRIVE_MODE_LOCK_PERMISSION_WARNING_ROUTE_NAME) != null) {
            GeneratedOutlineSupport1.outline145(routingService, DriveModeRoutingConstants.DRIVE_MODE_LOCK_PERMISSION_WARNING_ROUTE_NAME);
        }
    }

    private void navigateToSmartDevices() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        if (routingService.match(DriveModeRoutingConstants.DRIVE_MODE_SMART_DEVICE_ROUTE_NAME) != null) {
            GeneratedOutlineSupport1.outline145(routingService, DriveModeRoutingConstants.DRIVE_MODE_SMART_DEVICE_ROUTE_NAME);
        }
    }

    private void pollForSmartDevices() {
        this.pollForSmartDevicesRunnable = new Runnable() { // from class: com.amazon.alexa.drive.smart.device.SmartDevicePresenter.1
            @Override // java.lang.Runnable
            public void run() {
                SmartDevicePresenter.this.smartDeviceInteractor.getSmartDeviceInfo();
                SmartDevicePresenter.this.handler.postDelayed(this, 10000L);
            }
        };
        this.handler = new Handler();
        this.handler.post(this.pollForSmartDevicesRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHomeCard() {
        SmartDeviceInteractor.HomeCardData homeCardData = this.smartDeviceInteractor.getHomeCardData();
        if (homeCardData == null) {
            return;
        }
        SmartDevice smartDevice = null;
        SmartDevice smartDevice2 = null;
        SmartDevice smartDevice3 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (SmartDevice smartDevice4 : this.smartDeviceInteractor.getSmartDevices()) {
            if (smartDevice4.getSmartDeviceType() == 2) {
                i++;
                if (!smartDevice4.isLocked()) {
                    i3++;
                    smartDevice2 = smartDevice4;
                    smartDevice3 = smartDevice2;
                } else {
                    smartDevice2 = smartDevice4;
                }
            } else if (smartDevice4.getSmartDeviceType() == 1) {
                i2++;
                smartDevice = smartDevice4;
            }
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, this.driveModeThemeManager.getTheme());
        Resources resources = contextThemeWrapper.getResources();
        if (smartDevice != null) {
            String guardButtonText = homeCardData.getGuardButtonText();
            if (!TextUtils.isEmpty(guardButtonText)) {
                this.guardCard.setButtonText(guardButtonText);
                this.guardCard.setSecure(homeCardData.isGuardSecured());
            }
        }
        if (smartDevice2 != null) {
            String str = "";
            if (i == 1) {
                Lock lock = (Lock) smartDevice2;
                this.lockCard.setTitle(lock.getTitle());
                this.lockCard.setIcon(resources.getDrawable(lock.isLocked() ? R.drawable.ic_smart_lock_on : R.drawable.ic_smart_lock_off, contextThemeWrapper.getTheme()));
                this.lockCard.setDescription(lock.isReachable() ? str : this.context.getString(R.string.dm_smart_home_lock_unresponsive));
                SecureCard secureCard = this.lockCard;
                if (lock.isReachable()) {
                    str = lock.isLocked() ? this.context.getString(R.string.dm_smart_home_locked) : this.context.getString(R.string.dm_smart_home_unlocked);
                }
                secureCard.setButtonText(str);
                this.lockCard.setSecure(lock.isLocked());
                this.lockCard.setLastUserAction(lock.getLastUserAction() != null ? lock.getLastUserAction() : this.lockCard.getLastUserAction());
            } else if (i3 == 1) {
                Lock lock2 = (Lock) smartDevice3;
                this.lockCard.setTitle(lock2.getTitle());
                this.lockCard.setIcon(resources.getDrawable(R.drawable.ic_smart_lock_off, contextThemeWrapper.getTheme()));
                this.lockCard.setButtonText(str);
                this.lockCard.setSecure(false);
                SecureCard secureCard2 = this.lockCard;
                Object[] objArr = new Object[2];
                objArr[0] = lock2.getTitle();
                objArr[1] = lock2.isReachable() ? this.context.getString(R.string.dm_smart_home_unlocked) : this.context.getString(R.string.dm_smart_home_lock_unresponsive);
                secureCard2.setDescription(String.format("%s %s", objArr));
                this.lockCard.setLastUserAction(lock2.getLastUserAction() != null ? lock2.getLastUserAction() : this.lockCard.getLastUserAction());
            } else {
                this.lockCard.setIcon(resources.getDrawable(R.drawable.ic_smart_lock, contextThemeWrapper.getTheme()));
                this.lockCard.setTitle(this.context.getString(R.string.dm_smart_home_doors));
                this.lockCard.setDescription(String.format(Locale.ENGLISH, "%d %s", Integer.valueOf(i - i3), this.context.getString(R.string.dm_smart_home_locked)));
            }
            if (!this.smartDeviceInteractor.hasUnlockPermission()) {
                this.lockCard.setDescription(this.context.getString(R.string.dm_smart_home_enable_unlock_description));
            }
        }
        DriveModeCardChangeListener cardChangeListener = ((DefaultDriveModeCardsProvider) this.driveModeCardsProviderLazy.mo358get()).getCardChangeListener();
        if (cardChangeListener == null) {
            return;
        }
        if (i2 > 0) {
            cardChangeListener.updateCard(this.guardCard);
        } else {
            cardChangeListener.removeCard(this.guardCard);
        }
        if (i > 0) {
            cardChangeListener.updateCard(this.lockCard);
        } else {
            cardChangeListener.removeCard(this.lockCard);
        }
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Presenter
    public void addSmartDeviceChangeListener(SmartDeviceContract.DeviceUpdateListener deviceUpdateListener) {
        this.deviceUpdateListeners.add(deviceUpdateListener);
    }

    public void deinit() {
        removeCardProvider();
        this.handler.removeCallbacks(this.pollForSmartDevicesRunnable);
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Presenter
    public void getSmartDeviceInfo() {
        this.smartDeviceInteractor.getSmartDeviceInfo();
    }

    public void init() {
        initDriveModeService();
        initDriveModeCardProvider();
        this.driveModeService.addCardsProvider(this.driveModeCardsProviderLazy);
        this.smartDeviceInteractor.getSmartDeviceInfo();
        pollForSmartDevices();
    }

    public /* synthetic */ void lambda$initDriveModeCardProvider$0$SmartDevicePresenter(Card card) {
        if (!this.smartDeviceInteractor.hasUnlockPermission()) {
            navigateToLockPermissionWarning();
            return;
        }
        int i = 0;
        SmartDevice smartDevice = null;
        for (SmartDevice smartDevice2 : this.smartDeviceInteractor.getSmartDevices()) {
            if (smartDevice2.getSmartDeviceType() == 2) {
                i++;
                smartDevice = smartDevice2;
            }
        }
        if (i == 1) {
            onClick(smartDevice);
        } else if (i > 1) {
            navigateToSmartDevices();
        }
    }

    public /* synthetic */ void lambda$initDriveModeCardProvider$1$SmartDevicePresenter(Card card) {
        for (SmartDevice smartDevice : this.smartDeviceInteractor.getSmartDevices()) {
            if (smartDevice.getSmartDeviceType() == 1) {
                onClick(smartDevice);
                return;
            }
        }
    }

    public /* synthetic */ DriveModeCardsProvider lambda$initDriveModeCardProvider$2$SmartDevicePresenter() {
        DefaultDriveModeCardsProvider defaultDriveModeCardsProvider = this.defaultDriveModeCardsProvider;
        if (defaultDriveModeCardsProvider != null) {
            return defaultDriveModeCardsProvider;
        }
        this.defaultDriveModeCardsProvider = new DefaultDriveModeCardsProvider() { // from class: com.amazon.alexa.drive.smart.device.SmartDevicePresenter.2
            @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
            public void provideCards() {
                Log.i(SmartDevicePresenter.TAG, "provideCards");
                SmartDevicePresenter.this.updateHomeCard();
            }
        };
        return this.defaultDriveModeCardsProvider;
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.CardClickListener
    public void onClick(SmartDevice smartDevice) {
        this.smartDeviceInteractor.switchDeviceLockState(smartDevice);
    }

    public void removeCardProvider() {
        if (this.driveModeService != null) {
            Log.i(TAG, "removeCardProvider");
            this.driveModeService.getProviders().remove(this.defaultDriveModeCardsProvider);
        }
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Presenter
    public void removeSmartDeviceChangeListener(SmartDeviceContract.DeviceUpdateListener deviceUpdateListener) {
        this.deviceUpdateListeners.remove(deviceUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateSmartDevices() {
        List<SmartDevice> smartDevices = this.smartDeviceInteractor.getSmartDevices();
        for (SmartDeviceContract.DeviceUpdateListener deviceUpdateListener : this.deviceUpdateListeners) {
            deviceUpdateListener.onUpdate(smartDevices);
        }
        updateHomeCard();
    }
}
