package com.amazon.alexa;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* compiled from: NameSpaceDirective.java */
/* loaded from: classes.dex */
public class zAS {
    public final Name BIo;
    public final Namespace zZm;

    public zAS(@NonNull Namespace namespace, @NonNull Name name) {
        this.zZm = namespace;
        this.BIo = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || zAS.class != obj.getClass()) {
            return false;
        }
        zAS zas = (zAS) obj;
        if (this.zZm.getValue().equals(zas.zZm.getValue())) {
            return this.BIo.getValue().equals(zas.BIo.getValue());
        }
        return false;
    }

    public int hashCode() {
        return this.BIo.getValue().hashCode() + (this.zZm.getValue().hashCode() * 31);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("NameSpaceDirective{namespace=");
        zZm.append(this.zZm);
        zZm.append(", name=");
        zZm.append(this.BIo);
        zZm.append(JsonReaderKt.END_OBJ);
        return zZm.toString();
    }

    public Name zZm() {
        return this.BIo;
    }
}
