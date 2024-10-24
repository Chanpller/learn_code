package com.chanpller.spring6_springboot3_ssm.chapter2;

import org.springframework.beans.factory.FactoryBean;

public class HappyFactoryBean implements FactoryBean<HappyMachine> {

    private String machineName;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    @Override
    public HappyMachine getObject() throws Exception {

        // 方法内部模拟创建、设置一个对象的复杂过程
        HappyMachine happyMachine = new HappyMachine();

        happyMachine.setMachineName(this.machineName);

        return happyMachine;
    }

    @Override
    public Class<?> getObjectType() {

        // 返回要生产的对象的类型
        return HappyMachine.class;
    }
}