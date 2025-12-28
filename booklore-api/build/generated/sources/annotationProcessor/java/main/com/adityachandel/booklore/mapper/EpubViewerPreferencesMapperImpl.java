package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.EpubViewerPreferences;
import com.adityachandel.booklore.model.entity.EpubViewerPreferencesEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class EpubViewerPreferencesMapperImpl implements EpubViewerPreferencesMapper {

    @Override
    public EpubViewerPreferences toModel(EpubViewerPreferencesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EpubViewerPreferences.EpubViewerPreferencesBuilder epubViewerPreferences = EpubViewerPreferences.builder();

        epubViewerPreferences.bookId( entity.getBookId() );
        epubViewerPreferences.theme( entity.getTheme() );
        epubViewerPreferences.font( entity.getFont() );
        epubViewerPreferences.flow( entity.getFlow() );
        epubViewerPreferences.spread( entity.getSpread() );
        epubViewerPreferences.fontSize( entity.getFontSize() );
        epubViewerPreferences.letterSpacing( entity.getLetterSpacing() );
        epubViewerPreferences.lineHeight( entity.getLineHeight() );

        return epubViewerPreferences.build();
    }

    @Override
    public EpubViewerPreferencesEntity toEntity(EpubViewerPreferences entity) {
        if ( entity == null ) {
            return null;
        }

        EpubViewerPreferencesEntity.EpubViewerPreferencesEntityBuilder epubViewerPreferencesEntity = EpubViewerPreferencesEntity.builder();

        epubViewerPreferencesEntity.bookId( entity.getBookId() );
        epubViewerPreferencesEntity.theme( entity.getTheme() );
        epubViewerPreferencesEntity.font( entity.getFont() );
        epubViewerPreferencesEntity.fontSize( entity.getFontSize() );
        epubViewerPreferencesEntity.letterSpacing( entity.getLetterSpacing() );
        epubViewerPreferencesEntity.lineHeight( entity.getLineHeight() );
        epubViewerPreferencesEntity.flow( entity.getFlow() );
        epubViewerPreferencesEntity.spread( entity.getSpread() );

        return epubViewerPreferencesEntity.build();
    }
}
