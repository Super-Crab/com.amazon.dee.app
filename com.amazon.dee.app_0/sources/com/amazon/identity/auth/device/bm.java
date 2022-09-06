package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bm {
    private final Map<String, List<Callback>> hg = new HashMap();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    class a implements Callback {
        private final String mId;

        public a(String str) {
            this.mId = str;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            List<Callback> aJ = bm.this.aJ(this.mId);
            for (Callback callback : aJ) {
                callback.onError(bundle);
            }
            aJ.clear();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            List<Callback> aJ = bm.this.aJ(this.mId);
            for (Callback callback : aJ) {
                callback.onSuccess(bundle);
            }
            aJ.clear();
        }
    }

    private synchronized List<Callback> aK(String str) {
        List<Callback> list;
        list = this.hg.get(str);
        if (list == null) {
            list = aL(str);
        }
        return list;
    }

    private synchronized List<Callback> aL(String str) {
        LinkedList linkedList;
        linkedList = new LinkedList();
        this.hg.put(str, linkedList);
        return linkedList;
    }

    synchronized List<Callback> aJ(String str) {
        List<Callback> aK;
        aK = aK(str);
        if (aK.size() > 0) {
            aL(str);
        }
        return aK;
    }

    public synchronized Callback b(String str, Callback callback) {
        List<Callback> aK = aK(str);
        aK.add(callback);
        if (aK.size() > 1) {
            return null;
        }
        return new a(str);
    }
}
