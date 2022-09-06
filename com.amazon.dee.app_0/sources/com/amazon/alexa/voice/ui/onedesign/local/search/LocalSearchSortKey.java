package com.amazon.alexa.voice.ui.onedesign.local.search;
/* loaded from: classes11.dex */
public enum LocalSearchSortKey {
    UNKNOWN("UNKNOWN"),
    DISTANCE("DISTANCE"),
    RELEVANCE("RELEVANCE");
    
    private final String sortKey;

    LocalSearchSortKey(String str) {
        this.sortKey = str;
    }

    public static LocalSearchSortKey from(String str) {
        LocalSearchSortKey[] values;
        for (LocalSearchSortKey localSearchSortKey : values()) {
            if (localSearchSortKey.toString().equals(str)) {
                return localSearchSortKey;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.sortKey;
    }
}
