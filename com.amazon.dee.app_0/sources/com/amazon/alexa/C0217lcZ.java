package com.amazon.alexa;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import com.google.common.base.Optional;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: DavsOfflinePromptsDownloadManager.java */
/* renamed from: com.amazon.alexa.lcZ  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0217lcZ implements ArtifactDownloadResultListener, JEn {
    public static final String zZm = "lcZ";
    public final ArtifactManager BIo;
    public final Provider<SharedPreferences> JTe;
    public final LocaleProvider LPk;
    public final File Mlj;
    public final TimeProvider Qle;
    public final ScheduledExecutorService jiA;
    public final kHl yPL;
    public final DavsClient zQM;
    public final NetworkManager zyO;
    public final rOz zzR;

    public C0217lcZ(ArtifactManager artifactManager, DavsClient davsClient, NetworkManager networkManager, TimeProvider timeProvider, Provider<SharedPreferences> provider, LocaleProvider localeProvider, kHl khl, File file, rOz roz) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor("davsofflineprompts");
        this.BIo = artifactManager;
        this.zQM = davsClient;
        this.zyO = networkManager;
        this.Qle = timeProvider;
        this.JTe = provider;
        this.jiA = newSingleThreadScheduledExecutor;
        this.LPk = localeProvider;
        this.yPL = khl;
        this.Mlj = file;
        this.zzR = roz;
    }

    public synchronized void BIo() {
        this.zyO.teardown();
        this.jiA.shutdownNow();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactAlreadyUpToDate(long j, ArtifactModel artifactModel) {
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadFailure(long j, String str, Exception exc, String str2) {
        String str3 = zZm;
        Log.e(str3, "artifact download failure " + str2 + exc + str);
        ((Gju) this.zzR).zZm.zyO(new yFh(j, str, str2));
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadInterrupted(long j) {
        ((Gju) this.zzR).zZm.zyO(new RgD(j));
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
    public void onArtifactDownloadSuccess(long j, ArtifactModel artifactModel) {
        new File(this.Mlj.getPath(), this.LPk.getLocale().toLanguageTag());
        if (this.yPL.zZm(this.Mlj, artifactModel.getArtifactIdentifier(), this.LPk.getLocale().toLanguageTag())) {
            this.yPL.zZm(this.Mlj.getPath(), this.LPk.getLocale().toLanguageTag(), artifactModel.getArtifactIdentifier());
        }
        ((Gju) this.zzR).zZm.zyO(new yFV(j, CheckSumUtils.getMD5(artifactModel.getArtifactData())));
    }

    public synchronized void zZm() {
        this.jiA.execute(new Wnn(this));
    }

    public synchronized String zZm(ZFm zFm) {
        Optional<File> BIo = this.yPL.BIo(this.Mlj, zFm.getFileName(), this.LPk.getLocale().toLanguageTag());
        if (BIo.isPresent()) {
            return BIo.get().getPath();
        }
        zZm(this.LPk.getLocale().toLanguageTag());
        return "";
    }

    public final void zZm(String str) {
        this.jiA.execute(new Dwc(new RJu(str), this.Qle, this.zQM, this.JTe, this.BIo, this));
    }
}
