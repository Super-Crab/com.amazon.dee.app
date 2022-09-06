package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.AbstractC0183Xff;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.AbstractC0209ibG;
import com.amazon.alexa.AbstractC0229sSp;
import com.amazon.alexa.Agi;
import com.amazon.alexa.Alc;
import com.amazon.alexa.BGK;
import com.amazon.alexa.BIn;
import com.amazon.alexa.Bfv;
import com.amazon.alexa.Bsa;
import com.amazon.alexa.CAj;
import com.amazon.alexa.CiJ;
import com.amazon.alexa.DEe;
import com.amazon.alexa.DRc;
import com.amazon.alexa.DWt;
import com.amazon.alexa.Ejh;
import com.amazon.alexa.ErD;
import com.amazon.alexa.GhS;
import com.amazon.alexa.GkO;
import com.amazon.alexa.Gkq;
import com.amazon.alexa.Gzu;
import com.amazon.alexa.HYG;
import com.amazon.alexa.HbJ;
import com.amazon.alexa.HkJ;
import com.amazon.alexa.IKe;
import com.amazon.alexa.IUU;
import com.amazon.alexa.Iab;
import com.amazon.alexa.Ims;
import com.amazon.alexa.JGr;
import com.amazon.alexa.JTD;
import com.amazon.alexa.Jqr;
import com.amazon.alexa.KLX;
import com.amazon.alexa.Kyp;
import com.amazon.alexa.LWv;
import com.amazon.alexa.LZG;
import com.amazon.alexa.MOI;
import com.amazon.alexa.NYz;
import com.amazon.alexa.Nwd;
import com.amazon.alexa.PMk;
import com.amazon.alexa.PcE;
import com.amazon.alexa.Qds;
import com.amazon.alexa.Rgi;
import com.amazon.alexa.RhW;
import com.amazon.alexa.SOo;
import com.amazon.alexa.STS;
import com.amazon.alexa.Sas;
import com.amazon.alexa.StC;
import com.amazon.alexa.Suv;
import com.amazon.alexa.URU;
import com.amazon.alexa.UTs;
import com.amazon.alexa.UVo;
import com.amazon.alexa.VCD;
import com.amazon.alexa.Vma;
import com.amazon.alexa.WlR;
import com.amazon.alexa.Xdr;
import com.amazon.alexa.YiP;
import com.amazon.alexa.aNh;
import com.amazon.alexa.cMY;
import com.amazon.alexa.cVW;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_AudioItem;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackFailedEventPayload_Error;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_ProgressReport;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_Stream;
import com.amazon.alexa.client.alexaservice.capabilities.AutoValue_CapabilityPublishRequest;
import com.amazon.alexa.client.alexaservice.capabilities.v2.AutoValue_ExternalCapabilityAgentRegistrationData;
import com.amazon.alexa.client.alexaservice.capabilities.v2.AutoValue_ExternalCapabilityAgentRegistrationRawData;
import com.amazon.alexa.client.alexaservice.cardrenderer.payload.AutoValue_AlexaImage;
import com.amazon.alexa.client.alexaservice.cardrenderer.payload.AutoValue_PlayerInfoPayload_InfoText;
import com.amazon.alexa.client.alexaservice.cardrenderer.payload.AutoValue_PlayerInfoPayload_Progress;
import com.amazon.alexa.client.alexaservice.cardrenderer.payload.AutoValue_PlayerInfoPayload_Template;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerCall;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerCallInfo;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerConfiguration;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerDevice;
import com.amazon.alexa.client.alexaservice.componentstate.AutoValue_ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.display.window.AutoValue_Window;
import com.amazon.alexa.client.alexaservice.display.window.AutoValue_WindowConfiguration;
import com.amazon.alexa.client.alexaservice.eventing.events.AutoValue_AlexaUserSpeechVolumeChangedEvent;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerError;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerStructure;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizationCompletePayload_Authorized;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizationCompletePayload_Deauthorized;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizeDiscoveredPlayersPayload_Player;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_CoverUrls;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ExternalMediaPlayerMetadata;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_Player;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerRuntimeState;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ReportDiscoveredPlayersPayload_Player;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_AlexaLocation;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Altitude;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Coordinate;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Heading;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_LocationServices;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Speed;
import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.interactions.VisualActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.interactions.channels.AutoValue_ChannelState;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_ClusterDevice;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_Connection;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_DeviceInfo;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_IOComponent;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_IOComponentContext;
import com.amazon.alexa.client.alexaservice.iocomponent.AutoValue_SessionState;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_EventTarget;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_LaunchConfig;
import com.amazon.alexa.client.alexaservice.launcher.payload.AutoValue_Target;
import com.amazon.alexa.client.alexaservice.navigation.AutoValue_SetDestinationPayload_Destination;
import com.amazon.alexa.client.alexaservice.navigation.AutoValue_SetDestinationPayload_Destination_Coordinate;
import com.amazon.alexa.client.alexaservice.settings.AutoValue_Setting;
import com.amazon.alexa.client.alexaservice.settings.AutoValue_SupportsMobileDownchannelSetting;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordIndices;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordInitiator;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordInitiatorPayload;
import com.amazon.alexa.client.alexaservice.system.payload.AutoValue_ExceptionEncounteredPayload_Error;
import com.amazon.alexa.dEA;
import com.amazon.alexa.fcj;
import com.amazon.alexa.gUg;
import com.amazon.alexa.hFk;
import com.amazon.alexa.hYy;
import com.amazon.alexa.iaZ;
import com.amazon.alexa.kbU;
import com.amazon.alexa.lCm;
import com.amazon.alexa.lsL;
import com.amazon.alexa.mLq;
import com.amazon.alexa.mPf;
import com.amazon.alexa.nNv;
import com.amazon.alexa.pGm;
import com.amazon.alexa.pLw;
import com.amazon.alexa.pTS;
import com.amazon.alexa.pUc;
import com.amazon.alexa.pUe;
import com.amazon.alexa.pfY;
import com.amazon.alexa.pfe;
import com.amazon.alexa.plk;
import com.amazon.alexa.qWv;
import com.amazon.alexa.qgo;
import com.amazon.alexa.rAH;
import com.amazon.alexa.rXH;
import com.amazon.alexa.rfd;
import com.amazon.alexa.rff;
import com.amazon.alexa.sGd;
import com.amazon.alexa.sci;
import com.amazon.alexa.tPB;
import com.amazon.alexa.tRN;
import com.amazon.alexa.tWv;
import com.amazon.alexa.tui;
import com.amazon.alexa.uEF;
import com.amazon.alexa.uWW;
import com.amazon.alexa.uqh;
import com.amazon.alexa.urO;
import com.amazon.alexa.vJW;
import com.amazon.alexa.vQe;
import com.amazon.alexa.vRe;
import com.amazon.alexa.vST;
import com.amazon.alexa.vUA;
import com.amazon.alexa.vwO;
import com.amazon.alexa.wOp;
import com.amazon.alexa.wze;
import com.amazon.alexa.xik;
import com.amazon.alexa.xsd;
import com.amazon.alexa.yXU;
import com.amazon.alexa.yaQ;
import com.amazon.alexa.yfS;
import com.amazon.alexa.yqu;
import com.amazon.alexa.zAH;
import com.amazon.alexa.zpo;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (ActivityTrackerChannelState.class.isAssignableFrom(rawType)) {
            return new ActivityTrackerChannelState.GsonTypeAdapter(gson);
        }
        if (STS.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) STS.zZm(gson);
        }
        if (sci.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) sci.zZm(gson);
        }
        if (lsL.class.isAssignableFrom(rawType)) {
            return new AutoValue_AlexaImage.GsonTypeAdapter(gson);
        }
        if (DRc.class.isAssignableFrom(rawType)) {
            return new AutoValue_AlexaLocation.GsonTypeAdapter(gson);
        }
        if (SOo.class.isAssignableFrom(rawType)) {
            return SOo.zZm();
        }
        if (uqh.class.isAssignableFrom(rawType)) {
            return new AutoValue_AlexaUserSpeechVolumeChangedEvent.GsonTypeAdapter(gson);
        }
        if (Gkq.class.isAssignableFrom(rawType)) {
            return new AutoValue_Altitude.GsonTypeAdapter(gson);
        }
        if (plk.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) plk.zZm(gson);
        }
        if (BIn.class.isAssignableFrom(rawType)) {
            return new AutoValue_AudioItem.GsonTypeAdapter(gson);
        }
        if (Vma.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Vma.zZm(gson);
        }
        if (JGr.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) JGr.zZm(gson);
        }
        if (JGr.zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_AuthorizationCompletePayload_Authorized.GsonTypeAdapter(gson);
        }
        if (JGr.zQM.class.isAssignableFrom(rawType)) {
            return new AutoValue_AuthorizationCompletePayload_Deauthorized.GsonTypeAdapter(gson);
        }
        if (Ims.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Ims.zZm(gson);
        }
        if (Ims.zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_AuthorizeDiscoveredPlayersPayload_Player.GsonTypeAdapter(gson);
        }
        if (Ims.zZm.AbstractC0011zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata.GsonTypeAdapter(gson);
        }
        if (pfY.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) pfY.zZm(gson);
        }
        if (CAj.class.isAssignableFrom(rawType)) {
            return new AutoValue_CapabilityPublishRequest.GsonTypeAdapter(gson);
        }
        if (aNh.class.isAssignableFrom(rawType)) {
            return new AutoValue_ChannelState.GsonTypeAdapter(gson);
        }
        if (UVo.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) UVo.zZm(gson);
        }
        if (vwO.class.isAssignableFrom(rawType)) {
            return new AutoValue_ClusterDevice.GsonTypeAdapter(gson);
        }
        if (ComponentStateHeader.class.isAssignableFrom(rawType)) {
            return new AutoValue_ComponentStateHeader.GsonTypeAdapter(gson);
        }
        if (urO.class.isAssignableFrom(rawType)) {
            return new AutoValue_Connection.GsonTypeAdapter(gson);
        }
        if (hYy.class.isAssignableFrom(rawType)) {
            return new AutoValue_Coordinate.GsonTypeAdapter(gson);
        }
        if (zpo.class.isAssignableFrom(rawType)) {
            return new AutoValue_CoverUrls.GsonTypeAdapter(gson);
        }
        if (cVW.class.isAssignableFrom(rawType)) {
            return new AutoValue_DeviceInfo.GsonTypeAdapter(gson);
        }
        if (pGm.class.isAssignableFrom(rawType)) {
            return pGm.zZm();
        }
        if (IKe.class.isAssignableFrom(rawType)) {
            return IKe.zZm();
        }
        if (gUg.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) gUg.zZm(gson);
        }
        if (Bsa.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Bsa.zZm(gson);
        }
        if (StC.class.isAssignableFrom(rawType)) {
            return new AutoValue_EventTarget.GsonTypeAdapter(gson);
        }
        if (LWv.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) LWv.zZm(gson);
        }
        if (LWv.BIo.class.isAssignableFrom(rawType)) {
            return new AutoValue_ExceptionEncounteredPayload_Error.GsonTypeAdapter(gson);
        }
        if (pTS.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) pTS.zZm(gson);
        }
        if (nNv.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) nNv.zZm(gson);
        }
        if (Iab.class.isAssignableFrom(rawType)) {
            return new AutoValue_ExternalCapabilityAgentRegistrationData.GsonTypeAdapter(gson);
        }
        if (iaZ.class.isAssignableFrom(rawType)) {
            return new AutoValue_ExternalCapabilityAgentRegistrationRawData.GsonTypeAdapter(gson);
        }
        if (MOI.class.isAssignableFrom(rawType)) {
            return new AutoValue_ExternalMediaPlayerMetadata.GsonTypeAdapter(gson);
        }
        if (yfS.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) yfS.zZm(gson);
        }
        if (BGK.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) BGK.zZm(gson);
        }
        if (vQe.class.isAssignableFrom(rawType)) {
            return vQe.zZm();
        }
        if (LZG.class.isAssignableFrom(rawType)) {
            return LZG.zZm();
        }
        if (cMY.class.isAssignableFrom(rawType)) {
            return cMY.zZm();
        }
        if (Suv.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Suv.zZm(gson);
        }
        if (wze.class.isAssignableFrom(rawType)) {
            return new AutoValue_Heading.GsonTypeAdapter(gson);
        }
        if (tWv.class.isAssignableFrom(rawType)) {
            return new AutoValue_IOComponent.GsonTypeAdapter(gson);
        }
        if (URU.class.isAssignableFrom(rawType)) {
            return new AutoValue_IOComponentContext.GsonTypeAdapter(gson);
        }
        if (Agi.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Agi.zZm(gson);
        }
        if (GhS.class.isAssignableFrom(rawType)) {
            return new AutoValue_LaunchConfig.GsonTypeAdapter(gson);
        }
        if (mLq.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) mLq.zZm(gson);
        }
        if (sGd.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) sGd.zZm(gson);
        }
        if (Xdr.class.isAssignableFrom(rawType)) {
            return new AutoValue_LocationServices.GsonTypeAdapter(gson);
        }
        if (AbstractC0197ddD.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AbstractC0197ddD.zZm(gson);
        }
        if (Jqr.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Jqr.zZm(gson);
        }
        if (zAH.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) zAH.zZm(gson);
        }
        if (JTD.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) JTD.zZm(gson);
        }
        if (HbJ.class.isAssignableFrom(rawType)) {
            return new AutoValue_PhoneCallControllerCall.GsonTypeAdapter(gson);
        }
        if (vUA.class.isAssignableFrom(rawType)) {
            return new AutoValue_PhoneCallControllerCallInfo.GsonTypeAdapter(gson);
        }
        if (HYG.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) new TypeAdapter<HYG>() { // from class: com.amazon.alexa.client.alexaservice.comms.payload.PhoneCallControllerCallingFeature$1
                @Override // com.google.gson.TypeAdapter
                /* renamed from: zZm */
                public void write(JsonWriter jsonWriter, HYG hyg) throws IOException {
                    jsonWriter.beginObject();
                    rXH rxh = (rXH) hyg;
                    jsonWriter.name(rxh.zZm);
                    jsonWriter.value(rxh.BIo);
                    jsonWriter.endObject();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public HYG mo8353read(JsonReader jsonReader) throws IOException {
                    jsonReader.beginObject();
                    String nextName = jsonReader.nextName();
                    boolean nextBoolean = jsonReader.nextBoolean();
                    jsonReader.endObject();
                    return HYG.zZm(nextName, nextBoolean);
                }
            };
        }
        if (rAH.class.isAssignableFrom(rawType)) {
            return new AutoValue_PhoneCallControllerConfiguration.GsonTypeAdapter(gson);
        }
        if (Sas.class.isAssignableFrom(rawType)) {
            return new AutoValue_PhoneCallControllerDevice.GsonTypeAdapter(gson);
        }
        if (KLX.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) KLX.zZm(gson);
        }
        if (vJW.class.isAssignableFrom(rawType)) {
            return vJW.BIo();
        }
        if (uWW.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) uWW.zZm(gson);
        }
        if (xik.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) xik.zZm(gson);
        }
        if (lCm.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) lCm.zZm(gson);
        }
        if (hFk.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) hFk.zZm(gson);
        }
        if (hFk.BIo.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlaybackFailedEventPayload_Error.GsonTypeAdapter(gson);
        }
        if (GkO.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) GkO.zZm(gson);
        }
        if (pLw.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) pLw.zZm(gson);
        }
        if (HkJ.class.isAssignableFrom(rawType)) {
            return new AutoValue_Player.GsonTypeAdapter(gson);
        }
        if (pfe.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerError.GsonTypeAdapter(gson);
        }
        if (Rgi.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Rgi.zZm(gson);
        }
        if (Qds.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Qds.zZm(gson);
        }
        if (qgo.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) qgo.zZm(gson);
        }
        if (qgo.zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerInfoPayload_InfoText.GsonTypeAdapter(gson);
        }
        if (qgo.BIo.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerInfoPayload_Progress.GsonTypeAdapter(gson);
        }
        if (qgo.zQM.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerInfoPayload_Template.GsonTypeAdapter(gson);
        }
        if (dEA.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerRuntimeState.GsonTypeAdapter(gson);
        }
        if (Kyp.class.isAssignableFrom(rawType)) {
            return new AutoValue_PlayerStructure.GsonTypeAdapter(gson);
        }
        if (Nwd.class.isAssignableFrom(rawType)) {
            return Nwd.zZm();
        }
        if (CiJ.class.isAssignableFrom(rawType)) {
            return new AutoValue_ProgressReport.GsonTypeAdapter(gson);
        }
        if (AbstractC0183Xff.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) AbstractC0183Xff.zZm(gson);
        }
        if (Bfv.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Bfv.zZm(gson);
        }
        if (vST.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) vST.zZm(gson);
        }
        if (vST.BIo.class.isAssignableFrom(rawType)) {
            return new AutoValue_ReportDiscoveredPlayersPayload_Player.GsonTypeAdapter(gson);
        }
        if (PcE.class.isAssignableFrom(rawType)) {
            return new AutoValue_SessionState.GsonTypeAdapter(gson);
        }
        if (Alc.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Alc.zZm(gson);
        }
        if (Alc.zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_SetDestinationPayload_Destination.GsonTypeAdapter(gson);
        }
        if (Alc.zZm.AbstractC0010zZm.class.isAssignableFrom(rawType)) {
            return new AutoValue_SetDestinationPayload_Destination_Coordinate.GsonTypeAdapter(gson);
        }
        if (NYz.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) NYz.zZm(gson);
        }
        if (mPf.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) mPf.zZm(gson);
        }
        if (RhW.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) RhW.zZm(gson);
        }
        if (ErD.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ErD.zZm(gson);
        }
        if (tRN.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) tRN.zZm(gson);
        }
        if (qWv.class.isAssignableFrom(rawType)) {
            return new AutoValue_Setting.GsonTypeAdapter(gson);
        }
        if (xsd.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) xsd.zZm(gson);
        }
        if (UTs.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) UTs.zZm(gson);
        }
        if (wOp.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) wOp.zZm(gson);
        }
        if (DEe.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DEe.zZm(gson);
        }
        if (yXU.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) yXU.zZm(gson);
        }
        if (tui.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) tui.zZm(gson);
        }
        if (rfd.class.isAssignableFrom(rawType)) {
            return new AutoValue_Speed.GsonTypeAdapter(gson);
        }
        if (rff.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) rff.zZm(gson);
        }
        if (fcj.class.isAssignableFrom(rawType)) {
            return new AutoValue_Stream.GsonTypeAdapter(gson);
        }
        if (yqu.class.isAssignableFrom(rawType)) {
            return new AutoValue_SupportsMobileDownchannelSetting.GsonTypeAdapter(gson);
        }
        if (tPB.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) tPB.zZm(gson);
        }
        if (pUc.class.isAssignableFrom(rawType)) {
            return new AutoValue_Target.GsonTypeAdapter(gson);
        }
        if (PMk.class.isAssignableFrom(rawType)) {
            return PMk.zZm();
        }
        if (Ejh.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Ejh.zZm(gson);
        }
        if (yaQ.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) yaQ.zZm(gson);
        }
        if (IUU.class.isAssignableFrom(rawType)) {
            return IUU.zZm();
        }
        if (pUe.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) pUe.zZm(gson);
        }
        if (YiP.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) YiP.zZm(gson);
        }
        if (WlR.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) WlR.zZm(gson);
        }
        if (VisualActivityTrackerChannelState.class.isAssignableFrom(rawType)) {
            return new VisualActivityTrackerChannelState.GsonTypeAdapter(gson);
        }
        if (Gzu.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Gzu.zZm(gson);
        }
        if (VCD.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) VCD.zZm(gson);
        }
        if (vRe.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) vRe.zZm(gson);
        }
        if (DWt.class.isAssignableFrom(rawType)) {
            return new AutoValue_WakeWordIndices.GsonTypeAdapter(gson);
        }
        if (AbstractC0209ibG.class.isAssignableFrom(rawType)) {
            return new AutoValue_WakeWordInitiator.GsonTypeAdapter(gson);
        }
        if (kbU.class.isAssignableFrom(rawType)) {
            return new AutoValue_WakeWordInitiatorPayload.GsonTypeAdapter(gson);
        }
        if (uEF.class.isAssignableFrom(rawType)) {
            return new AutoValue_Window.GsonTypeAdapter(gson);
        }
        if (!AbstractC0229sSp.class.isAssignableFrom(rawType)) {
            return null;
        }
        return new AutoValue_WindowConfiguration.GsonTypeAdapter(gson);
    }
}
