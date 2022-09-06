package org.apache.logging.log4j.spi;
/* loaded from: classes4.dex */
public interface CleanableThreadContextMap extends ThreadContextMap2 {
    void removeAll(Iterable<String> iterable);
}
