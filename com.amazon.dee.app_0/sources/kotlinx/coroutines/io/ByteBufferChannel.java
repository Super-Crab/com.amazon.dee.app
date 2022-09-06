package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.react.uimanager.ViewProps;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.io.internal.CancellableReusableContinuation;
import kotlinx.coroutines.io.internal.ObjectPoolKt;
import kotlinx.coroutines.io.internal.ReadWriteBufferState;
import kotlinx.coroutines.io.internal.ReadWriteBufferStateKt;
import kotlinx.coroutines.io.internal.RingBufferCapacity;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.PacketJVMKt;
import kotlinx.io.core.internal.UnsafeKt;
import kotlinx.io.pool.ObjectPool;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0000\u0018\u0000 ·\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\b¶\u0002·\u0002¸\u0002¹\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B'\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0018\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020<2\u0006\u0010H\u001a\u00020\u0011H\u0016J\u0019\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0019\u0010L\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0011\u0010M\u001a\u00020<H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010O\u001a\u00020\t2\b\u0010P\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010Q\u001a\u00020\t2\b\u0010P\u001a\u0004\u0018\u00010\u001cH\u0016JI\u0010R\u001a\u00020<26\u0010S\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\t¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0004\u0012\u00020\t0TH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010XJI\u0010Y\u001a\u00020\t2\u0006\u0010W\u001a\u00020\t26\u0010S\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\t¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0004\u0012\u00020\t0TH\u0082\bJI\u0010Z\u001a\u00020<26\u0010S\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\t¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0004\u0012\u00020\t0TH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010XJ\u0010\u0010[\u001a\u00020<2\u0006\u0010J\u001a\u00020\u000eH\u0016J-\u0010\\\u001a\u0002012\u0006\u0010]\u001a\u00020\u00002\u0006\u0010^\u001a\u0002012\b\u0010_\u001a\u0004\u0018\u00010\"H\u0080@ø\u0001\u0000¢\u0006\u0004\b`\u0010aJ\u0019\u0010b\u001a\u00020<2\u0006\u0010c\u001a\u00020dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010eJ\u0019\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0019\u0010h\u001a\u00020<2\u0006\u0010i\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010jJ\u0019\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020mH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010nJB\u0010o\u001a\u00020<2\u0006\u0010_\u001a\u00020\"2'\u0010p\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0*\u0012\u0006\u0012\u0004\u0018\u00010A0T¢\u0006\u0002\bqH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010rJ\u0019\u0010s\u001a\u0002012\u0006\u0010t\u001a\u000201H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010jJ!\u0010u\u001a\u0002012\u0006\u0010v\u001a\u0002012\u0006\u0010t\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010wJ \u0010x\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010c\u001a\u00020d2\u0006\u0010E\u001a\u00020FH\u0002J \u0010x\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010l\u001a\u00020m2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010y\u001a\u00020<2\u0006\u0010_\u001a\u00020\"H\u0002J\b\u0010z\u001a\u00020<H\u0016J\u0018\u0010{\u001a\u00020<2\u0006\u0010|\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\u000eH\u0002J%\u0010~\u001a\u00020<2\u0006\u0010]\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020\tH\u0080@ø\u0001\u0000¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J+\u0010\u0082\u0001\u001a\u00020<2\u0006\u0010]\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020\t2\u0006\u0010_\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0083\u0001J2\u0010\u0084\u0001\u001a\u0003H\u0085\u0001\"\u0005\b\u0000\u0010\u0085\u00012\u0019\u0010S\u001a\u0015\u0012\u0005\u0012\u00030\u0086\u0001\u0012\u0005\u0012\u0003H\u0085\u00010@¢\u0006\u0002\bqH\u0016¢\u0006\u0003\u0010\u0087\u0001JD\u0010\u0088\u0001\u001a\u0003H\u0085\u0001\"\u0005\b\u0000\u0010\u0085\u00012(\u0010S\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u000b\u0012\t\u0012\u0005\u0012\u0003H\u0085\u00010*\u0012\u0006\u0012\u0004\u0018\u00010A0T¢\u0006\u0002\bqH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010XJ\t\u0010\u0089\u0001\u001a\u00020\fH\u0002J1\u0010\u008a\u0001\u001a\u00020<2\u0007\u0010\u008b\u0001\u001a\u00020\u000e2\u0013\u0010\u008c\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020<0@H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001J\u0012\u0010\u008e\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u0006H\u0002J%\u0010\u008e\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0002J)\u0010\u008e\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0093\u00012\t\b\u0002\u0010\u0094\u0001\u001a\u00020\u000e2\b\b\u0002\u0010t\u001a\u00020\u000eH\u0082\u0010J\u001c\u0010\u0095\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J/\u0010\u0095\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001d\u0010\u0095\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0093\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u001c\u0010\u0099\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J/\u0010\u0099\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001d\u0010\u0099\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0093\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J0\u0010\u009a\u0001\u001a\u00020<2\u0007\u0010\u008b\u0001\u001a\u00020\u000e2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020<0@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001J\u0012\u0010\u009b\u0001\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010\u009c\u0001\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010\u009d\u0001\u001a\u00020dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010\u009e\u0001\u001a\u00020dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0013\u0010\u009f\u0001\u001a\u00030 \u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0013\u0010¡\u0001\u001a\u00030 \u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0013\u0010¢\u0001\u001a\u00030£\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0013\u0010¤\u0001\u001a\u00030£\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u001c\u0010¥\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J/\u0010¥\u0001\u001a\u00020<2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J%\u0010¥\u0001\u001a\u00020<2\b\u0010\u008f\u0001\u001a\u00030\u0093\u00012\u0006\u0010J\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010¦\u0001J%\u0010§\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u00062\u0007\u0010¨\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010©\u0001J/\u0010§\u0001\u001a\u00020<2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0082Pø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J%\u0010§\u0001\u001a\u00020<2\b\u0010\u008f\u0001\u001a\u00030\u0093\u00012\u0006\u0010J\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¦\u0001J\u0012\u0010ª\u0001\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010«\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010¬\u0001\u001a\u000201H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010\u00ad\u0001\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ&\u0010®\u0001\u001a\u00030¯\u00012\u0007\u0010°\u0001\u001a\u00020\u000e2\u0007\u0010±\u0001\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010²\u0001J/\u0010³\u0001\u001a\u00030¯\u00012\u0007\u0010°\u0001\u001a\u00020\u000e2\b\u0010´\u0001\u001a\u00030µ\u00012\u0006\u0010D\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¶\u0001J%\u0010·\u0001\u001a\u00030¯\u00012\u0006\u0010^\u001a\u0002012\u0007\u0010±\u0001\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010¸\u0001J%\u0010¹\u0001\u001a\u00030¯\u00012\u0006\u0010^\u001a\u0002012\u0007\u0010±\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¸\u0001J$\u0010º\u0001\u001a\u00020<2\u0019\u0010\u008c\u0001\u001a\u0014\u0012\u0005\u0012\u00030»\u0001\u0012\u0004\u0012\u00020<0@¢\u0006\u0002\bqH\u0017J\u0012\u0010¼\u0001\u001a\u00020mH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0012\u0010½\u0001\u001a\u00020mH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u001b\u0010¾\u0001\u001a\u00020\t2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010¿\u0001\u001a\u00020\t2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010À\u0001\u001a\u00020\t2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082Pø\u0001\u0000¢\u0006\u0002\u0010KJ\u0013\u0010Á\u0001\u001a\u00020\t2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082\bJ=\u0010Â\u0001\u001a\u00020<2)\u0010\u008c\u0001\u001a$\b\u0001\u0012\u0005\u0012\u00030Ã\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0*\u0012\u0006\u0012\u0004\u0018\u00010A0T¢\u0006\u0002\bqH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010XJ\u001d\u0010Ä\u0001\u001a\u0005\u0018\u00010Å\u00012\u0006\u0010^\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010KJ6\u0010Æ\u0001\u001a\u00020\t\"\u000f\b\u0000\u0010Ç\u0001*\b0È\u0001j\u0003`É\u00012\b\u0010Ê\u0001\u001a\u0003HÇ\u00012\u0006\u0010^\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010Ë\u0001J*\u0010Ì\u0001\u001a\u00020\t2\r\u0010Ê\u0001\u001a\b0È\u0001j\u0003`É\u00012\u0006\u0010^\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010Ë\u0001JG\u0010Í\u0001\u001a\u00020\t2\r\u0010Ê\u0001\u001a\b0È\u0001j\u0003`É\u00012\u0006\u0010^\u001a\u00020\u000e2\b\u0010Î\u0001\u001a\u00030Ï\u00012\b\u0010Ð\u0001\u001a\u00030Ñ\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010Ò\u0001J)\u0010Ó\u0001\u001a\u00020\t2\u001d\u0010p\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020\t0T¢\u0006\u0002\bqH\u0082\bJ\u0011\u0010Ô\u0001\u001a\u00020<2\u0006\u0010D\u001a\u00020\fH\u0002J\u001d\u0010Õ\u0001\u001a\u0004\u0018\u00010\u00062\u0007\u0010Ö\u0001\u001a\u00020\u000e2\u0007\u0010×\u0001\u001a\u00020\u000eH\u0016J\u001d\u0010Ø\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010Ù\u0001\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"H\u0082\u0010J\t\u0010Ú\u0001\u001a\u00020<H\u0002J\t\u0010Û\u0001\u001a\u00020<H\u0002J\u0013\u0010Ü\u0001\u001a\u00020<2\b\u0010P\u001a\u0004\u0018\u00010\u001cH\u0002J\t\u0010Ý\u0001\u001a\u00020<H\u0002J\u001a\u0010Ý\u0001\u001a\u00020<2\u000e\u0010Þ\u0001\u001a\t\u0012\u0004\u0012\u00020\u001c0ß\u0001H\u0082\bJ\t\u0010à\u0001\u001a\u00020<H\u0002Jo\u0010á\u0001\u001a\u00020\t\"\u0005\b\u0000\u0010â\u0001\"\u0010\b\u0001\u0010ã\u0001*\t\u0012\u0005\u0012\u0003Hâ\u00010*2\u0011\u0010ä\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u0001Hã\u00010ß\u00012\u0017\u0010å\u0001\u001a\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0007\u0012\u0005\u0018\u0001Hã\u00010æ\u00012\b\u0010ç\u0001\u001a\u0003Hã\u00012\u000e\u0010è\u0001\u001a\t\u0012\u0004\u0012\u00020\t0ß\u0001H\u0082\b¢\u0006\u0003\u0010é\u0001J\u001a\u0010ê\u0001\u001a\u00020\"2\u0007\u0010ë\u0001\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020\tH\u0002J\u000b\u0010ì\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\u000b\u0010í\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\t\u0010î\u0001\u001a\u00020\tH\u0002J \u0010ï\u0001\u001a\u00020A2\u0007\u0010°\u0001\u001a\u00020\u000e2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\t0*H\u0002J\u0011\u0010ð\u0001\u001a\u00020\t2\u0006\u0010_\u001a\u00020\"H\u0002J\u0012\u0010ñ\u0001\u001a\u00020\t2\u0007\u0010ò\u0001\u001a\u00020\tH\u0002J\t\u0010ó\u0001\u001a\u00020\tH\u0002J+\u0010ô\u0001\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010c\u001a\u00020d2\u0006\u0010E\u001a\u00020FH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010õ\u0001J\u0013\u0010ö\u0001\u001a\u00020\u000e2\b\u0010÷\u0001\u001a\u00030¯\u0001H\u0002J+\u0010ø\u0001\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010l\u001a\u00020m2\u0006\u0010E\u001a\u00020FH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010ù\u0001J\u001b\u0010ú\u0001\u001a\u00020<2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJt\u0010û\u0001\u001a\u0011\u0012\u0005\u0012\u0003Hâ\u0001\u0012\u0005\u0012\u0003Hâ\u00010ü\u0001\"\t\b\u0000\u0010â\u0001*\u00020A2\u000f\u0010ä\u0001\u001a\n\u0012\u0005\u0012\u0003Hâ\u00010ß\u00012\u0015\u0010å\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0005\u0012\u0003Hâ\u00010æ\u00012&\u0010p\u001a\"\u0012\u0015\u0012\u0013Hâ\u0001¢\u0006\r\bU\u0012\t\bV\u0012\u0005\b\b(ý\u0001\u0012\u0007\u0012\u0005\u0018\u0001Hâ\u00010@H\u0082\bJ-\u0010þ\u0001\u001a\u000f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020/0ü\u00012\u0014\u0010p\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0006\u0012\u0004\u0018\u00010/0@H\u0082\bJ \u0010ÿ\u0001\u001a\u00020/2\u0014\u0010p\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0006\u0012\u0004\u0018\u00010/0@H\u0082\bJ0\u0010\u0080\u0002\u001a\u00020<2\u0007\u0010\u008b\u0001\u001a\u00020\u000e2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020<0@H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001J\u0011\u0010\u0081\u0002\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u0006H\u0002J$\u0010\u0081\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0002J\u0012\u0010\u0081\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0093\u0001H\u0002J\u001b\u0010\u0082\u0002\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J.\u0010\u0082\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001c\u0010\u0082\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0093\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u001b\u0010\u0083\u0002\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J\u001c\u0010\u0083\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0093\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J0\u0010\u0084\u0002\u001a\u00020<2\u0007\u0010\u008b\u0001\u001a\u00020\u000e2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020<0@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001J\u001a\u0010\u0085\u0002\u001a\u00020<2\u0006\u0010c\u001a\u00020dH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010eJ+\u0010\u0086\u0002\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010c\u001a\u00020d2\u0006\u0010E\u001a\u00020FH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010õ\u0001J\u001d\u0010\u0087\u0002\u001a\u00020<2\b\u0010\u0088\u0002\u001a\u00030 \u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0089\u0002J\u001d\u0010\u008a\u0002\u001a\u00020<2\b\u0010\u008b\u0002\u001a\u00030£\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u008c\u0002J\u001b\u0010\u008d\u0002\u001a\u00020<2\u0006\u0010]\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J.\u0010\u008d\u0002\u001a\u00020<2\u0007\u0010]\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001c\u0010\u008d\u0002\u001a\u00020<2\u0007\u0010]\u001a\u00030\u0093\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u001b\u0010\u008e\u0002\u001a\u00020<2\u0006\u0010]\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0096\u0001J.\u0010\u008e\u0002\u001a\u00020<2\u0007\u0010]\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0082Pø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001c\u0010\u008e\u0002\u001a\u00020<2\u0007\u0010]\u001a\u00030\u0093\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0098\u0001J\u001a\u0010\u008f\u0002\u001a\u00020<2\u0006\u0010g\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001a\u0010\u0090\u0002\u001a\u00020<2\u0006\u0010i\u001a\u000201H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010jJ\u001d\u0010\u0091\u0002\u001a\u00020<2\b\u0010÷\u0001\u001a\u00030¯\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u0092\u0002J\u001d\u0010\u0093\u0002\u001a\u00020<2\b\u0010÷\u0001\u001a\u00030¯\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0092\u0002J\u001a\u0010\u0094\u0002\u001a\u00020<2\u0006\u0010l\u001a\u00020mH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010nJ+\u0010\u0095\u0002\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010l\u001a\u00020m2\u0006\u0010E\u001a\u00020FH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010ù\u0001J.\u0010\u0096\u0002\u001a\u00020\u000e2\u0007\u0010]\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u000e2\u0007\u0010\u0092\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u0097\u0001J\u001b\u0010\u0096\u0002\u001a\u00020<2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0012\u0010\u0097\u0002\u001a\u00020\t2\u0007\u0010°\u0001\u001a\u00020\u000eH\u0002J<\u0010\u0098\u0002\u001a\u00020<2(\u0010S\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0099\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0*\u0012\u0006\u0012\u0004\u0018\u00010A0T¢\u0006\u0002\bqH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010XJ'\u0010\u009a\u0002\u001a\u00020<2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0@H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0002J/\u0010\u009c\u0002\u001a\u00020\t2\u0007\u0010\u008f\u0001\u001a\u00020\u00062\u0007\u0010\u009d\u0002\u001a\u00020F2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0@H\u0002J\u001d\u0010\u009e\u0002\u001a\u00020\t2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0@H\u0002J'\u0010\u009f\u0002\u001a\u00020<2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0@H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0002J0\u0010 \u0002\u001a\u00020<2$\u0010p\u001a \u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020<0¡\u0002¢\u0006\u0002\bqH\u0082\bJ\u001d\u0010¢\u0002\u001a\u00020<*\u00020\u00062\u0006\u0010E\u001a\u00020F2\u0006\u0010J\u001a\u00020\u000eH\u0002J\u001d\u0010£\u0002\u001a\u00020<*\u00020\u00062\u0006\u0010E\u001a\u00020F2\u0006\u0010J\u001a\u00020\u000eH\u0002J\r\u0010¤\u0002\u001a\u00020<*\u00020\u0006H\u0002J\u0016\u0010¥\u0002\u001a\u00020\u000e*\u00020\u00062\u0007\u0010¦\u0002\u001a\u00020\u000eH\u0002J(\u0010§\u0002\u001a\u00020<*\u00020\u00062\u0007\u0010¨\u0002\u001a\u00020$2\u0007\u0010©\u0002\u001a\u00020\u000e2\u0007\u0010ª\u0002\u001a\u00020\u000eH\u0002Jq\u0010«\u0002\u001a\u00020\t*\u00030\u0086\u00012\r\u0010Ê\u0001\u001a\b0È\u0001j\u0003`É\u00012\b\u0010Î\u0001\u001a\u00030Ï\u00012\b\u0010Ð\u0001\u001a\u00030Ñ\u00012\u0013\u0010¬\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\t0@2\u0013\u0010\u00ad\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020<0@2\u0013\u0010®\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002010@H\u0082\bJ\u0015\u0010¯\u0002\u001a\u00020<*\u00020\u00062\u0006\u0010J\u001a\u00020\u000eH\u0002J\u001d\u0010°\u0002\u001a\u00020\t*\u00020\u00062\u0006\u0010g\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020FH\u0002J\u001d\u0010±\u0002\u001a\u00020\t*\u00020\u00062\u0006\u0010i\u001a\u0002012\u0006\u0010E\u001a\u00020FH\u0002J'\u0010²\u0002\u001a\u00020<*\u00020\u00062\u0006\u0010g\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020FH\u0082Pø\u0001\u0000¢\u0006\u0003\u0010³\u0002J'\u0010´\u0002\u001a\u00020<*\u00020\u00062\u0006\u0010i\u001a\u0002012\u0006\u0010E\u001a\u00020FH\u0082Pø\u0001\u0000¢\u0006\u0003\u0010µ\u0002R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0014\u0010 \u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0016\u0010)\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\t0-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0002012\u0006\u00100\u001a\u000201@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001e\u00105\u001a\u0002012\u0006\u00100\u001a\u000201@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00104R$\u00108\u001a\u00020$2\u0006\u00107\u001a\u00020$@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010&\"\u0004\b:\u0010(R\u0016\u0010;\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020<0-X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010?\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0*\u0012\u0004\u0012\u00020A0@X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006º\u0002"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel;", "Lkotlinx/coroutines/io/ByteChannel;", "Lkotlinx/coroutines/io/ByteReadChannel;", "Lkotlinx/coroutines/io/ByteWriteChannel;", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "content", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "autoFlush", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState$Initial;", "reservedSize", "", "(ZLkotlinx/io/pool/ObjectPool;I)V", "attachedJob", "Lkotlinx/coroutines/Job;", "getAutoFlush", "()Z", "availableForRead", "getAvailableForRead", "()I", "availableForWrite", "getAvailableForWrite", "closed", "Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;", "closedCause", "", "getClosedCause", "()Ljava/lang/Throwable;", "isClosedForRead", "isClosedForWrite", "joining", "Lkotlinx/coroutines/io/ByteBufferChannel$JoiningState;", "readByteOrder", "Lkotlinx/io/core/ByteOrder;", "getReadByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setReadByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "readOp", "Lkotlin/coroutines/Continuation;", "readPosition", "readSuspendContinuationCache", "Lkotlinx/coroutines/io/internal/CancellableReusableContinuation;", "state", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "<set-?>", "", "totalBytesRead", "getTotalBytesRead", "()J", "totalBytesWritten", "getTotalBytesWritten", "newOrder", "writeByteOrder", "getWriteByteOrder", "setWriteByteOrder", "writeOp", "", "writePosition", "writeSuspendContinuationCache", "writeSuspension", "Lkotlin/Function1;", "", "writeSuspensionSize", "afterBufferVisited", "buffer", "c", "Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "attachJob", "job", "awaitAtLeast", JsonReportFormat.COUNT, "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAtLeastSuspend", "awaitClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", DeviceStateModule.CANCEL, "cause", "close", "consumeEachBufferRange", "visitor", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "last", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEachBufferRangeFast", "consumeEachBufferRangeSuspend", "consumed", "copyDirect", "src", MetricsUtil.LegacyMetricTypes.LIMIT, "joined", "copyDirect$kotlinx_coroutines_io", "(Lkotlinx/coroutines/io/ByteBufferChannel;JLkotlinx/coroutines/io/ByteBufferChannel$JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateByte", "b", "", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateInt", ContextChain.TAG_INFRA, "delegateLong", "l", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateShort", "s", "", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateSuspend", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/io/ByteBufferChannel$JoiningState;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discard", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "discardSuspend", "discarded0", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doWrite", "ensureClosedJoined", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "flushImpl", "minReadSize", "minWriteSize", "joinFrom", "delegateClose", "joinFrom$kotlinx_coroutines_io", "(Lkotlinx/coroutines/io/ByteBufferChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinFromSuspend", "(Lkotlinx/coroutines/io/ByteBufferChannel;ZLkotlinx/coroutines/io/ByteBufferChannel$JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookAhead", "R", "Lkotlinx/coroutines/io/LookAheadSession;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "lookAheadSuspend", "newBuffer", "read", ReactProperties.GEOFENCE_MINIMUM_RANGE, "consumer", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAsMuchAsPossible", "dst", "", "offset", "length", "Lkotlinx/io/core/IoBuffer;", "consumed0", "readAvailable", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/io/core/IoBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailableSuspend", "readBlockSuspend", "readBoolean", "readBooleanSuspend", "readByte", "readByteSuspend", "readDouble", "", "readDoubleSuspend", "readFloat", "", "readFloatSuspend", "readFully", "(Lkotlinx/io/core/IoBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFullySuspend", "rc0", "(Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readInt", "readIntSuspend", "readLong", "readLongSuspend", "readPacket", "Lkotlinx/io/core/ByteReadPacket;", "size", "headerSizeHint", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPacketSuspend", "builder", "Lkotlinx/io/core/BytePacketBuilder;", "(ILkotlinx/io/core/BytePacketBuilder;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemaining", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemainingSuspend", "readSession", "Lkotlinx/coroutines/io/ReadSession;", "readShort", "readShortSuspend", "readSuspend", "readSuspendImpl", "readSuspendLoop", "readSuspendPredicate", "readSuspendableSession", "Lkotlinx/coroutines/io/SuspendableReadSession;", "readUTF8Line", "", "readUTF8LineTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUTF8LineToAscii", "readUTF8LineToUtf8Suspend", "ca", "", "cb", "Ljava/nio/CharBuffer;", "(Ljava/lang/Appendable;I[CLjava/nio/CharBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reading", "releaseBuffer", "request", "skip", "atLeast", "resolveDelegation", "current", "restoreStateAfterRead", "restoreStateAfterWrite", "resumeClosed", "resumeReadOp", ADMRegistrationConstants.CALL_EXCEPTION, "Lkotlin/Function0;", "resumeWriteOp", "setContinuation", ExifInterface.GPS_DIRECTION_TRUE, "C", "getter", "updater", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "continuation", "predicate", "(Lkotlin/jvm/functions/Function0;Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function0;)Z", "setupDelegateTo", "delegate", "setupStateForRead", "setupStateForWrite", "shouldResumeReadOp", "suspensionForSize", "tryCompleteJoining", "tryReleaseBuffer", "forceTermination", "tryTerminate", "tryWriteByte", "(Ljava/nio/ByteBuffer;BLkotlinx/coroutines/io/internal/RingBufferCapacity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryWritePacketPart", "packet", "tryWriteShort", "(Ljava/nio/ByteBuffer;SLkotlinx/coroutines/io/internal/RingBufferCapacity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryWriteSuspend", "update", "Lkotlin/Pair;", "old", "updateState", "updateStateAndGet", "write", "writeAsMuchAsPossible", "writeAvailable", "writeAvailableSuspend", "writeBlockSuspend", "writeByte", "writeByteSuspend", "writeDouble", "d", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFloat", "f", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "writeFullySuspend", "writeInt", "writeLong", "writePacket", "(Lkotlinx/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacketSuspend", "writeShort", "writeShortSuspend", "writeSuspend", "writeSuspendPredicate", "writeSuspendSession", "Lkotlinx/coroutines/io/WriterSuspendSession;", "writeWhile", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeWhileLoop", "capacity", "writeWhileNoSuspend", "writeWhileSuspend", "writing", "Lkotlin/Function3;", "bytesRead", "bytesWritten", "carry", "carryIndex", "idx", "prepareBuffer", "order", ViewProps.POSITION, "available", "readLineLoop", "await", "addConsumed", "decode", "rollBytes", "tryWriteInt", "tryWriteLong", "writeIntSuspend", "(Ljava/nio/ByteBuffer;ILkotlinx/coroutines/io/internal/RingBufferCapacity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeLongSuspend", "(Ljava/nio/ByteBuffer;JLkotlinx/coroutines/io/internal/RingBufferCapacity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ClosedElement", "Companion", "JoiningState", "TerminatedLookAhead", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBufferChannel implements ByteChannel, ByteReadChannel, ByteWriteChannel, LookAheadSuspendSession {
    private static final AtomicReferenceFieldUpdater<ByteBufferChannel, ClosedElement> Closed;
    public static final Companion Companion = new Companion(null);
    private static final AtomicReferenceFieldUpdater<ByteBufferChannel, Continuation<Boolean>> ReadOp;
    private static final int ReservedLongIndex = -8;
    private static final AtomicReferenceFieldUpdater<ByteBufferChannel, ReadWriteBufferState> State;
    private static final AtomicReferenceFieldUpdater<ByteBufferChannel, Continuation<Unit>> WriteOp;
    private volatile Job attachedJob;
    private final boolean autoFlush;
    private volatile ClosedElement closed;
    private volatile JoiningState joining;
    private final ObjectPool<ReadWriteBufferState.Initial> pool;
    @NotNull
    private ByteOrder readByteOrder;
    private volatile Continuation<? super Boolean> readOp;
    private int readPosition;
    private final CancellableReusableContinuation<Boolean> readSuspendContinuationCache;
    private final int reservedSize;
    private volatile ReadWriteBufferState state;
    private volatile long totalBytesRead;
    private volatile long totalBytesWritten;
    @NotNull
    private ByteOrder writeByteOrder;
    private volatile Continuation<? super Unit> writeOp;
    private int writePosition;
    private final CancellableReusableContinuation<Unit> writeSuspendContinuationCache;
    private final Function1<Continuation<? super Unit>, Object> writeSuspension;
    private volatile int writeSuspensionSize;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ByteBufferChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;", "", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "sendException", "getSendException", "Companion", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class ClosedElement {
        public static final Companion Companion = new Companion(null);
        @NotNull
        private static final ClosedElement EmptyCause = new ClosedElement(null);
        @Nullable
        private final Throwable cause;

        /* compiled from: ByteBufferChannel.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement$Companion;", "", "()V", "EmptyCause", "Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;", "getEmptyCause", "()Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes4.dex */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final ClosedElement getEmptyCause() {
                return ClosedElement.EmptyCause;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public ClosedElement(@Nullable Throwable th) {
            this.cause = th;
        }

        @Nullable
        public final Throwable getCause() {
            return this.cause;
        }

        @NotNull
        public final Throwable getSendException() {
            Throwable th = this.cause;
            return th != null ? th : new ClosedWriteChannelException("The channel was closed");
        }
    }

    /* compiled from: ByteBufferChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel$Companion;", "", "()V", "Closed", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlinx/coroutines/io/ByteBufferChannel;", "Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;", "ReadOp", "Lkotlin/coroutines/Continuation;", "", "ReservedLongIndex", "", "State", "Lkotlinx/coroutines/io/internal/ReadWriteBufferState;", "WriteOp", "", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ByteBufferChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0013\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0014R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel$JoiningState;", "", "delegatedTo", "Lkotlinx/coroutines/io/ByteBufferChannel;", "delegateClose", "", "(Lkotlinx/coroutines/io/ByteBufferChannel;Z)V", "_closeWaitJob", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/Job;", "closeWaitJob", "getCloseWaitJob", "()Lkotlinx/coroutines/Job;", "closed", "Lkotlinx/atomicfu/AtomicInt;", "getDelegateClose", "()Z", "getDelegatedTo", "()Lkotlinx/coroutines/io/ByteBufferChannel;", "awaitClose", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class JoiningState {
        private static final AtomicReferenceFieldUpdater _closeWaitJob$FU = AtomicReferenceFieldUpdater.newUpdater(JoiningState.class, Object.class, "_closeWaitJob");
        private static final AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(JoiningState.class, "closed");
        private volatile Object _closeWaitJob;
        private volatile int closed;
        private final boolean delegateClose;
        @NotNull
        private final ByteBufferChannel delegatedTo;

        public JoiningState(@NotNull ByteBufferChannel delegatedTo, boolean z) {
            Intrinsics.checkParameterIsNotNull(delegatedTo, "delegatedTo");
            this.delegatedTo = delegatedTo;
            this.delegateClose = z;
            this._closeWaitJob = null;
            this.closed = 0;
        }

        private final Job getCloseWaitJob() {
            Job m12240Job$default;
            do {
                Job job = (Job) this._closeWaitJob;
                if (job != null) {
                    return job;
                }
                m12240Job$default = JobKt__JobKt.m12240Job$default((Job) null, 1, (Object) null);
            } while (!_closeWaitJob$FU.compareAndSet(this, null, m12240Job$default));
            if (this.closed == 1) {
                m12240Job$default.mo12309cancel();
            }
            return m12240Job$default;
        }

        @Nullable
        public final Object awaitClose(@NotNull Continuation<? super Unit> continuation) {
            return this.closed == 1 ? Unit.INSTANCE : getCloseWaitJob().join(continuation);
        }

        public final void complete() {
            this.closed = 1;
            Job job = (Job) _closeWaitJob$FU.getAndSet(this, null);
            if (job != null) {
                job.mo12309cancel();
            }
        }

        public final boolean getDelegateClose() {
            return this.delegateClose;
        }

        @NotNull
        public final ByteBufferChannel getDelegatedTo() {
            return this.delegatedTo;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ByteBufferChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/io/ByteBufferChannel$TerminatedLookAhead;", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "()V", "awaitAtLeast", "", JsonReportFormat.COUNT, "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumed", "", "request", "Ljava/nio/ByteBuffer;", "skip", "atLeast", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class TerminatedLookAhead implements LookAheadSuspendSession {
        public static final TerminatedLookAhead INSTANCE = new TerminatedLookAhead();

        private TerminatedLookAhead() {
        }

        @Override // kotlinx.coroutines.io.LookAheadSuspendSession
        @Nullable
        public Object awaitAtLeast(int i, @NotNull Continuation<? super Boolean> continuation) {
            boolean z = true;
            if (i >= 0) {
                if (i > 4088) {
                    z = false;
                }
                if (z) {
                    return Boxing.boxBoolean(false);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be larger than max buffer size of 4088: ", i).toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be negative: ", i).toString());
        }

        @Override // kotlinx.coroutines.io.LookAheadSession
        public void consumed(int i) {
            if (i <= 0) {
                return;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline52("Unable to mark ", i, " bytes consumed for already terminated channel"));
        }

        @Override // kotlinx.coroutines.io.LookAheadSession
        @Nullable
        public ByteBuffer request(int i, int i2) {
            return null;
        }
    }

    static {
        AtomicReferenceFieldUpdater<ByteBufferChannel, ReadWriteBufferState> newUpdater = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, ReadWriteBufferState.class, ByteBufferChannel$Companion$State$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicReferenceFieldUpda…a, T::class.java, p.name)");
        State = newUpdater;
        AtomicReferenceFieldUpdater<ByteBufferChannel, Continuation<Unit>> newUpdater2 = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Continuation.class, ByteBufferChannel$Companion$WriteOp$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater2, "AtomicReferenceFieldUpda…a, T::class.java, p.name)");
        WriteOp = newUpdater2;
        AtomicReferenceFieldUpdater<ByteBufferChannel, Continuation<Boolean>> newUpdater3 = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Continuation.class, ByteBufferChannel$Companion$ReadOp$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater3, "AtomicReferenceFieldUpda…a, T::class.java, p.name)");
        ReadOp = newUpdater3;
        AtomicReferenceFieldUpdater<ByteBufferChannel, ClosedElement> newUpdater4 = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, ClosedElement.class, ByteBufferChannel$Companion$Closed$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater4, "AtomicReferenceFieldUpda…a, T::class.java, p.name)");
        Closed = newUpdater4;
    }

    public ByteBufferChannel(boolean z, @NotNull ObjectPool<ReadWriteBufferState.Initial> pool, int i) {
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.autoFlush = z;
        this.pool = pool;
        this.reservedSize = i;
        this.state = ReadWriteBufferState.IdleEmpty.INSTANCE;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this.readByteOrder = byteOrder;
        this.writeByteOrder = byteOrder;
        this.readSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspension = new ByteBufferChannel$writeSuspension$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int afterBufferVisited(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity) {
        int position = byteBuffer.position() - this.readPosition;
        if (position > 0) {
            if (ringBufferCapacity.tryReadExact(position)) {
                bytesRead(byteBuffer, ringBufferCapacity, position);
                prepareBuffer(byteBuffer, getReadByteOrder(), this.readPosition, ringBufferCapacity.availableForRead);
            } else {
                throw new IllegalStateException("Consumed more bytes than available");
            }
        }
        return position;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bytesRead(@NotNull ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i >= 0) {
            this.readPosition = carryIndex(byteBuffer, this.readPosition + i);
            ringBufferCapacity.completeRead(i);
            this.totalBytesRead = getTotalBytesRead() + i;
            resumeWriteOp();
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bytesWritten(@NotNull ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i >= 0) {
            this.writePosition = carryIndex(byteBuffer, this.writePosition + i);
            ringBufferCapacity.completeWrite(i);
            this.totalBytesWritten = getTotalBytesWritten() + i;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void carry(@NotNull ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity() - this.reservedSize;
        int position = byteBuffer.position();
        for (int i = capacity; i < position; i++) {
            byteBuffer.put(i - capacity, byteBuffer.get(i));
        }
    }

    private final int carryIndex(@NotNull ByteBuffer byteBuffer, int i) {
        return i >= byteBuffer.capacity() - this.reservedSize ? i - (byteBuffer.capacity() - this.reservedSize) : i;
    }

    private final boolean consumeEachBufferRangeFast(boolean z, Function2<? super ByteBuffer, ? super Boolean, Boolean> function2) {
        ByteBuffer byteBuffer = setupStateForRead();
        boolean z2 = false;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    while (true) {
                        if (!byteBuffer.hasRemaining() && !z) {
                            InlineMarker.finallyStart(1);
                            restoreStateAfterRead();
                            tryTerminate();
                            InlineMarker.finallyEnd(1);
                            z2 = z;
                            break;
                        }
                        boolean booleanValue = function2.mo12248invoke(byteBuffer, Boolean.valueOf(z)).booleanValue();
                        afterBufferVisited(byteBuffer, ringBufferCapacity);
                        if (!booleanValue || (z && !byteBuffer.hasRemaining())) {
                            break;
                        }
                    }
                    InlineMarker.finallyStart(2);
                    restoreStateAfterRead();
                    tryTerminate();
                    InlineMarker.finallyEnd(2);
                    return true;
                }
            } finally {
                InlineMarker.finallyStart(1);
                restoreStateAfterRead();
                tryTerminate();
                InlineMarker.finallyEnd(1);
            }
        }
        if (z2 || this.closed == null) {
            return z2;
        }
        function2.mo12248invoke(ReadWriteBufferStateKt.getEmptyByteBuffer(), true);
        return true;
    }

    private final void doWrite(ByteBuffer byteBuffer, byte b, RingBufferCapacity ringBufferCapacity) {
        byteBuffer.put(b);
        bytesWritten(byteBuffer, ringBufferCapacity, 1);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite();
    }

    private final void ensureClosedJoined(JoiningState joiningState) {
        ClosedElement closedElement = this.closed;
        if (closedElement != null) {
            this.joining = null;
            if (joiningState.getDelegateClose()) {
                ReadWriteBufferState readWriteBufferState = joiningState.getDelegatedTo().state;
                boolean z = (readWriteBufferState instanceof ReadWriteBufferState.Writing) || (readWriteBufferState instanceof ReadWriteBufferState.ReadingWriting);
                if (closedElement.getCause() == null && z) {
                    joiningState.getDelegatedTo().flush();
                } else {
                    joiningState.getDelegatedTo().close(closedElement.getCause());
                }
            } else {
                joiningState.getDelegatedTo().flush();
            }
            joiningState.complete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushImpl(int i, int i2) {
        ReadWriteBufferState readWriteBufferState;
        ByteBufferChannel delegatedTo;
        JoiningState joiningState = this.joining;
        if (joiningState != null && (delegatedTo = joiningState.getDelegatedTo()) != null) {
            delegatedTo.flush();
        }
        do {
            readWriteBufferState = this.state;
            if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE) {
                return;
            }
            readWriteBufferState.capacity.flush();
        } while (readWriteBufferState != this.state);
        int i3 = readWriteBufferState.capacity.availableForWrite;
        if (readWriteBufferState.capacity.availableForRead >= i) {
            resumeReadOp();
        }
        JoiningState joiningState2 = this.joining;
        if (i3 >= i2) {
            if (joiningState2 != null && this.state != ReadWriteBufferState.Terminated.INSTANCE) {
                return;
            }
            resumeWriteOp();
        }
    }

    private final ReadWriteBufferState.Initial newBuffer() {
        ReadWriteBufferState.Initial mo12351borrow = this.pool.mo12351borrow();
        mo12351borrow.getReadBuffer().order(getReadByteOrder().getNioOrder());
        mo12351borrow.getWriteBuffer().order(getWriteByteOrder().getNioOrder());
        mo12351borrow.capacity.resetForWrite();
        return mo12351borrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareBuffer(@NotNull ByteBuffer byteBuffer, ByteOrder byteOrder, int i, int i2) {
        int coerceAtMost;
        boolean z = true;
        if (i >= 0) {
            if (i2 < 0) {
                z = false;
            }
            if (z) {
                byteBuffer.order(byteOrder.getNioOrder());
                coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2 + i, byteBuffer.capacity() - this.reservedSize);
                byteBuffer.limit(coerceAtMost);
                byteBuffer.position(i);
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final int readAsMuchAsPossible(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = setupStateForRead();
        int i = 0;
        if (byteBuffer2 != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    int capacity = byteBuffer2.capacity() - this.reservedSize;
                    while (true) {
                        int remaining = byteBuffer.remaining();
                        if (remaining == 0) {
                            break;
                        }
                        int i2 = this.readPosition;
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(capacity - i2, remaining));
                        if (tryReadAtMost == 0) {
                            break;
                        }
                        byteBuffer2.limit(i2 + tryReadAtMost);
                        byteBuffer2.position(i2);
                        byteBuffer.put(byteBuffer2);
                        bytesRead(byteBuffer2, ringBufferCapacity, tryReadAtMost);
                        i += tryReadAtMost;
                    }
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        return i;
    }

    static /* synthetic */ int readAsMuchAsPossible$default(ByteBufferChannel byteBufferChannel, IoBuffer ioBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ioBuffer.getWriteRemaining();
        }
        return byteBufferChannel.readAsMuchAsPossible(ioBuffer, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean readLineLoop(@NotNull LookAheadSession lookAheadSession, Appendable appendable, char[] cArr, CharBuffer charBuffer, Function1<? super Integer, Boolean> function1, Function1<? super Integer, Unit> function12, Function1<? super ByteBuffer, Long> function13) {
        int i;
        ByteBuffer request;
        int i2 = 1;
        while (function1.mo12165invoke(Integer.valueOf(i2)).booleanValue() && (request = lookAheadSession.request(0, 1)) != null) {
            int position = request.position();
            if (request.remaining() < i2) {
                rollBytes(request, i2);
            }
            long longValue = function13.mo12165invoke(request).longValue();
            lookAheadSession.consumed(request.position() - position);
            int i3 = (int) (longValue >> 32);
            int i4 = (int) (longValue & BodyPartID.bodyIdMax);
            i = -1;
            if (i4 == -1) {
                i = 0;
            } else if (i4 != 0 || !request.hasRemaining()) {
                i = Math.max(1, i4);
            }
            function12.mo12165invoke(Integer.valueOf(i3));
            if (appendable instanceof StringBuilder) {
                ((StringBuilder) appendable).append(cArr, 0, i3);
            } else {
                appendable.append(charBuffer, 0, i3);
            }
            if (i <= 0) {
                break;
            }
            i2 = i;
        }
        i = i2;
        return i == 0;
    }

    private final boolean readSuspendPredicate(int i) {
        ReadWriteBufferState readWriteBufferState = this.state;
        if (readWriteBufferState.capacity.availableForRead >= i) {
            return false;
        }
        if (this.joining == null || this.writeOp == null) {
            return true;
        }
        return readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE && !(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean reading(Function2<? super ByteBuffer, ? super RingBufferCapacity, Boolean> function2) {
        ByteBuffer byteBuffer = setupStateForRead();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    return function2.mo12248invoke(byteBuffer, ringBufferCapacity).booleanValue();
                }
                return false;
            } finally {
                InlineMarker.finallyStart(1);
                restoreStateAfterRead();
                tryTerminate();
                InlineMarker.finallyEnd(1);
            }
        }
        return false;
    }

    private final void releaseBuffer(ReadWriteBufferState.Initial initial) {
        this.pool.recycle(initial);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ByteBufferChannel resolveDelegation(ByteBufferChannel byteBufferChannel, JoiningState joiningState) {
        while (byteBufferChannel.state == ReadWriteBufferState.Terminated.INSTANCE) {
            byteBufferChannel = joiningState.getDelegatedTo();
            joiningState = byteBufferChannel.joining;
            if (joiningState == null) {
                return byteBufferChannel;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreStateAfterRead() {
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty;
        ReadWriteBufferState readWriteBufferState;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = State;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty2 = null;
        while (true) {
            ReadWriteBufferState readWriteBufferState2 = this.state;
            if (idleNonEmpty2 != null) {
                idleNonEmpty2.capacity.resetForWrite();
                resumeWriteOp();
                idleNonEmpty2 = null;
            }
            ReadWriteBufferState mo12319stopReading$kotlinx_coroutines_io = readWriteBufferState2.mo12319stopReading$kotlinx_coroutines_io();
            if (!(mo12319stopReading$kotlinx_coroutines_io instanceof ReadWriteBufferState.IdleNonEmpty) || this.state != readWriteBufferState2 || !mo12319stopReading$kotlinx_coroutines_io.capacity.tryLockForRelease()) {
                idleNonEmpty = idleNonEmpty2;
                readWriteBufferState = mo12319stopReading$kotlinx_coroutines_io;
            } else {
                idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) mo12319stopReading$kotlinx_coroutines_io;
                readWriteBufferState = ReadWriteBufferState.IdleEmpty.INSTANCE;
            }
            if (readWriteBufferState == null || (readWriteBufferState2 != readWriteBufferState && !atomicReferenceFieldUpdater.compareAndSet(this, readWriteBufferState2, readWriteBufferState))) {
                idleNonEmpty2 = idleNonEmpty;
            }
        }
        if (readWriteBufferState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
            if (idleNonEmpty != null) {
                releaseBuffer(idleNonEmpty.getInitial());
            }
            resumeWriteOp();
        } else if (!(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) || !readWriteBufferState.capacity.isEmpty() || !readWriteBufferState.capacity.tryLockForRelease() || !State.compareAndSet(this, readWriteBufferState, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
        } else {
            readWriteBufferState.capacity.resetForWrite();
            releaseBuffer(((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial());
            resumeWriteOp();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreStateAfterWrite() {
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty;
        ReadWriteBufferState readWriteBufferState2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = State;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty2 = null;
        while (true) {
            readWriteBufferState = this.state;
            ReadWriteBufferState mo12322stopWriting$kotlinx_coroutines_io = readWriteBufferState.mo12322stopWriting$kotlinx_coroutines_io();
            if (!(mo12322stopWriting$kotlinx_coroutines_io instanceof ReadWriteBufferState.IdleNonEmpty) || !mo12322stopWriting$kotlinx_coroutines_io.capacity.isEmpty()) {
                idleNonEmpty = idleNonEmpty2;
                readWriteBufferState2 = mo12322stopWriting$kotlinx_coroutines_io;
            } else {
                idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) mo12322stopWriting$kotlinx_coroutines_io;
                readWriteBufferState2 = ReadWriteBufferState.IdleEmpty.INSTANCE;
            }
            if (readWriteBufferState2 == null || (readWriteBufferState != readWriteBufferState2 && !atomicReferenceFieldUpdater.compareAndSet(this, readWriteBufferState, readWriteBufferState2))) {
                idleNonEmpty2 = idleNonEmpty;
            }
        }
        if (((ReadWriteBufferState) new Pair(readWriteBufferState, readWriteBufferState2).component2()) != ReadWriteBufferState.IdleEmpty.INSTANCE || idleNonEmpty == null) {
            return;
        }
        releaseBuffer(idleNonEmpty.getInitial());
    }

    private final void resumeClosed(Throwable th) {
        Continuation<Boolean> andSet = ReadOp.getAndSet(this, null);
        if (andSet != null) {
            if (th != null) {
                Result.Companion companion = Result.Companion;
                GeneratedOutlineSupport1.outline186(th, andSet);
            } else {
                Boolean valueOf = Boolean.valueOf(this.state.capacity.availableForRead > 0);
                Result.Companion companion2 = Result.Companion;
                andSet.resumeWith(Result.m10378constructorimpl(valueOf));
            }
        }
        Continuation<Unit> andSet2 = WriteOp.getAndSet(this, null);
        if (andSet2 != null) {
            if (th == null) {
                th = new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE);
            }
            Result.Companion companion3 = Result.Companion;
            GeneratedOutlineSupport1.outline186(th, andSet2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeReadOp() {
        Throwable th = null;
        Continuation<Boolean> andSet = ReadOp.getAndSet(this, null);
        if (andSet != null) {
            ClosedElement closedElement = this.closed;
            if (closedElement != null) {
                th = closedElement.getCause();
            }
            if (th != null) {
                Result.Companion companion = Result.Companion;
                GeneratedOutlineSupport1.outline186(th, andSet);
                return;
            }
            Result.Companion companion2 = Result.Companion;
            andSet.resumeWith(Result.m10378constructorimpl(true));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void resumeWriteOp() {
        Continuation<? super Unit> continuation;
        ClosedElement closedElement;
        Object createFailure;
        do {
            continuation = this.writeOp;
            if (continuation == null) {
                return;
            }
            closedElement = this.closed;
            if (closedElement == null && this.joining != null) {
                ReadWriteBufferState readWriteBufferState = this.state;
                if (!(readWriteBufferState instanceof ReadWriteBufferState.Writing) && !(readWriteBufferState instanceof ReadWriteBufferState.ReadingWriting) && readWriteBufferState != ReadWriteBufferState.Terminated.INSTANCE) {
                    return;
                }
            }
        } while (!WriteOp.compareAndSet(this, continuation, null));
        if (closedElement == null) {
            createFailure = Unit.INSTANCE;
            Result.Companion companion = Result.Companion;
        } else {
            Throwable sendException = closedElement.getSendException();
            Result.Companion companion2 = Result.Companion;
            createFailure = ResultKt.createFailure(sendException);
        }
        continuation.resumeWith(Result.m10378constructorimpl(createFailure));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rollBytes(@NotNull ByteBuffer byteBuffer, int i) {
        int remaining = byteBuffer.remaining();
        byteBuffer.limit(byteBuffer.position() + i);
        int i2 = i - remaining;
        for (int i3 = 0; i3 < i2; i3++) {
            byteBuffer.put((byteBuffer.capacity() - 8) + i3, byteBuffer.get(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T, C extends Continuation<? super T>> boolean setContinuation(Function0<? extends C> function0, AtomicReferenceFieldUpdater<ByteBufferChannel, C> atomicReferenceFieldUpdater, C c, Function0<Boolean> function02) {
        while (function0.mo12560invoke() == null) {
            if (!function02.mo12560invoke().booleanValue()) {
                return false;
            }
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, c)) {
                return function02.mo12560invoke().booleanValue() || !atomicReferenceFieldUpdater.compareAndSet(this, c, null);
            }
        }
        throw new IllegalStateException("Operation is already in progress");
    }

    private final JoiningState setupDelegateTo(ByteBufferChannel byteBufferChannel, boolean z) {
        if (this != byteBufferChannel) {
            JoiningState joiningState = new JoiningState(byteBufferChannel, z);
            byteBufferChannel.setWriteByteOrder(getWriteByteOrder());
            this.joining = joiningState;
            ClosedElement closedElement = this.closed;
            if (closedElement != null) {
                if (closedElement.getCause() != null) {
                    byteBufferChannel.close(closedElement.getCause());
                } else if (!z || this.state != ReadWriteBufferState.Terminated.INSTANCE) {
                    byteBufferChannel.flush();
                } else {
                    ByteWriteChannelKt.close(byteBufferChannel);
                }
            } else {
                flush();
            }
            return joiningState;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
        r0 = r2.getReadBuffer();
        prepareBuffer(r0, getReadByteOrder(), r4.readPosition, r2.capacity.availableForRead);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.nio.ByteBuffer setupStateForRead() {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = access$getState$cp()
        L4:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r1 = access$getState$p(r4)
            kotlinx.coroutines.io.internal.ReadWriteBufferState$Terminated r2 = kotlinx.coroutines.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            r3 = 0
            if (r2 == 0) goto L1e
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r0 = r4.closed
            if (r0 == 0) goto L1d
            java.lang.Throwable r0 = r0.getCause()
            if (r0 != 0) goto L1c
            goto L1d
        L1c:
            throw r0
        L1d:
            return r3
        L1e:
            kotlinx.coroutines.io.internal.ReadWriteBufferState$IdleEmpty r2 = kotlinx.coroutines.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r2 == 0) goto L33
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r0 = r4.closed
            if (r0 == 0) goto L32
            java.lang.Throwable r0 = r0.getCause()
            if (r0 != 0) goto L31
            goto L32
        L31:
            throw r0
        L32:
            return r3
        L33:
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r1.capacity
            int r2 = r2.availableForRead
            if (r2 != 0) goto L3a
            return r3
        L3a:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = r1.mo12321startReading$kotlinx_coroutines_io()
            if (r2 == 0) goto L4
            if (r1 == r2) goto L48
            boolean r1 = r0.compareAndSet(r4, r1, r2)
            if (r1 == 0) goto L4
        L48:
            java.nio.ByteBuffer r0 = r2.getReadBuffer()
            kotlinx.io.core.ByteOrder r1 = r4.getReadByteOrder()
            int r3 = r4.readPosition
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r2 = r2.availableForRead
            r4.prepareBuffer(r0, r1, r3, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.setupStateForRead():java.nio.ByteBuffer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0065, code lost:
        r1 = new kotlin.Pair(r3, r4);
        r2 = (kotlinx.coroutines.io.internal.ReadWriteBufferState) r1.component1();
        r1 = (kotlinx.coroutines.io.internal.ReadWriteBufferState) r1.component2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0078, code lost:
        if (r6.closed == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007a, code lost:
        restoreStateAfterWrite();
        tryTerminate();
        r0 = r6.closed;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0082, code lost:
        if (r0 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0084, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x008b, code lost:
        throw r0.getSendException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008c, code lost:
        r3 = r1.getWriteBuffer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0090, code lost:
        if (r0 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0094, code lost:
        if (r2 == kotlinx.coroutines.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0096, code lost:
        releaseBuffer(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0099, code lost:
        prepareBuffer(r3, getWriteByteOrder(), r6.writePosition, r1.capacity.availableForWrite);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a6, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.nio.ByteBuffer setupStateForWrite() {
        /*
            r6 = this;
            kotlin.coroutines.Continuation<? super kotlin.Unit> r0 = r6.writeOp
            if (r0 != 0) goto La7
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = access$getState$cp()
            r2 = 0
            r0 = r2
        La:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r3 = access$getState$p(r6)
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r4 = r6.joining
            if (r4 == 0) goto L18
            if (r0 == 0) goto L17
            r6.releaseBuffer(r0)
        L17:
            return r2
        L18:
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r4 = r6.closed
            if (r4 == 0) goto L2d
            if (r0 == 0) goto L21
            r6.releaseBuffer(r0)
        L21:
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r0 = r6.closed
            if (r0 != 0) goto L28
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L28:
            java.lang.Throwable r0 = r0.getSendException()
            throw r0
        L2d:
            kotlinx.coroutines.io.internal.ReadWriteBufferState$IdleEmpty r4 = kotlinx.coroutines.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r3 != r4) goto L3d
            if (r0 == 0) goto L34
            goto L38
        L34:
            kotlinx.coroutines.io.internal.ReadWriteBufferState$Initial r0 = r6.newBuffer()
        L38:
            kotlinx.coroutines.io.internal.ReadWriteBufferState$Writing r4 = r0.mo12317startWriting$kotlinx_coroutines_io()
            goto L5b
        L3d:
            kotlinx.coroutines.io.internal.ReadWriteBufferState$Terminated r4 = kotlinx.coroutines.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r3 != r4) goto L57
            if (r0 == 0) goto L46
            r6.releaseBuffer(r0)
        L46:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r0 = r6.joining
            if (r0 == 0) goto L4b
            return r2
        L4b:
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r0 = r6.closed
            if (r0 != 0) goto L52
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L52:
            java.lang.Throwable r0 = r0.getSendException()
            throw r0
        L57:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r4 = r3.mo12317startWriting$kotlinx_coroutines_io()
        L5b:
            if (r4 == 0) goto La
            if (r3 == r4) goto L65
            boolean r5 = r1.compareAndSet(r6, r3, r4)
            if (r5 == 0) goto La
        L65:
            kotlin.Pair r1 = new kotlin.Pair
            r1.<init>(r3, r4)
            java.lang.Object r2 = r1.component1()
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = (kotlinx.coroutines.io.internal.ReadWriteBufferState) r2
            java.lang.Object r1 = r1.component2()
            kotlinx.coroutines.io.internal.ReadWriteBufferState r1 = (kotlinx.coroutines.io.internal.ReadWriteBufferState) r1
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r3 = r6.closed
            if (r3 == 0) goto L8c
            r6.restoreStateAfterWrite()
            r6.tryTerminate()
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r0 = r6.closed
            if (r0 != 0) goto L87
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L87:
            java.lang.Throwable r0 = r0.getSendException()
            throw r0
        L8c:
            java.nio.ByteBuffer r3 = r1.getWriteBuffer()
            if (r0 == 0) goto L99
            kotlinx.coroutines.io.internal.ReadWriteBufferState$IdleEmpty r4 = kotlinx.coroutines.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r2 == r4) goto L99
            r6.releaseBuffer(r0)
        L99:
            kotlinx.io.core.ByteOrder r0 = r6.getWriteByteOrder()
            int r2 = r6.writePosition
            kotlinx.coroutines.io.internal.RingBufferCapacity r1 = r1.capacity
            int r1 = r1.availableForWrite
            r6.prepareBuffer(r3, r0, r2, r1)
            return r3
        La7:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Write operation is already in progress: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.setupStateForWrite():java.nio.ByteBuffer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldResumeReadOp() {
        return this.joining != null && (this.state == ReadWriteBufferState.IdleEmpty.INSTANCE || (this.state instanceof ReadWriteBufferState.IdleNonEmpty));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00e1, code lost:
        r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00e5, code lost:
        return r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e1 A[EDGE_INSN: B:86:0x00e1->B:80:0x00e1 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00df A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00de A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object suspensionForSize(int r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.suspensionForSize(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean tryCompleteJoining(JoiningState joiningState) {
        if (!tryReleaseBuffer(true)) {
            return false;
        }
        ensureClosedJoined(joiningState);
        Continuation continuation = (Continuation) ReadOp.getAndSet(this, null);
        if (continuation != null) {
            IllegalStateException illegalStateException = new IllegalStateException("Joining is in progress");
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(illegalStateException)));
        }
        resumeWriteOp();
        return true;
    }

    private final boolean tryReleaseBuffer(boolean z) {
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState.Terminated terminated;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = State;
        ReadWriteBufferState.Initial initial = null;
        while (true) {
            readWriteBufferState = this.state;
            if (initial != null) {
                initial.capacity.resetForWrite();
                resumeWriteOp();
                initial = null;
            }
            ClosedElement closedElement = this.closed;
            if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE) {
                return true;
            }
            if (readWriteBufferState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                terminated = ReadWriteBufferState.Terminated.INSTANCE;
            } else if (closedElement != null && (readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) && (readWriteBufferState.capacity.tryLockForRelease() || closedElement.getCause() != null)) {
                if (closedElement.getCause() != null) {
                    readWriteBufferState.capacity.forceLockForRelease();
                }
                initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                terminated = ReadWriteBufferState.Terminated.INSTANCE;
            } else if (!z || !(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) || !readWriteBufferState.capacity.tryLockForRelease()) {
                return false;
            } else {
                initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                terminated = ReadWriteBufferState.Terminated.INSTANCE;
            }
            if (terminated == null || (readWriteBufferState != terminated && !atomicReferenceFieldUpdater.compareAndSet(this, readWriteBufferState, terminated))) {
            }
        }
        new Pair(readWriteBufferState, terminated);
        if (initial != null && this.state == ReadWriteBufferState.Terminated.INSTANCE) {
            releaseBuffer(initial);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean tryTerminate() {
        if (this.closed != null && tryReleaseBuffer(false)) {
            JoiningState joiningState = this.joining;
            if (joiningState != null) {
                ensureClosedJoined(joiningState);
            }
            resumeReadOp();
            resumeWriteOp();
            return true;
        }
        return false;
    }

    private final boolean tryWriteInt(@NotNull ByteBuffer byteBuffer, int i, RingBufferCapacity ringBufferCapacity) {
        if (ringBufferCapacity.tryWriteExact(4)) {
            if (byteBuffer.remaining() < 4) {
                byteBuffer.limit(byteBuffer.capacity());
                byteBuffer.putInt(i);
                carry(byteBuffer);
            } else {
                byteBuffer.putInt(i);
            }
            bytesWritten(byteBuffer, ringBufferCapacity, 4);
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            restoreStateAfterWrite();
            tryTerminate();
            return true;
        }
        return false;
    }

    private final boolean tryWriteLong(@NotNull ByteBuffer byteBuffer, long j, RingBufferCapacity ringBufferCapacity) {
        if (ringBufferCapacity.tryWriteExact(8)) {
            if (byteBuffer.remaining() < 8) {
                byteBuffer.limit(byteBuffer.capacity());
                byteBuffer.putLong(j);
                carry(byteBuffer);
            } else {
                byteBuffer.putLong(j);
            }
            bytesWritten(byteBuffer, ringBufferCapacity, 8);
            if (ringBufferCapacity.isFull() || getAutoFlush() || this.joining != null) {
                flush();
            }
            restoreStateAfterWrite();
            tryTerminate();
            return true;
        }
        return false;
    }

    private final int tryWritePacketPart(ByteReadPacket byteReadPacket) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost((int) Math.min(byteReadPacket.m12336getRemaining(), byteBuffer.remaining()));
                    if (tryWriteAtMost > 0) {
                        byteBuffer.limit(byteBuffer.position() + tryWriteAtMost);
                        Input.DefaultImpls.readFully$default(byteReadPacket, byteBuffer, 0, 2, (Object) null);
                        byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, tryWriteAtMost);
                    }
                    return tryWriteAtMost;
                }
                throw closedElement.getSendException();
            } finally {
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Pair<T, T> update(Function0<? extends T> function0, AtomicReferenceFieldUpdater<ByteBufferChannel, T> atomicReferenceFieldUpdater, Function1<? super T, ? extends T> function1) {
        T mo12560invoke;
        T mo12165invoke;
        while (true) {
            mo12560invoke = function0.mo12560invoke();
            mo12165invoke = function1.mo12165invoke(mo12560invoke);
            if (mo12165invoke == null || (mo12560invoke != mo12165invoke && !atomicReferenceFieldUpdater.compareAndSet(this, mo12560invoke, mo12165invoke))) {
            }
        }
        return new Pair<>(mo12560invoke, mo12165invoke);
    }

    private final Pair<ReadWriteBufferState, ReadWriteBufferState> updateState(Function1<? super ReadWriteBufferState, ? extends ReadWriteBufferState> function1) {
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState mo12165invoke;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = State;
        while (true) {
            readWriteBufferState = this.state;
            mo12165invoke = function1.mo12165invoke(readWriteBufferState);
            if (mo12165invoke == null || (readWriteBufferState != mo12165invoke && !atomicReferenceFieldUpdater.compareAndSet(this, readWriteBufferState, mo12165invoke))) {
            }
        }
        return new Pair<>(readWriteBufferState, mo12165invoke);
    }

    private final ReadWriteBufferState updateStateAndGet(Function1<? super ReadWriteBufferState, ? extends ReadWriteBufferState> function1) {
        ReadWriteBufferState mo12165invoke;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = State;
        while (true) {
            ReadWriteBufferState readWriteBufferState = this.state;
            mo12165invoke = function1.mo12165invoke(readWriteBufferState);
            if (mo12165invoke == null || (readWriteBufferState != mo12165invoke && !atomicReferenceFieldUpdater.compareAndSet(this, readWriteBufferState, mo12165invoke))) {
            }
        }
        return mo12165invoke;
    }

    private final int writeAsMuchAsPossible(ByteBuffer byteBuffer) {
        ByteBufferChannel byteBufferChannel;
        int tryWriteAtMost;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer2 = byteBufferChannel.setupStateForWrite();
        if (byteBuffer2 != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    int limit = byteBuffer.limit();
                    int i = 0;
                    while (true) {
                        int position = limit - byteBuffer.position();
                        if (position == 0 || (tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(position, byteBuffer2.remaining()))) == 0) {
                            break;
                        }
                        if (tryWriteAtMost > 0) {
                            byteBuffer.limit(byteBuffer.position() + tryWriteAtMost);
                            byteBuffer2.put(byteBuffer);
                            i += tryWriteAtMost;
                            byteBufferChannel.prepareBuffer(byteBuffer2, byteBufferChannel.getWriteByteOrder(), byteBufferChannel.carryIndex(byteBuffer2, byteBufferChannel.writePosition + i), ringBufferCapacity.availableForWrite);
                        } else {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                    }
                    byteBuffer.limit(limit);
                    byteBufferChannel.bytesWritten(byteBuffer2, ringBufferCapacity, i);
                    return i;
                }
                throw closedElement.getSendException();
            } finally {
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean writeSuspendPredicate(int i) {
        JoiningState joiningState = this.joining;
        ReadWriteBufferState readWriteBufferState = this.state;
        if (this.closed != null) {
            return false;
        }
        if (joiningState == null) {
            if (readWriteBufferState.capacity.availableForWrite >= i || readWriteBufferState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                return false;
            }
        } else if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE || (readWriteBufferState instanceof ReadWriteBufferState.Writing) || (readWriteBufferState instanceof ReadWriteBufferState.ReadingWriting)) {
            return false;
        }
        return true;
    }

    private final boolean writeWhileLoop(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, Function1<? super ByteBuffer, Boolean> function1) {
        int coerceAtMost;
        int capacity = byteBuffer.capacity() - this.reservedSize;
        boolean z = true;
        while (z) {
            int tryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(1);
            if (tryWriteAtLeast == 0) {
                break;
            }
            int i = this.writePosition;
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i + tryWriteAtLeast, capacity);
            byteBuffer.limit(coerceAtMost);
            byteBuffer.position(i);
            try {
                boolean booleanValue = function1.mo12165invoke(byteBuffer).booleanValue();
                if (byteBuffer.limit() == coerceAtMost) {
                    int position = byteBuffer.position() - i;
                    if (position >= 0) {
                        bytesWritten(byteBuffer, ringBufferCapacity, position);
                        if (position < tryWriteAtLeast) {
                            ringBufferCapacity.completeRead(tryWriteAtLeast - position);
                        }
                        z = booleanValue;
                    } else {
                        throw new IllegalStateException("position has been moved backward: pushback is not supported");
                    }
                } else {
                    throw new IllegalStateException("buffer limit modified");
                }
            } catch (Throwable th) {
                ringBufferCapacity.completeRead(tryWriteAtLeast);
                throw th;
            }
        }
        return z;
    }

    private final boolean writeWhileNoSuspend(Function1<? super ByteBuffer, Boolean> function1) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    return byteBufferChannel.writeWhileLoop(byteBuffer, ringBufferCapacity, function1);
                }
                throw closedElement.getSendException();
            } finally {
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
            }
        }
        return true;
    }

    private final void writing(Function3<? super ByteBufferChannel, ? super ByteBuffer, ? super RingBufferCapacity, Unit> function3) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    function3.invoke(byteBufferChannel, byteBuffer, ringBufferCapacity);
                    return;
                }
                throw closedElement.getSendException();
            } finally {
                InlineMarker.finallyStart(1);
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
                InlineMarker.finallyEnd(1);
            }
        }
    }

    @Override // kotlinx.coroutines.io.ByteChannel
    public void attachJob(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        Job job2 = this.attachedJob;
        if (job2 != null) {
            job2.mo12309cancel();
        }
        this.attachedJob = job;
        Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ByteBufferChannel$attachJob$1(this), 2, null);
    }

    @Override // kotlinx.coroutines.io.LookAheadSuspendSession
    @Nullable
    public final Object awaitAtLeast(int i, @NotNull Continuation<? super Boolean> continuation) {
        boolean z = false;
        if (i >= 0) {
            if (i <= 4088) {
                z = true;
            }
            if (z) {
                if (this.state.capacity.availableForRead >= i) {
                    if (this.state.getIdle() || (this.state instanceof ReadWriteBufferState.Writing)) {
                        setupStateForRead();
                    }
                    return Boxing.boxBoolean(true);
                } else if (this.state.getIdle() || (this.state instanceof ReadWriteBufferState.Writing)) {
                    return awaitAtLeastSuspend(i, continuation);
                } else {
                    if (i == 1) {
                        return readSuspendImpl(1, continuation);
                    }
                    return readSuspend(i, continuation);
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be larger than max buffer size of 4088: ", i).toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("atLeast parameter shouldn't be negative: ", i).toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object awaitAtLeastSuspend(int r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$awaitAtLeastSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$awaitAtLeastSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$awaitAtLeastSuspend$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r5 = (kotlinx.coroutines.io.ByteBufferChannel) r5
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L30
            goto L4f
        L30:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L67
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.readSuspend(r5, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r5 = r4
        L4f:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L62
            kotlinx.coroutines.io.internal.ReadWriteBufferState r0 = r5.state
            boolean r0 = r0.getIdle()
            if (r0 == 0) goto L62
            r5.setupStateForRead()
        L62:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            return r5
        L67:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.awaitAtLeastSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    final /* synthetic */ Object awaitClose(@NotNull Continuation<? super Unit> continuation) {
        if (this.closed != null) {
            return Unit.INSTANCE;
        }
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            return joiningState.awaitClose(continuation);
        }
        if (this.closed != null) {
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("Only works for joined".toString());
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public boolean cancel(@Nullable Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel has been cancelled");
        }
        return close(th);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public boolean close(@Nullable Throwable th) {
        Job job;
        JoiningState joiningState;
        if (this.closed != null) {
            return false;
        }
        ClosedElement emptyCause = th == null ? ClosedElement.Companion.getEmptyCause() : new ClosedElement(th);
        this.state.capacity.flush();
        if (!Closed.compareAndSet(this, null, emptyCause)) {
            return false;
        }
        this.state.capacity.flush();
        if (this.state.capacity.isEmpty() || th != null) {
            tryTerminate();
        }
        resumeClosed(th);
        if (this.state == ReadWriteBufferState.Terminated.INSTANCE && (joiningState = this.joining) != null) {
            ensureClosedJoined(joiningState);
        }
        if (th == null || (job = this.attachedJob) == null) {
            return true;
        }
        job.mo12309cancel();
        return true;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object consumeEachBufferRange(@NotNull Function2<? super ByteBuffer, ? super Boolean, Boolean> function2, @NotNull Continuation<? super Unit> continuation) {
        ByteBuffer byteBuffer = setupStateForRead();
        boolean z = true;
        boolean z2 = false;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    while (byteBuffer.hasRemaining()) {
                        boolean booleanValue = function2.mo12248invoke(byteBuffer, Boxing.boxBoolean(false)).booleanValue();
                        afterBufferVisited(byteBuffer, ringBufferCapacity);
                        if (!booleanValue) {
                            break;
                        }
                    }
                    boolean booleanValue2 = Boxing.boxBoolean(false).booleanValue();
                    restoreStateAfterRead();
                    tryTerminate();
                    z2 = booleanValue2;
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        if (z2 || this.closed == null) {
            z = z2;
        } else {
            function2.mo12248invoke(ReadWriteBufferStateKt.getEmptyByteBuffer(), Boxing.boxBoolean(true));
        }
        if (z) {
            return Unit.INSTANCE;
        }
        return consumeEachBufferRangeSuspend(function2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
        r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2).booleanValue();
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00e0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00d4 -> B:58:0x00d8). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object consumeEachBufferRangeSuspend(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.consumeEachBufferRangeSuspend(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.LookAheadSession
    public void consumed(int i) {
        if (i >= 0) {
            ReadWriteBufferState readWriteBufferState = this.state;
            if (readWriteBufferState.capacity.tryReadExact(i)) {
                bytesRead(readWriteBufferState.getReadBuffer(), readWriteBufferState.capacity, i);
                return;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline52("Unable to consume ", i, " bytes: not enough available bytes"));
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:198:0x03cd, code lost:
        if (r13.tryCompleteJoining(r10) == false) goto L177;
     */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x0096: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:28:0x0096 */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0308 A[Catch: all -> 0x040b, TryCatch #3 {all -> 0x040b, blocks: (B:61:0x0150, B:63:0x0156, B:65:0x015c, B:69:0x0165, B:71:0x016b, B:147:0x0302, B:149:0x0308, B:153:0x0313, B:154:0x0322, B:151:0x030e, B:175:0x036b, B:178:0x0373, B:180:0x037d, B:181:0x0382, B:184:0x038a, B:186:0x0393, B:203:0x03de, B:205:0x03e2, B:210:0x0401, B:211:0x0404), top: B:227:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0313 A[Catch: all -> 0x040b, TryCatch #3 {all -> 0x040b, blocks: (B:61:0x0150, B:63:0x0156, B:65:0x015c, B:69:0x0165, B:71:0x016b, B:147:0x0302, B:149:0x0308, B:153:0x0313, B:154:0x0322, B:151:0x030e, B:175:0x036b, B:178:0x0373, B:180:0x037d, B:181:0x0382, B:184:0x038a, B:186:0x0393, B:203:0x03de, B:205:0x03e2, B:210:0x0401, B:211:0x0404), top: B:227:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0345 A[Catch: all -> 0x0365, TryCatch #0 {all -> 0x0365, blocks: (B:163:0x033f, B:165:0x0345, B:169:0x0350, B:170:0x035e, B:171:0x0364, B:167:0x034b), top: B:221:0x033f }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0350 A[Catch: all -> 0x0365, TryCatch #0 {all -> 0x0365, blocks: (B:163:0x033f, B:165:0x0345, B:169:0x0350, B:170:0x035e, B:171:0x0364, B:167:0x034b), top: B:221:0x033f }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03e2 A[Catch: all -> 0x040b, TryCatch #3 {all -> 0x040b, blocks: (B:61:0x0150, B:63:0x0156, B:65:0x015c, B:69:0x0165, B:71:0x016b, B:147:0x0302, B:149:0x0308, B:153:0x0313, B:154:0x0322, B:151:0x030e, B:175:0x036b, B:178:0x0373, B:180:0x037d, B:181:0x0382, B:184:0x038a, B:186:0x0393, B:203:0x03de, B:205:0x03e2, B:210:0x0401, B:211:0x0404), top: B:227:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0401 A[Catch: all -> 0x040b, TryCatch #3 {all -> 0x040b, blocks: (B:61:0x0150, B:63:0x0156, B:65:0x015c, B:69:0x0165, B:71:0x016b, B:147:0x0302, B:149:0x0308, B:153:0x0313, B:154:0x0322, B:151:0x030e, B:175:0x036b, B:178:0x0373, B:180:0x037d, B:181:0x0382, B:184:0x038a, B:186:0x0393, B:203:0x03de, B:205:0x03e2, B:210:0x0401, B:211:0x0404), top: B:227:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0219 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x02a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0156 A[Catch: all -> 0x040b, TryCatch #3 {all -> 0x040b, blocks: (B:61:0x0150, B:63:0x0156, B:65:0x015c, B:69:0x0165, B:71:0x016b, B:147:0x0302, B:149:0x0308, B:153:0x0313, B:154:0x0322, B:151:0x030e, B:175:0x036b, B:178:0x0373, B:180:0x037d, B:181:0x0382, B:184:0x038a, B:186:0x0393, B:203:0x03de, B:205:0x03e2, B:210:0x0401, B:211:0x0404), top: B:227:0x0150 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0191 A[Catch: all -> 0x0330, TryCatch #8 {all -> 0x0330, blocks: (B:75:0x018b, B:77:0x0191, B:79:0x0195), top: B:237:0x018b }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01e3 A[Catch: all -> 0x01ed, TRY_LEAVE, TryCatch #6 {all -> 0x01ed, blocks: (B:88:0x01db, B:91:0x01e3), top: B:233:0x01db }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:138:0x02b7 -> B:237:0x018b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:180:0x037d -> B:208:0x03fb). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:204:0x03e0 -> B:208:0x03fb). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:206:0x03f8 -> B:208:0x03fb). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object copyDirect$kotlinx_coroutines_io(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteBufferChannel r28, long r29, @org.jetbrains.annotations.Nullable kotlinx.coroutines.io.ByteBufferChannel.JoiningState r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r32) {
        /*
            Method dump skipped, instructions count: 1049
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.copyDirect$kotlinx_coroutines_io(kotlinx.coroutines.io.ByteBufferChannel, long, kotlinx.coroutines.io.ByteBufferChannel$JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    final /* synthetic */ Object delegateByte(byte b, @NotNull Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        if (joiningState == null) {
            Intrinsics.throwNpe();
        }
        return this.state == ReadWriteBufferState.Terminated.INSTANCE ? joiningState.getDelegatedTo().writeByte(b, continuation) : delegateSuspend(joiningState, new ByteBufferChannel$delegateByte$2(b, null), continuation);
    }

    @Nullable
    final /* synthetic */ Object delegateInt(int i, @NotNull Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        if (joiningState == null) {
            Intrinsics.throwNpe();
        }
        return this.state == ReadWriteBufferState.Terminated.INSTANCE ? joiningState.getDelegatedTo().writeInt(i, continuation) : delegateSuspend(joiningState, new ByteBufferChannel$delegateInt$2(i, null), continuation);
    }

    @Nullable
    final /* synthetic */ Object delegateLong(long j, @NotNull Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        if (joiningState == null) {
            Intrinsics.throwNpe();
        }
        return this.state == ReadWriteBufferState.Terminated.INSTANCE ? joiningState.getDelegatedTo().writeLong(j, continuation) : delegateSuspend(joiningState, new ByteBufferChannel$delegateLong$2(j, null), continuation);
    }

    @Nullable
    final /* synthetic */ Object delegateShort(short s, @NotNull Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        if (joiningState == null) {
            Intrinsics.throwNpe();
        }
        return this.state == ReadWriteBufferState.Terminated.INSTANCE ? joiningState.getDelegatedTo().writeShort(s, continuation) : delegateSuspend(joiningState, new ByteBufferChannel$delegateShort$2(s, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007e A[PHI: r9 
      PHI: (r9v4 java.lang.Object) = (r9v6 java.lang.Object), (r9v1 java.lang.Object) binds: [B:30:0x007b, B:20:0x0056] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object delegateSuspend(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteBufferChannel.JoiningState r7, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super kotlinx.coroutines.io.ByteBufferChannel, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteBufferChannel$delegateSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteBufferChannel$delegateSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$delegateSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$delegateSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$delegateSuspend$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5e
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r8 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r8
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r9 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3b
            r9 = r7
            r7 = r2
            goto L65
        L3b:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L40:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L48:
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r9 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L59
            goto L7e
        L59:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L5e:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8e
            r9 = r8
            r8 = r7
            r7 = r6
        L65:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = r7.state
            kotlinx.coroutines.io.internal.ReadWriteBufferState$Terminated r5 = kotlinx.coroutines.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r5) goto L7f
            kotlinx.coroutines.io.ByteBufferChannel r2 = r8.getDelegatedTo()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r4
            java.lang.Object r9 = r9.mo12248invoke(r2, r0)
            if (r9 != r1) goto L7e
            return r1
        L7e:
            return r9
        L7f:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r3
            java.lang.Object r2 = r7.writeSuspend(r4, r0)
            if (r2 != r1) goto L65
            return r1
        L8e:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.delegateSuspend(kotlinx.coroutines.io.ByteBufferChannel$JoiningState, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object discard(long j, @NotNull Continuation<? super Long> continuation) {
        long j2 = 0;
        if (j >= 0) {
            ByteBuffer byteBuffer = setupStateForRead();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = this.state.capacity;
                try {
                    if (ringBufferCapacity.availableForRead != 0) {
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost((int) Math.min(Integer.MAX_VALUE, j));
                        bytesRead(byteBuffer, ringBufferCapacity, tryReadAtMost);
                        long j3 = tryReadAtMost + 0;
                        Boxing.boxBoolean(true).booleanValue();
                        j2 = j3;
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate();
                }
            }
            long j4 = j2;
            if (j4 != j && !isClosedForRead()) {
                return discardSuspend(j4, j, continuation);
            }
            return Boxing.boxLong(j4);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("max shouldn't be negative: ", j).toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c1, code lost:
        if (r3.isClosedForRead() != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c3, code lost:
        r8.L$0 = r3;
        r6 = r19;
        r8.J$0 = r6;
        r8.J$1 = r0;
        r8.L$1 = r4;
        r8.label = 1;
        r10 = r3.readSuspend(1, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d4, code lost:
        if (r10 != r9) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d6, code lost:
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00dd, code lost:
        if (((java.lang.Boolean) r10).booleanValue() == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ea, code lost:
        return kotlin.coroutines.jvm.internal.Boxing.boxLong(r4.element);
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e0 A[LOOP:0: B:21:0x0067->B:45:0x00e0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bd A[EDGE_INSN: B:53:0x00bd->B:37:0x00bd ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00d4 -> B:42:0x00d7). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object discardSuspend(long r19, long r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r23) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.discardSuspend(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public void flush() {
        flushImpl(1, 1);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public int getAvailableForRead() {
        return this.state.capacity.availableForRead;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public int getAvailableForWrite() {
        return this.state.capacity.availableForWrite;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Throwable getClosedCause() {
        ClosedElement closedElement = this.closed;
        if (closedElement != null) {
            return closedElement.getCause();
        }
        return null;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @NotNull
    public ByteOrder getReadByteOrder() {
        return this.readByteOrder;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public long getTotalBytesRead() {
        return this.totalBytesRead;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @NotNull
    public ByteOrder getWriteByteOrder() {
        return this.writeByteOrder;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public boolean isClosedForRead() {
        return this.state == ReadWriteBufferState.Terminated.INSTANCE && this.closed != null;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel, kotlinx.coroutines.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return this.closed != null;
    }

    @Nullable
    public final Object joinFrom$kotlinx_coroutines_io(@NotNull ByteBufferChannel byteBufferChannel, boolean z, @NotNull Continuation<? super Unit> continuation) {
        if (byteBufferChannel.closed != null && byteBufferChannel.state == ReadWriteBufferState.Terminated.INSTANCE) {
            if (z) {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    Intrinsics.throwNpe();
                }
                close(closedElement.getCause());
            }
            return Unit.INSTANCE;
        }
        ClosedElement closedElement2 = this.closed;
        if (closedElement2 != null) {
            if (byteBufferChannel.closed != null) {
                return Unit.INSTANCE;
            }
            throw closedElement2.getSendException();
        }
        JoiningState joiningState = byteBufferChannel.setupDelegateTo(this, z);
        if (byteBufferChannel.tryCompleteJoining(joiningState)) {
            return byteBufferChannel.awaitClose(continuation);
        }
        return joinFromSuspend(byteBufferChannel, z, joiningState, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a2 A[PHI: r13 
      PHI: (r13v6 java.lang.Object) = (r13v4 java.lang.Object), (r13v1 java.lang.Object) binds: [B:36:0x009f, B:13:0x0038] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object joinFromSuspend(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteBufferChannel r10, boolean r11, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteBufferChannel.JoiningState r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof kotlinx.coroutines.io.ByteBufferChannel$joinFromSuspend$1
            if (r0 == 0) goto L13
            r0 = r13
            kotlinx.coroutines.io.ByteBufferChannel$joinFromSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$joinFromSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$joinFromSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$joinFromSuspend$1
            r0.<init>(r9, r13)
        L18:
            java.lang.Object r13 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L61
            if (r1 == r2) goto L48
            if (r1 != r8) goto L40
            java.lang.Object r10 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r10 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r10
            boolean r10 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.io.ByteBufferChannel r10 = (kotlinx.coroutines.io.ByteBufferChannel) r10
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r10 = (kotlinx.coroutines.io.ByteBufferChannel) r10
            boolean r10 = r13 instanceof kotlin.Result.Failure
            if (r10 != 0) goto L3b
            goto La2
        L3b:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r10 = r13.exception
            throw r10
        L40:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L48:
            java.lang.Object r10 = r0.L$2
            r12 = r10
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r12 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r12
            boolean r11 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.io.ByteBufferChannel r10 = (kotlinx.coroutines.io.ByteBufferChannel) r10
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r1 = (kotlinx.coroutines.io.ByteBufferChannel) r1
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5c
            goto L80
        L5c:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r10 = r13.exception
            throw r10
        L61:
            boolean r1 = r13 instanceof kotlin.Result.Failure
            if (r1 != 0) goto La3
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.Z$0 = r11
            r0.L$2 = r12
            r0.label = r2
            r1 = r9
            r2 = r10
            r5 = r12
            r6 = r0
            java.lang.Object r13 = r1.copyDirect$kotlinx_coroutines_io(r2, r3, r5, r6)
            if (r13 != r7) goto L7f
            return r7
        L7f:
            r1 = r9
        L80:
            if (r11 == 0) goto L8e
            boolean r13 = r10.isClosedForRead()
            if (r13 == 0) goto L8e
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r1)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L8e:
            r1.flush()
            r0.L$0 = r1
            r0.L$1 = r10
            r0.Z$0 = r11
            r0.L$2 = r12
            r0.label = r8
            java.lang.Object r13 = r10.awaitClose(r0)
            if (r13 != r7) goto La2
            return r7
        La2:
            return r13
        La3:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r10 = r13.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.joinFromSuspend(kotlinx.coroutines.io.ByteBufferChannel, boolean, kotlinx.coroutines.io.ByteBufferChannel$JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public <R> R lookAhead(@NotNull Function1<? super LookAheadSession, ? extends R> visitor) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        if (this.state != ReadWriteBufferState.Terminated.INSTANCE) {
            R r = null;
            boolean z = false;
            if (setupStateForRead() != null) {
                try {
                    if (this.state.capacity.availableForRead != 0) {
                        r = visitor.mo12165invoke(this);
                        z = true;
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate();
                }
            }
            if (!z) {
                return visitor.mo12165invoke(TerminatedLookAhead.INSTANCE);
            }
            if (r == null) {
                Intrinsics.throwNpe();
            }
            return r;
        }
        return visitor.mo12165invoke(TerminatedLookAhead.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0181  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <R> java.lang.Object lookAheadSuspend(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super kotlinx.coroutines.io.LookAheadSuspendSession, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r13) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.lookAheadSuspend(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object read(int i, @NotNull Function1<? super ByteBuffer, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        boolean z = true;
        boolean z2 = false;
        if (i >= 0) {
            ByteBuffer byteBuffer = setupStateForRead();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = this.state.capacity;
                try {
                    if (ringBufferCapacity.availableForRead != 0) {
                        int i2 = ringBufferCapacity.availableForRead;
                        if (i2 <= 0 || i2 < i) {
                            z = false;
                        } else {
                            int position = byteBuffer.position();
                            int limit = byteBuffer.limit();
                            function1.mo12165invoke(byteBuffer);
                            if (limit == byteBuffer.limit()) {
                                int position2 = byteBuffer.position() - position;
                                if (position2 >= 0) {
                                    if (ringBufferCapacity.tryReadExact(position2)) {
                                        bytesRead(byteBuffer, ringBufferCapacity, position2);
                                    } else {
                                        throw new IllegalStateException();
                                    }
                                } else {
                                    throw new IllegalStateException("position has been moved backward: pushback is not supported");
                                }
                            } else {
                                throw new IllegalStateException("buffer limit modified");
                            }
                        }
                        z2 = Boxing.boxBoolean(z).booleanValue();
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate();
                }
            }
            if (z2) {
                return Unit.INSTANCE;
            }
            return isClosedForRead() ? Unit.INSTANCE : readBlockSuspend(i, function1, continuation);
        }
        throw new IllegalArgumentException("min should be positive or zero".toString());
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(bArr, i, i2);
        if (readAsMuchAsPossible == 0 && this.closed != null) {
            if (this.state.capacity.flush()) {
                return Boxing.boxInt(readAsMuchAsPossible(bArr, i, i2));
            }
            return Boxing.boxInt(-1);
        } else if (readAsMuchAsPossible <= 0 && i2 != 0) {
            return readAvailableSuspend(bArr, i, i2, continuation);
        } else {
            return Boxing.boxInt(readAsMuchAsPossible);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull byte[] r6, int r7, int r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$1
            r0.<init>(r5, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5c
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            int r6 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r6 = (kotlinx.coroutines.io.ByteBufferChannel) r6
            boolean r6 = r9 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L39
            goto L91
        L39:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r9 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L57
            goto L72
        L57:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        L5c:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L92
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.readSuspend(r4, r0)
            if (r9 != r1) goto L71
            return r1
        L71:
            r2 = r5
        L72:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L80
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L80:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.readAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L91
            return r1
        L91:
            return r9
        L92:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r6 = r9.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readBlockSuspend(int r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$readBlockSuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$readBlockSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readBlockSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readBlockSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readBlockSuspend$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L59
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            int r6 = r0.I$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r6 = (kotlinx.coroutines.io.ByteBufferChannel) r6
            boolean r6 = r8 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            goto L9b
        L37:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r8 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L54
            goto L71
        L54:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L59:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L9c
            int r8 = kotlin.ranges.RangesKt.coerceAtLeast(r6, r4)
            r0.L$0 = r5
            r0.I$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r5.readSuspend(r8, r0)
            if (r8 != r1) goto L70
            return r1
        L70:
            r2 = r5
        L71:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L8c
            if (r6 > 0) goto L7e
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L7e:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Got EOF but at least "
            java.lang.String r0 = " bytes were expected"
            java.lang.String r6 = com.android.tools.r8.GeneratedOutlineSupport1.outline52(r8, r6, r0)
            r7.<init>(r6)
            throw r7
        L8c:
            r0.L$0 = r2
            r0.I$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r2.read(r6, r7, r0)
            if (r8 != r1) goto L9b
            return r1
        L9b:
            return r8
        L9c:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readBlockSuspend(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004d  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBoolean(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r6 = this;
            java.nio.ByteBuffer r0 = access$setupStateForRead(r6)
            r1 = 0
            if (r0 == 0) goto L45
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = access$getState$p(r6)
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2.availableForRead     // Catch: java.lang.Throwable -> L3d
            if (r3 != 0) goto L18
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            goto L45
        L18:
            r3 = 1
            boolean r4 = r2.tryReadExact(r3)     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L2d
            byte r4 = r0.get()     // Catch: java.lang.Throwable -> L3d
            byte r5 = (byte) r1     // Catch: java.lang.Throwable -> L3d
            if (r4 == r5) goto L27
            r1 = r3
        L27:
            r6.bytesRead(r0, r2, r3)     // Catch: java.lang.Throwable -> L3d
            r0 = r1
            r1 = r3
            goto L2e
        L2d:
            r0 = r1
        L2e:
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)     // Catch: java.lang.Throwable -> L3d
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L3d
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            goto L46
        L3d:
            r7 = move-exception
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            throw r7
        L45:
            r0 = r1
        L46:
            if (r1 == 0) goto L4d
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            return r7
        L4d:
            java.lang.Object r7 = r6.readBooleanSuspend(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readBoolean(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readBooleanSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readBooleanSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readBooleanSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readBooleanSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readBooleanSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readBooleanSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L6f
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5c
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L78
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r4, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            r2 = r5
        L5c:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L70
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readBoolean(r0)
            if (r6 != r1) goto L6f
            return r1
        L6f:
            return r6
        L70:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF: one byte required"
            r6.<init>(r0)
            throw r6
        L78:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readBooleanSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readByte(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Byte> r6) {
        /*
            r5 = this;
            java.nio.ByteBuffer r0 = access$setupStateForRead(r5)
            r1 = 0
            if (r0 == 0) goto L41
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = access$getState$p(r5)
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2.availableForRead     // Catch: java.lang.Throwable -> L39
            if (r3 != 0) goto L18
            access$restoreStateAfterRead(r5)
            access$tryTerminate(r5)
            goto L41
        L18:
            r3 = 1
            boolean r4 = r2.tryReadExact(r3)     // Catch: java.lang.Throwable -> L39
            if (r4 == 0) goto L29
            byte r1 = r0.get()     // Catch: java.lang.Throwable -> L39
            r5.bytesRead(r0, r2, r3)     // Catch: java.lang.Throwable -> L39
            r0 = r1
            r1 = r3
            goto L2a
        L29:
            r0 = r1
        L2a:
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)     // Catch: java.lang.Throwable -> L39
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L39
            access$restoreStateAfterRead(r5)
            access$tryTerminate(r5)
            goto L42
        L39:
            r6 = move-exception
            access$restoreStateAfterRead(r5)
            access$tryTerminate(r5)
            throw r6
        L41:
            r0 = r1
        L42:
            if (r1 == 0) goto L49
            java.lang.Byte r6 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r0)
            return r6
        L49:
            java.lang.Object r6 = r5.readByteSuspend(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readByte(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readByteSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Byte> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readByteSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readByteSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readByteSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readByteSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readByteSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L6f
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5c
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L78
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r4, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            r2 = r5
        L5c:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L70
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readByte(r0)
            if (r6 != r1) goto L6f
            return r1
        L6f:
            return r6
        L70:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF: one byte required"
            r6.<init>(r0)
            throw r6
        L78:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readByteSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object readDouble(@NotNull Continuation<? super Double> continuation) {
        boolean z;
        ByteBuffer byteBuffer = setupStateForRead();
        boolean z2 = false;
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    if (ringBufferCapacity.tryReadExact(8)) {
                        if (byteBuffer.remaining() < 8) {
                            rollBytes(byteBuffer, 8);
                        }
                        double d2 = byteBuffer.getDouble();
                        bytesRead(byteBuffer, ringBufferCapacity, 8);
                        z = true;
                        d = d2;
                    } else {
                        z = false;
                    }
                    z2 = Boxing.boxBoolean(z).booleanValue();
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        if (z2) {
            return Boxing.boxDouble(d);
        }
        return readDoubleSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readDoubleSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Double> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readDoubleSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readDoubleSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readDoubleSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readDoubleSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readDoubleSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L71
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5e
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7a
            r6 = 8
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r6, r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            r2 = r5
        L5e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L72
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readDouble(r0)
            if (r6 != r1) goto L71
            return r1
        L71:
            return r6
        L72:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while a double expected"
            r6.<init>(r0)
            throw r6
        L7a:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readDoubleSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object readFloat(@NotNull Continuation<? super Float> continuation) {
        boolean z;
        ByteBuffer byteBuffer = setupStateForRead();
        boolean z2 = false;
        float f = 0.0f;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    if (ringBufferCapacity.tryReadExact(4)) {
                        if (byteBuffer.remaining() < 4) {
                            rollBytes(byteBuffer, 4);
                        }
                        float f2 = byteBuffer.getFloat();
                        bytesRead(byteBuffer, ringBufferCapacity, 4);
                        z = true;
                        f = f2;
                    } else {
                        z = false;
                    }
                    z2 = Boxing.boxBoolean(z).booleanValue();
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        if (z2) {
            return Boxing.boxFloat(f);
        }
        return readFloatSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0071  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFloatSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Float> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readFloatSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readFloatSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readFloatSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readFloatSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readFloatSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L70
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5d
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L79
            r6 = 4
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r6, r0)
            if (r6 != r1) goto L5c
            return r1
        L5c:
            r2 = r5
        L5d:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L71
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readFloat(r0)
            if (r6 != r1) goto L70
            return r1
        L70:
            return r6
        L71:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while an int expected"
            r6.<init>(r0)
            throw r6
        L79:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readFloatSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object readFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(bArr, i, i2);
        if (readAsMuchAsPossible < i2) {
            return readFullySuspend(bArr, i + readAsMuchAsPossible, i2 - readAsMuchAsPossible, continuation);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0066 -> B:27:0x0069). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r8, int r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$1
            r0.<init>(r7, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L48
            if (r2 != r3) goto L40
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r4 = (kotlinx.coroutines.io.ByteBufferChannel) r4
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3b
            r6 = r9
            r9 = r8
            r8 = r2
            r2 = r1
            r1 = r6
            goto L69
        L3b:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r8 = r10.exception
            throw r8
        L40:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L48:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L99
            r10 = r7
            r2 = r1
            r1 = r9
        L4f:
            boolean r4 = r8.hasRemaining()
            if (r4 == 0) goto L94
            r0.L$0 = r10
            r0.L$1 = r8
            r0.I$0 = r1
            r0.I$1 = r9
            r0.label = r3
            java.lang.Object r4 = r10.readSuspend(r3, r0)
            if (r4 != r2) goto L66
            return r2
        L66:
            r6 = r4
            r4 = r10
            r10 = r6
        L69:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L78
            int r10 = r4.readAsMuchAsPossible(r8)
            int r9 = r9 + r10
            r10 = r4
            goto L4f
        L78:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r10 = "Unexpected EOF: expected "
            java.lang.StringBuilder r10 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r10)
            int r8 = r8.remaining()
            r10.append(r8)
            java.lang.String r8 = " more bytes"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r9.<init>(r8)
            throw r9
        L94:
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            return r8
        L99:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r8 = r10.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readFullySuspend(java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readInt(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r6 = this;
            java.nio.ByteBuffer r0 = access$setupStateForRead(r6)
            r1 = 0
            if (r0 == 0) goto L4c
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = access$getState$p(r6)
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2.availableForRead     // Catch: java.lang.Throwable -> L44
            if (r3 != 0) goto L18
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            goto L4c
        L18:
            r3 = 4
            boolean r4 = r2.tryReadExact(r3)     // Catch: java.lang.Throwable -> L44
            if (r4 == 0) goto L31
            int r1 = r0.remaining()     // Catch: java.lang.Throwable -> L44
            if (r1 >= r3) goto L28
            r6.rollBytes(r0, r3)     // Catch: java.lang.Throwable -> L44
        L28:
            int r1 = r0.getInt()     // Catch: java.lang.Throwable -> L44
            r6.bytesRead(r0, r2, r3)     // Catch: java.lang.Throwable -> L44
            r0 = 1
            goto L32
        L31:
            r0 = r1
        L32:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)     // Catch: java.lang.Throwable -> L44
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L44
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            r5 = r1
            r1 = r0
            r0 = r5
            goto L4d
        L44:
            r7 = move-exception
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            throw r7
        L4c:
            r0 = r1
        L4d:
            if (r1 == 0) goto L54
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r7
        L54:
            java.lang.Object r7 = r6.readIntSuspend(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readInt(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0071  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readIntSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readIntSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readIntSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readIntSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readIntSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readIntSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L70
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5d
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L79
            r6 = 4
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r6, r0)
            if (r6 != r1) goto L5c
            return r1
        L5c:
            r2 = r5
        L5d:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L71
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readInt(r0)
            if (r6 != r1) goto L70
            return r1
        L70:
            return r6
        L71:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while an int expected"
            r6.<init>(r0)
            throw r6
        L79:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readIntSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object readLong(@NotNull Continuation<? super Long> continuation) {
        boolean z;
        ByteBuffer byteBuffer = setupStateForRead();
        boolean z2 = false;
        long j = 0;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    if (ringBufferCapacity.tryReadExact(8)) {
                        if (byteBuffer.remaining() < 8) {
                            rollBytes(byteBuffer, 8);
                        }
                        long j2 = byteBuffer.getLong();
                        bytesRead(byteBuffer, ringBufferCapacity, 8);
                        z = true;
                        j = j2;
                    } else {
                        z = false;
                    }
                    z2 = Boxing.boxBoolean(z).booleanValue();
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        if (z2) {
            return Boxing.boxLong(j);
        }
        return readLongSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readLongSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Long> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readLongSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readLongSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readLongSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readLongSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readLongSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L71
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L47
            goto L5e
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7a
            r6 = 8
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.readSuspend(r6, r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            r2 = r5
        L5e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L72
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r2.readLong(r0)
            if (r6 != r1) goto L71
            return r1
        L71:
            return r6
        L72:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while a long expected"
            r6.<init>(r0)
            throw r6
        L7a:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readLongSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readPacket(int i, int i2, @NotNull Continuation<? super ByteReadPacket> continuation) {
        Throwable cause;
        ClosedElement closedElement = this.closed;
        if (closedElement == null || (cause = closedElement.getCause()) == null) {
            if (i == 0) {
                return ByteReadPacket.Companion.getEmpty();
            }
            BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i2);
            ByteBuffer mo12351borrow = ObjectPoolKt.getBufferPool().mo12351borrow();
            while (i > 0) {
                try {
                    mo12351borrow.clear();
                    if (mo12351borrow.remaining() > i) {
                        mo12351borrow.limit(i);
                    }
                    int readAsMuchAsPossible = readAsMuchAsPossible(mo12351borrow);
                    if (readAsMuchAsPossible == 0) {
                        break;
                    }
                    mo12351borrow.flip();
                    BytePacketBuilder.writeFully(mo12351borrow);
                    i -= readAsMuchAsPossible;
                } catch (Throwable th) {
                    ObjectPoolKt.getBufferPool().recycle(mo12351borrow);
                    BytePacketBuilder.release();
                    throw th;
                }
            }
            if (i == 0) {
                ObjectPoolKt.getBufferPool().recycle(mo12351borrow);
                return BytePacketBuilder.build();
            }
            return readPacketSuspend(i, BytePacketBuilder, mo12351borrow, continuation);
        }
        throw cause;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[Catch: all -> 0x0042, TRY_ENTER, TryCatch #0 {all -> 0x0042, blocks: (B:12:0x0035, B:32:0x007a, B:25:0x0058, B:27:0x0061, B:28:0x0064, B:33:0x0089, B:15:0x003d, B:16:0x0041), top: B:43:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0089 A[Catch: all -> 0x0042, TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:12:0x0035, B:32:0x007a, B:25:0x0058, B:27:0x0061, B:28:0x0064, B:33:0x0089, B:15:0x003d, B:16:0x0041), top: B:43:0x0035 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0077 -> B:32:0x007a). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readPacketSuspend(int r8, @org.jetbrains.annotations.NotNull kotlinx.io.core.BytePacketBuilder r9, @org.jetbrains.annotations.NotNull java.nio.ByteBuffer r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.io.core.ByteReadPacket> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.io.ByteBufferChannel$readPacketSuspend$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.io.ByteBufferChannel$readPacketSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readPacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readPacketSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readPacketSuspend$1
            r0.<init>(r7, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4c
            if (r2 != r3) goto L44
            int r8 = r0.I$1
            java.lang.Object r9 = r0.L$2
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r0.L$1
            kotlinx.io.core.BytePacketBuilder r10 = (kotlinx.io.core.BytePacketBuilder) r10
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r4 = (kotlinx.coroutines.io.ByteBufferChannel) r4
            boolean r5 = r11 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L42
            if (r5 != 0) goto L3d
            r6 = r2
            r2 = r1
            r1 = r6
            goto L7a
        L3d:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch: java.lang.Throwable -> L42
            java.lang.Throwable r8 = r11.exception     // Catch: java.lang.Throwable -> L42
            throw r8     // Catch: java.lang.Throwable -> L42
        L42:
            r8 = move-exception
            goto L95
        L44:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4c:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La2
            r11 = r7
            r2 = r1
            r1 = r8
            r6 = r10
            r10 = r9
            r9 = r6
        L56:
            if (r8 <= 0) goto L89
            r9.clear()     // Catch: java.lang.Throwable -> L42
            int r4 = r9.remaining()     // Catch: java.lang.Throwable -> L42
            if (r4 <= r8) goto L64
            r9.limit(r8)     // Catch: java.lang.Throwable -> L42
        L64:
            r0.L$0 = r11     // Catch: java.lang.Throwable -> L42
            r0.I$0 = r1     // Catch: java.lang.Throwable -> L42
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L42
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L42
            r0.I$1 = r8     // Catch: java.lang.Throwable -> L42
            r0.label = r3     // Catch: java.lang.Throwable -> L42
            java.lang.Object r4 = r11.readFully(r9, r0)     // Catch: java.lang.Throwable -> L42
            if (r4 != r2) goto L77
            return r2
        L77:
            r6 = r4
            r4 = r11
            r11 = r6
        L7a:
            java.lang.Number r11 = (java.lang.Number) r11     // Catch: java.lang.Throwable -> L42
            int r11 = r11.intValue()     // Catch: java.lang.Throwable -> L42
            r9.flip()     // Catch: java.lang.Throwable -> L42
            r10.writeFully(r9)     // Catch: java.lang.Throwable -> L42
            int r8 = r8 - r11
            r11 = r4
            goto L56
        L89:
            kotlinx.io.core.ByteReadPacket r8 = r10.build()     // Catch: java.lang.Throwable -> L42
            kotlinx.io.pool.ObjectPool r10 = kotlinx.coroutines.io.internal.ObjectPoolKt.getBufferPool()
            r10.recycle(r9)
            return r8
        L95:
            r10.release()     // Catch: java.lang.Throwable -> L99
            throw r8     // Catch: java.lang.Throwable -> L99
        L99:
            r8 = move-exception
            kotlinx.io.pool.ObjectPool r10 = kotlinx.coroutines.io.internal.ObjectPoolKt.getBufferPool()
            r10.recycle(r9)
            throw r8
        La2:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readPacketSuspend(int, kotlinx.io.core.BytePacketBuilder, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readRemaining(long j, int i, @NotNull Continuation<? super ByteReadPacket> continuation) {
        if (isClosedForWrite()) {
            BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(i);
            try {
                IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(BytePacketBuilder, 1, null);
                while (true) {
                    if (prepareWriteHead.getWriteRemaining() > j) {
                        prepareWriteHead.resetForWrite((int) j);
                    }
                    j -= readAsMuchAsPossible$default(this, prepareWriteHead, 0, 0, 6, null);
                    if (!Boxing.boxBoolean(j > 0 && !isClosedForRead()).booleanValue()) {
                        UnsafeKt.afterHeadWrite(BytePacketBuilder, prepareWriteHead);
                        return BytePacketBuilder.build();
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(BytePacketBuilder, 1, prepareWriteHead);
                }
            } catch (Throwable th) {
                BytePacketBuilder.release();
                throw th;
            }
        } else {
            return readRemainingSuspend(j, i, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097 A[Catch: all -> 0x0129, TryCatch #3 {all -> 0x0129, blocks: (B:29:0x0091, B:31:0x0097, B:32:0x009d, B:34:0x00c0), top: B:76:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c0 A[Catch: all -> 0x0129, TRY_LEAVE, TryCatch #3 {all -> 0x0129, blocks: (B:29:0x0091, B:31:0x0097, B:32:0x009d, B:34:0x00c0), top: B:76:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0111 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1, types: [kotlinx.io.core.BytePacketBuilder] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [kotlinx.io.core.Output] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00e4 -> B:41:0x00ea). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0105 -> B:72:0x0107). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readRemainingSuspend(long r19, int r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.io.core.ByteReadPacket> r22) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readRemainingSuspend(long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @ExperimentalIoApi
    public void readSession(@NotNull Function1<? super ReadSession, Unit> consumer) {
        Intrinsics.checkParameterIsNotNull(consumer, "consumer");
        lookAhead(new ByteBufferChannel$readSession$1(this, consumer));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readShort(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Short> r7) {
        /*
            r6 = this;
            java.nio.ByteBuffer r0 = access$setupStateForRead(r6)
            r1 = 0
            if (r0 == 0) goto L4c
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = access$getState$p(r6)
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2.availableForRead     // Catch: java.lang.Throwable -> L44
            if (r3 != 0) goto L18
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            goto L4c
        L18:
            r3 = 2
            boolean r4 = r2.tryReadExact(r3)     // Catch: java.lang.Throwable -> L44
            if (r4 == 0) goto L31
            int r1 = r0.remaining()     // Catch: java.lang.Throwable -> L44
            if (r1 >= r3) goto L28
            r6.rollBytes(r0, r3)     // Catch: java.lang.Throwable -> L44
        L28:
            short r1 = r0.getShort()     // Catch: java.lang.Throwable -> L44
            r6.bytesRead(r0, r2, r3)     // Catch: java.lang.Throwable -> L44
            r0 = 1
            goto L32
        L31:
            r0 = r1
        L32:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)     // Catch: java.lang.Throwable -> L44
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L44
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            r5 = r1
            r1 = r0
            r0 = r5
            goto L4d
        L44:
            r7 = move-exception
            access$restoreStateAfterRead(r6)
            access$tryTerminate(r6)
            throw r7
        L4c:
            r0 = r1
        L4d:
            if (r1 == 0) goto L54
            java.lang.Short r7 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r0)
            return r7
        L54:
            java.lang.Object r7 = r6.readShortSuspend(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readShort(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readShortSuspend(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Short> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteBufferChannel$readShortSuspend$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteBufferChannel$readShortSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readShortSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readShortSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readShortSuspend$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L4c
            if (r2 == r3) goto L3e
            if (r2 != r4) goto L36
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L31
            goto L6f
        L31:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r3 = r6 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L47
            goto L5c
        L47:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        L4c:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L78
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.readSuspend(r4, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            r2 = r5
        L5c:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L70
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r6 = r2.readShort(r0)
            if (r6 != r1) goto L6f
            return r1
        L6f:
            return r6
        L70:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while byte expected"
            r6.<init>(r0)
            throw r6
        L78:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r6 = r6.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readShortSuspend(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    final /* synthetic */ Object readSuspend(int i, @NotNull Continuation<? super Boolean> continuation) {
        boolean z = true;
        if (this.state.capacity.availableForRead >= i) {
            return Boxing.boxBoolean(true);
        }
        ClosedElement closedElement = this.closed;
        if (closedElement == null) {
            if (i == 1) {
                return readSuspendImpl(1, continuation);
            }
            return readSuspendLoop(i, continuation);
        } else if (closedElement.getCause() == null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            if (!ringBufferCapacity.flush() || ringBufferCapacity.availableForRead < i) {
                z = false;
            }
            if (this.readOp == null) {
                return Boxing.boxBoolean(z);
            }
            throw new IllegalStateException("Read operation is already in progress");
        } else {
            throw closedElement.getCause();
        }
    }

    @Nullable
    final /* synthetic */ Object readSuspendImpl(int i, @NotNull Continuation<? super Boolean> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        ReadWriteBufferState readWriteBufferState = this.state;
        boolean z = false;
        if (readWriteBufferState.capacity.availableForRead < i && (this.joining == null || this.writeOp == null || (readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE && !(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty)))) {
            z = true;
        }
        if (z) {
            CancellableReusableContinuation cancellableReusableContinuation = this.readSuspendContinuationCache;
            suspensionForSize(i, cancellableReusableContinuation);
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            Object completeSuspendBlock = cancellableReusableContinuation.completeSuspendBlock(intercepted);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (completeSuspendBlock == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return completeSuspendBlock;
        }
        return Boxing.boxBoolean(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x0098 -> B:14:0x0037). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readSuspendLoop(int r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteBufferChannel$readSuspendLoop$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteBufferChannel$readSuspendLoop$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readSuspendLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readSuspendLoop$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readSuspendLoop$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L47
            if (r2 != r4) goto L3f
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.io.internal.RingBufferCapacity r8 = (kotlinx.coroutines.io.internal.RingBufferCapacity) r8
            int r8 = r0.I$0
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r9 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3a
            r6 = r0
            r0 = r8
            r8 = r2
        L37:
            r2 = r1
            r1 = r6
            goto L9c
        L3a:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r8 = r9.exception
            throw r8
        L3f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L47:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lad
            r9 = r8
            r8 = r7
        L4d:
            kotlinx.coroutines.io.internal.ReadWriteBufferState r2 = r8.state
            kotlinx.coroutines.io.internal.RingBufferCapacity r2 = r2.capacity
            int r5 = r2.availableForRead
            if (r5 < r9) goto L5a
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r8
        L5a:
            kotlinx.coroutines.io.ByteBufferChannel$ClosedElement r5 = r8.closed
            if (r5 == 0) goto L89
            java.lang.Throwable r0 = r5.getCause()
            if (r0 != 0) goto L84
            kotlinx.coroutines.io.internal.ReadWriteBufferState r0 = r8.state
            kotlinx.coroutines.io.internal.RingBufferCapacity r0 = r0.capacity
            boolean r1 = r0.flush()
            if (r1 == 0) goto L73
            int r0 = r0.availableForRead
            if (r0 < r9) goto L73
            r3 = r4
        L73:
            kotlin.coroutines.Continuation<? super java.lang.Boolean> r8 = r8.readOp
            if (r8 != 0) goto L7c
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        L7c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Read operation is already in progress"
            r8.<init>(r9)
            throw r8
        L84:
            java.lang.Throwable r8 = r5.getCause()
            throw r8
        L89:
            r0.L$0 = r8
            r0.I$0 = r9
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r2 = r8.readSuspendImpl(r9, r0)
            if (r2 != r1) goto L98
            return r1
        L98:
            r6 = r0
            r0 = r9
            r9 = r2
            goto L37
        L9c:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto La9
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r8
        La9:
            r9 = r0
            r0 = r1
            r1 = r2
            goto L4d
        Lad:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r8 = r9.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readSuspendLoop(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @ExperimentalIoApi
    @Nullable
    public Object readSuspendableSession(@NotNull Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return lookAheadSuspend(new ByteBufferChannel$readSuspendableSession$2(this, function2, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object readUTF8Line(int r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteBufferChannel$readUTF8Line$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteBufferChannel$readUTF8Line$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readUTF8Line$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readUTF8Line$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readUTF8Line$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.L$1
            java.lang.StringBuilder r6 = (java.lang.StringBuilder) r6
            int r1 = r0.I$0
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r7 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L37
            r4 = r7
            r7 = r6
            r6 = r4
            goto L5c
        L37:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L6b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r0.L$0 = r5
            r0.I$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r6 = r5.readUTF8LineTo(r7, r6, r0)
            if (r6 != r1) goto L5c
            return r1
        L5c:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L66
            r6 = 0
            return r6
        L66:
            java.lang.String r6 = r7.toString()
            return r6
        L6b:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readUTF8Line(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public <A extends Appendable> Object readUTF8LineTo(@NotNull A a, int i, @NotNull Continuation<? super Boolean> continuation) {
        return readUTF8LineToAscii(a, i, continuation);
    }

    @Nullable
    final /* synthetic */ Object readUTF8LineToAscii(@NotNull Appendable appendable, int i, @NotNull Continuation<? super Boolean> continuation) {
        if (this.state == ReadWriteBufferState.Terminated.INSTANCE) {
            return Boxing.boxBoolean(false);
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        char[] cArr = new char[8192];
        CharBuffer wrap = CharBuffer.wrap(cArr);
        if (wrap == null) {
            Intrinsics.throwNpe();
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        lookAhead(new ByteBufferChannel$readUTF8LineToAscii$2(this, booleanRef, appendable, cArr, wrap, intRef, i));
        if (booleanRef.element) {
            return Boxing.boxBoolean(true);
        }
        if (intRef.element == 0 && isClosedForRead()) {
            return Boxing.boxBoolean(false);
        }
        int i2 = intRef.element;
        return readUTF8LineToUtf8Suspend(appendable, i - i2, cArr, wrap, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readUTF8LineToUtf8Suspend(@org.jetbrains.annotations.NotNull java.lang.Appendable r19, int r20, @org.jetbrains.annotations.NotNull char[] r21, @org.jetbrains.annotations.NotNull java.nio.CharBuffer r22, int r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r24) {
        /*
            r18 = this;
            r10 = r18
            r0 = r24
            boolean r1 = r0 instanceof kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1
            if (r1 == 0) goto L17
            r1 = r0
            kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1 r1 = (kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.label = r2
            goto L1c
        L17:
            kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1 r1 = new kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1
            r1.<init>(r10, r0)
        L1c:
            r11 = r1
            java.lang.Object r0 = r11.result
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r13 = 1
            if (r1 == 0) goto L59
            if (r1 != r13) goto L51
            java.lang.Object r1 = r11.L$5
            kotlin.jvm.internal.Ref$BooleanRef r1 = (kotlin.jvm.internal.Ref.BooleanRef) r1
            java.lang.Object r2 = r11.L$4
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            int r2 = r11.I$1
            java.lang.Object r2 = r11.L$3
            java.nio.CharBuffer r2 = (java.nio.CharBuffer) r2
            java.lang.Object r2 = r11.L$2
            char[] r2 = (char[]) r2
            int r2 = r11.I$0
            java.lang.Object r2 = r11.L$1
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            java.lang.Object r2 = r11.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r2 = r0 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L4c
            r15 = r1
            goto Lab
        L4c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        L51:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L59:
            boolean r1 = r0 instanceof kotlin.Result.Failure
            if (r1 != 0) goto Lb2
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r0 = 0
            r14.element = r0
            kotlin.jvm.internal.Ref$BooleanRef r15 = new kotlin.jvm.internal.Ref$BooleanRef
            r15.<init>()
            r15.element = r13
            kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2 r9 = new kotlinx.coroutines.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2
            r16 = 0
            r0 = r9
            r1 = r18
            r2 = r19
            r3 = r21
            r4 = r22
            r5 = r14
            r6 = r20
            r7 = r23
            r8 = r15
            r17 = r9
            r9 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r11.L$0 = r10
            r0 = r19
            r11.L$1 = r0
            r0 = r20
            r11.I$0 = r0
            r0 = r21
            r11.L$2 = r0
            r0 = r22
            r11.L$3 = r0
            r0 = r23
            r11.I$1 = r0
            r11.L$4 = r14
            r11.L$5 = r15
            r11.label = r13
            r0 = r17
            java.lang.Object r0 = r10.lookAheadSuspend(r0, r11)
            if (r0 != r12) goto Lab
            return r12
        Lab:
            boolean r0 = r15.element
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            return r0
        Lb2:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readUTF8LineToUtf8Suspend(java.lang.Appendable, int, char[], java.nio.CharBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.LookAheadSession
    @Nullable
    public ByteBuffer request(int i, int i2) {
        ReadWriteBufferState readWriteBufferState = this.state;
        int i3 = readWriteBufferState.capacity.availableForRead;
        int i4 = this.readPosition;
        if (i3 < i2 + i) {
            return null;
        }
        if (!readWriteBufferState.getIdle() && ((readWriteBufferState instanceof ReadWriteBufferState.Reading) || (readWriteBufferState instanceof ReadWriteBufferState.ReadingWriting))) {
            ByteBuffer readBuffer = readWriteBufferState.getReadBuffer();
            prepareBuffer(readBuffer, getReadByteOrder(), carryIndex(readBuffer, i4 + i), i3 - i);
            if (readBuffer.remaining() < i2) {
                return null;
            }
            return readBuffer;
        } else if (setupStateForRead() == null) {
            return null;
        } else {
            return request(i, i2);
        }
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public void setReadByteOrder(@NotNull ByteOrder byteOrder) {
        Intrinsics.checkParameterIsNotNull(byteOrder, "<set-?>");
        this.readByteOrder = byteOrder;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    public void setWriteByteOrder(@NotNull ByteOrder newOrder) {
        ByteBufferChannel delegatedTo;
        Intrinsics.checkParameterIsNotNull(newOrder, "newOrder");
        if (this.writeByteOrder != newOrder) {
            this.writeByteOrder = newOrder;
            JoiningState joiningState = this.joining;
            if (joiningState == null || (delegatedTo = joiningState.getDelegatedTo()) == null) {
                return;
            }
            delegatedTo.setWriteByteOrder(newOrder);
        }
    }

    @Nullable
    final /* synthetic */ Object tryWriteByte(@NotNull ByteBuffer byteBuffer, byte b, @NotNull RingBufferCapacity ringBufferCapacity, @NotNull Continuation<? super Unit> continuation) {
        if (!ringBufferCapacity.tryWriteExact(1)) {
            return writeByteSuspend(byteBuffer, b, ringBufferCapacity, continuation);
        }
        doWrite(byteBuffer, b, ringBufferCapacity);
        return Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object tryWriteShort(@NotNull ByteBuffer byteBuffer, short s, @NotNull RingBufferCapacity ringBufferCapacity, @NotNull Continuation<? super Unit> continuation) {
        if (!ringBufferCapacity.tryWriteExact(2)) {
            return writeShortSuspend(byteBuffer, s, ringBufferCapacity, continuation);
        }
        doWrite(byteBuffer, s, ringBufferCapacity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object tryWriteSuspend(int i, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        Throwable sendException;
        if (!writeSuspendPredicate(i)) {
            ClosedElement closedElement = this.closed;
            if (closedElement != null && (sendException = closedElement.getSendException()) != null) {
                throw sendException;
            }
            return Unit.INSTANCE;
        }
        this.writeSuspensionSize = i;
        if (this.attachedJob == null) {
            CancellableReusableContinuation cancellableReusableContinuation = this.writeSuspendContinuationCache;
            this.writeSuspension.mo12165invoke(cancellableReusableContinuation);
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            Object completeSuspendBlock = cancellableReusableContinuation.completeSuspendBlock(intercepted);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (completeSuspendBlock == coroutine_suspended) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return completeSuspendBlock;
        }
        Object mo12165invoke = this.writeSuspension.mo12165invoke(continuation);
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (mo12165invoke == coroutine_suspended2) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return mo12165invoke;
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object write(int i, @NotNull Function1<? super ByteBuffer, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel byteBufferChannel;
        boolean z = false;
        if (i > 0) {
            JoiningState joiningState = this.joining;
            if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
                byteBufferChannel = this;
            }
            ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
                long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
                try {
                    ClosedElement closedElement = byteBufferChannel.closed;
                    if (closedElement == null) {
                        int tryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(i);
                        if (tryWriteAtLeast > 0) {
                            int position = byteBuffer.position();
                            int limit = byteBuffer.limit();
                            function1.mo12165invoke(byteBuffer);
                            if (limit == byteBuffer.limit()) {
                                int position2 = byteBuffer.position() - position;
                                if (position2 >= 0) {
                                    byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, position2);
                                    if (position2 < tryWriteAtLeast) {
                                        ringBufferCapacity.completeRead(tryWriteAtLeast - position2);
                                    }
                                    z = true;
                                } else {
                                    throw new IllegalStateException("position has been moved backward: pushback is not supported");
                                }
                            } else {
                                throw new IllegalStateException("buffer limit modified");
                            }
                        }
                    } else {
                        throw closedElement.getSendException();
                    }
                } finally {
                    if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                        byteBufferChannel.flush();
                    }
                    if (byteBufferChannel != this) {
                        this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                    }
                    byteBufferChannel.restoreStateAfterWrite();
                    byteBufferChannel.tryTerminate();
                }
            }
            if (!z) {
                return writeBlockSuspend(i, function1, continuation);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("min should be positive".toString());
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeAvailable(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation2 = resolveDelegation(this, joiningState)) == null) {
            int writeAsMuchAsPossible = writeAsMuchAsPossible(byteBuffer);
            if (writeAsMuchAsPossible > 0) {
                return Boxing.boxInt(writeAsMuchAsPossible);
            }
            JoiningState joiningState2 = this.joining;
            return (joiningState2 == null || (resolveDelegation = resolveDelegation(this, joiningState2)) == null) ? writeAvailableSuspend(byteBuffer, continuation) : resolveDelegation.writeAvailableSuspend(byteBuffer, continuation);
        }
        return resolveDelegation2.writeAvailable(byteBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad A[PHI: r8 
      PHI: (r8v8 java.lang.Object) = (r8v5 java.lang.Object), (r8v1 java.lang.Object) binds: [B:45:0x00aa, B:14:0x0035] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeAvailableSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L72
            if (r2 == r5) goto L60
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L39
            goto Lad
        L39:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L5b
            goto L9f
        L5b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L60:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L6d
            goto L84
        L6d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L72:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lae
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L83
            return r1
        L83:
            r2 = r6
        L84:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r8 = r2.joining
            if (r8 == 0) goto La0
            kotlinx.coroutines.io.ByteBufferChannel r5 = r2.resolveDelegation(r2, r8)
            if (r5 == 0) goto La0
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r5
            r0.label = r4
            java.lang.Object r8 = r5.writeAvailableSuspend(r7, r0)
            if (r8 != r1) goto L9f
            return r1
        L9f:
            return r8
        La0:
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r2.writeAvailable(r7, r0)
            if (r8 != r1) goto Lad
            return r1
        Lad:
            return r8
        Lae:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba A[PHI: r9 
      PHI: (r9v8 java.lang.Object) = (r9v5 java.lang.Object), (r9v1 java.lang.Object) binds: [B:45:0x00b7, B:14:0x0037] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeBlockSuspend(int r7, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeBlockSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.io.ByteBufferChannel$writeBlockSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeBlockSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeBlockSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeBlockSuspend$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L79
            if (r2 == r5) goto L64
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            int r7 = r0.I$0
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r9 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L3b
            goto Lba
        L3b:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L40:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L48:
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            int r7 = r0.I$0
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r9 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L5f
            goto Laa
        L5f:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L64:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            int r7 = r0.I$0
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r9 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L74
            goto L8d
        L74:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        L79:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lbb
            r0.L$0 = r6
            r0.I$0 = r7
            r0.L$1 = r8
            r0.label = r5
            java.lang.Object r9 = r6.writeSuspend(r7, r0)
            if (r9 != r1) goto L8c
            return r1
        L8c:
            r2 = r6
        L8d:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r9 = r2.joining
            if (r9 == 0) goto Lab
            kotlinx.coroutines.io.ByteBufferChannel r5 = r2.resolveDelegation(r2, r9)
            if (r5 == 0) goto Lab
            r0.L$0 = r2
            r0.I$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r5
            r0.label = r4
            java.lang.Object r9 = r5.write(r7, r8, r0)
            if (r9 != r1) goto Laa
            return r1
        Laa:
            return r9
        Lab:
            r0.L$0 = r2
            r0.I$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.write(r7, r8, r0)
            if (r9 != r1) goto Lba
            return r1
        Lba:
            return r9
        Lbb:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r7 = r9.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeBlockSuspend(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeByte(byte b, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            ByteBuffer byteBuffer = setupStateForWrite();
            if (byteBuffer != null) {
                return tryWriteByte(byteBuffer, b, this.state.capacity, continuation);
            }
            return delegateByte(b, continuation);
        }
        return resolveDelegation.writeByte(b, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ae  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeByteSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r7, byte r8, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.internal.RingBufferCapacity r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeByteSuspend(java.nio.ByteBuffer, byte, kotlinx.coroutines.io.internal.RingBufferCapacity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeDouble(double d, @NotNull Continuation<? super Unit> continuation) {
        return writeLong(Double.doubleToRawLongBits(d), continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFloat(float f, @NotNull Continuation<? super Unit> continuation) {
        return writeInt(Float.floatToRawIntBits(f), continuation);
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            writeAsMuchAsPossible(byteBuffer);
            return !byteBuffer.hasRemaining() ? Unit.INSTANCE : writeFullySuspend(byteBuffer, continuation);
        }
        return resolveDelegation.writeFully(byteBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0072 -> B:32:0x0075). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeFullySuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5d
            if (r2 == r4) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L3d
            goto L90
        L3d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L42:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4a:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L58
            r8 = r2
            goto L75
        L58:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L5d:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L98
            r8 = r6
        L62:
            boolean r2 = r7.hasRemaining()
            if (r2 == 0) goto L95
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r2 = r8.tryWriteSuspend(r4, r0)
            if (r2 != r1) goto L75
            return r1
        L75:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r2 = r8.joining
            if (r2 == 0) goto L91
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.resolveDelegation(r8, r2)
            if (r5 == 0) goto L91
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r5
            r0.label = r3
            java.lang.Object r8 = r5.writeFully(r7, r0)
            if (r8 != r1) goto L90
            return r1
        L90:
            return r8
        L91:
            r8.writeAsMuchAsPossible(r7)
            goto L62
        L95:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L98:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeFullySuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeInt(int i, @NotNull Continuation<? super Unit> continuation) {
        ByteBuffer byteBuffer = setupStateForWrite();
        if (byteBuffer == null) {
            JoiningState joiningState = this.joining;
            if (joiningState == null) {
                Intrinsics.throwNpe();
            }
            ByteBufferChannel resolveDelegation = resolveDelegation(this, joiningState);
            if (resolveDelegation != null && resolveDelegation != this) {
                return resolveDelegation.writeInt(i, continuation);
            }
            JoiningState joiningState2 = this.joining;
            if (joiningState2 == null) {
                Intrinsics.throwNpe();
            }
            return delegateSuspend(joiningState2, new ByteBufferChannel$writeInt$2(i, null), continuation);
        }
        RingBufferCapacity ringBufferCapacity = this.state.capacity;
        if (tryWriteInt(byteBuffer, i, ringBufferCapacity)) {
            return Unit.INSTANCE;
        }
        return writeIntSuspend(byteBuffer, i, ringBufferCapacity, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0079 -> B:34:0x007c). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeIntSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r9, int r10, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.internal.RingBufferCapacity r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeIntSuspend$1
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.io.ByteBufferChannel$writeIntSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeIntSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeIntSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeIntSuspend$1
            r0.<init>(r8, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L65
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.io.internal.RingBufferCapacity r9 = (kotlinx.coroutines.io.internal.RingBufferCapacity) r9
            int r9 = r0.I$0
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r9 = (kotlinx.coroutines.io.ByteBufferChannel) r9
            boolean r9 = r12 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L3b
            goto L94
        L3b:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r9 = r12.exception
            throw r9
        L40:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L48:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.io.internal.RingBufferCapacity r9 = (kotlinx.coroutines.io.internal.RingBufferCapacity) r9
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$1
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r12 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L63
            if (r5 != 0) goto L5e
            r7 = r11
            r11 = r9
            r9 = r7
            goto L7c
        L5e:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch: java.lang.Throwable -> L63
            java.lang.Throwable r9 = r12.exception     // Catch: java.lang.Throwable -> L63
            throw r9     // Catch: java.lang.Throwable -> L63
        L63:
            r9 = move-exception
            goto Laa
        L65:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lb1
            r2 = r8
        L6a:
            r12 = 4
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L63
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L63
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L63
            r0.L$2 = r11     // Catch: java.lang.Throwable -> L63
            r0.label = r4     // Catch: java.lang.Throwable -> L63
            java.lang.Object r12 = r2.writeSuspend(r12, r0)     // Catch: java.lang.Throwable -> L63
            if (r12 != r1) goto L7c
            return r1
        L7c:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r12 = r2.joining
            if (r12 == 0) goto L95
            r2.restoreStateAfterWrite()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.I$0 = r10
            r0.L$2 = r11
            r0.label = r3
            java.lang.Object r12 = r2.delegateInt(r10, r0)
            if (r12 != r1) goto L94
            return r1
        L94:
            return r12
        L95:
            kotlinx.io.core.ByteOrder r12 = r2.getWriteByteOrder()
            int r5 = r2.writePosition
            int r6 = r11.availableForWrite
            r2.prepareBuffer(r9, r12, r5, r6)
            boolean r12 = r2.tryWriteInt(r9, r10, r11)
            if (r12 != 0) goto La7
            goto L6a
        La7:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        Laa:
            r2.restoreStateAfterWrite()
            r2.tryTerminate()
            throw r9
        Lb1:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r9 = r12.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeIntSuspend(java.nio.ByteBuffer, int, kotlinx.coroutines.io.internal.RingBufferCapacity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeLong(long j, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            ByteBuffer byteBuffer = setupStateForWrite();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = this.state.capacity;
                if (!tryWriteLong(byteBuffer, j, ringBufferCapacity)) {
                    return writeLongSuspend(byteBuffer, j, ringBufferCapacity, continuation);
                }
                return Unit.INSTANCE;
            }
            return delegateLong(j, continuation);
        }
        return resolveDelegation.writeLong(j, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x007a -> B:34:0x007d). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeLongSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r9, long r10, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.internal.RingBufferCapacity r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeLongSuspend$1
            if (r0 == 0) goto L13
            r0 = r13
            kotlinx.coroutines.io.ByteBufferChannel$writeLongSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeLongSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeLongSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeLongSuspend$1
            r0.<init>(r8, r13)
        L18:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L65
            if (r2 == r4) goto L48
            if (r2 != r3) goto L40
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.io.internal.RingBufferCapacity r9 = (kotlinx.coroutines.io.internal.RingBufferCapacity) r9
            long r9 = r0.J$0
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r9 = (kotlinx.coroutines.io.ByteBufferChannel) r9
            boolean r9 = r13 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L3b
            goto L95
        L3b:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r9 = r13.exception
            throw r9
        L40:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L48:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.io.internal.RingBufferCapacity r9 = (kotlinx.coroutines.io.internal.RingBufferCapacity) r9
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r13 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L63
            if (r5 != 0) goto L5e
            r7 = r12
            r12 = r9
            r9 = r7
            goto L7d
        L5e:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch: java.lang.Throwable -> L63
            java.lang.Throwable r9 = r13.exception     // Catch: java.lang.Throwable -> L63
            throw r9     // Catch: java.lang.Throwable -> L63
        L63:
            r9 = move-exception
            goto Lab
        L65:
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lb2
            r2 = r8
        L6a:
            r13 = 8
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L63
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L63
            r0.J$0 = r10     // Catch: java.lang.Throwable -> L63
            r0.L$2 = r12     // Catch: java.lang.Throwable -> L63
            r0.label = r4     // Catch: java.lang.Throwable -> L63
            java.lang.Object r13 = r2.writeSuspend(r13, r0)     // Catch: java.lang.Throwable -> L63
            if (r13 != r1) goto L7d
            return r1
        L7d:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r13 = r2.joining
            if (r13 == 0) goto L96
            r2.restoreStateAfterWrite()
            r0.L$0 = r2
            r0.L$1 = r9
            r0.J$0 = r10
            r0.L$2 = r12
            r0.label = r3
            java.lang.Object r13 = r2.delegateLong(r10, r0)
            if (r13 != r1) goto L95
            return r1
        L95:
            return r13
        L96:
            kotlinx.io.core.ByteOrder r13 = r2.getWriteByteOrder()
            int r5 = r2.writePosition
            int r6 = r12.availableForWrite
            r2.prepareBuffer(r9, r13, r5, r6)
            boolean r13 = r2.tryWriteLong(r9, r10, r12)
            if (r13 != 0) goto La8
            goto L6a
        La8:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        Lab:
            r2.restoreStateAfterWrite()
            r2.tryTerminate()
            throw r9
        Lb2:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r9 = r13.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeLongSuspend(java.nio.ByteBuffer, long, kotlinx.coroutines.io.internal.RingBufferCapacity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writePacket(@NotNull ByteReadPacket byteReadPacket, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation2 = resolveDelegation(this, joiningState)) == null) {
            do {
                try {
                    if (!byteReadPacket.isNotEmpty()) {
                        break;
                    }
                } catch (Throwable th) {
                    byteReadPacket.release();
                    throw th;
                }
            } while (tryWritePacketPart(byteReadPacket) != 0);
            if (byteReadPacket.m12336getRemaining() > 0) {
                JoiningState joiningState2 = this.joining;
                return (joiningState2 == null || (resolveDelegation = resolveDelegation(this, joiningState2)) == null) ? writePacketSuspend(byteReadPacket, continuation) : resolveDelegation.writePacket(byteReadPacket, continuation);
            }
            return Unit.INSTANCE;
        }
        return resolveDelegation2.writePacket(byteReadPacket, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a A[Catch: all -> 0x005d, TryCatch #0 {all -> 0x005d, blocks: (B:13:0x0038, B:16:0x003d, B:17:0x0041, B:21:0x0052, B:36:0x0077, B:38:0x007b, B:40:0x0081, B:45:0x0096, B:31:0x0064, B:33:0x006a, B:24:0x0058, B:25:0x005c), top: B:53:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0074 -> B:36:0x0077). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writePacketSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.ByteReadPacket r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$writePacketSuspend$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$writePacketSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writePacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writePacketSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writePacketSuspend$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5f
            if (r2 == r4) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.ByteReadPacket r7 = (kotlinx.io.core.ByteReadPacket) r7
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r0 = (kotlinx.coroutines.io.ByteBufferChannel) r0
            boolean r0 = r8 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L5d
            if (r0 != 0) goto L3d
            goto L92
        L3d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8     // Catch: java.lang.Throwable -> L5d
            java.lang.Throwable r8 = r8.exception     // Catch: java.lang.Throwable -> L5d
            throw r8     // Catch: java.lang.Throwable -> L5d
        L42:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4a:
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.ByteReadPacket r7 = (kotlinx.io.core.ByteReadPacket) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L5d
            if (r5 != 0) goto L58
            r8 = r2
            goto L77
        L58:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8     // Catch: java.lang.Throwable -> L5d
            java.lang.Throwable r8 = r8.exception     // Catch: java.lang.Throwable -> L5d
            throw r8     // Catch: java.lang.Throwable -> L5d
        L5d:
            r8 = move-exception
            goto La0
        L5f:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La4
            r8 = r6
        L64:
            boolean r2 = r7.isNotEmpty()     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L9a
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L5d
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L5d
            r0.label = r4     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r2 = r8.writeSuspend(r4, r0)     // Catch: java.lang.Throwable -> L5d
            if (r2 != r1) goto L77
            return r1
        L77:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r2 = r8.joining     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L96
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.resolveDelegation(r8, r2)     // Catch: java.lang.Throwable -> L5d
            if (r5 == 0) goto L96
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L5d
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L5d
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L5d
            r0.L$3 = r5     // Catch: java.lang.Throwable -> L5d
            r0.label = r3     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r8 = r5.writePacket(r7, r0)     // Catch: java.lang.Throwable -> L5d
            if (r8 != r1) goto L92
            return r1
        L92:
            r7.release()
            return r8
        L96:
            r8.tryWritePacketPart(r7)     // Catch: java.lang.Throwable -> L5d
            goto L64
        L9a:
            r7.release()
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        La0:
            r7.release()
            throw r8
        La4:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writePacketSuspend(kotlinx.io.core.ByteReadPacket, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeShort(short s, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            ByteBuffer byteBuffer = setupStateForWrite();
            if (byteBuffer != null) {
                return tryWriteShort(byteBuffer, s, this.state.capacity, continuation);
            }
            return delegateShort(s, continuation);
        }
        return resolveDelegation.writeShort(s, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ae  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeShortSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r7, short r8, @org.jetbrains.annotations.NotNull kotlinx.coroutines.io.internal.RingBufferCapacity r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeShortSuspend(java.nio.ByteBuffer, short, kotlinx.coroutines.io.internal.RingBufferCapacity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x007f -> B:30:0x0082). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object writeSuspend(@org.jetbrains.annotations.NotNull byte[] r8, int r9, int r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeSuspend$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.io.ByteBufferChannel$writeSuspend$1 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeSuspend$1 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeSuspend$1
            r0.<init>(r7, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L69
            if (r2 == r4) goto L4e
            if (r2 != r3) goto L46
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r8 = (kotlinx.coroutines.io.ByteBufferChannel) r8
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r8 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r8
            int r8 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r8 = (kotlinx.coroutines.io.ByteBufferChannel) r8
            boolean r8 = r11 instanceof kotlin.Result.Failure
            if (r8 != 0) goto L41
            goto La1
        L41:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        L46:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4e:
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            byte[] r10 = (byte[]) r10
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r11 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L64
            r11 = r8
            r8 = r2
            r6 = r10
            r10 = r9
            r9 = r6
            goto L82
        L64:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        L69:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lad
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
        L71:
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r10
            r0.I$1 = r11
            r0.label = r4
            java.lang.Object r2 = r8.tryWriteSuspend(r4, r0)
            if (r2 != r1) goto L82
            return r1
        L82:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r2 = r8.joining
            if (r2 == 0) goto La2
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.resolveDelegation(r8, r2)
            if (r5 == 0) goto La2
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r10
            r0.I$1 = r11
            r0.L$2 = r2
            r0.L$3 = r5
            r0.label = r3
            java.lang.Object r11 = r5.writeSuspend(r9, r10, r11, r0)
            if (r11 != r1) goto La1
            return r1
        La1:
            return r11
        La2:
            int r2 = r8.writeAsMuchAsPossible(r9, r10, r11)
            if (r2 <= 0) goto L71
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            return r8
        Lad:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r8 = r11.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a5, code lost:
        if (r1 != null) goto L44;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013b  */
    /* JADX WARN: Type inference failed for: r1v10, types: [T, kotlinx.io.core.IoBuffer] */
    /* JADX WARN: Type inference failed for: r1v14, types: [kotlinx.coroutines.io.internal.RingBufferCapacity, T] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, java.nio.ByteBuffer] */
    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @kotlinx.io.core.ExperimentalIoApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeSuspendSession(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super kotlinx.coroutines.io.WriterSuspendSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeSuspendSession(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeWhile(@NotNull Function1<? super ByteBuffer, Boolean> function1, @NotNull Continuation<? super Unit> continuation) {
        if (!writeWhileNoSuspend(function1)) {
            return Unit.INSTANCE;
        }
        ClosedElement closedElement = this.closed;
        if (closedElement == null) {
            return writeWhileSuspend(function1, continuation);
        }
        throw closedElement.getSendException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f5 A[Catch: all -> 0x012b, TryCatch #1 {all -> 0x012b, blocks: (B:44:0x00f0, B:47:0x00f5, B:49:0x00fb, B:50:0x00ff, B:41:0x00d0), top: B:97:0x00f0 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0169  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00ed -> B:97:0x00f0). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeWhileSuspend(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, java.lang.Boolean> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 401
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeWhileSuspend(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public final Object readFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(byteBuffer);
        return !byteBuffer.hasRemaining() ? Boxing.boxInt(readAsMuchAsPossible) : readFullySuspend(byteBuffer, readAsMuchAsPossible, continuation);
    }

    private final void doWrite(ByteBuffer byteBuffer, short s, RingBufferCapacity ringBufferCapacity) {
        if (byteBuffer.remaining() < 2) {
            byteBuffer.limit(byteBuffer.capacity());
            byteBuffer.putShort(s);
            carry(byteBuffer);
        } else {
            byteBuffer.putShort(s);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, 2);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite();
    }

    private final void resumeReadOp(Function0<? extends Throwable> function0) {
        Continuation continuation = (Continuation) ReadOp.getAndSet(this, null);
        if (continuation != null) {
            Result.Companion companion = Result.Companion;
            GeneratedOutlineSupport1.outline186(function0.mo12560invoke(), continuation);
        }
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Unit> continuation) {
        writeAsMuchAsPossible(ioBuffer);
        return !ioBuffer.canRead() ? Unit.INSTANCE : writeFullySuspend(ioBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$2
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$2 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$2 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$2
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r6 = (kotlinx.coroutines.io.ByteBufferChannel) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L81
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L82
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.readSuspend(r4, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L74
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L74:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable(r6, r0)
            if (r7 != r1) goto L81
            return r1
        L81:
            return r7
        L82:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation2 = resolveDelegation(this, joiningState)) == null) {
            int writeAsMuchAsPossible = writeAsMuchAsPossible(ioBuffer);
            if (writeAsMuchAsPossible > 0) {
                return Boxing.boxInt(writeAsMuchAsPossible);
            }
            JoiningState joiningState2 = this.joining;
            return (joiningState2 == null || (resolveDelegation = resolveDelegation(this, joiningState2)) == null) ? writeAvailableSuspend(ioBuffer, continuation) : resolveDelegation.writeAvailableSuspend(ioBuffer, continuation);
        }
        return resolveDelegation2.writeAvailable(ioBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad A[PHI: r8 
      PHI: (r8v8 java.lang.Object) = (r8v5 java.lang.Object), (r8v1 java.lang.Object) binds: [B:45:0x00aa, B:14:0x0035] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeAvailableSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$3
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeAvailableSuspend$3
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L72
            if (r2 == r5) goto L60
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.IoBuffer r7 = (kotlinx.io.core.IoBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L39
            goto Lad
        L39:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.IoBuffer r7 = (kotlinx.io.core.IoBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L5b
            goto L9f
        L5b:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L60:
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.IoBuffer r7 = (kotlinx.io.core.IoBuffer) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L6d
            goto L84
        L6d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L72:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lae
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L83
            return r1
        L83:
            r2 = r6
        L84:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r8 = r2.joining
            if (r8 == 0) goto La0
            kotlinx.coroutines.io.ByteBufferChannel r5 = r2.resolveDelegation(r2, r8)
            if (r5 == 0) goto La0
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r5
            r0.label = r4
            java.lang.Object r8 = r5.writeAvailableSuspend(r7, r0)
            if (r8 != r1) goto L9f
            return r1
        L9f:
            return r8
        La0:
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r2.writeAvailable(r7, r0)
            if (r8 != r1) goto Lad
            return r1
        Lad:
            return r8
        Lae:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeAvailableSuspend(kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readFully(@NotNull IoBuffer ioBuffer, int i, @NotNull Continuation<? super Unit> continuation) {
        int readAsMuchAsPossible$default = readAsMuchAsPossible$default(this, ioBuffer, 0, i, 2, null);
        if (readAsMuchAsPossible$default == i) {
            return Unit.INSTANCE;
        }
        return readFullySuspend(ioBuffer, i - readAsMuchAsPossible$default, continuation);
    }

    public /* synthetic */ ByteBufferChannel(boolean z, ObjectPool objectPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? ObjectPoolKt.getBufferObjectPool() : objectPool, (i2 & 4) != 0 ? 8 : i);
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(byteBuffer);
        if (readAsMuchAsPossible == 0 && this.closed != null) {
            if (this.state.capacity.flush()) {
                return Boxing.boxInt(readAsMuchAsPossible(byteBuffer));
            }
            return Boxing.boxInt(-1);
        } else if (readAsMuchAsPossible <= 0 && byteBuffer.hasRemaining()) {
            return readAvailableSuspend(byteBuffer, continuation);
        } else {
            return Boxing.boxInt(readAsMuchAsPossible);
        }
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            while (i2 > 0) {
                int writeAsMuchAsPossible = writeAsMuchAsPossible(bArr, i, i2);
                if (writeAsMuchAsPossible == 0) {
                    break;
                }
                i += writeAsMuchAsPossible;
                i2 -= writeAsMuchAsPossible;
            }
            if (i2 == 0) {
                return Unit.INSTANCE;
            }
            return writeFullySuspend(bArr, i, i2, continuation);
        }
        return resolveDelegation.writeFully(bArr, i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeSuspend(int r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel(@NotNull ByteBuffer content) {
        this(false, ObjectPoolKt.getBufferObjectNoPool(), 0);
        Intrinsics.checkParameterIsNotNull(content, "content");
        ByteBuffer slice = content.slice();
        Intrinsics.checkExpressionValueIsNotNull(slice, "content.slice()");
        ReadWriteBufferState.Initial initial = new ReadWriteBufferState.Initial(slice, 0);
        initial.capacity.resetForRead();
        this.state = initial.mo12317startWriting$kotlinx_coroutines_io();
        restoreStateAfterWrite();
        ByteWriteChannelKt.close(this);
        tryTerminate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0072 -> B:32:0x0075). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeFullySuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$3
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$3 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$3 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$3
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L5d
            if (r2 == r4) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r7 = (kotlinx.coroutines.io.ByteBufferChannel.JoiningState) r7
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.IoBuffer r7 = (kotlinx.io.core.IoBuffer) r7
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r7 = (kotlinx.coroutines.io.ByteBufferChannel) r7
            boolean r7 = r8 instanceof kotlin.Result.Failure
            if (r7 != 0) goto L3d
            goto L90
        L3d:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L42:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4a:
            java.lang.Object r7 = r0.L$1
            kotlinx.io.core.IoBuffer r7 = (kotlinx.io.core.IoBuffer) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r5 = r8 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L58
            r8 = r2
            goto L75
        L58:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        L5d:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L98
            r8 = r6
        L62:
            boolean r2 = r7.canRead()
            if (r2 == 0) goto L95
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r2 = r8.tryWriteSuspend(r4, r0)
            if (r2 != r1) goto L75
            return r1
        L75:
            kotlinx.coroutines.io.ByteBufferChannel$JoiningState r2 = r8.joining
            if (r2 == 0) goto L91
            kotlinx.coroutines.io.ByteBufferChannel r5 = r8.resolveDelegation(r8, r2)
            if (r5 == 0) goto L91
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r5
            r0.label = r3
            java.lang.Object r8 = r5.writeFully(r7, r0)
            if (r8 != r1) goto L90
            return r1
        L90:
            return r8
        L91:
            r8.writeAsMuchAsPossible(r7)
            goto L62
        L95:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L98:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r7 = r8.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeFullySuspend(kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$3
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$3 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$3 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readAvailableSuspend$3
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r6 = (kotlinx.coroutines.io.ByteBufferChannel) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L81
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            kotlinx.io.core.IoBuffer r6 = (kotlinx.io.core.IoBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L82
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.readSuspend(r4, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L74
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L74:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable(r6, r0)
            if (r7 != r1) goto L81
            return r1
        L81:
            return r7
        L82:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readAvailableSuspend(kotlinx.io.core.IoBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0068 -> B:28:0x006f). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull kotlinx.io.core.IoBuffer r12, int r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$2
            if (r0 == 0) goto L13
            r0 = r14
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$2 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$2 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$2
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L48
            if (r2 != r3) goto L40
            int r12 = r0.I$1
            int r13 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.io.core.IoBuffer r2 = (kotlinx.io.core.IoBuffer) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r4 = (kotlinx.coroutines.io.ByteBufferChannel) r4
            boolean r5 = r14 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3b
            r9 = r0
            r8 = r1
            r7 = r2
            r10 = r3
            r6 = r4
            goto L6f
        L3b:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r12 = r14.exception
            throw r12
        L40:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L48:
            boolean r2 = r14 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La5
            r14 = 0
            r2 = r1
            r1 = r11
        L4f:
            boolean r4 = r12.canWrite()
            if (r4 == 0) goto La2
            if (r14 >= r13) goto La2
            r0.L$0 = r1
            r0.L$1 = r12
            r0.I$0 = r13
            r0.I$1 = r14
            r0.label = r3
            java.lang.Object r4 = r1.readSuspend(r3, r0)
            if (r4 != r2) goto L68
            return r2
        L68:
            r7 = r12
            r12 = r14
            r9 = r0
            r6 = r1
            r8 = r2
            r10 = r3
            r14 = r4
        L6f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L89
            r2 = 0
            int r3 = r13 - r12
            r4 = 2
            r5 = 0
            r0 = r6
            r1 = r7
            int r14 = readAsMuchAsPossible$default(r0, r1, r2, r3, r4, r5)
            int r14 = r14 + r12
            r1 = r6
            r12 = r7
            r2 = r8
            r0 = r9
            r3 = r10
            goto L4f
        L89:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r14 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "Unexpected EOF: expected "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            int r13 = r13 - r12
            r0.append(r13)
            java.lang.String r12 = " more bytes"
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r14.<init>(r12)
            throw r14
        La2:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        La5:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r12 = r14.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readFullySuspend(kotlinx.io.core.IoBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (resolveDelegation = resolveDelegation(this, joiningState)) == null) {
            int writeAsMuchAsPossible = writeAsMuchAsPossible(bArr, i, i2);
            if (writeAsMuchAsPossible > 0) {
                return Boxing.boxInt(writeAsMuchAsPossible);
            }
            return writeSuspend(bArr, i, i2, continuation);
        }
        return resolveDelegation.writeAvailable(bArr, i, i2, continuation);
    }

    private final int readAsMuchAsPossible(IoBuffer ioBuffer, int i, int i2) {
        int i3;
        while (true) {
            ByteBuffer byteBuffer = setupStateForRead();
            boolean z = false;
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = this.state.capacity;
                try {
                    if (ringBufferCapacity.availableForRead == 0) {
                        restoreStateAfterRead();
                        tryTerminate();
                    } else {
                        int writeRemaining = ioBuffer.getWriteRemaining();
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(byteBuffer.remaining(), Math.min(writeRemaining, i2)));
                        if (tryReadAtMost > 0) {
                            int i4 = tryReadAtMost + 0;
                            if (writeRemaining < byteBuffer.remaining()) {
                                byteBuffer.limit(byteBuffer.position() + writeRemaining);
                            }
                            ioBuffer.writeFully(byteBuffer);
                            bytesRead(byteBuffer, ringBufferCapacity, tryReadAtMost);
                            z = true;
                            i3 = i4;
                        } else {
                            i3 = 0;
                        }
                        if (!z || !ioBuffer.canWrite() || this.state.capacity.availableForRead <= 0) {
                            break;
                        }
                        i += i3;
                        i2 -= i3;
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate();
                }
            }
            i3 = 0;
            if (!z) {
                break;
            }
            break;
        }
        return i3 + i;
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible$default = readAsMuchAsPossible$default(this, ioBuffer, 0, 0, 6, null);
        if (readAsMuchAsPossible$default == 0 && this.closed != null) {
            if (this.state.capacity.flush()) {
                return Boxing.boxInt(readAsMuchAsPossible$default(this, ioBuffer, 0, 0, 6, null));
            }
            return Boxing.boxInt(-1);
        } else if (readAsMuchAsPossible$default <= 0 && ioBuffer.canWrite()) {
            return readAvailableSuspend(ioBuffer, continuation);
        } else {
            return Boxing.boxInt(readAsMuchAsPossible$default);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0062 -> B:28:0x0066). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeFullySuspend(@org.jetbrains.annotations.NotNull byte[] r7, int r8, int r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$5
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$5 r0 = (kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$5 r0 = new kotlinx.coroutines.io.ByteBufferChannel$writeFullySuspend$5
            r0.<init>(r6, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            byte[] r9 = (byte[]) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r10 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L36
            goto L66
        L36:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L71
            r10 = r8
            r8 = r6
            r5 = r9
            r9 = r7
            r7 = r5
        L4c:
            if (r7 != 0) goto L51
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L51:
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r10
            r0.I$1 = r7
            r0.label = r3
            java.lang.Object r2 = r8.writeAvailable(r9, r10, r7, r0)
            if (r2 != r1) goto L62
            return r1
        L62:
            r5 = r2
            r2 = r8
            r8 = r10
            r10 = r5
        L66:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            int r8 = r8 + r10
            int r7 = r7 - r10
            r10 = r8
            r8 = r2
            goto L4c
        L71:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.writeFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005f -> B:25:0x0063). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull byte[] r7, int r8, int r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$3
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$3 r0 = (kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$3 r0 = new kotlinx.coroutines.io.ByteBufferChannel$readFullySuspend$3
            r0.<init>(r6, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            byte[] r9 = (byte[]) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteBufferChannel r2 = (kotlinx.coroutines.io.ByteBufferChannel) r2
            boolean r4 = r10 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L39
            r5 = r9
            r9 = r8
            r8 = r5
            goto L63
        L39:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L87
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
        L4e:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.I$0 = r9
            r0.I$1 = r10
            r0.label = r3
            java.lang.Object r2 = r7.readSuspend(r3, r0)
            if (r2 != r1) goto L5f
            return r1
        L5f:
            r5 = r2
            r2 = r7
            r7 = r10
            r10 = r5
        L63:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L79
            int r10 = r2.readAsMuchAsPossible(r8, r9, r7)
            if (r10 >= r7) goto L76
            int r9 = r9 + r10
            int r10 = r7 - r10
            r7 = r2
            goto L4e
        L76:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L79:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r8 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r9 = "Unexpected EOF: expected "
            java.lang.String r10 = " more bytes"
            java.lang.String r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline52(r9, r7, r10)
            r8.<init>(r7)
            throw r8
        L87:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r7 = r10.exception
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteBufferChannel.readFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final int writeAsMuchAsPossible(IoBuffer ioBuffer) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
        int i = 0;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement == null) {
                    while (true) {
                        int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(ioBuffer.getReadRemaining(), byteBuffer.remaining()));
                        if (tryWriteAtMost == 0) {
                            break;
                        }
                        ioBuffer.readFully(byteBuffer, tryWriteAtMost);
                        i += tryWriteAtMost;
                        byteBufferChannel.prepareBuffer(byteBuffer, byteBufferChannel.getWriteByteOrder(), byteBufferChannel.carryIndex(byteBuffer, byteBufferChannel.writePosition + i), ringBufferCapacity.availableForWrite);
                    }
                    byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, i);
                    return i;
                }
                throw closedElement.getSendException();
            } finally {
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
            }
        }
        return 0;
    }

    private final int readAsMuchAsPossible(byte[] bArr, int i, int i2) {
        ByteBuffer byteBuffer = setupStateForRead();
        int i3 = 0;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = this.state.capacity;
            try {
                if (ringBufferCapacity.availableForRead != 0) {
                    int capacity = byteBuffer.capacity() - this.reservedSize;
                    while (true) {
                        int i4 = i2 - i3;
                        if (i4 == 0) {
                            break;
                        }
                        int i5 = this.readPosition;
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(capacity - i5, i4));
                        if (tryReadAtMost == 0) {
                            break;
                        }
                        byteBuffer.limit(i5 + tryReadAtMost);
                        byteBuffer.position(i5);
                        byteBuffer.get(bArr, i + i3, tryReadAtMost);
                        bytesRead(byteBuffer, ringBufferCapacity, tryReadAtMost);
                        i3 += tryReadAtMost;
                    }
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate();
            }
        }
        return i3;
    }

    private final int writeAsMuchAsPossible(byte[] bArr, int i, int i2) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
            long totalBytesWritten = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closedElement = byteBufferChannel.closed;
                if (closedElement != null) {
                    throw closedElement.getSendException();
                }
                int i3 = 0;
                while (true) {
                    int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(i2 - i3, byteBuffer.remaining()));
                    if (tryWriteAtMost == 0) {
                        byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, i3);
                        return i3;
                    }
                    if (tryWriteAtMost > 0) {
                        byteBuffer.put(bArr, i + i3, tryWriteAtMost);
                        i3 += tryWriteAtMost;
                        byteBufferChannel.prepareBuffer(byteBuffer, byteBufferChannel.getWriteByteOrder(), byteBufferChannel.carryIndex(byteBuffer, byteBufferChannel.writePosition + i3), ringBufferCapacity.availableForWrite);
                    } else {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
            } finally {
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    this.totalBytesWritten = (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten) + getTotalBytesWritten();
                }
                byteBufferChannel.restoreStateAfterWrite();
                byteBufferChannel.tryTerminate();
            }
        } else {
            return 0;
        }
    }
}
