package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
/* loaded from: classes4.dex */
public class OS2FTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "MM-dd-yy HH:mm";
    private static final String REGEX = "(\\s+|[0-9]+)\\s*(\\s+|[A-Z]+)\\s*(DIR|\\s+)\\s*(\\S+)\\s+(\\S+)\\s+(\\S.*)";

    public OS2FTPEntryParser() {
        this(null);
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_OS2, DEFAULT_DATE_FORMAT, null, null, null, null);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        FTPFile fTPFile = new FTPFile();
        if (matches(str)) {
            String group = group(1);
            String group2 = group(2);
            String group3 = group(3);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(group(4));
            stringBuffer.append(" ");
            stringBuffer.append(group(5));
            String stringBuffer2 = stringBuffer.toString();
            String group4 = group(6);
            try {
                fTPFile.setTimestamp(super.parseTimestamp(stringBuffer2));
                if (!group3.trim().equals("DIR") && !group2.trim().equals("DIR")) {
                    fTPFile.setType(0);
                } else {
                    fTPFile.setType(1);
                }
                fTPFile.setName(group4.trim());
                fTPFile.setSize(Long.parseLong(group.trim()));
                return fTPFile;
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public OS2FTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }
}
