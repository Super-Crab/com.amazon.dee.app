package com.amazon.alexa.handsfree.uservoicerecognition.audio;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class TrainingSoundPlayer implements StepsContract.SoundPlayer {
    private static final String TAG = "TrainingSoundPlayer";
    private final Context mContext;
    private MediaPlayer mMediaPlayer;

    @VisibleForTesting
    /* loaded from: classes8.dex */
    class MediaPlayerCallback implements MediaPlayer.OnCompletionListener {
        private final StepsContract.SoundPlayer.SoundCallback mSoundCallback;

        MediaPlayerCallback(@Nullable StepsContract.SoundPlayer.SoundCallback soundCallback) {
            this.mSoundCallback = soundCallback;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(@NonNull MediaPlayer mediaPlayer) {
            Log.d(TrainingSoundPlayer.TAG, "playSound onSilence");
            StepsContract.SoundPlayer.SoundCallback soundCallback = this.mSoundCallback;
            if (soundCallback != null) {
                soundCallback.onSilence();
            }
            mediaPlayer.reset();
            mediaPlayer.release();
            TrainingSoundPlayer.this.mMediaPlayer = null;
        }
    }

    public TrainingSoundPlayer(@NonNull Context context) {
        this.mContext = context;
    }

    @VisibleForTesting
    MediaPlayer createMediaPlayer(@RawRes int i) {
        return MediaPlayer.create(this.mContext, i);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.SoundPlayer
    public void playSound(@NonNull TrainingSound trainingSound, @Nullable StepsContract.SoundPlayer.SoundCallback soundCallback) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("playSound called with TrainingSound: ");
        outline107.append(trainingSound.name());
        Log.d(str, outline107.toString());
        this.mMediaPlayer = createMediaPlayer(trainingSound.getResourceId());
        this.mMediaPlayer.start();
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayerCallback(soundCallback));
    }
}
