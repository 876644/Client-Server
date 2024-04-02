package com.company;

import com.company.Commands.AbstractCommand;
import com.company.Loginner.Login;
import com.company.PG.PGManager;
import com.company.Utility.Message;
import com.company.classes.StudyGroup;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends RecursiveAction {

    public static PriorityQueue<StudyGroup> collection = new PriorityQueue<>();//Создала коллекцию
    public static List<Long> AllId = new ArrayList<>();
    public static List<StudyGroup> twoColl = new ArrayList<>();
    public static List<String> saveHistory = new ArrayList<>();
    public static DatagramSocket datagramSocket;//  получать ответы от клиента
    public static DatagramPacket inputPacket;
    public static byte[] receivingDataBuffer = new byte[16384];
    public static byte[] sendingDataBuffer = new byte[16384];
    public static Message message;
    public static String answer;
    public static PGManager pgManager;
    public static Login login;
    public static String l = null;
    public static String p = null;
    public static int r;
    public static int h;
    public static int b;


    public static void main(String[] args) {
        pgManager = new PGManager();
        pgManager.connectToDatabase();
        //pgManager.createNewPasswordBase();
        //pgManager.createNewUserBase();
        pgManager.readCollectionFromDatabase(collection);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        login = new Login();

        pgManager.gId();
        System.out.println(AllId);
        while (true) {
            if(login.prev) {
                forkJoinPool.invoke(new Main());
            } else
                justGo();
        }
    }

    public static void connection() {

        int port = 21055;

        try {
            datagramSocket = new DatagramSocket(port);// буферы для хранения отправляемых и получаемых данных.

            receivingDataBuffer = new byte[16384];

            inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);//экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных
            System.out.println("Ждём действий от клиента...");
            datagramSocket.receive(inputPacket);// получаем данные от клиента и сохраните их в datagramPacket
            message = (Message) deserialize(receivingDataBuffer);
            System.out.println("Был получен объект. Идёт обработка... ");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void read() {
        p = message.password2;
        l = message.login;
        System.out.println(l);
        if(!message.newMes) {

            Lock l = new ReentrantLock();
            try{
                 l.lockInterruptibly();
                 try{
                     message.object.execute();
                 }catch (NullPointerException ignored) {
                 }finally {l.unlock();}
            }catch (InterruptedException e) {
                System.err.println("Ошибка..Подождите..");
            }
        }
        else{
            boolean userExists = pgManager.userExists(message.login);
            boolean passwordMatches = pgManager.passwordMatches(message.password2, message.login);
            Login login = new Login();
            login.password = message.password2;
            login.login = message.login;
            login.newUser = message.QQQ;
            r = login.r;
            System.out.println(r);
            h = login.g;
            b = login.b;

            if(login.isNew() && !userExists){
                pgManager.addNewUser(login);
                login.r = (int) (Math.random() * (255 - 1 + 1) + 1);
                login.g = (int) (Math.random() * (255 - 1 + 1) + 1);
                login.b = (int) (Math.random() * (255 - 1 + 1) + 1);
                answer = "Вы успешно вошли в систему";
            }
            if(login.isNew() && userExists){
                answer = "Такой пользователь уже существует";
            }
            if(!login.isNew() && userExists && passwordMatches){
                answer = "Вы успешно вошли в систему";
            }
            if(!login.isNew() && userExists && !passwordMatches){
                answer = "Неправильный пароль";
            }
            if(!login.isNew() && !userExists ){
                answer = "Неправильный логин";
            }
            message.newMes = false;
        }
        System.out.println("Идёт выпеполнение команды...");

    }

    public static Object deserialize(byte[] array) throws IOException, ClassNotFoundException{

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream);
        return objectInput.readObject();
    }
    public static byte[] serialize(Object obj) throws IOException{

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//данные записываются в массив байтов
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);//Записывает указанный объект в ObjectOutputStream
        objectOutputStream.flush();//Сбрасывает поток
        return byteArrayOutputStream.toByteArray();

    }

    public static void sent() {
        try {
            InetAddress host = inputPacket.getAddress(); // Получение IP-адрес и порт клиента
            int senderPort = inputPacket.getPort();

            sendingDataBuffer = serialize(message);
            //  новый UDP-пакет с данными, чтобы отправить их клиенту
            DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, host, senderPort);

            datagramSocket.send(outputPacket);// Отправляем пакет клиенту
            System.out.println("Сообщение о результате выполненной команды было отправлено на клиент.");

            datagramSocket.close();//закрытие соединение сокетов
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
    public static void justGo(){
        Main.answer = "";
        connection();
        read();
        message = new Message(answer,collection,p,l,login.prev,r,h,b);
        sent();
    }
    @Override
    public void compute(){
        Main.answer = "";
        connection();
        read();
        message = new Message(answer,collection,p,l,login.prev,r,h,b);
        sent();
    }

}
