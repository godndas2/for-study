package com.forstudy.oauth2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String name;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//    private Set<User> users = new HashSet<User>();

    @Override
    public String getAuthority() {
        return name;
    }
}
