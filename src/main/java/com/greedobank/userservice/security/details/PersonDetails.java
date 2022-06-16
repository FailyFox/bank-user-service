package com.greedobank.userservice.security.details;

import com.greedobank.userservice.model.Person;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PersonDetails implements UserDetails {

  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public static PersonDetails personToPersonDetails(Person person, String role) {
    PersonDetails details = new PersonDetails();
    details.email = person.getEmail();
    details.password = person.getPassword();
    details.grantedAuthorities = Collections.singletonList(
        new SimpleGrantedAuthority(role));
    return details;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}