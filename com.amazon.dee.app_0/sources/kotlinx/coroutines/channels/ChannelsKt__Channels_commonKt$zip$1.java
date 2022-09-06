package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: Channels.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Lkotlin/Pair;", ExifInterface.LONGITUDE_EAST, "R", "t1", "t2", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class ChannelsKt__Channels_commonKt$zip$1 extends Lambda implements Function2<E, R, Pair<? extends E, ? extends R>> {
    public static final ChannelsKt__Channels_commonKt$zip$1 INSTANCE = new ChannelsKt__Channels_commonKt$zip$1();

    ChannelsKt__Channels_commonKt$zip$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Object mo12248invoke(Object obj, Object obj2) {
        return mo12248invoke((ChannelsKt__Channels_commonKt$zip$1) obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function2
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Pair<E, R> mo12248invoke(E e, R r) {
        return TuplesKt.to(e, r);
    }
}
