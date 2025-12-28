package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.PdfViewerPreferences;
import com.adityachandel.booklore.model.entity.PdfViewerPreferencesEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class PdfViewerPreferencesMapperImpl implements PdfViewerPreferencesMapper {

    @Override
    public PdfViewerPreferences toModel(PdfViewerPreferencesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PdfViewerPreferences.PdfViewerPreferencesBuilder pdfViewerPreferences = PdfViewerPreferences.builder();

        pdfViewerPreferences.bookId( entity.getBookId() );
        pdfViewerPreferences.zoom( entity.getZoom() );
        pdfViewerPreferences.spread( entity.getSpread() );

        return pdfViewerPreferences.build();
    }

    @Override
    public PdfViewerPreferencesEntity toEntity(PdfViewerPreferences model) {
        if ( model == null ) {
            return null;
        }

        PdfViewerPreferencesEntity.PdfViewerPreferencesEntityBuilder pdfViewerPreferencesEntity = PdfViewerPreferencesEntity.builder();

        pdfViewerPreferencesEntity.bookId( model.getBookId() );
        pdfViewerPreferencesEntity.zoom( model.getZoom() );
        pdfViewerPreferencesEntity.spread( model.getSpread() );

        return pdfViewerPreferencesEntity.build();
    }
}
