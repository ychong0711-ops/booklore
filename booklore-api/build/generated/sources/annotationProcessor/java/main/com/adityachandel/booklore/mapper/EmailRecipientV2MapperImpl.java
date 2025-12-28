package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.EmailRecipientV2;
import com.adityachandel.booklore.model.dto.request.CreateEmailRecipientRequest;
import com.adityachandel.booklore.model.entity.EmailRecipientV2Entity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class EmailRecipientV2MapperImpl implements EmailRecipientV2Mapper {

    @Override
    public EmailRecipientV2 toDTO(EmailRecipientV2Entity entity) {
        if ( entity == null ) {
            return null;
        }

        EmailRecipientV2.EmailRecipientV2Builder emailRecipientV2 = EmailRecipientV2.builder();

        emailRecipientV2.id( entity.getId() );
        emailRecipientV2.userId( entity.getUserId() );
        emailRecipientV2.email( entity.getEmail() );
        emailRecipientV2.name( entity.getName() );
        emailRecipientV2.defaultRecipient( entity.isDefaultRecipient() );

        return emailRecipientV2.build();
    }

    @Override
    public EmailRecipientV2Entity toEntity(EmailRecipientV2 emailRecipient) {
        if ( emailRecipient == null ) {
            return null;
        }

        EmailRecipientV2Entity.EmailRecipientV2EntityBuilder emailRecipientV2Entity = EmailRecipientV2Entity.builder();

        emailRecipientV2Entity.id( emailRecipient.getId() );
        emailRecipientV2Entity.userId( emailRecipient.getUserId() );
        emailRecipientV2Entity.email( emailRecipient.getEmail() );
        emailRecipientV2Entity.name( emailRecipient.getName() );
        emailRecipientV2Entity.defaultRecipient( emailRecipient.isDefaultRecipient() );

        return emailRecipientV2Entity.build();
    }

    @Override
    public EmailRecipientV2Entity toEntity(CreateEmailRecipientRequest createRequest) {
        if ( createRequest == null ) {
            return null;
        }

        EmailRecipientV2Entity.EmailRecipientV2EntityBuilder emailRecipientV2Entity = EmailRecipientV2Entity.builder();

        emailRecipientV2Entity.email( createRequest.getEmail() );
        emailRecipientV2Entity.name( createRequest.getName() );
        emailRecipientV2Entity.defaultRecipient( createRequest.isDefaultRecipient() );

        return emailRecipientV2Entity.build();
    }

    @Override
    public void updateEntityFromRequest(CreateEmailRecipientRequest request, EmailRecipientV2Entity entity) {
        if ( request == null ) {
            return;
        }

        entity.setEmail( request.getEmail() );
        entity.setName( request.getName() );
        entity.setDefaultRecipient( request.isDefaultRecipient() );
    }
}
