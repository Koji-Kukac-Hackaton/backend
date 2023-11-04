package com.codebooq.repository;

import com.codebooq.model.domain.DemandHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandHistoryRepository extends JpaRepository<DemandHistory, String> {
}
