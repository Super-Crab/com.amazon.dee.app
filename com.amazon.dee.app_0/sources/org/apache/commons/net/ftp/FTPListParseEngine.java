package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/* loaded from: classes4.dex */
public class FTPListParseEngine {
    FTPFileEntryParser parser;
    private List entries = new LinkedList();
    private ListIterator _internalIterator = this.entries.listIterator();

    public FTPListParseEngine(FTPFileEntryParser fTPFileEntryParser) {
        this.parser = null;
        this.parser = fTPFileEntryParser;
    }

    private void readStream(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader;
        if (str == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        }
        String readNextEntry = this.parser.readNextEntry(bufferedReader);
        while (readNextEntry != null) {
            this.entries.add(readNextEntry);
            readNextEntry = this.parser.readNextEntry(bufferedReader);
        }
        bufferedReader.close();
    }

    public FTPFile[] getFiles() throws IOException {
        LinkedList linkedList = new LinkedList();
        for (String str : this.entries) {
            linkedList.add(this.parser.parseFTPEntry(str));
        }
        return (FTPFile[]) linkedList.toArray(new FTPFile[0]);
    }

    public FTPFile[] getNext(int i) {
        LinkedList linkedList = new LinkedList();
        while (i > 0 && this._internalIterator.hasNext()) {
            linkedList.add(this.parser.parseFTPEntry((String) this._internalIterator.next()));
            i--;
        }
        return (FTPFile[]) linkedList.toArray(new FTPFile[0]);
    }

    public FTPFile[] getPrevious(int i) {
        LinkedList linkedList = new LinkedList();
        while (i > 0 && this._internalIterator.hasPrevious()) {
            linkedList.add(0, this.parser.parseFTPEntry((String) this._internalIterator.previous()));
            i--;
        }
        return (FTPFile[]) linkedList.toArray(new FTPFile[0]);
    }

    public boolean hasNext() {
        return this._internalIterator.hasNext();
    }

    public boolean hasPrevious() {
        return this._internalIterator.hasPrevious();
    }

    public void readServerList(InputStream inputStream, String str) throws IOException {
        this.entries = new LinkedList();
        readStream(inputStream, str);
        this.parser.preParse(this.entries);
        resetIterator();
    }

    public void resetIterator() {
        this._internalIterator = this.entries.listIterator();
    }

    public void readServerList(InputStream inputStream) throws IOException {
        readServerList(inputStream, null);
    }
}
