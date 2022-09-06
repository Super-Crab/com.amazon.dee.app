package com.amazon.alexa;

import com.amazon.alexa.NEv;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
/* compiled from: WakeWordAuthority.java */
/* loaded from: classes.dex */
public class zWW implements ArtifactDownloadResultListener {
    public final /* synthetic */ bjR BIo;
    public final /* synthetic */ eOP zZm;

    public zWW(bjR bjr, eOP eop) {
        this.BIo = bjr;
        this.zZm = eop;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactAlreadyUpToDate(long j, ArtifactModel artifactModel) {
        String str = bjR.zZm;
        String.format("onArtifactAlreadyUpToDate id=%s, duration=%d", artifactModel.getArtifactIdentifier(), Long.valueOf(j));
        this.BIo.HvC.zyO(NEv.zQM.zZm(this.zZm));
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadFailure(long j, String str, Exception exc, String str2) {
        String str3 = bjR.zZm;
        String.format("onArtifactDownloadFailure duration=%d, reason=%s, md5=%s", Long.valueOf(j), str2, str);
        this.BIo.HvC.zyO(NEv.zZm.zZm(this.zZm, ApiCallFailure.InternalFailure.create(str2)));
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadInterrupted(long j) {
        String str = bjR.zZm;
        String.format("onArtifactDownloadInterrupted duration=%d", Long.valueOf(j));
        this.BIo.HvC.zyO(NEv.zZm.zZm(this.zZm, ApiCallFailure.InternalFailure.create("interrupted")));
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadSuccess(long j, ArtifactModel artifactModel) {
        String str = bjR.zZm;
        String.format("onArtifactDownloadSuccess id=%s, duration=%d", artifactModel.getArtifactIdentifier(), Long.valueOf(j));
        this.BIo.HvC.zyO(NEv.zQM.zZm(this.zZm));
    }
}
