package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* compiled from: MultipleSendMessageCallback.java */
/* loaded from: classes.dex */
public class MnN extends UcG {
    public final List<UcG> zZm;

    public MnN(Collection<UcG> collection) {
        this.zZm = new ArrayList(collection);
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
        for (UcG ucG : this.zZm) {
            ucG.onFailure(rrI, num, exc);
        }
    }

    @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
    public void onSuccess(RrI rrI, Collection<Message> collection) {
        for (UcG ucG : this.zZm) {
            ucG.onSuccess(rrI, collection);
        }
    }
}
