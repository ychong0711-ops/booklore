package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookNote;
import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.BookLoreUserEntity;
import com.adityachandel.booklore.model.entity.BookNoteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookNoteMapperImpl implements BookNoteMapper {

    @Override
    public BookNote toDto(BookNoteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookNote.BookNoteBuilder bookNote = BookNote.builder();

        bookNote.userId( entityUserId( entity ) );
        bookNote.bookId( entityBookId( entity ) );
        bookNote.id( entity.getId() );
        bookNote.title( entity.getTitle() );
        bookNote.content( entity.getContent() );
        bookNote.createdAt( entity.getCreatedAt() );
        bookNote.updatedAt( entity.getUpdatedAt() );

        return bookNote.build();
    }

    @Override
    public BookNoteEntity toEntity(BookNote dto) {
        if ( dto == null ) {
            return null;
        }

        BookNoteEntity.BookNoteEntityBuilder bookNoteEntity = BookNoteEntity.builder();

        bookNoteEntity.id( dto.getId() );
        bookNoteEntity.userId( dto.getUserId() );
        bookNoteEntity.bookId( dto.getBookId() );
        bookNoteEntity.title( dto.getTitle() );
        bookNoteEntity.content( dto.getContent() );

        return bookNoteEntity.build();
    }

    private Long entityUserId(BookNoteEntity bookNoteEntity) {
        BookLoreUserEntity user = bookNoteEntity.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long entityBookId(BookNoteEntity bookNoteEntity) {
        BookEntity book = bookNoteEntity.getBook();
        if ( book == null ) {
            return null;
        }
        return book.getId();
    }
}
