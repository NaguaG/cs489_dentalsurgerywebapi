package miu.edu.dentalsurgery.service.Impl;

import miu.edu.dentalsurgery.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DentalSurgeryUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public DentalSurgeryUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username NOT FOUND " + username));
    }
}
