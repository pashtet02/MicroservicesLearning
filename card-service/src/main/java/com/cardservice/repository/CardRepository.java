package com.cardservice.repository;

import com.cardservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByCardId(Long username);
    List<Card> findByUserId(Long userId);
    Optional<Card> findByCardId(Long cardId);
    void deleteByCardId(Long cardId);
}
