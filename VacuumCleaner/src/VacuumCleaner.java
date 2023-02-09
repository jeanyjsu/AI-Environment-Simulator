/*****************************
 * Ya Ching Su
 * T00645580
 * COMP 3711 - Assignment 1
 * VacuumCleaner.java
 * In this assignment, I implemented a software program that accepts 5 inputs as command line arguments using the template
 * on moodle. The program then will evaluate the next move of the vacuum and print the action on the screen.
 */
public class VacuumCleaner {
    public static void main(String args[]) {
        char currentLocation = args[0].charAt(0);

        //state:true -> clean, false -> dirty
        boolean spotA = Boolean.parseBoolean(args[1]);
        boolean spotB = Boolean.parseBoolean(args[2]);
        boolean spotC = Boolean.parseBoolean(args[3]);
        boolean spotD = Boolean.parseBoolean(args[4]);
        System.out.println("Current Location = " + currentLocation + "\n" +
                "Square A Status = " + spotA + "\n" +
                "Square B Status = " + spotB + "\n" +
                "Square C Status = " + spotC + "\n" +
                "Square D Status = " + spotD + "\n");

        //actual coding start here
        boolean allClean = true;
        boolean[] allSpots = {spotA, spotB, spotC, spotD};
        int [][] theSquare = {{0,0}, {0,1}, {1,0}, {1,1}};
        int currentX;
        int currentY;
        currentX = theSquare[currentLocation -'A'][0];
        currentY = theSquare[currentLocation -'A'][1];
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Status");

        //this just to show the status of each spot, this is just for me to see easier what is clean what is dirty.
        //also a way to check to see if all the squares are clean.
        for (int i = 0; i < allSpots.length; i++)
        {
            if (allSpots[i])
            {
                System.out.println((char) ('A' + i) + ": clean");
            }
            else
            {
                System.out.println((char) ('A' + i) + ": dirty");
                allClean = false;
            }
        }
        // now that we know if all the squares are clean, the vacuum stays in current position if it is.
        if (allClean)
        {
            System.out.println("The environment is clean, vacuum cleaner is staying at the current location to save energy.");
            System.out.println("Action - Staying At = " + currentLocation);
            System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
            return;
        }
        else
        {
            // if it is the current spot that is dirty, then the vacuum will stay to clean
            if (currentLocation == 'A' && !spotA || currentLocation == 'B' && !spotB || currentLocation == 'C' && !spotC || currentLocation == 'D' && !spotD)
            {
                System.out.println("This current location is dirty so the vacuum is staying to clean");
                System.out.println("Action - Cleaning At = " + currentLocation);
                System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                return;
            }
            else
            {
                //for horizontal movement checks for if the spot is dirty and comparing the coordinates using 2d array
                for(int i = 0; i < allSpots.length; i++) {
                    int x = theSquare[i][0];
                    int y = theSquare[i][1];
                    if (!allSpots[i] && x == currentX && (y == currentY + 1 || y == currentY - 1)) {
                        currentLocation = (char) ('A' + i);
                        System.out.println("Action - Cleaning At = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                        return;
                    }
                }

                //for vertical movement checks for if the spot is dirty and comparing the coordinates using 2d array
                for(int i = 0; i < allSpots.length; i++)
                {
                    int x = theSquare[i][0];
                    int y = theSquare[i][1];
                    if(!allSpots[i] && y == currentY && (x == currentX + 1 || x == currentX - 1))
                    {
                        currentLocation = (char)('A' + i);
                        System.out.println("Action - Cleaning At = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                        return;
                    }
                }

                //for diagonal check. I'm sure there are better way to accomplish this, but after days of trying, hard coding
                //in the conditions and the currentLocation in seems to work the best right now.
                for(int i = 0; i < allSpots.length; i++)
                {
                    if(currentLocation == 'A' && !allSpots[3])
                    {
                        currentLocation = 'C';
                        System.out.println("Action - Moving to = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                    }
                    else if(currentLocation == 'B' && !allSpots[2])
                    {
                        currentLocation = 'D';
                        System.out.println("Action - Moving to = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                    }
                    else if(currentLocation == 'C' && !allSpots[1])
                    {
                        currentLocation = 'A';
                        System.out.println("Action - Moving to = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                    }
                    else if(currentLocation == 'D' && !allSpots[0])
                    {
                        currentLocation = 'B';
                        System.out.println("Action - Moving to = " + currentLocation);
                        System.out.println("Action - Next Location = " + nextLocation(currentLocation, spotA, spotB, spotC, spotD));
                    }
                }
            }
        }
    }

    //this is the next location prediction that takes in the currentLocation, and the boolean values of all the spots.
    //Again, I'm sure there are more efficient way to do this. I basically made this method repeat what the main method
    //do with the currentLocation to figure out where the vacuum agent should go next following the rules.
    //Also added in the beginning to change the boolean state of the current spot, because I forgot to do that in the
    //main method.
    public static char nextLocation(char current, boolean A, boolean B, boolean C, boolean D)
    {
        char next = current;
        boolean[] allSpots = {A, B, C, D};
        boolean allClean = false;
        int [][] theSquare = {{0,0}, {0,1}, {1,0}, {1,1}};
        int currentX = theSquare[current -'A'][0];
        int currentY = theSquare[current -'A'][1];
        for(int i = 0; i < allSpots.length; i++)
        {
            if(current == 'A' && allSpots[i] == !A)
            {
                A = true;
            }
            else if(current == 'B' && allSpots[i] == !B)
            {
                B = true;
            }
            else if(current == 'C' && allSpots[i] == !C)
            {
                C = true;
            }
            else if(current == 'D' && allSpots[i] == !D)
            {
                D = true;
            }
        }
        for (int i = 0; i < allSpots.length; i++)
        {
            if(allSpots[i])
            {
                allClean = true;
            }
            else
            {
                    int x = theSquare[i][0];
                    int y = theSquare[i][1];
                    if (!allSpots[i] && x == currentX && (y == currentY + 1 || y == currentY - 1))
                    {
                        current = (char) ('A' + i);
                        next = current;
                    }
                    else if(!allSpots[i] && y == currentY && (x == currentX + 1 || x == currentX - 1))
                {
                    current = (char)('A' + i);
                    next = current;
                }
            }
        }
        return next;
    }
}
