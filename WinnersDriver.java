/***************************************************************
 * Developer:       Allison Crenshaw
 * Program #:       3
 * Chapter:         Chapter 16
 * Subject:         Baseball World Series Winners
 * File Name:       Program3Baseball.java
 * Course:          ITSE 2317 - Intermediate Java
 * Due Date:        02/25/2020
 * Instructor:      Fred Kumi
 * Description:     Program reads in a file of the team name
 *                  of every team who has ever won the World Series.
 *                  Program scans this file and creates a hashmap
 *                  of winners:
 *                      Key: Name of winning team
 *                      Value: Number of World Series wins
 *                  User interface: Program prompts user to
 *                  enter a team name. Program prints out number
 *                  of times that team has won the World Series.
 ****************************************************************/
// TODO Put user prompts into loop to allow repeat until sentinel value 0 entered
// TODO Create second HashMap to hold number of wins per team
// TODO Use a loop to loop through first HashMap and add team wins to second HM

public class WinnersDriver {
    public static void main(String[] args) {
        // create driver program
        Winners driver = new Winners();

        // call methods to drive program
        driver.mainMenu();

        // call method to show developer info
        developerInfo();
    } // end main

    //***************************************************************
    //  Method:       developerInfo
    //  Description:  The developer information method of the program
    //  Parameters:   None
    //  Returns:      N/A
    //**************************************************************
    public static void developerInfo() {
        System.out.println("");
        System.out.println("*************************************************");
        System.out.println ("Name:    Allison Crenshaw");
        System.out.println ("Course:  ITSE 2317 Intermediate Java Programming");
        System.out.println ("Program: Three");
        System.out.println("*************************************************");
    } // End of developerInfo

} // end class
