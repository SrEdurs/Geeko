package es.geeko.service;


import es.geeko.model.Usuario;

public interface IUserService {

    //Save user
    public Integer saveUser(Usuario usuario);

    //Check if user exists
    public boolean checkUserExists(String email);

    //cambiar contraseña
    public void changePassword(String email, String password);
}