package com.example.service.decentralization;



import com.example.model.decentralization.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAllRole();

    List<Role> getAllRoles();

    void saveCreateGmail(String email);


    List<Role> getRoleByUsername(String email);

}
