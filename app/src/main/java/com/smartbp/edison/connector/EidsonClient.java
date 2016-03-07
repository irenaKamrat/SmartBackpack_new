package com.smartbp.edison.connector;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by tovi on 3/7/2016.
 */
public class EidsonClient {



    public String getIDInfo() {
        //  ClientSocket aa;
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("172.17.192.215");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            // String sentence = inFromUser.readLine();
            sendData = "test".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            String result = "FROM SERVER:" + modifiedSentence;
            clientSocket.close();
            return result;
        } catch (Exception e) {
            System.err.append(e.getStackTrace().toString());
            return null;
        }

    }
}
