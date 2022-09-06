package com.amazon.photos.discovery.dedupe.stages;

import android.content.ContentResolver;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DigestCalculatorStage_MembersInjector implements MembersInjector<DigestCalculatorStage> {
    private final Provider<DiscoveryConfiguration> configurationProvider;
    private final Provider<ContentResolver> contentResolverProvider;
    private final Provider<FileUtils> fileUtilsProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<MD5Fingerprint> md5FingerprintProvider;
    private final Provider<MediaStoreUtil> mediaStoreUtilProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<SystemUtil> systemUtilProvider;

    public DigestCalculatorStage_MembersInjector(Provider<Metrics> provider, Provider<Logger> provider2, Provider<SystemUtil> provider3, Provider<FileUtils> provider4, Provider<MD5Fingerprint> provider5, Provider<ContentResolver> provider6, Provider<MediaStoreUtil> provider7, Provider<DiscoveryConfiguration> provider8) {
        this.metricsProvider = provider;
        this.loggerProvider = provider2;
        this.systemUtilProvider = provider3;
        this.fileUtilsProvider = provider4;
        this.md5FingerprintProvider = provider5;
        this.contentResolverProvider = provider6;
        this.mediaStoreUtilProvider = provider7;
        this.configurationProvider = provider8;
    }

    public static MembersInjector<DigestCalculatorStage> create(Provider<Metrics> provider, Provider<Logger> provider2, Provider<SystemUtil> provider3, Provider<FileUtils> provider4, Provider<MD5Fingerprint> provider5, Provider<ContentResolver> provider6, Provider<MediaStoreUtil> provider7, Provider<DiscoveryConfiguration> provider8) {
        return new DigestCalculatorStage_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectConfiguration(DigestCalculatorStage digestCalculatorStage, DiscoveryConfiguration discoveryConfiguration) {
        digestCalculatorStage.configuration = discoveryConfiguration;
    }

    public static void injectContentResolver(DigestCalculatorStage digestCalculatorStage, ContentResolver contentResolver) {
        digestCalculatorStage.contentResolver = contentResolver;
    }

    public static void injectFileUtils(DigestCalculatorStage digestCalculatorStage, FileUtils fileUtils) {
        digestCalculatorStage.fileUtils = fileUtils;
    }

    public static void injectLogger(DigestCalculatorStage digestCalculatorStage, Logger logger) {
        digestCalculatorStage.logger = logger;
    }

    public static void injectMd5Fingerprint(DigestCalculatorStage digestCalculatorStage, MD5Fingerprint mD5Fingerprint) {
        digestCalculatorStage.md5Fingerprint = mD5Fingerprint;
    }

    public static void injectMediaStoreUtil(DigestCalculatorStage digestCalculatorStage, MediaStoreUtil mediaStoreUtil) {
        digestCalculatorStage.mediaStoreUtil = mediaStoreUtil;
    }

    public static void injectMetrics(DigestCalculatorStage digestCalculatorStage, Metrics metrics) {
        digestCalculatorStage.metrics = metrics;
    }

    public static void injectSystemUtil(DigestCalculatorStage digestCalculatorStage, SystemUtil systemUtil) {
        digestCalculatorStage.systemUtil = systemUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DigestCalculatorStage digestCalculatorStage) {
        injectMetrics(digestCalculatorStage, this.metricsProvider.mo10268get());
        injectLogger(digestCalculatorStage, this.loggerProvider.mo10268get());
        injectSystemUtil(digestCalculatorStage, this.systemUtilProvider.mo10268get());
        injectFileUtils(digestCalculatorStage, this.fileUtilsProvider.mo10268get());
        injectMd5Fingerprint(digestCalculatorStage, this.md5FingerprintProvider.mo10268get());
        injectContentResolver(digestCalculatorStage, this.contentResolverProvider.mo10268get());
        injectMediaStoreUtil(digestCalculatorStage, this.mediaStoreUtilProvider.mo10268get());
        injectConfiguration(digestCalculatorStage, this.configurationProvider.mo10268get());
    }
}
