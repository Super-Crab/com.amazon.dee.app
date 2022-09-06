package kotlin.time;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigImpl;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Duration.kt */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u008d\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\u0086\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\u0086\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\u0086\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\u0086\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", TtmlNode.TAG_DIV, ModelTransformer.KEY_BATTERY_SCALE, "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", ExifInterface.GPS_DIRECTION_TRUE, "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", BizMetricsConstants.DURATION_METADATA_NAME, "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
@ExperimentalTime
/* loaded from: classes4.dex */
public final class Duration implements Comparable<Duration> {
    private final double value;
    public static final Companion Companion = new Companion(null);
    private static final double ZERO = m12181constructorimpl(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    private static final double INFINITE = m12181constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());

    /* compiled from: Duration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public final double convert(double d, @NotNull TimeUnit sourceUnit, @NotNull TimeUnit targetUnit) {
            Intrinsics.checkParameterIsNotNull(sourceUnit, "sourceUnit");
            Intrinsics.checkParameterIsNotNull(targetUnit, "targetUnit");
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, sourceUnit, targetUnit);
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ Duration(double d) {
        this.value = d;
    }

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m12179boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static double m12181constructorimpl(double d) {
        return d;
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m12182divLRDsOJo(double d, double d2) {
        return d / d2;
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m12184divimpl(double d, int i) {
        return m12181constructorimpl(d / i);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m12185equalsimpl(double d, @Nullable Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).m12224unboximpl()) == 0;
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m12186equalsimpl0(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    /* renamed from: getAbsoluteValue-impl  reason: not valid java name */
    public static final double m12187getAbsoluteValueimpl(double d) {
        return m12202isNegativeimpl(d) ? m12222unaryMinusimpl(d) : d;
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m12188getHoursComponentimpl(double d) {
        return (int) (m12190getInHoursimpl(d) % 24);
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m12189getInDaysimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m12190getInHoursimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.HOURS);
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m12191getInMicrosecondsimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m12192getInMillisecondsimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m12193getInMinutesimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.MINUTES);
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m12194getInNanosecondsimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m12195getInSecondsimpl(double d) {
        return m12213toDoubleimpl(d, TimeUnit.SECONDS);
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m12196getMinutesComponentimpl(double d) {
        return (int) (m12193getInMinutesimpl(d) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m12197getNanosecondsComponentimpl(double d) {
        return (int) (m12194getInNanosecondsimpl(d) % 1.0E9d);
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m12198getSecondsComponentimpl(double d) {
        return (int) (m12195getInSecondsimpl(d) % 60);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m12199hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    @PublishedApi
    public static /* synthetic */ void hoursComponent$annotations() {
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m12200isFiniteimpl(double d) {
        return !Double.isInfinite(d) && !Double.isNaN(d);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m12201isInfiniteimpl(double d) {
        return Double.isInfinite(d);
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m12202isNegativeimpl(double d) {
        return d < ((double) 0);
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m12203isPositiveimpl(double d) {
        return d > ((double) 0);
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final double m12204minusLRDsOJo(double d, double d2) {
        return m12181constructorimpl(d - d2);
    }

    @PublishedApi
    public static /* synthetic */ void minutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final double m12205plusLRDsOJo(double d, double d2) {
        return m12181constructorimpl(d + d2);
    }

    /* renamed from: precision-impl  reason: not valid java name */
    private static final int m12206precisionimpl(double d, double d2) {
        if (d2 < 1) {
            return 3;
        }
        if (d2 < 10) {
            return 2;
        }
        return d2 < ((double) 100) ? 1 : 0;
    }

    @PublishedApi
    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m12208timesimpl(double d, int i) {
        return m12181constructorimpl(d * i);
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m12212toComponentsimpl(double d, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m12189getInDaysimpl(d)), Integer.valueOf(m12188getHoursComponentimpl(d)), Integer.valueOf(m12196getMinutesComponentimpl(d)), Integer.valueOf(m12198getSecondsComponentimpl(d)), Integer.valueOf(m12197getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m12213toDoubleimpl(double d, @NotNull TimeUnit unit) {
        TimeUnit storageUnit;
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        storageUnit = DurationKt.getStorageUnit();
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, storageUnit, unit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m12214toIntimpl(double d, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (int) m12213toDoubleimpl(d, unit);
    }

    @NotNull
    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m12215toIsoStringimpl(double d) {
        String padStart;
        StringBuilder sb = new StringBuilder();
        if (m12202isNegativeimpl(d)) {
            sb.append('-');
        }
        sb.append("PT");
        double m12187getAbsoluteValueimpl = m12187getAbsoluteValueimpl(d);
        int m12190getInHoursimpl = (int) m12190getInHoursimpl(m12187getAbsoluteValueimpl);
        int m12196getMinutesComponentimpl = m12196getMinutesComponentimpl(m12187getAbsoluteValueimpl);
        int m12198getSecondsComponentimpl = m12198getSecondsComponentimpl(m12187getAbsoluteValueimpl);
        int m12197getNanosecondsComponentimpl = m12197getNanosecondsComponentimpl(m12187getAbsoluteValueimpl);
        boolean z = true;
        boolean z2 = m12190getInHoursimpl != 0;
        boolean z3 = (m12198getSecondsComponentimpl == 0 && m12197getNanosecondsComponentimpl == 0) ? false : true;
        if (m12196getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m12190getInHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(m12196getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(m12198getSecondsComponentimpl);
            if (m12197getNanosecondsComponentimpl != 0) {
                sb.append('.');
                padStart = StringsKt__StringsKt.padStart(String.valueOf(m12197getNanosecondsComponentimpl), 9, '0');
                if (m12197getNanosecondsComponentimpl % TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES == 0) {
                    sb.append((CharSequence) padStart, 0, 3);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else if (m12197getNanosecondsComponentimpl % 1000 == 0) {
                    sb.append((CharSequence) padStart, 0, 6);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else {
                    sb.append(padStart);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m12216toLongimpl(double d, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return (long) m12213toDoubleimpl(d, unit);
    }

    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m12217toLongMillisecondsimpl(double d) {
        return m12216toLongimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m12218toLongNanosecondsimpl(double d) {
        return m12216toLongimpl(d, TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a0  */
    @org.jetbrains.annotations.NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String m12219toStringimpl(double r8) {
        /*
            boolean r0 = m12201isInfiniteimpl(r8)
            if (r0 == 0) goto Lc
            java.lang.String r8 = java.lang.String.valueOf(r8)
            goto Lc1
        Lc:
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 != 0) goto L16
            java.lang.String r8 = "0s"
            goto Lc1
        L16:
            double r0 = m12187getAbsoluteValueimpl(r8)
            double r0 = m12194getInNanosecondsimpl(r0)
            r2 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r2 >= 0) goto L2f
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
        L2b:
            r1 = r3
            r3 = r4
            goto L90
        L2f:
            double r5 = (double) r4
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L38
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r1 = 7
            goto L90
        L38:
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L45
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
        L43:
            r1 = r3
            goto L90
        L45:
            r5 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L51
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MICROSECONDS
            goto L43
        L51:
            r5 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L5d
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            goto L43
        L5d:
            r5 = 4786511204640096256(0x426d1a94a2000000, double:1.0E12)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L69
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            goto L43
        L69:
            r5 = 4813020802404319232(0x42cb48eb57e00000, double:6.0E13)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L75
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MINUTES
            goto L43
        L75:
            r5 = 4839562400168542208(0x4329945ca2620000, double:3.6E15)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L81
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.HOURS
            goto L43
        L81:
            r5 = 4920018990336211136(0x44476b344f2a78c0, double:8.64E20)
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L8d
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L43
        L8d:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L2b
        L90:
            double r4 = m12213toDoubleimpl(r8, r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            if (r3 == 0) goto La0
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatScientific(r4)
            goto Lb3
        La0:
            if (r1 <= 0) goto La7
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatUpToDecimals(r4, r1)
            goto Lb3
        La7:
            double r6 = java.lang.Math.abs(r4)
            int r8 = m12206precisionimpl(r8, r6)
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatToExactDecimals(r4, r8)
        Lb3:
            r2.append(r8)
            java.lang.String r8 = kotlin.time.DurationUnitKt__DurationUnitKt.shortName(r0)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
        Lc1:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.Duration.m12219toStringimpl(double):java.lang.String");
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m12221toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m12220toStringimpl(d, timeUnit, i);
    }

    /* renamed from: unaryMinus-impl  reason: not valid java name */
    public static final double m12222unaryMinusimpl(double d) {
        return m12181constructorimpl(-d);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m12223compareToLRDsOJo(duration.m12224unboximpl());
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m12223compareToLRDsOJo(double d) {
        return m12180compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object obj) {
        return m12185equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m12199hashCodeimpl(this.value);
    }

    @NotNull
    public String toString() {
        return m12219toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ double m12224unboximpl() {
        return this.value;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m12180compareToLRDsOJo(double d, double d2) {
        return Double.compare(d, d2);
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m12183divimpl(double d, double d2) {
        return m12181constructorimpl(d / d2);
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m12207timesimpl(double d, double d2) {
        return m12181constructorimpl(d * d2);
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m12211toComponentsimpl(double d, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m12190getInHoursimpl(d)), Integer.valueOf(m12196getMinutesComponentimpl(d)), Integer.valueOf(m12198getSecondsComponentimpl(d)), Integer.valueOf(m12197getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m12210toComponentsimpl(double d, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.invoke(Integer.valueOf((int) m12193getInMinutesimpl(d)), Integer.valueOf(m12198getSecondsComponentimpl(d)), Integer.valueOf(m12197getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m12209toComponentsimpl(double d, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return action.mo12248invoke(Long.valueOf((long) m12195getInSecondsimpl(d)), Integer.valueOf(m12197getNanosecondsComponentimpl(d)));
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m12220toStringimpl(double d, @NotNull TimeUnit unit, int i) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (i >= 0) {
            if (m12201isInfiniteimpl(d)) {
                return String.valueOf(d);
            }
            double m12213toDoubleimpl = m12213toDoubleimpl(d, unit);
            StringBuilder sb = new StringBuilder();
            sb.append(Math.abs(m12213toDoubleimpl) < 1.0E14d ? FormatToDecimalsKt.formatToExactDecimals(m12213toDoubleimpl, RangesKt.coerceAtMost(i, 12)) : FormatToDecimalsKt.formatScientific(m12213toDoubleimpl));
            sb.append(DurationUnitKt__DurationUnitKt.shortName(unit));
            return sb.toString();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("decimals must be not negative, but was ", i).toString());
    }
}
