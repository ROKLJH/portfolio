package com.KoreaItAcdemy.OurNotion.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetRecordService service;

    // 가계부 목록 조회
    @GetMapping
    public String viewBudgetRecords(Model model) {
        List<BudgetRecord> records = service.findAll();
        model.addAttribute("records", records);
        return "budget/list"; // 타임리프 템플릿 경로
    }

    // 신규 추가 또는 수정
    @PostMapping("/save")
    public String saveRecord(@ModelAttribute BudgetRecord record, Model model) {
        try {
            if (record.getId() != null && record.getId() > 0) { // ID가 존재하면 수정
                BudgetRecord existingRecord = service.findById(record.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid record ID: " + record.getId()));

                existingRecord.setInfo(record.getInfo());
                existingRecord.setAmount(record.getAmount());
                existingRecord.setCategory(record.getCategory());
                existingRecord.setMemo(record.getMemo());
                existingRecord.setAccountDate(record.getAccountDate());
                existingRecord.setLastDateTime(LocalDate.now()); // 수정 날짜 갱신

                service.save(existingRecord);
            } else { // ID가 없으면 신규 추가
                record.setUserSeq(1L); // 기본 UserSeq 설정
                record.setAccountSeq(service.getNextAccountSeq()); // 새로운 AccountSeq 할당
                record.setLastDateTime(LocalDate.now()); // 생성 날짜 설정

                service.save(record);
            }
            return "redirect:/budget";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "잘못된 입력입니다: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "예기치 못한 오류가 발생했습니다: " + e.getMessage());
        }
        return "budget/error";
    }


    // 데이터 삭제
    @PostMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id, Model model) {
        try {
            service.deleteById(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid ID: " + e.getMessage());
            return "budget/error"; // 오류 시 렌더링할 페이지
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "budget/error";
        }
        return "redirect:/budget";
    }

    //데이타 업데이트
    @PutMapping("/{id}")
    public String updateRecord(@PathVariable Long id, @ModelAttribute BudgetRecord record, Model model) {
        try {
            BudgetRecord existingRecord = service.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid record ID: " + id));

            existingRecord.setInfo(record.getInfo());
            existingRecord.setAmount(record.getAmount());
            existingRecord.setCategory(record.getCategory());
            existingRecord.setMemo(record.getMemo());
            existingRecord.setAccountDate(record.getAccountDate());
            existingRecord.setLastDateTime(LocalDate.now()); // 수정 날짜 갱신

            service.save(existingRecord);
            return "redirect:/budget";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "잘못된 입력입니다: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "예기치 못한 오류가 발생했습니다: " + e.getMessage());
        }
        return "budget/error";
    }
}
