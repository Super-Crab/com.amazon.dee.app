package com.amazon.blueshift.bluefront.android.http.part;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public abstract class Part {
    private static final String CONTENT_DISPOSITION_FORM_DATA = "Content-Disposition: form-data; name=\"";
    private static final String CONTENT_TYPE = "Content-Type: ";
    private static final String NEW_LINE = "\r\n";
    private static final char QUOTE = '\"';
    private final MediaType mContentType;
    private final String mName;

    public Part(String str, MediaType mediaType) {
        Preconditions.checkNotNull(str, "Part name cannot be null");
        Preconditions.checkNotNull(mediaType, "Part Content-Type cannot be null");
        this.mName = str;
        this.mContentType = mediaType;
    }

    private void writeBoundary(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes(Charsets.UTF_8.displayName()));
    }

    private void writeFooter(OutputStream outputStream) throws IOException {
        outputStream.write("\r\n".getBytes(Charsets.UTF_8.displayName()));
    }

    private void writeHeaders(OutputStream outputStream) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CONTENT_DISPOSITION_FORM_DATA);
        outline107.append(this.mName);
        outline107.append('\"');
        outline107.append("\r\n");
        outline107.append(CONTENT_TYPE);
        outline107.append(this.mContentType.toString());
        outline107.append("\r\n");
        outline107.append("\r\n");
        outputStream.write(outline107.toString().getBytes(Charsets.UTF_8.displayName()));
    }

    protected abstract void writeBody(OutputStream outputStream) throws Exception;

    public void writePart(OutputStream outputStream, String str) throws Exception {
        writeBoundary(outputStream, str);
        writeHeaders(outputStream);
        writeBody(outputStream);
        writeFooter(outputStream);
    }
}
