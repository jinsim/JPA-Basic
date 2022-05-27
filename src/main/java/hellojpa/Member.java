package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity(name = "Member") // 클래스 명과 동일한 값이 디폴트로 들어가 있음.
// 그래서 일반적으로 쓸 일은 없다. 다른 패키지에 같은 이름의 클래스가 있고 둘 다 JPA로 매핑되어 있는 경우에 사용한다.
// JPA가 내부적으로 구별하는 이름이라고 생각하면 된다.
@Entity // JPA가 관리할 객체
//@Table(name = "MBR") // MBR 테이블과 매핑을 한다.
public class Member {

    @Id // 데이터베이스 PK와 매핑
    private Long id;
//    @Column(name = "username") // name 필드를 username 컬럼에 넣고 싶다면.
    @Column(unique = true, length = 10) // 유저 이름이 유일한 값이고, 10자리가 최대이다.
    private String name;

    public Member() {
    }
    // JPA는 내부적으로 리플렉션 등을 사용해야 하므로 동적으로 객체를 생성할 수 있어야 한다.
    // 아래의 생성자만 있으면 인텔리제이가 에러를 표시하고, 위의 기본 생성자까지 있어야 한다.
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }


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
