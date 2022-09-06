package io.ktor.network.selector;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.channels.spi.SelectorProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SelectorManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J!\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/network/selector/SelectorManager;", "", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, "Ljava/nio/channels/spi/SelectorProvider;", "provider$annotations", "()V", "getProvider", "()Ljava/nio/channels/spi/SelectorProvider;", "notifyClosed", "", "s", "Lio/ktor/network/selector/Selectable;", "select", "selectable", "interest", "Lio/ktor/network/selector/SelectInterest;", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectInterest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface SelectorManager {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: SelectorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/selector/SelectorManager$Companion;", "", "()V", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    /* compiled from: SelectorManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @KtorExperimentalAPI
        public static /* synthetic */ void provider$annotations() {
        }
    }

    @NotNull
    SelectorProvider getProvider();

    void notifyClosed(@NotNull Selectable selectable);

    @Nullable
    Object select(@NotNull Selectable selectable, @NotNull SelectInterest selectInterest, @NotNull Continuation<? super Unit> continuation);
}
