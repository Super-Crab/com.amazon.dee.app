package com.dee.app.data.reactnative;

import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes2.dex */
public class ElementsDataResponse {
    @Nullable
    public ElementsCacheMetadata cacheMetadata;
    @Nullable
    public String contentType;
    @Nullable
    public DataBlob data;
    @Nullable
    public Map<String, String> headers;
    @Nullable
    public String id;
    public int statusCode;
    @Nullable
    public String statusText;
    @Nullable
    public String uri;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ElementsDataResponse.class != obj.getClass()) {
            return false;
        }
        ElementsDataResponse elementsDataResponse = (ElementsDataResponse) obj;
        String str = this.contentType;
        if (str == null ? elementsDataResponse.contentType != null : !str.equals(elementsDataResponse.contentType)) {
            return false;
        }
        String str2 = this.uri;
        if (str2 == null ? elementsDataResponse.uri != null : !str2.equals(elementsDataResponse.uri)) {
            return false;
        }
        DataBlob dataBlob = this.data;
        if (dataBlob == null ? elementsDataResponse.data != null : !dataBlob.equals(elementsDataResponse.data)) {
            return false;
        }
        ElementsCacheMetadata elementsCacheMetadata = this.cacheMetadata;
        if (elementsCacheMetadata == null ? elementsDataResponse.cacheMetadata != null : !elementsCacheMetadata.equals(elementsDataResponse.cacheMetadata)) {
            return false;
        }
        Map<String, String> map = this.headers;
        if (map == null ? elementsDataResponse.headers != null : !map.equals(elementsDataResponse.headers)) {
            return false;
        }
        String str3 = this.id;
        if (str3 == null ? elementsDataResponse.id != null : !str3.equals(elementsDataResponse.id)) {
            return false;
        }
        if (this.statusCode != elementsDataResponse.statusCode) {
            return false;
        }
        String str4 = this.statusText;
        String str5 = elementsDataResponse.statusText;
        return str4 == null ? str5 == null : str4.equals(str5);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        Map<String, String> map = this.headers;
        int hashCode2 = Integer.valueOf(this.statusCode).hashCode() + hashCode + (map != null ? map.hashCode() : 0);
        String str2 = this.contentType;
        int hashCode3 = hashCode2 + (str2 != null ? str2.hashCode() : 0);
        String str3 = this.statusText;
        int hashCode4 = hashCode3 + (str3 != null ? str3.hashCode() : 0);
        String str4 = this.uri;
        int hashCode5 = hashCode4 + (str4 != null ? str4.hashCode() : 0);
        ElementsCacheMetadata elementsCacheMetadata = this.cacheMetadata;
        int hashCode6 = hashCode5 + (elementsCacheMetadata != null ? elementsCacheMetadata.hashCode() : 0);
        DataBlob dataBlob = this.data;
        if (dataBlob != null) {
            i = dataBlob.hashCode();
        }
        return hashCode6 + i;
    }
}
