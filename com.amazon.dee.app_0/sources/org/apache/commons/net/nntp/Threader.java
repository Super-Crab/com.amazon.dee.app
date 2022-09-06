package org.apache.commons.net.nntp;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes4.dex */
public class Threader {
    private int bogusIdCount = 0;
    private HashMap idTable;
    private ThreadContainer root;

    private void buildContainer(Threadable threadable) {
        String messageThreadId = threadable.messageThreadId();
        ThreadContainer threadContainer = (ThreadContainer) this.idTable.get(messageThreadId);
        if (threadContainer != null) {
            if (threadContainer.threadable != null) {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("<Bogus-id:");
                int i = this.bogusIdCount;
                this.bogusIdCount = i + 1;
                outline103.append(i);
                outline103.append(Config.Compare.GREATER_THAN);
                messageThreadId = outline103.toString();
                threadContainer = null;
            } else {
                threadContainer.threadable = threadable;
            }
        }
        if (threadContainer == null) {
            threadContainer = new ThreadContainer();
            threadContainer.threadable = threadable;
            this.idTable.put(messageThreadId, threadContainer);
        }
        String[] messageThreadReferences = threadable.messageThreadReferences();
        int i2 = 0;
        ThreadContainer threadContainer2 = null;
        while (i2 < messageThreadReferences.length) {
            String str = messageThreadReferences[i2];
            ThreadContainer threadContainer3 = (ThreadContainer) this.idTable.get(str);
            if (threadContainer3 == null) {
                threadContainer3 = new ThreadContainer();
                this.idTable.put(str, threadContainer3);
            }
            if (threadContainer2 != null && threadContainer3.parent == null && threadContainer2 != threadContainer3 && !threadContainer2.findChild(threadContainer3)) {
                threadContainer3.parent = threadContainer2;
                threadContainer3.next = threadContainer2.child;
                threadContainer2.child = threadContainer3;
            }
            i2++;
            threadContainer2 = threadContainer3;
        }
        if (threadContainer2 != null && (threadContainer2 == threadContainer || threadContainer.findChild(threadContainer2))) {
            threadContainer2 = null;
        }
        ThreadContainer threadContainer4 = threadContainer.parent;
        if (threadContainer4 != null) {
            ThreadContainer threadContainer5 = threadContainer4.child;
            ThreadContainer threadContainer6 = null;
            while (threadContainer5 != null && threadContainer5 != threadContainer) {
                threadContainer6 = threadContainer5;
                threadContainer5 = threadContainer5.next;
            }
            if (threadContainer5 != null) {
                if (threadContainer6 == null) {
                    threadContainer.parent.child = threadContainer.next;
                } else {
                    threadContainer6.next = threadContainer.next;
                }
                threadContainer.next = null;
                threadContainer.parent = null;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Didnt find ");
                stringBuffer.append(threadContainer);
                stringBuffer.append(" in parent");
                stringBuffer.append(threadContainer.parent);
                throw new RuntimeException(stringBuffer.toString());
            }
        }
        if (threadContainer2 != null) {
            threadContainer.parent = threadContainer2;
            threadContainer.next = threadContainer2.child;
            threadContainer2.child = threadContainer;
        }
    }

    private ThreadContainer findRootSet() {
        ThreadContainer threadContainer = new ThreadContainer();
        for (Object obj : this.idTable.keySet()) {
            ThreadContainer threadContainer2 = (ThreadContainer) this.idTable.get(obj);
            if (threadContainer2.parent == null) {
                if (threadContainer2.next == null) {
                    threadContainer2.next = threadContainer.child;
                    threadContainer.child = threadContainer2;
                } else {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("c.next is ");
                    outline103.append(threadContainer2.next.toString());
                    throw new RuntimeException(outline103.toString());
                }
            }
        }
        return threadContainer;
    }

    private void gatherSubjects() {
        ThreadContainer threadContainer;
        Threadable threadable;
        ThreadContainer threadContainer2;
        Threadable threadable2;
        Threadable threadable3;
        int i = 0;
        int i2 = 0;
        for (ThreadContainer threadContainer3 = this.root.child; threadContainer3 != null; threadContainer3 = threadContainer3.next) {
            i2++;
        }
        HashMap hashMap = new HashMap((int) (i2 * 1.2d), 0.9f);
        for (ThreadContainer threadContainer4 = this.root.child; threadContainer4 != null; threadContainer4 = threadContainer4.next) {
            Threadable threadable4 = threadContainer4.threadable;
            if (threadable4 == null) {
                threadable4 = threadContainer4.child.threadable;
            }
            String simplifiedSubject = threadable4.simplifiedSubject();
            if (simplifiedSubject != null && simplifiedSubject != "" && ((threadContainer2 = (ThreadContainer) hashMap.get(simplifiedSubject)) == null || ((threadContainer4.threadable == null && threadContainer2.threadable != null) || ((threadable2 = threadContainer2.threadable) != null && threadable2.subjectIsReply() && (threadable3 = threadContainer4.threadable) != null && !threadable3.subjectIsReply())))) {
                hashMap.put(simplifiedSubject, threadContainer4);
                i++;
            }
        }
        if (i == 0) {
            return;
        }
        ThreadContainer threadContainer5 = this.root.child;
        ThreadContainer threadContainer6 = threadContainer5.next;
        ThreadContainer threadContainer7 = null;
        while (threadContainer5 != null) {
            Threadable threadable5 = threadContainer5.threadable;
            if (threadable5 == null) {
                threadable5 = threadContainer5.child.threadable;
            }
            String simplifiedSubject2 = threadable5.simplifiedSubject();
            if (simplifiedSubject2 == null || simplifiedSubject2 == "" || (threadContainer = (ThreadContainer) hashMap.get(simplifiedSubject2)) == threadContainer5) {
                threadContainer7 = threadContainer5;
            } else {
                if (threadContainer7 == null) {
                    this.root.child = threadContainer5.next;
                } else {
                    threadContainer7.next = threadContainer5.next;
                }
                threadContainer5.next = null;
                if (threadContainer.threadable == null && threadContainer5.threadable == null) {
                    ThreadContainer threadContainer8 = threadContainer.child;
                    while (threadContainer8 != null) {
                        ThreadContainer threadContainer9 = threadContainer8.next;
                        if (threadContainer9 == null) {
                            break;
                        }
                        threadContainer8 = threadContainer9;
                    }
                    ThreadContainer threadContainer10 = threadContainer5.child;
                    threadContainer8.next = threadContainer10;
                    while (threadContainer10 != null) {
                        threadContainer10.parent = threadContainer;
                        threadContainer10 = threadContainer10.next;
                    }
                    threadContainer5.child = null;
                } else if (threadContainer.threadable != null && ((threadable = threadContainer5.threadable) == null || !threadable.subjectIsReply() || threadContainer.threadable.subjectIsReply())) {
                    ThreadContainer threadContainer11 = new ThreadContainer();
                    threadContainer11.threadable = threadContainer.threadable;
                    threadContainer11.child = threadContainer.child;
                    for (ThreadContainer threadContainer12 = threadContainer11.child; threadContainer12 != null; threadContainer12 = threadContainer12.next) {
                        threadContainer12.parent = threadContainer11;
                    }
                    threadContainer.threadable = null;
                    threadContainer.child = null;
                    threadContainer5.parent = threadContainer;
                    threadContainer11.parent = threadContainer;
                    threadContainer.child = threadContainer5;
                    threadContainer5.next = threadContainer11;
                } else {
                    threadContainer5.parent = threadContainer;
                    threadContainer5.next = threadContainer.child;
                    threadContainer.child = threadContainer5;
                }
            }
            ThreadContainer threadContainer13 = threadContainer6;
            threadContainer6 = threadContainer6 == null ? null : threadContainer6.next;
            threadContainer5 = threadContainer13;
        }
        hashMap.clear();
    }

    private void pruneEmptyContainers(ThreadContainer threadContainer) {
        ThreadContainer threadContainer2;
        ThreadContainer threadContainer3 = threadContainer.child;
        ThreadContainer threadContainer4 = threadContainer3.next;
        ThreadContainer threadContainer5 = null;
        while (threadContainer3 != null) {
            if (threadContainer3.threadable == null && threadContainer3.child == null) {
                if (threadContainer5 == null) {
                    threadContainer.child = threadContainer3.next;
                } else {
                    threadContainer5.next = threadContainer3.next;
                }
            } else if (threadContainer3.threadable == null && (threadContainer2 = threadContainer3.child) != null && (threadContainer3.parent != null || threadContainer2.next == null)) {
                threadContainer4 = threadContainer3.child;
                if (threadContainer5 == null) {
                    threadContainer.child = threadContainer4;
                } else {
                    threadContainer5.next = threadContainer4;
                }
                ThreadContainer threadContainer6 = threadContainer4;
                while (true) {
                    ThreadContainer threadContainer7 = threadContainer6.next;
                    if (threadContainer7 == null) {
                        break;
                    }
                    threadContainer6.parent = threadContainer3.parent;
                    threadContainer6 = threadContainer7;
                }
                threadContainer6.parent = threadContainer3.parent;
                threadContainer6.next = threadContainer3.next;
            } else {
                if (threadContainer3.child != null) {
                    pruneEmptyContainers(threadContainer3);
                }
                threadContainer5 = threadContainer3;
            }
            threadContainer3 = threadContainer4;
            threadContainer4 = threadContainer3 == null ? null : threadContainer3.next;
        }
    }

    public Threadable thread(Threadable[] threadableArr) {
        if (threadableArr == null) {
            return null;
        }
        this.idTable = new HashMap();
        for (int i = 0; i < threadableArr.length; i++) {
            if (!threadableArr[i].isDummy()) {
                buildContainer(threadableArr[i]);
            }
        }
        this.root = findRootSet();
        this.idTable.clear();
        this.idTable = null;
        pruneEmptyContainers(this.root);
        this.root.reverseChildren();
        gatherSubjects();
        ThreadContainer threadContainer = this.root;
        if (threadContainer.next == null) {
            for (ThreadContainer threadContainer2 = threadContainer.child; threadContainer2 != null; threadContainer2 = threadContainer2.next) {
                if (threadContainer2.threadable == null) {
                    threadContainer2.threadable = threadContainer2.child.threadable.makeDummy();
                }
            }
            ThreadContainer threadContainer3 = this.root.child;
            Threadable threadable = threadContainer3 == null ? null : threadContainer3.threadable;
            this.root.flush();
            this.root = null;
            return threadable;
        }
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("root node has a next:");
        outline103.append(this.root);
        throw new RuntimeException(outline103.toString());
    }
}
