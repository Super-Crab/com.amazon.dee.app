package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_IOComponentContext.java */
/* loaded from: classes.dex */
public abstract class KIc extends URU {
    public final Name BIo;
    public final Integer jiA;
    public final Integer zQM;
    public final Namespace zZm;
    public final String zyO;

    public KIc(Namespace namespace, Name name, Integer num, String str, Integer num2) {
        if (namespace != null) {
            this.zZm = namespace;
            if (name != null) {
                this.BIo = name;
                if (num != null) {
                    this.zQM = num;
                    if (str != null) {
                        this.zyO = str;
                        if (num2 != null) {
                            this.jiA = num2;
                            return;
                        }
                        throw new NullPointerException("Null uncertaintyInMilliseconds");
                    }
                    throw new NullPointerException("Null timeOfSample");
                }
                throw new NullPointerException("Null value");
            }
            throw new NullPointerException("Null name");
        }
        throw new NullPointerException("Null namespace");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof URU)) {
            return false;
        }
        KIc kIc = (KIc) obj;
        return this.zZm.equals(kIc.zZm) && this.BIo.equals(kIc.BIo) && this.zQM.equals(kIc.zQM) && this.zyO.equals(kIc.zyO) && this.jiA.equals(kIc.jiA);
    }

    public int hashCode() {
        return ((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("IOComponentContext{namespace=");
        zZm.append(this.zZm);
        zZm.append(", name=");
        zZm.append(this.BIo);
        zZm.append(", value=");
        zZm.append(this.zQM);
        zZm.append(", timeOfSample=");
        zZm.append(this.zyO);
        zZm.append(", uncertaintyInMilliseconds=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
