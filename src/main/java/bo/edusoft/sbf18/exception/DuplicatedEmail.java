package bo.edusoft.sbf18.exception;

public class DuplicatedEmail extends Exception{

    @Override
    public String getMessage() {
        return "El correo ya esta registrado.";
    }
}
