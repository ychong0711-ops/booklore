package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.MetadataClearFlags;
import com.adityachandel.booklore.model.dto.request.BulkMetadataUpdateRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class MetadataClearFlagsMapperImpl implements MetadataClearFlagsMapper {

    @Override
    public MetadataClearFlags toClearFlags(BulkMetadataUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        MetadataClearFlags metadataClearFlags = new MetadataClearFlags();

        metadataClearFlags.setPublisher( request.isClearPublisher() );
        metadataClearFlags.setPublishedDate( request.isClearPublishedDate() );
        metadataClearFlags.setSeriesName( request.isClearSeriesName() );
        metadataClearFlags.setSeriesTotal( request.isClearSeriesTotal() );
        metadataClearFlags.setLanguage( request.isClearLanguage() );
        metadataClearFlags.setAuthors( request.isClearAuthors() );
        metadataClearFlags.setCategories( request.isClearGenres() );
        metadataClearFlags.setMoods( request.isClearMoods() );
        metadataClearFlags.setTags( request.isClearTags() );

        return metadataClearFlags;
    }
}
