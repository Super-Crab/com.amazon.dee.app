package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class DocumentProperties {
    @JsonProperty("authors")
    private List<String> authors;
    @JsonProperty("documentVersion")
    private String documentVersion;
    @JsonProperty("title")
    private String title;

    protected boolean canEqual(Object obj) {
        return obj instanceof DocumentProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DocumentProperties)) {
            return false;
        }
        DocumentProperties documentProperties = (DocumentProperties) obj;
        if (!documentProperties.canEqual(this)) {
            return false;
        }
        String title = getTitle();
        String title2 = documentProperties.getTitle();
        if (title != null ? !title.equals(title2) : title2 != null) {
            return false;
        }
        String documentVersion = getDocumentVersion();
        String documentVersion2 = documentProperties.getDocumentVersion();
        if (documentVersion != null ? !documentVersion.equals(documentVersion2) : documentVersion2 != null) {
            return false;
        }
        List<String> authors = getAuthors();
        List<String> authors2 = documentProperties.getAuthors();
        return authors != null ? authors.equals(authors2) : authors2 == null;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getDocumentVersion() {
        return this.documentVersion;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String title = getTitle();
        int i = 43;
        int hashCode = title == null ? 43 : title.hashCode();
        String documentVersion = getDocumentVersion();
        int hashCode2 = ((hashCode + 59) * 59) + (documentVersion == null ? 43 : documentVersion.hashCode());
        List<String> authors = getAuthors();
        int i2 = hashCode2 * 59;
        if (authors != null) {
            i = authors.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("authors")
    public void setAuthors(List<String> list) {
        this.authors = list;
    }

    @JsonProperty("documentVersion")
    public void setDocumentVersion(String str) {
        this.documentVersion = str;
    }

    @JsonProperty("title")
    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DocumentProperties(title=");
        outline107.append(getTitle());
        outline107.append(", documentVersion=");
        outline107.append(getDocumentVersion());
        outline107.append(", authors=");
        outline107.append(getAuthors());
        outline107.append(")");
        return outline107.toString();
    }
}
