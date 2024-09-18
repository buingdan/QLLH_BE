package com.example.qllh.Services.UserService;


import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    ResponseApi getUsers();
    ResponseApi getUsersById(Long id);
    ResponseApi addUser(UserRequest userRequest);

    ResponseApi updateUser(Long id, UserRequest userRequest);

    ResponseApi deleteUser(Long id);

    ContentResponse getPageUsers(String textSearch, Long currentPage, Long limit, String sortData, String sortType);

    ContentResponse getPageUsersNew(String nameSearch, String phoneNumber, String addressSearch, String roleSearch, List<Boolean> genderSearch, Long currentPage, Long limit, String sortData, String sortType);

    ResponseApi getPatients();

    ResponseApi getDoctors();

}
