package com.ecommerece.userservice.service.impl;

import com.ecommerece.userservice.dtos.ProductDto;
import com.ecommerece.userservice.dtos.UserDto;
import com.ecommerece.userservice.entities.User;
import com.ecommerece.userservice.exceptions.ResourceNotFoundException;
import com.ecommerece.userservice.external.services.ProductService;
import com.ecommerece.userservice.repositories.UserRepository;
import com.ecommerece.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ProductService productService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    public UserDto saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return getUserDtofromUser(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll().stream().map(this::getUserDtofromUser).collect(Collectors.toList());
    }

    //get single user
    @Override
    public UserDto getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        List<ProductDto> products = productService.getUserProducts(user.getId()).getBody();
        UserDto userDto = getUserDtofromUser(user);
        userDto.setProducts(products);
        return userDto;
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

//        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//        logger.info("{} ", ratingsOfUser);
//        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            //api call to hotel service to get the hotel
//            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
//            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            // logger.info("response status code: {} ",forEntity.getStatusCode());
//            //set the hotel to rating
//            rating.setHotel(hotel);
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());

//        user.setRatings(ratingList);

//        return getUserDtofromUser(user);
    }

    @Override
    public void deleteUser(String userid) {
        userRepository.deleteById(userid);
    }

    @Override
    public UserDto updateUser(String userid, UserDto user) {
        user.setId(userid);
        return getUserDtofromUser(userRepository.save(getUserfromUserDto(user)));
    }

    @Override
    public UserDto updateUserFields(Map<String, Object> fields, String id) {
        UserDto currentUser = getUser(id);
        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(UserDto.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,currentUser,value);
        });
        return getUserDtofromUser(userRepository.save(getUserfromUserDto(currentUser)));
    }

    private UserDto getUserDtofromUser(User user){
       return modelMapper.map(user, UserDto.class);
    }

    private User getUserfromUserDto(UserDto user){
        return modelMapper.map(user, User.class);
    }
}
