package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* compiled from: AudioPlayerInteraction.java */
/* loaded from: classes.dex */
public class nkN extends UcG {
    public final /* synthetic */ Bha BIo;
    public int zZm = 6;

    public nkN(Bha bha) {
        this.BIo = bha;
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
        ScheduledExecutorService scheduledExecutorService;
        Log.e(Bha.JTe, "Failed to send playback nearly finished. Retrying");
        this.zZm--;
        if (this.zZm > 0) {
            scheduledExecutorService = this.BIo.HvC;
            scheduledExecutorService.schedule(new ach(this, this), 500L, TimeUnit.MILLISECONDS);
            return;
        }
        this.BIo.zzR.zyO(new C0212jfo(false));
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onSuccess(RrI rrI, Collection<Message> collection) {
        boolean z;
        Iterator<Message> it2 = collection.iterator();
        while (true) {
            z = true;
            boolean z2 = false;
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            Header header = it2.next().getHeader();
            if (AvsApiConstants.AudioPlayer.zZm.equals(header.getNamespace()) && AvsApiConstants.AudioPlayer.Directives.Play.zZm.equals(header.getName())) {
                z2 = true;
                continue;
            }
            if (z2) {
                break;
            }
        }
        this.BIo.zzR.zyO(new C0212jfo(z));
    }
}
