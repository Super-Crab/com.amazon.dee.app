package okio.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.google.android.exoplayer2.C;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Platform;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Utf8;
import okio.Util;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Buffer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0000\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0080\b\u001a\r\u0010\u0014\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\u0013*\u00020\u0013H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0017\u0010\u001a\u001a\u00020\n*\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0005H\u0080\b\u001a\r\u0010 \u001a\u00020\b*\u00020\u0013H\u0080\b\u001a%\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010&\u001a\u00020\u0005*\u00020\u00132\u0006\u0010'\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a%\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010)\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020,H\u0080\b\u001a\r\u0010-\u001a\u00020\u001e*\u00020\u0013H\u0080\b\u001a\r\u0010.\u001a\u00020\u0001*\u00020\u0013H\u0080\b\u001a\u0015\u0010.\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010/\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010/\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00100\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a\u001d\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00102\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00103\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\r\u00104\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0013H\u0080\b\u001a\u0015\u00107\u001a\u000208*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00109\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\u000f\u0010:\u001a\u0004\u0018\u000108*\u00020\u0013H\u0080\b\u001a\u0015\u0010;\u001a\u000208*\u00020\u00132\u0006\u0010<\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010=\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0080\b\u001a\u0015\u0010@\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010A\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010A\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010B\u001a\u00020\f*\u00020\u00132\u0006\u0010C\u001a\u00020\bH\u0080\b\u001a\u0015\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u0001H\u0080\b\u001a%\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0012*\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a)\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010F\u001a\u00020%2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020G2\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010H\u001a\u00020\u0005*\u00020\u00132\u0006\u0010E\u001a\u00020GH\u0080\b\u001a\u0015\u0010I\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\"\u001a\u00020\bH\u0080\b\u001a\u0015\u0010J\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010L\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010M\u001a\u00020\u0013*\u00020\u00132\u0006\u0010N\u001a\u00020\bH\u0080\b\u001a\u0015\u0010O\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010P\u001a\u00020\u0013*\u00020\u00132\u0006\u0010Q\u001a\u00020\bH\u0080\b\u001a%\u0010R\u001a\u00020\u0013*\u00020\u00132\u0006\u0010S\u001a\u0002082\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\bH\u0080\b\u001a\u0015\u0010V\u001a\u00020\u0013*\u00020\u00132\u0006\u0010W\u001a\u00020\bH\u0080\b\u001a\u0014\u0010X\u001a\u000208*\u00020\u00132\u0006\u0010Y\u001a\u00020\u0005H\u0000\u001a<\u0010Z\u001a\u0002H[\"\u0004\b\u0000\u0010[*\u00020\u00132\u0006\u0010#\u001a\u00020\u00052\u001a\u0010\\\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H[0]H\u0080\b¢\u0006\u0002\u0010^\u001a\u001e\u0010_\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010`\u001a\u00020\nH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", "offset", DecodeProducer.EXTRA_BITMAP_BYTES, "commonEquals", "other", "", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", "b", "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", MetricsUtil.LegacyMetricTypes.LIMIT, "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", "source", "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", ContextChain.TAG_INFRA, "commonWriteLong", "commonWriteShort", "s", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", VoiceBridgePayloadUtil.PayloadCommand.SEEK, ExifInterface.GPS_DIRECTION_TRUE, "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BufferKt {
    @NotNull
    private static final byte[] HEX_DIGIT_BYTES = Platform.asUtf8ToByteArray("0123456789abcdef");
    public static final long OVERFLOW_DIGIT_START = -7;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 4096;

    public static final void commonClear(@NotNull Buffer commonClear) {
        Intrinsics.checkParameterIsNotNull(commonClear, "$this$commonClear");
        commonClear.skip(commonClear.size());
    }

    public static final long commonCompleteSegmentByteCount(@NotNull Buffer commonCompleteSegmentByteCount) {
        Intrinsics.checkParameterIsNotNull(commonCompleteSegmentByteCount, "$this$commonCompleteSegmentByteCount");
        long size = commonCompleteSegmentByteCount.size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = commonCompleteSegmentByteCount.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment segment2 = segment.prev;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        int i = segment2.limit;
        return (i >= 8192 || !segment2.owner) ? size : size - (i - segment2.pos);
    }

    @NotNull
    public static final Buffer commonCopy(@NotNull Buffer commonCopy) {
        Intrinsics.checkParameterIsNotNull(commonCopy, "$this$commonCopy");
        Buffer buffer = new Buffer();
        if (commonCopy.size() == 0) {
            return buffer;
        }
        Segment segment = commonCopy.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment sharedCopy = segment.sharedCopy();
        buffer.head = sharedCopy;
        sharedCopy.prev = buffer.head;
        sharedCopy.next = sharedCopy.prev;
        for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
            Segment segment3 = sharedCopy.prev;
            if (segment3 == null) {
                Intrinsics.throwNpe();
            }
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            segment3.push(segment2.sharedCopy());
        }
        buffer.setSize$okio(commonCopy.size());
        return buffer;
    }

    @NotNull
    public static final Buffer commonCopyTo(@NotNull Buffer commonCopyTo, @NotNull Buffer out, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(commonCopyTo, "$this$commonCopyTo");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Util.checkOffsetAndCount(commonCopyTo.size(), j, j2);
        if (j2 == 0) {
            return commonCopyTo;
        }
        out.setSize$okio(out.size() + j2);
        Segment segment = commonCopyTo.head;
        while (true) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.limit;
            int i2 = segment.pos;
            if (j < i - i2) {
                break;
            }
            j -= i - i2;
            segment = segment.next;
        }
        while (j2 > 0) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            Segment sharedCopy = segment.sharedCopy();
            sharedCopy.pos += (int) j;
            sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j2), sharedCopy.limit);
            Segment segment2 = out.head;
            if (segment2 == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy.prev;
                out.head = sharedCopy.next;
            } else {
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                Segment segment3 = segment2.prev;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                segment3.push(sharedCopy);
            }
            j2 -= sharedCopy.limit - sharedCopy.pos;
            segment = segment.next;
            j = 0;
        }
        return commonCopyTo;
    }

    public static final boolean commonEquals(@NotNull Buffer commonEquals, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(commonEquals, "$this$commonEquals");
        if (commonEquals == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        if (commonEquals.size() != buffer.size()) {
            return false;
        }
        if (commonEquals.size() == 0) {
            return true;
        }
        Segment segment = commonEquals.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        Segment segment2 = buffer.head;
        if (segment2 == null) {
            Intrinsics.throwNpe();
        }
        int i = segment.pos;
        int i2 = segment2.pos;
        Segment segment3 = segment2;
        int i3 = i;
        int i4 = i2;
        long j = 0;
        while (j < commonEquals.size()) {
            long min = Math.min(segment.limit - i3, segment3.limit - i4);
            long j2 = 0;
            while (j2 < min) {
                int i5 = i3 + 1;
                int i6 = i4 + 1;
                if (segment.data[i3] != segment3.data[i4]) {
                    return false;
                }
                j2++;
                i3 = i5;
                i4 = i6;
            }
            if (i3 == segment.limit) {
                Segment segment4 = segment.next;
                if (segment4 == null) {
                    Intrinsics.throwNpe();
                }
                segment = segment4;
                i3 = segment4.pos;
            }
            if (i4 == segment3.limit) {
                segment3 = segment3.next;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                i4 = segment3.pos;
            }
            j += min;
        }
        return true;
    }

    public static final byte commonGet(@NotNull Buffer commonGet, long j) {
        Intrinsics.checkParameterIsNotNull(commonGet, "$this$commonGet");
        Util.checkOffsetAndCount(commonGet.size(), j, 1L);
        Segment segment = commonGet.head;
        if (segment != null) {
            if (commonGet.size() - j < j) {
                long size = commonGet.size();
                while (size > j) {
                    segment = segment.prev;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    size -= segment.limit - segment.pos;
                }
                return segment.data[(int) ((segment.pos + j) - size)];
            }
            long j2 = 0;
            while (true) {
                int i = segment.limit;
                int i2 = segment.pos;
                long j3 = (i - i2) + j2;
                if (j3 > j) {
                    return segment.data[(int) ((i2 + j) - j2)];
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j2 = j3;
            }
        } else {
            Intrinsics.throwNpe();
            throw null;
        }
    }

    public static final int commonHashCode(@NotNull Buffer commonHashCode) {
        Intrinsics.checkParameterIsNotNull(commonHashCode, "$this$commonHashCode");
        Segment segment = commonHashCode.head;
        if (segment != null) {
            int i = 1;
            do {
                int i2 = segment.limit;
                for (int i3 = segment.pos; i3 < i2; i3++) {
                    i = (i * 31) + segment.data[i3];
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
            } while (segment != commonHashCode.head);
            return i;
        }
        return 0;
    }

    public static final long commonIndexOf(@NotNull Buffer commonIndexOf, byte b, long j, long j2) {
        Segment segment;
        int i;
        Intrinsics.checkParameterIsNotNull(commonIndexOf, "$this$commonIndexOf");
        long j3 = 0;
        if (0 <= j && j2 >= j) {
            if (j2 > commonIndexOf.size()) {
                j2 = commonIndexOf.size();
            }
            if (j == j2 || (segment = commonIndexOf.head) == null) {
                return -1L;
            }
            if (commonIndexOf.size() - j < j) {
                j3 = commonIndexOf.size();
                while (j3 > j) {
                    segment = segment.prev;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j3 -= segment.limit - segment.pos;
                }
                while (j3 < j2) {
                    byte[] bArr = segment.data;
                    int min = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
                    i = (int) ((segment.pos + j) - j3);
                    while (i < min) {
                        if (bArr[i] != b) {
                            i++;
                        }
                    }
                    j = (segment.limit - segment.pos) + j3;
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j3 = j;
                }
                return -1L;
            }
            while (true) {
                long j4 = (segment.limit - segment.pos) + j3;
                if (j4 > j) {
                    break;
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j3 = j4;
            }
            while (j3 < j2) {
                byte[] bArr2 = segment.data;
                int min2 = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
                i = (int) ((segment.pos + j) - j3);
                while (i < min2) {
                    if (bArr2[i] != b) {
                        i++;
                    }
                }
                j = (segment.limit - segment.pos) + j3;
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j3 = j;
            }
            return -1L;
            return (i - segment.pos) + j3;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size=");
        outline107.append(commonIndexOf.size());
        outline107.append(" fromIndex=");
        outline107.append(j);
        outline107.append(" toIndex=");
        outline107.append(j2);
        throw new IllegalArgumentException(outline107.toString().toString());
    }

    public static final long commonIndexOfElement(@NotNull Buffer commonIndexOfElement, @NotNull ByteString targetBytes, long j) {
        int i;
        int i2;
        Intrinsics.checkParameterIsNotNull(commonIndexOfElement, "$this$commonIndexOfElement");
        Intrinsics.checkParameterIsNotNull(targetBytes, "targetBytes");
        long j2 = 0;
        if (j >= 0) {
            Segment segment = commonIndexOfElement.head;
            if (segment == null) {
                return -1L;
            }
            if (commonIndexOfElement.size() - j < j) {
                j2 = commonIndexOfElement.size();
                while (j2 > j) {
                    segment = segment.prev;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j2 -= segment.limit - segment.pos;
                }
                if (targetBytes.size() == 2) {
                    byte b = targetBytes.getByte(0);
                    byte b2 = targetBytes.getByte(1);
                    while (j2 < commonIndexOfElement.size()) {
                        byte[] bArr = segment.data;
                        i = (int) ((segment.pos + j) - j2);
                        int i3 = segment.limit;
                        while (i < i3) {
                            byte b3 = bArr[i];
                            if (b3 != b && b3 != b2) {
                                i++;
                            }
                            i2 = segment.pos;
                        }
                        j = (segment.limit - segment.pos) + j2;
                        segment = segment.next;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 = j;
                    }
                } else {
                    byte[] internalArray$okio = targetBytes.internalArray$okio();
                    while (j2 < commonIndexOfElement.size()) {
                        byte[] bArr2 = segment.data;
                        i = (int) ((segment.pos + j) - j2);
                        int i4 = segment.limit;
                        while (i < i4) {
                            byte b4 = bArr2[i];
                            for (byte b5 : internalArray$okio) {
                                if (b4 == b5) {
                                    i2 = segment.pos;
                                }
                            }
                            i++;
                        }
                        j = (segment.limit - segment.pos) + j2;
                        segment = segment.next;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 = j;
                    }
                }
                return -1L;
            }
            while (true) {
                long j3 = (segment.limit - segment.pos) + j2;
                if (j3 > j) {
                    break;
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j2 = j3;
            }
            if (targetBytes.size() == 2) {
                byte b6 = targetBytes.getByte(0);
                byte b7 = targetBytes.getByte(1);
                while (j2 < commonIndexOfElement.size()) {
                    byte[] bArr3 = segment.data;
                    i = (int) ((segment.pos + j) - j2);
                    int i5 = segment.limit;
                    while (i < i5) {
                        byte b8 = bArr3[i];
                        if (b8 != b6 && b8 != b7) {
                            i++;
                        }
                        i2 = segment.pos;
                    }
                    j = (segment.limit - segment.pos) + j2;
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j2 = j;
                }
            } else {
                byte[] internalArray$okio2 = targetBytes.internalArray$okio();
                while (j2 < commonIndexOfElement.size()) {
                    byte[] bArr4 = segment.data;
                    i = (int) ((segment.pos + j) - j2);
                    int i6 = segment.limit;
                    while (i < i6) {
                        byte b9 = bArr4[i];
                        for (byte b10 : internalArray$okio2) {
                            if (b9 == b10) {
                                i2 = segment.pos;
                            }
                        }
                        i++;
                    }
                    j = (segment.limit - segment.pos) + j2;
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j2 = j;
                }
            }
            return -1L;
            return (i - i2) + j2;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("fromIndex < 0: ", j).toString());
    }

    public static final boolean commonRangeEquals(@NotNull Buffer commonRangeEquals, long j, @NotNull ByteString bytes, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (j < 0 || i < 0 || i2 < 0 || commonRangeEquals.size() - j < i2 || bytes.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (commonRangeEquals.getByte(i3 + j) != bytes.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public static final int commonRead(@NotNull Buffer commonRead, @NotNull byte[] sink) {
        Intrinsics.checkParameterIsNotNull(commonRead, "$this$commonRead");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        return commonRead.read(sink, 0, sink.length);
    }

    public static final long commonReadAll(@NotNull Buffer commonReadAll, @NotNull Sink sink) {
        Intrinsics.checkParameterIsNotNull(commonReadAll, "$this$commonReadAll");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        long size = commonReadAll.size();
        if (size > 0) {
            sink.write(commonReadAll, size);
        }
        return size;
    }

    public static final byte commonReadByte(@NotNull Buffer commonReadByte) {
        Intrinsics.checkParameterIsNotNull(commonReadByte, "$this$commonReadByte");
        if (commonReadByte.size() != 0) {
            Segment segment = commonReadByte.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            commonReadByte.setSize$okio(commonReadByte.size() - 1);
            if (i3 == i2) {
                commonReadByte.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            } else {
                segment.pos = i3;
            }
            return b;
        }
        throw new EOFException();
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer commonReadByteArray) {
        Intrinsics.checkParameterIsNotNull(commonReadByteArray, "$this$commonReadByteArray");
        return commonReadByteArray.readByteArray(commonReadByteArray.size());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer commonReadByteString) {
        Intrinsics.checkParameterIsNotNull(commonReadByteString, "$this$commonReadByteString");
        return commonReadByteString.readByteString(commonReadByteString.size());
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00c1 A[EDGE_INSN: B:115:0x00c1->B:106:0x00c1 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long commonReadDecimalLong(@org.jetbrains.annotations.NotNull okio.Buffer r17) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.BufferKt.commonReadDecimalLong(okio.Buffer):long");
    }

    public static final void commonReadFully(@NotNull Buffer commonReadFully, @NotNull byte[] sink) {
        Intrinsics.checkParameterIsNotNull(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        int i = 0;
        while (i < sink.length) {
            int read = commonReadFully.read(sink, i, sink.length - i);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ae A[EDGE_INSN: B:96:0x00ae->B:91:0x00ae ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long commonReadHexadecimalUnsignedLong(@org.jetbrains.annotations.NotNull okio.Buffer r14) {
        /*
            java.lang.String r0 = "$this$commonReadHexadecimalUnsignedLong"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            long r0 = r14.size()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lb8
            r0 = 0
            r1 = r0
            r4 = r2
        L12:
            okio.Segment r6 = r14.head
            if (r6 != 0) goto L19
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L19:
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L1f:
            if (r8 >= r9) goto L98
            r10 = r7[r8]
            r11 = 48
            byte r11 = (byte) r11
            if (r10 < r11) goto L30
            r12 = 57
            byte r12 = (byte) r12
            if (r10 > r12) goto L30
            int r11 = r10 - r11
            goto L49
        L30:
            r11 = 97
            byte r11 = (byte) r11
            if (r10 < r11) goto L3b
            r12 = 102(0x66, float:1.43E-43)
            byte r12 = (byte) r12
            if (r10 > r12) goto L3b
            goto L45
        L3b:
            r11 = 65
            byte r11 = (byte) r11
            if (r10 < r11) goto L7d
            r12 = 70
            byte r12 = (byte) r12
            if (r10 > r12) goto L7d
        L45:
            int r11 = r10 - r11
            int r11 = r11 + 10
        L49:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L59
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L1f
        L59:
            okio.Buffer r14 = new okio.Buffer
            r14.<init>()
            okio.Buffer r14 = r14.mo12598writeHexadecimalUnsignedLong(r4)
            okio.Buffer r14 = r14.mo12596writeByte(r10)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = "Number too large: "
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r1)
            java.lang.String r14 = r14.readUtf8()
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            r0.<init>(r14)
            throw r0
        L7d:
            if (r0 == 0) goto L81
            r1 = 1
            goto L98
        L81:
            java.lang.NumberFormatException r14 = new java.lang.NumberFormatException
            java.lang.String r0 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r1 = okio.Util.toHexString(r10)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r14.<init>(r0)
            throw r14
        L98:
            if (r8 != r9) goto La6
            okio.Segment r7 = r6.pop()
            r14.head = r7
            okio.SegmentPool r7 = okio.SegmentPool.INSTANCE
            r7.recycle(r6)
            goto La8
        La6:
            r6.pos = r8
        La8:
            if (r1 != 0) goto Lae
            okio.Segment r6 = r14.head
            if (r6 != 0) goto L12
        Lae:
            long r1 = r14.size()
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.setSize$okio(r1)
            return r4
        Lb8:
            java.io.EOFException r14 = new java.io.EOFException
            r14.<init>()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.BufferKt.commonReadHexadecimalUnsignedLong(okio.Buffer):long");
    }

    public static final int commonReadInt(@NotNull Buffer commonReadInt) {
        Intrinsics.checkParameterIsNotNull(commonReadInt, "$this$commonReadInt");
        if (commonReadInt.size() >= 4) {
            Segment segment = commonReadInt.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 4) {
                return (commonReadInt.readByte() & 255) | ((commonReadInt.readByte() & 255) << 24) | ((commonReadInt.readByte() & 255) << 16) | ((commonReadInt.readByte() & 255) << 8);
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            int i6 = i4 + 1;
            int i7 = i5 | ((bArr[i4] & 255) << 8);
            int i8 = i6 + 1;
            int i9 = i7 | (bArr[i6] & 255);
            commonReadInt.setSize$okio(commonReadInt.size() - 4);
            if (i8 == i2) {
                commonReadInt.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            } else {
                segment.pos = i8;
            }
            return i9;
        }
        throw new EOFException();
    }

    public static final long commonReadLong(@NotNull Buffer commonReadLong) {
        Intrinsics.checkParameterIsNotNull(commonReadLong, "$this$commonReadLong");
        if (commonReadLong.size() >= 8) {
            Segment segment = commonReadLong.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 8) {
                return ((commonReadLong.readInt() & BodyPartID.bodyIdMax) << 32) | (BodyPartID.bodyIdMax & commonReadLong.readInt());
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            long j = ((bArr[i] & 255) << 56) | ((bArr[i3] & 255) << 48) | ((bArr[i4] & 255) << 40) | ((bArr[i5] & 255) << 32) | ((bArr[i6] & 255) << 24) | ((bArr[i7] & 255) << 16);
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            long j2 = j | ((bArr[i8] & 255) << 8) | (bArr[i9] & 255);
            commonReadLong.setSize$okio(commonReadLong.size() - 8);
            if (i10 == i2) {
                commonReadLong.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            } else {
                segment.pos = i10;
            }
            return j2;
        }
        throw new EOFException();
    }

    public static final short commonReadShort(@NotNull Buffer commonReadShort) {
        Intrinsics.checkParameterIsNotNull(commonReadShort, "$this$commonReadShort");
        if (commonReadShort.size() >= 2) {
            Segment segment = commonReadShort.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) ((commonReadShort.readByte() & 255) | ((commonReadShort.readByte() & 255) << 8));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            commonReadShort.setSize$okio(commonReadShort.size() - 2);
            if (i4 == i2) {
                commonReadShort.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) i5;
        }
        throw new EOFException();
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull Buffer commonReadUtf8, long j) {
        Intrinsics.checkParameterIsNotNull(commonReadUtf8, "$this$commonReadUtf8");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0 && j <= ((long) Integer.MAX_VALUE)) {
            if (commonReadUtf8.size() < j) {
                throw new EOFException();
            }
            if (i == 0) {
                return "";
            }
            Segment segment = commonReadUtf8.head;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i2 = segment.pos;
            if (i2 + j > segment.limit) {
                return _Utf8Kt.commonToUtf8String$default(commonReadUtf8.readByteArray(j), 0, 0, 3, null);
            }
            int i3 = (int) j;
            String commonToUtf8String = _Utf8Kt.commonToUtf8String(segment.data, i2, i2 + i3);
            segment.pos += i3;
            commonReadUtf8.setSize$okio(commonReadUtf8.size() - j);
            if (segment.pos == segment.limit) {
                commonReadUtf8.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            }
            return commonToUtf8String;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount: ", j).toString());
    }

    public static final int commonReadUtf8CodePoint(@NotNull Buffer commonReadUtf8CodePoint) {
        int i;
        int i2;
        int i3;
        Intrinsics.checkParameterIsNotNull(commonReadUtf8CodePoint, "$this$commonReadUtf8CodePoint");
        if (commonReadUtf8CodePoint.size() != 0) {
            byte b = commonReadUtf8CodePoint.getByte(0L);
            if ((b & 128) == 0) {
                i = b & Byte.MAX_VALUE;
                i3 = 0;
                i2 = 1;
            } else if ((b & 224) == 192) {
                i = b & 31;
                i2 = 2;
                i3 = 128;
            } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) == 224) {
                i = b & 15;
                i2 = 3;
                i3 = 2048;
            } else if ((b & 248) != 240) {
                commonReadUtf8CodePoint.skip(1L);
                return Utf8.REPLACEMENT_CODE_POINT;
            } else {
                i = b & 7;
                i2 = 4;
                i3 = 65536;
            }
            long j = i2;
            if (commonReadUtf8CodePoint.size() >= j) {
                for (int i4 = 1; i4 < i2; i4++) {
                    long j2 = i4;
                    byte b2 = commonReadUtf8CodePoint.getByte(j2);
                    if ((b2 & 192) != 128) {
                        commonReadUtf8CodePoint.skip(j2);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                    i = (i << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
                }
                commonReadUtf8CodePoint.skip(j);
                return (i <= 1114111 && (55296 > i || 57343 < i) && i >= i3) ? i : Utf8.REPLACEMENT_CODE_POINT;
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("size < ", i2, RealTimeTextConstants.COLON_SPACE);
            outline109.append(commonReadUtf8CodePoint.size());
            outline109.append(" (to read code point prefixed 0x");
            outline109.append(Util.toHexString(b));
            outline109.append(')');
            throw new EOFException(outline109.toString());
        }
        throw new EOFException();
    }

    @Nullable
    public static final String commonReadUtf8Line(@NotNull Buffer commonReadUtf8Line) {
        Intrinsics.checkParameterIsNotNull(commonReadUtf8Line, "$this$commonReadUtf8Line");
        long indexOf = commonReadUtf8Line.indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(commonReadUtf8Line, indexOf);
        }
        if (commonReadUtf8Line.size() == 0) {
            return null;
        }
        return commonReadUtf8Line.readUtf8(commonReadUtf8Line.size());
    }

    @NotNull
    public static final String commonReadUtf8LineStrict(@NotNull Buffer commonReadUtf8LineStrict, long j) {
        Intrinsics.checkParameterIsNotNull(commonReadUtf8LineStrict, "$this$commonReadUtf8LineStrict");
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            byte b = (byte) 10;
            long indexOf = commonReadUtf8LineStrict.indexOf(b, 0L, j2);
            if (indexOf != -1) {
                return readUtf8Line(commonReadUtf8LineStrict, indexOf);
            }
            if (j2 < commonReadUtf8LineStrict.size() && commonReadUtf8LineStrict.getByte(j2 - 1) == ((byte) 13) && commonReadUtf8LineStrict.getByte(j2) == b) {
                return readUtf8Line(commonReadUtf8LineStrict, j2);
            }
            Buffer buffer = new Buffer();
            commonReadUtf8LineStrict.copyTo(buffer, 0L, Math.min(32, commonReadUtf8LineStrict.size()));
            throw new EOFException("\\n not found: limit=" + Math.min(commonReadUtf8LineStrict.size(), j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("limit < 0: ", j).toString());
    }

    public static final int commonSelect(@NotNull Buffer commonSelect, @NotNull Options options) {
        Intrinsics.checkParameterIsNotNull(commonSelect, "$this$commonSelect");
        Intrinsics.checkParameterIsNotNull(options, "options");
        int selectPrefix$default = selectPrefix$default(commonSelect, options, false, 2, null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        commonSelect.skip(options.getByteStrings$okio()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public static final void commonSkip(@NotNull Buffer commonSkip, long j) {
        Intrinsics.checkParameterIsNotNull(commonSkip, "$this$commonSkip");
        while (j > 0) {
            Segment segment = commonSkip.head;
            if (segment != null) {
                int min = (int) Math.min(j, segment.limit - segment.pos);
                long j2 = min;
                commonSkip.setSize$okio(commonSkip.size() - j2);
                j -= j2;
                segment.pos += min;
                if (segment.pos == segment.limit) {
                    commonSkip.head = segment.pop();
                    SegmentPool.INSTANCE.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer commonSnapshot) {
        Intrinsics.checkParameterIsNotNull(commonSnapshot, "$this$commonSnapshot");
        if (commonSnapshot.size() <= ((long) Integer.MAX_VALUE)) {
            return commonSnapshot.snapshot((int) commonSnapshot.size());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size > Int.MAX_VALUE: ");
        outline107.append(commonSnapshot.size());
        throw new IllegalStateException(outline107.toString().toString());
    }

    @NotNull
    public static final Segment commonWritableSegment(@NotNull Buffer commonWritableSegment, int i) {
        Intrinsics.checkParameterIsNotNull(commonWritableSegment, "$this$commonWritableSegment");
        boolean z = true;
        if (i < 1 || i > 8192) {
            z = false;
        }
        if (z) {
            Segment segment = commonWritableSegment.head;
            if (segment == null) {
                Segment take = SegmentPool.INSTANCE.take();
                commonWritableSegment.head = take;
                take.prev = take;
                take.next = take;
                return take;
            }
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            Segment segment2 = segment.prev;
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            return (segment2.limit + i > 8192 || !segment2.owner) ? segment2.push(SegmentPool.INSTANCE.take()) : segment2;
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull ByteString byteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        byteString.write$okio(commonWrite, i, i2);
        return commonWrite;
    }

    public static /* synthetic */ Buffer commonWrite$default(Buffer commonWrite, ByteString byteString, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteString.size();
        }
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        byteString.write$okio(commonWrite, i, i2);
        return commonWrite;
    }

    public static final long commonWriteAll(@NotNull Buffer commonWriteAll, @NotNull Source source) {
        Intrinsics.checkParameterIsNotNull(commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkParameterIsNotNull(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(commonWriteAll, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    @NotNull
    public static final Buffer commonWriteByte(@NotNull Buffer commonWriteByte, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteByte, "$this$commonWriteByte");
        Segment writableSegment$okio = commonWriteByte.writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        writableSegment$okio.limit = i2 + 1;
        bArr[i2] = (byte) i;
        commonWriteByte.setSize$okio(commonWriteByte.size() + 1);
        return commonWriteByte;
    }

    @NotNull
    public static final Buffer commonWriteDecimalLong(@NotNull Buffer commonWriteDecimalLong, long j) {
        int i;
        Intrinsics.checkParameterIsNotNull(commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i2 == 0) {
            return commonWriteDecimalLong.mo12596writeByte(48);
        }
        boolean z = false;
        int i3 = 1;
        if (i2 < 0) {
            j = -j;
            if (j < 0) {
                return commonWriteDecimalLong.mo12607writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (j < 100000000) {
            if (j >= 10000) {
                i = j < 1000000 ? j < TarazedPrimerActivity.PRIMER_FROZEN_TIMEOUT_MS ? 5 : 6 : j < 10000000 ? 7 : 8;
            } else if (j >= 100) {
                i = j < 1000 ? 3 : 4;
            } else if (j >= 10) {
                i3 = 2;
            }
            i3 = i;
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i3 = j < C.NANOS_PER_SECOND ? 9 : 10;
            } else {
                i = j < 100000000000L ? 11 : 12;
                i3 = i;
            }
        } else if (j >= 1000000000000000L) {
            i3 = j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j < 10000000000000L) {
            i3 = 13;
        } else {
            i = j < 100000000000000L ? 14 : 15;
            i3 = i;
        }
        if (z) {
            i3++;
        }
        Segment writableSegment$okio = commonWriteDecimalLong.writableSegment$okio(i3);
        byte[] bArr = writableSegment$okio.data;
        int i4 = writableSegment$okio.limit + i3;
        while (j != 0) {
            long j2 = 10;
            i4--;
            bArr[i4] = getHEX_DIGIT_BYTES()[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[i4 - 1] = (byte) 45;
        }
        writableSegment$okio.limit += i3;
        commonWriteDecimalLong.setSize$okio(commonWriteDecimalLong.size() + i3);
        return commonWriteDecimalLong;
    }

    @NotNull
    public static final Buffer commonWriteHexadecimalUnsignedLong(@NotNull Buffer commonWriteHexadecimalUnsignedLong, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        if (j == 0) {
            return commonWriteHexadecimalUnsignedLong.mo12596writeByte(48);
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int i = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + 3) / 4);
        Segment writableSegment$okio = commonWriteHexadecimalUnsignedLong.writableSegment$okio(i);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        for (int i3 = (i2 + i) - 1; i3 >= i2; i3--) {
            bArr[i3] = getHEX_DIGIT_BYTES()[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment$okio.limit += i;
        commonWriteHexadecimalUnsignedLong.setSize$okio(commonWriteHexadecimalUnsignedLong.size() + i);
        return commonWriteHexadecimalUnsignedLong;
    }

    @NotNull
    public static final Buffer commonWriteInt(@NotNull Buffer commonWriteInt, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteInt, "$this$commonWriteInt");
        Segment writableSegment$okio = commonWriteInt.writableSegment$okio(4);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        writableSegment$okio.limit = i5 + 1;
        commonWriteInt.setSize$okio(commonWriteInt.size() + 4);
        return commonWriteInt;
    }

    @NotNull
    public static final Buffer commonWriteLong(@NotNull Buffer commonWriteLong, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteLong, "$this$commonWriteLong");
        Segment writableSegment$okio = commonWriteLong.writableSegment$okio(8);
        byte[] bArr = writableSegment$okio.data;
        int i = writableSegment$okio.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j >>> 8) & 255);
        bArr[i8] = (byte) (j & 255);
        writableSegment$okio.limit = i8 + 1;
        commonWriteLong.setSize$okio(commonWriteLong.size() + 8);
        return commonWriteLong;
    }

    @NotNull
    public static final Buffer commonWriteShort(@NotNull Buffer commonWriteShort, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteShort, "$this$commonWriteShort");
        Segment writableSegment$okio = commonWriteShort.writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        writableSegment$okio.limit = i3 + 1;
        commonWriteShort.setSize$okio(commonWriteShort.size() + 2);
        return commonWriteShort;
    }

    @NotNull
    public static final Buffer commonWriteUtf8(@NotNull Buffer commonWriteUtf8, @NotNull String string, int i, int i2) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (i >= 0) {
            if (i2 >= i) {
                if (!(i2 <= string.length())) {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("endIndex > string.length: ", i2, " > ");
                    outline109.append(string.length());
                    throw new IllegalArgumentException(outline109.toString().toString());
                }
                while (i < i2) {
                    char charAt2 = string.charAt(i);
                    if (charAt2 < 128) {
                        Segment writableSegment$okio = commonWriteUtf8.writableSegment$okio(1);
                        byte[] bArr = writableSegment$okio.data;
                        int i3 = writableSegment$okio.limit - i;
                        int min = Math.min(i2, 8192 - i3);
                        int i4 = i + 1;
                        bArr[i + i3] = (byte) charAt2;
                        while (true) {
                            i = i4;
                            if (i >= min || (charAt = string.charAt(i)) >= 128) {
                                break;
                            }
                            i4 = i + 1;
                            bArr[i + i3] = (byte) charAt;
                        }
                        int i5 = writableSegment$okio.limit;
                        int i6 = (i3 + i) - i5;
                        writableSegment$okio.limit = i5 + i6;
                        commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + i6);
                    } else {
                        if (charAt2 < 2048) {
                            Segment writableSegment$okio2 = commonWriteUtf8.writableSegment$okio(2);
                            byte[] bArr2 = writableSegment$okio2.data;
                            int i7 = writableSegment$okio2.limit;
                            bArr2[i7] = (byte) ((charAt2 >> 6) | 192);
                            bArr2[i7 + 1] = (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128);
                            writableSegment$okio2.limit = i7 + 2;
                            commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 2);
                        } else if (charAt2 >= 55296 && charAt2 <= 57343) {
                            int i8 = i + 1;
                            char charAt3 = i8 < i2 ? string.charAt(i8) : (char) 0;
                            if (charAt2 <= 56319 && 56320 <= charAt3 && 57343 >= charAt3) {
                                int i9 = (((charAt2 & 1023) << 10) | (charAt3 & 1023)) + 65536;
                                Segment writableSegment$okio3 = commonWriteUtf8.writableSegment$okio(4);
                                byte[] bArr3 = writableSegment$okio3.data;
                                int i10 = writableSegment$okio3.limit;
                                bArr3[i10] = (byte) ((i9 >> 18) | 240);
                                bArr3[i10 + 1] = (byte) (((i9 >> 12) & 63) | 128);
                                bArr3[i10 + 2] = (byte) (((i9 >> 6) & 63) | 128);
                                bArr3[i10 + 3] = (byte) ((i9 & 63) | 128);
                                writableSegment$okio3.limit = i10 + 4;
                                commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 4);
                                i += 2;
                            } else {
                                commonWriteUtf8.mo12596writeByte(63);
                                i = i8;
                            }
                        } else {
                            Segment writableSegment$okio4 = commonWriteUtf8.writableSegment$okio(3);
                            byte[] bArr4 = writableSegment$okio4.data;
                            int i11 = writableSegment$okio4.limit;
                            bArr4[i11] = (byte) ((charAt2 >> '\f') | 224);
                            bArr4[i11 + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                            bArr4[i11 + 2] = (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128);
                            writableSegment$okio4.limit = i11 + 3;
                            commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 3);
                        }
                        i++;
                    }
                }
                return commonWriteUtf8;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("endIndex < beginIndex: ", i2, " < ", i).toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("beginIndex < 0: ", i).toString());
    }

    @NotNull
    public static final Buffer commonWriteUtf8CodePoint(@NotNull Buffer commonWriteUtf8CodePoint, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (i < 128) {
            commonWriteUtf8CodePoint.mo12596writeByte(i);
        } else if (i < 2048) {
            Segment writableSegment$okio = commonWriteUtf8CodePoint.writableSegment$okio(2);
            byte[] bArr = writableSegment$okio.data;
            int i2 = writableSegment$okio.limit;
            bArr[i2] = (byte) ((i >> 6) | 192);
            bArr[i2 + 1] = (byte) ((i & 63) | 128);
            writableSegment$okio.limit = i2 + 2;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 2);
        } else if (55296 <= i && 57343 >= i) {
            commonWriteUtf8CodePoint.mo12596writeByte(63);
        } else if (i < 65536) {
            Segment writableSegment$okio2 = commonWriteUtf8CodePoint.writableSegment$okio(3);
            byte[] bArr2 = writableSegment$okio2.data;
            int i3 = writableSegment$okio2.limit;
            bArr2[i3] = (byte) ((i >> 12) | 224);
            bArr2[i3 + 1] = (byte) (((i >> 6) & 63) | 128);
            bArr2[i3 + 2] = (byte) ((i & 63) | 128);
            writableSegment$okio2.limit = i3 + 3;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 3);
        } else if (i <= 1114111) {
            Segment writableSegment$okio3 = commonWriteUtf8CodePoint.writableSegment$okio(4);
            byte[] bArr3 = writableSegment$okio3.data;
            int i4 = writableSegment$okio3.limit;
            bArr3[i4] = (byte) ((i >> 18) | 240);
            bArr3[i4 + 1] = (byte) (((i >> 12) & 63) | 128);
            bArr3[i4 + 2] = (byte) (((i >> 6) & 63) | 128);
            bArr3[i4 + 3] = (byte) ((i & 63) | 128);
            writableSegment$okio3.limit = i4 + 4;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 4);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected code point: 0x");
            outline107.append(Util.toHexString(i));
            throw new IllegalArgumentException(outline107.toString());
        }
        return commonWriteUtf8CodePoint;
    }

    @NotNull
    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(@NotNull Segment segment, int i, @NotNull byte[] bytes, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(segment, "segment");
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        int i4 = segment.limit;
        byte[] bArr = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i = segment.pos;
                i4 = segment.limit;
            }
            if (bArr[i] != bytes[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @NotNull
    public static final String readUtf8Line(@NotNull Buffer readUtf8Line, long j) {
        Intrinsics.checkParameterIsNotNull(readUtf8Line, "$this$readUtf8Line");
        if (j > 0) {
            long j2 = j - 1;
            if (readUtf8Line.getByte(j2) == ((byte) 13)) {
                String readUtf8 = readUtf8Line.readUtf8(j2);
                readUtf8Line.skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8Line.readUtf8(j);
        readUtf8Line.skip(1L);
        return readUtf82;
    }

    public static final <T> T seek(@NotNull Buffer seek, long j, @NotNull Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Intrinsics.checkParameterIsNotNull(seek, "$this$seek");
        Intrinsics.checkParameterIsNotNull(lambda, "lambda");
        Segment segment = seek.head;
        if (segment != null) {
            if (seek.size() - j < j) {
                long size = seek.size();
                while (size > j) {
                    segment = segment.prev;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    size -= segment.limit - segment.pos;
                }
                return lambda.mo12248invoke(segment, Long.valueOf(size));
            }
            long j2 = 0;
            while (true) {
                long j3 = (segment.limit - segment.pos) + j2;
                if (j3 > j) {
                    return lambda.mo12248invoke(segment, Long.valueOf(j2));
                }
                segment = segment.next;
                if (segment == null) {
                    Intrinsics.throwNpe();
                }
                j2 = j3;
            }
        } else {
            return lambda.mo12248invoke(null, -1L);
        }
    }

    public static final int selectPrefix(@NotNull Buffer selectPrefix, @NotNull Options options, boolean z) {
        int i;
        int i2;
        Intrinsics.checkParameterIsNotNull(selectPrefix, "$this$selectPrefix");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Segment segment = selectPrefix.head;
        if (segment == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment.data;
        int i3 = segment.pos;
        int i4 = segment.limit;
        int[] trie$okio = options.getTrie$okio();
        int i5 = -1;
        int i6 = i3;
        int i7 = i4;
        Segment segment2 = segment;
        byte[] bArr2 = bArr;
        int i8 = 0;
        loop0: while (true) {
            int i9 = i8 + 1;
            int i10 = trie$okio[i8];
            int i11 = i9 + 1;
            int i12 = trie$okio[i9];
            if (i12 != -1) {
                i5 = i12;
            }
            if (segment2 == null) {
                break;
            }
            if (i10 >= 0) {
                i = i6 + 1;
                int i13 = bArr2[i6] & 255;
                int i14 = i11 + i10;
                while (i11 != i14) {
                    if (i13 == trie$okio[i11]) {
                        i2 = trie$okio[i11 + i10];
                        if (i == i7) {
                            Segment segment3 = segment2.next;
                            if (segment3 == null) {
                                Intrinsics.throwNpe();
                            }
                            int i15 = segment3.pos;
                            bArr2 = segment3.data;
                            i7 = segment3.limit;
                            i = i15;
                            segment2 = segment3 == segment ? null : segment3;
                        }
                    } else {
                        i11++;
                    }
                }
                return i5;
            }
            int i16 = (i10 * (-1)) + i11;
            while (true) {
                int i17 = i6 + 1;
                int i18 = i11 + 1;
                if ((bArr2[i6] & 255) != trie$okio[i11]) {
                    return i5;
                }
                boolean z2 = i18 == i16;
                if (i17 == i7) {
                    if (segment2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Segment segment4 = segment2.next;
                    if (segment4 == null) {
                        Intrinsics.throwNpe();
                    }
                    int i19 = segment4.pos;
                    bArr2 = segment4.data;
                    i7 = segment4.limit;
                    if (segment4 != segment) {
                        segment2 = segment4;
                        i17 = i19;
                    } else if (!z2) {
                        break loop0;
                    } else {
                        i17 = i19;
                        segment2 = null;
                    }
                }
                if (z2) {
                    i2 = trie$okio[i18];
                    i = i17;
                    break;
                }
                i6 = i17;
                i11 = i18;
            }
            if (i2 >= 0) {
                return i2;
            }
            i8 = -i2;
            i6 = i;
        }
        if (!z) {
            return i5;
        }
        return -2;
    }

    public static /* synthetic */ int selectPrefix$default(Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    public static final int commonRead(@NotNull Buffer commonRead, @NotNull byte[] sink, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonRead, "$this$commonRead");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        Util.checkOffsetAndCount(sink.length, i, i2);
        Segment segment = commonRead.head;
        if (segment != null) {
            int min = Math.min(i2, segment.limit - segment.pos);
            byte[] bArr = segment.data;
            int i3 = segment.pos;
            ArraysKt___ArraysJvmKt.copyInto(bArr, sink, i, i3, i3 + min);
            segment.pos += min;
            commonRead.setSize$okio(commonRead.size() - min);
            if (segment.pos == segment.limit) {
                commonRead.head = segment.pop();
                SegmentPool.INSTANCE.recycle(segment);
            }
            return min;
        }
        return -1;
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer commonReadByteArray, long j) {
        Intrinsics.checkParameterIsNotNull(commonReadByteArray, "$this$commonReadByteArray");
        if (j >= 0 && j <= ((long) Integer.MAX_VALUE)) {
            if (commonReadByteArray.size() >= j) {
                byte[] bArr = new byte[(int) j];
                commonReadByteArray.readFully(bArr);
                return bArr;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount: ", j).toString());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer commonReadByteString, long j) {
        Intrinsics.checkParameterIsNotNull(commonReadByteString, "$this$commonReadByteString");
        if (j >= 0 && j <= ((long) Integer.MAX_VALUE)) {
            if (commonReadByteString.size() < j) {
                throw new EOFException();
            }
            if (j >= 4096) {
                ByteString snapshot = commonReadByteString.snapshot((int) j);
                commonReadByteString.skip(j);
                return snapshot;
            }
            return new ByteString(commonReadByteString.readByteArray(j));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount: ", j).toString());
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull byte[] source) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        return commonWrite.mo12595write(source, 0, source.length);
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull byte[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        long j = i2;
        Util.checkOffsetAndCount(source.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            Segment writableSegment$okio = commonWrite.writableSegment$okio(1);
            int min = Math.min(i3 - i, 8192 - writableSegment$okio.limit);
            int i4 = i + min;
            ArraysKt___ArraysJvmKt.copyInto(source, writableSegment$okio.data, writableSegment$okio.limit, i, i4);
            writableSegment$okio.limit += min;
            i = i4;
        }
        commonWrite.setSize$okio(commonWrite.size() + j);
        return commonWrite;
    }

    public static final void commonReadFully(@NotNull Buffer commonReadFully, @NotNull Buffer sink, long j) {
        Intrinsics.checkParameterIsNotNull(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (commonReadFully.size() >= j) {
            sink.write(commonReadFully, j);
        } else {
            sink.write(commonReadFully, commonReadFully.size());
            throw new EOFException();
        }
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer commonSnapshot, int i) {
        Intrinsics.checkParameterIsNotNull(commonSnapshot, "$this$commonSnapshot");
        if (i == 0) {
            return ByteString.EMPTY;
        }
        Util.checkOffsetAndCount(commonSnapshot.size(), 0L, i);
        int i2 = 0;
        Segment segment = commonSnapshot.head;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            int i5 = segment.limit;
            int i6 = segment.pos;
            if (i5 != i6) {
                i3 += i5 - i6;
                i4++;
                segment = segment.next;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i4];
        int[] iArr = new int[i4 * 2];
        Segment segment2 = commonSnapshot.head;
        int i7 = 0;
        while (i2 < i) {
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            bArr[i7] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            iArr[i7] = Math.min(i2, i);
            iArr[bArr.length + i7] = segment2.pos;
            segment2.shared = true;
            i7++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull Source source, long j) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        while (j > 0) {
            long read = source.read(commonWrite, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return commonWrite;
    }

    public static final long commonRead(@NotNull Buffer commonRead, @NotNull Buffer sink, long j) {
        Intrinsics.checkParameterIsNotNull(commonRead, "$this$commonRead");
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        if (j >= 0) {
            if (commonRead.size() == 0) {
                return -1L;
            }
            if (j > commonRead.size()) {
                j = commonRead.size();
            }
            sink.write(commonRead, j);
            return j;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("byteCount < 0: ", j).toString());
    }

    public static final void commonWrite(@NotNull Buffer commonWrite, @NotNull Buffer source, long j) {
        Segment segment;
        Segment segment2;
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (source != commonWrite) {
            Util.checkOffsetAndCount(source.size(), 0L, j);
            while (j > 0) {
                Segment segment3 = source.head;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                int i = segment3.limit;
                if (source.head == null) {
                    Intrinsics.throwNpe();
                }
                if (j < i - segment.pos) {
                    Segment segment4 = commonWrite.head;
                    if (segment4 != null) {
                        if (segment4 == null) {
                            Intrinsics.throwNpe();
                        }
                        segment2 = segment4.prev;
                    } else {
                        segment2 = null;
                    }
                    if (segment2 != null && segment2.owner) {
                        if ((segment2.limit + j) - (segment2.shared ? 0 : segment2.pos) <= 8192) {
                            Segment segment5 = source.head;
                            if (segment5 == null) {
                                Intrinsics.throwNpe();
                            }
                            segment5.writeTo(segment2, (int) j);
                            source.setSize$okio(source.size() - j);
                            commonWrite.setSize$okio(commonWrite.size() + j);
                            return;
                        }
                    }
                    Segment segment6 = source.head;
                    if (segment6 == null) {
                        Intrinsics.throwNpe();
                    }
                    source.head = segment6.split((int) j);
                }
                Segment segment7 = source.head;
                if (segment7 == null) {
                    Intrinsics.throwNpe();
                }
                long j2 = segment7.limit - segment7.pos;
                source.head = segment7.pop();
                Segment segment8 = commonWrite.head;
                if (segment8 == null) {
                    commonWrite.head = segment7;
                    segment7.prev = segment7;
                    segment7.next = segment7.prev;
                } else {
                    if (segment8 == null) {
                        Intrinsics.throwNpe();
                    }
                    Segment segment9 = segment8.prev;
                    if (segment9 == null) {
                        Intrinsics.throwNpe();
                    }
                    segment9.push(segment7).compact();
                }
                source.setSize$okio(source.size() - j2);
                commonWrite.setSize$okio(commonWrite.size() + j2);
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this".toString());
    }

    public static final long commonIndexOf(@NotNull Buffer commonIndexOf, @NotNull ByteString bytes, long j) {
        int i;
        Intrinsics.checkParameterIsNotNull(commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        if (bytes.size() > 0) {
            long j2 = 0;
            if (j >= 0) {
                Segment segment = commonIndexOf.head;
                if (segment == null) {
                    return -1L;
                }
                if (commonIndexOf.size() - j < j) {
                    j2 = commonIndexOf.size();
                    while (j2 > j) {
                        segment = segment.prev;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 -= segment.limit - segment.pos;
                    }
                    byte[] internalArray$okio = bytes.internalArray$okio();
                    byte b = internalArray$okio[0];
                    int size = bytes.size();
                    long size2 = (commonIndexOf.size() - size) + 1;
                    while (j2 < size2) {
                        byte[] bArr = segment.data;
                        int min = (int) Math.min(segment.limit, (segment.pos + size2) - j2);
                        i = (int) ((segment.pos + j) - j2);
                        while (i < min) {
                            if (bArr[i] != b || !rangeEquals(segment, i + 1, internalArray$okio, 1, size)) {
                                i++;
                            }
                        }
                        j = (segment.limit - segment.pos) + j2;
                        segment = segment.next;
                        if (segment == null) {
                            Intrinsics.throwNpe();
                        }
                        j2 = j;
                    }
                    return -1L;
                }
                while (true) {
                    long j3 = (segment.limit - segment.pos) + j2;
                    if (j3 > j) {
                        break;
                    }
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j2 = j3;
                }
                byte[] internalArray$okio2 = bytes.internalArray$okio();
                byte b2 = internalArray$okio2[0];
                int size3 = bytes.size();
                long size4 = (commonIndexOf.size() - size3) + 1;
                while (j2 < size4) {
                    byte[] bArr2 = segment.data;
                    int min2 = (int) Math.min(segment.limit, (segment.pos + size4) - j2);
                    i = (int) ((segment.pos + j) - j2);
                    while (i < min2) {
                        if (bArr2[i] != b2 || !rangeEquals(segment, i + 1, internalArray$okio2, 1, size3)) {
                            i++;
                        }
                    }
                    j = (segment.limit - segment.pos) + j2;
                    segment = segment.next;
                    if (segment == null) {
                        Intrinsics.throwNpe();
                    }
                    j2 = j;
                }
                return -1L;
                return (i - segment.pos) + j2;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("fromIndex < 0: ", j).toString());
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }
}
