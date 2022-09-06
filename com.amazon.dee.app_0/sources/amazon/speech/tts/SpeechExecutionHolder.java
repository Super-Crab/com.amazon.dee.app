package amazon.speech.tts;

import amazon.speech.tts.TtsMediaPlayer;
import amazon.speech.util.DebugUtil;
import amazon.speech.util.HandlerWrapper;
import amazon.speech.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class SpeechExecutionHolder {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, SpeechExecutionHolder.class);
    private final ExecutorService mCleanupConnectionExecutor;
    volatile HttpURLConnection mConnection;
    private Future<HttpURLConnection> mConnectionFuture;
    private final ExecutorService mEstablishConnectionExecutor;
    private final long mHttpConnectTimeout;
    final SpeechExecutionHolderedRunnable mPayloadRunnable;
    private final TtsPrewarmTracker mPrewarmTracker;
    private final HandlerWrapper mSpeakHandlerWrapper;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CleanupRunnable implements Runnable {
        final HttpURLConnection mConnection;
        final Future<HttpURLConnection> mConnectionFuture;

        CleanupRunnable(HttpURLConnection httpURLConnection, Future<HttpURLConnection> future) {
            this.mConnection = httpURLConnection;
            this.mConnectionFuture = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            Future<HttpURLConnection> future = this.mConnectionFuture;
            if (future != null) {
                future.cancel(true);
            }
            HttpURLConnection httpURLConnection = this.mConnection;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class EstablishConnectionCallable implements Callable<HttpURLConnection> {
        final TtsMediaPlayer.ConnectionMetricsListener mConnectionMetricsListener;
        final TtsPrewarmTracker mPrewarmTracker;
        final UrlWrapper mUrlWrapper;

        EstablishConnectionCallable(UrlWrapper urlWrapper, TtsMediaPlayer.ConnectionMetricsListener connectionMetricsListener, TtsPrewarmTracker ttsPrewarmTracker) {
            if (urlWrapper != null) {
                if (connectionMetricsListener != null) {
                    this.mUrlWrapper = urlWrapper;
                    this.mConnectionMetricsListener = connectionMetricsListener;
                    this.mPrewarmTracker = ttsPrewarmTracker;
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(new StringBuilder(), SpeechExecutionHolder.TAG, " ConnectionMetricsListener cannot be null"));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(new StringBuilder(), SpeechExecutionHolder.TAG, " UrlWrapper cannot be null"));
        }

        @Override // java.util.concurrent.Callable
        public HttpURLConnection call() throws IOException {
            long j;
            if (this.mPrewarmTracker == null) {
                Log.i(SpeechExecutionHolder.TAG, "Skipped wait for prewarm.");
                j = 0;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                this.mPrewarmTracker.await();
                j = System.currentTimeMillis() - currentTimeMillis;
                Log.i(SpeechExecutionHolder.TAG, String.format("Waited %d ms for prewarm to complete", Long.valueOf(j)));
            }
            this.mConnectionMetricsListener.onPrewarmWaitComplete(j);
            try {
                return this.mUrlWrapper.openConnection();
            } catch (MalformedURLException | UnknownHostException e) {
                this.mConnectionMetricsListener.onBadUrl();
                throw e;
            }
        }
    }

    public SpeechExecutionHolder(SpeechExecutionHolderedRunnable speechExecutionHolderedRunnable, HandlerWrapper handlerWrapper, ExecutorService executorService, ExecutorService executorService2, long j, TtsPrewarmTracker ttsPrewarmTracker) {
        if (speechExecutionHolderedRunnable != null) {
            if (handlerWrapper == null) {
                throw new IllegalArgumentException("handlerWrapper cannot be null");
            }
            if (executorService == null) {
                throw new IllegalArgumentException("cleanupConnectionExecutor cannot be null");
            }
            if (executorService2 != null) {
                this.mPayloadRunnable = speechExecutionHolderedRunnable;
                this.mPayloadRunnable.setSpeechExecutionHolder(this);
                this.mSpeakHandlerWrapper = handlerWrapper;
                this.mCleanupConnectionExecutor = executorService;
                this.mEstablishConnectionExecutor = executorService2;
                this.mHttpConnectTimeout = j;
                this.mPrewarmTracker = ttsPrewarmTracker;
                return;
            }
            throw new IllegalArgumentException("establishConnectionExecutor cannot be null");
        }
        throw new IllegalArgumentException("payload cannot be null");
    }

    private synchronized long getRemainingPrewarmWaitTimeMs() {
        long j = 0;
        if (this.mPrewarmTracker == null) {
            return 0L;
        }
        long remainingWaitTimeMs = this.mPrewarmTracker.getRemainingWaitTimeMs();
        if (remainingWaitTimeMs > 0) {
            j = remainingWaitTimeMs;
        }
        return j;
    }

    public void abortPayload() {
        this.mCleanupConnectionExecutor.submit(newCleanupRunnable(this.mConnection, this.mConnectionFuture));
    }

    public void executePayload() {
        this.mSpeakHandlerWrapper.post(this.mPayloadRunnable);
    }

    CleanupRunnable newCleanupRunnable(HttpURLConnection httpURLConnection, Future<HttpURLConnection> future) {
        return new CleanupRunnable(httpURLConnection, future);
    }

    EstablishConnectionCallable newEstablishConnectionCallable(UrlWrapper urlWrapper, TtsMediaPlayer.ConnectionMetricsListener connectionMetricsListener) {
        return new EstablishConnectionCallable(urlWrapper, connectionMetricsListener, this.mPrewarmTracker);
    }

    public void startEstablishConnection(UrlWrapper urlWrapper, TtsMediaPlayer.ConnectionMetricsListener connectionMetricsListener) {
        this.mConnectionFuture = this.mEstablishConnectionExecutor.submit(newEstablishConnectionCallable(urlWrapper, connectionMetricsListener));
    }

    public HttpURLConnection waitForReceiveConnection() throws InterruptedException, ConnectException {
        try {
            this.mConnection = this.mConnectionFuture.get(this.mHttpConnectTimeout + getRemainingPrewarmWaitTimeMs(), TimeUnit.MILLISECONDS);
        } catch (CancellationException unused) {
            throw new ConnectException("Connection aborted");
        } catch (ExecutionException e) {
            Log.e(TAG, "Error waiting for connection future.", e);
        } catch (TimeoutException unused2) {
            throw new ConnectException("Timed out establishing connection.");
        }
        if (!this.mConnectionFuture.isCancelled()) {
            return this.mConnection;
        }
        throw new ConnectException("Connection aborted");
    }
}
