package com.amazonaws.services.s3.internal;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.SSEAlgorithm;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.Md5Utils;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.net.ssl.SSLProtocolException;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes13.dex */
public class ServiceUtils {
    public static final boolean APPEND_MODE = true;
    private static final int DEAFAULT_BYTE_SIZE = 10240;
    public static final boolean OVERWRITE_MODE = false;
    private static final Log log = LogFactory.getLog(ServiceUtils.class);
    @Deprecated
    protected static final DateUtils DATE_UTILS = new DateUtils();

    /* loaded from: classes13.dex */
    public interface RetryableS3DownloadTask {
        S3Object getS3ObjectStream();

        boolean needIntegrityCheck();
    }

    public static URL convertRequestToUrl(Request<?> request) {
        return convertRequestToUrl(request, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    public static void downloadObjectToFile(S3Object s3Object, File file, boolean z, boolean z2) {
        ?? r2;
        byte[] bArr;
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        byte[] bArr2 = null;
        try {
            try {
                r2 = new BufferedOutputStream(new FileOutputStream(file, z2));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            r2 = bArr2;
        }
        try {
            byte[] bArr3 = new byte[10240];
            while (true) {
                int read = s3Object.getObjectContent().read(bArr3);
                if (read > -1) {
                    r2.write(bArr3, 0, read);
                } else {
                    try {
                        break;
                    } catch (Exception unused) {
                        log.debug("Caught exception. Ignoring.");
                    }
                }
            }
            r2.close();
            try {
                s3Object.getObjectContent().close();
            } catch (Exception unused2) {
                log.debug("Caught exception. Ignoring.");
            }
            try {
                if (!isMultipartUploadETag(s3Object.getObjectMetadata().getETag())) {
                    bArr = Md5Utils.computeMD5Hash(new FileInputStream(file));
                    try {
                        bArr2 = BinaryUtils.fromHex(s3Object.getObjectMetadata().getETag());
                    } catch (Exception e2) {
                        e = e2;
                        Log log2 = log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to calculate MD5 hash to validate download: ");
                        outline107.append(e.getMessage());
                        log2.warn(outline107.toString(), e);
                        if (!z) {
                            return;
                        }
                        return;
                    }
                } else {
                    bArr = null;
                }
            } catch (Exception e3) {
                e = e3;
                bArr = null;
            }
            if (!z || bArr == null || bArr2 == null || Arrays.equals(bArr, bArr2)) {
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to verify integrity of data download.  Client calculated content hash didn't match hash calculated by Amazon S3.  The data stored in '");
            outline1072.append(file.getAbsolutePath());
            outline1072.append("' may be corrupt.");
            throw new AmazonClientException(outline1072.toString());
        } catch (IOException e4) {
            e = e4;
            bArr2 = r2;
            s3Object.getObjectContent().abort();
            throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
        } catch (Throwable th2) {
            th = th2;
            try {
                r2.close();
            } catch (Exception unused3) {
                log.debug("Caught exception. Ignoring.");
            }
            try {
                s3Object.getObjectContent().close();
            } catch (Exception unused4) {
                log.debug("Caught exception. Ignoring.");
            }
            throw th;
        }
    }

    public static String formatIso8601Date(Date date) {
        return DateUtils.formatISO8601Date(date);
    }

    public static String formatRfc822Date(Date date) {
        return DateUtils.formatRFC822Date(date);
    }

    public static boolean isMultipartUploadETag(String str) {
        return str.contains(ProcessIdUtil.DEFAULT_PROCESSID);
    }

    public static String join(List<String> list) {
        String str = "";
        boolean z = true;
        for (String str2 : list) {
            if (!z) {
                str = GeneratedOutlineSupport1.outline72(str, ", ");
            }
            str = GeneratedOutlineSupport1.outline72(str, str2);
            z = false;
        }
        return str;
    }

    public static Date parseIso8601Date(String str) {
        return DateUtils.parseISO8601Date(str);
    }

    public static Date parseRfc822Date(String str) {
        return DateUtils.parseRFC822Date(str);
    }

    public static String removeQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            trim = trim.substring(1);
        }
        return trim.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) ? GeneratedOutlineSupport1.outline51(trim, 1, 0) : trim;
    }

    public static S3Object retryableDownloadS3ObjectToFile(File file, RetryableS3DownloadTask retryableS3DownloadTask, boolean z) {
        S3Object s3ObjectStream;
        boolean z2;
        boolean z3 = false;
        do {
            s3ObjectStream = retryableS3DownloadTask.getS3ObjectStream();
            if (s3ObjectStream == null) {
                return null;
            }
            z2 = true;
            try {
                try {
                    downloadObjectToFile(s3ObjectStream, file, retryableS3DownloadTask.needIntegrityCheck(), z);
                    s3ObjectStream.getObjectContent().abort();
                    z2 = false;
                    continue;
                } catch (AmazonClientException e) {
                    if (e.isRetryable()) {
                        if ((e.getCause() instanceof SocketException) || (e.getCause() instanceof SSLProtocolException)) {
                            throw e;
                        }
                        if (!z3) {
                            log.info("Retry the download of object " + s3ObjectStream.getKey() + " (bucket " + s3ObjectStream.getBucketName() + ")", e);
                            s3ObjectStream.getObjectContent().abort();
                            z3 = true;
                            continue;
                        } else {
                            throw e;
                        }
                    } else {
                        throw e;
                    }
                }
            } catch (Throwable th) {
                s3ObjectStream.getObjectContent().abort();
                throw th;
            }
        } while (z2);
        return s3ObjectStream;
    }

    public static boolean skipMd5CheckPerRequest(AmazonWebServiceRequest amazonWebServiceRequest) {
        if (System.getProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation") != null) {
            return true;
        }
        if (amazonWebServiceRequest instanceof GetObjectRequest) {
            GetObjectRequest getObjectRequest = (GetObjectRequest) amazonWebServiceRequest;
            if (getObjectRequest.getRange() != null || getObjectRequest.getSSECustomerKey() != null) {
                return true;
            }
        } else if (!(amazonWebServiceRequest instanceof PutObjectRequest)) {
            return (amazonWebServiceRequest instanceof UploadPartRequest) && ((UploadPartRequest) amazonWebServiceRequest).getSSECustomerKey() != null;
        } else {
            PutObjectRequest putObjectRequest = (PutObjectRequest) amazonWebServiceRequest;
            ObjectMetadata metadata = putObjectRequest.getMetadata();
            return ((metadata == null || metadata.getSSEAlgorithm() == null) && putObjectRequest.getSSECustomerKey() == null) ? false : true;
        }
        return false;
    }

    public static boolean skipMd5CheckPerResponse(ObjectMetadata objectMetadata) {
        if (objectMetadata == null) {
            return false;
        }
        return objectMetadata.getSSECustomerAlgorithm() != null || SSEAlgorithm.KMS.toString().equals(objectMetadata.getSSEAlgorithm());
    }

    public static byte[] toByteArray(String str) {
        return str.getBytes(StringUtils.UTF8);
    }

    public static URL convertRequestToUrl(Request<?> request, boolean z) {
        String outline72;
        boolean z2 = true;
        String urlEncode = S3HttpUtils.urlEncode(request.getResourcePath(), true);
        if (z && urlEncode.startsWith("/")) {
            urlEncode = urlEncode.substring(1);
        }
        String str = request.getEndpoint() + ("/" + urlEncode).replaceAll("(?<=/)/", "%2F");
        for (String str2 : request.getParameters().keySet()) {
            if (z2) {
                outline72 = GeneratedOutlineSupport1.outline72(str, WebConstants.UriConstants.QUESTIONMARK_KEY);
                z2 = false;
            } else {
                outline72 = GeneratedOutlineSupport1.outline72(str, WebConstants.UriConstants.AMPERSAND_KEY);
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115(outline72, str2, Config.Compare.EQUAL_TO);
            outline115.append(S3HttpUtils.urlEncode(request.getParameters().get(str2), false));
            str = outline115.toString();
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to convert request to well formed URL: ");
            outline107.append(e.getMessage());
            throw new AmazonClientException(outline107.toString(), e);
        }
    }
}
