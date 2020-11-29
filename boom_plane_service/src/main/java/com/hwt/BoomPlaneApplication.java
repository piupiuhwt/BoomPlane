package com.hwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.Properties;

@SpringBootApplication
public class BoomPlaneApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(BoomPlaneApplication.class);
    }
}
