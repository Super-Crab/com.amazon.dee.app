package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
/* loaded from: classes4.dex */
public interface FTPFileEntryParser {
    FTPFile parseFTPEntry(String str);

    List preParse(List list);

    String readNextEntry(BufferedReader bufferedReader) throws IOException;
}
