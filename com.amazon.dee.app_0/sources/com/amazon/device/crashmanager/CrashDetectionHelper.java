package com.amazon.device.crashmanager;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;
import android.util.Log;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.ndk.NDKCrashDetector;
import com.amazon.device.crashmanager.processor.JavaCrashArtifactProcessor;
import com.amazon.device.crashmanager.processor.MetricsHeaderProcessorFactory;
import com.amazon.device.crashmanager.processor.NativeCrashArtifactProcessor;
import com.amazon.device.crashmanager.utils.AmazonPackageLookup;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.DetEndpointConfig;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.device.utils.det.Domain;
import com.amazon.device.utils.det.DomainChooser;
import com.amazon.device.utils.det.NetworkManager;
import com.amazon.device.utils.det.NullStatusNotifier;
import com.amazon.device.utils.det.StatusNotifier;
import java.lang.Thread;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes12.dex */
public final class CrashDetectionHelper implements Thread.UncaughtExceptionHandler {
    static final String CRASH_TYPE_ERROR = "data_app_crash";
    private static final String DEFAULT_DET_BETA_URN = "https://det-ta-g7g.integ.amazon.com:443/DeviceEventProxy/DETLogServlet";
    private static final String DEFAULT_DET_PROD_URN = "https://det-ta-g7g.amazon.com:443/DeviceEventProxy/DETLogServlet";
    static final String KEY_COUNTRY_OF_RESIDENCE = "countryOfResidence";
    static final String KEY_MARKET_PLACE_ID = "MarketplaceID";
    private String countryOfResidence;
    private final boolean mAllowWANUpload;
    private final AmazonPackageLookup mAmazonPackageLookup;
    private final AppFileArtifactSource mAppFileArtifactSource;
    private CrashDescriptorDedupeUtil mCrashDescUtil;
    private final CrashDetailsAggregator mCrashDetailAggregator;
    private final String mDeviceId;
    private final String mDeviceType;
    private final DomainChooser mDomainChooser;
    private final MetricsFactory mMetricsFactory;
    private final NetworkManager mNetworkManager;
    private final Thread.UncaughtExceptionHandler mPreviousHandler = Thread.getDefaultUncaughtExceptionHandler();
    private final StatusNotifier mStatusNotifier = new NullStatusNotifier();
    private final boolean mUploadCrashBeforeRestart;
    private String marketPlaceId;
    private static final String TAG = CrashDetectionHelper.class.getName();
    private static final ExecutorService SINGLE_THREADED_EXECUTOR = Executors.newSingleThreadExecutor();
    private static CrashDetectionHelper sCrashDetectionHelper = null;
    private static final int ANDROID_API_VERSION = Build.VERSION.SDK_INT;

    /* loaded from: classes12.dex */
    public enum CrashTypeCaughtException {
        CRASH_TYPE_JAVA_EXCEPTION("data_app_exception"),
        CRASH_TYPE_NATIVE_EXCEPTION("data_native_exception");
        
        private String exceptionType;

        CrashTypeCaughtException(String str) {
            this.exceptionType = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.exceptionType;
        }
    }

    static {
        DetEndpointConfig.addEndPoint(Domain.BETA, DEFAULT_DET_BETA_URN);
        DetEndpointConfig.addEndPoint(Domain.PROD, DEFAULT_DET_PROD_URN);
    }

    CrashDetectionHelper(String str, String str2, MetricsFactory metricsFactory, AppFileArtifactSource appFileArtifactSource, AmazonPackageLookup amazonPackageLookup, DomainChooser domainChooser, NetworkManager networkManager, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil, CrashDetailsAggregator crashDetailsAggregator, boolean z, boolean z2) {
        this.mAppFileArtifactSource = appFileArtifactSource;
        this.mAmazonPackageLookup = amazonPackageLookup;
        this.mMetricsFactory = metricsFactory;
        this.mDomainChooser = domainChooser;
        this.mDeviceType = str;
        this.mDeviceId = str2;
        this.mAllowWANUpload = z;
        this.mUploadCrashBeforeRestart = z2;
        this.mNetworkManager = networkManager;
        this.mCrashDescUtil = crashDescriptorDedupeUtil;
        this.mCrashDetailAggregator = crashDetailsAggregator;
    }

    public static void enableNDKCrashDetection(Context context) {
        new NDKCrashDetector().enableNDKCrash(context);
    }

    public static CrashDetectionHelper getInstance() {
        if (ANDROID_API_VERSION < 9) {
            Log.e(TAG, "Could not set up crash detection, android versions before Gingerbread <9 are known to crash.");
            return null;
        }
        if (sCrashDetectionHelper == null) {
            Log.w(TAG, "CrashDetectionHelper.getInstance() called before CrashDetectionHelper.setUpCrashDetection().");
        }
        return sCrashDetectionHelper;
    }

    public static CrashDetectionHelper setUpCrashDetection(String str, String str2, MetricsFactory metricsFactory, Context context) {
        if (context == null) {
            Log.e(TAG, "Could not set up crash detection, context is null.");
            return null;
        }
        return setUpCrashDetection(str, str2, metricsFactory, CrashDetectionHelperUtil.getDomainChooser(context), context, true);
    }

    public void appendCrashDetailsCollector(CrashDetailsCollectable crashDetailsCollectable) {
        CrashDetailsAggregator crashDetailsAggregator = this.mCrashDetailAggregator;
        if (crashDetailsAggregator == null) {
            Log.e(TAG, "Could not set up additional crash detail collector. Initialize CrashDetectionHelper first.");
        } else {
            crashDetailsAggregator.appendCollector(crashDetailsCollectable);
        }
    }

    public void caughtException(Throwable th) {
        this.mAppFileArtifactSource.saveCrash(th, CrashTypeCaughtException.CRASH_TYPE_JAVA_EXCEPTION.toString(), null);
        uploadCrashReportAsync();
    }

    Map<String, String> getExtraDeviceInfo() {
        HashMap hashMap = new HashMap();
        String str = this.countryOfResidence;
        if (str != null) {
            hashMap.put("countryOfResidence", str);
        }
        String str2 = this.marketPlaceId;
        if (str2 != null) {
            hashMap.put("MarketplaceID", str2);
        }
        return hashMap;
    }

    public void reportCrash(Throwable th) {
        this.mAppFileArtifactSource.saveCrash(th);
        uploadCrashReportAsync();
    }

    public void setCountryOfResidence(String str) {
        this.countryOfResidence = str;
    }

    public void setMarketPlaceId(String str) {
        this.marketPlaceId = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            this.mAppFileArtifactSource.saveCrash(th);
            if (this.mUploadCrashBeforeRestart) {
                uploadCrashReportAsync();
            }
        } finally {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mPreviousHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    void uploadCrashReport() {
        if (!this.mNetworkManager.isWifiConnected() && !this.mNetworkManager.isEthernetConnected() && !this.mAllowWANUpload) {
            Log.i(TAG, "Skipping crash report upload. WiFi/Ethernet is not connected AND upload over WAN is not allowed");
            return;
        }
        Log.i(TAG, "Uploading Crash Report");
        MetricEvent createMetricEvent = this.mMetricsFactory.createMetricEvent(MetricsConstants.PROGRAM_NAME, MetricsConstants.SOURCE_NAME_UPLOAD_CRASH);
        try {
            try {
                DetUtil detUtil = new DetUtil();
                LinkedList linkedList = new LinkedList();
                linkedList.add(this.mAppFileArtifactSource);
                MetricsHeaderProcessorFactory metricsHeaderProcessorFactory = new MetricsHeaderProcessorFactory(this.mAmazonPackageLookup, createMetricEvent);
                LinkedList linkedList2 = new LinkedList();
                Map<String, String> extraDeviceInfo = getExtraDeviceInfo();
                linkedList2.add(new JavaCrashArtifactProcessor(detUtil, this.mDeviceType, extraDeviceInfo, metricsHeaderProcessorFactory, this.mCrashDescUtil));
                linkedList2.add(new NativeCrashArtifactProcessor(detUtil, this.mDeviceType, extraDeviceInfo, metricsHeaderProcessorFactory, this.mCrashDescUtil));
                ArtifactUploader artifactUploader = new ArtifactUploader(this.mDomainChooser.chooseDomain(), this.mDeviceType, this.mDeviceId, linkedList, linkedList2, this.mCrashDescUtil, this.mStatusNotifier, this.mNetworkManager, this.mAllowWANUpload);
                TrafficStats.setThreadStatsTag(Constants.THREAD_TAG);
                artifactUploader.uploadArtifacts(createMetricEvent);
            } catch (Exception e) {
                Log.e(TAG, "Exception thrown while uploading crash entries", e);
            }
        } finally {
            TrafficStats.clearThreadStatsTag();
            this.mMetricsFactory.record(createMetricEvent);
        }
    }

    public void uploadCrashReportAsync() {
        try {
            SINGLE_THREADED_EXECUTOR.execute(new Runnable() { // from class: com.amazon.device.crashmanager.CrashDetectionHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        CrashDetectionHelper.this.uploadCrashReport();
                    } catch (Exception e) {
                        Log.e(CrashDetectionHelper.TAG, "Failed to upload crash.", e);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            Log.e(TAG, "Failed to upload crash due to failure in accepting the task to execute", e);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to upload crash", e2);
        }
    }

    public void caughtException(Throwable th, CrashTypeCaughtException crashTypeCaughtException, CrashDetailsCollectable crashDetailsCollectable) {
        this.mAppFileArtifactSource.saveCrash(th, crashTypeCaughtException.toString(), crashDetailsCollectable);
        uploadCrashReportAsync();
    }

    public static CrashDetectionHelper setUpCrashDetection(String str, String str2, MetricsFactory metricsFactory, DomainChooser domainChooser, Context context, boolean z) {
        return setUpCrashDetection(str, str2, metricsFactory, domainChooser, context, z, false, false);
    }

    public static CrashDetectionHelper setUpCrashDetection(String str, String str2, MetricsFactory metricsFactory, DomainChooser domainChooser, Context context, boolean z, boolean z2, boolean z3) {
        if (ANDROID_API_VERSION < 9) {
            Log.e(TAG, "Could not set up crash detection, android versions before Gingerbread < 9 are known to crash.");
            return null;
        } else if (str == null) {
            Log.e(TAG, "Could not set up crash detection, device type is null.");
            return null;
        } else if (str2 == null) {
            Log.e(TAG, "Could not set up crash detection, device id is null.");
            return null;
        } else if (metricsFactory == null) {
            Log.e(TAG, "Could not set up crash detection, metrics factory is null.");
            return null;
        } else if (context == null) {
            Log.e(TAG, "Could not set up crash detection, context is null.");
            return null;
        } else if (domainChooser == null) {
            Log.e(TAG, "Could not set up crash detection, domainChooser is null.");
            return null;
        } else {
            synchronized (CrashDetectionHelper.class) {
                if (sCrashDetectionHelper == null) {
                    CrashDetailsAggregator crashDetailsAggregator = CrashDetectionHelperUtil.getCrashDetailsAggregator(context);
                    CrashDescriptorDedupeUtil crashDescriptorDedupeUtil = CrashDetectionHelperUtil.getCrashDescriptorDedupeUtil(context);
                    sCrashDetectionHelper = new CrashDetectionHelper(str, str2, metricsFactory, CrashDetectionHelperUtil.getAppFileArtifactSource(context, crashDetailsAggregator, crashDescriptorDedupeUtil), CrashDetectionHelperUtil.getAmazonPackageLookup(context), domainChooser, CrashDetectionHelperUtil.getNetworkManager(context), crashDescriptorDedupeUtil, crashDetailsAggregator, z2, z3);
                    sCrashDetectionHelper.uploadCrashReportAsync();
                }
            }
            if (z) {
                if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CrashDetectionHelper)) {
                    Log.i(TAG, "Installing crash detector as default exception handler.");
                    Thread.setDefaultUncaughtExceptionHandler(sCrashDetectionHelper);
                } else {
                    Log.i(TAG, "Crash detector already set up.");
                }
            } else {
                Log.i(TAG, "Caller opted out of installing uncaught exception handler.");
            }
            return sCrashDetectionHelper;
        }
    }
}
