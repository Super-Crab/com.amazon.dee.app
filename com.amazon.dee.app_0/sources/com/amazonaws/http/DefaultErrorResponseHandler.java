package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.XpathUtils;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/* loaded from: classes13.dex */
public class DefaultErrorResponseHandler implements HttpResponseHandler<AmazonServiceException> {
    private static final Log log = LogFactory.getLog(DefaultErrorResponseHandler.class);
    private List<Unmarshaller<AmazonServiceException, Node>> unmarshallerList;

    public DefaultErrorResponseHandler(List<Unmarshaller<AmazonServiceException, Node>> list) {
        this.unmarshallerList = list;
    }

    private AmazonServiceException newAmazonServiceException(String str, HttpResponse httpResponse, Exception exc) {
        AmazonServiceException amazonServiceException = new AmazonServiceException(str, exc);
        int statusCode = httpResponse.getStatusCode();
        amazonServiceException.setErrorCode(statusCode + " " + httpResponse.getStatusText());
        amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Unknown);
        amazonServiceException.setStatusCode(statusCode);
        return amazonServiceException;
    }

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazonaws.http.HttpResponseHandler
    /* renamed from: handle */
    public AmazonServiceException mo6743handle(HttpResponse httpResponse) throws Exception {
        Object obj;
        try {
            try {
                Document documentFrom = XpathUtils.documentFrom(IOUtils.toString(httpResponse.getContent()));
                for (Unmarshaller<AmazonServiceException, Node> unmarshaller : this.unmarshallerList) {
                    AmazonServiceException unmarshall = unmarshaller.unmarshall(documentFrom);
                    if (unmarshall != null) {
                        unmarshall.setStatusCode(httpResponse.getStatusCode());
                        return unmarshall;
                    }
                }
                throw new AmazonClientException("Unable to unmarshall error response from service");
            } catch (Exception e) {
                return newAmazonServiceException(String.format("Unable to unmarshall error response (%s)", obj), httpResponse, e);
            }
        } catch (IOException e2) {
            if (log.isDebugEnabled()) {
                log.debug("Failed in reading the error response", e2);
            }
            return newAmazonServiceException("Unable to unmarshall error response", httpResponse, e2);
        }
    }
}
