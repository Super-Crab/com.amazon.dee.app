package org.apache.commons.fileupload;

import java.util.Iterator;
/* loaded from: classes4.dex */
public interface FileItemHeaders {
    String getHeader(String str);

    Iterator<String> getHeaderNames();

    Iterator<String> getHeaders(String str);
}
