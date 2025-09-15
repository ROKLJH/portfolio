package com.KoreaItAcdemy.OurNotion.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRecordRepository extends JpaRepository<BudgetRecord, Long> {

    @Query("SELECT MAX(b.accountSeq) FROM BudgetRecord b")
    Long findMaxAccountSeq();
}
