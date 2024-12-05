package vn.hoidanit.laptopshop.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String deccription;

    // Role - one to many - User
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeccription() {
        return deccription;
    }

    public void setDeccription(String deccription) {
        this.deccription = deccription;
    }

    public Role() {
    }

    public Role(long id, String name, String deccription) {
        this.id = id;
        this.name = name;
        this.deccription = deccription;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", deccription=" + deccription + "]";
    }

}
