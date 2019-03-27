package models;

import java.time.LocalDate;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class SolarPanels {
    /*@Getter @Setter*/  private Boolean solarpanel;
    /*@Getter @Setter*/  private LocalDate date;

    public SolarPanels(Boolean solarpanel, LocalDate date) {
        this.solarpanel = solarpanel;
        this.date = date;
    }

    public SolarPanels() {
    }

    public Boolean getSolarpanel() {
        return solarpanel;
    }

    public void setSolarpanel(Boolean solarpanel) {
        this.solarpanel = solarpanel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SolarPanels{" +
                "solarpanel=" + solarpanel +
                ", date=" + date +
                '}';
    }
}
