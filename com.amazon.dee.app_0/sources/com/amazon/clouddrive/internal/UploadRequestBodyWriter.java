package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.ActionRequiredException;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.exceptions.RebuildRequestException;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.MultiPartPostBodyWriterHelper;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.StreamUtil;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.amazon.clouddrive.model.UploadFileRequest;
import com.amazon.clouddrive.model.serializer.UploadRequestSerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class UploadRequestBodyWriter extends RequestPropertyWriterImpl implements PostRequestWriter {
    private ByteArrayOutputStream mBodyAfterFileContents;
    private ByteArrayOutputStream mBodyBeforeFileContents;
    private final ProgressListener mProgressListener;
    private final UploadFileRequest mRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadRequestBodyWriter(UploadFileRequest uploadFileRequest, ProgressListener progressListener) {
        this.mRequest = uploadFileRequest;
        this.mProgressListener = progressListener;
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriterImpl, com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        RequestAssertUtils.assertNotNull(this.mRequest.getName(), "The file name is required when posting to Cloud Drive.");
        boolean z = this.mRequest.getInputStream() != null;
        try {
            setRequestProperty("Content-Type", MultiPartPostBodyWriterHelper.HEADER_CONTENT_TYPE);
            MultiPartPostBodyWriterHelper.Builder builder = new MultiPartPostBodyWriterHelper.Builder();
            builder.withName(this.mRequest.getName()).addMetadata(this.mRequest, UploadRequestSerializer.INSTANCE);
            if (z) {
                builder.addContent();
            }
            MultiPartPostBodyWriterHelper build = builder.build();
            this.mBodyBeforeFileContents = build.getBodyBeforeFileContents();
            this.mBodyAfterFileContents = build.getBodyAfterFileContents();
            setStreamingMode(httpURLConnection, this.mRequest.useChunkedStreaming(), this.mRequest.getChunkSize(), this.mRequest.getContentLength() + this.mBodyBeforeFileContents.size() + this.mBodyAfterFileContents.size());
            super.writeHeaders(httpURLConnection);
        } catch (IOException e) {
            throw new ActionRequiredException("Failed to write request to the service.", e);
        }
    }

    @Override // com.amazon.clouddrive.internal.PostRequestWriter
    public void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException {
        RequestAssertUtils.assertNotNull(this.mBodyBeforeFileContents, "The writeHeaders method was not called or did not successfully complete.");
        RequestAssertUtils.assertNotNull(this.mBodyAfterFileContents, "The writeHeaders method was not called or did not successfully complete.");
        try {
            try {
                try {
                    ThreadUtil.checkIfInterrupted();
                    this.mBodyBeforeFileContents.writeTo(outputStream);
                    if (this.mRequest.getInputStream() != null) {
                        long copyInputStreamToOutputStream = StreamUtil.copyInputStreamToOutputStream(new ProgressInputStream(this.mRequest.getInputStream(), this.mRequest.getContentLength(), this.mProgressListener), outputStream, this.mRequest.getBlockSize(), this.mRequest.getContentLength());
                        if (copyInputStreamToOutputStream != this.mRequest.getContentLength()) {
                            throw new InvalidParameter("InputStream [" + copyInputStreamToOutputStream + "] was not the same as the declared contentLength [" + this.mRequest.getContentLength() + "].", InvalidParameter.CONTENT_LENGTH_NOT_MATCH_UPLOAD_REQ_WRITER, getClass().getSimpleName());
                        }
                    }
                    ThreadUtil.checkIfInterrupted();
                    this.mBodyAfterFileContents.writeTo(outputStream);
                } catch (IOException e) {
                    throw new RebuildRequestException("Failed to write request to the service.", e);
                }
            } catch (InterruptedIOException unused) {
                throw new InterruptedException("Interrupted while writing to the post body.");
            }
        } finally {
            Closer.closeQuietly(this.mRequest.getInputStream());
        }
    }
}
