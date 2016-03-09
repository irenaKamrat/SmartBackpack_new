package com.smartbp.edison.connector;

import com.smartbp.edison.connector.bluetooth.EdisonBluetoothClientImpl;
import com.smartbp.edison.connector.mock.EdisonClientMockImpl;

import java.util.List;

/**
 * Created by lazara on 08/03/2016.
 */
public interface EdisonClient {

    public static final EdisonClient MOCK_INSTANCE = new EdisonClientMockImpl();

    public static final EdisonClient INSTANCE = new EdisonBluetoothClientImpl();

    public List<String> getIDs();
}
