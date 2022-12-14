package kotlin;

import com.amazon.alexa.shl;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UInt.kt */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 j2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001jB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u0013\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0013\u0010#\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b$\u0010\u0005J\u0013\u0010%\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u000fJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b)\u0010\u000bJ\u001b\u0010'\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u001dJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0016J\u001b\u0010,\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b-\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u000fJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u001dJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0016J\u001b\u00103\u001a\u0002042\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u000fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b9\u0010\u000bJ\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u001dJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0016J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\u0087\fø\u0001\u0000¢\u0006\u0004\b@\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u000fJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001dJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u0016J\u0010\u0010F\u001a\u00020GH\u0087\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020KH\u0087\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020OH\u0087\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u000f\u0010\\\u001a\u00020]H\u0016¢\u0006\u0004\b^\u0010_J\u0013\u0010`\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\ba\u0010IJ\u0013\u0010b\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\bc\u0010\u0005J\u0013\u0010d\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\be\u0010WJ\u0013\u0010f\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\bg\u0010[J\u001b\u0010h\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bi\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006k"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "data$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-impl", TtmlNode.TAG_DIV, "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", shl.zZm, "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
@ExperimentalUnsignedTypes
/* loaded from: classes3.dex */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* compiled from: UInt.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    private /* synthetic */ UInt(int i) {
        this.data = i;
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns  reason: not valid java name */
    private static final int m10457andWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i & i2);
    }

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m10458boximpl(int i) {
        return new UInt(i);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m10459compareTo7apg3OU(int i, byte b) {
        return UnsignedKt.uintCompare(i, m10464constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m10460compareToVKZWuLQ(int i, long j) {
        return UnsignedKt.ulongCompare(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax), j);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private int m10461compareToWZ4Q5Ns(int i) {
        return m10462compareToWZ4Q5Ns(this.data, i);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m10463compareToxj2QHRw(int i, short s) {
        return UnsignedKt.uintCompare(i, m10464constructorimpl(s & UShort.MAX_VALUE));
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m10464constructorimpl(int i) {
        return i;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    @InlineOnly
    /* renamed from: dec-impl  reason: not valid java name */
    private static final int m10465decimpl(int i) {
        return m10464constructorimpl(i - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m10466div7apg3OU(int i, byte b) {
        return UnsignedKt.m10693uintDivideJ1ME1BU(i, m10464constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m10467divVKZWuLQ(int i, long j) {
        return UnsignedKt.m10695ulongDivideeb3DHEI(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax), j);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m10468divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m10693uintDivideJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m10469divxj2QHRw(int i, short s) {
        return UnsignedKt.m10693uintDivideJ1ME1BU(i, m10464constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m10470equalsimpl(int i, @Nullable Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).m10507unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m10471equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m10472hashCodeimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: inc-impl  reason: not valid java name */
    private static final int m10473incimpl(int i) {
        return m10464constructorimpl(i + 1);
    }

    @InlineOnly
    /* renamed from: inv-impl  reason: not valid java name */
    private static final int m10474invimpl(int i) {
        return m10464constructorimpl(~i);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m10475minus7apg3OU(int i, byte b) {
        return m10464constructorimpl(i - m10464constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m10476minusVKZWuLQ(int i, long j) {
        return ULong.m10534constructorimpl(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax) - j);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m10477minusWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i - i2);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m10478minusxj2QHRw(int i, short s) {
        return m10464constructorimpl(i - m10464constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns  reason: not valid java name */
    private static final int m10479orWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i | i2);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m10480plus7apg3OU(int i, byte b) {
        return m10464constructorimpl(m10464constructorimpl(b & 255) + i);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m10481plusVKZWuLQ(int i, long j) {
        return ULong.m10534constructorimpl(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax) + j);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m10482plusWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i + i2);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m10483plusxj2QHRw(int i, short s) {
        return m10464constructorimpl(m10464constructorimpl(s & UShort.MAX_VALUE) + i);
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m10484rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m10485rem7apg3OU(int i, byte b) {
        return UnsignedKt.m10694uintRemainderJ1ME1BU(i, m10464constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m10486remVKZWuLQ(int i, long j) {
        return UnsignedKt.m10696ulongRemaindereb3DHEI(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax), j);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m10487remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m10694uintRemainderJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m10488remxj2QHRw(int i, short s) {
        return UnsignedKt.m10694uintRemainderJ1ME1BU(i, m10464constructorimpl(s & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: shl-impl  reason: not valid java name */
    private static final int m10489shlimpl(int i, int i2) {
        return m10464constructorimpl(i << i2);
    }

    @InlineOnly
    /* renamed from: shr-impl  reason: not valid java name */
    private static final int m10490shrimpl(int i, int i2) {
        return m10464constructorimpl(i >>> i2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m10491times7apg3OU(int i, byte b) {
        return m10464constructorimpl(m10464constructorimpl(b & 255) * i);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m10492timesVKZWuLQ(int i, long j) {
        return ULong.m10534constructorimpl(ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax) * j);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m10493timesWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i * i2);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m10494timesxj2QHRw(int i, short s) {
        return m10464constructorimpl(m10464constructorimpl(s & UShort.MAX_VALUE) * i);
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m10495toByteimpl(int i) {
        return (byte) i;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m10496toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m10497toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m10498toIntimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m10499toLongimpl(int i) {
        return i & BodyPartID.bodyIdMax;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m10500toShortimpl(int i) {
        return (short) i;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m10501toStringimpl(int i) {
        return String.valueOf(i & BodyPartID.bodyIdMax);
    }

    @InlineOnly
    /* renamed from: toUByte-impl  reason: not valid java name */
    private static final byte m10502toUByteimpl(int i) {
        return UByte.m10396constructorimpl((byte) i);
    }

    @InlineOnly
    /* renamed from: toUInt-impl  reason: not valid java name */
    private static final int m10503toUIntimpl(int i) {
        return i;
    }

    @InlineOnly
    /* renamed from: toULong-impl  reason: not valid java name */
    private static final long m10504toULongimpl(int i) {
        return ULong.m10534constructorimpl(i & BodyPartID.bodyIdMax);
    }

    @InlineOnly
    /* renamed from: toUShort-impl  reason: not valid java name */
    private static final short m10505toUShortimpl(int i) {
        return UShort.m10632constructorimpl((short) i);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns  reason: not valid java name */
    private static final int m10506xorWZ4Q5Ns(int i, int i2) {
        return m10464constructorimpl(i ^ i2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return m10461compareToWZ4Q5Ns(uInt.m10507unboximpl());
    }

    public boolean equals(Object obj) {
        return m10470equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m10472hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m10501toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m10507unboximpl() {
        return this.data;
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static int m10462compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }
}
