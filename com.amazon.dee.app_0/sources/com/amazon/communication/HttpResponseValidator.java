package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;
/* loaded from: classes12.dex */
public class HttpResponseValidator {
    private static final DPLogger log = new DPLogger("TComm.HttpResponseValidator");

    private HttpResponseValidator() {
    }

    private static void changeStatusLine(HttpResponse httpResponse, int i, String str) {
        StatusLine statusLine = httpResponse.getStatusLine();
        BasicStatusLine basicStatusLine = new BasicStatusLine(statusLine.getProtocolVersion(), i, str);
        log.debug("changeStatusCode", "changing code and phrase", "statusLine", basicStatusLine, "newStatusLine", statusLine);
        httpResponse.setStatusLine(basicStatusLine);
    }

    public static boolean validateContentLength(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode / 100 != 2) {
            log.debug("validateContentLength", "Ignoring non-2xx status code", "statusCode", Integer.valueOf(statusCode));
            return true;
        }
        Header firstHeader = httpResponse.getFirstHeader("Content-Length");
        if (firstHeader == null) {
            log.debug("validateContentLength", "No Content-Length header", new Object[0]);
            return true;
        }
        long parseLong = Long.parseLong(firstHeader.getValue());
        log.debug("validateContentLength", "Got header content-length", "headerContentLength", Long.valueOf(parseLong));
        int i = (parseLong > 0L ? 1 : (parseLong == 0L ? 0 : -1));
        if (i < 0) {
            return true;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            long contentLength = entity.getContentLength();
            log.debug("validateContentLength", "got entity content-length", "entityContentLength", Long.valueOf(contentLength));
            if (parseLong != contentLength) {
                log.error("validateContentLength", "Length mismatch", new Object[0]);
                httpResponse.setHeader("Content-Length", String.valueOf(contentLength));
                changeStatusLine(httpResponse, 500, contentLength < parseLong ? "Response Truncated" : "Content Length Mismatch");
                return false;
            }
        } else if (i != 0) {
            log.error("validateContentLength", "Unexpected null content", "headerContentLength", Long.valueOf(parseLong));
            httpResponse.setHeader("Content-Length", "0");
            changeStatusLine(httpResponse, 500, "Unexpected Null Content");
            return false;
        }
        return true;
    }
}
