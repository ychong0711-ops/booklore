package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookReview;
import com.adityachandel.booklore.model.entity.BookReviewEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookReviewMapperImpl implements BookReviewMapper {

    @Override
    public BookReview toDto(BookReviewEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookReview.BookReviewBuilder bookReview = BookReview.builder();

        bookReview.id( entity.getId() );
        bookReview.metadataProvider( entity.getMetadataProvider() );
        bookReview.reviewerName( entity.getReviewerName() );
        bookReview.title( entity.getTitle() );
        bookReview.rating( entity.getRating() );
        bookReview.date( entity.getDate() );
        bookReview.body( entity.getBody() );
        bookReview.country( entity.getCountry() );
        bookReview.spoiler( entity.getSpoiler() );
        bookReview.followersCount( entity.getFollowersCount() );
        bookReview.textReviewsCount( entity.getTextReviewsCount() );

        return bookReview.build();
    }

    @Override
    public BookReviewEntity toEntity(BookReview dto) {
        if ( dto == null ) {
            return null;
        }

        BookReviewEntity.BookReviewEntityBuilder bookReviewEntity = BookReviewEntity.builder();

        bookReviewEntity.metadataProvider( dto.getMetadataProvider() );
        bookReviewEntity.reviewerName( dto.getReviewerName() );
        bookReviewEntity.title( dto.getTitle() );
        bookReviewEntity.rating( dto.getRating() );
        bookReviewEntity.date( dto.getDate() );
        bookReviewEntity.body( dto.getBody() );
        bookReviewEntity.country( dto.getCountry() );
        bookReviewEntity.spoiler( dto.getSpoiler() );
        bookReviewEntity.followersCount( dto.getFollowersCount() );
        bookReviewEntity.textReviewsCount( dto.getTextReviewsCount() );

        return bookReviewEntity.build();
    }
}
