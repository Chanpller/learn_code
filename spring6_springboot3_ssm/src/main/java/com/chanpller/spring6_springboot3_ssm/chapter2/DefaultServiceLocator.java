package com.chanpller.spring6_springboot3_ssm.chapter2;

public class DefaultServiceLocator {

    private static ClientService  clientService= new ClientService();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
