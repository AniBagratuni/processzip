package com.example.processzip;

import com.example.processzip.service.UnzipFileService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
@EnableBatchProcessing
public class ProcesszipApplication implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    UnzipFileService unzipFileService;

    public static void main(String[] args) {
        SpringApplication.run(ProcesszipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String zipFilePath = System.getProperty("user.dir") + "/data.zip";
        unzipFileService.processZipFile(zipFilePath);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", UUID.randomUUID().toString())
                .addLong("JobId", System.currentTimeMillis())
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);
        System.out.println("STATUS :: " + execution.getStatus());
    }
}