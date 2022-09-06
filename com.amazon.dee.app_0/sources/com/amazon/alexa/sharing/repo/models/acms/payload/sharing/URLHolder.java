package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class URLHolder {
    private String origin;
    private String url;

    public URLHolder() {
        this(null, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || URLHolder.class != obj.getClass()) {
            return false;
        }
        URLHolder uRLHolder = (URLHolder) obj;
        return Objects.equals(this.url, uRLHolder.url) && Objects.equals(this.origin, uRLHolder.origin);
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return Objects.hash(this.url, this.origin);
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URLHolder{url='");
        GeneratedOutlineSupport1.outline176(outline107, this.url, Chars.QUOTE, ", origin='");
        return GeneratedOutlineSupport1.outline90(outline107, this.origin, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public URLHolder(String str, String str2) {
        this.url = str;
        this.origin = str2;
    }
}
