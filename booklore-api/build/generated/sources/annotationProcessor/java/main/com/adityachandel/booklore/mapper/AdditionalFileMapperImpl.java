package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.AdditionalFile;
import com.adityachandel.booklore.model.entity.BookAdditionalFileEntity;
import com.adityachandel.booklore.model.entity.BookEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class AdditionalFileMapperImpl implements AdditionalFileMapper {

    @Override
    public AdditionalFile toAdditionalFile(BookAdditionalFileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AdditionalFile.AdditionalFileBuilder additionalFile = AdditionalFile.builder();

        additionalFile.bookId( entityBookId( entity ) );
        additionalFile.filePath( mapFilePath( entity ) );
        additionalFile.id( entity.getId() );
        additionalFile.fileName( entity.getFileName() );
        additionalFile.fileSubPath( entity.getFileSubPath() );
        additionalFile.additionalFileType( entity.getAdditionalFileType() );
        additionalFile.fileSizeKb( entity.getFileSizeKb() );
        additionalFile.description( entity.getDescription() );
        additionalFile.addedOn( entity.getAddedOn() );

        return additionalFile.build();
    }

    @Override
    public List<AdditionalFile> toAdditionalFiles(List<BookAdditionalFileEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AdditionalFile> list = new ArrayList<AdditionalFile>( entities.size() );
        for ( BookAdditionalFileEntity bookAdditionalFileEntity : entities ) {
            list.add( toAdditionalFile( bookAdditionalFileEntity ) );
        }

        return list;
    }

    private Long entityBookId(BookAdditionalFileEntity bookAdditionalFileEntity) {
        BookEntity book = bookAdditionalFileEntity.getBook();
        if ( book == null ) {
            return null;
        }
        return book.getId();
    }
}
