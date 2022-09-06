package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class BatchLink implements Comparable<BatchLink> {
    private String content;
    private String self;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BatchLink) && compareTo((BatchLink) obj) == 0;
    }

    public String getContent() {
        return this.content;
    }

    public String getSelf() {
        return this.self;
    }

    public int hashCode() {
        String str = this.self;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.content;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setSelf(String str) {
        this.self = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(BatchLink batchLink) {
        if (batchLink == null) {
            return -1;
        }
        if (batchLink == this) {
            return 0;
        }
        String self = getSelf();
        String self2 = batchLink.getSelf();
        if (self != self2) {
            if (self == null) {
                return -1;
            }
            if (self2 == null) {
                return 1;
            }
            int compareTo = self.compareTo(self2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String content = getContent();
        String content2 = batchLink.getContent();
        if (content != content2) {
            if (content == null) {
                return -1;
            }
            if (content2 == null) {
                return 1;
            }
            int compareTo2 = content.compareTo(content2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
