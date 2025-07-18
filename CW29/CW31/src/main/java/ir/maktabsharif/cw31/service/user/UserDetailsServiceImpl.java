package ir.maktabsharif.cw31.service.user;

import ir.maktabsharif.cw31.model.Author;
import ir.maktabsharif.cw31.model.UserDetailsImpl;
import ir.maktabsharif.cw31.service.author.AuthorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthorService authorService;

    public UserDetailsServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author byUsername = authorService.findByUsername(username);
        return new UserDetailsImpl(byUsername);
    }
}
