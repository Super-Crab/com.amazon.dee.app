package org.apache.commons.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.output.NullOutputStream;
/* loaded from: classes4.dex */
public class FileUtils {
    private static final long FILE_COPY_BUFFER_SIZE = 31457280;
    public static final long ONE_EB = 1152921504606846976L;
    public static final long ONE_GB = 1073741824;
    public static final long ONE_KB = 1024;
    public static final long ONE_MB = 1048576;
    public static final long ONE_PB = 1125899906842624L;
    public static final long ONE_TB = 1099511627776L;
    public static final BigInteger ONE_ZB = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
    public static final BigInteger ONE_YB = ONE_ZB.multiply(BigInteger.valueOf(1152921504606846976L));
    public static final File[] EMPTY_FILE_ARRAY = new File[0];
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static String byteCountToDisplaySize(long j) {
        long j2 = j / 1152921504606846976L;
        if (j2 > 0) {
            return String.valueOf(j2) + " EB";
        }
        long j3 = j / ONE_PB;
        if (j3 > 0) {
            return String.valueOf(j3) + " PB";
        }
        long j4 = j / ONE_TB;
        if (j4 > 0) {
            return String.valueOf(j4) + " TB";
        }
        long j5 = j / 1073741824;
        if (j5 > 0) {
            return String.valueOf(j5) + " GB";
        }
        long j6 = j / 1048576;
        if (j6 > 0) {
            return String.valueOf(j6) + " MB";
        }
        long j7 = j / 1024;
        if (j7 > 0) {
            return String.valueOf(j7) + " KB";
        }
        return String.valueOf(j) + " bytes";
    }

    public static Checksum checksum(File file, Checksum checksum) throws IOException {
        CheckedInputStream checkedInputStream;
        if (!file.isDirectory()) {
            CheckedInputStream checkedInputStream2 = null;
            try {
                checkedInputStream = new CheckedInputStream(new FileInputStream(file), checksum);
            } catch (Throwable th) {
                th = th;
            }
            try {
                IOUtils.copy(checkedInputStream, new NullOutputStream());
                IOUtils.closeQuietly((InputStream) checkedInputStream);
                return checksum;
            } catch (Throwable th2) {
                th = th2;
                checkedInputStream2 = checkedInputStream;
                IOUtils.closeQuietly((InputStream) checkedInputStream2);
                throw th;
            }
        }
        throw new IllegalArgumentException("Checksums can't be computed on directories");
    }

    public static long checksumCRC32(File file) throws IOException {
        CRC32 crc32 = new CRC32();
        checksum(file, crc32);
        return crc32.getValue();
    }

    public static void cleanDirectory(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    IOException e = null;
                    for (File file2 : listFiles) {
                        try {
                            forceDelete(file2);
                        } catch (IOException e2) {
                            e = e2;
                        }
                    }
                    if (e != null) {
                        throw e;
                    }
                    return;
                }
                throw new IOException(GeneratedOutlineSupport1.outline63("Failed to list contents of ", file));
            }
            throw new IllegalArgumentException(file + " is not a directory");
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    private static void cleanDirectoryOnExit(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    IOException e = null;
                    for (File file2 : listFiles) {
                        try {
                            forceDeleteOnExit(file2);
                        } catch (IOException e2) {
                            e = e2;
                        }
                    }
                    if (e != null) {
                        throw e;
                    }
                    return;
                }
                throw new IOException(GeneratedOutlineSupport1.outline63("Failed to list contents of ", file));
            }
            throw new IllegalArgumentException(file + " is not a directory");
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static boolean contentEquals(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        boolean exists = file.exists();
        if (exists != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (!file.isDirectory() && !file2.isDirectory()) {
            if (file.length() != file2.length()) {
                return false;
            }
            if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
                return true;
            }
            FileInputStream fileInputStream3 = null;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream2 = new FileInputStream(file2);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
            try {
                boolean contentEquals = IOUtils.contentEquals(fileInputStream, fileInputStream2);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                IOUtils.closeQuietly((InputStream) fileInputStream2);
                return contentEquals;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream3 = fileInputStream2;
                IOUtils.closeQuietly((InputStream) fileInputStream);
                IOUtils.closeQuietly((InputStream) fileInputStream3);
                throw th;
            }
        }
        throw new IOException("Can't compare directories, only files");
    }

    public static boolean contentEqualsIgnoreEOL(File file, File file2, String str) throws IOException {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        boolean exists = file.exists();
        if (exists != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (!file.isDirectory() && !file2.isDirectory()) {
            if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
                return true;
            }
            InputStreamReader inputStreamReader3 = null;
            try {
                if (str == null) {
                    InputStreamReader inputStreamReader4 = new InputStreamReader(new FileInputStream(file));
                    try {
                        inputStreamReader2 = new InputStreamReader(new FileInputStream(file2));
                        inputStreamReader3 = inputStreamReader4;
                    } catch (Throwable th) {
                        th = th;
                        inputStreamReader = null;
                        inputStreamReader3 = inputStreamReader4;
                        IOUtils.closeQuietly((Reader) inputStreamReader3);
                        IOUtils.closeQuietly((Reader) inputStreamReader);
                        throw th;
                    }
                } else {
                    InputStreamReader inputStreamReader5 = new InputStreamReader(new FileInputStream(file), str);
                    try {
                        inputStreamReader2 = new InputStreamReader(new FileInputStream(file2), str);
                        inputStreamReader3 = inputStreamReader5;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                        inputStreamReader3 = inputStreamReader5;
                        IOUtils.closeQuietly((Reader) inputStreamReader3);
                        IOUtils.closeQuietly((Reader) inputStreamReader);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = inputStreamReader3;
            }
            try {
                boolean contentEqualsIgnoreEOL = IOUtils.contentEqualsIgnoreEOL(inputStreamReader3, inputStreamReader2);
                IOUtils.closeQuietly((Reader) inputStreamReader3);
                IOUtils.closeQuietly((Reader) inputStreamReader2);
                return contentEqualsIgnoreEOL;
            } catch (Throwable th4) {
                inputStreamReader = inputStreamReader2;
                th = th4;
                IOUtils.closeQuietly((Reader) inputStreamReader3);
                IOUtils.closeQuietly((Reader) inputStreamReader);
                throw th;
            }
        }
        throw new IOException("Can't compare directories, only files");
    }

    public static File[] convertFileCollectionToFileArray(Collection<File> collection) {
        return (File[]) collection.toArray(new File[collection.size()]);
    }

    public static void copyDirectory(File file, File file2) throws IOException {
        copyDirectory(file, file2, true);
    }

    public static void copyDirectoryToDirectory(File file, File file2) throws IOException {
        if (file != null) {
            if (file.exists() && !file.isDirectory()) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline64("Source '", file2, "' is not a directory"));
            }
            if (file2 != null) {
                if (file2.exists() && !file2.isDirectory()) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' is not a directory"));
                }
                copyDirectory(file, new File(file2, file.getName()), true);
                return;
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void copyFile(File file, File file2) throws IOException {
        copyFile(file, file2, true);
    }

    public static void copyFileToDirectory(File file, File file2) throws IOException {
        copyFileToDirectory(file, file2, true);
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try {
            FileOutputStream openOutputStream = openOutputStream(file);
            IOUtils.copy(inputStream, openOutputStream);
            openOutputStream.close();
            IOUtils.closeQuietly((OutputStream) openOutputStream);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static void copyURLToFile(URL url, File file) throws IOException {
        copyInputStreamToFile(url.openStream(), file);
    }

    static String decodeUrl(String str) {
        int i;
        if (str == null || str.indexOf(37) < 0) {
            return str;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        ByteBuffer allocate = ByteBuffer.allocate(length);
        int i2 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == '%') {
                while (true) {
                    i = i2 + 3;
                    try {
                        try {
                            allocate.put((byte) Integer.parseInt(str.substring(i2 + 1, i), 16));
                            if (i >= length) {
                                break;
                            }
                            try {
                                if (str.charAt(i) != '%') {
                                    break;
                                }
                                i2 = i;
                            } catch (RuntimeException unused) {
                                i2 = i;
                                if (allocate.position() > 0) {
                                    allocate.flip();
                                    stringBuffer.append(UTF8.decode(allocate).toString());
                                    allocate.clear();
                                }
                                stringBuffer.append(str.charAt(i2));
                                i2++;
                            }
                        } catch (RuntimeException unused2) {
                        }
                    } finally {
                        if (allocate.position() > 0) {
                            allocate.flip();
                            stringBuffer.append(UTF8.decode(allocate).toString());
                            allocate.clear();
                        }
                    }
                }
                i2 = i;
            }
            stringBuffer.append(str.charAt(i2));
            i2++;
        }
        return stringBuffer.toString();
    }

    public static void deleteDirectory(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        if (!isSymlink(file)) {
            cleanDirectory(file);
        }
        if (!file.delete()) {
            throw new IOException(GeneratedOutlineSupport1.outline64("Unable to delete directory ", file, "."));
        }
    }

    private static void deleteDirectoryOnExit(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        file.deleteOnExit();
        if (isSymlink(file)) {
            return;
        }
        cleanDirectoryOnExit(file);
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean directoryContains(File file, File file2) throws IOException {
        if (file != null) {
            if (file.isDirectory()) {
                if (file2 == null || !file.exists() || !file2.exists()) {
                    return false;
                }
                return FilenameUtils.directoryContains(file.getCanonicalPath(), file2.getCanonicalPath());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline63("Not a directory: ", file));
        }
        throw new IllegalArgumentException("Directory must not be null");
    }

    private static void doCopyDirectory(File file, File file2, FileFilter fileFilter, boolean z, List<String> list) throws IOException {
        File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        if (listFiles != null) {
            if (file2.exists()) {
                if (!file2.isDirectory()) {
                    throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' exists but is not a directory"));
                }
            } else if (!file2.mkdirs() && !file2.isDirectory()) {
                throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' directory cannot be created"));
            }
            if (file2.canWrite()) {
                for (File file3 : listFiles) {
                    File file4 = new File(file2, file3.getName());
                    if (list == null || !list.contains(file3.getCanonicalPath())) {
                        if (file3.isDirectory()) {
                            doCopyDirectory(file3, file4, fileFilter, z, list);
                        } else {
                            doCopyFile(file3, file4, z);
                        }
                    }
                }
                if (!z) {
                    return;
                }
                file2.setLastModified(file.lastModified());
                return;
            }
            throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' cannot be written to"));
        }
        throw new IOException(GeneratedOutlineSupport1.outline63("Failed to list contents of ", file));
    }

    private static void doCopyFile(File file, File file2, boolean z) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileChannel fileChannel;
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' exists but is a directory"));
        }
        FileChannel fileChannel2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    fileChannel = fileInputStream.getChannel();
                    try {
                        fileChannel2 = fileOutputStream.getChannel();
                        long size = fileChannel.size();
                        long j = 0;
                        while (j < size) {
                            long j2 = size - j;
                            j += fileChannel2.transferFrom(fileChannel, j, j2 > FILE_COPY_BUFFER_SIZE ? 31457280L : j2);
                        }
                        IOUtils.closeQuietly(fileChannel2);
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        IOUtils.closeQuietly(fileChannel);
                        IOUtils.closeQuietly((InputStream) fileInputStream);
                        if (file.length() == file2.length()) {
                            if (!z) {
                                return;
                            }
                            file2.setLastModified(file.lastModified());
                            return;
                        }
                        throw new IOException("Failed to copy full contents from '" + file + "' to '" + file2 + "'");
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(fileChannel2);
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        IOUtils.closeQuietly(fileChannel);
                        IOUtils.closeQuietly((InputStream) fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileChannel = null;
                IOUtils.closeQuietly(fileChannel2);
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                IOUtils.closeQuietly(fileChannel);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!exists) {
            throw new FileNotFoundException(GeneratedOutlineSupport1.outline63("File does not exist: ", file));
        }
        throw new IOException(GeneratedOutlineSupport1.outline63("Unable to delete file: ", file));
    }

    public static void forceDeleteOnExit(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    public static void forceMkdir(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " exists and is not a directory. Unable to create directory.");
        } else if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException(GeneratedOutlineSupport1.outline63("Unable to create directory ", file));
        }
    }

    public static File getFile(File file, String... strArr) {
        if (file != null) {
            if (strArr != null) {
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    i++;
                    file = new File(file, strArr[i]);
                }
                return file;
            }
            throw new NullPointerException("names must not be null");
        }
        throw new NullPointerException("directorydirectory must not be null");
    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    private static void innerListFiles(Collection<File> collection, File file, IOFileFilter iOFileFilter, boolean z) {
        File[] listFiles = file.listFiles((FileFilter) iOFileFilter);
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    if (z) {
                        collection.add(file2);
                    }
                    innerListFiles(collection, file2, iOFileFilter, z);
                } else {
                    collection.add(file2);
                }
            }
        }
    }

    public static boolean isFileNewer(File file, File file2) {
        if (file2 != null) {
            if (file2.exists()) {
                return isFileNewer(file, file2.lastModified());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline64("The reference file '", file2, "' doesn't exist"));
        }
        throw new IllegalArgumentException("No specified reference file");
    }

    public static boolean isFileOlder(File file, File file2) {
        if (file2 != null) {
            if (file2.exists()) {
                return isFileOlder(file, file2.lastModified());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline64("The reference file '", file2, "' doesn't exist"));
        }
        throw new IllegalArgumentException("No specified reference file");
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file != null) {
            if (FilenameUtils.isSystemWindows()) {
                return false;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !file.getCanonicalFile().equals(file.getAbsoluteFile());
        }
        throw new NullPointerException("File must not be null");
    }

    public static Iterator<File> iterateFiles(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return listFiles(file, iOFileFilter, iOFileFilter2).iterator();
    }

    public static Iterator<File> iterateFilesAndDirs(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return listFilesAndDirs(file, iOFileFilter, iOFileFilter2).iterator();
    }

    public static LineIterator lineIterator(File file, String str) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openInputStream(file);
            return IOUtils.lineIterator(fileInputStream, str);
        } catch (IOException e) {
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw e;
        } catch (RuntimeException e2) {
            IOUtils.closeQuietly((InputStream) fileInputStream);
            throw e2;
        }
    }

    public static Collection<File> listFiles(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        validateListFilesParameters(file, iOFileFilter);
        IOFileFilter upEffectiveFileFilter = setUpEffectiveFileFilter(iOFileFilter);
        IOFileFilter upEffectiveDirFilter = setUpEffectiveDirFilter(iOFileFilter2);
        LinkedList linkedList = new LinkedList();
        innerListFiles(linkedList, file, FileFilterUtils.or(upEffectiveFileFilter, upEffectiveDirFilter), false);
        return linkedList;
    }

    public static Collection<File> listFilesAndDirs(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        validateListFilesParameters(file, iOFileFilter);
        IOFileFilter upEffectiveFileFilter = setUpEffectiveFileFilter(iOFileFilter);
        IOFileFilter upEffectiveDirFilter = setUpEffectiveDirFilter(iOFileFilter2);
        LinkedList linkedList = new LinkedList();
        if (file.isDirectory()) {
            linkedList.add(file);
        }
        innerListFiles(linkedList, file, FileFilterUtils.or(upEffectiveFileFilter, upEffectiveDirFilter), true);
        return linkedList;
    }

    public static void moveDirectory(File file, File file2) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        if (!file2.exists()) {
                            if (file.renameTo(file2)) {
                                return;
                            }
                            if (!file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
                                copyDirectory(file, file2);
                                deleteDirectory(file);
                                if (!file.exists()) {
                                    return;
                                }
                                throw new IOException("Failed to delete original directory '" + file + "' after copy to '" + file2 + "'");
                            }
                            throw new IOException("Cannot move directory: " + file + " to a subdirectory of itself: " + file2);
                        }
                        throw new FileExistsException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' already exists"));
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Source '", file, "' is not a directory"));
                }
                throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("Source '", file, "' does not exist"));
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void moveDirectoryToDirectory(File file, File file2, boolean z) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (!file2.exists() && z) {
                    file2.mkdirs();
                }
                if (file2.exists()) {
                    if (file2.isDirectory()) {
                        moveDirectory(file, new File(file2, file.getName()));
                        return;
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' is not a directory"));
                }
                throw new FileNotFoundException("Destination directory '" + file2 + "' does not exist [createDestDir=" + z + "]");
            }
            throw new NullPointerException("Destination directory must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void moveFile(File file, File file2) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (!file.isDirectory()) {
                        if (!file2.exists()) {
                            if (!file2.isDirectory()) {
                                if (file.renameTo(file2)) {
                                    return;
                                }
                                copyFile(file, file2);
                                if (file.delete()) {
                                    return;
                                }
                                deleteQuietly(file2);
                                throw new IOException("Failed to delete original file '" + file + "' after copy to '" + file2 + "'");
                            }
                            throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' is a directory"));
                        }
                        throw new FileExistsException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' already exists"));
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Source '", file, "' is a directory"));
                }
                throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("Source '", file, "' does not exist"));
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void moveFileToDirectory(File file, File file2, boolean z) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (!file2.exists() && z) {
                    file2.mkdirs();
                }
                if (file2.exists()) {
                    if (file2.isDirectory()) {
                        moveFile(file, new File(file2, file.getName()));
                        return;
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' is not a directory"));
                }
                throw new FileNotFoundException("Destination directory '" + file2 + "' does not exist [createDestDir=" + z + "]");
            }
            throw new NullPointerException("Destination directory must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void moveToDirectory(File file, File file2, boolean z) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        moveDirectoryToDirectory(file, file2, z);
                        return;
                    } else {
                        moveFileToDirectory(file, file2, z);
                        return;
                    }
                }
                throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("Source '", file, "' does not exist"));
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (file.canRead()) {
                    return new FileInputStream(file);
                }
                throw new IOException(GeneratedOutlineSupport1.outline64("File '", file, "' cannot be read"));
            }
            throw new IOException(GeneratedOutlineSupport1.outline64("File '", file, "' exists but is a directory"));
        }
        throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("File '", file, "' does not exist"));
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
            try {
                byte[] byteArray = IOUtils.toByteArray(fileInputStream, file.length());
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return byteArray;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((InputStream) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static String readFileToString(File file, String str) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
            try {
                String iOUtils = IOUtils.toString(fileInputStream, str);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return iOUtils;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((InputStream) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static List<String> readLines(File file, String str) throws IOException {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openInputStream(file);
            try {
                List<String> readLines = IOUtils.readLines(fileInputStream, str);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return readLines;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((InputStream) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter iOFileFilter) {
        return iOFileFilter == null ? FalseFileFilter.INSTANCE : FileFilterUtils.and(iOFileFilter, DirectoryFileFilter.INSTANCE);
    }

    private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter iOFileFilter) {
        return FileFilterUtils.and(iOFileFilter, FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE));
    }

    public static long sizeOf(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return sizeOfDirectory(file);
            }
            return file.length();
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static long sizeOfDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                long j = 0;
                if (listFiles == null) {
                    return 0L;
                }
                for (File file2 : listFiles) {
                    j += sizeOf(file2);
                }
                return j;
            }
            throw new IllegalArgumentException(file + " is not a directory");
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static File toFile(URL url) {
        if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
            return null;
        }
        return new File(decodeUrl(url.getFile().replace('/', File.separatorChar)));
    }

    public static File[] toFiles(URL[] urlArr) {
        if (urlArr != null && urlArr.length != 0) {
            File[] fileArr = new File[urlArr.length];
            for (int i = 0; i < urlArr.length; i++) {
                URL url = urlArr[i];
                if (url != null) {
                    if (url.getProtocol().equals("file")) {
                        fileArr[i] = toFile(url);
                    } else {
                        throw new IllegalArgumentException("URL could not be converted to a File: " + url);
                    }
                }
            }
            return fileArr;
        }
        return EMPTY_FILE_ARRAY;
    }

    private static String[] toSuffixes(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".");
            outline107.append(strArr[i]);
            strArr2[i] = outline107.toString();
        }
        return strArr2;
    }

    public static URL[] toURLs(File[] fileArr) throws IOException {
        URL[] urlArr = new URL[fileArr.length];
        for (int i = 0; i < urlArr.length; i++) {
            urlArr[i] = fileArr[i].toURI().toURL();
        }
        return urlArr;
    }

    public static void touch(File file) throws IOException {
        if (!file.exists()) {
            IOUtils.closeQuietly((OutputStream) openOutputStream(file));
        }
        if (file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        throw new IOException(GeneratedOutlineSupport1.outline63("Unable to set the last modification time for ", file));
    }

    private static void validateListFilesParameters(File file, IOFileFilter iOFileFilter) {
        if (file.isDirectory()) {
            if (iOFileFilter == null) {
                throw new NullPointerException("Parameter 'fileFilter' is null");
            }
            return;
        }
        throw new IllegalArgumentException("Parameter 'directory' is not a directory");
    }

    public static boolean waitFor(File file, int i) {
        int i2 = 0;
        int i3 = 0;
        while (!file.exists()) {
            int i4 = i2 + 1;
            if (i2 >= 10) {
                int i5 = i3 + 1;
                if (i3 > i) {
                    return false;
                }
                i3 = i5;
                i2 = 0;
            } else {
                i2 = i4;
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            } catch (Exception unused2) {
                return true;
            }
        }
        return true;
    }

    public static void write(File file, CharSequence charSequence) throws IOException {
        write(file, charSequence, null, false);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr) throws IOException {
        writeByteArrayToFile(file, bArr, false);
    }

    public static void writeLines(File file, String str, Collection<?> collection) throws IOException {
        writeLines(file, str, collection, null, false);
    }

    public static void writeStringToFile(File file, String str, String str2) throws IOException {
        writeStringToFile(file, str, str2, false);
    }

    public static void copyDirectory(File file, File file2, boolean z) throws IOException {
        copyDirectory(file, file2, null, z);
    }

    public static void copyFile(File file, File file2, boolean z) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (!file.isDirectory()) {
                        if (!file.getCanonicalPath().equals(file2.getCanonicalPath())) {
                            File parentFile = file2.getParentFile();
                            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                                throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", parentFile, "' directory cannot be created"));
                            }
                            if (file2.exists() && !file2.canWrite()) {
                                throw new IOException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' exists but is read-only"));
                            }
                            doCopyFile(file, file2, z);
                            return;
                        }
                        throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Source '", file, "' exists but is a directory"));
                }
                throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("Source '", file, "' does not exist"));
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static void copyFileToDirectory(File file, File file2, boolean z) throws IOException {
        if (file2 != null) {
            if (file2.exists() && !file2.isDirectory()) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline64("Destination '", file2, "' is not a directory"));
            }
            copyFile(file, new File(file2, file.getName()), z);
            return;
        }
        throw new NullPointerException("Destination must not be null");
    }

    public static Iterator<File> iterateFiles(File file, String[] strArr, boolean z) {
        return listFiles(file, strArr, z).iterator();
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (!file.canWrite()) {
                    throw new IOException(GeneratedOutlineSupport1.outline64("File '", file, "' cannot be written to"));
                }
            } else {
                throw new IOException(GeneratedOutlineSupport1.outline64("File '", file, "' exists but is a directory"));
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException(GeneratedOutlineSupport1.outline64("Directory '", parentFile, "' could not be created"));
            }
        }
        return new FileOutputStream(file, z);
    }

    public static void write(File file, CharSequence charSequence, boolean z) throws IOException {
        write(file, charSequence, null, z);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
            throw th;
        }
    }

    public static void writeLines(File file, String str, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, str, collection, null, z);
    }

    public static void writeStringToFile(File file, String str, String str2, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            IOUtils.write(str, (OutputStream) fileOutputStream, str2);
            fileOutputStream.close();
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
            throw th;
        }
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter) throws IOException {
        copyDirectory(file, file2, fileFilter, true);
    }

    public static void copyURLToFile(URL url, File file, int i, int i2) throws IOException {
        URLConnection openConnection = url.openConnection();
        openConnection.setConnectTimeout(i);
        openConnection.setReadTimeout(i2);
        copyInputStreamToFile(openConnection.getInputStream(), file);
    }

    public static void write(File file, CharSequence charSequence, String str) throws IOException {
        write(file, charSequence, str, false);
    }

    public static void writeLines(File file, Collection<?> collection) throws IOException {
        writeLines(file, null, collection, null, false);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter, boolean z) throws IOException {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        if (!file.getCanonicalPath().equals(file2.getCanonicalPath())) {
                            ArrayList arrayList = null;
                            if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
                                File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
                                if (listFiles != null && listFiles.length > 0) {
                                    arrayList = new ArrayList(listFiles.length);
                                    for (File file3 : listFiles) {
                                        arrayList.add(new File(file2, file3.getName()).getCanonicalPath());
                                    }
                                }
                            }
                            doCopyDirectory(file, file2, fileFilter, z, arrayList);
                            return;
                        }
                        throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline64("Source '", file, "' exists but is not a directory"));
                }
                throw new FileNotFoundException(GeneratedOutlineSupport1.outline64("Source '", file, "' does not exist"));
            }
            throw new NullPointerException("Destination must not be null");
        }
        throw new NullPointerException("Source must not be null");
    }

    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, null);
    }

    public static List<String> readLines(File file) throws IOException {
        return readLines(file, null);
    }

    public static void write(File file, CharSequence charSequence, String str, boolean z) throws IOException {
        writeStringToFile(file, charSequence == null ? null : charSequence.toString(), str, z);
    }

    public static void writeLines(File file, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, null, collection, null, z);
    }

    public static File getFile(String... strArr) {
        if (strArr != null) {
            File file = null;
            for (String str : strArr) {
                if (file == null) {
                    file = new File(str);
                } else {
                    file = new File(file, str);
                }
            }
            return file;
        }
        throw new NullPointerException("names must not be null");
    }

    public static boolean isFileNewer(File file, Date date) {
        if (date != null) {
            return isFileNewer(file, date.getTime());
        }
        throw new IllegalArgumentException("No specified date");
    }

    public static boolean isFileOlder(File file, Date date) {
        if (date != null) {
            return isFileOlder(file, date.getTime());
        }
        throw new IllegalArgumentException("No specified date");
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2) throws IOException {
        writeLines(file, str, collection, str2, false);
    }

    public static Collection<File> listFiles(File file, String[] strArr, boolean z) {
        IOFileFilter suffixFileFilter;
        if (strArr == null) {
            suffixFileFilter = TrueFileFilter.INSTANCE;
        } else {
            suffixFileFilter = new SuffixFileFilter(toSuffixes(strArr));
        }
        return listFiles(file, suffixFileFilter, z ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, z);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            IOUtils.writeLines(collection, str2, fileOutputStream, str);
            fileOutputStream.close();
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((OutputStream) fileOutputStream);
            throw th;
        }
    }

    public static void writeStringToFile(File file, String str) throws IOException {
        writeStringToFile(file, str, null, false);
    }

    public static boolean isFileNewer(File file, long j) {
        if (file != null) {
            return file.exists() && file.lastModified() > j;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static boolean isFileOlder(File file, long j) {
        if (file != null) {
            return file.exists() && file.lastModified() < j;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static LineIterator lineIterator(File file) throws IOException {
        return lineIterator(file, null);
    }

    public static void writeStringToFile(File file, String str, boolean z) throws IOException {
        writeStringToFile(file, str, null, z);
    }

    public static void writeLines(File file, Collection<?> collection, String str) throws IOException {
        writeLines(file, null, collection, str, false);
    }

    public static void writeLines(File file, Collection<?> collection, String str, boolean z) throws IOException {
        writeLines(file, null, collection, str, z);
    }

    public static long copyFile(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return IOUtils.copyLarge(fileInputStream, outputStream);
        } finally {
            fileInputStream.close();
        }
    }
}
