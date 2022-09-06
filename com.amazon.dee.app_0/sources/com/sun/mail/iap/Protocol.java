package com.sun.mail.iap;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.PropUtil;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
/* loaded from: classes3.dex */
public class Protocol {
    private static final byte[] CRLF = {13, 10};
    private final Vector handlers;
    protected String host;
    private volatile ResponseInputStream input;
    private String localHostName;
    protected MailLogger logger;
    private volatile DataOutputStream output;
    protected String prefix;
    protected Properties props;
    protected boolean quote;
    private Socket socket;
    private int tagCounter;
    private volatile long timestamp;
    private TraceInputStream traceInput;
    protected MailLogger traceLogger;
    private TraceOutputStream traceOutput;

    public Protocol(String str, int i, Properties properties, String str2, boolean z, MailLogger mailLogger) throws IOException, ProtocolException {
        this.tagCounter = 0;
        this.handlers = new Vector();
        try {
            this.host = str;
            this.props = properties;
            this.prefix = str2;
            this.logger = mailLogger;
            this.traceLogger = mailLogger.getSubLogger("protocol", null);
            this.socket = SocketFetcher.getSocket(str, i, properties, str2, z);
            this.quote = PropUtil.getBooleanProperty(properties, "mail.debug.quote", false);
            initStreams();
            processGreeting(readResponse());
            this.timestamp = System.currentTimeMillis();
        } catch (Throwable th) {
            disconnect();
            throw th;
        }
    }

    private void commandEnd() {
    }

    private void commandStart(String str) {
    }

    private void initStreams() throws IOException {
        this.traceInput = new TraceInputStream(this.socket.getInputStream(), this.traceLogger);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(this.socket.getOutputStream(), this.traceLogger);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.handlers.addElement(responseHandler);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005d A[Catch: all -> 0x0074, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:13:0x0026, B:21:0x003e, B:24:0x0046, B:26:0x004f, B:30:0x005d, B:31:0x0060, B:16:0x002c, B:20:0x003a, B:8:0x0012, B:10:0x001b), top: B:37:0x0001, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0059 -> B:12:0x0024). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.sun.mail.iap.Response[] command(java.lang.String r8, com.sun.mail.iap.Argument r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.commandStart(r8)     // Catch: java.lang.Throwable -> L74
            java.util.Vector r0 = new java.util.Vector     // Catch: java.lang.Throwable -> L74
            r0.<init>()     // Catch: java.lang.Throwable -> L74
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r8 = r7.writeCommand(r8, r9)     // Catch: java.lang.Exception -> L11 com.sun.mail.iap.LiteralException -> L1a java.lang.Throwable -> L74
            goto L24
        L11:
            r8 = move-exception
            com.sun.mail.iap.Response r8 = com.sun.mail.iap.Response.byeResponse(r8)     // Catch: java.lang.Throwable -> L74
            r0.addElement(r8)     // Catch: java.lang.Throwable -> L74
            goto L22
        L1a:
            r8 = move-exception
            com.sun.mail.iap.Response r8 = r8.getResponse()     // Catch: java.lang.Throwable -> L74
            r0.addElement(r8)     // Catch: java.lang.Throwable -> L74
        L22:
            r8 = r2
            goto L59
        L24:
            if (r1 != 0) goto L5b
            com.sun.mail.iap.Response r9 = r7.readResponse()     // Catch: com.sun.mail.iap.ProtocolException -> L2b java.io.IOException -> L36 java.lang.Throwable -> L74
            goto L3e
        L2b:
            r9 = move-exception
            com.sun.mail.util.MailLogger r4 = r7.logger     // Catch: java.lang.Throwable -> L74
            java.util.logging.Level r5 = java.util.logging.Level.FINE     // Catch: java.lang.Throwable -> L74
            java.lang.String r6 = "ignoring bad response"
            r4.log(r5, r6, r9)     // Catch: java.lang.Throwable -> L74
            goto L24
        L36:
            r9 = move-exception
            if (r2 == 0) goto L3a
            goto L5b
        L3a:
            com.sun.mail.iap.Response r9 = com.sun.mail.iap.Response.byeResponse(r9)     // Catch: java.lang.Throwable -> L74
        L3e:
            boolean r4 = r9.isBYE()     // Catch: java.lang.Throwable -> L74
            if (r4 == 0) goto L46
            r2 = r9
            goto L24
        L46:
            r0.addElement(r9)     // Catch: java.lang.Throwable -> L74
            boolean r4 = r9.isTagged()     // Catch: java.lang.Throwable -> L74
            if (r4 == 0) goto L24
            java.lang.String r9 = r9.getTag()     // Catch: java.lang.Throwable -> L74
            boolean r9 = r9.equals(r8)     // Catch: java.lang.Throwable -> L74
            if (r9 == 0) goto L24
        L59:
            r1 = r3
            goto L24
        L5b:
            if (r2 == 0) goto L60
            r0.addElement(r2)     // Catch: java.lang.Throwable -> L74
        L60:
            int r8 = r0.size()     // Catch: java.lang.Throwable -> L74
            com.sun.mail.iap.Response[] r8 = new com.sun.mail.iap.Response[r8]     // Catch: java.lang.Throwable -> L74
            r0.copyInto(r8)     // Catch: java.lang.Throwable -> L74
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L74
            r7.timestamp = r0     // Catch: java.lang.Throwable -> L74
            r7.commandEnd()     // Catch: java.lang.Throwable -> L74
            monitor-exit(r7)
            return r8
        L74:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.iap.Protocol.command(java.lang.String, com.sun.mail.iap.Argument):com.sun.mail.iap.Response[]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void disconnect() {
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException unused) {
            }
            this.socket = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }

    public SocketChannel getChannel() {
        return this.socket.getChannel();
    }

    public InetAddress getInetAddress() {
        return this.socket.getInetAddress();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ResponseInputStream getInputStream() {
        return this.input;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized String getLocalHost() {
        if (this.localHostName == null || this.localHostName.length() <= 0) {
            Properties properties = this.props;
            this.localHostName = properties.getProperty(this.prefix + ".localhost");
        }
        if (this.localHostName == null || this.localHostName.length() <= 0) {
            Properties properties2 = this.props;
            this.localHostName = properties2.getProperty(this.prefix + ".localaddress");
        }
        try {
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                InetAddress localHost = InetAddress.getLocalHost();
                this.localHostName = localHost.getCanonicalHostName();
                if (this.localHostName == null) {
                    this.localHostName = "[" + localHost.getHostAddress() + "]";
                }
            }
        } catch (UnknownHostException unused) {
        }
        if ((this.localHostName == null || this.localHostName.length() <= 0) && this.socket != null && this.socket.isBound()) {
            InetAddress localAddress = this.socket.getLocalAddress();
            this.localHostName = localAddress.getCanonicalHostName();
            if (this.localHostName == null) {
                this.localHostName = "[" + localAddress.getHostAddress() + "]";
            }
        }
        return this.localHostName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OutputStream getOutputStream() {
        return this.output;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteArray getResponseBuffer() {
        return null;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void handleResult(Response response) throws ProtocolException {
        if (response.isOK()) {
            return;
        }
        if (!response.isNO()) {
            if (!response.isBAD()) {
                if (!response.isBYE()) {
                    return;
                }
                disconnect();
                throw new ConnectionException(this, response);
            }
            throw new BadCommandException(response);
        }
        throw new CommandFailedException(response);
    }

    public boolean hasResponse() {
        try {
            return this.input.available() > 0;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean isSSL() {
        return this.socket instanceof SSLSocket;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isTracing() {
        return this.traceLogger.isLoggable(Level.FINEST);
    }

    public void notifyResponseHandlers(Response[] responseArr) {
        if (this.handlers.size() == 0) {
            return;
        }
        for (Response response : responseArr) {
            if (response != null) {
                Object[] array = this.handlers.toArray();
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null) {
                        ((ResponseHandler) array[i]).handleResponse(response);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processGreeting(Response response) throws ProtocolException {
        if (!response.isBYE()) {
            return;
        }
        throw new ConnectionException(this, response);
    }

    public Response readResponse() throws IOException, ProtocolException {
        return new Response(this);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.handlers.removeElement(responseHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resumeTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(true);
            this.traceOutput.setTrace(true);
        }
    }

    public void simpleCommand(String str, Argument argument) throws ProtocolException {
        Response[] command = command(str, argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
    }

    public synchronized void startTLS(String str) throws IOException, ProtocolException {
        if (this.socket instanceof SSLSocket) {
            return;
        }
        simpleCommand(str, null);
        this.socket = SocketFetcher.startTLS(this.socket, this.host, this.props, this.prefix);
        initStreams();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized boolean supportsNonSyncLiterals() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void suspendTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(false);
            this.traceOutput.setTrace(false);
        }
    }

    public String writeCommand(String str, Argument argument) throws IOException, ProtocolException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        int i = this.tagCounter;
        this.tagCounter = i + 1;
        outline107.append(Integer.toString(i, 10));
        String sb = outline107.toString();
        DataOutputStream dataOutputStream = this.output;
        dataOutputStream.writeBytes(sb + " " + str);
        if (argument != null) {
            this.output.write(32);
            argument.write(this);
        }
        this.output.write(CRLF);
        this.output.flush();
        return sb;
    }

    public Protocol(InputStream inputStream, PrintStream printStream, Properties properties, boolean z) throws IOException {
        this.tagCounter = 0;
        this.handlers = new Vector();
        this.host = AndroidInfoHelpers.DEVICE_LOCALHOST;
        this.props = properties;
        this.quote = false;
        this.logger = new MailLogger(getClass(), "DEBUG", z, System.out);
        this.traceLogger = this.logger.getSubLogger("protocol", null);
        this.traceInput = new TraceInputStream(inputStream, this.traceLogger);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(printStream, this.traceLogger);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
        this.timestamp = System.currentTimeMillis();
    }
}
