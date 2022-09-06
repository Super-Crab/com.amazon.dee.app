package org.bouncycastle.mime;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes5.dex */
public abstract class MimeWriter {
    protected final Headers headers;

    /* JADX INFO: Access modifiers changed from: protected */
    public MimeWriter(Headers headers) {
        this.headers = headers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<String> mapToLines(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (String str : map.keySet()) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(map.get(str));
            arrayList.add(outline113.toString());
        }
        return arrayList;
    }

    public abstract OutputStream getContentStream() throws IOException;

    public Headers getHeaders() {
        return this.headers;
    }
}
