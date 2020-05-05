package innometrics.sonarqube.tables;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@EnableAutoConfiguration
@Entity
@Table()
public class Maintainability {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String new_technical_debt;
    @Column
    private String code_smells;
    @Column
    private String sqale_rating;
    @Column
    private String new_code_smells;

    public Long getId() {
        return id;
    }

    public String getCode_smells() {
        return code_smells;
    }

    public String getNew_code_smells() {
        return new_code_smells;
    }

    public String getNew_technical_debt() {
        return new_technical_debt;
    }

    public String getSqale_rating() {
        return sqale_rating;
    }

    public void setCode_smells(String code_smells) {
        this.code_smells = code_smells;
    }

    public void setNew_code_smells(String new_code_smells) {
        this.new_code_smells = new_code_smells;
    }

    public void setNew_technical_debt(String new_technical_debt) {
        this.new_technical_debt = new_technical_debt;
    }

    public void setSqale_rating(String sqale_rating) {
        this.sqale_rating = sqale_rating;
    }
}
