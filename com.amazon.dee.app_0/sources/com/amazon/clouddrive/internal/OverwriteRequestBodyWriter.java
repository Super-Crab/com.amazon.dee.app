package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.MultiPartPostBodyWriterHelper;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.StreamUtil;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.amazon.clouddrive.model.OverwriteFileRequest;
import com.amazon.clouddrive.model.serializer.OverwriteRequestSerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class OverwriteRequestBodyWriter extends RequestPropertyWriterImpl implements PostRequestWriter {
    private ByteArrayOutputStream mBodyAfterFileContents;
    private ByteArrayOutputStream mBodyBeforeFileContents;
    private final ProgressListener mProgressListener;
    private final OverwriteFileRequest mRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OverwriteRequestBodyWriter(OverwriteFileRequest overwriteFileRequest, ProgressListener progressListener) {
        this.mRequest = overwriteFileRequest;
        this.mProgressListener = progressListener;
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriterImpl, com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        boolean z = this.mRequest.getInputStream() != null;
        try {
            setRequestProperty("Content-Type", MultiPartPostBodyWriterHelper.HEADER_CONTENT_TYPE);
            MultiPartPostBodyWriterHelper.Builder builder = new MultiPartPostBodyWriterHelper.Builder();
            builder.addMetadata(this.mRequest, OverwriteRequestSerializer.INSTANCE);
            if (z) {
                builder.addContent();
            }
            MultiPartPostBodyWriterHelper build = builder.build();
            this.mBodyBeforeFileContents = build.getBodyBeforeFileContents();
            this.mBodyAfterFileContents = build.getBodyAfterFileContents();
            setStreamingMode(httpURLConnection, this.mRequest.useChunkedStreaming(), this.mRequest.getChunkSize(), this.mRequest.getContentLength() + this.mBodyBeforeFileContents.size() + this.mBodyAfterFileContents.size());
            super.writeHeaders(httpURLConnection);
        } catch (IOException e) {
            throw new CloudDriveException("Failed to build the post body", e);
        }
    }

    @Override // com.amazon.clouddrive.internal.PostRequestWriter
    public void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException {
        RequestAssertUtils.assertNotNull(this.mBodyBeforeFileContents, "The writeHeaders method was not called or did not successfully complete.");
        RequestAssertUtils.assertNotNull(this.mBodyAfterFileContents, "The writeHeaders method was not called or did not successfully complete.");
        try {
            try {
                ThreadUtil.checkIfInterrupted();
                this.mBodyBeforeFileContents.writeTo(outputStream);
                if (this.mRequest.getInputStream() != null && StreamUtil.copyInputStreamToOutputStream(new ProgressInputStream(this.mRequest.getInputStream(), this.mRequest.getContentLength(), this.mProgressListener), outputStream, this.mRequest.getBlockSize(), this.mRequest.getContentLength()) != this.mRequest.getContentLength()) {
                    throw new InvalidParameter("InputStream was longer than declared contentLength [" + this.mRequest.getContentLength() + "].", InvalidParameter.CONTENT_LENGTH_NOT_MATCH_OVERWRITE_REQ_WRITER, getClass().getSimpleName());
                }
                ThreadUtil.checkIfInterrupted();
                this.mBodyAfterFileContents.writeTo(outputStream);
            } catch (IOException e) {
                throw new CloudDriveException("Failed to build the post body", e);
            }
        } finally {
            Closer.closeQuietly(this.mRequest.getInputStream());
        }
    }
}
