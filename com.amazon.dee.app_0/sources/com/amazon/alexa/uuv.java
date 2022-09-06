package com.amazon.alexa;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.telephony.TelephonyManager;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AudioPlayerModule.java */
@Module
/* loaded from: classes.dex */
public class uuv {
    @Provides
    @Singleton
    public dcs BIo(Lazy<SoundPool> lazy, Context context) {
        return new dcs(lazy, context);
    }

    @Provides
    @Singleton
    @Named("ScoSoundPool")
    public SoundPool zQM() {
        return new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setContentType(1).setUsage(2).build()).setMaxStreams(4).build();
    }

    @Provides
    @Singleton
    @Named("ScoSoundWrapper")
    public dcs zZm(@Named("ScoSoundPool") Lazy<SoundPool> lazy, Context context) {
        return new dcs(lazy, context);
    }

    @Provides
    @Singleton
    public SoundPool zyO() {
        return new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setContentType(2).setUsage(1).build()).setMaxStreams(4).build();
    }

    @Provides
    public MediaPlayer BIo() {
        return new MediaPlayer();
    }

    @Provides
    @Singleton
    public uxN zZm(Context context, TimeProvider timeProvider, gSO gso, Lazy<shl> lazy, Lazy<dAN> lazy2, uXm uxm) {
        return new VIX(context, timeProvider, gso, "speech-player", lazy, lazy2, null, uxm);
    }

    @Provides
    @Singleton
    public BluetoothScoController zZm(Context context, AudioManager audioManager, TelephonyManager telephonyManager, MLT mlt) {
        return new BluetoothScoController(audioManager, telephonyManager, context, mlt);
    }

    @Provides
    @Singleton
    public liS zZm(Context context, C0245zoo c0245zoo, AlexaClientEventBus alexaClientEventBus) {
        return new ciO(context, c0245zoo, alexaClientEventBus);
    }

    @Provides
    @Singleton
    public AudioPlaybackConfigurationHelper zZm() {
        return AudioPlaybackConfigurationHelper.getInstance();
    }
}
