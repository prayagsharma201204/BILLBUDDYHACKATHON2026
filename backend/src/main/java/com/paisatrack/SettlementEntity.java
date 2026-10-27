package com.paisatrack;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "settlements")
public class SettlementEntity {
    @Id
    @Column(length = 120)
    private String id;
    @Column(nullable = false, length = 80)
    private String fromPerson;
    @Column(nullable = false, length = 80)
    private String toPerson;
    @Column(nullable = false, length = 50)
    private String groupName = "General";
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate date;
    private Long createdAt;

    public SettlementEntity() {}
    public String getId(){return id;} public void setId(String v){id=v;}
    public String getFrom(){return fromPerson;} public void setFrom(String v){fromPerson=v;}
    public String getTo(){return toPerson;} public void setTo(String v){toPerson=v;}
    public String getGroup(){return groupName;} public void setGroup(String v){groupName=(v==null||v.isBlank())?"General":v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate v){date=v;}
    public Long getCreatedAt(){return createdAt;} public void setCreatedAt(Long v){createdAt=v;}
}
