package com.amazon.alexa.accessory.notificationpublisher.storage;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
/* loaded from: classes.dex */
public final class MigrationHelper {
    private static final String TAG = "MigrationHelper";
    private static boolean isApproveInvitationOnAccessoryFetched;
    private static boolean isForwardNotificationToAccessoryFetched;
    private static boolean isStatusEnablementFetched;
    @VisibleForTesting
    static Boolean oldZionApproveInvitationOnAccessory;
    @VisibleForTesting
    static Boolean oldZionForwardNotificationToAccessory;
    @VisibleForTesting
    static Boolean oldZionStatusEnablement;

    private MigrationHelper() {
    }

    public static synchronized boolean isApproveInvitationOnAccessoryFetched() {
        boolean z;
        synchronized (MigrationHelper.class) {
            z = isApproveInvitationOnAccessoryFetched;
        }
        return z;
    }

    public static synchronized boolean isForwardNotificationToAccessoryFetched() {
        boolean z;
        synchronized (MigrationHelper.class) {
            z = isForwardNotificationToAccessoryFetched;
        }
        return z;
    }

    public static synchronized boolean isStatusEnablementFetched() {
        boolean z;
        synchronized (MigrationHelper.class) {
            z = isStatusEnablementFetched;
        }
        return z;
    }

    public static synchronized void reset() {
        synchronized (MigrationHelper.class) {
            Log.d(TAG, "Reset");
            isForwardNotificationToAccessoryFetched = false;
            isStatusEnablementFetched = false;
            isApproveInvitationOnAccessoryFetched = false;
            oldZionApproveInvitationOnAccessory = null;
            oldZionForwardNotificationToAccessory = null;
            oldZionStatusEnablement = null;
        }
    }

    public static synchronized void setOldZionApproveInvitationOnAccessory(Boolean bool) {
        synchronized (MigrationHelper.class) {
            oldZionApproveInvitationOnAccessory = bool;
        }
    }

    public static synchronized void setOldZionForwardNotificationToAccessory(Boolean bool) {
        synchronized (MigrationHelper.class) {
            oldZionForwardNotificationToAccessory = bool;
        }
    }

    public static synchronized void setOldZionStatusEnablement(Boolean bool) {
        synchronized (MigrationHelper.class) {
            oldZionStatusEnablement = bool;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        if (r1 == 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r1 == 2) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.isStatusEnablementFetched = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
        if (com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionStatusEnablement == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005a, code lost:
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG, "Update Status Enablement for Zion");
        com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance().put("A3IYPH06PH1HRA", com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY, com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionStatusEnablement, com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0071, code lost:
        com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.isApproveInvitationOnAccessoryFetched = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionApproveInvitationOnAccessory == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0077, code lost:
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG, "Update Approve Invitation On Approve for Zion");
        com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance().put("A3IYPH06PH1HRA", com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionApproveInvitationOnAccessory, com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void updateFetchStatus(java.lang.String r6) {
        /*
            java.lang.Class<com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper> r0 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.class
            monitor-enter(r0)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r2.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = "Update Fetch Status: "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb5
            r2.append(r6)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lb5
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r1, r2)     // Catch: java.lang.Throwable -> Lb5
            r1 = -1
            int r2 = r6.hashCode()     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            r3 = -502436310(0xffffffffe20d6e2a, float:-6.522323E20)
            r4 = 2
            r5 = 1
            if (r2 == r3) goto L44
            r3 = 1846186485(0x6e0a91f5, float:1.0721346E28)
            if (r2 == r3) goto L3a
            r3 = 1869150543(0x6f68f94f, float:7.210192E28)
            if (r2 == r3) goto L30
            goto L4d
        L30:
            java.lang.String r2 = "Alexa.Accessories.VipFilter.approveInvitationOnAccessory"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto L4d
            r1 = r5
            goto L4d
        L3a:
            java.lang.String r2 = "Alexa.Accessories.VipFilter.statusEnablement"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto L4d
            r1 = r4
            goto L4d
        L44:
            java.lang.String r2 = "Alexa.Accessories.VipFilter.forwardNotificationToAccessoryEnablement"
            boolean r6 = r6.equals(r2)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto L4d
            r1 = 0
        L4d:
            if (r1 == 0) goto L8e
            if (r1 == r5) goto L71
            if (r1 == r4) goto L54
            goto Lb3
        L54:
            com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.isStatusEnablementFetched = r5     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.Boolean r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionStatusEnablement     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto Lb3
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "Update Status Enablement for Zion"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r6, r1)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper r6 = com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance()     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "A3IYPH06PH1HRA"
            java.lang.String r2 = "Alexa.Accessories.VipFilter.statusEnablement"
            java.lang.Boolean r3 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionStatusEnablement     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r4 = "Alexa.Accessories.VipFilter.statusEnablement"
            r6.put(r1, r2, r3, r4)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            goto Lb3
        L71:
            com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.isApproveInvitationOnAccessoryFetched = r5     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.Boolean r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionApproveInvitationOnAccessory     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto Lb3
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "Update Approve Invitation On Approve for Zion"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r6, r1)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper r6 = com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance()     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "A3IYPH06PH1HRA"
            java.lang.String r2 = "Alexa.Accessories.VipFilter.approveInvitationOnAccessory"
            java.lang.Boolean r3 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionApproveInvitationOnAccessory     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r4 = "Alexa.Accessories.VipFilter.approveInvitationOnAccessory"
            r6.put(r1, r2, r3, r4)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            goto Lb3
        L8e:
            com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.isForwardNotificationToAccessoryFetched = r5     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.Boolean r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionForwardNotificationToAccessory     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            if (r6 == 0) goto Lb3
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "Update forward notification to Accessory for Zion"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r6, r1)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper r6 = com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance()     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r1 = "A3IYPH06PH1HRA"
            java.lang.String r2 = "Alexa.Accessories.VipFilter.forwardNotificationToAccessoryEnablement"
            java.lang.Boolean r3 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.oldZionForwardNotificationToAccessory     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            java.lang.String r4 = "Alexa.Accessories.VipFilter.forwardNotificationToAccessoryEnablement"
            r6.put(r1, r2, r3, r4)     // Catch: java.lang.Exception -> Lab java.lang.Throwable -> Lb5
            goto Lb3
        Lab:
            r6 = move-exception
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.TAG     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = "Exception on update fetch status"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r1, r2, r6)     // Catch: java.lang.Throwable -> Lb5
        Lb3:
            monitor-exit(r0)
            return
        Lb5:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.storage.MigrationHelper.updateFetchStatus(java.lang.String):void");
    }
}
