package org.mvc.redis.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, String>  {
    Optional<Token> findBySeries(final String series);
}
