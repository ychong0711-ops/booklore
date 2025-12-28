package com.adityachandel.booklore.mapper;

import com.adityachandel.booklore.model.dto.BookMark;
import com.adityachandel.booklore.model.entity.BookEntity;
import com.adityachandel.booklore.model.entity.BookLoreUserEntity;
import com.adityachandel.booklore.model.entity.BookMarkEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-28T14:33:26+0900",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class BookMarkMapperImpl implements BookMarkMapper {

    @Override
    public BookMark toDto(BookMarkEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookMark.BookMarkBuilder bookMark = BookMark.builder();

        bookMark.bookId( entityBookId( entity ) );
        bookMark.userId( entityUserId( entity ) );
        bookMark.id( entity.getId() );
        bookMark.cfi( entity.getCfi() );
        bookMark.title( entity.getTitle() );
        bookMark.color( entity.getColor() );
        bookMark.notes( entity.getNotes() );
        bookMark.priority( entity.getPriority() );
        bookMark.createdAt( entity.getCreatedAt() );
        bookMark.updatedAt( entity.getUpdatedAt() );

        return bookMark.build();
    }

    private Long entityBookId(BookMarkEntity bookMarkEntity) {
        BookEntity book = bookMarkEntity.getBook();
        if ( book == null ) {
            return null;
        }
        return book.getId();
    }

    private Long entityUserId(BookMarkEntity bookMarkEntity) {
        BookLoreUserEntity user = bookMarkEntity.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
