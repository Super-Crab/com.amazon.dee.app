package com.adobe.xmp;

import java.util.Iterator;
/* loaded from: classes.dex */
public interface XMPIterator extends Iterator {
    void skipSiblings();

    void skipSubtree();
}
