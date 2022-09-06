package com.sun.mail.smtp;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.sun.mail.auth.Ntlm;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.MailConnectException;
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.PropUtil;
import com.sun.mail.util.SocketConnectException;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Service;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.ParseException;
import javax.net.ssl.SSLSocket;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class SMTPTransport extends Transport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String UNKNOWN = "UNKNOWN";
    private Address[] addresses;
    private Map authenticators;
    private String authorizationID;
    private SMTPOutputStream dataStream;
    private String defaultAuthenticationMechanisms;
    private int defaultPort;
    private boolean enableSASL;
    private MessagingException exception;
    private Hashtable extMap;
    private String host;
    private Address[] invalidAddr;
    private boolean isSSL;
    private int lastReturnCode;
    private String lastServerResponse;
    private LineInputStream lineInputStream;
    private String localHostName;
    private MailLogger logger;
    private MimeMessage message;
    private String name;
    private boolean noauthdebug;
    private boolean noopStrict;
    private boolean notificationDone;
    private String ntlmDomain;
    private boolean quitWait;
    private boolean reportSuccess;
    private boolean requireStartTLS;
    private SaslAuthenticator saslAuthenticator;
    private String[] saslMechanisms;
    private String saslRealm;
    private boolean sendPartiallyFailed;
    private BufferedInputStream serverInput;
    private OutputStream serverOutput;
    private Socket serverSocket;
    private TraceInputStream traceInput;
    private MailLogger traceLogger;
    private TraceOutputStream traceOutput;
    private boolean useCanonicalHostName;
    private boolean useRset;
    private boolean useStartTLS;
    private Address[] validSentAddr;
    private Address[] validUnsentAddr;
    private static final String[] ignoreList = {"Bcc", "Content-Length"};
    private static final byte[] CRLF = {13, 10};
    private static final String[] UNKNOWN_SA = new String[0];
    private static char[] hexchar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public abstract class Authenticator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private String mech;
        protected int resp;

        Authenticator(String str) {
            this.mech = str.toUpperCase(Locale.ENGLISH);
        }

        boolean authenticate(String str, String str2, String str3, String str4) throws MessagingException {
            String str5 = "succeeded";
            try {
                try {
                    String initialResponse = getInitialResponse(str, str2, str3, str4);
                    if (SMTPTransport.this.noauthdebug && SMTPTransport.this.isTracing()) {
                        MailLogger mailLogger = SMTPTransport.this.logger;
                        mailLogger.fine("AUTH " + this.mech + " command trace suppressed");
                        SMTPTransport.this.suspendTracing();
                    }
                    if (initialResponse != null) {
                        SMTPTransport sMTPTransport = SMTPTransport.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("AUTH ");
                        sb.append(this.mech);
                        sb.append(" ");
                        sb.append(initialResponse.length() == 0 ? Config.Compare.EQUAL_TO : initialResponse);
                        this.resp = sMTPTransport.simpleCommand(sb.toString());
                    } else {
                        SMTPTransport sMTPTransport2 = SMTPTransport.this;
                        this.resp = sMTPTransport2.simpleCommand("AUTH " + this.mech);
                    }
                    if (this.resp == 530) {
                        SMTPTransport.this.startTLS();
                        if (initialResponse != null) {
                            SMTPTransport sMTPTransport3 = SMTPTransport.this;
                            this.resp = sMTPTransport3.simpleCommand("AUTH " + this.mech + " " + initialResponse);
                        } else {
                            SMTPTransport sMTPTransport4 = SMTPTransport.this;
                            this.resp = sMTPTransport4.simpleCommand("AUTH " + this.mech);
                        }
                    }
                    if (this.resp == 334) {
                        doAuth(str, str2, str3, str4);
                    }
                    if (SMTPTransport.this.noauthdebug && SMTPTransport.this.isTracing()) {
                        MailLogger mailLogger2 = SMTPTransport.this.logger;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AUTH ");
                        outline107.append(this.mech);
                        outline107.append(" ");
                        if (this.resp != 235) {
                            str5 = "failed";
                        }
                        outline107.append(str5);
                        mailLogger2.fine(outline107.toString());
                    }
                    SMTPTransport.this.resumeTracing();
                    if (this.resp == 235) {
                        return true;
                    }
                    SMTPTransport.this.closeConnection();
                    throw new AuthenticationFailedException(SMTPTransport.this.getLastServerResponse());
                } catch (Throwable th) {
                    if (SMTPTransport.this.noauthdebug && SMTPTransport.this.isTracing()) {
                        MailLogger mailLogger3 = SMTPTransport.this.logger;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AUTH ");
                        outline1072.append(this.mech);
                        outline1072.append(" ");
                        if (this.resp != 235) {
                            str5 = "failed";
                        }
                        outline1072.append(str5);
                        mailLogger3.fine(outline1072.toString());
                    }
                    SMTPTransport.this.resumeTracing();
                    if (this.resp != 235) {
                        SMTPTransport.this.closeConnection();
                        throw new AuthenticationFailedException(SMTPTransport.this.getLastServerResponse());
                    }
                    throw th;
                }
            } catch (IOException e) {
                MailLogger mailLogger4 = SMTPTransport.this.logger;
                Level level = Level.FINE;
                mailLogger4.log(level, "AUTH " + this.mech + " failed", (Throwable) e);
                if (SMTPTransport.this.noauthdebug && SMTPTransport.this.isTracing()) {
                    MailLogger mailLogger5 = SMTPTransport.this.logger;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("AUTH ");
                    outline1073.append(this.mech);
                    outline1073.append(" ");
                    if (this.resp != 235) {
                        str5 = "failed";
                    }
                    outline1073.append(str5);
                    mailLogger5.fine(outline1073.toString());
                }
                SMTPTransport.this.resumeTracing();
                if (this.resp != 235) {
                    SMTPTransport.this.closeConnection();
                    throw new AuthenticationFailedException(SMTPTransport.this.getLastServerResponse());
                }
                return true;
            } catch (Throwable th2) {
                MailLogger mailLogger6 = SMTPTransport.this.logger;
                Level level2 = Level.FINE;
                mailLogger6.log(level2, "AUTH " + this.mech + " failed", (Throwable) th2);
                if (SMTPTransport.this.noauthdebug && SMTPTransport.this.isTracing()) {
                    MailLogger mailLogger7 = SMTPTransport.this.logger;
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("AUTH ");
                    outline1074.append(this.mech);
                    outline1074.append(" ");
                    if (this.resp != 235) {
                        str5 = "failed";
                    }
                    outline1074.append(str5);
                    mailLogger7.fine(outline1074.toString());
                }
                SMTPTransport.this.resumeTracing();
                if (this.resp != 235) {
                    SMTPTransport.this.closeConnection();
                    if (!(th2 instanceof Error)) {
                        if (!(th2 instanceof Exception)) {
                            throw new AuthenticationFailedException(SMTPTransport.this.getLastServerResponse());
                        }
                        throw new AuthenticationFailedException(SMTPTransport.this.getLastServerResponse(), th2);
                    }
                    throw ((Error) th2);
                }
                return true;
            }
        }

        abstract void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException;

        String getInitialResponse(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            return null;
        }

        String getMechanism() {
            return this.mech;
        }
    }

    /* loaded from: classes3.dex */
    private class DigestMD5Authenticator extends Authenticator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private DigestMD5 md5support;

        DigestMD5Authenticator() {
            super("DIGEST-MD5");
        }

        private synchronized DigestMD5 getMD5() {
            if (this.md5support == null) {
                this.md5support = new DigestMD5(SMTPTransport.this.logger);
            }
            return this.md5support;
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            DigestMD5 md5 = getMD5();
            this.resp = SMTPTransport.this.simpleCommand(md5.authClient(str, str3, str4, SMTPTransport.this.getSASLRealm(), SMTPTransport.this.getLastServerResponse()));
            if (this.resp == 334) {
                if (!md5.authServer(SMTPTransport.this.getLastServerResponse())) {
                    this.resp = -1;
                } else {
                    this.resp = SMTPTransport.this.simpleCommand(new byte[0]);
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    private class LoginAuthenticator extends Authenticator {
        LoginAuthenticator() {
            super("LOGIN");
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            this.resp = SMTPTransport.this.simpleCommand(BASE64EncoderStream.encode(ASCIIUtility.getBytes(str3)));
            if (this.resp == 334) {
                this.resp = SMTPTransport.this.simpleCommand(BASE64EncoderStream.encode(ASCIIUtility.getBytes(str4)));
            }
        }
    }

    /* loaded from: classes3.dex */
    private class NtlmAuthenticator extends Authenticator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int flags;
        private Ntlm ntlm;

        NtlmAuthenticator() {
            super("NTLM");
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            this.resp = SMTPTransport.this.simpleCommand(this.ntlm.generateType3Msg(SMTPTransport.this.getLastServerResponse().substring(4).trim()));
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        String getInitialResponse(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            this.ntlm = new Ntlm(SMTPTransport.this.getNTLMDomain(), SMTPTransport.this.getLocalHost(), str3, str4, SMTPTransport.this.logger);
            Properties properties = ((Service) SMTPTransport.this).session.getProperties();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.");
            outline107.append(SMTPTransport.this.name);
            outline107.append(".auth.ntlm.flags");
            this.flags = PropUtil.getIntProperty(properties, outline107.toString(), 0);
            return this.ntlm.generateType1Msg(this.flags);
        }
    }

    /* loaded from: classes3.dex */
    private class PlainAuthenticator extends Authenticator {
        PlainAuthenticator() {
            super("PLAIN");
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            throw new AuthenticationFailedException("PLAIN asked for more");
        }

        @Override // com.sun.mail.smtp.SMTPTransport.Authenticator
        String getInitialResponse(String str, String str2, String str3, String str4) throws MessagingException, IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
            if (str2 != null) {
                bASE64EncoderStream.write(ASCIIUtility.getBytes(str2));
            }
            bASE64EncoderStream.write(0);
            bASE64EncoderStream.write(ASCIIUtility.getBytes(str3));
            bASE64EncoderStream.write(0);
            bASE64EncoderStream.write(ASCIIUtility.getBytes(str4));
            bASE64EncoderStream.flush();
            return ASCIIUtility.toString(byteArrayOutputStream.toByteArray());
        }
    }

    public SMTPTransport(Session session, URLName uRLName) {
        this(session, uRLName, "smtp", false);
    }

    private void addressesFailed() {
        Address[] addressArr = this.validSentAddr;
        if (addressArr != null) {
            Address[] addressArr2 = this.validUnsentAddr;
            if (addressArr2 != null) {
                Address[] addressArr3 = new Address[addressArr.length + addressArr2.length];
                System.arraycopy(addressArr, 0, addressArr3, 0, addressArr.length);
                Address[] addressArr4 = this.validUnsentAddr;
                System.arraycopy(addressArr4, 0, addressArr3, this.validSentAddr.length, addressArr4.length);
                this.validSentAddr = null;
                this.validUnsentAddr = addressArr3;
                return;
            }
            this.validUnsentAddr = addressArr;
            this.validSentAddr = null;
        }
    }

    private boolean authenticate(String str, String str2) throws MessagingException {
        Session session = this.session;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.");
        outline107.append(this.name);
        outline107.append(".auth.mechanisms");
        String property = session.getProperty(outline107.toString());
        if (property == null) {
            property = this.defaultAuthenticationMechanisms;
        }
        String authorizationId = getAuthorizationId();
        if (authorizationId == null) {
            authorizationId = str;
        }
        if (this.enableSASL) {
            this.logger.fine("Authenticate with SASL");
            try {
                if (sasllogin(getSASLMechanisms(), getSASLRealm(), authorizationId, str, str2)) {
                    return true;
                }
                this.logger.fine("SASL authentication failed");
                return false;
            } catch (UnsupportedOperationException e) {
                this.logger.log(Level.FINE, "SASL support failed", (Throwable) e);
            }
        }
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            mailLogger.fine("Attempt to authenticate using mechanisms: " + property);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(property);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mail.");
            outline1072.append(this.name);
            outline1072.append(".auth.");
            outline1072.append(nextToken.toLowerCase(Locale.ENGLISH));
            outline1072.append(".disable");
            String sb = outline1072.toString();
            if (PropUtil.getBooleanSessionProperty(this.session, sb, false)) {
                if (this.logger.isLoggable(Level.FINE)) {
                    MailLogger mailLogger2 = this.logger;
                    mailLogger2.fine("mechanism " + nextToken + " disabled by property: " + sb);
                }
            } else {
                String upperCase = nextToken.toUpperCase(Locale.ENGLISH);
                if (!supportsAuthentication(upperCase)) {
                    this.logger.log(Level.FINE, "mechanism {0} not supported by server", upperCase);
                } else {
                    Authenticator authenticator = (Authenticator) this.authenticators.get(upperCase);
                    if (authenticator == null) {
                        this.logger.log(Level.FINE, "no authenticator for mechanism {0}", upperCase);
                    } else {
                        return authenticator.authenticate(this.host, authorizationId, str, str2);
                    }
                }
            }
        }
        throw new AuthenticationFailedException("No authentication mechanisms supported by both server and client");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.OutputStream, java.net.Socket, java.io.BufferedInputStream, com.sun.mail.util.LineInputStream] */
    public void closeConnection() throws MessagingException {
        try {
            try {
                if (this.serverSocket != null) {
                    this.serverSocket.close();
                }
            } catch (IOException e) {
                throw new MessagingException("Server Close Failed", e);
            }
        } finally {
            this.serverSocket = null;
            this.serverOutput = null;
            this.serverInput = null;
            this.lineInputStream = null;
            if (super.isConnected()) {
                super.close();
            }
        }
    }

    private boolean convertTo8Bit(MimePart mimePart) {
        boolean z = false;
        try {
            if (mimePart.isMimeType("text/*")) {
                String encoding = mimePart.getEncoding();
                if (encoding == null) {
                    return false;
                }
                if (!encoding.equalsIgnoreCase("quoted-printable") && !encoding.equalsIgnoreCase("base64")) {
                    return false;
                }
                InputStream inputStream = mimePart.getInputStream();
                if (is8Bit(inputStream)) {
                    mimePart.setContent(mimePart.getContent(), mimePart.getContentType());
                    mimePart.setHeader("Content-Transfer-Encoding", "8bit");
                    z = true;
                }
                if (inputStream == null) {
                    return z;
                }
                inputStream.close();
                return z;
            } else if (!mimePart.isMimeType("multipart/*")) {
                return false;
            } else {
                MimeMultipart mimeMultipart = (MimeMultipart) mimePart.getContent();
                int count = mimeMultipart.getCount();
                boolean z2 = false;
                for (int i = 0; i < count; i++) {
                    try {
                        if (convertTo8Bit((MimePart) mimeMultipart.getBodyPart(i))) {
                            z2 = true;
                        }
                    } catch (IOException | MessagingException unused) {
                    }
                }
                return z2;
            }
        } catch (IOException | MessagingException unused2) {
            return false;
        }
    }

    private void expandGroups() {
        Vector vector = null;
        int i = 0;
        while (true) {
            Address[] addressArr = this.addresses;
            if (i >= addressArr.length) {
                break;
            }
            InternetAddress internetAddress = (InternetAddress) addressArr[i];
            if (internetAddress.isGroup()) {
                if (vector == null) {
                    vector = new Vector();
                    for (int i2 = 0; i2 < i; i2++) {
                        vector.addElement(this.addresses[i2]);
                    }
                }
                try {
                    InternetAddress[] group = internetAddress.getGroup(true);
                    if (group != null) {
                        for (InternetAddress internetAddress2 : group) {
                            vector.addElement(internetAddress2);
                        }
                    } else {
                        vector.addElement(internetAddress);
                    }
                } catch (ParseException unused) {
                    vector.addElement(internetAddress);
                }
            } else if (vector != null) {
                vector.addElement(internetAddress);
            }
            i++;
        }
        if (vector != null) {
            InternetAddress[] internetAddressArr = new InternetAddress[vector.size()];
            vector.copyInto(internetAddressArr);
            this.addresses = internetAddressArr;
        }
    }

    private void initStreams() throws IOException {
        boolean booleanSessionProperty = PropUtil.getBooleanSessionProperty(this.session, "mail.debug.quote", false);
        this.traceInput = new TraceInputStream(this.serverSocket.getInputStream(), this.traceLogger);
        this.traceInput.setQuote(booleanSessionProperty);
        this.traceOutput = new TraceOutputStream(this.serverSocket.getOutputStream(), this.traceLogger);
        this.traceOutput.setQuote(booleanSessionProperty);
        this.serverOutput = new BufferedOutputStream(this.traceOutput);
        this.serverInput = new BufferedInputStream(this.traceInput);
        this.lineInputStream = new LineInputStream(this.serverInput);
    }

    private boolean is8Bit(InputStream inputStream) {
        boolean z = false;
        int i = 0;
        while (true) {
            try {
                int read = inputStream.read();
                if (read < 0) {
                    if (z) {
                        this.logger.fine("found an 8bit part");
                    }
                    return z;
                }
                int i2 = read & 255;
                if (i2 == 13 || i2 == 10) {
                    i = 0;
                } else if (i2 == 0 || (i = i + 1) > 998) {
                    return false;
                }
                if (i2 > 127) {
                    z = true;
                }
            } catch (IOException unused) {
                return false;
            }
        }
    }

    private boolean isNotLastLine(String str) {
        return str != null && str.length() >= 4 && str.charAt(3) == '-';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTracing() {
        return this.traceLogger.isLoggable(Level.FINEST);
    }

    private void issueSendCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        int readServerResponse = readServerResponse();
        if (readServerResponse != i) {
            Address[] addressArr = this.validSentAddr;
            int length = addressArr == null ? 0 : addressArr.length;
            Address[] addressArr2 = this.validUnsentAddr;
            int length2 = addressArr2 == null ? 0 : addressArr2.length;
            Address[] addressArr3 = new Address[length + length2];
            if (length > 0) {
                System.arraycopy(this.validSentAddr, 0, addressArr3, 0, length);
            }
            if (length2 > 0) {
                System.arraycopy(this.validUnsentAddr, 0, addressArr3, length, length2);
            }
            this.validSentAddr = null;
            this.validUnsentAddr = addressArr3;
            if (this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("got response code ", readServerResponse, ", with response: ");
                outline109.append(this.lastServerResponse);
                mailLogger.fine(outline109.toString());
            }
            String str2 = this.lastServerResponse;
            int i2 = this.lastReturnCode;
            if (this.serverSocket != null) {
                issueCommand("RSET", -1);
            }
            this.lastServerResponse = str2;
            this.lastReturnCode = i2;
            throw new SMTPSendFailedException(str, readServerResponse, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
        }
    }

    private String normalizeAddress(String str) {
        return (str.startsWith(Config.Compare.LESS_THAN) || str.endsWith(Config.Compare.GREATER_THAN)) ? str : GeneratedOutlineSupport1.outline75(Config.Compare.LESS_THAN, str, Config.Compare.GREATER_THAN);
    }

    private void openServer(String str, int i) throws MessagingException {
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            mailLogger.fine("trying to connect to host \"" + str + "\", port " + i + ", isSSL " + this.isSSL);
        }
        try {
            Properties properties = this.session.getProperties();
            this.serverSocket = SocketFetcher.getSocket(str, i, properties, "mail." + this.name, this.isSSL);
            int port = this.serverSocket.getPort();
            this.host = str;
            initStreams();
            int readServerResponse = readServerResponse();
            if (readServerResponse != 220) {
                this.serverSocket.close();
                this.serverSocket = null;
                this.serverOutput = null;
                this.serverInput = null;
                this.lineInputStream = null;
                if (this.logger.isLoggable(Level.FINE)) {
                    MailLogger mailLogger2 = this.logger;
                    mailLogger2.fine("could not connect to host \"" + str + "\", port: " + port + ", response: " + readServerResponse + "\n");
                }
                throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + port + ", response: " + readServerResponse);
            } else if (!this.logger.isLoggable(Level.FINE)) {
            } else {
                MailLogger mailLogger3 = this.logger;
                mailLogger3.fine("connected to host \"" + str + "\", port: " + port + "\n");
            }
        } catch (SocketConnectException e) {
            throw new MailConnectException(e);
        } catch (UnknownHostException e2) {
            throw new MessagingException(GeneratedOutlineSupport1.outline72("Unknown SMTP host: ", str), e2);
        } catch (IOException e3) {
            throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + i, e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(true);
            this.traceOutput.setTrace(true);
        }
    }

    private void sendMessageEnd() {
    }

    private void sendMessageStart(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void suspendTracing() {
        if (this.traceLogger.isLoggable(Level.FINEST)) {
            this.traceInput.setTrace(false);
            this.traceOutput.setTrace(false);
        }
    }

    protected static String xtext(String str) {
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= 128) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Non-ASCII character in SMTP submitter: ", str));
            }
            if (charAt < '!' || charAt > '~' || charAt == '+' || charAt == '=') {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length() + 4);
                    stringBuffer.append(str.substring(0, i));
                }
                stringBuffer.append('+');
                stringBuffer.append(hexchar[(charAt & 240) >> 4]);
                stringBuffer.append(hexchar[charAt & 15]);
            } else if (stringBuffer != null) {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer != null ? stringBuffer.toString() : str;
    }

    protected void checkConnected() {
        if (super.isConnected()) {
            return;
        }
        throw new IllegalStateException("Not connected");
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        int readServerResponse;
        if (!super.isConnected()) {
            return;
        }
        if (this.serverSocket != null) {
            sendCommand("QUIT");
            if (this.quitWait && (readServerResponse = readServerResponse()) != 221 && readServerResponse != -1 && this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.logger;
                mailLogger.fine("QUIT failed with " + readServerResponse);
            }
        }
        closeConnection();
    }

    public synchronized void connect(Socket socket) throws MessagingException {
        this.serverSocket = socket;
        super.connect();
    }

    protected OutputStream data() throws MessagingException {
        issueSendCommand("DATA", 354);
        this.dataStream = new SMTPOutputStream(this.serverOutput);
        return this.dataStream;
    }

    protected boolean ehlo(String str) throws MessagingException {
        sendCommand(str != null ? GeneratedOutlineSupport1.outline72("EHLO ", str) : "EHLO");
        int readServerResponse = readServerResponse();
        if (readServerResponse == 250) {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(this.lastServerResponse));
            this.extMap = new Hashtable();
            boolean z = true;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (z) {
                        z = false;
                    } else if (readLine.length() >= 5) {
                        String substring = readLine.substring(4);
                        int indexOf = substring.indexOf(32);
                        String str2 = "";
                        if (indexOf > 0) {
                            str2 = substring.substring(indexOf + 1);
                            substring = substring.substring(0, indexOf);
                        }
                        if (this.logger.isLoggable(Level.FINE)) {
                            this.logger.fine("Found extension \"" + substring + "\", arg \"" + str2 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                        }
                        this.extMap.put(substring.toUpperCase(Locale.ENGLISH), str2);
                    }
                } catch (IOException unused) {
                }
            }
        }
        return readServerResponse == 250;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        try {
            closeConnection();
        } catch (MessagingException unused) {
        }
    }

    protected void finishData() throws IOException, MessagingException {
        this.dataStream.ensureAtBOL();
        issueSendCommand(".", 250);
    }

    public synchronized String getAuthorizationId() {
        if (this.authorizationID == "UNKNOWN") {
            Session session = this.session;
            this.authorizationID = session.getProperty("mail." + this.name + ".sasl.authorizationid");
        }
        return this.authorizationID;
    }

    public String getExtensionParameter(String str) {
        Hashtable hashtable = this.extMap;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str.toUpperCase(Locale.ENGLISH));
    }

    public synchronized int getLastReturnCode() {
        return this.lastReturnCode;
    }

    public synchronized String getLastServerResponse() {
        return this.lastServerResponse;
    }

    public synchronized String getLocalHost() {
        if (this.localHostName == null || this.localHostName.length() <= 0) {
            Session session = this.session;
            this.localHostName = session.getProperty("mail." + this.name + ".localhost");
        }
        if (this.localHostName == null || this.localHostName.length() <= 0) {
            Session session2 = this.session;
            this.localHostName = session2.getProperty("mail." + this.name + ".localaddress");
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
        if ((this.localHostName == null || this.localHostName.length() <= 0) && this.serverSocket != null && this.serverSocket.isBound()) {
            InetAddress localAddress = this.serverSocket.getLocalAddress();
            this.localHostName = localAddress.getCanonicalHostName();
            if (this.localHostName == null) {
                this.localHostName = "[" + localAddress.getHostAddress() + "]";
            }
        }
        return this.localHostName;
    }

    public synchronized String getNTLMDomain() {
        if (this.ntlmDomain == "UNKNOWN") {
            Session session = this.session;
            this.ntlmDomain = session.getProperty("mail." + this.name + ".auth.ntlm.domain");
        }
        return this.ntlmDomain;
    }

    public synchronized boolean getNoopStrict() {
        return this.noopStrict;
    }

    public synchronized boolean getReportSuccess() {
        return this.reportSuccess;
    }

    public synchronized boolean getRequireStartTLS() {
        return this.requireStartTLS;
    }

    public synchronized boolean getSASLEnabled() {
        return this.enableSASL;
    }

    public synchronized String[] getSASLMechanisms() {
        if (this.saslMechanisms == UNKNOWN_SA) {
            ArrayList arrayList = new ArrayList(5);
            Session session = this.session;
            String property = session.getProperty("mail." + this.name + ".sasl.mechanisms");
            if (property != null && property.length() > 0) {
                if (this.logger.isLoggable(Level.FINE)) {
                    MailLogger mailLogger = this.logger;
                    mailLogger.fine("SASL mechanisms allowed: " + property);
                }
                StringTokenizer stringTokenizer = new StringTokenizer(property, " ,");
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() > 0) {
                        arrayList.add(nextToken);
                    }
                }
            }
            this.saslMechanisms = new String[arrayList.size()];
            arrayList.toArray(this.saslMechanisms);
        }
        if (this.saslMechanisms == null) {
            return null;
        }
        return (String[]) this.saslMechanisms.clone();
    }

    public synchronized String getSASLRealm() {
        if (this.saslRealm == "UNKNOWN") {
            Session session = this.session;
            this.saslRealm = session.getProperty("mail." + this.name + ".sasl.realm");
            if (this.saslRealm == null) {
                Session session2 = this.session;
                this.saslRealm = session2.getProperty("mail." + this.name + ".saslrealm");
            }
        }
        return this.saslRealm;
    }

    public synchronized boolean getStartTLS() {
        return this.useStartTLS;
    }

    public synchronized boolean getUseCanonicalHostName() {
        return this.useCanonicalHostName;
    }

    public synchronized boolean getUseRset() {
        return this.useRset;
    }

    protected void helo(String str) throws MessagingException {
        if (str != null) {
            issueCommand("HELO " + str, 250);
            return;
        }
        issueCommand("HELO", 250);
    }

    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        if (!super.isConnected()) {
            return false;
        }
        try {
            try {
                if (this.useRset) {
                    sendCommand("RSET");
                } else {
                    sendCommand("NOOP");
                }
                int readServerResponse = readServerResponse();
                if (readServerResponse >= 0 && (!this.noopStrict ? readServerResponse != 421 : readServerResponse == 250)) {
                    return true;
                }
                try {
                    closeConnection();
                } catch (MessagingException unused) {
                }
                return false;
            } catch (Exception unused2) {
                closeConnection();
                return false;
            }
        } catch (MessagingException unused3) {
            return false;
        }
    }

    public synchronized boolean isSSL() {
        return this.serverSocket instanceof SSLSocket;
    }

    public synchronized void issueCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        int readServerResponse = readServerResponse();
        if (i != -1 && readServerResponse != i) {
            throw new MessagingException(this.lastServerResponse);
        }
    }

    protected void mailFrom() throws MessagingException {
        InternetAddress localAddress;
        Address[] from;
        MimeMessage mimeMessage = this.message;
        String str = null;
        String envelopeFrom = mimeMessage instanceof SMTPMessage ? ((SMTPMessage) mimeMessage).getEnvelopeFrom() : null;
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            Session session = this.session;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.");
            outline107.append(this.name);
            outline107.append(".from");
            envelopeFrom = session.getProperty(outline107.toString());
        }
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            MimeMessage mimeMessage2 = this.message;
            if (mimeMessage2 != null && (from = mimeMessage2.getFrom()) != null && from.length > 0) {
                localAddress = from[0];
            } else {
                localAddress = InternetAddress.getLocalAddress(this.session);
            }
            if (localAddress != null) {
                envelopeFrom = localAddress.getAddress();
            } else {
                throw new MessagingException("can't determine local email address");
            }
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("MAIL FROM:");
        outline1072.append(normalizeAddress(envelopeFrom));
        String sb = outline1072.toString();
        if (supportsExtension("DSN")) {
            MimeMessage mimeMessage3 = this.message;
            String dSNRet = mimeMessage3 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage3).getDSNRet() : null;
            if (dSNRet == null) {
                Session session2 = this.session;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mail.");
                outline1073.append(this.name);
                outline1073.append(".dsn.ret");
                dSNRet = session2.getProperty(outline1073.toString());
            }
            if (dSNRet != null) {
                sb = GeneratedOutlineSupport1.outline75(sb, " RET=", dSNRet);
            }
        }
        if (supportsExtension("AUTH")) {
            MimeMessage mimeMessage4 = this.message;
            String submitter = mimeMessage4 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage4).getSubmitter() : null;
            if (submitter == null) {
                Session session3 = this.session;
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("mail.");
                outline1074.append(this.name);
                outline1074.append(".submitter");
                submitter = session3.getProperty(outline1074.toString());
            }
            if (submitter != null) {
                try {
                    sb = sb + " AUTH=" + xtext(submitter);
                } catch (IllegalArgumentException e) {
                    if (this.logger.isLoggable(Level.FINE)) {
                        this.logger.log(Level.FINE, "ignoring invalid submitter: " + submitter, (Throwable) e);
                    }
                }
            }
        }
        MimeMessage mimeMessage5 = this.message;
        if (mimeMessage5 instanceof SMTPMessage) {
            str = ((SMTPMessage) mimeMessage5).getMailExtension();
        }
        if (str == null) {
            Session session4 = this.session;
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("mail.");
            outline1075.append(this.name);
            outline1075.append(".mailextension");
            str = session4.getProperty(outline1075.toString());
        }
        if (str != null && str.length() > 0) {
            sb = GeneratedOutlineSupport1.outline75(sb, " ", str);
        }
        try {
            issueSendCommand(sb, 250);
        } catch (SMTPSendFailedException e2) {
            int returnCode = e2.getReturnCode();
            if (returnCode == 501 || returnCode == 503 || returnCode == 553 || returnCode == 550 || returnCode == 551) {
                try {
                    e2.setNextException(new SMTPSenderFailedException(new InternetAddress(envelopeFrom), sb, returnCode, e2.getMessage()));
                } catch (AddressException unused) {
                }
            }
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Transport
    public void notifyTransportListeners(int i, Address[] addressArr, Address[] addressArr2, Address[] addressArr3, Message message) {
        if (!this.notificationDone) {
            super.notifyTransportListeners(i, addressArr, addressArr2, addressArr3, message);
            this.notificationDone = true;
        }
    }

    @Override // javax.mail.Service
    protected synchronized boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        Session session = this.session;
        boolean z = false;
        boolean booleanSessionProperty = PropUtil.getBooleanSessionProperty(session, "mail." + this.name + ".auth", false);
        if (booleanSessionProperty && (str2 == null || str3 == null)) {
            this.logger.fine("need username and password for authentication");
            return false;
        }
        Session session2 = this.session;
        boolean booleanSessionProperty2 = PropUtil.getBooleanSessionProperty(session2, "mail." + this.name + ".ehlo", true);
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            mailLogger.fine("useEhlo " + booleanSessionProperty2 + ", useAuth " + booleanSessionProperty);
        }
        if (i == -1) {
            Session session3 = this.session;
            i = PropUtil.getIntSessionProperty(session3, "mail." + this.name + ".port", -1);
        }
        if (i == -1) {
            i = this.defaultPort;
        }
        if (str == null || str.length() == 0) {
            str = AndroidInfoHelpers.DEVICE_LOCALHOST;
        }
        if (this.serverSocket != null) {
            openServer();
        } else {
            openServer(str, i);
        }
        if (booleanSessionProperty2) {
            z = ehlo(getLocalHost());
        }
        if (!z) {
            helo(getLocalHost());
        }
        if (this.useStartTLS || this.requireStartTLS) {
            if (this.serverSocket instanceof SSLSocket) {
                this.logger.fine("STARTTLS requested but already using SSL");
            } else if (supportsExtension("STARTTLS")) {
                startTLS();
                ehlo(getLocalHost());
            } else if (this.requireStartTLS) {
                this.logger.fine("STARTTLS required but not supported");
                throw new MessagingException("STARTTLS is required but host does not support STARTTLS");
            }
        }
        if ((!booleanSessionProperty && (str2 == null || str3 == null)) || (!supportsExtension("AUTH") && !supportsExtension("AUTH=LOGIN"))) {
            return true;
        }
        boolean authenticate = authenticate(str2, str3);
        if (!authenticate) {
            try {
                closeConnection();
            } catch (MessagingException unused) {
            }
        }
        return authenticate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:154:0x016b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0130  */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable, javax.mail.SendFailedException] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void rcptTo() throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.rcptTo():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int readServerResponse() throws javax.mail.MessagingException {
        /*
            r6 = this;
            java.lang.String r0 = "close failed"
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 100
            r1.<init>(r2)
        L9:
            r2 = 0
            com.sun.mail.util.LineInputStream r3 = r6.lineInputStream     // Catch: java.io.IOException -> L79
            java.lang.String r3 = r3.readLine()     // Catch: java.io.IOException -> L79
            r4 = -1
            if (r3 != 0) goto L2d
            java.lang.String r0 = r1.toString()     // Catch: java.io.IOException -> L79
            int r1 = r0.length()     // Catch: java.io.IOException -> L79
            if (r1 != 0) goto L1f
            java.lang.String r0 = "[EOF]"
        L1f:
            r6.lastServerResponse = r0     // Catch: java.io.IOException -> L79
            r6.lastReturnCode = r4     // Catch: java.io.IOException -> L79
            com.sun.mail.util.MailLogger r1 = r6.logger     // Catch: java.io.IOException -> L79
            java.util.logging.Level r3 = java.util.logging.Level.FINE     // Catch: java.io.IOException -> L79
            java.lang.String r5 = "EOF: {0}"
            r1.log(r3, r5, r0)     // Catch: java.io.IOException -> L79
            return r4
        L2d:
            r1.append(r3)     // Catch: java.io.IOException -> L79
            java.lang.String r5 = "\n"
            r1.append(r5)     // Catch: java.io.IOException -> L79
            boolean r3 = r6.isNotLastLine(r3)     // Catch: java.io.IOException -> L79
            if (r3 != 0) goto L9
            java.lang.String r1 = r1.toString()     // Catch: java.io.IOException -> L79
            int r3 = r1.length()
            r5 = 3
            if (r3 < r5) goto L68
            java.lang.String r2 = r1.substring(r2, r5)     // Catch: java.lang.StringIndexOutOfBoundsException -> L4f java.lang.NumberFormatException -> L5c
            int r0 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.StringIndexOutOfBoundsException -> L4f java.lang.NumberFormatException -> L5c
            goto L69
        L4f:
            r6.close()     // Catch: javax.mail.MessagingException -> L53
            goto L68
        L53:
            r2 = move-exception
            com.sun.mail.util.MailLogger r3 = r6.logger
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r0, r2)
            goto L68
        L5c:
            r6.close()     // Catch: javax.mail.MessagingException -> L60
            goto L68
        L60:
            r2 = move-exception
            com.sun.mail.util.MailLogger r3 = r6.logger
            java.util.logging.Level r5 = java.util.logging.Level.FINE
            r3.log(r5, r0, r2)
        L68:
            r0 = r4
        L69:
            if (r0 != r4) goto L74
            com.sun.mail.util.MailLogger r2 = r6.logger
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            java.lang.String r4 = "bad server response: {0}"
            r2.log(r3, r4, r1)
        L74:
            r6.lastServerResponse = r1
            r6.lastReturnCode = r0
            return r0
        L79:
            r0 = move-exception
            com.sun.mail.util.MailLogger r1 = r6.logger
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            java.lang.String r4 = "exception reading response"
            r1.log(r3, r4, r0)
            java.lang.String r1 = ""
            r6.lastServerResponse = r1
            r6.lastReturnCode = r2
            javax.mail.MessagingException r1 = new javax.mail.MessagingException
            java.lang.String r2 = "Exception reading response"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.readServerResponse():int");
    }

    public boolean sasllogin(String[] strArr, String str, String str2, String str3, String str4) throws MessagingException {
        String str5;
        ArrayList arrayList;
        String str6;
        if (this.useCanonicalHostName) {
            str5 = this.serverSocket.getInetAddress().getCanonicalHostName();
        } else {
            str5 = this.host;
        }
        if (this.saslAuthenticator == null) {
            try {
                this.saslAuthenticator = (SaslAuthenticator) Class.forName("com.sun.mail.smtp.SMTPSaslAuthenticator").getConstructor(SMTPTransport.class, String.class, Properties.class, MailLogger.class, String.class).newInstance(this, this.name, this.session.getProperties(), this.logger, str5);
            } catch (Exception e) {
                this.logger.log(Level.FINE, "Can't load SASL authenticator", (Throwable) e);
                return false;
            }
        }
        if (strArr != null && strArr.length > 0) {
            arrayList = new ArrayList(strArr.length);
            for (int i = 0; i < strArr.length; i++) {
                if (supportsAuthentication(strArr[i])) {
                    arrayList.add(strArr[i]);
                }
            }
        } else {
            arrayList = new ArrayList();
            Hashtable hashtable = this.extMap;
            if (hashtable != null && (str6 = (String) hashtable.get("AUTH")) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(str6);
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add(stringTokenizer.nextToken());
                }
            }
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        try {
            if (this.noauthdebug && isTracing()) {
                this.logger.fine("SASL AUTH command trace suppressed");
                suspendTracing();
            }
            return this.saslAuthenticator.authenticate(strArr2, str, str2, str3, str4);
        } finally {
            resumeTracing();
        }
    }

    protected void sendCommand(String str) throws MessagingException {
        sendCommand(ASCIIUtility.getBytes(str));
    }

    @Override // javax.mail.Transport
    public synchronized void sendMessage(Message message, Address[] addressArr) throws MessagingException, SendFailedException {
        sendMessageStart(message != null ? message.getSubject() : "");
        checkConnected();
        if (message instanceof MimeMessage) {
            for (int i = 0; i < addressArr.length; i++) {
                if (!(addressArr[i] instanceof InternetAddress)) {
                    throw new MessagingException(addressArr[i] + " is not an InternetAddress");
                }
            }
            if (addressArr.length != 0) {
                this.message = (MimeMessage) message;
                this.addresses = addressArr;
                this.validUnsentAddr = addressArr;
                expandGroups();
                boolean allow8bitMIME = message instanceof SMTPMessage ? ((SMTPMessage) message).getAllow8bitMIME() : false;
                if (!allow8bitMIME) {
                    allow8bitMIME = PropUtil.getBooleanSessionProperty(this.session, "mail." + this.name + ".allow8bitmime", false);
                }
                if (this.logger.isLoggable(Level.FINE)) {
                    this.logger.fine("use8bit " + allow8bitMIME);
                }
                if (allow8bitMIME && supportsExtension("8BITMIME") && convertTo8Bit(this.message)) {
                    try {
                        this.message.saveChanges();
                    } catch (MessagingException unused) {
                    }
                }
                try {
                    mailFrom();
                    rcptTo();
                    this.message.writeTo(data(), ignoreList);
                    finishData();
                    if (!this.sendPartiallyFailed) {
                        this.logger.fine("message successfully delivered to mail server");
                        notifyTransportListeners(1, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                        this.invalidAddr = null;
                        this.validUnsentAddr = null;
                        this.validSentAddr = null;
                        this.addresses = null;
                        this.message = null;
                        this.exception = null;
                        this.sendPartiallyFailed = false;
                        this.notificationDone = false;
                        sendMessageEnd();
                    } else {
                        this.logger.fine("Sending partially failed because of invalid destination addresses");
                        notifyTransportListeners(3, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                        throw new SMTPSendFailedException(".", this.lastReturnCode, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
                    }
                } catch (IOException e) {
                    this.logger.log(Level.FINE, "IOException while sending, closing", (Throwable) e);
                    try {
                        closeConnection();
                    } catch (MessagingException unused2) {
                    }
                    addressesFailed();
                    notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                    throw new MessagingException("IOException while sending message", e);
                } catch (MessagingException e2) {
                    this.logger.log(Level.FINE, "MessagingException while sending", (Throwable) e2);
                    if (e2.getNextException() instanceof IOException) {
                        this.logger.fine("nested IOException, closing");
                        try {
                            closeConnection();
                        } catch (MessagingException unused3) {
                        }
                    }
                    addressesFailed();
                    notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                    throw e2;
                }
            } else {
                throw new SendFailedException("No recipient addresses");
            }
        } else {
            this.logger.fine("Can only send RFC822 msgs");
            throw new MessagingException("SMTP can only send RFC822 messages");
        }
    }

    public synchronized void setAuthorizationID(String str) {
        this.authorizationID = str;
    }

    public synchronized void setLocalHost(String str) {
        this.localHostName = str;
    }

    public synchronized void setNTLMDomain(String str) {
        this.ntlmDomain = str;
    }

    public synchronized void setNoopStrict(boolean z) {
        this.noopStrict = z;
    }

    public synchronized void setReportSuccess(boolean z) {
        this.reportSuccess = z;
    }

    public synchronized void setRequireStartTLS(boolean z) {
        this.requireStartTLS = z;
    }

    public synchronized void setSASLEnabled(boolean z) {
        this.enableSASL = z;
    }

    public synchronized void setSASLMechanisms(String[] strArr) {
        if (strArr != null) {
            strArr = (String[]) strArr.clone();
        }
        this.saslMechanisms = strArr;
    }

    public synchronized void setSASLRealm(String str) {
        this.saslRealm = str;
    }

    public synchronized void setStartTLS(boolean z) {
        this.useStartTLS = z;
    }

    public synchronized void setUseCanonicalHostName(boolean z) {
        this.useCanonicalHostName = z;
    }

    public synchronized void setUseRset(boolean z) {
        this.useRset = z;
    }

    public synchronized int simpleCommand(String str) throws MessagingException {
        sendCommand(str);
        return readServerResponse();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startTLS() throws MessagingException {
        issueCommand("STARTTLS", 220);
        try {
            Socket socket = this.serverSocket;
            String str = this.host;
            Properties properties = this.session.getProperties();
            this.serverSocket = SocketFetcher.startTLS(socket, str, properties, "mail." + this.name);
            initStreams();
        } catch (IOException e) {
            closeConnection();
            throw new MessagingException("Could not convert socket to TLS", e);
        }
    }

    protected boolean supportsAuthentication(String str) {
        String str2;
        Hashtable hashtable = this.extMap;
        if (hashtable == null || (str2 = (String) hashtable.get("AUTH")) == null) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str2);
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equalsIgnoreCase(str)) {
                return true;
            }
        }
        if (!str.equalsIgnoreCase("LOGIN") || !supportsExtension("AUTH=LOGIN")) {
            return false;
        }
        this.logger.fine("use AUTH=LOGIN hack");
        return true;
    }

    public boolean supportsExtension(String str) {
        Hashtable hashtable = this.extMap;
        return (hashtable == null || hashtable.get(str.toUpperCase(Locale.ENGLISH)) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SMTPTransport(Session session, URLName uRLName, String str, boolean z) {
        super(session, uRLName);
        this.name = "smtp";
        this.defaultPort = 25;
        this.isSSL = false;
        this.sendPartiallyFailed = false;
        this.authenticators = new HashMap();
        this.quitWait = false;
        this.saslRealm = "UNKNOWN";
        this.authorizationID = "UNKNOWN";
        this.enableSASL = false;
        this.useCanonicalHostName = false;
        this.saslMechanisms = UNKNOWN_SA;
        this.ntlmDomain = "UNKNOWN";
        this.noopStrict = true;
        this.noauthdebug = true;
        this.logger = new MailLogger(getClass(), "DEBUG SMTP", session);
        this.traceLogger = this.logger.getSubLogger("protocol", null);
        this.noauthdebug = !PropUtil.getBooleanSessionProperty(session, "mail.debug.auth", false);
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        z = !z ? GeneratedOutlineSupport1.outline191("mail.", str, ".ssl.enable", session, false) : z;
        if (z) {
            this.defaultPort = 465;
        } else {
            this.defaultPort = 25;
        }
        this.isSSL = z;
        this.quitWait = GeneratedOutlineSupport1.outline191("mail.", str, ".quitwait", session, true);
        this.reportSuccess = GeneratedOutlineSupport1.outline191("mail.", str, ".reportsuccess", session, false);
        this.useStartTLS = GeneratedOutlineSupport1.outline191("mail.", str, ".starttls.enable", session, false);
        this.requireStartTLS = GeneratedOutlineSupport1.outline191("mail.", str, ".starttls.required", session, false);
        this.useRset = GeneratedOutlineSupport1.outline191("mail.", str, ".userset", session, false);
        this.noopStrict = GeneratedOutlineSupport1.outline191("mail.", str, ".noop.strict", session, true);
        this.enableSASL = GeneratedOutlineSupport1.outline191("mail.", str, ".sasl.enable", session, false);
        if (this.enableSASL) {
            this.logger.config("enable SASL");
        }
        this.useCanonicalHostName = GeneratedOutlineSupport1.outline191("mail.", str, ".sasl.usecanonicalhostname", session, false);
        if (this.useCanonicalHostName) {
            this.logger.config("use canonical host name");
        }
        Authenticator[] authenticatorArr = {new LoginAuthenticator(), new PlainAuthenticator(), new DigestMD5Authenticator(), new NtlmAuthenticator()};
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < authenticatorArr.length; i++) {
            this.authenticators.put(authenticatorArr[i].getMechanism(), authenticatorArr[i]);
            stringBuffer.append(authenticatorArr[i].getMechanism());
            stringBuffer.append(Chars.SPACE);
        }
        this.defaultAuthenticationMechanisms = stringBuffer.toString();
    }

    private void sendCommand(byte[] bArr) throws MessagingException {
        try {
            this.serverOutput.write(bArr);
            this.serverOutput.write(CRLF);
            this.serverOutput.flush();
        } catch (IOException e) {
            throw new MessagingException("Can't send command to SMTP host", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int simpleCommand(byte[] bArr) throws MessagingException {
        sendCommand(bArr);
        return readServerResponse();
    }

    private void openServer() throws MessagingException {
        this.host = "UNKNOWN";
        try {
            int port = this.serverSocket.getPort();
            this.host = this.serverSocket.getInetAddress().getHostName();
            if (this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.logger;
                mailLogger.fine("starting protocol to host \"" + this.host + "\", port " + port);
            }
            initStreams();
            int readServerResponse = readServerResponse();
            if (readServerResponse != 220) {
                this.serverSocket.close();
                this.serverSocket = null;
                this.serverOutput = null;
                this.serverInput = null;
                this.lineInputStream = null;
                if (this.logger.isLoggable(Level.FINE)) {
                    MailLogger mailLogger2 = this.logger;
                    mailLogger2.fine("got bad greeting from host \"" + this.host + "\", port: " + port + ", response: " + readServerResponse + "\n");
                }
                throw new MessagingException("Got bad greeting from SMTP host: " + this.host + ", port: " + port + ", response: " + readServerResponse);
            } else if (!this.logger.isLoggable(Level.FINE)) {
            } else {
                MailLogger mailLogger3 = this.logger;
                mailLogger3.fine("protocol started to host \"" + this.host + "\", port: " + port + "\n");
            }
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not start protocol to SMTP host: ");
            outline107.append(this.host);
            outline107.append(", port: ");
            outline107.append(-1);
            throw new MessagingException(outline107.toString(), e);
        }
    }
}
