package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("KIA", 123)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Opel", 456)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Hunday", 789)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Toyota", 963)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + "Model: " + user.getCar().getModel()
                  + ". Series: " + user.getCar().getSeries());
         System.out.println();
      }
      userService.getUserByCar(new Car("Opel", 456));

      context.close();
   }
}
