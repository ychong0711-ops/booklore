package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.FetchedProposal;
import com.adityachandel.booklore.model.entity.MetadataFetchProposalEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class FetchedProposalMapperImpl extends FetchedProposalMapper {

    @Override
    public FetchedProposal toDto(MetadataFetchProposalEntity entity) {
        if ( entity == null ) {
            return null;
        }

        FetchedProposal.FetchedProposalBuilder fetchedProposal = FetchedProposal.builder();

        fetchedProposal.proposalId( entity.getProposalId() );
        fetchedProposal.bookId( entity.getBookId() );
        fetchedProposal.fetchedAt( entity.getFetchedAt() );
        fetchedProposal.reviewedAt( entity.getReviewedAt() );
        if ( entity.getReviewerUserId() != null ) {
            fetchedProposal.reviewerUserId( String.valueOf( entity.getReviewerUserId() ) );
        }
        fetchedProposal.status( entity.getStatus() );

        fetchedProposal.taskId( getTaskId(entity) );

        FetchedProposal fetchedProposalResult = fetchedProposal.build();

        mapMetadataJson( entity, fetchedProposalResult );

        return fetchedProposalResult;
    }
}
