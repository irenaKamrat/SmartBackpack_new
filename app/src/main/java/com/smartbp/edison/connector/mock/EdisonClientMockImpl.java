package com.smartbp.edison.connector.mock;

import com.smartbp.edison.connector.EdisonClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ikamrat on 08/03/2016.
 */
public class EdisonClientMockImpl implements EdisonClient {

    private State mockState = State.START;
    private List<String> rfIds = new ArrayList<>();

    public EdisonClientMockImpl(){
        rfIds.addAll(Arrays.asList("97", "114", "3"));
    }

    @Override
    public List<String> getIDs() {
        switch (mockState){
            case START:
                mockState = State.INSERT_1;
                break;
            case INSERT_1:
                rfIds.add("220");
                rfIds.add("181");
                mockState = State.INSERT_2;
                break;
            case INSERT_2:
                rfIds.add("206");
                mockState = State.REMOVE;
                break;
            case REMOVE:
                mockState = State.FINAL;
                rfIds.remove("3");
            case FINAL:
                break;
        }

        return rfIds;
    }
}
