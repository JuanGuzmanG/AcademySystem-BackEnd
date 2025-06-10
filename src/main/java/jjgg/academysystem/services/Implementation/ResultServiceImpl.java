package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Result;
import jjgg.academysystem.repositories.ResultRepository;
import jjgg.academysystem.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }
    @Override
    public Long getAttemptCountForUser(Long userDocument, Long testId){
        return resultRepository.countByUserAndTest(userDocument, testId);
    }
}
