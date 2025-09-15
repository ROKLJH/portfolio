package com.KoreaItAcdemy.OurNotion.budget;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "T_ACCOUNTBOOK") // 실제 테이블 이름
public class BudgetRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // 테이블에 없는 컬럼은 제외해야 함
    private Long id;

    @Column(name = "USERSEQ", nullable = false)
    private Long userSeq;

    @Column(name = "ACCOUNTSEQ", nullable = false)
    private Long accountSeq;

    @Column(name = "INFO", length = 10)
    private String info;

    @Column(name = "AMOUNT", nullable = false, precision = 38, scale = 2)
    private BigDecimal amount;

    @Column(name = "CATEGORY", length = 10)
    private String category;

    @Column(name = "MEMO", length = 100)
    private String memo;

    @Column(name = "ACCOUNTDATE")
    private LocalDate accountDate;

    @Column(name = "LASTDATETIME")
    private LocalDate lastDateTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getAccountSeq() {
        return accountSeq;
    }

    public void setAccountSeq(Long accountSeq) {
        this.accountSeq = accountSeq;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDate getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(LocalDate accountDate) {
        this.accountDate = accountDate;
    }

    public LocalDate getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(LocalDate lastDateTime) {
        this.lastDateTime = lastDateTime;
    }
}
