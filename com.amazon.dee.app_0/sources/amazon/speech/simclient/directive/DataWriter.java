package amazon.speech.simclient.directive;

import amazon.speech.simclient.directive.Piper;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public class DataWriter {
    private static final int BUF_SIZE = 4096;
    private static final int READ_TIMEOUT_MS = 60000;
    private final ExecutorService mExecutor;
    private final HandlerWrapper mHandler;
    private final Piper.Factory mPiperFactory;
    private static final String TAG = GeneratedOutlineSupport1.outline39(DataWriter.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final ExecutorService DEFAULT_EXECUTOR = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TimeoutHandler implements Runnable {
        private final Closeable[] mStreamsToClose;
        private Future<?> mTask;
        private final WriteFailedCallback mWriteFailedCallback;

        public TimeoutHandler(WriteFailedCallback writeFailedCallback, Closeable... closeableArr) {
            this.mWriteFailedCallback = writeFailedCallback;
            this.mStreamsToClose = closeableArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            Future<?> future = this.mTask;
            if (future != null) {
                future.cancel(true);
            }
            Closeable[] closeableArr = this.mStreamsToClose;
            if (closeableArr != null) {
                for (Closeable closeable : closeableArr) {
                    try {
                        closeable.close();
                    } catch (IOException unused) {
                    }
                }
            }
            WriteFailedCallback writeFailedCallback = this.mWriteFailedCallback;
            if (writeFailedCallback != null) {
                writeFailedCallback.onReadTimeout();
            }
        }

        public void setTask(Future<?> future) {
            this.mTask = future;
        }
    }

    /* loaded from: classes.dex */
    public interface WriteFailedCallback {
        void onIOException(IOException iOException);

        void onReadTimeout();
    }

    public DataWriter() {
        this(new Handler(Looper.getMainLooper()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postException(final WriteFailedCallback writeFailedCallback, final IOException iOException) {
        if (writeFailedCallback == null) {
            return;
        }
        Log.e(TAG, "Exception encountered writing to the pipe, posting to callback", iOException);
        this.mHandler.post(new Runnable() { // from class: amazon.speech.simclient.directive.DataWriter.2
            @Override // java.lang.Runnable
            public void run() {
                writeFailedCallback.onIOException(iOException);
            }
        });
    }

    public ParcelFileDescriptor writeToPipe(final InputStream inputStream, final WriteFailedCallback writeFailedCallback) throws IOException {
        final Piper create = this.mPiperFactory.create();
        final TimeoutHandler timeoutHandler = new TimeoutHandler(writeFailedCallback, inputStream, create);
        timeoutHandler.setTask(this.mExecutor.submit(new Callable<Void>() { // from class: amazon.speech.simclient.directive.DataWriter.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                try {
                    try {
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = inputStream.read(bArr, 0, bArr.length);
                                if (read <= 0) {
                                    break;
                                }
                                DataWriter.this.mHandler.postDelayed(timeoutHandler, 60000L);
                                create.write(bArr, 0, read);
                                DataWriter.this.mHandler.removeCallbacks(timeoutHandler);
                            }
                            String unused = DataWriter.TAG;
                            inputStream.close();
                        } catch (IOException unused2) {
                        }
                    } catch (IOException e) {
                        DataWriter.this.postException(writeFailedCallback, e);
                        DataWriter.this.mHandler.removeCallbacks(timeoutHandler);
                        inputStream.close();
                    }
                    try {
                        create.close();
                    } catch (IOException unused3) {
                        return null;
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException unused4) {
                    }
                    try {
                        create.close();
                    } catch (IOException unused5) {
                    }
                    throw th;
                }
            }
        }));
        return create.claimPipeReaderOwnership();
    }

    public DataWriter(Handler handler) {
        this(DEFAULT_EXECUTOR, Piper.Factory.DEFAULT, new HandlerWrapper(handler));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataWriter(ExecutorService executorService, Piper.Factory factory, HandlerWrapper handlerWrapper) {
        this.mExecutor = executorService;
        this.mPiperFactory = factory;
        this.mHandler = handlerWrapper;
    }
}
