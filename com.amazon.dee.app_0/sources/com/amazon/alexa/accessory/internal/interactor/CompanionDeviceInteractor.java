package com.amazon.alexa.accessory.internal.interactor;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule;
import com.amazon.alexa.accessory.internal.interactor.CompanionDeviceInteractor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class CompanionDeviceInteractor implements Interactor {
    private static final String LOG_TAG = "CompanionDeviceInteractor: ";
    private static final String TAG = "COMPANION_DEVICE_INTERACTOR";
    private boolean active;
    private final CompanionDeviceModule companionDeviceModule;
    private final boolean companionDeviceSetupAvailable;
    private final CompositeDisposable compositeDisposable;
    private final AccessoryServiceConfigurationSupplier configurationSupplier;
    private final Context context;
    private List<DeviceGroup> currentDeviceGroups;
    private final DeviceSupplierV2 deviceSupplier;
    private final Object lock;
    private final NotificationManagerCompat notificationManager;
    private final AccessorySessionListener sessionListener;
    private final SessionSupplier sessionSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.interactor.CompanionDeviceInteractor$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends AccessorySessionListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onAccessorySessionConnected$1(Accessory accessory, Boolean bool) throws Throwable {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompanionDeviceInteractor: Successfully requested  companion device notification ");
            outline107.append(accessory.getAddress());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(bool);
            Logger.d(outline107.toString());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onAccessorySessionConnected$2(Accessory accessory, Throwable th) throws Throwable {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompanionDeviceInteractor: ERROR: Exception caught, or device is not setup for accessory ");
            outline107.append(accessory.toString());
            outline107.append(" will not request companion device notification.");
            Logger.d(outline107.toString(), th);
            Logger.e("CompanionDeviceInteractor: Exception caught, or device is not setup for accessory " + RedactionUtil.redact(accessory).toString() + " will not request companion device notification.", th);
        }

        public /* synthetic */ Boolean lambda$onAccessorySessionConnected$0$CompanionDeviceInteractor$1(Accessory accessory, DeviceGroup deviceGroup) throws Throwable {
            if (deviceGroup.getDevice() != null) {
                return Boolean.valueOf(CompanionDeviceInteractor.this.requestCompanionDeviceNotification(accessory.getAddress(), accessory.getName()));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompanionDeviceInteractor: Device is not setup for accessory ");
            outline107.append(accessory.toString());
            outline107.append(" will not request companion device notification.");
            throw new IllegalStateException(outline107.toString());
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(final Accessory accessory) {
            if (CompanionDeviceInteractor.this.configurationSupplier.showCompanionDeviceNotification()) {
                int i = Build.VERSION.SDK_INT;
                if (CompanionDeviceInteractor.this.configurationSupplier.showCompanionDeviceNotificationBeforeSetupComplete()) {
                    try {
                        CompanionDeviceInteractor.this.requestCompanionDeviceNotification(accessory.getAddress(), accessory.getName());
                        return;
                    } catch (IllegalAccessException e) {
                        Logger.e("CompanionDeviceInteractor: Caught exception onAccessorySessionConnected when trying to request companion device notification", e);
                        return;
                    }
                }
                CompanionDeviceInteractor.this.compositeDisposable.add(CompanionDeviceInteractor.this.deviceSupplier.getDeviceGroup(accessory.getAddress()).map(new Function() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$1$Y55tAjda8aVbfU1ECDQld8i7mWI
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj) {
                        return CompanionDeviceInteractor.AnonymousClass1.this.lambda$onAccessorySessionConnected$0$CompanionDeviceInteractor$1(accessory, (DeviceGroup) obj);
                    }
                }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$1$54yt6JRbB1WsCCt78xrJlWUXh98
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        CompanionDeviceInteractor.AnonymousClass1.lambda$onAccessorySessionConnected$1(Accessory.this, (Boolean) obj);
                    }
                }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$1$Imr7CEB4LJdaaD0rDnc_KbDCoSc
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        CompanionDeviceInteractor.AnonymousClass1.lambda$onAccessorySessionConnected$2(Accessory.this, (Throwable) obj);
                    }
                }));
            }
        }
    }

    public CompanionDeviceInteractor(SessionSupplier sessionSupplier, DeviceSupplierV2 deviceSupplierV2, Context context, AccessoryServiceConfigurationSupplier accessoryServiceConfigurationSupplier, CompanionDeviceModule companionDeviceModule) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        Preconditions.notNull(context, "context");
        Preconditions.notNull(accessoryServiceConfigurationSupplier, "configurationSupplier");
        Preconditions.notNull(companionDeviceModule, "companionDeviceModule");
        this.sessionSupplier = sessionSupplier;
        this.deviceSupplier = deviceSupplierV2;
        this.context = context;
        this.configurationSupplier = accessoryServiceConfigurationSupplier;
        this.companionDeviceModule = companionDeviceModule;
        this.notificationManager = NotificationManagerCompat.from(context);
        this.compositeDisposable = new CompositeDisposable();
        this.sessionListener = createSessionListener();
        this.lock = new Object();
        int i = Build.VERSION.SDK_INT;
        this.companionDeviceSetupAvailable = CompanionDeviceModule.companionDeviceSetupAvailable(context.getPackageManager());
    }

    @RequiresApi(api = 26)
    private Notification createCompanionDeviceNotification(String str, String str2) throws IllegalAccessException {
        CharSequence companionDeviceNotificationBodyText = this.configurationSupplier.getCompanionDeviceNotificationBodyText(str2, str);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this.context, this.configurationSupplier.highPriorityChannelId()).setContentTitle(this.configurationSupplier.getCompanionDeviceNotificationTitleText(str2, str)).setVisibility(1).setPriority(2).setSmallIcon(this.configurationSupplier.getCompanionDeviceNotificationSmallIconRes()).setColor(this.configurationSupplier.getCompanionDeviceNotificationColor()).setAutoCancel(false).setStyle(new NotificationCompat.BigTextStyle().bigText(companionDeviceNotificationBodyText)).setLargeIcon(this.configurationSupplier.getCompanionDeviceNotificationLargeIcon()).setCategory(NotificationCompat.CATEGORY_REMINDER).setContentIntent(this.companionDeviceModule.getRequestCompanionDevicePendingIntent(str));
        Bitmap notificationLargeIcon = this.configurationSupplier.getNotificationLargeIcon();
        if (notificationLargeIcon != null) {
            contentIntent.setLargeIcon(notificationLargeIcon);
        }
        return contentIntent.build();
    }

    private AccessorySessionListener createSessionListener() {
        return new AnonymousClass1();
    }

    private static List<DeviceGroup> generateDeviceGroupDiff(List<DeviceGroup> list, List<DeviceGroup> list2) {
        ArrayList arrayList = new ArrayList();
        for (DeviceGroup deviceGroup : list) {
            boolean z = false;
            Iterator<DeviceGroup> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                } else if (it2.next().getIdentifier().equals(deviceGroup.getIdentifier())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                arrayList.add(deviceGroup);
            }
        }
        return arrayList;
    }

    @RequiresApi(api = 26)
    private void listenForNewCompanionDevices() {
        this.compositeDisposable.add(this.companionDeviceModule.queryNewCompanionDevices().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$yCR-AtejVzDpdRiCsalLgZeqBec
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CompanionDeviceInteractor.this.lambda$listenForNewCompanionDevices$0$CompanionDeviceInteractor((String) obj);
            }
        }, $$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE.INSTANCE));
    }

    @RequiresApi(api = 26)
    private void listenForRemovedDevices() {
        this.compositeDisposable.add(this.deviceSupplier.queryDeviceGroups().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$HOesaQqQ_YPq-uJEkv_U68eQB9U
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CompanionDeviceInteractor.this.lambda$listenForRemovedDevices$2$CompanionDeviceInteractor((List) obj);
            }
        }, $$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1rWagEkI.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 26)
    public boolean requestCompanionDeviceNotification(String str, String str2) throws IllegalAccessException {
        if (!this.companionDeviceModule.isCompanionDevice(str)) {
            AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.CompanionDevice.COMPANION_DEVICE_NOTIFICATION_CREATED, String.valueOf(Build.VERSION.SDK_INT), true, null);
            this.notificationManager.notify(TAG, str.hashCode(), createCompanionDeviceNotification(str, str2));
            Logger.d("CompanionDeviceInteractor: Requested a companion device notification for session with address %s", str);
            return true;
        }
        Logger.d("CompanionDeviceInteractor: Did not request a companion device notification for session with address %s because it is already a companion device", str);
        return false;
    }

    private void stopObservingSessions() {
        this.sessionSupplier.removeSessionListener(this.sessionListener);
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (Build.VERSION.SDK_INT < this.configurationSupplier.showCompanionDeviceNotificationForApiLevelAndAbove() || !this.companionDeviceSetupAvailable || this.active) {
            return;
        }
        this.active = true;
        Logger.d("CompanionDeviceInteractor: activate");
        observeSessions();
        listenForNewCompanionDevices();
        listenForRemovedDevices();
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        Preconditions.mainThread();
        if (Build.VERSION.SDK_INT < this.configurationSupplier.showCompanionDeviceNotificationForApiLevelAndAbove() || !this.companionDeviceSetupAvailable || !this.active) {
            return;
        }
        this.active = false;
        Logger.d("CompanionDeviceInteractor: deactivate");
        stopObservingSessions();
        ObservableUtils.dispose(this.compositeDisposable);
    }

    public /* synthetic */ void lambda$listenForNewCompanionDevices$0$CompanionDeviceInteractor(String str) throws Throwable {
        this.notificationManager.cancel(TAG, str.hashCode());
    }

    public /* synthetic */ void lambda$listenForRemovedDevices$2$CompanionDeviceInteractor(List list) throws Throwable {
        synchronized (this.lock) {
            if (this.currentDeviceGroups == null) {
                this.currentDeviceGroups = list;
                return;
            }
            for (DeviceGroup deviceGroup : generateDeviceGroupDiff(this.currentDeviceGroups, list)) {
                Logger.d("CompanionDeviceInteractor: Cancelling potential companion device notification for removed device: " + deviceGroup.toString());
                this.notificationManager.cancel(TAG, deviceGroup.getIdentifier().hashCode());
            }
            this.currentDeviceGroups = list;
        }
    }

    public void observeSessions() {
        this.sessionSupplier.addSessionListener(this.sessionListener);
    }
}
