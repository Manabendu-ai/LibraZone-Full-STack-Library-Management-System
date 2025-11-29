package riku.spring.models;

import org.springframework.stereotype.Component;

@Component
public interface Fine {
    Double calculateFine(String due_date, String return_date);
}
