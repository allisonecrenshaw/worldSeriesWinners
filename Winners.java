// import statements
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Winners {
    /***************************************************************
     * Method:       mainMenu
     * Description:  displays winner based on user-input year
     *                and number of times that team has won the WS
     * Parameters:   none
     * Returns:      none
     ****************************************************************/
    public void mainMenu() {
        // member method variables
        int inputYear; // year given by user
        Integer numberOfWins = 0; // dummy value just for now
        String winningTeam = ""; // team returned from findWinningTeam(), found based on user entered year
        Map<Integer, String> yearlyWinners; // HashMap created by and returned from readFile()
        Map<String, Integer> teamWins; // HashMap created by and returned from tallyWinners()

        // create map of annual winners from file & tally each team's total wins
        yearlyWinners = readFile();
        teamWins = tallyWins(yearlyWinners);

        // print map to test readFile
        // printYearlyWinners(yearlyWinners);

        // create scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // greeting message
        System.out.println("*************************************************************************");
        System.out.println("Welcome! This program tells you which baseball team won the World Series ");
        System.out.println("in a given year and how many times that team has won the World Series.");
        System.out.println("The World Series began in 1903 but was not played in years 1904 and 1994.");
        System.out.println("*************************************************************************");

        // prompt user for input first time
        System.out.print("Enter a year between 1903 and 2019 or enter 0 to end the program: ");
        inputYear = scanner.nextInt();

        while (inputYear != 0) { // allow user to run program multiple times until a sentinel value of 0 entered
            // print which team won in the given year
            if (inputYear == 1904 || inputYear == 1994) // World Series not played during these years
                System.out.println("There was not a World Series game played in the year " + inputYear + ".");
            else if (inputYear < 1903 || inputYear > 2019) // invalid years (before World Series began & after 2019)
                System.out.println("You have entered an invalid year. Please enter a year between 1903 and 2019.");
            else { // else statement for all valid input
                // find name of the winning team and how many times they have won
                winningTeam = findWinningTeam(inputYear, yearlyWinners);
                numberOfWins = teamWins.get(winningTeam);

                // print which team won and how many times they won
                System.out.println("The " + winningTeam + " won the World Series in " + inputYear + ".");
                if (numberOfWins == 1)
                    System.out.println("The " + winningTeam + " have won the World Series 1 time.");
                else
                    System.out.println("The " + winningTeam + " have won " + numberOfWins + " times.");
            }

            // allow user to choose to use the program again
            System.out.println();
            System.out.println("Enter a year between 1903 and 2019 to see info for another year ");
            System.out.print("or enter 0 to end the program: ");
            inputYear = scanner.nextInt();
        } // end while loop

    } // end method mainMenu()

    /***************************************************************
     * Method:       openFile
     * Description:  opens text file for reading, called by readFile
     * Parameters:   String fileName
     * Returns:      Scanner file
     ****************************************************************/
    public Scanner openFile(String fileName) {
        // create scanner object
        Scanner file;
       //  try catch block in case of error in opening file
        try {
            file = new Scanner(Paths.get(fileName));
        } // end try
        catch (IOException ioException) {
            System.out.println("Error: file not found.");
            file = null;
        } // end catch
        return file;
    } // end readFile()

    /***************************************************************
     * Method:       readFile
     * Description:  reads file into a HashMap w/ winning year as keys
     *                  and team names as values
     * Parameters:   none
     * Returns:      HashMap yearlyWinners
     ****************************************************************/
    public Map<Integer, String> readFile() {
        // method member variables
        Map<Integer, String> yearlyWinners = new HashMap<>();
        int year;
        String winner;

        // initialize current value for year
        // set at 1903 to start HashMap at 1st year of World Series
        year = 1903;

        // open file to read values into HashMap
        Scanner file = openFile("Program3.txt");

        // create HashMap w/ years as keys, teams as values
        while (file.hasNextLine()) { // while file still has remaining teams
            // assign next line to current winner
            winner = file.nextLine();
            // add year and winner as key/value pair in HashMap
            yearlyWinners.put(year, winner);
            year++;

            // skip years when World Series not played
            if (year == 1904 || year == 1994)
                year++; // increment again to skip years without winners
        } // end while loop

        return yearlyWinners;
    } // end readFile()


    /***************************************************************
     * Method:      printYearlyWinners()
     * Description: prints the HashMap key-value pairs to test
     *                  that file was read in properly
     * Parameters:  Map<Integer, String> yearlyWinners
     * Returns:     none
     ****************************************************************/
    static void printYearlyWinners(Map<Integer, String> yearlyWinners) {
        // print out HashMap to test that loop worked
        System.out.println("Year\tWinning Team");
        // start for loop
        for (Integer yearKey : yearlyWinners.keySet()) {
            System.out.println(yearKey + "\t" + yearlyWinners.get(yearKey));
        } // end for loop
    } // end printYearlyWinners()

    /***************************************************************
     * Method:          findWinningTeam()
     * Description:     gives a String name of the team that won in
     *                      a given year based on passed int year
     * Parameters:      int year, Map<Integer, String> yearlyWinners
     * Returns:         String winner
     ****************************************************************/
    public String findWinningTeam(int year, Map<Integer, String> yearlyWinners) {
        // member method variables
        String winner = "";

        // loop through HashMap to find winner based on given year
        for (Integer yearKey : yearlyWinners.keySet()) {
            if (yearKey == year)
                winner = yearlyWinners.get(yearKey);
        } // end for loop
        return winner;

    } // end findWinningTeam()

    /***************************************************************
     * Method:      tallyWins()
     * Description: counts number of times each winning team has
     *                  won throughout history of World Series
     * Parameters:  Map<Integer, String> yearlyWinners
     * Returns:     Map<String, Integer>
     ****************************************************************/
    public Map<String, Integer> tallyWins(Map<Integer, String> yearlyWinners) {
        // method member variables
        int numberOfWins;
        String currentWinner;
        Map<String, Integer> teamWins = new HashMap<>();

        // create HashMap of winners
        // loop through HashMap yearlyWinners and tally each team's wins
        for (Integer yearKey : yearlyWinners.keySet()) {
            currentWinner = yearlyWinners.get(yearKey);
            teamWins.putIfAbsent(currentWinner, 0); // line ignored if team already in HashMap
            teamWins.replace(currentWinner, teamWins.get(currentWinner) + 1);
        } // end for loop
        return teamWins;
    } // end tallyWins()
} // end class Winners
