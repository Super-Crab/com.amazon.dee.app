package org.apache.commons.net.ftp.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.StringTokenizer;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
/* loaded from: classes4.dex */
public class VMSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "d-MMM-yyyy HH:mm:ss";
    private static final String REGEX = "(.*;[0-9]+)\\s*(\\d+)/\\d+\\s*(\\S+)\\s+(\\S+)\\s+\\[(([0-9$A-Za-z_]+)|([0-9$A-Za-z_]+),([0-9$a-zA-Z_]+))\\]?\\s*\\([a-zA-Z]*,[a-zA-Z]*,[a-zA-Z]*,[a-zA-Z]*\\)";

    public VMSFTPEntryParser() {
        this(null);
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_VMS, DEFAULT_DATE_FORMAT, null, null, null, null);
    }

    protected boolean isVersioning() {
        return false;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        String nextToken;
        String str2 = null;
        if (matches(str)) {
            FTPFile fTPFile = new FTPFile();
            fTPFile.setRawListing(str);
            String group = group(1);
            String group2 = group(2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(group(3));
            stringBuffer.append(" ");
            stringBuffer.append(group(4));
            String stringBuffer2 = stringBuffer.toString();
            String group3 = group(5);
            try {
                fTPFile.setTimestamp(super.parseTimestamp(stringBuffer2));
                StringTokenizer stringTokenizer = new StringTokenizer(group3, ",");
                int countTokens = stringTokenizer.countTokens();
                if (countTokens == 1) {
                    nextToken = stringTokenizer.nextToken();
                } else if (countTokens != 2) {
                    nextToken = null;
                } else {
                    str2 = stringTokenizer.nextToken();
                    nextToken = stringTokenizer.nextToken();
                }
                if (group.lastIndexOf(".DIR") != -1) {
                    fTPFile.setType(1);
                } else {
                    fTPFile.setType(0);
                }
                if (isVersioning()) {
                    fTPFile.setName(group);
                } else {
                    fTPFile.setName(group.substring(0, group.lastIndexOf(";")));
                }
                fTPFile.setSize(Long.parseLong(group2) * 512);
                fTPFile.setGroup(str2);
                fTPFile.setUser(nextToken);
                return fTPFile;
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParserImpl, org.apache.commons.net.ftp.FTPFileListParser
    public FTPFile[] parseFileList(InputStream inputStream) throws IOException {
        FTPListParseEngine fTPListParseEngine = new FTPListParseEngine(this);
        fTPListParseEngine.readServerList(inputStream);
        return fTPListParseEngine.getFiles();
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParserImpl, org.apache.commons.net.ftp.FTPFileEntryParser
    public String readNextEntry(BufferedReader bufferedReader) throws IOException {
        String readLine = bufferedReader.readLine();
        StringBuffer stringBuffer = new StringBuffer();
        while (readLine != null) {
            if (!readLine.startsWith("Directory") && !readLine.startsWith("Total")) {
                stringBuffer.append(readLine);
                if (readLine.trim().endsWith(")")) {
                    break;
                }
                readLine = bufferedReader.readLine();
            } else {
                readLine = bufferedReader.readLine();
            }
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        return stringBuffer.toString();
    }

    public VMSFTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }
}
