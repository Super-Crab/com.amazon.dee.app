package com.google.android.exoplayer2.extractor.mp4;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.net.HttpHeaders;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class MetadataUtil {
    private static final String LANGUAGE_UNDEFINED = "und";
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int SHORT_TYPE_ALBUM = 6384738;
    private static final int SHORT_TYPE_ARTIST = 4280916;
    private static final int SHORT_TYPE_COMMENT = 6516084;
    private static final int SHORT_TYPE_COMPOSER_1 = 6516589;
    private static final int SHORT_TYPE_COMPOSER_2 = 7828084;
    private static final int SHORT_TYPE_ENCODER = 7630703;
    private static final int SHORT_TYPE_GENRE = 6776174;
    private static final int SHORT_TYPE_LYRICS = 7108978;
    private static final int SHORT_TYPE_NAME_1 = 7233901;
    private static final int SHORT_TYPE_NAME_2 = 7631467;
    private static final int SHORT_TYPE_YEAR = 6578553;
    @VisibleForTesting
    static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop", "Abstract", "Art Rock", "Baroque", "Bhangra", "Big beat", "Breakbeat", "Chillout", "Downtempo", "Dub", "EBM", "Eclectic", "Electro", "Electroclash", "Emo", "Experimental", "Garage", "Global", "IDM", "Illbient", "Industro-Goth", "Jam Band", "Krautrock", "Leftfield", "Lounge", "Math Rock", "New Romantic", "Nu-Breakz", "Post-Punk", "Post-Rock", "Psytrance", "Shoegaze", "Space Rock", "Trop Rock", "World Music", "Neoclassical", "Audiobook", "Audio theatre", "Neue Deutsche Welle", "Podcast", "Indie-Rock", "G-Funk", "Dubstep", "Garage Rock", "Psybient"};
    private static final String TAG = "MetadataUtil";
    private static final int TYPE_ALBUM_ARTIST = 1631670868;
    private static final int TYPE_COMPILATION = 1668311404;
    private static final int TYPE_COVER_ART = 1668249202;
    private static final int TYPE_DISK_NUMBER = 1684632427;
    private static final int TYPE_GAPLESS_ALBUM = 1885823344;
    private static final int TYPE_GENRE = 1735291493;
    private static final int TYPE_GROUPING = 6779504;
    private static final int TYPE_INTERNAL = 757935405;
    private static final int TYPE_RATING = 1920233063;
    private static final int TYPE_SORT_ALBUM = 1936679276;
    private static final int TYPE_SORT_ALBUM_ARTIST = 1936679265;
    private static final int TYPE_SORT_ARTIST = 1936679282;
    private static final int TYPE_SORT_COMPOSER = 1936679791;
    private static final int TYPE_SORT_TRACK_NAME = 1936682605;
    private static final int TYPE_TEMPO = 1953329263;
    private static final int TYPE_TOP_BYTE_COPYRIGHT = 169;
    private static final int TYPE_TOP_BYTE_REPLACEMENT = 253;
    private static final int TYPE_TRACK_NUMBER = 1953655662;
    private static final int TYPE_TV_SHOW = 1953919848;
    private static final int TYPE_TV_SORT_SHOW = 1936683886;

    private MetadataUtil() {
    }

    @Nullable
    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame("und", readNullTerminatedString, readNullTerminatedString);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse comment attribute: ");
        outline107.append(Atom.getAtomTypeString(i));
        Log.w(TAG, outline107.toString());
        return null;
    }

    @Nullable
    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = parseFullAtomFlags == 13 ? MimeTypes.IMAGE_JPEG : parseFullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                GeneratedOutlineSupport1.outline152("Unrecognized cover art flags: ", parseFullAtomFlags, TAG);
                return null;
            }
            parsableByteArray.skipBytes(4);
            byte[] bArr = new byte[readInt - 16];
            parsableByteArray.readBytes(bArr, 0, bArr.length);
            return new ApicFrame(str, null, 3, bArr);
        }
        Log.w(TAG, "Failed to parse cover art attribute");
        return null;
    }

    @Nullable
    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt() + parsableByteArray.getPosition();
        int readInt2 = parsableByteArray.readInt();
        int i = (readInt2 >> 24) & 255;
        try {
            if (i == 169 || i == 253) {
                int i2 = 16777215 & readInt2;
                if (i2 == SHORT_TYPE_COMMENT) {
                    return parseCommentAttribute(readInt2, parsableByteArray);
                }
                if (i2 == SHORT_TYPE_NAME_1 || i2 == SHORT_TYPE_NAME_2) {
                    return parseTextAttribute(readInt2, "TIT2", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_COMPOSER_1 || i2 == SHORT_TYPE_COMPOSER_2) {
                    return parseTextAttribute(readInt2, "TCOM", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_YEAR) {
                    return parseTextAttribute(readInt2, "TDRC", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_ARTIST) {
                    return parseTextAttribute(readInt2, "TPE1", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_ENCODER) {
                    return parseTextAttribute(readInt2, "TSSE", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_ALBUM) {
                    return parseTextAttribute(readInt2, "TALB", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_LYRICS) {
                    return parseTextAttribute(readInt2, "USLT", parsableByteArray);
                }
                if (i2 == SHORT_TYPE_GENRE) {
                    return parseTextAttribute(readInt2, "TCON", parsableByteArray);
                }
                if (i2 == TYPE_GROUPING) {
                    return parseTextAttribute(readInt2, "TIT1", parsableByteArray);
                }
            } else if (readInt2 == TYPE_GENRE) {
                return parseStandardGenreAttribute(parsableByteArray);
            } else {
                if (readInt2 == TYPE_DISK_NUMBER) {
                    return parseIndexAndCountAttribute(readInt2, "TPOS", parsableByteArray);
                }
                if (readInt2 == TYPE_TRACK_NUMBER) {
                    return parseIndexAndCountAttribute(readInt2, "TRCK", parsableByteArray);
                }
                if (readInt2 == TYPE_TEMPO) {
                    return parseUint8Attribute(readInt2, "TBPM", parsableByteArray, true, false);
                }
                if (readInt2 == TYPE_COMPILATION) {
                    return parseUint8Attribute(readInt2, "TCMP", parsableByteArray, true, true);
                }
                if (readInt2 == TYPE_COVER_ART) {
                    return parseCoverArt(parsableByteArray);
                }
                if (readInt2 == TYPE_ALBUM_ARTIST) {
                    return parseTextAttribute(readInt2, "TPE2", parsableByteArray);
                }
                if (readInt2 == TYPE_SORT_TRACK_NAME) {
                    return parseTextAttribute(readInt2, "TSOT", parsableByteArray);
                }
                if (readInt2 == TYPE_SORT_ALBUM) {
                    return parseTextAttribute(readInt2, "TSO2", parsableByteArray);
                }
                if (readInt2 == TYPE_SORT_ARTIST) {
                    return parseTextAttribute(readInt2, "TSOA", parsableByteArray);
                }
                if (readInt2 == TYPE_SORT_ALBUM_ARTIST) {
                    return parseTextAttribute(readInt2, "TSOP", parsableByteArray);
                }
                if (readInt2 == TYPE_SORT_COMPOSER) {
                    return parseTextAttribute(readInt2, "TSOC", parsableByteArray);
                }
                if (readInt2 == TYPE_RATING) {
                    return parseUint8Attribute(readInt2, "ITUNESADVISORY", parsableByteArray, false, false);
                }
                if (readInt2 == TYPE_GAPLESS_ALBUM) {
                    return parseUint8Attribute(readInt2, "ITUNESGAPLESS", parsableByteArray, false, true);
                }
                if (readInt2 == TYPE_TV_SORT_SHOW) {
                    return parseTextAttribute(readInt2, "TVSHOWSORT", parsableByteArray);
                }
                if (readInt2 == TYPE_TV_SHOW) {
                    return parseTextAttribute(readInt2, "TVSHOW", parsableByteArray);
                }
                if (readInt2 == TYPE_INTERNAL) {
                    return parseInternalAttribute(parsableByteArray, readInt);
                }
            }
            Log.d(TAG, "Skipped unknown metadata entry: " + Atom.getAtomTypeString(readInt2));
            return null;
        } finally {
            parsableByteArray.setPosition(readInt);
        }
    }

    @Nullable
    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385 && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            if (readUnsignedShort > 0) {
                String outline49 = GeneratedOutlineSupport1.outline49("", readUnsignedShort);
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    outline49 = GeneratedOutlineSupport1.outline74(outline49, "/", readUnsignedShort2);
                }
                return new TextInformationFrame(str, null, outline49);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse index/count attribute: ");
        outline107.append(Atom.getAtomTypeString(i));
        Log.w(TAG, outline107.toString());
        return null;
    }

    @Nullable
    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        int i2 = -1;
        int i3 = -1;
        String str = null;
        String str2 = null;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == 1835360622) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == 1851878757) {
                str2 = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == 1684108385) {
                    i2 = position;
                    i3 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (str == null || str2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(str, str2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    @Nullable
    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i, String str) {
        while (true) {
            int position = parsableByteArray.getPosition();
            if (position < i) {
                int readInt = parsableByteArray.readInt();
                if (parsableByteArray.readInt() == 1684108385) {
                    int readInt2 = parsableByteArray.readInt();
                    int readInt3 = parsableByteArray.readInt();
                    int i2 = readInt - 16;
                    byte[] bArr = new byte[i2];
                    parsableByteArray.readBytes(bArr, 0, i2);
                    return new MdtaMetadataEntry(str, bArr, readInt3, readInt2);
                }
                parsableByteArray.setPosition(position + readInt);
            } else {
                return null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0014  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.google.android.exoplayer2.metadata.id3.TextInformationFrame parseStandardGenreAttribute(com.google.android.exoplayer2.util.ParsableByteArray r3) {
        /*
            int r3 = parseUint8AttributeValue(r3)
            r0 = 0
            if (r3 <= 0) goto L11
            java.lang.String[] r1 = com.google.android.exoplayer2.extractor.mp4.MetadataUtil.STANDARD_GENRES
            int r2 = r1.length
            if (r3 > r2) goto L11
            int r3 = r3 + (-1)
            r3 = r1[r3]
            goto L12
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L1c
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = new com.google.android.exoplayer2.metadata.id3.TextInformationFrame
            java.lang.String r2 = "TCON"
            r1.<init>(r2, r0, r3)
            return r1
        L1c:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            com.google.android.exoplayer2.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.MetadataUtil.parseStandardGenreAttribute(com.google.android.exoplayer2.util.ParsableByteArray):com.google.android.exoplayer2.metadata.id3.TextInformationFrame");
    }

    @Nullable
    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(readInt - 16));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse text attribute: ");
        outline107.append(Atom.getAtomTypeString(i));
        Log.w(TAG, outline107.toString());
        return null;
    }

    @Nullable
    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            parseUint8AttributeValue = Math.min(1, parseUint8AttributeValue);
        }
        if (parseUint8AttributeValue >= 0) {
            if (z) {
                return new TextInformationFrame(str, null, Integer.toString(parseUint8AttributeValue));
            }
            return new CommentFrame("und", str, Integer.toString(parseUint8AttributeValue));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse uint8 attribute: ");
        outline107.append(Atom.getAtomTypeString(i));
        Log.w(TAG, outline107.toString());
        return null;
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w(TAG, "Failed to parse uint8 attribute value");
        return -1;
    }

    public static void setFormatGaplessInfo(int i, GaplessInfoHolder gaplessInfoHolder, Format.Builder builder) {
        if (i != 1 || !gaplessInfoHolder.hasGaplessInfo()) {
            return;
        }
        builder.setEncoderDelay(gaplessInfoHolder.encoderDelay).setEncoderPadding(gaplessInfoHolder.encoderPadding);
    }

    public static void setFormatMetadata(int i, @Nullable Metadata metadata, @Nullable Metadata metadata2, Format.Builder builder, Metadata... metadataArr) {
        Metadata metadata3;
        Metadata metadata4 = new Metadata(new Metadata.Entry[0]);
        if (i == 1) {
            if (metadata != null) {
                metadata3 = metadata;
            }
            metadata3 = metadata4;
        } else {
            if (i == 2 && metadata2 != null) {
                for (int i2 = 0; i2 < metadata2.length(); i2++) {
                    Metadata.Entry entry = metadata2.get(i2);
                    if (entry instanceof MdtaMetadataEntry) {
                        MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                        if (MdtaMetadataEntry.KEY_ANDROID_CAPTURE_FPS.equals(mdtaMetadataEntry.key)) {
                            metadata3 = new Metadata(mdtaMetadataEntry);
                            break;
                        }
                    }
                }
            }
            metadata3 = metadata4;
        }
        for (Metadata metadata5 : metadataArr) {
            metadata3 = metadata3.copyWithAppendedEntriesFrom(metadata5);
        }
        if (metadata3.length() > 0) {
            builder.setMetadata(metadata3);
        }
    }
}
