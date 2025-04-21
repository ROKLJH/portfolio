package com.KoreaItAcdemy.OurNotion.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetRecordController {

    @Autowired
    private BudgetRecordService service;

    @GetMapping
    public List<BudgetRecord> getAllRecords() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetRecord> getRecordById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BudgetRecord createRecord(@RequestBody BudgetRecord record) {
        return service.save(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetRecord> updateRecord(@PathVariable Long id, @RequestBody BudgetRecord record) {
        return service.findById(id)
                .map(existingRecord -> {
                    record.setId(existingRecord.getId()); // 문제 없음
                    return ResponseEntity.ok(service.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}