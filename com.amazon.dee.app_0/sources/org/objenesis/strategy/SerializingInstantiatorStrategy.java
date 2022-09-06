package org.objenesis.strategy;

import java.io.NotSerializableException;
import java.io.Serializable;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.AndroidSerializationInstantiator;
import org.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import org.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import org.objenesis.instantiator.perc.PercSerializationInstantiator;
/* loaded from: classes5.dex */
public class SerializingInstantiatorStrategy extends BaseInstantiatorStrategy {
    @Override // org.objenesis.strategy.InstantiatorStrategy
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        if (Serializable.class.isAssignableFrom(cls)) {
            return (PlatformDescription.JVM_NAME.startsWith(PlatformDescription.SUN) || PlatformDescription.isThisJVM(PlatformDescription.OPENJDK)) ? new ObjectStreamClassInstantiator(cls) : PlatformDescription.JVM_NAME.startsWith(PlatformDescription.DALVIK) ? new AndroidSerializationInstantiator(cls) : PlatformDescription.JVM_NAME.startsWith(PlatformDescription.GNU) ? new GCJSerializationInstantiator(cls) : PlatformDescription.JVM_NAME.startsWith(PlatformDescription.PERC) ? new PercSerializationInstantiator(cls) : new ObjectStreamClassInstantiator(cls);
        }
        throw new ObjenesisException(new NotSerializableException(cls + " not serializable"));
    }
}
