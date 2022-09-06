package com.amazonaws.http;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.internal.CRC32MismatchException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.VoidJsonUnmarshaller;
import com.amazonaws.util.CRC32ChecksumCalculatingInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonReader;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
/* loaded from: classes13.dex */
public class JsonResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {
    private static final Log log = LogFactory.getLog("com.amazonaws.request");
    public boolean needsConnectionLeftOpen = false;
    private Unmarshaller<T, JsonUnmarshallerContext> responseUnmarshaller;

    public JsonResponseHandler(Unmarshaller<T, JsonUnmarshallerContext> unmarshaller) {
        this.responseUnmarshaller = unmarshaller;
        if (this.responseUnmarshaller == null) {
            this.responseUnmarshaller = new VoidJsonUnmarshaller();
        }
    }

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return this.needsConnectionLeftOpen;
    }

    @Deprecated
    protected void registerAdditionalMetadataExpressions(JsonUnmarshallerContext jsonUnmarshallerContext) {
    }

    @Override // com.amazonaws.http.HttpResponseHandler
    /* renamed from: handle */
    public AmazonWebServiceResponse<T> mo6743handle(HttpResponse httpResponse) throws Exception {
        CRC32ChecksumCalculatingInputStream cRC32ChecksumCalculatingInputStream;
        log.trace("Parsing service response JSON");
        String str = httpResponse.getHeaders().get("x-amz-crc32");
        InputStream rawContent = httpResponse.getRawContent();
        if (rawContent == null) {
            rawContent = new ByteArrayInputStream("{}".getBytes(StringUtils.UTF8));
        }
        Log log2 = log;
        log2.debug("CRC32Checksum = " + str);
        Log log3 = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("content encoding = ");
        outline107.append(httpResponse.getHeaders().get("Content-Encoding"));
        log3.debug(outline107.toString());
        if (str != null) {
            cRC32ChecksumCalculatingInputStream = new CRC32ChecksumCalculatingInputStream(rawContent);
            rawContent = "gzip".equals(httpResponse.getHeaders().get("Content-Encoding")) ? new GZIPInputStream(cRC32ChecksumCalculatingInputStream) : cRC32ChecksumCalculatingInputStream;
        } else {
            cRC32ChecksumCalculatingInputStream = null;
        }
        AwsJsonReader jsonReader = JsonUtils.getJsonReader(new InputStreamReader(rawContent, StringUtils.UTF8));
        try {
            AmazonWebServiceResponse<T> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
            T unmarshall = this.responseUnmarshaller.unmarshall(new JsonUnmarshallerContext(jsonReader, httpResponse));
            if (str != null) {
                if (cRC32ChecksumCalculatingInputStream.getCRC32Checksum() != Long.parseLong(str)) {
                    throw new CRC32MismatchException("Client calculated crc32 checksum didn't match that calculated by server side");
                }
            }
            amazonWebServiceResponse.setResult(unmarshall);
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseMetadata.AWS_REQUEST_ID, httpResponse.getHeaders().get("x-amzn-RequestId"));
            amazonWebServiceResponse.setResponseMetadata(new ResponseMetadata(hashMap));
            log.trace("Done parsing service response");
            return amazonWebServiceResponse;
        } finally {
            if (!this.needsConnectionLeftOpen) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    log.warn("Error closing json parser", e);
                }
            }
        }
    }
}
