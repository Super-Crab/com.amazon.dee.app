package com.amazon.alexa.presence.bleconn.identity.clients;

import com.amazon.alexa.presence.bleconn.identity.IdentityClient;
import com.amazon.alexa.presence.bleconn.identity.model.BleIdentityCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class LocalResponseIdentityClient implements IdentityClient {
    private static Logger LOG = LoggerFactory.getLogger(LocalResponseIdentityClient.class);

    public String getV2BeaconIdentityJson() {
        LOG.debug("Identity packet requested from local client.");
        return "{\"tokens\":[{\"rollingProximityIdentifierToken\":\"010193ca2bad8eebab8a28277c42a0a61cb12b754e785d10694832e395c6aa3ae1bbdcab22b6cf9309b74797be08bcd7e56814fc20211499ec4f764e81fd60e67bd785a4d9\",\"validTimePeriod\":{\"start\":\"2020-01-01T00:00:00Z\",\"end\":\"2040-01-01T00:00:00Z\"}}],\"encryptionKeys\":[{\"key\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmQAKv87m4CSGaQh/oxn4u8gEzrGh1RFWHQVLfBeUOtBMXGPoUA2TnwacCo47lsIcSKZWh070yIwvgBJhjTF7HDDNnegfiu0B2W0uBCDPcizjDKin8N+k7eKCF9PESVr9XHssFlV1BAXwyjLLOgUOIrxoupwR07YIp85Iqq3b/6hueefo/IJTN6PG5acHAXkKpwOyWWBcTO3q9kCTuCIc6cOaEH+S3vyE3D584V0MSxHngqPAnmVODTWaWH05OBOzxRJWORTDtNUnZJJXFQz9vQUILqix3P2tMO+cfAKX3TPQv6Uy/AK3Ke8odFvcOw3gOt5ZaBCNNSuRwQpMJ4FCbwIDAQAB\",\"validTimePeriod\":{\"start\":\"2020-01-01T00:00:00Z\",\"end\":\"2040-01-01T00:00:00Z\"}}],\"relationshipVerificationKeys\":[{\"key\":\"Relationship key 1\",\"validTimePeriod\":{\"start\":\"2020-03-16T22:41:54Z\",\"end\":\"2021-03-16T23:01:53Z\"}},{\"key\":\"Relationship key 2\",\"validTimePeriod\":{\"start\":\"2020-03-17T22:41:54Z\",\"end\":\"2020-03-17T23:01:53Z\"}}]}";
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.IdentityClient
    public BleIdentityCore getV2BeaconIdentityPacket() {
        return BleIdentityCore.deserialize(getV2BeaconIdentityJson());
    }
}
