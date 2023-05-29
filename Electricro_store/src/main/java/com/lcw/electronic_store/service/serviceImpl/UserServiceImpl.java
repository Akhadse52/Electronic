package com.lcw.electronic_store.service.serviceImpl;

import com.lcw.electronic_store.dtos.UserDto;
import com.lcw.electronic_store.entity.User;
import com.lcw.electronic_store.exception.ResourceNotFoundException;
import com.lcw.electronic_store.repository.UserRepository;
import com.lcw.electronic_store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        //generate unique id in string

        String userId = UUID.randomUUID().toString();

        userDto.setUserId(userId);

        // dto> entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        // entity > dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserId not found "));

        user.setName(userDto.getName());
        //user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());
        user.setAbout(userDto.getAbout());
        user.setMobileNumber(userDto.getMobileNumber());
        User save = userRepository.save(user);
        UserDto userDto1 = entityToDto(save);
        return userDto1;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id not found"));
        userRepository.delete(user);

    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id Not Found"));
        UserDto dto = entityToDto(user);
        return dto ;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email Id not Found !!!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<User> all = userRepository.findAll(pageRequest);
        List<User> content = all.getContent();
        List<UserDto> collect = content.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
        return collect  ;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> byNameContaining = userRepository.findByNameContaining(keyword);
       return byNameContaining.stream().map(x->entityToDto(x)).collect(Collectors.toList());

    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new ResourceNotFoundException("Email and Password is not Found"));
        return modelMapper.map(user, UserDto.class);
    }

    private UserDto entityToDto(User savedUser) {
//        UserDto dtos = new UserDto();
//        dtos.setUserId(savedUser.getUserId());
//        dtos.setName(savedUser.getName());
//        dtos.setEmail(savedUser.getEmail());
//        dtos.setPassword(savedUser.getPassword());
//        dtos.setGender(savedUser.getGender());
//        dtos.setImageName(savedUser.getImageName());
//        dtos.setAbout(savedUser.getAbout());
//        dtos.setMobileNumber(savedUser.getMobileNumber());

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .email(savedUser.getEmail())
//                .mobileNumber(savedUser.getMobileNumber())
//                .gender(savedUser.getGender())
//                .about(savedUser.getAbout())
//                .imageName(savedUser.getImageName())
//                .name(savedUser.getName())
//                .build();
        UserDto dto = modelMapper.map(savedUser, UserDto.class);
        return dto;

    }

    private User dtoToEntity(UserDto userDto) {
//        User user = new User();
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setGender(userDto.getGender());
//        user.setImageName(userDto.getImageName());
//        user.setAbout(userDto.getAbout());
//        user.setMobileNumber(userDto.getMobileNumber());
//       User user =  User.builder()
//                .userId(userDto.getUserId())
//                .email(userDto.getEmail())
//                .mobileNumber(userDto.getMobileNumber())
//                .gender(userDto.getGender())
//                .about(userDto.getAbout())
//                .imageName(userDto.getImageName())
//                .name(userDto.getName())
//                .build();

        User user = modelMapper.map(userDto, User.class);
        return user;

    }

}
