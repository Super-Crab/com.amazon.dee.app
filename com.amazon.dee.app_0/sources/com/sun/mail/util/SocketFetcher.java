package com.sun.mail.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes3.dex */
public class SocketFetcher {
    private static MailLogger logger = new MailLogger(SocketFetcher.class, "socket", "DEBUG SocketFetcher", PropUtil.getBooleanSystemProperty("mail.socket.debug", false), System.out);

    private SocketFetcher() {
    }

    private static void checkServerIdentity(String str, SSLSocket sSLSocket) throws IOException {
        try {
            Certificate[] peerCertificates = sSLSocket.getSession().getPeerCertificates();
            if (peerCertificates != null && peerCertificates.length > 0 && (peerCertificates[0] instanceof X509Certificate)) {
                if (matchCert(str, (X509Certificate) peerCertificates[0])) {
                    return;
                }
            }
            sSLSocket.close();
            throw new IOException(GeneratedOutlineSupport1.outline72("Can't verify identity of server: ", str));
        } catch (SSLPeerUnverifiedException e) {
            sSLSocket.close();
            IOException iOException = new IOException(GeneratedOutlineSupport1.outline72("Can't verify identity of server: ", str));
            iOException.initCause(e);
            throw iOException;
        }
    }

    private static void configureSSLSocket(Socket socket, String str, Properties properties, String str2, SocketFactory socketFactory) throws IOException {
        if (!(socket instanceof SSLSocket)) {
            return;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        String property = properties.getProperty(str2 + ".ssl.protocols", null);
        if (property != null) {
            sSLSocket.setEnabledProtocols(stringArray(property));
        } else {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            if (logger.isLoggable(Level.FINER)) {
                MailLogger mailLogger = logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SSL enabled protocols before ");
                outline107.append(Arrays.asList(enabledProtocols));
                mailLogger.finer(outline107.toString());
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < enabledProtocols.length; i++) {
                if (enabledProtocols[i] != null && !enabledProtocols[i].startsWith("SSL")) {
                    arrayList.add(enabledProtocols[i]);
                }
            }
            sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        String property2 = properties.getProperty(str2 + ".ssl.ciphersuites", null);
        if (property2 != null) {
            sSLSocket.setEnabledCipherSuites(stringArray(property2));
        }
        if (logger.isLoggable(Level.FINER)) {
            MailLogger mailLogger2 = logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SSL enabled protocols after ");
            outline1072.append(Arrays.asList(sSLSocket.getEnabledProtocols()));
            mailLogger2.finer(outline1072.toString());
            MailLogger mailLogger3 = logger;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SSL enabled ciphers after ");
            outline1073.append(Arrays.asList(sSLSocket.getEnabledCipherSuites()));
            mailLogger3.finer(outline1073.toString());
        }
        sSLSocket.startHandshake();
        if (PropUtil.getBooleanProperty(properties, str2 + ".ssl.checkserveridentity", false)) {
            checkServerIdentity(str, sSLSocket);
        }
        if (!(socketFactory instanceof MailSSLSocketFactory) || ((MailSSLSocketFactory) socketFactory).isServerTrusted(str, sSLSocket)) {
            return;
        }
        sSLSocket.close();
        throw new IOException(GeneratedOutlineSupport1.outline72("Server is not trusted: ", str));
    }

    private static Socket createSocket(InetAddress inetAddress, int i, String str, int i2, int i3, int i4, Properties properties, String str2, SocketFactory socketFactory, boolean z) throws IOException {
        String str3;
        String str4;
        Socket socket;
        SSLSocketFactory sSLSocketFactory;
        String str5;
        SSLSocketFactory sSLSocketFactory2 = socketFactory;
        if (logger.isLoggable(Level.FINEST)) {
            MailLogger mailLogger = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("create socket: prefix ");
            sb.append(str2);
            sb.append(", localaddr ");
            sb.append(inetAddress);
            sb.append(", localport ");
            sb.append(i);
            sb.append(", host ");
            sb.append(str);
            sb.append(", port ");
            GeneratedOutlineSupport1.outline175(sb, i2, ", connection timeout ", i3, ", timeout ");
            sb.append(i4);
            sb.append(", socket factory ");
            sb.append(sSLSocketFactory2);
            sb.append(", useSSL ");
            sb.append(z);
            mailLogger.finest(sb.toString());
        }
        String property = properties.getProperty(str2 + ".socks.host", null);
        int i5 = PhotoshopDirectory.TAG_COUNT_INFORMATION;
        if (property != null) {
            int indexOf = property.indexOf(58);
            if (indexOf >= 0) {
                property = property.substring(0, indexOf);
                try {
                    i5 = Integer.parseInt(property.substring(indexOf + 1));
                } catch (NumberFormatException unused) {
                }
            }
            i5 = PropUtil.getIntProperty(properties, str2 + ".socks.port", i5);
            String str6 = "Using SOCKS host, port: " + property + ", " + i5;
            if (logger.isLoggable(Level.FINER)) {
                MailLogger mailLogger2 = logger;
                StringBuilder sb2 = new StringBuilder();
                str5 = str6;
                sb2.append("socks host ");
                sb2.append(property);
                sb2.append(", port ");
                sb2.append(i5);
                mailLogger2.finer(sb2.toString());
            } else {
                str5 = str6;
            }
            str3 = str5;
        } else {
            str3 = null;
        }
        Socket createSocket = (sSLSocketFactory2 == null || (sSLSocketFactory2 instanceof SSLSocketFactory)) ? null : socketFactory.createSocket();
        if (createSocket != null) {
            str4 = str3;
        } else if (property != null) {
            str4 = str3;
            createSocket = new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(property, i5)));
        } else {
            str4 = str3;
            if (PropUtil.getBooleanProperty(properties, str2 + ".usesocketchannels", false)) {
                logger.finer("using SocketChannels");
                createSocket = SocketChannel.open().socket();
            } else {
                createSocket = new Socket();
            }
        }
        if (i4 >= 0) {
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("set socket read timeout " + i4);
            }
            createSocket.setSoTimeout(i4);
        }
        int intProperty = PropUtil.getIntProperty(properties, str2 + ".writetimeout", -1);
        if (intProperty != -1) {
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("set socket write timeout " + intProperty);
            }
            socket = new WriteTimeoutSocket(createSocket, intProperty);
        } else {
            socket = createSocket;
        }
        if (inetAddress != null) {
            socket.bind(new InetSocketAddress(inetAddress, i));
        }
        try {
            logger.finest("connecting...");
            if (i3 >= 0) {
                socket.connect(new InetSocketAddress(str, i2), i3);
            } else {
                socket.connect(new InetSocketAddress(str, i2));
            }
            logger.finest("success!");
            if (z && !(socket instanceof SSLSocket)) {
                String property2 = properties.getProperty(str2 + ".ssl.trust");
                if (property2 != null) {
                    try {
                        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
                        if (property2.equals("*")) {
                            mailSSLSocketFactory.setTrustAllHosts(true);
                        } else {
                            mailSSLSocketFactory.setTrustedHosts(property2.split("\\s+"));
                        }
                        sSLSocketFactory = mailSSLSocketFactory;
                    } catch (GeneralSecurityException e) {
                        IOException iOException = new IOException("Can't create MailSSLSocketFactory");
                        iOException.initCause(e);
                        throw iOException;
                    }
                } else if (sSLSocketFactory2 instanceof SSLSocketFactory) {
                    sSLSocketFactory = (SSLSocketFactory) sSLSocketFactory2;
                } else {
                    sSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                }
                socket = sSLSocketFactory.createSocket(socket, str, i2, true);
                sSLSocketFactory2 = sSLSocketFactory;
            }
            configureSSLSocket(socket, str, properties, str2, sSLSocketFactory2);
            return socket;
        } catch (IOException e2) {
            logger.log(Level.FINEST, "connection failed", (Throwable) e2);
            throw new SocketConnectException(str4, e2, str, i2, i3);
        }
    }

    private static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.mail.util.SocketFetcher.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.net.Socket getSocket(java.lang.String r22, int r23, java.util.Properties r24, java.lang.String r25, boolean r26) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.SocketFetcher.getSocket(java.lang.String, int, java.util.Properties, java.lang.String, boolean):java.net.Socket");
    }

    private static SocketFactory getSocketFactory(String str) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        ClassLoader contextClassLoader = getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                cls = Class.forName(str, false, contextClassLoader);
            } catch (ClassNotFoundException unused) {
            }
        }
        if (cls == null) {
            cls = Class.forName(str);
        }
        return (SocketFactory) cls.getMethod("getDefault", new Class[0]).invoke(new Object(), new Object[0]);
    }

    private static boolean matchCert(String str, X509Certificate x509Certificate) {
        if (logger.isLoggable(Level.FINER)) {
            logger.finer("matchCert server " + str + ", cert " + x509Certificate);
        }
        try {
            Class<?> cls = Class.forName("sun.security.util.HostnameChecker");
            Object invoke = cls.getMethod("getInstance", Byte.TYPE).invoke(new Object(), (byte) 2);
            if (logger.isLoggable(Level.FINER)) {
                logger.finer("using sun.security.util.HostnameChecker");
            }
            try {
                cls.getMethod("match", String.class, X509Certificate.class).invoke(invoke, str, x509Certificate);
                return true;
            } catch (InvocationTargetException e) {
                logger.log(Level.FINER, "HostnameChecker FAIL", (Throwable) e);
                return false;
            }
        } catch (Exception e2) {
            logger.log(Level.FINER, "NO sun.security.util.HostnameChecker", (Throwable) e2);
            try {
                Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                if (subjectAlternativeNames != null) {
                    boolean z = false;
                    for (List<?> list : subjectAlternativeNames) {
                        if (((Integer) list.get(0)).intValue() == 2) {
                            String str2 = (String) list.get(1);
                            if (logger.isLoggable(Level.FINER)) {
                                logger.finer("found name: " + str2);
                            }
                            if (matchServer(str, str2)) {
                                return true;
                            }
                            z = true;
                        }
                    }
                    if (z) {
                        return false;
                    }
                }
            } catch (CertificateParsingException unused) {
            }
            Matcher matcher = Pattern.compile("CN=([^,]*)").matcher(x509Certificate.getSubjectX500Principal().getName());
            return matcher.find() && matchServer(str, matcher.group(1).trim());
        }
    }

    private static boolean matchServer(String str, String str2) {
        int length;
        if (logger.isLoggable(Level.FINER)) {
            MailLogger mailLogger = logger;
            mailLogger.finer("match server " + str + " with " + str2);
        }
        if (str2.startsWith("*.")) {
            String substring = str2.substring(2);
            if (substring.length() == 0 || (length = str.length() - substring.length()) < 1) {
                return false;
            }
            return str.charAt(length + (-1)) == '.' && str.regionMatches(true, length, substring, 0, substring.length());
        }
        return str.equalsIgnoreCase(str2);
    }

    @Deprecated
    public static Socket startTLS(Socket socket) throws IOException {
        return startTLS(socket, new Properties(), "socket");
    }

    private static String[] stringArray(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Deprecated
    public static Socket startTLS(Socket socket, Properties properties, String str) throws IOException {
        return startTLS(socket, socket.getInetAddress().getHostName(), properties, str);
    }

    public static Socket startTLS(Socket socket, String str, Properties properties, String str2) throws IOException {
        SocketFactory socketFactory;
        int port = socket.getPort();
        if (logger.isLoggable(Level.FINER)) {
            logger.finer("startTLS host " + str + ", port " + port);
        }
        String str3 = "unknown socket factory";
        try {
            Object obj = properties.get(str2 + ".ssl.socketFactory");
            SSLSocketFactory sSLSocketFactory = null;
            sSLSocketFactory = null;
            if (obj instanceof SocketFactory) {
                socketFactory = (SocketFactory) obj;
                str3 = "SSL socket factory instance " + socketFactory;
            } else {
                socketFactory = null;
            }
            if (socketFactory == null) {
                String property = properties.getProperty(str2 + ".ssl.socketFactory.class");
                str3 = "SSL socket factory class " + property;
                socketFactory = getSocketFactory(property);
            }
            if (socketFactory != null && (socketFactory instanceof SSLSocketFactory)) {
                sSLSocketFactory = socketFactory;
            }
            if (sSLSocketFactory == null) {
                Object obj2 = properties.get(str2 + ".socketFactory");
                if (obj2 instanceof SocketFactory) {
                    socketFactory = (SocketFactory) obj2;
                    str3 = "socket factory instance " + socketFactory;
                }
                if (socketFactory == null) {
                    String property2 = properties.getProperty(str2 + ".socketFactory.class");
                    str3 = "socket factory class " + property2;
                    socketFactory = getSocketFactory(property2);
                }
                if (socketFactory != null && (socketFactory instanceof SSLSocketFactory)) {
                    sSLSocketFactory = socketFactory;
                }
            }
            MailSSLSocketFactory mailSSLSocketFactory = sSLSocketFactory;
            if (sSLSocketFactory == null) {
                String property3 = properties.getProperty(str2 + ".ssl.trust");
                if (property3 != null) {
                    try {
                        MailSSLSocketFactory mailSSLSocketFactory2 = new MailSSLSocketFactory();
                        if (property3.equals("*")) {
                            mailSSLSocketFactory2.setTrustAllHosts(true);
                        } else {
                            mailSSLSocketFactory2.setTrustedHosts(property3.split("\\s+"));
                        }
                        mailSSLSocketFactory = mailSSLSocketFactory2;
                    } catch (GeneralSecurityException e) {
                        IOException iOException = new IOException("Can't create MailSSLSocketFactory");
                        iOException.initCause(e);
                        throw iOException;
                    }
                } else {
                    mailSSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                }
            }
            Socket createSocket = mailSSLSocketFactory.createSocket(socket, str, port, true);
            configureSSLSocket(createSocket, str, properties, str2, mailSSLSocketFactory);
            return createSocket;
        } catch (Exception e2) {
            e = e2;
            if (e instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) e).getTargetException();
                if (targetException instanceof Exception) {
                    e = (Exception) targetException;
                }
            }
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Exception in startTLS using ", str3, ": host, port: ", str, ", ");
            outline116.append(port);
            outline116.append("; Exception: ");
            outline116.append(e);
            IOException iOException2 = new IOException(outline116.toString());
            iOException2.initCause(e);
            throw iOException2;
        }
    }

    public static Socket getSocket(String str, int i, Properties properties, String str2) throws IOException {
        return getSocket(str, i, properties, str2, false);
    }
}
