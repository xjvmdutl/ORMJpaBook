package domain;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isWork(){
        //해당 메소드 안에서 응집성 있게 만들수 있다.
        return true;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
