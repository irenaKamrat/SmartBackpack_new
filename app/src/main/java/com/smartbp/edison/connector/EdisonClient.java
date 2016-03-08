package com.smartbp.edison.connector;

import com.smartbp.edison.connector.bluetooth.EdisonBluetoothClientImpl;

import java.util.List;

/**
 * Created by lazara on 08/03/2016.
 */
public interface EdisonClient {

    public static final EdisonClient INSTANCE = new EdisonBluetoothClientImpl();

    public List<String> getIDs();
}
