package model;

import java.time.LocalDate;

public class PensionPlan {
    private String planReferenceNo;
    private LocalDate enrollmentDate;
    private Double monthlyContribution;

    public PensionPlan(String planReferenceNo, LocalDate enrollmentDate, Double monthlyContribution) {
        this.planReferenceNo = planReferenceNo;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
    }

    public String getPlanReferenceNo() {
        return planReferenceNo;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setPlanReferenceNo(String planReferenceNo) {
        this.planReferenceNo = planReferenceNo;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }
}
