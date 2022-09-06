package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
/* loaded from: classes4.dex */
public class NTFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "MM-dd-yy hh:mma";
    private static final String REGEX = "(\\S+)\\s+(\\S+)\\s+(<DIR>)?\\s*([0-9]+)?\\s+(\\S.*)";

    public NTFTPEntryParser() {
        this(null);
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    public FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig("WINDOWS", DEFAULT_DATE_FORMAT, null, null, null, null);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        FTPFile fTPFile = new FTPFile();
        fTPFile.setRawListing(str);
        if (matches(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(group(1));
            stringBuffer.append(" ");
            stringBuffer.append(group(2));
            String stringBuffer2 = stringBuffer.toString();
            String group = group(3);
            String group2 = group(4);
            String group3 = group(5);
            try {
                fTPFile.setTimestamp(super.parseTimestamp(stringBuffer2));
                if (group3 != null && !group3.equals(".") && !group3.equals("..")) {
                    fTPFile.setName(group3);
                    if ("<DIR>".equals(group)) {
                        fTPFile.setType(1);
                        fTPFile.setSize(0L);
                    } else {
                        fTPFile.setType(0);
                        if (group2 != null) {
                            fTPFile.setSize(Long.parseLong(group2));
                        }
                    }
                    return fTPFile;
                }
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public NTFTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }
}
