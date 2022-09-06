package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.ActionRequiredException;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.RebuildRequestException;
import com.amazon.clouddrive.exceptions.RetryException;
import com.amazon.clouddrive.exceptions.SystemFault;
import com.amazon.clouddrive.handlers.ProgressListener;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.internal.utils.StreamUtil;
import com.amazon.clouddrive.metrics.MetricListener;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.URL;
import okhttp3.Response;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class DownloadFileOperation extends AbstractCloudDriveOperation<Void> {
    private final int mBlockSize;
    private final OutputStream mOutputStream;
    private final ProgressListener mProgressListener;
    private final RequestPathGenerator.RequestPath mRequestPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadFileOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, RequestPathGenerator.RequestPath requestPath, String str, MetricListener metricListener, ProgressListener progressListener, Class<?> cls, OutputStream outputStream, int i) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str, metricListener, cls);
        this.mRequestPath = requestPath;
        this.mProgressListener = progressListener;
        this.mOutputStream = outputStream;
        this.mBlockSize = i;
    }

    @Override // com.amazon.clouddrive.internal.AbstractCloudDriveOperation
    /* renamed from: retryCall  reason: avoid collision after fix types in other method */
    public final Void mo3157retryCall() throws CloudDriveException, InterruptedException {
        Response response;
        ProgressInputStream progressInputStream;
        try {
            try {
                response = this.mClient.newCall(setUpRequest(new URL(this.mRequestPath.getPath())).build()).execute();
                try {
                    assertSuccessResponseCode(response);
                    long j = -1;
                    String header = response.header("Content-Length");
                    if (header != null) {
                        try {
                            j = Long.parseLong(header);
                        } catch (NumberFormatException e) {
                            AmazonCloudDriveLog.w("Exception thrown while parsing the \"Content-Length\" header.", e);
                        }
                    }
                    progressInputStream = new ProgressInputStream(response.body().byteStream(), j, this.mProgressListener);
                    try {
                        long copyInputStreamToOutputStream = StreamUtil.copyInputStreamToOutputStream(progressInputStream, this.mOutputStream, this.mBlockSize, j);
                        if (copyInputStreamToOutputStream == j) {
                            Closer.closeQuietly(progressInputStream);
                            Closer.closeQuietly(this.mOutputStream);
                            response.close();
                            return null;
                        }
                        throw new SystemFault("Expected Content-Length [" + j + "] did not match bytes read [" + copyInputStreamToOutputStream + "]");
                    } catch (RetryException e2) {
                        e = e2;
                        throw new RebuildRequestException("Request requires new OutputStream instance to continue.", e);
                    } catch (InterruptedIOException unused) {
                        throw new InterruptedException();
                    } catch (IOException e3) {
                        e = e3;
                        throw new ActionRequiredException("Failure in creating a connection", e);
                    } catch (Throwable th) {
                        th = th;
                        Closer.closeQuietly(progressInputStream);
                        Closer.closeQuietly(this.mOutputStream);
                        if (response != null) {
                            response.close();
                        }
                        throw th;
                    }
                } catch (RetryException e4) {
                    e = e4;
                } catch (InterruptedIOException unused2) {
                } catch (IOException e5) {
                    e = e5;
                } catch (Throwable th2) {
                    th = th2;
                    progressInputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
            }
        } catch (RetryException e6) {
            e = e6;
        } catch (InterruptedIOException unused3) {
        } catch (IOException e7) {
            e = e7;
        } catch (Throwable th4) {
            th = th4;
            response = null;
            progressInputStream = null;
        }
    }
}
