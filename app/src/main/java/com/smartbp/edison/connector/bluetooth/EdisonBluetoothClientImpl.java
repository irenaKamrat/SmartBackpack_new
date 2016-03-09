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

    private BluetoothSocket getSocket() {
        /*
        Represents the local Bluetooth adapter (Bluetooth radio).
        The BluetoothAdapter is the entry-point for all Bluetooth interaction.
        Using this, you can discover other Bluetooth devices, query a list of bonded (paired) devices,
        instantiate a BluetoothDevice using a known MAC address, and create a BluetoothServerSocket to listen for
        communications from other devices.
        * */
        BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothSocket socket = null;
        if (myBluetoothAdapter != null) {
            Set<BluetoothDevice> devices = myBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                if (device.getName().equals(DEVICE_NAME)) {
                    if (BluetoothDevice.BOND_NONE == device.getBondState()) {
                        System.err.append("Device not connected");
                        return null;
                    }
                    try {
                        return device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
                    } catch (Exception e) {
                        System.err.append("Socket not created");
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return socket;
    }


    public List<String> getIDs() {
        List<String> ids = new LinkedList<>();
        BluetoothSocket bluetoothSocket = getSocket();
        if(bluetoothSocket!=null){
            try {
                bluetoothSocket.connect();
                BufferedReader ein = new BufferedReader(new InputStreamReader(bluetoothSocket.getInputStream()));
                // char[] data = new char[2];
                String line = ein.readLine();
                line = ein.readLine();
                String[] idsArray = new String[]{};
                if(!line.trim().isEmpty()){
                    idsArray = line.split(",");
                }
                System.out.println(ids);
                return Arrays.asList(idsArray);
            } catch (Exception e) {
                e.printStackTrace();
                ids = EdisonClient.MOCK_INSTANCE.getIDs();
            } catch (Throwable t) {
                t.printStackTrace();
                ids = EdisonClient.MOCK_INSTANCE.getIDs();
            }finally {
                if (bluetoothSocket != null) {
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            ids = EdisonClient.MOCK_INSTANCE.getIDs();
        }
        return ids;
    }

}
