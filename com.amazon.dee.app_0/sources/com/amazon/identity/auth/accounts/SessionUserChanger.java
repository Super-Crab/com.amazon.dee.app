package com.amazon.identity.auth.accounts;

import com.amazon.identity.auth.device.hs;
import com.amazon.identity.auth.device.io;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class SessionUserChanger {
    private static final String TAG = "com.amazon.identity.auth.accounts.SessionUserChanger";
    private final AmazonAccountManager cE;
    private final List<OnSessionUsersChangedListener> cF = new ArrayList();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface OnSessionUsersChangedListener {
        void onChanged(Collection<String> collection, Collection<String> collection2);
    }

    public SessionUserChanger(AmazonAccountManager amazonAccountManager) {
        this.cE = amazonAccountManager;
    }

    private Set<String> a(Collection<String> collection, Collection<String> collection2) {
        if (collection.size() <= 0 && collection2.size() <= 0) {
            io.i(TAG, "No New Session Users to add");
        } else {
            for (String str : collection) {
                this.cE.y(str);
            }
            for (String str2 : collection2) {
                this.cE.z(str2);
            }
            for (OnSessionUsersChangedListener onSessionUsersChangedListener : this.cF) {
                onSessionUsersChangedListener.onChanged(collection, collection2);
            }
        }
        return this.cE.q();
    }

    private boolean ac(String str) {
        return this.cE.D(str) && !this.cE.x(str);
    }

    public Set<String> ab(String str) {
        if (!this.cE.B(str)) {
            String str2 = TAG;
            String.format("%s is not a session user, so nothing to notify about it being deregistered", str);
            io.dm(str2);
            return this.cE.q();
        }
        HashSet hashSet = new HashSet();
        String str3 = null;
        if (this.cE.A(str) && this.cE.q().size() == 1) {
            String o = this.cE.o();
            if (ac(o)) {
                str3 = o;
            }
        }
        if (str3 != null) {
            hashSet.add(str3);
        }
        return a(hashSet, hs.a(str));
    }

    public Set<String> d(Set<String> set) {
        HashSet hashSet;
        Set<String> q = this.cE.q();
        if (set == null) {
            hashSet = null;
        } else {
            HashSet hashSet2 = new HashSet();
            for (String str : set) {
                if (ac(str)) {
                    hashSet2.add(str);
                }
            }
            hashSet = hashSet2;
        }
        if (hashSet != null && hashSet.size() != 0) {
            Set<String> q2 = this.cE.q();
            HashSet hashSet3 = new HashSet();
            hashSet3.addAll(hashSet);
            hashSet3.removeAll(q2);
            HashSet hashSet4 = new HashSet();
            hashSet4.addAll(q2);
            hashSet4.removeAll(hashSet);
            return a(hashSet3, hashSet4);
        }
        io.e(TAG, "Cannot remove all session users. Change aborted");
        return q;
    }
}
