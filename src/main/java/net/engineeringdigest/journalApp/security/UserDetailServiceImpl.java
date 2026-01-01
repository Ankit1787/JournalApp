package net.engineeringdigest.journalApp.security;

import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.entity.UserPrincipal;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
   @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserEntry user =userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));

        return new UserPrincipal(user);
    }
}
