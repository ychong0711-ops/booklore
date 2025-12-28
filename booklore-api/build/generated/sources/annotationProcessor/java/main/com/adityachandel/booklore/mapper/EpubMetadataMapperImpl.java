package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookMetadata;
import com.adityachandel.booklore.model.dto.EpubMetadata;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class EpubMetadataMapperImpl implements EpubMetadataMapper {

    @Override
    public EpubMetadata toEpubMetadata(BookMetadata bookMetadata) {
        if ( bookMetadata == null ) {
            return null;
        }

        EpubMetadata.EpubMetadataBuilder epubMetadata = EpubMetadata.builder();

        epubMetadata.bookId( bookMetadata.getBookId() );
        epubMetadata.title( bookMetadata.getTitle() );
        epubMetadata.subtitle( bookMetadata.getSubtitle() );
        epubMetadata.publisher( bookMetadata.getPublisher() );
        epubMetadata.publishedDate( bookMetadata.getPublishedDate() );
        epubMetadata.description( bookMetadata.getDescription() );
        epubMetadata.seriesName( bookMetadata.getSeriesName() );
        epubMetadata.seriesNumber( bookMetadata.getSeriesNumber() );
        epubMetadata.seriesTotal( bookMetadata.getSeriesTotal() );
        epubMetadata.isbn13( bookMetadata.getIsbn13() );
        epubMetadata.isbn10( bookMetadata.getIsbn10() );
        epubMetadata.pageCount( bookMetadata.getPageCount() );
        epubMetadata.language( bookMetadata.getLanguage() );
        epubMetadata.asin( bookMetadata.getAsin() );
        epubMetadata.amazonRating( bookMetadata.getAmazonRating() );
        epubMetadata.amazonReviewCount( bookMetadata.getAmazonReviewCount() );
        epubMetadata.goodreadsId( bookMetadata.getGoodreadsId() );
        epubMetadata.comicvineId( bookMetadata.getComicvineId() );
        epubMetadata.goodreadsRating( bookMetadata.getGoodreadsRating() );
        epubMetadata.goodreadsReviewCount( bookMetadata.getGoodreadsReviewCount() );
        epubMetadata.hardcoverId( bookMetadata.getHardcoverId() );
        epubMetadata.hardcoverRating( bookMetadata.getHardcoverRating() );
        epubMetadata.hardcoverReviewCount( bookMetadata.getHardcoverReviewCount() );
        epubMetadata.googleId( bookMetadata.getGoogleId() );
        Set<String> set = bookMetadata.getAuthors();
        if ( set != null ) {
            epubMetadata.authors( new LinkedHashSet<String>( set ) );
        }
        Set<String> set1 = bookMetadata.getCategories();
        if ( set1 != null ) {
            epubMetadata.categories( new LinkedHashSet<String>( set1 ) );
        }

        return epubMetadata.build();
    }

    @Override
    public BookMetadata toBookMetadata(EpubMetadata epubMetadata) {
        if ( epubMetadata == null ) {
            return null;
        }

        BookMetadata.BookMetadataBuilder bookMetadata = BookMetadata.builder();

        bookMetadata.bookId( epubMetadata.getBookId() );
        bookMetadata.title( epubMetadata.getTitle() );
        bookMetadata.subtitle( epubMetadata.getSubtitle() );
        bookMetadata.publisher( epubMetadata.getPublisher() );
        bookMetadata.publishedDate( epubMetadata.getPublishedDate() );
        bookMetadata.description( epubMetadata.getDescription() );
        bookMetadata.seriesName( epubMetadata.getSeriesName() );
        bookMetadata.seriesNumber( epubMetadata.getSeriesNumber() );
        bookMetadata.seriesTotal( epubMetadata.getSeriesTotal() );
        bookMetadata.isbn13( epubMetadata.getIsbn13() );
        bookMetadata.isbn10( epubMetadata.getIsbn10() );
        bookMetadata.pageCount( epubMetadata.getPageCount() );
        bookMetadata.language( epubMetadata.getLanguage() );
        bookMetadata.asin( epubMetadata.getAsin() );
        bookMetadata.amazonRating( epubMetadata.getAmazonRating() );
        bookMetadata.amazonReviewCount( epubMetadata.getAmazonReviewCount() );
        bookMetadata.goodreadsId( epubMetadata.getGoodreadsId() );
        bookMetadata.comicvineId( epubMetadata.getComicvineId() );
        bookMetadata.goodreadsRating( epubMetadata.getGoodreadsRating() );
        bookMetadata.goodreadsReviewCount( epubMetadata.getGoodreadsReviewCount() );
        bookMetadata.hardcoverId( epubMetadata.getHardcoverId() );
        bookMetadata.hardcoverRating( epubMetadata.getHardcoverRating() );
        bookMetadata.hardcoverReviewCount( epubMetadata.getHardcoverReviewCount() );
        bookMetadata.googleId( epubMetadata.getGoogleId() );
        Set<String> set = epubMetadata.getAuthors();
        if ( set != null ) {
            bookMetadata.authors( new LinkedHashSet<String>( set ) );
        }
        Set<String> set1 = epubMetadata.getCategories();
        if ( set1 != null ) {
            bookMetadata.categories( new LinkedHashSet<String>( set1 ) );
        }

        return bookMetadata.build();
    }
}
