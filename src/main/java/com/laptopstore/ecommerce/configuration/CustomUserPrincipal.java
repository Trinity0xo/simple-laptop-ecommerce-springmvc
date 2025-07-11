package com.laptopstore.ecommerce.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class CustomUserPrincipal extends User {
    private String fullName;
    private String avatar;
    private int cartItemCount;

    public CustomUserPrincipal(String username, String password, String fullName, String avatar, int cartItemCount, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.fullName = fullName;
        this.avatar = avatar;
        this.cartItemCount = cartItemCount;
    }

}
