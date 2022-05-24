package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA가 관리할 객체
//@Table(name = "USER") // User 테이블에 저장하고 싶다면
public class Member {

    public Member() {
    }
    // JPA는 내부적으로 리플렉션 등을 사용해야 하므로 동적으로 객체를 생성할 수 있어야 한다.
    // 아래의 생성자만 있으면 인텔리제이가 에러를 표시하고, 위의 기본 생성자까지 있어야 한다.
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id // 데이터베이스 PK와 매핑
    private Long id;
//    @Column(name = "username") // name 필드를 username 컬럼에 넣고 싶다면.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
