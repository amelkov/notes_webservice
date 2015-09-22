package by.amelkov.webservice;

import by.amelkov.model.User;
import by.amelkov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import java.util.List;

@WebService(portName = "UsersListServicePort",serviceName = "UsersListService",name = "UsersList",targetNamespace = "http://www.UsersList.by")
public class NotesWebService {

    @Autowired
    UserService usersService;

    @WebMethod
    @WebResult(name = "User")
    public List<User> getUsersList(){
        List<User> userList = usersService.getAllUsersAndLastDate();
        return userList;
    }
}
