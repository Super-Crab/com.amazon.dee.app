package com.google.android.exoplayer2.transformer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.transformer.FrameworkMuxer;
import com.google.android.exoplayer2.transformer.Muxer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@RequiresApi(18)
/* loaded from: classes2.dex */
public final class Transformer {
    public static final int PROGRESS_STATE_AVAILABLE = 1;
    public static final int PROGRESS_STATE_NO_TRANSFORMATION = 4;
    public static final int PROGRESS_STATE_UNAVAILABLE = 2;
    public static final int PROGRESS_STATE_WAITING_FOR_AVAILABILITY = 0;
    private final Clock clock;
    private final Context context;
    private Listener listener;
    private final Looper looper;
    private final MediaSourceFactory mediaSourceFactory;
    private final Muxer.Factory muxerFactory;
    @Nullable
    private MuxerWrapper muxerWrapper;
    @Nullable
    private SimpleExoPlayer player;
    private int progressState;
    private final Transformation transformation;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Clock clock;
        private Context context;
        private boolean flattenForSlowMotion;
        private Listener listener;
        private Looper looper;
        private MediaSourceFactory mediaSourceFactory;
        private Muxer.Factory muxerFactory;
        private String outputMimeType;
        private boolean removeAudio;
        private boolean removeVideo;

        public Transformer build() {
            Assertions.checkStateNotNull(this.context);
            if (this.mediaSourceFactory == null) {
                DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
                if (this.flattenForSlowMotion) {
                    defaultExtractorsFactory.setMp4ExtractorFlags(4);
                }
                this.mediaSourceFactory = new DefaultMediaSourceFactory(this.context, defaultExtractorsFactory);
            }
            boolean supportsOutputMimeType = this.muxerFactory.supportsOutputMimeType(this.outputMimeType);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported output MIME type: ");
            outline107.append(this.outputMimeType);
            Assertions.checkState(supportsOutputMimeType, outline107.toString());
            return new Transformer(this.context, this.mediaSourceFactory, this.muxerFactory, new Transformation(this.removeAudio, this.removeVideo, this.flattenForSlowMotion, this.outputMimeType), this.listener, this.looper, this.clock);
        }

        @VisibleForTesting
        Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context.getApplicationContext();
            return this;
        }

        public Builder setFlattenForSlowMotion(boolean z) {
            this.flattenForSlowMotion = z;
            return this;
        }

        public Builder setListener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setLooper(Looper looper) {
            this.looper = looper;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSourceFactory mediaSourceFactory) {
            this.mediaSourceFactory = mediaSourceFactory;
            return this;
        }

        @VisibleForTesting
        Builder setMuxerFactory(Muxer.Factory factory) {
            this.muxerFactory = factory;
            return this;
        }

        public Builder setOutputMimeType(String str) {
            this.outputMimeType = str;
            return this;
        }

        public Builder setRemoveAudio(boolean z) {
            this.removeAudio = z;
            return this;
        }

        public Builder setRemoveVideo(boolean z) {
            this.removeVideo = z;
            return this;
        }

        public Builder() {
            this.muxerFactory = new FrameworkMuxer.Factory();
            this.outputMimeType = MimeTypes.VIDEO_MP4;
            this.listener = new Listener() { // from class: com.google.android.exoplayer2.transformer.Transformer.Builder.1
            };
            this.looper = Util.getCurrentOrMainLooper();
            this.clock = Clock.DEFAULT;
        }

        private Builder(Transformer transformer) {
            this.context = transformer.context;
            this.mediaSourceFactory = transformer.mediaSourceFactory;
            this.muxerFactory = transformer.muxerFactory;
            this.removeAudio = transformer.transformation.removeAudio;
            this.removeVideo = transformer.transformation.removeVideo;
            this.flattenForSlowMotion = transformer.transformation.flattenForSlowMotion;
            this.outputMimeType = transformer.transformation.outputMimeType;
            this.listener = transformer.listener;
            this.looper = transformer.looper;
            this.clock = transformer.clock;
        }
    }

    /* loaded from: classes2.dex */
    public interface Listener {
        default void onTransformationCompleted(MediaItem mediaItem) {
        }

        default void onTransformationError(MediaItem mediaItem, Exception exc) {
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ProgressState {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class TransformerAnalyticsListener implements AnalyticsListener {
        private final MediaItem mediaItem;
        private final MuxerWrapper muxerWrapper;

        public TransformerAnalyticsListener(MediaItem mediaItem, MuxerWrapper muxerWrapper) {
            this.mediaItem = mediaItem;
            this.muxerWrapper = muxerWrapper;
        }

        private void handleTransformationEnded(@Nullable Exception exc) {
            try {
                Transformer.this.releaseResources(false);
            } catch (IllegalStateException e) {
                if (exc == null) {
                    exc = e;
                }
            }
            if (exc == null) {
                Transformer.this.listener.onTransformationCompleted(this.mediaItem);
            } else {
                Transformer.this.listener.onTransformationError(this.mediaItem, exc);
            }
        }

        @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
        public void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
            if (i == 4) {
                handleTransformationEnded(null);
            }
        }

        @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
        public void onPlayerError(AnalyticsListener.EventTime eventTime, ExoPlaybackException exoPlaybackException) {
            handleTransformationEnded(exoPlaybackException);
        }

        @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
        public void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
            if (Transformer.this.progressState != 0) {
                return;
            }
            Timeline.Window window = new Timeline.Window();
            eventTime.timeline.getWindow(0, window);
            if (window.isPlaceholder) {
                return;
            }
            long j = window.durationUs;
            Transformer.this.progressState = (j <= 0 || j == C.TIME_UNSET) ? 2 : 1;
            ((SimpleExoPlayer) Assertions.checkNotNull(Transformer.this.player)).play();
        }

        @Override // com.google.android.exoplayer2.analytics.AnalyticsListener
        public void onTracksChanged(AnalyticsListener.EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            if (this.muxerWrapper.getTrackCount() == 0) {
                handleTransformationEnded(new IllegalStateException("The output does not contain any tracks. Check that at least one of the input sample formats is supported."));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TransformerRenderersFactory implements RenderersFactory {
        private final TransformerMediaClock mediaClock = new TransformerMediaClock();
        private final MuxerWrapper muxerWrapper;
        private final Transformation transformation;

        public TransformerRenderersFactory(MuxerWrapper muxerWrapper, Transformation transformation) {
            this.muxerWrapper = muxerWrapper;
            this.transformation = transformation;
        }

        @Override // com.google.android.exoplayer2.RenderersFactory
        public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
            Transformation transformation = this.transformation;
            Renderer[] rendererArr = new Renderer[(transformation.removeAudio || transformation.removeVideo) ? 1 : 2];
            char c = 0;
            Transformation transformation2 = this.transformation;
            if (!transformation2.removeAudio) {
                rendererArr[0] = new TransformerAudioRenderer(this.muxerWrapper, this.mediaClock, transformation2);
                c = 1;
            }
            Transformation transformation3 = this.transformation;
            if (!transformation3.removeVideo) {
                rendererArr[c] = new TransformerVideoRenderer(this.muxerWrapper, this.mediaClock, transformation3);
            }
            return rendererArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseResources(boolean z) {
        verifyApplicationThread();
        SimpleExoPlayer simpleExoPlayer = this.player;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            this.player = null;
        }
        MuxerWrapper muxerWrapper = this.muxerWrapper;
        if (muxerWrapper != null) {
            muxerWrapper.release(z);
            this.muxerWrapper = null;
        }
        this.progressState = 4;
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() == this.looper) {
            return;
        }
        throw new IllegalStateException("Transformer is accessed on the wrong thread.");
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public void cancel() {
        releaseResources(true);
    }

    public Looper getApplicationLooper() {
        return this.looper;
    }

    public int getProgress(ProgressHolder progressHolder) {
        verifyApplicationThread();
        if (this.progressState == 1) {
            Player player = (Player) Assertions.checkNotNull(this.player);
            progressHolder.progress = Math.min((int) ((player.getCurrentPosition() * 100) / player.getDuration()), 99);
        }
        return this.progressState;
    }

    public void setListener(Listener listener) {
        verifyApplicationThread();
        this.listener = listener;
    }

    public void startTransformation(MediaItem mediaItem, String str) throws IOException {
        startTransformation(mediaItem, this.muxerFactory.mo7449create(str, this.transformation.outputMimeType));
    }

    private Transformer(Context context, MediaSourceFactory mediaSourceFactory, Muxer.Factory factory, Transformation transformation, Listener listener, Looper looper, Clock clock) {
        Assertions.checkState(!transformation.removeAudio || !transformation.removeVideo, "Audio and video cannot both be removed.");
        this.context = context;
        this.mediaSourceFactory = mediaSourceFactory;
        this.muxerFactory = factory;
        this.transformation = transformation;
        this.listener = listener;
        this.looper = looper;
        this.clock = clock;
        this.progressState = 4;
    }

    @RequiresApi(26)
    public void startTransformation(MediaItem mediaItem, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        startTransformation(mediaItem, this.muxerFactory.mo7448create(parcelFileDescriptor, this.transformation.outputMimeType));
    }

    private void startTransformation(MediaItem mediaItem, Muxer muxer) {
        verifyApplicationThread();
        if (this.player == null) {
            MuxerWrapper muxerWrapper = new MuxerWrapper(muxer);
            this.muxerWrapper = muxerWrapper;
            DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(this.context);
            defaultTrackSelector.setParameters(new DefaultTrackSelector.ParametersBuilder(this.context).setForceHighestSupportedBitrate(true).mo7434build());
            this.player = new SimpleExoPlayer.Builder(this.context, new TransformerRenderersFactory(muxerWrapper, this.transformation)).setMediaSourceFactory(this.mediaSourceFactory).setTrackSelector(defaultTrackSelector).setLoadControl(new DefaultLoadControl.Builder().setBufferDurationsMs(50000, 50000, 250, 500).build()).setLooper(this.looper).setClock(this.clock).build();
            this.player.setMediaItem(mediaItem);
            this.player.addAnalyticsListener(new TransformerAnalyticsListener(mediaItem, muxerWrapper));
            this.player.prepare();
            this.progressState = 0;
            return;
        }
        throw new IllegalStateException("There is already a transformation in progress.");
    }
}
