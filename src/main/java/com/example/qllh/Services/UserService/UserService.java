package com.example.qllh.Services.UserService;

import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.DTO.UserDTO.UserResponse;
import com.example.qllh.Entities.Users;
import com.example.qllh.Mapping.UserMapping;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import com.example.qllh.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService implements IUserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ResponseApi getUsers() {
        try {
            List<Users> userEntityList = usersRepository.findAll();
            List<UserResponse> userResponseList = userEntityList
                    .stream()
                    .map(UserMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Lấy dữ liệu người dùng thành công!", userEntityList, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi getUsersById(Long id) {
        try {
            Optional<Users> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu người dùng", null, true);
            }
            var user = usersRepository.findById(id);
            List<UserResponse> userResponseList = user
                    .stream()
                    .map(UserMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Xóa dữ liệu thành công!!", userResponseList , true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi addUser(UserRequest userRequest) {
        try {
            Users userEntityList = UserMapping.mapRequestToEntity(userRequest);
            userEntityList.setActive(true);
            usersRepository.save(userEntityList);
            return new ResponseApi("Thêm người dùng thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi updateUser(Long id, UserRequest userRequest) {
        try {
            Optional<Users> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu người dùng!", null, true);
            }
            Users userEntity = userEntityOptional.get();
            userEntity.setFullName(userRequest.getFullName());
            userEntity.setAge(userRequest.getAge());
            userEntity.setGender(userRequest.isGender());
            userEntity.setPhoneNumber(userRequest.getPhoneNumber());
            userEntity.setAddress(userRequest.getAddress());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setRole(userRequest.getRole());
            userEntity.setActive(userEntity.isActive());

            usersRepository.save(userEntity);
            return new ResponseApi("Cập nhật dữ liệu thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi deleteUser(Long id) {
        try {
            Optional<Users> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu người dùng", null, true);
            }
            usersRepository.softDelete(id);
            return new ResponseApi("Xóa dữ liệu thành công!!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    public ContentResponse getPageUsers(String textSearch, Long currentPage, Long limit, String sortData, String sortType) {
            ContentResponse contentResponse = new ContentResponse();
            currentPage -= 1;
            Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            var list = usersRepository.searchUser(textSearch, pageable);
            List<UserResponse> userResponseList = list
                    .stream()
                    .map(UserMapping::mapEntityToResponse)
                    .collect(Collectors.toList());

            Integer totalUser = Math.toIntExact(list.getTotalElements());
            Integer totalPageUser = Math.toIntExact(list.getTotalPages());
            if (currentPage.intValue() > totalPageUser) {
                currentPage = totalPageUser.longValue();
                pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
                list = usersRepository.searchUser(textSearch, pageable);
                userResponseList = list
                        .stream()
                        .map(UserMapping::mapEntityToResponse)
                        .collect(Collectors.toList());
                totalUser = Math.toIntExact(list.getTotalElements());
            }
            contentResponse.setList(userResponseList);
            contentResponse.setCurrentPage(currentPage.intValue() + 1);
            contentResponse.setTotalRecord(totalUser);
            return contentResponse;

    }

    @Override
    public ContentResponse getPageUsersNew(String nameSearch, String phoneNumber, String addressSearch, String roleSearch, List<Boolean> genderSearch, Long currentPage, Long limit, String sortData, String sortType) {
        ContentResponse contentResponse = new ContentResponse();
        currentPage -= 1;
        Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
        var list = usersRepository.searchUserNew(nameSearch, phoneNumber, addressSearch, genderSearch, roleSearch, pageable);
        List<UserResponse> userResponseList = list
                .stream()
                .map(UserMapping::mapEntityToResponse)
                .collect(Collectors.toList());

        Integer totalUser = Math.toIntExact(list.getTotalElements());
        Integer totalPageUser = Math.toIntExact(list.getTotalPages());
        if (currentPage.intValue() > totalPageUser) {
            currentPage = totalPageUser.longValue();
            pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            list = usersRepository.searchUserNew(nameSearch, phoneNumber, addressSearch, genderSearch, roleSearch, pageable);
            userResponseList = list
                    .stream()
                    .map(UserMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            totalUser = Math.toIntExact(list.getTotalElements());
        }
        contentResponse.setList(userResponseList);
        contentResponse.setCurrentPage(currentPage.intValue() + 1);
        contentResponse.setTotalRecord(totalUser);
        return contentResponse;
    }

    public List<Sort.Order> sortOrder(String sort, String sortDirection) {
        System.out.println(sortDirection);
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        if (sortDirection != null) {
            direction = Sort.Direction.fromString(sortDirection);
        } else {
            direction = Sort.Direction.DESC;
        }
        sorts.add(new Sort.Order(direction, sort));
        return sorts;
    }
}
