package org.apache.commons.net.ftp;

import java.util.List;
/* loaded from: classes4.dex */
public class FTPFileIterator {
    private static final int DIREMPTY = -2;
    private static final FTPFile[] EMPTY = new FTPFile[0];
    private static final int UNINIT = -1;
    private int firstGoodEntry;
    private int itemptr;
    private FTPFileEntryParser parser;
    private List rawlines;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FTPFileIterator(FTPFileList fTPFileList) {
        this(fTPFileList, fTPFileList.getParser());
    }

    private int getFirstGoodEntry() {
        for (int i = 0; i < this.rawlines.size(); i++) {
            if (parseFTPEntry((String) this.rawlines.get(i)) != null) {
                return i;
            }
        }
        return -2;
    }

    private void init() {
        this.itemptr = 0;
        this.firstGoodEntry = -1;
    }

    private FTPFile parseFTPEntry(String str) {
        return this.parser.parseFTPEntry(str);
    }

    public FTPFile[] getFiles() {
        if (this.itemptr != -2) {
            init();
        }
        return getNext(0);
    }

    public FTPFile[] getNext(int i) {
        if (this.firstGoodEntry == -1) {
            this.firstGoodEntry = getFirstGoodEntry();
        }
        if (this.firstGoodEntry == -2) {
            return EMPTY;
        }
        int size = this.rawlines.size() - this.firstGoodEntry;
        if (i == 0) {
            i = size;
        }
        if (this.itemptr + i >= this.rawlines.size()) {
            i = this.rawlines.size() - this.itemptr;
        }
        FTPFile[] fTPFileArr = new FTPFile[i];
        int i2 = 0;
        int i3 = this.firstGoodEntry + this.itemptr;
        while (i2 < i) {
            fTPFileArr[i2] = parseFTPEntry((String) this.rawlines.get(i3));
            this.itemptr++;
            i2++;
            i3++;
        }
        return fTPFileArr;
    }

    public FTPFile[] getPrevious(int i) {
        int i2 = this.itemptr;
        if (i > i2) {
            i = i2;
        }
        FTPFile[] fTPFileArr = new FTPFile[i];
        int i3 = this.firstGoodEntry + this.itemptr;
        while (i > 0) {
            i--;
            i3--;
            fTPFileArr[i] = parseFTPEntry((String) this.rawlines.get(i3));
            this.itemptr--;
        }
        return fTPFileArr;
    }

    public boolean hasNext() {
        int i = this.firstGoodEntry;
        if (i == -2) {
            return false;
        }
        if (i < 0) {
            i = getFirstGoodEntry();
        }
        return i + this.itemptr < this.rawlines.size();
    }

    public boolean hasPrevious() {
        int i = this.firstGoodEntry;
        if (i == -2) {
            return false;
        }
        if (i < 0) {
            i = getFirstGoodEntry();
        }
        return this.itemptr > i;
    }

    public FTPFile next() {
        FTPFile[] next = getNext(1);
        if (next.length > 0) {
            return next[0];
        }
        return null;
    }

    public FTPFile previous() {
        FTPFile[] previous = getPrevious(1);
        if (previous.length > 0) {
            return previous[0];
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FTPFileIterator(FTPFileList fTPFileList, FTPFileEntryParser fTPFileEntryParser) {
        this.itemptr = 0;
        this.firstGoodEntry = -1;
        this.rawlines = fTPFileList.getLines();
        this.parser = fTPFileEntryParser;
    }
}
