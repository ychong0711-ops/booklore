package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.Shelf;
import com.adityachandel.booklore.model.entity.BookLoreUserEntity;
import com.adityachandel.booklore.model.entity.ShelfEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ShelfMapperImpl implements ShelfMapper {

    @Override
    public Shelf toShelf(ShelfEntity shelfEntity) {
        if ( shelfEntity == null ) {
            return null;
        }

        Shelf.ShelfBuilder shelf = Shelf.builder();

        shelf.userId( shelfEntityUserId( shelfEntity ) );
        shelf.id( shelfEntity.getId() );
        shelf.name( shelfEntity.getName() );
        shelf.icon( shelfEntity.getIcon() );
        shelf.iconType( shelfEntity.getIconType() );
        shelf.sort( shelfEntity.getSort() );

        return shelf.build();
    }

    private Long shelfEntityUserId(ShelfEntity shelfEntity) {
        BookLoreUserEntity user = shelfEntity.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
