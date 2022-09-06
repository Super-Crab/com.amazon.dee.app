package com.amazon.alexa;

import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaVisualTaskFactory;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.system.UserInactivityAuthority;
import dagger.MembersInjector;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: AlexaService_MembersInjector.java */
/* loaded from: classes.dex */
public final class zQM implements MembersInjector<AlexaService> {
    public static void zZm(AlexaService alexaService, AlexaClient alexaClient) {
        alexaService.BIo = alexaClient;
    }

    public static void zZm(AlexaService alexaService, UYN uyn) {
        alexaService.zQM = uyn;
    }

    public static void zZm(AlexaService alexaService, CrashReporter crashReporter) {
        alexaService.zyO = crashReporter;
    }

    public static void zZm(AlexaService alexaService, tol tolVar) {
        alexaService.jiA = tolVar;
    }

    public static void zZm(AlexaService alexaService, AlexaClientEventBus alexaClientEventBus) {
        alexaService.Qle = alexaClientEventBus;
    }

    public static void zZm(AlexaService alexaService, AHr aHr) {
        alexaService.JTe = aHr;
    }

    public static void zZm(AlexaService alexaService, UserInactivityAuthority userInactivityAuthority) {
        alexaService.LPk = userInactivityAuthority;
    }

    public static void zZm(AlexaService alexaService, IYJ iyj) {
        alexaService.yPL = iyj;
    }

    public static void zZm(AlexaService alexaService, AlexaNotificationManager alexaNotificationManager) {
        alexaService.Mlj = alexaNotificationManager;
    }

    public static void zZm(AlexaService alexaService, wLb wlb) {
        alexaService.zzR = wlb;
    }

    public static void zZm(AlexaService alexaService, KvZ kvZ) {
        alexaService.lOf = kvZ;
    }

    public static void zZm(AlexaService alexaService, CGv cGv) {
        alexaService.dMe = cGv;
    }

    public static void zZm(AlexaService alexaService, Mlj mlj) {
        alexaService.uzr = mlj;
    }

    public static void zZm(AlexaService alexaService, AlexaVisualTaskFactory alexaVisualTaskFactory) {
        alexaService.HvC = alexaVisualTaskFactory;
    }

    public static void zZm(AlexaService alexaService, ScheduledExecutorService scheduledExecutorService) {
        alexaService.vkx = scheduledExecutorService;
    }

    public static void zZm(AlexaService alexaService, pYn pyn) {
        alexaService.wDP = pyn;
    }

    public static void zZm(AlexaService alexaService, gSO gso) {
        alexaService.noQ = gso;
    }

    public static void zZm(AlexaService alexaService, MBE mbe) {
        alexaService.Qgh = mbe;
    }

    public static void zZm(AlexaService alexaService, zDj zdj) {
        alexaService.Tbw = zdj;
    }
}
