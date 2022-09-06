package com.amazon.rtcsc.wrappers;

import com.amazon.rtcsc.wrappers.RTCAppClientListenerInterface;
import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
/* loaded from: classes13.dex */
public class RTCControllerAndroidJNI {
    static {
        swig_module_init();
    }

    public static final native long CounterVector_capacity(long j, CounterVector counterVector);

    public static final native void CounterVector_clear(long j, CounterVector counterVector);

    public static final native void CounterVector_doAdd__SWIG_0(long j, CounterVector counterVector, long j2, RTCCustomMetricInterface.Counter counter);

    public static final native void CounterVector_doAdd__SWIG_1(long j, CounterVector counterVector, int i, long j2, RTCCustomMetricInterface.Counter counter);

    public static final native long CounterVector_doGet(long j, CounterVector counterVector, int i);

    public static final native long CounterVector_doRemove(long j, CounterVector counterVector, int i);

    public static final native void CounterVector_doRemoveRange(long j, CounterVector counterVector, int i, int i2);

    public static final native long CounterVector_doSet(long j, CounterVector counterVector, int i, long j2, RTCCustomMetricInterface.Counter counter);

    public static final native int CounterVector_doSize(long j, CounterVector counterVector);

    public static final native boolean CounterVector_isEmpty(long j, CounterVector counterVector);

    public static final native void CounterVector_reserve(long j, CounterVector counterVector, long j2);

    public static final native long MetadataVector_capacity(long j, MetadataVector metadataVector);

    public static final native void MetadataVector_clear(long j, MetadataVector metadataVector);

    public static final native void MetadataVector_doAdd__SWIG_0(long j, MetadataVector metadataVector, long j2, RTCCustomMetricInterface.Metadata metadata);

    public static final native void MetadataVector_doAdd__SWIG_1(long j, MetadataVector metadataVector, int i, long j2, RTCCustomMetricInterface.Metadata metadata);

    public static final native long MetadataVector_doGet(long j, MetadataVector metadataVector, int i);

    public static final native long MetadataVector_doRemove(long j, MetadataVector metadataVector, int i);

    public static final native void MetadataVector_doRemoveRange(long j, MetadataVector metadataVector, int i, int i2);

    public static final native long MetadataVector_doSet(long j, MetadataVector metadataVector, int i, long j2, RTCCustomMetricInterface.Metadata metadata);

    public static final native int MetadataVector_doSize(long j, MetadataVector metadataVector);

    public static final native boolean MetadataVector_isEmpty(long j, MetadataVector metadataVector);

    public static final native void MetadataVector_reserve(long j, MetadataVector metadataVector, long j2);

    public static final native void RTCAppClientListenerInterface_change_ownership(RTCAppClientListenerInterface rTCAppClientListenerInterface, long j, boolean z);

    public static final native void RTCAppClientListenerInterface_director_connect(RTCAppClientListenerInterface rTCAppClientListenerInterface, long j, boolean z, boolean z2);

    public static final native void RTCAppClientListenerInterface_onFirstFrameReceived(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i);

    public static final native void RTCAppClientListenerInterface_onFirstFrameRendered(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i);

    public static final native void RTCAppClientListenerInterface_onMediaConnectionStateChanged(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i);

    public static final native void RTCAppClientListenerInterface_onMediaStatusChanged(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, int i2, boolean z);

    public static final native void RTCAppClientListenerInterface_onSessionAvailable(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str);

    public static final native void RTCAppClientListenerInterface_onSessionError(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, String str2);

    public static final native void RTCAppClientListenerInterface_onSessionRemoved(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str);

    public static final native void RTCAppClientListenerInterface_onSessionStateChanged(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i);

    public static final native void RTCAppClientListenerInterface_onVideoEffectChanged(long j, RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, int i2);

    public static final native int RTCAppClientManagerInterface_acceptSession(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str);

    public static final native int RTCAppClientManagerInterface_disconnectSession(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, int i);

    public static final native int RTCAppClientManagerInterface_registerRTCAppClientListener(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, long j2, RTCAppInfo rTCAppInfo, long j3, RTCAppClientListenerInterface rTCAppClientListenerInterface);

    public static final native int RTCAppClientManagerInterface_setLocalAudioState(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, boolean z);

    public static final native int RTCAppClientManagerInterface_setLocalVideoState(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, boolean z);

    public static final native int RTCAppClientManagerInterface_setRemoteAudioState(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, boolean z);

    public static final native int RTCAppClientManagerInterface_setVideoEffect(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, int i, int i2);

    public static final native int RTCAppClientManagerInterface_signalReadyForSession(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str);

    public static final native int RTCAppClientManagerInterface_switchCamera(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, String str, String str2);

    public static final native int RTCAppClientManagerInterface_unregisterRTCAppClientListener(long j, RTCAppClientManagerInterface rTCAppClientManagerInterface, long j2, RTCAppInfo rTCAppInfo);

    public static final native String RTCAppInfo_appIdentifier_get(long j, RTCAppInfo rTCAppInfo);

    public static final native void RTCAppInfo_appIdentifier_set(long j, RTCAppInfo rTCAppInfo, String str);

    public static final native int RTCClientInterface_handleDirective(long j, RTCClientInterface rTCClientInterface, String str, String str2);

    public static final native int RTCClientInterface_registerEventListener(long j, RTCClientInterface rTCClientInterface, long j2, RTCAppInfo rTCAppInfo, long j3, RTCEventListenerInterface rTCEventListenerInterface);

    public static final native int RTCClientInterface_unregisterEventListener(long j, RTCClientInterface rTCClientInterface, long j2, RTCAppInfo rTCAppInfo);

    public static final native String RTCCustomMetricInterface_Counter_name_get(long j, RTCCustomMetricInterface.Counter counter);

    public static final native void RTCCustomMetricInterface_Counter_name_set(long j, RTCCustomMetricInterface.Counter counter, String str);

    public static final native int RTCCustomMetricInterface_Counter_value_get(long j, RTCCustomMetricInterface.Counter counter);

    public static final native void RTCCustomMetricInterface_Counter_value_set(long j, RTCCustomMetricInterface.Counter counter, int i);

    public static final native String RTCCustomMetricInterface_Metadata_name_get(long j, RTCCustomMetricInterface.Metadata metadata);

    public static final native void RTCCustomMetricInterface_Metadata_name_set(long j, RTCCustomMetricInterface.Metadata metadata, String str);

    public static final native String RTCCustomMetricInterface_Metadata_value_get(long j, RTCCustomMetricInterface.Metadata metadata);

    public static final native void RTCCustomMetricInterface_Metadata_value_set(long j, RTCCustomMetricInterface.Metadata metadata, String str);

    public static final native String RTCCustomMetricInterface_Timer_name_get(long j, RTCCustomMetricInterface.Timer timer);

    public static final native void RTCCustomMetricInterface_Timer_name_set(long j, RTCCustomMetricInterface.Timer timer, String str);

    public static final native double RTCCustomMetricInterface_Timer_value_get(long j, RTCCustomMetricInterface.Timer timer);

    public static final native void RTCCustomMetricInterface_Timer_value_set(long j, RTCCustomMetricInterface.Timer timer, double d);

    public static final native long RTCCustomMetricInterface_getCounters(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native long RTCCustomMetricInterface_getMetadata(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native int RTCCustomMetricInterface_getPriority(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native String RTCCustomMetricInterface_getProgramName(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native String RTCCustomMetricInterface_getSourceName(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native long RTCCustomMetricInterface_getTimers(long j, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native void RTCCustomMetricsPublisherAdapterInterface_change_ownership(RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface, long j, boolean z);

    public static final native void RTCCustomMetricsPublisherAdapterInterface_director_connect(RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface, long j, boolean z, boolean z2);

    public static final native boolean RTCCustomMetricsPublisherAdapterInterface_recordMetric(long j, RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface, long j2, RTCCustomMetricInterface rTCCustomMetricInterface);

    public static final native void RTCEventListenerInterface_change_ownership(RTCEventListenerInterface rTCEventListenerInterface, long j, boolean z);

    public static final native void RTCEventListenerInterface_director_connect(RTCEventListenerInterface rTCEventListenerInterface, long j, boolean z, boolean z2);

    public static final native void RTCEventListenerInterface_onRTCSessionContextUpdated(long j, RTCEventListenerInterface rTCEventListenerInterface, String str);

    public static final native void RTCEventListenerInterface_onSendEvent(long j, RTCEventListenerInterface rTCEventListenerInterface, String str, String str2, String str3, String str4);

    public static final native long RTCSCManagerInterface_createAndInitInstance(String str);

    public static final native long RTCSCManagerInterface_getInstance();

    public static final native long RTCSCManagerInterface_getRTCAppClientManager(long j, RTCSCManagerInterface rTCSCManagerInterface);

    public static final native long RTCSCManagerInterface_getRTCClient(long j, RTCSCManagerInterface rTCSCManagerInterface);

    public static final native int RTCSCManagerInterface_registerRTCCustomMetricsPublisherAdapter(long j, RTCSCManagerInterface rTCSCManagerInterface, long j2, RTCAppInfo rTCAppInfo, long j3, RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface);

    public static final native void RTCSCManagerInterface_releaseInstance();

    public static final native int RTCSCManagerInterface_unregisterRTCCustomMetricsPublisherAdapter(long j, RTCSCManagerInterface rTCSCManagerInterface, long j2, RTCAppInfo rTCAppInfo);

    public static void SwigDirector_RTCAppClientListenerInterface_onFirstFrameReceived(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i) {
        rTCAppClientListenerInterface.onFirstFrameReceived(str, RTCMediaType.swigToEnum(i));
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onFirstFrameRendered(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i) {
        rTCAppClientListenerInterface.onFirstFrameRendered(str, RTCSide.swigToEnum(i));
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onMediaConnectionStateChanged(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i) {
        rTCAppClientListenerInterface.onMediaConnectionStateChanged(str, RTCMediaConnectionState.swigToEnum(i));
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onMediaStatusChanged(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, int i2, boolean z) {
        rTCAppClientListenerInterface.onMediaStatusChanged(str, RTCAppClientListenerInterface.Side.swigToEnum(i), RTCAppClientListenerInterface.MediaType.swigToEnum(i2), z);
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onSessionAvailable(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str) {
        rTCAppClientListenerInterface.onSessionAvailable(str);
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onSessionError(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, String str2) {
        rTCAppClientListenerInterface.onSessionError(str, RTCSCErrorCode.swigToEnum(i), str2);
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onSessionRemoved(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str) {
        rTCAppClientListenerInterface.onSessionRemoved(str);
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onSessionStateChanged(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i) {
        rTCAppClientListenerInterface.onSessionStateChanged(str, RTCSessionState.swigToEnum(i));
    }

    public static void SwigDirector_RTCAppClientListenerInterface_onVideoEffectChanged(RTCAppClientListenerInterface rTCAppClientListenerInterface, String str, int i, int i2) {
        rTCAppClientListenerInterface.onVideoEffectChanged(str, RTCVideoEffect.swigToEnum(i), i2);
    }

    public static boolean SwigDirector_RTCCustomMetricsPublisherAdapterInterface_recordMetric(RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface, long j) {
        return rTCCustomMetricsPublisherAdapterInterface.recordMetric(j == 0 ? null : new RTCCustomMetricInterface(j, false));
    }

    public static void SwigDirector_RTCEventListenerInterface_onRTCSessionContextUpdated(RTCEventListenerInterface rTCEventListenerInterface, String str) {
        rTCEventListenerInterface.onRTCSessionContextUpdated(str);
    }

    public static void SwigDirector_RTCEventListenerInterface_onSendEvent(RTCEventListenerInterface rTCEventListenerInterface, String str, String str2, String str3, String str4) {
        rTCEventListenerInterface.onSendEvent(str, str2, str3, str4);
    }

    public static final native long TimerVector_capacity(long j, TimerVector timerVector);

    public static final native void TimerVector_clear(long j, TimerVector timerVector);

    public static final native void TimerVector_doAdd__SWIG_0(long j, TimerVector timerVector, long j2, RTCCustomMetricInterface.Timer timer);

    public static final native void TimerVector_doAdd__SWIG_1(long j, TimerVector timerVector, int i, long j2, RTCCustomMetricInterface.Timer timer);

    public static final native long TimerVector_doGet(long j, TimerVector timerVector, int i);

    public static final native long TimerVector_doRemove(long j, TimerVector timerVector, int i);

    public static final native void TimerVector_doRemoveRange(long j, TimerVector timerVector, int i, int i2);

    public static final native long TimerVector_doSet(long j, TimerVector timerVector, int i, long j2, RTCCustomMetricInterface.Timer timer);

    public static final native int TimerVector_doSize(long j, TimerVector timerVector);

    public static final native boolean TimerVector_isEmpty(long j, TimerVector timerVector);

    public static final native void TimerVector_reserve(long j, TimerVector timerVector, long j2);

    public static final native void delete_CounterVector(long j);

    public static final native void delete_MetadataVector(long j);

    public static final native void delete_RTCAppInfo(long j);

    public static final native void delete_RTCCustomMetricInterface_Counter(long j);

    public static final native void delete_RTCCustomMetricInterface_Metadata(long j);

    public static final native void delete_RTCCustomMetricInterface_Timer(long j);

    public static final native void delete_TimerVector(long j);

    public static final native long new_CounterVector__SWIG_0();

    public static final native long new_CounterVector__SWIG_1(long j, CounterVector counterVector);

    public static final native long new_CounterVector__SWIG_2(int i, long j, RTCCustomMetricInterface.Counter counter);

    public static final native long new_MetadataVector__SWIG_0();

    public static final native long new_MetadataVector__SWIG_1(long j, MetadataVector metadataVector);

    public static final native long new_MetadataVector__SWIG_2(int i, long j, RTCCustomMetricInterface.Metadata metadata);

    public static final native long new_RTCAppClientListenerInterface();

    public static final native long new_RTCAppInfo();

    public static final native long new_RTCCustomMetricInterface_Counter();

    public static final native long new_RTCCustomMetricInterface_Metadata();

    public static final native long new_RTCCustomMetricInterface_Timer();

    public static final native long new_RTCCustomMetricsPublisherAdapterInterface();

    public static final native long new_RTCEventListenerInterface();

    public static final native long new_TimerVector__SWIG_0();

    public static final native long new_TimerVector__SWIG_1(long j, TimerVector timerVector);

    public static final native long new_TimerVector__SWIG_2(int i, long j, RTCCustomMetricInterface.Timer timer);

    private static final native void swig_module_init();
}
