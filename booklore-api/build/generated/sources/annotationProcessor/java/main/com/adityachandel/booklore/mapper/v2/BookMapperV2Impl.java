package com.adityachandel.booklore.mapper.v2;

import com.adityachandel.booklore.mapper.ShelfMapper;
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
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookMapperV2Impl implements BookMapperV2 {

    @Autowired
    private ShelfMapper shelfMapper;

    @Override
    public Book toDTO(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.libraryId( bookEntityLibraryId( bookEntity ) );
        book.libraryName( bookEntityLibraryName( bookEntity ) );
        book.libraryPath( mapLibraryPathIdOnly( bookEntity.getLibraryPath() ) );
        book.metadata( mapMetadata( bookEntity.getMetadata() ) );
        book.id( bookEntity.getId() );
        book.bookType( bookEntity.getBookType() );
        book.fileName( bookEntity.getFileName() );
        book.fileSubPath( bookEntity.getFileSubPath() );
        book.fileSizeKb( bookEntity.getFileSizeKb() );
        book.addedOn( bookEntity.getAddedOn() );
        book.metadataMatchScore( bookEntity.getMetadataMatchScore() );
        book.shelves( shelfEntitySetToShelfSet( bookEntity.getShelves() ) );

        return book.build();
    }

    @Override
    public BookMetadata mapMetadata(BookMetadataEntity metadataEntity) {
        if ( metadataEntity == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.authors( mapAuthors( metadataEntity.getAuthors() ) );
        bookMetadata.categories( mapCategories( metadataEntity.getCategories() ) );
        bookMetadata.moods( mapMoods( metadataEntity.getMoods() ) );
        bookMetadata.tags( mapTags( metadataEntity.getTags() ) );
        bookMetadata.bookId( metadataEntity.getBookId() );
        bookMetadata.title( metadataEntity.getTitle() );
        bookMetadata.subtitle( metadataEntity.getSubtitle() );
        bookMetadata.publisher( metadataEntity.getPublisher() );
        bookMetadata.publishedDate( metadataEntity.getPublishedDate() );
        bookMetadata.description( metadataEntity.getDescription() );
        bookMetadata.seriesName( metadataEntity.getSeriesName() );
        bookMetadata.seriesNumber( metadataEntity.getSeriesNumber() );
        bookMetadata.seriesTotal( metadataEntity.getSeriesTotal() );
        bookMetadata.isbn13( metadataEntity.getIsbn13() );
        bookMetadata.isbn10( metadataEntity.getIsbn10() );
        bookMetadata.pageCount( metadataEntity.getPageCount() );
        bookMetadata.language( metadataEntity.getLanguage() );
        bookMetadata.asin( metadataEntity.getAsin() );
        bookMetadata.amazonRating( metadataEntity.getAmazonRating() );
        bookMetadata.amazonReviewCount( metadataEntity.getAmazonReviewCount() );
        bookMetadata.goodreadsId( metadataEntity.getGoodreadsId() );
        bookMetadata.comicvineId( metadataEntity.getComicvineId() );
        bookMetadata.goodreadsRating( metadataEntity.getGoodreadsRating() );
        bookMetadata.goodreadsReviewCount( metadataEntity.getGoodreadsReviewCount() );
        bookMetadata.hardcoverId( metadataEntity.getHardcoverId() );
        bookMetadata.hardcoverBookId( metadataEntity.getHardcoverBookId() );
        bookMetadata.hardcoverRating( metadataEntity.getHardcoverRating() );
        bookMetadata.hardcoverReviewCount( metadataEntity.getHardcoverReviewCount() );
        bookMetadata.googleId( metadataEntity.getGoogleId() );
        bookMetadata.coverUpdatedOn( metadataEntity.getCoverUpdatedOn() );
        bookMetadata.titleLocked( metadataEntity.getTitleLocked() );
        bookMetadata.subtitleLocked( metadataEntity.getSubtitleLocked() );
        bookMetadata.publisherLocked( metadataEntity.getPublisherLocked() );
        bookMetadata.publishedDateLocked( metadataEntity.getPublishedDateLocked() );
        bookMetadata.descriptionLocked( metadataEntity.getDescriptionLocked() );
        bookMetadata.seriesNameLocked( metadataEntity.getSeriesNameLocked() );
        bookMetadata.seriesNumberLocked( metadataEntity.getSeriesNumberLocked() );
        bookMetadata.seriesTotalLocked( metadataEntity.getSeriesTotalLocked() );
        bookMetadata.isbn13Locked( metadataEntity.getIsbn13Locked() );
        bookMetadata.isbn10Locked( metadataEntity.getIsbn10Locked() );
        bookMetadata.asinLocked( metadataEntity.getAsinLocked() );
        bookMetadata.goodreadsIdLocked( metadataEntity.getGoodreadsIdLocked() );
        bookMetadata.comicvineIdLocked( metadataEntity.getComicvineIdLocked() );
        bookMetadata.hardcoverIdLocked( metadataEntity.getHardcoverIdLocked() );
        bookMetadata.hardcoverBookIdLocked( metadataEntity.getHardcoverBookIdLocked() );
        bookMetadata.googleIdLocked( metadataEntity.getGoogleIdLocked() );
        bookMetadata.pageCountLocked( metadataEntity.getPageCountLocked() );
        bookMetadata.languageLocked( metadataEntity.getLanguageLocked() );
        bookMetadata.amazonRatingLocked( metadataEntity.getAmazonRatingLocked() );
        bookMetadata.amazonReviewCountLocked( metadataEntity.getAmazonReviewCountLocked() );
        bookMetadata.goodreadsRatingLocked( metadataEntity.getGoodreadsRatingLocked() );
        bookMetadata.goodreadsReviewCountLocked( metadataEntity.getGoodreadsReviewCountLocked() );
        bookMetadata.hardcoverRatingLocked( metadataEntity.getHardcoverRatingLocked() );
        bookMetadata.hardcoverReviewCountLocked( metadataEntity.getHardcoverReviewCountLocked() );
        bookMetadata.coverLocked( metadataEntity.getCoverLocked() );
        bookMetadata.authorsLocked( metadataEntity.getAuthorsLocked() );
        bookMetadata.categoriesLocked( metadataEntity.getCategoriesLocked() );
        bookMetadata.moodsLocked( metadataEntity.getMoodsLocked() );
        bookMetadata.tagsLocked( metadataEntity.getTagsLocked() );
        bookMetadata.reviewsLocked( metadataEntity.getReviewsLocked() );

        return bookMetadata.build();
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
}
