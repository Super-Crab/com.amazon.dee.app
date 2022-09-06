package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.ActionRequiredException;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.StreamUtil;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.amazon.clouddrive.model.ResumeFileRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
/* loaded from: classes11.dex */
class ResumeFileRequestWriter extends RequestPropertyWriterImpl implements PostRequestWriter {
    private final ProgressListener mProgressListener;
    private final ResumeFileRequest mRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResumeFileRequestWriter(ResumeFileRequest resumeFileRequest, ProgressListener progressListener) {
        this.mRequest = resumeFileRequest;
        this.mProgressListener = progressListener;
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriterImpl, com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        setRequestProperty("Content-Type", MultiPartPostBodyWriterHelper.HEADER_CONTENT_TYPE);
        setRequestProperty("Content-Range", String.format(RequestPropertyWriter.CONTENT_RANGE_FORMAT, Long.valueOf(this.mRequest.getResumeFromPosition()), Long.valueOf(this.mRequest.getContentLength() - 1), Long.valueOf(this.mRequest.getContentLength())));
        setStreamingMode(httpURLConnection, this.mRequest.useChunkedStreaming(), this.mRequest.getChunkSize(), this.mRequest.getContentLength() - this.mRequest.getResumeFromPosition());
        super.writeHeaders(httpURLConnection);
    }

    @Override // com.amazon.clouddrive.internal.PostRequestWriter
    public void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException {
        ThreadUtil.checkIfInterrupted();
        ProgressInputStream progressInputStream = new ProgressInputStream(this.mRequest.getInputStream(), this.mRequest.getContentLength(), this.mProgressListener);
        try {
            try {
                long skip = progressInputStream.skip(this.mRequest.getResumeFromPosition());
                ThreadUtil.checkIfInterrupted();
                if (skip + StreamUtil.copyInputStreamToOutputStream(progressInputStream, outputStream, this.mRequest.getBlockSize(), this.mRequest.getContentLength() - skip) == this.mRequest.getContentLength()) {
                    return;
                }
                throw new InvalidParameter("Bytes read from the request InputStream differed from the provided content length.", InvalidParameter.CONTENT_LENGTH_NOT_MATCH_RESUME_REQ_WRITER, getClass().getSimpleName());
            } catch (IOException e) {
                throw new ActionRequiredException("Failed to build the post body.", e);
            }
        } finally {
            Closer.closeQuietly(progressInputStream);
        }
    }
}
