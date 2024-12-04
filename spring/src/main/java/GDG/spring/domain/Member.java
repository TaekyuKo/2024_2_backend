package GDG.spring.domain;

import jakarta.persistence.*;

@Entity //Jpa가 관리하는 Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 name 을 넣으면 ID를 자동으로 생성(Indentity 전략)
    private Long id; //데이터를 구분하기 위해 시스템을 저장하는 아이디(시스템이 그냥 정해줌)
    private String name; // 고객이 적는 name

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
