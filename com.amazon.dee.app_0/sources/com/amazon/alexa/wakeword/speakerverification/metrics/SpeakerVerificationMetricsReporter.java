package com.amazon.alexa.wakeword.speakerverification.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.wakeword.speakerverification.errors.ModelDownloadReason;
import com.amazon.alexa.wakeword.speakerverification.errors.ReadWriteFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.pryon.PryonOperation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes11.dex */
public class SpeakerVerificationMetricsReporter implements SpeakerVerificationMetricsListener {
    static final String CANCEL_ON_HANDS_FREE_DISABLED = "CANCEL_ON_HANDS_FREE_DISABLED";
    static final String DELETE_USER_DATA = "DELETE_USER_DATA";
    static final String DELETE_USER_DATA_FAILURE = "DELETE_USER_DATA_FAILURE";
    static final String GET_PROFILE = "GET_PROFILE";
    static final String GET_PROFILE_FAILURE = "GET_PROFILE_FAILURE";
    static final String GET_UTTERANCES = "GET_UTTERANCES";
    static final String GET_UTTERANCES_FAILURE = "GET_UTTERANCES_FAILURE";
    static final String MIC_INITIALIZATION = "MIC_INITIALIZATION";
    static final String MIC_INITIALIZATION_LATENCY = "MIC_INITIALIZATION_LATENCY";
    static final String MODEL_DOWNLOAD_COMPLETE = "MODEL_DOWNLOAD_COMPLETE";
    static final String MODEL_DOWNLOAD_COMPLETE_FAILURE = "MODEL_DOWNLOAD_COMPLETE_FAILURE";
    static final String MODEL_DOWNLOAD_INITIATED = "MODEL_DOWNLOAD_INITIATED";
    static final String MODEL_DOWNLOAD_LATENCY = "MODEL_DOWNLOAD_LATENCY";
    static final String PERSIST_MODEL = "PERSIST_MODEL";
    static final String PERSIST_MODEL_FAILURE = "PERSIST_MODEL_FAILURE";
    static final String PROFILE_GENERATION = "PROFILE_GENERATION";
    static final String PROFILE_GENERATION_ENGINE_SAME = "PROFILE_GENERATION_ENGINE_SAME";
    static final String PROFILE_GENERATION_ENGINE_UPDATE = "PROFILE_GENERATION_ENGINE_UPDATE";
    static final String PROFILE_GENERATION_FAILURE = "PROFILE_GENERATION_FAILURE";
    static final String PRYON_OPERATION = "PRYON_OPERATION";
    static final String PRYON_OPERATION_FAILURE = "PRYON_OPERATION_FAILURE";
    static final String PRYON_PUSH_EXAMPLE = "PRYON_PUSH_EXAMPLE";
    static final String SAVE_PROFILE = "SAVE_PROFILE";
    static final String SAVE_PROFILE_FAILURE = "SAVE_PROFILE_FAILURE";
    static final String SAVE_UTTERANCES = "SAVE_UTTERANCES";
    static final String SAVE_UTTERANCES_FAILURE = "SAVE_UTTERANCES_FAILURE";
    static final String SEPARATOR = ".";
    static final String START_PROFILE_GENERATION = "START_PROFILE_GENERATION";
    static final String START_PROFILE_GENERATION_FAILURE = "START_PROFILE_GENERATION_FAILURE";
    static final String START_PROFILE_GENERATION_SAME_MODEL = "START_PROFILE_GENERATION_SAME_MODEL";
    static final String TAG = "SpeakerVerificationMetricsReporter";
    static final String UPLOAD_UTTERANCES = "UPLOAD_UTTERANCES";
    static final String UPLOAD_UTTERANCES_FAILURE = "UPLOAD_UTTERANCES_FAILURE";
    static final String UTTERANCE_TRAINED = "UTTERANCE_TRAINED";
    static final String UTTERANCE_TRAINED_FAILURE = "UTTERANCE_TRAINED_FAILURE";
    static final String WAKEWORD_MODEL_AVAILABILITY = "WAKEWORD_MODEL_AVAILABILITY";
    static final String WAKEWORD_MODEL_UNAVAILABLE = "WAKEWORD_MODEL_UNAVAILABLE";
    private final Context mContext;
    private final Lazy<MetricsBuilderProvider> mMetricsBuilderProviderLazy;
    private final MetricsSource mSource;

    /* loaded from: classes11.dex */
    public enum MetricsSource {
        MODEL_DOWNLOAD,
        ENROLLMENT,
        RETRY,
        APP_UPDATE
    }

    public SpeakerVerificationMetricsReporter(@NonNull Context context, @NonNull MetricsSource metricsSource) {
        this(context, metricsSource, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).metricsBuilderProviderLazy());
    }

    @VisibleForTesting
    String createFailureMetricName(@NonNull String str, @NonNull String str2) {
        return this.mSource.name() + "." + str + "." + str2;
    }

    @VisibleForTesting
    String createMetricName(@NonNull String str) {
        return this.mSource.name() + "." + str;
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onDeleteUserDataFailure(@NonNull ReadWriteFailure readWriteFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(DELETE_USER_DATA)).withPerformanceMetric(TAG, createFailureMetricName(DELETE_USER_DATA_FAILURE, readWriteFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onDeleteUserDataSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(DELETE_USER_DATA)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onExampleAccepted() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(PRYON_PUSH_EXAMPLE)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onExampleRejected() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(PRYON_PUSH_EXAMPLE)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetProfileFailure(@NonNull ReadWriteFailure readWriteFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(GET_PROFILE)).withPerformanceMetric(TAG, createFailureMetricName(GET_PROFILE_FAILURE, readWriteFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetProfileSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(GET_PROFILE)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(GET_UTTERANCES)).withPerformanceMetric(TAG, createFailureMetricName(GET_UTTERANCES_FAILURE, readWriteFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetUtterancesSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(GET_UTTERANCES)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationFailure() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(MIC_INITIALIZATION)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationSuccess(long j) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(MIC_INITIALIZATION)).withLatencyMetric(TAG, createMetricName(MIC_INITIALIZATION_LATENCY), j).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadFailure(@NonNull ModelDownloadReason modelDownloadReason, @NonNull String str) {
        MetricsBuilder withPercentileMetricFailure = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(MODEL_DOWNLOAD_COMPLETE));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MODEL_DOWNLOAD_COMPLETE_FAILURE.");
        outline107.append(modelDownloadReason.getFullName());
        withPercentileMetricFailure.withSpeakerVerificationModelDownloadMetric(outline107.toString(), this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadInitiated() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, createMetricName(MODEL_DOWNLOAD_INITIATED)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadSuccess(@NonNull long j, @NonNull ModelDownloadReason modelDownloadReason, @NonNull String str) {
        MetricsBuilder withPercentileMetricSuccess = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(MODEL_DOWNLOAD_COMPLETE));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MODEL_DOWNLOAD_COMPLETE.");
        outline107.append(modelDownloadReason.getFullName());
        withPercentileMetricSuccess.withSpeakerVerificationModelDownloadMetric(outline107.toString(), this.mSource.name(), str).withLatencyMetric(TAG, createMetricName(MODEL_DOWNLOAD_LATENCY), j).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onPersistModelFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str) {
        MetricsBuilder withPerformanceMetric = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(PERSIST_MODEL)).withPerformanceMetric(TAG, createFailureMetricName(PERSIST_MODEL_FAILURE, trainingFailure.name()));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PERSIST_MODEL_FAILURE.");
        outline107.append(trainingFailure.name());
        withPerformanceMetric.withSpeakerVerificationProfileGenerationMetric(outline107.toString(), this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onPersistModelSuccess(@NonNull String str) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(PERSIST_MODEL)).withSpeakerVerificationProfileGenerationMetric(PERSIST_MODEL, this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationEngineSame() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, createMetricName(PROFILE_GENERATION_ENGINE_SAME)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationEngineUpdate() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, createMetricName(PROFILE_GENERATION_ENGINE_UPDATE)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str) {
        MetricsBuilder withPerformanceMetric = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(PROFILE_GENERATION)).withPerformanceMetric(TAG, createFailureMetricName(PROFILE_GENERATION_FAILURE, trainingFailure.name()));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PROFILE_GENERATION_FAILURE.");
        outline107.append(trainingFailure.name());
        withPerformanceMetric.withSpeakerVerificationProfileGenerationMetric(outline107.toString(), this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationHandsFreeDisabled() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, createMetricName(CANCEL_ON_HANDS_FREE_DISABLED)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationSuccess(@NonNull String str) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(PROFILE_GENERATION)).withSpeakerVerificationProfileGenerationMetric(PROFILE_GENERATION, this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onPryonOperationFailure(@NonNull PryonOperation pryonOperation, int i) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProviderLazy.mo358get().newBuilder();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PRYON_OPERATION.");
        outline107.append(pryonOperation.name());
        MetricsBuilder withPercentileMetricFailure = newBuilder.withPercentileMetricFailure(str, createMetricName(outline107.toString()));
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("PRYON_OPERATION_FAILURE.");
        outline1072.append(pryonOperation.name());
        withPercentileMetricFailure.withPerformanceMetric(str2, createFailureMetricName(outline1072.toString(), Integer.toString(i))).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onPryonOperationSuccess(@NonNull PryonOperation pryonOperation) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProviderLazy.mo358get().newBuilder();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PRYON_OPERATION.");
        outline107.append(pryonOperation.name());
        newBuilder.withPercentileMetricSuccess(str, createMetricName(outline107.toString())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveProfileFailure(@NonNull ReadWriteFailure readWriteFailure, @NonNull String str) {
        MetricsBuilder withPerformanceMetric = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(SAVE_PROFILE)).withPerformanceMetric(TAG, createFailureMetricName(SAVE_PROFILE_FAILURE, readWriteFailure.name()));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SAVE_PROFILE_FAILURE.");
        outline107.append(readWriteFailure.name());
        withPerformanceMetric.withSpeakerVerificationProfileGenerationMetric(outline107.toString(), this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveProfileSuccess(@NonNull String str) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(SAVE_PROFILE)).withSpeakerVerificationProfileGenerationMetric(SAVE_PROFILE, this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(SAVE_UTTERANCES)).withPerformanceMetric(TAG, createFailureMetricName(SAVE_UTTERANCES_FAILURE, readWriteFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveUtterancesSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(SAVE_UTTERANCES)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onStartProfileGenerationFailure(@NonNull StartProfileGenerationFailure startProfileGenerationFailure, @NonNull String str) {
        MetricsBuilder withPerformanceMetric = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(START_PROFILE_GENERATION)).withPerformanceMetric(TAG, createFailureMetricName(START_PROFILE_GENERATION_FAILURE, startProfileGenerationFailure.name()));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("START_PROFILE_GENERATION_FAILURE.");
        outline107.append(startProfileGenerationFailure.name());
        withPerformanceMetric.withSpeakerVerificationProfileGenerationMetric(outline107.toString(), this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onStartProfileGenerationSuccess(@NonNull String str) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(START_PROFILE_GENERATION)).withSpeakerVerificationProfileGenerationMetric(START_PROFILE_GENERATION, this.mSource.name(), str).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUploadUtterancesFailure(@NonNull TrainingFailure trainingFailure, int i) {
        MetricsBuilder withPercentileMetricFailure = this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(UPLOAD_UTTERANCES));
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UPLOAD_UTTERANCES_FAILURE.");
        outline107.append(trainingFailure.name());
        withPercentileMetricFailure.withPerformanceMetric(str, createFailureMetricName(outline107.toString(), Integer.toString(i))).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUploadUtterancesSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(UPLOAD_UTTERANCES)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUtteranceTrainedFailure(@NonNull TrainingFailure trainingFailure) {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricFailure(TAG, createMetricName(UTTERANCE_TRAINED_FAILURE)).withPerformanceMetric(TAG, createFailureMetricName(UTTERANCE_TRAINED_FAILURE, trainingFailure.name())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUtteranceTrainedSuccess() {
        this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPercentileMetricSuccess(TAG, createMetricName(UTTERANCE_TRAINED)).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onWakeWordModelAvailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProviderLazy.mo358get().newBuilder();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WAKEWORD_MODEL_AVAILABILITY.");
        outline107.append(downloadStarter.name());
        newBuilder.withPercentileMetricSuccess(str, createMetricName(outline107.toString())).emit(this.mContext);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onWakeWordModelUnavailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter, @NonNull ArtifactDownloadStateProvider.DownloadState downloadState) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProviderLazy.mo358get().newBuilder();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WAKEWORD_MODEL_AVAILABILITY.");
        outline107.append(downloadStarter.name());
        MetricsBuilder withPercentileMetricFailure = newBuilder.withPercentileMetricFailure(str, createMetricName(outline107.toString()));
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("WAKEWORD_MODEL_UNAVAILABLE.");
        outline1072.append(downloadStarter.name());
        withPercentileMetricFailure.withPerformanceMetric(str2, createFailureMetricName(outline1072.toString(), downloadState.name())).emit(this.mContext);
    }

    @VisibleForTesting
    SpeakerVerificationMetricsReporter(@NonNull Context context, @NonNull MetricsSource metricsSource, @NonNull Lazy<MetricsBuilderProvider> lazy) {
        this.mContext = context;
        this.mSource = metricsSource;
        this.mMetricsBuilderProviderLazy = lazy;
    }
}
