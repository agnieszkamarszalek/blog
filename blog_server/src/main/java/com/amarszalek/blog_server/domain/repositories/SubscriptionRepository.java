package com.amarszalek.blog_server.domain.repositories;

import com.amarszalek.blog_server.domain.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<List<Subscription>> findByAuthorUserName(String authorUserName);
    Optional<Subscription> findByUserIdAndAndAuthorUserName(String userId, String authorUserName);
}
