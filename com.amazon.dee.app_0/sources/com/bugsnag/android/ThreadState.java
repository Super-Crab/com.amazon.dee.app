package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ThreadState implements JsonStream.Streamable {
    private static final String THREAD_TYPE = "android";
    private final CachedThread[] cachedThreads;

    public ThreadState(@NonNull Configuration configuration, @NonNull Thread thread, @NonNull Map<Thread, StackTraceElement[]> map, @Nullable Throwable th) {
        if (!map.containsKey(thread)) {
            map.put(thread, thread.getStackTrace());
        }
        if (th != null) {
            map.put(thread, th.getStackTrace());
        }
        long id = thread.getId();
        Thread[] sortThreadsById = sortThreadsById(map);
        this.cachedThreads = new CachedThread[sortThreadsById.length];
        for (int i = 0; i < sortThreadsById.length; i++) {
            Thread thread2 = sortThreadsById[i];
            this.cachedThreads[i] = new CachedThread(configuration, thread2.getId(), thread2.getName(), THREAD_TYPE, thread2.getId() == id, map.get(thread2));
        }
    }

    private Thread[] sortThreadsById(Map<Thread, StackTraceElement[]> map) {
        Thread[] threadArr = (Thread[]) map.keySet().toArray(new Thread[0]);
        Arrays.sort(threadArr, new Comparator<Thread>() { // from class: com.bugsnag.android.ThreadState.1
            @Override // java.util.Comparator
            public int compare(@NonNull Thread thread, @NonNull Thread thread2) {
                return Long.valueOf(thread.getId()).compareTo(Long.valueOf(thread2.getId()));
            }
        });
        return threadArr;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginArray();
        for (CachedThread cachedThread : this.cachedThreads) {
            jsonStream.value((JsonStream.Streamable) cachedThread);
        }
        jsonStream.endArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadState(@NonNull CachedThread[] cachedThreadArr) {
        this.cachedThreads = cachedThreadArr;
    }
}
