package com.example.demo;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.customer.Customer;
import com.example.customer.CustomerRepository;
import com.example.customer.CustomerService;

@SpringBootApplication
@ComponentScan({"com.example"})
@EnableJpaRepositories("com.example")
@EntityScan("com.example")
public class SpringTestApplication {

	@Autowired
	CustomerRepository customerRepository;
	
	private static final Logger log = LoggerFactory.getLogger(SpringTestApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringTestApplication.class);
//	    app.setWebApplicationType(WebApplicationType.NONE);
//	    ApplicationListener<ApplicationEvent> listeners=new ApplicationListener<ApplicationEvent>() {
//
//			@Override
//			public void onApplicationEvent(ApplicationEvent event) {
//				System.out.println("hello-------");
//				
//			}
//		};
//	    app.addListeners(listeners);
//	    app.setBannerMode(Banner.Mode.LOG);
		app.run(args);

	}

//	  @Bean
//	    public HttpMessageConverters customConverters() {
//	        HttpMessageConverter<?> additional = new HttpMessageConverters(additionalConverters)
//	        HttpMessageConverter<?> another = ...
//	        return new HttpMessageConverters(additional, another);
//	    }

//	 @Bean
//	    public ExitCodeGenerator exitCodeGenerator() {
//	        return () -> 42;
//	    }
//	 
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}

	@Bean
	public CommandLineRunner demo(ApplicationContext ctx,CustomerRepository repository,CustomerService customerService) {
		return (args) -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

			
			
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			
			log.info("Customer with first name");
			repository.findByFirstName("Kim").forEach(Kim -> {
				log.info(Kim.toString());
			});;
			log.info("");
			
			log.info("CustomerService--");
			customerService.findAll().forEach(customer1->{
				log.info(customer1.toString());
			});
		};
	}
}
