package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyCallable;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.DownloadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.DownloadMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileDownloadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileTransferMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileUploadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.S3ProgressListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.S3ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferManagerUtils;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferProgressUpdatingListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadCallable;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadMonitor;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.util.Mimetypes;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
@Deprecated
/* loaded from: classes13.dex */
public class TransferManager {
    private static final String DEFAULT_DELIMITER = "/";
    private TransferManagerConfiguration configuration;
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private final ScheduledExecutorService timedThreadPool;
    private static final Log log = LogFactory.getLog(TransferManager.class);
    private static final String USER_AGENT = TransferManager.class.getName() + "/" + VersionInfoUtils.getVersion();
    private static final String USER_AGENT_MULTIPART = TransferManager.class.getName() + "_multipart/" + VersionInfoUtils.getVersion();
    private static final ThreadFactory daemonThreadFactory = new ThreadFactory() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager.3
        final AtomicInteger threadCount = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            int incrementAndGet = this.threadCount.incrementAndGet();
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("S3TransferManagerTimedThread-" + incrementAndGet);
            return thread;
        }
    };

    public TransferManager() {
        this(new AmazonS3Client(new DefaultAWSCredentialsProviderChain()));
    }

    public static <X extends AmazonWebServiceRequest> X appendMultipartUserAgent(X x) {
        x.getRequestClientOptions().appendUserAgent(USER_AGENT_MULTIPART);
        return x;
    }

    public static <X extends AmazonWebServiceRequest> X appendSingleObjectUserAgent(X x) {
        x.getRequestClientOptions().appendUserAgent(USER_AGENT);
        return x;
    }

    private void assertParameterNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    private Download doDownload(GetObjectRequest getObjectRequest, File file, TransferStateChangeListener transferStateChangeListener, S3ProgressListener s3ProgressListener, boolean z) {
        long j;
        appendSingleObjectUserAgent(getObjectRequest);
        String str = "Downloading from " + getObjectRequest.getBucketName() + "/" + getObjectRequest.getKey();
        TransferProgress transferProgress = new TransferProgress();
        S3ProgressListenerChain s3ProgressListenerChain = new S3ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress), getObjectRequest.getGeneralProgressListener(), s3ProgressListener);
        getObjectRequest.setGeneralProgressListener(new ProgressListenerChain(new ProgressListenerChain.ProgressEventFilter() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager.1
            @Override // com.amazonaws.event.ProgressListenerChain.ProgressEventFilter
            public ProgressEvent filter(ProgressEvent progressEvent) {
                if (progressEvent.getEventCode() == 4) {
                    progressEvent.setEventCode(0);
                }
                return progressEvent;
            }
        }, s3ProgressListenerChain));
        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest(getObjectRequest.getBucketName(), getObjectRequest.getKey());
        if (getObjectRequest.getSSECustomerKey() != null) {
            getObjectMetadataRequest.setSSECustomerKey(getObjectRequest.getSSECustomerKey());
        }
        ObjectMetadata objectMetadata = this.s3.getObjectMetadata(getObjectMetadataRequest);
        DownloadImpl downloadImpl = new DownloadImpl(str, transferProgress, s3ProgressListenerChain, null, transferStateChangeListener, getObjectRequest, file);
        long contentLength = objectMetadata.getContentLength() - 1;
        if (getObjectRequest.getRange() == null || getObjectRequest.getRange().length != 2) {
            j = 0;
        } else {
            j = getObjectRequest.getRange()[0];
            contentLength = getObjectRequest.getRange()[1];
        }
        long j2 = (contentLength - j) + 1;
        transferProgress.setTotalBytesToTransfer(j2);
        if (z && file.exists()) {
            long length = file.length();
            long j3 = j + length;
            getObjectRequest.setRange(j3, contentLength);
            transferProgress.updateProgress(Math.min(length, j2));
            j2 = (contentLength - j3) + 1;
        }
        if (j2 >= 0) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            downloadImpl.setMonitor(new DownloadMonitor(downloadImpl, submitDownloadTask(getObjectRequest, file, z, countDownLatch, downloadImpl)));
            countDownLatch.countDown();
            return downloadImpl;
        }
        throw new IllegalArgumentException("Unable to determine the range for download operation.");
    }

    private Upload doUpload(PutObjectRequest putObjectRequest, TransferStateChangeListener transferStateChangeListener, S3ProgressListener s3ProgressListener, PersistableUpload persistableUpload) throws AmazonServiceException, AmazonClientException {
        appendSingleObjectUserAgent(putObjectRequest);
        String multipartUploadId = persistableUpload != null ? persistableUpload.getMultipartUploadId() : null;
        if (putObjectRequest.getMetadata() == null) {
            putObjectRequest.setMetadata(new ObjectMetadata());
        }
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        File requestFile = TransferManagerUtils.getRequestFile(putObjectRequest);
        if (requestFile != null) {
            metadata.setContentLength(requestFile.length());
            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(requestFile));
            }
        } else if (multipartUploadId != null) {
            throw new IllegalArgumentException("Unable to resume the upload. No file specified.");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Uploading to ");
        outline107.append(putObjectRequest.getBucketName());
        outline107.append("/");
        outline107.append(putObjectRequest.getKey());
        String sb = outline107.toString();
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(TransferManagerUtils.getContentLength(putObjectRequest));
        S3ProgressListenerChain s3ProgressListenerChain = new S3ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress), putObjectRequest.getGeneralProgressListener(), s3ProgressListener);
        putObjectRequest.setGeneralProgressListener(s3ProgressListenerChain);
        UploadImpl uploadImpl = new UploadImpl(sb, transferProgress, s3ProgressListenerChain, transferStateChangeListener);
        UploadMonitor uploadMonitor = new UploadMonitor(this, uploadImpl, this.threadPool, new UploadCallable(this, this.threadPool, uploadImpl, putObjectRequest, s3ProgressListenerChain, multipartUploadId, transferProgress), putObjectRequest, s3ProgressListenerChain);
        uploadMonitor.setTimedThreadPool(this.timedThreadPool);
        uploadImpl.setMonitor(uploadMonitor);
        return uploadImpl;
    }

    private void listFiles(File file, List<File> list, boolean z) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (!file2.isDirectory()) {
                    list.add(file2);
                } else if (z) {
                    listFiles(file2, list, z);
                }
            }
        }
    }

    private void shutdown() {
        this.threadPool.shutdown();
        this.timedThreadPool.shutdown();
    }

    private Future<?> submitDownloadTask(final GetObjectRequest getObjectRequest, final File file, final boolean z, final CountDownLatch countDownLatch, final DownloadImpl downloadImpl) {
        return this.threadPool.submit(new Callable<Object>() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager.2
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                try {
                    countDownLatch.await();
                    downloadImpl.setState(Transfer.TransferState.InProgress);
                    if (ServiceUtils.retryableDownloadS3ObjectToFile(file, new ServiceUtils.RetryableS3DownloadTask() { // from class: com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager.2.1
                        @Override // com.amazonaws.services.s3.internal.ServiceUtils.RetryableS3DownloadTask
                        public S3Object getS3ObjectStream() {
                            S3Object object = TransferManager.this.s3.getObject(getObjectRequest);
                            downloadImpl.setS3Object(object);
                            return object;
                        }

                        @Override // com.amazonaws.services.s3.internal.ServiceUtils.RetryableS3DownloadTask
                        public boolean needIntegrityCheck() {
                            return !ServiceUtils.skipMd5CheckPerRequest(getObjectRequest) && !(TransferManager.this.s3 instanceof AmazonS3EncryptionClient);
                        }
                    }, z) == null) {
                        downloadImpl.setState(Transfer.TransferState.Canceled);
                        downloadImpl.setMonitor(new DownloadMonitor(downloadImpl, null));
                        return downloadImpl;
                    }
                    downloadImpl.setState(Transfer.TransferState.Completed);
                    return true;
                } catch (Throwable th) {
                    if (downloadImpl.getState() != Transfer.TransferState.Canceled) {
                        downloadImpl.setState(Transfer.TransferState.Failed);
                    }
                    if (th instanceof Exception) {
                        throw th;
                    }
                    throw ((Error) th);
                }
            }
        });
    }

    public void abortMultipartUploads(String str, Date date) throws AmazonServiceException, AmazonClientException {
        MultipartUploadListing listMultipartUploads = this.s3.listMultipartUploads((ListMultipartUploadsRequest) appendSingleObjectUserAgent(new ListMultipartUploadsRequest(str)));
        do {
            for (MultipartUpload multipartUpload : listMultipartUploads.getMultipartUploads()) {
                if (multipartUpload.getInitiated().compareTo(date) < 0) {
                    this.s3.abortMultipartUpload((AbortMultipartUploadRequest) appendSingleObjectUserAgent(new AbortMultipartUploadRequest(str, multipartUpload.getKey(), multipartUpload.getUploadId())));
                }
            }
            listMultipartUploads = this.s3.listMultipartUploads((ListMultipartUploadsRequest) appendSingleObjectUserAgent(new ListMultipartUploadsRequest(str).withUploadIdMarker(listMultipartUploads.getNextUploadIdMarker()).withKeyMarker(listMultipartUploads.getNextKeyMarker())));
        } while (listMultipartUploads.isTruncated());
    }

    public Copy copy(String str, String str2, String str3, String str4) throws AmazonServiceException, AmazonClientException {
        return copy(new CopyObjectRequest(str, str2, str3, str4));
    }

    public Download download(String str, String str2, File file) {
        return download(new GetObjectRequest(str, str2), file);
    }

    public MultipleFileDownload downloadDirectory(String str, String str2, File file) {
        if (str2 == null) {
            str2 = "";
        }
        String str3 = str2;
        LinkedList<S3ObjectSummary> linkedList = new LinkedList();
        Stack stack = new Stack();
        stack.add(str3);
        long j = 0;
        do {
            String str4 = (String) stack.pop();
            ObjectListing objectListing = null;
            do {
                if (objectListing == null) {
                    objectListing = this.s3.listObjects(new ListObjectsRequest().withBucketName(str).withDelimiter("/").withPrefix(str4));
                } else {
                    objectListing = this.s3.listNextBatchOfObjects(objectListing);
                }
                for (S3ObjectSummary s3ObjectSummary : objectListing.getObjectSummaries()) {
                    if (!s3ObjectSummary.getKey().equals(str4)) {
                        List<String> commonPrefixes = objectListing.getCommonPrefixes();
                        if (!commonPrefixes.contains(s3ObjectSummary.getKey() + "/")) {
                            linkedList.add(s3ObjectSummary);
                            j += s3ObjectSummary.getSize();
                        }
                    }
                    Log log2 = log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Skipping download for object ");
                    outline107.append(s3ObjectSummary.getKey());
                    outline107.append(" since it is also a virtual directory");
                    log2.debug(outline107.toString());
                }
                stack.addAll(objectListing.getCommonPrefixes());
            } while (objectListing.isTruncated());
        } while (!stack.isEmpty());
        ProgressListenerChain progressListenerChain = new ProgressListenerChain(new ProgressListener[0]);
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(j);
        MultipleFileTransferProgressUpdatingListener multipleFileTransferProgressUpdatingListener = new MultipleFileTransferProgressUpdatingListener(transferProgress, progressListenerChain);
        ArrayList arrayList = new ArrayList();
        MultipleFileDownloadImpl multipleFileDownloadImpl = new MultipleFileDownloadImpl(GeneratedOutlineSupport1.outline76("Downloading from ", str, "/", str3), transferProgress, progressListenerChain, str3, str, arrayList);
        multipleFileDownloadImpl.setMonitor(new MultipleFileTransferMonitor(multipleFileDownloadImpl, arrayList));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MultipleFileTransferStateChangeListener multipleFileTransferStateChangeListener = new MultipleFileTransferStateChangeListener(countDownLatch, multipleFileDownloadImpl);
        for (S3ObjectSummary s3ObjectSummary2 : linkedList) {
            File file2 = new File(file, s3ObjectSummary2.getKey());
            File parentFile = file2.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Couldn't create parent directories for ");
                outline1072.append(file2.getAbsolutePath());
                throw new RuntimeException(outline1072.toString());
            }
            arrayList.add((DownloadImpl) doDownload(new GetObjectRequest(s3ObjectSummary2.getBucketName(), s3ObjectSummary2.getKey()).mo6730withGeneralProgressListener((ProgressListener) multipleFileTransferProgressUpdatingListener), file2, multipleFileTransferStateChangeListener, null, false));
        }
        if (arrayList.isEmpty()) {
            multipleFileDownloadImpl.setState(Transfer.TransferState.Completed);
            return multipleFileDownloadImpl;
        }
        countDownLatch.countDown();
        return multipleFileDownloadImpl;
    }

    protected void finalize() throws Throwable {
        shutdown();
    }

    public AmazonS3 getAmazonS3Client() {
        return this.s3;
    }

    public TransferManagerConfiguration getConfiguration() {
        return this.configuration;
    }

    public Download resumeDownload(PersistableDownload persistableDownload) {
        assertParameterNotNull(persistableDownload, "PausedDownload is mandatory to resume a download.");
        GetObjectRequest getObjectRequest = new GetObjectRequest(persistableDownload.getBucketName(), persistableDownload.getKey(), persistableDownload.getVersionId());
        if (persistableDownload.getRange() != null && persistableDownload.getRange().length == 2) {
            long[] range = persistableDownload.getRange();
            getObjectRequest.setRange(range[0], range[1]);
        }
        getObjectRequest.setRequesterPays(persistableDownload.isRequesterPays());
        getObjectRequest.setResponseHeaders(persistableDownload.getResponseHeaders());
        return doDownload(getObjectRequest, new File(persistableDownload.getFile()), null, null, true);
    }

    public Upload resumeUpload(PersistableUpload persistableUpload) {
        assertParameterNotNull(persistableUpload, "PauseUpload is mandatory to resume a upload.");
        this.configuration.setMinimumUploadPartSize(persistableUpload.getPartSize());
        this.configuration.setMultipartUploadThreshold(persistableUpload.getMutlipartUploadThreshold());
        return doUpload(new PutObjectRequest(persistableUpload.getBucketName(), persistableUpload.getKey(), new File(persistableUpload.getFile())), null, null, persistableUpload);
    }

    public void setConfiguration(TransferManagerConfiguration transferManagerConfiguration) {
        this.configuration = transferManagerConfiguration;
    }

    public void shutdownNow() {
        shutdownNow(true);
    }

    public Upload upload(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(str, str2, inputStream, objectMetadata));
    }

    public MultipleFileUpload uploadDirectory(String str, String str2, File file, boolean z) {
        return uploadDirectory(str, str2, file, z, null);
    }

    public MultipleFileUpload uploadFileList(String str, String str2, File file, List<File> list) {
        return uploadFileList(str, str2, file, list, null);
    }

    public TransferManager(AWSCredentialsProvider aWSCredentialsProvider) {
        this(new AmazonS3Client(aWSCredentialsProvider));
    }

    public Copy copy(CopyObjectRequest copyObjectRequest) {
        return copy(copyObjectRequest, null);
    }

    public Download download(GetObjectRequest getObjectRequest, File file) {
        return doDownload(getObjectRequest, file, null, null, false);
    }

    public void shutdownNow(boolean z) {
        this.threadPool.shutdownNow();
        this.timedThreadPool.shutdownNow();
        if (z) {
            AmazonS3 amazonS3 = this.s3;
            if (!(amazonS3 instanceof AmazonS3Client)) {
                return;
            }
            ((AmazonS3Client) amazonS3).shutdown();
        }
    }

    public Upload upload(String str, String str2, File file) throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(str, str2, file));
    }

    public MultipleFileUpload uploadDirectory(String str, String str2, File file, boolean z, ObjectMetadataProvider objectMetadataProvider) {
        if (file != null && file.exists() && file.isDirectory()) {
            LinkedList linkedList = new LinkedList();
            listFiles(file, linkedList, z);
            return uploadFileList(str, str2, file, linkedList, objectMetadataProvider);
        }
        throw new IllegalArgumentException("Must provide a directory to upload");
    }

    public MultipleFileUpload uploadFileList(String str, String str2, File file, List<File> list, ObjectMetadataProvider objectMetadataProvider) {
        String str3;
        String str4 = str2;
        ObjectMetadataProvider objectMetadataProvider2 = objectMetadataProvider;
        if (file != null && file.exists() && file.isDirectory()) {
            if (str4 == null || str2.length() == 0) {
                str4 = "";
            } else if (!str4.endsWith("/")) {
                str4 = GeneratedOutlineSupport1.outline72(str4, "/");
            }
            ProgressListenerChain progressListenerChain = new ProgressListenerChain(new ProgressListener[0]);
            TransferProgress transferProgress = new TransferProgress();
            MultipleFileTransferProgressUpdatingListener multipleFileTransferProgressUpdatingListener = new MultipleFileTransferProgressUpdatingListener(transferProgress, progressListenerChain);
            LinkedList linkedList = new LinkedList();
            MultipleFileUploadImpl multipleFileUploadImpl = new MultipleFileUploadImpl("Uploading etc", transferProgress, progressListenerChain, str4, str, linkedList);
            multipleFileUploadImpl.setMonitor(new MultipleFileTransferMonitor(multipleFileUploadImpl, linkedList));
            CountDownLatch countDownLatch = new CountDownLatch(1);
            MultipleFileTransferStateChangeListener multipleFileTransferStateChangeListener = new MultipleFileTransferStateChangeListener(countDownLatch, multipleFileUploadImpl);
            if (list != null && !list.isEmpty()) {
                int length = file.getAbsolutePath().length();
                if (!file.getAbsolutePath().endsWith(File.separator)) {
                    length++;
                }
                long j = 0;
                for (File file2 : list) {
                    if (file2.isFile()) {
                        long length2 = file2.length() + j;
                        String replaceAll = file2.getAbsolutePath().substring(length).replaceAll("\\\\", "/");
                        ObjectMetadata objectMetadata = new ObjectMetadata();
                        if (objectMetadataProvider2 != null) {
                            objectMetadataProvider2.provideObjectMetadata(file2, objectMetadata);
                        }
                        str3 = str4;
                        linkedList.add((UploadImpl) doUpload(new PutObjectRequest(str, GeneratedOutlineSupport1.outline72(str4, replaceAll), file2).mo6733withMetadata(objectMetadata).mo6730withGeneralProgressListener((ProgressListener) multipleFileTransferProgressUpdatingListener), multipleFileTransferStateChangeListener, null, null));
                        j = length2;
                    } else {
                        str3 = str4;
                    }
                    str4 = str3;
                    objectMetadataProvider2 = objectMetadataProvider;
                }
                transferProgress.setTotalBytesToTransfer(j);
            } else {
                multipleFileUploadImpl.setState(Transfer.TransferState.Completed);
            }
            countDownLatch.countDown();
            return multipleFileUploadImpl;
        }
        throw new IllegalArgumentException("Must provide a common base directory for uploaded files");
    }

    public TransferManager(AWSCredentials aWSCredentials) {
        this(new AmazonS3Client(aWSCredentials));
    }

    public Copy copy(CopyObjectRequest copyObjectRequest, TransferStateChangeListener transferStateChangeListener) throws AmazonServiceException, AmazonClientException {
        appendSingleObjectUserAgent(copyObjectRequest);
        assertParameterNotNull(copyObjectRequest.getSourceBucketName(), "The source bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getSourceKey(), "The source object key must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getDestinationBucketName(), "The destination bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getDestinationKey(), "The destination object key must be specified when a copy request is initiated.");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Copying object from ");
        outline107.append(copyObjectRequest.getSourceBucketName());
        outline107.append("/");
        outline107.append(copyObjectRequest.getSourceKey());
        outline107.append(" to ");
        outline107.append(copyObjectRequest.getDestinationBucketName());
        outline107.append("/");
        outline107.append(copyObjectRequest.getDestinationKey());
        String sb = outline107.toString();
        ObjectMetadata objectMetadata = this.s3.getObjectMetadata(new GetObjectMetadataRequest(copyObjectRequest.getSourceBucketName(), copyObjectRequest.getSourceKey()).withSSECustomerKey(copyObjectRequest.getSourceSSECustomerKey()));
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(objectMetadata.getContentLength());
        ProgressListenerChain progressListenerChain = new ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress));
        CopyImpl copyImpl = new CopyImpl(sb, transferProgress, progressListenerChain, transferStateChangeListener);
        CopyMonitor copyMonitor = new CopyMonitor(this, copyImpl, this.threadPool, new CopyCallable(this, this.threadPool, copyImpl, copyObjectRequest, objectMetadata, progressListenerChain), copyObjectRequest, progressListenerChain);
        copyMonitor.setTimedThreadPool(this.timedThreadPool);
        copyImpl.setMonitor(copyMonitor);
        return copyImpl;
    }

    public Download download(GetObjectRequest getObjectRequest, File file, S3ProgressListener s3ProgressListener) {
        return doDownload(getObjectRequest, file, null, s3ProgressListener, false);
    }

    public Upload upload(PutObjectRequest putObjectRequest) throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, null, null);
    }

    public TransferManager(AmazonS3 amazonS3) {
        this(amazonS3, TransferManagerUtils.createDefaultExecutorService());
    }

    public Upload upload(PutObjectRequest putObjectRequest, S3ProgressListener s3ProgressListener) throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, s3ProgressListener, null);
    }

    public TransferManager(AmazonS3 amazonS3, ExecutorService executorService) {
        this.timedThreadPool = new ScheduledThreadPoolExecutor(1, daemonThreadFactory);
        this.s3 = amazonS3;
        this.threadPool = executorService;
        this.configuration = new TransferManagerConfiguration();
    }
}
