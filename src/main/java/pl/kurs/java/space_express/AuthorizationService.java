package pl.kurs.java.space_express;

public class AuthorizationService {
    public boolean isAuthorized(String employeeId){
        return employeeId != null && !employeeId.isBlank();
    }
}
