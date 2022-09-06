package com.amazon.communication;

import amazon.communication.Message;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
/* loaded from: classes12.dex */
public class PlainTextHttpRequestResponseConverter extends HttpRequestResponseConverterBase {
    private static final int BUFFER_SIZE = 1024;
    public static final int DEFAULT_BYTE_BUFFER_CHAIN_MESSAGE_SIZE_CUT_OFF = 102400;
    private final int mByteBufferChainMessageSizeLimit;
    private static final DPLogger log = new DPLogger("TComm.PlainTextHttpRequestResponseConverter");
    private static final ProtocolVersion PROTOCOL_VERSION = new ProtocolVersion("HTTP", 1, 1);
    private static final PlainTextHttpRequestResponseConverter SINGLETON = new PlainTextHttpRequestResponseConverter();

    protected PlainTextHttpRequestResponseConverter() {
        this(102400);
    }

    private void addEntityBodyToMessage(HttpMessage httpMessage, InputStream inputStream, int i) throws ProtocolException {
        log.verbose("addEntityBodyToMessage", "beginning execution", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "bodySize", Integer.valueOf(i));
        if (i > 0) {
            InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream, i);
            if (httpMessage instanceof HttpResponse) {
                ((HttpResponse) httpMessage).setEntity(inputStreamEntity);
            } else if (httpMessage instanceof HttpEntityEnclosingRequest) {
                ((HttpEntityEnclosingRequest) httpMessage).setEntity(inputStreamEntity);
            } else {
                throw new ProtocolException("Message contains bytes for an entity body, but this HttpMessage type does not support entity bodies.");
            }
        }
    }

    private void addHeadersToMessage(StringBuilder sb, HttpMessage httpMessage, InputStream inputStream) throws ProtocolException {
        String nextLine = getNextLine(sb, inputStream);
        sb.setLength(0);
        while (!isEndOfHeaders(nextLine)) {
            String[] split = nextLine.split(":", 2);
            if (split.length == 2) {
                httpMessage.addHeader(split[0], split[1]);
                String nextLine2 = getNextLine(sb, inputStream);
                sb.setLength(0);
                log.verbose("addHeadersToMessage", "finished adding headers", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "name", split[0], "value", split[1]);
                nextLine = nextLine2;
            } else {
                throw new ProtocolException("Invalid message header format");
            }
        }
    }

    private Message createMessageFromEntityBody(InputStream inputStream) throws IOException {
        ByteBufferChain byteBufferChain = new ByteBufferChain();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.mByteBufferChainMessageSizeLimit) {
                break;
            }
            byte[] bArr = new byte[1024];
            int read = inputStream.read(bArr);
            if (read == -1) {
                z = true;
                break;
            }
            byteBufferChain.append(ByteBuffer.wrap(bArr, 0, read));
            i += read;
        }
        if (z) {
            return new ByteBufferChainMessageImpl(byteBufferChain);
        }
        return new InputStreamMessageImpl(byteBufferChain.getByteBufferList(), inputStream);
    }

    private HttpRequestBase createRequestFromRequestLine(String str) throws ProtocolException {
        log.verbose("createRequestFromRequestLine", "beginning execution", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "requestLine", str);
        String[] split = str.split(" ", 3);
        if (split.length == 3) {
            String str2 = split[2];
            if (str2.equals("HTTP/1.1")) {
                String str3 = split[0];
                String str4 = split[1];
                if (str3.equals(SmartDeviceDataProvider.METHOD_HTTP_PUT)) {
                    return new HttpPut(str4);
                }
                if (str3.equals("GET")) {
                    return new HttpGet(str4);
                }
                if (str3.equals("POST")) {
                    return new HttpPost(str4);
                }
                if (str3.equals(Constants.REQUEST_METHOD_DELETE)) {
                    return new HttpDelete(str4);
                }
                throw new ProtocolException(GeneratedOutlineSupport1.outline72("Unsupported request method at this time: ", str3));
            }
            throw new ProtocolException(GeneratedOutlineSupport1.outline72("Invalid protocol version: ", str2));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid message header format: ");
        outline107.append(split.length);
        throw new ProtocolException(outline107.toString());
    }

    private HttpResponse createResponseFromResponseLine(String str) throws ProtocolException {
        String[] split = str.split(" ", 2);
        if (split.length == 2) {
            String str2 = split[0];
            if (str2.equals("HTTP/1.1")) {
                String str3 = split[1];
                if (str3.length() == 3) {
                    try {
                        int parseInt = Integer.parseInt(str3);
                        log.verbose("createResponseFromResponseLine", "finished parsing responseLine", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "reasonCode", Integer.valueOf(parseInt));
                        return new BasicHttpResponse(new BasicStatusLine(PROTOCOL_VERSION, parseInt, (String) null));
                    } catch (NumberFormatException unused) {
                        throw new ProtocolException(GeneratedOutlineSupport1.outline72("Invalid reason code: ", str3));
                    }
                }
                throw new ProtocolException(GeneratedOutlineSupport1.outline72("Invalid reason code: ", str3));
            }
            throw new ProtocolException(GeneratedOutlineSupport1.outline72("Invalid protocol version: ", str2));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid message header format: ");
        outline107.append(split.length);
        throw new ProtocolException(outline107.toString());
    }

    public static PlainTextHttpRequestResponseConverter getInstance() {
        return SINGLETON;
    }

    private void validateMethod(String str) {
        if (str != null) {
            if (!SmartDeviceDataProvider.METHOD_HTTP_PUT.equals(str) && !"GET".equals(str) && !"POST".equals(str) && !Constants.REQUEST_METHOD_DELETE.equals(str)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("unknown method '", str, "'"));
            }
            return;
        }
        throw new IllegalArgumentException("request missing method");
    }

    private void validateProtocolVersion(ProtocolVersion protocolVersion) {
        if (protocolVersion.getProtocol().equals("HTTP") && protocolVersion.getMajor() == 1 && protocolVersion.getMinor() == 1) {
            return;
        }
        throw new IllegalArgumentException("Only HTTP/1.1 supported");
    }

    private void validateUri(String str) {
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException("request missing uri");
    }

    @Override // com.amazon.communication.HttpRequestResponseConverter
    public HttpRequestBase convertMessageToRequest(Message message) throws ProtocolException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(message.getPayload());
        log.verbose("convertMessageToRequest", "beginning execution", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "message.getPayloadSize", Integer.valueOf(message.getPayloadSize()));
        StringBuilder sb = new StringBuilder();
        HttpRequestBase createRequestFromRequestLine = createRequestFromRequestLine(getNextLine(sb, bufferedInputStream));
        sb.setLength(0);
        addHeadersToMessage(sb, createRequestFromRequestLine, bufferedInputStream);
        try {
            addEntityBodyToMessage(createRequestFromRequestLine, bufferedInputStream, bufferedInputStream.available());
            return createRequestFromRequestLine;
        } catch (IOException e) {
            throw new ProtocolException(e);
        }
    }

    @Override // com.amazon.communication.HttpRequestResponseConverter
    public HttpResponse convertMessageToResponse(Message message) throws ProtocolException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(message.getPayload());
        log.verbose("convertMessageToResponse", "finished reading message payload", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "message.getPayloadSize", Integer.valueOf(message.getPayloadSize()));
        StringBuilder sb = new StringBuilder();
        HttpResponse createResponseFromResponseLine = createResponseFromResponseLine(getNextLine(sb, bufferedInputStream));
        sb.setLength(0);
        addHeadersToMessage(sb, createResponseFromResponseLine, bufferedInputStream);
        try {
            addEntityBodyToMessage(createResponseFromResponseLine, bufferedInputStream, bufferedInputStream.available());
            return createResponseFromResponseLine;
        } catch (IOException e) {
            throw new ProtocolException(e);
        }
    }

    @Override // com.amazon.communication.HttpRequestResponseConverter
    public Message convertRequestToMessage(HttpRequest httpRequest) {
        Header[] allHeaders;
        HttpEntity entity;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        try {
            RequestLine requestLine = httpRequest.getRequestLine();
            String method = requestLine.getMethod();
            validateMethod(method);
            outputStreamWriter.write(method);
            outputStreamWriter.write(" ");
            String uri = requestLine.getUri();
            validateUri(uri);
            outputStreamWriter.write(uri);
            outputStreamWriter.write(" ");
            validateProtocolVersion(requestLine.getProtocolVersion());
            outputStreamWriter.write("HTTP/1.1");
            outputStreamWriter.write("\r\n");
            log.verbose("convertRequestToMessage", "finished reading method and uri", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), MetricsConstants.NativeFetch.METHOD, method, "uri", uri);
            for (Header header : httpRequest.getAllHeaders()) {
                outputStreamWriter.write(header.getName());
                outputStreamWriter.write(":");
                outputStreamWriter.write(header.getValue());
                outputStreamWriter.write("\r\n");
            }
            outputStreamWriter.write("\r\n");
            outputStreamWriter.flush();
            InputStream inputStream = null;
            if ((httpRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity()) != null) {
                inputStream = entity.getContent();
            }
            ByteBuffer wrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            if (inputStream != null) {
                Message createMessageFromEntityBody = createMessageFromEntityBody(inputStream);
                createMessageFromEntityBody.prependPayload(wrap);
                return createMessageFromEntityBody;
            }
            return new ByteBufferChainMessageImpl(wrap);
        } catch (IOException e) {
            throw new IllegalArgumentException("Malformed request object", e);
        }
    }

    @Override // com.amazon.communication.HttpRequestResponseConverter
    public Message convertResponseToMessage(HttpResponse httpResponse) throws ProtocolException {
        log.verbose("convertResponseToMessage", "converting from HttpResponse to TComm message", "response.getStatusLine", httpResponse.getStatusLine());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        try {
            outputStreamWriter.write("HTTP/1.1");
            outputStreamWriter.write(" ");
            outputStreamWriter.write(Integer.toString(httpResponse.getStatusLine().getStatusCode()));
            outputStreamWriter.write("\r\n");
            Header[] allHeaders = httpResponse.getAllHeaders();
            if (allHeaders != null) {
                for (Header header : allHeaders) {
                    outputStreamWriter.write(header.getName());
                    outputStreamWriter.write(":");
                    outputStreamWriter.write(header.getValue());
                    outputStreamWriter.write("\r\n");
                }
            }
            outputStreamWriter.write("\r\n");
            outputStreamWriter.flush();
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                entity.writeTo(byteArrayOutputStream);
            }
            return new ByteBufferChainMessageImpl(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Malformed response object", e);
        }
    }

    protected PlainTextHttpRequestResponseConverter(int i) {
        this.mByteBufferChainMessageSizeLimit = i;
    }

    public static PlainTextHttpRequestResponseConverter getInstance(int i) {
        return new PlainTextHttpRequestResponseConverter(i);
    }
}
