package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.networking.adapters.MessageAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.HashMap;
/* compiled from: NetworkingModule_ProvidesMessageAdapterFactory.java */
/* loaded from: classes.dex */
public final class OoI implements Factory<MessageAdapter> {
    public static final OoI zZm = new OoI();

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        HashMap hashMap = new HashMap();
        hashMap.put(AvsApiConstants.System.zZm, new ZiF());
        hashMap.put(AvsApiConstants.SpeechSynthesizer.zZm, new frW());
        hashMap.put(AvsApiConstants.SpeechRecognizer.zZm, new zEd());
        hashMap.put(AvsApiConstants.InteractionModel.zZm, new FUN());
        hashMap.put(AvsApiConstants.AudioPlayer.zZm, new nhU());
        hashMap.put(AvsApiConstants.Speaker.zZm, new KED());
        hashMap.put(AvsApiConstants.CardRenderer.zZm, new RmH());
        hashMap.put(AvsApiConstants.Navigation.zZm, new tTF());
        hashMap.put(AvsApiConstants.ExternalMediaPlayer.zZm, new FcM());
        hashMap.put(AvsApiConstants.ApplicationManager.zZm, new YFD());
        hashMap.put(AvsApiConstants.Alexa.FavoritesController.zZm, new xzy());
        hashMap.put(AvsApiConstants.Alexa.PlaybackController.zZm, new RZO());
        hashMap.put(AvsApiConstants.Alexa.PlaylistController.zZm, new eAg());
        hashMap.put(AvsApiConstants.Alexa.SeekController.zZm, new YDN());
        hashMap.put(AvsApiConstants.Alexa.Launcher.zZm, new C0190bPW());
        return (MessageAdapter) Preconditions.checkNotNull(new MessageAdapter(hashMap), "Cannot return null from a non-@Nullable @Provides method");
    }
}
