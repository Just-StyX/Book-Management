package jsl.jayva.api.service;

import jsl.jayva.model.Review;

public interface ReviewEntityService {
    Review postReviewByUserId(String userId, String bookId, Review review);
}
