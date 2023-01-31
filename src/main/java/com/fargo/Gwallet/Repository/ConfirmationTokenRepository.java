package com.fargo.Gwallet.Repository;

import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByTokenNumberIgnoreCase(String token);
    Optional<ConfirmationToken> findTokenByUser(User user);
}
