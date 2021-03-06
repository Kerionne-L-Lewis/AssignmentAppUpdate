package com.company;


import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.company.Category.QUIZ;
import static com.company.EnumCourseDescript.Courses.AfricanAHist;
import static com.company.EnumCourseDescript.Courses.RaceCulComm;
import static com.company.Main.Priority.*;

public class Main {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);




    public static void main(String[] args)   {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Output the current date-time.
        LocalDateTime today = LocalDateTime.now();
        System.out.println("\nThe current date-time is " + today);

        //Output tomorrow's date using a formatter.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = today.format(formatter);
        System.out.println("Tomorrow's formatted date is " + formatDateTime);

        //Add 5 weeks to today's LocalDateTime.
        LocalDateTime fiveWeeksLater = today.plusWeeks(5);
        System.out.println("The five weeks, the date will be " + fiveWeeksLater);

        //Initialize a LocalDateTime object to your birthdate and the time 12:35 PM.
        LocalDateTime birthDate = LocalDateTime.of(1999, 2, 7, 12, 35);
        System.out.println("Your birthdate is " + birthDate);

        //Output the day of the week (Sunday-Saturday) that you were born.
        System.out.println("The day of the week of your birthdate was " + birthDate.getDayOfWeek());

        //Output the number of days you've been alive.
        System.out.println("The number of days you have been alive is " + ChronoUnit.DAYS.between(birthDate, today) + " days.");

        //Output the number of days between two dates.
        LocalDateTime obamaInauguration = LocalDateTime.of(2009, 2, 20, 12, 0);
        System.out.println("The number of days between your birthdate and Obama's inauguration is " + ChronoUnit.DAYS.between(birthDate, obamaInauguration) + " days.");

        //Given two dates, output the earlier.
        System.out.println("The earlier date is " + FindEarlierDate(today, obamaInauguration));

        //Create a file with 100 random "month/day/year  hour:minutes" (in that format) on each line.
        ArrayList<LocalDateTime> hundredRandomDates = randomDateArray(100);
        hundredRandomDates.forEach(d -> System.out.println("Date is " + d));

        //Output the number of stored dates in the year [Y].
        System.out.print("\nWhat is the year you want to find the dates of? ");
        ArrayList<LocalDateTime> datesOfUserYear = searchByYear(hundredRandomDates, sc.nextInt());
        System.out.println("The number of dates with that year is " + datesOfUserYear.size());

        //Count the number of stored dates in the current year.
        ArrayList<LocalDateTime> datesOfCurrentYear = searchByYear(hundredRandomDates, today.getYear());
        System.out.println("\nThe number of dates in the current year is " + datesOfCurrentYear.size());

        //Count the number of duplicates.
        ArrayList<LocalDateTime> duplicatedDates = seekDuplicates(hundredRandomDates);
        System.out.println("\nThere are " + duplicatedDates.size() + " duplicated dates.");

        // Sort the dates in chronological order.
        Collections.sort(hundredRandomDates);
        System.out.println("\nThe sorted dates are as followed: ");
        hundredRandomDates.forEach(d -> System.out.println(d));

        //Count the number of duplicates in a sorted list without using a Java Set.
        System.out.println("\nWithout using a Set, the number of duplicated dates are " + countDuplicates(hundredRandomDates));

        //Count the number of evening (after 6pm) dates.
        ArrayList<LocalDateTime> eveningDates = searchDatesInTimeframe(hundredRandomDates, 18, 24);
        System.out.println("\nThe number of evening dates are " + eveningDates.size());

        //Count the number of dates in each of the individual 12 months without using a Java Map.
        System.out.print("\nWhat is the number of the month you are searching the dates for? ");
        int month = sc.nextInt();
        if (month > 12 || month < 1) System.out.println("That month value is not valid.");
        else {
            ArrayList<LocalDateTime> datesOfMonth = searchByMonth(hundredRandomDates, month);
            System.out.println("The number of dates in month " + month + " is " + datesOfMonth.size());
        }

        //Count the number of dates in each of the individual 12 months using a Java Map.
        if (month >= 1 && month <= 12) {
            System.out.println("Using a Java Map, the number of dates in month " + month + " is " + mapByMonthSearch(hundredRandomDates, month));
        }

        //Determine the index of the latest LocalDateTime.
        System.out.println("\nThe index of the latest LocalDateTime is " + indexLatestDate(hundredRandomDates));

        //Determine the indexes of the elements that have the earliest starting time, regardless of date.
        System.out.println("\nThe index of the date with the earliest starting time is " + indexEarliestTime(hundredRandomDates));

        //Output a date in the format "January 1st, 2018".
        System.out.print("\nWhat is the index of the date you want to be outputted in the format \"January 1st, 2018\"? ");
        System.out.println("The formatted date is " + formattedDate(hundredRandomDates.get(sc.nextInt())));
        //I had completely forgotten to submit the previous story, so if it looks like I completed this story incredibly quickly, it's because I temporarily removed this code so I could post the for the previous story.


        DaysOfWeek day = DaysOfWeek.SATURDAY;
        System.out.println("Define and use DayOfWeek " + DaysOfWeek.MONDAY + " " + DaysOfWeek.TUESDAY + " " + DaysOfWeek.WEDNESDAY
                + " " + DaysOfWeek.THURSDAY + " " + DaysOfWeek.FRIDAY + " " + DaysOfWeek.SATURDAY + " " + DaysOfWeek.SUNDAY);
        System.out.println("Which day of the week is it ? " + day);

        EnumCourseDescript.Courses coursesNames = EnumCourseDescript.Courses.AfricanAHist;
        System.out.println(coursesNames);

        System.out.print("\nEnter a course (PersonalFinance, DataStructures, " +
                "RaceCulComm, AfricanAHist, or ResearchMethods): ");
        String crse = sc.next();
        EnumCourseDescript.Courses courseOne = EnumCourseDescript.Courses.valueOf(crse);
        courseOne.howIFeelAbout(courseOne);

        LocalDateTime midtermDate = LocalDateTime.of(2018,03,15,8,00);
        LocalDateTime finalExam= LocalDateTime.of(2017,12,06,10,30);

        Assignments assign1= new Assignments(finalExam,EnumCourseDescript.Courses.DataStructures, QUIZ, MEDIUM);
        System.out.println("Assignment 1: " + assign1);

        Assignments assign2 = new Assignments(midtermDate,EnumCourseDescript.Courses.ResearchMethods, Category.HOMEWORK, HIGH);
        System.out.println("Assignment 2: " + assign2);

        Assignments assign3=assign1.copy();
        System.out.println("Assignment 3: " + assign3);

        boolean assign4 = assign1.equals(assign3);
        System.out.println("\n(Override Assignment.equals) Are these the same assignments " + assign4);

        int num1 = assign1.compareTo(assign1);
        int num2 =assign1.compareTo(assign2);
        int num3 =assign2.compareTo(assign1);
        int num4=assign1.compareTo(assign3);


      //  System.out.println("results " + num1 + " " + num2 + " " + num3+ " "+num4);

        int earilest = earliest(num1, num2, num3);
        System.out.println("The earliest assignment is " + earilest );

        ArrayList<Assignments> assignmentsList = listOFAssignments(assign1,assign2,assign3);

        randomlyGenerateAssignFile(assignmentsList , 100);
        ArrayList<Assignments> assign= readAssignmentFromFile();


      /*  System.out.println("\nKerionne:\n\t" + assign.get(0) + "\n\t" + assign.get(1));
        boolean answer1 = assign.get( 0 ).equals( assign.get( 0 ));  // true
        boolean answer2 = assign.get( 0 ).equals( assign.get( 1 ));  // false
        boolean answer3 = assign.get( 1 ).equals( assign.get( 0 ));  // false
        System.out.println(answer1 + " "+  answer2+" " + " " +answer3);*/

        assign.add(assign.get(0));
        assign.add(assign.get(1));
        assign.add(assign.get(2));

     Set<Assignments> areThereDupes = removeDupes(assign);
        System.out.println("\nAre the dupes removed ? "+ areThereDupes);

        System.out.println("\n# assignments = " + assign.size() + " # Set + 3 = "
                + areThereDupes.size());

        int counter =countingCourse(assign,RaceCulComm);
        System.out.println("How many assignments for course [x] " + counter );

        ArrayList<Assignments>courseAssignments= whatAreCourseAssigns(assign, AfricanAHist);
        System.out.println("What are the course [C] assignments? " + courseAssignments);


        Collections.sort(assign, Collections.reverseOrder());
        System.out.println("ReverseList " + assign);


       // prioritySort(assign);
        Collections.sort(assign, (p,q) -> p.getLevels().ordinal()-q.getLevels().ordinal());
        System.out.println("did it sort by priority " + assign);

        Collections.sort(assign,(p,q)->p.getTime().compareTo(q.getTime()));
        System.out.println("Sort by time " + assign);

        Collections.sort( assign, (p,q) -> q.getTime().compareTo(p.getTime()));
        System.out.println("Sort time in reverse" + assign);


        Collections.sort( assign, (p,q) -> p.getCourses().ordinal() - q.getCourses().ordinal() );
        System.out.println("Sort by courses " + assign);


        ArrayList<Assignments>whichAssignmentsDue= findAssignmentDue(assign);
        System.out.println("What Assignments are due today " + whichAssignmentsDue);


        ArrayList<Assignments>whichAssignmentDueNextDays = dueNextDays(assign,15);
        System.out.println("What Assignments is due in [x] days " +whichAssignmentDueNextDays);

        ArrayList<Assignments>pastDue= whichArePastDue(assign);
        System.out.println("The pastDue assignments are " + pastDue);

       //TODO System.out.println("What is the next Assignment due for each course ");

     //   ArrayList<Assignments>nextAssignDue= whatIsNextAssign(assign);


        ArrayList<Assignments> highestPriorityStillDue =highPriority(assign);
        Collections.sort(highestPriorityStillDue,(p,q)->p.getTime().compareTo(q.getTime()) );
        System.out.println("Assignments with high priority thats still due " + highestPriorityStillDue);




    }

    private static ArrayList<Assignments> highPriority(ArrayList<Assignments> assign) {
        LocalDateTime mardiGras = LocalDateTime.of(2018,2,13, 11,59);
        LocalDateTime due = LocalDateTime.of(2018, 3, 31, 11, 59);
        ArrayList<Assignments> highPriority = new ArrayList<>();
        for (int i = 0; i < assign.size(); i++) {
            if ((assign.get(i).getLevels()).equals(HIGH)) {
                if (assign.get(i).getTime().isBefore(due) && assign.get(i).getTime().isAfter(mardiGras)) {
                    highPriority.add(assign.get(i));
                }
            }
        }
            return highPriority;
    }

   /* private static ArrayList<Assignments> whatIsNextAssign(ArrayList<Assignments> assign) {
        ArrayList<Assignments>nextAssignment= new ArrayList<>();
        LocalDateTime today =LocalDateTime.of(2018,3,1,10,30);
        for (Assignments courses:assign) {
           LocalDateTime dates= courses.getTime();
            if (dates.isAfter(today));
            nextAssignment.add(dates); //Add assignments with those dates, not just dates. For each may not work
        }
    }*/


    public static ArrayList<Assignments>whichArePastDue(ArrayList<Assignments> assign){
        ArrayList<Assignments> pastDueAssign = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for (int i = 0; i <assign.size() ; i++) {
            if (assign.get(i).getTime().isBefore(today)){
                pastDueAssign.add(assign.get(i));
            }
        }
        return pastDueAssign;
    }

    private static ArrayList<Assignments> dueNextDays(ArrayList<Assignments> assign, int days) {
       LocalDateTime today = LocalDateTime.now();
       LocalDateTime startTime = today.minusDays(days);
        ArrayList<Assignments>dueWithinNextDays = new ArrayList<>();
        for (int i = 0; i <assign.size() ; i++) {
            if (assign.get(i).getTime().isAfter(startTime) && assign.get(i).getTime().isBefore(today)){
                dueWithinNextDays.add(assign.get(i));
            }
        }
        return dueWithinNextDays;
    }

    private static ArrayList<Assignments> findAssignmentDue(ArrayList<Assignments> assign) {
       ArrayList<Assignments>due= new ArrayList<>();
       LocalDateTime today = LocalDateTime.now();
        for (int i = 0; i <assign.size() ; i++) {
            if (assign.get(i).equals(today)) {
                due.add(assign.get(i));
            }
        }
        return due;
    }

    public static void prioritySort(ArrayList<Assignments> list){
        for (int i = 0; i <list.size() ; i++) {
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(i).getLevels().equals(NOPRIORITY)) {
                    Assignments temp = list.get(i);

                    if (list.get(i).getLevels().equals(HIGH)) {
                        Assignments temp1 = list.get(j);

                        list.set(i, temp1);
                        list.set(j,temp);
                    }
                }

            }
        }
        }

    private static ArrayList<Assignments> whatAreCourseAssigns(ArrayList<Assignments> assign,
                                                               EnumCourseDescript.Courses course) {
        ArrayList<Assignments> courseAssignments = new ArrayList<>();
        for (int i = 0; i < assign.size(); i++) {
            if (assign.get(i).getCourses().equals(course)) {
                courseAssignments.add(assign.get(i));
            }
        }
        return courseAssignments;
    }
    private static int countingCourse( ArrayList<Assignments> assign, EnumCourseDescript.Courses course ) {
        int count = 0;
        for (int i = 0; i < assign.size(); i++) {
            if (assign.get(i).getCourses().equals(course)) {
                count++;
            }
        }
        return count;
    }

    private static Set<Assignments> removeDupes(ArrayList<Assignments> assign) {
        boolean result = false;
        Set<Assignments> noDupes = new HashSet<>();
        for (int i = 0; i <assign.size() ; i++) {
            noDupes.add(assign.get(i));
        }
        return noDupes;
    }

    public static ArrayList<Assignments> readAssignmentFromFile() {
        File readFile = new File("input.dat");
        ArrayList<Assignments>newList = new ArrayList<>();
        Assignments assign5=null;
        try (Scanner sc = new Scanner(readFile)) {
            while (sc.hasNext()) {
              String assigns = sc.nextLine();

                String[] parts = assigns.split(" , ");

                String[] partsLocalDateTime = parts[0].split("T");
                String[] date = partsLocalDateTime[0].split("-");
                String[] time = partsLocalDateTime[1].split(":");


                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                int hour = Integer.parseInt(time[0]);
                int minute = Integer.parseInt(time[1]);

                String course = parts[1];
                String category = parts[2];
                String priority= parts[3];

                LocalDateTime temp = LocalDateTime.of(year, month, day, hour, minute);
                EnumCourseDescript.Courses courses = EnumCourseDescript.Courses.valueOf(course);
                Category categorynew = Category.valueOf(category);
                Priority priority1 = Priority.valueOf(priority);


               assign5= new Assignments(temp, courses, categorynew, priority1  );
               newList.add(assign5);
               // System.out.print("\n\nKerionne: " +assign5);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static ArrayList<Assignments> listOFAssignments(Assignments assign1, Assignments assign2, Assignments assign3) {
        ArrayList<Assignments>assignments = new ArrayList<>();
        assignments.add(assign1);
        assignments.add(assign2);
        assignments.add(assign3);
        return assignments;
    }

    public static void randomlyGenerateAssignFile(ArrayList<Assignments> assignmentsList, int num) {
                File outfile = new File("input.dat");
                try (PrintWriter pw = new PrintWriter(outfile)) {
                    for (int i = 0; i < num; i++) {
                        LocalDateTime randomDate= randomDateGenerator();
                         Category randEnums= Category.values()[new Random().nextInt(Category.values().length)];
                        EnumCourseDescript.Courses randCourses = EnumCourseDescript.Courses.values()
                                [new Random().nextInt(EnumCourseDescript.Courses.values().length)];
                        Priority randPriority= Priority.values()[new Random().nextInt(Priority.values().length)];

                        Assignments temp = new Assignments(randomDate, randCourses,
                                randEnums,randPriority);

                        pw.println(temp);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

    }

    private static int earliest(int num1, int num2, int num3) {
       int earliest;
        if (num1<num2&& num1<num3){
            earliest=num1;
        }
        else if (num2< num3 && num2<num1){
            earliest=num2;
        }
        else
            earliest= num2;

     return earliest;}

    private static String formattedDate(LocalDateTime date) {
        String newDate = "";
        newDate += date.format(DateTimeFormatter.ofPattern("MMMM "));
        newDate += intToOrdinal(date.getDayOfMonth());
        newDate += date.format(DateTimeFormatter.ofPattern(", yyyy"));
        return newDate;
    }

    private static String intToOrdinal(int num) {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        //I probably should have made this into a switch/case.
        if (num % 100 == 11 || num % 100 == 12 || num % 100 == 13) {
            return num + "th";
        } else {
            return num + suffixes[num % 10];
        }
    }

    private static Integer indexEarliestTime(ArrayList<LocalDateTime> dateList) {
        LocalDateTime earliestDateTime = dateList.get(0);
        for (LocalDateTime date : dateList) {
            if (earliestDateTime.toLocalTime().isAfter(date.toLocalTime())) earliestDateTime = date;
        }
        return dateList.indexOf(earliestDateTime);
    }

    private static Integer indexLatestDate(ArrayList<LocalDateTime> dateList) {
        return dateList.indexOf(Collections.max(dateList));
    }

    private static Integer mapByMonthSearch(ArrayList<LocalDateTime> dateList, int month) {
        return mapByMonth(dateList).get(month);
    }

    private static Map<Integer, Integer> mapByMonth(ArrayList<LocalDateTime> dateList) {
        Map<Integer, Integer> returnMap = new HashMap<>();
        for (LocalDateTime date : dateList) {
            Integer count = returnMap.get(date.getMonthValue());
            returnMap.put(date.getMonthValue(), (count == null) ? 1 : count + 1);
        }
        return returnMap;
    }

    private static ArrayList<LocalDateTime> searchByMonth(ArrayList<LocalDateTime> dateList, int month) {
        return (ArrayList) dateList.stream()
                .filter(date -> date.getMonthValue() == month)
                .collect(Collectors.toList());
    }

    private static ArrayList searchDatesInTimeframe(ArrayList<LocalDateTime> dateList, int startHour, int endHour) {
        return (ArrayList) dateList.stream()
                .filter(date -> date.getHour() >= startHour && date.getHour() < endHour)
                .collect(Collectors.toList());
    }

    private static int countDuplicates(ArrayList<LocalDateTime> hundredRandomDates) {
        int count = 0;
        for (LocalDateTime date : hundredRandomDates) {
            if (Collections.frequency(hundredRandomDates, date) >= 2) count++;
        }
        return count;
    }

    private static ArrayList<LocalDateTime> seekDuplicates(ArrayList<LocalDateTime> userList) {
        ArrayList<LocalDateTime> returnArray = new ArrayList<>();
        Set<LocalDateTime> dateSet = new HashSet<>();
        for (LocalDateTime date : userList) {
            if (dateSet.contains(date)) returnArray.add(date);
            dateSet.add(date);
        }
        return returnArray;
    }

    private static ArrayList<LocalDateTime> searchByYear(ArrayList<LocalDateTime> listOfLocalDateTimes, int year) {
        return (ArrayList) listOfLocalDateTimes.stream()
                .filter(date -> date.getYear() == year)
                .collect(Collectors.toList());
    }

    private static ArrayList<LocalDateTime> randomDateArray(int NumElements) {
        ArrayList<LocalDateTime> returnArray = new ArrayList<>();
        for (int i = 0; i < NumElements; i++) {
            returnArray.add(randomDateGenerator());
        }
        return returnArray;
    }

    private static LocalDateTime randomDateGenerator() {
        long startOfTime = ChronoUnit.MINUTES.between(LocalDateTime.of(2016, 1, 1, 0, 0)
                , LocalDateTime.now());
        long minutes = rand.nextInt((int) startOfTime);
        return LocalDateTime.now().minusMinutes(minutes);
    }

    private static LocalDateTime FindEarlierDate(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime earlyDate = date1;
        if (date2.isBefore(date1)) {
            earlyDate = date2;
        }
        return earlyDate;
    }

    private static class Assignments implements Comparable<Assignments>   {
        private LocalDateTime time;
        private EnumCourseDescript.Courses courses;
        private Category subjects;
        private Priority levels;
        private int t;

        public Assignments(LocalDateTime time, EnumCourseDescript.Courses courses,
                           Category subjects, Priority levels) {
            this.time = time;
            this.courses = courses;
            this.subjects = subjects;
            this.levels = levels;
        }


        public LocalDateTime getTime() {
            return time;
        }

        public EnumCourseDescript.Courses getCourses() {
            return courses;
        }

        public Category getSubjects() {
            return subjects;
        }

        public Priority getLevels() {
           /* if (levels.equals(NOPRIORITY))
                System.out.print(0);
            else if (levels.equals(LOW))
                System.out.print(1);
            else if (levels.equals(MEDIUM))
                System.out.print(2);
           //else
              //  System.out.print(3);*/

            return levels;
        }

        @Override
        public String toString() {
            return
                     getTime() +
                            " , " + getCourses() +
                            " , " + getSubjects() +
                            " , " + getLevels()
                            ;
        }

        public int compareTo(Assignments rhs) {

            return this.time.compareTo(rhs.time);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Assignments that = (Assignments) o;
            return Objects.equals(time, that.time);
        }
        @Override
        public int hashCode() {

            return Objects.hash(time);
        }

        public Assignments copy() {
            Assignments answer = new Assignments(this.time, this.courses, this.subjects, this.levels);
            return answer;


        }


    }

enum Priority{
      NOPRIORITY, LOW , MEDIUM, HIGH
    }
}

enum Category {
    HOMEWORK, QUIZ, PRESENTATION, FINAL_EXAM
}

class EnumCourseDescript {
    Courses courses;

    public EnumCourseDescript(Courses courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
        if (o == null|| getClass() != o.getClass()) return false;
        EnumCourseDescript that = (EnumCourseDescript) o;
        return courses == that.courses;
     // return this.equals(courses);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    enum Courses {
        PersonalFinance, DataStructures, RaceCulComm, AfricanAHist, ResearchMethods;

        public void howIFeelAbout(Courses crse) {
            switch (crse) {
                case ResearchMethods:
                    System.out.println("Research Methods is so hard!");
                    break;

                case AfricanAHist:
                    System.out.println(" African American History is better.");
                    break;
                default:
                    System.out.println("All my classes are alot of work");
                    break;
            }
        }
    }

}
    enum DaysOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }
