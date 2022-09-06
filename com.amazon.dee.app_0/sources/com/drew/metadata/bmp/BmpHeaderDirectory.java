package com.drew.metadata.bmp;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class BmpHeaderDirectory extends Directory {
    public static final int TAG_ALPHA_MASK = 15;
    public static final int TAG_BITMAP_TYPE = -2;
    public static final int TAG_BITS_PER_PIXEL = 4;
    public static final int TAG_BLUE_MASK = 14;
    public static final int TAG_COLOR_ENCODING = 11;
    public static final int TAG_COLOR_SPACE_TYPE = 16;
    public static final int TAG_COLOUR_PLANES = 3;
    public static final int TAG_COMPRESSION = 5;
    public static final int TAG_GAMMA_BLUE = 19;
    public static final int TAG_GAMMA_GREEN = 18;
    public static final int TAG_GAMMA_RED = 17;
    public static final int TAG_GREEN_MASK = 13;
    public static final int TAG_HEADER_SIZE = -1;
    public static final int TAG_IMAGE_HEIGHT = 1;
    public static final int TAG_IMAGE_WIDTH = 2;
    public static final int TAG_IMPORTANT_COLOUR_COUNT = 9;
    public static final int TAG_INTENT = 20;
    public static final int TAG_LINKED_PROFILE = 21;
    public static final int TAG_PALETTE_COLOUR_COUNT = 8;
    public static final int TAG_RED_MASK = 12;
    public static final int TAG_RENDERING = 10;
    public static final int TAG_X_PIXELS_PER_METER = 6;
    public static final int TAG_Y_PIXELS_PER_METER = 7;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    /* renamed from: com.drew.metadata.bmp.BmpHeaderDirectory$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm;
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent = new int[RenderingIntent.values().length];

        static {
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_BUSINESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_GRAPHICS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_IMAGES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingIntent[RenderingIntent.LCS_GM_ABS_COLORIMETRIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType = new int[ColorSpaceType.values().length];
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.LCS_CALIBRATED_RGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.LCS_sRGB.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.LCS_WINDOWS_COLOR_SPACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.PROFILE_LINKED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$ColorSpaceType[ColorSpaceType.PROFILE_EMBEDDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm = new int[RenderingHalftoningAlgorithm.values().length];
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.ERROR_DIFFUSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.PANDA.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$RenderingHalftoningAlgorithm[RenderingHalftoningAlgorithm.SUPER_CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression = new int[Compression.values().length];
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE8.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE4.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_BITFIELDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_HUFFMAN_1D.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_JPEG.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_RLE24.ordinal()] = 7;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_PNG.ordinal()] = 8;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_ALPHABITFIELDS.ordinal()] = 9;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYK.ordinal()] = 10;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYKRLE8.ordinal()] = 11;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$Compression[Compression.BI_CMYKRLE4.ordinal()] = 12;
            } catch (NoSuchFieldError unused25) {
            }
            $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType = new int[BitmapType.values().length];
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.BITMAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_BITMAP_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_COLOR_ICON.ordinal()] = 3;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_COLOR_POINTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_ICON.ordinal()] = 5;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$drew$metadata$bmp$BmpHeaderDirectory$BitmapType[BitmapType.OS2_POINTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum BitmapType {
        BITMAP(BmpReader.BITMAP),
        OS2_BITMAP_ARRAY(BmpReader.OS2_BITMAP_ARRAY),
        OS2_ICON(BmpReader.OS2_ICON),
        OS2_COLOR_ICON(BmpReader.OS2_COLOR_ICON),
        OS2_COLOR_POINTER(BmpReader.OS2_COLOR_POINTER),
        OS2_POINTER(BmpReader.OS2_POINTER);
        
        private final int value;

        BitmapType(int i) {
            this.value = i;
        }

        @Nullable
        public static BitmapType typeOf(int i) {
            BitmapType[] values;
            for (BitmapType bitmapType : values()) {
                if (bitmapType.value == i) {
                    return bitmapType;
                }
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return "Bitmap Array";
                }
                if (ordinal == 2) {
                    return "Monochrome Icon";
                }
                if (ordinal == 3) {
                    return "Color Icon";
                }
                if (ordinal == 4) {
                    return "Color Pointer";
                }
                if (ordinal == 5) {
                    return "Monochrome Pointer";
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unimplemented bitmap type ");
                outline107.append(super.toString());
                throw new IllegalStateException(outline107.toString());
            }
            return "Standard";
        }
    }

    /* loaded from: classes2.dex */
    public enum ColorEncoding {
        RGB(0);
        
        private final int value;

        ColorEncoding(int i) {
            this.value = i;
        }

        @Nullable
        public static ColorEncoding typeOf(int i) {
            if (i == 0) {
                return RGB;
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum ColorSpaceType {
        LCS_CALIBRATED_RGB(0),
        LCS_sRGB(1934772034),
        LCS_WINDOWS_COLOR_SPACE(1466527264),
        PROFILE_LINKED(1279872587),
        PROFILE_EMBEDDED(1296188740);
        
        private final long value;

        ColorSpaceType(long j) {
            this.value = j;
        }

        @Nullable
        public static ColorSpaceType typeOf(long j) {
            ColorSpaceType[] values;
            for (ColorSpaceType colorSpaceType : values()) {
                if (colorSpaceType.value == j) {
                    return colorSpaceType;
                }
            }
            return null;
        }

        public long getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return "sRGB Color Space";
                }
                if (ordinal == 2) {
                    return "System Default Color Space, sRGB";
                }
                if (ordinal == 3) {
                    return "Linked Profile";
                }
                if (ordinal == 4) {
                    return "Embedded Profile";
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unimplemented color space type ");
                outline107.append(super.toString());
                throw new IllegalStateException(outline107.toString());
            }
            return "Calibrated RGB";
        }
    }

    /* loaded from: classes2.dex */
    public enum Compression {
        BI_RGB(0),
        BI_RLE8(1),
        BI_RLE4(2),
        BI_BITFIELDS(3),
        BI_HUFFMAN_1D(3),
        BI_JPEG(4),
        BI_RLE24(4),
        BI_PNG(5),
        BI_ALPHABITFIELDS(6),
        BI_CMYK(11),
        BI_CMYKRLE8(12),
        BI_CMYKRLE4(13);
        
        private final int value;

        Compression(int i) {
            this.value = i;
        }

        @Nullable
        public static Compression typeOf(int i, int i2) {
            switch (i) {
                case 0:
                    return BI_RGB;
                case 1:
                    return BI_RLE8;
                case 2:
                    return BI_RLE4;
                case 3:
                    return i2 == 64 ? BI_HUFFMAN_1D : BI_BITFIELDS;
                case 4:
                    return i2 == 64 ? BI_RLE24 : BI_JPEG;
                case 5:
                    return BI_PNG;
                case 6:
                    return BI_ALPHABITFIELDS;
                case 7:
                case 8:
                case 9:
                case 10:
                default:
                    return null;
                case 11:
                    return BI_CMYK;
                case 12:
                    return BI_CMYKRLE8;
                case 13:
                    return BI_CMYKRLE4;
            }
        }

        @Nullable
        public static Compression typeOf(@NotNull BmpHeaderDirectory bmpHeaderDirectory) {
            Integer integer;
            Integer integer2 = bmpHeaderDirectory.getInteger(5);
            if (integer2 == null || (integer = bmpHeaderDirectory.getInteger(-1)) == null) {
                return null;
            }
            return typeOf(integer2.intValue(), integer.intValue());
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            switch (ordinal()) {
                case 0:
                    return "None";
                case 1:
                    return "RLE 8-bit/pixel";
                case 2:
                    return "RLE 4-bit/pixel";
                case 3:
                    return "Bit Fields";
                case 4:
                    return "Huffman 1D";
                case 5:
                    return "JPEG";
                case 6:
                    return "RLE 24-bit/pixel";
                case 7:
                    return "PNG";
                case 8:
                    return "RGBA Bit Fields";
                case 9:
                    return "CMYK Uncompressed";
                case 10:
                    return "CMYK RLE-8";
                case 11:
                    return "CMYK RLE-4";
                default:
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unimplemented compression type ");
                    outline107.append(super.toString());
                    throw new IllegalStateException(outline107.toString());
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum RenderingHalftoningAlgorithm {
        NONE(0),
        ERROR_DIFFUSION(1),
        PANDA(2),
        SUPER_CIRCLE(3);
        
        private final int value;

        RenderingHalftoningAlgorithm(int i) {
            this.value = i;
        }

        @Nullable
        public static RenderingHalftoningAlgorithm typeOf(int i) {
            RenderingHalftoningAlgorithm[] values;
            for (RenderingHalftoningAlgorithm renderingHalftoningAlgorithm : values()) {
                if (renderingHalftoningAlgorithm.value == i) {
                    return renderingHalftoningAlgorithm;
                }
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return "Error Diffusion Halftoning";
                }
                if (ordinal == 2) {
                    return "Processing Algorithm for Noncoded Document Acquisition";
                }
                if (ordinal == 3) {
                    return "Super-circle Halftoning";
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unimplemented rendering halftoning algorithm type ");
                outline107.append(super.toString());
                throw new IllegalStateException(outline107.toString());
            }
            return "No Halftoning Algorithm";
        }
    }

    /* loaded from: classes2.dex */
    public enum RenderingIntent {
        LCS_GM_BUSINESS(1),
        LCS_GM_GRAPHICS(2),
        LCS_GM_IMAGES(4),
        LCS_GM_ABS_COLORIMETRIC(8);
        
        private final int value;

        RenderingIntent(int i) {
            this.value = i;
        }

        @Nullable
        public static RenderingIntent typeOf(long j) {
            RenderingIntent[] values;
            for (RenderingIntent renderingIntent : values()) {
                if (renderingIntent.value == j) {
                    return renderingIntent;
                }
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return "Proof, Relative Colorimetric";
                }
                if (ordinal == 2) {
                    return "Picture, Perceptual";
                }
                if (ordinal == 3) {
                    return "Match, Absolute Colorimetric";
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unimplemented rendering intent ");
                outline107.append(super.toString());
                throw new IllegalStateException(outline107.toString());
            }
            return "Graphic, Saturation";
        }
    }

    static {
        _tagNameMap.put(-2, "Bitmap type");
        _tagNameMap.put(-1, "Header Size");
        _tagNameMap.put(1, "Image Height");
        _tagNameMap.put(2, "Image Width");
        _tagNameMap.put(3, "Planes");
        _tagNameMap.put(4, "Bits Per Pixel");
        _tagNameMap.put(5, ExifInterface.TAG_COMPRESSION);
        _tagNameMap.put(6, "X Pixels per Meter");
        _tagNameMap.put(7, "Y Pixels per Meter");
        _tagNameMap.put(8, "Palette Colour Count");
        _tagNameMap.put(9, "Important Colour Count");
        _tagNameMap.put(10, "Rendering");
        _tagNameMap.put(11, "Color Encoding");
        _tagNameMap.put(12, "Red Mask");
        _tagNameMap.put(13, "Green Mask");
        _tagNameMap.put(14, "Blue Mask");
        _tagNameMap.put(15, "Alpha Mask");
        _tagNameMap.put(16, "Color Space Type");
        _tagNameMap.put(17, "Red Gamma Curve");
        _tagNameMap.put(18, "Green Gamma Curve");
        _tagNameMap.put(19, "Blue Gamma Curve");
        _tagNameMap.put(20, "Rendering Intent");
        _tagNameMap.put(21, "Linked Profile File Name");
    }

    public BmpHeaderDirectory() {
        setDescriptor(new BmpHeaderDescriptor(this));
    }

    @Nullable
    public BitmapType getBitmapType() {
        Integer integer = getInteger(-2);
        if (integer == null) {
            return null;
        }
        return BitmapType.typeOf(integer.intValue());
    }

    @Nullable
    public ColorEncoding getColorEncoding() {
        Integer integer = getInteger(11);
        if (integer == null) {
            return null;
        }
        return ColorEncoding.typeOf(integer.intValue());
    }

    @Nullable
    public ColorSpaceType getColorSpaceType() {
        Long longObject = getLongObject(16);
        if (longObject == null) {
            return null;
        }
        return ColorSpaceType.typeOf(longObject.longValue());
    }

    @Nullable
    public Compression getCompression() {
        return Compression.typeOf(this);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "BMP Header";
    }

    @Nullable
    public RenderingHalftoningAlgorithm getRendering() {
        Integer integer = getInteger(10);
        if (integer == null) {
            return null;
        }
        return RenderingHalftoningAlgorithm.typeOf(integer.intValue());
    }

    @Nullable
    public RenderingIntent getRenderingIntent() {
        Integer integer = getInteger(20);
        if (integer == null) {
            return null;
        }
        return RenderingIntent.typeOf(integer.intValue());
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
