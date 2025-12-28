package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.EmailProviderV2;
import com.adityachandel.booklore.model.dto.request.CreateEmailProviderRequest;
import com.adityachandel.booklore.model.entity.EmailProviderV2Entity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class EmailProviderV2MapperImpl implements EmailProviderV2Mapper {

    @Override
    public EmailProviderV2 toDTO(EmailProviderV2Entity entity, Long defaultProviderId) {
        if ( entity == null ) {
            return null;
        }

        EmailProviderV2.EmailProviderV2Builder emailProviderV2 = EmailProviderV2.builder();

        emailProviderV2.id( entity.getId() );
        emailProviderV2.userId( entity.getUserId() );
        emailProviderV2.name( entity.getName() );
        emailProviderV2.host( entity.getHost() );
        emailProviderV2.port( entity.getPort() );
        emailProviderV2.username( entity.getUsername() );
        emailProviderV2.fromAddress( entity.getFromAddress() );
        emailProviderV2.auth( entity.isAuth() );
        emailProviderV2.startTls( entity.isStartTls() );
        emailProviderV2.shared( entity.isShared() );

        emailProviderV2.defaultProvider( entity.getId() != null && entity.getId().equals(defaultProviderId) );

        return emailProviderV2.build();
    }

    @Override
    public EmailProviderV2Entity toEntity(CreateEmailProviderRequest request) {
        if ( request == null ) {
            return null;
        }

        EmailProviderV2Entity.EmailProviderV2EntityBuilder emailProviderV2Entity = EmailProviderV2Entity.builder();

        emailProviderV2Entity.name( request.getName() );
        emailProviderV2Entity.host( request.getHost() );
        if ( request.getPort() != null ) {
            emailProviderV2Entity.port( request.getPort() );
        }
        emailProviderV2Entity.username( request.getUsername() );
        emailProviderV2Entity.password( request.getPassword() );
        emailProviderV2Entity.fromAddress( request.getFromAddress() );
        if ( request.getAuth() != null ) {
            emailProviderV2Entity.auth( request.getAuth() );
        }
        if ( request.getStartTls() != null ) {
            emailProviderV2Entity.startTls( request.getStartTls() );
        }

        return emailProviderV2Entity.build();
    }

    @Override
    public void updateEntityFromRequest(CreateEmailProviderRequest request, EmailProviderV2Entity entity) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            entity.setName( request.getName() );
        }
        if ( request.getHost() != null ) {
            entity.setHost( request.getHost() );
        }
        if ( request.getPort() != null ) {
            entity.setPort( request.getPort() );
        }
        if ( request.getUsername() != null ) {
            entity.setUsername( request.getUsername() );
        }
        if ( request.getPassword() != null ) {
            entity.setPassword( request.getPassword() );
        }
        if ( request.getFromAddress() != null ) {
            entity.setFromAddress( request.getFromAddress() );
        }
        if ( request.getAuth() != null ) {
            entity.setAuth( request.getAuth() );
        }
        if ( request.getStartTls() != null ) {
            entity.setStartTls( request.getStartTls() );
        }
    }
}
