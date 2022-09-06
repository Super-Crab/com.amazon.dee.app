package org.apache.commons.io;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes4.dex */
public class FileSystemUtils {
    private static final String DF;
    private static final int INIT_PROBLEM = -1;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();
    private static final int OS;
    private static final int OTHER = 0;
    private static final int POSIX_UNIX = 3;
    private static final int UNIX = 2;
    private static final int WINDOWS = 1;

    static {
        String property;
        String str = "df";
        int i = -1;
        try {
            property = System.getProperty("os.name");
        } catch (Exception unused) {
        }
        if (property != null) {
            String lowerCase = property.toLowerCase(Locale.ENGLISH);
            if (lowerCase.indexOf("windows") != -1) {
                i = 1;
            } else {
                if (lowerCase.indexOf("linux") == -1 && lowerCase.indexOf("mpe/ix") == -1 && lowerCase.indexOf("freebsd") == -1 && lowerCase.indexOf("irix") == -1 && lowerCase.indexOf("digital unix") == -1 && lowerCase.indexOf("unix") == -1 && lowerCase.indexOf("mac os x") == -1) {
                    if (lowerCase.indexOf("sun os") == -1 && lowerCase.indexOf("sunos") == -1 && lowerCase.indexOf("solaris") == -1) {
                        if (lowerCase.indexOf("hp-ux") == -1 && lowerCase.indexOf("aix") == -1) {
                            i = 0;
                        }
                        i = 3;
                    }
                    str = "/usr/xpg4/bin/df";
                    i = 3;
                }
                i = 2;
            }
            OS = i;
            DF = str;
            return;
        }
        throw new IOException("os.name not found");
    }

    @Deprecated
    public static long freeSpace(String str) throws IOException {
        return INSTANCE.freeSpaceOS(str, OS, false, -1L);
    }

    public static long freeSpaceKb(String str) throws IOException {
        return freeSpaceKb(str, -1L);
    }

    long freeSpaceOS(String str, int i, boolean z, long j) throws IOException {
        if (str != null) {
            if (i == 0) {
                throw new IllegalStateException("Unsupported operating system");
            }
            if (i == 1) {
                long freeSpaceWindows = freeSpaceWindows(str, j);
                return z ? freeSpaceWindows / 1024 : freeSpaceWindows;
            } else if (i == 2) {
                return freeSpaceUnix(str, z, false, j);
            } else {
                if (i == 3) {
                    return freeSpaceUnix(str, z, true, j);
                }
                throw new IllegalStateException("Exception caught when determining operating system");
            }
        }
        throw new IllegalArgumentException("Path must not be empty");
    }

    long freeSpaceUnix(String str, boolean z, boolean z2, long j) throws IOException {
        if (str.length() != 0) {
            String str2 = ProcessIdUtil.DEFAULT_PROCESSID;
            if (z) {
                str2 = GeneratedOutlineSupport1.outline72(str2, "k");
            }
            if (z2) {
                str2 = GeneratedOutlineSupport1.outline72(str2, "P");
            }
            List<String> performCommand = performCommand(str2.length() > 1 ? new String[]{DF, str2, str} : new String[]{DF, str}, 3, j);
            if (performCommand.size() >= 2) {
                StringTokenizer stringTokenizer = new StringTokenizer(performCommand.get(1), " ");
                if (stringTokenizer.countTokens() < 4) {
                    if (stringTokenizer.countTokens() == 1 && performCommand.size() >= 3) {
                        stringTokenizer = new StringTokenizer(performCommand.get(2), " ");
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Command line '");
                        GeneratedOutlineSupport1.outline181(outline107, DF, "' did not return data as expected ", "for path '", str);
                        outline107.append("'- check path is valid");
                        throw new IOException(outline107.toString());
                    }
                } else {
                    stringTokenizer.nextToken();
                }
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                return parseBytes(stringTokenizer.nextToken(), str);
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Command line '");
            GeneratedOutlineSupport1.outline181(outline1072, DF, "' did not return info as expected ", "for path '", str);
            outline1072.append("'- response was ");
            outline1072.append(performCommand);
            throw new IOException(outline1072.toString());
        }
        throw new IllegalArgumentException("Path must not be empty");
    }

    long freeSpaceWindows(String str, long j) throws IOException {
        String normalize = FilenameUtils.normalize(str, false);
        if (normalize.length() > 0 && normalize.charAt(0) != '\"') {
            normalize = GeneratedOutlineSupport1.outline75(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, normalize, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        List<String> performCommand = performCommand(new String[]{"cmd.exe", "/C", GeneratedOutlineSupport1.outline72("dir /a /-c ", normalize)}, Integer.MAX_VALUE, j);
        for (int size = performCommand.size() - 1; size >= 0; size--) {
            String str2 = performCommand.get(size);
            if (str2.length() > 0) {
                return parseDir(str2, normalize);
            }
        }
        throw new IOException(GeneratedOutlineSupport1.outline75("Command line 'dir /-c' did not return any info for path '", normalize, "'"));
    }

    Process openProcess(String[] strArr) throws IOException {
        return Runtime.getRuntime().exec(strArr);
    }

    long parseBytes(String str, String str2) throws IOException {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong >= 0) {
                return parseLong;
            }
            throw new IOException("Command line '" + DF + "' did not find free space in response for path '" + str2 + "'- check path is valid");
        } catch (NumberFormatException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Command line '");
            GeneratedOutlineSupport1.outline181(outline107, DF, "' did not return numeric data as expected ", "for path '", str2);
            outline107.append("'- check path is valid");
            throw new IOExceptionWithCause(outline107.toString(), e);
        }
    }

    long parseDir(String str, String str2) throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int length = str.length();
        while (true) {
            length--;
            i = 0;
            if (length < 0) {
                i2 = 0;
                break;
            } else if (Character.isDigit(str.charAt(length))) {
                i2 = length + 1;
                break;
            }
        }
        while (true) {
            if (length < 0) {
                i3 = 0;
                break;
            }
            char charAt = str.charAt(length);
            if (!Character.isDigit(charAt) && charAt != ',' && charAt != '.') {
                i3 = length + 1;
                break;
            }
            length--;
        }
        if (length >= 0) {
            StringBuilder sb = new StringBuilder(str.substring(i3, i2));
            while (i < sb.length()) {
                if (sb.charAt(i) == ',' || sb.charAt(i) == '.') {
                    i4 = i - 1;
                    sb.deleteCharAt(i);
                } else {
                    i4 = i;
                }
                i = i4 + 1;
            }
            return parseBytes(sb.toString(), str2);
        }
        throw new IOException(GeneratedOutlineSupport1.outline75("Command line 'dir /-c' did not return valid info for path '", str2, "'"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0109  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.BufferedReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.util.List<java.lang.String> performCommand(java.lang.String[] r10, int r11, long r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.FileSystemUtils.performCommand(java.lang.String[], int, long):java.util.List");
    }

    public static long freeSpaceKb(String str, long j) throws IOException {
        return INSTANCE.freeSpaceOS(str, OS, true, j);
    }

    public static long freeSpaceKb() throws IOException {
        return freeSpaceKb(-1L);
    }

    public static long freeSpaceKb(long j) throws IOException {
        return freeSpaceKb(new File(".").getAbsolutePath(), j);
    }
}
