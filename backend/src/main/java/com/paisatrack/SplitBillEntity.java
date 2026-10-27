package com.paisatrack;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "split_bills")
public class SplitBillEntity {
    @Id
    @Column(length = 120)
    private String id;
    @Column(nullable = false, length = 70)
    private String title;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false, length = 60)
    private String payer;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "split_bill_participants", joinColumns = @JoinColumn(name = "bill_id"))
    @Column(name = "participant_name", nullable = false, length = 80)
    private List<String> participants = new ArrayList<>();
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 40)
    private String category;
    @Column(nullable = false, length = 50)
    private String groupName = "General";
    private boolean settled;
    private Long createdAt;
    @Column(length = 120)
    private String personalTransactionId;

    public SplitBillEntity() {}
    public String getId(){return id;} public void setId(String v){id=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
    public String getPayer(){return payer;} public void setPayer(String v){payer=v;}
    public List<String> getParticipants(){return participants;} public void setParticipants(List<String> v){participants=v==null?new ArrayList<>():v;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate v){date=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getGroup(){return groupName;} public void setGroup(String v){groupName=(v==null||v.isBlank())?"General":v;}
    public boolean isSettled(){return settled;} public void setSettled(boolean v){settled=v;}
    public Long getCreatedAt(){return createdAt;} public void setCreatedAt(Long v){createdAt=v;}
    public String getPersonalTransactionId(){return personalTransactionId;} public void setPersonalTransactionId(String v){personalTransactionId=v;}
}
