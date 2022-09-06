package com.drew.tools;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentData;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.Iterables;
import com.drew.lang.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes2.dex */
public class ExtractJpegSegmentTool {
    public static void main(String[] strArr) throws IOException, JpegProcessingException {
        if (strArr.length < 1) {
            printUsage();
            System.exit(1);
        }
        String str = strArr[0];
        if (!new File(str).exists()) {
            System.err.println("File does not exist");
            printUsage();
            System.exit(1);
        }
        HashSet hashSet = new HashSet();
        for (int i = 1; i < strArr.length; i++) {
            JpegSegmentType valueOf = JpegSegmentType.valueOf(strArr[i].toUpperCase());
            if (!valueOf.canContainMetadata) {
                System.err.printf("WARNING: Segment type %s cannot contain metadata so it may not be necessary to extract it%n", valueOf);
            }
            hashSet.add(valueOf);
        }
        if (hashSet.size() == 0) {
            hashSet.addAll(JpegSegmentType.canContainMetadataTypes);
        }
        System.out.println("Reading: " + str);
        saveSegmentFiles(str, JpegSegmentReader.readSegments(new File(str), hashSet));
    }

    private static void printUsage() {
        JpegSegmentType[] jpegSegmentTypeArr;
        System.out.println("USAGE:\n");
        System.out.println("\tjava com.drew.tools.ExtractJpegSegmentTool <filename> [<segment> ...]\n");
        System.out.print("Where <segment> is zero or more of:");
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.canContainMetadata) {
                PrintStream printStream = System.out;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" ");
                outline107.append(jpegSegmentType.toString());
                printStream.print(outline107.toString());
            }
        }
        System.out.println();
    }

    public static void saveSegmentFiles(@NotNull String str, @NotNull JpegSegmentData jpegSegmentData) throws IOException {
        for (JpegSegmentType jpegSegmentType : jpegSegmentData.getSegmentTypes()) {
            List list = Iterables.toList(jpegSegmentData.getSegments(jpegSegmentType));
            if (list.size() != 0) {
                if (list.size() > 1) {
                    for (int i = 0; i < list.size(); i++) {
                        String format = String.format("%s.%s.%d", str, jpegSegmentType.toString().toLowerCase(), Integer.valueOf(i));
                        System.out.println("Writing: " + format);
                        FileUtil.saveBytes(new File(format), (byte[]) list.get(i));
                    }
                } else {
                    String format2 = String.format("%s.%s", str, jpegSegmentType.toString().toLowerCase());
                    System.out.println("Writing: " + format2);
                    FileUtil.saveBytes(new File(format2), (byte[]) list.get(0));
                }
            }
        }
    }
}
