//package io.github.zeculesu.itmo.prog5.client;
//
//import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
//import io.github.zeculesu.itmo.prog5.error.InputFormException;
//import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
//import io.github.zeculesu.itmo.prog5.server.command.CommandAction;
//import io.github.zeculesu.itmo.prog5.models.Response;
//import org.jetbrains.annotations.NotNull;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//import static io.github.zeculesu.itmo.prog5.client.ColorConsole.*;
//import static kotlin.io.ConsoleKt.readlnOrNull;
//
///**
// * Реализует консоль, взаимодействие с пользователем
// */
//public class CLientConsole implements CommunicatedClient {
//    private StateIO stateIO;
//    private final Set<String> scriptQueue = new HashSet<>();
//    private BufferedReader bufferReaderScript;
//    private String fileNameCollection;
//
//    private final CommandIOConsole console;
//    boolean run;
//
//    public CLientConsole() {
//        this.console = new CommandIOConsole();
//    }
//
//    @Override
//    public void run() {
//        run = true;
//
//        String command;
//
//        while (run) {
//
//            console.print("> ");
//
//            command = readlnOrNullCommand();
//
//            if (this.stateIO == StateIO.SCRIPT_TO_CONSOLE) {
//                console.println("Конец скрипта");
//                this.stateIO = StateIO.CONSOLE;
//                this.scriptQueue.clear();
//            } else if (command == null) {
//                console.println("Конец работы программы");
//                return;
//            } else if (command.isBlank()) {
//                console.println("Команда не введена");
//            } else {
//                readCommand(command);
//            }
//        }
//    }
//
//    public void readCommand(@NotNull String command) {
//        String[] token = command.split(" ");
//        CommandAction com = this.environment.getCommandSetMap().findCommand(token[0]);
//        if (com != null) {
//            String[] args = token.length == 2 ? token[1].split(" ") : new String[0];
//            SpaceMarine element = null;
//            try {
//                if (com.isAcceptsElement()) {
//                    element = ElementFormConsole.getElemFromForm(new CommandIOConsole());
//                }
//                Response response = com.execute(this.collectionSpaceMarine, this.environment, args, element);
//                outputResponse(response);
//            } catch (InputFormException | NamingEnumException | IOException e) {
//                console.println(e.getMessage(), ERROR);
//            }
//
//        } else console.println("Неизвестная команда. Введите 'help' для получения справки.");
//        // todo this.environment.addCommandToHistory(token[0]);
//        if (this.environment.getStateIO() == StateIO.CONSOLE_TO_SCRIPT) {
//            this.environment.setStateIO(StateIO.SCRIPT);
//        }
//    }
//
//    public void outputResponse(Response response) {
//        if (response.isOutputElement()) {
//            for (SpaceMarine line : response.getOutputElement()) {
//                console.println(line.toString());
//            }
//        }
//        if (response.isOutput()) {
//            for (String line : response.getOutput()) {
//                console.println(line);
//            }
//        }
//        if (response.isError()) console.println(response.getError(), ColorConsole.ERROR);
//        if (response.isMessage()) console.println(response.getMessage());
//    }
//
//    public String readlnOrNullCommand() {
//        if (this.stateIO == StateIO.SCRIPT) {
//            try {
//                if (!this.bufferReaderScript.ready()) {
//                    this.stateIO = StateIO.SCRIPT_TO_CONSOLE;
//                    console.println("");
//                    return null;
//                }
//                return console.readln();
//            } catch (IOException e) {
//                console.println("Проблемы с чтением файла скрипта", ERROR);
//            }
//        }
//        return readlnOrNull();
//    }
//
//    class CommandIOConsole implements CommandIO {
//        @Override
//        public void print(String line, ColorConsole... color) {
//            if (color.length != 0) {
//                System.out.print(color[0].getAnsiCode() + conversionForScriptOutput(line) + DEFAULT.getAnsiCode());
//            } else {
//                System.out.print(conversionForScriptOutput(line));
//            }
//        }
//
//        @Override
//        public void println(String line, ColorConsole... color) {
//            if (color.length != 0) {
//                System.out.println(color[0].getAnsiCode() + conversionForScriptOutput(line) + DEFAULT.getAnsiCode());
//            } else {
//                System.out.println(conversionForScriptOutput(line));
//            }
//        }
//
//        @Override
//        public String readln() throws IOException {
//            if (CLientConsole.this.stateIO == StateIO.SCRIPT) {
//                String input = CLientConsole.this.bufferReaderScript.readLine();
//                System.out.println(SCRIPT.getAnsiCode() + input + DEFAULT.getAnsiCode());
//                return input;
//            }
//            return readlnOrNull();
//        }
//
//        public String conversionForScriptOutput(String line) {
//            if (CLientConsole.this.stateIO == StateIO.SCRIPT) {
//                return "\t" + line;
//            }
//            return line;
//        }
//    }
//}
