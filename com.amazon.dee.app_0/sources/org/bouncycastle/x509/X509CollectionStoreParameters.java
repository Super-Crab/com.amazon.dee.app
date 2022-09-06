package org.bouncycastle.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes5.dex */
public class X509CollectionStoreParameters implements X509StoreParameters {
    private Collection collection;

    public X509CollectionStoreParameters(Collection collection) {
        if (collection != null) {
            this.collection = collection;
            return;
        }
        throw new NullPointerException("collection cannot be null");
    }

    public Object clone() {
        return new X509CollectionStoreParameters(this.collection);
    }

    public Collection getCollection() {
        return new ArrayList(this.collection);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("X509CollectionStoreParameters: [\n");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("  collection: ");
        outline107.append(this.collection);
        outline107.append("\n");
        outline103.append(outline107.toString());
        outline103.append("]");
        return outline103.toString();
    }
}
