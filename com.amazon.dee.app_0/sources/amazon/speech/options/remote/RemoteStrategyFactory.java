package amazon.speech.options.remote;

import amazon.speech.config.SpeechPolicyFactory;
import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.content.Context;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes.dex */
public class RemoteStrategyFactory {
    protected static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.CONF);
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CONF, RemoteStrategyFactory.class);

    private RemoteSettingsStrategy createStrategyFromReflection(String str, Context context, String str2) {
        try {
            return (RemoteSettingsStrategy) Class.forName(str).getConstructor(Context.class, String.class).newInstance(context, str2);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RemoteSettingsStrategy create(Context context, String str) {
        RemoteSettingsStrategy createStrategyFromReflection = SpeechPolicyFactory.getPolicy(context).supportsArcusService() ? createStrategyFromReflection("amazon.speech.options.remote.ArcusStrategy", context, str) : null;
        if (createStrategyFromReflection == null) {
            Log.e(TAG, "No impl available for RemoteSettingsStrategy, fallback to NullStrategy");
            return new NullStrategy();
        }
        return createStrategyFromReflection;
    }
}
