package com.drew.imaging;

import com.drew.imaging.avi.AviMetadataReader;
import com.drew.imaging.bmp.BmpMetadataReader;
import com.drew.imaging.eps.EpsMetadataReader;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.ico.IcoMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.imaging.pcx.PcxMetadataReader;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.psd.PsdMetadataReader;
import com.drew.imaging.quicktime.QuickTimeMetadataReader;
import com.drew.imaging.raf.RafMetadataReader;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.imaging.wav.WavMetadataReader;
import com.drew.imaging.webp.WebpMetadataReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.file.FileTypeDirectory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class ImageMetadataReader {

    /* renamed from: com.drew.imaging.ImageMetadataReader$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$imaging$FileType = new int[FileType.values().length];

        static {
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Jpeg.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Tiff.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Arw.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Cr2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Nef.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Orf.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Rw2.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Psd.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Png.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Bmp.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Gif.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Ico.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Pcx.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.WebP.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Raf.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Avi.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Wav.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Mov.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Mp4.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Eps.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Unknown.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    private ImageMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }

    public static void main(@NotNull String[] strArr) throws MetadataException, IOException {
        ArrayList<String> arrayList = new ArrayList(Arrays.asList(strArr));
        boolean remove = arrayList.remove("-markdown");
        boolean remove2 = arrayList.remove("-hex");
        if (arrayList.size() < 1) {
            String implementationVersion = ImageMetadataReader.class.getPackage().getImplementationVersion();
            System.out.println("metadata-extractor version " + implementationVersion);
            System.out.println();
            PrintStream printStream = System.out;
            Object[] objArr = new Object[1];
            if (implementationVersion == null) {
                implementationVersion = "a.b.c";
            }
            objArr[0] = implementationVersion;
            printStream.println(String.format("Usage: java -jar metadata-extractor-%s.jar <filename> [<filename>] [-thumb] [-markdown] [-hex]", objArr));
            System.exit(1);
        }
        for (String str : arrayList) {
            long nanoTime = System.nanoTime();
            File file = new File(str);
            if (!remove && arrayList.size() > 1) {
                System.out.printf("\n***** PROCESSING: %s%n%n", str);
            }
            Metadata metadata = null;
            try {
                metadata = readMetadata(file);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
            long nanoTime2 = System.nanoTime() - nanoTime;
            if (!remove) {
                System.out.printf("Processed %.3f MB file in %.2f ms%n%n", Double.valueOf(file.length() / 1048576.0d), Double.valueOf(nanoTime2 / 1000000.0d));
            }
            if (remove) {
                String name = file.getName();
                String urlEncode = StringUtil.urlEncode(str);
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                String str2 = "";
                String string = exifIFD0Directory == null ? str2 : exifIFD0Directory.getString(271);
                if (exifIFD0Directory != null) {
                    str2 = exifIFD0Directory.getString(272);
                }
                System.out.println();
                System.out.println("---");
                System.out.println();
                System.out.printf("# %s - %s%n", string, str2);
                System.out.println();
                System.out.printf("<a href=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\">%n", urlEncode);
                System.out.printf("<img src=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\" width=\"300\"/><br/>%n", urlEncode);
                System.out.println(name);
                System.out.println("</a>");
                System.out.println();
                System.out.println("Directory | Tag Id | Tag Name | Extracted Value");
                System.out.println(":--------:|-------:|----------|----------------");
            }
            for (Directory directory : metadata.getDirectories()) {
                String name2 = directory.getName();
                for (Tag tag : directory.getTags()) {
                    String tagName = tag.getTagName();
                    String description = tag.getDescription();
                    if (description != null && description.length() > 1024) {
                        description = description.substring(0, 1024) + "...";
                    }
                    if (remove) {
                        System.out.printf("%s|0x%s|%s|%s%n", name2, Integer.toHexString(tag.getTagType()), tagName, description);
                    } else if (remove2) {
                        System.out.printf("[%s - %s] %s = %s%n", name2, tag.getTagTypeHex(), tagName, description);
                    } else {
                        System.out.printf("[%s] %s = %s%n", name2, tagName, description);
                    }
                }
                Iterator<String> it2 = directory.getErrors().iterator();
                while (it2.hasNext()) {
                    System.err.println("ERROR: " + it2.next());
                }
            }
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws ImageProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata readMetadata = readMetadata(fileInputStream, file.length());
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, readMetadata);
            return readMetadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws ImageProcessingException, IOException {
        return readMetadata(inputStream, -1L);
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream, long j) throws ImageProcessingException, IOException {
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        FileType detectFileType = FileTypeDetector.detectFileType(bufferedInputStream);
        Metadata readMetadata = readMetadata(bufferedInputStream, j, detectFileType);
        readMetadata.addDirectory(new FileTypeDirectory(detectFileType));
        return readMetadata;
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream, long j, FileType fileType) throws IOException, ImageProcessingException {
        switch (fileType.ordinal()) {
            case 0:
                throw new ImageProcessingException("File format could not be determined");
            case 1:
                return JpegMetadataReader.readMetadata(inputStream);
            case 2:
            case 17:
            case 19:
            case 20:
            case 21:
            case 23:
                return TiffMetadataReader.readMetadata(new RandomAccessStreamReader(inputStream, 2048, j));
            case 3:
                return PsdMetadataReader.readMetadata(inputStream);
            case 4:
                return PngMetadataReader.readMetadata(inputStream);
            case 5:
                return BmpMetadataReader.readMetadata(inputStream);
            case 6:
                return GifMetadataReader.readMetadata(inputStream);
            case 7:
                return IcoMetadataReader.readMetadata(inputStream);
            case 8:
                return PcxMetadataReader.readMetadata(inputStream);
            case 9:
            case 15:
            case 18:
            default:
                return new Metadata();
            case 10:
                return WavMetadataReader.readMetadata(inputStream);
            case 11:
                return AviMetadataReader.readMetadata(inputStream);
            case 12:
                return WebpMetadataReader.readMetadata(inputStream);
            case 13:
                return QuickTimeMetadataReader.readMetadata(inputStream);
            case 14:
                return Mp4MetadataReader.readMetadata(inputStream);
            case 16:
                return EpsMetadataReader.readMetadata(inputStream);
            case 22:
                return RafMetadataReader.readMetadata(inputStream);
        }
    }
}
