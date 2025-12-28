package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookMetadata;
import com.adityachandel.booklore.model.entity.AuthorEntity;
import com.adityachandel.booklore.model.entity.BookMetadataEntity;
import com.adityachandel.booklore.model.entity.CategoryEntity;
import com.adityachandel.booklore.model.entity.MoodEntity;
import com.adityachandel.booklore.model.entity.TagEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookMetadataMapperImpl implements BookMetadataMapper {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private MoodMapper moodMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public BookMetadata toBookMetadata(BookMetadataEntity bookMetadataEntity, boolean includeDescription) {
        if ( bookMetadataEntity == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.bookId( bookMetadataEntity.getBookId() );
        bookMetadata.title( bookMetadataEntity.getTitle() );
        bookMetadata.subtitle( bookMetadataEntity.getSubtitle() );
        bookMetadata.publisher( bookMetadataEntity.getPublisher() );
        bookMetadata.publishedDate( bookMetadataEntity.getPublishedDate() );
        bookMetadata.seriesName( bookMetadataEntity.getSeriesName() );
        bookMetadata.seriesNumber( bookMetadataEntity.getSeriesNumber() );
        bookMetadata.seriesTotal( bookMetadataEntity.getSeriesTotal() );
        bookMetadata.isbn13( bookMetadataEntity.getIsbn13() );
        bookMetadata.isbn10( bookMetadataEntity.getIsbn10() );
        bookMetadata.pageCount( bookMetadataEntity.getPageCount() );
        bookMetadata.language( bookMetadataEntity.getLanguage() );
        bookMetadata.asin( bookMetadataEntity.getAsin() );
        bookMetadata.amazonRating( bookMetadataEntity.getAmazonRating() );
        bookMetadata.amazonReviewCount( bookMetadataEntity.getAmazonReviewCount() );
        bookMetadata.goodreadsId( bookMetadataEntity.getGoodreadsId() );
        bookMetadata.comicvineId( bookMetadataEntity.getComicvineId() );
        bookMetadata.goodreadsRating( bookMetadataEntity.getGoodreadsRating() );
        bookMetadata.goodreadsReviewCount( bookMetadataEntity.getGoodreadsReviewCount() );
        bookMetadata.hardcoverId( bookMetadataEntity.getHardcoverId() );
        bookMetadata.hardcoverBookId( bookMetadataEntity.getHardcoverBookId() );
        bookMetadata.hardcoverRating( bookMetadataEntity.getHardcoverRating() );
        bookMetadata.hardcoverReviewCount( bookMetadataEntity.getHardcoverReviewCount() );
        bookMetadata.googleId( bookMetadataEntity.getGoogleId() );
        bookMetadata.coverUpdatedOn( bookMetadataEntity.getCoverUpdatedOn() );
        bookMetadata.authors( authorEntitySetToStringSet( bookMetadataEntity.getAuthors(), includeDescription ) );
        bookMetadata.categories( categoryEntitySetToStringSet( bookMetadataEntity.getCategories(), includeDescription ) );
        bookMetadata.moods( moodEntitySetToStringSet( bookMetadataEntity.getMoods(), includeDescription ) );
        bookMetadata.tags( tagEntitySetToStringSet( bookMetadataEntity.getTags(), includeDescription ) );
        bookMetadata.titleLocked( bookMetadataEntity.getTitleLocked() );
        bookMetadata.subtitleLocked( bookMetadataEntity.getSubtitleLocked() );
        bookMetadata.publisherLocked( bookMetadataEntity.getPublisherLocked() );
        bookMetadata.publishedDateLocked( bookMetadataEntity.getPublishedDateLocked() );
        bookMetadata.descriptionLocked( bookMetadataEntity.getDescriptionLocked() );
        bookMetadata.seriesNameLocked( bookMetadataEntity.getSeriesNameLocked() );
        bookMetadata.seriesNumberLocked( bookMetadataEntity.getSeriesNumberLocked() );
        bookMetadata.seriesTotalLocked( bookMetadataEntity.getSeriesTotalLocked() );
        bookMetadata.isbn13Locked( bookMetadataEntity.getIsbn13Locked() );
        bookMetadata.isbn10Locked( bookMetadataEntity.getIsbn10Locked() );
        bookMetadata.asinLocked( bookMetadataEntity.getAsinLocked() );
        bookMetadata.goodreadsIdLocked( bookMetadataEntity.getGoodreadsIdLocked() );
        bookMetadata.comicvineIdLocked( bookMetadataEntity.getComicvineIdLocked() );
        bookMetadata.hardcoverIdLocked( bookMetadataEntity.getHardcoverIdLocked() );
        bookMetadata.hardcoverBookIdLocked( bookMetadataEntity.getHardcoverBookIdLocked() );
        bookMetadata.googleIdLocked( bookMetadataEntity.getGoogleIdLocked() );
        bookMetadata.pageCountLocked( bookMetadataEntity.getPageCountLocked() );
        bookMetadata.languageLocked( bookMetadataEntity.getLanguageLocked() );
        bookMetadata.amazonRatingLocked( bookMetadataEntity.getAmazonRatingLocked() );
        bookMetadata.amazonReviewCountLocked( bookMetadataEntity.getAmazonReviewCountLocked() );
        bookMetadata.goodreadsRatingLocked( bookMetadataEntity.getGoodreadsRatingLocked() );
        bookMetadata.goodreadsReviewCountLocked( bookMetadataEntity.getGoodreadsReviewCountLocked() );
        bookMetadata.hardcoverRatingLocked( bookMetadataEntity.getHardcoverRatingLocked() );
        bookMetadata.hardcoverReviewCountLocked( bookMetadataEntity.getHardcoverReviewCountLocked() );
        bookMetadata.coverLocked( bookMetadataEntity.getCoverLocked() );
        bookMetadata.authorsLocked( bookMetadataEntity.getAuthorsLocked() );
        bookMetadata.categoriesLocked( bookMetadataEntity.getCategoriesLocked() );
        bookMetadata.moodsLocked( bookMetadataEntity.getMoodsLocked() );
        bookMetadata.tagsLocked( bookMetadataEntity.getTagsLocked() );
        bookMetadata.reviewsLocked( bookMetadataEntity.getReviewsLocked() );

        BookMetadata bookMetadataResult = bookMetadata.build();

        mapWithDescriptionCondition( bookMetadataEntity, bookMetadataResult, includeDescription );

        return bookMetadataResult;
    }

    @Override
    public BookMetadata toBookMetadataWithoutRelations(BookMetadataEntity bookMetadataEntity, boolean includeDescription) {
        if ( bookMetadataEntity == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.bookId( bookMetadataEntity.getBookId() );
        bookMetadata.title( bookMetadataEntity.getTitle() );
        bookMetadata.subtitle( bookMetadataEntity.getSubtitle() );
        bookMetadata.publisher( bookMetadataEntity.getPublisher() );
        bookMetadata.publishedDate( bookMetadataEntity.getPublishedDate() );
        bookMetadata.seriesName( bookMetadataEntity.getSeriesName() );
        bookMetadata.seriesNumber( bookMetadataEntity.getSeriesNumber() );
        bookMetadata.seriesTotal( bookMetadataEntity.getSeriesTotal() );
        bookMetadata.isbn13( bookMetadataEntity.getIsbn13() );
        bookMetadata.isbn10( bookMetadataEntity.getIsbn10() );
        bookMetadata.pageCount( bookMetadataEntity.getPageCount() );
        bookMetadata.language( bookMetadataEntity.getLanguage() );
        bookMetadata.asin( bookMetadataEntity.getAsin() );
        bookMetadata.amazonRating( bookMetadataEntity.getAmazonRating() );
        bookMetadata.amazonReviewCount( bookMetadataEntity.getAmazonReviewCount() );
        bookMetadata.goodreadsId( bookMetadataEntity.getGoodreadsId() );
        bookMetadata.comicvineId( bookMetadataEntity.getComicvineId() );
        bookMetadata.goodreadsRating( bookMetadataEntity.getGoodreadsRating() );
        bookMetadata.goodreadsReviewCount( bookMetadataEntity.getGoodreadsReviewCount() );
        bookMetadata.hardcoverId( bookMetadataEntity.getHardcoverId() );
        bookMetadata.hardcoverBookId( bookMetadataEntity.getHardcoverBookId() );
        bookMetadata.hardcoverRating( bookMetadataEntity.getHardcoverRating() );
        bookMetadata.hardcoverReviewCount( bookMetadataEntity.getHardcoverReviewCount() );
        bookMetadata.googleId( bookMetadataEntity.getGoogleId() );
        bookMetadata.coverUpdatedOn( bookMetadataEntity.getCoverUpdatedOn() );
        bookMetadata.titleLocked( bookMetadataEntity.getTitleLocked() );
        bookMetadata.subtitleLocked( bookMetadataEntity.getSubtitleLocked() );
        bookMetadata.publisherLocked( bookMetadataEntity.getPublisherLocked() );
        bookMetadata.publishedDateLocked( bookMetadataEntity.getPublishedDateLocked() );
        bookMetadata.descriptionLocked( bookMetadataEntity.getDescriptionLocked() );
        bookMetadata.seriesNameLocked( bookMetadataEntity.getSeriesNameLocked() );
        bookMetadata.seriesNumberLocked( bookMetadataEntity.getSeriesNumberLocked() );
        bookMetadata.seriesTotalLocked( bookMetadataEntity.getSeriesTotalLocked() );
        bookMetadata.isbn13Locked( bookMetadataEntity.getIsbn13Locked() );
        bookMetadata.isbn10Locked( bookMetadataEntity.getIsbn10Locked() );
        bookMetadata.asinLocked( bookMetadataEntity.getAsinLocked() );
        bookMetadata.goodreadsIdLocked( bookMetadataEntity.getGoodreadsIdLocked() );
        bookMetadata.comicvineIdLocked( bookMetadataEntity.getComicvineIdLocked() );
        bookMetadata.hardcoverIdLocked( bookMetadataEntity.getHardcoverIdLocked() );
        bookMetadata.hardcoverBookIdLocked( bookMetadataEntity.getHardcoverBookIdLocked() );
        bookMetadata.googleIdLocked( bookMetadataEntity.getGoogleIdLocked() );
        bookMetadata.pageCountLocked( bookMetadataEntity.getPageCountLocked() );
        bookMetadata.languageLocked( bookMetadataEntity.getLanguageLocked() );
        bookMetadata.amazonRatingLocked( bookMetadataEntity.getAmazonRatingLocked() );
        bookMetadata.amazonReviewCountLocked( bookMetadataEntity.getAmazonReviewCountLocked() );
        bookMetadata.goodreadsRatingLocked( bookMetadataEntity.getGoodreadsRatingLocked() );
        bookMetadata.goodreadsReviewCountLocked( bookMetadataEntity.getGoodreadsReviewCountLocked() );
        bookMetadata.hardcoverRatingLocked( bookMetadataEntity.getHardcoverRatingLocked() );
        bookMetadata.hardcoverReviewCountLocked( bookMetadataEntity.getHardcoverReviewCountLocked() );
        bookMetadata.coverLocked( bookMetadataEntity.getCoverLocked() );
        bookMetadata.authorsLocked( bookMetadataEntity.getAuthorsLocked() );
        bookMetadata.categoriesLocked( bookMetadataEntity.getCategoriesLocked() );
        bookMetadata.moodsLocked( bookMetadataEntity.getMoodsLocked() );
        bookMetadata.tagsLocked( bookMetadataEntity.getTagsLocked() );
        bookMetadata.reviewsLocked( bookMetadataEntity.getReviewsLocked() );

        BookMetadata bookMetadataResult = bookMetadata.build();

        mapWithDescriptionCondition( bookMetadataEntity, bookMetadataResult, includeDescription );

        return bookMetadataResult;
    }

    protected Set<String> authorEntitySetToStringSet(Set<AuthorEntity> set, boolean includeDescription) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( AuthorEntity authorEntity : set ) {
            set1.add( authorMapper.toAuthorEntityName( authorEntity ) );
        }

        return set1;
    }

    protected Set<String> categoryEntitySetToStringSet(Set<CategoryEntity> set, boolean includeDescription) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( CategoryEntity categoryEntity : set ) {
            set1.add( categoryMapper.toCategoryName( categoryEntity ) );
        }

        return set1;
    }

    protected Set<String> moodEntitySetToStringSet(Set<MoodEntity> set, boolean includeDescription) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( MoodEntity moodEntity : set ) {
            set1.add( moodMapper.toMoodName( moodEntity ) );
        }

        return set1;
    }

    protected Set<String> tagEntitySetToStringSet(Set<TagEntity> set, boolean includeDescription) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( TagEntity tagEntity : set ) {
            set1.add( tagMapper.toTagName( tagEntity ) );
        }

        return set1;
    }
}
