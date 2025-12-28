package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.kobo.KoboReadingState;
import com.adityachandel.booklore.model.entity.KoboReadingStateEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:25+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class KoboReadingStateMapperImpl implements KoboReadingStateMapper {

    @Override
    public KoboReadingStateEntity toEntity(KoboReadingState dto) {
        if ( dto == null ) {
            return null;
        }

        KoboReadingStateEntity.KoboReadingStateEntityBuilder koboReadingStateEntity = KoboReadingStateEntity.builder();

        koboReadingStateEntity.currentBookmarkJson( toJson(dto.getCurrentBookmark()) );
        koboReadingStateEntity.statisticsJson( toJson(dto.getStatistics()) );
        koboReadingStateEntity.statusInfoJson( toJson(dto.getStatusInfo()) );
        koboReadingStateEntity.entitlementId( cleanString(dto.getEntitlementId()) );
        koboReadingStateEntity.created( dto.getCreated() );
        koboReadingStateEntity.lastModified( dto.getLastModified() );
        koboReadingStateEntity.priorityTimestamp( dto.getPriorityTimestamp() );

        return koboReadingStateEntity.build();
    }

    @Override
    public KoboReadingState toDto(KoboReadingStateEntity entity) {
        if ( entity == null ) {
            return null;
        }

        KoboReadingState.KoboReadingStateBuilder koboReadingState = KoboReadingState.builder();

        koboReadingState.currentBookmark( fromJson(entity.getCurrentBookmarkJson(), KoboReadingState.CurrentBookmark.class) );
        koboReadingState.statistics( fromJson(entity.getStatisticsJson(), KoboReadingState.Statistics.class) );
        koboReadingState.statusInfo( fromJson(entity.getStatusInfoJson(), KoboReadingState.StatusInfo.class) );
        koboReadingState.entitlementId( cleanString(entity.getEntitlementId()) );
        koboReadingState.created( entity.getCreated() );
        koboReadingState.lastModified( entity.getLastModified() );
        koboReadingState.priorityTimestamp( entity.getPriorityTimestamp() );

        return koboReadingState.build();
    }
}
