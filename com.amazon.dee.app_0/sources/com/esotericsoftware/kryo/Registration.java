package com.esotericsoftware.kryo;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes2.dex */
public class Registration {
    private final int id;
    private ObjectInstantiator instantiator;
    private Serializer serializer;
    private final Class type;

    public Registration(Class cls, Serializer serializer, int i) {
        if (cls != null) {
            if (serializer != null) {
                this.type = cls;
                this.serializer = serializer;
                this.id = i;
                return;
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public int getId() {
        return this.id;
    }

    public ObjectInstantiator getInstantiator() {
        return this.instantiator;
    }

    public Serializer getSerializer() {
        return this.serializer;
    }

    public Class getType() {
        return this.type;
    }

    public void setInstantiator(ObjectInstantiator objectInstantiator) {
        if (objectInstantiator != null) {
            this.instantiator = objectInstantiator;
            return;
        }
        throw new IllegalArgumentException("instantiator cannot be null.");
    }

    public void setSerializer(Serializer serializer) {
        if (serializer != null) {
            this.serializer = serializer;
            if (!Log.TRACE) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Update registered serializer: ");
            outline107.append(this.type.getName());
            outline107.append(" (");
            outline107.append(serializer.getClass().getName());
            outline107.append(")");
            Log.trace("kryo", outline107.toString());
            return;
        }
        throw new IllegalArgumentException("serializer cannot be null.");
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(this.id);
        outline107.append(", ");
        outline107.append(Util.className(this.type));
        outline107.append("]");
        return outline107.toString();
    }
}
