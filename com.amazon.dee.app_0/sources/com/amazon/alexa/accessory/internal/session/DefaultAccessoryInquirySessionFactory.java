package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.session.AccessoryInquirySession;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.ModelTransformer;
/* loaded from: classes.dex */
public class DefaultAccessoryInquirySessionFactory implements AccessoryInquirySession.Factory {
    private final FeatureChecker featureChecker;
    private final AccessoryTransport.Factory transportFactory;

    public DefaultAccessoryInquirySessionFactory(AccessoryTransport.Factory factory, FeatureChecker featureChecker) {
        Preconditions.notNull(factory, "transportFactory");
        Preconditions.notNull(featureChecker, "featureChecker");
        this.transportFactory = factory;
        this.featureChecker = featureChecker;
    }

    @Override // com.amazon.alexa.accessory.internal.session.AccessoryInquirySession.Factory
    public AccessoryInquirySession create(Accessory accessory) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return new DefaultAccessoryInquirySession(accessory, this.transportFactory, this.featureChecker);
    }
}
