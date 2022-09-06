package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.phonecallcontroller.AcceptNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.EndNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.NoCallPermissionHandler;
import com.amazon.deecomms.util.TimeoutHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class NativeCallActivity_MembersInjector implements MembersInjector<NativeCallActivity> {
    private final Provider<AcceptNativeCallHandler> acceptNativeCallHandlerProvider;
    private final Provider<EndNativeCallHandler> endNativeCallHandlerProvider;
    private final Provider<MakeNativeCallHandler> makeNativeCallHandlerProvider;
    private final Provider<NoCallPermissionHandler> noCallPermissionHandlerProvider;
    private final Provider<TimeoutHelper> timeoutHelperProvider;

    public NativeCallActivity_MembersInjector(Provider<MakeNativeCallHandler> provider, Provider<AcceptNativeCallHandler> provider2, Provider<EndNativeCallHandler> provider3, Provider<NoCallPermissionHandler> provider4, Provider<TimeoutHelper> provider5) {
        this.makeNativeCallHandlerProvider = provider;
        this.acceptNativeCallHandlerProvider = provider2;
        this.endNativeCallHandlerProvider = provider3;
        this.noCallPermissionHandlerProvider = provider4;
        this.timeoutHelperProvider = provider5;
    }

    public static MembersInjector<NativeCallActivity> create(Provider<MakeNativeCallHandler> provider, Provider<AcceptNativeCallHandler> provider2, Provider<EndNativeCallHandler> provider3, Provider<NoCallPermissionHandler> provider4, Provider<TimeoutHelper> provider5) {
        return new NativeCallActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAcceptNativeCallHandler(NativeCallActivity nativeCallActivity, AcceptNativeCallHandler acceptNativeCallHandler) {
        nativeCallActivity.acceptNativeCallHandler = acceptNativeCallHandler;
    }

    public static void injectEndNativeCallHandler(NativeCallActivity nativeCallActivity, EndNativeCallHandler endNativeCallHandler) {
        nativeCallActivity.endNativeCallHandler = endNativeCallHandler;
    }

    public static void injectMakeNativeCallHandler(NativeCallActivity nativeCallActivity, MakeNativeCallHandler makeNativeCallHandler) {
        nativeCallActivity.makeNativeCallHandler = makeNativeCallHandler;
    }

    public static void injectNoCallPermissionHandler(NativeCallActivity nativeCallActivity, NoCallPermissionHandler noCallPermissionHandler) {
        nativeCallActivity.noCallPermissionHandler = noCallPermissionHandler;
    }

    public static void injectTimeoutHelper(NativeCallActivity nativeCallActivity, TimeoutHelper timeoutHelper) {
        nativeCallActivity.timeoutHelper = timeoutHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NativeCallActivity nativeCallActivity) {
        nativeCallActivity.makeNativeCallHandler = this.makeNativeCallHandlerProvider.mo10268get();
        nativeCallActivity.acceptNativeCallHandler = this.acceptNativeCallHandlerProvider.mo10268get();
        nativeCallActivity.endNativeCallHandler = this.endNativeCallHandlerProvider.mo10268get();
        nativeCallActivity.noCallPermissionHandler = this.noCallPermissionHandlerProvider.mo10268get();
        nativeCallActivity.timeoutHelper = this.timeoutHelperProvider.mo10268get();
    }
}
