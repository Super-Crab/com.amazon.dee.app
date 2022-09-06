package org.apache.commons.io;

import android.support.v4.media.session.PlaybackStateCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;
/* loaded from: classes4.dex */
public class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    private static final int EOF = -1;
    public static final String LINE_SEPARATOR;
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static byte[] SKIP_BYTE_BUFFER;
    private static char[] SKIP_CHAR_BUFFER;

    static {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter(4);
        PrintWriter printWriter = new PrintWriter(stringBuilderWriter);
        printWriter.println();
        LINE_SEPARATOR = stringBuilderWriter.toString();
        printWriter.close();
    }

    public static void closeQuietly(Reader reader) {
        closeQuietly((Closeable) reader);
    }

    public static boolean contentEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(inputStream2 instanceof BufferedInputStream)) {
            inputStream2 = new BufferedInputStream(inputStream2);
        }
        for (int read = inputStream.read(); -1 != read; read = inputStream.read()) {
            if (read != inputStream2.read()) {
                return false;
            }
        }
        return inputStream2.read() == -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
        if (r1 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean contentEqualsIgnoreEOL(java.io.Reader r3, java.io.Reader r4) throws java.io.IOException {
        /*
            java.io.BufferedReader r3 = toBufferedReader(r3)
            java.io.BufferedReader r4 = toBufferedReader(r4)
            java.lang.String r0 = r3.readLine()
            java.lang.String r1 = r4.readLine()
        L10:
            if (r0 == 0) goto L23
            if (r1 == 0) goto L23
            boolean r2 = r0.equals(r1)
            if (r2 == 0) goto L23
            java.lang.String r0 = r3.readLine()
            java.lang.String r1 = r4.readLine()
            goto L10
        L23:
            if (r0 != 0) goto L2b
            if (r1 != 0) goto L29
            r3 = 1
            goto L2f
        L29:
            r3 = 0
            goto L2f
        L2b:
            boolean r3 = r0.equals(r1)
        L2f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.contentEqualsIgnoreEOL(java.io.Reader, java.io.Reader):boolean");
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long copyLarge = copyLarge(inputStream, outputStream);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copyLarge(inputStream, outputStream, new byte[4096]);
    }

    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }

    public static int read(Reader reader, char[] cArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = i2;
            while (i3 > 0) {
                int read = reader.read(cArr, (i2 - i3) + i, i3);
                if (-1 == read) {
                    break;
                }
                i3 -= read;
            }
            return i2 - i3;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Length must not be negative: ", i2));
    }

    public static void readFully(Reader reader, char[] cArr, int i, int i2) throws IOException {
        int read = read(reader, cArr, i, i2);
        if (read == i2) {
            return;
        }
        throw new EOFException(GeneratedOutlineSupport1.outline53("Length to read: ", i2, " actual: ", read));
    }

    public static List<String> readLines(InputStream inputStream) throws IOException {
        return readLines(new InputStreamReader(inputStream));
    }

    public static long skip(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            if (SKIP_BYTE_BUFFER == null) {
                SKIP_BYTE_BUFFER = new byte[2048];
            }
            long j2 = j;
            while (j2 > 0) {
                long read = inputStream.read(SKIP_BYTE_BUFFER, 0, (int) Math.min(j2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH));
                if (read < 0) {
                    break;
                }
                j2 -= read;
            }
            return j - j2;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Skip count must be non-negative, actual: ", j));
    }

    public static void skipFully(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            long skip = skip(inputStream, j);
            if (skip == j) {
                return;
            }
            StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Bytes to skip: ", j, " actual: ");
            outline111.append(skip);
            throw new EOFException(outline111.toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Bytes to skip must not be negative: ", j));
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(inputStream);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static char[] toCharArray(InputStream inputStream) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        copy(inputStream, charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static InputStream toInputStream(CharSequence charSequence) {
        return toInputStream(charSequence.toString());
    }

    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, (String) null);
    }

    public static void write(byte[] bArr, OutputStream outputStream) throws IOException {
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }

    public static void writeLines(Collection<?> collection, String str, OutputStream outputStream) throws IOException {
        if (collection == null) {
            return;
        }
        if (str == null) {
            str = LINE_SEPARATOR;
        }
        for (Object obj : collection) {
            if (obj != null) {
                outputStream.write(obj.toString().getBytes());
            }
            outputStream.write(str.getBytes());
        }
    }

    public static void closeQuietly(Writer writer) {
        closeQuietly((Closeable) writer);
    }

    public static void copy(InputStream inputStream, Writer writer) throws IOException {
        copy(new InputStreamReader(inputStream), writer);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
                j += read;
            } else {
                return j;
            }
        }
    }

    public static LineIterator lineIterator(InputStream inputStream, String str) throws IOException {
        InputStreamReader inputStreamReader;
        if (str == null) {
            inputStreamReader = new InputStreamReader(inputStream);
        } else {
            inputStreamReader = new InputStreamReader(inputStream, str);
        }
        return new LineIterator(inputStreamReader);
    }

    public static InputStream toInputStream(CharSequence charSequence, String str) throws IOException {
        return toInputStream(charSequence.toString(), str);
    }

    public static String toString(InputStream inputStream, String str) throws IOException {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        copy(inputStream, stringBuilderWriter, str);
        return stringBuilderWriter.toString();
    }

    public static void write(byte[] bArr, Writer writer) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr));
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        closeQuietly((Closeable) inputStream);
    }

    public static int read(Reader reader, char[] cArr) throws IOException {
        return read(reader, cArr, 0, cArr.length);
    }

    public static void readFully(Reader reader, char[] cArr) throws IOException {
        readFully(reader, cArr, 0, cArr.length);
    }

    public static List<String> readLines(InputStream inputStream, String str) throws IOException {
        if (str == null) {
            return readLines(inputStream);
        }
        return readLines(new InputStreamReader(inputStream, str));
    }

    public static InputStream toInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static void write(byte[] bArr, Writer writer, String str) throws IOException {
        if (bArr != null) {
            if (str == null) {
                write(bArr, writer);
            } else {
                writer.write(new String(bArr, str));
            }
        }
    }

    public static void closeQuietly(OutputStream outputStream) {
        closeQuietly((Closeable) outputStream);
    }

    public static void copy(InputStream inputStream, Writer writer, String str) throws IOException {
        if (str == null) {
            copy(inputStream, writer);
        } else {
            copy(new InputStreamReader(inputStream, str), writer);
        }
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, long j, long j2) throws IOException {
        return copyLarge(inputStream, outputStream, j, j2, new byte[4096]);
    }

    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = i2;
            while (i3 > 0) {
                int read = inputStream.read(bArr, (i2 - i3) + i, i3);
                if (-1 == read) {
                    break;
                }
                i3 -= read;
            }
            return i2 - i3;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Length must not be negative: ", i2));
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int read = read(inputStream, bArr, i, i2);
        if (read == i2) {
            return;
        }
        throw new EOFException(GeneratedOutlineSupport1.outline53("Length to read: ", i2, " actual: ", read));
    }

    public static void skipFully(Reader reader, long j) throws IOException {
        long skip = skip(reader, j);
        if (skip == j) {
            return;
        }
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Chars to skip: ", j, " actual: ");
        outline111.append(skip);
        throw new EOFException(outline111.toString());
    }

    public static byte[] toByteArray(InputStream inputStream, long j) throws IOException {
        if (j <= 2147483647L) {
            return toByteArray(inputStream, (int) j);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Size cannot be greater than Integer max value: ", j));
    }

    public static char[] toCharArray(InputStream inputStream, String str) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        copy(inputStream, charArrayWriter, str);
        return charArrayWriter.toCharArray();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, long j, long j2, byte[] bArr) throws IOException {
        long j3 = 0;
        if (j > 0) {
            skipFully(inputStream, j);
        }
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i == 0) {
            return 0L;
        }
        int length = bArr.length;
        int i2 = (i <= 0 || j2 >= ((long) length)) ? length : (int) j2;
        while (i2 > 0) {
            int read = inputStream.read(bArr, 0, i2);
            if (-1 == read) {
                break;
            }
            outputStream.write(bArr, 0, read);
            j3 += read;
            if (i > 0) {
                i2 = (int) Math.min(j2 - j3, length);
            }
        }
        return j3;
    }

    public static long skip(Reader reader, long j) throws IOException {
        if (j >= 0) {
            if (SKIP_CHAR_BUFFER == null) {
                SKIP_CHAR_BUFFER = new char[2048];
            }
            long j2 = j;
            while (j2 > 0) {
                long read = reader.read(SKIP_CHAR_BUFFER, 0, (int) Math.min(j2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH));
                if (read < 0) {
                    break;
                }
                j2 -= read;
            }
            return j - j2;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("Skip count must be non-negative, actual: ", j));
    }

    public static InputStream toInputStream(String str, String str2) throws IOException {
        return new ByteArrayInputStream(str2 != null ? str.getBytes(str2) : str.getBytes());
    }

    public static String toString(Reader reader) throws IOException {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        copy(reader, stringBuilderWriter);
        return stringBuilderWriter.toString();
    }

    public static void write(char[] cArr, Writer writer) throws IOException {
        if (cArr != null) {
            writer.write(cArr);
        }
    }

    public static void writeLines(Collection<?> collection, String str, OutputStream outputStream, String str2) throws IOException {
        if (str2 == null) {
            writeLines(collection, str, outputStream);
        } else if (collection != null) {
            if (str == null) {
                str = LINE_SEPARATOR;
            }
            for (Object obj : collection) {
                if (obj != null) {
                    outputStream.write(obj.toString().getBytes(str2));
                }
                outputStream.write(str.getBytes(str2));
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int read(InputStream inputStream, byte[] bArr) throws IOException {
        return read(inputStream, bArr, 0, bArr.length);
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static List<String> readLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            arrayList.add(readLine);
        }
        return arrayList;
    }

    public static byte[] toByteArray(InputStream inputStream, int i) throws IOException {
        if (i >= 0) {
            int i2 = 0;
            if (i == 0) {
                return new byte[0];
            }
            byte[] bArr = new byte[i];
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 != i) {
                throw new IOException(GeneratedOutlineSupport1.outline53("Unexpected readed size. current: ", i2, ", excepted: ", i));
            }
            return bArr;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Size must be equal or greater than zero: ", i));
    }

    public static void write(char[] cArr, OutputStream outputStream) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes());
        }
    }

    public static void closeQuietly(Selector selector) {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int copy(Reader reader, Writer writer) throws IOException {
        long copyLarge = copyLarge(reader, writer);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    public static char[] toCharArray(Reader reader) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        copy(reader, charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static void write(char[] cArr, OutputStream outputStream, String str) throws IOException {
        if (cArr != null) {
            if (str == null) {
                write(cArr, outputStream);
            } else {
                outputStream.write(new String(cArr).getBytes(str));
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void copy(Reader reader, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        copy(reader, outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static String toString(URI uri) throws IOException {
        return toString(uri, (String) null);
    }

    public static boolean contentEquals(Reader reader, Reader reader2) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        BufferedReader bufferedReader2 = toBufferedReader(reader2);
        for (int read = bufferedReader.read(); -1 != read; read = bufferedReader.read()) {
            if (read != bufferedReader2.read()) {
                return false;
            }
        }
        return bufferedReader2.read() == -1;
    }

    public static String toString(URI uri, String str) throws IOException {
        return toString(uri.toURL(), str);
    }

    public static void write(CharSequence charSequence, Writer writer) throws IOException {
        if (charSequence != null) {
            write(charSequence.toString(), writer);
        }
    }

    public static long copyLarge(Reader reader, Writer writer) throws IOException {
        return copyLarge(reader, writer, new char[4096]);
    }

    public static byte[] toByteArray(Reader reader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(reader, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static String toString(URL url) throws IOException {
        return toString(url, (String) null);
    }

    public static void write(CharSequence charSequence, OutputStream outputStream) throws IOException {
        if (charSequence != null) {
            write(charSequence.toString(), outputStream);
        }
    }

    public static void writeLines(Collection<?> collection, String str, Writer writer) throws IOException {
        if (collection == null) {
            return;
        }
        if (str == null) {
            str = LINE_SEPARATOR;
        }
        for (Object obj : collection) {
            if (obj != null) {
                writer.write(obj.toString());
            }
            writer.write(str);
        }
    }

    public static void copy(Reader reader, OutputStream outputStream, String str) throws IOException {
        if (str == null) {
            copy(reader, outputStream);
            return;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, str);
        copy(reader, outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static long copyLarge(Reader reader, Writer writer, char[] cArr) throws IOException {
        long j = 0;
        while (true) {
            int read = reader.read(cArr);
            if (-1 != read) {
                writer.write(cArr, 0, read);
                j += read;
            } else {
                return j;
            }
        }
    }

    public static String toString(URL url, String str) throws IOException {
        InputStream openStream = url.openStream();
        try {
            return toString(openStream, str);
        } finally {
            openStream.close();
        }
    }

    public static void write(CharSequence charSequence, OutputStream outputStream, String str) throws IOException {
        if (charSequence != null) {
            write(charSequence.toString(), outputStream, str);
        }
    }

    public static void write(String str, Writer writer) throws IOException {
        if (str != null) {
            writer.write(str);
        }
    }

    public static long copyLarge(Reader reader, Writer writer, long j, long j2) throws IOException {
        return copyLarge(reader, writer, j, j2, new char[4096]);
    }

    public static byte[] toByteArray(Reader reader, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(reader, byteArrayOutputStream, str);
        return byteArrayOutputStream.toByteArray();
    }

    public static void write(String str, OutputStream outputStream) throws IOException {
        if (str != null) {
            outputStream.write(str.getBytes());
        }
    }

    public static long copyLarge(Reader reader, Writer writer, long j, long j2, char[] cArr) throws IOException {
        long j3 = 0;
        if (j > 0) {
            skipFully(reader, j);
        }
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i == 0) {
            return 0L;
        }
        int length = cArr.length;
        if (i > 0 && j2 < cArr.length) {
            length = (int) j2;
        }
        while (length > 0) {
            int read = reader.read(cArr, 0, length);
            if (-1 == read) {
                break;
            }
            writer.write(cArr, 0, read);
            j3 += read;
            if (i > 0) {
                length = (int) Math.min(j2 - j3, cArr.length);
            }
        }
        return j3;
    }

    @Deprecated
    public static String toString(byte[] bArr) throws IOException {
        return new String(bArr);
    }

    public static void write(String str, OutputStream outputStream, String str2) throws IOException {
        if (str != null) {
            if (str2 == null) {
                write(str, outputStream);
            } else {
                outputStream.write(str.getBytes(str2));
            }
        }
    }

    @Deprecated
    public static String toString(byte[] bArr, String str) throws IOException {
        if (str == null) {
            return new String(bArr);
        }
        return new String(bArr, str);
    }

    @Deprecated
    public static byte[] toByteArray(String str) throws IOException {
        return str.getBytes();
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, Writer writer) throws IOException {
        if (stringBuffer != null) {
            writer.write(stringBuffer.toString());
        }
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, OutputStream outputStream) throws IOException {
        if (stringBuffer != null) {
            outputStream.write(stringBuffer.toString().getBytes());
        }
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, OutputStream outputStream, String str) throws IOException {
        if (stringBuffer != null) {
            if (str == null) {
                write(stringBuffer, outputStream);
            } else {
                outputStream.write(stringBuffer.toString().getBytes(str));
            }
        }
    }
}
