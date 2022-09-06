package kotlinx.io.core;

import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.internal.jvm.ErrorsKt;
import kotlinx.io.pool.DefaultPool;
import kotlinx.io.pool.NoPoolImpl;
import kotlinx.io.pool.ObjectPool;
import kotlinx.io.utils.AtomicKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IoBufferJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0010\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u001b\u0018\u0000 µ\u00012\u00020\u00012\u00020\u0002:\u0002µ\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\bJ\b\u0010E\u001a\u00020FH\u0002J\t\u0010G\u001a\u00020FH\u0081\bJ\u0014\u0010H\u001a\u00060Ij\u0002`J2\u0006\u0010K\u001a\u00020LH\u0016J\"\u0010H\u001a\u00060Ij\u0002`J2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aJ\u0014\u0010H\u001a\u00060Ij\u0002`J2\b\u0010M\u001a\u0004\u0018\u00010QJ$\u0010H\u001a\u00060Ij\u0002`J2\b\u0010M\u001a\u0004\u0018\u00010Q2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aJ(\u0010R\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010M\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aH\u0002J(\u0010S\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010M\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aH\u0002J\u0018\u0010T\u001a\u00020\u001a2\u0006\u0010U\u001a\u00020L2\u0006\u0010V\u001a\u00020\u001aH\u0002J\u001e\u0010W\u001a\u00020\u001a2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aJ\u001e\u0010W\u001a\u00020\u001a2\u0006\u0010M\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aJ(\u0010X\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010M\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u001aH\u0002J\u0006\u0010Y\u001a\u00020(J\u0006\u0010Z\u001a\u00020(J\b\u0010[\u001a\u00020FH\u0002J\b\u0010\\\u001a\u00020FH\u0016J\u000e\u0010]\u001a\u00020:2\u0006\u0010^\u001a\u00020:J\u000e\u0010_\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aJ\u0016\u0010`\u001a\u00020F2\u0006\u0010^\u001a\u00020:2\u0006\u0010a\u001a\u00020bJ\u0006\u0010c\u001a\u00020FJ\b\u0010d\u001a\u00020(H\u0007J\u0006\u0010e\u001a\u00020\u0000J\u0010\u0010f\u001a\u00020\u001e2\u0006\u0010K\u001a\u00020LH\u0002J\u000e\u0010g\u001a\u00020\u001a2\u0006\u0010h\u001a\u00020\u0000J\u000e\u0010i\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aJ\u0018\u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020\u00042\u0006\u0010l\u001a\u00020\u001aH\u0007J \u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020m2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aH\u0007J\u0016\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020\u00042\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020m2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020p2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020q2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020r2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020s2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020t2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u0016\u0010o\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020\u00002\u0006\u0010l\u001a\u00020\u001aJ\u0006\u0010u\u001a\u00020bJ\u001d\u0010v\u001a\u00020\u001a2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020F0xH\u0086\bJ\u0006\u0010y\u001a\u00020zJ\u0006\u0010{\u001a\u00020|J\u0016\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020\u00042\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020m2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020p2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020q2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020r2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020s2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u001e\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020t2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u0018\u0010}\u001a\u00020F2\u0006\u0010k\u001a\u00020\u00002\b\b\u0002\u0010l\u001a\u00020\u001aJ\u0006\u0010~\u001a\u00020\u001aJ\u0006\u0010\u007f\u001a\u00020:J\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001J2\u0010\u0082\u0001\u001a\u00020\u001a2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u000b\u0010\u0085\u0001\u001a\u00060Ij\u0002`J2\u0007\u0010\u0086\u0001\u001a\u00020(2\t\b\u0002\u0010\u0087\u0001\u001a\u00020\u001aJ1\u0010\u0088\u0001\u001a\u00020\u001a2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u0086\u0001\u001a\u00020(2\t\b\u0002\u0010\u0087\u0001\u001a\u00020\u001aH\u0002J4\u0010\u008a\u0001\u001a\u00020\u001a2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u000b\u0010\u0085\u0001\u001a\u00060Ij\u0002`J2\u0007\u0010\u0086\u0001\u001a\u00020(2\t\b\u0002\u0010\u0087\u0001\u001a\u00020\u001aH\u0002J\u0017\u0010\u008b\u0001\u001a\u00020F2\u000e\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000\u008d\u0001J\t\u0010\u008e\u0001\u001a\u00020(H\u0002J\u000f\u0010\u008f\u0001\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aJ\u000f\u0010\u0090\u0001\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aJ\u0007\u0010\u0091\u0001\u001a\u00020FJ\u0007\u0010\u0092\u0001\u001a\u00020FJ\u0010\u0010\u0092\u0001\u001a\u00020F2\u0007\u0010\u0093\u0001\u001a\u00020\u001aJ\u0012\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020\u0004H\u0007J\u0017\u0010\u0096\u0001\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aH\u0000¢\u0006\u0003\b\u0097\u0001J\u0017\u0010\u0098\u0001\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001aH\u0000¢\u0006\u0003\b\u0099\u0001J\n\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0016J\u0007\u0010\u009c\u0001\u001a\u00020\u001aJ\u000b\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0004H\u0002J\u0011\u0010\u009e\u0001\u001a\u00020F2\u0006\u0010h\u001a\u00020\u0004H\u0007J\"\u0010\u009e\u0001\u001a\u00020F2\u0007\u0010\u009f\u0001\u001a\u00020m2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aH\u0007J\u0019\u0010=\u001a\u00020\u001a2\u0007\u0010 \u0001\u001a\u00020\u00002\u0006\u0010l\u001a\u00020\u001aH\u0007J!\u0010¡\u0001\u001a\u00020F2\u0007\u0010¢\u0001\u001a\u00020\u00002\u0007\u0010£\u0001\u001a\u00020\u001aH\u0000¢\u0006\u0003\b¤\u0001J\u0018\u0010¥\u0001\u001a\u00020F2\u0007\u0010¢\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\b¦\u0001J\u000f\u0010§\u0001\u001a\u00020F2\u0006\u0010a\u001a\u00020bJ'\u0010¨\u0001\u001a\u00020\u001a2\u0007\u0010©\u0001\u001a\u00020\u001a2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020F0xH\u0086\bJ\u000f\u0010ª\u0001\u001a\u00020F2\u0006\u0010a\u001a\u00020zJ\u000f\u0010«\u0001\u001a\u00020F2\u0006\u0010a\u001a\u00020|J\u0012\u0010¬\u0001\u001a\u00020F2\u0007\u0010\u00ad\u0001\u001a\u00020\u0004H\u0016J \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020m2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020p2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020q2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020r2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020s2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ \u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020t2\u0006\u0010n\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020\u001aJ\u0018\u0010¬\u0001\u001a\u00020F2\u0007\u0010 \u0001\u001a\u00020\u00002\u0006\u0010l\u001a\u00020\u001aJ\u000f\u0010®\u0001\u001a\u00020F2\u0006\u0010a\u001a\u00020\u001aJ\u000f\u0010¯\u0001\u001a\u00020F2\u0006\u0010a\u001a\u00020:J\u0010\u0010°\u0001\u001a\u00020F2\u0007\u0010a\u001a\u00030\u0081\u0001J\u0016\u0010±\u0001\u001a\u00020\u001a*\u00020\u00042\u0006\u0010a\u001a\u00020\u001aH\u0082\bJ\u001f\u0010²\u0001\u001a\u00020\u001a*\u00020\u00042\u0007\u0010³\u0001\u001a\u00020\u001a2\u0007\u0010´\u0001\u001a\u00020\u001aH\u0002R&\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001e8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\f\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001e8FX\u0087\u0004¢\u0006\f\u0012\u0004\b#\u0010\f\u001a\u0004\b$\u0010!R\u0011\u0010%\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b&\u0010\u001cR\u0014\u0010'\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0000X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010-R\u0018\u00101\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0000\u0012\u0004\b2\u0010\fR&\u00103\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u001a8Â\u0002@Â\u0002X\u0082\u000e¢\u0006\f\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u00106R\u0011\u00107\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b8\u0010\u001cR\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010;\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b<\u0010\u001cR\u0018\u0010=\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0000\u0012\u0004\b>\u0010\fR,\u0010?\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u001a8À\u0002@À\u0002X\u0081\u000e¢\u0006\u0012\u0012\u0004\b@\u0010\f\u001a\u0004\bA\u0010\u001c\"\u0004\bB\u00106R\u0011\u0010C\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\bD\u0010\u001c¨\u0006¶\u0001"}, d2 = {"Lkotlinx/io/core/IoBuffer;", "Lkotlinx/io/core/Input;", "Lkotlinx/io/core/Output;", "external", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "content", "origin", "(Ljava/nio/ByteBuffer;Lkotlinx/io/core/IoBuffer;)V", "attachment", "", "attachment$annotations", "()V", "getAttachment", "()Ljava/lang/Object;", "setAttachment", "(Ljava/lang/Object;)V", "value", "Lkotlinx/io/core/ByteOrder;", "byteOrder", "byteOrder$annotations", "getByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "capacity", "", "getCapacity", "()I", "doNotImplementInputButExtendAbstractInputInstead", "", "doNotImplementInputButExtendAbstractInputInstead$annotations", "getDoNotImplementInputButExtendAbstractInputInstead", "()Ljava/lang/Void;", "doNotImplementOutputButExtendAbstractOutputInstead", "doNotImplementOutputButExtendAbstractOutputInstead$annotations", "getDoNotImplementOutputButExtendAbstractOutputInstead", "endGap", "getEndGap", "endOfInput", "", "getEndOfInput", "()Z", "next", "getNext", "()Lkotlinx/io/core/IoBuffer;", "setNext", "(Lkotlinx/io/core/IoBuffer;)V", "getOrigin$kotlinx_io", "readBuffer", "readBuffer$annotations", "readPosition", "getReadPosition", "setReadPosition", "(I)V", "readRemaining", "getReadRemaining", "refCount", "", "startGap", "getStartGap", "writeBuffer", "writeBuffer$annotations", "writePosition", "writePosition$annotations", "getWritePosition", "setWritePosition", "writeRemaining", "getWriteRemaining", "acquire", "", "afterWrite", "append", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "c", "", "csq", "", "start", "end", "", "appendASCII_array", "appendASCII_buffer", "appendCharFailed", "ch", "idx", "appendChars", "appendUTF8", "canRead", "canWrite", "clearInstanceInternal", "close", "discard", JsonReportFormat.COUNT, "discardExact", "fill", "v", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "isExclusivelyOwned", "makeView", "notEnoughFreeSpace", "peekTo", "buffer", "pushBack", "read", "dst", "length", "", "offset", "readAvailable", "", "", "", "", "", "readByte", "readDirect", "block", "Lkotlin/Function1;", "readDouble", "", "readFloat", "", "readFully", "readInt", "readLong", "readShort", "", "readText", "decoder", "Ljava/nio/charset/CharsetDecoder;", "out", "lastBuffer", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "readTextDirectlyToOut", "Ljava/nio/CharBuffer;", "readTextImpl", "release", "pool", "Lkotlinx/io/pool/ObjectPool;", "releaseRefCount", "reserveEndGap", "reserveStartGap", "resetForRead", "resetForWrite", MetricsUtil.LegacyMetricTypes.LIMIT, "resetFromContentToWrite", MetricKeys.VALUE_IS_CHILD, "restoreEndGap", "restoreEndGap$kotlinx_io", "restoreStartGap", "restoreStartGap$kotlinx_io", "toString", "", "tryPeek", MetricsConstants.Session.SESSION_RELEASED_REASON_UNLINK, "write", "array", "src", "writeBufferAppend", "other", "maxSize", "writeBufferAppend$kotlinx_io", "writeBufferPrepend", "writeBufferPrepend$kotlinx_io", "writeByte", "writeDirect", "size", "writeDouble", "writeFloat", "writeFully", "bb", "writeInt", "writeLong", "writeShort", "putUtf8Char", "putUtf8CharSurrogate", RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW, "Companion", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class IoBuffer implements Input, Output {
    private static final int DEFAULT_BUFFER_POOL_DIRECT;
    private static final int DEFAULT_BUFFER_POOL_SIZE;
    private static final int DEFAULT_BUFFER_SIZE;
    @NotNull
    private static final IoBuffer Empty;
    private static final ByteBuffer EmptyBuffer;
    @NotNull
    private static final ObjectPool<IoBuffer> EmptyPool;
    @NotNull
    private static final ObjectPool<IoBuffer> NoPool;
    @NotNull
    private static final ObjectPool<IoBuffer> Pool;
    private static final AtomicLongFieldUpdater<IoBuffer> RefCount;
    @Nullable
    private Object attachment;
    private ByteBuffer content;
    @Nullable
    private IoBuffer next;
    @Nullable
    private final IoBuffer origin;
    @JvmField
    @NotNull
    public ByteBuffer readBuffer;
    private volatile long refCount;
    @JvmField
    @NotNull
    public ByteBuffer writeBuffer;
    public static final Companion Companion = new Companion(null);
    private static final int ReservedSize = 8;

    /* compiled from: IoBufferJVM.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\b0\b0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lkotlinx/io/core/IoBuffer$Companion;", "", "()V", "DEFAULT_BUFFER_POOL_DIRECT", "", "DEFAULT_BUFFER_POOL_SIZE", "DEFAULT_BUFFER_SIZE", "Empty", "Lkotlinx/io/core/IoBuffer;", "getEmpty", "()Lkotlinx/io/core/IoBuffer;", "EmptyBuffer", "Ljava/nio/ByteBuffer;", "EmptyPool", "Lkotlinx/io/pool/ObjectPool;", "getEmptyPool", "()Lkotlinx/io/pool/ObjectPool;", "NoPool", "getNoPool", "Pool", "getPool", "RefCount", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "kotlin.jvm.PlatformType", "ReservedSize", "getReservedSize", "()I", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final IoBuffer getEmpty() {
            return IoBuffer.Empty;
        }

        @NotNull
        public final ObjectPool<IoBuffer> getEmptyPool() {
            return IoBuffer.EmptyPool;
        }

        @NotNull
        public final ObjectPool<IoBuffer> getNoPool() {
            return IoBuffer.NoPool;
        }

        @NotNull
        public final ObjectPool<IoBuffer> getPool() {
            return IoBuffer.Pool;
        }

        public final int getReservedSize() {
            return IoBuffer.ReservedSize;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(0);
        Intrinsics.checkExpressionValueIsNotNull(allocateDirect, "ByteBuffer.allocateDirect(0)");
        EmptyBuffer = allocateDirect;
        AtomicLongFieldUpdater<IoBuffer> newUpdater = AtomicLongFieldUpdater.newUpdater(IoBuffer.class, "refCount");
        if (newUpdater == null) {
            Intrinsics.throwNpe();
        }
        RefCount = newUpdater;
        DEFAULT_BUFFER_SIZE = AtomicKt.getIOIntProperty("buffer.size", 4096);
        DEFAULT_BUFFER_POOL_SIZE = AtomicKt.getIOIntProperty("buffer.pool.size", 100);
        DEFAULT_BUFFER_POOL_DIRECT = AtomicKt.getIOIntProperty("buffer.pool.direct", 0);
        Empty = new IoBuffer(EmptyBuffer, null);
        final int i = DEFAULT_BUFFER_POOL_SIZE;
        Pool = new DefaultPool<IoBuffer>(i) { // from class: kotlinx.io.core.IoBuffer$Companion$Pool$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            @NotNull
            public IoBuffer clearInstance(@NotNull IoBuffer instance) {
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                instance.clearInstanceInternal();
                return instance;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            public void disposeInstance(@NotNull IoBuffer instance) {
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                instance.unlink();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            @NotNull
            public IoBuffer produceInstance() {
                int i2;
                int i3;
                ByteBuffer buffer;
                int i4;
                i2 = IoBuffer.DEFAULT_BUFFER_POOL_DIRECT;
                if (i2 != 0) {
                    i4 = IoBuffer.DEFAULT_BUFFER_SIZE;
                    buffer = ByteBuffer.allocateDirect(i4);
                } else {
                    i3 = IoBuffer.DEFAULT_BUFFER_SIZE;
                    buffer = ByteBuffer.allocate(i3);
                }
                Intrinsics.checkExpressionValueIsNotNull(buffer, "buffer");
                return new IoBuffer(buffer, null, null);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // kotlinx.io.pool.DefaultPool
            public void validateInstance(@NotNull IoBuffer instance) {
                long j;
                Intrinsics.checkParameterIsNotNull(instance, "instance");
                j = instance.refCount;
                boolean z = true;
                if (j == 0) {
                    if (instance.getOrigin$kotlinx_io() != null) {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                    new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$Companion$Pool$1$validateInstance$$inlined$require$2
                        @Override // kotlinx.io.core.internal.RequireFailureCapture
                        @NotNull
                        public Void doFail() {
                            throw new IllegalArgumentException("Unable to recycle buffer view, only origin buffers are applicable");
                        }
                    }.doFail();
                    throw null;
                }
                new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$Companion$Pool$1$validateInstance$$inlined$require$1
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        throw new IllegalArgumentException("Buffer is not yet released but tried to recycle");
                    }
                }.doFail();
                throw null;
            }
        };
        NoPool = new NoPoolImpl<IoBuffer>() { // from class: kotlinx.io.core.IoBuffer$Companion$NoPool$1
            @Override // kotlinx.io.pool.ObjectPool
            @NotNull
            /* renamed from: borrow  reason: collision with other method in class */
            public IoBuffer mo12351borrow() {
                int i2;
                int i3;
                ByteBuffer buffer;
                int i4;
                i2 = IoBuffer.DEFAULT_BUFFER_POOL_DIRECT;
                if (i2 != 0) {
                    i4 = IoBuffer.DEFAULT_BUFFER_SIZE;
                    buffer = ByteBuffer.allocateDirect(i4);
                } else {
                    i3 = IoBuffer.DEFAULT_BUFFER_SIZE;
                    buffer = ByteBuffer.allocate(i3);
                }
                Intrinsics.checkExpressionValueIsNotNull(buffer, "buffer");
                return new IoBuffer(buffer, null, null);
            }
        };
        EmptyPool = EmptyBufferPoolImpl.INSTANCE;
    }

    private IoBuffer(ByteBuffer byteBuffer, IoBuffer ioBuffer) {
        this.content = byteBuffer;
        this.origin = ioBuffer;
        ByteBuffer byteBuffer2 = this.content;
        ByteBuffer byteBuffer3 = EmptyBuffer;
        if (byteBuffer2 != byteBuffer3) {
            byteBuffer3 = byteBuffer2.slice();
            Intrinsics.checkExpressionValueIsNotNull(byteBuffer3, "content.slice()");
        }
        this.readBuffer = byteBuffer3;
        ByteBuffer byteBuffer4 = this.content;
        ByteBuffer byteBuffer5 = EmptyBuffer;
        if (byteBuffer4 != byteBuffer5) {
            byteBuffer5 = byteBuffer4.slice();
            Intrinsics.checkExpressionValueIsNotNull(byteBuffer5, "content.slice()");
        }
        this.writeBuffer = byteBuffer5;
        this.refCount = 1L;
        if (this.origin != this) {
            this.readBuffer.limit(0);
        } else {
            new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$$special$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    throw new IllegalArgumentException("origin shouldn't point to itself");
                }
            }.doFail();
            throw null;
        }
    }

    private final void acquire() {
        long j;
        do {
            j = this.refCount;
            if (j == 0) {
                throw new IllegalStateException("Unable to acquire: already released");
            }
        } while (!RefCount.compareAndSet(this, j, 1 + j));
    }

    private final int appendASCII_array(ByteBuffer byteBuffer, CharSequence charSequence, int i, int i2) {
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int min = Math.min(i2, byteBuffer.remaining() + i);
        while (true) {
            if (i < min) {
                char charAt = charSequence.charAt(i);
                if (charAt > 127 || position >= array.length) {
                    break;
                }
                array[position] = (byte) charAt;
                i++;
                position++;
            } else {
                i = min;
                break;
            }
        }
        byteBuffer.position(position - byteBuffer.arrayOffset());
        return i;
    }

    private final int appendASCII_buffer(ByteBuffer byteBuffer, CharSequence charSequence, int i, int i2) {
        int min = Math.min(i2, byteBuffer.remaining() + i);
        while (i < min) {
            char charAt = charSequence.charAt(i);
            if (charAt > 127) {
                return i;
            }
            byteBuffer.put((byte) charAt);
            i++;
        }
        return min;
    }

    private final int appendCharFailed(char c, int i) {
        this.readBuffer.limit(this.writeBuffer.position());
        return CharsKt.isSurrogate(c) ? i - 2 : i - 1;
    }

    private final int appendUTF8(ByteBuffer byteBuffer, CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            int i4 = 0;
            if (CharsKt.isSurrogate(charAt)) {
                i4 = putUtf8CharSurrogate(byteBuffer, charAt, charSequence.charAt(i3));
                i3++;
            } else if (1 <= charAt && 127 >= charAt) {
                if (byteBuffer.remaining() >= 1) {
                    byteBuffer.put((byte) charAt);
                    i4 = 1;
                }
            } else if (charAt > 65535) {
                if (byteBuffer.remaining() >= 4) {
                    byteBuffer.put((byte) (((charAt >> 18) & 63) | 240));
                    byteBuffer.put((byte) (((charAt >> '\f') & 63) | 128));
                    byteBuffer.put((byte) (((charAt >> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128));
                    i4 = 4;
                }
            } else if (charAt > 2047) {
                if (byteBuffer.remaining() >= 3) {
                    byteBuffer.put((byte) (((charAt >> '\f') & 15) | 224));
                    byteBuffer.put((byte) (((charAt >> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128));
                    i4 = 3;
                }
            } else if (byteBuffer.remaining() >= 2) {
                byteBuffer.put((byte) (((charAt >> 6) & 31) | 192));
                byteBuffer.put((byte) ((charAt & Constants.DEFAULT_IMAGE_CHAR) | 128));
                i4 = 2;
            }
            if (i4 == 0) {
                return appendCharFailed(charAt, i3);
            }
            i = i3;
        }
        this.readBuffer.limit(this.writeBuffer.position());
        return i2;
    }

    @ExperimentalIoApi
    public static /* synthetic */ void attachment$annotations() {
    }

    @Deprecated(message = "Read/write with readXXXLittleEndian/writeXXXLittleEndian or do readXXX/writeXXX with X.reverseByteOrder() instead.")
    public static /* synthetic */ void byteOrder$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearInstanceInternal() {
        this.next = null;
        this.attachment = null;
        ByteBuffer byteBuffer = this.writeBuffer;
        byteBuffer.limit(byteBuffer.capacity());
        this.writeBuffer.position(0);
        this.readBuffer.limit(0);
        if (RefCount.compareAndSet(this, 0L, 1L)) {
            return;
        }
        throw new IllegalStateException("Unable to prepare buffer: refCount is not zero (used while parked in the pool?)");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Suppress warning.")
    public static /* synthetic */ void doNotImplementInputButExtendAbstractInputInstead$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Suppress warning.")
    public static /* synthetic */ void doNotImplementOutputButExtendAbstractOutputInstead$annotations() {
    }

    private final int getReadPosition() {
        return this.readBuffer.position();
    }

    private final Void notEnoughFreeSpace(char c) {
        throw new IllegalStateException("Not Enough free space to append character '" + c + "', remaining " + getWriteRemaining() + " bytes");
    }

    private final int putUtf8Char(@NotNull ByteBuffer byteBuffer, int i) {
        if (1 <= i && 127 >= i) {
            if (byteBuffer.remaining() < 1) {
                return 0;
            }
            byteBuffer.put((byte) i);
            return 1;
        } else if (i > 65535) {
            if (byteBuffer.remaining() < 4) {
                return 0;
            }
            byteBuffer.put((byte) (((i >> 18) & 63) | 240));
            byteBuffer.put((byte) (((i >> 12) & 63) | 128));
            byteBuffer.put((byte) (((i >> 6) & 63) | 128));
            byteBuffer.put((byte) ((i & 63) | 128));
            return 4;
        } else if (i > 2047) {
            if (byteBuffer.remaining() < 3) {
                return 0;
            }
            byteBuffer.put((byte) (((i >> 12) & 15) | 224));
            byteBuffer.put((byte) (((i >> 6) & 63) | 128));
            byteBuffer.put((byte) ((i & 63) | 128));
            return 3;
        } else if (byteBuffer.remaining() < 2) {
            return 0;
        } else {
            byteBuffer.put((byte) (((i >> 6) & 31) | 192));
            byteBuffer.put((byte) ((i & 63) | 128));
            return 2;
        }
    }

    private final int putUtf8CharSurrogate(@NotNull ByteBuffer byteBuffer, int i, int i2) {
        int i3 = ((i & 2047) << 10) | (i2 & 1023) | 65536;
        if (1 <= i3 && 127 >= i3) {
            if (byteBuffer.remaining() < 1) {
                return 0;
            }
            byteBuffer.put((byte) i3);
            return 1;
        } else if (i3 > 65535) {
            if (byteBuffer.remaining() < 4) {
                return 0;
            }
            byteBuffer.put((byte) (((i3 >> 18) & 63) | 240));
            byteBuffer.put((byte) (((i3 >> 12) & 63) | 128));
            byteBuffer.put((byte) (((i3 >> 6) & 63) | 128));
            byteBuffer.put((byte) ((i3 & 63) | 128));
            return 4;
        } else if (i3 > 2047) {
            if (byteBuffer.remaining() < 3) {
                return 0;
            }
            byteBuffer.put((byte) (((i3 >> 12) & 15) | 224));
            byteBuffer.put((byte) (((i3 >> 6) & 63) | 128));
            byteBuffer.put((byte) ((i3 & 63) | 128));
            return 3;
        } else if (byteBuffer.remaining() < 2) {
            return 0;
        } else {
            byteBuffer.put((byte) (((i3 >> 6) & 31) | 192));
            byteBuffer.put((byte) ((i3 & 63) | 128));
            return 2;
        }
    }

    @PublishedApi
    public static /* synthetic */ void readBuffer$annotations() {
    }

    public static /* synthetic */ int readText$default(IoBuffer ioBuffer, CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return ioBuffer.readText(charsetDecoder, appendable, z, i);
    }

    private final int readTextDirectlyToOut(CharsetDecoder charsetDecoder, CharBuffer charBuffer, boolean z, int i) {
        int i2 = 0;
        if (!this.readBuffer.hasRemaining()) {
            return 0;
        }
        int limit = charBuffer.limit();
        if (i < charBuffer.remaining()) {
            charBuffer.limit(charBuffer.position() + i);
        }
        while (true) {
            int position = charBuffer.position();
            CoderResult cr = charsetDecoder.decode(this.readBuffer, charBuffer, z);
            Intrinsics.checkExpressionValueIsNotNull(cr, "cr");
            if (cr.isError()) {
                cr.throwException();
            }
            i2 += charBuffer.position() - position;
            if (!cr.isOverflow() && (!cr.isUnderflow() || this.readBuffer.hasRemaining())) {
            }
        }
        charBuffer.limit(limit);
        return i2;
    }

    static /* synthetic */ int readTextDirectlyToOut$default(IoBuffer ioBuffer, CharsetDecoder charsetDecoder, CharBuffer charBuffer, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return ioBuffer.readTextDirectlyToOut(charsetDecoder, charBuffer, z, i);
    }

    private final int readTextImpl(CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i) {
        int i2 = 0;
        if (i != 0 && this.readBuffer.hasRemaining()) {
            IoBuffer mo12351borrow = Pool.mo12351borrow();
            CharBuffer asCharBuffer = mo12351borrow.content.asCharBuffer();
            int i3 = i;
            while (i2 < i) {
                asCharBuffer.clear();
                if (i3 < asCharBuffer.remaining()) {
                    asCharBuffer.limit(i3);
                }
                CoderResult cr = charsetDecoder.decode(this.readBuffer, asCharBuffer, z);
                Intrinsics.checkExpressionValueIsNotNull(cr, "cr");
                if (cr.isError()) {
                    mo12351borrow.release(Pool);
                    cr.throwException();
                }
                asCharBuffer.flip();
                int remaining = asCharBuffer.remaining();
                appendable.append(asCharBuffer);
                i2 += remaining;
                i3 -= remaining;
                if (remaining == 0 && cr.isUnderflow()) {
                    break;
                }
            }
            mo12351borrow.release(Pool);
        }
        return i2;
    }

    static /* synthetic */ int readTextImpl$default(IoBuffer ioBuffer, CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return ioBuffer.readTextImpl(charsetDecoder, appendable, z, i);
    }

    private final boolean releaseRefCount() {
        long j;
        long j2;
        if (this != Empty) {
            do {
                j = this.refCount;
                j2 = j - 1;
                if (j == 0) {
                    throw new IllegalStateException("Unable to release: already released");
                }
            } while (!RefCount.compareAndSet(this, j, j2));
            return j2 == 0;
        }
        throw new IllegalArgumentException("Attempted to release empty");
    }

    private final void setReadPosition(int i) {
        this.readBuffer.position(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ByteBuffer unlink() {
        if (this.refCount == 0) {
            ByteBuffer byteBuffer = EmptyBuffer;
            ByteBuffer byteBuffer2 = this.content;
            if (byteBuffer2 == byteBuffer) {
                return null;
            }
            this.content = byteBuffer;
            this.readBuffer = byteBuffer;
            this.writeBuffer = byteBuffer;
            return byteBuffer2;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("Unable to unlink buffer view: refCount is "), this.refCount, " != 0"));
    }

    @PublishedApi
    public static /* synthetic */ void writeBuffer$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void writePosition$annotations() {
    }

    @PublishedApi
    public final void afterWrite() {
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // java.lang.Appendable
    @NotNull
    public final Appendable append(@Nullable CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (appendChars(charSequence, i, i2) == i2) {
            return this;
        }
        throw new IllegalStateException("Not enough free space to append char sequence");
    }

    public final int appendChars(@NotNull char[] csq, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        ByteBuffer byteBuffer = this.writeBuffer;
        while (i < i2) {
            int i3 = i + 1;
            char c = csq[i];
            int i4 = 0;
            if (CharsKt.isSurrogate(c)) {
                i4 = putUtf8CharSurrogate(byteBuffer, c, csq[i3]);
                i3++;
            } else if (1 <= c && 127 >= c) {
                if (byteBuffer.remaining() >= 1) {
                    byteBuffer.put((byte) c);
                    i4 = 1;
                }
            } else if (c > 65535) {
                if (byteBuffer.remaining() >= 4) {
                    byteBuffer.put((byte) (((c >> 18) & 63) | 240));
                    byteBuffer.put((byte) (((c >> '\f') & 63) | 128));
                    byteBuffer.put((byte) (((c >> 6) & 63) | 128));
                    byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
                    i4 = 4;
                }
            } else if (c > 2047) {
                if (byteBuffer.remaining() >= 3) {
                    byteBuffer.put((byte) (((c >> '\f') & 15) | 224));
                    byteBuffer.put((byte) (((c >> 6) & 63) | 128));
                    byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
                    i4 = 3;
                }
            } else if (byteBuffer.remaining() >= 2) {
                byteBuffer.put((byte) (((c >> 6) & 31) | 192));
                byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
                i4 = 2;
            }
            if (i4 == 0) {
                return appendCharFailed(c, i3);
            }
            i = i3;
        }
        this.readBuffer.limit(this.writeBuffer.position());
        return i2;
    }

    public final boolean canRead() {
        return this.readBuffer.hasRemaining();
    }

    public final boolean canWrite() {
        return this.writeBuffer.hasRemaining();
    }

    @Override // kotlinx.io.core.Input, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("close for buffer view is not supported");
    }

    @Override // kotlinx.io.core.Input
    public final long discard(final long j) {
        if (j >= 0) {
            int min = (int) Math.min(getReadRemaining(), j);
            this.readBuffer.position(this.readBuffer.position() + min);
            return min;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$discard$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Negative discard quantity ");
                outline107.append(j);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    public final void discardExact(int i) {
        ByteBuffer byteBuffer = this.readBuffer;
        byteBuffer.position(byteBuffer.position() + i);
    }

    @Override // kotlinx.io.core.Output
    public final void fill(final long j, byte b) {
        if (j <= ((long) getWriteRemaining())) {
            ByteBuffer byteBuffer = this.writeBuffer;
            int i = (int) j;
            for (int i2 = 0; i2 < i; i2++) {
                byteBuffer.put(b);
            }
            this.readBuffer.limit(this.writeBuffer.position());
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$fill$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("Not enough space to write "), j, " bytes"));
            }
        }.doFail();
        throw null;
    }

    @Override // kotlinx.io.core.Output
    public final void flush() {
    }

    @Nullable
    public final Object getAttachment() {
        return this.attachment;
    }

    @Override // kotlinx.io.core.Input
    @NotNull
    public final ByteOrder getByteOrder() {
        ByteOrder.Companion companion = ByteOrder.Companion;
        java.nio.ByteOrder order = this.readBuffer.order();
        Intrinsics.checkExpressionValueIsNotNull(order, "readBuffer.order()");
        return companion.of(order);
    }

    public final int getCapacity() {
        return this.writeBuffer.capacity();
    }

    @Override // kotlinx.io.core.Input
    @NotNull
    public final /* synthetic */ Void getDoNotImplementInputButExtendAbstractInputInstead() {
        throw new IllegalStateException("Should be never accessed.".toString());
    }

    @Override // kotlinx.io.core.Output
    @NotNull
    public final /* synthetic */ Void getDoNotImplementOutputButExtendAbstractOutputInstead() {
        throw new IllegalStateException("Should be never accessed.".toString());
    }

    public final int getEndGap() {
        return this.writeBuffer.capacity() - this.writeBuffer.limit();
    }

    @Override // kotlinx.io.core.Input
    public boolean getEndOfInput() {
        return !canRead();
    }

    @Nullable
    public final IoBuffer getNext() {
        return this.next;
    }

    @Nullable
    public final IoBuffer getOrigin$kotlinx_io() {
        return this.origin;
    }

    public final int getReadRemaining() {
        return this.readBuffer.remaining();
    }

    public final int getStartGap() {
        return this.readBuffer.position();
    }

    public final int getWritePosition() {
        return this.writeBuffer.position();
    }

    public final int getWriteRemaining() {
        return this.writeBuffer.remaining();
    }

    @ExperimentalIoApi
    public final boolean isExclusivelyOwned() {
        return this.refCount == 1;
    }

    @NotNull
    public final IoBuffer makeView() {
        if (this == Empty) {
            return this;
        }
        IoBuffer ioBuffer = this.origin;
        if (ioBuffer == null) {
            ioBuffer = this;
        }
        ioBuffer.acquire();
        IoBuffer ioBuffer2 = new IoBuffer(this.content, ioBuffer);
        ioBuffer2.attachment = this.attachment;
        int position = this.writeBuffer.position();
        ioBuffer2.writeBuffer.position(position);
        ioBuffer2.readBuffer.limit(position);
        ioBuffer2.writeBuffer.limit(this.writeBuffer.limit());
        ioBuffer2.readBuffer.position(this.readBuffer.position());
        return ioBuffer2;
    }

    @Override // kotlinx.io.core.Input
    public final int peekTo(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining, buffer.getWriteRemaining());
        buffer.writeFully(this, min);
        return min;
    }

    public final void pushBack(int i) {
        ByteBuffer byteBuffer = this.readBuffer;
        byteBuffer.position(byteBuffer.position() - i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use readFully instead", replaceWith = @ReplaceWith(expression = "readFully(dst, offset, length)", imports = {}))
    public final void read(@NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        readFully(dst, i, i2);
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int min = Math.min(i2, this.readBuffer.remaining());
        if (min == -1 && this.readBuffer.remaining() == 0) {
            return -1;
        }
        this.readBuffer.get(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public final byte readByte() {
        return this.readBuffer.get();
    }

    public final int readDirect(@NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteBuffer byteBuffer = this.readBuffer;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        block.mo12165invoke(byteBuffer);
        int position2 = byteBuffer.position() - position;
        if (position2 >= 0) {
            if (byteBuffer.limit() == limit) {
                return position2;
            }
            ErrorsKt.limitChangeError();
            throw null;
        }
        ErrorsKt.negativeShiftError(position2);
        throw null;
    }

    @Override // kotlinx.io.core.Input
    public final double readDouble() {
        return this.readBuffer.getDouble();
    }

    @Override // kotlinx.io.core.Input
    public final float readFloat() {
        return this.readBuffer.getFloat();
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.get(dst, i, i2);
    }

    @Override // kotlinx.io.core.Input
    public final int readInt() {
        return this.readBuffer.getInt();
    }

    @Override // kotlinx.io.core.Input
    public final long readLong() {
        return this.readBuffer.getLong();
    }

    @Override // kotlinx.io.core.Input
    public final short readShort() {
        return this.readBuffer.getShort();
    }

    public final int readText(@NotNull CharsetDecoder decoder, @NotNull Appendable out, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(out, "out");
        if (out instanceof CharBuffer) {
            return readTextDirectlyToOut(decoder, (CharBuffer) out, z, i);
        }
        return readTextImpl(decoder, out, z, i);
    }

    public final void release(@NotNull ObjectPool<IoBuffer> pool) {
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        if (releaseRefCount()) {
            resetForWrite();
            if (this.origin != null) {
                unlink();
                this.origin.release(pool);
                return;
            }
            pool.recycle(this);
        }
    }

    public final void reserveEndGap(final int i) {
        if (i >= 0) {
            int limit = this.writeBuffer.limit();
            if (limit == this.writeBuffer.capacity()) {
                int i2 = limit - i;
                if (i2 >= this.writeBuffer.position()) {
                    this.writeBuffer.limit(i2);
                    return;
                }
                throw new IllegalStateException(GeneratedOutlineSupport1.outline52("Can't reserve ", i, " bytes gap: there are already bytes written at the end - not enough space to reserve"));
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Can't reserve ", i, " bytes gap: there is already a reserved gap (");
            outline109.append(this.writeBuffer.capacity() - limit);
            outline109.append(" bytes)");
            throw new IllegalStateException(outline109.toString());
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$reserveEndGap$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("n shouldn't be negative: ");
                outline107.append(i);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    public final void reserveStartGap(final int i) {
        boolean z = true;
        if (i >= 0) {
            if (i > this.writeBuffer.capacity()) {
                z = false;
            }
            if (z) {
                int position = this.readBuffer.position();
                if (position == 0) {
                    int position2 = this.writeBuffer.position();
                    if (position2 == 0 && position == position2) {
                        int i2 = position2 + i;
                        this.writeBuffer.position(i2);
                        this.readBuffer.limit(i2);
                        this.readBuffer.position(position + i);
                        return;
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline52("Can't reserve ", i, " bytes gap: there are already bytes written at the beginning"));
                }
                throw new IllegalStateException(GeneratedOutlineSupport1.outline54("Can't reserve ", i, " bytes gap: there is already a reserved gap (", position, " bytes)"));
            }
            new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$reserveStartGap$$inlined$require$2
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough space to reserve "), i, " bytes"));
                }
            }.doFail();
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$reserveStartGap$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("n shouldn't be negative: ");
                outline107.append(i);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    public final void resetForRead() {
        this.readBuffer.position(0);
        int limit = this.writeBuffer.limit();
        this.writeBuffer.position(limit);
        this.readBuffer.limit(limit);
    }

    public final void resetForWrite() {
        resetForWrite(this.writeBuffer.capacity());
    }

    @ExperimentalIoApi
    public final void resetFromContentToWrite(@NotNull ByteBuffer child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        this.writeBuffer.limit(child.limit());
        this.writeBuffer.position(child.position());
    }

    public final void restoreEndGap$kotlinx_io(int i) {
        int limit = this.writeBuffer.limit() - i;
        this.writeBuffer.limit(limit);
        if (this.readBuffer.limit() > limit) {
            this.readBuffer.limit(limit);
        }
    }

    public final void restoreStartGap$kotlinx_io(int i) {
        int position = this.readBuffer.position();
        if (position >= i) {
            this.readBuffer.position(position - i);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Can't restore start gap: ", i, " bytes were not reserved before"));
    }

    public final void setAttachment(@Nullable Object obj) {
        this.attachment = obj;
    }

    @Override // kotlinx.io.core.Input
    public final void setByteOrder(@NotNull ByteOrder value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.readBuffer.order(value.getNioOrder());
        this.writeBuffer.order(value.getNioOrder());
    }

    public final void setNext(@Nullable IoBuffer ioBuffer) {
        this.next = ioBuffer;
    }

    public final void setWritePosition(int i) {
        this.writeBuffer.position(i);
        this.readBuffer.limit(i);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Buffer[readable = ");
        outline107.append(getReadRemaining());
        outline107.append(", writable = ");
        outline107.append(getWriteRemaining());
        outline107.append(", startGap = ");
        outline107.append(getStartGap());
        outline107.append(", endGap = ");
        outline107.append(getEndGap());
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    @Override // kotlinx.io.core.Input
    public final int tryPeek() {
        ByteBuffer byteBuffer = this.readBuffer;
        if (byteBuffer.hasRemaining()) {
            return byteBuffer.get(byteBuffer.position()) & 255;
        }
        return -1;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use writeFully instead", replaceWith = @ReplaceWith(expression = "writeFully(array, offset, length)", imports = {}))
    public final void write(@NotNull byte[] array, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        writeFully(array, i, i2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use writeFully instead", replaceWith = @ReplaceWith(expression = "writeFully(src, length)", imports = {}))
    public final int writeBuffer(@NotNull IoBuffer src, int i) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        writeFully(src, i);
        return i;
    }

    public final void writeBufferAppend$kotlinx_io(@NotNull IoBuffer other, int i) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        int remaining = this.writeBuffer.remaining();
        int min = Math.min(i, other.getReadRemaining());
        if (remaining < min) {
            int i2 = min - remaining;
            if (i2 <= getEndGap()) {
                ByteBuffer byteBuffer = this.writeBuffer;
                byteBuffer.limit(byteBuffer.limit() + i2);
            } else {
                throw new IllegalArgumentException("Can't append buffer: not enough free space at the end");
            }
        }
        writeFully(other, min);
    }

    public final void writeBufferPrepend$kotlinx_io(@NotNull IoBuffer other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        int readRemaining = other.getReadRemaining();
        int position = this.readBuffer.position();
        if (readRemaining <= position) {
            ByteBuffer byteBuffer = this.writeBuffer;
            int position2 = byteBuffer.position();
            int limit = byteBuffer.limit();
            byteBuffer.limit(position);
            int i = position - readRemaining;
            byteBuffer.position(i);
            byteBuffer.put(other.readBuffer);
            byteBuffer.limit(limit);
            this.readBuffer.position(i);
            this.writeBuffer.position(position2);
            this.readBuffer.limit(position2);
            return;
        }
        throw new IllegalArgumentException("Can't prepend buffer: not enough free space at the beginning");
    }

    @Override // kotlinx.io.core.Output
    public final void writeByte(byte b) {
        this.writeBuffer.put(b);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    public final int writeDirect(final int i, @NotNull Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        final int writeRemaining = getWriteRemaining();
        if (i <= writeRemaining) {
            ByteBuffer byteBuffer = this.writeBuffer;
            int position = byteBuffer.position();
            block.mo12165invoke(byteBuffer);
            int position2 = byteBuffer.position() - position;
            if (position2 >= 0 && position2 <= writeRemaining) {
                this.readBuffer.limit(this.writeBuffer.position());
                return position2;
            }
            ErrorsKt.wrongBufferPositionChangeError(position2, i);
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$writeDirect$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size ");
                outline107.append(i);
                outline107.append(" is greater than buffer's remaining capacity ");
                outline107.append(writeRemaining);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    @Override // kotlinx.io.core.Output
    public final void writeDouble(double d) {
        this.writeBuffer.putDouble(d);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Output
    public final void writeFloat(float f) {
        this.writeBuffer.putFloat(f);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull byte[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.writeBuffer.put(src, i, i2);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Output
    public final void writeInt(int i) {
        this.writeBuffer.putInt(i);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Output
    public final void writeLong(long j) {
        this.writeBuffer.putLong(j);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Output
    public final void writeShort(short s) {
        this.writeBuffer.putShort(s);
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use readFully instead", replaceWith = @ReplaceWith(expression = "readFully(dst, length)", imports = {}))
    public final void read(@NotNull ByteBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        readFully(dst, i);
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull IoBuffer dst, final int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        boolean z = true;
        if (!(i <= dst.getWriteRemaining())) {
            new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$readFully$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough space in the destination buffer to write "), i, " bytes"));
                }
            }.doFail();
            throw null;
        }
        if (i > readRemaining) {
            z = false;
        }
        if (z) {
            readFully(dst.writeBuffer, i);
            dst.readBuffer.limit(dst.writeBuffer.position());
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$readFully$$inlined$require$2
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough bytes available to read "), i, " bytes"));
            }
        }.doFail();
        throw null;
    }

    public final void resetForWrite(int i) {
        this.writeBuffer.limit(i);
        this.readBuffer.position(0);
        this.writeBuffer.position(0);
        this.readBuffer.limit(0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use writeFully instead", replaceWith = @ReplaceWith(expression = "writeFully(buffer)", imports = {}))
    public final void write(@NotNull ByteBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        writeFully(buffer);
    }

    @Override // java.lang.Appendable
    @NotNull
    public final Appendable append(@Nullable CharSequence charSequence) {
        return charSequence == null ? append("null") : append(charSequence, 0, charSequence.length());
    }

    @Override // kotlinx.io.core.Output
    @NotNull
    public final Appendable append(@NotNull char[] csq, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        if (appendChars(csq, i, i2) == i2) {
            return this;
        }
        throw new IllegalStateException("Not enough free space to append char sequence");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull IoBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        int min = Math.min(i, readRemaining);
        if (readRemaining == 0) {
            return -1;
        }
        readFully(dst.writeBuffer, min);
        dst.readBuffer.limit(dst.writeBuffer.position());
        return min;
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull short[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteBuffer byteBuffer = this.writeBuffer;
        int i3 = i2 + i;
        while (i < i3) {
            byteBuffer.putShort(src[i]);
            i++;
        }
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // java.lang.Appendable
    @NotNull
    public Appendable append(char c) {
        ByteBuffer byteBuffer = this.writeBuffer;
        boolean z = false;
        if (1 <= c && 127 >= c) {
            if (byteBuffer.remaining() >= 1) {
                byteBuffer.put((byte) c);
                z = true;
            }
        } else if (c > 65535) {
            if (byteBuffer.remaining() >= 4) {
                byteBuffer.put((byte) (((c >> 18) & 63) | 240));
                byteBuffer.put((byte) (((c >> '\f') & 63) | 128));
                byteBuffer.put((byte) (((c >> 6) & 63) | 128));
                byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
                z = true;
            }
        } else if (c > 2047) {
            if (byteBuffer.remaining() >= 3) {
                byteBuffer.put((byte) (((c >> '\f') & 15) | 224));
                byteBuffer.put((byte) (((c >> 6) & 63) | 128));
                byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
                z = true;
            }
        } else if (byteBuffer.remaining() >= 2) {
            byteBuffer.put((byte) (((c >> 6) & 31) | 192));
            byteBuffer.put((byte) ((c & Constants.DEFAULT_IMAGE_CHAR) | 128));
            z = true;
        }
        if (z) {
            this.readBuffer.limit(this.writeBuffer.position());
            return this;
        }
        notEnoughFreeSpace(c);
        throw null;
    }

    public /* synthetic */ IoBuffer(ByteBuffer byteBuffer, IoBuffer ioBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, ioBuffer);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IoBuffer(@NotNull ByteBuffer external) {
        this(external, null);
        Intrinsics.checkParameterIsNotNull(external, "external");
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining >> 1, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull int[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteBuffer byteBuffer = this.writeBuffer;
        int i3 = i2 + i;
        while (i < i3) {
            byteBuffer.putInt(src[i]);
            i++;
        }
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.asShortBuffer().get(dst, i, i2);
        this.readBuffer.position(this.readBuffer.position() + (i2 << 1));
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining >> 2, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.asIntBuffer().get(dst, i, i2);
        this.readBuffer.position(this.readBuffer.position() + (i2 << 2));
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull long[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteBuffer byteBuffer = this.writeBuffer;
        int i3 = i2 + i;
        while (i < i3) {
            byteBuffer.putLong(src[i]);
            i++;
        }
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining >> 3, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.asLongBuffer().get(dst, i, i2);
        this.readBuffer.position(this.readBuffer.position() + (i2 << 3));
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull float[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining >> 2, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull float[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteBuffer byteBuffer = this.writeBuffer;
        int i3 = i2 + i;
        while (i < i3) {
            byteBuffer.putFloat(src[i]);
            i++;
        }
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull double[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.asDoubleBuffer().get(dst, i, i2);
        this.readBuffer.position(this.readBuffer.position() + (i2 << 3));
    }

    public final int appendChars(@NotNull CharSequence csq, int i, int i2) {
        int appendASCII_buffer;
        Intrinsics.checkParameterIsNotNull(csq, "csq");
        ByteBuffer byteBuffer = this.writeBuffer;
        if (!byteBuffer.hasRemaining()) {
            return i;
        }
        if (byteBuffer.hasArray()) {
            appendASCII_buffer = appendASCII_array(byteBuffer, csq, i, i2);
        } else {
            appendASCII_buffer = appendASCII_buffer(byteBuffer, csq, i, i2);
        }
        if (byteBuffer.hasRemaining() && appendASCII_buffer != i2) {
            return appendUTF8(byteBuffer, csq, appendASCII_buffer, i2);
        }
        this.readBuffer.limit(this.writeBuffer.position());
        return appendASCII_buffer;
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull double[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        if (readRemaining == 0) {
            return -1;
        }
        int min = Math.min(readRemaining >> 3, i2);
        readFully(dst, i, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull float[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        this.readBuffer.asFloatBuffer().get(dst, i, i2);
        this.readBuffer.position(this.readBuffer.position() + (i2 << 2));
    }

    @Override // kotlinx.io.core.Input
    public final int readAvailable(@NotNull ByteBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int readRemaining = getReadRemaining();
        int min = Math.min(readRemaining, i);
        if (readRemaining == 0) {
            return -1;
        }
        readFully(dst, min);
        return min;
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull double[] src, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteBuffer byteBuffer = this.writeBuffer;
        int i3 = i2 + i;
        while (i < i3) {
            byteBuffer.putDouble(src[i]);
            i++;
        }
        this.readBuffer.limit(this.writeBuffer.position());
    }

    @Override // kotlinx.io.core.Input
    public final void readFully(@NotNull ByteBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        ByteBuffer byteBuffer = this.readBuffer;
        int remaining = byteBuffer.remaining();
        if (i == remaining) {
            dst.put(byteBuffer);
        } else if (i <= remaining) {
            int limit = byteBuffer.limit();
            byteBuffer.limit(byteBuffer.position() + i);
            dst.put(byteBuffer);
            byteBuffer.limit(limit);
        } else {
            throw new BufferUnderflowException();
        }
    }

    @Override // kotlinx.io.core.Output
    public final void writeFully(@NotNull final IoBuffer src, final int i) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        boolean z = true;
        if (i >= 0) {
            if (i <= src.getReadRemaining()) {
                if (i > getWriteRemaining()) {
                    z = false;
                }
                if (z) {
                    if (i == src.getReadRemaining()) {
                        this.writeBuffer.put(src.readBuffer);
                    } else {
                        ByteBuffer byteBuffer = src.readBuffer;
                        int limit = byteBuffer.limit();
                        byteBuffer.limit(byteBuffer.position() + i);
                        this.writeBuffer.put(byteBuffer);
                        byteBuffer.limit(limit);
                    }
                    this.readBuffer.limit(this.writeBuffer.position());
                    return;
                }
                new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$writeFully$$inlined$require$3
                    @Override // kotlinx.io.core.internal.RequireFailureCapture
                    @NotNull
                    public Void doFail() {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Not enough space to write "), i, " bytes"));
                    }
                }.doFail();
                throw null;
            }
            new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$writeFully$$inlined$require$2
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length is bigger than src buffer size: ");
                    outline107.append(i);
                    outline107.append(" > ");
                    outline107.append(src.getReadRemaining());
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$writeFully$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length shouldn't be negative: ");
                outline107.append(i);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    @Override // kotlinx.io.core.Output
    public void writeFully(@NotNull final ByteBuffer bb) {
        Intrinsics.checkParameterIsNotNull(bb, "bb");
        if (bb.remaining() <= getWriteRemaining()) {
            this.writeBuffer.put(bb);
            this.readBuffer.limit(this.writeBuffer.position());
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.IoBuffer$writeFully$$inlined$require$4
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not enough space to write ");
                outline107.append(bb.remaining());
                outline107.append(" bytes");
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }
}
