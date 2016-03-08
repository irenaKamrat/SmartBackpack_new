package com.smartbp.edison.connector.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import com.smartbp.edison.connector.EdisonClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lazara on 08/03/2016.
 */
public class EdisonBluetoothClientImpl implements EdisonClient {

    private  static final String DEVICE_NAME = "HC-05";
  /*  public void run(){
        while(true){
            setUpConnection();
        }
    }
*/
    public List<String> getIDs() {

        List<String> ids = new LinkedList<>();

        /*
        Represents the local Bluetooth adapter (Bluetooth radio).
        The BluetoothAdapter is the entry-point for all Bluetooth interaction.
        Using this, you can discover other Bluetooth devices, query a list of bonded (paired) devices,
        instantiate a BluetoothDevice using a known MAC address, and create a BluetoothServerSocket to listen for
        communications from other devices.
        * */
        BluetoothAdapter myBluetoothAdapter;

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothSocket socket = null;
        if (myBluetoothAdapter != null) {
            Set<BluetoothDevice> devices = myBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                if (device.getName().equals(DEVICE_NAME)) {
                    try {
                        socket = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
                        if(BluetoothDevice.BOND_NONE==device.getBondState()){
                            System.err.append("Device not connected");
                            return ids;
                        }
                        socket.connect();
                        //      byte[] data = new byte[1];
                        //      InputStream inputStream = socket.getInputStream();
                        //      inputStream.read(data);
                        //     String s = new String(data, "UTF-8");
                        BufferedReader ein = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // char[] data = new char[2];
                        String line = ein.readLine();
                        String[] idsArray = line.split(",");
                        System.out.println(ids);
                        socket.close();
                        return Arrays.asList(idsArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    } finally {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return ids;
    }

}
