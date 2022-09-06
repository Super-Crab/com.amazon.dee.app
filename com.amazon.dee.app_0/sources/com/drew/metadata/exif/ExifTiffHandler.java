package com.drew.metadata.exif;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.BufferBoundsException;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.exif.makernotes.AppleMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KodakMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KyoceraMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaType5MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusEquipmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopment2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PentaxMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxHyperFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxUltraFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.RicohMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SamsungType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SigmaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType6MakernoteDirectory;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.tiff.DirectoryTiffHandler;
import com.drew.metadata.xmp.XmpReader;
import io.ktor.http.LinkHeader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class ExifTiffHandler extends DirectoryTiffHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public ExifTiffHandler(@NotNull Metadata metadata, @Nullable Directory directory) {
        super(metadata, directory);
    }

    @NotNull
    private static String getReaderString(@NotNull RandomAccessReader randomAccessReader, int i, int i2) throws IOException {
        try {
            return randomAccessReader.getString(i, i2, Charsets.UTF_8);
        } catch (BufferBoundsException unused) {
            return "";
        }
    }

    private static boolean handlePrintIM(@NotNull Directory directory, int i) {
        if (i == 50341) {
            return true;
        }
        if (i != 3584) {
            return false;
        }
        return (directory instanceof CasioType2MakernoteDirectory) || (directory instanceof KyoceraMakernoteDirectory) || (directory instanceof NikonType2MakernoteDirectory) || (directory instanceof OlympusMakernoteDirectory) || (directory instanceof PanasonicMakernoteDirectory) || (directory instanceof PentaxMakernoteDirectory) || (directory instanceof RicohMakernoteDirectory) || (directory instanceof SanyoMakernoteDirectory) || (directory instanceof SonyType1MakernoteDirectory);
    }

    private static void processBinary(@NotNull Directory directory, int i, @NotNull RandomAccessReader randomAccessReader, int i2, Boolean bool, int i3) throws IOException {
        int i4 = 0;
        while (i4 < i2) {
            if (directory.hasTagName(i4)) {
                if (i4 >= i2 - 1 || !directory.hasTagName(i4 + 1)) {
                    if (bool.booleanValue()) {
                        short[] sArr = new short[i3];
                        for (int i5 = 0; i5 < sArr.length; i5++) {
                            sArr[i5] = randomAccessReader.getInt16(((i4 + i5) * 2) + i);
                        }
                        directory.setObjectArray(i4, sArr);
                    } else {
                        int[] iArr = new int[i3];
                        for (int i6 = 0; i6 < iArr.length; i6++) {
                            iArr[i6] = randomAccessReader.getUInt16(((i4 + i6) * 2) + i);
                        }
                        directory.setObjectArray(i4, iArr);
                    }
                    i4 += i3 - 1;
                } else {
                    directory.setObject(i4, bool.booleanValue() ? Short.valueOf(randomAccessReader.getInt16((i4 * 2) + i)) : Integer.valueOf(randomAccessReader.getUInt16((i4 * 2) + i)));
                }
            }
            i4++;
        }
    }

    private static void processKodakMakernote(@NotNull KodakMakernoteDirectory kodakMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) {
        int i2 = i + 8;
        try {
            kodakMakernoteDirectory.setStringValue(0, randomAccessReader.getStringValue(i2, 8, Charsets.UTF_8));
            kodakMakernoteDirectory.setInt(9, randomAccessReader.getUInt8(i2 + 9));
            kodakMakernoteDirectory.setInt(10, randomAccessReader.getUInt8(i2 + 10));
            kodakMakernoteDirectory.setInt(12, randomAccessReader.getUInt16(i2 + 12));
            kodakMakernoteDirectory.setInt(14, randomAccessReader.getUInt16(i2 + 14));
            kodakMakernoteDirectory.setInt(16, randomAccessReader.getUInt16(i2 + 16));
            kodakMakernoteDirectory.setByteArray(18, randomAccessReader.getBytes(i2 + 18, 2));
            kodakMakernoteDirectory.setByteArray(20, randomAccessReader.getBytes(i2 + 20, 4));
            kodakMakernoteDirectory.setInt(24, randomAccessReader.getUInt16(i2 + 24));
            kodakMakernoteDirectory.setInt(27, randomAccessReader.getUInt8(i2 + 27));
            kodakMakernoteDirectory.setInt(28, randomAccessReader.getUInt8(i2 + 28));
            kodakMakernoteDirectory.setInt(29, randomAccessReader.getUInt8(i2 + 29));
            kodakMakernoteDirectory.setInt(30, randomAccessReader.getUInt16(i2 + 30));
            kodakMakernoteDirectory.setLong(32, randomAccessReader.getUInt32(i2 + 32));
            kodakMakernoteDirectory.setInt(36, randomAccessReader.getInt16(i2 + 36));
            kodakMakernoteDirectory.setInt(56, randomAccessReader.getUInt8(i2 + 56));
            kodakMakernoteDirectory.setInt(64, randomAccessReader.getUInt8(i2 + 64));
            kodakMakernoteDirectory.setInt(92, randomAccessReader.getUInt8(i2 + 92));
            kodakMakernoteDirectory.setInt(93, randomAccessReader.getUInt8(i2 + 93));
            kodakMakernoteDirectory.setInt(94, randomAccessReader.getUInt16(i2 + 94));
            kodakMakernoteDirectory.setInt(96, randomAccessReader.getUInt16(i2 + 96));
            kodakMakernoteDirectory.setInt(98, randomAccessReader.getUInt16(i2 + 98));
            kodakMakernoteDirectory.setInt(100, randomAccessReader.getUInt16(i2 + 100));
            kodakMakernoteDirectory.setInt(102, randomAccessReader.getUInt16(i2 + 102));
            kodakMakernoteDirectory.setInt(104, randomAccessReader.getUInt16(i2 + 104));
            kodakMakernoteDirectory.setInt(107, randomAccessReader.getInt8(i2 + 107));
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error processing Kodak makernote data: ");
            outline107.append(e.getMessage());
            kodakMakernoteDirectory.addError(outline107.toString());
        }
    }

    private boolean processMakernote(int i, @NotNull Set<Integer> set, int i2, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        int i3;
        int int32;
        Class<? extends Directory> cls;
        Class<? extends Directory> cls2;
        int i4;
        int i5 = i;
        Directory firstDirectoryOfType = this._metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        String string = firstDirectoryOfType == null ? null : firstDirectoryOfType.getString(271);
        String readerString = getReaderString(randomAccessReader, i5, 2);
        String readerString2 = getReaderString(randomAccessReader, i5, 3);
        String readerString3 = getReaderString(randomAccessReader, i5, 4);
        String readerString4 = getReaderString(randomAccessReader, i5, 5);
        String readerString5 = getReaderString(randomAccessReader, i5, 6);
        String readerString6 = getReaderString(randomAccessReader, i5, 7);
        String readerString7 = getReaderString(randomAccessReader, i5, 8);
        String readerString8 = getReaderString(randomAccessReader, i5, 9);
        String readerString9 = getReaderString(randomAccessReader, i5, 10);
        String readerString10 = getReaderString(randomAccessReader, i5, 12);
        boolean isMotorolaByteOrder = randomAccessReader.isMotorolaByteOrder();
        if (!"OLYMP\u0000".equals(readerString5) && !"EPSON".equals(readerString4) && !"AGFA".equals(readerString3)) {
            if (!"OLYMPUS\u0000II".equals(readerString9)) {
                if (string == null || !string.toUpperCase().startsWith("MINOLTA")) {
                    if (string == null || !string.trim().toUpperCase().startsWith("NIKON")) {
                        if ("SONY CAM".equals(readerString7) || "SONY DSC".equals(readerString7)) {
                            pushDirectory(SonyType1MakernoteDirectory.class);
                        } else if (string != null && string.startsWith("SONY") && !Arrays.equals(randomAccessReader.getBytes(i5, 2), new byte[]{1, 0})) {
                            pushDirectory(SonyType1MakernoteDirectory.class);
                        } else if ("SEMC MS\u0000\u0000\u0000\u0000\u0000".equals(readerString10)) {
                            randomAccessReader.setMotorolaByteOrder(true);
                            pushDirectory(SonyType6MakernoteDirectory.class);
                            i5 += 20;
                        } else {
                            if ("SIGMA\u0000\u0000\u0000".equals(readerString7) || "FOVEON\u0000\u0000".equals(readerString7)) {
                                pushDirectory(SigmaMakernoteDirectory.class);
                                i3 = 10;
                            } else if ("KDK".equals(readerString2)) {
                                randomAccessReader.setMotorolaByteOrder(readerString6.equals("KDK INFO"));
                                KodakMakernoteDirectory kodakMakernoteDirectory = new KodakMakernoteDirectory();
                                this._metadata.addDirectory(kodakMakernoteDirectory);
                                processKodakMakernote(kodakMakernoteDirectory, i5, randomAccessReader);
                            } else {
                                if ("Canon".equalsIgnoreCase(string)) {
                                    cls = CanonMakernoteDirectory.class;
                                } else if (string == null || !string.toUpperCase().startsWith("CASIO")) {
                                    if ("FUJIFILM".equals(readerString7) || "Fujifilm".equalsIgnoreCase(string)) {
                                        randomAccessReader.setMotorolaByteOrder(false);
                                        int32 = randomAccessReader.getInt32(i5 + 8) + i5;
                                        pushDirectory(FujifilmMakernoteDirectory.class);
                                    } else if ("KYOCERA".equals(readerString6)) {
                                        pushDirectory(KyoceraMakernoteDirectory.class);
                                        i5 += 22;
                                    } else {
                                        if ("LEICA".equals(readerString4)) {
                                            randomAccessReader.setMotorolaByteOrder(false);
                                            if ("LEICA\u0000\u0001\u0000".equals(readerString7) || "LEICA\u0000\u0004\u0000".equals(readerString7) || "LEICA\u0000\u0005\u0000".equals(readerString7) || "LEICA\u0000\u0006\u0000".equals(readerString7) || "LEICA\u0000\u0007\u0000".equals(readerString7)) {
                                                cls2 = LeicaType5MakernoteDirectory.class;
                                            } else {
                                                if ("Leica Camera AG".equals(string)) {
                                                    pushDirectory(LeicaMakernoteDirectory.class);
                                                    i4 = 8;
                                                } else {
                                                    i4 = 8;
                                                    if (!"LEICA".equals(string)) {
                                                        return false;
                                                    }
                                                    pushDirectory(PanasonicMakernoteDirectory.class);
                                                }
                                                i5 += i4;
                                            }
                                        } else if ("Panasonic\u0000\u0000\u0000".equals(readerString10)) {
                                            pushDirectory(PanasonicMakernoteDirectory.class);
                                        } else if ("AOC\u0000".equals(readerString3)) {
                                            pushDirectory(CasioType2MakernoteDirectory.class);
                                            int32 = i5 + 6;
                                        } else if (string != null && (string.toUpperCase().startsWith("PENTAX") || string.toUpperCase().startsWith("ASAHI"))) {
                                            pushDirectory(PentaxMakernoteDirectory.class);
                                            TiffReader.processIfd(this, randomAccessReader, set, i5, i5);
                                        } else if ("SANYO\u0000\u0001\u0000".equals(readerString7)) {
                                            cls2 = SanyoMakernoteDirectory.class;
                                        } else if (string == null || !string.toLowerCase().startsWith("ricoh")) {
                                            if (readerString9.equals("Apple iOS\u0000")) {
                                                boolean isMotorolaByteOrder2 = randomAccessReader.isMotorolaByteOrder();
                                                randomAccessReader.setMotorolaByteOrder(true);
                                                pushDirectory(AppleMakernoteDirectory.class);
                                                TiffReader.processIfd(this, randomAccessReader, set, i5 + 14, i5);
                                                randomAccessReader.setMotorolaByteOrder(isMotorolaByteOrder2);
                                            } else if (randomAccessReader.getUInt16(i5) == 61697) {
                                                ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory = new ReconyxHyperFireMakernoteDirectory();
                                                this._metadata.addDirectory(reconyxHyperFireMakernoteDirectory);
                                                processReconyxHyperFireMakernote(reconyxHyperFireMakernoteDirectory, i5, randomAccessReader);
                                            } else if (readerString8.equalsIgnoreCase("RECONYXUF")) {
                                                ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory = new ReconyxUltraFireMakernoteDirectory();
                                                this._metadata.addDirectory(reconyxUltraFireMakernoteDirectory);
                                                processReconyxUltraFireMakernote(reconyxUltraFireMakernoteDirectory, i5, randomAccessReader);
                                            } else if (!"SAMSUNG".equals(string)) {
                                                return false;
                                            } else {
                                                cls = SamsungType2MakernoteDirectory.class;
                                            }
                                        } else if (readerString.equals("Rv") || readerString2.equals(LinkHeader.Parameters.Rev)) {
                                            return false;
                                        } else {
                                            if (readerString4.equalsIgnoreCase("Ricoh")) {
                                                randomAccessReader.setMotorolaByteOrder(true);
                                                cls2 = RicohMakernoteDirectory.class;
                                            }
                                        }
                                        pushDirectory(cls2);
                                        int32 = i5 + 8;
                                    }
                                } else if ("QVC\u0000\u0000\u0000".equals(readerString5)) {
                                    pushDirectory(CasioType2MakernoteDirectory.class);
                                    i3 = 6;
                                } else {
                                    cls = CasioType1MakernoteDirectory.class;
                                }
                                pushDirectory(cls);
                            }
                            i5 += i3;
                        }
                        i3 = 12;
                        i5 += i3;
                    } else if ("Nikon".equals(readerString4)) {
                        short uInt8 = randomAccessReader.getUInt8(i5 + 6);
                        if (uInt8 == 1) {
                            pushDirectory(NikonType1MakernoteDirectory.class);
                        } else if (uInt8 != 2) {
                            this._currentDirectory.addError("Unsupported Nikon makernote data ignored.");
                        } else {
                            pushDirectory(NikonType2MakernoteDirectory.class);
                            int32 = i5 + 18;
                            i5 += 10;
                        }
                    } else {
                        pushDirectory(NikonType2MakernoteDirectory.class);
                    }
                    randomAccessReader.setMotorolaByteOrder(isMotorolaByteOrder);
                    return true;
                }
                pushDirectory(OlympusMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i5, i2);
                randomAccessReader.setMotorolaByteOrder(isMotorolaByteOrder);
                return true;
            }
            pushDirectory(OlympusMakernoteDirectory.class);
            int32 = i5 + 12;
            TiffReader.processIfd(this, randomAccessReader, set, int32, i5);
            randomAccessReader.setMotorolaByteOrder(isMotorolaByteOrder);
            return true;
        }
        pushDirectory(OlympusMakernoteDirectory.class);
        i3 = 8;
        i5 += i3;
        TiffReader.processIfd(this, randomAccessReader, set, i5, i2);
        randomAccessReader.setMotorolaByteOrder(isMotorolaByteOrder);
        return true;
    }

    private static void processPrintIM(@NotNull PrintIMDirectory printIMDirectory, int i, @NotNull RandomAccessReader randomAccessReader, int i2) throws IOException {
        Boolean bool;
        int i3;
        String str;
        if (i2 == 0) {
            str = "Empty PrintIM data";
        } else if (i2 <= 15) {
            str = "Bad PrintIM data";
        } else {
            String string = randomAccessReader.getString(i, 12, Charsets.UTF_8);
            if (string.startsWith("PrintIM")) {
                int i4 = i + 14;
                int uInt16 = randomAccessReader.getUInt16(i4);
                if (i2 < (uInt16 * 6) + 16) {
                    Boolean valueOf = Boolean.valueOf(randomAccessReader.isMotorolaByteOrder());
                    randomAccessReader.setMotorolaByteOrder(!randomAccessReader.isMotorolaByteOrder());
                    i3 = randomAccessReader.getUInt16(i4);
                    if (i2 < (i3 * 6) + 16) {
                        str = "Bad PrintIM size";
                    } else {
                        bool = valueOf;
                    }
                } else {
                    bool = null;
                    i3 = uInt16;
                }
                printIMDirectory.setObject(0, string.substring(8, 12));
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = (i5 * 6) + i + 16;
                    printIMDirectory.setObject(randomAccessReader.getUInt16(i6), Long.valueOf(randomAccessReader.getUInt32(i6 + 2)));
                }
                if (bool == null) {
                    return;
                }
                randomAccessReader.setMotorolaByteOrder(bool.booleanValue());
                return;
            }
            str = "Invalid PrintIM header";
        }
        printIMDirectory.addError(str);
    }

    private static void processReconyxHyperFireMakernote(@NotNull ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        Integer num;
        reconyxHyperFireMakernoteDirectory.setObject(0, Integer.valueOf(randomAccessReader.getUInt16(i)));
        int i2 = i + 2;
        int uInt16 = randomAccessReader.getUInt16(i2);
        int uInt162 = randomAccessReader.getUInt16(i2 + 2);
        int uInt163 = randomAccessReader.getUInt16(i2 + 4);
        String outline72 = GeneratedOutlineSupport1.outline72(String.format("%04X", Integer.valueOf(randomAccessReader.getUInt16(i2 + 6))), String.format("%04X", Integer.valueOf(randomAccessReader.getUInt16(i2 + 8))));
        try {
            num = Integer.valueOf(Integer.parseInt(outline72));
        } catch (NumberFormatException unused) {
            num = null;
        }
        if (num != null) {
            reconyxHyperFireMakernoteDirectory.setString(2, String.format("%d.%d.%d.%s", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163), num));
        } else {
            reconyxHyperFireMakernoteDirectory.setString(2, String.format("%d.%d.%d", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163)));
            reconyxHyperFireMakernoteDirectory.addError("Error processing Reconyx HyperFire makernote data: build '" + outline72 + "' is not in the expected format and will be omitted from Firmware Version.");
        }
        reconyxHyperFireMakernoteDirectory.setString(12, String.valueOf((char) randomAccessReader.getUInt16(i + 12)));
        int i3 = i + 14;
        reconyxHyperFireMakernoteDirectory.setIntArray(14, new int[]{randomAccessReader.getUInt16(i3), randomAccessReader.getUInt16(i3 + 2)});
        int i4 = i + 18;
        reconyxHyperFireMakernoteDirectory.setInt(18, (randomAccessReader.getUInt16(i4) << 16) + randomAccessReader.getUInt16(i4 + 2));
        int i5 = i + 22;
        int uInt164 = randomAccessReader.getUInt16(i5);
        int uInt165 = randomAccessReader.getUInt16(i5 + 2);
        int uInt166 = randomAccessReader.getUInt16(i5 + 4);
        int uInt167 = randomAccessReader.getUInt16(i5 + 6);
        int uInt168 = randomAccessReader.getUInt16(i5 + 8);
        int uInt169 = randomAccessReader.getUInt16(i5 + 10);
        if (uInt164 < 0 || uInt164 >= 60 || uInt165 < 0 || uInt165 >= 60 || uInt166 < 0 || uInt166 >= 24 || uInt167 < 1 || uInt167 >= 13 || uInt168 < 1 || uInt168 >= 32 || uInt169 < 1 || uInt169 > 9999) {
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Error processing Reconyx HyperFire makernote data: Date/Time Original ", uInt169, ProcessIdUtil.DEFAULT_PROCESSID, uInt167, ProcessIdUtil.DEFAULT_PROCESSID);
            GeneratedOutlineSupport1.outline175(outline110, uInt168, " ", uInt166, ":");
            outline110.append(uInt165);
            outline110.append(":");
            outline110.append(uInt164);
            outline110.append(" is not a valid date/time.");
            reconyxHyperFireMakernoteDirectory.addError(outline110.toString());
        } else {
            reconyxHyperFireMakernoteDirectory.setString(22, String.format("%4d:%2d:%2d %2d:%2d:%2d", Integer.valueOf(uInt169), Integer.valueOf(uInt167), Integer.valueOf(uInt168), Integer.valueOf(uInt166), Integer.valueOf(uInt165), Integer.valueOf(uInt164)));
        }
        reconyxHyperFireMakernoteDirectory.setInt(36, randomAccessReader.getUInt16(i + 36));
        reconyxHyperFireMakernoteDirectory.setInt(38, randomAccessReader.getInt16(i + 38));
        reconyxHyperFireMakernoteDirectory.setInt(40, randomAccessReader.getInt16(i + 40));
        reconyxHyperFireMakernoteDirectory.setStringValue(42, new StringValue(randomAccessReader.getBytes(i + 42, 28), Charsets.UTF_16LE));
        reconyxHyperFireMakernoteDirectory.setInt(72, randomAccessReader.getUInt16(i + 72));
        reconyxHyperFireMakernoteDirectory.setInt(74, randomAccessReader.getUInt16(i + 74));
        reconyxHyperFireMakernoteDirectory.setInt(76, randomAccessReader.getUInt16(i + 76));
        reconyxHyperFireMakernoteDirectory.setInt(78, randomAccessReader.getUInt16(i + 78));
        reconyxHyperFireMakernoteDirectory.setInt(80, randomAccessReader.getUInt16(i + 80));
        reconyxHyperFireMakernoteDirectory.setInt(82, randomAccessReader.getUInt16(i + 82));
        reconyxHyperFireMakernoteDirectory.setDouble(84, randomAccessReader.getUInt16(i + 84) / 1000.0d);
        reconyxHyperFireMakernoteDirectory.setString(86, randomAccessReader.getNullTerminatedString(i + 86, 44, Charsets.UTF_8));
    }

    private static void processReconyxUltraFireMakernote(@NotNull ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory, int i, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        reconyxUltraFireMakernoteDirectory.setString(0, randomAccessReader.getString(i, 9, Charsets.UTF_8));
        reconyxUltraFireMakernoteDirectory.setString(52, randomAccessReader.getString(i + 52, 1, Charsets.UTF_8));
        int i2 = i + 53;
        reconyxUltraFireMakernoteDirectory.setIntArray(53, new int[]{randomAccessReader.getByte(i2), randomAccessReader.getByte(i2 + 1)});
        int i3 = i + 59;
        randomAccessReader.getByte(i3);
        randomAccessReader.getByte(i3 + 1);
        randomAccessReader.getByte(i3 + 2);
        randomAccessReader.getByte(i3 + 3);
        randomAccessReader.getByte(i3 + 4);
        reconyxUltraFireMakernoteDirectory.setInt(67, randomAccessReader.getByte(i + 67));
        reconyxUltraFireMakernoteDirectory.setInt(72, randomAccessReader.getByte(i + 72));
        reconyxUltraFireMakernoteDirectory.setStringValue(75, new StringValue(randomAccessReader.getBytes(i + 75, 14), Charsets.UTF_8));
        reconyxUltraFireMakernoteDirectory.setString(80, randomAccessReader.getNullTerminatedString(i + 80, 20, Charsets.UTF_8));
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean customProcessTag(int i, @NotNull Set<Integer> set, int i2, @NotNull RandomAccessReader randomAccessReader, int i3, int i4) throws IOException {
        Directory directory;
        StringBuilder outline107;
        String message;
        if (i3 == 0) {
            if (this._currentDirectory.containsTag(i3)) {
                return false;
            }
            if (i4 == 0) {
                return true;
            }
        }
        if (i3 != 37500 || !(this._currentDirectory instanceof ExifSubIFDDirectory)) {
            if (i3 == 33723 && (this._currentDirectory instanceof ExifIFD0Directory)) {
                if (randomAccessReader.getInt8(i) != 28) {
                    return false;
                }
                byte[] bytes = randomAccessReader.getBytes(i, i4);
                new IptcReader().extract(new SequentialByteArrayReader(bytes), this._metadata, bytes.length, this._currentDirectory);
                return true;
            } else if (i3 == 700 && (this._currentDirectory instanceof ExifIFD0Directory)) {
                new XmpReader().extract(randomAccessReader.getNullTerminatedBytes(i, i4), this._metadata, this._currentDirectory);
                return true;
            } else if (handlePrintIM(this._currentDirectory, i3)) {
                PrintIMDirectory printIMDirectory = new PrintIMDirectory();
                printIMDirectory.setParent(this._currentDirectory);
                this._metadata.addDirectory(printIMDirectory);
                processPrintIM(printIMDirectory, i, randomAccessReader, i4);
                return true;
            } else {
                if (this._currentDirectory instanceof OlympusMakernoteDirectory) {
                    if (i3 == 8208) {
                        pushDirectory(OlympusEquipmentMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 8224) {
                        pushDirectory(OlympusCameraSettingsMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 8256) {
                        pushDirectory(OlympusImageProcessingMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 8272) {
                        pushDirectory(OlympusFocusInfoMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 12288) {
                        pushDirectory(OlympusRawInfoMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 16384) {
                        pushDirectory(OlympusMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 8240) {
                        pushDirectory(OlympusRawDevelopmentMakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    } else if (i3 == 8241) {
                        pushDirectory(OlympusRawDevelopment2MakernoteDirectory.class);
                        TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                        return true;
                    }
                }
                if (this._currentDirectory instanceof PanasonicRawIFD0Directory) {
                    if (i3 == 19) {
                        PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory = new PanasonicRawWbInfoDirectory();
                        panasonicRawWbInfoDirectory.setParent(this._currentDirectory);
                        this._metadata.addDirectory(panasonicRawWbInfoDirectory);
                        processBinary(panasonicRawWbInfoDirectory, i, randomAccessReader, i4, false, 2);
                        return true;
                    } else if (i3 == 39) {
                        PanasonicRawWbInfo2Directory panasonicRawWbInfo2Directory = new PanasonicRawWbInfo2Directory();
                        panasonicRawWbInfo2Directory.setParent(this._currentDirectory);
                        this._metadata.addDirectory(panasonicRawWbInfo2Directory);
                        processBinary(panasonicRawWbInfo2Directory, i, randomAccessReader, i4, false, 3);
                        return true;
                    } else if (i3 == 281) {
                        PanasonicRawDistortionDirectory panasonicRawDistortionDirectory = new PanasonicRawDistortionDirectory();
                        panasonicRawDistortionDirectory.setParent(this._currentDirectory);
                        this._metadata.addDirectory(panasonicRawDistortionDirectory);
                        processBinary(panasonicRawDistortionDirectory, i, randomAccessReader, i4, true, 1);
                        return true;
                    }
                }
                if (i3 == 46 && (this._currentDirectory instanceof PanasonicRawIFD0Directory)) {
                    try {
                        for (Directory directory2 : JpegMetadataReader.readMetadata(new ByteArrayInputStream(randomAccessReader.getBytes(i, i4))).getDirectories()) {
                            directory2.setParent(this._currentDirectory);
                            this._metadata.addDirectory(directory2);
                        }
                        return true;
                    } catch (JpegProcessingException e) {
                        directory = this._currentDirectory;
                        outline107 = GeneratedOutlineSupport1.outline107("Error processing JpgFromRaw: ");
                        message = e.getMessage();
                        outline107.append(message);
                        directory.addError(outline107.toString());
                        return false;
                    } catch (IOException e2) {
                        directory = this._currentDirectory;
                        outline107 = GeneratedOutlineSupport1.outline107("Error reading JpgFromRaw: ");
                        message = e2.getMessage();
                        outline107.append(message);
                        directory.addError(outline107.toString());
                        return false;
                    }
                }
                return false;
            }
        }
        return processMakernote(i, set, i2, randomAccessReader);
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean hasFollowerIfd() {
        Directory directory = this._currentDirectory;
        if (!(directory instanceof ExifIFD0Directory) && !(directory instanceof ExifImageDirectory)) {
            return directory instanceof ExifThumbnailDirectory;
        }
        pushDirectory(this._currentDirectory.containsTag(ExifDirectoryBase.TAG_PAGE_NUMBER) ? ExifImageDirectory.class : ExifThumbnailDirectory.class);
        return true;
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public void setTiffMarker(int i) throws TiffProcessingException {
        Class<? extends Directory> cls;
        if (i != 42) {
            if (i == 85) {
                cls = PanasonicRawIFD0Directory.class;
                pushDirectory(cls);
            } else if (i != 20306 && i != 21330) {
                throw new TiffProcessingException(String.format("Unexpected TIFF marker: 0x%X", Integer.valueOf(i)));
            }
        }
        cls = ExifIFD0Directory.class;
        pushDirectory(cls);
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    @Nullable
    public Long tryCustomProcessFormat(int i, int i2, long j) {
        return i2 == 13 ? Long.valueOf(j * 4) : i2 == 0 ? 0L : null;
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean tryEnterSubIfd(int i) {
        Class<? extends Directory> cls;
        if (i == 330) {
            pushDirectory(ExifSubIFDDirectory.class);
            return true;
        }
        Directory directory = this._currentDirectory;
        if ((directory instanceof ExifIFD0Directory) || (directory instanceof PanasonicRawIFD0Directory)) {
            if (i == 34665) {
                pushDirectory(ExifSubIFDDirectory.class);
                return true;
            } else if (i == 34853) {
                cls = GpsDirectory.class;
                pushDirectory(cls);
                return true;
            }
        }
        if ((this._currentDirectory instanceof ExifSubIFDDirectory) && i == 40965) {
            cls = ExifInteropDirectory.class;
        } else if (!(this._currentDirectory instanceof OlympusMakernoteDirectory)) {
            return false;
        } else {
            if (i == 8208) {
                cls = OlympusEquipmentMakernoteDirectory.class;
            } else if (i == 8224) {
                cls = OlympusCameraSettingsMakernoteDirectory.class;
            } else if (i == 8256) {
                cls = OlympusImageProcessingMakernoteDirectory.class;
            } else if (i == 8272) {
                cls = OlympusFocusInfoMakernoteDirectory.class;
            } else if (i == 12288) {
                cls = OlympusRawInfoMakernoteDirectory.class;
            } else if (i == 16384) {
                cls = OlympusMakernoteDirectory.class;
            } else if (i == 8240) {
                cls = OlympusRawDevelopmentMakernoteDirectory.class;
            } else if (i != 8241) {
                return false;
            } else {
                cls = OlympusRawDevelopment2MakernoteDirectory.class;
            }
        }
        pushDirectory(cls);
        return true;
    }
}
