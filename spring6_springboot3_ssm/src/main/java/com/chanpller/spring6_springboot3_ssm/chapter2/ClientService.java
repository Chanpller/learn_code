package com.chanpller.spring6_springboot3_ssm.chapter2;

public class ClientService {
    private static ClientService clientService = new ClientService();
    public ClientService() {}

    public static ClientService createInstance() {

        return clientService;
    }
}
