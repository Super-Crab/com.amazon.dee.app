package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes4.dex */
public abstract class FTPFileEntryParserImpl implements FTPFileEntryParser, FTPFileListParser {
    @Override // org.apache.commons.net.ftp.FTPFileListParser
    public FTPFile[] parseFileList(InputStream inputStream, String str) throws IOException {
        return FTPFileList.create(inputStream, this, str).getFiles();
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public List preParse(List list) {
        Iterator it2 = list.iterator();
        while (it2.hasNext() && parseFTPEntry((String) it2.next()) == null) {
            it2.remove();
        }
        return list;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public String readNextEntry(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    @Override // org.apache.commons.net.ftp.FTPFileListParser
    public FTPFile[] parseFileList(InputStream inputStream) throws IOException {
        return parseFileList(inputStream, null);
    }
}
