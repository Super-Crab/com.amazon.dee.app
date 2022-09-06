package com.amazon.whisperjoin.deviceprovisioningservice.wifi;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.credentiallocker.CredentialLockerClient;
import com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class WifiLocker {
    private static final String TAG = "WifiLocker";
    private CredentialLockerClient mCredLockClient;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker$4  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement;

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement = new int[com.amazon.whisperjoin.wifi.WifiKeyManagement.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[com.amazon.whisperjoin.wifi.WifiKeyManagement.WPA_PSK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[com.amazon.whisperjoin.wifi.WifiKeyManagement.WEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[com.amazon.whisperjoin.wifi.WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[com.amazon.whisperjoin.wifi.WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public WifiLocker(MapAuthenticationProvider mapAuthenticationProvider, Context context) {
        try {
            this.mCredLockClient = new CredentialLockerClient(context, mapAuthenticationProvider.getOAuthToken());
        } catch (PackageManager.NameNotFoundException e) {
            WJLog.w(TAG, "Name not found exception occured while instantiating Cred Locker Client", e);
            throw new RuntimeException(e);
        }
    }

    public List<WifiConfiguration> convertConfigsForWJv1Types(List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration> list) {
        WifiConfiguration createOpenWifiConfiguration;
        ArrayList arrayList = new ArrayList();
        for (com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration wifiConfiguration : list) {
            int ordinal = wifiConfiguration.getKeyManagement().ordinal();
            if (ordinal == 0) {
                createOpenWifiConfiguration = WifiConfiguration.createOpenWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getNetworkPriority(), wifiConfiguration.isHiddenNetwork());
            } else if (ordinal == 1) {
                createOpenWifiConfiguration = WifiConfiguration.createWpaWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getPsk(), wifiConfiguration.getNetworkPriority(), wifiConfiguration.isHiddenNetwork());
            } else if (ordinal != 2) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported key management: ");
                outline107.append(wifiConfiguration.getKeyManagement());
                WJLog.d(str, outline107.toString());
            } else {
                createOpenWifiConfiguration = WifiConfiguration.createWepWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getWepKey(), wifiConfiguration.getNetworkPriority(), wifiConfiguration.isHiddenNetwork());
            }
            arrayList.add(createOpenWifiConfiguration);
        }
        return arrayList;
    }

    public List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration> convertConfigsForWJv2Types(List<WifiConfiguration> list) {
        com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration createOpenWifiConfiguration;
        ArrayList arrayList = new ArrayList();
        for (WifiConfiguration wifiConfiguration : list) {
            int ordinal = wifiConfiguration.getKeyMgmt().ordinal();
            if (ordinal == 0) {
                createOpenWifiConfiguration = com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration.createOpenWifiConfiguration(wifiConfiguration.getSsid());
            } else if (ordinal == 1) {
                createOpenWifiConfiguration = com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration.createWpaWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getPsk());
            } else if (ordinal != 2) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported Key Management: ");
                outline107.append(wifiConfiguration.getKeyMgmt());
                WJLog.d(str, outline107.toString());
            } else {
                createOpenWifiConfiguration = com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration.createWepWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getWepKey());
            }
            arrayList.add(createOpenWifiConfiguration);
        }
        return arrayList;
    }

    public Single<List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration>> getWifiLocker() {
        return Single.create(new SingleOnSubscribe<List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker.2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public void subscribe(SingleEmitter<List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration>> singleEmitter) throws Exception {
                try {
                    List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration> convertConfigsForWJv2Types = WifiLocker.this.convertConfigsForWJv2Types(WifiLocker.this.mCredLockClient.getAllWifiConfigurations(new TargetDevice.Builder().withTargetManufacturer("WJ").withTargetName("DeviceProvisioningService").withTargetVersion("v2").build()));
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    singleEmitter.onSuccess(convertConfigsForWJv2Types);
                } catch (Exception e) {
                    WJLog.e(WifiLocker.TAG, "An Error occurred while getting wifi locker!");
                    if (singleEmitter.isDisposed()) {
                        WJLog.w(WifiLocker.TAG, "Already disposed, ignoring error");
                    } else {
                        singleEmitter.onError(e);
                    }
                }
            }
        }).onErrorReturn(new Function<Throwable, List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration> mo10358apply(Throwable th) throws Exception {
                WJLog.d(WifiLocker.TAG, "Returning empty list");
                return Collections.emptyList();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Completable saveToWifiLocker(final List<com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration> list) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker.3
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public void subscribe(@NonNull CompletableEmitter completableEmitter) throws Exception {
                try {
                    String str = WifiLocker.TAG;
                    WJLog.d(str, "Saving network to Wifi Locker: " + list);
                    WifiLocker.this.mCredLockClient.saveWifiConfigurations(WifiLocker.this.convertConfigsForWJv1Types(list));
                    if (completableEmitter.isDisposed()) {
                        return;
                    }
                    completableEmitter.onComplete();
                } catch (Exception e) {
                    if (!completableEmitter.isDisposed()) {
                        WJLog.e(WifiLocker.TAG, "An error occurred while saving wifi locker", e);
                        completableEmitter.onError(e);
                        return;
                    }
                    WJLog.w(WifiLocker.TAG, "Already disposed, ignoring error");
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
