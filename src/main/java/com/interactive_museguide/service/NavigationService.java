package com.interactive_museguide.service;

import com.interactive_museguide.model.Edge;
import com.interactive_museguide.repository.EdgeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NavigationService {

  private final EdgeRepository edgeRepository;

  public List<String> findRoute(
      Long startId,
      Long endId) {

    Map<Long, List<Neighbor>> graph = buildGraph();

    return dijkstra(graph, startId, endId);
  }

  private Map<Long, List<Neighbor>> buildGraph() {

    Map<Long, List<Neighbor>> graph = new HashMap<>();

    for (Edge edge : edgeRepository.findAll()) {

      graph.computeIfAbsent(
              edge.getFromLocation().getId(),
              k -> new ArrayList<>())
          .add(new Neighbor(
              edge.getToLocation().getId(),
              edge.getDistance(),
              edge.getInstruction()));

      graph.computeIfAbsent(
              edge.getToLocation().getId(),
              k -> new ArrayList<>())
          .add(new Neighbor(
              edge.getFromLocation().getId(),
              edge.getDistance(),
              edge.getInstruction()));
    }

    return graph;
  }

  private List<String> dijkstra(
      Map<Long, List<Neighbor>> graph,
      Long startId,
      Long endId) {

    Map<Long, Integer> distances = new HashMap<>();
    Map<Long, Long> previous = new HashMap<>();
    Map<Long, String> instructions = new HashMap<>();

    PriorityQueue<NodeDistance> queue =
        new PriorityQueue<>(
            Comparator.comparingInt(NodeDistance::distance));

    distances.put(startId, 0);
    queue.add(new NodeDistance(startId, 0));

    while (!queue.isEmpty()) {

      NodeDistance current = queue.poll();

      Long currentNode = current.nodeId();

      if (currentNode.equals(endId)) {
        break;
      }

      List<Neighbor> neighbors =
          graph.getOrDefault(currentNode, List.of());

      for (Neighbor neighbor : neighbors) {

        int newDistance =
            distances.get(currentNode)
                + neighbor.distance();

        if (newDistance <
            distances.getOrDefault(
                neighbor.targetId(),
                Integer.MAX_VALUE)) {

          distances.put(
              neighbor.targetId(),
              newDistance);

          previous.put(
              neighbor.targetId(),
              currentNode);

          instructions.put(
              neighbor.targetId(),
              neighbor.instruction());

          queue.add(
              new NodeDistance(
                  neighbor.targetId(),
                  newDistance));
        }
      }
    }

    if (!distances.containsKey(endId)) {
      return List.of("Маршрут не найден");
    }

    List<String> routeInstructions =
        new ArrayList<>();

    Long current = endId;

    while (!current.equals(startId)) {

      String instruction =
          instructions.get(current);

      if (instruction != null) {
        routeInstructions.add(instruction);
      }

      current = previous.get(current);

      if (current == null) {
        return List.of("Маршрут не найден");
      }
    }

    Collections.reverse(routeInstructions);

    routeInstructions.add(
        "Вы прибыли в пункт назначения.");

    return routeInstructions;
  }
}
