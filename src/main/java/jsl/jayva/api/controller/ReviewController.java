package jsl.jayva.api.controller;

import jakarta.validation.Valid;
import jsl.jayva.api.ReviewApi;
import jsl.jayva.api.service.ReviewEntityService;
import jsl.jayva.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController implements ReviewApi {
    private final ReviewEntityService reviewEntityService;

    public ReviewController(ReviewEntityService reviewEntityService) {
        this.reviewEntityService = reviewEntityService;
    }

    @Override
    public ResponseEntity<Review> postReviewByUserId(String userId, String bookId, @Valid Review review) {
        return ResponseEntity.ok(reviewEntityService.postReviewByUserId(userId, bookId, review));
    }
}
