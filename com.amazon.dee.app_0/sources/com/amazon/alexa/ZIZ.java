package com.amazon.alexa;

import android.media.AudioAttributes;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.google.android.exoplayer2.audio.AudioAttributes;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AudioAttributesProvider.java */
@Singleton
/* loaded from: classes.dex */
public class ZIZ {
    public final vVi zZm;

    @Inject
    public ZIZ(vVi vvi) {
        this.zZm = vvi;
    }

    public IkF zZm(PJz pJz, DialogRequestIdentifier dialogRequestIdentifier) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        if (this.zZm.zZm(dialogRequestIdentifier)) {
            builder.setUsage(2);
            builder.setContentType(1);
        } else {
            PNy pNy = (PNy) pJz;
            builder.setUsage(pNy.zQM.zZm());
            builder.setContentType(pNy.zyO.zZm());
        }
        AudioAttributes build = builder.build();
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        if (this.zZm.zZm(dialogRequestIdentifier)) {
            builder2.setUsage(2);
            builder2.setContentType(1);
        } else {
            PNy pNy2 = (PNy) pJz;
            builder2.setUsage(pNy2.zQM.zZm());
            builder2.setContentType(pNy2.zyO.zZm());
        }
        return new IkF(build, builder2.build());
    }
}
