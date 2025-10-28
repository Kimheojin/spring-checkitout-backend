package hongik.demo_book.address.service;


import hongik.demo_book.address.dto.AddressDto;
import hongik.demo_book.address.dto.AddressResponse;
import hongik.demo_book.address.entity.Address;
import hongik.demo_book.global.exception.NotFoundMemberaddress;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final MemberRepository memberRepository;


    //쓰기전용
    @Transactional
    public AddressDto AddressSave(AddressDto addressdto, Member member) {

        Address address = Address.builder()
                .city(addressdto.getCity())
                .street(addressdto.getStreet())
                .zipCode(addressdto.getZipCode())
                .build();

        member.updateAddress(address);

        Address savedAddress = member.getAddress();

        return AddressDto.builder()
                .city(savedAddress.getCity())
                .street(savedAddress.getStreet())
                .zipCode(savedAddress.getZipCode())
                .build();
    }


    //주소 삭제
    @Transactional
    public AddressResponse deleteAddress(Member member) {



        Address currentAddress = member.getAddress();

        if(currentAddress == null){
            throw new NotFoundMemberaddress();
        }
        member.updateAddress(null);


        // 안해도 되긴함, 변경 감지라
        memberRepository.save(member);

        // 빈 객체 반환
        return AddressResponse.builder().build();

    }

    //주소 목록 반환
    @Transactional(readOnly = true)
    public AddressDto AddressReturn(Member member){

        Address address = member.getAddress();
        return AddressDto.builder()
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .city(address.getZipCode())
                .build();
    }
}
