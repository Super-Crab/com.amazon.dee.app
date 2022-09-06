package org.bouncycastle.jsse;

import java.util.List;
/* loaded from: classes4.dex */
public interface BCApplicationProtocolSelector<T> {
    String select(T t, List<String> list);
}
