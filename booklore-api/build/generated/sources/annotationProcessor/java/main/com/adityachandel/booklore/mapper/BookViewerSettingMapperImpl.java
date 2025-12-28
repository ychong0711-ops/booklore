package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookViewerSetting;
import com.adityachandel.booklore.model.entity.PdfViewerPreferencesEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookViewerSettingMapperImpl implements BookViewerSettingMapper {

    @Override
    public BookViewerSetting toBookViewerSetting(PdfViewerPreferencesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookViewerSetting.BookViewerSettingBuilder bookViewerSetting = BookViewerSetting.builder();

        bookViewerSetting.zoom( entity.getZoom() );
        bookViewerSetting.spread( entity.getSpread() );

        return bookViewerSetting.build();
    }
}
