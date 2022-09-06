package org.apache.commons.net.ftp.parser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFileEntryParser;
/* loaded from: classes4.dex */
public class DefaultFTPFileEntryParserFactory implements FTPFileEntryParserFactory {
    private FTPClientConfig config = null;

    @Override // org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory
    public FTPFileEntryParser createFileEntryParser(String str) {
        FTPFileEntryParser createMVSEntryParser;
        FTPFileEntryParser fTPFileEntryParser;
        Class<?> cls;
        String str2 = null;
        Class<?> cls2 = null;
        try {
            try {
                cls = Class.forName(str);
            } catch (ClassCastException e) {
                e = e;
            }
            try {
                fTPFileEntryParser = (FTPFileEntryParser) cls.newInstance();
            } catch (ClassCastException e2) {
                e = e2;
                cls2 = cls;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cls2.getName());
                stringBuffer.append(" does not implement the interface ");
                stringBuffer.append("org.apache.commons.net.ftp.FTPFileEntryParser.");
                throw new ParserInitializationException(stringBuffer.toString(), e);
            }
        } catch (ClassNotFoundException unused) {
            if (str != null) {
                str2 = str.toUpperCase();
            }
            if (str2.indexOf(FTPClientConfig.SYST_UNIX) >= 0) {
                createMVSEntryParser = createUnixFTPEntryParser();
            } else if (str2.indexOf(FTPClientConfig.SYST_VMS) >= 0) {
                createMVSEntryParser = createVMSVersioningFTPEntryParser();
            } else if (str2.indexOf("WINDOWS") >= 0) {
                createMVSEntryParser = createNTFTPEntryParser();
            } else if (str2.indexOf(FTPClientConfig.SYST_OS2) >= 0) {
                createMVSEntryParser = createOS2FTPEntryParser();
            } else if (str2.indexOf(FTPClientConfig.SYST_OS400) >= 0) {
                createMVSEntryParser = createOS400FTPEntryParser();
            } else if (str2.indexOf(FTPClientConfig.SYST_MVS) >= 0) {
                createMVSEntryParser = createMVSEntryParser();
            } else {
                throw new ParserInitializationException(GeneratedOutlineSupport1.outline71("Unknown parser type: ", str));
            }
            fTPFileEntryParser = createMVSEntryParser;
        } catch (Throwable th) {
            throw new ParserInitializationException("Error initializing parser", th);
        }
        if (fTPFileEntryParser instanceof Configurable) {
            ((Configurable) fTPFileEntryParser).configure(this.config);
        }
        return fTPFileEntryParser;
    }

    public FTPFileEntryParser createMVSEntryParser() {
        return new MVSFTPEntryParser();
    }

    public FTPFileEntryParser createNTFTPEntryParser() {
        FTPClientConfig fTPClientConfig = this.config;
        if (fTPClientConfig != null && "WINDOWS".equals(fTPClientConfig.getServerSystemKey())) {
            return new NTFTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[]{new NTFTPEntryParser(), new UnixFTPEntryParser()});
    }

    public FTPFileEntryParser createOS2FTPEntryParser() {
        return new OS2FTPEntryParser();
    }

    public FTPFileEntryParser createOS400FTPEntryParser() {
        FTPClientConfig fTPClientConfig = this.config;
        if (fTPClientConfig != null && FTPClientConfig.SYST_OS400.equals(fTPClientConfig.getServerSystemKey())) {
            return new OS400FTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[]{new OS400FTPEntryParser(), new UnixFTPEntryParser()});
    }

    public FTPFileEntryParser createUnixFTPEntryParser() {
        return new UnixFTPEntryParser();
    }

    public FTPFileEntryParser createVMSVersioningFTPEntryParser() {
        return new VMSVersioningFTPEntryParser();
    }

    @Override // org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory
    public FTPFileEntryParser createFileEntryParser(FTPClientConfig fTPClientConfig) throws ParserInitializationException {
        this.config = fTPClientConfig;
        return createFileEntryParser(fTPClientConfig.getServerSystemKey());
    }
}
