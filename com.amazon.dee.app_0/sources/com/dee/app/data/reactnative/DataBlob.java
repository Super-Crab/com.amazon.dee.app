package com.dee.app.data.reactnative;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class DataBlob {
    @Nullable
    public final String contentType;
    @Nullable
    public final String text;

    public DataBlob(String str, String str2) {
        this.text = str;
        this.contentType = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DataBlob.class != obj.getClass()) {
            return false;
        }
        DataBlob dataBlob = (DataBlob) obj;
        String str = this.text;
        if (str == null ? dataBlob.text != null : !str.equals(dataBlob.text)) {
            return false;
        }
        String str2 = this.contentType;
        String str3 = dataBlob.contentType;
        return str2 == null ? str3 == null : str2.equals(str3);
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.contentType;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
