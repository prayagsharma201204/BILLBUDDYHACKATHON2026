package com.paisatrack;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @Column(length = 100)
    private String id;
    @Column(nullable = false, length = 60)
    private String title;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false, length = 10)
    private String type;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 40)
    private String category;
    @Column(length = 160)
    private String note;
    private Long createdAt;
    private String splitBillId;

    public TransactionEntity() {}
    public String getId(){return id;} public void setId(String v){id=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
    public String getType(){return type;} public void setType(String v){type=v;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate v){date=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getNote(){return note;} public void setNote(String v){note=v;}
    public Long getCreatedAt(){return createdAt;} public void setCreatedAt(Long v){createdAt=v;}
    public String getSplitBillId(){return splitBillId;} public void setSplitBillId(String v){splitBillId=v;}
}
