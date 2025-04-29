package com.KoreaItAcdemy.OurNotion.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetRecordService {

    @Autowired
    private BudgetRecordRepository repository;

    // 전체 목록 조회
    public List<BudgetRecord> findAll() {
        return repository.findAll();
    }

    // ID로 조회
    public Optional<BudgetRecord> findById(Long id) {
        return repository.findById(id);
    }

    // 데이터 저장
    @Transactional
    public BudgetRecord save(BudgetRecord record) {
        return repository.save(record);
    }

    // 데이터 삭제
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // 새로운 AccountSeq 생성
    public synchronized Long getNextAccountSeq() {
        Long maxSeq = repository.findMaxAccountSeq();
        return (maxSeq == null) ? 1L : maxSeq + 1L;
    }
}
