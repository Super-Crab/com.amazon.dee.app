package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
/* loaded from: classes4.dex */
public class MVSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
    private static final String REGEX = "(.*)\\s+([^\\s]+)\\s*";

    public MVSFTPEntryParser() {
        super(REGEX);
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_MVS, DEFAULT_DATE_FORMAT, null, null, null, null);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        if (matches(str)) {
            FTPFile fTPFile = new FTPFile();
            String group = group(2);
            fTPFile.setType(0);
            fTPFile.setName(group);
            return fTPFile;
        }
        return null;
    }
}
