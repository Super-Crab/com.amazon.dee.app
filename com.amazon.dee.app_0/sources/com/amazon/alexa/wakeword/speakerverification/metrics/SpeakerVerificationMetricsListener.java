package com.amazon.alexa.wakeword.speakerverification.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.audiocapturer.MetricsListener;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.wakeword.speakerverification.errors.ModelDownloadReason;
import com.amazon.alexa.wakeword.speakerverification.errors.ReadWriteFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
/* loaded from: classes11.dex */
public interface SpeakerVerificationMetricsListener extends MetricsListener, PryonMetricsListener {
    void onDeleteUserDataFailure(@NonNull ReadWriteFailure readWriteFailure);

    void onDeleteUserDataSuccess();

    void onGetProfileFailure(@NonNull ReadWriteFailure readWriteFailure);

    void onGetProfileSuccess();

    void onGetUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure);

    void onGetUtterancesSuccess();

    void onModelDownloadFailure(@NonNull ModelDownloadReason modelDownloadReason, @NonNull String str);

    void onModelDownloadInitiated();

    void onModelDownloadSuccess(@NonNull long j, @NonNull ModelDownloadReason modelDownloadReason, @NonNull String str);

    void onPersistModelFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str);

    void onPersistModelSuccess(@NonNull String str);

    void onProfileGenerationEngineSame();

    void onProfileGenerationEngineUpdate();

    void onProfileGenerationFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str);

    void onProfileGenerationHandsFreeDisabled();

    void onProfileGenerationSuccess(@NonNull String str);

    void onSaveProfileFailure(@NonNull ReadWriteFailure readWriteFailure, @NonNull String str);

    void onSaveProfileSuccess(@NonNull String str);

    void onSaveUtterancesFailure(@NonNull ReadWriteFailure readWriteFailure);

    void onSaveUtterancesSuccess();

    void onStartProfileGenerationFailure(@NonNull StartProfileGenerationFailure startProfileGenerationFailure, @NonNull String str);

    void onStartProfileGenerationSuccess(@NonNull String str);

    void onUploadUtterancesFailure(@NonNull TrainingFailure trainingFailure, int i);

    void onUploadUtterancesSuccess();

    void onUtteranceTrainedFailure(@NonNull TrainingFailure trainingFailure);

    void onUtteranceTrainedSuccess();

    void onWakeWordModelAvailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter);

    void onWakeWordModelUnavailable(@NonNull ArtifactDownloadStateProvider.DownloadStarter downloadStarter, @NonNull ArtifactDownloadStateProvider.DownloadState downloadState);
}
