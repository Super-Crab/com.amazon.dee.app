package org.apache.commons.net.ftp;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes4.dex */
public interface FTPFileListParser {
    FTPFile[] parseFileList(InputStream inputStream) throws IOException;

    FTPFile[] parseFileList(InputStream inputStream, String str) throws IOException;
}
