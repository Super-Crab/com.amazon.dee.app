package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.utils.DataDirectoryProvider;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.RecordingTracker;
import com.amazon.alexa.wakeword.WakeWordArbitration;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import com.amazon.alexa.wakeword.davs.ArtifactDownloader;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelAuthority;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Singleton;
/* compiled from: WakeWordModule.java */
@Module
/* loaded from: classes.dex */
public class iPU {
    @Provides
    @Singleton
    public DataDirectoryProvider BIo(Context context) {
        return new oee(this, context);
    }

    @Provides
    @Singleton
    public RfA jiA() {
        return new RfA();
    }

    @Provides
    @Singleton
    public PackageNameProvider zQM(Context context) {
        return new yrG(this, context);
    }

    @Provides
    @Singleton
    public LocaleProvider zZm(MBE mbe) {
        return new mqg(mbe);
    }

    @Provides
    @Singleton
    public WakeWordArbitration zyO() {
        return new WakeWordArbitration();
    }

    @Provides
    @Singleton
    public RecordingTracker BIo() {
        return new RecordingTracker();
    }

    @Provides
    @Singleton
    public GSf zQM() {
        return new GSf();
    }

    @Provides
    @Singleton
    public WakeWordDetectorProvider zZm(WakeWordModelAuthority wakeWordModelAuthority, LocaleProvider localeProvider, TimeProvider timeProvider, FdV fdV, WakeWordDownloadManager wakeWordDownloadManager) {
        return new WakeWordDetectorProvider(wakeWordModelAuthority, localeProvider, timeProvider, new QoN(this), fdV, wakeWordDownloadManager);
    }

    @Provides
    @Singleton
    public WakeWordModelAuthority zZm(WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, FdV fdV, WakeWordDownloadManager wakeWordDownloadManager, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, ClientConfiguration clientConfiguration) {
        return new WakeWordModelAuthority(wakeWordModelContentProviderHelper, fdV, wakeWordDownloadManager, clientConfiguration.getBackupWakeWordModelAssetFileName());
    }

    @Provides
    @Singleton
    public MultiWakeWordFeatureEnabledProvider zZm(gSO gso, Lazy<ClientConfiguration> lazy) {
        return new kKu(this, lazy, gso);
    }

    @Provides
    @Singleton
    public WakeWordModelContentProviderHelper zZm(Context context, PackageNameProvider packageNameProvider) {
        return new WakeWordModelContentProviderHelper(context.getContentResolver(), packageNameProvider);
    }

    @Provides
    @Singleton
    public WakeWordDownloadManager zZm(WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, ArtifactManager artifactManager, TimeProvider timeProvider, NetworkManager networkManager, FdV fdV, DavsClient davsClient, CrashReporter crashReporter, RfA rfA, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, DataDirectoryProvider dataDirectoryProvider, MultiWakeWordFeatureEnabledProvider multiWakeWordFeatureEnabledProvider) {
        return new DavsWakeWordDownloadManager(wakeWordModelContentProviderHelper, artifactManager, timeProvider, networkManager, fdV, davsClient, crashReporter, rfA, alexaHandsFreeDeviceInformation.isCurrentDeviceHandsFree(), dataDirectoryProvider, multiWakeWordFeatureEnabledProvider);
    }

    @Provides
    @Singleton
    public ArtifactManager zZm(Context context) {
        return new ArtifactManager(context);
    }

    @Provides
    @Singleton
    public NetworkManager zZm(TokenProvider tokenProvider) {
        return new NetworkManager(tokenProvider);
    }

    @Provides
    @Singleton
    public DavsClient zZm(NetworkManager networkManager, ArtifactManager artifactManager) {
        return new DavsClient(networkManager.getAuthorizedHttpClient(), new ArtifactDownloader(networkManager.getUnauthorizedHttpClient(), artifactManager));
    }

    @Provides
    @Singleton
    public AudioCapturerAuthority zZm(RecordingTracker recordingTracker, WakeWordDetectorProvider wakeWordDetectorProvider) {
        return AudioCapturerAuthority.create(recordingTracker, wakeWordDetectorProvider);
    }

    @Provides
    @Singleton
    public WakeWordDetectionController zZm(Context context, AudioCapturerAuthority audioCapturerAuthority) {
        return new WakeWordDetectionController(context, audioCapturerAuthority);
    }

    @Provides
    @Singleton
    public FLQ zZm() {
        return new FLQ();
    }

    @Provides
    @Singleton
    public Set<InternalWakeWordPrecondition> zZm(jxu jxuVar, Snr snr, GSf gSf, RecordingTracker recordingTracker, FLQ flq) {
        return new HashSet(Arrays.asList(jxuVar, snr, gSf, recordingTracker.getWakeWordPrecondition(), flq));
    }
}
