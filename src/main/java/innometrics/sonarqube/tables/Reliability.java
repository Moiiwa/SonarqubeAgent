package innometrics.sonarqube.tables;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table
public class Reliability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String bugs;
    @Column
    private String newBugs;
    @Column
    private String alertStatus;
    @Column
    private String reliabilityRating;

    public Long getId() {
        return id;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public String getBugs() {
        return bugs;
    }

    public String getNewBugs() {
        return newBugs;
    }

    public String getReliabilityRating() {
        return reliabilityRating;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    public void setNewBugs(String newBugs) {
        this.newBugs = newBugs;
    }

    public void setReliabilityRating(String reliabilityRating) {
        this.reliabilityRating = reliabilityRating;
    }
}
