package com.drew.tools;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.FileTypeDetector;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.ExifThumbnailDirectory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public class ProcessAllImagesInFolderUtility {

    /* loaded from: classes2.dex */
    static class BasicFileHandler extends FileHandlerBase {
        BasicFileHandler() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            for (Directory directory : metadata.getDirectories()) {
                directory.getName();
                for (Tag tag : directory.getTags()) {
                    tag.getTagName();
                    tag.getDescription();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface FileHandler {
        void onBeforeExtraction(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str);

        void onExtractionError(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream);

        void onExtractionSuccess(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream);

        void onScanCompleted(@NotNull PrintStream printStream);

        void onStartingDirectory(@NotNull File file);

        boolean shouldProcess(@NotNull File file);
    }

    /* loaded from: classes2.dex */
    static abstract class FileHandlerBase implements FileHandler {
        private final Set<String> _supportedExtensions = new HashSet(Arrays.asList(ImageType.JPG, "jpeg", ImageType.PNG, "gif", "bmp", "ico", "webp", "pcx", "ai", "eps", "nef", "crw", "cr2", "orf", "arw", "raf", "srw", "x3f", "rw2", "rwl", "tif", "tiff", "psd", "dng", "3g2", "3gp", "m4v", "mov", "mp4", "pbm", "pnm", "pgm"));
        private int _processedFileCount = 0;
        private int _exceptionCount = 0;
        private int _errorCount = 0;
        private long _processedByteCount = 0;

        FileHandlerBase() {
        }

        @Nullable
        protected String getExtension(@NotNull File file) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf == -1 || lastIndexOf == name.length() - 1) {
                return null;
            }
            return name.substring(lastIndexOf + 1);
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onBeforeExtraction(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str) {
            this._processedFileCount++;
            this._processedByteCount = file.length() + this._processedByteCount;
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionError(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream) {
            this._exceptionCount++;
            printStream.printf("\t[%s] %s\n", th.getClass().getName(), th.getMessage());
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            if (metadata.hasErrors()) {
                printStream.print(file);
                printStream.print('\n');
                for (Directory directory : metadata.getDirectories()) {
                    if (directory.hasErrors()) {
                        Iterator<String> it2 = directory.getErrors().iterator();
                        while (it2.hasNext()) {
                            printStream.printf("\t[%s] %s\n", directory.getName(), it2.next());
                            this._errorCount++;
                        }
                    }
                }
            }
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onScanCompleted(@NotNull PrintStream printStream) {
            int i = this._processedFileCount;
            if (i > 0) {
                printStream.print(String.format("Processed %,d files (%,d bytes) with %,d exceptions and %,d file errors\n", Integer.valueOf(i), Long.valueOf(this._processedByteCount), Integer.valueOf(this._exceptionCount), Integer.valueOf(this._errorCount)));
            }
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onStartingDirectory(@NotNull File file) {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public boolean shouldProcess(@NotNull File file) {
            String extension = getExtension(file);
            return extension != null && this._supportedExtensions.contains(extension.toLowerCase());
        }
    }

    /* loaded from: classes2.dex */
    static class MarkdownTableOutputHandler extends FileHandlerBase {
        private final Map<String, String> _extensionEquivalence = new HashMap();
        private final Map<String, List<Row>> _rowListByExtension = new HashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public static class Row {
            @Nullable
            private String exifVersion;
            final File file;
            @Nullable
            private String makernote;
            @Nullable
            private String manufacturer;
            final Metadata metadata;
            @Nullable
            private String model;
            @NotNull
            final String relativePath;
            @Nullable
            private String thumbnail;

            Row(@NotNull File file, @NotNull Metadata metadata, @NotNull String str) {
                boolean z;
                this.file = file;
                this.metadata = metadata;
                this.relativePath = str;
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                ExifThumbnailDirectory exifThumbnailDirectory = (ExifThumbnailDirectory) metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class);
                if (exifIFD0Directory != null) {
                    this.manufacturer = exifIFD0Directory.getDescription(271);
                    this.model = exifIFD0Directory.getDescription(272);
                }
                if (exifSubIFDDirectory != null) {
                    this.exifVersion = exifSubIFDDirectory.getDescription(36864);
                    z = exifSubIFDDirectory.containsTag(ExifDirectoryBase.TAG_MAKERNOTE);
                } else {
                    z = false;
                }
                if (exifThumbnailDirectory != null) {
                    Integer integer = exifThumbnailDirectory.getInteger(256);
                    Integer integer2 = exifThumbnailDirectory.getInteger(257);
                    this.thumbnail = (integer == null || integer2 == null) ? "Yes" : String.format("Yes (%s x %s)", integer, integer2);
                }
                Iterator<Directory> it2 = metadata.getDirectories().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Directory next = it2.next();
                    if (next.getClass().getName().contains("Makernote")) {
                        this.makernote = next.getName().replace("Makernote", "").trim();
                        break;
                    }
                }
                if (this.makernote == null) {
                    this.makernote = z ? "(Unknown)" : CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
                }
            }
        }

        public MarkdownTableOutputHandler() {
            this._extensionEquivalence.put("jpeg", ImageType.JPG);
        }

        private void writeOutput(@NotNull PrintStream printStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(printStream);
            outputStreamWriter.write("# Image Database Summary\n\n");
            for (Map.Entry<String, List<Row>> entry : this._rowListByExtension.entrySet()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("## ");
                outline107.append(entry.getKey().toUpperCase());
                outline107.append(" Files\n\n");
                outputStreamWriter.write(outline107.toString());
                outputStreamWriter.write("File|Manufacturer|Model|Dir Count|Exif?|Makernote|Thumbnail|All Data\n");
                outputStreamWriter.write("----|------------|-----|---------|-----|---------|---------|--------\n");
                List<Row> value = entry.getValue();
                Collections.sort(value, new Comparator<Row>() { // from class: com.drew.tools.ProcessAllImagesInFolderUtility.MarkdownTableOutputHandler.1
                    @Override // java.util.Comparator
                    public int compare(Row row, Row row2) {
                        int compare = StringUtil.compare(row.manufacturer, row2.manufacturer);
                        return compare != 0 ? compare : StringUtil.compare(row.model, row2.model);
                    }
                });
                for (Row row : value) {
                    Object[] objArr = new Object[11];
                    objArr[0] = row.file.getName();
                    objArr[1] = row.relativePath;
                    objArr[2] = StringUtil.urlEncode(row.file.getName());
                    String str = "";
                    objArr[3] = row.manufacturer == null ? str : row.manufacturer;
                    objArr[4] = row.model == null ? str : row.model;
                    objArr[5] = Integer.valueOf(row.metadata.getDirectoryCount());
                    objArr[6] = row.exifVersion == null ? str : row.exifVersion;
                    objArr[7] = row.makernote == null ? str : row.makernote;
                    if (row.thumbnail != null) {
                        str = row.thumbnail;
                    }
                    objArr[8] = str;
                    objArr[9] = row.relativePath;
                    objArr[10] = StringUtil.urlEncode(row.file.getName()).toLowerCase();
                    outputStreamWriter.write(String.format("[%s](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/%s)|%s|%s|%d|%s|%s|%s|[metadata](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/metadata/%s.txt)\n", objArr));
                }
                outputStreamWriter.write(10);
            }
            outputStreamWriter.flush();
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            String extension = getExtension(file);
            if (extension == null) {
                return;
            }
            String lowerCase = extension.toLowerCase();
            if (this._extensionEquivalence.containsKey(lowerCase)) {
                lowerCase = this._extensionEquivalence.get(lowerCase);
            }
            List<Row> list = this._rowListByExtension.get(lowerCase);
            if (list == null) {
                list = new ArrayList<>();
                this._rowListByExtension.put(lowerCase, list);
            }
            list.add(new Row(file, metadata, str));
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onScanCompleted(@com.drew.lang.annotations.NotNull java.io.PrintStream r5) {
            /*
                r4 = this;
                super.onScanCompleted(r5)
                r5 = 0
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L37
                java.lang.String r1 = "../wiki/ImageDatabaseSummary.md"
                r2 = 0
                r0.<init>(r1, r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L37
                java.io.PrintStream r1 = new java.io.PrintStream     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2f
                r1.<init>(r0, r2)     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2f
                r4.writeOutput(r1)     // Catch: java.lang.Throwable -> L1e java.io.IOException -> L24
                r1.flush()     // Catch: java.lang.Throwable -> L1e java.io.IOException -> L24
                r1.close()
                r0.close()     // Catch: java.io.IOException -> L47
                goto L4b
            L1e:
                r5 = move-exception
                r3 = r0
                r0 = r5
                r5 = r1
                r1 = r3
                goto L4d
            L24:
                r5 = move-exception
                r3 = r0
                r0 = r5
                r5 = r1
                r1 = r3
                goto L39
            L2a:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
                goto L4d
            L2f:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
                goto L39
            L34:
                r0 = move-exception
                r1 = r5
                goto L4d
            L37:
                r0 = move-exception
                r1 = r5
            L39:
                r0.printStackTrace()     // Catch: java.lang.Throwable -> L4c
                if (r5 == 0) goto L41
                r5.close()
            L41:
                if (r1 == 0) goto L4b
                r1.close()     // Catch: java.io.IOException -> L47
                goto L4b
            L47:
                r5 = move-exception
                r5.printStackTrace()
            L4b:
                return
            L4c:
                r0 = move-exception
            L4d:
                if (r5 == 0) goto L52
                r5.close()
            L52:
                if (r1 == 0) goto L5c
                r1.close()     // Catch: java.io.IOException -> L58
                goto L5c
            L58:
                r5 = move-exception
                r5.printStackTrace()
            L5c:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.MarkdownTableOutputHandler.onScanCompleted(java.io.PrintStream):void");
        }
    }

    /* loaded from: classes2.dex */
    static class TextFileOutputHandler extends FileHandlerBase {
        private static final String NEW_LINE = "\n";

        TextFileOutputHandler() {
        }

        private static void closeWriter(@Nullable Writer writer) throws IOException {
            if (writer != null) {
                writer.write("Generated using metadata-extractor\n");
                writer.write("https://drewnoakes.com/code/exif/\n");
                writer.flush();
                writer.close();
            }
        }

        private static void deleteRecursively(@NotNull File file) {
            String[] list;
            if (file.isDirectory()) {
                if (file.exists() && (list = file.list()) != null) {
                    for (String str : list) {
                        File file2 = new File(str);
                        if (file2.isDirectory()) {
                            deleteRecursively(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
                file.delete();
                return;
            }
            throw new IllegalArgumentException("Must be a directory.");
        }

        @NotNull
        private static PrintWriter openWriter(@NotNull File file) throws IOException {
            File file2 = new File(String.format("%s/metadata", file.getParent()));
            if (!file2.exists()) {
                file2.mkdir();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(String.format("%s/metadata/%s.txt", file.getParent(), file.getName())), "UTF-8");
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FILE: ");
            outline107.append(file.getName());
            outline107.append("\n");
            outputStreamWriter.write(outline107.toString());
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    outputStreamWriter.write(String.format("TYPE: %s\n", FileTypeDetector.detectFileType(bufferedInputStream2).toString().toUpperCase()));
                    outputStreamWriter.write("\n");
                    bufferedInputStream2.close();
                    return new PrintWriter(outputStreamWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0029 A[LOOP:1: B:13:0x0025->B:15:0x0029, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static void writeHierarchyLevel(@com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r4, @com.drew.lang.annotations.NotNull java.io.PrintWriter r5, @com.drew.lang.annotations.Nullable com.drew.metadata.Directory r6, int r7) {
            /*
                java.lang.Iterable r0 = r4.getDirectories()
                java.util.Iterator r0 = r0.iterator()
            L8:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L48
                java.lang.Object r1 = r0.next()
                com.drew.metadata.Directory r1 = (com.drew.metadata.Directory) r1
                com.drew.metadata.Directory r2 = r1.getParent()
                if (r6 != 0) goto L1d
                if (r2 == 0) goto L24
                goto L8
            L1d:
                boolean r2 = r6.equals(r2)
                if (r2 != 0) goto L24
                goto L8
            L24:
                r2 = 0
            L25:
                int r3 = r7 * 4
                if (r2 >= r3) goto L31
                r3 = 32
                r5.write(r3)
                int r2 = r2 + 1
                goto L25
            L31:
                java.lang.String r2 = "- "
                r5.write(r2)
                java.lang.String r2 = r1.getName()
                r5.write(r2)
                java.lang.String r2 = "\n"
                r5.write(r2)
                int r2 = r7 + 1
                writeHierarchyLevel(r4, r5, r1, r2)
                goto L8
            L48:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.writeHierarchyLevel(com.drew.metadata.Metadata, java.io.PrintWriter, com.drew.metadata.Directory, int):void");
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onBeforeExtraction(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str) {
            super.onBeforeExtraction(file, printStream, str);
            printStream.print(file.getAbsoluteFile());
            printStream.print("\n");
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionError(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream) {
            super.onExtractionError(file, th, printStream);
            try {
                PrintWriter openWriter = openWriter(file);
                openWriter.write("EXCEPTION: " + th.getMessage() + "\n");
                openWriter.write("\n");
                closeWriter(openWriter);
            } catch (IOException e) {
                printStream.printf("IO exception writing metadata file: %s%s", e.getMessage(), "\n");
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:102:0x005f A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x012f A[SYNTHETIC] */
        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onExtractionSuccess(@com.drew.lang.annotations.NotNull java.io.File r17, @com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r18, @com.drew.lang.annotations.NotNull java.lang.String r19, @com.drew.lang.annotations.NotNull java.io.PrintStream r20) {
            /*
                Method dump skipped, instructions count: 335
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.onExtractionSuccess(java.io.File, com.drew.metadata.Metadata, java.lang.String, java.io.PrintStream):void");
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onStartingDirectory(@NotNull File file) {
            super.onStartingDirectory(file);
            File file2 = new File(file + "/metadata");
            if (file2.exists()) {
                deleteRecursively(file2);
            }
        }
    }

    /* loaded from: classes2.dex */
    static class UnknownTagHandler extends FileHandlerBase {
        private HashMap<String, HashMap<Integer, Integer>> _occurrenceCountByTagByDirectory = new HashMap<>();

        UnknownTagHandler() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if (!tag.hasTagName()) {
                        HashMap<Integer, Integer> hashMap = this._occurrenceCountByTagByDirectory.get(directory.getName());
                        if (hashMap == null) {
                            hashMap = new HashMap<>();
                            this._occurrenceCountByTagByDirectory.put(directory.getName(), hashMap);
                        }
                        Integer num = hashMap.get(Integer.valueOf(tag.getTagType()));
                        if (num == null) {
                            hashMap.put(Integer.valueOf(tag.getTagType()), 0);
                            num = 0;
                        }
                        hashMap.put(Integer.valueOf(tag.getTagType()), Integer.valueOf(num.intValue() + 1));
                    }
                }
            }
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onScanCompleted(@NotNull PrintStream printStream) {
            super.onScanCompleted(printStream);
            for (Map.Entry<String, HashMap<Integer, Integer>> entry : this._occurrenceCountByTagByDirectory.entrySet()) {
                String key = entry.getKey();
                ArrayList<Map.Entry> arrayList = new ArrayList(entry.getValue().entrySet());
                Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Integer>>() { // from class: com.drew.tools.ProcessAllImagesInFolderUtility.UnknownTagHandler.1
                    @Override // java.util.Comparator
                    public int compare(Map.Entry<Integer, Integer> entry2, Map.Entry<Integer, Integer> entry3) {
                        return entry3.getValue().compareTo(entry2.getValue());
                    }
                });
                for (Map.Entry entry2 : arrayList) {
                    printStream.format("%s, 0x%04X, %d\n", key, (Integer) entry2.getKey(), (Integer) entry2.getValue());
                }
            }
        }
    }

    public static void main(String[] strArr) throws IOException, JpegProcessingException {
        ArrayList<String> arrayList = new ArrayList();
        FileHandler fileHandler = null;
        PrintStream printStream = System.out;
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (str.equalsIgnoreCase("--text")) {
                fileHandler = new TextFileOutputHandler();
            } else if (str.equalsIgnoreCase("--markdown")) {
                fileHandler = new MarkdownTableOutputHandler();
            } else if (str.equalsIgnoreCase("--unknown")) {
                fileHandler = new UnknownTagHandler();
            } else if (str.equalsIgnoreCase("--log-file")) {
                if (i == strArr.length - 1) {
                    printUsage();
                    System.exit(1);
                }
                i++;
                printStream = new PrintStream((OutputStream) new FileOutputStream(strArr[i], false), true);
            } else {
                arrayList.add(str);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            System.err.println("Expects one or more directories as arguments.");
            printUsage();
            System.exit(1);
        }
        if (fileHandler == null) {
            fileHandler = new BasicFileHandler();
        }
        long nanoTime = System.nanoTime();
        for (String str2 : arrayList) {
            processDirectory(new File(str2), fileHandler, "", printStream);
        }
        fileHandler.onScanCompleted(printStream);
        System.out.println(String.format("Completed in %d ms", Long.valueOf((System.nanoTime() - nanoTime) / 1000000)));
        if (printStream != System.out) {
            printStream.close();
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println();
        System.out.println("  java com.drew.tools.ProcessAllImagesInFolderUtility [--text|--markdown|--unknown] [--log-file <file-name>]");
    }

    private static void processDirectory(@NotNull File file, @NotNull FileHandler fileHandler, @NotNull String str, PrintStream printStream) {
        fileHandler.onStartingDirectory(file);
        String[] list = file.list();
        if (list == null) {
            return;
        }
        Arrays.sort(list);
        for (String str2 : list) {
            File file2 = new File(file, str2);
            if (file2.isDirectory()) {
                if (str.length() != 0) {
                    str2 = GeneratedOutlineSupport1.outline75(str, "/", str2);
                }
                processDirectory(file2, fileHandler, str2, printStream);
            } else if (fileHandler.shouldProcess(file2)) {
                fileHandler.onBeforeExtraction(file2, printStream, str);
                try {
                    fileHandler.onExtractionSuccess(file2, ImageMetadataReader.readMetadata(file2), str, printStream);
                } catch (Throwable th) {
                    fileHandler.onExtractionError(file2, th, printStream);
                }
            }
        }
    }
}
