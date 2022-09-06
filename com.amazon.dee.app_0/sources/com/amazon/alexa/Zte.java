package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ComponentStateHeader.java */
/* loaded from: classes.dex */
public abstract class Zte extends ComponentStateHeader {
    public final Name BIo;
    public final Namespace zZm;

    public Zte(Namespace namespace, Name name) {
        if (namespace != null) {
            this.zZm = namespace;
            if (name != null) {
                this.BIo = name;
                return;
            }
            throw new NullPointerException("Null name");
        }
        throw new NullPointerException("Null namespace");
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader
    public Namespace BIo() {
        return this.zZm;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComponentStateHeader)) {
            return false;
        }
        ComponentStateHeader componentStateHeader = (ComponentStateHeader) obj;
        return this.zZm.equals(componentStateHeader.BIo()) && this.BIo.equals(componentStateHeader.zZm());
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ComponentStateHeader{namespace=");
        zZm.append(this.zZm);
        zZm.append(", name=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader
    public Name zZm() {
        return this.BIo;
    }
}
