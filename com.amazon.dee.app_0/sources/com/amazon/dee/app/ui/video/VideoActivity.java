package com.amazon.dee.app.ui.video;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.VideoBinding;
/* loaded from: classes12.dex */
public class VideoActivity extends Activity {
    private static final String INSTANCE_STATE_VIDEO_PROGRESS = "VIDEO_PROGRESS";
    public static final String INTENT_EXTRA_VIDEO_PROGRESS = "com.amazon.mobile.webapp.videoProgress";
    public static final String INTENT_EXTRA_VIDEO_URL = "com.amazon.mobile.webapp.videoUrl";
    VideoBinding binding;
    int videoProgress = 0;
    private boolean videoIsComplete = false;

    private void saveProgress() {
        this.videoProgress = getVideoProgress();
        Intent intent = new Intent();
        intent.putExtra(INTENT_EXTRA_VIDEO_PROGRESS, this.videoProgress);
        setResult(0, intent);
    }

    int getSeekTimeFromProgressPercentage(int i) {
        return (this.binding.VideoView.getDuration() * i) / 100;
    }

    int getVideoProgress() {
        int duration = this.binding.VideoView.getDuration();
        if (duration != 0) {
            return (this.binding.VideoView.getCurrentPosition() * 100) / duration;
        }
        return 0;
    }

    public /* synthetic */ void lambda$onResume$0$VideoActivity(MediaPlayer mediaPlayer) {
        int i = this.videoProgress;
        if (i > 0) {
            this.binding.VideoView.seekTo(getSeekTimeFromProgressPercentage(i));
        }
        this.binding.VideoView.start();
    }

    public /* synthetic */ void lambda$setUpVideoView$1$VideoActivity(MediaPlayer mediaPlayer) {
        this.videoIsComplete = true;
        setResult(-1);
        finish();
    }

    public /* synthetic */ boolean lambda$setUpVideoView$2$VideoActivity(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            saveProgress();
            finish();
            return false;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        saveProgress();
        super.onBackPressed();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.binding = (VideoBinding) DataBindingUtil.setContentView(this, R.layout.video);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(INTENT_EXTRA_VIDEO_URL);
            int i = 0;
            int intExtra = intent.getIntExtra(INTENT_EXTRA_VIDEO_PROGRESS, 0);
            if (bundle != null) {
                i = bundle.getInt(INSTANCE_STATE_VIDEO_PROGRESS);
            }
            this.videoProgress = Math.max(intExtra, i);
            setUpVideoView(stringExtra);
            this.binding.VideoView.requestFocus();
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        if (this.videoIsComplete) {
            this.videoProgress = 0;
            this.videoIsComplete = false;
        } else {
            saveProgress();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        this.binding.VideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.amazon.dee.app.ui.video.-$$Lambda$VideoActivity$xFZSYFz7VS5i3wJxXkUoj4jR3WE
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                VideoActivity.this.lambda$onResume$0$VideoActivity(mediaPlayer);
            }
        });
        super.onResume();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(INSTANCE_STATE_VIDEO_PROGRESS, getVideoProgress());
    }

    void setUpVideoView(String str) {
        this.binding.VideoView.setVideoURI(Uri.parse(str));
        this.binding.VideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.amazon.dee.app.ui.video.-$$Lambda$VideoActivity$Vk6XhN0gCw_5R55Qn9TZcwTRAmY
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                VideoActivity.this.lambda$setUpVideoView$1$VideoActivity(mediaPlayer);
            }
        });
        this.binding.VideoView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.dee.app.ui.video.-$$Lambda$VideoActivity$b9PVHJ41lYimpGQ0l6rqJpCYk3M
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return VideoActivity.this.lambda$setUpVideoView$2$VideoActivity(view, motionEvent);
            }
        });
    }
}
