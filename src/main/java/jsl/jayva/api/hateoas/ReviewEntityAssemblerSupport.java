package jsl.jayva.api.hateoas;

import jsl.jayva.api.controller.ReviewController;
import jsl.jayva.api.entities.ReviewEntity;
import jsl.jayva.model.Review;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReviewEntityAssemblerSupport extends RepresentationModelAssemblerSupport<Review, Review> {
    public ReviewEntityAssemblerSupport() {
        super(ReviewController.class, Review.class);
    }

    @Override
    public Review toModel(Review review) {
        review.add(linkTo(methodOn(ReviewController.class).postReviewByUserId("user-id", "book-id", review)).withSelfRel());
        return review;
    }
}
