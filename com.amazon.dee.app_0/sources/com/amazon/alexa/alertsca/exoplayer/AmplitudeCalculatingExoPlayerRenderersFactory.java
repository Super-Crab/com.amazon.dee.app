package com.amazon.alexa.alertsca.exoplayer;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public class AmplitudeCalculatingExoPlayerRenderersFactory extends DefaultRenderersFactory {
    private final AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink;

    public AmplitudeCalculatingExoPlayerRenderersFactory(Context context, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink) {
        super(context);
        this.amplitudeCalculatingAudioSink = amplitudeCalculatingAudioSink;
    }

    @Override // com.google.android.exoplayer2.DefaultRenderersFactory
    protected void buildAudioRenderers(Context context, int i, MediaCodecSelector mediaCodecSelector, boolean z, AudioSink audioSink, Handler handler, AudioRendererEventListener audioRendererEventListener, ArrayList<Renderer> arrayList) {
        arrayList.add(new MediaCodecAudioRenderer(context, MediaCodecSelector.DEFAULT, z, handler, audioRendererEventListener, this.amplitudeCalculatingAudioSink));
    }
}
