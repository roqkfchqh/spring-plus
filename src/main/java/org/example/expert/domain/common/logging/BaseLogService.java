package org.example.expert.domain.common.logging;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface BaseLogService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveLog(String message);
}
