package agh.ii.prinjava.lab07.Part6;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // EXO 1

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Alice", "123 Main St", Optional.of("alice@example.com")));
        clients.add(new Client("Bob", "456 Elm St", Optional.empty()));
        clients.add(new Client("Charlie", "789 Oak St", Optional.of("charlie@example.com")));

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("City A", "City B", LocalDate.of(2023, 11, 25), clients.get(0), 100));
        tickets.add(new Ticket("City B", "City C", LocalDate.of(2023, 11, 26), clients.get(1), 150));
        tickets.add(new Ticket("City C", "City A", LocalDate.of(2023, 11, 27), clients.get(2), 200));

    }

    public static int countTicketsWithDestination(List<Ticket> tickets, String destination) {
        return (int) tickets.stream()
                .filter(ticket -> ticket.destination().equals(destination))
                .count();
    }

    public static void printTicketsForDate(List<Ticket> tickets, LocalDate date) {
        tickets.stream()
                .filter(ticket -> ticket.date().equals(date))
                .forEach(System.out::println);
    }

    public static boolean hasTicketForClient(List<Ticket> tickets, String clientName) {
        return tickets.stream()
                .anyMatch(ticket -> ticket.client().name().equals(clientName));
    }

    public static double CalculateAverageTicketPrice(List<Ticket> tickets) {
        return tickets.stream()
                .mapToInt(Ticket::priceInUnits)
                .average()
                .orElse(0.0);
    }

    public static boolean allClientHaveEmail(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::client)
                .allMatch(client -> client.email().isPresent());
    }

    public static String getCommaSeparatedDestination(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::destination)
                .collect(Collectors.joining(","));
    }
}

// Your record definitions
record Client(String name, String address, Optional<String> email) {
}

record Ticket(String departure, String destination, LocalDate date, Client client, int priceInUnits) {
}