package hellojpa;

import javax.persistence.*;
import java.util.Date;

//@Entity(name = "Member") // 클래스 명과 동일한 값이 디폴트로 들어가 있음.
// 그래서 일반적으로 쓸 일은 없다. 다른 패키지에 같은 이름의 클래스가 있고 둘 다 JPA로 매핑되어 있는 경우에 사용한다.
// JPA가 내부적으로 구별하는 이름이라고 생각하면 된다.
@Entity // JPA가 관리할 객체
//@Table(name = "MBR") // MBR 테이블과 매핑을 한다.
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR", // 제너레이터 이름
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50 // DB에 50씩 미리 확보해두고 메모리에서 사용한다.
        // 영속 컨텍스트에 저장하기 위해서는 pk값이 필요하기 때문에. 시퀀스와 테이블 전략에서 사용.
)
public class MemberEx {

    @Id // 데이터베이스 PK와 매핑
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR") // 제너레이터 이름
    private Long id;
//    @Column(unique = true, length = 10) // 유저 이름이 유일한 값이고, 10자리가 최대이다.최대이다
    @Column(name = "name")  // username 필드를 name 컬럼에 넣고 싶다면.
    private String username;

    private Integer age;  // Integer같은 타입도 사용 가능. DB에는 Integer와 가장 적절한 타입으로 들어간다.

    @Enumerated(EnumType.STRING) // Enum도 사용 가능하다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // DB에서 날짜 및 시간은, 3가지 케이스 중 선택해서 사용해야 한다.
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // VARCHAR를 넘어서는 큰 컨텐츠는 Lob을 사용한다.
    private String description;

    public MemberEx() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //    // JPA는 내부적으로 리플렉션 등을 사용해야 하므로 동적으로 객체를 생성할 수 있어야 한다.
//    // 아래의 생성자만 있으면 인텔리제이가 에러를 표시하고, 위의 기본 생성자까지 있어야 한다.
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

}
