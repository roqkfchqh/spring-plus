package org.example.expert.domain.common.logging;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.common.entity.Timestamped;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "log")
public class Log extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public static Log of(String message) {
        Log log = new Log();
        log.message = message;
        return log;
    }
}
