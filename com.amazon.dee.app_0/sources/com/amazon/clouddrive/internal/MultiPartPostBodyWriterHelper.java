package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
/* loaded from: classes11.dex */
class MultiPartPostBodyWriterHelper {
    static final String BODY_END = "--*****--";
    static final String BODY_SEPARATOR = "--*****\r\n";
    static final String BOUNDARY = "*****";
    static final String CONTENT_DATA_HEADING_HEAD = "Content-Disposition: form-data; name=\"content\"; filename=\"";
    static final String CONTENT_DATA_HEADING_TAIL = "\"\r\n";
    static final String CONTENT_TYPE_HEADING = "Content-Type: %s\r\n";
    static final String DEFAULT_MIME_TYPE = "application/octet-stream";
    public static final String HEADER_CONTENT_TYPE = "multipart/form-data;boundary=*****";
    static final String LINE_END = "\r\n";
    static final String META_DATA_HEADING = "Content-Disposition: form-data; name=\"metadata\"\r\n";
    static final String TWO_HYPHENS = "--";
    private final ByteArrayOutputStream mBodyAfterFileContents;
    private final ByteArrayOutputStream mBodyBeforeFileContents;

    /* loaded from: classes11.dex */
    public static class Builder {
        private boolean mNeedsLineEnding = false;
        private String mName = "";
        private String mMimeType = "application/octet-stream";
        private final ByteArrayOutputStream mBodyBeforeFileContents = new ByteArrayOutputStream();
        private final DataOutputStream mBeforeFileOS = new DataOutputStream(this.mBodyBeforeFileContents);
        private final ByteArrayOutputStream mBodyAfterFileContents = new ByteArrayOutputStream();
        private final DataOutputStream mAfterFileOS = new DataOutputStream(this.mBodyAfterFileContents);

        private Builder addBodyAfterFileContents() throws IOException {
            if (this.mNeedsLineEnding) {
                this.mAfterFileOS.writeBytes("\r\n");
            }
            this.mAfterFileOS.writeBytes(MultiPartPostBodyWriterHelper.BODY_END);
            this.mAfterFileOS.flush();
            return this;
        }

        public Builder addContent() throws IOException {
            this.mNeedsLineEnding = true;
            RequestAssertUtils.assertNotNull(this.mName, "Name cannot be null.");
            RequestAssertUtils.assertNotNullOrEmpty(this.mMimeType, "MimeType cannot be null.");
            this.mBeforeFileOS.writeBytes(MultiPartPostBodyWriterHelper.BODY_SEPARATOR);
            this.mBeforeFileOS.writeBytes(MultiPartPostBodyWriterHelper.CONTENT_DATA_HEADING_HEAD);
            this.mBeforeFileOS.writeBytes(URLEncoder.encode(this.mName, "UTF-8"));
            this.mBeforeFileOS.writeBytes(MultiPartPostBodyWriterHelper.CONTENT_DATA_HEADING_TAIL);
            this.mBeforeFileOS.writeBytes(String.format(MultiPartPostBodyWriterHelper.CONTENT_TYPE_HEADING, this.mMimeType));
            this.mBeforeFileOS.writeBytes("\r\n");
            this.mBeforeFileOS.flush();
            return this;
        }

        public <T> Builder addMetadata(T t, JsonSerializer<T> jsonSerializer) throws IOException, CloudDriveException, InterruptedException {
            RequestAssertUtils.assertNotNull(t, "Request cannot be null.");
            this.mBeforeFileOS.writeBytes(MultiPartPostBodyWriterHelper.BODY_SEPARATOR);
            this.mBeforeFileOS.writeBytes(MultiPartPostBodyWriterHelper.META_DATA_HEADING);
            this.mBeforeFileOS.writeBytes("\r\n");
            CloudDriveObjectMapper.writeStream(this.mBeforeFileOS, t, jsonSerializer);
            this.mBeforeFileOS.writeBytes("\r\n");
            this.mBeforeFileOS.flush();
            return this;
        }

        public MultiPartPostBodyWriterHelper build() throws IOException {
            addBodyAfterFileContents();
            return new MultiPartPostBodyWriterHelper(this);
        }

        public Builder withName(String str) {
            this.mName = str;
            this.mMimeType = URLConnection.guessContentTypeFromName(MultiPartPostBodyWriterHelper.getExtensionFromFilename(str));
            if (this.mMimeType == null) {
                this.mMimeType = "application/octet-stream";
            }
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getExtensionFromFilename(String str) {
        if (str.endsWith("/")) {
            return GeneratedOutlineSupport1.outline72(".", "html");
        }
        int lastIndexOf = str.lastIndexOf(46) + 1;
        int lastIndexOf2 = str.lastIndexOf(35);
        if (lastIndexOf2 < 0 || lastIndexOf2 <= lastIndexOf) {
            lastIndexOf2 = str.length();
        }
        if (lastIndexOf <= str.lastIndexOf(47)) {
            return ".";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".");
        outline107.append(str.substring(lastIndexOf, lastIndexOf2));
        return outline107.toString();
    }

    public ByteArrayOutputStream getBodyAfterFileContents() {
        return this.mBodyAfterFileContents;
    }

    public ByteArrayOutputStream getBodyBeforeFileContents() {
        return this.mBodyBeforeFileContents;
    }

    private MultiPartPostBodyWriterHelper(Builder builder) {
        this.mBodyBeforeFileContents = builder.mBodyBeforeFileContents;
        this.mBodyAfterFileContents = builder.mBodyAfterFileContents;
    }
}
