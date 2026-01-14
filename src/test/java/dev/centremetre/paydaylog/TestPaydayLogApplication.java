package dev.centremetre.paydaylog;

import org.springframework.boot.SpringApplication;

public class TestPaydayLogApplication
{

    public static void main(String[] args)
    {
        SpringApplication.from(PaydayLogApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
