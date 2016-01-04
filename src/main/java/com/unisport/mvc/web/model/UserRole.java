package com.unisport.mvc.web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//User-Role
@Entity
@Table(name = "ROLE")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ROLE_ID")
    private Long userRoleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME", unique = true)
    private NameRole nameRole;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "USER_ID")
    @ManyToMany
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private Set<User> userList = new HashSet<User>();

    public NameRole getNameRole() {
        return nameRole;
    }

    public void setNameRole(NameRole nameRole) {
        this.nameRole = nameRole;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public String getAuthority() {
        return nameRole.getNameRole();
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Role role1 = (Role) o;
//
//        if (listRole != null ? !listRole.equals(role1.listRole) : role1.listRole != null) return false;
//        if (!roleID.equals(role1.roleID)) return false;
////        if (user != null ? !user.equals(role1.user) : role1.user != null) return false;
//            return true;
//        }
//
//    @Override
//    public int hashCode() {
//        int result = roleID.hashCode();
//        result = 31 * result + (listRole != null ? listRole.hashCode() : 0);
////        result = 31 * result + (user != null ? user.hashCode() : 0);
//        return result;
//    }
}