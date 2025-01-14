package org.example.expert.domain.common.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService implements BaseLogService {

    private final LogRepository logRepository;

    @Override
    public void saveLog(String message){
        Log log = Log.of(message);
        logRepository.save(log);
    }
}
