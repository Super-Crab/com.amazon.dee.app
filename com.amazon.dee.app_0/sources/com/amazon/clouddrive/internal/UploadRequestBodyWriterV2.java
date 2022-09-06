package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.exceptions.RebuildRequestException;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.StreamUtil;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.amazon.clouddrive.model.UploadFileRequest;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.UUID;
/* loaded from: classes11.dex */
class UploadRequestBodyWriterV2 extends RequestPropertyWriterImpl implements PostRequestWriter {
    static final String HEADER_MD5 = "x-amzn-file-md5";
    static final String HEADER_REQUEST_ID = "x-amzn-RequestId";
    static final String HEADER_TRACE_ID = "x-amzn-Trace-Id";
    private final ProgressListener mProgressListener;
    private final UploadFileRequest mRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadRequestBodyWriterV2(UploadFileRequest uploadFileRequest, ProgressListener progressListener) {
        this.mRequest = uploadFileRequest;
        this.mProgressListener = progressListener;
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriterImpl, com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        setRequestProperty("Content-Type", MultiPartPostBodyWriterHelper.HEADER_CONTENT_TYPE);
        String uuid = UUID.randomUUID().toString();
        httpURLConnection.setRequestProperty("x-amzn-file-md5", this.mRequest.getMD5());
        httpURLConnection.setRequestProperty(HEADER_REQUEST_ID, uuid);
        httpURLConnection.setRequestProperty(HEADER_TRACE_ID, uuid);
        setStreamingMode(httpURLConnection, this.mRequest.useChunkedStreaming(), this.mRequest.getChunkSize(), this.mRequest.getContentLength());
        super.writeHeaders(httpURLConnection);
    }

    @Override // com.amazon.clouddrive.internal.PostRequestWriter
    public void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException {
        try {
            try {
                ThreadUtil.checkIfInterrupted();
                if (this.mRequest.getInputStream() != null) {
                    long copyInputStreamToOutputStream = StreamUtil.copyInputStreamToOutputStream(new ProgressInputStream(this.mRequest.getInputStream(), this.mRequest.getContentLength(), this.mProgressListener), outputStream, this.mRequest.getBlockSize(), this.mRequest.getContentLength());
                    if (copyInputStreamToOutputStream != this.mRequest.getContentLength()) {
                        throw new InvalidParameter("InputStream [" + copyInputStreamToOutputStream + "] was not the same as the declared contentLength [" + this.mRequest.getContentLength() + "].", InvalidParameter.CONTENT_LENGTH_NOT_MATCH_UPLOAD_REQ_WRITER, getClass().getSimpleName());
                    }
                }
                ThreadUtil.checkIfInterrupted();
            } catch (InterruptedIOException unused) {
                throw new InterruptedException("Interrupted while writing to the post body.");
            } catch (IOException e) {
                throw new RebuildRequestException("Failed to write request to the service.", e);
            }
        } finally {
            Closer.closeQuietly(this.mRequest.getInputStream());
        }
    }
}
