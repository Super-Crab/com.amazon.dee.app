package com.amazon.alexa.wakeword.speakerverification.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.wakeword.speakerverification.errors.ModelDownloadReason;
import com.amazon.alexa.wakeword.speakerverification.errors.ReadWriteFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.pryon.PryonOperation;
/* loaded from: classes11.dex */
public class NullSpeakerVerificationMetricsReporter implements SpeakerVerificationMetricsListener {
    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onDeleteUserDataFailure(@NonNull ReadWriteFailure readWriteFailure) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onDeleteUserDataSuccess() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onExampleAccepted() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onExampleRejected() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetProfileFailure(@NonNull ReadWriteFailure readWriteFailure) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetProfileSuccess() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onGetUtterancesSuccess() {
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationFailure() {
    }

    @Override // com.amazon.alexa.audiocapturer.MetricsListener
    public void onMicInitializationSuccess(long j) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadFailure(@NonNull ModelDownloadReason modelDownloadReason, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadInitiated() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onModelDownloadSuccess(@NonNull long j, @NonNull ModelDownloadReason modelDownloadReason, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onPersistModelFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onPersistModelSuccess(@NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationEngineSame() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationEngineUpdate() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationHandsFreeDisabled() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onProfileGenerationSuccess(@NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onPryonOperationFailure(@NonNull PryonOperation pryonOperation, int i) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.PryonMetricsListener
    public void onPryonOperationSuccess(@NonNull PryonOperation pryonOperation) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveProfileFailure(@NonNull ReadWriteFailure readWriteFailure, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveProfileSuccess(@NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onSaveUtterancesSuccess() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onStartProfileGenerationFailure(@NonNull StartProfileGenerationFailure startProfileGenerationFailure, @NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onStartProfileGenerationSuccess(@NonNull String str) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUploadUtterancesFailure(@NonNull TrainingFailure trainingFailure, int i) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUploadUtterancesSuccess() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUtteranceTrainedFailure(@NonNull TrainingFailure trainingFailure) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onUtteranceTrainedSuccess() {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onWakeWordModelAvailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter) {
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener
    public void onWakeWordModelUnavailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter, @NonNull ArtifactDownloadStateProvider.DownloadState downloadState) {
    }
}
