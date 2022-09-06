package com.amazon.alexa.presence.bleconn.service.handlers;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import com.amazon.alexa.presence.bleconn.helpers.MathHelper;
import com.amazon.alexa.presence.bleconn.identity.IdentityController;
import com.amazon.alexa.presence.bleconn.identity.MatchingRelationshipData;
import com.amazon.alexa.presence.bleconn.service.CharacteristicReadRequestHandler;
import com.amazon.alexa.presence.bleconn.service.CharacteristicWriteRequestHandler;
import com.amazon.alexa.presence.bleconn.service.Request;
import com.amazon.alexa.presence.bleconn.service.protocols.identity.IdentityResponseV1;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class IdentityRequestHandler implements CharacteristicReadRequestHandler, CharacteristicWriteRequestHandler {
    private static final boolean ENCRYPT_TOKENS = true;
    private static final int MAX_POSSIBLE_MTU = 512;
    private static final int MAX_RESPONSE_SIZE_BYTES = 511;
    private static final int RELATIONSHIP_CACHE_MAX_DURATION_MS = 300000;
    private static final int RELATIONSHIP_CACHE_MAX_ENTRIES = 32;
    private static final String RELATIONSHIP_DATA_DEBUG_VALUE = "DEBUG_GROUP";
    private static final int RELATIONSHIP_DATA_SIZE_LIMIT_BYTES = 1024;
    private static final int RESPONSE_CACHE_MAX_DURATION_MS = 10000;
    private static final int RESPONSE_CACHE_MAX_ENTRIES = 32;
    private final IdentityController identityController;
    private final List<IdentityRequestedSubscriber> onIdentityRequestedSubscribers = new ArrayList();
    private final Cache<String, byte[]> relationshipData;
    private final Cache<String, byte[]> responseCache;
    private static final Logger LOG = LoggerFactory.getLogger(IdentityRequestHandler.class);
    private static final byte[] NO_RELATIONSHIP_DATA_RESPONSE = ByteHelper.asByteArray(255, 0, 1);
    private static final byte[] NO_MATCHING_RELATIONSHIP_FOUND = ByteHelper.asByteArray(255, 0, 2);
    private static final byte[] NO_IDENTITY_RESPONSE = ByteHelper.asByteArray(255, 0, 3);
    private static final byte[] NO_RESPONSE_RESPONSE = ByteHelper.asByteArray(255, 0, 4);
    private static final byte[] UNEXPECTED_ERROR_RESPONSE = ByteHelper.asByteArray(255, 0, 255);

    public IdentityRequestHandler(IdentityController identityController) {
        Objects.requireNonNull(identityController);
        this.identityController = identityController;
        this.responseCache = CacheBuilder.newBuilder().maximumSize(32L).expireAfterWrite(10000L, TimeUnit.MILLISECONDS).build();
        this.relationshipData = CacheBuilder.newBuilder().maximumSize(32L).expireAfterWrite(300000L, TimeUnit.MILLISECONDS).build();
    }

    private void appendRelationshipData(String str, byte[] bArr) {
        byte[] relationshipData = getRelationshipData(str);
        if (relationshipData.length + bArr.length <= 1024) {
            byte[] bArr2 = new byte[relationshipData.length + bArr.length];
            System.arraycopy(relationshipData, 0, bArr2, 0, relationshipData.length);
            System.arraycopy(bArr, 0, bArr2, relationshipData.length, bArr.length);
            this.relationshipData.put(str, bArr2);
            return;
        }
        throw new RuntimeException("Relationship data limit exceeded");
    }

    private byte[] extractDataSegment(int i, int i2, byte[] bArr) {
        return Arrays.copyOfRange(bArr, MathHelper.clamp(i, 0, bArr.length), MathHelper.clamp(i + i2, 0, bArr.length));
    }

    private IdentityGenerationResult generateResponse(boolean z, byte[] bArr) {
        byte[] bytes;
        LOG.info("Generating response");
        if (bArr != null && bArr.length != 0) {
            MatchingRelationshipData resolveRelationshipMatch = resolveRelationshipMatch(bArr);
            byte[] validationData = resolveRelationshipMatch != null ? resolveRelationshipMatch.getValidationData() : null;
            if (validationData == null || validationData.length == 0) {
                LOG.info("No relationship match found.  Returning NO_MATCHING_RELATIONSHIP_FOUND");
                return new IdentityGenerationResult(NO_MATCHING_RELATIONSHIP_FOUND);
            }
            if (z) {
                bytes = this.identityController.token();
            } else {
                bytes = this.identityController.clearTextToken().getBytes(StandardCharsets.UTF_8);
            }
            if (bytes == null) {
                return new IdentityGenerationResult(NO_IDENTITY_RESPONSE);
            }
            try {
                return new IdentityGenerationResult(new IdentityResponseV1().withRelationshipData(validationData).withToken(bytes).toBytes(), resolveRelationshipMatch);
            } catch (IllegalArgumentException unused) {
                LOG.warn("Invalid response generated");
                return new IdentityGenerationResult(null, null);
            }
        }
        LOG.info("No relationship data written.  Returning NO_RELATIONSHIP_DATA_RESPONSE");
        return new IdentityGenerationResult(NO_RELATIONSHIP_DATA_RESPONSE);
    }

    private byte[] getRelationshipData(Request request) {
        return getRelationshipData(request.getClient().getAddress());
    }

    private boolean isDebugRelationship(byte[] bArr) {
        return Arrays.equals(RELATIONSHIP_DATA_DEBUG_VALUE.getBytes(StandardCharsets.UTF_8), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte[] lambda$getRelationshipData$0() throws Exception {
        return new byte[0];
    }

    private void notifyIdentityReadRequested(IdentityGenerationResult identityGenerationResult) {
        byte[] response = identityGenerationResult.getResponse();
        boolean z = true;
        boolean z2 = response != NO_RELATIONSHIP_DATA_RESPONSE;
        boolean z3 = z2 && response != NO_MATCHING_RELATIONSHIP_FOUND;
        Integer typeId = (identityGenerationResult.match == null || identityGenerationResult.getMatch().getMatchingKey() == null) ? null : identityGenerationResult.getMatch().getMatchingKey().getTypeId();
        if (!z3 || response == NO_IDENTITY_RESPONSE) {
            z = false;
        }
        for (IdentityRequestedSubscriber identityRequestedSubscriber : this.onIdentityRequestedSubscribers) {
            try {
                identityRequestedSubscriber.notifyIdentityReadRequested(z2, z3, typeId, z);
            } catch (Throwable th) {
                LOG.warn("Subscriber threw an exception", th);
            }
        }
    }

    private void processRelationshipDataRequest(Request request) {
        String address = request.getClient().getAddress();
        if (request.getOffset() == 0) {
            this.responseCache.invalidate(address);
            this.relationshipData.put(address, request.getData());
            return;
        }
        appendRelationshipData(address, request.getData());
    }

    private MatchingRelationshipData resolveRelationshipMatch(byte[] bArr) {
        return this.identityController.getFirstMatchingRelationship(bArr.length > 0 ? RequesterRelationshipsPacketV1.tryParse(bArr) : null);
    }

    private void sendResponseApplicationFailure(Request request) {
        request.mo2296getResponse().sendResponseSuccess(UNEXPECTED_ERROR_RESPONSE);
    }

    private void sendResponseNoToken(Request request) {
        LOG.debug("Sending empty token response");
        request.mo2296getResponse().sendResponseSuccess(NO_RESPONSE_RESPONSE);
    }

    private void sendResponseRequestedTokenSegment(Request request, byte[] bArr) {
        byte[] extractDataSegment = extractDataSegment(request.getOffset(), 511, bArr);
        Logger logger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sending token segment.  Bytes sent: ");
        outline107.append(extractDataSegment.length);
        logger.debug(outline107.toString());
        request.mo2296getResponse().sendMultipartResponseSuccess(request.getOffset(), extractDataSegment);
    }

    private byte[] tryLoadReadResponse(Request request) {
        String address = request.getClient().getAddress();
        byte[] ifPresent = this.responseCache.getIfPresent(address);
        if (ifPresent == null) {
            LOG.debug("No response cached.  Will generate response");
            IdentityGenerationResult generateResponse = generateResponse(true, getRelationshipData(request));
            ifPresent = generateResponse.getResponse();
            if (ifPresent != null) {
                LOG.debug("Caching full response");
                this.responseCache.put(address, ifPresent);
            }
            notifyIdentityReadRequested(generateResponse);
        }
        return ifPresent;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.CharacteristicWriteRequestHandler
    public void onCharacteristicExecuteWriteRequest(Request request) {
        LOG.info("onCharacteristicExecuteWriteRequest");
        request.mo2296getResponse().sendResponseSuccess(new byte[0]);
    }

    @Override // com.amazon.alexa.presence.bleconn.service.CharacteristicReadRequestHandler
    public void onCharacteristicReadRequest(Request request) {
        LOG.info("onCharacteristicReadRequest");
        try {
            byte[] tryLoadReadResponse = tryLoadReadResponse(request);
            if (tryLoadReadResponse == null) {
                LOG.warn("Unable to load response, returning empty data set.");
                sendResponseNoToken(request);
                return;
            }
            sendResponseRequestedTokenSegment(request, tryLoadReadResponse);
        } catch (Throwable th) {
            Logger logger = LOG;
            logger.error("Exception encountered handling request. Returning failure. " + th);
            sendResponseApplicationFailure(request);
        }
    }

    @Override // com.amazon.alexa.presence.bleconn.service.CharacteristicWriteRequestHandler
    public void onCharacteristicWriteRequest(Request request) {
        try {
            LOG.info("onCharacteristicWriteRequest");
            if (request.requiresResponse()) {
                request.mo2296getResponse().sendMultipartResponseSuccess(request.getOffset(), request.getData());
            }
            if (request.getData() == null) {
                return;
            }
            LOG.info("Data received size: %s (bytes)", Integer.valueOf(request.getData().length));
            LOG.info("Data received this request (hex): %s", ByteHelper.asHex(request.getData()));
            LOG.info("Data received this request (binary): %s", ByteHelper.asBinary(request.getData()));
            processRelationshipDataRequest(request);
            LOG.info("Characteristic data (hex): %s", ByteHelper.asHex(getRelationshipData(request)));
            LOG.info("Characteristic data (binary): %s", ByteHelper.asBinary(getRelationshipData(request)));
        } catch (Throwable th) {
            LOG.warn("Error on characteristic write", th);
            if (!request.requiresResponse()) {
                return;
            }
            request.mo2296getResponse().sendResponseFailure();
        }
    }

    public void onIdentityReadRequested(IdentityRequestedSubscriber identityRequestedSubscriber) {
        if (identityRequestedSubscriber != null && !this.onIdentityRequestedSubscribers.contains(identityRequestedSubscriber)) {
            this.onIdentityRequestedSubscribers.add(identityRequestedSubscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class IdentityGenerationResult {
        private final MatchingRelationshipData match;
        private final byte[] response;

        public IdentityGenerationResult(byte[] bArr) {
            this.response = bArr;
            this.match = null;
        }

        public MatchingRelationshipData getMatch() {
            return this.match;
        }

        public byte[] getResponse() {
            return this.response;
        }

        public IdentityGenerationResult(byte[] bArr, MatchingRelationshipData matchingRelationshipData) {
            this.response = bArr;
            this.match = matchingRelationshipData;
        }
    }

    private byte[] getRelationshipData(String str) {
        try {
            return this.relationshipData.get(str, $$Lambda$IdentityRequestHandler$sjHFB4i1qVhSXTaqbnqP_nXnDl8.INSTANCE);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
