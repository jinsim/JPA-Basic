package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") // 팀에서 멤버로 가는 건 일대다
    // mappedBy는 일대다 매핑에서 어떤 변수랑 매핑되어있는지를 확인하는 것
    // = Member 내의 team 필드와 연결되어 있음
    private List<Member> members = new ArrayList<>();
    // add 할 때 nullPointException이 뜨지 않도록 하기 위해서 초기화 시킨다.

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
