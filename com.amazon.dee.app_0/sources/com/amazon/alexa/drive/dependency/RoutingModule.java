package com.amazon.alexa.drive.dependency;

import com.amazon.regulator.Router;
import dagger.Module;
/* JADX INFO: Access modifiers changed from: package-private */
@Module
/* loaded from: classes7.dex */
public class RoutingModule {
    private Router driveModeInnerRouter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoutingModule(Router router) {
        this.driveModeInnerRouter = router;
    }
}
