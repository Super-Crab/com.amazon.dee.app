package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes4.dex */
public class NoOpThreadContextMap implements ThreadContextMap {
    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean containsKey(String str) {
        return false;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public String get(String str) {
        return null;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        return new HashMap();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        return null;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public boolean isEmpty() {
        return true;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(String str, String str2) {
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(String str) {
    }
}
