package com.google.common.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.threeten.bp.chrono.HijrahDate;
@GwtIncompatible
/* loaded from: classes3.dex */
public final class Files {
    private static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>() { // from class: com.google.common.io.Files.2
        @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        /* renamed from: successors  reason: avoid collision after fix types in other method */
        public Iterable<File> mo8171successors(File file) {
            File[] listFiles;
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                return Collections.unmodifiableList(Arrays.asList(listFiles));
            }
            return ImmutableList.of();
        }
    };
    private static final int TEMP_DIR_ATTEMPTS = 10000;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FileByteSink extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        public String toString() {
            String valueOf = String.valueOf(this.file);
            String valueOf2 = String.valueOf(this.modes);
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + 20, "Files.asByteSink(", valueOf, ", ", valueOf2);
            outline106.append(")");
            return outline106.toString();
        }

        private FileByteSink(File file, FileWriteMode... fileWriteModeArr) {
            this.file = (File) Preconditions.checkNotNull(file);
            this.modes = ImmutableSet.copyOf(fileWriteModeArr);
        }

        @Override // com.google.common.io.ByteSink
        /* renamed from: openStream */
        public FileOutputStream mo8234openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FileByteSource extends ByteSource {
        private final File file;

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            try {
                FileInputStream fileInputStream = (FileInputStream) Closer.create().register(mo8235openStream());
                return ByteStreams.toByteArray(fileInputStream, fileInputStream.getChannel().size());
            } finally {
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            if (this.file.isFile()) {
                return Optional.of(Long.valueOf(this.file.length()));
            }
            return Optional.absent();
        }

        public String toString() {
            String valueOf = String.valueOf(this.file);
            return GeneratedOutlineSupport1.outline30(valueOf.length() + 20, "Files.asByteSource(", valueOf, ")");
        }

        private FileByteSource(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        @Override // com.google.common.io.ByteSource
        /* renamed from: openStream */
        public FileInputStream mo8235openStream() throws IOException {
            return new FileInputStream(this.file);
        }
    }

    /* loaded from: classes3.dex */
    private enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY { // from class: com.google.common.io.Files.FilePredicate.1
            @Override // java.lang.Enum
            public String toString() {
                return "Files.isDirectory()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE { // from class: com.google.common.io.Files.FilePredicate.2
            @Override // java.lang.Enum
            public String toString() {
                return "Files.isFile()";
            }

            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isFile();
            }
        }
    }

    private Files() {
    }

    @Beta
    @Deprecated
    public static void append(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, FileWriteMode.APPEND).write(charSequence);
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr);
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... fileWriteModeArr) {
        return asByteSink(file, fileWriteModeArr).asCharSink(charset);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    @Beta
    public static void copy(File file, OutputStream outputStream) throws IOException {
        asByteSource(file).copyTo(outputStream);
    }

    @Beta
    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        String valueOf = String.valueOf(file);
        throw new IOException(GeneratedOutlineSupport1.outline29(valueOf.length() + 39, "Unable to create parent directories of ", valueOf));
    }

    @Beta
    @Deprecated
    public static File createTempDir() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(21);
        sb.append(currentTimeMillis);
        sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
        String sb2 = sb.toString();
        for (int i = 0; i < 10000; i++) {
            File file2 = new File(file, GeneratedOutlineSupport1.outline27(GeneratedOutlineSupport1.outline6(sb2, 11), sb2, i));
            if (file2.mkdir()) {
                return file2;
            }
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline85(GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(sb2, GeneratedOutlineSupport1.outline6(sb2, 66)), "Failed to create directory within 10000 attempts (tried ", sb2, "0 to ", sb2), HijrahDate.MAX_VALUE_OF_ERA, ')'));
    }

    @Beta
    public static boolean equal(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        if (file == file2 || file.equals(file2)) {
            return true;
        }
        long length = file.length();
        long length2 = file2.length();
        if (length != 0 && length2 != 0 && length != length2) {
            return false;
        }
        return asByteSource(file).contentEquals(asByteSource(file2));
    }

    @Beta
    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    @Beta
    public static String getFileExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : name.substring(lastIndexOf + 1);
    }

    @Beta
    public static String getNameWithoutExtension(String str) {
        Preconditions.checkNotNull(str);
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? name : name.substring(0, lastIndexOf);
    }

    @Beta
    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return asByteSource(file).hash(hashFunction);
    }

    @Beta
    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    @Beta
    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }

    @Beta
    public static MappedByteBuffer map(File file) throws IOException {
        Preconditions.checkNotNull(file);
        return map(file, FileChannel.MapMode.READ_ONLY);
    }

    private static MappedByteBuffer mapInternal(File file, FileChannel.MapMode mapMode, long j) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(mapMode);
        Closer create = Closer.create();
        try {
            FileChannel fileChannel = (FileChannel) create.register(((RandomAccessFile) create.register(new RandomAccessFile(file, mapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw"))).getChannel());
            if (j == -1) {
                j = fileChannel.size();
            }
            return fileChannel.map(mapMode, 0L, j);
        } finally {
        }
    }

    @Beta
    public static void move(File file, File file2) throws IOException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        if (!file.renameTo(file2)) {
            copy(file, file2);
            if (file.delete()) {
                return;
            }
            if (!file2.delete()) {
                String valueOf = String.valueOf(file2);
                throw new IOException(GeneratedOutlineSupport1.outline29(valueOf.length() + 17, "Unable to delete ", valueOf));
            } else {
                String valueOf2 = String.valueOf(file);
                throw new IOException(GeneratedOutlineSupport1.outline29(valueOf2.length() + 17, "Unable to delete ", valueOf2));
            }
        }
    }

    @Beta
    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    @Beta
    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(charset);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    @Beta
    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return (T) asByteSource(file).read(byteProcessor);
    }

    @Beta
    @Deprecated
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    @Beta
    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) asCharSource(file, charset).readLines(new LineProcessor<List<String>>() { // from class: com.google.common.io.Files.1
            final List<String> result = Lists.newArrayList();

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String str) {
                this.result.add(str);
                return true;
            }

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r4.equals(".") != false) goto L15;
     */
    @com.google.common.annotations.Beta
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String simplifyPath(java.lang.String r11) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r11)
            int r0 = r11.length()
            java.lang.String r1 = "."
            if (r0 != 0) goto Lc
            return r1
        Lc:
            r0 = 47
            com.google.common.base.Splitter r2 = com.google.common.base.Splitter.on(r0)
            com.google.common.base.Splitter r2 = r2.omitEmptyStrings()
            java.lang.Iterable r2 = r2.split(r11)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L23:
            boolean r4 = r2.hasNext()
            r5 = 0
            if (r4 == 0) goto L7d
            java.lang.Object r4 = r2.next()
            java.lang.String r4 = (java.lang.String) r4
            r6 = -1
            int r7 = r4.hashCode()
            r8 = 46
            java.lang.String r9 = ".."
            r10 = 1
            if (r7 == r8) goto L49
            r5 = 1472(0x5c0, float:2.063E-42)
            if (r7 == r5) goto L41
            goto L50
        L41:
            boolean r5 = r4.equals(r9)
            if (r5 == 0) goto L50
            r5 = r10
            goto L51
        L49:
            boolean r7 = r4.equals(r1)
            if (r7 == 0) goto L50
            goto L51
        L50:
            r5 = r6
        L51:
            if (r5 == 0) goto L23
            if (r5 == r10) goto L59
            r3.add(r4)
            goto L23
        L59:
            int r4 = r3.size()
            if (r4 <= 0) goto L79
            int r4 = r3.size()
            int r4 = r4 - r10
            java.lang.Object r4 = r3.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L79
            int r4 = r3.size()
            int r4 = r4 - r10
            r3.remove(r4)
            goto L23
        L79:
            r3.add(r9)
            goto L23
        L7d:
            com.google.common.base.Joiner r2 = com.google.common.base.Joiner.on(r0)
            java.lang.String r2 = r2.join(r3)
            char r11 = r11.charAt(r5)
            java.lang.String r3 = "/"
            if (r11 != r0) goto La2
            java.lang.String r11 = java.lang.String.valueOf(r2)
            int r0 = r11.length()
            if (r0 == 0) goto L9c
            java.lang.String r11 = r3.concat(r11)
            goto La3
        L9c:
            java.lang.String r11 = new java.lang.String
            r11.<init>(r3)
            goto La3
        La2:
            r11 = r2
        La3:
            java.lang.String r0 = "/../"
            boolean r0 = r11.startsWith(r0)
            if (r0 == 0) goto Lb1
            r0 = 3
            java.lang.String r11 = r11.substring(r0)
            goto La3
        Lb1:
            java.lang.String r0 = "/.."
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto Lbb
            r11 = r3
            goto Lc4
        Lbb:
            java.lang.String r0 = ""
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto Lc4
            r11 = r1
        Lc4:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.Files.simplifyPath(java.lang.String):java.lang.String");
    }

    @Beta
    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    @Beta
    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    @Beta
    public static void touch(File file) throws IOException {
        Preconditions.checkNotNull(file);
        if (file.createNewFile() || file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        String valueOf = String.valueOf(file);
        throw new IOException(GeneratedOutlineSupport1.outline29(valueOf.length() + 38, "Unable to update modification time of ", valueOf));
    }

    @Beta
    public static void write(byte[] bArr, File file) throws IOException {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    @Beta
    public static void copy(File file, File file2) throws IOException {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    @Beta
    @Deprecated
    public static void write(CharSequence charSequence, File file, Charset charset) throws IOException {
        asCharSink(file, charset, new FileWriteMode[0]).write(charSequence);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode) throws IOException {
        return mapInternal(file, mapMode, -1L);
    }

    @Beta
    @Deprecated
    @CanIgnoreReturnValue
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return (T) asCharSource(file, charset).readLines(lineProcessor);
    }

    @Beta
    @Deprecated
    public static void copy(File file, Charset charset, Appendable appendable) throws IOException {
        asCharSource(file, charset).copyTo(appendable);
    }

    @Beta
    public static MappedByteBuffer map(File file, FileChannel.MapMode mapMode, long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "size (%s) may not be negative", j);
        return mapInternal(file, mapMode, j);
    }
}
