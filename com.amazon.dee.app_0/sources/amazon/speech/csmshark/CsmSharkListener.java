package amazon.speech.csmshark;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class CsmSharkListener implements ICsmInterceptor, ISharkableEnqueuer {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SHARK, CsmSharkListener.class);
    private static final CsmSharkListener sInstance = new CsmSharkListener();
    private boolean doRecord;
    private Set<CSMSharkFlag> mFlags;
    private final Queue<Sharkable> mSharkableQueue;

    private CsmSharkListener() {
        this(new ConcurrentLinkedQueue(), new HashSet());
    }

    public static CsmSharkListener getInstance() {
        return sInstance;
    }

    @Override // amazon.speech.csmshark.ISharkableEnqueuer
    public Collection<Sharkable> getSharkables(int i) {
        int i2 = 0;
        boolean z = i <= 0;
        LinkedList linkedList = new LinkedList();
        while (!this.mSharkableQueue.isEmpty() && (z || i2 < i)) {
            Sharkable poll = this.mSharkableQueue.poll();
            if (poll != null) {
                linkedList.add(poll);
                i2++;
            } else {
                Log.i(TAG, "Pulled nothing off the queue");
            }
        }
        return linkedList;
    }

    @Override // amazon.speech.csmshark.ISharkableEnqueuer
    public boolean isRecording() {
        return this.doRecord;
    }

    @Override // amazon.speech.csmshark.ICsmInterceptor
    public void onSharkable(Sharkable sharkable) {
        Log.d(TAG, "onSharkable");
        if (sharkable == null) {
            Log.e(TAG, "sharkable is null");
        } else if (this.doRecord) {
            if (this.mFlags.contains(CSMSharkFlag.ENQUEUE_SHARKABLES)) {
                this.mSharkableQueue.add(sharkable);
            }
            if (!this.mFlags.contains(CSMSharkFlag.LOG_SHARKABLES)) {
                return;
            }
            for (String str : SerializeSharkable.convertToLoggable(sharkable)) {
                Log.d(TAG, str);
            }
        }
    }

    @Override // amazon.speech.csmshark.ISharkableEnqueuer
    public void startRecording(CSMSharkFlag... cSMSharkFlagArr) {
        Log.d(TAG, "startRecording");
        HashSet hashSet = new HashSet(Arrays.asList(cSMSharkFlagArr));
        if (hashSet.isEmpty()) {
            hashSet.add(CSMSharkFlag.ENQUEUE_SHARKABLES);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("recording with flags: [");
        outline107.append(TextUtils.join(", ", CSMSharkFlag.getFlagNames((CSMSharkFlag[]) hashSet.toArray(new CSMSharkFlag[0]))));
        outline107.append("]");
        Log.d(str, outline107.toString());
        this.mFlags.addAll(hashSet);
        this.doRecord = true;
    }

    @Override // amazon.speech.csmshark.ISharkableEnqueuer
    public void stopRecording() {
        Log.d(TAG, "stopRecording");
        this.doRecord = false;
        this.mFlags.clear();
        this.mSharkableQueue.clear();
    }

    CsmSharkListener(Queue<Sharkable> queue) {
        this.doRecord = false;
        this.mSharkableQueue = queue;
        this.mFlags = new HashSet();
    }

    CsmSharkListener(Queue<Sharkable> queue, Set<CSMSharkFlag> set) {
        this.doRecord = false;
        this.mSharkableQueue = queue;
        this.mFlags = set;
    }
}
