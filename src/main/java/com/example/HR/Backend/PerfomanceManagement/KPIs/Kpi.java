package com.example.HR.Backend.PerformanceManagement.KPIs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Kpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kpiName;
    private String description;
    private String unitOfMeasure;
    private String aggregation;
    private String target;
    private String category;
    private String targetType;

    // Operational Audit
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String postedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private char posted = 'N'; // Changed to primitive char instead of Character
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime postedTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String verifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private char verifiedFlag = 'N';
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime verifiedTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String deletedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private char deletedFlag = 'N';
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private char modifiedFlag = 'N';
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedTime;

    public void setPostedFlag(char y) {
    }

    public void setPostedBy(String currentUser) {
    }

    public void setPostedTime(LocalDateTime now) {
    }

    // Getters and setters...
}
