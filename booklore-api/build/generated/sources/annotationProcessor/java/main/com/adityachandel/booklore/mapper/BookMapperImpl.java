package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.Book;
import com.adityachandel.booklore.model.dto.BookMetadata;
import com.adityachandel.booklore.model.dto.Shelf;
import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.BookMetadataEntity;
import com.adityachandel.booklore.model.entity.LibraryEntity;
import com.adityachandel.booklore.model.entity.ShelfEntity;
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
public class BookMapperImpl implements BookMapper {

    @Autowired
    private BookMetadataMapper bookMetadataMapper;
    @Autowired
    private ShelfMapper shelfMapper;

    @Override
    public Book toBook(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.libraryId( bookEntityLibraryId( bookEntity ) );
        book.libraryName( bookEntityLibraryName( bookEntity ) );
        book.libraryPath( mapLibraryPathIdOnly( bookEntity.getLibraryPath() ) );
        book.metadata( bookMetadataEntityToBookMetadata( bookEntity.getMetadata() ) );
        book.shelves( shelfEntitySetToShelfSet( bookEntity.getShelves() ) );
        book.alternativeFormats( mapAlternativeFormats( bookEntity.getAdditionalFiles() ) );
        book.supplementaryFiles( mapSupplementaryFiles( bookEntity.getAdditionalFiles() ) );
        book.id( bookEntity.getId() );
        book.bookType( bookEntity.getBookType() );
        book.fileName( bookEntity.getFileName() );
        book.fileSubPath( bookEntity.getFileSubPath() );
        book.fileSizeKb( bookEntity.getFileSizeKb() );
        book.addedOn( bookEntity.getAddedOn() );
        book.metadataMatchScore( bookEntity.getMetadataMatchScore() );

        return book.build();
    }

    @Override
    public Book toBookWithDescription(BookEntity bookEntity, boolean includeDescription) {
        if ( bookEntity == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.libraryId( bookEntityLibraryId( bookEntity ) );
        book.libraryName( bookEntityLibraryName( bookEntity ) );
        book.libraryPath( mapLibraryPathIdOnly( bookEntity.getLibraryPath() ) );
        book.metadata( bookMetadataEntityToBookMetadata1( bookEntity.getMetadata(), includeDescription ) );
        book.shelves( shelfEntitySetToShelfSet1( bookEntity.getShelves(), includeDescription ) );
        book.alternativeFormats( mapAlternativeFormats( bookEntity.getAdditionalFiles() ) );
        book.supplementaryFiles( mapSupplementaryFiles( bookEntity.getAdditionalFiles() ) );
        book.id( bookEntity.getId() );
        book.bookType( bookEntity.getBookType() );
        book.fileName( bookEntity.getFileName() );
        book.fileSubPath( bookEntity.getFileSubPath() );
        book.fileSizeKb( bookEntity.getFileSizeKb() );
        book.addedOn( bookEntity.getAddedOn() );
        book.metadataMatchScore( bookEntity.getMetadataMatchScore() );

        return book.build();
    }

    private Long bookEntityLibraryId(BookEntity bookEntity) {
        LibraryEntity library = bookEntity.getLibrary();
        if ( library == null ) {
            return null;
        }
        return library.getId();
    }

    private String bookEntityLibraryName(BookEntity bookEntity) {
        LibraryEntity library = bookEntity.getLibrary();
        if ( library == null ) {
            return null;
        }
        return library.getName();
    }

    protected BookMetadata bookMetadataEntityToBookMetadata(BookMetadataEntity bookMetadataEntity) {
        if ( bookMetadataEntity == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.bookId( bookMetadataEntity.getBookId() );
        bookMetadata.title( bookMetadataEntity.getTitle() );
        bookMetadata.subtitle( bookMetadataEntity.getSubtitle() );
        bookMetadata.publisher( bookMetadataEntity.getPublisher() );
        bookMetadata.publishedDate( bookMetadataEntity.getPublishedDate() );
        bookMetadata.description( bookMetadataEntity.getDescription() );
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
        bookMetadata.authors( mapAuthors( bookMetadataEntity.getAuthors() ) );
        bookMetadata.categories( mapCategories( bookMetadataEntity.getCategories() ) );
        bookMetadata.moods( mapMoods( bookMetadataEntity.getMoods() ) );
        bookMetadata.tags( mapTags( bookMetadataEntity.getTags() ) );
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

        return bookMetadata.build();
    }

    protected Set<Shelf> shelfEntitySetToShelfSet(Set<ShelfEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Shelf> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( ShelfEntity shelfEntity : set ) {
            set1.add( shelfMapper.toShelf( shelfEntity ) );
        }

        return set1;
    }

    protected BookMetadata bookMetadataEntityToBookMetadata1(BookMetadataEntity bookMetadataEntity, boolean includeDescription) {
        if ( bookMetadataEntity == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.bookId( bookMetadataEntity.getBookId() );
        bookMetadata.title( bookMetadataEntity.getTitle() );
        bookMetadata.subtitle( bookMetadataEntity.getSubtitle() );
        bookMetadata.publisher( bookMetadataEntity.getPublisher() );
        bookMetadata.publishedDate( bookMetadataEntity.getPublishedDate() );
        bookMetadata.description( bookMetadataEntity.getDescription() );
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
        bookMetadata.authors( mapAuthors( bookMetadataEntity.getAuthors() ) );
        bookMetadata.categories( mapCategories( bookMetadataEntity.getCategories() ) );
        bookMetadata.moods( mapMoods( bookMetadataEntity.getMoods() ) );
        bookMetadata.tags( mapTags( bookMetadataEntity.getTags() ) );
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

        bookMetadataMapper.mapWithDescriptionCondition( bookMetadataEntity, bookMetadataResult, includeDescription );

        return bookMetadataResult;
    }

    protected Set<Shelf> shelfEntitySetToShelfSet1(Set<ShelfEntity> set, boolean includeDescription) {
        if ( set == null ) {
            return null;
        }

        Set<Shelf> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( ShelfEntity shelfEntity : set ) {
            set1.add( shelfMapper.toShelf( shelfEntity ) );
        }

        return set1;
    }
}
