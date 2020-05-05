package innometrics.sonarqube.tables;

import javax.persistence.*;

@Entity
@Table
public class Security {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String newSecurityHotspots;
    @Column
    private String securityHotspots;
    @Column
    private String newSecurityHotspotsReviewed;
    @Column
    private String newVulnerabilities;
    @Column
    private String securityRating;

    public String getNewSecurityHotspots() {
        return newSecurityHotspots;
    }

    public String getNewSecurityHotspotsReviewed() {
        return newSecurityHotspotsReviewed;
    }

    public String getNewVulnerabilities() {
        return newVulnerabilities;
    }

    public String getSecurityHotspots() {
        return securityHotspots;
    }

    public String getSecurityRating() {
        return securityRating;
    }

    public void setNewSecurityHotspots(String newSecurityHotspots) {
        this.newSecurityHotspots = newSecurityHotspots;
    }

    public void setNewSecurityHotspotsReviewed(String newSecurityHotspotsReviewed) {
        this.newSecurityHotspotsReviewed = newSecurityHotspotsReviewed;
    }

    public void setNewVulnerabilities(String newVulnerabilities) {
        this.newVulnerabilities = newVulnerabilities;
    }

    public void setSecurityHotspots(String securityHotspots) {
        this.securityHotspots = securityHotspots;
    }

    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }
}
