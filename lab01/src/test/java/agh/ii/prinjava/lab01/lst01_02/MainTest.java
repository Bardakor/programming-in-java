package agh.ii.prinjava.lab01.lst01_02;

import agh.ii.prinjava.lab01.lst01_01.HelloEncapsulation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testMain() {
        RichDad richDad = new RichDad("R", "Doe", BigDecimal.valueOf(1), List.of("School Mate"));
        richDad.increaseWealth();
        // Add assertions to check if the wealth increased as expected or other behaviors

        RichDadsKid richDadsKid =
                new RichDadsKid("Mike", richDad.surname, richDad.getFortune(), richDad.getContacts());
        // Add assertions to check if the richDadsKid was created correctly

        richDadsKid.setUpSuccessfulCompany();
        // Add assertions to check if the setup of a successful company worked as expected


    }
}
