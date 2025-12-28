package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.KoreaderUser;
import com.adityachandel.booklore.model.entity.KoreaderUserEntity;
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
public class KoreaderUserMapperImpl implements KoreaderUserMapper {

    @Override
    public KoreaderUser toDto(KoreaderUserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String password = null;
        String passwordMD5 = null;
        boolean syncEnabled = false;

        id = entity.getId();
        username = entity.getUsername();
        password = entity.getPassword();
        passwordMD5 = entity.getPasswordMD5();
        syncEnabled = entity.isSyncEnabled();

        KoreaderUser koreaderUser = new KoreaderUser( id, username, password, passwordMD5, syncEnabled );

        return koreaderUser;
    }

    @Override
    public KoreaderUserEntity toEntity(KoreaderUser dto) {
        if ( dto == null ) {
            return null;
        }

        KoreaderUserEntity.KoreaderUserEntityBuilder koreaderUserEntity = KoreaderUserEntity.builder();

        koreaderUserEntity.id( dto.getId() );
        koreaderUserEntity.username( dto.getUsername() );
        koreaderUserEntity.password( dto.getPassword() );
        koreaderUserEntity.passwordMD5( dto.getPasswordMD5() );
        koreaderUserEntity.syncEnabled( dto.isSyncEnabled() );

        return koreaderUserEntity.build();
    }

    @Override
    public List<KoreaderUser> toDtoList(List<KoreaderUserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<KoreaderUser> list = new ArrayList<KoreaderUser>( entities.size() );
        for ( KoreaderUserEntity koreaderUserEntity : entities ) {
            list.add( toDto( koreaderUserEntity ) );
        }

        return list;
    }
}
