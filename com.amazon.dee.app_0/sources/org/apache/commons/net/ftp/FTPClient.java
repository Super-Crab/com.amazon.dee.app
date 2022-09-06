package org.apache.commons.net.ftp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.commons.net.io.SocketInputStream;
import org.apache.commons.net.io.SocketOutputStream;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
import org.apache.commons.net.io.Util;
/* loaded from: classes4.dex */
public class FTPClient extends FTP implements Configurable {
    public static final int ACTIVE_LOCAL_DATA_CONNECTION_MODE = 0;
    public static final int ACTIVE_REMOTE_DATA_CONNECTION_MODE = 1;
    public static final int PASSIVE_LOCAL_DATA_CONNECTION_MODE = 2;
    public static final int PASSIVE_REMOTE_DATA_CONNECTION_MODE = 3;
    private int __bufferSize;
    private FTPClientConfig __configuration;
    private int __dataConnectionMode;
    private int __dataTimeout;
    private FTPFileEntryParser __entryParser;
    private int __fileFormat;
    private int __fileStructure;
    private int __fileTransferMode;
    private int __fileType;
    private FTPFileEntryParserFactory __parserFactory;
    private String __passiveHost;
    private int __passivePort;
    private boolean __remoteVerificationEnabled;
    private long __restartOffset;
    private String __systemName;

    public FTPClient() {
        __initDefaults();
        this.__dataTimeout = -1;
        this.__remoteVerificationEnabled = true;
        this.__parserFactory = new DefaultFTPFileEntryParserFactory();
        this.__configuration = null;
    }

    private void __initDefaults() {
        this.__dataConnectionMode = 0;
        this.__passiveHost = null;
        this.__passivePort = -1;
        this.__fileType = 0;
        this.__fileStructure = 7;
        this.__fileFormat = 4;
        this.__fileTransferMode = 10;
        this.__restartOffset = 0L;
        this.__systemName = null;
        this.__entryParser = null;
        this.__bufferSize = 1024;
    }

    private void __parsePassiveModeReply(String str) throws MalformedServerReplyException {
        String trim = str.substring(str.indexOf(40) + 1, str.indexOf(41)).trim();
        StringBuffer stringBuffer = new StringBuffer(24);
        int indexOf = trim.indexOf(44);
        int i = 0;
        stringBuffer.append(trim.substring(0, indexOf));
        while (i < 3) {
            stringBuffer.append('.');
            int i2 = indexOf + 1;
            int indexOf2 = trim.indexOf(44, i2);
            stringBuffer.append(trim.substring(i2, indexOf2));
            i++;
            indexOf = indexOf2;
        }
        int i3 = indexOf + 1;
        int indexOf3 = trim.indexOf(44, i3);
        String substring = trim.substring(i3, indexOf3);
        String substring2 = trim.substring(indexOf3 + 1);
        try {
            int parseInt = Integer.parseInt(substring);
            int parseInt2 = Integer.parseInt(substring2);
            this.__passiveHost = stringBuffer.toString();
            this.__passivePort = parseInt2 | (parseInt << 8);
        } catch (NumberFormatException unused) {
            throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Could not parse passive host information.\nServer Reply: ", trim));
        }
    }

    private String __parsePathname(String str) {
        int indexOf = str.indexOf(34) + 1;
        return str.substring(indexOf, str.indexOf(34, indexOf));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.commons.net.io.ToNetASCIIOutputStream] */
    private boolean __storeFile(int i, String str, InputStream inputStream) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(i, str);
        if (_openDataConnection_ == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(_openDataConnection_.getOutputStream(), getBufferSize());
        if (this.__fileType == 0) {
            bufferedOutputStream = new ToNetASCIIOutputStream(bufferedOutputStream);
        }
        try {
            Util.copyStream(inputStream, bufferedOutputStream, getBufferSize(), -1L, null, false);
            bufferedOutputStream.close();
            _openDataConnection_.close();
            return completePendingCommand();
        } catch (IOException e) {
            try {
                _openDataConnection_.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    private OutputStream __storeFileStream(int i, String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(i, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        OutputStream outputStream = _openDataConnection_.getOutputStream();
        if (this.__fileType == 0) {
            outputStream = new ToNetASCIIOutputStream(new BufferedOutputStream(outputStream, getBufferSize()));
        }
        return new SocketOutputStream(_openDataConnection_, outputStream);
    }

    private boolean restart(long j) throws IOException {
        this.__restartOffset = 0L;
        return FTPReply.isPositiveIntermediate(rest(Long.toString(j)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.telnet.TelnetClient, org.apache.commons.net.telnet.Telnet, org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        __initDefaults();
    }

    protected Socket _openDataConnection_(int i, String str) throws IOException {
        Socket socket;
        int i2 = this.__dataConnectionMode;
        if (i2 == 0 || i2 == 2) {
            if (this.__dataConnectionMode == 0) {
                ServerSocket createServerSocket = this._socketFactory_.createServerSocket(0, 1, getLocalAddress());
                if (!FTPReply.isPositiveCompletion(port(getLocalAddress(), createServerSocket.getLocalPort()))) {
                    createServerSocket.close();
                    return null;
                }
                long j = this.__restartOffset;
                if (j > 0 && !restart(j)) {
                    createServerSocket.close();
                    return null;
                } else if (!FTPReply.isPositivePreliminary(sendCommand(i, str))) {
                    createServerSocket.close();
                    return null;
                } else {
                    int i3 = this.__dataTimeout;
                    if (i3 >= 0) {
                        createServerSocket.setSoTimeout(i3);
                    }
                    socket = createServerSocket.accept();
                    createServerSocket.close();
                }
            } else if (pasv() != 227) {
                return null;
            } else {
                __parsePassiveModeReply((String) this._replyLines.elementAt(0));
                Socket createSocket = this._socketFactory_.createSocket(this.__passiveHost, this.__passivePort);
                long j2 = this.__restartOffset;
                if (j2 > 0 && !restart(j2)) {
                    createSocket.close();
                    return null;
                } else if (!FTPReply.isPositivePreliminary(sendCommand(i, str))) {
                    createSocket.close();
                    return null;
                } else {
                    socket = createSocket;
                }
            }
            if (this.__remoteVerificationEnabled && !verifyRemote(socket)) {
                InetAddress inetAddress = socket.getInetAddress();
                InetAddress remoteAddress = getRemoteAddress();
                socket.close();
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Host attempting data connection ");
                outline103.append(inetAddress.getHostAddress());
                outline103.append(" is not same as server ");
                outline103.append(remoteAddress.getHostAddress());
                throw new IOException(outline103.toString());
            }
            int i4 = this.__dataTimeout;
            if (i4 >= 0) {
                socket.setSoTimeout(i4);
            }
            return socket;
        }
        return null;
    }

    public boolean abort() throws IOException {
        return FTPReply.isPositiveCompletion(abor());
    }

    public boolean allocate(int i) throws IOException {
        return FTPReply.isPositiveCompletion(allo(i));
    }

    public boolean appendFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(16, str, inputStream);
    }

    public OutputStream appendFileStream(String str) throws IOException {
        return __storeFileStream(16, str);
    }

    public boolean changeToParentDirectory() throws IOException {
        return FTPReply.isPositiveCompletion(cdup());
    }

    public boolean changeWorkingDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(cwd(str));
    }

    public boolean completePendingCommand() throws IOException {
        return FTPReply.isPositiveCompletion(getReply());
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig fTPClientConfig) {
        this.__configuration = fTPClientConfig;
    }

    public FTPFileList createFileList(FTPFileEntryParser fTPFileEntryParser) throws IOException {
        return createFileList(null, fTPFileEntryParser);
    }

    public boolean deleteFile(String str) throws IOException {
        return FTPReply.isPositiveCompletion(dele(str));
    }

    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.telnet.TelnetClient, org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        __initDefaults();
    }

    public void enterLocalActiveMode() {
        this.__dataConnectionMode = 0;
        this.__passiveHost = null;
        this.__passivePort = -1;
    }

    public void enterLocalPassiveMode() {
        this.__dataConnectionMode = 2;
        this.__passiveHost = null;
        this.__passivePort = -1;
    }

    public boolean enterRemoteActiveMode(InetAddress inetAddress, int i) throws IOException {
        if (FTPReply.isPositiveCompletion(port(inetAddress, i))) {
            this.__dataConnectionMode = 1;
            this.__passiveHost = null;
            this.__passivePort = -1;
            return true;
        }
        return false;
    }

    public boolean enterRemotePassiveMode() throws IOException {
        if (pasv() != 227) {
            return false;
        }
        this.__dataConnectionMode = 3;
        __parsePassiveModeReply((String) this._replyLines.elementAt(0));
        return true;
    }

    public int getBufferSize() {
        return this.__bufferSize;
    }

    public int getDataConnectionMode() {
        return this.__dataConnectionMode;
    }

    public String getPassiveHost() {
        return this.__passiveHost;
    }

    public int getPassivePort() {
        return this.__passivePort;
    }

    public long getRestartOffset() {
        return this.__restartOffset;
    }

    public String getStatus() throws IOException {
        if (FTPReply.isPositiveCompletion(stat())) {
            return getReplyString();
        }
        return null;
    }

    public String getSystemName() throws IOException {
        if (this.__systemName == null && FTPReply.isPositiveCompletion(syst())) {
            this.__systemName = ((String) this._replyLines.elementAt(0)).substring(4);
        }
        return this.__systemName;
    }

    public FTPListParseEngine initiateListParsing() throws IOException {
        return initiateListParsing(null);
    }

    public boolean isRemoteVerificationEnabled() {
        return this.__remoteVerificationEnabled;
    }

    public FTPFile[] listFiles(String str, String str2) throws IOException {
        return initiateListParsing(str, str2).getFiles();
    }

    public String listHelp() throws IOException {
        if (FTPReply.isPositiveCompletion(help())) {
            return getReplyString();
        }
        return null;
    }

    public String[] listNames(String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(27, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_openDataConnection_.getInputStream(), getControlEncoding()));
        Vector vector = new Vector();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            vector.addElement(readLine);
        }
        bufferedReader.close();
        _openDataConnection_.close();
        if (!completePendingCommand()) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }

    public boolean login(String str, String str2) throws IOException {
        user(str);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (FTPReply.isPositiveIntermediate(this._replyCode)) {
            return FTPReply.isPositiveCompletion(pass(str2));
        }
        return false;
    }

    public boolean logout() throws IOException {
        return FTPReply.isPositiveCompletion(quit());
    }

    public boolean makeDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(mkd(str));
    }

    public String printWorkingDirectory() throws IOException {
        if (pwd() != 257) {
            return null;
        }
        return __parsePathname((String) this._replyLines.elementAt(0));
    }

    boolean reinitialize() throws IOException {
        rein();
        if (FTPReply.isPositiveCompletion(this._replyCode) || (FTPReply.isPositivePreliminary(this._replyCode) && FTPReply.isPositiveCompletion(getReply()))) {
            __initDefaults();
            return true;
        }
        return false;
    }

    public boolean remoteAppend(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stor(str));
        }
        return false;
    }

    public boolean remoteRetrieve(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(retr(str));
        }
        return false;
    }

    public boolean remoteStore(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stor(str));
        }
        return false;
    }

    public boolean remoteStoreUnique(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou(str));
        }
        return false;
    }

    public boolean removeDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(rmd(str));
    }

    public boolean rename(String str, String str2) throws IOException {
        if (!FTPReply.isPositiveIntermediate(rnfr(str))) {
            return false;
        }
        return FTPReply.isPositiveCompletion(rnto(str2));
    }

    public boolean retrieveFile(String str, OutputStream outputStream) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(13, str);
        if (_openDataConnection_ == null) {
            return false;
        }
        FilterInputStream bufferedInputStream = new BufferedInputStream(_openDataConnection_.getInputStream(), getBufferSize());
        try {
            Util.copyStream(this.__fileType == 0 ? new FromNetASCIIInputStream(bufferedInputStream) : bufferedInputStream, outputStream, getBufferSize(), -1L, null, false);
            _openDataConnection_.close();
            return completePendingCommand();
        } catch (IOException e) {
            try {
                _openDataConnection_.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    public InputStream retrieveFileStream(String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(13, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        InputStream inputStream = _openDataConnection_.getInputStream();
        if (this.__fileType == 0) {
            inputStream = new FromNetASCIIInputStream(new BufferedInputStream(inputStream, getBufferSize()));
        }
        return new SocketInputStream(_openDataConnection_, inputStream);
    }

    public boolean sendNoOp() throws IOException {
        return FTPReply.isPositiveCompletion(noop());
    }

    public boolean sendSiteCommand(String str) throws IOException {
        return FTPReply.isPositiveCompletion(site(str));
    }

    public void setBufferSize(int i) {
        this.__bufferSize = i;
    }

    public void setDataTimeout(int i) {
        this.__dataTimeout = i;
    }

    public boolean setFileStructure(int i) throws IOException {
        if (FTPReply.isPositiveCompletion(stru(i))) {
            this.__fileStructure = i;
            return true;
        }
        return false;
    }

    public boolean setFileTransferMode(int i) throws IOException {
        if (FTPReply.isPositiveCompletion(mode(i))) {
            this.__fileTransferMode = i;
            return true;
        }
        return false;
    }

    public boolean setFileType(int i) throws IOException {
        if (FTPReply.isPositiveCompletion(type(i))) {
            this.__fileType = i;
            this.__fileFormat = 4;
            return true;
        }
        return false;
    }

    public void setParserFactory(FTPFileEntryParserFactory fTPFileEntryParserFactory) {
        this.__parserFactory = fTPFileEntryParserFactory;
    }

    public void setRemoteVerificationEnabled(boolean z) {
        this.__remoteVerificationEnabled = z;
    }

    public void setRestartOffset(long j) {
        if (j >= 0) {
            this.__restartOffset = j;
        }
    }

    public boolean storeFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(14, str, inputStream);
    }

    public OutputStream storeFileStream(String str) throws IOException {
        return __storeFileStream(14, str);
    }

    public boolean storeUniqueFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(15, str, inputStream);
    }

    public OutputStream storeUniqueFileStream(String str) throws IOException {
        return __storeFileStream(15, str);
    }

    public boolean structureMount(String str) throws IOException {
        return FTPReply.isPositiveCompletion(smnt(str));
    }

    public boolean allocate(int i, int i2) throws IOException {
        return FTPReply.isPositiveCompletion(allo(i, i2));
    }

    public FTPFileList createFileList(String str, FTPFileEntryParser fTPFileEntryParser) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(26, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        FTPFileList create = FTPFileList.create(_openDataConnection_.getInputStream(), fTPFileEntryParser);
        _openDataConnection_.close();
        completePendingCommand();
        return create;
    }

    public FTPListParseEngine initiateListParsing(String str) throws IOException {
        return initiateListParsing((String) null, str);
    }

    public boolean storeUniqueFile(InputStream inputStream) throws IOException {
        return __storeFile(15, null, inputStream);
    }

    public OutputStream storeUniqueFileStream() throws IOException {
        return __storeFileStream(15, null);
    }

    public String getStatus(String str) throws IOException {
        if (FTPReply.isPositiveCompletion(stat(str))) {
            return getReplyString();
        }
        return null;
    }

    public FTPListParseEngine initiateListParsing(String str, String str2) throws IOException {
        if (this.__entryParser == null) {
            if (str != null) {
                this.__entryParser = this.__parserFactory.createFileEntryParser(str);
            } else {
                FTPClientConfig fTPClientConfig = this.__configuration;
                if (fTPClientConfig != null) {
                    this.__entryParser = this.__parserFactory.createFileEntryParser(fTPClientConfig);
                } else {
                    this.__entryParser = this.__parserFactory.createFileEntryParser(getSystemName());
                }
            }
        }
        return initiateListParsing(this.__entryParser, str2);
    }

    public FTPFile[] listFiles(String str) throws IOException {
        return initiateListParsing((String) null, str).getFiles();
    }

    public String listHelp(String str) throws IOException {
        if (FTPReply.isPositiveCompletion(help(str))) {
            return getReplyString();
        }
        return null;
    }

    public boolean remoteStoreUnique() throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou());
        }
        return false;
    }

    public boolean setFileType(int i, int i2) throws IOException {
        if (FTPReply.isPositiveCompletion(type(i, i2))) {
            this.__fileType = i;
            this.__fileFormat = i2;
            return true;
        }
        return false;
    }

    public FTPFile[] listFiles() throws IOException {
        return listFiles((String) null);
    }

    public boolean login(String str, String str2, String str3) throws IOException {
        user(str);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (!FTPReply.isPositiveIntermediate(this._replyCode)) {
            return false;
        }
        pass(str2);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (FTPReply.isPositiveIntermediate(this._replyCode)) {
            return FTPReply.isPositiveCompletion(acct(str3));
        }
        return false;
    }

    public FTPFile[] listFiles(FTPFileListParser fTPFileListParser, String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(26, str);
        if (_openDataConnection_ == null) {
            return new FTPFile[0];
        }
        FTPFile[] parseFileList = fTPFileListParser.parseFileList(_openDataConnection_.getInputStream(), getControlEncoding());
        _openDataConnection_.close();
        completePendingCommand();
        return parseFileList;
    }

    private FTPListParseEngine initiateListParsing(FTPFileEntryParser fTPFileEntryParser, String str) throws IOException {
        FTPListParseEngine fTPListParseEngine = new FTPListParseEngine(fTPFileEntryParser);
        Socket _openDataConnection_ = _openDataConnection_(26, str);
        if (_openDataConnection_ == null) {
            return fTPListParseEngine;
        }
        fTPListParseEngine.readServerList(_openDataConnection_.getInputStream(), getControlEncoding());
        _openDataConnection_.close();
        completePendingCommand();
        return fTPListParseEngine;
    }

    public FTPFile[] listFiles(FTPFileListParser fTPFileListParser) throws IOException {
        return listFiles(fTPFileListParser, (String) null);
    }

    public String[] listNames() throws IOException {
        return listNames(null);
    }
}
