package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver;
@Deprecated
/* loaded from: classes13.dex */
public class Id {
    private static final Id EMPTY_ID = new Id("");
    private final String id;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Id(String str) {
        this.id = str;
    }

    public static synchronized Id getEmptyId() {
        Id id;
        synchronized (Id.class) {
            id = EMPTY_ID;
        }
        return id;
    }

    public static Id valueOf(String str) {
        if (str != null && !str.equals("")) {
            return new Id(str);
        }
        return getEmptyId();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Id.class != obj.getClass()) {
            return false;
        }
        Id id = (Id) obj;
        String str = this.id;
        if (str == null) {
            if (id.id != null) {
                return false;
            }
        } else if (!str.equals(id.id)) {
            return false;
        }
        return true;
    }

    public String getValue() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        return 31 + (str == null ? 0 : str.hashCode());
    }
}
