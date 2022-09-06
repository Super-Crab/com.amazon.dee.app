package com.amazon.alexa;

import com.amazon.alexa.ARM;
import com.amazon.alexa.BaP;
import com.amazon.alexa.Bha;
import com.amazon.alexa.BjL;
import com.amazon.alexa.EIa;
import com.amazon.alexa.Fwh;
import com.amazon.alexa.HdS;
import com.amazon.alexa.ICG;
import com.amazon.alexa.NEv;
import com.amazon.alexa.QYV;
import com.amazon.alexa.UMd;
import com.amazon.alexa.Ued;
import com.amazon.alexa.VXG;
import com.amazon.alexa.Vxb;
import com.amazon.alexa.XnZ;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.fuM;
import com.amazon.alexa.gKM;
import com.amazon.alexa.jrC;
import com.amazon.alexa.jsd;
import com.amazon.alexa.kbp;
import com.amazon.alexa.pPw;
import com.amazon.alexa.yqV;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.meta.SimpleSubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
import org.greenrobot.eventbus.meta.SubscriberMethodInfo;
/* compiled from: AlexaEventBusIndex.java */
/* loaded from: classes.dex */
public class GRR implements SubscriberInfoIndex {
    public static final Map<Class<?>, SubscriberInfo> zZm = new HashMap();

    static {
        zZm(new SimpleSubscriberInfo(BOa.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", kOA.class), new SubscriberMethodInfo("on", fuM.jiA.class)}));
        zZm(new SimpleSubscriberInfo(QIY.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", aBV.class), new SubscriberMethodInfo("on", thq.class)}));
        zZm(new SimpleSubscriberInfo(bwE.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AQg.class)}));
        zZm(new SimpleSubscriberInfo(AxK.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", fuM.BIo.class), new SubscriberMethodInfo("on", AQg.class), new SubscriberMethodInfo("on", QYV.Qle.class), new SubscriberMethodInfo("on", HDT.class, ThreadMode.POSTING, 0, true)}));
        zZm(new SimpleSubscriberInfo(TqI.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", UMd.BIo.class), new SubscriberMethodInfo("on", UMd.zZm.class)}));
        zZm(new SimpleSubscriberInfo(yjS.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("registerAlexaTextResponseListener", Kkr.class), new SubscriberMethodInfo("deregisterAlexaTextResponseListener", KMu.class), new SubscriberMethodInfo("registerAlexaExpectTextListener", Ezv.class), new SubscriberMethodInfo("deregisterAlexaExpectTextListener", SbW.class), new SubscriberMethodInfo("sendTextMessage", uxJ.class), new SubscriberMethodInfo("on", QYV.zyO.class), new SubscriberMethodInfo("on", AbstractC0208iNL.class), new SubscriberMethodInfo("on", PYl.class)}));
        zZm(new SimpleSubscriberInfo(pbo.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", BWd.class), new SubscriberMethodInfo("on", EVw.class)}));
        zZm(new SimpleSubscriberInfo(nmS.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("onResponse", ZAZ.class, ThreadMode.ASYNC)}));
        zZm(new SimpleSubscriberInfo(szT.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", tkb.class), new SubscriberMethodInfo("on", Ppr.class)}));
        zZm(new SimpleSubscriberInfo(eCj.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", ZBK.class), new SubscriberMethodInfo("on", zCQ.class)}));
        zZm(new SimpleSubscriberInfo(ciO.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", CKO.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", pPd.class, ThreadMode.POSTING, 100, false)}));
        zZm(new SimpleSubscriberInfo(YKQ.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(bXh.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Vba.class), new SubscriberMethodInfo("on", wVP.class), new SubscriberMethodInfo("on", SQt.class)}));
        zZm(new SimpleSubscriberInfo(JXl.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", NEv.BIo.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", NEv.zQM.class), new SubscriberMethodInfo("on", NEv.zZm.class)}));
        zZm(new SimpleSubscriberInfo(AlexaClient.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", NTk.class), new SubscriberMethodInfo("on", Jkd.class), new SubscriberMethodInfo("on", OeW.class), new SubscriberMethodInfo("on", BnD.class), new SubscriberMethodInfo("on", Seo.class), new SubscriberMethodInfo("on", BeW.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(OIb.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Zbv.class), new SubscriberMethodInfo("on", fxz.class)}));
        zZm(new SimpleSubscriberInfo(NDW.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", vhe.class), new SubscriberMethodInfo("on", eXo.class), new SubscriberMethodInfo("on", pPd.class), new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", Qrm.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(fjm.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(zDj.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Bob.class), new SubscriberMethodInfo("on", Txs.class)}));
        zZm(new SimpleSubscriberInfo(yED.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Voz.class), new SubscriberMethodInfo("on", TTH.class), new SubscriberMethodInfo("on", MyZ.class)}));
        zZm(new SimpleSubscriberInfo(RfA.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(Lnt.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AzW.class), new SubscriberMethodInfo("on", ery.class), new SubscriberMethodInfo("on", DUu.class), new SubscriberMethodInfo("on", Bob.class)}));
        zZm(new SimpleSubscriberInfo(gMz.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", qUD.class), new SubscriberMethodInfo("on", dOG.class)}));
        zZm(new SimpleSubscriberInfo(XUD.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Bob.class), new SubscriberMethodInfo("on", YHu.class), new SubscriberMethodInfo("on", ycT.class), new SubscriberMethodInfo("on", MyZ.class)}));
        zZm(new SimpleSubscriberInfo(AlexaService.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Bob.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", qZM.class), new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", bFb.class), new SubscriberMethodInfo("on", ZnH.class), new SubscriberMethodInfo("on", MiL.class), new SubscriberMethodInfo("on", Ust.class)}));
        zZm(new SimpleSubscriberInfo(pZY.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", UMd.zZm.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", WBQ.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", rKi.class), new SubscriberMethodInfo("on", AbstractC0242ykQ.class)}));
        zZm(new SimpleSubscriberInfo(Nyy.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", VXG.Qle.class), new SubscriberMethodInfo("on", VXG.jiA.class), new SubscriberMethodInfo("on", QYV.zZm.class)}));
        zZm(new SimpleSubscriberInfo(lUQ.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", kOA.class)}));
        zZm(new SimpleSubscriberInfo(xQf.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AbstractC0177Pjz.class), new SubscriberMethodInfo("on", DLc.class)}));
        zZm(new SimpleSubscriberInfo(KPv.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AQg.class)}));
        zZm(new SimpleSubscriberInfo(AlexaNotificationManager.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Ehk.class), new SubscriberMethodInfo("on", cer.class), new SubscriberMethodInfo("on", jkT.class), new SubscriberMethodInfo("on", ZBK.class), new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", qZM.class), new SubscriberMethodInfo("on", HDT.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", qTm.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", AhI.class), new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", NId.class), new SubscriberMethodInfo("on", pPd.class), new SubscriberMethodInfo("on", AgS.class), new SubscriberMethodInfo("on", WgM.class), new SubscriberMethodInfo("on", kAu.class)}));
        zZm(new SimpleSubscriberInfo(oGE.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", fMa.class), new SubscriberMethodInfo("on", AgS.class), new SubscriberMethodInfo("on", TSb.class)}));
        zZm(new SimpleSubscriberInfo(VEQ.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", fuM.BIo.class), new SubscriberMethodInfo("on", fuM.jiA.class), new SubscriberMethodInfo("on", qTm.class)}));
        zZm(new SimpleSubscriberInfo(LuX.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AbstractC0238xdr.class), new SubscriberMethodInfo("on", QYV.JTe.class), new SubscriberMethodInfo("on", QYV.Qle.class)}));
        zZm(new SimpleSubscriberInfo(Fsz.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", tkb.class), new SubscriberMethodInfo("on", Ehk.class), new SubscriberMethodInfo("on", FXk.class), new SubscriberMethodInfo("on", ZBK.class), new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", ZRZ.class), new SubscriberMethodInfo("on", TWb.class), new SubscriberMethodInfo("on", fuM.BIo.class), new SubscriberMethodInfo("on", fuM.jiA.class), new SubscriberMethodInfo("on", Yud.class), new SubscriberMethodInfo("on", HBD.class), new SubscriberMethodInfo("on", uxs.class), new SubscriberMethodInfo("on", DlG.class), new SubscriberMethodInfo("on", TTH.class), new SubscriberMethodInfo("on", wob.class), new SubscriberMethodInfo("on", paE.class), new SubscriberMethodInfo("on", psG.class), new SubscriberMethodInfo("on", glM.class), new SubscriberMethodInfo("on", PMW.class), new SubscriberMethodInfo("on", acC.class), new SubscriberMethodInfo("on", Ppr.class), new SubscriberMethodInfo("on", MwJ.class), new SubscriberMethodInfo("on", AQg.class), new SubscriberMethodInfo("on", AbstractC0173MQv.class), new SubscriberMethodInfo("on", qqU.class), new SubscriberMethodInfo("on", SuC.class), new SubscriberMethodInfo("on", NZj.class), new SubscriberMethodInfo("on", yvr.class), new SubscriberMethodInfo("on", JjA.class), new SubscriberMethodInfo("on", BaP.zZm.class), new SubscriberMethodInfo("on", ARM.zZm.class), new SubscriberMethodInfo("on", BaP.zQM.class), new SubscriberMethodInfo("on", ARM.BIo.class), new SubscriberMethodInfo("on", BaP.BIo.class), new SubscriberMethodInfo("on", vUW.class), new SubscriberMethodInfo("on", HdS.zZm.class), new SubscriberMethodInfo("on", UyX.class), new SubscriberMethodInfo("on", UyS.class), new SubscriberMethodInfo("on", bdJ.class), new SubscriberMethodInfo("on", HdS.BIo.class), new SubscriberMethodInfo("on", HdS.zQM.class), new SubscriberMethodInfo("on", HdS.zyO.class), new SubscriberMethodInfo("on", Vdm.class), new SubscriberMethodInfo("on", gKM.BIo.class), new SubscriberMethodInfo("on", gKM.zZm.class), new SubscriberMethodInfo("on", UMd.zZm.class), new SubscriberMethodInfo("on", UMd.BIo.class), new SubscriberMethodInfo("on", TNw.class), new SubscriberMethodInfo("on", VXG.zyO.class), new SubscriberMethodInfo("on", VXG.zQM.class), new SubscriberMethodInfo("on", VXG.jiA.class), new SubscriberMethodInfo("on", VXG.zzR.class), new SubscriberMethodInfo("on", VXG.Mlj.class), new SubscriberMethodInfo("on", VXG.yPL.class), new SubscriberMethodInfo("on", VXG.zZm.class), new SubscriberMethodInfo("on", VXG.LPk.class), new SubscriberMethodInfo("on", VXG.Qle.class), new SubscriberMethodInfo("on", VXG.JTe.class), new SubscriberMethodInfo("on", VXG.BIo.class), new SubscriberMethodInfo("on", BjL.zQM.class), new SubscriberMethodInfo("on", BjL.BIo.class), new SubscriberMethodInfo("on", BjL.zZm.class), new SubscriberMethodInfo("on", Fwh.zQM.class), new SubscriberMethodInfo("on", Fwh.BIo.class), new SubscriberMethodInfo("on", Fwh.zZm.class), new SubscriberMethodInfo("on", WbI.class), new SubscriberMethodInfo("on", jsd.BIo.class), new SubscriberMethodInfo("on", jsd.zZm.class), new SubscriberMethodInfo("on", XnZ.BIo.class), new SubscriberMethodInfo("on", XnZ.zZm.class), new SubscriberMethodInfo("on", XnZ.zQM.class), new SubscriberMethodInfo("on", zeW.class), new SubscriberMethodInfo("on", AbstractC0175Mka.class), new SubscriberMethodInfo("on", GSJ.class), new SubscriberMethodInfo("on", HtI.class), new SubscriberMethodInfo("on", iRJ.class), new SubscriberMethodInfo("on", AbstractC0224oIb.class), new SubscriberMethodInfo("on", Bob.class), new SubscriberMethodInfo("on", yHQ.class), new SubscriberMethodInfo("on", Ued.BIo.class), new SubscriberMethodInfo("on", Ued.zZm.class), new SubscriberMethodInfo("on", ICG.BIo.class), new SubscriberMethodInfo("on", ICG.zZm.class), new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", pPw.zZm.BIo.class), new SubscriberMethodInfo("on", pPw.zZm.AbstractC0034zZm.class), new SubscriberMethodInfo("on", OUQ.class), new SubscriberMethodInfo("on", NEv.BIo.class), new SubscriberMethodInfo("on", NEv.zQM.class), new SubscriberMethodInfo("on", NEv.zZm.class), new SubscriberMethodInfo("on", NEv.zyO.class), new SubscriberMethodInfo("on", ZZE.class), new SubscriberMethodInfo("on", Ega.class), new SubscriberMethodInfo("on", jLK.class), new SubscriberMethodInfo("on", XFF.class), new SubscriberMethodInfo("on", Qqb.class)}));
        zZm(new SimpleSubscriberInfo(MBE.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Bob.class), new SubscriberMethodInfo("on", cer.class), new SubscriberMethodInfo("on", wzr.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(jSO.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", kOA.class)}));
        zZm(new SimpleSubscriberInfo(kDa.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("schedule", Wyk.class), new SubscriberMethodInfo("unschedule", ArZ.class)}));
        zZm(new SimpleSubscriberInfo(DIi.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", ISQ.class), new SubscriberMethodInfo("on", kOA.class)}));
        zZm(new SimpleSubscriberInfo(Ccz.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", GSR.class), new SubscriberMethodInfo("on", urz.class), new SubscriberMethodInfo("on", kbp.jiA.class), new SubscriberMethodInfo("on", kbp.zZm.class), new SubscriberMethodInfo("on", kbp.zQM.class), new SubscriberMethodInfo("on", kbp.zyO.class)}));
        zZm(new SimpleSubscriberInfo(EIa.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Zbv.class), new SubscriberMethodInfo("on", fxz.class), new SubscriberMethodInfo("on", MwZ.class)}));
        zZm(new SimpleSubscriberInfo(Qoi.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", BJt.class), new SubscriberMethodInfo("on", Obt.class), new SubscriberMethodInfo("on", AbstractC0238xdr.class)}));
        zZm(new SimpleSubscriberInfo(C0220mUo.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", CKO.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", HDT.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", pPd.class, ThreadMode.POSTING, 100, false)}));
        zZm(new SimpleSubscriberInfo(jxu.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", TSb.class)}));
        zZm(new SimpleSubscriberInfo(MSk.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", LBB.class)}));
        zZm(new SimpleSubscriberInfo(tjk.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("onAudioPlaybackChangedEvent", qZM.class)}));
        zZm(new SimpleSubscriberInfo(aeS.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Txs.class), new SubscriberMethodInfo("on", Bob.class)}));
        zZm(new SimpleSubscriberInfo(rte.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", GSR.class), new SubscriberMethodInfo("on", GUc.class), new SubscriberMethodInfo("on", EOM.class), new SubscriberMethodInfo("on", kbp.jiA.class), new SubscriberMethodInfo("on", kbp.BIo.class), new SubscriberMethodInfo("on", kbp.zZm.class), new SubscriberMethodInfo("on", kbp.zQM.class), new SubscriberMethodInfo("on", kbp.zyO.class)}));
        zZm(new SimpleSubscriberInfo(yYy.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", AgS.class)}));
        zZm(new SimpleSubscriberInfo(Wyh.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", fuM.zQM.class)}));
        zZm(new SimpleSubscriberInfo(kjl.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", eoR.class), new SubscriberMethodInfo("on", ahl.class)}));
        zZm(new SimpleSubscriberInfo(rqw.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", DqQ.class), new SubscriberMethodInfo("on", lyG.class), new SubscriberMethodInfo("on", ShT.class), new SubscriberMethodInfo("on", AbstractC0230smc.class), new SubscriberMethodInfo("on", Tkm.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(OGT.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", QYV.BIo.class), new SubscriberMethodInfo("on", QYV.zZm.class), new SubscriberMethodInfo("on", TxC.class), new SubscriberMethodInfo("on", Vxb.zZm.class), new SubscriberMethodInfo("on", Vxb.BIo.class)}));
        zZm(new SimpleSubscriberInfo(BrT.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", YHu.class), new SubscriberMethodInfo("on", ycT.class), new SubscriberMethodInfo("on", FOR.class), new SubscriberMethodInfo("on", cXw.class), new SubscriberMethodInfo("on", cre.class), new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", hyR.class)}));
        zZm(new SimpleSubscriberInfo(DVu.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", WBQ.class), new SubscriberMethodInfo("on", bFb.class), new SubscriberMethodInfo("on", xaz.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", mUQ.class, ThreadMode.POSTING, 100, false)}));
        zZm(new SimpleSubscriberInfo(LUo.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", fuM.jiA.class), new SubscriberMethodInfo("on", kOA.class)}));
        zZm(new SimpleSubscriberInfo(GQk.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", BaP.zQM.class), new SubscriberMethodInfo("on", BaP.zZm.class)}));
        zZm(new SimpleSubscriberInfo(aew.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", fEt.class), new SubscriberMethodInfo("on", yHQ.class), new SubscriberMethodInfo("on", Bob.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", NEv.jiA.class), new SubscriberMethodInfo("on", rjK.class), new SubscriberMethodInfo("on", JjI.class)}));
        zZm(new SimpleSubscriberInfo(zmg.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Bob.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", FXk.class), new SubscriberMethodInfo("on", ddC.class)}));
        zZm(new SimpleSubscriberInfo(eDG.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MyZ.class), new SubscriberMethodInfo("on", YHu.class, ThreadMode.POSTING, 100, true), new SubscriberMethodInfo("on", ycT.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", ZBK.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", QTn.class), new SubscriberMethodInfo("on", WBQ.class), new SubscriberMethodInfo("on", xaz.class), new SubscriberMethodInfo("on", JjI.class), new SubscriberMethodInfo("on", TTH.class), new SubscriberMethodInfo("on", uXi.class), new SubscriberMethodInfo("on", mUQ.class)}));
        zZm(new SimpleSubscriberInfo(SKB.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MwZ.class), new SubscriberMethodInfo("on", gad.class)}));
        zZm(new SimpleSubscriberInfo(DJw.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", MwZ.class), new SubscriberMethodInfo("on", AbstractC0201dvl.class), new SubscriberMethodInfo("on", FXk.class), new SubscriberMethodInfo("on", Ehk.class), new SubscriberMethodInfo("on", ddC.class)}));
        zZm(new SimpleSubscriberInfo(TIo.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", BQL.class), new SubscriberMethodInfo("on", kOA.class), new SubscriberMethodInfo("on", ISQ.class)}));
        zZm(new SimpleSubscriberInfo(Ado.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Yud.class), new SubscriberMethodInfo("on", aJD.class), new SubscriberMethodInfo("on", Obt.class)}));
        zZm(new SimpleSubscriberInfo(vkx.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", TTH.class), new SubscriberMethodInfo("on", gdD.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(wLb.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", YQk.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", AhI.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", jkT.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", ddC.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", FXk.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", CKO.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", AgS.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", Ust.class, ThreadMode.POSTING, 100, false), new SubscriberMethodInfo("on", qZM.class, ThreadMode.POSTING, 100, false)}));
        zZm(new SimpleSubscriberInfo(OPl.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", CKO.class), new SubscriberMethodInfo("on", BBo.class), new SubscriberMethodInfo("on", bOH.class)}));
        zZm(new SimpleSubscriberInfo(PWS.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", vUW.class), new SubscriberMethodInfo("on", jrC.BIo.class), new SubscriberMethodInfo("on", jrC.zZm.class)}));
        zZm(new SimpleSubscriberInfo(NXS.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", uqh.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(Qgh.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", ZBK.class)}));
        zZm(new SimpleSubscriberInfo(JgM.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", tkb.class), new SubscriberMethodInfo("on", AzW.class), new SubscriberMethodInfo("on", ery.class)}));
        zZm(new SimpleSubscriberInfo(Xtu.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", UMd.class), new SubscriberMethodInfo("on", NUK.class)}));
        zZm(new SimpleSubscriberInfo(lAm.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", mUQ.class), new SubscriberMethodInfo("on", yHQ.class), new SubscriberMethodInfo("on", PMW.class)}));
        zZm(new SimpleSubscriberInfo(dAN.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", ycT.class), new SubscriberMethodInfo("on", mtH.class)}));
        zZm(new SimpleSubscriberInfo(yqV.zZm.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", TSb.class)}));
        zZm(new SimpleSubscriberInfo(tol.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", diQ.class), new SubscriberMethodInfo("on", bFb.class), new SubscriberMethodInfo("on", Odp.class)}));
        zZm(new SimpleSubscriberInfo(AHr.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", wzr.class), new SubscriberMethodInfo("on", xZV.class), new SubscriberMethodInfo("on", Bob.class), new SubscriberMethodInfo("on", cer.class)}));
        zZm(new SimpleSubscriberInfo(RBt.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", UMd.zZm.class)}));
        zZm(new SimpleSubscriberInfo(Jvr.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", QYV.jiA.class), new SubscriberMethodInfo("on", QYV.zQM.class), new SubscriberMethodInfo("on", ISQ.class), new SubscriberMethodInfo("on", kOA.class), new SubscriberMethodInfo("on", QYV.zyO.class), new SubscriberMethodInfo("on", QYV.BIo.class)}));
        zZm(new SimpleSubscriberInfo(LPk.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", qZM.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(hlu.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", GSR.class), new SubscriberMethodInfo("on", GUc.class), new SubscriberMethodInfo("on", kbp.zQM.class), new SubscriberMethodInfo("on", yHQ.class), new SubscriberMethodInfo("on", Bob.class)}));
        zZm(new SimpleSubscriberInfo(EIa.zZm.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Zbv.class, ThreadMode.POSTING, 0, true), new SubscriberMethodInfo("on", fxz.class, ThreadMode.POSTING, 0, true)}));
        zZm(new SimpleSubscriberInfo(bjR.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", Ppr.class), new SubscriberMethodInfo("on", oJW.class), new SubscriberMethodInfo("on", WGo.class), new SubscriberMethodInfo("on", RCa.class), new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(HSA.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", fuM.jiA.class)}));
        zZm(new SimpleSubscriberInfo(Snr.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", HDT.class)}));
        zZm(new SimpleSubscriberInfo(JYe.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", SuB.class)}));
        zZm(new SimpleSubscriberInfo(Bha.zZm.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", YHu.class)}));
        zZm(new SimpleSubscriberInfo(jdJ.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", xZV.class)}));
        zZm(new SimpleSubscriberInfo(brA.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", mZe.class), new SubscriberMethodInfo("on", LBB.class), new SubscriberMethodInfo("on", AbstractC0238xdr.class)}));
    }

    public static void zZm(SubscriberInfo subscriberInfo) {
        zZm.put(subscriberInfo.getSubscriberClass(), subscriberInfo);
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfoIndex
    public SubscriberInfo getSubscriberInfo(Class<?> cls) {
        SubscriberInfo subscriberInfo = zZm.get(cls);
        if (subscriberInfo != null) {
            return subscriberInfo;
        }
        return null;
    }
}
