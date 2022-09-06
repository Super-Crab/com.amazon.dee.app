package com.amazon.identity.auth.device.authorization;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IInterface;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.HashAlgorithm;
import com.amazon.identity.auth.device.utils.MAPConstants;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.device.utils.PackageSignatureUtil;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.amazon.identity.auth.map.device.utils.MAPVersion;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ThirdPartyServiceHelper {
    private static final String AUTHORIZATION_SERVICE_INTENT_ACTION_NAME = "com.amazon.identity.auth.device.authorization.MapAuthorizationService";
    private static final String BINDING_ERROR_MESSAGE = "Binding to authorization service has timed out!";
    private static final long CONNECTION_TIMEOUT = 10;
    static String DEVO_FINGERPRINT = "7cac391937981b6134bdce1fd9834c253181f5abf91ded6078210d0f91ace360";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper";
    private static String PROD_FINGERPRINT = "2f19adeb284eb36f7f07786152b9a1d14b21653203ad0b04ebbf9c73ab6d7625";
    private static Object lock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class HighestVersionedService {
        static MAPServiceInfo mapServiceInfo;
        static long serviceDiscoveryTimestamp;

        HighestVersionedService() {
        }

        public static MAPServiceInfo getMAPServiceInfo() {
            return mapServiceInfo;
        }

        static boolean isServiceCacheStale() {
            return mapServiceInfo == null || new Date().getTime() > serviceDiscoveryTimestamp + 86400000;
        }

        static void setMAPServiceInfo(MAPServiceInfo mAPServiceInfo) {
            mapServiceInfo = mAPServiceInfo;
            if (mAPServiceInfo == null) {
                serviceDiscoveryTimestamp = 0L;
            } else {
                serviceDiscoveryTimestamp = new Date().getTime();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class MAPServiceInfo {
        private ThirdPartyAuthorizationServiceConnection mConnection;
        private final boolean mIsPrimary;
        private final MAPVersion mMapVersion;
        private final ResolveInfo mResolveInfo;
        private IInterface mService;
        private Intent mServiceIntent;

        public MAPServiceInfo(MAPVersion mAPVersion, IInterface iInterface, ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection, boolean z, ResolveInfo resolveInfo, Intent intent) {
            this.mMapVersion = mAPVersion;
            this.mService = iInterface;
            setConnection(thirdPartyAuthorizationServiceConnection);
            this.mIsPrimary = z;
            this.mResolveInfo = resolveInfo;
            this.mServiceIntent = intent;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ResolveInfo getResolveInfo() {
            return this.mResolveInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnection(ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection) {
            this.mConnection = thirdPartyAuthorizationServiceConnection;
        }

        public ThirdPartyAuthorizationServiceConnection getConnection() {
            return this.mConnection;
        }

        public MAPVersion getMapVersion() {
            return this.mMapVersion;
        }

        public IInterface getService() {
            return this.mService;
        }

        public Intent getServiceIntent() {
            return this.mServiceIntent;
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public void setService(IInterface iInterface) {
            this.mService = iInterface;
        }

        public void setServiceIntent(Intent intent) {
            this.mServiceIntent = intent;
        }
    }

    /* loaded from: classes12.dex */
    public static final class TOKEN_KEYS {
        public static final String ACCESS_ATZ_EXPIRES_IN = "accessAtzToken.expiries_in";
        public static final String ACCESS_ATZ_TOKEN = "accessAtzToken";
        public static final String REFRESH_ATZ_TOKEN = "refreshAtzToken";
    }

    public static void clearCachedService(Context context) {
        synchronized (lock) {
            MAPLog.i(LOG_TAG, "Clearing Highest Versioned Service");
            MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
            if (mAPServiceInfo != null) {
                safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                HighestVersionedService.setMAPServiceInfo(null);
            }
        }
    }

    private static boolean isFingerprintValid(Context context, Signature[] signatureArr) {
        if (MAPUtils.isDevo(context)) {
            MAPLog.i(LOG_TAG, "Attempting to check fingerprint in development environment");
            return isSameSignatureFingerprint(DEVO_FINGERPRINT, signatureArr[0]);
        }
        MAPLog.i(LOG_TAG, "Attempting to check fingerprint in production environment");
        return isSameSignatureFingerprint(PROD_FINGERPRINT, signatureArr[0]);
    }

    static boolean isSameSignatureFingerprint(String str, Signature signature) {
        try {
            String signatureFingerprint = PackageSignatureUtil.getSignatureFingerprint(signature, HashAlgorithm.SHA_256);
            String str2 = LOG_TAG;
            MAPLog.pii(str2, "Expected fingerprint", "Fingerprint=" + str);
            String str3 = LOG_TAG;
            MAPLog.pii(str3, "Extracted fingerprint", "Fingerprint=" + signatureFingerprint);
            return str.equals(signatureFingerprint);
        } catch (IOException e) {
            MAPLog.pii(LOG_TAG, "IOException getting Fingerprint. ", e.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e2) {
            MAPLog.pii(LOG_TAG, "NoSuchAlgorithmException getting Fingerprint. ", e2.getMessage());
            return false;
        } catch (CertificateException e3) {
            MAPLog.pii(LOG_TAG, "CertificateException getting Fingerprint. ", e3.getMessage());
            return false;
        }
    }

    public static void safeUnbind(Context context, ServiceConnection serviceConnection, Intent intent) {
        String packageName = intent != null ? intent.getComponent().getPackageName() : null;
        String str = LOG_TAG;
        MAPLog.d(str, "Unbinding pkg=" + packageName);
        if (serviceConnection != null) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException unused) {
                Log.w(LOG_TAG, String.format("IllegalArgumentException is received during unbinding from %s. Ignored.", packageName));
            }
        }
    }

    public static void unbind(Context context) {
        synchronized (lock) {
            MAPLog.i(LOG_TAG, "Unbinding Highest Versioned Service");
            MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
            if (mAPServiceInfo != null && mAPServiceInfo.getConnection() != null) {
                safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                mAPServiceInfo.setService(null);
                mAPServiceInfo.setConnection(null);
                mAPServiceInfo.setServiceIntent(null);
            }
        }
    }

    boolean bindHighestVersionedService(Context context) throws AuthError {
        if (HighestVersionedService.isServiceCacheStale()) {
            return false;
        }
        final MAPServiceInfo mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
        ServiceInfo serviceInfo = mAPServiceInfo.getResolveInfo().serviceInfo;
        ComponentName componentName = new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name);
        final Intent intent = new Intent();
        intent.setComponent(componentName);
        final ThirdPartyAuthorizationServiceConnection thirdPartyAuthorizationServiceConnection = new ThirdPartyAuthorizationServiceConnection();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        thirdPartyAuthorizationServiceConnection.setServiceListener(new AmazonServiceListener() { // from class: com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper.1
            @Override // com.amazon.identity.auth.device.authorization.AmazonServiceListener
            public void onBindError(AuthError authError) {
                mAPServiceInfo.setService(null);
                mAPServiceInfo.setConnection(null);
                mAPServiceInfo.setServiceIntent(null);
                MAPLog.i(ThirdPartyServiceHelper.LOG_TAG, "Bind - error");
                countDownLatch.countDown();
            }

            @Override // com.amazon.identity.auth.device.authorization.AmazonServiceListener
            public void onBindSuccess(IInterface iInterface) {
                mAPServiceInfo.setService(iInterface);
                mAPServiceInfo.setConnection(thirdPartyAuthorizationServiceConnection);
                mAPServiceInfo.setServiceIntent(intent);
                countDownLatch.countDown();
            }
        });
        if (context.bindService(intent, thirdPartyAuthorizationServiceConnection, 5)) {
            try {
                MAPLog.i(LOG_TAG, "Awaiting latch");
                if (!countDownLatch.await(10L, TimeUnit.SECONDS)) {
                    MAPLog.w(LOG_TAG, "Unable to establish bind within timelimit = 10");
                    HighestVersionedService.setMAPServiceInfo(null);
                    throw new AuthError(BINDING_ERROR_MESSAGE, AuthError.ERROR_TYPE.ERROR_THREAD);
                }
            } catch (InterruptedException e) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("msg+=");
                outline107.append(e.getMessage());
                MAPLog.pii(str, PhotosMetricsConstants.INTERRUPTED_EXCEPTION, outline107.toString());
                HighestVersionedService.setMAPServiceInfo(null);
                throw new AuthError(BINDING_ERROR_MESSAGE, e, AuthError.ERROR_TYPE.ERROR_THREAD);
            }
        } else {
            HighestVersionedService.setMAPServiceInfo(null);
            String str2 = LOG_TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Bind Service ");
            outline1072.append(intent.getComponent().flattenToString());
            outline1072.append("unsuccessful");
            MAPLog.w(str2, outline1072.toString());
        }
        return true;
    }

    MAPServiceInfo findAuthorizationService(List<MAPServiceInfo> list) {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of MAP services to compare = ");
        outline107.append(list.size());
        MAPLog.i(str, outline107.toString());
        MAPServiceInfo mAPServiceInfo = null;
        for (MAPServiceInfo mAPServiceInfo2 : list) {
            if (mAPServiceInfo == null || mAPServiceInfo2.getMapVersion().compare(mAPServiceInfo.getMapVersion()) > 0) {
                mAPServiceInfo = mAPServiceInfo2;
            }
        }
        return mAPServiceInfo;
    }

    List<MAPServiceInfo> getAllAuthorizationServices(Context context, List<ResolveInfo> list, MAPServiceInfo mAPServiceInfo) throws AuthError {
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : list) {
            if (mAPServiceInfo != null && resolveInfo.serviceInfo.applicationInfo.packageName.equals(mAPServiceInfo.mResolveInfo.serviceInfo.applicationInfo.packageName)) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring previous service =");
                outline107.append(resolveInfo.serviceInfo.name);
                MAPLog.i(str, outline107.toString());
            } else {
                try {
                    String str2 = LOG_TAG;
                    MAPLog.i(str2, "Verifying signature for pkg=" + resolveInfo.serviceInfo.applicationInfo.packageName);
                    Signature[] signatureArr = context.getPackageManager().getPackageInfo(resolveInfo.serviceInfo.applicationInfo.packageName, 64).signatures;
                    if (signatureArr.length == 1) {
                        if (!isFingerprintValid(context, signatureArr)) {
                            MAPLog.pii(LOG_TAG, "Security check failure", "Signature is incorrect.");
                        } else {
                            ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.applicationInfo.packageName, resolveInfo.serviceInfo.name);
                            Bundle bundle = context.getPackageManager().getServiceInfo(componentName, 128).metaData;
                            if (bundle != null) {
                                boolean z = bundle.getBoolean(MAPConstants.MAP_IS_PRIMARY);
                                String string = bundle.getString(MAPConstants.MAP_VERSION_KEY);
                                if (!TextUtils.isEmpty(string) || z) {
                                    arrayList.add(new MAPServiceInfo(z ? MAPVersion.VERSION_ZERO : new MAPVersion(string), null, new ThirdPartyAuthorizationServiceConnection(), z, resolveInfo, new Intent().setComponent(componentName)));
                                }
                            }
                        }
                    } else {
                        String str3 = LOG_TAG;
                        MAPLog.pii(str3, "Security count failure", "Signature count (" + signatureArr.length + ") is incorrect.");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    String str4 = LOG_TAG;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("msg=");
                    outline1072.append(e.getMessage());
                    MAPLog.pii(str4, "NameNotFoundException.", outline1072.toString());
                }
            }
        }
        return arrayList;
    }

    protected IInterface getAuthorizationServiceInstance(Context context, boolean z) throws AuthError {
        MAPServiceInfo mAPServiceInfo;
        if (!ThreadUtils.isRunningOnMainThread()) {
            MAPLog.d(LOG_TAG, "getAuthorizationServiceInstance");
            synchronized (lock) {
                IInterface iInterface = null;
                if (z) {
                    mAPServiceInfo = HighestVersionedService.getMAPServiceInfo();
                    if (mAPServiceInfo != null) {
                        safeUnbind(context, mAPServiceInfo.getConnection(), mAPServiceInfo.getServiceIntent());
                        HighestVersionedService.setMAPServiceInfo(null);
                    }
                } else {
                    MAPServiceInfo mAPServiceInfo2 = HighestVersionedService.getMAPServiceInfo();
                    if (mAPServiceInfo2 != null) {
                        safeUnbind(context, mAPServiceInfo2.getConnection(), mAPServiceInfo2.getServiceIntent());
                        if (bindHighestVersionedService(context)) {
                            return mAPServiceInfo2.getService();
                        }
                        HighestVersionedService.setMAPServiceInfo(null);
                    }
                    mAPServiceInfo = null;
                }
                PackageManager packageManager = context.getPackageManager();
                Intent intent = new Intent();
                intent.setAction(AUTHORIZATION_SERVICE_INTENT_ACTION_NAME);
                List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of services found : ");
                outline107.append(queryIntentServices.size());
                MAPLog.i(str, outline107.toString());
                List<MAPServiceInfo> allAuthorizationServices = getAllAuthorizationServices(context, queryIntentServices, mAPServiceInfo);
                synchronized (lock) {
                    HighestVersionedService.setMAPServiceInfo(findAuthorizationService(allAuthorizationServices));
                    if (HighestVersionedService.getMAPServiceInfo() == null) {
                        MAPLog.i(LOG_TAG, "Returning no service to use");
                        return null;
                    }
                    bindHighestVersionedService(context);
                    MAPLog.i(LOG_TAG, "Returning service to use");
                    MAPServiceInfo mAPServiceInfo3 = HighestVersionedService.getMAPServiceInfo();
                    if (mAPServiceInfo3 != null) {
                        iInterface = mAPServiceInfo3.getService();
                    }
                    return iInterface;
                }
            }
        }
        MAPLog.e(LOG_TAG, "getAuthorizationServiceInstance started on main thread");
        throw new IllegalStateException("getAuthorizationServiceInstance started on main thread");
    }

    public AmazonAuthorizationServiceInterface getRemoteAndroidService(Context context, boolean z) throws AuthError {
        String str = LOG_TAG;
        MAPLog.i(str, "Attempting to retrieve remote Android service. Ignore cached service=" + z);
        return (AmazonAuthorizationServiceInterface) getAuthorizationServiceInstance(context, z);
    }
}
