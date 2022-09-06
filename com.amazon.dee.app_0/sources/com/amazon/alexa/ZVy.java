package com.amazon.alexa;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amazon.alexa.Cta;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.zoO;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
/* compiled from: LegacyFlagsModule.java */
@Module
/* loaded from: classes.dex */
public class ZVy {
    @Provides
    public Cta zZm(WindowManager windowManager, Lazy<ClientConfiguration> lazy) {
        String datamartNamespace = !TextUtils.isEmpty(lazy.mo358get().getDatamartNamespace()) ? lazy.mo358get().getDatamartNamespace() : "Vox";
        boolean booleanValue = lazy.mo358get().getSupportsPfmChanged() != null ? lazy.mo358get().getSupportsPfmChanged().booleanValue() : true;
        zoO.zZm zzm = new zoO.zZm();
        zzm.zZm = true;
        Cta.zZm zZm = zzm.LPk(false).Mlj(booleanValue).zZm("VOX");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return zZm.zZm(displayMetrics.widthPixels).uzr(false).BIo(false).zzR(true).Qle(true).JTe(false).yPL(false).zyO(true).jiA(true).dMe(true).HvC(true).vkx(true).zZm(true).lOf(false).BIo(datamartNamespace).zQM("TABLET").zQM(true).zZm();
    }
}
