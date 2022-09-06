package com.amazon.device.crashmanager;

import android.content.Context;
import com.amazon.device.crashmanager.utils.AmazonPackageLookup;
import com.amazon.device.crashmanager.utils.CrashDescriptor3rdPartyDedupeUtil;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.utils.det.DomainChooser;
import com.amazon.device.utils.det.NetworkManager;
/* loaded from: classes12.dex */
public class CrashDetectionHelperUtil {
    private static final String SHARED_PREF_NAME = "CrashDetectionHelper.crashManager3rdParty";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AmazonPackageLookup getAmazonPackageLookup(Context context) {
        return new AmazonPackageLookup(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AppFileArtifactSource getAppFileArtifactSource(Context context, CrashDetailsAggregator crashDetailsAggregator, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        return new AppFileArtifactSource(context, crashDetailsAggregator, crashDescriptorDedupeUtil);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CrashDescriptorDedupeUtil getCrashDescriptorDedupeUtil(Context context) {
        return new CrashDescriptor3rdPartyDedupeUtil(context.getSharedPreferences(SHARED_PREF_NAME, 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CrashDetailsAggregator getCrashDetailsAggregator(Context context) {
        CrashDetailsAggregator crashDetailsAggregator = new CrashDetailsAggregator();
        crashDetailsAggregator.appendCollector(new CrashDetailsCollector(context));
        return crashDetailsAggregator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DomainChooser getDomainChooser(Context context) {
        return new ThirdPartyDomainChooser(context.getApplicationInfo());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NetworkManager getNetworkManager(Context context) {
        return NetworkManager.instance(context);
    }
}
