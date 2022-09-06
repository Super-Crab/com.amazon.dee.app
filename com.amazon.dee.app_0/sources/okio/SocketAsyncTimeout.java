package okio;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Okio.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokio/SocketAsyncTimeout;", "Lokio/AsyncTimeout;", "socket", "Ljava/net/Socket;", "(Ljava/net/Socket;)V", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "", "okio"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SocketAsyncTimeout extends AsyncTimeout {
    private final Logger logger;
    private final Socket socket;

    public SocketAsyncTimeout(@NotNull Socket socket) {
        Intrinsics.checkParameterIsNotNull(socket, "socket");
        this.socket = socket;
        this.logger = Logger.getLogger("okio.Okio");
    }

    @Override // okio.AsyncTimeout
    @NotNull
    protected IOException newTimeoutException(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // okio.AsyncTimeout
    protected void timedOut() {
        try {
            this.socket.close();
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                Logger logger = this.logger;
                Level level = Level.WARNING;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to close timed out socket ");
                outline107.append(this.socket);
                logger.log(level, outline107.toString(), (Throwable) e);
                return;
            }
            throw e;
        } catch (Exception e2) {
            Logger logger2 = this.logger;
            Level level2 = Level.WARNING;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed to close timed out socket ");
            outline1072.append(this.socket);
            logger2.log(level2, outline1072.toString(), (Throwable) e2);
        }
    }
}
