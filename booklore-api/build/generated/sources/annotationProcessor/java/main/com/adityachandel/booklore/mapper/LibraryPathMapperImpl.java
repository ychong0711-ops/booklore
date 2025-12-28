package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.LibraryPath;
import com.adityachandel.booklore.model.entity.LibraryPathEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class LibraryPathMapperImpl implements LibraryPathMapper {

    @Override
    public LibraryPath toLibraryPath(LibraryPathEntity libraryPathEntity) {
        if ( libraryPathEntity == null ) {
            return null;
        }

        LibraryPath.LibraryPathBuilder libraryPath = LibraryPath.builder();

        libraryPath.id( libraryPathEntity.getId() );
        libraryPath.path( libraryPathEntity.getPath() );

        return libraryPath.build();
    }
}
