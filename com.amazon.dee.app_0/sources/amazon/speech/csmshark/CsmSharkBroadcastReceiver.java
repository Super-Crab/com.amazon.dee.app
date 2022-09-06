package amazon.speech.csmshark;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import org.json.JSONArray;
/* loaded from: classes.dex */
public class CsmSharkBroadcastReceiver extends BroadcastReceiver {
    static final String ACTION_GET_SHARKABLE = "CSMSHARK.GET_SHARKABLES";
    private static final String ACTION_NS = "CSMSHARK.";
    static final String ACTION_START_RECORDING = "CSMSHARK.START_RECORDING";
    static final String ACTION_STOP_RECORDING = "CSMSHARK.STOP_RECORDING";
    static final int MAX_TO_RETURN = 5;
    static final String NOT_RECORDING = "[{\"isRecording\":false}]";
    static final String NO_SHARKABLES = "[]";
    private static String TAG = DebugUtil.getTag(DebugUtil.Module.SHARK, CsmSharkBroadcastReceiver.class);
    private final ISharkableEnqueuer mCSMSharkListener;

    public CsmSharkBroadcastReceiver() {
        this(CsmSharkListener.getInstance());
    }

    private String getDefaultReturn() {
        return !this.mCSMSharkListener.isRecording() ? NOT_RECORDING : "[]";
    }

    private CSMSharkFlag[] getFlags(Intent intent) {
        CSMSharkFlag[] values;
        ArrayList arrayList = new ArrayList();
        for (CSMSharkFlag cSMSharkFlag : CSMSharkFlag.values()) {
            if (intent.getBooleanExtra(cSMSharkFlag.getName(), cSMSharkFlag.getDefault())) {
                arrayList.add(cSMSharkFlag);
            }
        }
        return (CSMSharkFlag[]) arrayList.toArray(new CSMSharkFlag[0]);
    }

    private String getSharkables() {
        JSONArray jSONArray = new JSONArray();
        for (Sharkable sharkable : this.mCSMSharkListener.getSharkables(5)) {
            jSONArray.put(SerializeSharkable.serializeSharkable(sharkable));
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of sharkables returned: ");
        outline107.append(jSONArray.length());
        Log.d(str, outline107.toString());
        return jSONArray.toString();
    }

    PendingResultWrapper getPendResult() {
        return new PendingResultWrapper(goAsync());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive: ");
        outline107.append(intent.getAction());
        Log.d(str, outline107.toString());
        if (ACTION_GET_SHARKABLE.equals(intent.getAction())) {
            PendingResultWrapper pendResult = getPendResult();
            String defaultReturn = getDefaultReturn();
            try {
                try {
                    String sharkables = getSharkables();
                    if (sharkables == null) {
                        Log.e(TAG, "sharkables are null");
                        pendResult.setResultData(defaultReturn);
                    } else {
                        if (sharkables.equals("[]")) {
                            sharkables = defaultReturn;
                        }
                        String str2 = TAG;
                        Log.d(str2, "setResults with string length: " + sharkables.length());
                        pendResult.setResultData(sharkables);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error processing sharkables:", e);
                    pendResult.setResultData(defaultReturn);
                    throw e;
                }
            } finally {
                pendResult.finish();
            }
        } else if (ACTION_START_RECORDING.equals(intent.getAction())) {
            this.mCSMSharkListener.startRecording(getFlags(intent));
        } else if (ACTION_STOP_RECORDING.equals(intent.getAction())) {
            this.mCSMSharkListener.stopRecording();
        } else {
            String str3 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("invalid action: ");
            outline1072.append(intent.getAction());
            Log.e(str3, outline1072.toString());
        }
    }

    CsmSharkBroadcastReceiver(ISharkableEnqueuer iSharkableEnqueuer) {
        this.mCSMSharkListener = iSharkableEnqueuer;
    }
}
