package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.KoboSnapshotBookEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookEntityToKoboSnapshotBookMapperImpl implements BookEntityToKoboSnapshotBookMapper {

    @Override
    public KoboSnapshotBookEntity toKoboSnapshotBook(BookEntity book) {
        if ( book == null ) {
            return null;
        }

        KoboSnapshotBookEntity.KoboSnapshotBookEntityBuilder koboSnapshotBookEntity = KoboSnapshotBookEntity.builder();

        koboSnapshotBookEntity.bookId( book.getId() );
        koboSnapshotBookEntity.synced( false );

        return koboSnapshotBookEntity.build();
    }
}
