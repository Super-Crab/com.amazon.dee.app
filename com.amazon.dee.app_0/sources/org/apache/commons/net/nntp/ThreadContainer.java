package org.apache.commons.net.nntp;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: Threader.java */
/* loaded from: classes4.dex */
class ThreadContainer {
    ThreadContainer child;
    ThreadContainer next;
    ThreadContainer parent;
    ThreadContainer prev;
    Threadable threadable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean findChild(ThreadContainer threadContainer) {
        ThreadContainer threadContainer2 = this.child;
        if (threadContainer2 == null) {
            return false;
        }
        if (threadContainer2 != threadContainer) {
            return threadContainer2.findChild(threadContainer);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush() {
        if (this.parent != null && this.threadable == null) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("no threadable in ");
            outline103.append(toString());
            throw new RuntimeException(outline103.toString());
        }
        this.parent = null;
        Threadable threadable = this.threadable;
        if (threadable != null) {
            ThreadContainer threadContainer = this.child;
            threadable.setChild(threadContainer == null ? null : threadContainer.threadable);
        }
        ThreadContainer threadContainer2 = this.child;
        if (threadContainer2 != null) {
            threadContainer2.flush();
            this.child = null;
        }
        Threadable threadable2 = this.threadable;
        if (threadable2 != null) {
            ThreadContainer threadContainer3 = this.next;
            threadable2.setNext(threadContainer3 == null ? null : threadContainer3.threadable);
        }
        ThreadContainer threadContainer4 = this.next;
        if (threadContainer4 != null) {
            threadContainer4.flush();
            this.next = null;
        }
        this.threadable = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reverseChildren() {
        ThreadContainer threadContainer = this.child;
        if (threadContainer != null) {
            ThreadContainer threadContainer2 = threadContainer.next;
            ThreadContainer threadContainer3 = null;
            while (threadContainer != null) {
                threadContainer.next = threadContainer3;
                threadContainer3 = threadContainer;
                threadContainer = threadContainer2;
                threadContainer2 = threadContainer2 == null ? null : threadContainer2.next;
            }
            this.child = threadContainer3;
            for (ThreadContainer threadContainer4 = this.child; threadContainer4 != null; threadContainer4 = threadContainer4.next) {
                threadContainer4.reverseChildren();
            }
        }
    }
}
