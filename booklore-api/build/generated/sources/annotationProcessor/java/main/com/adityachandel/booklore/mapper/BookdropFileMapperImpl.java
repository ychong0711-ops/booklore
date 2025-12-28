package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookdropFile;
import com.adityachandel.booklore.model.entity.BookdropFileEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookdropFileMapperImpl implements BookdropFileMapper {

    @Override
    public BookdropFile toDto(BookdropFileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookdropFile bookdropFile = new BookdropFile();

        bookdropFile.setOriginalMetadata( jsonToBookMetadata( entity.getOriginalMetadata() ) );
        bookdropFile.setFetchedMetadata( jsonToBookMetadata( entity.getFetchedMetadata() ) );
        bookdropFile.setId( entity.getId() );
        bookdropFile.setFileName( entity.getFileName() );
        bookdropFile.setFilePath( entity.getFilePath() );
        bookdropFile.setFileSize( entity.getFileSize() );
        if ( entity.getCreatedAt() != null ) {
            bookdropFile.setCreatedAt( entity.getCreatedAt().toString() );
        }
        if ( entity.getUpdatedAt() != null ) {
            bookdropFile.setUpdatedAt( entity.getUpdatedAt().toString() );
        }
        bookdropFile.setStatus( entity.getStatus() );

        return bookdropFile;
    }
}
