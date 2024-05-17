package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.AuthCheckSpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.data.CachedSpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.data.InMemorySpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.sql.JDBCSpaceMarineCollection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static kotlin.io.ConsoleKt.readlnOrNull;

public class Server {
    private final int port;
    private final ConsoleCommandEnvironment environment;

    private JDBCSpaceMarineCollection jdbcSpaceMarineCollection;
    private InMemorySpaceMarineCollection inMemorySpaceMarineCollection;
    private InMemorySpaceMarineCollection collectionSpaceMarine;

    private final byte[] receiveData = new byte[65507];

    public Server(ConsoleCommandEnvironment environment, int port) {
        this.environment = environment;
        this.collectionSpaceMarine = new InMemorySpaceMarineCollection();
        this.port = port;
    }

    public void start() {
        System.out.println("Начало работы сервера");
        this.environment.setRun(true);

        System.out.println("-------");

        if (!connectDB()) {
            return;
        }
        System.out.println("-------");

        createCollection();
        System.out.println("-------");
        run();
    }

    public void run() {
        try {
            // Создаем сокет для приема данных на порту
            DatagramSocket serverSocket = new DatagramSocket(port);
            ExecutorService service = Executors.newFixedThreadPool(4);

            while (this.environment.isRun()) {
                // получаем запрос от клиента
                DatagramPacket receivePacket = ConnectionReception.reception(serverSocket, this.receiveData);

                // Выполняем запрос клиента
                Callable<Response> task = () -> RequestReading.requestRead(receivePacket, this.environment, this.jdbcSpaceMarineCollection);
                Future<Response> result = service.submit(task);

                //отправляем ответ клиенту
                ResponseSending.responseSend(serverSocket, receivePacket, result.get());
            }
            service.shutdown();
        } catch (Exception e) {
            run();
        }
    }

    private boolean connectDB() {
        try {
            System.out.println("ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ");
            System.out.print("Введите имя пользователя: ");
            String username = readlnOrNull();
            System.out.print("Введите пароль: ");
            String password = readlnOrNull();
            environment.setConnection("jdbc:postgresql://localhost:5432/SpaceMarines", username, password);
            System.out.println("База данных подключена");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Не удалось получить доступ к бд");
            System.out.println("Проверьте, что с базой данной всё в порядке и перезапустите сервер");
            return false;
        }
    }

    private void createCollection() {
        System.out.println("ИНИЦИАЛИЗАЦИЯ КОЛЛЕКЦИИ");
        this.jdbcSpaceMarineCollection = new JDBCSpaceMarineCollection(environment.getConnection());
        this.inMemorySpaceMarineCollection = new InMemorySpaceMarineCollection();
        //   df = AuthCheckSpaceMarineCollection(new CachedSpaceMarineCollection(inMemorySpaceMarineCollection, jdbcSpaceMarineCollection), "login");
    }
}
