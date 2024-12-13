package com.explorerquest.explorerquest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(length = 500)
    private String bio;

    @Column
    private String profileImageUrl;

    @Column
    private LocalDate dateOfBirth;

    // Campi specifici per la sicurezza (se necessari)
    @Column(nullable = false)
    private boolean enabled = true; // Account abilitato

    @Column(nullable = false)
    private boolean accountNonLocked = true; // Account non bloccato

    @Column(nullable = false)
    private boolean accountNonExpired = true; // Account non scaduto

    @Column(nullable = false)
    private boolean credentialsNonExpired = true; // Credenziali non scadute

    // Metodi richiesti da UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aggiungi ruoli o autorizzazioni, se applicabile. Per ora restituiamo una lista vuota.
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
