package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.Auth;
import io.github.zeculesu.itmo.prog5.sql.ConnectingDB;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterCommand extends AbstractCommand {
    public RegisterCommand() {
        super("register", "регистрация нового пользователя", false, true);
    }

    @Override
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        String[] log_pas = args[0].split(" ");
        String login = log_pas[0];
        String password = log_pas[1];

        try{
            Connection connection = ConnectingDB.getConnection("jdbc:postgresql://localhost:5432/SpaceMarines", "root", "root");

            String query = "INSERT INTO users (login, password) VALUES (?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            password = Auth.hash_password(password);

            ps.setString(1, login);
            ps.setString(2, password);

            ps.executeUpdate();

            connection.close();

            response.setMessage("Регистрация успешно пройдена");
        }

        catch (SQLException e){
            response.setError("Не получилось подключиться к бд");
        }
        return response;
    }


}
