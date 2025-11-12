package com.example.dockbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<Portador> portadores = new HashSet<>();

    public Role() {
    }

    public Role(Long id, String authority) {
        super();
        this.id = id;
        this.authority = authority;
    }

    public Set<Portador> getPortadores() {
        return portadores;
    }

    public void setPortadores(Set<Portador> portadores) {
        this.portadores = portadores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        return Objects.equals(authority, other.authority);
    }
}
