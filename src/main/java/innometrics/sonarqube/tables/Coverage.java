package innometrics.sonarqube.tables;

import javax.persistence.*;

@Entity
@Table
public class Coverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String branchCoverage;
    @Column
    private String coverage;
    @Column
    private String lineCoverage;

    public Long getId() {
        return id;
    }

    public String getBranchCoverage() {
        return branchCoverage;
    }

    public String getCoverage() {
        return coverage;
    }

    public String getLineCoverage() {
        return lineCoverage;
    }

    public void setBranchCoverage(String branchCoverage) {
        this.branchCoverage = branchCoverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public void setLineCoverage(String lineCoverage) {
        this.lineCoverage = lineCoverage;
    }
}
