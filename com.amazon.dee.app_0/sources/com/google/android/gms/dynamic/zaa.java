package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes2.dex */
final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.zarj.zarf = lifecycleDelegate;
        linkedList = this.zarj.zarh;
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            lifecycleDelegate2 = this.zarj.zarf;
            ((DeferredLifecycleHelper.zaa) it2.next()).zaa(lifecycleDelegate2);
        }
        linkedList2 = this.zarj.zarh;
        linkedList2.clear();
        this.zarj.zarg = null;
    }
}
