package kotlin.text;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.appmanager.lib.DefaultPreloadManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.apache.commons.codec.language.bm.Languages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _Strings.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001a3\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0087\b\u001aN\u0010\u001d\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u000e\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0005\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0087\b¢\u0006\u0002\u0010\u0018\u001a\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\r\u0010%\u001a\u00020\"*\u00020\u0002H\u0087\b\u001a!\u0010%\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010&\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a!\u0010)\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010)\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010*\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010*\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a)\u0010+\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u001c\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"H\u0087\b¢\u0006\u0002\u0010/\u001a!\u00100\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u00100\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u00101\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000102H\u0086\b\u001a6\u00101\u001a\u00020 *\u00020 2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000102H\u0086\b\u001aQ\u00105\u001a\u0002H6\"\f\b\u0000\u00106*\u000607j\u0002`8*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000102H\u0086\b¢\u0006\u0002\u00109\u001a!\u0010:\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010:\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a<\u0010;\u001a\u0002H6\"\f\b\u0000\u00106*\u000607j\u0002`8*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010<\u001a<\u0010=\u001a\u0002H6\"\f\b\u0000\u00106*\u000607j\u0002`8*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010<\u001a(\u0010>\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u0010?\u001a(\u0010@\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u0010?\u001a\n\u0010A\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010A\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010B\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010C\u001a(\u0010B\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010?\u001a3\u0010D\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\u0086\b\u001aL\u0010E\u001a\u0002H6\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00106*\n\u0012\u0006\b\u0000\u0012\u0002H#0F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\u0086\b¢\u0006\u0002\u0010G\u001aI\u0010H\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2'\u0010J\u001a#\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#02H\u0086\b¢\u0006\u0002\u0010L\u001a^\u0010M\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0NH\u0086\b¢\u0006\u0002\u0010O\u001aI\u0010P\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2'\u0010J\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u0002H#02H\u0086\b¢\u0006\u0002\u0010L\u001a^\u0010Q\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u0002H#0NH\u0086\b¢\u0006\u0002\u0010O\u001a!\u0010R\u001a\u00020S*\u00020\u00022\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020S0\u0004H\u0086\b\u001a6\u0010U\u001a\u00020S*\u00020\u00022'\u0010T\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020S02H\u0086\b\u001a)\u0010V\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0019\u0010W\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"¢\u0006\u0002\u0010/\u001a9\u0010X\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001f0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aS\u0010X\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001f0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aR\u0010Y\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050Z0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001al\u0010Y\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0Z0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a5\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\\\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0087\b\u001a!\u0010]\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010^\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010_\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010_\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010`\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010C\u001a(\u0010`\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010?\u001a-\u0010a\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\u0086\b\u001aB\u0010b\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#02H\u0086\b\u001aH\u0010c\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020d*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#02H\u0086\b\u001aa\u0010e\u001a\u0002H6\"\b\b\u0000\u0010#*\u00020d\"\u0010\b\u0001\u00106*\n\u0012\u0006\b\u0000\u0012\u0002H#0F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#02H\u0086\b¢\u0006\u0002\u0010f\u001a[\u0010g\u001a\u0002H6\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00106*\n\u0012\u0006\b\u0000\u0012\u0002H#0F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#02H\u0086\b¢\u0006\u0002\u0010f\u001a3\u0010h\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020d*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\u0086\b\u001aL\u0010i\u001a\u0002H6\"\b\b\u0000\u0010#*\u00020d\"\u0010\b\u0001\u00106*\n\u0012\u0006\b\u0000\u0012\u0002H#0F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\u0086\b¢\u0006\u0002\u0010G\u001aF\u0010j\u001a\u0002H6\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00106*\n\u0012\u0006\b\u0000\u0012\u0002H#0F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H62\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\u0086\b¢\u0006\u0002\u0010G\u001a\u0011\u0010k\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010C\u001a8\u0010l\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0m*\u00020\u00022\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\u0086\b¢\u0006\u0002\u0010?\u001a-\u0010o\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010p\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050qj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`r¢\u0006\u0002\u0010s\u001a\u0011\u0010t\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010C\u001a8\u0010u\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0m*\u00020\u00022\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\u0086\b¢\u0006\u0002\u0010?\u001a-\u0010v\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010p\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050qj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`r¢\u0006\u0002\u0010s\u001a\n\u0010w\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010w\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a0\u0010x\u001a\u0002Hy\"\b\b\u0000\u0010y*\u00020\u0002*\u0002Hy2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020S0\u0004H\u0087\b¢\u0006\u0002\u0010z\u001a-\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a-\u0010{\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u0010*\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\r\u0010|\u001a\u00020\u0005*\u00020\u0002H\u0087\b\u001a\u0014\u0010|\u001a\u00020\u0005*\u00020\u00022\u0006\u0010|\u001a\u00020}H\u0007\u001a\u0014\u0010~\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0087\b¢\u0006\u0002\u0010C\u001a\u001b\u0010~\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010|\u001a\u00020}H\u0007¢\u0006\u0002\u0010\u007f\u001a7\u0010\u0080\u0001\u001a\u00020\u0005*\u00020\u00022'\u0010J\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000502H\u0086\b\u001aL\u0010\u0081\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050NH\u0086\b\u001a?\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022'\u0010J\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000502H\u0087\b¢\u0006\u0003\u0010\u0083\u0001\u001a7\u0010\u0084\u0001\u001a\u00020\u0005*\u00020\u00022'\u0010J\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u000502H\u0086\b\u001aL\u0010\u0085\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u00050NH\u0086\b\u001a?\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022'\u0010J\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u000502H\u0087\b¢\u0006\u0003\u0010\u0083\u0001\u001a\u000b\u0010\u0087\u0001\u001a\u00020\u0002*\u00020\u0002\u001a\u000e\u0010\u0087\u0001\u001a\u00020 *\u00020 H\u0087\b\u001aQ\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2'\u0010J\u001a#\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#02H\u0087\b¢\u0006\u0003\u0010\u0089\u0001\u001af\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010I\u001a\u0002H#2<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0013\u0012\u0011H#¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0NH\u0087\b¢\u0006\u0003\u0010\u008b\u0001\u001a=\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f*\u00020\u00022'\u0010J\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000502H\u0087\b\u001aR\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f*\u00020\u00022<\u0010J\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050NH\u0087\b\u001a\u000b\u0010\u008e\u0001\u001a\u00020\u0005*\u00020\u0002\u001a\"\u0010\u008e\u0001\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0012\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010C\u001a)\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010?\u001a\u001a\u0010\u0090\u0001\u001a\u00020\u0002*\u00020\u00022\r\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\b\u001a\u0015\u0010\u0090\u0001\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001\u001a\u001d\u0010\u0090\u0001\u001a\u00020 *\u00020 2\r\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\bH\u0087\b\u001a\u0015\u0010\u0090\u0001\u001a\u00020 *\u00020 2\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001\u001a\"\u0010\u0093\u0001\u001a\u00020\"*\u00020\u00022\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004H\u0086\b\u001a$\u0010\u0094\u0001\u001a\u00030\u0095\u0001*\u00020\u00022\u0013\u0010n\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0095\u00010\u0004H\u0086\b\u001a\u0013\u0010\u0096\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0096\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0097\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0097\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\"\u0010\u0098\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0098\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0099\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0099\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a+\u0010\u009a\u0001\u001a\u0002H6\"\u0010\b\u0000\u00106*\n\u0012\u0006\b\u0000\u0012\u00020\u00050F*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H6¢\u0006\u0003\u0010\u009b\u0001\u001a\u001d\u0010\u009c\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u009d\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u009e\u0001*\u00020\u0002\u001a\u0011\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f*\u00020\u0002\u001a\u0011\u0010 \u0001\u001a\b\u0012\u0004\u0012\u00020\u00050Z*\u00020\u0002\u001a\u0012\u0010¡\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050¢\u0001*\u00020\u0002\u001a1\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010¤\u0001\u001a\u00020\"2\t\b\u0002\u0010¥\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010£\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010¤\u0001\u001a\u00020\"2\t\b\u0002\u0010¥\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a1\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010¤\u0001\u001a\u00020\"2\t\b\u0002\u0010¥\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010¤\u0001\u001a\u00020\"2\t\b\u0002\u0010¥\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u0018\u0010§\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050¨\u00010\b*\u00020\u0002\u001a)\u0010©\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u00022\u0007\u0010ª\u0001\u001a\u00020\u0002H\u0086\u0004\u001a]\u0010©\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u001f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010ª\u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b3\u0012\t\b4\u0012\u0005\b\b(«\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b3\u0012\t\b4\u0012\u0005\b\b(¬\u0001\u0012\u0004\u0012\u0002H\u000e02H\u0086\b\u001a\u001f\u0010\u00ad\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u0002H\u0007\u001aT\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b3\u0012\t\b4\u0012\u0005\b\b(«\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b3\u0012\t\b4\u0012\u0005\b\b(¬\u0001\u0012\u0004\u0012\u0002H#02H\u0087\b¨\u0006®\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", Languages.ANY, "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ViewProps.TRANSFORM, "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "associateWith", "valueSelector", "associateWithTo", "chunked", "", "", "size", "", "R", "chunkedSequence", "count", "drop", JsonReportFormat.COUNT, "dropLast", "dropLastWhile", "dropWhile", "elementAtOrElse", "index", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", DefaultPreloadManager.METRIC_PATH_FIRST, "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", BuildConfig.FLAVOR_authtype, "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", ReactProperties.GEOFENCE_MINIMUM_RANGE, "minBy", "minWith", "none", "onEach", ExifInterface.LATITUDE_SOUTH, "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "random", "Lkotlin/random/Random;", "randomOrNull", "(Ljava/lang/CharSequence;Lkotlin/random/Random;)Ljava/lang/Character;", "reduce", "reduceIndexed", "reduceOrNull", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function2;)Ljava/lang/Character;", "reduceRight", "reduceRightIndexed", "reduceRightOrNull", "reversed", "scan", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "scanIndexed", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/util/List;", "scanReduce", "scanReduceIndexed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "windowed", "step", "partialWindows", "windowedSequence", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static final boolean all(@NotNull CharSequence all, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(all, "$this$all");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < all.length(); i++) {
            if (!((Boolean) GeneratedOutlineSupport1.outline19(all, i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull CharSequence any) {
        Intrinsics.checkParameterIsNotNull(any, "$this$any");
        return !(any.length() == 0);
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull CharSequence asIterable) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(asIterable, "$this$asIterable");
        if (asIterable instanceof String) {
            if (asIterable.length() == 0) {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList;
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(asIterable);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull final CharSequence asSequence) {
        Sequence<Character> emptySequence;
        Intrinsics.checkParameterIsNotNull(asSequence, "$this$asSequence");
        if (asSequence instanceof String) {
            if (asSequence.length() == 0) {
                emptySequence = SequencesKt__SequencesKt.emptySequence();
                return emptySequence;
            }
        }
        return new Sequence<Character>() { // from class: kotlin.text.StringsKt___StringsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Character> iterator() {
                return StringsKt__StringsKt.iterator(asSequence);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull CharSequence associate, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(associate, "$this$associate");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(associate.length());
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i = 0; i < associate.length(); i++) {
            Pair pair = (Pair) GeneratedOutlineSupport1.outline19(associate, i, transform);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull CharSequence associateBy, @NotNull Function1<? super Character, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(associateBy, "$this$associateBy");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateBy.length());
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i = 0; i < associateBy.length(); i++) {
            char charAt = associateBy.charAt(i);
            linkedHashMap.put(keySelector.mo12165invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence associateByTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(associateByTo, "$this$associateByTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        for (int i = 0; i < associateByTo.length(); i++) {
            char charAt = associateByTo.charAt(i);
            destination.put(keySelector.mo12165invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence associateTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkParameterIsNotNull(associateTo, "$this$associateTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < associateTo.length(); i++) {
            Pair pair = (Pair) GeneratedOutlineSupport1.outline19(associateTo, i, transform);
            destination.put(pair.getFirst(), pair.getSecond());
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <V> Map<Character, V> associateWith(@NotNull CharSequence associateWith, @NotNull Function1<? super Character, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(associateWith, "$this$associateWith");
        Intrinsics.checkParameterIsNotNull(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateWith.length());
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i = 0; i < associateWith.length(); i++) {
            char charAt = associateWith.charAt(i);
            linkedHashMap.put(Character.valueOf(charAt), valueSelector.mo12165invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <V, M extends Map<? super Character, ? super V>> M associateWithTo(@NotNull CharSequence associateWithTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkParameterIsNotNull(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(valueSelector, "valueSelector");
        for (int i = 0; i < associateWithTo.length(); i++) {
            char charAt = associateWithTo.charAt(i);
            destination.put(Character.valueOf(charAt), valueSelector.mo12165invoke(Character.valueOf(charAt)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static List<String> chunked(@NotNull CharSequence chunked, int i) {
        Intrinsics.checkParameterIsNotNull(chunked, "$this$chunked");
        return windowed(chunked, i, i, true);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<String> chunkedSequence(@NotNull CharSequence chunkedSequence, int i) {
        Intrinsics.checkParameterIsNotNull(chunkedSequence, "$this$chunkedSequence");
        return chunkedSequence(chunkedSequence, i, StringsKt___StringsKt$chunkedSequence$1.INSTANCE);
    }

    @InlineOnly
    private static final int count(@NotNull CharSequence charSequence) {
        return charSequence.length();
    }

    @NotNull
    public static final CharSequence drop(@NotNull CharSequence drop, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(drop, "$this$drop");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, drop.length());
            return drop.subSequence(coerceAtMost, drop.length());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence dropLast(@NotNull CharSequence dropLast, int i) {
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(dropLast, "$this$dropLast");
        if (i >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(dropLast.length() - i, 0);
            return take(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence dropLastWhile(@NotNull CharSequence dropLastWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(dropLastWhile); lastIndex >= 0; lastIndex--) {
            if (!((Boolean) GeneratedOutlineSupport1.outline19(dropLastWhile, lastIndex, predicate)).booleanValue()) {
                return dropLastWhile.subSequence(0, lastIndex + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence dropWhile(@NotNull CharSequence dropWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(dropWhile, "$this$dropWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = dropWhile.length();
        for (int i = 0; i < length; i++) {
            if (!((Boolean) GeneratedOutlineSupport1.outline19(dropWhile, i, predicate)).booleanValue()) {
                return dropWhile.subSequence(i, dropWhile.length());
            }
        }
        return "";
    }

    @InlineOnly
    private static final char elementAtOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt__StringsKt.getLastIndex(charSequence)) ? function1.mo12165invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @InlineOnly
    private static final Character elementAtOrNull(@NotNull CharSequence charSequence, int i) {
        return getOrNull(charSequence, i);
    }

    @NotNull
    public static final CharSequence filter(@NotNull CharSequence filter, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filter, "$this$filter");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = filter.length();
        for (int i = 0; i < length; i++) {
            char charAt = filter.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    @NotNull
    public static final CharSequence filterIndexed(@NotNull CharSequence filterIndexed, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < filterIndexed.length()) {
            char charAt = filterIndexed.charAt(i);
            int i3 = i2 + 1;
            if (predicate.mo12248invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return sb;
    }

    @NotNull
    public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence filterIndexedTo, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < filterIndexedTo.length()) {
            char charAt = filterIndexedTo.charAt(i);
            int i3 = i2 + 1;
            if (predicate.mo12248invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final CharSequence filterNot(@NotNull CharSequence filterNot, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterNot, "$this$filterNot");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filterNot.length(); i++) {
            char charAt = filterNot.charAt(i);
            if (!predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    @NotNull
    public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence filterNotTo, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < filterNotTo.length(); i++) {
            char charAt = filterNotTo.charAt(i);
            if (!predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Appendable> C filterTo(@NotNull CharSequence filterTo, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterTo, "$this$filterTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = filterTo.length();
        for (int i = 0; i < length; i++) {
            char charAt = filterTo.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
        }
        return destination;
    }

    @InlineOnly
    private static final Character find(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character findLast(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        char charAt;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!function1.mo12165invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    public static final char first(@NotNull CharSequence first) {
        Intrinsics.checkParameterIsNotNull(first, "$this$first");
        if (!(first.length() == 0)) {
            return first.charAt(0);
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence firstOrNull) {
        Intrinsics.checkParameterIsNotNull(firstOrNull, "$this$firstOrNull");
        if (firstOrNull.length() == 0) {
            return null;
        }
        return Character.valueOf(firstOrNull.charAt(0));
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull CharSequence flatMap, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(flatMap, "$this$flatMap");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < flatMap.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) GeneratedOutlineSupport1.outline19(flatMap, i, transform));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence flatMapTo, @NotNull C destination, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < flatMapTo.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) GeneratedOutlineSupport1.outline19(flatMapTo, i, transform));
        }
        return destination;
    }

    public static final <R> R fold(@NotNull CharSequence fold, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(fold, "$this$fold");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int i = 0; i < fold.length(); i++) {
            r = operation.mo12248invoke(r, Character.valueOf(fold.charAt(i)));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull CharSequence foldIndexed, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 0;
        for (int i2 = 0; i2 < foldIndexed.length(); i2++) {
            char charAt = foldIndexed.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            r = operation.invoke(valueOf, r, Character.valueOf(charAt));
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull CharSequence foldRight, R r, @NotNull Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(foldRight, "$this$foldRight");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(foldRight); lastIndex >= 0; lastIndex--) {
            r = operation.mo12248invoke(Character.valueOf(foldRight.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull CharSequence foldRightIndexed, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(foldRightIndexed); lastIndex >= 0; lastIndex--) {
            r = operation.invoke(Integer.valueOf(lastIndex), Character.valueOf(foldRightIndexed.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final void forEach(@NotNull CharSequence forEach, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(forEach, "$this$forEach");
        Intrinsics.checkParameterIsNotNull(action, "action");
        for (int i = 0; i < forEach.length(); i++) {
            action.mo12165invoke(Character.valueOf(forEach.charAt(i)));
        }
    }

    public static final void forEachIndexed(@NotNull CharSequence forEachIndexed, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int i = 0;
        for (int i2 = 0; i2 < forEachIndexed.length(); i2++) {
            char charAt = forEachIndexed.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            action.mo12248invoke(valueOf, Character.valueOf(charAt));
        }
    }

    @InlineOnly
    private static final char getOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt__StringsKt.getLastIndex(charSequence)) ? function1.mo12165invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @Nullable
    public static final Character getOrNull(@NotNull CharSequence getOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(getOrNull, "$this$getOrNull");
        if (i < 0 || i > StringsKt__StringsKt.getLastIndex(getOrNull)) {
            return null;
        }
        return Character.valueOf(getOrNull.charAt(i));
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence groupBy, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(groupBy, "$this$groupBy");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < groupBy.length(); i++) {
            char charAt = groupBy.charAt(i);
            K mo12165invoke = keySelector.mo12165invoke(Character.valueOf(charAt));
            Object obj = linkedHashMap.get(mo12165invoke);
            if (obj == null) {
                obj = GeneratedOutlineSupport1.outline131(linkedHashMap, mo12165invoke);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence groupByTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(groupByTo, "$this$groupByTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        for (int i = 0; i < groupByTo.length(); i++) {
            char charAt = groupByTo.charAt(i);
            K mo12165invoke = keySelector.mo12165invoke(Character.valueOf(charAt));
            Object obj = destination.get(mo12165invoke);
            if (obj == null) {
                obj = GeneratedOutlineSupport1.outline132(destination, mo12165invoke);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K> Grouping<Character, K> groupingBy(@NotNull final CharSequence groupingBy, @NotNull final Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(groupingBy, "$this$groupingBy");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        return new Grouping<Character, K>() { // from class: kotlin.text.StringsKt___StringsKt$groupingBy$1
            @Override // kotlin.collections.Grouping
            public /* bridge */ /* synthetic */ Object keyOf(Character ch) {
                return keyOf(ch.charValue());
            }

            @Override // kotlin.collections.Grouping
            @NotNull
            public Iterator<Character> sourceIterator() {
                return StringsKt__StringsKt.iterator(groupingBy);
            }

            /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, K] */
            public K keyOf(char c) {
                return keySelector.mo12165invoke(Character.valueOf(c));
            }
        };
    }

    public static final int indexOfFirst(@NotNull CharSequence indexOfFirst, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = indexOfFirst.length();
        for (int i = 0; i < length; i++) {
            if (((Boolean) GeneratedOutlineSupport1.outline19(indexOfFirst, i, predicate)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull CharSequence indexOfLast, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int length = indexOfLast.length() - 1; length >= 0; length--) {
            if (((Boolean) GeneratedOutlineSupport1.outline19(indexOfLast, length, predicate)).booleanValue()) {
                return length;
            }
        }
        return -1;
    }

    public static char last(@NotNull CharSequence last) {
        Intrinsics.checkParameterIsNotNull(last, "$this$last");
        if (!(last.length() == 0)) {
            return last.charAt(StringsKt__StringsKt.getLastIndex(last));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence lastOrNull) {
        Intrinsics.checkParameterIsNotNull(lastOrNull, "$this$lastOrNull");
        if (lastOrNull.length() == 0) {
            return null;
        }
        return Character.valueOf(lastOrNull.charAt(lastOrNull.length() - 1));
    }

    @NotNull
    public static final <R> List<R> map(@NotNull CharSequence map, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(map, "$this$map");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList(map.length());
        for (int i = 0; i < map.length(); i++) {
            arrayList.add(transform.mo12165invoke(Character.valueOf(map.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull CharSequence mapIndexed, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList(mapIndexed.length());
        int i = 0;
        for (int i2 = 0; i2 < mapIndexed.length(); i2++) {
            char charAt = mapIndexed.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            arrayList.add(transform.mo12248invoke(valueOf, Character.valueOf(charAt)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence mapIndexedNotNull, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapIndexedNotNull, "$this$mapIndexedNotNull");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < mapIndexedNotNull.length()) {
            int i3 = i2 + 1;
            R mo12248invoke = transform.mo12248invoke(Integer.valueOf(i2), Character.valueOf(mapIndexedNotNull.charAt(i)));
            if (mo12248invoke != null) {
                arrayList.add(mo12248invoke);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence mapIndexedNotNullTo, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapIndexedNotNullTo, "$this$mapIndexedNotNullTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int i = 0;
        int i2 = 0;
        while (i < mapIndexedNotNullTo.length()) {
            int i3 = i2 + 1;
            R mo12248invoke = transform.mo12248invoke(Integer.valueOf(i2), Character.valueOf(mapIndexedNotNullTo.charAt(i)));
            if (mo12248invoke != null) {
                destination.add(mo12248invoke);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence mapIndexedTo, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int i = 0;
        for (int i2 = 0; i2 < mapIndexedTo.length(); i2++) {
            char charAt = mapIndexedTo.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            destination.add(transform.mo12248invoke(valueOf, Character.valueOf(charAt)));
        }
        return destination;
    }

    @NotNull
    public static final <R> List<R> mapNotNull(@NotNull CharSequence mapNotNull, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapNotNull, "$this$mapNotNull");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < mapNotNull.length(); i++) {
            Object outline19 = GeneratedOutlineSupport1.outline19(mapNotNull, i, transform);
            if (outline19 != null) {
                arrayList.add(outline19);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence mapNotNullTo, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapNotNullTo, "$this$mapNotNullTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < mapNotNullTo.length(); i++) {
            Object outline19 = GeneratedOutlineSupport1.outline19(mapNotNullTo, i, transform);
            if (outline19 != null) {
                destination.add(outline19);
            }
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence mapTo, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(mapTo, "$this$mapTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < mapTo.length(); i++) {
            destination.add(transform.mo12165invoke(Character.valueOf(mapTo.charAt(i))));
        }
        return destination;
    }

    @Nullable
    public static final Character max(@NotNull CharSequence max) {
        Intrinsics.checkParameterIsNotNull(max, "$this$max");
        int i = 1;
        if (max.length() == 0) {
            return null;
        }
        char charAt = max.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(max);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = max.charAt(i);
                if (charAt < charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character maxBy(@NotNull CharSequence maxBy, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(maxBy, "$this$maxBy");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (maxBy.length() == 0) {
            return null;
        }
        char charAt = maxBy.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(maxBy);
        if (lastIndex == 0) {
            return Character.valueOf(charAt);
        }
        R mo12165invoke = selector.mo12165invoke(Character.valueOf(charAt));
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = maxBy.charAt(i);
                R mo12165invoke2 = selector.mo12165invoke(Character.valueOf(charAt2));
                if (mo12165invoke.compareTo(mo12165invoke2) < 0) {
                    charAt = charAt2;
                    mo12165invoke = mo12165invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character maxWith(@NotNull CharSequence maxWith, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(maxWith, "$this$maxWith");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (maxWith.length() == 0) {
            return null;
        }
        char charAt = maxWith.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(maxWith);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = maxWith.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) < 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character min(@NotNull CharSequence min) {
        Intrinsics.checkParameterIsNotNull(min, "$this$min");
        int i = 1;
        if (min.length() == 0) {
            return null;
        }
        char charAt = min.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(min);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = min.charAt(i);
                if (charAt > charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character minBy(@NotNull CharSequence minBy, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(minBy, "$this$minBy");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (minBy.length() == 0) {
            return null;
        }
        char charAt = minBy.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(minBy);
        if (lastIndex == 0) {
            return Character.valueOf(charAt);
        }
        R mo12165invoke = selector.mo12165invoke(Character.valueOf(charAt));
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = minBy.charAt(i);
                R mo12165invoke2 = selector.mo12165invoke(Character.valueOf(charAt2));
                if (mo12165invoke.compareTo(mo12165invoke2) > 0) {
                    charAt = charAt2;
                    mo12165invoke = mo12165invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character minWith(@NotNull CharSequence minWith, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(minWith, "$this$minWith");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (minWith.length() == 0) {
            return null;
        }
        char charAt = minWith.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(minWith);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = minWith.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) > 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final boolean none(@NotNull CharSequence none) {
        Intrinsics.checkParameterIsNotNull(none, "$this$none");
        return none.length() == 0;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S extends CharSequence> S onEach(@NotNull S onEach, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(onEach, "$this$onEach");
        Intrinsics.checkParameterIsNotNull(action, "action");
        for (int i = 0; i < onEach.length(); i++) {
            action.mo12165invoke(Character.valueOf(onEach.charAt(i)));
        }
        return onEach;
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence partition, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(partition, "$this$partition");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < partition.length(); i++) {
            char charAt = partition.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb, sb2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char random(@NotNull CharSequence charSequence) {
        return random(charSequence, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final Character randomOrNull(@NotNull CharSequence charSequence) {
        return randomOrNull(charSequence, Random.Default);
    }

    public static final char reduce(@NotNull CharSequence reduce, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduce, "$this$reduce");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 1;
        if (!(reduce.length() == 0)) {
            char charAt = reduce.charAt(0);
            int lastIndex = StringsKt__StringsKt.getLastIndex(reduce);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = operation.mo12248invoke(Character.valueOf(charAt), Character.valueOf(reduce.charAt(i))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char reduceIndexed(@NotNull CharSequence reduceIndexed, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 1;
        if (!(reduceIndexed.length() == 0)) {
            char charAt = reduceIndexed.charAt(0);
            int lastIndex = StringsKt__StringsKt.getLastIndex(reduceIndexed);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = operation.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(reduceIndexed.charAt(i))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Character reduceOrNull(@NotNull CharSequence reduceOrNull, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 1;
        if (reduceOrNull.length() == 0) {
            return null;
        }
        char charAt = reduceOrNull.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(reduceOrNull);
        if (1 <= lastIndex) {
            while (true) {
                charAt = operation.mo12248invoke(Character.valueOf(charAt), Character.valueOf(reduceOrNull.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final char reduceRight(@NotNull CharSequence reduceRight, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduceRight, "$this$reduceRight");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(reduceRight);
        if (lastIndex >= 0) {
            char charAt = reduceRight.charAt(lastIndex);
            for (int i = lastIndex - 1; i >= 0; i--) {
                charAt = operation.mo12248invoke(Character.valueOf(reduceRight.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char reduceRightIndexed(@NotNull CharSequence reduceRightIndexed, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(reduceRightIndexed);
        if (lastIndex >= 0) {
            char charAt = reduceRightIndexed.charAt(lastIndex);
            for (int i = lastIndex - 1; i >= 0; i--) {
                charAt = operation.invoke(Integer.valueOf(i), Character.valueOf(reduceRightIndexed.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Character reduceRightOrNull(@NotNull CharSequence reduceRightOrNull, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(reduceRightOrNull);
        if (lastIndex < 0) {
            return null;
        }
        char charAt = reduceRightOrNull.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            charAt = operation.mo12248invoke(Character.valueOf(reduceRightOrNull.charAt(i)), Character.valueOf(charAt)).charValue();
        }
        return Character.valueOf(charAt);
    }

    @NotNull
    public static final CharSequence reversed(@NotNull CharSequence reversed) {
        Intrinsics.checkParameterIsNotNull(reversed, "$this$reversed");
        StringBuilder reverse = new StringBuilder(reversed).reverse();
        Intrinsics.checkExpressionValueIsNotNull(reverse, "StringBuilder(this).reverse()");
        return reverse;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <R> List<R> scan(@NotNull CharSequence scan, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkParameterIsNotNull(scan, "$this$scan");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        if (scan.length() == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(scan.length() + 1);
        arrayList.add(r);
        for (int i = 0; i < scan.length(); i++) {
            r = operation.mo12248invoke(r, Character.valueOf(scan.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <R> List<R> scanIndexed(@NotNull CharSequence scanIndexed, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkParameterIsNotNull(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        if (scanIndexed.length() == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(scanIndexed.length() + 1);
        arrayList.add(r);
        int length = scanIndexed.length();
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Character.valueOf(scanIndexed.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final List<Character> scanReduce(@NotNull CharSequence scanReduce, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        List<Character> emptyList;
        Intrinsics.checkParameterIsNotNull(scanReduce, "$this$scanReduce");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        if (scanReduce.length() == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        char charAt = scanReduce.charAt(0);
        ArrayList arrayList = new ArrayList(scanReduce.length());
        arrayList.add(Character.valueOf(charAt));
        int length = scanReduce.length();
        for (int i = 1; i < length; i++) {
            charAt = operation.mo12248invoke(Character.valueOf(charAt), Character.valueOf(scanReduce.charAt(i))).charValue();
            arrayList.add(Character.valueOf(charAt));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final List<Character> scanReduceIndexed(@NotNull CharSequence scanReduceIndexed, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        List<Character> emptyList;
        Intrinsics.checkParameterIsNotNull(scanReduceIndexed, "$this$scanReduceIndexed");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        if (scanReduceIndexed.length() == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        char charAt = scanReduceIndexed.charAt(0);
        ArrayList arrayList = new ArrayList(scanReduceIndexed.length());
        arrayList.add(Character.valueOf(charAt));
        int length = scanReduceIndexed.length();
        for (int i = 1; i < length; i++) {
            charAt = operation.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(scanReduceIndexed.charAt(i))).charValue();
            arrayList.add(Character.valueOf(charAt));
        }
        return arrayList;
    }

    public static char single(@NotNull CharSequence single) {
        Intrinsics.checkParameterIsNotNull(single, "$this$single");
        int length = single.length();
        if (length != 0) {
            if (length == 1) {
                return single.charAt(0);
            }
            throw new IllegalArgumentException("Char sequence has more than one element.");
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @Nullable
    public static Character singleOrNull(@NotNull CharSequence singleOrNull) {
        Intrinsics.checkParameterIsNotNull(singleOrNull, "$this$singleOrNull");
        if (singleOrNull.length() == 1) {
            return Character.valueOf(singleOrNull.charAt(0));
        }
        return null;
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence slice, @NotNull IntRange indices) {
        Intrinsics.checkParameterIsNotNull(slice, "$this$slice");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt__StringsKt.subSequence(slice, indices);
    }

    public static final int sumBy(@NotNull CharSequence sumBy, @NotNull Function1<? super Character, Integer> selector) {
        Intrinsics.checkParameterIsNotNull(sumBy, "$this$sumBy");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 0;
        for (int i2 = 0; i2 < sumBy.length(); i2++) {
            i += ((Number) GeneratedOutlineSupport1.outline19(sumBy, i2, selector)).intValue();
        }
        return i;
    }

    public static final double sumByDouble(@NotNull CharSequence sumByDouble, @NotNull Function1<? super Character, Double> selector) {
        Intrinsics.checkParameterIsNotNull(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        for (int i = 0; i < sumByDouble.length(); i++) {
            d += ((Number) GeneratedOutlineSupport1.outline19(sumByDouble, i, selector)).doubleValue();
        }
        return d;
    }

    @NotNull
    public static final CharSequence take(@NotNull CharSequence take, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(take, "$this$take");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, take.length());
            return take.subSequence(0, coerceAtMost);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence takeLast(@NotNull CharSequence takeLast, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(takeLast, "$this$takeLast");
        if (i >= 0) {
            int length = takeLast.length();
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, length);
            return takeLast.subSequence(length - coerceAtMost, length);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence takeLastWhile(@NotNull CharSequence takeLastWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(takeLastWhile); lastIndex >= 0; lastIndex--) {
            if (!((Boolean) GeneratedOutlineSupport1.outline19(takeLastWhile, lastIndex, predicate)).booleanValue()) {
                return takeLastWhile.subSequence(lastIndex + 1, takeLastWhile.length());
            }
        }
        return takeLastWhile.subSequence(0, takeLastWhile.length());
    }

    @NotNull
    public static final CharSequence takeWhile(@NotNull CharSequence takeWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(takeWhile, "$this$takeWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = takeWhile.length();
        for (int i = 0; i < length; i++) {
            if (!((Boolean) GeneratedOutlineSupport1.outline19(takeWhile, i, predicate)).booleanValue()) {
                return takeWhile.subSequence(0, i);
            }
        }
        return takeWhile.subSequence(0, takeWhile.length());
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence toCollection, @NotNull C destination) {
        Intrinsics.checkParameterIsNotNull(toCollection, "$this$toCollection");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        for (int i = 0; i < toCollection.length(); i++) {
            destination.add(Character.valueOf(toCollection.charAt(i)));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull CharSequence toHashSet) {
        int mapCapacity;
        Intrinsics.checkParameterIsNotNull(toHashSet, "$this$toHashSet");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(toHashSet.length());
        return (HashSet) toCollection(toHashSet, new HashSet(mapCapacity));
    }

    @NotNull
    public static final List<Character> toList(@NotNull CharSequence toList) {
        List<Character> emptyList;
        List<Character> listOf;
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        int length = toList.length();
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(toList);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(toList.charAt(0)));
            return listOf;
        }
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull CharSequence toMutableList) {
        Intrinsics.checkParameterIsNotNull(toMutableList, "$this$toMutableList");
        return (List) toCollection(toMutableList, new ArrayList(toMutableList.length()));
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull CharSequence toSet) {
        Set<Character> emptySet;
        Set<Character> of;
        int mapCapacity;
        Intrinsics.checkParameterIsNotNull(toSet, "$this$toSet");
        int length = toSet.length();
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(toSet.length());
            return (Set) toCollection(toSet, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Character.valueOf(toSet.charAt(0)));
            return of;
        }
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<String> windowed(@NotNull CharSequence windowed, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(windowed, "$this$windowed");
        return windowed(windowed, i, i2, z, StringsKt___StringsKt$windowed$1.INSTANCE);
    }

    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<String> windowedSequence(@NotNull CharSequence windowedSequence, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(windowedSequence, "$this$windowedSequence");
        return windowedSequence(windowedSequence, i, i2, z, StringsKt___StringsKt$windowedSequence$1.INSTANCE);
    }

    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z);
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull CharSequence withIndex) {
        Intrinsics.checkParameterIsNotNull(withIndex, "$this$withIndex");
        return new IndexingIterable(new StringsKt___StringsKt$withIndex$1(withIndex));
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull CharSequence zip, @NotNull CharSequence other, @NotNull Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkParameterIsNotNull(zip, "$this$zip");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int min = Math.min(zip.length(), other.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(transform.mo12248invoke(Character.valueOf(zip.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> zipWithNext(@NotNull CharSequence zipWithNext, @NotNull Function2<? super Character, ? super Character, ? extends R> transform) {
        List<R> emptyList;
        Intrinsics.checkParameterIsNotNull(zipWithNext, "$this$zipWithNext");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int length = zipWithNext.length() - 1;
        if (length < 1) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            i++;
            arrayList.add(transform.mo12248invoke(Character.valueOf(zipWithNext.charAt(i)), Character.valueOf(zipWithNext.charAt(i))));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull CharSequence any, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(any, "$this$any");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < any.length(); i++) {
            if (((Boolean) GeneratedOutlineSupport1.outline19(any, i, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> chunked(@NotNull CharSequence chunked, int i, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(chunked, "$this$chunked");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        return windowed(chunked, i, i, true, transform);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> Sequence<R> chunkedSequence(@NotNull CharSequence chunkedSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(chunkedSequence, "$this$chunkedSequence");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        return windowedSequence(chunkedSequence, i, i, true, transform);
    }

    public static final int count(@NotNull CharSequence count, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(count, "$this$count");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < count.length(); i2++) {
            if (((Boolean) GeneratedOutlineSupport1.outline19(count, i2, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence firstOrNull, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < firstOrNull.length(); i++) {
            char charAt = firstOrNull.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence lastOrNull, @NotNull Function1<? super Character, Boolean> predicate) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = lastOrNull.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = lastOrNull.charAt(length);
        } while (!predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    public static final boolean none(@NotNull CharSequence none, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(none, "$this$none");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < none.length(); i++) {
            if (((Boolean) GeneratedOutlineSupport1.outline19(none, i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull CharSequence random, @NotNull Random random2) {
        Intrinsics.checkParameterIsNotNull(random, "$this$random");
        Intrinsics.checkParameterIsNotNull(random2, "random");
        if (!(random.length() == 0)) {
            return random.charAt(random2.nextInt(random.length()));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final Character randomOrNull(@NotNull CharSequence randomOrNull, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkParameterIsNotNull(random, "random");
        if (randomOrNull.length() == 0) {
            return null;
        }
        return Character.valueOf(randomOrNull.charAt(random.nextInt(randomOrNull.length())));
    }

    @InlineOnly
    private static final String reversed(@NotNull String str) {
        if (str != null) {
            return reversed((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence singleOrNull, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        boolean z = false;
        Character ch = null;
        for (int i = 0; i < singleOrNull.length(); i++) {
            char charAt = singleOrNull.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                if (z) {
                    return null;
                }
                ch = Character.valueOf(charAt);
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        return ch;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> windowed(@NotNull CharSequence windowed, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(windowed, "$this$windowed");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        int length = windowed.length();
        int i3 = 0;
        ArrayList arrayList = new ArrayList((length / i2) + (length % i2 == 0 ? 0 : 1));
        while (i3 >= 0 && length > i3) {
            int i4 = i3 + i;
            if (i4 < 0 || i4 > length) {
                if (!z) {
                    break;
                }
                i4 = length;
            }
            arrayList.add(transform.mo12165invoke(windowed.subSequence(i3, i4)));
            i3 += i2;
        }
        return arrayList;
    }

    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z, function1);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> Sequence<R> windowedSequence(@NotNull CharSequence windowedSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        IntRange until;
        IntProgression step;
        Sequence asSequence;
        Sequence<R> map;
        Intrinsics.checkParameterIsNotNull(windowedSequence, "$this$windowedSequence");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        if (z) {
            until = StringsKt__StringsKt.getIndices(windowedSequence);
        } else {
            until = RangesKt___RangesKt.until(0, (windowedSequence.length() - i) + 1);
        }
        step = RangesKt___RangesKt.step(until, i2);
        asSequence = CollectionsKt___CollectionsKt.asSequence(step);
        map = SequencesKt___SequencesKt.map(asSequence, new StringsKt___StringsKt$windowedSequence$2(windowedSequence, i, transform));
        return map;
    }

    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z, function1);
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence associateByTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(associateByTo, "$this$associateByTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        for (int i = 0; i < associateByTo.length(); i++) {
            char charAt = associateByTo.charAt(i);
            destination.put(keySelector.mo12165invoke(Character.valueOf(charAt)), valueTransform.mo12165invoke(Character.valueOf(charAt)));
        }
        return destination;
    }

    @NotNull
    public static String drop(@NotNull String drop, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(drop, "$this$drop");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, drop.length());
            String substring = drop.substring(coerceAtMost);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final String dropLast(@NotNull String dropLast, int i) {
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(dropLast, "$this$dropLast");
        if (i >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(dropLast.length() - i, 0);
            return take(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final String filterNot(@NotNull String filterNot, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterNot, "$this$filterNot");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filterNot.length(); i++) {
            char charAt = filterNot.charAt(i);
            if (!predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterNotTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final String slice(@NotNull String slice, @NotNull IntRange indices) {
        Intrinsics.checkParameterIsNotNull(slice, "$this$slice");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt__StringsKt.substring(slice, indices);
    }

    @NotNull
    public static String take(@NotNull String take, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(take, "$this$take");
        if (i >= 0) {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, take.length());
            String substring = take.substring(0, coerceAtMost);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final String dropLastWhile(@NotNull String dropLastWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(dropLastWhile); lastIndex >= 0; lastIndex--) {
            if (!predicate.mo12165invoke(Character.valueOf(dropLastWhile.charAt(lastIndex))).booleanValue()) {
                String substring = dropLastWhile.substring(0, lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return "";
    }

    @NotNull
    public static final String dropWhile(@NotNull String dropWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(dropWhile, "$this$dropWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = dropWhile.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.mo12165invoke(Character.valueOf(dropWhile.charAt(i))).booleanValue()) {
                String substring = dropWhile.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return "";
    }

    @NotNull
    public static final String filterIndexed(@NotNull String filterIndexed, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < filterIndexed.length()) {
            char charAt = filterIndexed.charAt(i);
            int i3 = i2 + 1;
            if (predicate.mo12248invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterIndexedTo(StringBu…(), predicate).toString()");
        return sb2;
    }

    public static final char first(@NotNull CharSequence first, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(first, "$this$first");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < first.length(); i++) {
            char charAt = first.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                return charAt;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @NotNull
    public static String takeLast(@NotNull String takeLast, int i) {
        int coerceAtMost;
        Intrinsics.checkParameterIsNotNull(takeLast, "$this$takeLast");
        if (i >= 0) {
            int length = takeLast.length();
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(i, length);
            String substring = takeLast.substring(length - coerceAtMost);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull CharSequence zip, @NotNull CharSequence other) {
        Intrinsics.checkParameterIsNotNull(zip, "$this$zip");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int min = Math.min(zip.length(), other.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(Character.valueOf(zip.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence associateBy, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkParameterIsNotNull(associateBy, "$this$associateBy");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateBy.length());
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i = 0; i < associateBy.length(); i++) {
            char charAt = associateBy.charAt(i);
            linkedHashMap.put(keySelector.mo12165invoke(Character.valueOf(charAt)), valueTransform.mo12165invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final String filter(@NotNull String filter, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(filter, "$this$filter");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = filter.length();
        for (int i = 0; i < length; i++) {
            char charAt = filter.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    public static final char last(@NotNull CharSequence last, @NotNull Function1<? super Character, Boolean> predicate) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(last, "$this$last");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = last.length();
        do {
            length--;
            if (length >= 0) {
                charAt = last.charAt(length);
            } else {
                throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
            }
        } while (!predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue());
        return charAt;
    }

    public static final char single(@NotNull CharSequence single, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(single, "$this$single");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < single.length(); i++) {
            char charAt = single.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                if (!z) {
                    ch = Character.valueOf(charAt);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
            }
        }
        if (z) {
            if (ch == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
            }
            return ch.charValue();
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence slice, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(slice, "$this$slice");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(collectionSizeOrDefault);
        for (Integer num : indices) {
            sb.append(slice.charAt(num.intValue()));
        }
        return sb;
    }

    @NotNull
    public static final String takeLastWhile(@NotNull String takeLastWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(takeLastWhile); lastIndex >= 0; lastIndex--) {
            if (!predicate.mo12165invoke(Character.valueOf(takeLastWhile.charAt(lastIndex))).booleanValue()) {
                String substring = takeLastWhile.substring(lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return takeLastWhile;
    }

    @NotNull
    public static final String takeWhile(@NotNull String takeWhile, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(takeWhile, "$this$takeWhile");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = takeWhile.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.mo12165invoke(Character.valueOf(takeWhile.charAt(i))).booleanValue()) {
                String substring = takeWhile.substring(0, i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return takeWhile;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<Pair<Character, Character>> zipWithNext(@NotNull CharSequence zipWithNext) {
        List<Pair<Character, Character>> emptyList;
        Intrinsics.checkParameterIsNotNull(zipWithNext, "$this$zipWithNext");
        int length = zipWithNext.length() - 1;
        if (length < 1) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            char charAt = zipWithNext.charAt(i);
            i++;
            arrayList.add(TuplesKt.to(Character.valueOf(charAt), Character.valueOf(zipWithNext.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence groupByTo, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(groupByTo, "$this$groupByTo");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        for (int i = 0; i < groupByTo.length(); i++) {
            char charAt = groupByTo.charAt(i);
            K mo12165invoke = keySelector.mo12165invoke(Character.valueOf(charAt));
            Object obj = destination.get(mo12165invoke);
            if (obj == null) {
                obj = GeneratedOutlineSupport1.outline132(destination, mo12165invoke);
            }
            ((List) obj).add(valueTransform.mo12165invoke(Character.valueOf(charAt)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence groupBy, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(groupBy, "$this$groupBy");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < groupBy.length(); i++) {
            char charAt = groupBy.charAt(i);
            K mo12165invoke = keySelector.mo12165invoke(Character.valueOf(charAt));
            Object obj = linkedHashMap.get(mo12165invoke);
            if (obj == null) {
                obj = GeneratedOutlineSupport1.outline131(linkedHashMap, mo12165invoke);
            }
            ((List) obj).add(valueTransform.mo12165invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final Pair<String, String> partition(@NotNull String partition, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(partition, "$this$partition");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = partition.length();
        for (int i = 0; i < length; i++) {
            char charAt = partition.charAt(i);
            if (predicate.mo12165invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb.toString(), sb2.toString());
    }

    @InlineOnly
    private static final String slice(@NotNull String str, Iterable<Integer> iterable) {
        if (str != null) {
            return slice((CharSequence) str, iterable).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
