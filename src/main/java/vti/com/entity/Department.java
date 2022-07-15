package vti.com.entity;

import vti.com.entity.enumerated.DepartmentType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_member")
    private Integer total_member;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @Column(name = "created_date")
    private Date createDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accountList;


    public Department() {
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

    public Integer getTotal_number() {
        return total_member;
    }

    public void setTotal_number(Integer total_number) {
        this.total_member = total_number;
    }

    public DepartmentType getType() {
        return type;
    }

    public void setType(DepartmentType type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

//    public List<Account> getAccountList() {
//        return accountList;
//    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total_number=" + total_member +
                ", type=" + type +
                ", createDate=" + createDate +
                '}';
    }
}
