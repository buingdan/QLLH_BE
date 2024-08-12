package com.example.qllh.Services.UserService;


import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    ResponseApi getUsers();
    ResponseApi getUsersById(Long id);
    ResponseApi addUser(UserRequest userRequest);

    ResponseApi updateUser(Long id, UserRequest userRequest);

    ResponseApi deleteUser(Long id);

    ContentResponse getPageUsers(String textSearch, Long currentPage, Long limit, String sortData, String sortType);
}
