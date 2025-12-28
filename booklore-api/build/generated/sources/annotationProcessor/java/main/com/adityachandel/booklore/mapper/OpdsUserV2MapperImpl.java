package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.OpdsUserV2;
import com.adityachandel.booklore.model.entity.BookLoreUserEntity;
import com.adityachandel.booklore.model.entity.OpdsUserV2Entity;
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
public class OpdsUserV2MapperImpl implements OpdsUserV2Mapper {

    @Override
    public OpdsUserV2 toDto(OpdsUserV2Entity entity) {
        if ( entity == null ) {
            return null;
        }

        OpdsUserV2.OpdsUserV2Builder opdsUserV2 = OpdsUserV2.builder();

        opdsUserV2.userId( entityUserId( entity ) );
        opdsUserV2.id( entity.getId() );
        opdsUserV2.username( entity.getUsername() );
        opdsUserV2.passwordHash( entity.getPasswordHash() );
        opdsUserV2.sortOrder( entity.getSortOrder() );

        return opdsUserV2.build();
    }

    @Override
    public List<OpdsUserV2> toDto(List<OpdsUserV2Entity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OpdsUserV2> list = new ArrayList<OpdsUserV2>( entities.size() );
        for ( OpdsUserV2Entity opdsUserV2Entity : entities ) {
            list.add( toDto( opdsUserV2Entity ) );
        }

        return list;
    }

    @Override
    public OpdsUserV2Entity toEntity(OpdsUserV2 dto) {
        if ( dto == null ) {
            return null;
        }

        OpdsUserV2Entity.OpdsUserV2EntityBuilder opdsUserV2Entity = OpdsUserV2Entity.builder();

        opdsUserV2Entity.user( opdsUserV2ToBookLoreUserEntity( dto ) );
        opdsUserV2Entity.id( dto.getId() );
        opdsUserV2Entity.username( dto.getUsername() );
        opdsUserV2Entity.passwordHash( dto.getPasswordHash() );
        opdsUserV2Entity.sortOrder( dto.getSortOrder() );

        return opdsUserV2Entity.build();
    }

    private Long entityUserId(OpdsUserV2Entity opdsUserV2Entity) {
        BookLoreUserEntity user = opdsUserV2Entity.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected BookLoreUserEntity opdsUserV2ToBookLoreUserEntity(OpdsUserV2 opdsUserV2) {
        if ( opdsUserV2 == null ) {
            return null;
        }

        BookLoreUserEntity.BookLoreUserEntityBuilder bookLoreUserEntity = BookLoreUserEntity.builder();

        bookLoreUserEntity.id( opdsUserV2.getUserId() );

        return bookLoreUserEntity.build();
    }
}
