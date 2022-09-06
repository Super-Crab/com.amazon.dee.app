package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaCardRendererListenerProxy;
import java.util.Objects;
/* compiled from: CardListenerWrapper.java */
/* loaded from: classes.dex */
public class GvA {
    public static final String zZm = "GvA";
    public final AlexaCardRendererListenerProxy BIo;
    public final AlexaCardListener zQM;

    public GvA(AlexaCardListener alexaCardListener) {
        this.BIo = null;
        this.zQM = alexaCardListener;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GvA.class != obj.getClass()) {
            return false;
        }
        GvA gvA = (GvA) obj;
        return Objects.equals(this.BIo, gvA.BIo) && Objects.equals(this.zQM, gvA.zQM);
    }

    public int hashCode() {
        return Objects.hash(this.BIo, this.zQM);
    }

    public GvA(AlexaCardRendererListenerProxy alexaCardRendererListenerProxy) {
        this.BIo = alexaCardRendererListenerProxy;
        this.zQM = null;
    }
}
