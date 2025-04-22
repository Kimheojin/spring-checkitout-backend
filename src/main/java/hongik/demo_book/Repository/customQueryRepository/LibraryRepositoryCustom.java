package hongik.demo_book.Repository.customQueryRepository;


import hongik.demo_book.domain.Library;
import hongik.demo_book.domain.Member;

import java.util.List;

public interface LibraryRepositoryCustom {

    List<Library> FindLibrayhWithMember(Member member);

}
