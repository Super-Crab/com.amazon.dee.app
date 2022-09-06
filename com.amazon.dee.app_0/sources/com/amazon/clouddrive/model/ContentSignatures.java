package com.amazon.clouddrive.model;

import java.util.Map;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ContentSignatures implements Comparable<ContentSignatures> {
    private final Map<String, String> contentSignatures;

    public ContentSignatures(Map<String, String> map) {
        this.contentSignatures = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ContentSignatures) && compareTo((ContentSignatures) obj) == 0;
    }

    public String getSignature(String str) {
        return this.contentSignatures.get(str.toLowerCase());
    }

    public int hashCode() {
        return Objects.hash(this.contentSignatures);
    }

    @Override // java.lang.Comparable
    public int compareTo(ContentSignatures contentSignatures) {
        Map<String, String> map;
        Map<String, String> map2;
        if (contentSignatures == null) {
            return -1;
        }
        if (this != contentSignatures && (map = this.contentSignatures) != (map2 = contentSignatures.contentSignatures)) {
            if (map == null) {
                return -1;
            }
            if (map2 == null) {
                return 1;
            }
            if (!map.equals(map2)) {
                int hashCode = this.contentSignatures.hashCode();
                int hashCode2 = contentSignatures.contentSignatures.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
