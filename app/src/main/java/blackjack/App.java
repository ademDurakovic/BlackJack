/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package blackjack;

import blackjack.model.*;
import blackjack.view.*;
import blackjack.controller.*;

public class App {

    public static void main(String[] args) {

        User player = new User();

        Controller controller = new Controller(player);
    }
}
