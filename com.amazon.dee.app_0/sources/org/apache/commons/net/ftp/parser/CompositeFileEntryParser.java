package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParser;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;
/* loaded from: classes4.dex */
public class CompositeFileEntryParser extends FTPFileEntryParserImpl {
    private FTPFileEntryParser cachedFtpFileEntryParser = null;
    private final FTPFileEntryParser[] ftpFileEntryParsers;

    public CompositeFileEntryParser(FTPFileEntryParser[] fTPFileEntryParserArr) {
        this.ftpFileEntryParsers = fTPFileEntryParserArr;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        FTPFileEntryParser fTPFileEntryParser = this.cachedFtpFileEntryParser;
        if (fTPFileEntryParser != null) {
            FTPFile parseFTPEntry = fTPFileEntryParser.parseFTPEntry(str);
            if (parseFTPEntry == null) {
                return null;
            }
            return parseFTPEntry;
        }
        int i = 0;
        while (true) {
            FTPFileEntryParser[] fTPFileEntryParserArr = this.ftpFileEntryParsers;
            if (i >= fTPFileEntryParserArr.length) {
                return null;
            }
            FTPFileEntryParser fTPFileEntryParser2 = fTPFileEntryParserArr[i];
            FTPFile parseFTPEntry2 = fTPFileEntryParser2.parseFTPEntry(str);
            if (parseFTPEntry2 != null) {
                this.cachedFtpFileEntryParser = fTPFileEntryParser2;
                return parseFTPEntry2;
            }
            i++;
        }
    }
}
