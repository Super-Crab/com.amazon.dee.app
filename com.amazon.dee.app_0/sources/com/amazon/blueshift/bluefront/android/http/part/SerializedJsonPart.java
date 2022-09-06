package com.amazon.blueshift.bluefront.android.http.part;

import com.google.common.base.Charsets;
import com.google.common.net.MediaType;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public class SerializedJsonPart extends Part {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json");
    private final String mShape;

    public SerializedJsonPart(String str, String str2) {
        super(str2, CONTENT_TYPE);
        this.mShape = str;
    }

    @Override // com.amazon.blueshift.bluefront.android.http.part.Part
    protected void writeBody(OutputStream outputStream) throws Exception {
        outputStream.write(this.mShape.getBytes(Charsets.UTF_8.displayName()));
    }
}
