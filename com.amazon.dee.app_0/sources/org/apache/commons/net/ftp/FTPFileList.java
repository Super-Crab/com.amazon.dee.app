package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes4.dex */
public class FTPFileList {
    private static final int EMPTY_DIR = -2;
    private LinkedList lines;
    private FTPFileEntryParser parser;

    private FTPFileList(FTPFileEntryParser fTPFileEntryParser, String str) {
        this.lines = null;
        this.parser = fTPFileEntryParser;
        this.lines = new LinkedList();
    }

    public static FTPFileList create(InputStream inputStream, FTPFileEntryParser fTPFileEntryParser, String str) throws IOException {
        FTPFileList fTPFileList = new FTPFileList(fTPFileEntryParser, str);
        fTPFileList.readStream(inputStream, str);
        fTPFileEntryParser.preParse(fTPFileList.lines);
        return fTPFileList;
    }

    public FTPFile[] getFiles() {
        return iterator().getFiles();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List getLines() {
        return this.lines;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FTPFileEntryParser getParser() {
        return this.parser;
    }

    public FTPFileIterator iterator() {
        return new FTPFileIterator(this);
    }

    public void readStream(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        String readNextEntry = this.parser.readNextEntry(bufferedReader);
        while (readNextEntry != null) {
            this.lines.add(readNextEntry);
            readNextEntry = this.parser.readNextEntry(bufferedReader);
        }
        bufferedReader.close();
    }

    public FTPFileIterator iterator(FTPFileEntryParser fTPFileEntryParser) {
        return new FTPFileIterator(this, fTPFileEntryParser);
    }

    public static FTPFileList create(InputStream inputStream, FTPFileEntryParser fTPFileEntryParser) throws IOException {
        return create(inputStream, fTPFileEntryParser, null);
    }

    public void readStream(InputStream inputStream) throws IOException {
        readStream(inputStream, null);
    }
}
