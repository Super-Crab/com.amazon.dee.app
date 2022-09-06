package com.drew.metadata.photoshop;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class PhotoshopDescriptor extends TagDescriptor<PhotoshopDirectory> {
    public PhotoshopDescriptor(@NotNull PhotoshopDirectory photoshopDirectory) {
        super(photoshopDirectory);
    }

    @Nullable
    private String get32BitNumberString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return String.format("%d", Integer.valueOf(new ByteArrayReader(byteArray).getInt32(0)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    private String getBinaryDataString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return String.format("%d bytes binary data", Integer.valueOf(byteArray.length));
    }

    @Nullable
    private String getBooleanString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        return byteArray[0] == 0 ? "No" : "Yes";
    }

    @Nullable
    private String getSimpleString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return new String(byteArray);
    }

    @Nullable
    public String getClippingPathNameString(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            return new String(byteArrayReader.getBytes(1, byteArrayReader.getByte(0)), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i != 1002) {
            if (i == 1005) {
                return getResolutionInfoDescription();
            }
            if (i == 1028) {
                return getBinaryDataString(i);
            }
            if (i == 1030) {
                return getJpegQualityString();
            }
            if (i != 1044 && i != 1054) {
                if (i == 1057) {
                    return getVersionDescription();
                }
                if (i == 1062) {
                    return getPrintScaleDescription();
                }
                if (i == 1064) {
                    return getPixelAspectRatioString();
                }
                if (i == 2999) {
                    return getClippingPathNameString(i);
                }
                if (i != 1049) {
                    if (i == 1050) {
                        return getSlicesDescription();
                    }
                    switch (i) {
                        case 1033:
                        case 1036:
                            return getThumbnailDescription(i);
                        case 1034:
                            return getBooleanString(i);
                        case 1035:
                            break;
                        case 1037:
                            break;
                        default:
                            return (i < 2000 || i > 2998) ? super.getDescription(i) : getPathString(i);
                    }
                }
            }
            return get32BitNumberString(i);
        }
        return getSimpleString(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    @com.drew.lang.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getJpegQualityString() {
        /*
            r11 = this;
            T extends com.drew.metadata.Directory r0 = r11._directory     // Catch: java.io.IOException -> Lab
            com.drew.metadata.photoshop.PhotoshopDirectory r0 = (com.drew.metadata.photoshop.PhotoshopDirectory) r0     // Catch: java.io.IOException -> Lab
            r1 = 1030(0x406, float:1.443E-42)
            byte[] r0 = r0.getByteArray(r1)     // Catch: java.io.IOException -> Lab
            if (r0 != 0) goto L15
            T extends com.drew.metadata.Directory r0 = r11._directory     // Catch: java.io.IOException -> Lab
            com.drew.metadata.photoshop.PhotoshopDirectory r0 = (com.drew.metadata.photoshop.PhotoshopDirectory) r0     // Catch: java.io.IOException -> Lab
            java.lang.String r0 = r0.getString(r1)     // Catch: java.io.IOException -> Lab
            return r0
        L15:
            com.drew.lang.ByteArrayReader r1 = new com.drew.lang.ByteArrayReader     // Catch: java.io.IOException -> Lab
            r1.<init>(r0)     // Catch: java.io.IOException -> Lab
            r0 = 0
            int r2 = r1.getUInt16(r0)     // Catch: java.io.IOException -> Lab
            r3 = 2
            int r4 = r1.getUInt16(r3)     // Catch: java.io.IOException -> Lab
            r5 = 4
            int r1 = r1.getUInt16(r5)     // Catch: java.io.IOException -> Lab
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r2 > r6) goto L39
            r6 = 65533(0xfffd, float:9.1831E-41)
            if (r2 < r6) goto L39
            r6 = 65532(0xfffc, float:9.183E-41)
            int r6 = r2 - r6
            goto L41
        L39:
            r6 = 8
            if (r2 > r6) goto L40
            int r6 = r2 + 4
            goto L41
        L40:
            r6 = r2
        L41:
            switch(r2) {
                case 0: goto L53;
                case 1: goto L50;
                case 2: goto L50;
                case 3: goto L50;
                case 4: goto L4d;
                case 5: goto L4d;
                case 6: goto L4a;
                case 7: goto L4a;
                case 8: goto L4a;
                default: goto L44;
            }     // Catch: java.io.IOException -> Lab
        L44:
            switch(r2) {
                case 65533: goto L53;
                case 65534: goto L53;
                case 65535: goto L53;
                default: goto L47;
            }     // Catch: java.io.IOException -> Lab
        L47:
            java.lang.String r2 = "Unknown"
            goto L55
        L4a:
            java.lang.String r2 = "Maximum"
            goto L55
        L4d:
            java.lang.String r2 = "High"
            goto L55
        L50:
            java.lang.String r2 = "Medium"
            goto L55
        L53:
            java.lang.String r2 = "Low"
        L55:
            java.lang.String r7 = "Unknown 0x%04X"
            r8 = 1
            if (r4 == 0) goto L73
            if (r4 == r8) goto L70
            r9 = 257(0x101, float:3.6E-43)
            if (r4 == r9) goto L6d
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.io.IOException -> Lab
            r9[r0] = r4     // Catch: java.io.IOException -> Lab
            java.lang.String r4 = java.lang.String.format(r7, r9)     // Catch: java.io.IOException -> Lab
            goto L75
        L6d:
            java.lang.String r4 = "Progressive"
            goto L75
        L70:
            java.lang.String r4 = "Optimised"
            goto L75
        L73:
            java.lang.String r4 = "Standard"
        L75:
            r9 = 3
            if (r1 < r8) goto L8a
            if (r1 > r9) goto L8a
            java.lang.String r7 = "%d"
            java.lang.Object[] r10 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            int r1 = r1 + r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.io.IOException -> Lab
            r10[r0] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r1 = java.lang.String.format(r7, r10)     // Catch: java.io.IOException -> Lab
            goto L96
        L8a:
            java.lang.Object[] r10 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.io.IOException -> Lab
            r10[r0] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r1 = java.lang.String.format(r7, r10)     // Catch: java.io.IOException -> Lab
        L96:
            java.lang.String r7 = "%d (%s), %s format, %s scans"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.io.IOException -> Lab
            r5[r0] = r6     // Catch: java.io.IOException -> Lab
            r5[r8] = r2     // Catch: java.io.IOException -> Lab
            r5[r3] = r4     // Catch: java.io.IOException -> Lab
            r5[r9] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r0 = java.lang.String.format(r7, r5)     // Catch: java.io.IOException -> Lab
            return r0
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.photoshop.PhotoshopDescriptor.getJpegQualityString():java.lang.String");
    }

    @Nullable
    public String getPathString(int i) {
        String str;
        String str2;
        int i2;
        ByteArrayReader byteArrayReader;
        ArrayList arrayList;
        String str3;
        Subpath subpath;
        String str4 = ")";
        String str5 = ",";
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader2 = new ByteArrayReader(byteArray);
            short s = 1;
            int length = ((int) ((byteArrayReader2.getLength() - byteArrayReader2.getByte(((int) byteArrayReader2.getLength()) - 1)) - 1)) / 26;
            Subpath subpath2 = new Subpath();
            Subpath subpath3 = new Subpath();
            ArrayList arrayList2 = new ArrayList();
            String str6 = null;
            Subpath subpath4 = subpath3;
            Subpath subpath5 = subpath2;
            int i3 = 0;
            while (i3 < length) {
                int i4 = i3 * 26;
                try {
                    short int16 = byteArrayReader2.getInt16(i4);
                    Subpath subpath6 = subpath4;
                    switch (int16) {
                        case 0:
                            str = str4;
                            str2 = str5;
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList2;
                            str3 = str6;
                            if (subpath5.size() != 0) {
                                arrayList.add(subpath5);
                            }
                            subpath4 = subpath6;
                            subpath5 = new Subpath("Closed Subpath");
                            break;
                        case 1:
                        case 2:
                            str = str4;
                            str2 = str5;
                            i2 = length;
                            arrayList = arrayList2;
                            str3 = str6;
                            subpath = subpath6;
                            Knot knot = int16 == s ? new Knot("Linked") : new Knot("Unlinked");
                            int i5 = 0;
                            while (i5 < 6) {
                                int i6 = i5 * 4;
                                knot.setPoint(i5, (byteArrayReader2.getInt24((i6 + 3) + i4) / Math.pow(2.0d, 24.0d)) + byteArrayReader2.getInt8(i6 + 2 + i4));
                                i5++;
                                byteArrayReader2 = byteArrayReader2;
                                i4 = i4;
                            }
                            byteArrayReader = byteArrayReader2;
                            subpath5.add(knot);
                            subpath4 = subpath;
                            break;
                        case 3:
                            str = str4;
                            str2 = str5;
                            i2 = length;
                            ArrayList arrayList3 = arrayList2;
                            str3 = str6;
                            if (subpath6.size() != 0) {
                                arrayList = arrayList3;
                                arrayList.add(subpath6);
                            } else {
                                arrayList = arrayList3;
                            }
                            subpath4 = new Subpath("Open Subpath");
                            byteArrayReader = byteArrayReader2;
                            break;
                        case 4:
                        case 5:
                            Knot knot2 = int16 == 4 ? new Knot("Linked") : new Knot("Unlinked");
                            int i7 = 0;
                            int i8 = 6;
                            while (i7 < i8) {
                                int i9 = i7 * 4;
                                knot2.setPoint(i7, (byteArrayReader2.getInt24((i9 + 3) + i4) / Math.pow(2.0d, 24.0d)) + byteArrayReader2.getInt8(i9 + 2 + i4));
                                i7++;
                                i8 = 6;
                                arrayList2 = arrayList2;
                                length = length;
                                str4 = str4;
                                str5 = str5;
                                str6 = str6;
                            }
                            str = str4;
                            str2 = str5;
                            i2 = length;
                            str3 = str6;
                            subpath = subpath6;
                            subpath.add(knot2);
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList2;
                            subpath4 = subpath;
                            break;
                        case 6:
                        case 7:
                        default:
                            str = str4;
                            str2 = str5;
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList2;
                            str3 = str6;
                            subpath = subpath6;
                            subpath4 = subpath;
                            break;
                        case 8:
                            str = str4;
                            str2 = str5;
                            str6 = byteArrayReader2.getInt16(i4 + 2) == s ? "with all pixels" : "without all pixels";
                            i2 = length;
                            byteArrayReader = byteArrayReader2;
                            arrayList = arrayList2;
                            subpath4 = subpath6;
                            continue;
                            i3++;
                            arrayList2 = arrayList;
                            byteArrayReader2 = byteArrayReader;
                            length = i2;
                            str4 = str;
                            str5 = str2;
                            s = 1;
                    }
                    str6 = str3;
                    i3++;
                    arrayList2 = arrayList;
                    byteArrayReader2 = byteArrayReader;
                    length = i2;
                    str4 = str;
                    str5 = str2;
                    s = 1;
                } catch (Exception unused) {
                    return null;
                }
            }
            String str7 = str4;
            String str8 = str5;
            ByteArrayReader byteArrayReader3 = byteArrayReader2;
            ArrayList arrayList4 = arrayList2;
            Subpath subpath7 = subpath4;
            String str9 = str6;
            if (subpath5.size() != 0) {
                arrayList4.add(subpath5);
            }
            if (subpath7.size() != 0) {
                arrayList4.add(subpath7);
            }
            byte b = byteArrayReader3.getByte(((int) byteArrayReader3.getLength()) - 1);
            String string = byteArrayReader3.getString((((int) byteArrayReader3.getLength()) - b) - 1, b, Charsets.ASCII);
            StringBuilder sb = new StringBuilder();
            sb.append('\"');
            sb.append(string);
            sb.append('\"');
            sb.append(" having ");
            if (str9 != null) {
                sb.append("initial fill rule \"");
                sb.append(str9);
                sb.append("\" and ");
            }
            sb.append(arrayList4.size());
            sb.append(arrayList4.size() == 1 ? " subpath:" : " subpaths:");
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                Subpath subpath8 = (Subpath) it2.next();
                sb.append("\n- ");
                sb.append(subpath8.getType());
                sb.append(" with ");
                sb.append(arrayList4.size());
                sb.append(arrayList4.size() == 1 ? " knot:" : " knots:");
                for (Knot knot3 : subpath8.getKnots()) {
                    sb.append("\n  - ");
                    sb.append(knot3.getType());
                    sb.append(" (");
                    sb.append(knot3.getPoint(0));
                    String str10 = str8;
                    sb.append(str10);
                    sb.append(knot3.getPoint(1));
                    String str11 = str7;
                    sb.append(str11);
                    sb.append(" (");
                    sb.append(knot3.getPoint(2));
                    sb.append(str10);
                    sb.append(knot3.getPoint(3));
                    sb.append(str11);
                    sb.append(" (");
                    sb.append(knot3.getPoint(4));
                    sb.append(str10);
                    sb.append(knot3.getPoint(5));
                    sb.append(str11);
                    str8 = str10;
                    str7 = str11;
                }
            }
            return sb.toString();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Nullable
    public String getPixelAspectRatioString() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PIXEL_ASPECT_RATIO);
            if (byteArray != null) {
                return Double.toString(new ByteArrayReader(byteArray).getDouble64(4));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String getPrintScaleDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PRINT_SCALE);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            float float32 = byteArrayReader.getFloat32(2);
            float float322 = byteArrayReader.getFloat32(6);
            float float323 = byteArrayReader.getFloat32(10);
            if (int32 != 0) {
                return int32 != 1 ? int32 != 2 ? String.format("Unknown %04X, X:%s Y:%s, Scale:%s", Integer.valueOf(int32), Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)) : String.format("User defined, X:%s Y:%s, Scale:%s", Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)) : "Size to fit";
            }
            return "Centered, Scale " + float323;
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String getResolutionInfoDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(1005);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            float s15Fixed16 = byteArrayReader.getS15Fixed16(0);
            float s15Fixed162 = byteArrayReader.getS15Fixed16(8);
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            return decimalFormat.format(s15Fixed16) + ReactProperties.HereMapMarker.X + decimalFormat.format(s15Fixed162) + " DPI";
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public String getSlicesDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_SLICES);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(20) * 2;
            return String.format("%s (%d,%d,%d,%d) %d Slices", byteArrayReader.getString(24, int32, "UTF-16"), Integer.valueOf(byteArrayReader.getInt32(4)), Integer.valueOf(byteArrayReader.getInt32(8)), Integer.valueOf(byteArrayReader.getInt32(12)), Integer.valueOf(byteArrayReader.getInt32(16)), Integer.valueOf(byteArrayReader.getInt32(int32 + 24)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String getThumbnailDescription(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(4);
            int int323 = byteArrayReader.getInt32(8);
            int int324 = byteArrayReader.getInt32(16);
            int int325 = byteArrayReader.getInt32(20);
            int int326 = byteArrayReader.getInt32(24);
            Object[] objArr = new Object[6];
            objArr[0] = int32 == 1 ? "JpegRGB" : "RawRGB";
            objArr[1] = Integer.valueOf(int322);
            objArr[2] = Integer.valueOf(int323);
            objArr[3] = Integer.valueOf(int324);
            objArr[4] = Integer.valueOf(int326);
            objArr[5] = Integer.valueOf(int325);
            return String.format("%s, %dx%d, Decomp %d bytes, %d bpp, %d bytes", objArr);
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String getVersionDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_VERSION);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(5) * 2;
            String string = byteArrayReader.getString(9, int322, "UTF-16");
            int i = 9 + int322;
            int int323 = byteArrayReader.getInt32(i);
            int i2 = i + 4;
            int i3 = int323 * 2;
            return String.format("%d (%s, %s) %d", Integer.valueOf(int32), string, byteArrayReader.getString(i2, i3, "UTF-16"), Integer.valueOf(byteArrayReader.getInt32(i2 + i3)));
        } catch (IOException unused) {
            return null;
        }
    }
}
