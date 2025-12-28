package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.Library;
import com.adityachandel.booklore.model.dto.LibraryPath;
import com.adityachandel.booklore.model.entity.LibraryEntity;
import com.adityachandel.booklore.model.entity.LibraryPathEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class LibraryMapperImpl implements LibraryMapper {

    @Override
    public Library toLibrary(LibraryEntity libraryEntity) {
        if ( libraryEntity == null ) {
            return null;
        }

        Library.LibraryBuilder library = Library.builder();

        library.paths( libraryPathEntityListToLibraryPathList( libraryEntity.getLibraryPaths() ) );
        library.id( libraryEntity.getId() );
        library.name( libraryEntity.getName() );
        library.sort( libraryEntity.getSort() );
        library.icon( libraryEntity.getIcon() );
        library.iconType( libraryEntity.getIconType() );
        library.fileNamingPattern( libraryEntity.getFileNamingPattern() );
        library.watch( libraryEntity.isWatch() );
        library.scanMode( libraryEntity.getScanMode() );
        library.defaultBookFormat( libraryEntity.getDefaultBookFormat() );

        return library.build();
    }

    protected LibraryPath libraryPathEntityToLibraryPath(LibraryPathEntity libraryPathEntity) {
        if ( libraryPathEntity == null ) {
            return null;
        }

        LibraryPath.LibraryPathBuilder libraryPath = LibraryPath.builder();

        libraryPath.id( libraryPathEntity.getId() );
        libraryPath.path( libraryPathEntity.getPath() );

        return libraryPath.build();
    }

    protected List<LibraryPath> libraryPathEntityListToLibraryPathList(List<LibraryPathEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<LibraryPath> list1 = new ArrayList<LibraryPath>( list.size() );
        for ( LibraryPathEntity libraryPathEntity : list ) {
            list1.add( libraryPathEntityToLibraryPath( libraryPathEntity ) );
        }

        return list1;
    }
}
