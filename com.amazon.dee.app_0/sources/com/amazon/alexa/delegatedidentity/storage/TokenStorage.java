package com.amazon.alexa.delegatedidentity.storage;

import com.amazon.alexa.delegatedidentity.model.Token;
/* loaded from: classes6.dex */
public interface TokenStorage {
    void cleanupTokens();

    Token getActorToken();

    Token getDelegatedToken();

    void putActorToken(Token token);

    void putDelegatedToken(Token token);
}
