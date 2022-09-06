package com.facebook.react.bridge;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.react.common.ReactConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class Inspector {
    private final HybridData mHybridData;

    /* loaded from: classes2.dex */
    public static class LocalConnection {
        private final HybridData mHybridData;

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }

        public native void disconnect();

        public native void sendMessage(String str);
    }

    /* loaded from: classes2.dex */
    public static class Page {
        private final int mId;
        private final String mTitle;
        private final String mVM;

        private Page(int i, String str, String str2) {
            this.mId = i;
            this.mTitle = str;
            this.mVM = str2;
        }

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Page{mId=");
            outline107.append(this.mId);
            outline107.append(", mTitle='");
            return GeneratedOutlineSupport1.outline90(outline107, this.mTitle, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }
    }

    /* loaded from: classes2.dex */
    public interface RemoteConnection {
        void onDisconnect();

        void onMessage(String str);
    }

    static {
        ReactBridge.staticInit();
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public static LocalConnection connect(int i, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i, remoteConnection);
        } catch (UnsatisfiedLinkError e) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e);
            throw new RuntimeException(e);
        }
    }

    private native LocalConnection connectNative(int i, RemoteConnection remoteConnection);

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e);
            return Collections.emptyList();
        }
    }

    private native Page[] getPagesNative();

    private static native Inspector instance();
}
