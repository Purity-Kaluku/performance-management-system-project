package com.example.HR.Backend.Employees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String firstName;
    private String emailAddress;
    private String lastName;
    private String department;
    private String jobTitle;
    private int employeeId;

    //    Operational Audit
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String postedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character postedFlag ='N';
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime postedTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedOn;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String deletedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character deletedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedOn;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character modifiedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String verifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character verifiedFlag ='N';
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime verifiedTime;

}
