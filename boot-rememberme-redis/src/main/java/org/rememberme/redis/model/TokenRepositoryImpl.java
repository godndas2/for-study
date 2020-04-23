package org.rememberme.redis.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Transactional
public class TokenRepositoryImpl implements PersistentTokenRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        logger.info("***** createNewToken *****");
        Token token = new Token();
        token.setUsername(persistentRememberMeToken.getUsername());
        token.setToken(persistentRememberMeToken.getTokenValue());
        token.setSeries(persistentRememberMeToken.getSeries());
        token.setLastUsed(persistentRememberMeToken.getDate());
        logger.info(token.toString());

        tokenRepository.save(token);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        logger.info("***** getTokenForSeries *****");
        Optional<Token> token = tokenRepository.findBySeries(series);
        if (token.isPresent()) {
            PersistentRememberMeToken persistentRememberMeToken =
                    new PersistentRememberMeToken(
                            token.get().getUsername(),
                            series,
                            token.get().getToken(),
                            token.get().getLastUsed()
                    );
            return persistentRememberMeToken;
        } else {
            logger.info("persistentRememberMeToken Failed..!");
        }

        return null;
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        logger.info("***** updateToken *****");
        Optional<Token> token = tokenRepository.findById(series);
        if (token.isPresent()) {
            Token updateToken = token.get();
            updateToken.setToken(tokenValue);
            updateToken.setLastUsed(lastUsed);
            updateToken.setSeries(series);

            tokenRepository.save(updateToken);
        }
    }

    @Override
    public void removeUserTokens(String username) {
        logger.info("***** removeUserTokens *****");
        Optional<Token> token = tokenRepository.findById(username);
        logger.info(token.get().toString());
        if (token.isPresent()) tokenRepository.delete(token.get());
    }
}
