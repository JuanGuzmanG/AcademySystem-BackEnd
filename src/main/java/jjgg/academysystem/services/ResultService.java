package jjgg.academysystem.services;

import jjgg.academysystem.entities.Result;

public interface ResultService {
    Result saveResult(Result result);
    Long getAttemptCountForUser(Long userDocument, Long testId);
}
