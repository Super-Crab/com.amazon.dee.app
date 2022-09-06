package com.sun.mail.pop3;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.PropUtil;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Protocol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CRLF = "\r\n";
    private static final int POP3_PORT = 110;
    private static final int SLOP = 128;
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String apopChallenge;
    private Map capabilities = null;
    private String host;
    private BufferedReader input;
    private MailLogger logger;
    private boolean noauthdebug;
    private PrintWriter output;
    private boolean pipelining;
    private String prefix;
    private Properties props;
    private Socket socket;
    private TraceInputStream traceInput;
    private MailLogger traceLogger;
    private TraceOutputStream traceOutput;
    private boolean traceSuspended;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Protocol(String str, int i, MailLogger mailLogger, Properties properties, String str2, boolean z) throws IOException {
        this.apopChallenge = null;
        boolean z2 = true;
        this.noauthdebug = true;
        this.host = str;
        this.props = properties;
        this.prefix = str2;
        this.logger = mailLogger;
        this.traceLogger = mailLogger.getSubLogger("protocol", null);
        this.noauthdebug = !PropUtil.getBooleanProperty(properties, "mail.debug.auth", false);
        boolean boolProp = getBoolProp(properties, GeneratedOutlineSupport1.outline72(str2, ".apop.enable"));
        boolean boolProp2 = getBoolProp(properties, GeneratedOutlineSupport1.outline72(str2, ".disablecapa"));
        i = i == -1 ? 110 : i;
        try {
            if (mailLogger.isLoggable(Level.FINE)) {
                mailLogger.fine("connecting to host \"" + str + "\", port " + i + ", isSSL " + z);
            }
            this.socket = SocketFetcher.getSocket(str, i, properties, str2, z);
            initStreams();
            Response simpleCommand = simpleCommand(null);
            if (simpleCommand.ok) {
                if (boolProp) {
                    int indexOf = simpleCommand.data.indexOf(60);
                    int indexOf2 = simpleCommand.data.indexOf(62, indexOf);
                    if (indexOf != -1 && indexOf2 != -1) {
                        this.apopChallenge = simpleCommand.data.substring(indexOf, indexOf2 + 1);
                    }
                    mailLogger.log(Level.FINE, "APOP challenge: {0}", this.apopChallenge);
                }
                if (!boolProp2) {
                    setCapabilities(capa());
                }
                if (!hasCapability("PIPELINING")) {
                    if (!PropUtil.getBooleanProperty(properties, str2 + ".pipelining", false)) {
                        z2 = false;
                    }
                }
                this.pipelining = z2;
                if (!this.pipelining) {
                    return;
                }
                mailLogger.config("PIPELINING enabled");
                return;
            }
            this.socket.close();
            throw new IOException("Connect failed");
        } catch (IOException e) {
            this.socket.close();
            throw e;
        }
    }

    private void batchCommandContinue(String str) {
    }

    private void batchCommandEnd() {
    }

    private void batchCommandStart(String str) {
    }

    private final synchronized boolean getBoolProp(Properties properties, String str) {
        boolean booleanProperty;
        booleanProperty = PropUtil.getBooleanProperty(properties, str, false);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger = this.logger;
            mailLogger.config(str + RealTimeTextConstants.COLON_SPACE + booleanProperty);
        }
        return booleanProperty;
    }

    private String getDigest(String str) {
        try {
            return toHex(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.apopChallenge, str).getBytes("iso-8859-1")));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private void initStreams() throws IOException {
        boolean booleanProperty = PropUtil.getBooleanProperty(this.props, "mail.debug.quote", false);
        this.traceInput = new TraceInputStream(this.socket.getInputStream(), this.traceLogger);
        this.traceInput.setQuote(booleanProperty);
        this.traceOutput = new TraceOutputStream(this.socket.getOutputStream(), this.traceLogger);
        this.traceOutput.setQuote(booleanProperty);
        this.input = new BufferedReader(new InputStreamReader(this.traceInput, "iso-8859-1"));
        this.output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.traceOutput, "iso-8859-1")));
    }

    private void issueCommand(String str) throws IOException {
        if (this.socket != null) {
            if (str == null) {
                return;
            }
            this.output.print(GeneratedOutlineSupport1.outline72(str, "\r\n"));
            this.output.flush();
            return;
        }
        throw new IOException("Folder is closed");
    }

    private Response multilineCommand(String str, int i) throws IOException {
        multilineCommandStart(str);
        issueCommand(str);
        Response readResponse = readResponse();
        if (!readResponse.ok) {
            multilineCommandEnd();
            return readResponse;
        }
        readResponse.bytes = readMultilineResponse(i);
        multilineCommandEnd();
        return readResponse;
    }

    private void multilineCommandEnd() {
    }

    private void multilineCommandStart(String str) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        r2 = r3.input.read();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.InputStream readMultilineResponse(int r4) throws java.io.IOException {
        /*
            r3 = this;
            com.sun.mail.util.SharedByteArrayOutputStream r0 = new com.sun.mail.util.SharedByteArrayOutputStream
            r0.<init>(r4)
            r4 = 10
            r1 = r4
        L8:
            java.io.BufferedReader r2 = r3.input     // Catch: java.io.InterruptedIOException -> L3b
            int r2 = r2.read()     // Catch: java.io.InterruptedIOException -> L3b
            if (r2 < 0) goto L2c
            if (r1 != r4) goto L27
            r1 = 46
            if (r2 != r1) goto L27
            java.io.BufferedReader r1 = r3.input     // Catch: java.io.InterruptedIOException -> L3b
            int r1 = r1.read()     // Catch: java.io.InterruptedIOException -> L3b
            r2 = 13
            if (r1 != r2) goto L28
            java.io.BufferedReader r4 = r3.input     // Catch: java.io.InterruptedIOException -> L3b
            int r2 = r4.read()     // Catch: java.io.InterruptedIOException -> L3b
            goto L2c
        L27:
            r1 = r2
        L28:
            r0.write(r1)     // Catch: java.io.InterruptedIOException -> L3b
            goto L8
        L2c:
            if (r2 < 0) goto L33
            java.io.InputStream r4 = r0.toStream()
            return r4
        L33:
            java.io.EOFException r4 = new java.io.EOFException
            java.lang.String r0 = "EOF on socket"
            r4.<init>(r0)
            throw r4
        L3b:
            r4 = move-exception
            java.net.Socket r0 = r3.socket     // Catch: java.io.IOException -> L41
            r0.close()     // Catch: java.io.IOException -> L41
        L41:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.Protocol.readMultilineResponse(int):java.io.InputStream");
    }

    private Response readResponse() throws IOException {
        try {
            String readLine = this.input.readLine();
            if (readLine != null) {
                Response response = new Response();
                if (readLine.startsWith("+OK")) {
                    response.ok = true;
                } else if (readLine.startsWith("-ERR")) {
                    response.ok = false;
                } else {
                    throw new IOException(GeneratedOutlineSupport1.outline72("Unexpected response: ", readLine));
                }
                int indexOf = readLine.indexOf(32);
                if (indexOf >= 0) {
                    response.data = readLine.substring(indexOf + 1);
                }
                return response;
            }
            this.traceLogger.finest("<EOF>");
            throw new EOFException("EOF on socket");
        } catch (InterruptedIOException e) {
            try {
                this.socket.close();
            } catch (IOException unused) {
            }
            throw new EOFException(e.getMessage());
        } catch (SocketException e2) {
            try {
                this.socket.close();
            } catch (IOException unused2) {
            }
            throw new EOFException(e2.getMessage());
        }
    }

    private void resumeTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(true);
            this.traceOutput.setTrace(true);
        }
    }

    private Response simpleCommand(String str) throws IOException {
        simpleCommandStart(str);
        issueCommand(str);
        Response readResponse = readResponse();
        simpleCommandEnd();
        return readResponse;
    }

    private void simpleCommandEnd() {
    }

    private void simpleCommandStart(String str) {
    }

    private void suspendTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(false);
            this.traceOutput.setTrace(false);
        }
    }

    private static String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = i + 1;
            char[] cArr2 = digits;
            cArr[i] = cArr2[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream capa() throws IOException {
        Response multilineCommand = multilineCommand("CAPA", 128);
        if (!multilineCommand.ok) {
            return null;
        }
        return multilineCommand.bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean dele(int i) throws IOException {
        return simpleCommand("DELE " + i).ok;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.socket != null) {
            quit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Map getCapabilities() {
        return this.capabilities;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean hasCapability(String str) {
        boolean z;
        if (this.capabilities != null) {
            if (this.capabilities.containsKey(str.toUpperCase(Locale.ENGLISH))) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isSSL() {
        return this.socket instanceof SSLSocket;
    }

    protected boolean isTracing() {
        return this.traceLogger.isLoggable(Level.FINEST);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int list(int i) throws IOException {
        int i2;
        String str;
        Response simpleCommand = simpleCommand("LIST " + i);
        i2 = -1;
        if (simpleCommand.ok && (str = simpleCommand.data) != null) {
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(str);
                stringTokenizer.nextToken();
                i2 = Integer.parseInt(stringTokenizer.nextToken());
            } catch (RuntimeException unused) {
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String login(String str, String str2) throws IOException {
        Response simpleCommand;
        boolean z = this.pipelining && (this.socket instanceof SSLSocket);
        if (this.noauthdebug && isTracing()) {
            this.logger.fine("authentication command trace suppressed");
            suspendTracing();
        }
        String digest = this.apopChallenge != null ? getDigest(str2) : null;
        if (this.apopChallenge != null && digest != null) {
            simpleCommand = simpleCommand("APOP " + str + " " + digest);
        } else if (z) {
            String str3 = "USER " + str;
            batchCommandStart(str3);
            issueCommand(str3);
            String str4 = "PASS " + str2;
            batchCommandContinue(str4);
            issueCommand(str4);
            Response readResponse = readResponse();
            if (!readResponse.ok) {
                String str5 = readResponse.data;
                if (str5 == null) {
                    str5 = "USER command failed";
                }
                readResponse();
                batchCommandEnd();
                resumeTracing();
                return str5;
            }
            simpleCommand = readResponse();
            batchCommandEnd();
        } else {
            Response simpleCommand2 = simpleCommand("USER " + str);
            if (!simpleCommand2.ok) {
                String str6 = simpleCommand2.data;
                if (str6 == null) {
                    str6 = "USER command failed";
                }
                resumeTracing();
                return str6;
            }
            simpleCommand = simpleCommand("PASS " + str2);
        }
        if (this.noauthdebug && isTracing()) {
            this.logger.log(Level.FINE, "authentication command {0}", simpleCommand.ok ? "succeeded" : "failed");
        }
        if (!simpleCommand.ok) {
            String str7 = simpleCommand.data;
            if (str7 == null) {
                str7 = "login failed";
            }
            resumeTracing();
            return str7;
        }
        resumeTracing();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean noop() throws IOException {
        return simpleCommand("NOOP").ok;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean quit() throws IOException {
        boolean z;
        z = simpleCommand("QUIT").ok;
        this.socket.close();
        this.socket = null;
        this.input = null;
        this.output = null;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0012 A[Catch: all -> 0x000a, TryCatch #3 {all -> 0x000a, blocks: (B:5:0x0004, B:13:0x0012, B:15:0x0048, B:17:0x004c, B:21:0x0061, B:23:0x006b, B:27:0x0086, B:29:0x008e, B:30:0x0096, B:52:0x0111, B:55:0x0117, B:57:0x0121, B:58:0x013d, B:31:0x009b, B:33:0x00ba, B:38:0x00c2, B:40:0x00c6, B:42:0x00db, B:46:0x00e4, B:48:0x00ee, B:51:0x0108), top: B:72:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009b A[Catch: all -> 0x000a, TryCatch #3 {all -> 0x000a, blocks: (B:5:0x0004, B:13:0x0012, B:15:0x0048, B:17:0x004c, B:21:0x0061, B:23:0x006b, B:27:0x0086, B:29:0x008e, B:30:0x0096, B:52:0x0111, B:55:0x0117, B:57:0x0121, B:58:0x013d, B:31:0x009b, B:33:0x00ba, B:38:0x00c2, B:40:0x00c6, B:42:0x00db, B:46:0x00e4, B:48:0x00ee, B:51:0x0108), top: B:72:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.io.InputStream retr(int r6, int r7) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.Protocol.retr(int, int):java.io.InputStream");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean rset() throws IOException {
        return simpleCommand("RSET").ok;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setCapabilities(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        if (inputStream == null) {
            this.capabilities = null;
        } else {
            this.capabilities = new HashMap(10);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "us-ascii"));
            } catch (UnsupportedEncodingException unused) {
            }
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        int indexOf = readLine.indexOf(32);
                        this.capabilities.put((indexOf > 0 ? readLine.substring(0, indexOf) : readLine).toUpperCase(Locale.ENGLISH), readLine);
                    }
                } catch (IOException unused2) {
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                    throw th;
                }
                try {
                    break;
                } catch (IOException unused4) {
                    return;
                }
            }
            inputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Status stat() throws IOException {
        Status status;
        Response simpleCommand = simpleCommand("STAT");
        status = new Status();
        if (simpleCommand.ok) {
            String str = simpleCommand.data;
            if (str != null) {
                try {
                    StringTokenizer stringTokenizer = new StringTokenizer(str);
                    status.total = Integer.parseInt(stringTokenizer.nextToken());
                    status.size = Integer.parseInt(stringTokenizer.nextToken());
                } catch (RuntimeException unused) {
                }
            }
        } else {
            throw new IOException("STAT command failed: " + simpleCommand.data);
        }
        return status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean stls() throws IOException {
        if (this.socket instanceof SSLSocket) {
            return true;
        }
        Response simpleCommand = simpleCommand("STLS");
        if (simpleCommand.ok) {
            try {
                this.socket = SocketFetcher.startTLS(this.socket, this.host, this.props, this.prefix);
                initStreams();
            } catch (IOException e) {
                this.socket.close();
                this.socket = null;
                this.input = null;
                this.output = null;
                IOException iOException = new IOException("Could not convert socket to TLS");
                iOException.initCause(e);
                throw iOException;
            }
        }
        return simpleCommand.ok;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream top(int i, int i2) throws IOException {
        return multilineCommand("TOP " + i + " " + i2, 0).bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String uidl(int i) throws IOException {
        Response simpleCommand = simpleCommand("UIDL " + i);
        if (!simpleCommand.ok) {
            return null;
        }
        int indexOf = simpleCommand.data.indexOf(32);
        if (indexOf <= 0) {
            return null;
        }
        return simpleCommand.data.substring(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream list() throws IOException {
        return multilineCommand("LIST", 128).bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean uidl(String[] strArr) throws IOException {
        int parseInt;
        Response multilineCommand = multilineCommand("UIDL", strArr.length * 15);
        if (!multilineCommand.ok) {
            return false;
        }
        LineInputStream lineInputStream = new LineInputStream(multilineCommand.bytes);
        while (true) {
            String readLine = lineInputStream.readLine();
            if (readLine != null) {
                int indexOf = readLine.indexOf(32);
                if (indexOf >= 1 && indexOf < readLine.length() && (parseInt = Integer.parseInt(readLine.substring(0, indexOf))) > 0 && parseInt <= strArr.length) {
                    strArr[parseInt - 1] = readLine.substring(indexOf + 1);
                }
            } else {
                try {
                    break;
                } catch (IOException unused) {
                }
            }
        }
        multilineCommand.bytes.close();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r2 = r5.input.read();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean retr(int r6, java.io.OutputStream r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            r0.<init>()     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = "RETR "
            r0.append(r1)     // Catch: java.lang.Throwable -> L8f
            r0.append(r6)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r6 = r0.toString()     // Catch: java.lang.Throwable -> L8f
            r5.multilineCommandStart(r6)     // Catch: java.lang.Throwable -> L8f
            r5.issueCommand(r6)     // Catch: java.lang.Throwable -> L8f
            com.sun.mail.pop3.Response r6 = r5.readResponse()     // Catch: java.lang.Throwable -> L8f
            boolean r6 = r6.ok     // Catch: java.lang.Throwable -> L8f
            if (r6 != 0) goto L26
            r5.multilineCommandEnd()     // Catch: java.lang.Throwable -> L8f
            r6 = 0
            monitor-exit(r5)
            return r6
        L26:
            r6 = 0
            r0 = 10
            r1 = r6
            r6 = r0
        L2b:
            java.io.BufferedReader r2 = r5.input     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            int r2 = r2.read()     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            if (r2 < 0) goto L67
            if (r6 != r0) goto L4a
            r6 = 46
            if (r2 != r6) goto L4a
            java.io.BufferedReader r6 = r5.input     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            int r6 = r6.read()     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            r2 = 13
            if (r6 != r2) goto L4b
            java.io.BufferedReader r6 = r5.input     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            int r2 = r6.read()     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            goto L67
        L4a:
            r6 = r2
        L4b:
            if (r1 != 0) goto L2b
            r7.write(r6)     // Catch: java.lang.RuntimeException -> L51 java.io.IOException -> L5c java.lang.Throwable -> L8f
            goto L2b
        L51:
            r1 = move-exception
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            java.util.logging.Level r3 = java.util.logging.Level.FINE     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            java.lang.String r4 = "exception while streaming"
            r2.log(r3, r4, r1)     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            goto L2b
        L5c:
            r1 = move-exception
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            java.util.logging.Level r3 = java.util.logging.Level.FINE     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            java.lang.String r4 = "exception while streaming"
            r2.log(r3, r4, r1)     // Catch: java.io.InterruptedIOException -> L88 java.lang.Throwable -> L8f
            goto L2b
        L67:
            if (r2 < 0) goto L80
            if (r1 == 0) goto L7a
            boolean r6 = r1 instanceof java.io.IOException     // Catch: java.lang.Throwable -> L8f
            if (r6 != 0) goto L77
            boolean r6 = r1 instanceof java.lang.RuntimeException     // Catch: java.lang.Throwable -> L8f
            if (r6 != 0) goto L74
            goto L7a
        L74:
            java.lang.RuntimeException r1 = (java.lang.RuntimeException) r1     // Catch: java.lang.Throwable -> L8f
            throw r1     // Catch: java.lang.Throwable -> L8f
        L77:
            java.io.IOException r1 = (java.io.IOException) r1     // Catch: java.lang.Throwable -> L8f
            throw r1     // Catch: java.lang.Throwable -> L8f
        L7a:
            r5.multilineCommandEnd()     // Catch: java.lang.Throwable -> L8f
            r6 = 1
            monitor-exit(r5)
            return r6
        L80:
            java.io.EOFException r6 = new java.io.EOFException     // Catch: java.lang.Throwable -> L8f
            java.lang.String r7 = "EOF on socket"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L8f
            throw r6     // Catch: java.lang.Throwable -> L8f
        L88:
            r6 = move-exception
            java.net.Socket r7 = r5.socket     // Catch: java.io.IOException -> L8e java.lang.Throwable -> L8f
            r7.close()     // Catch: java.io.IOException -> L8e java.lang.Throwable -> L8f
        L8e:
            throw r6     // Catch: java.lang.Throwable -> L8f
        L8f:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.Protocol.retr(int, java.io.OutputStream):boolean");
    }
}
