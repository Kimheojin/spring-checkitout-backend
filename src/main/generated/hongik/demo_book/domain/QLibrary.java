package hongik.demo_book.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLibrary is a Querydsl query type for Library
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLibrary extends EntityPathBase<Library> {

    private static final long serialVersionUID = -1836956410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLibrary library = new QLibrary("library");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath library_code = createString("library_code");

    public final EnumPath<LibraryStatus> library_status = createEnum("library_status", LibraryStatus.class);

    public final QMember member;

    public QLibrary(String variable) {
        this(Library.class, forVariable(variable), INITS);
    }

    public QLibrary(Path<? extends Library> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLibrary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLibrary(PathMetadata metadata, PathInits inits) {
        this(Library.class, metadata, inits);
    }

    public QLibrary(Class<? extends Library> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

