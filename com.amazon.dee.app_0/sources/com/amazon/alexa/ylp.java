package com.amazon.alexa;

import android.content.SharedPreferences;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.pryon.LocaleProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
/* compiled from: OfflinePromptsModule_ProvideOfflinePromptsDownloadManagerFactory.java */
/* loaded from: classes.dex */
public final class ylp implements Factory<JEn> {
    public final Provider<ArtifactManager> BIo;
    public final Provider<kHl> JTe;
    public final Provider<Gju> LPk;
    public final Provider<File> Mlj;
    public final Provider<LocaleProvider> Qle;
    public final Provider<TimeProvider> jiA;
    public final Provider<SharedPreferences> yPL;
    public final Provider<DavsClient> zQM;
    public final QYP zZm;
    public final Provider<NetworkManager> zyO;

    public ylp(QYP qyp, Provider<ArtifactManager> provider, Provider<DavsClient> provider2, Provider<NetworkManager> provider3, Provider<TimeProvider> provider4, Provider<LocaleProvider> provider5, Provider<kHl> provider6, Provider<Gju> provider7, Provider<SharedPreferences> provider8, Provider<File> provider9) {
        this.zZm = qyp;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
        this.LPk = provider7;
        this.yPL = provider8;
        this.Mlj = provider9;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (JEn) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get(), this.JTe.mo10268get(), this.LPk.mo10268get(), DoubleCheck.lazy(this.yPL), this.Mlj.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
