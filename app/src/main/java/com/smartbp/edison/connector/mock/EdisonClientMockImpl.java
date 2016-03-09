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
        rfIds.addAll(Arrays.asList("97", "206", "114", "3"));
    }

    @Override
    public List<String> getIDs() {
        switch (mockState){
            case START:
                mockState = State.INSERT;
                break;
            case INSERT:
                rfIds.add("220");
                rfIds.add("181");
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
