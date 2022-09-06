package com.amazon.alexa.wakeword.speakerverification.model;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadException;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactDownloader;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.davs.ArtifactNameFactory;
import com.amazon.alexa.wakeword.davs.ArtifactPersistedData;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.davs.SpeakerVerificationModelArtifactInfo;
import com.amazon.alexa.wakeword.speakerverification.errors.ModelDownloadReason;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.sync.SpeakerVerificationSyncArtifactManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelAuthority {
    private static final String BACKUP_MODEL = "V_1400KB.en-US.alexa.bin";
    @VisibleForTesting
    public static final String DEFAULT_LOCALE = Locale.US.toLanguageTag();
    @VisibleForTesting
    public static final Long DOWNLOAD_TIME_ZERO = 0L;
    private static final String LOG_TAG = "SpeakerVerificationModelAuthority";
    @VisibleForTesting
    public static final String SPEAKER_VERIFICATION_CLASSIFICATION_MODEL_SHARED_PREF_NAME = "speaker_verification_classification_model_store";
    private static final String SPEAKER_VERIFICATION_EXECUTOR_SERVICE_NAME = "davsSpeakerVerification";
    @VisibleForTesting
    public static final String SPEAKER_VERIFICATION_REGENERATION_MODEL_SHARED_PREF_NAME = "speaker_verification_regeneration_model_store";
    private final SpeakerVerificationSyncArtifactManager artifactManager;
    private final Provider<SharedPreferences> classificationModelSharedPreferencesProvider;
    private final Context context;
    private final ScheduledExecutorService davsAccessExecutor;
    private DavsClient davsClient;
    private final Provider<SharedPreferences> regenerationModelSharedPreferencesProvider;
    private SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter;
    private final TimeProvider timeProvider;

    public SpeakerVerificationModelAuthority(final Context context, @Nullable TokenProvider tokenProvider) {
        this.context = context;
        this.davsAccessExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor(SPEAKER_VERIFICATION_EXECUTOR_SERVICE_NAME);
        this.timeProvider = new TimeProvider();
        this.artifactManager = new SpeakerVerificationSyncArtifactManager(context);
        this.regenerationModelSharedPreferencesProvider = new Provider() { // from class: com.amazon.alexa.wakeword.speakerverification.model.-$$Lambda$SpeakerVerificationModelAuthority$2avQxxj4eWXt29pnlsrWTTAKSp8
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public final Object mo2864get() {
                SharedPreferences sharedPreferences;
                sharedPreferences = context.getSharedPreferences(SpeakerVerificationModelAuthority.SPEAKER_VERIFICATION_REGENERATION_MODEL_SHARED_PREF_NAME, 4);
                return sharedPreferences;
            }
        };
        this.classificationModelSharedPreferencesProvider = new Provider() { // from class: com.amazon.alexa.wakeword.speakerverification.model.-$$Lambda$SpeakerVerificationModelAuthority$CIREQMUYIbLpCYQFjFn0bZPCzxQ
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public final Object mo2864get() {
                SharedPreferences sharedPreferences;
                sharedPreferences = context.getSharedPreferences(SpeakerVerificationModelAuthority.SPEAKER_VERIFICATION_CLASSIFICATION_MODEL_SHARED_PREF_NAME, 4);
                return sharedPreferences;
            }
        };
        this.speakerVerificationMetricsReporter = new SpeakerVerificationMetricsReporter(context, SpeakerVerificationMetricsReporter.MetricsSource.MODEL_DOWNLOAD);
        if (tokenProvider != null) {
            NetworkManager networkManager = new NetworkManager(tokenProvider);
            this.davsClient = new DavsClient(networkManager.getAuthorizedHttpClient(), new ArtifactDownloader(networkManager.getUnauthorizedHttpClient(), this.artifactManager));
            return;
        }
        Log.d(LOG_TAG, "Token provider is null, davsClient will not be set.");
    }

    private ArtifactDownloadResultListener createArtifactDownloadResultListener(final SpeakerVerificationRegenerationModelCallable speakerVerificationRegenerationModelCallable) {
        return new ArtifactDownloadResultListener() { // from class: com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.1
            @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
            public void onArtifactAlreadyUpToDate(long j, ArtifactModel artifactModel) {
                Log.i(SpeakerVerificationModelAuthority.LOG_TAG, String.format("Download already up to date, took %d ms, MD5: %s", Long.valueOf(j), CheckSumUtils.getMD5(artifactModel.getArtifactData())));
                SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadSuccess(j, ModelDownloadReason.ALREADY_DOWNLOADED, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
                speakerVerificationRegenerationModelCallable.regenerationModelCall(null);
            }

            @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
            public void onArtifactDownloadFailure(long j, String str, Exception exc, String str2) {
                Log.e(SpeakerVerificationModelAuthority.LOG_TAG, String.format("Download failure, request took %d ms, MD5: %s, reason: %s", Long.valueOf(j), str, str2), exc, new Object[0]);
                ModelDownloadReason modelDownloadReason = ModelDownloadReason.DOWNLOAD_FAILURE;
                modelDownloadReason.setErrorDetails(str2);
                SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadFailure(modelDownloadReason, str);
                speakerVerificationRegenerationModelCallable.regenerationModelCall(null);
            }

            @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
            public void onArtifactDownloadInterrupted(long j) {
                Log.e(SpeakerVerificationModelAuthority.LOG_TAG, String.format("Download interrupted, request took %d ms", Long.valueOf(j)));
                SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.DOWNLOAD_INTERRUPTED, "");
                speakerVerificationRegenerationModelCallable.regenerationModelCall(null);
            }

            @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
            public void onArtifactDownloadSuccess(long j, ArtifactModel artifactModel) {
                if (artifactModel.getArtifactData() == null) {
                    Log.e(SpeakerVerificationModelAuthority.LOG_TAG, "Download success, but artifact data is missing.");
                    SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.MODEL_DATA_NOT_PRESENT, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
                } else {
                    Log.i(SpeakerVerificationModelAuthority.LOG_TAG, String.format("Download success, took %d ms, MD5: %s", Long.valueOf(j), CheckSumUtils.getMD5(artifactModel.getArtifactData())));
                    if (artifactModel.getEngineCompatibilityId() == null || artifactModel.getArtifactIdentifier() == null || artifactModel.getArtifactDownloadedTime() == null || artifactModel.getLocale() == null) {
                        Log.e(SpeakerVerificationModelAuthority.LOG_TAG, "Downloaded persisted metadata was not stored or retrieved correctly.");
                        SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.MODEL_METADATA_NOT_PRESENT, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
                    } else if (!SpeakerVerificationModelAuthority.this.engineCompatibilityIdMatches(artifactModel.getEngineCompatibilityId())) {
                        Log.e(SpeakerVerificationModelAuthority.LOG_TAG, "Downloaded model is not engine compatible. Client may need to be updated.");
                        SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.ENGINE_INCOMPATIBLE, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
                    } else {
                        SpeakerVerificationModelAuthority.this.speakerVerificationMetricsReporter.onModelDownloadSuccess(j, ModelDownloadReason.DOWNLOADED_NEW_MODEL, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
                        speakerVerificationRegenerationModelCallable.regenerationModelCall(artifactModel);
                        return;
                    }
                }
                speakerVerificationRegenerationModelCallable.regenerationModelCall(null);
            }
        };
    }

    private String getCurrentEngineCompatibilityId() {
        return new SpeakerVerificationModelArtifactInfo(DEFAULT_LOCALE).getEngineCompatibilityId();
    }

    private ArtifactModel getPersistedModel(SharedPreferences sharedPreferences) {
        String persistedArtifactIdentifier = ArtifactPersistedData.getPersistedArtifactIdentifier(sharedPreferences);
        if (StringUtils.isBlank(persistedArtifactIdentifier)) {
            Log.i(LOG_TAG, "Model id is null or blank");
            return null;
        }
        Log.d(LOG_TAG, String.format("Model id is present: %s", persistedArtifactIdentifier));
        if (!engineCompatibilityIdMatches(sharedPreferences)) {
            Log.w(LOG_TAG, "Model is not engine compatible");
            return null;
        }
        Log.d(LOG_TAG, "Model is engine compatible");
        try {
            String artifactFilename = ArtifactNameFactory.getArtifactFilename(new SpeakerVerificationModelArtifactInfo(DEFAULT_LOCALE), persistedArtifactIdentifier);
            if (!this.artifactManager.hasArtifact(artifactFilename)) {
                Log.w(LOG_TAG, String.format("Model file %s does not exist", artifactFilename));
                return null;
            }
            Log.d(LOG_TAG, String.format("Model file %s exists", artifactFilename));
            try {
                InputStream artifactInputStream = this.artifactManager.readArtifact(artifactFilename).getArtifactInputStream();
                try {
                    byte[] byteArray = IOUtils.toByteArray(artifactInputStream);
                    if (artifactInputStream != null) {
                        artifactInputStream.close();
                    }
                    if (byteArray == null) {
                        Log.w(LOG_TAG, "Downloaded model is not present");
                        return null;
                    }
                    Log.i(LOG_TAG, "Model successfully retrieved");
                    ArtifactPersistedData fromSharedPreferences = ArtifactPersistedData.fromSharedPreferences(sharedPreferences);
                    return ArtifactModel.builder().setArtifactDownloadedTime(Long.valueOf(fromSharedPreferences.getDownloadTime())).setLocale(fromSharedPreferences.getLocale()).setArtifactIdentifier(fromSharedPreferences.getArtifactIdentifier()).setEngineCompatibilityId(fromSharedPreferences.getEngineCompatibilityId()).setArtifactData(byteArray).build();
                } finally {
                }
            } catch (ArtifactDownloadException e) {
                Log.e(LOG_TAG, "Artifact failed to be read", e, new Object[0]);
                return null;
            }
        } catch (IOException e2) {
            Log.e(LOG_TAG, "Downloaded data cannot be read", e2, new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.alexa.wakeword.davs.ArtifactModel getPreloadedModel() {
        /*
            r10 = this;
            java.lang.String r0 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.LOG_TAG
            java.lang.String r1 = "Getting the preloaded model from disk"
            com.amazon.alexa.handsfree.protocols.utils.Log.i(r0, r1)
            r0 = 0
            r1 = 0
            android.content.Context r2 = r10.context     // Catch: java.io.IOException -> L31 java.io.FileNotFoundException -> L3d
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.io.IOException -> L31 java.io.FileNotFoundException -> L3d
            java.lang.String r3 = "V_1400KB.en-US.alexa.bin"
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.io.IOException -> L31 java.io.FileNotFoundException -> L3d
            byte[] r3 = org.apache.commons.io.IOUtils.toByteArray(r2)     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L48
            r2.close()     // Catch: java.io.IOException -> L1f java.io.FileNotFoundException -> L21
            goto L48
        L1f:
            r2 = move-exception
            goto L33
        L21:
            r2 = move-exception
            goto L3f
        L23:
            r3 = move-exception
            throw r3     // Catch: java.lang.Throwable -> L25
        L25:
            r4 = move-exception
            if (r2 == 0) goto L30
            r2.close()     // Catch: java.lang.Throwable -> L2c
            goto L30
        L2c:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch: java.io.IOException -> L31 java.io.FileNotFoundException -> L3d
        L30:
            throw r4     // Catch: java.io.IOException -> L31 java.io.FileNotFoundException -> L3d
        L31:
            r2 = move-exception
            r3 = r1
        L33:
            java.lang.String r4 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.LOG_TAG
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r5 = "Preloaded model cannot currently be read from disk."
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r4, r5, r2, r0)
            goto L48
        L3d:
            r2 = move-exception
            r3 = r1
        L3f:
            java.lang.String r4 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.LOG_TAG
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r5 = "Preloaded model is not present."
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r4, r5, r2, r0)
        L48:
            if (r3 == 0) goto L82
            java.lang.String r0 = com.amazon.alexa.wakeword.davs.CheckSumUtils.getMD5(r3)
            com.amazon.alexa.wakeword.speakerverification.model.ModelType r5 = com.amazon.alexa.wakeword.speakerverification.model.ModelType.REGENERATION
            java.lang.String r7 = r10.getCurrentEngineCompatibilityId()
            java.lang.String r8 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.DEFAULT_LOCALE
            java.lang.Long r9 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.DOWNLOAD_TIME_ZERO
            r4 = r10
            r6 = r0
            r4.updateModelMetadata(r5, r6, r7, r8, r9)
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r1 = com.amazon.alexa.wakeword.davs.ArtifactModel.builder()
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r1 = r1.setArtifactData(r3)
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r0 = r1.setArtifactIdentifier(r0)
            java.lang.Long r1 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.DOWNLOAD_TIME_ZERO
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r0 = r0.setArtifactDownloadedTime(r1)
            java.lang.String r1 = r10.getCurrentEngineCompatibilityId()
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r0 = r0.setEngineCompatibilityId(r1)
            java.lang.String r1 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.DEFAULT_LOCALE
            com.amazon.alexa.wakeword.davs.ArtifactModel$Builder r0 = r0.setLocale(r1)
            com.amazon.alexa.wakeword.davs.ArtifactModel r0 = r0.build()
            return r0
        L82:
            java.lang.String r0 = com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.LOG_TAG
            java.lang.String r2 = "Cannot get the preloaded model from disk"
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority.getPreloadedModel():com.amazon.alexa.wakeword.davs.ArtifactModel");
    }

    public void downloadSpeakerVerificationModelAsync(SpeakerVerificationRegenerationModelCallable speakerVerificationRegenerationModelCallable) {
        Log.i(LOG_TAG, "Downloading new speaker verification regeneration model");
        this.davsAccessExecutor.execute(new SpeakerVerificationModelDownloadTask(new SpeakerVerificationModelArtifactInfo(DEFAULT_LOCALE), this.timeProvider, this.davsClient, this.regenerationModelSharedPreferencesProvider, this.artifactManager, createArtifactDownloadResultListener(speakerVerificationRegenerationModelCallable)));
    }

    public boolean engineCompatibilityIdMatches(@NonNull ModelType modelType) {
        SharedPreferences mo2864get;
        Log.i(LOG_TAG, String.format("Checking if the engine is compatible model for type: %s.", modelType.name()));
        if (ModelType.REGENERATION.equals(modelType)) {
            mo2864get = this.regenerationModelSharedPreferencesProvider.mo2864get();
        } else {
            mo2864get = this.classificationModelSharedPreferencesProvider.mo2864get();
        }
        return engineCompatibilityIdMatches(ArtifactPersistedData.getPersistedEngineCompatibilityId(mo2864get));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArtifactModel getModel(@NonNull ModelType modelType) {
        SharedPreferences mo2864get;
        Log.i(LOG_TAG, String.format("Getting the speaker verification model for type: %s.", modelType.name()));
        if (ModelType.REGENERATION.equals(modelType)) {
            mo2864get = this.regenerationModelSharedPreferencesProvider.mo2864get();
        } else {
            mo2864get = this.classificationModelSharedPreferencesProvider.mo2864get();
        }
        ArtifactModel persistedModel = getPersistedModel(mo2864get);
        if (persistedModel != null) {
            return persistedModel;
        }
        if (!ModelType.REGENERATION.equals(modelType)) {
            return null;
        }
        return getPreloadedModel();
    }

    public boolean persistRegenerationModelAsClassification() {
        Log.i(LOG_TAG, "Persisting pending regeneration model into the classification regeneration model store");
        ArtifactModel model = getModel(ModelType.REGENERATION);
        if (model == null) {
            Log.e(LOG_TAG, "Cannot persist regeneration model, as it is null.");
            return false;
        }
        String artifactFilename = ArtifactNameFactory.getArtifactFilename(new SpeakerVerificationModelArtifactInfo(DEFAULT_LOCALE), model.getArtifactIdentifier());
        Log.i(LOG_TAG, String.format("Attempting to write data for artifact id %s, filename: %s, md5: %s", model.getArtifactIdentifier(), artifactFilename, CheckSumUtils.getMD5(model.getArtifactData())));
        try {
            this.artifactManager.writeArtifact(artifactFilename, model.getArtifactData());
            Log.i(LOG_TAG, "Model data written successfully");
            updateModelMetadata(ModelType.CLASSIFICATION, model.getArtifactIdentifier(), model.getEngineCompatibilityId(), model.getLocale(), model.getArtifactDownloadedTime());
            Log.i(LOG_TAG, String.format("Wrote shared preferences for artifact id %s", model.getArtifactIdentifier()));
            return true;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error persisting model data. Do not write shared prefs.", e, new Object[0]);
            return false;
        }
    }

    public void updateModelMetadata(@NonNull ModelType modelType, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l) {
        SharedPreferences mo2864get;
        if (ModelType.REGENERATION.equals(modelType)) {
            mo2864get = this.regenerationModelSharedPreferencesProvider.mo2864get();
        } else {
            mo2864get = this.classificationModelSharedPreferencesProvider.mo2864get();
        }
        ArtifactPersistedData.Builder locale = ArtifactPersistedData.builder().setArtifactIdentifier(str).setEngineCompatibilityId(str2).setLocale(str3);
        if (l == null) {
            l = DOWNLOAD_TIME_ZERO;
        }
        locale.setDownloadTime(l.longValue()).build().persistArtifactSharedPreferences(mo2864get);
    }

    private boolean engineCompatibilityIdMatches(SharedPreferences sharedPreferences) {
        return engineCompatibilityIdMatches(ArtifactPersistedData.getPersistedEngineCompatibilityId(sharedPreferences));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean engineCompatibilityIdMatches(String str) {
        String currentEngineCompatibilityId = getCurrentEngineCompatibilityId();
        Log.d(LOG_TAG, String.format("Current engine compatibility id: %s, persisted engine compatibility id: %s", currentEngineCompatibilityId, str));
        return currentEngineCompatibilityId.equals(str);
    }

    public SpeakerVerificationModelAuthority(Context context) {
        this(context, null);
    }
}
