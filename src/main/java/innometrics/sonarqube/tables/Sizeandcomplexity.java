package innometrics.sonarqube.tables;

import javax.persistence.*;

@Entity
@Table
public class Sizeandcomplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String classes;
    @Column
    private String lines;
    @Column
    private String ncloc;
    @Column
    private String complexityInClasses;

    public Long getId() {
        return id;
    }

    public String getClasses() {
        return classes;
    }

    public String getComplexityInClasses() {
        return complexityInClasses;
    }

    public String getLines() {
        return lines;
    }

    public String getNcloc() {
        return ncloc;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setComplexityInClasses(String complexityInClasses) {
        this.complexityInClasses = complexityInClasses;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public void setNcloc(String ncloc) {
        this.ncloc = ncloc;
    }
}
