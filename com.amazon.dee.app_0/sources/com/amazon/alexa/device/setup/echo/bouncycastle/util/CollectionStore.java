package com.amazon.alexa.device.setup.echo.bouncycastle.util;

import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes.dex */
public class CollectionStore implements Store {
    private final Collection _local;

    public CollectionStore(Collection collection) {
        this._local = new ArrayList(collection);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Store
    public Collection getMatches(Selector selector) {
        if (selector == null) {
            return new ArrayList(this._local);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : this._local) {
            if (selector.match(obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
