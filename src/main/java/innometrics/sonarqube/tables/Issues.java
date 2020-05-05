package innometrics.sonarqube.tables;

import javax.persistence.*;

@Entity
@Table
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String blockerViolations;
    @Column
    private String violations;
    @Column
    private String majorViolations;
    @Column
    private String minorViolations;
    @Column
    private String newBlockerViolations;
    @Column
    private String newCriticalViolations;
    @Column
    private String newMajorViolations;
    @Column
    private String newMinorViolations;
    @Column
    private String reopenedIssues;

    public Long getId() {
        return id;
    }

    public String getMinorViolations() {
        return minorViolations;
    }

    public void setMinorViolations(String minorViolations) {
        this.minorViolations = minorViolations;
    }

    public String getBlockerViolations() {
        return blockerViolations;
    }

    public String getMajorViolations() {
        return majorViolations;
    }

    public String getViolations() {
        return violations;
    }

    public String getNewBlockerViolations() {
        return newBlockerViolations;
    }

    public String getNewCriticalViolations() {
        return newCriticalViolations;
    }

    public String getNewMajorViolations() {
        return newMajorViolations;
    }

    public String getNewMinorViolations() {
        return newMinorViolations;
    }

    public String getReopenedIssues() {
        return reopenedIssues;
    }

    public void setBlockerViolations(String blockerViolations) {
        this.blockerViolations = blockerViolations;
    }

    public void setMajorViolations(String majorViolations) {
        this.majorViolations = majorViolations;
    }

    public void setNewBlockerViolations(String newBlockerViolations) {
        this.newBlockerViolations = newBlockerViolations;
    }

    public void setNewCriticalViolations(String newCriticalViolations) {
        this.newCriticalViolations = newCriticalViolations;
    }

    public void setNewMajorViolations(String newMajorViolations) {
        this.newMajorViolations = newMajorViolations;
    }

    public void setNewMinorViolations(String newMinorViolations) {
        this.newMinorViolations = newMinorViolations;
    }

    public void setReopenedIssues(String reopenedIssues) {
        this.reopenedIssues = reopenedIssues;
    }

    public void setViolations(String violations) {
        this.violations = violations;
    }
}
