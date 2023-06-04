package hr.tvz.biljan.studapp.quartz;

import hr.tvz.biljan.studapp.infrastructure.persistence.StudentRepository;
import hr.tvz.biljan.studapp.models.Student;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class Job extends QuartzJobBean {

    private final StudentRepository studentRepository;

    public Job(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Student> hardwareList = studentRepository.findAll();
        for (var student : hardwareList) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }
}

@Configuration
public class SchedulerConfiguration {
    @Bean
    public JobDetail hardwareListJobBuilder() {
        return JobBuilder.newJob(Job.class)
                .withIdentity("hardwareListJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger hardwareListJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(hardwareListJobBuilder())
                .withIdentity("hardwareListTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}