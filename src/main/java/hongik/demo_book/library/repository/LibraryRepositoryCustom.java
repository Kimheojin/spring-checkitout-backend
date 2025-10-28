package hongik.demo_book.library.repository;


import hongik.demo_book.library.entity.Library;
import hongik.demo_book.member.entity.Member;

import java.util.List;

public interface LibraryRepositoryCustom {

    List<Library> FindLibrayhWithMember(Member member);

}
