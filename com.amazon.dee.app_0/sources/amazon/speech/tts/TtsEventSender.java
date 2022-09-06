package amazon.speech.tts;

import amazon.speech.tts.TtsEventListener;
import amazon.speech.util.HandlerWrapper;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class TtsEventSender {
    private static final boolean DEBUG = true;
    private static final String TAG = "TtsEventSender";
    private final HandlerWrapper mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SpeechCompletedRunnable implements Runnable {
        final TtsListener mListener;
        final TtsEventListener.CompletionCode mReason;
        final long mTimestamp;

        SpeechCompletedRunnable(TtsListener ttsListener, long j, TtsEventListener.CompletionCode completionCode) {
            this.mListener = ttsListener;
            this.mReason = completionCode;
            this.mTimestamp = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            TtsListener ttsListener = this.mListener;
            if (ttsListener instanceof TtsEventListener) {
                String unused = TtsEventSender.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Firing onSpeechEnded ");
                outline107.append(this.mReason);
                outline107.toString();
                ((TtsEventListener) this.mListener).onSpeechEnded(this.mTimestamp, this.mReason);
            } else if (!(ttsListener instanceof SpeechEndListener)) {
                if (ttsListener == null) {
                    return;
                }
                String unused2 = TtsEventSender.TAG;
                this.mListener.onSpeechCompleted();
            } else {
                String unused3 = TtsEventSender.TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Firing onSpeechEnded ");
                outline1072.append(this.mReason);
                outline1072.toString();
                ((SpeechEndListener) this.mListener).onSpeechEnded(this.mReason.getCode());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SpeechErrorRunnable implements Runnable {
        final TtsEventListener.ErrorCode mErrorCode;
        final String mErrorMessage;
        final TtsListener mListener;
        final long mTimestamp;

        SpeechErrorRunnable(TtsListener ttsListener, long j, TtsEventListener.ErrorCode errorCode, String str) {
            this.mListener = ttsListener;
            this.mErrorCode = errorCode;
            this.mErrorMessage = str;
            this.mTimestamp = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            TtsListener ttsListener = this.mListener;
            if (ttsListener instanceof TtsEventListener) {
                ((TtsEventListener) ttsListener).onSpeechError(this.mTimestamp, this.mErrorCode, this.mErrorMessage);
            } else if (ttsListener instanceof SpeechEndListener) {
                ((SpeechEndListener) ttsListener).onSpeechError(this.mErrorCode.getCode(), this.mErrorMessage);
            } else if (ttsListener == null) {
            } else {
                ttsListener.onSpeechError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SpeechStartedRunnable implements Runnable {
        final TtsListener mListener;
        final long mTimestamp;

        SpeechStartedRunnable(TtsListener ttsListener, long j) {
            this.mListener = ttsListener;
            this.mTimestamp = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            TtsListener ttsListener = this.mListener;
            if (ttsListener instanceof TtsEventListener) {
                ((TtsEventListener) ttsListener).onSpeechStarted(this.mTimestamp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class UriConnectionEstablishedRunnable implements Runnable {
        final TtsListener mListener;
        final long mTimestamp;

        UriConnectionEstablishedRunnable(TtsListener ttsListener, long j) {
            this.mListener = ttsListener;
            this.mTimestamp = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            TtsListener ttsListener = this.mListener;
            if (ttsListener instanceof TtsEventListener) {
                ((TtsEventListener) ttsListener).onUriConnectionEstablished(this.mTimestamp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class UriConnectionStartedRunnable implements Runnable {
        final TtsListener mListener;
        final long mTimestamp;

        UriConnectionStartedRunnable(TtsListener ttsListener, long j) {
            this.mListener = ttsListener;
            this.mTimestamp = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            TtsListener ttsListener = this.mListener;
            if (ttsListener instanceof TtsEventListener) {
                ((TtsEventListener) ttsListener).onUriConnectionStarted(this.mTimestamp);
            }
        }
    }

    public TtsEventSender(HandlerWrapper handlerWrapper) {
        this.mHandler = handlerWrapper;
    }

    public void fireSpeechCompleted(TtsListener ttsListener, long j, TtsEventListener.CompletionCode completionCode) {
        String str = "fireSpeechCompleted " + completionCode;
        this.mHandler.post(new SpeechCompletedRunnable(ttsListener, j, completionCode));
    }

    public void fireSpeechError(TtsListener ttsListener, long j, TtsEventListener.ErrorCode errorCode, String str) {
        String str2 = TAG;
        Log.e(str2, "fireSpeechError " + errorCode + " " + str);
        this.mHandler.post(new SpeechErrorRunnable(ttsListener, j, errorCode, str));
    }

    public void fireSpeechStarted(TtsListener ttsListener, long j) {
        this.mHandler.post(new SpeechStartedRunnable(ttsListener, j));
    }

    public void fireUriConnectionEstablished(TtsListener ttsListener, long j) {
        this.mHandler.post(new UriConnectionEstablishedRunnable(ttsListener, j));
    }

    public void fireUriConnectionStarted(TtsListener ttsListener, long j) {
        this.mHandler.post(new UriConnectionStartedRunnable(ttsListener, j));
    }
}
