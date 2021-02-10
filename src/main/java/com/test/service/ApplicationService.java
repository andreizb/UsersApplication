package com.test.service;

import com.test.dto.request.UserRequestDto;
import com.test.dto.response.LocalityResponseDto;
import com.test.dto.response.UserResponseDto;
import com.test.exception.NotAllowedException;
import com.test.exception.NotFoundException;
import com.test.model.County;
import com.test.model.Locality;
import com.test.model.User;
import com.test.repository.CountyRepository;
import com.test.repository.LocalityRepository;
import com.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final UserRepository userRepository;

    private final CountyRepository countyRepository;

    private final LocalityRepository localityRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ApplicationService(UserRepository userRepository,
                              CountyRepository countyRepository,
                              LocalityRepository localityRepository,
                              ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.countyRepository = countyRepository;
        this.localityRepository = localityRepository;
        this.modelMapper = modelMapper;
    }

    public List<LocalityResponseDto> getLocalities(String countyCode) {
        return localityRepository.findAllByCountyCode(countyCode);
    }

    public void addUser(UserRequestDto requestDto) {
        County county = countyRepository.findById(requestDto.getCountyId()).orElseThrow(NotFoundException::new);
        Locality locality = county.getLocalities()
                .stream()
                .filter(e -> e.getId().equals(requestDto.getLocalityId()))
                .findFirst().orElseThrow(NotAllowedException::new);

        userRepository.findByEmail(requestDto.getEmail()).ifPresent(user -> {
            throw new NotAllowedException();
        });

        User user = new User(requestDto.getFullName(), requestDto.getEmail(), county, locality);

        userRepository.save(user);
    }

    public void updateUser(UserRequestDto requestDto, Long id) {
        County county = countyRepository.findById(requestDto.getCountyId()).orElseThrow(NotFoundException::new);
        Locality locality = county.getLocalities()
                .stream()
                .filter(e -> e.getId().equals(requestDto.getLocalityId()))
                .findFirst().orElseThrow(NotAllowedException::new);

        userRepository.findById(id).orElseThrow(NotFoundException::new);

        userRepository.findByEmail(requestDto.getEmail()).ifPresent(user -> {
            if (!user.getId().equals(id)) {
                throw new NotAllowedException();
            }
        });

        User user = new User(requestDto.getFullName(), requestDto.getEmail(), county, locality);
        user.setId(id);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    public List<UserResponseDto> getUsersByLocality(Long localityId) {
        localityRepository.findById(localityId).orElseThrow(NotFoundException::new);

        return userRepository.findAllByLocalityId(localityId)
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<UserResponseDto> getUsersByCounty(Long countyId) {
        countyRepository.findById(countyId).orElseThrow(NotFoundException::new);

        return userRepository.findAllByCountyIdCounty(countyId)
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

}
