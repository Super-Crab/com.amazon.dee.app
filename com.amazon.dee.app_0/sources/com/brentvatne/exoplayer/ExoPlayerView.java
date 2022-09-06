package com.brentvatne.exoplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.video.VideoListener;
import java.util.List;
@TargetApi(16)
/* loaded from: classes.dex */
public final class ExoPlayerView extends FrameLayout {
    private final ComponentListener componentListener;
    private Context context;
    private boolean hideShutterView;
    private final AspectRatioFrameLayout layout;
    private ViewGroup.LayoutParams layoutParams;
    private final Runnable measureAndLayout;
    private SimpleExoPlayer player;
    private final View shutterView;
    private final SubtitleView subtitleLayout;
    private View surfaceView;
    private boolean useTextureView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ComponentListener implements VideoListener, TextOutput, Player.EventListener {
        private ComponentListener() {
        }

        @Override // com.google.android.exoplayer2.text.TextOutput
        public void onCues(List<Cue> list) {
            ExoPlayerView.this.subtitleLayout.onCues(list);
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onLoadingChanged(boolean z) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i) {
        }

        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onRenderedFirstFrame() {
            ExoPlayerView.this.shutterView.setVisibility(4);
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onRepeatModeChanged(int i) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onSeekProcessed() {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onShuffleModeEnabledChanged(boolean z) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            ExoPlayerView.this.updateForCurrentTrackSelections();
        }

        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            boolean z = ExoPlayerView.this.layout.getAspectRatio() == 0.0f;
            ExoPlayerView.this.layout.setAspectRatio(i2 == 0 ? 1.0f : (i * f) / i2);
            if (z) {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.post(exoPlayerView.measureAndLayout);
            }
        }
    }

    public ExoPlayerView(Context context) {
        this(context, null);
    }

    private void clearVideoView() {
        View view = this.surfaceView;
        if (view instanceof TextureView) {
            this.player.clearVideoTextureView((TextureView) view);
        } else if (!(view instanceof SurfaceView)) {
        } else {
            this.player.clearVideoSurfaceView((SurfaceView) view);
        }
    }

    private void setVideoView() {
        View view = this.surfaceView;
        if (view instanceof TextureView) {
            this.player.setVideoTextureView((TextureView) view);
        } else if (!(view instanceof SurfaceView)) {
        } else {
            this.player.setVideoSurfaceView((SurfaceView) view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForCurrentTrackSelections() {
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer == null) {
            return;
        }
        TrackSelectionArray currentTrackSelections = simpleExoPlayer.getCurrentTrackSelections();
        for (int i = 0; i < currentTrackSelections.length; i++) {
            if (this.player.getRendererType(i) == 2 && currentTrackSelections.get(i) != null) {
                return;
            }
        }
        this.shutterView.setVisibility(0);
    }

    private void updateShutterViewVisibility() {
        this.shutterView.setVisibility(this.hideShutterView ? 4 : 0);
    }

    private void updateSurfaceView() {
        View textureView = this.useTextureView ? new TextureView(this.context) : new SurfaceView(this.context);
        textureView.setLayoutParams(this.layoutParams);
        this.surfaceView = textureView;
        if (this.layout.getChildAt(0) != null) {
            this.layout.removeViewAt(0);
        }
        this.layout.addView(this.surfaceView, 0, this.layoutParams);
        if (this.player != null) {
            setVideoView();
        }
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public void invalidateAspectRatio() {
        this.layout.invalidateAspectRatio();
    }

    public void setHideShutterView(boolean z) {
        this.hideShutterView = z;
        updateShutterViewVisibility();
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        SimpleExoPlayer simpleExoPlayer2 = this.player;
        if (simpleExoPlayer2 == simpleExoPlayer) {
            return;
        }
        if (simpleExoPlayer2 != null) {
            simpleExoPlayer2.removeTextOutput(this.componentListener);
            this.player.removeVideoListener(this.componentListener);
            this.player.removeListener(this.componentListener);
            clearVideoView();
        }
        this.player = simpleExoPlayer;
        this.shutterView.setVisibility(0);
        if (simpleExoPlayer == null) {
            return;
        }
        setVideoView();
        simpleExoPlayer.addVideoListener(this.componentListener);
        simpleExoPlayer.addListener(this.componentListener);
        simpleExoPlayer.addTextOutput(this.componentListener);
    }

    public void setResizeMode(int i) {
        if (this.layout.getResizeMode() != i) {
            this.layout.setResizeMode(i);
            post(this.measureAndLayout);
        }
    }

    public void setUseTextureView(boolean z) {
        if (z != this.useTextureView) {
            this.useTextureView = z;
            updateSurfaceView();
        }
    }

    public ExoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.useTextureView = true;
        this.hideShutterView = false;
        this.measureAndLayout = new Runnable() { // from class: com.brentvatne.exoplayer.ExoPlayerView.1
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.measure(View.MeasureSpec.makeMeasureSpec(exoPlayerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ExoPlayerView.this.getHeight(), 1073741824));
                ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                exoPlayerView2.layout(exoPlayerView2.getLeft(), ExoPlayerView.this.getTop(), ExoPlayerView.this.getRight(), ExoPlayerView.this.getBottom());
            }
        };
        this.context = context;
        this.layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.componentListener = new ComponentListener();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.layout = new AspectRatioFrameLayout(context);
        this.layout.setLayoutParams(layoutParams);
        this.shutterView = new View(getContext());
        this.shutterView.setLayoutParams(this.layoutParams);
        this.shutterView.setBackgroundColor(ContextCompat.getColor(context, 17170444));
        this.subtitleLayout = new SubtitleView(context);
        this.subtitleLayout.setLayoutParams(this.layoutParams);
        this.subtitleLayout.setUserDefaultStyle();
        this.subtitleLayout.setUserDefaultTextSize();
        updateSurfaceView();
        this.layout.addView(this.shutterView, 1, this.layoutParams);
        this.layout.addView(this.subtitleLayout, 2, this.layoutParams);
        addViewInLayout(this.layout, 0, layoutParams);
    }
}
