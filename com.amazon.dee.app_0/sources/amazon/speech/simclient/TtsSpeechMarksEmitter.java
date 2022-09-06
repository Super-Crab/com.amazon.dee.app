package amazon.speech.simclient;

import amazon.speech.broadcasthelpers.SpeechMarkBroadcaster;
import amazon.speech.util.HandlerWrapper;
import amazon.speech.util.Log;
import android.content.Context;
import android.text.TextUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
class TtsSpeechMarksEmitter implements ITtsSpeechMarksEmitter {
    public static final String SPEECH_MARKS_SUPPRESSION_PROPERTY = "persist.ww.speechmarks.suppress";
    private static final String TAG = "TtsSpeechMarksEmitter";
    private static final String TIME_KEY = "\"time\":";
    private static Pattern mMatchPattern = Pattern.compile("[^\\d]");
    final Context mContext;
    ICurrentTimeProvider mCurrentTimeProvider;
    HandlerWrapper mEventFiringHandlerWrapper;
    Queue<String> mMarksQueue;
    final List<Runnable> mRunnablesPosted;
    SpeechMarkBroadcaster mSpeechMarkBroadcaster;
    long mStartTime;
    private final TtsSpeechMarksListener mTtsSpeechMarksListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ICurrentTimeProvider {
        long getCurrentTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsSpeechMarksEmitter(TtsSpeechMarksListener ttsSpeechMarksListener, HandlerWrapper handlerWrapper, Context context) {
        this(ttsSpeechMarksListener, handlerWrapper, context, new SpeechMarkBroadcaster(context));
    }

    private int indexOfRegex(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
    public synchronized void clear() {
        this.mStartTime = 0L;
        this.mMarksQueue.clear();
        for (Runnable runnable : this.mRunnablesPosted) {
            this.mEventFiringHandlerWrapper.removeCallbacks(runnable);
        }
    }

    void doScheduleSpeechMark(final String str) {
        long markTime = getMarkTime(str) - this.mCurrentTimeProvider.getCurrentTime();
        long j = 0;
        if (markTime >= 0) {
            j = markTime;
        }
        Runnable runnable = new Runnable() { // from class: amazon.speech.simclient.TtsSpeechMarksEmitter.2
            @Override // java.lang.Runnable
            public void run() {
                if (TtsSpeechMarksEmitter.this.mTtsSpeechMarksListener != null) {
                    TtsSpeechMarksEmitter.this.mTtsSpeechMarksListener.onSpeechMark(str);
                }
                SpeechMarkBroadcaster speechMarkBroadcaster = TtsSpeechMarksEmitter.this.mSpeechMarkBroadcaster;
                if (speechMarkBroadcaster != null) {
                    speechMarkBroadcaster.processSpeechMark(str);
                }
            }
        };
        this.mRunnablesPosted.add(runnable);
        this.mEventFiringHandlerWrapper.postDelayed(runnable, j);
    }

    @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
    public void errorParsingSpeechmarks(Throwable th) {
        SpeechMarkBroadcaster speechMarkBroadcaster = this.mSpeechMarkBroadcaster;
        if (speechMarkBroadcaster != null) {
            speechMarkBroadcaster.errorParsingSpeechmarks(th.getLocalizedMessage());
        }
    }

    long getMarkTime(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = str.indexOf(TIME_KEY)) > -1) {
            String substring = str.substring(indexOf + 7);
            int i = 0;
            try {
                i = Integer.valueOf(substring.substring(0, indexOfRegex(mMatchPattern, substring))).intValue();
            } catch (Exception e) {
                Log.w(TAG, "error parsing time", e);
            }
            return this.mStartTime + i;
        }
        return this.mStartTime;
    }

    @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
    public synchronized void scheduleSpeechMark(String str) {
        if (0 == this.mStartTime) {
            this.mMarksQueue.add(str);
        } else {
            doScheduleSpeechMark(str);
        }
    }

    @Override // amazon.speech.simclient.ITtsSpeechMarksEmitter
    public synchronized void start() {
        if (this.mStartTime > 0) {
            Log.w(TAG, "emitter already started, ignoring start()");
            return;
        }
        this.mStartTime = this.mCurrentTimeProvider.getCurrentTime();
        while (!this.mMarksQueue.isEmpty()) {
            doScheduleSpeechMark(this.mMarksQueue.poll());
        }
    }

    TtsSpeechMarksEmitter(TtsSpeechMarksListener ttsSpeechMarksListener, HandlerWrapper handlerWrapper, Context context, SpeechMarkBroadcaster speechMarkBroadcaster) {
        this.mMarksQueue = new LinkedList();
        this.mRunnablesPosted = new LinkedList();
        this.mCurrentTimeProvider = new ICurrentTimeProvider() { // from class: amazon.speech.simclient.TtsSpeechMarksEmitter.1
            @Override // amazon.speech.simclient.TtsSpeechMarksEmitter.ICurrentTimeProvider
            public long getCurrentTime() {
                return System.currentTimeMillis();
            }
        };
        if (handlerWrapper != null) {
            if (context != null) {
                this.mTtsSpeechMarksListener = ttsSpeechMarksListener;
                this.mEventFiringHandlerWrapper = handlerWrapper;
                this.mContext = context;
                this.mSpeechMarkBroadcaster = speechMarkBroadcaster;
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }
        throw new IllegalArgumentException("HandlerWrapper must not be null.");
    }
}
