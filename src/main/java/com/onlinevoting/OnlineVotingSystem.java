package com.onlinevoting;
import java.util.*;

class Voter {
    String name;
    String voterId;
    boolean hasVoted;

    Voter(String name, String voterId) {
        this.name = name;
        this.voterId = voterId;
        this.hasVoted = false;
    }
}

public class OnlineVotingSystem {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Voter> voterDatabase = new HashMap<>();
    static HashMap<String, Integer> candidates = new HashMap<>();

    public static void main(String[] args) {
        // Pre-defined candidates
        candidates.put("Alice", 0);
        candidates.put("Bob", 0);
        candidates.put("Charlie", 0);

        while (true) {
            System.out.println("\n---- ONLINE VOTING SYSTEM ----");
            System.out.println("1. Register Voter");
            System.out.println("2. Vote");
            System.out.println("3. View Result (Admin)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerVoter();
                    break;
                case 2:
                    vote();
                    break;
                case 3:
                    viewResult();
                    break;
                case 4:
                    System.out.println("Thank you for using the system.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void registerVoter() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter Voter ID: ");
        String id = sc.nextLine();

        if (voterDatabase.containsKey(id)) {
            System.out.println("Voter ID already registered.");
        } else {
            voterDatabase.put(id, new Voter(name, id));
            System.out.println("Voter registered successfully!");
        }
    }

    static void vote() {
        System.out.print("Enter your Voter ID: ");
        String id = sc.nextLine();

        if (!voterDatabase.containsKey(id)) {
            System.out.println("Voter not found. Please register first.");
            return;
        }

        Voter voter = voterDatabase.get(id);
        if (voter.hasVoted) {
            System.out.println("You have already voted.");
            return;
        }

        System.out.println("Candidates:");
        for (String candidate : candidates.keySet()) {
            System.out.println("- " + candidate);
        }

        System.out.print("Enter your vote (Candidate name): ");
        String vote = sc.nextLine();

        if (candidates.containsKey(vote)) {
            candidates.put(vote, candidates.get(vote) + 1);
            voter.hasVoted = true;
            System.out.println("Thank you for voting!");
        } else {
            System.out.println("Invalid candidate.");
        }
    }

    static void viewResult() {
        System.out.print("Enter admin password to view results: ");
        String password = sc.nextLine();

        if (!password.equals("admin123")) {
            System.out.println("Incorrect password.");
            return;
        }

        System.out.println("\n--- Voting Results ---");
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }
}