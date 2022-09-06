package org.apache.oro.util;

import java.io.Serializable;
/* loaded from: classes4.dex */
final class GenericCacheEntry implements Serializable {
    int _index;
    Object _value = null;
    Object _key = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericCacheEntry(int i) {
        this._index = i;
    }
}
