package client;

import java.io.IOException;


public  class Test {

    Client client=null;
    public Test(){
        client= new Client();
    }

    public void pass (String info) throws IOException, InterruptedException {
        client.pass(info);
    }

    public  String get(){
        return client.get();
    }
}