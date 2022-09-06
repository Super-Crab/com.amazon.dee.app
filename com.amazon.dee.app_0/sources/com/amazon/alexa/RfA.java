package com.amazon.alexa;

import com.amazon.alexa.api.AlexaArtifactDownloadListener;
import com.amazon.alexa.api.ArtifactDownloadListenerFailure;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import java.util.Iterator;
import java.util.Locale;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: WakeWordArtifactDownload.java */
@Singleton
/* loaded from: classes.dex */
public class RfA implements ArtifactDownloadResultListener {
    public static final String zZm = "RfA";
    public final Shr<AlexaArtifactDownloadListener> BIo = new Shr<>();

    public final void BIo(ArtifactModel artifactModel) {
        synchronized (this.BIo) {
            Iterator<AlexaArtifactDownloadListener> it2 = this.BIo.iterator();
            while (it2.hasNext()) {
                it2.next().onArtifactDownloadSuccess(Locale.forLanguageTag(artifactModel.getLocale()));
            }
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        this.BIo.BIo(((uyC) xzv).BIo);
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactAlreadyUpToDate(long j, ArtifactModel artifactModel) {
        zZm(artifactModel);
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadFailure(long j, String str, Exception exc, String str2) {
        zZm(ArtifactDownloadListenerFailure.DOWNLOAD);
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadInterrupted(long j) {
        zZm(ArtifactDownloadListenerFailure.INTERRUPTION);
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadSuccess(long j, ArtifactModel artifactModel) {
        BIo(artifactModel);
    }

    public void zZm(ExtendedClient extendedClient, AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        StringBuilder zZm2 = C0179Pya.zZm("Registering Alexa Artifact Download Listener for client: ");
        zZm2.append(extendedClient.getId());
        zZm2.toString();
        this.BIo.zZm(extendedClient, alexaArtifactDownloadListener);
    }

    public void zZm(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        ExtendedClient remove = this.BIo.remove(alexaArtifactDownloadListener);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering Alexa Artifact Download Listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }

    public final void zZm(ArtifactModel artifactModel) {
        synchronized (this.BIo) {
            Iterator<AlexaArtifactDownloadListener> it2 = this.BIo.iterator();
            while (it2.hasNext()) {
                it2.next().onArtifactAlreadyUpToDate(Locale.forLanguageTag(artifactModel.getLocale()));
            }
        }
    }

    public final void zZm(ArtifactDownloadListenerFailure artifactDownloadListenerFailure) {
        synchronized (this.BIo) {
            Iterator<AlexaArtifactDownloadListener> it2 = this.BIo.iterator();
            while (it2.hasNext()) {
                it2.next().onArtifactDownloadFailure(artifactDownloadListenerFailure);
            }
        }
    }
}
