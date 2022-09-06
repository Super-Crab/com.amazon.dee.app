package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class Endpoint {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected Endpoint(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Endpoint endpoint) {
        if (endpoint == null) {
            return 0L;
        }
        return endpoint.swigCPtr;
    }

    public static Endpoint instance() throws Exception {
        return new Endpoint(pjsua2JNI.Endpoint_instance(), false);
    }

    public AudDevManager audDevManager() {
        return new AudDevManager(pjsua2JNI.Endpoint_audDevManager(this.swigCPtr, this), false);
    }

    public CodecInfoVector codecEnum() throws Exception {
        return new CodecInfoVector(pjsua2JNI.Endpoint_codecEnum(this.swigCPtr, this), false);
    }

    public SWIGTYPE_p_void codecGetParam(String str) throws Exception {
        long Endpoint_codecGetParam = pjsua2JNI.Endpoint_codecGetParam(this.swigCPtr, this, str);
        if (Endpoint_codecGetParam == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(Endpoint_codecGetParam, false);
    }

    public void codecSetParam(String str, SWIGTYPE_p_void sWIGTYPE_p_void) throws Exception {
        pjsua2JNI.Endpoint_codecSetParam(this.swigCPtr, this, str, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void codecSetPriority(String str, short s) throws Exception {
        pjsua2JNI.Endpoint_codecSetPriority(this.swigCPtr, this, str, s);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Endpoint(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public VidCodecParam getVideoCodecParam(String str) throws Exception {
        return new VidCodecParam(pjsua2JNI.Endpoint_getVideoCodecParam(this.swigCPtr, this, str), true);
    }

    public void hangupAllCalls() {
        pjsua2JNI.Endpoint_hangupAllCalls(this.swigCPtr, this);
    }

    public void libCreate() throws Exception {
        pjsua2JNI.Endpoint_libCreate(this.swigCPtr, this);
    }

    public void libDestroy(long j) throws Exception {
        Runtime.getRuntime().gc();
        libDestroy_(j);
    }

    public void libDestroy_(long j) throws Exception {
        pjsua2JNI.Endpoint_libDestroy___SWIG_0(this.swigCPtr, this, j);
    }

    public pjsua_state libGetState() {
        return pjsua_state.swigToEnum(pjsua2JNI.Endpoint_libGetState(this.swigCPtr, this));
    }

    public int libHandleEvents(long j) {
        return pjsua2JNI.Endpoint_libHandleEvents(this.swigCPtr, this, j);
    }

    public void libInit(EpConfig epConfig) throws Exception {
        pjsua2JNI.Endpoint_libInit(this.swigCPtr, this, EpConfig.getCPtr(epConfig), epConfig);
    }

    public boolean libIsThreadRegistered() {
        return pjsua2JNI.Endpoint_libIsThreadRegistered(this.swigCPtr, this);
    }

    public void libRegisterThread(String str) throws Exception {
        pjsua2JNI.Endpoint_libRegisterThread(this.swigCPtr, this, str);
    }

    public void libStart() throws Exception {
        pjsua2JNI.Endpoint_libStart(this.swigCPtr, this);
    }

    public void libStopWorkerThreads() {
        pjsua2JNI.Endpoint_libStopWorkerThreads(this.swigCPtr, this);
    }

    public Version libVersion() {
        return new Version(pjsua2JNI.Endpoint_libVersion(this.swigCPtr, this), true);
    }

    public long mediaActivePorts() {
        return pjsua2JNI.Endpoint_mediaActivePorts(this.swigCPtr, this);
    }

    public void mediaAdd(AudioMedia audioMedia) {
        pjsua2JNI.Endpoint_mediaAdd(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public AudioMediaVector mediaEnumPorts() throws Exception {
        return new AudioMediaVector(pjsua2JNI.Endpoint_mediaEnumPorts(this.swigCPtr, this), false);
    }

    public boolean mediaExists(AudioMedia audioMedia) {
        return pjsua2JNI.Endpoint_mediaExists(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long mediaMaxPorts() {
        return pjsua2JNI.Endpoint_mediaMaxPorts(this.swigCPtr, this);
    }

    public void mediaRemove(AudioMedia audioMedia) {
        pjsua2JNI.Endpoint_mediaRemove(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public void natCancelCheckStunServers(SWIGTYPE_p_void sWIGTYPE_p_void, boolean z) throws Exception {
        pjsua2JNI.Endpoint_natCancelCheckStunServers__SWIG_0(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), z);
    }

    public void natCheckStunServers(StringVector stringVector, boolean z, SWIGTYPE_p_void sWIGTYPE_p_void) throws Exception {
        pjsua2JNI.Endpoint_natCheckStunServers(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, z, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void natDetectType() throws Exception {
        pjsua2JNI.Endpoint_natDetectType(this.swigCPtr, this);
    }

    public pj_stun_nat_type natGetType() throws Exception {
        return pj_stun_nat_type.swigToEnum(pjsua2JNI.Endpoint_natGetType(this.swigCPtr, this));
    }

    public void natUpdateStunServers(StringVector stringVector, boolean z) throws Exception {
        pjsua2JNI.Endpoint_natUpdateStunServers(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, z);
    }

    public void onNatCheckStunServersComplete(OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam) {
        if (Endpoint.class == Endpoint.class) {
            pjsua2JNI.Endpoint_onNatCheckStunServersComplete(this.swigCPtr, this, OnNatCheckStunServersCompleteParam.getCPtr(onNatCheckStunServersCompleteParam), onNatCheckStunServersCompleteParam);
        } else {
            pjsua2JNI.Endpoint_onNatCheckStunServersCompleteSwigExplicitEndpoint(this.swigCPtr, this, OnNatCheckStunServersCompleteParam.getCPtr(onNatCheckStunServersCompleteParam), onNatCheckStunServersCompleteParam);
        }
    }

    public void onNatDetectionComplete(OnNatDetectionCompleteParam onNatDetectionCompleteParam) {
        if (Endpoint.class == Endpoint.class) {
            pjsua2JNI.Endpoint_onNatDetectionComplete(this.swigCPtr, this, OnNatDetectionCompleteParam.getCPtr(onNatDetectionCompleteParam), onNatDetectionCompleteParam);
        } else {
            pjsua2JNI.Endpoint_onNatDetectionCompleteSwigExplicitEndpoint(this.swigCPtr, this, OnNatDetectionCompleteParam.getCPtr(onNatDetectionCompleteParam), onNatDetectionCompleteParam);
        }
    }

    public void onSelectAccount(OnSelectAccountParam onSelectAccountParam) {
        if (Endpoint.class == Endpoint.class) {
            pjsua2JNI.Endpoint_onSelectAccount(this.swigCPtr, this, OnSelectAccountParam.getCPtr(onSelectAccountParam), onSelectAccountParam);
        } else {
            pjsua2JNI.Endpoint_onSelectAccountSwigExplicitEndpoint(this.swigCPtr, this, OnSelectAccountParam.getCPtr(onSelectAccountParam), onSelectAccountParam);
        }
    }

    public void onTimer(OnTimerParam onTimerParam) {
        if (Endpoint.class == Endpoint.class) {
            pjsua2JNI.Endpoint_onTimer(this.swigCPtr, this, OnTimerParam.getCPtr(onTimerParam), onTimerParam);
        } else {
            pjsua2JNI.Endpoint_onTimerSwigExplicitEndpoint(this.swigCPtr, this, OnTimerParam.getCPtr(onTimerParam), onTimerParam);
        }
    }

    public void onTransportState(OnTransportStateParam onTransportStateParam) {
        if (Endpoint.class == Endpoint.class) {
            pjsua2JNI.Endpoint_onTransportState(this.swigCPtr, this, OnTransportStateParam.getCPtr(onTransportStateParam), onTransportStateParam);
        } else {
            pjsua2JNI.Endpoint_onTransportStateSwigExplicitEndpoint(this.swigCPtr, this, OnTransportStateParam.getCPtr(onTransportStateParam), onTransportStateParam);
        }
    }

    public void resetVideoCodecParam(String str) throws Exception {
        pjsua2JNI.Endpoint_resetVideoCodecParam(this.swigCPtr, this, str);
    }

    public void setVideoCodecParam(String str, VidCodecParam vidCodecParam) throws Exception {
        pjsua2JNI.Endpoint_setVideoCodecParam(this.swigCPtr, this, str, VidCodecParam.getCPtr(vidCodecParam), vidCodecParam);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Endpoint_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Endpoint_change_ownership(this, this.swigCPtr, true);
    }

    public void transportClose(int i, boolean z) throws Exception {
        pjsua2JNI.Endpoint_transportClose__SWIG_0(this.swigCPtr, this, i, z);
    }

    public int transportCreate(pjsip_transport_type_e pjsip_transport_type_eVar, TransportConfig transportConfig) throws Exception {
        return pjsua2JNI.Endpoint_transportCreate(this.swigCPtr, this, pjsip_transport_type_eVar.swigValue(), TransportConfig.getCPtr(transportConfig), transportConfig);
    }

    public IntVector transportEnum() throws Exception {
        return new IntVector(pjsua2JNI.Endpoint_transportEnum(this.swigCPtr, this), true);
    }

    public TransportInfo transportGetInfo(int i) throws Exception {
        return new TransportInfo(pjsua2JNI.Endpoint_transportGetInfo(this.swigCPtr, this, i), true);
    }

    public void transportSetEnable(int i, boolean z) throws Exception {
        pjsua2JNI.Endpoint_transportSetEnable(this.swigCPtr, this, i, z);
    }

    public void utilAddPendingJob(PendingJob pendingJob) {
        pjsua2JNI.Endpoint_utilAddPendingJob(this.swigCPtr, this, PendingJob.getCPtr(pendingJob), pendingJob);
    }

    public void utilLogWrite(int i, String str, String str2) {
        pjsua2JNI.Endpoint_utilLogWrite__SWIG_0(this.swigCPtr, this, i, str, str2);
    }

    public IntVector utilSslGetAvailableCiphers() throws Exception {
        return new IntVector(pjsua2JNI.Endpoint_utilSslGetAvailableCiphers(this.swigCPtr, this), true);
    }

    public String utilStrError(int i) {
        return pjsua2JNI.Endpoint_utilStrError(this.swigCPtr, this, i);
    }

    public void utilTimerCancel(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Endpoint_utilTimerCancel(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void utilTimerSchedule(long j, SWIGTYPE_p_void sWIGTYPE_p_void) throws Exception {
        long Endpoint_utilTimerSchedule = pjsua2JNI.Endpoint_utilTimerSchedule(this.swigCPtr, this, j, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
        if (Endpoint_utilTimerSchedule == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(Endpoint_utilTimerSchedule, false);
    }

    public int utilVerifySipUri(String str) {
        return pjsua2JNI.Endpoint_utilVerifySipUri(this.swigCPtr, this, str);
    }

    public int utilVerifyUri(String str) {
        return pjsua2JNI.Endpoint_utilVerifyUri(this.swigCPtr, this, str);
    }

    public VidDevManager vidDevManager() {
        return new VidDevManager(pjsua2JNI.Endpoint_vidDevManager(this.swigCPtr, this), false);
    }

    public CodecInfoVector videoCodecEnum() throws Exception {
        return new CodecInfoVector(pjsua2JNI.Endpoint_videoCodecEnum(this.swigCPtr, this), false);
    }

    public void videoCodecSetPriority(String str, short s) throws Exception {
        pjsua2JNI.Endpoint_videoCodecSetPriority(this.swigCPtr, this, str, s);
    }

    public void libDestroy_() throws Exception {
        pjsua2JNI.Endpoint_libDestroy___SWIG_1(this.swigCPtr, this);
    }

    public void natCancelCheckStunServers(SWIGTYPE_p_void sWIGTYPE_p_void) throws Exception {
        pjsua2JNI.Endpoint_natCancelCheckStunServers__SWIG_1(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void transportClose(int i) throws Exception {
        pjsua2JNI.Endpoint_transportClose__SWIG_1(this.swigCPtr, this, i);
    }

    public void utilLogWrite(LogEntry logEntry) {
        pjsua2JNI.Endpoint_utilLogWrite__SWIG_1(this.swigCPtr, this, LogEntry.getCPtr(logEntry), logEntry);
    }

    public void libDestroy() throws Exception {
        Runtime.getRuntime().gc();
        libDestroy_();
    }

    public Endpoint() {
        this(pjsua2JNI.new_Endpoint(), true);
        pjsua2JNI.Endpoint_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
