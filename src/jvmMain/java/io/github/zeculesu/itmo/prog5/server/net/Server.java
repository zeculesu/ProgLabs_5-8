package io.github.zeculesu.itmo.prog5.server.net;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.AuthCheckSpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.data.CachedSpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.data.InMemorySpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.sql.JDBCSpaceMarineCollection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static kotlin.io.ConsoleKt.readlnOrNull;

public class Server {
    private final int port;
    private final ConsoleCommandEnvironment environment;

    protected static CachedSpaceMarineCollection cachedSpaceMarineCollection;
    private static final Map<String, AuthCheckSpaceMarineCollection> clientCollections = new HashMap<>();

    private final byte[] receiveData = new byte[65507];

    public Server(ConsoleCommandEnvironment environment, int port) {
        this.environment = environment;
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
        System.exit(0);
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
                Callable<Response> task = () -> RequestReading.requestRead(receivePacket, this.environment, clientCollections);
                Future<Response> result = service.submit(task);

                //отправляем ответ клиенту
                ResponseSending.responseSend(serverSocket, receivePacket, result.get());
            }
            service.shutdown();
        } catch (Exception e) {
            System.out.println(e);
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
        JDBCSpaceMarineCollection jdbcSpaceMarineCollection = new JDBCSpaceMarineCollection(environment.getConnection());
        InMemorySpaceMarineCollection inMemorySpaceMarineCollection = new InMemorySpaceMarineCollection();

        for (SpaceMarine o : jdbcSpaceMarineCollection.show()) {
            inMemorySpaceMarineCollection.add(o.getId(), o);
        }

        cachedSpaceMarineCollection = new CachedSpaceMarineCollection(jdbcSpaceMarineCollection, inMemorySpaceMarineCollection);

        //   df = AuthCheckSpaceMarineCollection(new CachedSpaceMarineCollection(inMemorySpaceMarineCollection, jdbcSpaceMarineCollection), "login");
    }
}
