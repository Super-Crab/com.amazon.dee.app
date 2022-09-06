package org.apache.commons.net.ftp.parser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
/* loaded from: classes4.dex */
public class OS400FTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private static final String REGEX = "(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\*\\S+)\\s+(\\S+/?)\\s*";

    public OS400FTPEntryParser() {
        this(null);
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_OS400, DEFAULT_DATE_FORMAT, null, null, null, null);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        FTPFile fTPFile = new FTPFile();
        fTPFile.setRawListing(str);
        if (matches(str)) {
            String group = group(1);
            String group2 = group(2);
            StringBuffer stringBuffer = new StringBuffer();
            int i = 3;
            stringBuffer.append(group(3));
            stringBuffer.append(" ");
            stringBuffer.append(group(4));
            String stringBuffer2 = stringBuffer.toString();
            String group3 = group(5);
            String group4 = group(6);
            try {
                fTPFile.setTimestamp(super.parseTimestamp(stringBuffer2));
                if (group3.equalsIgnoreCase("*STMF")) {
                    i = 0;
                } else if (group3.equalsIgnoreCase("*DIR")) {
                    i = 1;
                }
                fTPFile.setType(i);
                fTPFile.setUser(group);
                try {
                    fTPFile.setSize(Long.parseLong(group2));
                } catch (NumberFormatException unused) {
                }
                if (group4.endsWith("/")) {
                    group4 = GeneratedOutlineSupport1.outline51(group4, 1, 0);
                }
                int lastIndexOf = group4.lastIndexOf(47);
                if (lastIndexOf > -1) {
                    group4 = group4.substring(lastIndexOf + 1);
                }
                fTPFile.setName(group4);
                return fTPFile;
            } catch (ParseException unused2) {
            }
        }
        return null;
    }

    public OS400FTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }
}
