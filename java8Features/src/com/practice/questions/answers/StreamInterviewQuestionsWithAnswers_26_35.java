package com.practice.questions.answers;

import java.util.*;
import java.util.stream.*;

public class StreamInterviewQuestionsWithAnswers_26_35 {
    public static void main(String[] args) {
        // Question 26: Stream with JPA entities
        // Explanation: Stream JPA entities for filtering or mapping (simulate with POJOs here).
        class Entity { int id; boolean active; Entity(int id, boolean active) { this.id = id; this.active = active; } public int getId() { return id; } public boolean isActive() { return active; } }
        List<Entity> entities = Arrays.asList(new Entity(1, true), new Entity(2, false), new Entity(3, true));
        List<Entity> activeEntities = entities.stream().filter(Entity::isActive).collect(Collectors.toList());
        System.out.println("Active Entities: " + activeEntities.size());

        // Question 27: Stream with DTO mapping
        // Explanation: Map entities to DTOs using map().
        class EntityDTO { int id; EntityDTO(int id) { this.id = id; } public String toString() { return "DTO:" + id; } }
        List<EntityDTO> dtos = entities.stream().map(e -> new EntityDTO(e.getId())).collect(Collectors.toList());
        System.out.println("DTOs: " + dtos);

        // Question 28: Stream with REST API responses
        // Explanation: Filter and map REST API responses (simulate with POJOs).
        class Response { String status; String data; Response(String status, String data) { this.status = status; this.data = data; } public String getStatus() { return status; } public String getData() { return data; } }
        List<Response> responses = Arrays.asList(new Response("SUCCESS", "A"), new Response("FAIL", "B"), new Response("SUCCESS", "C"));
        List<String> successData = responses.stream().filter(r -> r.getStatus().equals("SUCCESS")).map(Response::getData).collect(Collectors.toList());
        System.out.println("Success Data: " + successData);

        // Question 29: Stream with pagination
        // Explanation: Use skip() and limit() for pagination.
        List<Integer> paged = IntStream.rangeClosed(1, 100).boxed().skip(10).limit(10).collect(Collectors.toList());
        System.out.println("Paged (11-20): " + paged);

        // Question 30: Stream with sorting and filtering in repositories
        // Explanation: Filter and sort a list (simulate repository data).
        List<Entity> sortedActive = entities.stream().filter(Entity::isActive).sorted(Comparator.comparing(Entity::getId).reversed()).collect(Collectors.toList());
        System.out.println("Sorted Active Entities: " + sortedActive.size());

        // Question 31: Stream with security filtering
        // Explanation: Filter data based on user roles (simulate with strings).
        List<String> roles = Arrays.asList("ADMIN", "USER", "GUEST");
        List<String> allowed = roles.stream().filter(r -> !r.equals("GUEST")).collect(Collectors.toList());
        System.out.println("Allowed Roles: " + allowed);

        // Question 32: Stream with transactional operations
        // Explanation: Use streams inside transactions (simulate with a list update).
        List<Entity> updated = entities.stream().peek(e -> { if (e.active) e.id += 100; }).collect(Collectors.toList());
        System.out.println("Updated Entities: " + updated.size());

        // Question 33: Stream with caching
        // Explanation: Use streams to filter cached data (simulate with a map).
        Map<Integer, String> cache = new HashMap<>(); cache.put(1, "A"); cache.put(2, "B");
        List<String> cached = cache.entrySet().stream().filter(e -> e.getKey() == 1).map(Map.Entry::getValue).collect(Collectors.toList());
        System.out.println("Cached Values: " + cached);

        // Question 34: Stream with asynchronous processing
        // Explanation: Use parallelStream for async-like processing (simulate with parallelStream).
        List<Integer> asyncProcessed = numbers.parallelStream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println("Async Processed: " + asyncProcessed);

        // Question 35: Stream with validation
        // Explanation: Validate data using allMatch/anyMatch/noneMatch.
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("All Positive: " + allPositive);
    }
}
