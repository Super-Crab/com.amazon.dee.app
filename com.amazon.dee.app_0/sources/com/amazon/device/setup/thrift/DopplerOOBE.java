package com.amazon.device.setup.thrift;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.orig.EncodingUtils;
import org.apache.thrift.orig.ProcessFunction;
import org.apache.thrift.orig.TApplicationException;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TBaseProcessor;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.TProcessor;
import org.apache.thrift.orig.TServiceClient;
import org.apache.thrift.orig.TServiceClientFactory;
import org.apache.thrift.orig.async.AsyncMethodCallback;
import org.apache.thrift.orig.async.TAsyncClient;
import org.apache.thrift.orig.async.TAsyncClientFactory;
import org.apache.thrift.orig.async.TAsyncClientManager;
import org.apache.thrift.orig.async.TAsyncMethodCall;
import org.apache.thrift.orig.meta_data.EnumMetaData;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.meta_data.ListMetaData;
import org.apache.thrift.orig.meta_data.StructMetaData;
import org.apache.thrift.orig.protocol.TCompactProtocol;
import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TList;
import org.apache.thrift.orig.protocol.TMessage;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolFactory;
import org.apache.thrift.orig.protocol.TProtocolUtil;
import org.apache.thrift.orig.protocol.TStruct;
import org.apache.thrift.orig.protocol.TTupleProtocol;
import org.apache.thrift.orig.scheme.IScheme;
import org.apache.thrift.orig.scheme.SchemeFactory;
import org.apache.thrift.orig.scheme.StandardScheme;
import org.apache.thrift.orig.scheme.TupleScheme;
import org.apache.thrift.orig.transport.TIOStreamTransport;
import org.apache.thrift.orig.transport.TMemoryInputTransport;
import org.apache.thrift.orig.transport.TNonblockingTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes12.dex */
public class DopplerOOBE {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.DopplerOOBE$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_result$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setLocaleAndEndpoints_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setLocaleAndEndpoints_result$_Fields = new int[setLocaleAndEndpoints_result._Fields.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_args$_Fields;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_result$_Fields;

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setLocaleAndEndpoints_result$_Fields[setLocaleAndEndpoints_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setLocaleAndEndpoints_args$_Fields = new int[setLocaleAndEndpoints_args._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setLocaleAndEndpoints_args$_Fields[setLocaleAndEndpoints_args._Fields.LO.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_result$_Fields = new int[evaluateCaptivePortal_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_result$_Fields[evaluateCaptivePortal_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_args$_Fields = new int[evaluateCaptivePortal_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_result$_Fields = new int[getAlexaConnectionState_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_result$_Fields[getAlexaConnectionState_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_args$_Fields = new int[getAlexaConnectionState_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_result$_Fields = new int[getRegistrationState_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_result$_Fields[getRegistrationState_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_args$_Fields = new int[getRegistrationState_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_result$_Fields = new int[setupComplete_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_result$_Fields[setupComplete_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_args$_Fields = new int[setupComplete_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_result$_Fields = new int[getLinkCode_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_result$_Fields[getLinkCode_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_args$_Fields = new int[getLinkCode_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_result$_Fields = new int[forgetAP_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_result$_Fields[forgetAP_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_args$_Fields = new int[forgetAP_args._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$forgetAP_args$_Fields[forgetAP_args._Fields.SSID.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_result$_Fields = new int[disconnectFromAP_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_result$_Fields[disconnectFromAP_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_args$_Fields = new int[disconnectFromAP_args._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$disconnectFromAP_args$_Fields[disconnectFromAP_args._Fields.SSID.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_result$_Fields = new int[connectToAPEx_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_result$_Fields[connectToAPEx_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_args$_Fields = new int[connectToAPEx_args._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_args$_Fields[connectToAPEx_args._Fields.AP.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAPEx_args$_Fields[connectToAPEx_args._Fields.EXTENDED.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_result$_Fields = new int[connectToAP_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_result$_Fields[connectToAP_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_args$_Fields = new int[connectToAP_args._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$connectToAP_args$_Fields[connectToAP_args._Fields.AP.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_result$_Fields = new int[getOTADetails_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_result$_Fields[getOTADetails_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_args$_Fields = new int[getOTADetails_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_result$_Fields = new int[getKnownList_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_result$_Fields[getKnownList_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_args$_Fields = new int[getKnownList_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_result$_Fields = new int[getScanList_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_result$_Fields[getScanList_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_args$_Fields = new int[getScanList_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_result$_Fields = new int[getDeviceDetails_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_result$_Fields[getDeviceDetails_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_args$_Fields = new int[getDeviceDetails_args._Fields.values().length];
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_result$_Fields = new int[ping_result._Fields.values().length];
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_result$_Fields[ping_result._Fields.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            $SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_args$_Fields = new int[ping_args._Fields.values().length];
        }
    }

    /* loaded from: classes12.dex */
    public static class AsyncClient extends TAsyncClient implements AsyncIface {

        /* loaded from: classes12.dex */
        public static class Factory implements TAsyncClientFactory<AsyncClient> {
            private TAsyncClientManager clientManager;
            private TProtocolFactory protocolFactory;

            public Factory(TAsyncClientManager tAsyncClientManager, TProtocolFactory tProtocolFactory) {
                this.clientManager = tAsyncClientManager;
                this.protocolFactory = tProtocolFactory;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.async.TAsyncClientFactory
            /* renamed from: getAsyncClient */
            public AsyncClient mo3848getAsyncClient(TNonblockingTransport tNonblockingTransport) {
                return new AsyncClient(this.protocolFactory, this.clientManager, tNonblockingTransport);
            }
        }

        /* loaded from: classes12.dex */
        public static class connectToAPEx_call extends TAsyncMethodCall {
            private APConnectInfo ap;
            private APConnectExtendedInfo extended;

            public connectToAPEx_call(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo, AsyncMethodCallback<connectToAPEx_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
                this.ap = aPConnectInfo;
                this.extended = aPConnectExtendedInfo;
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_connectToAPEx();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("connectToAPEx", (byte) 1, 0));
                connectToAPEx_args connecttoapex_args = new connectToAPEx_args();
                connecttoapex_args.setAp(this.ap);
                connecttoapex_args.setExtended(this.extended);
                connecttoapex_args.write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class connectToAP_call extends TAsyncMethodCall {
            private APConnectInfo ap;

            public connectToAP_call(APConnectInfo aPConnectInfo, AsyncMethodCallback<connectToAP_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
                this.ap = aPConnectInfo;
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_connectToAP();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("connectToAP", (byte) 1, 0));
                connectToAP_args connecttoap_args = new connectToAP_args();
                connecttoap_args.setAp(this.ap);
                connecttoap_args.write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class disconnectFromAP_call extends TAsyncMethodCall {
            private String ssid;

            public disconnectFromAP_call(String str, AsyncMethodCallback<disconnectFromAP_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
                this.ssid = str;
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_disconnectFromAP();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("disconnectFromAP", (byte) 1, 0));
                disconnectFromAP_args disconnectfromap_args = new disconnectFromAP_args();
                disconnectfromap_args.setSsid(this.ssid);
                disconnectfromap_args.write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal_call extends TAsyncMethodCall {
            public evaluateCaptivePortal_call(AsyncMethodCallback<evaluateCaptivePortal_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_evaluateCaptivePortal();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("evaluateCaptivePortal", (byte) 1, 0));
                new evaluateCaptivePortal_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class forgetAP_call extends TAsyncMethodCall {
            private String ssid;

            public forgetAP_call(String str, AsyncMethodCallback<forgetAP_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
                this.ssid = str;
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_forgetAP();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("forgetAP", (byte) 1, 0));
                forgetAP_args forgetap_args = new forgetAP_args();
                forgetap_args.setSsid(this.ssid);
                forgetap_args.write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState_call extends TAsyncMethodCall {
            public getAlexaConnectionState_call(AsyncMethodCallback<getAlexaConnectionState_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public AlexaConnectionState getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getAlexaConnectionState();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getAlexaConnectionState", (byte) 1, 0));
                new getAlexaConnectionState_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getDeviceDetails_call extends TAsyncMethodCall {
            public getDeviceDetails_call(AsyncMethodCallback<getDeviceDetails_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public DeviceDetails getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getDeviceDetails();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getDeviceDetails", (byte) 1, 0));
                new getDeviceDetails_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getKnownList_call extends TAsyncMethodCall {
            public getKnownList_call(AsyncMethodCallback<getKnownList_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public List<APDetail> getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getKnownList();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getKnownList", (byte) 1, 0));
                new getKnownList_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getLinkCode_call extends TAsyncMethodCall {
            public getLinkCode_call(AsyncMethodCallback<getLinkCode_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public DeviceCredentials getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getLinkCode();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getLinkCode", (byte) 1, 0));
                new getLinkCode_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getOTADetails_call extends TAsyncMethodCall {
            public getOTADetails_call(AsyncMethodCallback<getOTADetails_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public OTADetails getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getOTADetails();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getOTADetails", (byte) 1, 0));
                new getOTADetails_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getRegistrationState_call extends TAsyncMethodCall {
            public getRegistrationState_call(AsyncMethodCallback<getRegistrationState_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public RegistrationState getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getRegistrationState();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getRegistrationState", (byte) 1, 0));
                new getRegistrationState_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class getScanList_call extends TAsyncMethodCall {
            public getScanList_call(AsyncMethodCallback<getScanList_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public List<APDetail> getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_getScanList();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("getScanList", (byte) 1, 0));
                new getScanList_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class ping_call extends TAsyncMethodCall {
            public ping_call(AsyncMethodCallback<ping_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public boolean getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_ping();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("ping", (byte) 1, 0));
                new ping_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints_call extends TAsyncMethodCall {
            private LocaleAndEndpointInfo lo;

            public setLocaleAndEndpoints_call(LocaleAndEndpointInfo localeAndEndpointInfo, AsyncMethodCallback<setLocaleAndEndpoints_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
                this.lo = localeAndEndpointInfo;
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_setLocaleAndEndpoints();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("setLocaleAndEndpoints", (byte) 1, 0));
                setLocaleAndEndpoints_args setlocaleandendpoints_args = new setLocaleAndEndpoints_args();
                setlocaleandendpoints_args.setLo(this.lo);
                setlocaleandendpoints_args.write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        /* loaded from: classes12.dex */
        public static class setupComplete_call extends TAsyncMethodCall {
            public setupComplete_call(AsyncMethodCallback<setupComplete_call> asyncMethodCallback, TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport) throws TException {
                super(tAsyncClient, tProtocolFactory, tNonblockingTransport, asyncMethodCallback, false);
            }

            public ReturnError getResult() throws TException {
                if (getState() == TAsyncMethodCall.State.RESPONSE_READ) {
                    return new Client(this.client.getProtocolFactory().getProtocol(new TMemoryInputTransport(getFrameBuffer().array()))).recv_setupComplete();
                }
                throw new IllegalStateException("Method call not finished!");
            }

            @Override // org.apache.thrift.orig.async.TAsyncMethodCall
            public void write_args(TProtocol tProtocol) throws TException {
                tProtocol.writeMessageBegin(new TMessage("setupComplete", (byte) 1, 0));
                new setupComplete_args().write(tProtocol);
                tProtocol.writeMessageEnd();
            }
        }

        public AsyncClient(TProtocolFactory tProtocolFactory, TAsyncClientManager tAsyncClientManager, TNonblockingTransport tNonblockingTransport) {
            super(tProtocolFactory, tAsyncClientManager, tNonblockingTransport);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void connectToAP(APConnectInfo aPConnectInfo, AsyncMethodCallback<connectToAP_call> asyncMethodCallback) throws TException {
            checkReady();
            connectToAP_call connecttoap_call = new connectToAP_call(aPConnectInfo, asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = connecttoap_call;
            this.___manager.call(connecttoap_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void connectToAPEx(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo, AsyncMethodCallback<connectToAPEx_call> asyncMethodCallback) throws TException {
            checkReady();
            connectToAPEx_call connecttoapex_call = new connectToAPEx_call(aPConnectInfo, aPConnectExtendedInfo, asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = connecttoapex_call;
            this.___manager.call(connecttoapex_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void disconnectFromAP(String str, AsyncMethodCallback<disconnectFromAP_call> asyncMethodCallback) throws TException {
            checkReady();
            disconnectFromAP_call disconnectfromap_call = new disconnectFromAP_call(str, asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = disconnectfromap_call;
            this.___manager.call(disconnectfromap_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void evaluateCaptivePortal(AsyncMethodCallback<evaluateCaptivePortal_call> asyncMethodCallback) throws TException {
            checkReady();
            evaluateCaptivePortal_call evaluatecaptiveportal_call = new evaluateCaptivePortal_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = evaluatecaptiveportal_call;
            this.___manager.call(evaluatecaptiveportal_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void forgetAP(String str, AsyncMethodCallback<forgetAP_call> asyncMethodCallback) throws TException {
            checkReady();
            forgetAP_call forgetap_call = new forgetAP_call(str, asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = forgetap_call;
            this.___manager.call(forgetap_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getAlexaConnectionState(AsyncMethodCallback<getAlexaConnectionState_call> asyncMethodCallback) throws TException {
            checkReady();
            getAlexaConnectionState_call getalexaconnectionstate_call = new getAlexaConnectionState_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getalexaconnectionstate_call;
            this.___manager.call(getalexaconnectionstate_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getDeviceDetails(AsyncMethodCallback<getDeviceDetails_call> asyncMethodCallback) throws TException {
            checkReady();
            getDeviceDetails_call getdevicedetails_call = new getDeviceDetails_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getdevicedetails_call;
            this.___manager.call(getdevicedetails_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getKnownList(AsyncMethodCallback<getKnownList_call> asyncMethodCallback) throws TException {
            checkReady();
            getKnownList_call getknownlist_call = new getKnownList_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getknownlist_call;
            this.___manager.call(getknownlist_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getLinkCode(AsyncMethodCallback<getLinkCode_call> asyncMethodCallback) throws TException {
            checkReady();
            getLinkCode_call getlinkcode_call = new getLinkCode_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getlinkcode_call;
            this.___manager.call(getlinkcode_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getOTADetails(AsyncMethodCallback<getOTADetails_call> asyncMethodCallback) throws TException {
            checkReady();
            getOTADetails_call getotadetails_call = new getOTADetails_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getotadetails_call;
            this.___manager.call(getotadetails_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getRegistrationState(AsyncMethodCallback<getRegistrationState_call> asyncMethodCallback) throws TException {
            checkReady();
            getRegistrationState_call getregistrationstate_call = new getRegistrationState_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getregistrationstate_call;
            this.___manager.call(getregistrationstate_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void getScanList(AsyncMethodCallback<getScanList_call> asyncMethodCallback) throws TException {
            checkReady();
            getScanList_call getscanlist_call = new getScanList_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = getscanlist_call;
            this.___manager.call(getscanlist_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void ping(AsyncMethodCallback<ping_call> asyncMethodCallback) throws TException {
            checkReady();
            ping_call ping_callVar = new ping_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = ping_callVar;
            this.___manager.call(ping_callVar);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void setLocaleAndEndpoints(LocaleAndEndpointInfo localeAndEndpointInfo, AsyncMethodCallback<setLocaleAndEndpoints_call> asyncMethodCallback) throws TException {
            checkReady();
            setLocaleAndEndpoints_call setlocaleandendpoints_call = new setLocaleAndEndpoints_call(localeAndEndpointInfo, asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = setlocaleandendpoints_call;
            this.___manager.call(setlocaleandendpoints_call);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.AsyncIface
        public void setupComplete(AsyncMethodCallback<setupComplete_call> asyncMethodCallback) throws TException {
            checkReady();
            setupComplete_call setupcomplete_call = new setupComplete_call(asyncMethodCallback, this, this.___protocolFactory, this.___transport);
            this.___currentMethod = setupcomplete_call;
            this.___manager.call(setupcomplete_call);
        }
    }

    /* loaded from: classes12.dex */
    public interface AsyncIface {
        void connectToAP(APConnectInfo aPConnectInfo, AsyncMethodCallback<AsyncClient.connectToAP_call> asyncMethodCallback) throws TException;

        void connectToAPEx(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo, AsyncMethodCallback<AsyncClient.connectToAPEx_call> asyncMethodCallback) throws TException;

        void disconnectFromAP(String str, AsyncMethodCallback<AsyncClient.disconnectFromAP_call> asyncMethodCallback) throws TException;

        void evaluateCaptivePortal(AsyncMethodCallback<AsyncClient.evaluateCaptivePortal_call> asyncMethodCallback) throws TException;

        void forgetAP(String str, AsyncMethodCallback<AsyncClient.forgetAP_call> asyncMethodCallback) throws TException;

        void getAlexaConnectionState(AsyncMethodCallback<AsyncClient.getAlexaConnectionState_call> asyncMethodCallback) throws TException;

        void getDeviceDetails(AsyncMethodCallback<AsyncClient.getDeviceDetails_call> asyncMethodCallback) throws TException;

        void getKnownList(AsyncMethodCallback<AsyncClient.getKnownList_call> asyncMethodCallback) throws TException;

        void getLinkCode(AsyncMethodCallback<AsyncClient.getLinkCode_call> asyncMethodCallback) throws TException;

        void getOTADetails(AsyncMethodCallback<AsyncClient.getOTADetails_call> asyncMethodCallback) throws TException;

        void getRegistrationState(AsyncMethodCallback<AsyncClient.getRegistrationState_call> asyncMethodCallback) throws TException;

        void getScanList(AsyncMethodCallback<AsyncClient.getScanList_call> asyncMethodCallback) throws TException;

        void ping(AsyncMethodCallback<AsyncClient.ping_call> asyncMethodCallback) throws TException;

        void setLocaleAndEndpoints(LocaleAndEndpointInfo localeAndEndpointInfo, AsyncMethodCallback<AsyncClient.setLocaleAndEndpoints_call> asyncMethodCallback) throws TException;

        void setupComplete(AsyncMethodCallback<AsyncClient.setupComplete_call> asyncMethodCallback) throws TException;
    }

    /* loaded from: classes12.dex */
    public static class Client extends TServiceClient implements Iface {

        /* loaded from: classes12.dex */
        public static class Factory implements TServiceClientFactory<Client> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.TServiceClientFactory
            /* renamed from: getClient */
            public Client mo3849getClient(TProtocol tProtocol) {
                return new Client(tProtocol);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.TServiceClientFactory
            /* renamed from: getClient */
            public Client mo3850getClient(TProtocol tProtocol, TProtocol tProtocol2) {
                return new Client(tProtocol, tProtocol2);
            }
        }

        public Client(TProtocol tProtocol) {
            super(tProtocol, tProtocol);
        }

        public Client(TProtocol tProtocol, TProtocol tProtocol2) {
            super(tProtocol, tProtocol2);
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError connectToAP(APConnectInfo aPConnectInfo) throws TException {
            send_connectToAP(aPConnectInfo);
            return recv_connectToAP();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError connectToAPEx(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            send_connectToAPEx(aPConnectInfo, aPConnectExtendedInfo);
            return recv_connectToAPEx();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError disconnectFromAP(String str) throws TException {
            send_disconnectFromAP(str);
            return recv_disconnectFromAP();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError evaluateCaptivePortal() throws TException {
            send_evaluateCaptivePortal();
            return recv_evaluateCaptivePortal();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError forgetAP(String str) throws TException {
            send_forgetAP(str);
            return recv_forgetAP();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public AlexaConnectionState getAlexaConnectionState() throws TException {
            send_getAlexaConnectionState();
            return recv_getAlexaConnectionState();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public DeviceDetails getDeviceDetails() throws TException {
            send_getDeviceDetails();
            return recv_getDeviceDetails();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public List<APDetail> getKnownList() throws TException {
            send_getKnownList();
            return recv_getKnownList();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public DeviceCredentials getLinkCode() throws TException {
            send_getLinkCode();
            return recv_getLinkCode();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public OTADetails getOTADetails() throws TException {
            send_getOTADetails();
            return recv_getOTADetails();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public RegistrationState getRegistrationState() throws TException {
            send_getRegistrationState();
            return recv_getRegistrationState();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public List<APDetail> getScanList() throws TException {
            send_getScanList();
            return recv_getScanList();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public boolean ping() throws TException {
            send_ping();
            return recv_ping();
        }

        public ReturnError recv_connectToAP() throws TException {
            connectToAP_result connecttoap_result = new connectToAP_result();
            receiveBase(connecttoap_result, "connectToAP");
            if (connecttoap_result.isSetSuccess()) {
                return connecttoap_result.success;
            }
            throw new TApplicationException(5, "connectToAP failed: unknown result");
        }

        public ReturnError recv_connectToAPEx() throws TException {
            connectToAPEx_result connecttoapex_result = new connectToAPEx_result();
            receiveBase(connecttoapex_result, "connectToAPEx");
            if (connecttoapex_result.isSetSuccess()) {
                return connecttoapex_result.success;
            }
            throw new TApplicationException(5, "connectToAPEx failed: unknown result");
        }

        public ReturnError recv_disconnectFromAP() throws TException {
            disconnectFromAP_result disconnectfromap_result = new disconnectFromAP_result();
            receiveBase(disconnectfromap_result, "disconnectFromAP");
            if (disconnectfromap_result.isSetSuccess()) {
                return disconnectfromap_result.success;
            }
            throw new TApplicationException(5, "disconnectFromAP failed: unknown result");
        }

        public ReturnError recv_evaluateCaptivePortal() throws TException {
            evaluateCaptivePortal_result evaluatecaptiveportal_result = new evaluateCaptivePortal_result();
            receiveBase(evaluatecaptiveportal_result, "evaluateCaptivePortal");
            if (evaluatecaptiveportal_result.isSetSuccess()) {
                return evaluatecaptiveportal_result.success;
            }
            throw new TApplicationException(5, "evaluateCaptivePortal failed: unknown result");
        }

        public ReturnError recv_forgetAP() throws TException {
            forgetAP_result forgetap_result = new forgetAP_result();
            receiveBase(forgetap_result, "forgetAP");
            if (forgetap_result.isSetSuccess()) {
                return forgetap_result.success;
            }
            throw new TApplicationException(5, "forgetAP failed: unknown result");
        }

        public AlexaConnectionState recv_getAlexaConnectionState() throws TException {
            getAlexaConnectionState_result getalexaconnectionstate_result = new getAlexaConnectionState_result();
            receiveBase(getalexaconnectionstate_result, "getAlexaConnectionState");
            if (getalexaconnectionstate_result.isSetSuccess()) {
                return getalexaconnectionstate_result.success;
            }
            throw new TApplicationException(5, "getAlexaConnectionState failed: unknown result");
        }

        public DeviceDetails recv_getDeviceDetails() throws TException {
            getDeviceDetails_result getdevicedetails_result = new getDeviceDetails_result();
            receiveBase(getdevicedetails_result, "getDeviceDetails");
            if (getdevicedetails_result.isSetSuccess()) {
                return getdevicedetails_result.success;
            }
            throw new TApplicationException(5, "getDeviceDetails failed: unknown result");
        }

        public List<APDetail> recv_getKnownList() throws TException {
            getKnownList_result getknownlist_result = new getKnownList_result();
            receiveBase(getknownlist_result, "getKnownList");
            if (getknownlist_result.isSetSuccess()) {
                return getknownlist_result.success;
            }
            throw new TApplicationException(5, "getKnownList failed: unknown result");
        }

        public DeviceCredentials recv_getLinkCode() throws TException {
            getLinkCode_result getlinkcode_result = new getLinkCode_result();
            receiveBase(getlinkcode_result, "getLinkCode");
            if (getlinkcode_result.isSetSuccess()) {
                return getlinkcode_result.success;
            }
            throw new TApplicationException(5, "getLinkCode failed: unknown result");
        }

        public OTADetails recv_getOTADetails() throws TException {
            getOTADetails_result getotadetails_result = new getOTADetails_result();
            receiveBase(getotadetails_result, "getOTADetails");
            if (getotadetails_result.isSetSuccess()) {
                return getotadetails_result.success;
            }
            throw new TApplicationException(5, "getOTADetails failed: unknown result");
        }

        public RegistrationState recv_getRegistrationState() throws TException {
            getRegistrationState_result getregistrationstate_result = new getRegistrationState_result();
            receiveBase(getregistrationstate_result, "getRegistrationState");
            if (getregistrationstate_result.isSetSuccess()) {
                return getregistrationstate_result.success;
            }
            throw new TApplicationException(5, "getRegistrationState failed: unknown result");
        }

        public List<APDetail> recv_getScanList() throws TException {
            getScanList_result getscanlist_result = new getScanList_result();
            receiveBase(getscanlist_result, "getScanList");
            if (getscanlist_result.isSetSuccess()) {
                return getscanlist_result.success;
            }
            throw new TApplicationException(5, "getScanList failed: unknown result");
        }

        public boolean recv_ping() throws TException {
            ping_result ping_resultVar = new ping_result();
            receiveBase(ping_resultVar, "ping");
            if (ping_resultVar.isSetSuccess()) {
                return ping_resultVar.success;
            }
            throw new TApplicationException(5, "ping failed: unknown result");
        }

        public ReturnError recv_setLocaleAndEndpoints() throws TException {
            setLocaleAndEndpoints_result setlocaleandendpoints_result = new setLocaleAndEndpoints_result();
            receiveBase(setlocaleandendpoints_result, "setLocaleAndEndpoints");
            if (setlocaleandendpoints_result.isSetSuccess()) {
                return setlocaleandendpoints_result.success;
            }
            throw new TApplicationException(5, "setLocaleAndEndpoints failed: unknown result");
        }

        public ReturnError recv_setupComplete() throws TException {
            setupComplete_result setupcomplete_result = new setupComplete_result();
            receiveBase(setupcomplete_result, "setupComplete");
            if (setupcomplete_result.isSetSuccess()) {
                return setupcomplete_result.success;
            }
            throw new TApplicationException(5, "setupComplete failed: unknown result");
        }

        public void send_connectToAP(APConnectInfo aPConnectInfo) throws TException {
            connectToAP_args connecttoap_args = new connectToAP_args();
            connecttoap_args.setAp(aPConnectInfo);
            sendBase("connectToAP", connecttoap_args);
        }

        public void send_connectToAPEx(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            connectToAPEx_args connecttoapex_args = new connectToAPEx_args();
            connecttoapex_args.setAp(aPConnectInfo);
            connecttoapex_args.setExtended(aPConnectExtendedInfo);
            sendBase("connectToAPEx", connecttoapex_args);
        }

        public void send_disconnectFromAP(String str) throws TException {
            disconnectFromAP_args disconnectfromap_args = new disconnectFromAP_args();
            disconnectfromap_args.setSsid(str);
            sendBase("disconnectFromAP", disconnectfromap_args);
        }

        public void send_evaluateCaptivePortal() throws TException {
            sendBase("evaluateCaptivePortal", new evaluateCaptivePortal_args());
        }

        public void send_forgetAP(String str) throws TException {
            forgetAP_args forgetap_args = new forgetAP_args();
            forgetap_args.setSsid(str);
            sendBase("forgetAP", forgetap_args);
        }

        public void send_getAlexaConnectionState() throws TException {
            sendBase("getAlexaConnectionState", new getAlexaConnectionState_args());
        }

        public void send_getDeviceDetails() throws TException {
            sendBase("getDeviceDetails", new getDeviceDetails_args());
        }

        public void send_getKnownList() throws TException {
            sendBase("getKnownList", new getKnownList_args());
        }

        public void send_getLinkCode() throws TException {
            sendBase("getLinkCode", new getLinkCode_args());
        }

        public void send_getOTADetails() throws TException {
            sendBase("getOTADetails", new getOTADetails_args());
        }

        public void send_getRegistrationState() throws TException {
            sendBase("getRegistrationState", new getRegistrationState_args());
        }

        public void send_getScanList() throws TException {
            sendBase("getScanList", new getScanList_args());
        }

        public void send_ping() throws TException {
            sendBase("ping", new ping_args());
        }

        public void send_setLocaleAndEndpoints(LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            setLocaleAndEndpoints_args setlocaleandendpoints_args = new setLocaleAndEndpoints_args();
            setlocaleandendpoints_args.setLo(localeAndEndpointInfo);
            sendBase("setLocaleAndEndpoints", setlocaleandendpoints_args);
        }

        public void send_setupComplete() throws TException {
            sendBase("setupComplete", new setupComplete_args());
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError setLocaleAndEndpoints(LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            send_setLocaleAndEndpoints(localeAndEndpointInfo);
            return recv_setLocaleAndEndpoints();
        }

        @Override // com.amazon.device.setup.thrift.DopplerOOBE.Iface
        public ReturnError setupComplete() throws TException {
            send_setupComplete();
            return recv_setupComplete();
        }
    }

    /* loaded from: classes12.dex */
    public interface Iface {
        ReturnError connectToAP(APConnectInfo aPConnectInfo) throws TException;

        ReturnError connectToAPEx(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws TException;

        ReturnError disconnectFromAP(String str) throws TException;

        ReturnError evaluateCaptivePortal() throws TException;

        ReturnError forgetAP(String str) throws TException;

        AlexaConnectionState getAlexaConnectionState() throws TException;

        DeviceDetails getDeviceDetails() throws TException;

        List<APDetail> getKnownList() throws TException;

        DeviceCredentials getLinkCode() throws TException;

        OTADetails getOTADetails() throws TException;

        RegistrationState getRegistrationState() throws TException;

        List<APDetail> getScanList() throws TException;

        boolean ping() throws TException;

        ReturnError setLocaleAndEndpoints(LocaleAndEndpointInfo localeAndEndpointInfo) throws TException;

        ReturnError setupComplete() throws TException;
    }

    /* loaded from: classes12.dex */
    public static class Processor<I extends Iface> extends TBaseProcessor<I> implements TProcessor {
        private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());

        /* loaded from: classes12.dex */
        public static class connectToAP<I extends Iface> extends ProcessFunction<I, connectToAP_args> {
            public connectToAP() {
                super("connectToAP");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public connectToAP_args mo3865getEmptyArgsInstance() {
                return new connectToAP_args();
            }

            public connectToAP_result getResult(I i, connectToAP_args connecttoap_args) throws TException {
                connectToAP_result connecttoap_result = new connectToAP_result();
                connecttoap_result.success = i.connectToAP(connecttoap_args.ap);
                return connecttoap_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, connectToAP_args connecttoap_args) throws TException {
                return getResult((connectToAP<I>) ((Iface) obj), connecttoap_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class connectToAPEx<I extends Iface> extends ProcessFunction<I, connectToAPEx_args> {
            public connectToAPEx() {
                super("connectToAPEx");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public connectToAPEx_args mo3865getEmptyArgsInstance() {
                return new connectToAPEx_args();
            }

            public connectToAPEx_result getResult(I i, connectToAPEx_args connecttoapex_args) throws TException {
                connectToAPEx_result connecttoapex_result = new connectToAPEx_result();
                connecttoapex_result.success = i.connectToAPEx(connecttoapex_args.ap, connecttoapex_args.extended);
                return connecttoapex_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, connectToAPEx_args connecttoapex_args) throws TException {
                return getResult((connectToAPEx<I>) ((Iface) obj), connecttoapex_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class disconnectFromAP<I extends Iface> extends ProcessFunction<I, disconnectFromAP_args> {
            public disconnectFromAP() {
                super("disconnectFromAP");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public disconnectFromAP_args mo3865getEmptyArgsInstance() {
                return new disconnectFromAP_args();
            }

            public disconnectFromAP_result getResult(I i, disconnectFromAP_args disconnectfromap_args) throws TException {
                disconnectFromAP_result disconnectfromap_result = new disconnectFromAP_result();
                disconnectfromap_result.success = i.disconnectFromAP(disconnectfromap_args.ssid);
                return disconnectfromap_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, disconnectFromAP_args disconnectfromap_args) throws TException {
                return getResult((disconnectFromAP<I>) ((Iface) obj), disconnectfromap_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal<I extends Iface> extends ProcessFunction<I, evaluateCaptivePortal_args> {
            public evaluateCaptivePortal() {
                super("evaluateCaptivePortal");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public evaluateCaptivePortal_args mo3865getEmptyArgsInstance() {
                return new evaluateCaptivePortal_args();
            }

            public evaluateCaptivePortal_result getResult(I i, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                evaluateCaptivePortal_result evaluatecaptiveportal_result = new evaluateCaptivePortal_result();
                evaluatecaptiveportal_result.success = i.evaluateCaptivePortal();
                return evaluatecaptiveportal_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                return getResult((evaluateCaptivePortal<I>) ((Iface) obj), evaluatecaptiveportal_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class forgetAP<I extends Iface> extends ProcessFunction<I, forgetAP_args> {
            public forgetAP() {
                super("forgetAP");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public forgetAP_args mo3865getEmptyArgsInstance() {
                return new forgetAP_args();
            }

            public forgetAP_result getResult(I i, forgetAP_args forgetap_args) throws TException {
                forgetAP_result forgetap_result = new forgetAP_result();
                forgetap_result.success = i.forgetAP(forgetap_args.ssid);
                return forgetap_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, forgetAP_args forgetap_args) throws TException {
                return getResult((forgetAP<I>) ((Iface) obj), forgetap_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState<I extends Iface> extends ProcessFunction<I, getAlexaConnectionState_args> {
            public getAlexaConnectionState() {
                super("getAlexaConnectionState");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getAlexaConnectionState_args mo3865getEmptyArgsInstance() {
                return new getAlexaConnectionState_args();
            }

            public getAlexaConnectionState_result getResult(I i, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                getAlexaConnectionState_result getalexaconnectionstate_result = new getAlexaConnectionState_result();
                getalexaconnectionstate_result.success = i.getAlexaConnectionState();
                return getalexaconnectionstate_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                return getResult((getAlexaConnectionState<I>) ((Iface) obj), getalexaconnectionstate_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getDeviceDetails<I extends Iface> extends ProcessFunction<I, getDeviceDetails_args> {
            public getDeviceDetails() {
                super("getDeviceDetails");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getDeviceDetails_args mo3865getEmptyArgsInstance() {
                return new getDeviceDetails_args();
            }

            public getDeviceDetails_result getResult(I i, getDeviceDetails_args getdevicedetails_args) throws TException {
                getDeviceDetails_result getdevicedetails_result = new getDeviceDetails_result();
                getdevicedetails_result.success = i.getDeviceDetails();
                return getdevicedetails_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getDeviceDetails_args getdevicedetails_args) throws TException {
                return getResult((getDeviceDetails<I>) ((Iface) obj), getdevicedetails_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getKnownList<I extends Iface> extends ProcessFunction<I, getKnownList_args> {
            public getKnownList() {
                super("getKnownList");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getKnownList_args mo3865getEmptyArgsInstance() {
                return new getKnownList_args();
            }

            public getKnownList_result getResult(I i, getKnownList_args getknownlist_args) throws TException {
                getKnownList_result getknownlist_result = new getKnownList_result();
                getknownlist_result.success = i.getKnownList();
                return getknownlist_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getKnownList_args getknownlist_args) throws TException {
                return getResult((getKnownList<I>) ((Iface) obj), getknownlist_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getLinkCode<I extends Iface> extends ProcessFunction<I, getLinkCode_args> {
            public getLinkCode() {
                super("getLinkCode");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getLinkCode_args mo3865getEmptyArgsInstance() {
                return new getLinkCode_args();
            }

            public getLinkCode_result getResult(I i, getLinkCode_args getlinkcode_args) throws TException {
                getLinkCode_result getlinkcode_result = new getLinkCode_result();
                getlinkcode_result.success = i.getLinkCode();
                return getlinkcode_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getLinkCode_args getlinkcode_args) throws TException {
                return getResult((getLinkCode<I>) ((Iface) obj), getlinkcode_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getOTADetails<I extends Iface> extends ProcessFunction<I, getOTADetails_args> {
            public getOTADetails() {
                super("getOTADetails");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getOTADetails_args mo3865getEmptyArgsInstance() {
                return new getOTADetails_args();
            }

            public getOTADetails_result getResult(I i, getOTADetails_args getotadetails_args) throws TException {
                getOTADetails_result getotadetails_result = new getOTADetails_result();
                getotadetails_result.success = i.getOTADetails();
                return getotadetails_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getOTADetails_args getotadetails_args) throws TException {
                return getResult((getOTADetails<I>) ((Iface) obj), getotadetails_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getRegistrationState<I extends Iface> extends ProcessFunction<I, getRegistrationState_args> {
            public getRegistrationState() {
                super("getRegistrationState");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getRegistrationState_args mo3865getEmptyArgsInstance() {
                return new getRegistrationState_args();
            }

            public getRegistrationState_result getResult(I i, getRegistrationState_args getregistrationstate_args) throws TException {
                getRegistrationState_result getregistrationstate_result = new getRegistrationState_result();
                getregistrationstate_result.success = i.getRegistrationState();
                return getregistrationstate_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getRegistrationState_args getregistrationstate_args) throws TException {
                return getResult((getRegistrationState<I>) ((Iface) obj), getregistrationstate_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class getScanList<I extends Iface> extends ProcessFunction<I, getScanList_args> {
            public getScanList() {
                super("getScanList");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public getScanList_args mo3865getEmptyArgsInstance() {
                return new getScanList_args();
            }

            public getScanList_result getResult(I i, getScanList_args getscanlist_args) throws TException {
                getScanList_result getscanlist_result = new getScanList_result();
                getscanlist_result.success = i.getScanList();
                return getscanlist_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, getScanList_args getscanlist_args) throws TException {
                return getResult((getScanList<I>) ((Iface) obj), getscanlist_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class ping<I extends Iface> extends ProcessFunction<I, ping_args> {
            public ping() {
                super("ping");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public ping_args mo3865getEmptyArgsInstance() {
                return new ping_args();
            }

            public ping_result getResult(I i, ping_args ping_argsVar) throws TException {
                ping_result ping_resultVar = new ping_result();
                ping_resultVar.success = i.ping();
                ping_resultVar.setSuccessIsSet(true);
                return ping_resultVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, ping_args ping_argsVar) throws TException {
                return getResult((ping<I>) ((Iface) obj), ping_argsVar);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints<I extends Iface> extends ProcessFunction<I, setLocaleAndEndpoints_args> {
            public setLocaleAndEndpoints() {
                super("setLocaleAndEndpoints");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public setLocaleAndEndpoints_args mo3865getEmptyArgsInstance() {
                return new setLocaleAndEndpoints_args();
            }

            public setLocaleAndEndpoints_result getResult(I i, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                setLocaleAndEndpoints_result setlocaleandendpoints_result = new setLocaleAndEndpoints_result();
                setlocaleandendpoints_result.success = i.setLocaleAndEndpoints(setlocaleandendpoints_args.lo);
                return setlocaleandendpoints_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                return getResult((setLocaleAndEndpoints<I>) ((Iface) obj), setlocaleandendpoints_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        /* loaded from: classes12.dex */
        public static class setupComplete<I extends Iface> extends ProcessFunction<I, setupComplete_args> {
            public setupComplete() {
                super("setupComplete");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // org.apache.thrift.orig.ProcessFunction
            /* renamed from: getEmptyArgsInstance */
            public setupComplete_args mo3865getEmptyArgsInstance() {
                return new setupComplete_args();
            }

            public setupComplete_result getResult(I i, setupComplete_args setupcomplete_args) throws TException {
                setupComplete_result setupcomplete_result = new setupComplete_result();
                setupcomplete_result.success = i.setupComplete();
                return setupcomplete_result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.apache.thrift.orig.ProcessFunction
            public /* bridge */ /* synthetic */ TBase getResult(Object obj, setupComplete_args setupcomplete_args) throws TException {
                return getResult((setupComplete<I>) ((Iface) obj), setupcomplete_args);
            }

            @Override // org.apache.thrift.orig.ProcessFunction
            protected boolean isOneway() {
                return false;
            }
        }

        public Processor(I i) {
            super(i, getProcessMap(new HashMap()));
        }

        protected Processor(I i, Map<String, ProcessFunction<I, ? extends TBase>> map) {
            super(i, getProcessMap(map));
        }

        private static <I extends Iface> Map<String, ProcessFunction<I, ? extends TBase>> getProcessMap(Map<String, ProcessFunction<I, ? extends TBase>> map) {
            map.put("ping", new ping());
            map.put("getDeviceDetails", new getDeviceDetails());
            map.put("getScanList", new getScanList());
            map.put("getKnownList", new getKnownList());
            map.put("getOTADetails", new getOTADetails());
            map.put("connectToAP", new connectToAP());
            map.put("connectToAPEx", new connectToAPEx());
            map.put("disconnectFromAP", new disconnectFromAP());
            map.put("forgetAP", new forgetAP());
            map.put("getLinkCode", new getLinkCode());
            map.put("setupComplete", new setupComplete());
            map.put("getRegistrationState", new getRegistrationState());
            map.put("getAlexaConnectionState", new getAlexaConnectionState());
            map.put("evaluateCaptivePortal", new evaluateCaptivePortal());
            map.put("setLocaleAndEndpoints", new setLocaleAndEndpoints());
            return map;
        }
    }

    /* loaded from: classes12.dex */
    public static class connectToAPEx_args implements TBase<connectToAPEx_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public APConnectInfo ap;
        public APConnectExtendedInfo extended;
        private static final TStruct STRUCT_DESC = new TStruct("connectToAPEx_args");
        private static final TField AP_FIELD_DESC = new TField("ap", (byte) 12, 1);
        private static final TField EXTENDED_FIELD_DESC = new TField("extended", (byte) 12, 2);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            AP(1, "ap"),
            EXTENDED(2, "extended");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 1) {
                    if (i == 2) {
                        return EXTENDED;
                    }
                    return null;
                }
                return AP;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAPEx_argsStandardScheme extends StandardScheme<connectToAPEx_args> {
            private connectToAPEx_argsStandardScheme() {
            }

            /* synthetic */ connectToAPEx_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAPEx_args connecttoapex_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        connecttoapex_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    if (s != 1) {
                        if (s == 2 && b == 12) {
                            connecttoapex_args.extended = new APConnectExtendedInfo();
                            connecttoapex_args.extended.read(tProtocol);
                            connecttoapex_args.setExtendedIsSet(true);
                            tProtocol.readFieldEnd();
                        }
                        TProtocolUtil.skip(tProtocol, b);
                        tProtocol.readFieldEnd();
                    } else {
                        if (b == 12) {
                            connecttoapex_args.ap = new APConnectInfo();
                            connecttoapex_args.ap.read(tProtocol);
                            connecttoapex_args.setApIsSet(true);
                            tProtocol.readFieldEnd();
                        }
                        TProtocolUtil.skip(tProtocol, b);
                        tProtocol.readFieldEnd();
                    }
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAPEx_args connecttoapex_args) throws TException {
                connecttoapex_args.validate();
                tProtocol.writeStructBegin(connectToAPEx_args.STRUCT_DESC);
                if (connecttoapex_args.ap != null) {
                    tProtocol.writeFieldBegin(connectToAPEx_args.AP_FIELD_DESC);
                    connecttoapex_args.ap.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                if (connecttoapex_args.extended != null) {
                    tProtocol.writeFieldBegin(connectToAPEx_args.EXTENDED_FIELD_DESC);
                    connecttoapex_args.extended.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAPEx_argsStandardSchemeFactory implements SchemeFactory {
            private connectToAPEx_argsStandardSchemeFactory() {
            }

            /* synthetic */ connectToAPEx_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAPEx_argsStandardScheme mo12846getScheme() {
                return new connectToAPEx_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAPEx_argsTupleScheme extends TupleScheme<connectToAPEx_args> {
            private connectToAPEx_argsTupleScheme() {
            }

            /* synthetic */ connectToAPEx_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAPEx_args connecttoapex_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet readBitSet = tTupleProtocol.readBitSet(2);
                if (readBitSet.get(0)) {
                    connecttoapex_args.ap = new APConnectInfo();
                    connecttoapex_args.ap.read(tTupleProtocol);
                    connecttoapex_args.setApIsSet(true);
                }
                if (readBitSet.get(1)) {
                    connecttoapex_args.extended = new APConnectExtendedInfo();
                    connecttoapex_args.extended.read(tTupleProtocol);
                    connecttoapex_args.setExtendedIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAPEx_args connecttoapex_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (connecttoapex_args.isSetAp()) {
                    bitSet.set(0);
                }
                if (connecttoapex_args.isSetExtended()) {
                    bitSet.set(1);
                }
                tTupleProtocol.writeBitSet(bitSet, 2);
                if (connecttoapex_args.isSetAp()) {
                    connecttoapex_args.ap.write(tTupleProtocol);
                }
                if (connecttoapex_args.isSetExtended()) {
                    connecttoapex_args.extended.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAPEx_argsTupleSchemeFactory implements SchemeFactory {
            private connectToAPEx_argsTupleSchemeFactory() {
            }

            /* synthetic */ connectToAPEx_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAPEx_argsTupleScheme mo12846getScheme() {
                return new connectToAPEx_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new connectToAPEx_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new connectToAPEx_argsTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.AP, (_Fields) new FieldMetaData("ap", (byte) 3, new StructMetaData((byte) 12, APConnectInfo.class)));
            enumMap.put((EnumMap) _Fields.EXTENDED, (_Fields) new FieldMetaData("extended", (byte) 3, new StructMetaData((byte) 12, APConnectExtendedInfo.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(connectToAPEx_args.class, metaDataMap);
        }

        public connectToAPEx_args() {
        }

        public connectToAPEx_args(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) {
            this();
            this.ap = aPConnectInfo;
            this.extended = aPConnectExtendedInfo;
        }

        public connectToAPEx_args(connectToAPEx_args connecttoapex_args) {
            if (connecttoapex_args.isSetAp()) {
                this.ap = new APConnectInfo(connecttoapex_args.ap);
            }
            if (connecttoapex_args.isSetExtended()) {
                this.extended = new APConnectExtendedInfo(connecttoapex_args.extended);
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.ap = null;
            this.extended = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(connectToAPEx_args connecttoapex_args) {
            int compareTo;
            int compareTo2;
            if (!connectToAPEx_args.class.equals(connecttoapex_args.getClass())) {
                return connectToAPEx_args.class.getName().compareTo(connectToAPEx_args.class.getName());
            }
            int compareTo3 = Boolean.valueOf(isSetAp()).compareTo(Boolean.valueOf(connecttoapex_args.isSetAp()));
            if (compareTo3 != 0) {
                return compareTo3;
            }
            if (isSetAp() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.ap, (Comparable) connecttoapex_args.ap)) != 0) {
                return compareTo2;
            }
            int compareTo4 = Boolean.valueOf(isSetExtended()).compareTo(Boolean.valueOf(connecttoapex_args.isSetExtended()));
            if (compareTo4 != 0) {
                return compareTo4;
            }
            if (isSetExtended() && (compareTo = TBaseHelper.compareTo((Comparable) this.extended, (Comparable) connecttoapex_args.extended)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<connectToAPEx_args, _Fields> deepCopy2() {
            return new connectToAPEx_args(this);
        }

        public boolean equals(connectToAPEx_args connecttoapex_args) {
            if (connecttoapex_args == null) {
                return false;
            }
            boolean isSetAp = isSetAp();
            boolean isSetAp2 = connecttoapex_args.isSetAp();
            if ((isSetAp || isSetAp2) && (!isSetAp || !isSetAp2 || !this.ap.equals(connecttoapex_args.ap))) {
                return false;
            }
            boolean isSetExtended = isSetExtended();
            boolean isSetExtended2 = connecttoapex_args.isSetExtended();
            if (!isSetExtended && !isSetExtended2) {
                return true;
            }
            return isSetExtended && isSetExtended2 && this.extended.equals(connecttoapex_args.extended);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof connectToAPEx_args)) {
                return equals((connectToAPEx_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        public APConnectInfo getAp() {
            return this.ap;
        }

        public APConnectExtendedInfo getExtended() {
            return this.extended;
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int ordinal = _fields.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    throw new IllegalStateException();
                }
                return getExtended();
            }
            return getAp();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                int ordinal = _fields.ordinal();
                if (ordinal == 0) {
                    return isSetAp();
                }
                if (ordinal != 1) {
                    throw new IllegalStateException();
                }
                return isSetExtended();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetAp() {
            return this.ap != null;
        }

        public boolean isSetExtended() {
            return this.extended != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        public connectToAPEx_args setAp(APConnectInfo aPConnectInfo) {
            this.ap = aPConnectInfo;
            return this;
        }

        public void setApIsSet(boolean z) {
            if (!z) {
                this.ap = null;
            }
        }

        public connectToAPEx_args setExtended(APConnectExtendedInfo aPConnectExtendedInfo) {
            this.extended = aPConnectExtendedInfo;
            return this;
        }

        public void setExtendedIsSet(boolean z) {
            if (!z) {
                this.extended = null;
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int ordinal = _fields.ordinal();
            if (ordinal == 0) {
                if (obj == null) {
                    unsetAp();
                } else {
                    setAp((APConnectInfo) obj);
                }
            } else if (ordinal != 1) {
            } else {
                if (obj == null) {
                    unsetExtended();
                } else {
                    setExtended((APConnectExtendedInfo) obj);
                }
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("connectToAPEx_args(", "ap:");
            APConnectInfo aPConnectInfo = this.ap;
            if (aPConnectInfo == null) {
                outline112.append("null");
            } else {
                outline112.append(aPConnectInfo);
            }
            outline112.append(", ");
            outline112.append("extended:");
            APConnectExtendedInfo aPConnectExtendedInfo = this.extended;
            if (aPConnectExtendedInfo == null) {
                outline112.append("null");
            } else {
                outline112.append(aPConnectExtendedInfo);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetAp() {
            this.ap = null;
        }

        public void unsetExtended() {
            this.extended = null;
        }

        public void validate() throws TException {
            APConnectInfo aPConnectInfo = this.ap;
            if (aPConnectInfo != null) {
                aPConnectInfo.validate();
            }
            APConnectExtendedInfo aPConnectExtendedInfo = this.extended;
            if (aPConnectExtendedInfo != null) {
                aPConnectExtendedInfo.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class connectToAPEx_result implements TBase<connectToAPEx_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("connectToAPEx_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAPEx_resultStandardScheme extends StandardScheme<connectToAPEx_result> {
            private connectToAPEx_resultStandardScheme() {
            }

            /* synthetic */ connectToAPEx_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAPEx_result connecttoapex_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        connecttoapex_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        connecttoapex_result.success = ReturnError.findByValue(tProtocol.readI32());
                        connecttoapex_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAPEx_result connecttoapex_result) throws TException {
                connecttoapex_result.validate();
                tProtocol.writeStructBegin(connectToAPEx_result.STRUCT_DESC);
                if (connecttoapex_result.success != null) {
                    tProtocol.writeFieldBegin(connectToAPEx_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(connecttoapex_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAPEx_resultStandardSchemeFactory implements SchemeFactory {
            private connectToAPEx_resultStandardSchemeFactory() {
            }

            /* synthetic */ connectToAPEx_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAPEx_resultStandardScheme mo12846getScheme() {
                return new connectToAPEx_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAPEx_resultTupleScheme extends TupleScheme<connectToAPEx_result> {
            private connectToAPEx_resultTupleScheme() {
            }

            /* synthetic */ connectToAPEx_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAPEx_result connecttoapex_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    connecttoapex_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    connecttoapex_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAPEx_result connecttoapex_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (connecttoapex_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (connecttoapex_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(connecttoapex_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAPEx_resultTupleSchemeFactory implements SchemeFactory {
            private connectToAPEx_resultTupleSchemeFactory() {
            }

            /* synthetic */ connectToAPEx_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAPEx_resultTupleScheme mo12846getScheme() {
                return new connectToAPEx_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new connectToAPEx_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new connectToAPEx_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(connectToAPEx_result.class, metaDataMap);
        }

        public connectToAPEx_result() {
        }

        public connectToAPEx_result(connectToAPEx_result connecttoapex_result) {
            if (connecttoapex_result.isSetSuccess()) {
                this.success = connecttoapex_result.success;
            }
        }

        public connectToAPEx_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(connectToAPEx_result connecttoapex_result) {
            int compareTo;
            if (!connectToAPEx_result.class.equals(connecttoapex_result.getClass())) {
                return connectToAPEx_result.class.getName().compareTo(connectToAPEx_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(connecttoapex_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) connecttoapex_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<connectToAPEx_result, _Fields> deepCopy2() {
            return new connectToAPEx_result(this);
        }

        public boolean equals(connectToAPEx_result connecttoapex_result) {
            if (connecttoapex_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = connecttoapex_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(connecttoapex_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof connectToAPEx_result)) {
                return equals((connectToAPEx_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public connectToAPEx_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("connectToAPEx_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class connectToAP_args implements TBase<connectToAP_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public APConnectInfo ap;
        private static final TStruct STRUCT_DESC = new TStruct("connectToAP_args");
        private static final TField AP_FIELD_DESC = new TField("ap", (byte) 12, 1);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            AP(1, "ap");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 1) {
                    return null;
                }
                return AP;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAP_argsStandardScheme extends StandardScheme<connectToAP_args> {
            private connectToAP_argsStandardScheme() {
            }

            /* synthetic */ connectToAP_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAP_args connecttoap_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        connecttoap_args.validate();
                        return;
                    }
                    if (readFieldBegin.id == 1 && b == 12) {
                        connecttoap_args.ap = new APConnectInfo();
                        connecttoap_args.ap.read(tProtocol);
                        connecttoap_args.setApIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAP_args connecttoap_args) throws TException {
                connecttoap_args.validate();
                tProtocol.writeStructBegin(connectToAP_args.STRUCT_DESC);
                if (connecttoap_args.ap != null) {
                    tProtocol.writeFieldBegin(connectToAP_args.AP_FIELD_DESC);
                    connecttoap_args.ap.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAP_argsStandardSchemeFactory implements SchemeFactory {
            private connectToAP_argsStandardSchemeFactory() {
            }

            /* synthetic */ connectToAP_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAP_argsStandardScheme mo12846getScheme() {
                return new connectToAP_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAP_argsTupleScheme extends TupleScheme<connectToAP_args> {
            private connectToAP_argsTupleScheme() {
            }

            /* synthetic */ connectToAP_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAP_args connecttoap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    connecttoap_args.ap = new APConnectInfo();
                    connecttoap_args.ap.read(tTupleProtocol);
                    connecttoap_args.setApIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAP_args connecttoap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (connecttoap_args.isSetAp()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (connecttoap_args.isSetAp()) {
                    connecttoap_args.ap.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAP_argsTupleSchemeFactory implements SchemeFactory {
            private connectToAP_argsTupleSchemeFactory() {
            }

            /* synthetic */ connectToAP_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAP_argsTupleScheme mo12846getScheme() {
                return new connectToAP_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new connectToAP_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new connectToAP_argsTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.AP, (_Fields) new FieldMetaData("ap", (byte) 3, new StructMetaData((byte) 12, APConnectInfo.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(connectToAP_args.class, metaDataMap);
        }

        public connectToAP_args() {
        }

        public connectToAP_args(APConnectInfo aPConnectInfo) {
            this();
            this.ap = aPConnectInfo;
        }

        public connectToAP_args(connectToAP_args connecttoap_args) {
            if (connecttoap_args.isSetAp()) {
                this.ap = new APConnectInfo(connecttoap_args.ap);
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.ap = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(connectToAP_args connecttoap_args) {
            int compareTo;
            if (!connectToAP_args.class.equals(connecttoap_args.getClass())) {
                return connectToAP_args.class.getName().compareTo(connectToAP_args.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetAp()).compareTo(Boolean.valueOf(connecttoap_args.isSetAp()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetAp() && (compareTo = TBaseHelper.compareTo((Comparable) this.ap, (Comparable) connecttoap_args.ap)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<connectToAP_args, _Fields> deepCopy2() {
            return new connectToAP_args(this);
        }

        public boolean equals(connectToAP_args connecttoap_args) {
            if (connecttoap_args == null) {
                return false;
            }
            boolean isSetAp = isSetAp();
            boolean isSetAp2 = connecttoap_args.isSetAp();
            if (!isSetAp && !isSetAp2) {
                return true;
            }
            return isSetAp && isSetAp2 && this.ap.equals(connecttoap_args.ap);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof connectToAP_args)) {
                return equals((connectToAP_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        public APConnectInfo getAp() {
            return this.ap;
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getAp();
            }
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetAp();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetAp() {
            return this.ap != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        public connectToAP_args setAp(APConnectInfo aPConnectInfo) {
            this.ap = aPConnectInfo;
            return this;
        }

        public void setApIsSet(boolean z) {
            if (!z) {
                this.ap = null;
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetAp();
            } else {
                setAp((APConnectInfo) obj);
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("connectToAP_args(", "ap:");
            APConnectInfo aPConnectInfo = this.ap;
            if (aPConnectInfo == null) {
                outline112.append("null");
            } else {
                outline112.append(aPConnectInfo);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetAp() {
            this.ap = null;
        }

        public void validate() throws TException {
            APConnectInfo aPConnectInfo = this.ap;
            if (aPConnectInfo != null) {
                aPConnectInfo.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class connectToAP_result implements TBase<connectToAP_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("connectToAP_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAP_resultStandardScheme extends StandardScheme<connectToAP_result> {
            private connectToAP_resultStandardScheme() {
            }

            /* synthetic */ connectToAP_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAP_result connecttoap_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        connecttoap_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        connecttoap_result.success = ReturnError.findByValue(tProtocol.readI32());
                        connecttoap_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAP_result connecttoap_result) throws TException {
                connecttoap_result.validate();
                tProtocol.writeStructBegin(connectToAP_result.STRUCT_DESC);
                if (connecttoap_result.success != null) {
                    tProtocol.writeFieldBegin(connectToAP_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(connecttoap_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAP_resultStandardSchemeFactory implements SchemeFactory {
            private connectToAP_resultStandardSchemeFactory() {
            }

            /* synthetic */ connectToAP_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAP_resultStandardScheme mo12846getScheme() {
                return new connectToAP_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class connectToAP_resultTupleScheme extends TupleScheme<connectToAP_result> {
            private connectToAP_resultTupleScheme() {
            }

            /* synthetic */ connectToAP_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, connectToAP_result connecttoap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    connecttoap_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    connecttoap_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, connectToAP_result connecttoap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (connecttoap_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (connecttoap_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(connecttoap_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class connectToAP_resultTupleSchemeFactory implements SchemeFactory {
            private connectToAP_resultTupleSchemeFactory() {
            }

            /* synthetic */ connectToAP_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public connectToAP_resultTupleScheme mo12846getScheme() {
                return new connectToAP_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new connectToAP_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new connectToAP_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(connectToAP_result.class, metaDataMap);
        }

        public connectToAP_result() {
        }

        public connectToAP_result(connectToAP_result connecttoap_result) {
            if (connecttoap_result.isSetSuccess()) {
                this.success = connecttoap_result.success;
            }
        }

        public connectToAP_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(connectToAP_result connecttoap_result) {
            int compareTo;
            if (!connectToAP_result.class.equals(connecttoap_result.getClass())) {
                return connectToAP_result.class.getName().compareTo(connectToAP_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(connecttoap_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) connecttoap_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<connectToAP_result, _Fields> deepCopy2() {
            return new connectToAP_result(this);
        }

        public boolean equals(connectToAP_result connecttoap_result) {
            if (connecttoap_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = connecttoap_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(connecttoap_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof connectToAP_result)) {
                return equals((connectToAP_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public connectToAP_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("connectToAP_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class disconnectFromAP_args implements TBase<disconnectFromAP_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public String ssid;
        private static final TStruct STRUCT_DESC = new TStruct("disconnectFromAP_args");
        private static final TField SSID_FIELD_DESC = new TField("ssid", (byte) 11, 1);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SSID(1, "ssid");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 1) {
                    return null;
                }
                return SSID;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class disconnectFromAP_argsStandardScheme extends StandardScheme<disconnectFromAP_args> {
            private disconnectFromAP_argsStandardScheme() {
            }

            /* synthetic */ disconnectFromAP_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, disconnectFromAP_args disconnectfromap_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        disconnectfromap_args.validate();
                        return;
                    }
                    if (readFieldBegin.id == 1 && b == 11) {
                        disconnectfromap_args.ssid = tProtocol.readString();
                        disconnectfromap_args.setSsidIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, disconnectFromAP_args disconnectfromap_args) throws TException {
                disconnectfromap_args.validate();
                tProtocol.writeStructBegin(disconnectFromAP_args.STRUCT_DESC);
                if (disconnectfromap_args.ssid != null) {
                    tProtocol.writeFieldBegin(disconnectFromAP_args.SSID_FIELD_DESC);
                    tProtocol.writeString(disconnectfromap_args.ssid);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class disconnectFromAP_argsStandardSchemeFactory implements SchemeFactory {
            private disconnectFromAP_argsStandardSchemeFactory() {
            }

            /* synthetic */ disconnectFromAP_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public disconnectFromAP_argsStandardScheme mo12846getScheme() {
                return new disconnectFromAP_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class disconnectFromAP_argsTupleScheme extends TupleScheme<disconnectFromAP_args> {
            private disconnectFromAP_argsTupleScheme() {
            }

            /* synthetic */ disconnectFromAP_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, disconnectFromAP_args disconnectfromap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    disconnectfromap_args.ssid = tTupleProtocol.readString();
                    disconnectfromap_args.setSsidIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, disconnectFromAP_args disconnectfromap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (disconnectfromap_args.isSetSsid()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (disconnectfromap_args.isSetSsid()) {
                    tTupleProtocol.writeString(disconnectfromap_args.ssid);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class disconnectFromAP_argsTupleSchemeFactory implements SchemeFactory {
            private disconnectFromAP_argsTupleSchemeFactory() {
            }

            /* synthetic */ disconnectFromAP_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public disconnectFromAP_argsTupleScheme mo12846getScheme() {
                return new disconnectFromAP_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new disconnectFromAP_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new disconnectFromAP_argsTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SSID, (_Fields) new FieldMetaData("ssid", (byte) 3, new FieldValueMetaData((byte) 11)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(disconnectFromAP_args.class, metaDataMap);
        }

        public disconnectFromAP_args() {
        }

        public disconnectFromAP_args(disconnectFromAP_args disconnectfromap_args) {
            if (disconnectfromap_args.isSetSsid()) {
                this.ssid = disconnectfromap_args.ssid;
            }
        }

        public disconnectFromAP_args(String str) {
            this();
            this.ssid = str;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.ssid = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(disconnectFromAP_args disconnectfromap_args) {
            int compareTo;
            if (!disconnectFromAP_args.class.equals(disconnectfromap_args.getClass())) {
                return disconnectFromAP_args.class.getName().compareTo(disconnectFromAP_args.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSsid()).compareTo(Boolean.valueOf(disconnectfromap_args.isSetSsid()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSsid() && (compareTo = TBaseHelper.compareTo(this.ssid, disconnectfromap_args.ssid)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<disconnectFromAP_args, _Fields> deepCopy2() {
            return new disconnectFromAP_args(this);
        }

        public boolean equals(disconnectFromAP_args disconnectfromap_args) {
            if (disconnectfromap_args == null) {
                return false;
            }
            boolean isSetSsid = isSetSsid();
            boolean isSetSsid2 = disconnectfromap_args.isSetSsid();
            if (!isSetSsid && !isSetSsid2) {
                return true;
            }
            return isSetSsid && isSetSsid2 && this.ssid.equals(disconnectfromap_args.ssid);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof disconnectFromAP_args)) {
                return equals((disconnectFromAP_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSsid();
            }
            throw new IllegalStateException();
        }

        public String getSsid() {
            return this.ssid;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSsid();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSsid() {
            return this.ssid != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSsid();
            } else {
                setSsid((String) obj);
            }
        }

        public disconnectFromAP_args setSsid(String str) {
            this.ssid = str;
            return this;
        }

        public void setSsidIsSet(boolean z) {
            if (!z) {
                this.ssid = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("disconnectFromAP_args(", "ssid:");
            String str = this.ssid;
            if (str == null) {
                str = "null";
            }
            return GeneratedOutlineSupport1.outline91(outline112, str, ")");
        }

        public void unsetSsid() {
            this.ssid = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class disconnectFromAP_result implements TBase<disconnectFromAP_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("disconnectFromAP_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class disconnectFromAP_resultStandardScheme extends StandardScheme<disconnectFromAP_result> {
            private disconnectFromAP_resultStandardScheme() {
            }

            /* synthetic */ disconnectFromAP_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, disconnectFromAP_result disconnectfromap_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        disconnectfromap_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        disconnectfromap_result.success = ReturnError.findByValue(tProtocol.readI32());
                        disconnectfromap_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, disconnectFromAP_result disconnectfromap_result) throws TException {
                disconnectfromap_result.validate();
                tProtocol.writeStructBegin(disconnectFromAP_result.STRUCT_DESC);
                if (disconnectfromap_result.success != null) {
                    tProtocol.writeFieldBegin(disconnectFromAP_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(disconnectfromap_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class disconnectFromAP_resultStandardSchemeFactory implements SchemeFactory {
            private disconnectFromAP_resultStandardSchemeFactory() {
            }

            /* synthetic */ disconnectFromAP_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public disconnectFromAP_resultStandardScheme mo12846getScheme() {
                return new disconnectFromAP_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class disconnectFromAP_resultTupleScheme extends TupleScheme<disconnectFromAP_result> {
            private disconnectFromAP_resultTupleScheme() {
            }

            /* synthetic */ disconnectFromAP_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, disconnectFromAP_result disconnectfromap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    disconnectfromap_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    disconnectfromap_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, disconnectFromAP_result disconnectfromap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (disconnectfromap_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (disconnectfromap_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(disconnectfromap_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class disconnectFromAP_resultTupleSchemeFactory implements SchemeFactory {
            private disconnectFromAP_resultTupleSchemeFactory() {
            }

            /* synthetic */ disconnectFromAP_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public disconnectFromAP_resultTupleScheme mo12846getScheme() {
                return new disconnectFromAP_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new disconnectFromAP_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new disconnectFromAP_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(disconnectFromAP_result.class, metaDataMap);
        }

        public disconnectFromAP_result() {
        }

        public disconnectFromAP_result(disconnectFromAP_result disconnectfromap_result) {
            if (disconnectfromap_result.isSetSuccess()) {
                this.success = disconnectfromap_result.success;
            }
        }

        public disconnectFromAP_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(disconnectFromAP_result disconnectfromap_result) {
            int compareTo;
            if (!disconnectFromAP_result.class.equals(disconnectfromap_result.getClass())) {
                return disconnectFromAP_result.class.getName().compareTo(disconnectFromAP_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(disconnectfromap_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) disconnectfromap_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<disconnectFromAP_result, _Fields> deepCopy2() {
            return new disconnectFromAP_result(this);
        }

        public boolean equals(disconnectFromAP_result disconnectfromap_result) {
            if (disconnectfromap_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = disconnectfromap_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(disconnectfromap_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof disconnectFromAP_result)) {
                return equals((disconnectFromAP_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public disconnectFromAP_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("disconnectFromAP_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class evaluateCaptivePortal_args implements TBase<evaluateCaptivePortal_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("evaluateCaptivePortal_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal_argsStandardScheme extends StandardScheme<evaluateCaptivePortal_args> {
            private evaluateCaptivePortal_argsStandardScheme() {
            }

            /* synthetic */ evaluateCaptivePortal_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        evaluatecaptiveportal_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                evaluatecaptiveportal_args.validate();
                tProtocol.writeStructBegin(evaluateCaptivePortal_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class evaluateCaptivePortal_argsStandardSchemeFactory implements SchemeFactory {
            private evaluateCaptivePortal_argsStandardSchemeFactory() {
            }

            /* synthetic */ evaluateCaptivePortal_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public evaluateCaptivePortal_argsStandardScheme mo12846getScheme() {
                return new evaluateCaptivePortal_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal_argsTupleScheme extends TupleScheme<evaluateCaptivePortal_args> {
            private evaluateCaptivePortal_argsTupleScheme() {
            }

            /* synthetic */ evaluateCaptivePortal_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, evaluateCaptivePortal_args evaluatecaptiveportal_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class evaluateCaptivePortal_argsTupleSchemeFactory implements SchemeFactory {
            private evaluateCaptivePortal_argsTupleSchemeFactory() {
            }

            /* synthetic */ evaluateCaptivePortal_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public evaluateCaptivePortal_argsTupleScheme mo12846getScheme() {
                return new evaluateCaptivePortal_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new evaluateCaptivePortal_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new evaluateCaptivePortal_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(evaluateCaptivePortal_args.class, metaDataMap);
        }

        public evaluateCaptivePortal_args() {
        }

        public evaluateCaptivePortal_args(evaluateCaptivePortal_args evaluatecaptiveportal_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(evaluateCaptivePortal_args evaluatecaptiveportal_args) {
            if (!evaluateCaptivePortal_args.class.equals(evaluatecaptiveportal_args.getClass())) {
                return evaluateCaptivePortal_args.class.getName().compareTo(evaluateCaptivePortal_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<evaluateCaptivePortal_args, _Fields> deepCopy2() {
            return new evaluateCaptivePortal_args(this);
        }

        public boolean equals(evaluateCaptivePortal_args evaluatecaptiveportal_args) {
            return evaluatecaptiveportal_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof evaluateCaptivePortal_args)) {
                return equals((evaluateCaptivePortal_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$evaluateCaptivePortal_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "evaluateCaptivePortal_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class evaluateCaptivePortal_result implements TBase<evaluateCaptivePortal_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("evaluateCaptivePortal_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal_resultStandardScheme extends StandardScheme<evaluateCaptivePortal_result> {
            private evaluateCaptivePortal_resultStandardScheme() {
            }

            /* synthetic */ evaluateCaptivePortal_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, evaluateCaptivePortal_result evaluatecaptiveportal_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        evaluatecaptiveportal_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        evaluatecaptiveportal_result.success = ReturnError.findByValue(tProtocol.readI32());
                        evaluatecaptiveportal_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, evaluateCaptivePortal_result evaluatecaptiveportal_result) throws TException {
                evaluatecaptiveportal_result.validate();
                tProtocol.writeStructBegin(evaluateCaptivePortal_result.STRUCT_DESC);
                if (evaluatecaptiveportal_result.success != null) {
                    tProtocol.writeFieldBegin(evaluateCaptivePortal_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(evaluatecaptiveportal_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class evaluateCaptivePortal_resultStandardSchemeFactory implements SchemeFactory {
            private evaluateCaptivePortal_resultStandardSchemeFactory() {
            }

            /* synthetic */ evaluateCaptivePortal_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public evaluateCaptivePortal_resultStandardScheme mo12846getScheme() {
                return new evaluateCaptivePortal_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class evaluateCaptivePortal_resultTupleScheme extends TupleScheme<evaluateCaptivePortal_result> {
            private evaluateCaptivePortal_resultTupleScheme() {
            }

            /* synthetic */ evaluateCaptivePortal_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, evaluateCaptivePortal_result evaluatecaptiveportal_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    evaluatecaptiveportal_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    evaluatecaptiveportal_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, evaluateCaptivePortal_result evaluatecaptiveportal_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (evaluatecaptiveportal_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (evaluatecaptiveportal_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(evaluatecaptiveportal_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class evaluateCaptivePortal_resultTupleSchemeFactory implements SchemeFactory {
            private evaluateCaptivePortal_resultTupleSchemeFactory() {
            }

            /* synthetic */ evaluateCaptivePortal_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public evaluateCaptivePortal_resultTupleScheme mo12846getScheme() {
                return new evaluateCaptivePortal_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new evaluateCaptivePortal_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new evaluateCaptivePortal_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(evaluateCaptivePortal_result.class, metaDataMap);
        }

        public evaluateCaptivePortal_result() {
        }

        public evaluateCaptivePortal_result(evaluateCaptivePortal_result evaluatecaptiveportal_result) {
            if (evaluatecaptiveportal_result.isSetSuccess()) {
                this.success = evaluatecaptiveportal_result.success;
            }
        }

        public evaluateCaptivePortal_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(evaluateCaptivePortal_result evaluatecaptiveportal_result) {
            int compareTo;
            if (!evaluateCaptivePortal_result.class.equals(evaluatecaptiveportal_result.getClass())) {
                return evaluateCaptivePortal_result.class.getName().compareTo(evaluateCaptivePortal_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(evaluatecaptiveportal_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) evaluatecaptiveportal_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<evaluateCaptivePortal_result, _Fields> deepCopy2() {
            return new evaluateCaptivePortal_result(this);
        }

        public boolean equals(evaluateCaptivePortal_result evaluatecaptiveportal_result) {
            if (evaluatecaptiveportal_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = evaluatecaptiveportal_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(evaluatecaptiveportal_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof evaluateCaptivePortal_result)) {
                return equals((evaluateCaptivePortal_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public evaluateCaptivePortal_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("evaluateCaptivePortal_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class forgetAP_args implements TBase<forgetAP_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public String ssid;
        private static final TStruct STRUCT_DESC = new TStruct("forgetAP_args");
        private static final TField SSID_FIELD_DESC = new TField("ssid", (byte) 11, 1);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SSID(1, "ssid");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 1) {
                    return null;
                }
                return SSID;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class forgetAP_argsStandardScheme extends StandardScheme<forgetAP_args> {
            private forgetAP_argsStandardScheme() {
            }

            /* synthetic */ forgetAP_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, forgetAP_args forgetap_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        forgetap_args.validate();
                        return;
                    }
                    if (readFieldBegin.id == 1 && b == 11) {
                        forgetap_args.ssid = tProtocol.readString();
                        forgetap_args.setSsidIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, forgetAP_args forgetap_args) throws TException {
                forgetap_args.validate();
                tProtocol.writeStructBegin(forgetAP_args.STRUCT_DESC);
                if (forgetap_args.ssid != null) {
                    tProtocol.writeFieldBegin(forgetAP_args.SSID_FIELD_DESC);
                    tProtocol.writeString(forgetap_args.ssid);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class forgetAP_argsStandardSchemeFactory implements SchemeFactory {
            private forgetAP_argsStandardSchemeFactory() {
            }

            /* synthetic */ forgetAP_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public forgetAP_argsStandardScheme mo12846getScheme() {
                return new forgetAP_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class forgetAP_argsTupleScheme extends TupleScheme<forgetAP_args> {
            private forgetAP_argsTupleScheme() {
            }

            /* synthetic */ forgetAP_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, forgetAP_args forgetap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    forgetap_args.ssid = tTupleProtocol.readString();
                    forgetap_args.setSsidIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, forgetAP_args forgetap_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (forgetap_args.isSetSsid()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (forgetap_args.isSetSsid()) {
                    tTupleProtocol.writeString(forgetap_args.ssid);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class forgetAP_argsTupleSchemeFactory implements SchemeFactory {
            private forgetAP_argsTupleSchemeFactory() {
            }

            /* synthetic */ forgetAP_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public forgetAP_argsTupleScheme mo12846getScheme() {
                return new forgetAP_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new forgetAP_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new forgetAP_argsTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SSID, (_Fields) new FieldMetaData("ssid", (byte) 3, new FieldValueMetaData((byte) 11)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(forgetAP_args.class, metaDataMap);
        }

        public forgetAP_args() {
        }

        public forgetAP_args(forgetAP_args forgetap_args) {
            if (forgetap_args.isSetSsid()) {
                this.ssid = forgetap_args.ssid;
            }
        }

        public forgetAP_args(String str) {
            this();
            this.ssid = str;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.ssid = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(forgetAP_args forgetap_args) {
            int compareTo;
            if (!forgetAP_args.class.equals(forgetap_args.getClass())) {
                return forgetAP_args.class.getName().compareTo(forgetAP_args.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSsid()).compareTo(Boolean.valueOf(forgetap_args.isSetSsid()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSsid() && (compareTo = TBaseHelper.compareTo(this.ssid, forgetap_args.ssid)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<forgetAP_args, _Fields> deepCopy2() {
            return new forgetAP_args(this);
        }

        public boolean equals(forgetAP_args forgetap_args) {
            if (forgetap_args == null) {
                return false;
            }
            boolean isSetSsid = isSetSsid();
            boolean isSetSsid2 = forgetap_args.isSetSsid();
            if (!isSetSsid && !isSetSsid2) {
                return true;
            }
            return isSetSsid && isSetSsid2 && this.ssid.equals(forgetap_args.ssid);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof forgetAP_args)) {
                return equals((forgetAP_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSsid();
            }
            throw new IllegalStateException();
        }

        public String getSsid() {
            return this.ssid;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSsid();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSsid() {
            return this.ssid != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSsid();
            } else {
                setSsid((String) obj);
            }
        }

        public forgetAP_args setSsid(String str) {
            this.ssid = str;
            return this;
        }

        public void setSsidIsSet(boolean z) {
            if (!z) {
                this.ssid = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("forgetAP_args(", "ssid:");
            String str = this.ssid;
            if (str == null) {
                str = "null";
            }
            return GeneratedOutlineSupport1.outline91(outline112, str, ")");
        }

        public void unsetSsid() {
            this.ssid = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class forgetAP_result implements TBase<forgetAP_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("forgetAP_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class forgetAP_resultStandardScheme extends StandardScheme<forgetAP_result> {
            private forgetAP_resultStandardScheme() {
            }

            /* synthetic */ forgetAP_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, forgetAP_result forgetap_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        forgetap_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        forgetap_result.success = ReturnError.findByValue(tProtocol.readI32());
                        forgetap_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, forgetAP_result forgetap_result) throws TException {
                forgetap_result.validate();
                tProtocol.writeStructBegin(forgetAP_result.STRUCT_DESC);
                if (forgetap_result.success != null) {
                    tProtocol.writeFieldBegin(forgetAP_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(forgetap_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class forgetAP_resultStandardSchemeFactory implements SchemeFactory {
            private forgetAP_resultStandardSchemeFactory() {
            }

            /* synthetic */ forgetAP_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public forgetAP_resultStandardScheme mo12846getScheme() {
                return new forgetAP_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class forgetAP_resultTupleScheme extends TupleScheme<forgetAP_result> {
            private forgetAP_resultTupleScheme() {
            }

            /* synthetic */ forgetAP_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, forgetAP_result forgetap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    forgetap_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    forgetap_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, forgetAP_result forgetap_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (forgetap_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (forgetap_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(forgetap_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class forgetAP_resultTupleSchemeFactory implements SchemeFactory {
            private forgetAP_resultTupleSchemeFactory() {
            }

            /* synthetic */ forgetAP_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public forgetAP_resultTupleScheme mo12846getScheme() {
                return new forgetAP_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new forgetAP_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new forgetAP_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(forgetAP_result.class, metaDataMap);
        }

        public forgetAP_result() {
        }

        public forgetAP_result(forgetAP_result forgetap_result) {
            if (forgetap_result.isSetSuccess()) {
                this.success = forgetap_result.success;
            }
        }

        public forgetAP_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(forgetAP_result forgetap_result) {
            int compareTo;
            if (!forgetAP_result.class.equals(forgetap_result.getClass())) {
                return forgetAP_result.class.getName().compareTo(forgetAP_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(forgetap_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) forgetap_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<forgetAP_result, _Fields> deepCopy2() {
            return new forgetAP_result(this);
        }

        public boolean equals(forgetAP_result forgetap_result) {
            if (forgetap_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = forgetap_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(forgetap_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof forgetAP_result)) {
                return equals((forgetAP_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public forgetAP_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("forgetAP_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getAlexaConnectionState_args implements TBase<getAlexaConnectionState_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getAlexaConnectionState_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState_argsStandardScheme extends StandardScheme<getAlexaConnectionState_args> {
            private getAlexaConnectionState_argsStandardScheme() {
            }

            /* synthetic */ getAlexaConnectionState_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getalexaconnectionstate_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                getalexaconnectionstate_args.validate();
                tProtocol.writeStructBegin(getAlexaConnectionState_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getAlexaConnectionState_argsStandardSchemeFactory implements SchemeFactory {
            private getAlexaConnectionState_argsStandardSchemeFactory() {
            }

            /* synthetic */ getAlexaConnectionState_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getAlexaConnectionState_argsStandardScheme mo12846getScheme() {
                return new getAlexaConnectionState_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState_argsTupleScheme extends TupleScheme<getAlexaConnectionState_args> {
            private getAlexaConnectionState_argsTupleScheme() {
            }

            /* synthetic */ getAlexaConnectionState_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getAlexaConnectionState_args getalexaconnectionstate_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getAlexaConnectionState_argsTupleSchemeFactory implements SchemeFactory {
            private getAlexaConnectionState_argsTupleSchemeFactory() {
            }

            /* synthetic */ getAlexaConnectionState_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getAlexaConnectionState_argsTupleScheme mo12846getScheme() {
                return new getAlexaConnectionState_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getAlexaConnectionState_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getAlexaConnectionState_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getAlexaConnectionState_args.class, metaDataMap);
        }

        public getAlexaConnectionState_args() {
        }

        public getAlexaConnectionState_args(getAlexaConnectionState_args getalexaconnectionstate_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getAlexaConnectionState_args getalexaconnectionstate_args) {
            if (!getAlexaConnectionState_args.class.equals(getalexaconnectionstate_args.getClass())) {
                return getAlexaConnectionState_args.class.getName().compareTo(getAlexaConnectionState_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getAlexaConnectionState_args, _Fields> deepCopy2() {
            return new getAlexaConnectionState_args(this);
        }

        public boolean equals(getAlexaConnectionState_args getalexaconnectionstate_args) {
            return getalexaconnectionstate_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getAlexaConnectionState_args)) {
                return equals((getAlexaConnectionState_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getAlexaConnectionState_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getAlexaConnectionState_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getAlexaConnectionState_result implements TBase<getAlexaConnectionState_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public AlexaConnectionState success;
        private static final TStruct STRUCT_DESC = new TStruct("getAlexaConnectionState_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState_resultStandardScheme extends StandardScheme<getAlexaConnectionState_result> {
            private getAlexaConnectionState_resultStandardScheme() {
            }

            /* synthetic */ getAlexaConnectionState_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getAlexaConnectionState_result getalexaconnectionstate_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getalexaconnectionstate_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        getalexaconnectionstate_result.success = AlexaConnectionState.findByValue(tProtocol.readI32());
                        getalexaconnectionstate_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getAlexaConnectionState_result getalexaconnectionstate_result) throws TException {
                getalexaconnectionstate_result.validate();
                tProtocol.writeStructBegin(getAlexaConnectionState_result.STRUCT_DESC);
                if (getalexaconnectionstate_result.success != null) {
                    tProtocol.writeFieldBegin(getAlexaConnectionState_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(getalexaconnectionstate_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getAlexaConnectionState_resultStandardSchemeFactory implements SchemeFactory {
            private getAlexaConnectionState_resultStandardSchemeFactory() {
            }

            /* synthetic */ getAlexaConnectionState_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getAlexaConnectionState_resultStandardScheme mo12846getScheme() {
                return new getAlexaConnectionState_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getAlexaConnectionState_resultTupleScheme extends TupleScheme<getAlexaConnectionState_result> {
            private getAlexaConnectionState_resultTupleScheme() {
            }

            /* synthetic */ getAlexaConnectionState_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getAlexaConnectionState_result getalexaconnectionstate_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    getalexaconnectionstate_result.success = AlexaConnectionState.findByValue(tTupleProtocol.readI32());
                    getalexaconnectionstate_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getAlexaConnectionState_result getalexaconnectionstate_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getalexaconnectionstate_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getalexaconnectionstate_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(getalexaconnectionstate_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getAlexaConnectionState_resultTupleSchemeFactory implements SchemeFactory {
            private getAlexaConnectionState_resultTupleSchemeFactory() {
            }

            /* synthetic */ getAlexaConnectionState_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getAlexaConnectionState_resultTupleScheme mo12846getScheme() {
                return new getAlexaConnectionState_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getAlexaConnectionState_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getAlexaConnectionState_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, AlexaConnectionState.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getAlexaConnectionState_result.class, metaDataMap);
        }

        public getAlexaConnectionState_result() {
        }

        public getAlexaConnectionState_result(AlexaConnectionState alexaConnectionState) {
            this();
            this.success = alexaConnectionState;
        }

        public getAlexaConnectionState_result(getAlexaConnectionState_result getalexaconnectionstate_result) {
            if (getalexaconnectionstate_result.isSetSuccess()) {
                this.success = getalexaconnectionstate_result.success;
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getAlexaConnectionState_result getalexaconnectionstate_result) {
            int compareTo;
            if (!getAlexaConnectionState_result.class.equals(getalexaconnectionstate_result.getClass())) {
                return getAlexaConnectionState_result.class.getName().compareTo(getAlexaConnectionState_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getalexaconnectionstate_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) getalexaconnectionstate_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getAlexaConnectionState_result, _Fields> deepCopy2() {
            return new getAlexaConnectionState_result(this);
        }

        public boolean equals(getAlexaConnectionState_result getalexaconnectionstate_result) {
            if (getalexaconnectionstate_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getalexaconnectionstate_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getalexaconnectionstate_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getAlexaConnectionState_result)) {
                return equals((getAlexaConnectionState_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public AlexaConnectionState getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((AlexaConnectionState) obj);
            }
        }

        public getAlexaConnectionState_result setSuccess(AlexaConnectionState alexaConnectionState) {
            this.success = alexaConnectionState;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getAlexaConnectionState_result(", "success:");
            AlexaConnectionState alexaConnectionState = this.success;
            if (alexaConnectionState == null) {
                outline112.append("null");
            } else {
                outline112.append(alexaConnectionState);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getDeviceDetails_args implements TBase<getDeviceDetails_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getDeviceDetails_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getDeviceDetails_argsStandardScheme extends StandardScheme<getDeviceDetails_args> {
            private getDeviceDetails_argsStandardScheme() {
            }

            /* synthetic */ getDeviceDetails_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getDeviceDetails_args getdevicedetails_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getdevicedetails_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getDeviceDetails_args getdevicedetails_args) throws TException {
                getdevicedetails_args.validate();
                tProtocol.writeStructBegin(getDeviceDetails_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getDeviceDetails_argsStandardSchemeFactory implements SchemeFactory {
            private getDeviceDetails_argsStandardSchemeFactory() {
            }

            /* synthetic */ getDeviceDetails_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getDeviceDetails_argsStandardScheme mo12846getScheme() {
                return new getDeviceDetails_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getDeviceDetails_argsTupleScheme extends TupleScheme<getDeviceDetails_args> {
            private getDeviceDetails_argsTupleScheme() {
            }

            /* synthetic */ getDeviceDetails_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getDeviceDetails_args getdevicedetails_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getDeviceDetails_args getdevicedetails_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getDeviceDetails_argsTupleSchemeFactory implements SchemeFactory {
            private getDeviceDetails_argsTupleSchemeFactory() {
            }

            /* synthetic */ getDeviceDetails_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getDeviceDetails_argsTupleScheme mo12846getScheme() {
                return new getDeviceDetails_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getDeviceDetails_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getDeviceDetails_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getDeviceDetails_args.class, metaDataMap);
        }

        public getDeviceDetails_args() {
        }

        public getDeviceDetails_args(getDeviceDetails_args getdevicedetails_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getDeviceDetails_args getdevicedetails_args) {
            if (!getDeviceDetails_args.class.equals(getdevicedetails_args.getClass())) {
                return getDeviceDetails_args.class.getName().compareTo(getDeviceDetails_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getDeviceDetails_args, _Fields> deepCopy2() {
            return new getDeviceDetails_args(this);
        }

        public boolean equals(getDeviceDetails_args getdevicedetails_args) {
            return getdevicedetails_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getDeviceDetails_args)) {
                return equals((getDeviceDetails_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getDeviceDetails_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getDeviceDetails_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getDeviceDetails_result implements TBase<getDeviceDetails_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public DeviceDetails success;
        private static final TStruct STRUCT_DESC = new TStruct("getDeviceDetails_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 12, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getDeviceDetails_resultStandardScheme extends StandardScheme<getDeviceDetails_result> {
            private getDeviceDetails_resultStandardScheme() {
            }

            /* synthetic */ getDeviceDetails_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getDeviceDetails_result getdevicedetails_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getdevicedetails_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 12) {
                        getdevicedetails_result.success = new DeviceDetails();
                        getdevicedetails_result.success.read(tProtocol);
                        getdevicedetails_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getDeviceDetails_result getdevicedetails_result) throws TException {
                getdevicedetails_result.validate();
                tProtocol.writeStructBegin(getDeviceDetails_result.STRUCT_DESC);
                if (getdevicedetails_result.success != null) {
                    tProtocol.writeFieldBegin(getDeviceDetails_result.SUCCESS_FIELD_DESC);
                    getdevicedetails_result.success.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getDeviceDetails_resultStandardSchemeFactory implements SchemeFactory {
            private getDeviceDetails_resultStandardSchemeFactory() {
            }

            /* synthetic */ getDeviceDetails_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getDeviceDetails_resultStandardScheme mo12846getScheme() {
                return new getDeviceDetails_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getDeviceDetails_resultTupleScheme extends TupleScheme<getDeviceDetails_result> {
            private getDeviceDetails_resultTupleScheme() {
            }

            /* synthetic */ getDeviceDetails_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getDeviceDetails_result getdevicedetails_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    getdevicedetails_result.success = new DeviceDetails();
                    getdevicedetails_result.success.read(tTupleProtocol);
                    getdevicedetails_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getDeviceDetails_result getdevicedetails_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getdevicedetails_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getdevicedetails_result.isSetSuccess()) {
                    getdevicedetails_result.success.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getDeviceDetails_resultTupleSchemeFactory implements SchemeFactory {
            private getDeviceDetails_resultTupleSchemeFactory() {
            }

            /* synthetic */ getDeviceDetails_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getDeviceDetails_resultTupleScheme mo12846getScheme() {
                return new getDeviceDetails_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getDeviceDetails_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getDeviceDetails_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new StructMetaData((byte) 12, DeviceDetails.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getDeviceDetails_result.class, metaDataMap);
        }

        public getDeviceDetails_result() {
        }

        public getDeviceDetails_result(DeviceDetails deviceDetails) {
            this();
            this.success = deviceDetails;
        }

        public getDeviceDetails_result(getDeviceDetails_result getdevicedetails_result) {
            if (getdevicedetails_result.isSetSuccess()) {
                this.success = new DeviceDetails(getdevicedetails_result.success);
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getDeviceDetails_result getdevicedetails_result) {
            int compareTo;
            if (!getDeviceDetails_result.class.equals(getdevicedetails_result.getClass())) {
                return getDeviceDetails_result.class.getName().compareTo(getDeviceDetails_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getdevicedetails_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) getdevicedetails_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getDeviceDetails_result, _Fields> deepCopy2() {
            return new getDeviceDetails_result(this);
        }

        public boolean equals(getDeviceDetails_result getdevicedetails_result) {
            if (getdevicedetails_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getdevicedetails_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getdevicedetails_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getDeviceDetails_result)) {
                return equals((getDeviceDetails_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public DeviceDetails getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((DeviceDetails) obj);
            }
        }

        public getDeviceDetails_result setSuccess(DeviceDetails deviceDetails) {
            this.success = deviceDetails;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getDeviceDetails_result(", "success:");
            DeviceDetails deviceDetails = this.success;
            if (deviceDetails == null) {
                outline112.append("null");
            } else {
                outline112.append(deviceDetails);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
            DeviceDetails deviceDetails = this.success;
            if (deviceDetails != null) {
                deviceDetails.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getKnownList_args implements TBase<getKnownList_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getKnownList_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getKnownList_argsStandardScheme extends StandardScheme<getKnownList_args> {
            private getKnownList_argsStandardScheme() {
            }

            /* synthetic */ getKnownList_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getKnownList_args getknownlist_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getknownlist_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getKnownList_args getknownlist_args) throws TException {
                getknownlist_args.validate();
                tProtocol.writeStructBegin(getKnownList_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getKnownList_argsStandardSchemeFactory implements SchemeFactory {
            private getKnownList_argsStandardSchemeFactory() {
            }

            /* synthetic */ getKnownList_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getKnownList_argsStandardScheme mo12846getScheme() {
                return new getKnownList_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getKnownList_argsTupleScheme extends TupleScheme<getKnownList_args> {
            private getKnownList_argsTupleScheme() {
            }

            /* synthetic */ getKnownList_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getKnownList_args getknownlist_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getKnownList_args getknownlist_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getKnownList_argsTupleSchemeFactory implements SchemeFactory {
            private getKnownList_argsTupleSchemeFactory() {
            }

            /* synthetic */ getKnownList_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getKnownList_argsTupleScheme mo12846getScheme() {
                return new getKnownList_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getKnownList_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getKnownList_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getKnownList_args.class, metaDataMap);
        }

        public getKnownList_args() {
        }

        public getKnownList_args(getKnownList_args getknownlist_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getKnownList_args getknownlist_args) {
            if (!getKnownList_args.class.equals(getknownlist_args.getClass())) {
                return getKnownList_args.class.getName().compareTo(getKnownList_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getKnownList_args, _Fields> deepCopy2() {
            return new getKnownList_args(this);
        }

        public boolean equals(getKnownList_args getknownlist_args) {
            return getknownlist_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getKnownList_args)) {
                return equals((getKnownList_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getKnownList_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getKnownList_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getKnownList_result implements TBase<getKnownList_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public List<APDetail> success;
        private static final TStruct STRUCT_DESC = new TStruct("getKnownList_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 15, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getKnownList_resultStandardScheme extends StandardScheme<getKnownList_result> {
            private getKnownList_resultStandardScheme() {
            }

            /* synthetic */ getKnownList_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getKnownList_result getknownlist_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getknownlist_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 15) {
                        TList readListBegin = tProtocol.readListBegin();
                        getknownlist_result.success = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            APDetail aPDetail = new APDetail();
                            aPDetail.read(tProtocol);
                            getknownlist_result.success.add(aPDetail);
                        }
                        tProtocol.readListEnd();
                        getknownlist_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getKnownList_result getknownlist_result) throws TException {
                getknownlist_result.validate();
                tProtocol.writeStructBegin(getKnownList_result.STRUCT_DESC);
                if (getknownlist_result.success != null) {
                    tProtocol.writeFieldBegin(getKnownList_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeListBegin(new TList((byte) 12, getknownlist_result.success.size()));
                    for (APDetail aPDetail : getknownlist_result.success) {
                        aPDetail.write(tProtocol);
                    }
                    tProtocol.writeListEnd();
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getKnownList_resultStandardSchemeFactory implements SchemeFactory {
            private getKnownList_resultStandardSchemeFactory() {
            }

            /* synthetic */ getKnownList_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getKnownList_resultStandardScheme mo12846getScheme() {
                return new getKnownList_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getKnownList_resultTupleScheme extends TupleScheme<getKnownList_result> {
            private getKnownList_resultTupleScheme() {
            }

            /* synthetic */ getKnownList_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getKnownList_result getknownlist_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    TList tList = new TList((byte) 12, tTupleProtocol.readI32());
                    getknownlist_result.success = new ArrayList(tList.size);
                    for (int i = 0; i < tList.size; i++) {
                        APDetail aPDetail = new APDetail();
                        aPDetail.read(tTupleProtocol);
                        getknownlist_result.success.add(aPDetail);
                    }
                    getknownlist_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getKnownList_result getknownlist_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getknownlist_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getknownlist_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(getknownlist_result.success.size());
                    for (APDetail aPDetail : getknownlist_result.success) {
                        aPDetail.write(tTupleProtocol);
                    }
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getKnownList_resultTupleSchemeFactory implements SchemeFactory {
            private getKnownList_resultTupleSchemeFactory() {
            }

            /* synthetic */ getKnownList_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getKnownList_resultTupleScheme mo12846getScheme() {
                return new getKnownList_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getKnownList_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getKnownList_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new ListMetaData((byte) 15, new StructMetaData((byte) 12, APDetail.class))));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getKnownList_result.class, metaDataMap);
        }

        public getKnownList_result() {
        }

        public getKnownList_result(getKnownList_result getknownlist_result) {
            if (getknownlist_result.isSetSuccess()) {
                ArrayList arrayList = new ArrayList();
                for (APDetail aPDetail : getknownlist_result.success) {
                    arrayList.add(new APDetail(aPDetail));
                }
                this.success = arrayList;
            }
        }

        public getKnownList_result(List<APDetail> list) {
            this();
            this.success = list;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        public void addToSuccess(APDetail aPDetail) {
            if (this.success == null) {
                this.success = new ArrayList();
            }
            this.success.add(aPDetail);
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getKnownList_result getknownlist_result) {
            int compareTo;
            if (!getKnownList_result.class.equals(getknownlist_result.getClass())) {
                return getKnownList_result.class.getName().compareTo(getKnownList_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getknownlist_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((List) this.success, (List) getknownlist_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getKnownList_result, _Fields> deepCopy2() {
            return new getKnownList_result(this);
        }

        public boolean equals(getKnownList_result getknownlist_result) {
            if (getknownlist_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getknownlist_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getknownlist_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getKnownList_result)) {
                return equals((getKnownList_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public List<APDetail> getSuccess() {
            return this.success;
        }

        public Iterator<APDetail> getSuccessIterator() {
            List<APDetail> list = this.success;
            if (list == null) {
                return null;
            }
            return list.iterator();
        }

        public int getSuccessSize() {
            List<APDetail> list = this.success;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((List) obj);
            }
        }

        public getKnownList_result setSuccess(List<APDetail> list) {
            this.success = list;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getKnownList_result(", "success:");
            List<APDetail> list = this.success;
            if (list == null) {
                outline112.append("null");
            } else {
                outline112.append(list);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getLinkCode_args implements TBase<getLinkCode_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getLinkCode_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getLinkCode_argsStandardScheme extends StandardScheme<getLinkCode_args> {
            private getLinkCode_argsStandardScheme() {
            }

            /* synthetic */ getLinkCode_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getLinkCode_args getlinkcode_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getlinkcode_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getLinkCode_args getlinkcode_args) throws TException {
                getlinkcode_args.validate();
                tProtocol.writeStructBegin(getLinkCode_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getLinkCode_argsStandardSchemeFactory implements SchemeFactory {
            private getLinkCode_argsStandardSchemeFactory() {
            }

            /* synthetic */ getLinkCode_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getLinkCode_argsStandardScheme mo12846getScheme() {
                return new getLinkCode_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getLinkCode_argsTupleScheme extends TupleScheme<getLinkCode_args> {
            private getLinkCode_argsTupleScheme() {
            }

            /* synthetic */ getLinkCode_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getLinkCode_args getlinkcode_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getLinkCode_args getlinkcode_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getLinkCode_argsTupleSchemeFactory implements SchemeFactory {
            private getLinkCode_argsTupleSchemeFactory() {
            }

            /* synthetic */ getLinkCode_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getLinkCode_argsTupleScheme mo12846getScheme() {
                return new getLinkCode_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getLinkCode_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getLinkCode_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getLinkCode_args.class, metaDataMap);
        }

        public getLinkCode_args() {
        }

        public getLinkCode_args(getLinkCode_args getlinkcode_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getLinkCode_args getlinkcode_args) {
            if (!getLinkCode_args.class.equals(getlinkcode_args.getClass())) {
                return getLinkCode_args.class.getName().compareTo(getLinkCode_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getLinkCode_args, _Fields> deepCopy2() {
            return new getLinkCode_args(this);
        }

        public boolean equals(getLinkCode_args getlinkcode_args) {
            return getlinkcode_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getLinkCode_args)) {
                return equals((getLinkCode_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getLinkCode_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getLinkCode_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getLinkCode_result implements TBase<getLinkCode_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public DeviceCredentials success;
        private static final TStruct STRUCT_DESC = new TStruct("getLinkCode_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 12, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getLinkCode_resultStandardScheme extends StandardScheme<getLinkCode_result> {
            private getLinkCode_resultStandardScheme() {
            }

            /* synthetic */ getLinkCode_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getLinkCode_result getlinkcode_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getlinkcode_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 12) {
                        getlinkcode_result.success = new DeviceCredentials();
                        getlinkcode_result.success.read(tProtocol);
                        getlinkcode_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getLinkCode_result getlinkcode_result) throws TException {
                getlinkcode_result.validate();
                tProtocol.writeStructBegin(getLinkCode_result.STRUCT_DESC);
                if (getlinkcode_result.success != null) {
                    tProtocol.writeFieldBegin(getLinkCode_result.SUCCESS_FIELD_DESC);
                    getlinkcode_result.success.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getLinkCode_resultStandardSchemeFactory implements SchemeFactory {
            private getLinkCode_resultStandardSchemeFactory() {
            }

            /* synthetic */ getLinkCode_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getLinkCode_resultStandardScheme mo12846getScheme() {
                return new getLinkCode_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getLinkCode_resultTupleScheme extends TupleScheme<getLinkCode_result> {
            private getLinkCode_resultTupleScheme() {
            }

            /* synthetic */ getLinkCode_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getLinkCode_result getlinkcode_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    getlinkcode_result.success = new DeviceCredentials();
                    getlinkcode_result.success.read(tTupleProtocol);
                    getlinkcode_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getLinkCode_result getlinkcode_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getlinkcode_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getlinkcode_result.isSetSuccess()) {
                    getlinkcode_result.success.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getLinkCode_resultTupleSchemeFactory implements SchemeFactory {
            private getLinkCode_resultTupleSchemeFactory() {
            }

            /* synthetic */ getLinkCode_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getLinkCode_resultTupleScheme mo12846getScheme() {
                return new getLinkCode_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getLinkCode_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getLinkCode_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new StructMetaData((byte) 12, DeviceCredentials.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getLinkCode_result.class, metaDataMap);
        }

        public getLinkCode_result() {
        }

        public getLinkCode_result(DeviceCredentials deviceCredentials) {
            this();
            this.success = deviceCredentials;
        }

        public getLinkCode_result(getLinkCode_result getlinkcode_result) {
            if (getlinkcode_result.isSetSuccess()) {
                this.success = new DeviceCredentials(getlinkcode_result.success);
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getLinkCode_result getlinkcode_result) {
            int compareTo;
            if (!getLinkCode_result.class.equals(getlinkcode_result.getClass())) {
                return getLinkCode_result.class.getName().compareTo(getLinkCode_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getlinkcode_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) getlinkcode_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getLinkCode_result, _Fields> deepCopy2() {
            return new getLinkCode_result(this);
        }

        public boolean equals(getLinkCode_result getlinkcode_result) {
            if (getlinkcode_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getlinkcode_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getlinkcode_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getLinkCode_result)) {
                return equals((getLinkCode_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public DeviceCredentials getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((DeviceCredentials) obj);
            }
        }

        public getLinkCode_result setSuccess(DeviceCredentials deviceCredentials) {
            this.success = deviceCredentials;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getLinkCode_result(", "success:");
            DeviceCredentials deviceCredentials = this.success;
            if (deviceCredentials == null) {
                outline112.append("null");
            } else {
                outline112.append(deviceCredentials);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
            DeviceCredentials deviceCredentials = this.success;
            if (deviceCredentials != null) {
                deviceCredentials.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getOTADetails_args implements TBase<getOTADetails_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getOTADetails_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getOTADetails_argsStandardScheme extends StandardScheme<getOTADetails_args> {
            private getOTADetails_argsStandardScheme() {
            }

            /* synthetic */ getOTADetails_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getOTADetails_args getotadetails_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getotadetails_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getOTADetails_args getotadetails_args) throws TException {
                getotadetails_args.validate();
                tProtocol.writeStructBegin(getOTADetails_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getOTADetails_argsStandardSchemeFactory implements SchemeFactory {
            private getOTADetails_argsStandardSchemeFactory() {
            }

            /* synthetic */ getOTADetails_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getOTADetails_argsStandardScheme mo12846getScheme() {
                return new getOTADetails_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getOTADetails_argsTupleScheme extends TupleScheme<getOTADetails_args> {
            private getOTADetails_argsTupleScheme() {
            }

            /* synthetic */ getOTADetails_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getOTADetails_args getotadetails_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getOTADetails_args getotadetails_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getOTADetails_argsTupleSchemeFactory implements SchemeFactory {
            private getOTADetails_argsTupleSchemeFactory() {
            }

            /* synthetic */ getOTADetails_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getOTADetails_argsTupleScheme mo12846getScheme() {
                return new getOTADetails_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getOTADetails_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getOTADetails_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getOTADetails_args.class, metaDataMap);
        }

        public getOTADetails_args() {
        }

        public getOTADetails_args(getOTADetails_args getotadetails_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getOTADetails_args getotadetails_args) {
            if (!getOTADetails_args.class.equals(getotadetails_args.getClass())) {
                return getOTADetails_args.class.getName().compareTo(getOTADetails_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getOTADetails_args, _Fields> deepCopy2() {
            return new getOTADetails_args(this);
        }

        public boolean equals(getOTADetails_args getotadetails_args) {
            return getotadetails_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getOTADetails_args)) {
                return equals((getOTADetails_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getOTADetails_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getOTADetails_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getOTADetails_result implements TBase<getOTADetails_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public OTADetails success;
        private static final TStruct STRUCT_DESC = new TStruct("getOTADetails_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 12, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getOTADetails_resultStandardScheme extends StandardScheme<getOTADetails_result> {
            private getOTADetails_resultStandardScheme() {
            }

            /* synthetic */ getOTADetails_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getOTADetails_result getotadetails_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getotadetails_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 12) {
                        getotadetails_result.success = new OTADetails();
                        getotadetails_result.success.read(tProtocol);
                        getotadetails_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getOTADetails_result getotadetails_result) throws TException {
                getotadetails_result.validate();
                tProtocol.writeStructBegin(getOTADetails_result.STRUCT_DESC);
                if (getotadetails_result.success != null) {
                    tProtocol.writeFieldBegin(getOTADetails_result.SUCCESS_FIELD_DESC);
                    getotadetails_result.success.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getOTADetails_resultStandardSchemeFactory implements SchemeFactory {
            private getOTADetails_resultStandardSchemeFactory() {
            }

            /* synthetic */ getOTADetails_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getOTADetails_resultStandardScheme mo12846getScheme() {
                return new getOTADetails_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getOTADetails_resultTupleScheme extends TupleScheme<getOTADetails_result> {
            private getOTADetails_resultTupleScheme() {
            }

            /* synthetic */ getOTADetails_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getOTADetails_result getotadetails_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    getotadetails_result.success = new OTADetails();
                    getotadetails_result.success.read(tTupleProtocol);
                    getotadetails_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getOTADetails_result getotadetails_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getotadetails_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getotadetails_result.isSetSuccess()) {
                    getotadetails_result.success.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getOTADetails_resultTupleSchemeFactory implements SchemeFactory {
            private getOTADetails_resultTupleSchemeFactory() {
            }

            /* synthetic */ getOTADetails_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getOTADetails_resultTupleScheme mo12846getScheme() {
                return new getOTADetails_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getOTADetails_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getOTADetails_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new StructMetaData((byte) 12, OTADetails.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getOTADetails_result.class, metaDataMap);
        }

        public getOTADetails_result() {
        }

        public getOTADetails_result(getOTADetails_result getotadetails_result) {
            if (getotadetails_result.isSetSuccess()) {
                this.success = new OTADetails(getotadetails_result.success);
            }
        }

        public getOTADetails_result(OTADetails oTADetails) {
            this();
            this.success = oTADetails;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getOTADetails_result getotadetails_result) {
            int compareTo;
            if (!getOTADetails_result.class.equals(getotadetails_result.getClass())) {
                return getOTADetails_result.class.getName().compareTo(getOTADetails_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getotadetails_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) getotadetails_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getOTADetails_result, _Fields> deepCopy2() {
            return new getOTADetails_result(this);
        }

        public boolean equals(getOTADetails_result getotadetails_result) {
            if (getotadetails_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getotadetails_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getotadetails_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getOTADetails_result)) {
                return equals((getOTADetails_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public OTADetails getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((OTADetails) obj);
            }
        }

        public getOTADetails_result setSuccess(OTADetails oTADetails) {
            this.success = oTADetails;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getOTADetails_result(", "success:");
            OTADetails oTADetails = this.success;
            if (oTADetails == null) {
                outline112.append("null");
            } else {
                outline112.append(oTADetails);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
            OTADetails oTADetails = this.success;
            if (oTADetails != null) {
                oTADetails.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getRegistrationState_args implements TBase<getRegistrationState_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getRegistrationState_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getRegistrationState_argsStandardScheme extends StandardScheme<getRegistrationState_args> {
            private getRegistrationState_argsStandardScheme() {
            }

            /* synthetic */ getRegistrationState_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getRegistrationState_args getregistrationstate_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getregistrationstate_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getRegistrationState_args getregistrationstate_args) throws TException {
                getregistrationstate_args.validate();
                tProtocol.writeStructBegin(getRegistrationState_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getRegistrationState_argsStandardSchemeFactory implements SchemeFactory {
            private getRegistrationState_argsStandardSchemeFactory() {
            }

            /* synthetic */ getRegistrationState_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getRegistrationState_argsStandardScheme mo12846getScheme() {
                return new getRegistrationState_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getRegistrationState_argsTupleScheme extends TupleScheme<getRegistrationState_args> {
            private getRegistrationState_argsTupleScheme() {
            }

            /* synthetic */ getRegistrationState_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getRegistrationState_args getregistrationstate_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getRegistrationState_args getregistrationstate_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getRegistrationState_argsTupleSchemeFactory implements SchemeFactory {
            private getRegistrationState_argsTupleSchemeFactory() {
            }

            /* synthetic */ getRegistrationState_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getRegistrationState_argsTupleScheme mo12846getScheme() {
                return new getRegistrationState_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getRegistrationState_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getRegistrationState_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getRegistrationState_args.class, metaDataMap);
        }

        public getRegistrationState_args() {
        }

        public getRegistrationState_args(getRegistrationState_args getregistrationstate_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getRegistrationState_args getregistrationstate_args) {
            if (!getRegistrationState_args.class.equals(getregistrationstate_args.getClass())) {
                return getRegistrationState_args.class.getName().compareTo(getRegistrationState_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getRegistrationState_args, _Fields> deepCopy2() {
            return new getRegistrationState_args(this);
        }

        public boolean equals(getRegistrationState_args getregistrationstate_args) {
            return getregistrationstate_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getRegistrationState_args)) {
                return equals((getRegistrationState_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getRegistrationState_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getRegistrationState_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getRegistrationState_result implements TBase<getRegistrationState_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public RegistrationState success;
        private static final TStruct STRUCT_DESC = new TStruct("getRegistrationState_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getRegistrationState_resultStandardScheme extends StandardScheme<getRegistrationState_result> {
            private getRegistrationState_resultStandardScheme() {
            }

            /* synthetic */ getRegistrationState_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getRegistrationState_result getregistrationstate_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getregistrationstate_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        getregistrationstate_result.success = RegistrationState.findByValue(tProtocol.readI32());
                        getregistrationstate_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getRegistrationState_result getregistrationstate_result) throws TException {
                getregistrationstate_result.validate();
                tProtocol.writeStructBegin(getRegistrationState_result.STRUCT_DESC);
                if (getregistrationstate_result.success != null) {
                    tProtocol.writeFieldBegin(getRegistrationState_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(getregistrationstate_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getRegistrationState_resultStandardSchemeFactory implements SchemeFactory {
            private getRegistrationState_resultStandardSchemeFactory() {
            }

            /* synthetic */ getRegistrationState_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getRegistrationState_resultStandardScheme mo12846getScheme() {
                return new getRegistrationState_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getRegistrationState_resultTupleScheme extends TupleScheme<getRegistrationState_result> {
            private getRegistrationState_resultTupleScheme() {
            }

            /* synthetic */ getRegistrationState_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getRegistrationState_result getregistrationstate_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    getregistrationstate_result.success = RegistrationState.findByValue(tTupleProtocol.readI32());
                    getregistrationstate_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getRegistrationState_result getregistrationstate_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getregistrationstate_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getregistrationstate_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(getregistrationstate_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getRegistrationState_resultTupleSchemeFactory implements SchemeFactory {
            private getRegistrationState_resultTupleSchemeFactory() {
            }

            /* synthetic */ getRegistrationState_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getRegistrationState_resultTupleScheme mo12846getScheme() {
                return new getRegistrationState_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getRegistrationState_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getRegistrationState_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, RegistrationState.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getRegistrationState_result.class, metaDataMap);
        }

        public getRegistrationState_result() {
        }

        public getRegistrationState_result(getRegistrationState_result getregistrationstate_result) {
            if (getregistrationstate_result.isSetSuccess()) {
                this.success = getregistrationstate_result.success;
            }
        }

        public getRegistrationState_result(RegistrationState registrationState) {
            this();
            this.success = registrationState;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getRegistrationState_result getregistrationstate_result) {
            int compareTo;
            if (!getRegistrationState_result.class.equals(getregistrationstate_result.getClass())) {
                return getRegistrationState_result.class.getName().compareTo(getRegistrationState_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getregistrationstate_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) getregistrationstate_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getRegistrationState_result, _Fields> deepCopy2() {
            return new getRegistrationState_result(this);
        }

        public boolean equals(getRegistrationState_result getregistrationstate_result) {
            if (getregistrationstate_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getregistrationstate_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getregistrationstate_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getRegistrationState_result)) {
                return equals((getRegistrationState_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public RegistrationState getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((RegistrationState) obj);
            }
        }

        public getRegistrationState_result setSuccess(RegistrationState registrationState) {
            this.success = registrationState;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getRegistrationState_result(", "success:");
            RegistrationState registrationState = this.success;
            if (registrationState == null) {
                outline112.append("null");
            } else {
                outline112.append(registrationState);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getScanList_args implements TBase<getScanList_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("getScanList_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getScanList_argsStandardScheme extends StandardScheme<getScanList_args> {
            private getScanList_argsStandardScheme() {
            }

            /* synthetic */ getScanList_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getScanList_args getscanlist_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getscanlist_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getScanList_args getscanlist_args) throws TException {
                getscanlist_args.validate();
                tProtocol.writeStructBegin(getScanList_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getScanList_argsStandardSchemeFactory implements SchemeFactory {
            private getScanList_argsStandardSchemeFactory() {
            }

            /* synthetic */ getScanList_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getScanList_argsStandardScheme mo12846getScheme() {
                return new getScanList_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getScanList_argsTupleScheme extends TupleScheme<getScanList_args> {
            private getScanList_argsTupleScheme() {
            }

            /* synthetic */ getScanList_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getScanList_args getscanlist_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getScanList_args getscanlist_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class getScanList_argsTupleSchemeFactory implements SchemeFactory {
            private getScanList_argsTupleSchemeFactory() {
            }

            /* synthetic */ getScanList_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getScanList_argsTupleScheme mo12846getScheme() {
                return new getScanList_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getScanList_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getScanList_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(getScanList_args.class, metaDataMap);
        }

        public getScanList_args() {
        }

        public getScanList_args(getScanList_args getscanlist_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(getScanList_args getscanlist_args) {
            if (!getScanList_args.class.equals(getscanlist_args.getClass())) {
                return getScanList_args.class.getName().compareTo(getScanList_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getScanList_args, _Fields> deepCopy2() {
            return new getScanList_args(this);
        }

        public boolean equals(getScanList_args getscanlist_args) {
            return getscanlist_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getScanList_args)) {
                return equals((getScanList_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$getScanList_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "getScanList_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class getScanList_result implements TBase<getScanList_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public List<APDetail> success;
        private static final TStruct STRUCT_DESC = new TStruct("getScanList_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 15, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getScanList_resultStandardScheme extends StandardScheme<getScanList_result> {
            private getScanList_resultStandardScheme() {
            }

            /* synthetic */ getScanList_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getScanList_result getscanlist_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        getscanlist_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 15) {
                        TList readListBegin = tProtocol.readListBegin();
                        getscanlist_result.success = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            APDetail aPDetail = new APDetail();
                            aPDetail.read(tProtocol);
                            getscanlist_result.success.add(aPDetail);
                        }
                        tProtocol.readListEnd();
                        getscanlist_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getScanList_result getscanlist_result) throws TException {
                getscanlist_result.validate();
                tProtocol.writeStructBegin(getScanList_result.STRUCT_DESC);
                if (getscanlist_result.success != null) {
                    tProtocol.writeFieldBegin(getScanList_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeListBegin(new TList((byte) 12, getscanlist_result.success.size()));
                    for (APDetail aPDetail : getscanlist_result.success) {
                        aPDetail.write(tProtocol);
                    }
                    tProtocol.writeListEnd();
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class getScanList_resultStandardSchemeFactory implements SchemeFactory {
            private getScanList_resultStandardSchemeFactory() {
            }

            /* synthetic */ getScanList_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getScanList_resultStandardScheme mo12846getScheme() {
                return new getScanList_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class getScanList_resultTupleScheme extends TupleScheme<getScanList_result> {
            private getScanList_resultTupleScheme() {
            }

            /* synthetic */ getScanList_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, getScanList_result getscanlist_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    TList tList = new TList((byte) 12, tTupleProtocol.readI32());
                    getscanlist_result.success = new ArrayList(tList.size);
                    for (int i = 0; i < tList.size; i++) {
                        APDetail aPDetail = new APDetail();
                        aPDetail.read(tTupleProtocol);
                        getscanlist_result.success.add(aPDetail);
                    }
                    getscanlist_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, getScanList_result getscanlist_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (getscanlist_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (getscanlist_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(getscanlist_result.success.size());
                    for (APDetail aPDetail : getscanlist_result.success) {
                        aPDetail.write(tTupleProtocol);
                    }
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class getScanList_resultTupleSchemeFactory implements SchemeFactory {
            private getScanList_resultTupleSchemeFactory() {
            }

            /* synthetic */ getScanList_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public getScanList_resultTupleScheme mo12846getScheme() {
                return new getScanList_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new getScanList_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new getScanList_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new ListMetaData((byte) 15, new StructMetaData((byte) 12, APDetail.class))));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(getScanList_result.class, metaDataMap);
        }

        public getScanList_result() {
        }

        public getScanList_result(getScanList_result getscanlist_result) {
            if (getscanlist_result.isSetSuccess()) {
                ArrayList arrayList = new ArrayList();
                for (APDetail aPDetail : getscanlist_result.success) {
                    arrayList.add(new APDetail(aPDetail));
                }
                this.success = arrayList;
            }
        }

        public getScanList_result(List<APDetail> list) {
            this();
            this.success = list;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        public void addToSuccess(APDetail aPDetail) {
            if (this.success == null) {
                this.success = new ArrayList();
            }
            this.success.add(aPDetail);
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(getScanList_result getscanlist_result) {
            int compareTo;
            if (!getScanList_result.class.equals(getscanlist_result.getClass())) {
                return getScanList_result.class.getName().compareTo(getScanList_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(getscanlist_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((List) this.success, (List) getscanlist_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<getScanList_result, _Fields> deepCopy2() {
            return new getScanList_result(this);
        }

        public boolean equals(getScanList_result getscanlist_result) {
            if (getscanlist_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = getscanlist_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(getscanlist_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof getScanList_result)) {
                return equals((getScanList_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public List<APDetail> getSuccess() {
            return this.success;
        }

        public Iterator<APDetail> getSuccessIterator() {
            List<APDetail> list = this.success;
            if (list == null) {
                return null;
            }
            return list.iterator();
        }

        public int getSuccessSize() {
            List<APDetail> list = this.success;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((List) obj);
            }
        }

        public getScanList_result setSuccess(List<APDetail> list) {
            this.success = list;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("getScanList_result(", "success:");
            List<APDetail> list = this.success;
            if (list == null) {
                outline112.append("null");
            } else {
                outline112.append(list);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class ping_args implements TBase<ping_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("ping_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class ping_argsStandardScheme extends StandardScheme<ping_args> {
            private ping_argsStandardScheme() {
            }

            /* synthetic */ ping_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, ping_args ping_argsVar) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        ping_argsVar.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, ping_args ping_argsVar) throws TException {
                ping_argsVar.validate();
                tProtocol.writeStructBegin(ping_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class ping_argsStandardSchemeFactory implements SchemeFactory {
            private ping_argsStandardSchemeFactory() {
            }

            /* synthetic */ ping_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public ping_argsStandardScheme mo12846getScheme() {
                return new ping_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class ping_argsTupleScheme extends TupleScheme<ping_args> {
            private ping_argsTupleScheme() {
            }

            /* synthetic */ ping_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, ping_args ping_argsVar) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, ping_args ping_argsVar) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class ping_argsTupleSchemeFactory implements SchemeFactory {
            private ping_argsTupleSchemeFactory() {
            }

            /* synthetic */ ping_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public ping_argsTupleScheme mo12846getScheme() {
                return new ping_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new ping_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new ping_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(ping_args.class, metaDataMap);
        }

        public ping_args() {
        }

        public ping_args(ping_args ping_argsVar) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(ping_args ping_argsVar) {
            if (!ping_args.class.equals(ping_argsVar.getClass())) {
                return ping_args.class.getName().compareTo(ping_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<ping_args, _Fields> deepCopy2() {
            return new ping_args(this);
        }

        public boolean equals(ping_args ping_argsVar) {
            return ping_argsVar != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof ping_args)) {
                return equals((ping_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$ping_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "ping_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class ping_result implements TBase<ping_result, _Fields>, Serializable, Cloneable {
        private static final int __SUCCESS_ISSET_ID = 0;
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private byte __isset_bitfield;
        public boolean success;
        private static final TStruct STRUCT_DESC = new TStruct("ping_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 2, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class ping_resultStandardScheme extends StandardScheme<ping_result> {
            private ping_resultStandardScheme() {
            }

            /* synthetic */ ping_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, ping_result ping_resultVar) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        ping_resultVar.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 2) {
                        ping_resultVar.success = tProtocol.readBool();
                        ping_resultVar.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, ping_result ping_resultVar) throws TException {
                ping_resultVar.validate();
                tProtocol.writeStructBegin(ping_result.STRUCT_DESC);
                if (ping_resultVar.isSetSuccess()) {
                    tProtocol.writeFieldBegin(ping_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeBool(ping_resultVar.success);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class ping_resultStandardSchemeFactory implements SchemeFactory {
            private ping_resultStandardSchemeFactory() {
            }

            /* synthetic */ ping_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public ping_resultStandardScheme mo12846getScheme() {
                return new ping_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class ping_resultTupleScheme extends TupleScheme<ping_result> {
            private ping_resultTupleScheme() {
            }

            /* synthetic */ ping_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, ping_result ping_resultVar) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    ping_resultVar.success = tTupleProtocol.readBool();
                    ping_resultVar.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, ping_result ping_resultVar) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (ping_resultVar.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (ping_resultVar.isSetSuccess()) {
                    tTupleProtocol.writeBool(ping_resultVar.success);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class ping_resultTupleSchemeFactory implements SchemeFactory {
            private ping_resultTupleSchemeFactory() {
            }

            /* synthetic */ ping_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public ping_resultTupleScheme mo12846getScheme() {
                return new ping_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new ping_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new ping_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new FieldValueMetaData((byte) 2)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(ping_result.class, metaDataMap);
        }

        public ping_result() {
            this.__isset_bitfield = (byte) 0;
        }

        public ping_result(ping_result ping_resultVar) {
            this.__isset_bitfield = (byte) 0;
            this.__isset_bitfield = ping_resultVar.__isset_bitfield;
            this.success = ping_resultVar.success;
        }

        public ping_result(boolean z) {
            this();
            this.success = z;
            setSuccessIsSet(true);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                this.__isset_bitfield = (byte) 0;
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            setSuccessIsSet(false);
            this.success = false;
        }

        @Override // java.lang.Comparable
        public int compareTo(ping_result ping_resultVar) {
            int compareTo;
            if (!ping_result.class.equals(ping_resultVar.getClass())) {
                return ping_result.class.getName().compareTo(ping_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(ping_resultVar.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo(this.success, ping_resultVar.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<ping_result, _Fields> deepCopy2() {
            return new ping_result(this);
        }

        public boolean equals(ping_result ping_resultVar) {
            return ping_resultVar != null && this.success == ping_resultVar.success;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof ping_result)) {
                return equals((ping_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return Boolean.valueOf(isSuccess());
            }
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return EncodingUtils.testBit(this.__isset_bitfield, 0);
        }

        public boolean isSuccess() {
            return this.success;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess(((Boolean) obj).booleanValue());
            }
        }

        public ping_result setSuccess(boolean z) {
            this.success = z;
            setSuccessIsSet(true);
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline112("ping_result(", "success:"), this.success, ")");
        }

        public void unsetSuccess() {
            this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class setLocaleAndEndpoints_args implements TBase<setLocaleAndEndpoints_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public LocaleAndEndpointInfo lo;
        private static final TStruct STRUCT_DESC = new TStruct("setLocaleAndEndpoints_args");
        private static final TField LO_FIELD_DESC = new TField("lo", (byte) 12, 1);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            LO(1, "lo");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 1) {
                    return null;
                }
                return LO;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints_argsStandardScheme extends StandardScheme<setLocaleAndEndpoints_args> {
            private setLocaleAndEndpoints_argsStandardScheme() {
            }

            /* synthetic */ setLocaleAndEndpoints_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        setlocaleandendpoints_args.validate();
                        return;
                    }
                    if (readFieldBegin.id == 1 && b == 12) {
                        setlocaleandendpoints_args.lo = new LocaleAndEndpointInfo();
                        setlocaleandendpoints_args.lo.read(tProtocol);
                        setlocaleandendpoints_args.setLoIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                setlocaleandendpoints_args.validate();
                tProtocol.writeStructBegin(setLocaleAndEndpoints_args.STRUCT_DESC);
                if (setlocaleandendpoints_args.lo != null) {
                    tProtocol.writeFieldBegin(setLocaleAndEndpoints_args.LO_FIELD_DESC);
                    setlocaleandendpoints_args.lo.write(tProtocol);
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class setLocaleAndEndpoints_argsStandardSchemeFactory implements SchemeFactory {
            private setLocaleAndEndpoints_argsStandardSchemeFactory() {
            }

            /* synthetic */ setLocaleAndEndpoints_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setLocaleAndEndpoints_argsStandardScheme mo12846getScheme() {
                return new setLocaleAndEndpoints_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints_argsTupleScheme extends TupleScheme<setLocaleAndEndpoints_args> {
            private setLocaleAndEndpoints_argsTupleScheme() {
            }

            /* synthetic */ setLocaleAndEndpoints_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    setlocaleandendpoints_args.lo = new LocaleAndEndpointInfo();
                    setlocaleandendpoints_args.lo.read(tTupleProtocol);
                    setlocaleandendpoints_args.setLoIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setLocaleAndEndpoints_args setlocaleandendpoints_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (setlocaleandendpoints_args.isSetLo()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (setlocaleandendpoints_args.isSetLo()) {
                    setlocaleandendpoints_args.lo.write(tTupleProtocol);
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class setLocaleAndEndpoints_argsTupleSchemeFactory implements SchemeFactory {
            private setLocaleAndEndpoints_argsTupleSchemeFactory() {
            }

            /* synthetic */ setLocaleAndEndpoints_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setLocaleAndEndpoints_argsTupleScheme mo12846getScheme() {
                return new setLocaleAndEndpoints_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new setLocaleAndEndpoints_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new setLocaleAndEndpoints_argsTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.LO, (_Fields) new FieldMetaData("lo", (byte) 3, new StructMetaData((byte) 12, LocaleAndEndpointInfo.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(setLocaleAndEndpoints_args.class, metaDataMap);
        }

        public setLocaleAndEndpoints_args() {
        }

        public setLocaleAndEndpoints_args(setLocaleAndEndpoints_args setlocaleandendpoints_args) {
            if (setlocaleandendpoints_args.isSetLo()) {
                this.lo = new LocaleAndEndpointInfo(setlocaleandendpoints_args.lo);
            }
        }

        public setLocaleAndEndpoints_args(LocaleAndEndpointInfo localeAndEndpointInfo) {
            this();
            this.lo = localeAndEndpointInfo;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.lo = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(setLocaleAndEndpoints_args setlocaleandendpoints_args) {
            int compareTo;
            if (!setLocaleAndEndpoints_args.class.equals(setlocaleandendpoints_args.getClass())) {
                return setLocaleAndEndpoints_args.class.getName().compareTo(setLocaleAndEndpoints_args.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetLo()).compareTo(Boolean.valueOf(setlocaleandendpoints_args.isSetLo()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetLo() && (compareTo = TBaseHelper.compareTo((Comparable) this.lo, (Comparable) setlocaleandendpoints_args.lo)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<setLocaleAndEndpoints_args, _Fields> deepCopy2() {
            return new setLocaleAndEndpoints_args(this);
        }

        public boolean equals(setLocaleAndEndpoints_args setlocaleandendpoints_args) {
            if (setlocaleandendpoints_args == null) {
                return false;
            }
            boolean isSetLo = isSetLo();
            boolean isSetLo2 = setlocaleandendpoints_args.isSetLo();
            if (!isSetLo && !isSetLo2) {
                return true;
            }
            return isSetLo && isSetLo2 && this.lo.equals(setlocaleandendpoints_args.lo);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof setLocaleAndEndpoints_args)) {
                return equals((setLocaleAndEndpoints_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getLo();
            }
            throw new IllegalStateException();
        }

        public LocaleAndEndpointInfo getLo() {
            return this.lo;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetLo();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetLo() {
            return this.lo != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetLo();
            } else {
                setLo((LocaleAndEndpointInfo) obj);
            }
        }

        public setLocaleAndEndpoints_args setLo(LocaleAndEndpointInfo localeAndEndpointInfo) {
            this.lo = localeAndEndpointInfo;
            return this;
        }

        public void setLoIsSet(boolean z) {
            if (!z) {
                this.lo = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("setLocaleAndEndpoints_args(", "lo:");
            LocaleAndEndpointInfo localeAndEndpointInfo = this.lo;
            if (localeAndEndpointInfo == null) {
                outline112.append("null");
            } else {
                outline112.append(localeAndEndpointInfo);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetLo() {
            this.lo = null;
        }

        public void validate() throws TException {
            LocaleAndEndpointInfo localeAndEndpointInfo = this.lo;
            if (localeAndEndpointInfo != null) {
                localeAndEndpointInfo.validate();
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class setLocaleAndEndpoints_result implements TBase<setLocaleAndEndpoints_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("setLocaleAndEndpoints_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints_resultStandardScheme extends StandardScheme<setLocaleAndEndpoints_result> {
            private setLocaleAndEndpoints_resultStandardScheme() {
            }

            /* synthetic */ setLocaleAndEndpoints_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setLocaleAndEndpoints_result setlocaleandendpoints_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        setlocaleandendpoints_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        setlocaleandendpoints_result.success = ReturnError.findByValue(tProtocol.readI32());
                        setlocaleandendpoints_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setLocaleAndEndpoints_result setlocaleandendpoints_result) throws TException {
                setlocaleandendpoints_result.validate();
                tProtocol.writeStructBegin(setLocaleAndEndpoints_result.STRUCT_DESC);
                if (setlocaleandendpoints_result.success != null) {
                    tProtocol.writeFieldBegin(setLocaleAndEndpoints_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(setlocaleandendpoints_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class setLocaleAndEndpoints_resultStandardSchemeFactory implements SchemeFactory {
            private setLocaleAndEndpoints_resultStandardSchemeFactory() {
            }

            /* synthetic */ setLocaleAndEndpoints_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setLocaleAndEndpoints_resultStandardScheme mo12846getScheme() {
                return new setLocaleAndEndpoints_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setLocaleAndEndpoints_resultTupleScheme extends TupleScheme<setLocaleAndEndpoints_result> {
            private setLocaleAndEndpoints_resultTupleScheme() {
            }

            /* synthetic */ setLocaleAndEndpoints_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setLocaleAndEndpoints_result setlocaleandendpoints_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    setlocaleandendpoints_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    setlocaleandendpoints_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setLocaleAndEndpoints_result setlocaleandendpoints_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (setlocaleandendpoints_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (setlocaleandendpoints_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(setlocaleandendpoints_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class setLocaleAndEndpoints_resultTupleSchemeFactory implements SchemeFactory {
            private setLocaleAndEndpoints_resultTupleSchemeFactory() {
            }

            /* synthetic */ setLocaleAndEndpoints_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setLocaleAndEndpoints_resultTupleScheme mo12846getScheme() {
                return new setLocaleAndEndpoints_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new setLocaleAndEndpoints_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new setLocaleAndEndpoints_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(setLocaleAndEndpoints_result.class, metaDataMap);
        }

        public setLocaleAndEndpoints_result() {
        }

        public setLocaleAndEndpoints_result(setLocaleAndEndpoints_result setlocaleandendpoints_result) {
            if (setlocaleandendpoints_result.isSetSuccess()) {
                this.success = setlocaleandendpoints_result.success;
            }
        }

        public setLocaleAndEndpoints_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(setLocaleAndEndpoints_result setlocaleandendpoints_result) {
            int compareTo;
            if (!setLocaleAndEndpoints_result.class.equals(setlocaleandendpoints_result.getClass())) {
                return setLocaleAndEndpoints_result.class.getName().compareTo(setLocaleAndEndpoints_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(setlocaleandendpoints_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) setlocaleandendpoints_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<setLocaleAndEndpoints_result, _Fields> deepCopy2() {
            return new setLocaleAndEndpoints_result(this);
        }

        public boolean equals(setLocaleAndEndpoints_result setlocaleandendpoints_result) {
            if (setlocaleandendpoints_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = setlocaleandendpoints_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(setlocaleandendpoints_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof setLocaleAndEndpoints_result)) {
                return equals((setLocaleAndEndpoints_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public setLocaleAndEndpoints_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("setLocaleAndEndpoints_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class setupComplete_args implements TBase<setupComplete_args, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        private static final TStruct STRUCT_DESC = new TStruct("setupComplete_args");
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            ;
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                return null;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setupComplete_argsStandardScheme extends StandardScheme<setupComplete_args> {
            private setupComplete_argsStandardScheme() {
            }

            /* synthetic */ setupComplete_argsStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setupComplete_args setupcomplete_args) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        setupcomplete_args.validate();
                        return;
                    }
                    short s = readFieldBegin.id;
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setupComplete_args setupcomplete_args) throws TException {
                setupcomplete_args.validate();
                tProtocol.writeStructBegin(setupComplete_args.STRUCT_DESC);
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class setupComplete_argsStandardSchemeFactory implements SchemeFactory {
            private setupComplete_argsStandardSchemeFactory() {
            }

            /* synthetic */ setupComplete_argsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setupComplete_argsStandardScheme mo12846getScheme() {
                return new setupComplete_argsStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setupComplete_argsTupleScheme extends TupleScheme<setupComplete_args> {
            private setupComplete_argsTupleScheme() {
            }

            /* synthetic */ setupComplete_argsTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setupComplete_args setupcomplete_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setupComplete_args setupcomplete_args) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            }
        }

        /* loaded from: classes12.dex */
        private static class setupComplete_argsTupleSchemeFactory implements SchemeFactory {
            private setupComplete_argsTupleSchemeFactory() {
            }

            /* synthetic */ setupComplete_argsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setupComplete_argsTupleScheme mo12846getScheme() {
                return new setupComplete_argsTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new setupComplete_argsStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new setupComplete_argsTupleSchemeFactory(null));
            metaDataMap = Collections.unmodifiableMap(new EnumMap(_Fields.class));
            FieldMetaData.addStructMetaDataMap(setupComplete_args.class, metaDataMap);
        }

        public setupComplete_args() {
        }

        public setupComplete_args(setupComplete_args setupcomplete_args) {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
        }

        @Override // java.lang.Comparable
        public int compareTo(setupComplete_args setupcomplete_args) {
            if (!setupComplete_args.class.equals(setupcomplete_args.getClass())) {
                return setupComplete_args.class.getName().compareTo(setupComplete_args.class.getName());
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<setupComplete_args, _Fields> deepCopy2() {
            return new setupComplete_args(this);
        }

        public boolean equals(setupComplete_args setupcomplete_args) {
            return setupcomplete_args != null;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof setupComplete_args)) {
                return equals((setupComplete_args) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields == null) {
                throw new IllegalArgumentException();
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_args$_Fields[_fields.ordinal()];
            throw new IllegalStateException();
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$setup$thrift$DopplerOOBE$setupComplete_args$_Fields[_fields.ordinal()];
        }

        public String toString() {
            return "setupComplete_args()";
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }

    /* loaded from: classes12.dex */
    public static class setupComplete_result implements TBase<setupComplete_result, _Fields>, Serializable, Cloneable {
        public static final Map<_Fields, FieldMetaData> metaDataMap;
        public ReturnError success;
        private static final TStruct STRUCT_DESC = new TStruct("setupComplete_result");
        private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte) 8, 0);
        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

        /* loaded from: classes12.dex */
        public enum _Fields implements TFieldIdEnum {
            SUCCESS(0, "success");
            
            private static final Map<String, _Fields> byName = new HashMap();
            private final String _fieldName;
            private final short _thriftId;

            static {
                Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
                while (it2.hasNext()) {
                    _Fields _fields = (_Fields) it2.next();
                    byName.put(_fields.getFieldName(), _fields);
                }
            }

            _Fields(short s, String str) {
                this._thriftId = s;
                this._fieldName = str;
            }

            public static _Fields findByName(String str) {
                return byName.get(str);
            }

            public static _Fields findByThriftId(int i) {
                if (i != 0) {
                    return null;
                }
                return SUCCESS;
            }

            public static _Fields findByThriftIdOrThrow(int i) {
                _Fields findByThriftId = findByThriftId(i);
                if (findByThriftId != null) {
                    return findByThriftId;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public String getFieldName() {
                return this._fieldName;
            }

            @Override // org.apache.thrift.orig.TFieldIdEnum
            public short getThriftFieldId() {
                return this._thriftId;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setupComplete_resultStandardScheme extends StandardScheme<setupComplete_result> {
            private setupComplete_resultStandardScheme() {
            }

            /* synthetic */ setupComplete_resultStandardScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setupComplete_result setupcomplete_result) throws TException {
                tProtocol.readStructBegin();
                while (true) {
                    TField readFieldBegin = tProtocol.readFieldBegin();
                    byte b = readFieldBegin.type;
                    if (b == 0) {
                        tProtocol.readStructEnd();
                        setupcomplete_result.validate();
                        return;
                    }
                    if (readFieldBegin.id == 0 && b == 8) {
                        setupcomplete_result.success = ReturnError.findByValue(tProtocol.readI32());
                        setupcomplete_result.setSuccessIsSet(true);
                    } else {
                        TProtocolUtil.skip(tProtocol, b);
                    }
                    tProtocol.readFieldEnd();
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setupComplete_result setupcomplete_result) throws TException {
                setupcomplete_result.validate();
                tProtocol.writeStructBegin(setupComplete_result.STRUCT_DESC);
                if (setupcomplete_result.success != null) {
                    tProtocol.writeFieldBegin(setupComplete_result.SUCCESS_FIELD_DESC);
                    tProtocol.writeI32(setupcomplete_result.success.getValue());
                    tProtocol.writeFieldEnd();
                }
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
            }
        }

        /* loaded from: classes12.dex */
        private static class setupComplete_resultStandardSchemeFactory implements SchemeFactory {
            private setupComplete_resultStandardSchemeFactory() {
            }

            /* synthetic */ setupComplete_resultStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setupComplete_resultStandardScheme mo12846getScheme() {
                return new setupComplete_resultStandardScheme(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class setupComplete_resultTupleScheme extends TupleScheme<setupComplete_result> {
            private setupComplete_resultTupleScheme() {
            }

            /* synthetic */ setupComplete_resultTupleScheme(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void read(TProtocol tProtocol, setupComplete_result setupcomplete_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                if (tTupleProtocol.readBitSet(1).get(0)) {
                    setupcomplete_result.success = ReturnError.findByValue(tTupleProtocol.readI32());
                    setupcomplete_result.setSuccessIsSet(true);
                }
            }

            @Override // org.apache.thrift.orig.scheme.IScheme
            public void write(TProtocol tProtocol, setupComplete_result setupcomplete_result) throws TException {
                TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
                BitSet bitSet = new BitSet();
                if (setupcomplete_result.isSetSuccess()) {
                    bitSet.set(0);
                }
                tTupleProtocol.writeBitSet(bitSet, 1);
                if (setupcomplete_result.isSetSuccess()) {
                    tTupleProtocol.writeI32(setupcomplete_result.success.getValue());
                }
            }
        }

        /* loaded from: classes12.dex */
        private static class setupComplete_resultTupleSchemeFactory implements SchemeFactory {
            private setupComplete_resultTupleSchemeFactory() {
            }

            /* synthetic */ setupComplete_resultTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // org.apache.thrift.orig.scheme.SchemeFactory
            /* renamed from: getScheme */
            public setupComplete_resultTupleScheme mo12846getScheme() {
                return new setupComplete_resultTupleScheme(null);
            }
        }

        static {
            schemes.put(StandardScheme.class, new setupComplete_resultStandardSchemeFactory(null));
            schemes.put(TupleScheme.class, new setupComplete_resultTupleSchemeFactory(null));
            EnumMap enumMap = new EnumMap(_Fields.class);
            enumMap.put((EnumMap) _Fields.SUCCESS, (_Fields) new FieldMetaData("success", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
            metaDataMap = Collections.unmodifiableMap(enumMap);
            FieldMetaData.addStructMetaDataMap(setupComplete_result.class, metaDataMap);
        }

        public setupComplete_result() {
        }

        public setupComplete_result(setupComplete_result setupcomplete_result) {
            if (setupcomplete_result.isSetSuccess()) {
                this.success = setupcomplete_result.success;
            }
        }

        public setupComplete_result(ReturnError returnError) {
            this();
            this.success = returnError;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            try {
                read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            try {
                write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
            } catch (TException e) {
                throw new IOException(e);
            }
        }

        @Override // org.apache.thrift.orig.TBase
        public void clear() {
            this.success = null;
        }

        @Override // java.lang.Comparable
        public int compareTo(setupComplete_result setupcomplete_result) {
            int compareTo;
            if (!setupComplete_result.class.equals(setupcomplete_result.getClass())) {
                return setupComplete_result.class.getName().compareTo(setupComplete_result.class.getName());
            }
            int compareTo2 = Boolean.valueOf(isSetSuccess()).compareTo(Boolean.valueOf(setupcomplete_result.isSetSuccess()));
            if (compareTo2 != 0) {
                return compareTo2;
            }
            if (isSetSuccess() && (compareTo = TBaseHelper.compareTo((Comparable) this.success, (Comparable) setupcomplete_result.success)) != 0) {
                return compareTo;
            }
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        /* renamed from: deepCopy */
        public TBase<setupComplete_result, _Fields> deepCopy2() {
            return new setupComplete_result(this);
        }

        public boolean equals(setupComplete_result setupcomplete_result) {
            if (setupcomplete_result == null) {
                return false;
            }
            boolean isSetSuccess = isSetSuccess();
            boolean isSetSuccess2 = setupcomplete_result.isSetSuccess();
            if (!isSetSuccess && !isSetSuccess2) {
                return true;
            }
            return isSetSuccess && isSetSuccess2 && this.success.equals(setupcomplete_result.success);
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof setupComplete_result)) {
                return equals((setupComplete_result) obj);
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.thrift.orig.TBase
        /* renamed from: fieldForId */
        public _Fields mo3968fieldForId(int i) {
            return _Fields.findByThriftId(i);
        }

        @Override // org.apache.thrift.orig.TBase
        public Object getFieldValue(_Fields _fields) {
            if (_fields.ordinal() == 0) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        public ReturnError getSuccess() {
            return this.success;
        }

        public int hashCode() {
            return 0;
        }

        @Override // org.apache.thrift.orig.TBase
        public boolean isSet(_Fields _fields) {
            if (_fields != null) {
                if (_fields.ordinal() != 0) {
                    throw new IllegalStateException();
                }
                return isSetSuccess();
            }
            throw new IllegalArgumentException();
        }

        public boolean isSetSuccess() {
            return this.success != null;
        }

        @Override // org.apache.thrift.orig.TBase
        public void read(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
        }

        @Override // org.apache.thrift.orig.TBase
        public void setFieldValue(_Fields _fields, Object obj) {
            if (_fields.ordinal() != 0) {
                return;
            }
            if (obj == null) {
                unsetSuccess();
            } else {
                setSuccess((ReturnError) obj);
            }
        }

        public setupComplete_result setSuccess(ReturnError returnError) {
            this.success = returnError;
            return this;
        }

        public void setSuccessIsSet(boolean z) {
            if (!z) {
                this.success = null;
            }
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("setupComplete_result(", "success:");
            ReturnError returnError = this.success;
            if (returnError == null) {
                outline112.append("null");
            } else {
                outline112.append(returnError);
            }
            outline112.append(")");
            return outline112.toString();
        }

        public void unsetSuccess() {
            this.success = null;
        }

        public void validate() throws TException {
        }

        @Override // org.apache.thrift.orig.TBase
        public void write(TProtocol tProtocol) throws TException {
            schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
        }
    }
}
