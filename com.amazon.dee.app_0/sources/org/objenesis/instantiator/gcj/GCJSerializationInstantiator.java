package org.objenesis.instantiator.gcj;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
/* loaded from: classes5.dex */
public class GCJSerializationInstantiator<T> extends GCJInstantiatorBase<T> {
    private Class<? super T> superType;

    public GCJSerializationInstantiator(Class<T> cls) {
        super(cls);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(cls);
    }

    @Override // org.objenesis.instantiator.gcj.GCJInstantiatorBase, org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return this.type.cast(GCJInstantiatorBase.newObjectMethod.invoke(GCJInstantiatorBase.dummyStream, this.type, this.superType));
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
